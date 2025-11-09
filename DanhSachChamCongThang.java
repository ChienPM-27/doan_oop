import java.util.Scanner ;
public class DanhSachChamCongThang {
    private Chamcongthang[] ds;
    private int count;
    private static final int MAX = 100;

    public DanhSachChamCongThang() {
        ds = new Chamcongthang[MAX];
        count = 0;
    }

    // Hàm nhập danh sách
    public void Nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap so luong cham cong: ");
        int n = sc.nextInt();
        sc.nextLine();

        if (n > (MAX - count)) {
            System.out.println("Chi con " + (MAX - count) + " vi tri trong danh sach. Se chi nhap toi da so do.");
            n = MAX - count;
        }

        for (int i = 0; i < n; i++) {
            System.out.println("\nNhap thong tin cham cong thu " + (count + 1) + ":");
            Chamcongthang cc = new Chamcongthang();

            System.out.print("Nhap ma cong thang: ");
            cc.setMacongthang(sc.nextLine());

            System.out.print("Nhap ma nhan vien: ");
            cc.setManv(sc.nextLine());

            System.out.print("Nhap thang: ");
            cc.setThang(sc.nextInt());
            sc.nextLine();

            System.out.print("Nhap nam: ");
            cc.setNam(sc.nextInt());
            sc.nextLine();

            System.out.print("Nhap so ngay cong: ");
            cc.setSongaycong(sc.nextInt());
            sc.nextLine();

            ds[count++] = cc;
        }
    }

    // Hàm xuất danh sách
    public void Xuat() {
        System.out.println("\n===== DANH SACH CHAM CONG THANG =====");
        for (int i = 0; i < count; i++) {
            System.out.println("Ma cong thang: " + ds[i].getMacongthang() +
                    " | Ma NV: " + ds[i].getManv() +
                    " | Thang/Nam: " + ds[i].getThang() + "/" + ds[i].getNam() +
                    " | So ngay cong: " + ds[i].getSongaycong() +
                    " | So ngay nghi: " + ds[i].tinhSoNgayNghi());
        }
    }

    // Hàm thêm chấm công
    public void Them() {
        if (count >= MAX) {
            System.out.println("Danh sach da day, khong the them moi!");
            return;
        }

        Scanner sc = new Scanner(System.in);
        Chamcongthang cc = new Chamcongthang();

        System.out.print("Nhap ma cong thang: ");
        cc.setMacongthang(sc.nextLine());

        System.out.print("Nhap ma nhan vien: ");
        cc.setManv(sc.nextLine());

        System.out.print("Nhap thang: ");
        cc.setThang(sc.nextInt());
        sc.nextLine();

        System.out.print("Nhap nam: ");
        cc.setNam(sc.nextInt());
        sc.nextLine();

        System.out.print("Nhap so ngay cong: ");
        cc.setSongaycong(sc.nextInt());
        sc.nextLine();

        ds[count++] = cc;
        System.out.println("Da them thanh cong!");
    }

    // Hàm xoá chấm công theo mã
    public void Xoa() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma cong thang can xoa: ");
        String ma = sc.nextLine();

        for (int i = 0; i < count; i++) {
            if (ds[i].getMacongthang().equalsIgnoreCase(ma)) {
                // Dồn phần tử sau lên
                for (int j = i; j < count - 1; j++) {
                    ds[j] = ds[j + 1];
                }
                ds[count - 1] = null; // clear last
                count--;
                System.out.println("Da xoa ma cong thang: " + ma);
                return;
            }
        }
        System.out.println("Khong tim thay ma cong thang can xoa!");
    }

    // Hàm sửa thông tin chấm công theo mã
    public void Sua() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma cong thang can sua: ");
        String ma = sc.nextLine();

        for (int i = 0; i < count; i++) {
            if (ds[i].getMacongthang().equalsIgnoreCase(ma)) {
                System.out.println("Nhap thong tin moi cho ma cong thang " + ma + ":");

                System.out.print("Nhap ma nhan vien moi: ");
                ds[i].setManv(sc.nextLine());

                System.out.print("Nhap thang moi: ");
                ds[i].setThang(sc.nextInt());
                sc.nextLine();

                System.out.print("Nhap nam moi: ");
                ds[i].setNam(sc.nextInt());
                sc.nextLine();

                System.out.print("Nhap so ngay cong moi: ");
                ds[i].setSongaycong(sc.nextInt());
                sc.nextLine();

                System.out.println("Da sua thanh cong!");
                return;
            }
        }
        System.out.println("Khong tim thay ma cong thang can sua!");
    }

    // Hàm tìm kiếm chấm công theo mã
    public void TimKiem() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma cong thang can tim: ");
        String ma = sc.nextLine();

        for (int i = 0; i < count; i++) {
            if (ds[i].getMacongthang().equalsIgnoreCase(ma)) {
                System.out.println("Ma cong thang: " + ds[i].getMacongthang() +
                        " | Ma NV: " + ds[i].getManv() +
                        " | Thang/Nam: " + ds[i].getThang() + "/" + ds[i].getNam() +
                        " | So ngay cong: " + ds[i].getSongaycong() +
                        " | So ngay nghi: " + ds[i].tinhSoNgayNghi());
                return;
            }
        }
        System.out.println("Khong tim thay ma cong thang!");
    }

    // Hàm thống kê tổng số ngày công
    public void ThongKe() {
        int tong = 0;
        for (int i = 0; i < count; i++) {
            tong += ds[i].getSongaycong();
        }
        System.out.println("Tong so ngay cong: " + tong);
    }
}
