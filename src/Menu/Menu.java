package Menu;

import ai.ACO;
import ai.BackTrackingPlus;
import ai.Backtracking;
import ai.BranchAndBound;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Menu {

    // File dữ liệu mặc định
    private static String DEFAULTDATA = "C:\\Users\\84336\\OneDrive\\Documents\\NetBeansProjects\\Test java\\AI\\src\\data\\DULIEU.INP";
    public static String BASE_URL = "C:\\Users\\84336\\OneDrive\\Documents\\NetBeansProjects\\Test java\\AI\\src\\data\\DULIEU.INP";
    public static String BASE_URL1 = "C:\\Users\\84336\\OneDrive\\Documents\\NetBeansProjects\\Test java\\AI\\src\\data\\DULIEU_1.INP";
    public static String BASE_URL2 = "C:\\Users\\84336\\OneDrive\\Documents\\NetBeansProjects\\Test java\\AI\\src\\data\\DULIEU_2.INP";
    public static String BASE_URL3 = "C:\\Users\\84336\\OneDrive\\Documents\\NetBeansProjects\\Test java\\AI\\src\\data\\DULIEU_3.INP";
    public static String BASE_URL4 = "C:\\Users\\84336\\OneDrive\\Documents\\NetBeansProjects\\Test java\\AI\\src\\data\\DULIEU_4.INP";
    public static String BASE_URL5 = "C:\\Users\\84336\\OneDrive\\Documents\\NetBeansProjects\\Test java\\AI\\src\\data\\DULIEU_5.INP";
    public static String BASE_URL6 = "C:\\Users\\84336\\OneDrive\\Documents\\NetBeansProjects\\Test java\\AI\\src\\data\\DULIEU_6.INP";
    public static String BASE_URL7 = "C:\\Users\\84336\\OneDrive\\Documents\\NetBeansProjects\\Test java\\AI\\src\\data\\DULIEU_7.INP";
    public static String BASE_URL8 = "C:\\Users\\84336\\OneDrive\\Documents\\NetBeansProjects\\Test java\\AI\\src\\data\\DULIEU_8.INP";
    public static String BASE_URL9 = "C:\\Users\\84336\\OneDrive\\Documents\\NetBeansProjects\\Test java\\AI\\src\\data\\DULIEU_9.INP";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("-------------------------------------------------------------------------------------------------");
            System.out.println("--------------------------------------------- MENU ----------------------------------------------");
            System.out.println("1. Giải bằng thuật toán Backtracking");
            System.out.println("2. Giải bằng thuật toán BacktrackingPlus");
            System.out.println("3. Giải bằng thuật toán BranchAndBound");
            System.out.println("4. Giải bằng thuật toán ACO");
            System.out.println("5. Đổi file dulieu.inp");
            System.out.println("0. Thoát");
            System.out.print("==>  Nhập lựa chọn của bạn: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    if (DEFAULTDATA != null) {
                        Backtracking.solveWithBacktracking(DEFAULTDATA);
                    } else {
                        System.out.println("Thông Báo: Vui lòng chọn file dữ liệu trước!");
                    }
                    break;
                case 2:
                    if (DEFAULTDATA != null) {
                        solveWithBacktrackingPlus(DEFAULTDATA);
                    } else {
                        System.out.println("Thông Báo: Vui lòng chọn file dữ liệu trước!");
                    }
                    break;

                case 3:
                    if (DEFAULTDATA != null) {
                        solveWithACO();
                    } else {
                        System.out.println("Thông Báo: Vui lòng chọn file dữ liệu trước!");
                    }
                    break;
               
                case 4:
                    if (DEFAULTDATA != null) {
                        solveWithACO();
                    } else {
                        System.out.println("Thông Báo: Vui lòng chọn file dữ liệu trước!");
                    }
                    break;
                case 5:
                    changeDataFile(scanner);
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Thông Báo: Lựa chọn không hợp lệ. Vui lòng chọn lại!");
            }

            System.out.println();
        }

        scanner.close();
    }

    private static void solveWithBacktrackingPlus(String dataFile) {
        BackTrackingPlus backtrackingPlus = new BackTrackingPlus();
        backtrackingPlus.solve(dataFile);
    }

    private static void solveWithBranchAndBound() {
        if (DEFAULTDATA != null) {
            BranchAndBound.solveWithBranchAndBound(DEFAULTDATA);
        } else {
            System.out.println("Thông Báo: Vui lòng chọn file dữ liệu trước!");
        }
    }

    private static void solveWithACO() {
        ACO aco = new ACO();
        aco.readData(DEFAULTDATA);
        aco.solve();
        aco.printSolution();
    }

    private static void changeDataFile(Scanner scanner) {
    boolean backToMainMenu = false;
    while (!backToMainMenu) {
        System.out.println("\n-------------------------------------- LIST DATA FILE ------------------------------------------");
        System.out.println("\tNhập 1. Chọn file DULIEU.INP với n = 5, không có trọng số");
        System.out.println("\tNhập 2. Chọn file DULIEU_1.INP với n = 7, không có trọng số");
        System.out.println("\tNhập 3. Chọn file DULIEU_2.INP với n = 10, không có trọng số");
        System.out.println("\tNhập 4. Chọn file DULIEU_3.INP với n = 4, có trọng số");
        System.out.println("\tNhập 5. Chọn file DULIEU_4.INP với n = 5, có trọng số");
        System.out.println("\tNhập 6. Chọn file DULIEU_5.INP với n = 10, có trọng số");
        System.out.println("\tNhập 7. Chọn file DULIEU_6.INP với n = 10, có trọng số");
        System.out.println("\tNhập 8. Chọn file DULIEU_7.INP với n = 15, có trọng số");
        System.out.println("\tNhập 9. Chọn file DULIEU_8.INP với n = 15, có trọng số");
        System.out.println("\tNhập 10. Chọn file DULIEU_9.INP với n = 20, có trọng số");
        System.out.println("\tNhập 11. Hiển thị ma trận đang được lựa chọn");
        System.out.println("\tNhập 0. Trở lại menu chính");
        System.out.print("\t ==> Nhập lựa chọn của bạn: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 0:
                backToMainMenu = true;
                break;
            case 1:
                DEFAULTDATA = BASE_URL;
                break;
            case 2:
                DEFAULTDATA = BASE_URL1;
                break;
            case 3:
                DEFAULTDATA = BASE_URL2;
                break;
            case 4:
                DEFAULTDATA = BASE_URL3;
                break;
            case 5:
                DEFAULTDATA = BASE_URL4;
                break;
            case 6:
                DEFAULTDATA = BASE_URL5;
                break;
            case 7:
                DEFAULTDATA = BASE_URL6;
                break;
            case 8:
                DEFAULTDATA = BASE_URL7;
                break;
            case 9:
                DEFAULTDATA = BASE_URL8;
                break;
            case 10:
                DEFAULTDATA = BASE_URL9;
                break;
            case 11:
                displayDataFile(DEFAULTDATA);
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ.");
                break;
        }

        if (!backToMainMenu) {
            System.out.println("Đã đổi file dữ liệu thành: " + DEFAULTDATA);
        }
    }
}

    private static void displayDataFile(String filePath) {
        try {
            Scanner fileScanner = new Scanner(new File(filePath));
            int size = fileScanner.nextInt();
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("          Kích thước: " + size + " * " + size);
            System.out.println("          Phân loại:  " + determineMatrixType(fileScanner));
            System.out.println("                            Ma trận");
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (!line.isEmpty()) {
                    System.out.println("                          " + line);
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Không tìm thấy file dữ liệu: " + filePath);
        }
    }

    private static String determineMatrixType(Scanner fileScanner) {
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            if (!line.isEmpty()) {
                String[] elements = line.trim().split(" ");
                for (String element : elements) {
                    if (!element.equals("0") && !element.equals("1")) {
                        return "có trọng số";
                    }
                }
                return "không có trọng số";
            }
        }
        return "Không xác định";
    }
}
