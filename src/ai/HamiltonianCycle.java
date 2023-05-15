package ai;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class HamiltonianCycle {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("C:\\Users\\84336\\OneDrive\\Documents\\NetBeansProjects\\Test java\\AI\\src\\ai\\DULIEU.INP"));

            int n = scanner.nextInt();
            int[][] A = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    A[i][j] = scanner.nextInt();
                }
            }
            scanner.close();

            List<Integer> path = findHamiltonianCycle(A);
            if (path == null) {
                System.out.println("Không tìm thấy chu trình Hamilton!");
            } else {
                System.out.println("Chu trình Hamilton: " + path);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static List<Integer> findHamiltonianCycle(int[][] A) {
        int n = A.length;
        boolean[] visited = new boolean[n];
        List<Integer> path = new ArrayList<>();
        path.add(0);

        boolean foundCycle = exploreHamiltonianPath(A, visited, path, 0, 1, n);

        if (foundCycle && A[path.get(n - 1)][0] == 1) {
            return path;
        } else {
            return null; // Không tìm thấy chu trình Hamilton
        }
    }

    private static boolean exploreHamiltonianPath(int[][] A, boolean[] visited, List<Integer> path, int current, int count, int n) {
        if (count == n) {
            return true;
        }

        int[] sortedNeighbors = sortNeighborsByDegree(A, visited, current, n);

        for (int neighbor : sortedNeighbors) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                path.add(neighbor);

                if (exploreHamiltonianPath(A, visited, path, neighbor, count + 1, n)) {
                    return true;
                }

                visited[neighbor] = false;
                path.remove(path.size() - 1);
            }
        }

        return false;
    }

    private static int[] sortNeighborsByDegree(int[][] A, boolean[] visited, int current, int n) {
        int[] neighbors = new int[n];
        int[] degreeCount = new int[n];

        int neighborCount = 0;
        for (int i = 0; i < n; i++) {
            if (A[current][i] == 1 && !visited[i]) {
                neighbors[neighborCount] = i;
                neighborCount++;
            }
        }

        for (int neighbor : neighbors) {
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (A[neighbor][i] == 1 && !visited[i]) {
                    count++;
                }
            }
            degreeCount[neighbor] = count;
        }

        // Sắp xếp đỉnh kề theo số đỉnh kề tăng dần
        for (int i = 0; i < neighborCount - 1; i++) {
            for (int j = i + 1; j < neighborCount; j++) {
                if (degreeCount[neighbors[j]] < degreeCount[neighbors[i]]) {
                    int temp = neighbors[i];
                    neighbors[i] = neighbors[j];
                    neighbors[j] = temp;
                }
            }
        }

        return neighbors;
    }
}
