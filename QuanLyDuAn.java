
import java.util.Scanner;

public class QuanLyDuAn{

    private DuAn[] duan;
    private int count;
    private static final int MAX = 100;

    public QuanLyDuAn() {
        duan = new DuAn[MAX];
        count = 0;
    }

    // Nhap danh sach du an
    public void nhapDanhSach() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap so luong du an: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.println("\nNhap du an thu " + (i + 1) + ": ");
            DuAn da = new DuAn();
            System.out.print("Ma du an: ");
            da.setMaDA(sc.nextLine());
            System.out.print("Ten du an: ");
            da.setTenDA(sc.nextLine());
            System.out.print("Ngay bat dau: ");
            da.setNgayBD(sc.nextLine());
            System.out.print("Ngay ket thuc: ");
            da.setNgayKT(sc.nextLine());
            System.out.print("Kinh phi: ");
            da.setKinhphi(sc.nextDouble());
            System.out.print("Thuong du an: ");
            da.setThuongDA(sc.nextDouble());
            sc.nextLine();
            duan[count++] = da;
        }
    }

    // Xuat danh sach
    public void xuatDanhSach() {
        if (count == 0) {
            System.out.println("Danh sach rong");
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.println("\nDu an thu " + (i + 1) + ":");
            duan[i].xuat();
        }
    }
    // Thêm 1 dự án mới vào danh sách

    public void themDuAn() {
        if (count >= MAX) {
            System.out.println("Danh sach da day, khong the them du an moi!");
            return;
        }

        Scanner sc = new Scanner(System.in);
        DuAn da = new DuAn();

        System.out.println("\n=== Nhap thong tin du an moi ===");
        System.out.print("Ma du an: ");
        da.setMaDA(sc.nextLine());
        System.out.print("Ten du an: ");
        da.setTenDA(sc.nextLine());
        System.out.print("Ngay bat dau: ");
        da.setNgayBD(sc.nextLine());
        System.out.print("Ngay ket thuc: ");
        da.setNgayKT(sc.nextLine());
        System.out.print("Kinh phi: ");
        da.setKinhphi(sc.nextDouble());
        System.out.print("Thuong du an: ");
        da.setThuongDA(sc.nextDouble());

        duan[count++] = da;
        System.out.println("Them du an moi thanh cong!");
    }

    //tim kiem du an theo ma
    public void timKiem(String ma) {
        for (int i = 0; i < count; i++) {
            if (duan[i].getMaDA().equalsIgnoreCase(ma)) {
                System.out.println("\nTim thay du an:");
                duan[i].xuat();
                return;
            }
        }
        System.out.println("Khong tim thay");
    }

    // xoa du an theo ma
    public void xoa(String ma) {
        for (int i = 0; i < count; i++) {
            if (duan[i].getMaDA().equalsIgnoreCase(ma)) {
                for (int j = i; j < count - 1; j++) {
                    duan[j] = duan[j + 1];
                }
                count--;
                System.out.println("Da xoa du an");
                return;
            }
        }
        System.out.println("Khong ton tai du an de xoa");
    }

    //HAM SUA THONG TIN DU AN
    public void suaThongTin(String ma) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < count; i++) {
            if (duan[i].getMaDA().equalsIgnoreCase(ma)) {
                System.out.println("\n--- Sua thong tin du an ---");
                System.out.print("Ten du an moi: ");
                duan[i].setTenDA(sc.nextLine());
                System.out.print("Ngay bat dau moi: ");
                duan[i].setNgayBD(sc.nextLine());
                System.out.print("Ngay ket thuc moi: ");
                duan[i].setNgayKT(sc.nextLine());
                System.out.print("Kinh phi moi: ");
                duan[i].setKinhphi(sc.nextDouble());
                System.out.print("Thuong du an moi: ");
                duan[i].setThuongDA(sc.nextDouble());
                System.out.println("Da cap nhat thong tin du an thanh cong!");
                return;
            }
        }
        System.out.println("Khong tim thay du an can sua!");
    }

    // Thong ke tong kinh phi
    public void thongKeKinhPhi() {
        double tong = 0;
        for (int i = 0; i < count; i++) {
            tong += duan[i].getKinhphi();
        }
        System.out.println("\nTong kinh phi tat ca du an: " + tong);
    }

    // Thong ke so du an co thuong
    public void thongKeDuAnThuong() {
        int dem = 0;
        for (int i = 0; i < count; i++) {
            if (duan[i].getThuongDA() > 0) {
                dem++;
            }
        }
        System.out.println("\nSo du an co thuong: " + dem);
    }

    public void menu() {
        Scanner sc = new Scanner(System.in);
        int chon;

        do {
            System.out.println("\n===== MENU QUẢN LÝ DỰ ÁN =====");
            System.out.println("1. Nhập danh sách dự án");
            System.out.println("2. Xuất danh sách dự án");
            System.out.println("3. Thêm dự án mới");
            System.out.println("4. Tìm kiếm dự án theo mã");
            System.out.println("5. Xóa dự án theo mã");
            System.out.println("6. Thống kê tổng kinh phí");
            System.out.println("7. Thống kê số dự án có thưởng");
            System.out.println("0. Thoát");
            System.out.print("👉 Chọn chức năng: ");
            chon = Integer.parseInt(sc.nextLine());

            switch (chon) {
                case 1:
                    nhapDanhSach();
                    break;

                case 2:
                    xuatDanhSach();
                    break;

                case 3:
                    themDuAn();
                    break;

                case 4:
                    System.out.print("Nhập mã dự án cần tìm: ");
                    String maTK = sc.nextLine();
                    timKiem(maTK);
                    break;

                case 5:
                    System.out.print("Nhập mã dự án cần xóa: ");
                    String maXoa = sc.nextLine();
                    xoa(maXoa);
                    break;

                case 6:
                    thongKeKinhPhi();
                    break;

                case 7:
                    thongKeDuAnThuong();
                    break;

                case 0:
                    System.out.println("👋 Thoát chương trình!");
                    break;

                default:
                    System.out.println("❌ Lựa chọn không hợp lệ. Vui lòng chọn lại!");
            }
        } while (chon != 0);
    }
}
