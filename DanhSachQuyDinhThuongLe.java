import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DanhSachQuyDinhThuongLe {
    private QuyDinhThuongLe[] ds;
    private int count;
    private static final int MAX = 100;
    private Scanner sc = new Scanner(System.in);

    public DanhSachQuyDinhThuongLe() {
        ds = new QuyDinhThuongLe[MAX];
        count = 0;
    }

    // ===== ĐỌC FILE =====
    public void docFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("DSQuyDinhThuongLe.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length < 4) {
                    System.out.println("⚠️ Dòng sai định dạng: " + line);
                    continue;
                }

                String mathuong = parts[0];
                String tenle = parts[1];
                String ngayle = parts[2];
                double mucthuong = Double.parseDouble(parts[3]);

                ds[count++] = new QuyDinhThuongLe(mathuong, tenle, ngayle, mucthuong);
            }

            System.out.println("✅ Đọc file DSQuyDinhThuongLe.txt thành công (" + count + " quy định).");

        } catch (IOException e) {
            System.out.println("❌ Lỗi đọc file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("❌ Lỗi định dạng số: " + e.getMessage());
        }
    }

    // ===== GHI FILE =====
    public void ghiFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("DSQuyDinhThuongLe.txt"))) {
            for (int i = 0; i < count; i++) {
                QuyDinhThuongLe qd = ds[i];
                String line = qd.getMathuong() + "," + 
                             qd.getTenle() + "," + 
                             qd.getNgayle() + "," + 
                             qd.getMucthuong();
                bw.write(line);
                bw.newLine();
            }
            System.out.println("✅ Ghi file DSQuyDinhThuongLe.txt thành công (" + count + " quy định).");
        } catch (IOException e) {
            System.out.println("❌ Lỗi ghi file: " + e.getMessage());
        }
    }

    // ===== THÊM QUY ĐỊNH =====
    public void them() {
        if (count >= MAX) {
            System.out.println("❌ Danh sách đã đầy!");
            return;
        }

        System.out.println("\n=== THÊM QUY ĐỊNH THƯỞNG LỄ ===");
        System.out.print("Mã thưởng: ");
        String mathuong = sc.nextLine().trim();

        // Kiểm tra trùng mã
        for (int i = 0; i < count; i++) {
            if (ds[i].getMathuong().equalsIgnoreCase(mathuong)) {
                System.out.println("❌ Mã thưởng đã tồn tại!");
                return;
            }
        }

        System.out.print("Tên lễ: ");
        String tenle = sc.nextLine().trim();

        System.out.print("Ngày lễ (dd/mm): ");
        String ngayle = sc.nextLine().trim();

        System.out.print("Mức thưởng: ");
        double mucthuong = sc.nextDouble();
        sc.nextLine();

        ds[count++] = new QuyDinhThuongLe(mathuong, tenle, ngayle, mucthuong);
        System.out.println("✅ Đã thêm quy định thành công!");
    }

    // ===== XÓA QUY ĐỊNH =====
    public void xoa() {
        System.out.print("\nNhập mã thưởng cần xóa: ");
        String ma = sc.nextLine().trim();

        for (int i = 0; i < count; i++) {
            if (ds[i].getMathuong().equalsIgnoreCase(ma)) {
                System.out.print("⚠️ Bạn có chắc muốn xóa quy định '" + ds[i].getTenle() + "'? (y/n): ");
                if (!sc.nextLine().equalsIgnoreCase("y")) {
                    System.out.println("Đã hủy thao tác xóa.");
                    return;
                }

                // Dịch chuyển mảng
                for (int j = i; j < count - 1; j++) {
                    ds[j] = ds[j + 1];
                }
                ds[--count] = null;
                System.out.println("✅ Đã xóa quy định!");
                return;
            }
        }
        System.out.println("❌ Không tìm thấy mã thưởng: " + ma);
    }
    // ===== SỬA QUY ĐỊNH =====
    public void sua() {
        System.out.print("\nNhập mã thưởng cần sửa: ");
        String ma = sc.nextLine().trim();

        for (int i = 0; i < count; i++) {
            if (ds[i].getMathuong().equalsIgnoreCase(ma)) {
                QuyDinhThuongLe qd = ds[i];

                System.out.println("\n=== SỬA QUY ĐỊNH THƯỞNG LỄ ===");
                System.out.println("Hiện tại:");
                System.out.printf("Tên lễ: %s | Ngày lễ: %s | Mức thưởng: %.0f%n",
                        qd.getTenle(), qd.getNgayle(), qd.getMucthuong());

                System.out.print("\nNhập tên lễ mới (Enter để giữ nguyên): ");
                String tenleMoi = sc.nextLine().trim();
                if (!tenleMoi.isEmpty()) {
                    qd.setTenle(tenleMoi);
                }

                System.out.print("Nhập ngày lễ mới (dd/mm, Enter để giữ nguyên): ");
                String ngayleMoi = sc.nextLine().trim();
                if (!ngayleMoi.isEmpty()) {
                    qd.setNgayle(ngayleMoi);
                }

                System.out.print("Nhập mức thưởng mới (Enter để giữ nguyên): ");
                String mucMoiStr = sc.nextLine().trim();
                if (!mucMoiStr.isEmpty()) {
                    try {
                        double mucthuongMoi = Double.parseDouble(mucMoiStr);
                        qd.setMucthuong(mucthuongMoi);
                    } catch (NumberFormatException e) {
                        System.out.println("⚠️ Giá trị không hợp lệ, giữ nguyên mức thưởng cũ.");
                    }
                }

                System.out.println("✅ Đã cập nhật thông tin quy định!");
                return;
            }
        }
        System.out.println("❌ Không tìm thấy mã thưởng: " + ma);
    }

    // ===== HIỂN THỊ DANH SÁCH =====
    public void hienThi() {
        if (count == 0) {
            System.out.println("\n❌ Danh sách trống!");
            return;
        }

        System.out.println("\n" + "=".repeat(90));
        System.out.println("DANH SÁCH QUY ĐỊNH THƯỞNG LỄ");
        System.out.println("=".repeat(90));
        System.out.printf("%-12s | %-30s | %-12s | %-15s%n",
                "Mã thưởng", "Tên lễ", "Ngày lễ", "Mức thưởng");
        System.out.println("-".repeat(90));

        for (int i = 0; i < count; i++) {
            QuyDinhThuongLe qd = ds[i];
            System.out.printf("%-12s | %-30s | %-12s | %,15.0f%n",
                    qd.getMathuong(),
                    qd.getTenle(),
                    qd.getNgayle(),
                    qd.getMucthuong());
        }
        System.out.println("=".repeat(90));
        System.out.println("Tổng số: " + count + " quy định");
    }

    // ===== MENU =====
    // ===== MENU =====
    public void menu() {
        int chon;
        do {
            System.out.println("\n╔═══════════════════════════════════════════════╗");
            System.out.println("║        QUẢN LÝ QUY ĐỊNH THƯỞNG - LỄ             ║");
            System.out.println("╠═════════════════════════════════════════════════╣");
            System.out.println("║  1. Đọc danh sách từ file                       ║");
            System.out.println("║  2. Thêm quy định                               ║");
            System.out.println("║  3. Xóa quy định                                ║");
            System.out.println("║  4. Sửa quy định                                ║");
            System.out.println("║  5. Hiển thị tất cả quy định                    ║");
            System.out.println("║  6. Ghi danh sách ra file                       ║");
            System.out.println("║  0. Thoát                                       ║");
            System.out.println("╚═════════════════════════════════════════════════╝");
            System.out.print("👉 Chọn chức năng: ");

            while (!sc.hasNextInt()) {
                System.out.print("❌ Vui lòng nhập số: ");
                sc.next();
            }
            chon = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (chon) {
                case 1:
                    docFile();
                    break;
                case 2:
                    them();
                    break;
                case 3:
                    xoa();
                    break;
                case 4:
                    sua();
                    break;
                case 5:
                    hienThi();
                    break;
                case 6:
                    ghiFile();
                    break;
                case 0:
                    System.out.println("\n👋 Cảm ơn bạn đã sử dụng chương trình!");
                    break;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ! Vui lòng chọn lại.");
            }

        } while (chon != 0);
    }



    // ===== GETTERS =====
    public QuyDinhThuongLe[] getDs() {
        return ds;
    }

    public int getCount() {
        return count;
    }
}