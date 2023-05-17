package ai;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class warnsdorff {

    private static int dem = 0;

    public void solve(String dataFile) {
    try {
        Scanner scanner = new Scanner(new File(dataFile));
            int n = scanner.nextInt();
            int[][] A = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    A[i][j] = scanner.nextInt();
                }
            }
            scanner.close();

            boolean[] visited = new boolean[n];
            int[] path = new int[n];
            path[0] = 0;
            visited[0] = true;
            findHamiltonCycles(A, visited, path, 1, n);

            if (dem == 0) {
                System.out.println("Không tìm thấy chu trình Hamilton!");
            } else {
                System.out.println("=> Tìm thấy " + dem + " chu trình");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void findHamiltonCycles(int[][] A, boolean[] visited, int[] path, int pos, int n) {
        if (pos == n) {
            if (A[path[pos - 1]][0] == 1) {
                System.out.print("Đường đi Hamilton: ");
                for (int i = 0; i < n; i++) {
                    System.out.print((path[i] + 1) + "->");
                }
                System.out.println(path[0] + 1);
                dem++;
            }
            return;
        }

        int[] sortedNeighbors = sortNeighborsByDegree(A, visited, path, pos, n);

        for (int neighbor : sortedNeighbors) {
            if (!visited[neighbor]) {
                path[pos] = neighbor;
                visited[neighbor] = true;
                findHamiltonCycles(A, visited, path, pos + 1, n);
                visited[neighbor] = false;
            }
        }
    }

    private static int[] sortNeighborsByDegree(int[][] A, boolean[] visited, int[] path, int pos, int n) {
        int[] neighbors = new int[n];
        int[] degreeCount = new int[n];

        int currentVertex = path[pos - 1];

        int neighborCount = 0;
        for (int i = 0; i < n; i++) {
            if (A[currentVertex][i] == 1 && !visited[i]) {
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

        // Sort neighbors by degree count
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
