package ai;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

   public class HeuristicSolver {
    private int[][] graph;
    private boolean[] visited;
    private int[] path;
    private int numVertices;
    private int pathIndex;

    public HeuristicSolver(int[][] graph) {
        this.graph = graph;
        this.numVertices = graph.length;
        this.visited = new boolean[numVertices];
        this.path = new int[numVertices];
        this.pathIndex = 0;
    }

    public void solve() {
        // Chọn đỉnh xuất phát ngẫu nhiên
        int startVertex = (int) (Math.random() * numVertices);

        // Đánh dấu đỉnh xuất phát là đã thăm
        visited[startVertex] = true;
        path[pathIndex++] = startVertex;

        // Tìm lộ trình gần đúng bằng thuật toán Nearest Neighbor
        while (pathIndex < numVertices) {
            int currentVertex = path[pathIndex - 1];
            int nextVertex = getNearestNeighbor(currentVertex);
            visited[nextVertex] = true;
            path[pathIndex++] = nextVertex;
        }

        // Kiểm tra xem lộ trình có thành chu trình Hamilton không
        if (graph[path[pathIndex - 1]][startVertex] == 1) {
            path[pathIndex] = startVertex;
            printHamiltonCycle();
        } else {
            System.out.println("Không tìm thấy chu trình Hamilton gần đúng!");
        }
    }

    private int getNearestNeighbor(int vertex) {
        int nearestNeighbor = -1;
        int shortestDistance = Integer.MAX_VALUE;

        for (int i = 0; i < numVertices; i++) {
            if (!visited[i] && graph[vertex][i] == 1 && graph[vertex][i] < shortestDistance) {
                nearestNeighbor = i;
                shortestDistance = graph[vertex][i];
            }
        }

        return nearestNeighbor;
    }

    private void printHamiltonCycle() {
        System.out.print("Đường đi Hamilton gần đúng: ");
        for (int i = 0; i <= pathIndex; i++) {
            System.out.print(path[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] graph = {
            {0, 1, 0, 1, 0},
            {1, 0, 1, 1, 0},
            {0, 1, 0, 1, 1},
            {1, 1, 1, 0, 1},
            {0, 0, 1, 1, 0}
        };

        HeuristicSolver solver = new HeuristicSolver(graph);
        solver.solve();
    }
}
