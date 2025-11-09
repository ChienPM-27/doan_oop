import java.util.Scanner;

public class QuanLyBangLuong {

    private BangLuong[] bl = new BangLuong[100];
    private int soLuong = 0;
    private DocGhiFile docGhiFile = new DocGhiFile();

    public void menu() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("========== MENU QUẢN LÝ BẢNG LƯƠNG ==========");
            System.out.println("1. Đọc dữ liệu từ file");
            System.out.println("2. Tìm kiếm bảng lương theo mã nhân viên");
            System.out.println("3. Tìm kiếm bảng lương theo tháng và năm");
            System.out.println("4. Thống kê số lượng bảng lương");
            System.out.println("5. Tính tổng lương theo tháng và năm");
            System.out.println("6. In toàn bộ bảng lương");
            System.out.println("0. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            // đảm bảo nhập số hợp lệ
            while (!sc.hasNextInt()) {
                System.out.print("Vui lòng nhập số: ");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    docDuLieu();
                }
                case 2 -> {
                    System.out.print("Nhập mã nhân viên cần tìm: ");
                    String maNV = sc.nextLine().trim();
                    BangLuong[] ketQuaTimKiemNV = BangLuong.timKiemTheoMaNV(bl, soLuong, maNV);
                    if (ketQuaTimKiemNV != null && ketQuaTimKiemNV.length > 0) {
                        BangLuong.inBangLuong(ketQuaTimKiemNV, ketQuaTimKiemNV.length);
                    } else {
                        System.out.println("Không tìm thấy bảng lương cho mã nhân viên: " + maNV);
                    }
                }
                case 3 -> {
                    System.out.print("Nhập tháng cần tìm: ");
                    int thang = sc.nextInt();
                    System.out.print("Nhập năm cần tìm: ");
                    int nam = sc.nextInt();
                    sc.nextLine();
                    BangLuong[] ketQuaTimKiemThangNam = BangLuong.timKiemTheoThangNam(bl, soLuong, thang, nam);
                    if (ketQuaTimKiemThangNam != null && ketQuaTimKiemThangNam.length > 0) {
                        BangLuong.inBangLuong(ketQuaTimKiemThangNam, ketQuaTimKiemThangNam.length);
                    } else {
                        System.out.println("Không tìm thấy bảng lương cho " + thang + "/" + nam);
                    }
                }
                case 4 -> {
                    System.out.println("Số lượng bảng lương: " + BangLuong.demSoLuongBangLuong(bl, soLuong));
                }
                case 5 -> {
                    System.out.print("Nhập tháng cần tính tổng lương: ");
                    int thangTinh = sc.nextInt();
                    System.out.print("Nhập năm cần tính tổng lương: ");
                    int namTinh = sc.nextInt();
                    sc.nextLine();
                    double tongLuong = BangLuong.tinhTongLuongTheoThangNam(bl, soLuong, thangTinh, namTinh);
                    System.out.println("Tổng lương tháng " + thangTinh + "/" + namTinh + ": " + String.format("%,.0f", tongLuong) + " VND");
                }
                case 6 -> {
                    BangLuong.inBangLuong(bl, soLuong);
                }
                case 0 -> {
                    System.out.println("Thoát chương trình.");
                }
                default -> {
                    System.out.println("Lựa chọn không hợp lệ.");
                }
            }

            System.out.println(); // blank line for readability
        } while (choice != 0);

        sc.close();
    }

    public void docDuLieu() {
        Object[] data = docGhiFile.docFile("bangluong.dat");
        if (data != null) {
            bl = new BangLuong[data.length];
            for (int i = 0; i < data.length; i++) {
                bl[i] = (BangLuong) data[i];
            }
            soLuong = data.length;
            System.out.println("Đọc dữ liệu từ file thành công. Số bản ghi: " + soLuong);
        } else {
            System.out.println("Không thể đọc dữ liệu từ file.");
        }
    }

    public static void main(String[] args) {
        QuanLyBangLuong qlbl = new QuanLyBangLuong();
        qlbl.menu();
    }
}
