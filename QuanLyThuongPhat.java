import java.time.LocalDate;
import java.util.Scanner;

public class QuanLyThuongPhat {
    private ThuongPhat[] dsThuongPhat;
    private int soLuong;
    private static final int MAX = 500;
    private Scanner sc = new Scanner(System.in);

    public QuanLyThuongPhat() {
        dsThuongPhat = new ThuongPhat[MAX];
        soLuong = 0;
    }

    // Thêm thưởng phạt
    public void themThuongPhat() {
        if (soLuong >= MAX) {
            System.out.println("Danh sách đã đầy!");
            return;
        }

        System.out.println("\n=== THÊM THƯỞNG/PHẠT ===");
        System.out.print("Mã thuong/phat: ");
        String maTP = sc.nextLine().trim();

        System.out.print("Mã nhân viên: ");
        String maNV = sc.nextLine().trim();

        System.out.print("Loại (Thuong/Phat): ");
        String loai = sc.nextLine().trim();

        System.out.print("Số tiền: ");
        double soTien = sc.nextDouble();
        sc.nextLine();

        System.out.print("Lý do: ");
        String lyDo = sc.nextLine().trim();

        System.out.print("Ngày (yyyy-mm-dd): ");
        String ngayStr = sc.nextLine().trim();
        LocalDate ngay = LocalDate.parse(ngayStr);

        dsThuongPhat[soLuong] = new ThuongPhat(maTP, maNV, soTien, loai, lyDo, ngay);
        soLuong++;
        System.out.println("✓ Thêm thành công!");
    }

    // Xuất danh sách
    public void xuatDanhSach() {
        if (soLuong == 0) {
            System.out.println("Danh sách rỗng!");
            return;
        }

        System.out.println("\n========== DANH SÁCH THƯỞNG PHẠT ==========");
        System.out.println("--------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-10s | %-10s | %-15s | %-20s | %-12s |\n",
                "Ma TP", "Ma NV", "Loai", "So tien", "Ly do", "Ngay");
        System.out.println("--------------------------------------------------------------------------------------------------------");

        for (int i = 0; i < soLuong; i++) {
            if (dsThuongPhat[i] != null) {
                System.out.println("| " + dsThuongPhat[i].toString() + " |");
            }
        }
        System.out.println("--------------------------------------------------------------------------------------------------------");
        System.out.println("Tong so: " + soLuong + " ban ghi");
    }

    // Tìm kiếm theo mã nhân viên
    public void timKiemTheoMaNV() {
        System.out.print("Nhap ma nhan vien: ");
        String maNV = sc.nextLine().trim();

        boolean timThay = false;
        System.out.println("\n=== KET QUA TIM KIEM ===");

        for (int i = 0; i < soLuong; i++) {
            if (dsThuongPhat[i] != null) {
                String ma = dsThuongPhat[i].getMaNV();
                if (ma != null && ma.equals(maNV)) {
                    dsThuongPhat[i].xuat();
                    timThay = true;
                }
            }
        }

        if (!timThay) {
            System.out.println("Khong tim thay thuong/phat cho nhan vien: " + maNV);
        }
    }

    // Tìm kiếm theo loại
    public void timKiemTheoLoai() {
        System.out.print("Nhap loai (Thuong/Phat): ");
        String loai = sc.nextLine().trim();

        boolean timThay = false;
        System.out.println("\n=== DANH SACH " + loai.toUpperCase() + " ===");

        for (int i = 0; i < soLuong; i++) {
            if (dsThuongPhat[i] != null) {
                String loaiItem = dsThuongPhat[i].getLoai();
                if (loaiItem != null && loaiItem.equalsIgnoreCase(loai)) {
                    dsThuongPhat[i].xuat();
                    timThay = true;
                }
            }
        }

        if (!timThay) {
            System.out.println("Khong tim thay!");
        }
    }

    // Thống kê theo nhân viên
    public void thongKeTheoNhanVien() {
        System.out.print("Nhap ma nhan vien: ");
        String maNV = sc.nextLine().trim();

        double tongThuong = 0;
        double tongPhat = 0;

        for (int i = 0; i < soLuong; i++) {
            if (dsThuongPhat[i] != null) {
                String ma = dsThuongPhat[i].getMaNV();
                if (ma != null && ma.equals(maNV)) {
                    String loai = dsThuongPhat[i].getLoai();
                    if (loai != null && loai.equalsIgnoreCase("Thuong")) {
                        tongThuong += dsThuongPhat[i].getSoTien();
                    } else if (loai != null && loai.equalsIgnoreCase("Phat")) {
                        tongPhat += dsThuongPhat[i].getSoTien();
                    }
                }
            }
        }

        System.out.println("\n=== THONG KE THUONG PHAT ===");
        System.out.println("Nhan vien: " + maNV);
        System.out.println("Tong thuong: " + String.format("%,.0f", tongThuong) + " VND");
        System.out.println("Tong phat: " + String.format("%,.0f", tongPhat) + " VND");
        System.out.println("Chenh lech: " + String.format("%,.0f", (tongThuong - tongPhat)) + " VND");
    }

    // Thống kê tổng
    public void thongKeTong() {
        double tongThuong = 0;
        double tongPhat = 0;
        int soThuong = 0;
        int soPhat = 0;

        for (int i = 0; i < soLuong; i++) {
            if (dsThuongPhat[i] != null) {
                String loai = dsThuongPhat[i].getLoai();
                if (loai != null && loai.equalsIgnoreCase("Thuong")) {
                    tongThuong += dsThuongPhat[i].getSoTien();
                    soThuong++;
                } else if (loai != null && loai.equalsIgnoreCase("Phat")) {
                    tongPhat += dsThuongPhat[i].getSoTien();
                    soPhat++;
                }
            }
        }

        System.out.println("\n=== THONG KE TONG ===");
        System.out.println("So luong thuong: " + soThuong + " (Tong: " + String.format("%,.0f", tongThuong) + " VND)");
        System.out.println("So luong phat: " + soPhat + " (Tong: " + String.format("%,.0f", tongPhat) + " VND)");
        System.out.println("Tong chi: " + String.format("%,.0f", (tongThuong + tongPhat)) + " VND");
    }

    // Xóa thưởng phạt
    public void xoaThuongPhat() {
        System.out.print("Nhap ma thuong/phat can xoa: ");
        String ma = sc.nextLine().trim();

        for (int i = 0; i < soLuong; i++) {
            if (dsThuongPhat[i] != null) {
                if (dsThuongPhat[i].getMaTP() != null && dsThuongPhat[i].getMaTP().equals(ma)) {
                    for (int j = i; j < soLuong - 1; j++) {
                        dsThuongPhat[j] = dsThuongPhat[j + 1];
                    }
                    dsThuongPhat[--soLuong] = null;
                    System.out.println("✓ Xoa thanh cong!");
                    return;
                }
            }
        }
        System.out.println("✗ Khong tim thay ma: " + ma);
    }

    // Getters
    public ThuongPhat[] getDsThuongPhat() {
        return dsThuongPhat;
    }

    public int getSoLuong() {
        return soLuong;
    }

    // Menu
    public void menu() {
        int chon;
        do {
            System.out.println("\n===== MENU QUAN LY THUONG PHAT =====");
            System.out.println("1. Them thuong/phat");
            System.out.println("2. Xuat danh sach");
            System.out.println("3. Tim kiem theo ma nhan vien");
            System.out.println("4. Tim kiem theo loai");
            System.out.println("5. Thong ke theo nhan vien");
            System.out.println("6. Thong ke tong");
            System.out.println("7. Xoa thuong/phat");
            System.out.println("0. Thoat");
            System.out.print(">> Chon chuc nang: ");

            while (!sc.hasNextInt()) {
                System.out.print("Vui lòng nhập số: ");
                sc.next();
            }
            chon = sc.nextInt();
            sc.nextLine();

            switch (chon) {
                case 1:
                    themThuongPhat();
                    break;
                case 2:
                    xuatDanhSach();
                    break;
                case 3:
                    timKiemTheoMaNV();
                    break;
                case 4:
                    timKiemTheoLoai();
                    break;
                case 5:
                    thongKeTheoNhanVien();
                    break;
                case 6:
                    thongKeTong();
                    break;
                case 7:
                    xoaThuongPhat();
                    break;
                case 0:
                    System.out.println("Thoat chuong trinh!");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (chon != 0);
    }

    public static void main(String[] args) {
        QuanLyThuongPhat qltp = new QuanLyThuongPhat();
        qltp.menu();
    }
}
