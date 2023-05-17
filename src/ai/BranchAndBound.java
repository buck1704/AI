package ai;

import static Menu.Menu.BASE_URL;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BranchAndBound {

    private int[][] A;
    private boolean[] visited;
    private int[] path;
    private int n;
    private List<List<Integer>> optimalCycles;
    private List<Integer> cycleCosts;

    public BranchAndBound(int[][] A) {
        this.A = A;
        this.n = A.length;
        this.visited = new boolean[n];
        this.path = new int[n];
        this.optimalCycles = new ArrayList<>();
        this.cycleCosts = new ArrayList<>();
    }

    public static void solveWithBranchAndBound(String dataFile) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(dataFile));

            int n = Integer.parseInt(reader.readLine());
            int[][] A = new int[n][n];

            for (int i = 0; i < n; i++) {
                String[] row = reader.readLine().split(" ");
                for (int j = 0; j < n; j++) {
                    A[i][j] = Integer.parseInt(row[j]);
                }
            }

            reader.close();

            BranchAndBound branchAndBound = new BranchAndBound(A);
            List<List<Integer>> cycles = branchAndBound.findHamiltonianCycles();

            if (cycles.isEmpty()) {
                System.out.println("Không tồn tại chu trình Hamilton.");
            } else {
                System.out.println("Tất cả các chu trình Hamilton:");
                for (int i = 0; i < cycles.size(); i++) {
                    List<Integer> cycle = cycles.get(i);
                    int cycleCost = branchAndBound.cycleCosts.get(i);
                    System.out.println("Chu trình " + (i + 1) + ": " + cycle);
                    System.out.println("Chi phí: " + cycleCost);
                }

                System.out.println("Top 3 chu trình tối ưu nhất:");
                List<Integer> sortedCosts = new ArrayList<>(branchAndBound.cycleCosts);
                Collections.sort(sortedCosts);
                for (int i = 0; i < Math.min(3, cycles.size()); i++) {
                    int minCost = sortedCosts.get(i);
                    int minCostIndex = branchAndBound.cycleCosts.indexOf(minCost);
                    List<Integer> minCostCycle = cycles.get(minCostIndex);
                    System.out.println("Chu trình " + (i + 1) + ": " + minCostCycle);
                    System.out.println("Chi phí: " + minCost);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<List<Integer>> findHamiltonianCycles() {
        visited[0] = true;
        path[0] = 0;
        branchAndBoundHamiltonianCycle(1, 0, 0);

        return optimalCycles;
    }

    private void branchAndBoundHamiltonianCycle(int v, int cost, int level) {
        if (v == n && A[path[v - 1]][path[0]] != 0) {
            // Tìm thấy chu trình Hamilton
            List<Integer> cycle = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                cycle.add(path[i] + 1);  // +1 để chuyển từ chỉ số 0-based sang 1-based
            }
            cycle.add(path[0] + 1);
            optimalCycles.add(cycle);
            cycleCosts.add(cost + A[path[v - 1]][path[0]]);
        } else {
            for (int i = 1; i < n; i++) {
                if (isSafe(i, v)) {
                    path[v] = i;
                    visited[i] = true;

                    if (level + 1 < n && lowerBound(v, cost) < calculateMinCost()) {
                        branchAndBoundHamiltonianCycle(v + 1, cost + A[path[v - 1]][path[v]], level + 1);
                    } else if (v == n) {
                        branchAndBoundHamiltonianCycle(v + 1, cost + A[path[v - 1]][path[v]], level);
                    }

                    visited[i] = false;
                }
            }
        }
    }

    private boolean isSafe(int v, int pos) {
        if (A[path[pos - 1]][v] == 0 || visited[v]) {
            return false;
        }
        return true;
    }

    private int calculateMinCost() {
        int minCost = Integer.MAX_VALUE;
        for (int cost : cycleCosts) {
            if (cost < minCost) {
                minCost = cost;
            }
        }
        return minCost;
    }

    private int lowerBound(int v, int cost) {
        int lb = cost;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int minEdgeCost = Integer.MAX_VALUE;
                for (int j = 0; j < n; j++) {
                    if (A[i][j] < minEdgeCost) {
                        minEdgeCost = A[i][j];
                    }
                }
                lb += minEdgeCost;
            }
        }
        return lb;
    }

}
