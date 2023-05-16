package ai;

import static ai.AI.BASE_URL;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HamiltonianCycle {

    private static final int MAX_ITERATIONS = 100; // Số lần lặp tối đa
    private static final int NUM_ANTS = 10; // Số con kiến
    private static final double ALPHA = 1.0; // Tham số alpha
    private static final double BETA = 2.0; // Tham số beta
    private static final double RHO = 0.5; // Tỷ lệ bay hơi
    private static final double Q = 100.0; // Tham số Q
    private static final double INITIAL_PHEROMONE = 1.0; // Mức pheromone ban đầu

    private int n; // Số thành phố
    private int[][] A; // Ma trận giao thông

    private double[][] pheromone; // Ma trận pheromone
    private int[] bestPath; // Đường đi tốt nhất
    private double bestLength; // Độ dài đường đi tốt nhất

    public static void main(String[] args) {
        HamiltonianCycle hc = new HamiltonianCycle();
        hc.readData(BASE_URL);
        hc.solve();
        hc.printSolution();
    }

    public void readData(String filePath) {
        try {
            Scanner scanner = new Scanner(new File(filePath));

            n = scanner.nextInt();
            A = new int[n][n];
            pheromone = new double[n][n];
            bestPath = new int[n];
            bestLength = Double.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    A[i][j] = scanner.nextInt();
                    pheromone[i][j] = INITIAL_PHEROMONE;
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void solve() {
        for (int iteration = 0; iteration < MAX_ITERATIONS; iteration++) {
            List<int[]> antPaths = new ArrayList<>();

            // Tạo con kiến và tìm đường đi của chúng
            for (int ant = 0; ant < NUM_ANTS; ant++) {
                int[] path = constructSolution();
                antPaths.add(path);
            }

            // Cập nhật mức pheromone
            updatePheromone(antPaths);

            // Tìm đường đi tốt nhất trong lần lặp
            for (int i = 0; i < NUM_ANTS; i++) {
                int[] path = antPaths.get(i);
                double length = calculateLength(path);

                if (length < bestLength) {
                    bestLength = length;
                    System.arraycopy(path, 0, bestPath, 0, n);
                }
            }

            // Bay hơi pheromone
            evaporatePheromone();

            // Cập nhật pheromone dựa trên đường đi tốt nhất
            updateBestPathPheromone();

            // In thông tin đường đi tốt nhất tại lần lặp hiện tại (tùy chọn)
            System.out.println("Iteration " + (iteration + 1) + ": Best Length = " + bestLength);
        }
    }

    // Tạo đường đi cho một con kiến
    private int[] constructSolution() {
        int[] path = new int[n];
        boolean[] visited = new boolean[n];
        int start = 0; // Thành phố xuất phát

        path[0] = start;
        visited[start] = true;

        for (int i = 1; i < n; i++) {
            int nextCity = selectNextCity(start, visited);
            path[i] = nextCity;
            visited[nextCity] = true;
            start = nextCity;
        }

        return path;
    }

    // Lựa chọn thành phố tiếp theo dựa trên mức pheromone và heuristic
    private int selectNextCity(int currentCity, boolean[] visited) {
        double[] probabilities = new double[n];
        double sum = 0.0;

        // Tính tổng các xác suất chuyển pheromone và heuristic
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                probabilities[i] = Math.pow(pheromone[currentCity][i], ALPHA)
                        * Math.pow((1.0 / A[currentCity][i]), BETA);
                sum += probabilities[i];
            }
        }

        // Lựa chọn thành phố dựa trên xác suất
        double random = Math.random();
        double cumulativeProbability = 0.0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                cumulativeProbability += probabilities[i] / sum;

                if (random <= cumulativeProbability) {
                    return i;
                }
            }
        }

        // Trường hợp không có thành phố nào thỏa mãn, trả về thành phố bất kỳ chưa được thăm
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                return i;
            }
        }

        return -1; // Không tìm thấy thành phố thích hợp (nếu mọi thành phố đã được thăm)
    }

    // Cập nhật mức pheromone dựa trên các con kiến
    private void updatePheromone(List<int[]> antPaths) {
        for (int[] path : antPaths) {
            double deltaPheromone = Q / calculateLength(path);

            for (int i = 0; i < n - 1; i++) {
                int city1 = path[i];
                int city2 = path[i + 1];
                pheromone[city1][city2] += deltaPheromone;
                pheromone[city2][city1] += deltaPheromone;
            }

            // Cập nhật pheromone cho cạnh cuối cùng (quay lại thành phố xuất phát)
            int lastCity = path[n - 1];
            int firstCity = path[0];
            pheromone[lastCity][firstCity] += deltaPheromone;
            pheromone[firstCity][lastCity] += deltaPheromone;
        }
    }

    // Bay hơi pheromone
    private void evaporatePheromone() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                pheromone[i][j] *= (1.0 - RHO);
            }
        }
    }

    // Cập nhật mức pheromone dựa trên đường đi tốt nhất
    private void updateBestPathPheromone() {
        double deltaPheromone = Q / bestLength;

        for (int i = 0; i < n - 1; i++) {
            int city1 = bestPath[i];
            int city2 = bestPath[i + 1];
            pheromone[city1][city2] += deltaPheromone;
            pheromone[city2][city1] += deltaPheromone;
        }

        // Cập nhật pheromone cho cạnh cuối cùng (quay lại thành phố xuất phát)
        int lastCity = bestPath[n - 1];
        int firstCity = bestPath[0];
        pheromone[lastCity][firstCity] += deltaPheromone;
        pheromone[firstCity][lastCity] += deltaPheromone;
    }

    // Tính độ dài của đường đi
    private double calculateLength(int[] path) {
        double length = 0.0;

        for (int i = 0; i < n - 1; i++) {
            int city1 = path[i];
            int city2 = path[i + 1];
            length += A[city1][city2];
        }

        // Tính chiều dài cạnh cuối cùng (quay lại thành phố xuất phát)
        int lastCity = path[n - 1];
        int firstCity = path[0];
        length += A[lastCity][firstCity];

        return length;
    }

    // In kết quả
    public void printSolution() {
        if (bestLength == Double.MAX_VALUE) {
            System.out.println("Không tìm thấy chu trình Hamilton!");
        } else {
            System.out.print("Chu trình Hamilton: ");
            for (int city : bestPath) {
                System.out.print(city + " -> ");
            }
            System.out.println(bestPath[0]); // In thành phố xuất phát để hoàn thành chu trình
            System.out.println("Độ dài: " + bestLength);
        }
    }
}

