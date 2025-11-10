import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Arrays;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
public class DanhSachChucVu {
    private ChucVu[] ds;
    private int count;
    private static final int MAX = 100;

    public DanhSachChucVu() {
        ds = new ChucVu[MAX];
        count = 0;
    }

    // ===== Nhập danh sách =====
    public void Nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap so luong chuc vu: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.println("\nNhap thong tin chuc vu thu " + (i + 1) + ":");
            ChucVu cv = new ChucVu();
            cv.Nhap();
            ds[count++] = cv;
        }
    }

    // ===== Xuất danh sách =====
    public void Xuat() {
        if (count == 0) {
            System.out.println("Danh sach rong!");
            return;
        }
        System.out.println("\n===== DANH SACH CHUC VU =====");
        for (int i = 0; i < count; i++) {
            ds[i].Xuat();
        }
    }

    // ===== Thêm chức vụ =====
    public void Them() {
        if (count >= MAX) {
            System.out.println("Danh sach da day, khong the them moi!");
            return;
        }
        ChucVu cv = new ChucVu();
        System.out.println("\nNhap thong tin chuc vu moi:");
        cv.Nhap();
        ds[count++] = cv;
        System.out.println("Da them chuc vu moi thanh cong!");
    }

    // ===== Xóa chức vụ theo mã =====
    public void Xoa() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma chuc vu can xoa: ");
        String ma = sc.nextLine();

        for (int i = 0; i < count; i++) {
            if (ds[i].getMacv().equalsIgnoreCase(ma)) {
                for (int j = i; j < count - 1; j++) {
                    ds[j] = ds[j + 1];
                }
                count--;
                ds = Arrays.copyOf(ds, ds.length - 1);
                System.out.println("Da xoa chuc vu co ma: " + ma);
                return;
            }
        }
        System.out.println("Khong tim thay ma chuc vu can xoa!");
    }

    // ===== Sửa thông tin chức vụ =====
    public void Sua() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma chuc vu can sua: ");
        String ma = sc.nextLine();

        for (int i = 0; i < count; i++) {
            if (ds[i].getMacv().equalsIgnoreCase(ma)) {
                System.out.println("Nhap thong tin moi cho chuc vu co ma " + ma + ":");
                ds[i].Nhap();
                System.out.println("Da sua thong tin thanh cong!");
                return;
            }
        }
        System.out.println("Khong tim thay ma chuc vu can sua!");
    }

    // ===== Tìm kiếm chức vụ =====
    public void TimKiem() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma chuc vu can tim: ");
        String ma = sc.nextLine();

        for (int i = 0; i < count; i++) {
            if (ds[i].getMacv().equalsIgnoreCase(ma)) {
                System.out.println("\nTim thay chuc vu:");
                ds[i].Xuat();
                return;
            }
        }
        System.out.println("Khong tim thay ma chuc vu!");
    }

    // ===== Thống kê tổng phụ cấp =====
    public void ThongKe() {
        double tong = 0;
        for (int i = 0; i < count; i++) {
            tong += ds[i].getPhucap();
        }
        System.out.println("\nTong phu cap tat ca chuc vu: " + tong);
    }
    public void ThongKePhuCapCaoThap() {
    if (count == 0) {
        System.out.println("❌ Danh sách rỗng, không thể thống kê!");
        return;
    }

    ChucVu max = ds[0];
    ChucVu min = ds[0];
    double tong = 0;

    for (int i = 0; i < count; i++) {
        if (ds[i].getPhucap() > max.getPhucap()) {
            max = ds[i];
        }
        if (ds[i].getPhucap() < min.getPhucap()) {
            min = ds[i];
        }
        tong += ds[i].getPhucap();
    }

    double tb = tong / count;

    System.out.println("\n╔══════════════════════════════════════════════════════╗");
    System.out.println("║             THỐNG KÊ PHỤ CẤP CHỨC VỤ                 ║");
    System.out.println("╠══════════════════════════════════════════════════════╣");
    System.out.printf("║ Tổng số chức vụ     : %-25d ║%n", count);
    System.out.printf("║ Tổng phụ cấp        : %-25.2f ║%n", tong);
    System.out.printf("║ Phụ cấp trung bình  : %-25.2f ║%n", tb);
    System.out.println("╠══════════════════════════════════════════════════════╣");
    System.out.printf("║ Phụ cấp cao nhất    : %-25.2f ║%n", max.getPhucap());
    System.out.printf("║  → Mã chức vụ: %-10s | Tên: %-12s ║%n", max.getMacv(), max.getTencv());
    System.out.println("╠──────────────────────────────────────────────────────╣");
    System.out.printf("║ Phụ cấp thấp nhất   : %-25.2f ║%n", min.getPhucap());
    System.out.printf("║  → Mã chức vụ: %-10s | Tên: %-12s ║%n", min.getMacv(), min.getTencv());
    System.out.println("╚══════════════════════════════════════════════════════╝");
}
    //GET DS CHUC VU
    public ChucVu[] getDs(){
        return ds;
    }
    public int getCount(){
        return count;
    }
    //DOC FILE GAN VAO DS
    public void DocFile() throws IOException{
        try(BufferedReader br = new BufferedReader(new FileReader("DanhSachChucVu.txt"))){
            String line;
            while((line = br.readLine()) != null){
                line = line.trim();
                if(line.isEmpty()) continue;

                String[] parts = line.split(",");
                if(parts.length < 3 ){
                    System.out.println("⚠️ Dòng sai định dạng: " + line);
                    continue;
                }
                String ma = parts[0];
                String ten = parts[1];
                double phucap = Double.parseDouble(parts[2]);
                ChucVu chucvu = new ChucVu(ma,ten,phucap);
                ds[count++] = chucvu;

            }
            System.out.println("Đọc file DanhSachChucVu.txt thành công (" + count + " Chuc Vu).");

        } catch (IOException e) {
            System.out.println("Lỗi đọc file: " + e.getMessage());
        }

        }

    // ===== GHI FILE =====
    public void GhiFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("DanhSachChucVu.txt"))) {
            for (int i = 0; i < count; i++) {
                ChucVu cv = ds[i];
                bw.write(cv.getMacv() + "," + cv.getTencv() + "," + cv.getPhucap());
                bw.newLine();
            }
            System.out.println("✅ Ghi file DanhSachChucVu.txt thành công (" + count + " chức vụ).");
        } catch (IOException e) {
            System.out.println("❌ Lỗi ghi file: " + e.getMessage());
        }
    }
    public void Menu() throws IOException {
        Scanner sc = new Scanner(System.in);
        int chon;
        do {
            System.out.println("\n╔══════════════════════════════════════════════════╗");
            System.out.println("║          QUẢN LÝ DANH SÁCH CHỨC VỤ               ║");
            System.out.println("╠══════════════════════════════════════════════════╣");
            System.out.println("║  1. Đọc danh sách chức vụ từ file                ║");
            System.out.println("║  2. Thêm chức vụ mới                             ║");
            System.out.println("║  3. Xóa chức vụ theo mã                          ║");
            System.out.println("║  4. Sửa thông tin chức vụ                        ║");
            System.out.println("║  5. Tìm kiếm chức vụ theo mã                     ║");
            System.out.println("║  6. Hiển thị danh sách chức vụ                   ║");
            System.out.println("║  7. Thống kê tổng phụ cấp                        ║");
            System.out.println("║  8. Ghi danh sách chức vụ ra file                ║");
            System.out.println("║  9. Thống kê phụ cấp cao nhất & thấp nhất        ║");
            System.out.println("║  0. Thoát chương trình                           ║");
            System.out.println("╚══════════════════════════════════════════════════╝");
            System.out.print("👉 Chọn chức năng: ");

            while (!sc.hasNextInt()) {
                System.out.print("❌ Vui lòng nhập số: ");
                sc.next();
            }
            chon = sc.nextInt();
            sc.nextLine();

            switch (chon) {
                case 1:
                    DocFile();
                    break;
                case 2:
                    Them();
                    break;
                case 3:
                    Xoa();
                    break;
                case 4:
                    Sua();
                    break;
                case 5:
                    TimKiem();
                    break;
                case 6:
                    Xuat();
                    break;
                case 7:
                    ThongKe();
                    break;
                case 8:
                    GhiFile();
                    break;
                case 9:
                ThongKePhuCapCaoThap();
                break;
                case 0:
                    System.out.println("\n👋 Cảm ơn bạn đã sử dụng chương trình quản lý chức vụ!");
                    break;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ! Vui lòng chọn lại!");
            }

        } while (chon != 0);
    }
}


