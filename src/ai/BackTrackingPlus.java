package ai;

import static ai.AI.BASE_URL;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BackTrackingPlus {

    private static int dem = 0;                                                 // Biến đếm số lượng chu trình

    public static void main(String[] args) {
        try {

            Scanner scanner = new Scanner(new File(BASE_URL));
            int n = scanner.nextInt();
            int[][] A = new int[n][n];
            // Đọc dữ liệu của ma trận ở dữ liệu đầu vào vào ma trận A.
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    A[i][j] = scanner.nextInt();
                }
            }
            scanner.close();

            boolean[] visited = new boolean[n];
            int[] path = new int[n];

            path[0] = 0;                                                        // Bắt đầu từ đỉnh 0
            visited[0] = true;                                                  // Đánh dấu đỉnh 0 đã được thăm
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

    // visited: đánh dấu các thành phố đã thăm
    // path:    lưu lộ trình
    // pos:     vị trí hiện tại trong lộ trình
    // n:       tổng các thành phố
    private static void findHamiltonCycles(int[][] A, boolean[] visited, int[] path, int pos, int n) {
        /* điều kiện dừng đệ quy của thuật toán. Khi pos (vị trí hiện tại trong lộ trình) bằng n (tổng số thành phố), 
         tức là đã duyệt qua tất cả các thành phố trong lộ trình, thuật toán kiểm tra xem thành phố cuối cùng có kết 
         nối với thành phố đầu tiên hay không.    */
        if (pos == n) {
            if (A[path[pos - 1]][0] > 0) {                                     // Kiểm tra xem đỉnh cuối cùng có kết nối với đỉnh đầu tiên hay không
                System.out.print("Đường đi Hamilton: ");
                for (int i = 0; i < n; i++) {
                    System.out.print((path[i] + 1) + "->"); // In ra chu trình Hamilton
                }
                System.out.println(path[0] + 1);
                dem++; // Tăng biến đếm số lượng chu trình
            }
            return;
        }
        for (int i = 1; i < n; i++) {
            if (A[path[pos - 1]][i] > 0 && !visited[i]) {
                path[pos] = i;
                visited[i] = true;
                findHamiltonCycles(A, visited, path, pos + 1, n);
                visited[i] = false;
            }
        }
    }
}
