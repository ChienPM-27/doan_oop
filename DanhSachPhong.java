import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DanhSachPhong {
    private Scanner sc = new Scanner(System.in);
    private PhongBan[] dsPhongBan = new PhongBan[100];
    private int soluong = 0;

    // ===== Thêm phòng ban =====
    public void themPhongBan() {
        if (soluong >= dsPhongBan.length) {
            System.out.println("❌ Danh sách đã đầy!");
            return;
        }

        System.out.println("\n=== THÊM PHÒNG BAN ===");
        System.out.print("Nhập mã phòng ban: ");
        String mapb = sc.nextLine().trim();

        // Kiểm tra trùng mã
        if (timPhongBan(mapb) != null) {
            System.out.println("❌ Mã phòng ban đã tồn tại!");
            return;
        }

        System.out.print("Nhập tên phòng ban: ");
        String tenpb = sc.nextLine().trim();
        
        System.out.print("Nhập mã quản lý: ");
        String maql = sc.nextLine().trim();
        
        System.out.print("Nhập ngày thành lập (yyyy-MM-dd): ");
        String ngay = sc.nextLine().trim();
        
        System.out.print("Nhập mã dự án: ");
        String duan = sc.nextLine().trim();

        dsPhongBan[soluong++] = new PhongBan(mapb, maql, tenpb, ngay, duan);
        System.out.println("✅ Đã thêm phòng ban thành công!");
    }

    // ===== Hiển thị danh sách =====
    public void hienThiDS() {
        if (soluong == 0) {
            System.out.println("\n❌ Danh sách phòng ban trống!");
            return;
        }

        System.out.println("\n" + "=".repeat(120));
        System.out.println("DANH SÁCH PHÒNG BAN");
        System.out.println("=".repeat(120));
        System.out.printf("%-10s | %-20s | %-10s | %-15s | %-15s%n",
                "Mã PB", "Tên phòng ban", "Mã QL", "Ngày thành lập", "Mã dự án");
        System.out.println("-".repeat(120));

        for (int i = 0; i < soluong; i++) {
            PhongBan pb = dsPhongBan[i];
            System.out.printf("%-10s | %-20s | %-10s | %-15s | %-15s%n",
                    pb.getMapb(),
                    pb.getTenpb(),
                    pb.getMaql(),
                    pb.getNgaythanhlap(),
                    pb.getDuAn());
        }
        System.out.println("=".repeat(120));
        System.out.println("Tổng số: " + soluong + " phòng ban");
    }

    // ===== Sửa phòng ban =====
    public void suaPhongBan() {
        System.out.print("\nNhập mã phòng ban cần sửa: ");
        String mapb = sc.nextLine().trim();

        PhongBan pb = timPhongBan(mapb);
        if (pb == null) {
            System.out.println("❌ Không tìm thấy mã phòng ban: " + mapb);
            return;
        }

        System.out.println("\n✅ Tìm thấy phòng ban:");
        System.out.println(pb);

        System.out.println("\n--- SỬA THÔNG TIN ---");
        System.out.print("Tên phòng ban mới (Enter để bỏ qua): ");
        String ten = sc.nextLine().trim();
        if (!ten.isEmpty()) pb.setTenpb(ten);

        System.out.print("Mã quản lý mới (Enter để bỏ qua): ");
        String maql = sc.nextLine().trim();
        if (!maql.isEmpty()) pb.setMaql(maql);

        System.out.print("Ngày thành lập mới (Enter để bỏ qua): ");
        String ngay = sc.nextLine().trim();
        if (!ngay.isEmpty()) pb.setNgaythanhlap(ngay);

        System.out.print("Mã dự án mới (Enter để bỏ qua): ");
        String duan = sc.nextLine().trim();
        if (!duan.isEmpty()) pb.setDuAn(duan);

        System.out.println("✅ Đã cập nhật thông tin phòng ban!");
    }

    // ===== Xóa phòng ban =====
    public void xoaPhongBan() {
        System.out.print("\nNhập mã phòng ban cần xóa: ");
        String mapb = sc.nextLine().trim();

        for (int i = 0; i < soluong; i++) {
            if (dsPhongBan[i].getMapb().equalsIgnoreCase(mapb)) {
                System.out.print("⚠️ Bạn có chắc muốn xóa phòng ban '" + dsPhongBan[i].getTenpb() + "'? (y/n): ");
                if (!sc.nextLine().equalsIgnoreCase("y")) {
                    System.out.println("Đã hủy thao tác xóa.");
                    return;
                }

                for (int j = i; j < soluong - 1; j++) {
                    dsPhongBan[j] = dsPhongBan[j + 1];
                }
                dsPhongBan[--soluong] = null;
                System.out.println("✅ Đã xóa phòng ban!");
                return;
            }
        }
        System.out.println("❌ Không tìm thấy mã phòng ban: " + mapb);
    }

    // ===== TÌM KIẾM =====
    public void timKiem() {
        System.out.println("\n=== TÌM KIẾM PHÒNG BAN ===");
        System.out.println("1. Tìm theo mã phòng ban");
        System.out.println("2. Tìm theo tên phòng ban");
        System.out.println("3. Tìm theo mã quản lý");
        System.out.println("4. Tìm theo mã dự án");
        System.out.print("Chọn: ");
        int chon = sc.nextInt();
        sc.nextLine();

        switch (chon) {
            case 1:
                timTheoMa();
                break;
            case 2:
                timTheoTen();
                break;
            case 3:
                timTheoMaQL();
                break;
            case 4:
                timTheoDuAn();
                break;
            default:
                System.out.println("❌ Lựa chọn không hợp lệ!");
        }
    }

    private void timTheoMa() {
        System.out.print("Nhập mã phòng ban: ");
        String ma = sc.nextLine().trim();

        PhongBan pb = timPhongBan(ma);
        if (pb != null) {
            System.out.println("\n✅ Tìm thấy:");
            System.out.println(pb);
        } else {
            System.out.println("❌ Không tìm thấy!");
        }
    }

    private void timTheoTen() {
        System.out.print("Nhập tên phòng ban (hoặc một phần): ");
        String ten = sc.nextLine().trim().toLowerCase();

        boolean timThay = false;
        System.out.println("\n=== KẾT QUẢ TÌM KIẾM ===");

        for (int i = 0; i < soluong; i++) {
            if (dsPhongBan[i].getTenpb().toLowerCase().contains(ten)) {
                System.out.println(dsPhongBan[i]);
                timThay = true;
            }
        }

        if (!timThay) {
            System.out.println("❌ Không tìm thấy!");
        }
    }

    private void timTheoMaQL() {
        System.out.print("Nhập mã quản lý: ");
        String maql = sc.nextLine().trim();

        boolean timThay = false;
        System.out.println("\n=== PHÒNG BAN DO QUẢN LÝ " + maql + " ===");

        for (int i = 0; i < soluong; i++) {
            if (dsPhongBan[i].getMaql().equalsIgnoreCase(maql)) {
                System.out.println(dsPhongBan[i]);
                timThay = true;
            }
        }

        if (!timThay) {
            System.out.println("❌ Không tìm thấy!");
        }
    }

    private void timTheoDuAn() {
        System.out.print("Nhập mã dự án: ");
        String duan = sc.nextLine().trim();

        boolean timThay = false;
        System.out.println("\n=== PHÒNG BAN THAM GIA DỰ ÁN " + duan + " ===");

        for (int i = 0; i < soluong; i++) {
            if (dsPhongBan[i].getDuAn().equalsIgnoreCase(duan)) {
                System.out.println(dsPhongBan[i]);
                timThay = true;
            }
        }

        if (!timThay) {
            System.out.println("❌ Không tìm thấy!");
        }
    }

    // ===== THỐNG KÊ =====
    public void thongKe() {
        System.out.println("\n=== THỐNG KÊ PHÒNG BAN ===");
        System.out.println("1. Tổng số phòng ban");
        System.out.println("2. Thống kê theo năm thành lập");
        System.out.println("3. Thống kê theo dự án");
        System.out.println("4. Thống kê theo quản lý");
        System.out.print("Chọn: ");
        int chon = sc.nextInt();
        sc.nextLine();

        switch (chon) {
            case 1:
                thongKeSoLuong();
                break;
            case 2:
                thongKeTheoNam();
                break;
            case 3:
                thongKeTheoDuAn();
                break;
            case 4:
                thongKeTheoQuanLy();
                break;
            default:
                System.out.println("❌ Lựa chọn không hợp lệ!");
        }
    }

    private void thongKeSoLuong() {
        System.out.println("\n📊 Tổng số phòng ban: " + soluong);
    }

    private void thongKeTheoNam() {
        if (soluong == 0) {
            System.out.println("❌ Chưa có dữ liệu!");
            return;
        }

        // Đếm số phòng ban theo từng năm
        String[] nam = new String[100];
        int[] dem = new int[100];
        int soNam = 0;

        for (int i = 0; i < soluong; i++) {
            String namTL = dsPhongBan[i].getNgaythanhlap().substring(0, 4);
            
            boolean timThay = false;
            for (int j = 0; j < soNam; j++) {
                if (nam[j].equals(namTL)) {
                    dem[j]++;
                    timThay = true;
                    break;
                }
            }
            
            if (!timThay) {
                nam[soNam] = namTL;
                dem[soNam] = 1;
                soNam++;
            }
        }

        System.out.println("\n=== THỐNG KÊ THEO NĂM THÀNH LẬP ===");
        for (int i = 0; i < soNam; i++) {
            System.out.println("Năm " + nam[i] + ": " + dem[i] + " phòng ban");
        }
    }

    private void thongKeTheoDuAn() {
        if (soluong == 0) {
            System.out.println("❌ Chưa có dữ liệu!");
            return;
        }

        // Đếm số phòng ban theo từng dự án
        String[] duan = new String[100];
        int[] dem = new int[100];
        int soDuAn = 0;

        for (int i = 0; i < soluong; i++) {
            String maDA = dsPhongBan[i].getDuAn();
            
            boolean timThay = false;
            for (int j = 0; j < soDuAn; j++) {
                if (duan[j].equalsIgnoreCase(maDA)) {
                    dem[j]++;
                    timThay = true;
                    break;
                }
            }
            
            if (!timThay) {
                duan[soDuAn] = maDA;
                dem[soDuAn] = 1;
                soDuAn++;
            }
        }

        System.out.println("\n=== THỐNG KÊ THEO DỰ ÁN ===");
        for (int i = 0; i < soDuAn; i++) {
            System.out.println("Dự án " + duan[i] + ": " + dem[i] + " phòng ban");
        }
    }

    private void thongKeTheoQuanLy() {
        if (soluong == 0) {
            System.out.println("❌ Chưa có dữ liệu!");
            return;
        }

        // Đếm số phòng ban theo từng quản lý
        String[] ql = new String[100];
        int[] dem = new int[100];
        int soQL = 0;

        for (int i = 0; i < soluong; i++) {
            String maQL = dsPhongBan[i].getMaql();
            
            boolean timThay = false;
            for (int j = 0; j < soQL; j++) {
                if (ql[j].equalsIgnoreCase(maQL)) {
                    dem[j]++;
                    timThay = true;
                    break;
                }
            }
            
            if (!timThay) {
                ql[soQL] = maQL;
                dem[soQL] = 1;
                soQL++;
            }
        }

        System.out.println("\n=== THỐNG KÊ THEO QUẢN LÝ ===");
        for (int i = 0; i < soQL; i++) {
            System.out.println("Quản lý " + ql[i] + ": " + dem[i] + " phòng ban");
        }
    }

// ===== ĐỌC FILE =====
    public void docFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("DanhSachPhongBan.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length < 5) {
                    System.out.println("⚠️ Dòng sai định dạng: " + line);
                    continue;
                }

                String mapb = parts[0];
                String maql = parts[1];
                String tenpb = parts[2];
                String ngaythanhlap = parts[3];
                String maduan = parts[4];

                dsPhongBan[soluong++] = new PhongBan(mapb, maql, tenpb, ngaythanhlap, maduan);
            }

            System.out.println("✅ Đọc file DanhSachPhongBan.txt thành công (" + soluong + " phòng ban).");

        } catch (IOException e) {
            System.out.println("❌ Lỗi đọc file: " + e.getMessage());
        }
    }

    // ===== GHI FILE =====
    public void ghiFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("DanhSachPhongBan.txt"))) {
            for (int i = 0; i < soluong; i++) {
                PhongBan pb = dsPhongBan[i];
                String line = pb.getMapb() + "," + 
                             pb.getMaql() + "," + 
                             pb.getTenpb() + "," + 
                             pb.getNgaythanhlap() + "," + 
                             pb.getDuAn();
                bw.write(line);
                bw.newLine();
            }
            System.out.println("✅ Ghi file DanhSachPhongBan.txt thành công (" + soluong + " phòng ban).");
        } catch (IOException e) {
            System.out.println("❌ Lỗi ghi file: " + e.getMessage());
        }
    }

    // ===== HÀM HỖ TRỢ =====
    private PhongBan timPhongBan(String mapb) {
        for (int i = 0; i < soluong; i++) {
            if (dsPhongBan[i].getMapb().equalsIgnoreCase(mapb)) {
                return dsPhongBan[i];
            }
        }
        return null;
    }

// ===== MENU =====
public void menu() {
    int chon;
    do {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║      QUẢN LÝ PHÒNG BAN                 ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║  1. Thêm phòng ban                     ║");
        System.out.println("║  2. Hiển thị danh sách                 ║");
        System.out.println("║  3. Sửa thông tin phòng ban            ║");
        System.out.println("║  4. Xóa phòng ban                      ║");
        System.out.println("║  5. Tìm kiếm phòng ban                 ║");
        System.out.println("║  6. Thống kê                           ║");
        System.out.println("║  7. Ghi file                           ║");  // <-- ĐÃ THÊM
        System.out.println("║  0. Thoát                              ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.print("👉 Chọn chức năng: ");
        
        chon = sc.nextInt();
        sc.nextLine();

        switch (chon) {
            case 1:
                themPhongBan();
                break;
            case 2:
                hienThiDS();
                break;
            case 3:
                suaPhongBan();
                break;
            case 4:
                xoaPhongBan();
                break;
            case 5:
                timKiem();
                break;
            case 6:
                thongKe();
                break;
            case 7:
                ghiFile();  // <-- ĐÃ THÊM
                break;
            case 0:
                System.out.println("👋 Thoát quản lý phòng ban!");
                break;
            default:
                System.out.println("❌ Lựa chọn không hợp lệ!");
        }
    } while (chon != 0);
}
}