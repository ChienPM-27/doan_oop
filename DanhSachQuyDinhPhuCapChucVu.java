import java.util.Arrays;
import java.util.Scanner;

public class DanhSachQuyDinhPhuCapChucVu {

    private QuyDinhPhuCapChucVu[] ds;
    private int count;
    private static final int MAX = 100;

    public DanhSachQuyDinhPhuCapChucVu() {
        ds = new QuyDinhPhuCapChucVu[MAX];
        count = 0;
    }

    // Hàm nhập danh sách
    public void Nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap so luong quy dinh phu cap chuc vu: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.println("\nNhap thong tin phu cap thu " + (i + 1) + ":");
            QuyDinhPhuCapChucVu qd = new QuyDinhPhuCapChucVu();

            System.out.print("Nhap ma chuc vu: ");
            qd.setMaCV(sc.nextLine());

            System.out.print("Nhap ten chuc vu: ");
            qd.setTenCV(sc.nextLine());

            System.out.print("Nhap muc phu cap: ");
            qd.setPhucap(sc.nextDouble());
            sc.nextLine();

            System.out.print("Nhap ghi chu: ");
            qd.setGhichu(sc.nextLine());

            ds[count++] = qd;
        }
    }

    // Hàm xuất danh sách (hiển thị đẹp)
    public void Xuat() {
        if (count == 0) {
            System.out.println("\nDanh sach rong, khong co du lieu de hien thi!");
            return;
        }

        System.out.println("\n╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║              DANH SACH QUY DINH PHU CAP CHUC VU                            ║");
        System.out.println("╠════════════╦════════════════════════════╦═══════════════╦══════════════════╣");
        System.out.printf("║ %-10s ║ %-30s ║ %-13s ║ %-16s ║%n",
                "Ma CV", "Ten Chuc Vu", "Phu Cap", "Ghi Chu");
        System.out.println("╠════════════╬════════════════════════════╬═══════════════╬══════════════════╣");

        for (int i = 0; i < count; i++) {
            QuyDinhPhuCapChucVu qd = ds[i];
            String ma = qd.getMaCV() == null ? "" : qd.getMaCV();
            String ten = qd.getTenCV() == null ? "" : qd.getTenCV();
            String phuCap = (qd.getPhucap() == null) ? "" : String.format("%.2f", qd.getPhucap());
            String ghiChu = qd.getGhichu() == null ? "" : qd.getGhichu();

            System.out.printf("║ %-10s ║ %-30s ║ %-13s ║ %-16s ║%n",
                    ma, ten, phuCap, ghiChu);
        }

        System.out.println("╚════════════╩════════════════════════════╩═══════════════╩══════════════════╝");
    }

    // Hàm thêm quy định phụ cấp
    public void Them() {
        if (count >= MAX) {
            System.out.println("Danh sach da day, khong the them moi!");
            return;
        }

        Scanner sc = new Scanner(System.in);
        QuyDinhPhuCapChucVu qd = new QuyDinhPhuCapChucVu();

        System.out.print("Nhap ma chuc vu: ");
        qd.setMaCV(sc.nextLine());

        System.out.print("Nhap ten chuc vu: ");
        qd.setTenCV(sc.nextLine());

        System.out.print("Nhap muc phu cap: ");
        qd.setPhucap(sc.nextDouble());
        sc.nextLine();

        System.out.print("Nhap ghi chu: ");
        qd.setGhichu(sc.nextLine());

        ds[count++] = qd;
        System.out.println("✅ Da them thanh cong!");
    }

    // Hàm xoá quy định phụ cấp theo mã
    public void Xoa() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma chuc vu can xoa: ");
        String ma = sc.nextLine();

        for (int i = 0; i < count; i++) {
            if (ds[i].getMaCV().equalsIgnoreCase(ma)) {
                for (int j = i; j < count - 1; j++) {
                    ds[j] = ds[j + 1];
                }
                count--;
                ds = Arrays.copyOf(ds, ds.length - 1);

                System.out.println("🗑️  Da xoa phu cap cho chuc vu co ma: " + ma);
                return;
            }
        }
        System.out.println("⚠️  Khong tim thay ma chuc vu can xoa!");
    }

    // Hàm sửa thông tin phụ cấp theo mã
    public void Sua() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma chuc vu can sua: ");
        String ma = sc.nextLine();

        for (int i = 0; i < count; i++) {
            if (ds[i].getMaCV().equalsIgnoreCase(ma)) {
                System.out.println("Nhap thong tin moi cho chuc vu co ma " + ma + ":");

                System.out.print("Nhap ten chuc vu moi: ");
                ds[i].setTenCV(sc.nextLine());

                System.out.print("Nhap muc phu cap moi: ");
                ds[i].setPhucap(sc.nextDouble());
                sc.nextLine();

                System.out.print("Nhap ghi chu moi: ");
                ds[i].setGhichu(sc.nextLine());

                System.out.println("✏️  Da sua thanh cong!");
                return;
            }
        }
        System.out.println("⚠️  Khong tim thay ma chuc vu can sua!");
    }

    // Hàm tìm kiếm phụ cấp theo mã
    public void TimKiem() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma chuc vu can tim: ");
        String ma = sc.nextLine();

        for (int i = 0; i < count; i++) {
            if (ds[i].getMaCV().equalsIgnoreCase(ma)) {
                System.out.println("\n🔍 Thong tin chuc vu tim thay:");
                System.out.println("Ma CV: " + ds[i].getMaCV()
                        + " | Ten CV: " + ds[i].getTenCV()
                        + " | Phu cap: " + ds[i].getPhucap()
                        + " | Ghi chu: " + ds[i].getGhichu());
                return;
            }
        }
        System.out.println("❌ Khong tim thay ma chuc vu!");
    }

    // Hàm thống kê tổng mức phụ cấp
    public void ThongKe() {
        double tong = 0;
        for (int i = 0; i < count; i++) {
            Double p = ds[i].getPhucap();
            if (p != null) tong += p;
        }
        System.out.printf("\n📊 Tong muc phu cap cho tat ca chuc vu: %.2f%n", tong);
    }
}

