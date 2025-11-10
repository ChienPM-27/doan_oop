import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class DanhSachQuyDinhPhuCapThoiVu {

    private QuyDinhPhuCapThoiVu[] ds;
    private int count;
    private static final int MAX = 200;
    private Scanner sc = new Scanner(System.in);

    public DanhSachQuyDinhPhuCapThoiVu() {
        ds = new QuyDinhPhuCapThoiVu[MAX];
        count = 0;
    }

    // ======= NHẬP NHIỀU =======
    public void Nhap() {
        System.out.print("Nhập số lượng quy định phụ cấp thời vụ: ");
        int n = readIntPositive();
        for (int i = 0; i < n; i++) {
            if (count >= MAX) {
                System.out.println("❌ Danh sách đã đầy, không thể nhập thêm!");
                break;
            }
            System.out.println("\n--- Nhập quy định thứ " + (i + 1) + " ---");
            QuyDinhPhuCapThoiVu q = new QuyDinhPhuCapThoiVu();

            System.out.print("Mã phụ cấp (ví dụ PC001): ");
            q.setMaPC(sc.nextLine().trim());

            System.out.print("Loại thành viên (ví dụ: NV, Thực tập): ");
            q.setLoaithanhvien(sc.nextLine().trim());

            System.out.print("Mức phụ cấp: ");
            q.setMucphucap(readDoubleSafe());

            System.out.print("Đơn vị tính (VNĐ, USD, ngày,...): ");
            q.setDonvitinh(sc.nextLine().trim());

            ds[count++] = q;
            System.out.println("✅ Đã thêm.");
        }
    }

    // ======= XUẤT =======
    public void Xuat() {
        if (count == 0) {
            System.out.println("\n❌ Danh sách trống!");
            return;
        }
        System.out.println("╔════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║              DANH SÁCH QUY ĐỊNH PHỤ CẤP THỜI VỤ                        ║");
        System.out.println("╠═════════╦═════════════════════════════════════╦══════════════╦═════════╣");
        System.out.printf("║ %-7s ║ %-35s ║ %-12s ║ %-7s ║%n", "MaPC", "LoaiThanhVien", "MucPhuCap", "DVT");
        System.out.println("╠═════════╬═════════════════════════════════════╬══════════════╬═════════╣");
        for (int i = 0; i < count; i++) {
            QuyDinhPhuCapThoiVu q = ds[i];
            String ma = q.getMaPC() == null ? "" : q.getMaPC();
            String loai = q.getLoaithanhvien() == null ? "" : q.getLoaithanhvien();
            String muc = q.getMucphucap() == null ? "" : String.format("%.2f", q.getMucphucap());
            String dv = q.getDonvitinh() == null ? "" : q.getDonvitinh();
            System.out.printf("║ %-7s ║ %-35s ║ %-12s ║ %-7s ║%n", ma, loai, muc, dv);
        }
        System.out.println("╚═════════╩═════════════════════════════════════╩══════════════╩═════════╝");
    }
    // ======= THÊM =======
    public void Them() {
        if (count >= MAX) {
            System.out.println("Danh sách đã đầy!");
            return;
        }
        QuyDinhPhuCapThoiVu q = new QuyDinhPhuCapThoiVu();
        System.out.print("Mã phụ cấp: ");
        q.setMaPC(sc.nextLine().trim());
        System.out.print("Loại thành viên: ");
        q.setLoaithanhvien(sc.nextLine().trim());
        System.out.print("Mức phụ cấp: ");
        q.setMucphucap(readDoubleSafe());
        System.out.print("Đơn vị tính: ");
        q.setDonvitinh(sc.nextLine().trim());
        ds[count++] = q;
        System.out.println("✅ Thêm thành công!");
    }

    // ======= XÓA =======
    public void Xoa() {
        System.out.print("Nhập mã phụ cấp cần xóa: ");
        String ma = sc.nextLine().trim();
        for (int i = 0; i < count; i++) {
            if (ds[i].getMaPC().equalsIgnoreCase(ma)) {
                for (int j = i; j < count - 1; j++) ds[j] = ds[j + 1];
                ds[--count] = null;
                System.out.println("🗑️  Đã xóa mã: " + ma);
                return;
            }
        }
        System.out.println("⚠️  Không tìm thấy mã cần xóa!");
    }

    // ======= SỬA =======
    public void Sua() {
        System.out.print("Nhập mã phụ cấp cần sửa: ");
        String ma = sc.nextLine().trim();
        for (int i = 0; i < count; i++) {
            if (ds[i].getMaPC().equalsIgnoreCase(ma)) {
                System.out.println("✅ Tìm thấy — nhập thông tin mới (Enter để bỏ qua):");
                System.out.print("Loại thành viên mới: ");
                String loai = sc.nextLine().trim();
                if (!loai.isEmpty()) ds[i].setLoaithanhvien(loai);

                System.out.print("Mức phụ cấp mới: ");
                String muc = sc.nextLine().trim();
                if (!muc.isEmpty()) {
                    try {
                        ds[i].setMucphucap(Double.parseDouble(muc));
                    } catch (NumberFormatException ignored) {}
                }

                System.out.print("Đơn vị tính mới: ");
                String dv = sc.nextLine().trim();
                if (!dv.isEmpty()) ds[i].setDonvitinh(dv);

                System.out.println("✏️  Đã sửa thành công!");
                return;
            }
        }
        System.out.println("❌ Không tìm thấy mã để sửa!");
    }

    // ======= TÌM KIẾM =======
    public void TimKiem() {
        System.out.print("Nhập mã phụ cấp cần tìm: ");
        String ma = sc.nextLine().trim();
        for (int i = 0; i < count; i++) {
            if (ds[i].getMaPC().equalsIgnoreCase(ma)) {
                System.out.println("\n🔍 Thông tin tìm thấy:");
                System.out.println("Mã: " + ds[i].getMaPC()
                        + " | Loại: " + ds[i].getLoaithanhvien()
                        + " | Mức: " + ds[i].getMucphucap()
                        + " | ĐVT: " + ds[i].getDonvitinh());
                return;
            }
        }
        System.out.println("❌ Không tìm thấy mã phụ cấp!");
    }

    // ======= THỐNG KÊ TỔNG & TRUNG BÌNH =======
    public void ThongKe() {
        if (count == 0) {
            System.out.println("Không có dữ liệu để thống kê.");
            return;
        }
        double tong = 0;
        int dem = 0;
        for (int i = 0; i < count; i++) {
            Double m = ds[i].getMucphucap();
            if (m != null) {
                tong += m;
                dem++;
            }
        }
        System.out.printf("\n📊 Tổng mức phụ cấp (tất cả): %.2f%n", tong);
        if (dem > 0) System.out.printf("📈 Mức phụ cấp trung bình: %.2f%n", tong / dem);
        else System.out.println("📈 Mức phụ cấp trung bình: N/A");
    }

    // ======= THỐNG KÊ 1: Top N phụ cấp cao nhất =======
    public void thongKeTopPhuCap(int n) {
        if (count == 0) {
            System.out.println("Không có dữ liệu.");
            return;
        }
        QuyDinhPhuCapThoiVu[] copy = Arrays.copyOf(ds, count);
        Arrays.sort(copy, 0, count, Comparator.comparingDouble(q -> - (q.getMucphucap() == null ? 0.0 : q.getMucphucap())));
        System.out.println("\n🏆 Top " + n + " phụ cấp thời vụ cao nhất:");
        for (int i = 0; i < Math.min(n, count); i++) {
            QuyDinhPhuCapThoiVu q = copy[i];
            System.out.printf("%d. %s | %s | Mức: %.2f %s%n", i + 1, q.getMaPC(), q.getLoaithanhvien(),
                    q.getMucphucap() == null ? 0.0 : q.getMucphucap(), q.getDonvitinh());
        }
    }

    // ======= THỐNG KÊ 2: Phân bố theo khoảng mức phụ cấp =======
    public void thongKePhanBo() {
        int below1000 = 0;
        int from1000to2000 = 0;
        int from2000to4000 = 0;
        int above4000 = 0;

        for (int i = 0; i < count; i++) {
            double v = ds[i].getMucphucap() == null ? 0.0 : ds[i].getMucphucap();
            if (v < 1000) below1000++;
            else if (v < 2000) from1000to2000++;
            else if (v < 4000) from2000to4000++;
            else above4000++;
        }

        System.out.println("\n📊 Phân bố mức phụ cấp thời vụ:");
        System.out.println(" - Dưới 1.000        : " + below1000);
        System.out.println(" - 1.000 - 1.999     : " + from1000to2000);
        System.out.println(" - 2.000 - 3.999     : " + from2000to4000);
        System.out.println(" - Từ 4.000 trở lên  : " + above4000);
    }

    // ======= ĐỌC FILE =======
    public void docFile() {
        File f = new File("QuyDinhPhuCapThoiVu.txt");
        if (!f.exists()) {
            // không in báo nếu file không tồn tại (lần chạy đầu tiên)
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            count = 0;
            while ((line = br.readLine()) != null && count < MAX) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length < 3) {
                    System.out.println("⚠️ Dòng sai định dạng: " + line);
                    continue;
                }
                String ma = parts[0].trim();
                String loai = parts[1].trim();
                Double muc = null;
                try {
                    muc = Double.parseDouble(parts[2].trim());
                } catch (NumberFormatException e) {
                    System.out.println("⚠️ Lỗi dữ liệu mức phụ cấp: " + line);
                    continue;
                }
                String dv = (parts.length >= 4) ? parts[3].trim() : "";
                QuyDinhPhuCapThoiVu q = new QuyDinhPhuCapThoiVu(ma, loai, muc, dv);
                ds[count++] = q;
            }
            System.out.println("✅ Đã đọc file QuyDinhPhuCapThoiVu.txt (" + count + " dòng).");
        } catch (IOException e) {
            System.out.println("Lỗi đọc file: " + e.getMessage());
        }
    }

    // ======= GHI FILE =======
    public void ghiFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("QuyDinhPhuCapThoiVu.txt"))) {
            for (int i = 0; i < count; i++) {
                QuyDinhPhuCapThoiVu q = ds[i];
                if (q == null) continue;
                String line = String.join(",",
                        safeString(q.getMaPC()),
                        safeString(q.getLoaithanhvien()),
                        (q.getMucphucap() == null) ? "" : q.getMucphucap().toString(),
                        safeString(q.getDonvitinh())
                );
                bw.write(line);
                bw.newLine();
            }
            System.out.println("✅ Ghi file QuyDinhPhuCapThoiVu.txt thành công (" + count + " dòng).");
        } catch (IOException e) {
            System.out.println("Lỗi ghi file: " + e.getMessage());
        }
    }

    // Trợ giúp an toàn khi ghi file (loại bỏ newline, thay dấu phẩy)
    private String safeString(String s) {
        if (s == null) return "";
        return s.replaceAll("\\r?\\n", " ").replace(",", ";").trim();
    }

    // ======= MENU (ĐÃ BỎ MỤC 1, 8, 9) =======
    public void menu() {
        // tự động đọc file khi khởi động (nếu có)
        docFile();

        int chon;
        do {
            System.out.println("  ╔═══════════════════════════════════════════════╗");
            System.out.println("  ║   HỆ THỐNG QUẢN LÝ QUY ĐỊNH PHỤ CẤP THỜI VỤ   ║");
            System.out.println("  ╠═══════════════════════════════════════════════╣");
            System.out.println("  ║  1. Thêm quy định                             ║");
            System.out.println("  ║  2. Xóa theo mã                               ║");
            System.out.println("  ║  3. Sửa theo mã                               ║");
            System.out.println("  ║  4. Tìm kiếm theo mã                          ║");
            System.out.println("  ║  5. Hiển thị                                  ║");
            System.out.println("  ║  6. Thống kê tổng & trung bình                ║");
            System.out.println("  ║  7. Ghi file QuyDinhPhuCapThoiVu.txt          ║");
            System.out.println("  ║  0. Thoát (tự lưu)                            ║");
            System.out.println("  ╚═══════════════════════════════════════════════╝");
            System.out.print("👉 Chọn chức năng: ");

            while (!sc.hasNextInt()) {
                System.out.print("❌ Vui lòng nhập số: ");
                sc.next();
            }
            chon = sc.nextInt();
            sc.nextLine();

            switch (chon) {
                case 1 -> Them();
                case 2 -> Xoa();
                case 3 -> Sua();
                case 4 -> TimKiem();
                case 5 -> Xuat();
                case 6 -> ThongKe();
                case 7 -> ghiFile();
                case 0 -> {
                    // lưu trước khi thoát
                    ghiFile();
                    System.out.println("\n👋 Cảm ơn bạn đã sử dụng hệ thống!");
                }
                default -> System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        } while (chon != 0);
    }

    // ======= HỖ TRỢ =======
    private int readIntPositive() {
        while (true) {
            if (sc.hasNextInt()) {
                int v = sc.nextInt();
                sc.nextLine();
                if (v >= 0) return v;
                System.out.print("Vui lòng nhập số >= 0: ");
            } else {
                System.out.print("Vui lòng nhập số nguyên: ");
                sc.next();
            }
        }
    }

    private double readDoubleSafe() {
        while (true) {
            String s = sc.nextLine().trim();
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException e) {
                System.out.print("Vui lòng nhập số thực hợp lệ: ");
            }
        }
    }
 
    // ======= MAIN =======
    public static void main(String[] args) {
        DanhSachQuyDinhPhuCapThoiVu ds = new DanhSachQuyDinhPhuCapThoiVu();
        ds.menu();
    }
}
