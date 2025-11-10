import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class DanhSachPhanCong {

    private PhanCong[] ds;
    private int count;
    private static final int MAX = 100;
    private Scanner sc = new Scanner(System.in);

    public DanhSachPhanCong() {
        ds = new PhanCong[MAX];
        count = 0;
    }

    // ======= THÊM PHÂN CÔNG =======
    public void Them() {
        if (count >= MAX) {
            System.out.println("❌ Danh sách đã đầy!");
            return;
        }

        PhanCong pc = new PhanCong();

        System.out.print("Nhập mã nhân viên: ");
        pc.setMaNV(sc.nextLine().trim());

        System.out.print("Nhập mã dự án: ");
        pc.setMaDA(sc.nextLine().trim());

        System.out.print("Nhập vai trò: ");
        pc.setVaitro(sc.nextLine().trim());

        System.out.print("Nhập tiền thưởng dự án: ");
        while (!sc.hasNextDouble()) {
            System.out.print("❌ Vui lòng nhập số: ");
            sc.next();
        }
        pc.setTienthuongDA(sc.nextDouble());
        sc.nextLine();

        ds[count++] = pc;
        System.out.println("✅ Thêm phân công thành công!");
    }

    // ======= XÓA =======
    public void Xoa() {
        System.out.print("Nhập mã nhân viên cần xóa: ");
        String maNV = sc.nextLine().trim();
        System.out.print("Nhập mã dự án cần xóa: ");
        String maDA = sc.nextLine().trim();

        for (int i = 0; i < count; i++) {
            if (ds[i].getMaNV().equalsIgnoreCase(maNV)
                    && ds[i].getMaDA().equalsIgnoreCase(maDA)) {
                for (int j = i; j < count - 1; j++) {
                    ds[j] = ds[j + 1];
                }
                ds[--count] = null;
                System.out.println("✅ Đã xóa phân công của NV " + maNV + " trong dự án " + maDA);
                return;
            }
        }
        System.out.println("❌ Không tìm thấy phân công cần xóa!");
    }

    // ======= SỬA =======
    public void Sua() {
        System.out.print("Nhập mã nhân viên cần sửa: ");
        String maNV = sc.nextLine().trim();
        System.out.print("Nhập mã dự án cần sửa: ");
        String maDA = sc.nextLine().trim();

        for (int i = 0; i < count; i++) {
            if (ds[i].getMaNV().equalsIgnoreCase(maNV)
                    && ds[i].getMaDA().equalsIgnoreCase(maDA)) {

                System.out.println("✅ Tìm thấy phân công — nhập thông tin mới:");
                System.out.print("Vai trò mới (Enter để bỏ qua): ");
                String vaitro = sc.nextLine().trim();
                if (!vaitro.isEmpty()) ds[i].setVaitro(vaitro);

                System.out.print("Tiền thưởng mới (âm hoặc Enter để bỏ qua): ");
                String tien = sc.nextLine().trim();
                if (!tien.isEmpty()) {
                    try {
                        double val = Double.parseDouble(tien);
                        ds[i].setTienthuongDA(val);
                    } catch (NumberFormatException ignored) {}
                }

                System.out.println("✏️  Đã sửa thành công!");
                return;
            }
        }
        System.out.println("❌ Không tìm thấy phân công cần sửa!");
    }

    // ======= HIỂN THỊ =======
    public void Xuat() {
        if (count == 0) {
            System.out.println("\n❌ Danh sách phân công trống!");
            return;
        }

        System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                       DANH SÁCH PHÂN CÔNG                             ║");
        System.out.println("╠═════════╦═════════╦════════════════════════════╦══════════════════════╣");
        System.out.printf("║ %-7s ║ %-7s ║ %-26s ║ %-20s ║%n",
                "Mã NV", "Mã DA", "Vai trò", "Tiền thưởng");
        System.out.println("╠═════════╬═════════╬════════════════════════════╬══════════════════════╣");

        for (int i = 0; i < count; i++) {
            PhanCong pc = ds[i];
            System.out.printf("║ %-7s ║ %-7s ║ %-26s ║ %-20f ║%n",
                    pc.getMaNV(), pc.getMaDA(), pc.getVaitro(), pc.getTienthuongDA());
        }

        System.out.println("╚═════════╩═════════╩════════════════════════════╩══════════════════════╝");
    }

    // ======= ĐỌC FILE (kiểu giống bạn gửi) =======
    public void docFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("PhanCong.txt"))) {
            String line;
            count = 0;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length < 4) {
                    System.out.println("⚠️ Dòng sai định dạng: " + line);
                    continue;
                }

                String maNV = parts[0].trim();
                String maDA = parts[1].trim();
                String vaitro = parts[2].trim();
                double tienthuong;

                try {
                    tienthuong = Double.parseDouble(parts[3].trim());
                } catch (NumberFormatException e) {
                    System.out.println("⚠️ Lỗi dữ liệu tiền thưởng: " + line);
                    continue;
                }

                PhanCong pc = new PhanCong(maNV, maDA, vaitro, tienthuong);
                ds[count++] = pc;
            }

            System.out.println("✅ Đọc file PhanCong.txt thành công (" + count + " phân công).");

        } catch (IOException e) {
            System.out.println("Lỗi đọc file: " + e.getMessage());
        }
    }

    // ======= GHI FILE =======
    public void ghiFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("PhanCong.txt"))) {
            for (int i = 0; i < count; i++) {
                PhanCong pc = ds[i];
                String line = pc.getMaNV() + "," + pc.getMaDA() + "," + pc.getVaitro() + "," + pc.getTienthuongDA();
                bw.write(line);
                bw.newLine();
            }
            System.out.println("✅ Ghi file PhanCong.txt thành công (" + count + " dòng).");
        } catch (IOException e) {
            System.out.println("Lỗi ghi file: " + e.getMessage());
        }
    }

    // ======= TÌM KIẾM =======
    public void TimKiem() {
        System.out.print("Nhập mã nhân viên cần tìm: ");
        String maNV = sc.nextLine().trim();

        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (ds[i].getMaNV().equalsIgnoreCase(maNV)) {
                if (!found) {
                    System.out.println("\n🔍 Danh sách phân công của nhân viên " + maNV + ":");
                    found = true;
                }
                System.out.println(" - Mã DA: " + ds[i].getMaDA()
                        + " | Vai trò: " + ds[i].getVaitro()
                        + " | Thưởng: " + ds[i].getTienthuongDA());
            }
        }

        if (!found)
            System.out.println("❌ Không tìm thấy phân công của nhân viên này!");
    }

    // ======= THỐNG KÊ =======
    public void ThongKe() {
        double tong = 0;
        for (int i = 0; i < count; i++) tong += ds[i].getTienthuongDA();
        System.out.printf("💰 Tổng tiền thưởng tất cả phân công: %.2f%n", tong);
    }

    // ======= THỐNG KÊ MỚI: Theo dự án =======
    public void thongKeTheoDuAn() {
        if (count == 0) {
            System.out.println("Không có dữ liệu để thống kê theo dự án.");
            return;
        }

        String[] maDA = new String[MAX];
        double[] sum = new double[MAX];
        int[] cnt = new int[MAX];
        int soDuAn = 0;

        for (int i = 0; i < count; i++) {
            String m = ds[i].getMaDA();
            boolean found = false;
            for (int j = 0; j < soDuAn; j++) {
                if (maDA[j].equalsIgnoreCase(m)) {
                    sum[j] += ds[i].getTienthuongDA();
                    cnt[j]++;
                    found = true;
                    break;
                }
            }
            if (!found) {
                maDA[soDuAn] = m;
                sum[soDuAn] = ds[i].getTienthuongDA();
                cnt[soDuAn] = 1;
                soDuAn++;
            }
        }

        System.out.println("\n📊 Thống kê theo dự án:");
        System.out.printf("%-10s | %-10s | %-15s%n", "Mã dự án", "Số phân công", "Tổng tiền thưởng");
        System.out.println("-------------------------------------------------");
        for (int i = 0; i < soDuAn; i++) {
            System.out.printf("%-10s | %-12d | %-15.2f%n", maDA[i], cnt[i], sum[i]);
        }
    }

    // ======= MENU CHÍNH (đã bỏ mục ĐỌC FILE khỏi menu) =======
    public void menu() {
        int chon;
        do {
            System.out.println("╔═══════════════════════════════════════════════╗");
            System.out.println("║        HỆ THỐNG QUẢN LÝ PHÂN CÔNG NHÂN SỰ     ║");
            System.out.println("╠═══════════════════════════════════════════════╣");
            System.out.println("║  1. Thêm phân công                            ║");
            System.out.println("║  2. Xóa phân công                             ║");
            System.out.println("║  3. Sửa phân công                             ║");
            System.out.println("║  4. Tìm kiếm theo mã nhân viên                ║");
            System.out.println("║  5. Hiển thị tất cả phân công                 ║");
            System.out.println("║  6. Thống kê tổng thưởng                      ║");
            System.out.println("║  7. Thống kê theo dự án                       ║");
            System.out.println("║  8. Ghi file PhanCong.txt                     ║");
            System.out.println("║  0. Thoát                                     ║");
            System.out.println("╚═══════════════════════════════════════════════╝");
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
                case 7 -> thongKeTheoDuAn();
                case 8 -> ghiFile();
                case 0 -> System.out.println("\n👋 Cảm ơn bạn đã sử dụng hệ thống!");
                default -> System.out.println("❌ Lựa chọn không hợp lệ!");
            }

        } while (chon != 0);
    }

    // ======= MAIN =======
    public static void main(String[] args) {
        DanhSachPhanCong dspc = new DanhSachPhanCong();
        // đọc file khi khởi động (menu không còn mục 'đọc file')
        dspc.docFile();
        dspc.menu();
    }
}
