import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class QuanLyPhongBan {
    private Scanner sc = new Scanner(System.in);
    private PhongBan[] dsPhongBan = new PhongBan[100];
    private int soluong = 0;

    // ===== Thêm phòng ban =====
    public void themPhongBan(PhongBan pb) {
        if (soluong < dsPhongBan.length) {
            dsPhongBan[soluong++] = pb;
            System.out.println("Đã thêm phòng ban thành công");
        } else {
            System.out.println("Danh sách đã đầy");
        }
    }

    // ===== Hiển thị danh sách =====
    public void hienThiDS() {
        if (soluong == 0) {
            System.out.println("Danh sách phòng ban trống");
        } else {
            System.out.println("\n--- DANH SÁCH PHÒNG BAN ---");
            for (int i = 0; i < soluong; i++) {
                System.out.println(dsPhongBan[i]);
            }
        }
    }

    // ===== Sửa phòng ban =====
    public void suaPhongBan(String mapb, String tenmoi, String maqlmoi, String ngaymoi, String duAnMoi) {
        for (int i = 0; i < soluong; i++) {
            if (dsPhongBan[i].getMapb().equalsIgnoreCase(mapb)) {
                dsPhongBan[i].setTenpb(tenmoi);
                dsPhongBan[i].setMaql(maqlmoi);
                dsPhongBan[i].setNgaythanhlap(ngaymoi);
                dsPhongBan[i].setDuAn(duAnMoi);
                System.out.println("Đã sửa thông tin phòng ban");
                return;
            }
        }
        System.out.println("Không tìm thấy mã phòng ban: " + mapb);
    }

    // ===== Xóa phòng ban =====
    public void xoaPhongBan(String mapb) {
        for (int i = 0; i < soluong; i++) {
            if (dsPhongBan[i].getMapb().equalsIgnoreCase(mapb)) {
                for (int j = i; j < soluong - 1; j++) {
                    dsPhongBan[j] = dsPhongBan[j + 1];
                }
                dsPhongBan[--soluong] = null;
                System.out.println("Đã xóa phòng ban");
                return;
            }
        }
        System.out.println("Không tìm thấy mã phòng ban: " + mapb);
    }

    // ===== THỐNG KÊ PHÒNG BAN =====

    // 1️⃣ Thống kê số lượng phòng ban
    public void thongKeSoLuong() {
        System.out.println("Tổng số phòng ban: " + soluong);
    }

    // 2️⃣ Thống kê theo năm thành lập
    public void thongKeTheoNam() {
        Map<String, Integer> thongKeNam = new HashMap<>();
        for (int i = 0; i < soluong; i++) {
            String nam = dsPhongBan[i].getNgaythanhlap().substring(0, 4); // giả sử định dạng yyyy-MM-dd
            thongKeNam.put(nam, thongKeNam.getOrDefault(nam, 0) + 1);
        }

        System.out.println("\n--- THỐNG KÊ THEO NĂM THÀNH LẬP ---");
        for (Map.Entry<String, Integer> entry : thongKeNam.entrySet()) {
            System.out.println("Năm " + entry.getKey() + ": " + entry.getValue() + " phòng ban");
        }
    }

    // 3️⃣ Thống kê theo dự án
    public void thongKeTheoDuAn(String tenDuAn) {
        int dem = 0;
        for (int i = 0; i < soluong; i++) {
            if (dsPhongBan[i].getDuAn().equalsIgnoreCase(tenDuAn)) {
                dem++;
            }
        }
        System.out.println("\nPhòng ban tham gia dự án '" + tenDuAn + "': " + dem);
    }
    public void menu() {
        int chon;
        do {
            System.out.println("\n===== MENU QUẢN LÝ PHÒNG BAN =====");
            System.out.println("1. Thêm phòng ban");
            System.out.println("2. Hiển thị danh sách phòng ban");
            System.out.println("3. Sửa thông tin phòng ban");
            System.out.println("4. Xóa phòng ban");
            System.out.println("5. Thống kê số lượng phòng ban");
            System.out.println("6. Thống kê theo năm thành lập");
            System.out.println("7. Thống kê theo dự án");
            System.out.println("0. Thoát");
            System.out.print("👉 Chọn chức năng: ");
            chon = Integer.parseInt(sc.nextLine());

            switch (chon) {
                case 1:
                    System.out.print("Nhập mã phòng ban: ");
                    String mapb = sc.nextLine();
                    System.out.print("Nhập mã quản lý: ");
                    String maql = sc.nextLine();
                    System.out.print("Nhập tên phòng ban: ");
                    String tenpb = sc.nextLine();
                    System.out.print("Nhập ngày thành lập (yyyy-MM-dd): ");
                    String ngay = sc.nextLine();
                    System.out.print("Nhập tên dự án: ");
                    String duan = sc.nextLine();
                    themPhongBan(new PhongBan(mapb, maql, tenpb, ngay, duan));
                    break;

                case 2:
                    hienThiDS();
                    break;

                case 3:
                    System.out.print("Nhập mã phòng ban cần sửa: ");
                    String m1 = sc.nextLine();
                    System.out.print("Nhập tên mới: ");
                    String tenmoi = sc.nextLine();
                    System.out.print("Nhập mã quản lý mới: ");
                    String maqlmoi = sc.nextLine();
                    System.out.print("Nhập ngày thành lập mới: ");
                    String ngaymoi = sc.nextLine();
                    System.out.print("Nhập dự án mới: ");
                    String duanmoi = sc.nextLine();
                    suaPhongBan(m1, tenmoi, maqlmoi, ngaymoi, duanmoi);
                    break;

                case 4:
                    System.out.print("Nhập mã phòng ban cần xóa: ");
                    String m2 = sc.nextLine();
                    xoaPhongBan(m2);
                    break;

                case 5:
                    thongKeSoLuong();
                    break;

                case 6:
                    thongKeTheoNam();
                    break;

                case 7:
                    System.out.print("Nhập tên dự án cần thống kê: ");
                    String tenDA = sc.nextLine();
                    thongKeTheoDuAn(tenDA);
                    break;

                case 0:
                    System.out.println("👋 Thoát chương trình.");
                    break;

                default:
                    System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        } while (chon != 0);
    }
}

