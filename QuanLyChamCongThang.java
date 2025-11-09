import java.util.Scanner;

public class QuanLyChamCongThang {
    private Chamcongthang[] chamcong;
    private int soluong;
    private static final int MAX = 500;
    private Scanner sc = new Scanner(System.in);

    public QuanLyChamCongThang() {
        chamcong = new Chamcongthang[MAX];
        soluong = 0;
    }

    // Them cham cong thang
    public void themChamCongThang() {
        if (soluong >= MAX) {
            System.out.println("Danh sach da day!");
            return;
        }

        System.out.println("\n=== THEM CHAM CONG THANG ===");
        System.out.print("Ma cong thang: ");
        String macongthang = sc.nextLine();

        System.out.print("Ma nhan vien: ");
        String manv = sc.nextLine();

        System.out.print("Thang: ");
        int thang = sc.nextInt();

        System.out.print("Nam: ");
        int nam = sc.nextInt();

        System.out.print("So ngay cong: ");
        int songaycong = sc.nextInt();
        sc.nextLine();

        chamcong[soluong] = new Chamcongthang(macongthang, thang, nam, songaycong, manv);
        soluong++;
        System.out.println("✓ Them cham cong thang thanh cong!");
    }

    // Xuat danh sach
    public void xuatDanhSach() {
        if (soluong == 0) {
            System.out.println("Danh sach cham cong thang rong!");
            return;
        }

        System.out.println("\n========== DANH SACH CHAM CONG THANG ==========");
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.printf("| %-15s | %-10s | %-8s | %-10s | %-10s |\n",
                "Ma cong thang", "Ma NV", "Thang/Nam", "Ngay cong", "Ngay nghi");
        System.out.println("-----------------------------------------------------------------------------------");

        for (int i = 0; i < soluong; i++) {
            System.out.println("| " + chamcong[i].toString() + " |");
        }
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("Tong so: " + soluong + " ban ghi");
    }

    // Tim kiem theo ma nhan vien
    public void timKiemTheoMaNV() {
        System.out.print("Nhap ma nhan vien can tim: ");
        String manv = sc.nextLine();

        boolean timThay = false;
        System.out.println("\n=== KET QUA TIM KIEM ===");

        for (int i = 0; i < soluong; i++) {
            if (chamcong[i].getManv().equals(manv)) {
                chamcong[i].xuat();
                timThay = true;
            }
        }

        if (!timThay) {
            System.out.println("Khong tim thay cham cong cua nhan vien: " + manv);
        }
    }

    // Tim kiem theo thang nam
    public void timKiemTheoThangNam() {
        System.out.print("Nhap thang: ");
        int thang = sc.nextInt();
        System.out.print("Nhap nam: ");
        int nam = sc.nextInt();
        sc.nextLine();

        boolean timThay = false;
        System.out.println("\n=== CHAM CONG THANG " + thang + "/" + nam + " ===");

        for (int i = 0; i < soluong; i++) {
            if (chamcong[i].getThang() == thang && chamcong[i].getNam() == nam) {
                chamcong[i].xuat();
                timThay = true;
            }
        }

        if (!timThay) {
            System.out.println("Khong co du lieu cham cong thang " + thang + "/" + nam);
        }
    }

    // Cap nhat so ngay cong
    public void capNhatSoNgayCong() {
        System.out.print("Nhap ma cong thang can cap nhat: ");
        String ma = sc.nextLine();

        for (int i = 0; i < soluong; i++) {
            if (chamcong[i].getMacongthang().equals(ma)) {
                System.out.println("So ngay cong hien tai: " + chamcong[i].getSongaycong());
                System.out.print("Nhap so ngay cong moi: ");
                int songaycongmoi = sc.nextInt();
                sc.nextLine();

                chamcong[i].setSongaycong(songaycongmoi);
                System.out.println("✓ Cap nhat thanh cong!");
                return;
            }
        }
        System.out.println("✗ Khong tim thay ma cong thang: " + ma);
    }

    // Thong ke
    public void thongKe() {
        System.out.print("Nhap thang: ");
        int thang = sc.nextInt();
        System.out.print("Nhap nam: ");
        int nam = sc.nextInt();
        sc.nextLine();

        int tongNhanVien = 0;
        int tongNgayCong = 0;
        int tongNgayNghi = 0;

        for (int i = 0; i < soluong; i++) {
            if (chamcong[i].getThang() == thang && chamcong[i].getNam() == nam) {
                tongNhanVien++;
                tongNgayCong += chamcong[i].getSongaycong();
                tongNgayNghi += chamcong[i].tinhSoNgayNghi();
            }
        }

        if (tongNhanVien > 0) {
            System.out.println("\n=== THONG KE CHAM CONG THANG " + thang + "/" + nam + " ===");
            System.out.println("Tong so nhan vien: " + tongNhanVien);
            System.out.println("Tong ngay cong: " + tongNgayCong);
            System.out.println("Tong ngay nghi: " + tongNgayNghi);
            System.out.println("Trung binh ngay cong/nhan vien: " + (double) tongNgayCong / tongNhanVien);
        } else {
            System.out.println("Khong co du lieu thang " + thang + "/" + nam);
        }
    }

    // Xoa cham cong thang
    public void xoaChamCong() {
        System.out.print("Nhap ma cong thang can xoa: ");
        String ma = sc.nextLine();

        for (int i = 0; i < soluong; i++) {
            if (chamcong[i].getMacongthang().equals(ma)) {
                for (int j = i; j < soluong - 1; j++) {
                    chamcong[j] = chamcong[j + 1];
                }
                chamcong[--soluong] = null;
                System.out.println("✓ Xoa thanh cong!");
                return;
            }
        }
        System.out.println("✗ Khong tim thay ma cong thang: " + ma);
    }

    // Menu
    public void menu() {
        int chon;
        do {
            System.out.println("\n===== MENU QUAN LY CHAM CONG THANG =====");
            System.out.println("1. Them cham cong thang");
            System.out.println("2. Xuat danh sach cham cong thang");
            System.out.println("3. Tim kiem theo ma nhan vien");
            System.out.println("4. Tim kiem theo thang/nam");
            System.out.println("5. Cap nhat so ngay cong");
            System.out.println("6. Thong ke cham cong thang");
            System.out.println("7. Xoa cham cong thang");
            System.out.println("0. Thoat");
            System.out.print(">> Chon chuc nang: ");

            chon = sc.nextInt();
            sc.nextLine();

            switch (chon) {
                case 1:
                    themChamCongThang();
                    break;
                case 2:
                    xuatDanhSach();
                    break;
                case 3:
                    timKiemTheoMaNV();
                    break;
                case 4:
                    timKiemTheoThangNam();
                    break;
                case 5:
                    capNhatSoNgayCong();
                    break;
                case 6:
                    thongKe();
                    break;
                case 7:
                    xoaChamCong();
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
        QuanLyChamCongThang qlcc = new QuanLyChamCongThang();
        qlcc.menu();
    }
}