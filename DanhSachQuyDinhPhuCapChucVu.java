import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class DanhSachQuyDinhPhuCapChucVu {

    private QuyDinhPhuCapChucVu[] ds;
    private int count;
    private static final int MAX = 100;
    private Scanner sc = new Scanner(System.in);

    public DanhSachQuyDinhPhuCapChucVu() {
        ds = new QuyDinhPhuCapChucVu[MAX];
        count = 0;
    }

    // Hàm xuất danh sách (hiển thị đẹp)
    public void Xuat() {
        if (count == 0) {
            System.out.println("\nDanh sach rong, khong co du lieu de hien thi!");
            return;
        }

        System.out.println("╔══════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                           DANH SACH QUY DINH PHU CAP CHUC VU                                 ║");
        System.out.println("╠════════════╦════════════════════════════════╦═══════════════╦════════════════════════════════╣");
        System.out.printf("║ %-10s ║ %-30s ║ %-13s ║ %-30s ║%n",
                "Ma CV", "Ten Chuc Vu", "Phu Cap", "Ghi Chu");
        System.out.println("╠════════════╬════════════════════════════════╬═══════════════╬════════════════════════════════╣");

        for (int i = 0; i < count; i++) {
            QuyDinhPhuCapChucVu qd = ds[i];
            String ma = qd.getMaCV() == null ? "" : qd.getMaCV();
            String ten = qd.getTenCV() == null ? "" : qd.getTenCV();
            String phuCap = (qd.getPhucap() == null) ? "" : String.format("%.2f", qd.getPhucap());
            String ghiChu = qd.getGhichu() == null ? "" : qd.getGhichu();

            System.out.printf("║ %-10s ║ %-30s ║ %-13s ║ %-30s ║%n",
                    ma, ten, phuCap, ghiChu);
        }

        System.out.println("╚════════════╩════════════════════════════════╩═══════════════╩════════════════════════════════╝");
    }

    // Hàm thêm quy định phụ cấp
    public void Them() {
        if (count >= MAX) {
            System.out.println("Danh sach da day, khong the them moi!");
            return;
        }

        QuyDinhPhuCapChucVu qd = new QuyDinhPhuCapChucVu();

        System.out.print("Nhap ma chuc vu: ");
        qd.setMaCV(sc.nextLine().trim());

        System.out.print("Nhap ten chuc vu: ");
        qd.setTenCV(sc.nextLine().trim());

        System.out.print("Nhap muc phu cap: ");
        qd.setPhucap(readDoubleSafe());

        System.out.print("Nhap ghi chu: ");
        qd.setGhichu(sc.nextLine().trim());

        ds[count++] = qd;
        System.out.println("✅ Da them thanh cong!");
    }

    // Hàm xoá quy định phụ cấp theo mã
    public void Xoa() {
        System.out.print("Nhap ma chuc vu can xoa: ");
        String ma = sc.nextLine().trim();

        for (int i = 0; i < count; i++) {
            if (ds[i].getMaCV().equalsIgnoreCase(ma)) {
                for (int j = i; j < count - 1; j++) {
                    ds[j] = ds[j + 1];
                }
                ds[--count] = null;
                System.out.println("🗑️  Da xoa phu cap cho chuc vu co ma: " + ma);
                return;
            }
        }
        System.out.println("⚠️  Khong tim thay ma chuc vu can xoa!");
    }

    // Hàm sửa thông tin phụ cấp theo mã
    public void Sua() {
        System.out.print("Nhap ma chuc vu can sua: ");
        String ma = sc.nextLine().trim();

        for (int i = 0; i < count; i++) {
            if (ds[i].getMaCV().equalsIgnoreCase(ma)) {
                System.out.println("Nhap thong tin moi cho chuc vu co ma " + ma + ":");

                System.out.print("Nhap ten chuc vu moi (Enter để bỏ qua): ");
                String ten = sc.nextLine().trim();
                if (!ten.isEmpty()) ds[i].setTenCV(ten);

                System.out.print("Nhap muc phu cap moi (Enter để bỏ qua): ");
                String ph = sc.nextLine().trim();
                if (!ph.isEmpty()) {
                    try {
                        ds[i].setPhucap(Double.parseDouble(ph));
                    } catch (NumberFormatException ignored) {}
                }

                System.out.print("Nhap ghi chu moi (Enter để bỏ qua): ");
                String gc = sc.nextLine().trim();
                if (!gc.isEmpty()) ds[i].setGhichu(gc);

                System.out.println("✏️  Da sua thanh cong!");
                return;
            }
        }
        System.out.println("⚠️  Khong tim thay ma chuc vu can sua!");
    }

    // Hàm tìm kiếm phụ cấp theo mã
    public void TimKiem() {
        System.out.print("Nhap ma chuc vu can tim: ");
        String ma = sc.nextLine().trim();

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
        int dem = 0;
        for (int i = 0; i < count; i++) {
            Double p = ds[i].getPhucap();
            if (p != null) {
                tong += p;
                dem++;
            }
        }
        System.out.printf("\n📊 Tong muc phu cap cho tat ca chuc vu: %.2f%n", tong);
        if (dem > 0) {
            System.out.printf("📈 Muc phu cap trung binh: %.2f%n", tong / dem);
        } else {
            System.out.println("📈 Muc phu cap trung binh: N/A");
        }
    }

    // ==== Thống kê nhỏ: số chức vụ có phụ cấp > 0, max và min ====
    public void thongKeNho() {
        if (count == 0) {
            System.out.println("Không có dữ liệu để thống kê.");
            return;
        }

        int soCoPhuCap = 0;
        Double max = null;
        Double min = null;

        for (int i = 0; i < count; i++) {
            Double p = ds[i].getPhucap();
            if (p == null) continue;
            if (p > 0) soCoPhuCap++;

            if (max == null || (p != null && p > max)) max = p;
            if (min == null || (p != null && p < min)) min = p;
        }

        System.out.println("\n📊 Thống kê nhỏ:");
        System.out.println(" - Số chức vụ có phụ cấp > 0: " + soCoPhuCap);
        if (max != null) System.out.printf(" - Mức phụ cấp lớn nhất: %.2f%n", max);
        else System.out.println(" - Mức phụ cấp lớn nhất: N/A");
        if (min != null) System.out.printf(" - Mức phụ cấp nhỏ nhất: %.2f%n", min);
        else System.out.println(" - Mức phụ cấp nhỏ nhất: N/A");
    }

    // ======= ĐỌC FILE =======
    public void docFile() {
        File file = new File("DanhSachQuyDinhPhuCapChucVu.txt");
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            count = 0;
            while ((line = br.readLine()) != null && count < MAX) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length < 3) continue;

                String ma = parts[0].trim();
                String ten = parts[1].trim();
                Double phuCap = null;
                try {
                    phuCap = Double.parseDouble(parts[2].trim());
                } catch (NumberFormatException ignored) {}
                String ghichu = (parts.length >= 4) ? parts[3].trim() : "";

                ds[count++] = new QuyDinhPhuCapChucVu(ma, ten, phuCap, ghichu);
            }
            System.out.println("✅ Đã đọc file DanhSachQuyDinhPhuCapChucVu.txt (" + count + " dòng).");
        } catch (IOException e) {
            System.out.println("❌ Lỗi đọc file: " + e.getMessage());
        }
    }

    // ======= GHI FILE =======
    public void ghiFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("DanhSachQuyDinhPhuCapChucVu.txt"))) {
            for (int i = 0; i < count; i++) {
                QuyDinhPhuCapChucVu qd = ds[i];
                if (qd == null) continue;
                String line = String.join(",",
                        safeString(qd.getMaCV()),
                        safeString(qd.getTenCV()),
                        (qd.getPhucap() == null) ? "" : qd.getPhucap().toString(),
                        safeString(qd.getGhichu())
                );
                bw.write(line);
                bw.newLine();
            }
            System.out.println("✅ Ghi file QuyDinhPhuCapChucVu.txt thành công (" + count + " dòng).");
        } catch (IOException e) {
            System.out.println("❌ Lỗi ghi file: " + e.getMessage());
        }
    }

    private String safeString(String s) {
        if (s == null) return "";
        return s.replaceAll("\\r?\\n", " ").replace(",", ";").trim();
    }

    // ======= MENU CHÍNH =======
    public void menu() {
        docFile(); // tự động đọc file khi khởi động

        int chon;
        do {
            System.out.println("╔════════════════════════════════════════════════╗");
            System.out.println("║   HỆ THỐNG QUẢN LÝ QUY ĐỊNH PHỤ CẤP CHỨC VỤ    ║");
            System.out.println("╠════════════════════════════════════════════════╣");
            System.out.println("║  1. Thêm quy định                              ║");
            System.out.println("║  2. Xóa theo mã                                ║");
            System.out.println("║  3. Sửa theo mã                                ║");
            System.out.println("║  4. Tìm kiếm theo mã                           ║");
            System.out.println("║  5. Hiển thị tất cả                            ║");
            System.out.println("║  6. Thống kê nhỏ (số chức vụ, max/min)         ║");
            System.out.println("║  7. Thống kê tổng & trung bình                 ║");
            System.out.println("║  8. Ghi file QuyDinhPhuCapChucVu.txt           ║");
            System.out.println("║  0. Thoát (tự lưu)                             ║");
            System.out.println("╚════════════════════════════════════════════════╝");
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
                case 6 -> thongKeNho();
                case 7 -> ThongKe();
                case 8 -> ghiFile();
                case 0 -> {
                    ghiFile();
                    System.out.println("\n👋 Cảm ơn bạn đã sử dụng hệ thống!");
                }
                default -> System.out.println("❌ Lựa chọn không hợp lệ!");
            }

        } while (chon != 0);
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

    public static void main(String[] args) {
        DanhSachQuyDinhPhuCapChucVu dsqd = new DanhSachQuyDinhPhuCapChucVu();
        dsqd.menu();
    }
}
