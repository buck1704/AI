package ai;

import static Menu.Menu.BASE_URL;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BackTrackingPlus {

    private static int[][] A;
    private static int n;
    private static boolean[] visited;
    private static List<List<Integer>> cycles;
    private static List<List<Integer>> potentialCycles;

    public void solve(String dataFile) {
        try {
            Scanner scanner = new Scanner(new File(dataFile));
            n = scanner.nextInt();
            A = new int[n][n];
            // Đọc dữ liệu của ma trận ở dữ liệu đầu vào vào ma trận A.
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    A[i][j] = scanner.nextInt();
                }
            }
            scanner.close();

            visited = new boolean[n];
            cycles = new ArrayList<>();
            potentialCycles = new ArrayList<>();

            findHamiltonianCycles(0, new ArrayList<>());

            if (cycles.isEmpty()) {
                System.out.println("Không tìm thấy chu trình Hamiltonian!");
            } else {
                System.out.println("Tổng số lượng chu trình Hamiltonian tìm thấy: " + cycles.size());
                System.out.println("Tất cả các chu trình Hamiltonian tìm thấy: ");
                for (List<Integer> cycle : cycles) {
                    for (int i = 0; i < cycle.size(); i++) {
                        System.out.print((cycle.get(i) + 1) + "->");
                    }
                    System.out.println(cycle.get(0) + 1);
                }

                Collections.sort(potentialCycles, (cycle1, cycle2) -> calculateCycleCost(cycle1) - calculateCycleCost(cycle2));

                int numOfOptimalCycles = Math.min(potentialCycles.size(), 3);
                System.out.println("3 chu trình Hamiltonian tiềm năng với tổng chi phí thấp nhất: ");
                for (int i = 0; i < numOfOptimalCycles; i++) {
                    List<Integer> cycle = potentialCycles.get(i);
                    int cycleCost = calculateCycleCost(cycle);
                    System.out.print("Chu trình " + (i + 1) + ": ");
                    for (int j = 0; j < cycle.size(); j++) {
                        System.out.print((cycle.get(j) + 1) + "->");
                    }
                    System.out.println(cycle.get(0) + 1);
                    System.out.println("Chi phí: " + cycleCost);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void findHamiltonianCycles(int currentNode, List<Integer> path) {
        path.add(currentNode);
        visited[currentNode] = true;

        if (path.size() == n) {
            if (A[currentNode][path.get(0)] > 0) {
                List<Integer> cycle = new ArrayList<>(path);
                cycles.add(cycle);
                potentialCycles.add(cycle);
            }
        } else {
            for (int nextNode
                    = 0; nextNode < n; nextNode++) {
                if (A[currentNode][nextNode] > 0 && !visited[nextNode]) {
                    findHamiltonianCycles(nextNode, path);
                }
            }
        }
        visited[currentNode] = false;
        path.remove(path.size() - 1);
    }

    private static int calculateCycleCost(List<Integer> cycle) {
        int cost = 0;
        int size = cycle.size();
        for (int i = 0; i < size; i++) {
            int fromNode = cycle.get(i);
            int toNode = cycle.get((i + 1) % size);
            cost += A[fromNode][toNode];
        }
        return cost;
    }
}
