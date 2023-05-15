package ai;

import java.util.Arrays;

public class TSP {
    private static final int MAX_DISTANCE = 1000000;

    public static void main(String[] args) {
        int[][] graph = {
            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
        };

        int[] path = tsp(graph);
        System.out.println("Shortest Path: " + Arrays.toString(path));
    }

    public static int[] tsp(int[][] graph) {
        int n = graph.length;
        boolean[] visited = new boolean[n];
        visited[0] = true;
        int[] path = new int[n];
        path[0] = 0;

        for (int i = 1; i < n; i++) {
            int next = findNext(visited, graph, path, i);
            path[i] = next;
            visited[next] = true;
        }

        return path;
    }

    private static int findNext(boolean[] visited, int[][] graph, int[] path, int pos) {
        int n = graph.length;
        int current = path[pos - 1];
        int minDistance = MAX_DISTANCE;
        int next = -1;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int heuristicDistance = calculateHeuristicDistance(i, visited, graph);
                if (heuristicDistance < minDistance) {
                    minDistance = heuristicDistance;
                    next = i;
                }
            }
        }

        if (next == -1) {
            for (int i = 0; i < n; i++) {
                if (!visited[i] && graph[current][i] < minDistance) {
                    minDistance = graph[current][i];
                    next = i;
                }
            }
        }

        return next;
    }

    private static int calculateHeuristicDistance(int node, boolean[] visited, int[][] graph) {
        int n = graph.length;
        int minDistance = MAX_DISTANCE;

        for (int i = 0; i < n; i++) {
            if (visited[i] && graph[i][node] < minDistance) {
                minDistance = graph[i][node];
            }
        }

        return minDistance;
    }
}
