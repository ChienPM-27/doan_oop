import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DanhSachChamCongNgay {
    private Chamcongngay[] dsChamCong;
    private int soLuong;
    private static final int MAX = 1000;
    private Scanner sc = new Scanner(System.in);

    public DanhSachChamCongNgay() {
        dsChamCong = new Chamcongngay[MAX];
        soLuong = 0;
    }

    // ===== ĐỌC FILE =====
    public void docFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("DanhSachChamCongNgay.txt.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length < 5) continue;

                String mangaycong = parts[0];
                int ngay = Integer.parseInt(parts[1]);
                int thang = Integer.parseInt(parts[2]);
                int nam = Integer.parseInt(parts[3]);
                String manv = parts[4];

                dsChamCong[soLuong++] = new Chamcongngay(mangaycong, ngay, thang, nam, manv);
            }
            System.out.println("✅ Đọc file thành công (" + soLuong + " bản ghi).");
        } catch (IOException e) {
            System.out.println("❌ Lỗi đọc file: " + e.getMessage());
        }
    }

    // ===== GHI FILE =====
    public void ghiFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("DSChamCongNgay.txt"))) {
            for (int i = 0; i < soLuong; i++) {
                Chamcongngay cc = dsChamCong[i];
                bw.write(cc.getMangaycong() + "," + cc.getNgay() + "," +
                        cc.getThang() + "," + cc.getNam() + "," + cc.getMans());
                bw.newLine();
            }
            System.out.println("✅ Ghi file thành công.");
        } catch (IOException e) {
            System.out.println("❌ Lỗi ghi file: " + e.getMessage());
        }
    }

    // ===== THÊM CHẤM CÔNG =====
    public void themChamCong() {
        if (soLuong >= MAX) {
            System.out.println("❌ Danh sách đã đầy!");
            return;
        }

        System.out.println("\n=== THÊM CHẤM CÔNG NGÀY ===");

        System.out.print("Mã nhân viên: ");
        String manv = sc.nextLine().trim();

        System.out.print("Ngày (dd): ");
        int ngay = sc.nextInt();

        System.out.print("Tháng (mm): ");
        int thang = sc.nextInt();

        System.out.print("Năm (yyyy): ");
        int nam = sc.nextInt();
        sc.nextLine();

        // Kiểm tra đã chấm công chưa (1 nhân viên chỉ chấm 1 lần/ngày)
        for (int i = 0; i < soLuong; i++) {
            if (dsChamCong[i].getMans().equalsIgnoreCase(manv) &&
                    dsChamCong[i].getNgay() == ngay &&
                    dsChamCong[i].getThang() == thang &&
                    dsChamCong[i].getNam() == nam) {
                System.out.println("❌ Nhân viên đã chấm công ngày này!");
                return;
            }
        }

        // Tự động sinh mã duy nhất: CC + Năm + Tháng + Ngày + Mã NV
        String mangaycong = String.format("CC%04d%02d%02d_%s", nam, thang, ngay, manv);

        dsChamCong[soLuong++] = new Chamcongngay(mangaycong, ngay, thang, nam, manv);
        System.out.println("✅ Thêm chấm công thành công! Mã: " + mangaycong);
    }

    // ===== XÓA CHẤM CÔNG =====
    public void xoaChamCong() {
        System.out.print("\nMã nhân viên: ");
        String manv = sc.nextLine().trim();

        System.out.print("Ngày (dd): ");
        int ngay = sc.nextInt();

        System.out.print("Tháng (mm): ");
        int thang = sc.nextInt();

        System.out.print("Năm (yyyy): ");
        int nam = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < soLuong; i++) {
            if (dsChamCong[i].getMans().equalsIgnoreCase(manv) &&
                    dsChamCong[i].getNgay() == ngay &&
                    dsChamCong[i].getThang() == thang &&
                    dsChamCong[i].getNam() == nam) {

                // Dịch mảng
                for (int j = i; j < soLuong - 1; j++) {
                    dsChamCong[j] = dsChamCong[j + 1];
                }
                dsChamCong[--soLuong] = null;
                System.out.println("✅ Đã xóa chấm công!");
                return;
            }
        }
        System.out.println("❌ Không tìm thấy!");
    }

    // ===== HIỂN THỊ DANH SÁCH =====
    public void hienThiDanhSach() {
        if (soLuong == 0) {
            System.out.println("\n❌ Danh sách trống!");
            return;
        }

        System.out.println("\n" + "=".repeat(90));
        System.out.println("DANH SÁCH CHẤM CÔNG NGÀY");
        System.out.println("=".repeat(90));
        System.out.printf("%-25s | %-10s | %-12s | %-15s%n",
                "Mã chấm công", "Ngày", "Tháng/Năm", "Mã NV");
        System.out.println("-".repeat(90));

        for (int i = 0; i < soLuong; i++) {
            Chamcongngay cc = dsChamCong[i];
            System.out.printf("%-25s | %02d        | %02d/%04d      | %-15s%n",
                    cc.getMangaycong(),
                    cc.getNgay(),
                    cc.getThang(),
                    cc.getNam(),
                    cc.getMans());
        }
        System.out.println("=".repeat(90));
        System.out.println("Tổng số: " + soLuong + " bản ghi");
    }

    // ===== TÌM KIẾM THEO MÃ NHÂN VIÊN =====
    public void timKiemTheoMaNV() {
        System.out.print("\nNhập mã nhân viên: ");
        String manv = sc.nextLine().trim();

        boolean timThay = false;
        System.out.println("\n=== CHẤM CÔNG CỦA NHÂN VIÊN " + manv + " ===");

        for (int i = 0; i < soLuong; i++) {
            if (dsChamCong[i].getMans().equalsIgnoreCase(manv)) {
                if (!timThay) {
                    System.out.printf("%-25s | %-12s%n", "Mã chấm công", "Ngày");
                    System.out.println("-".repeat(40));
                    timThay = true;
                }
                System.out.printf("%-25s | %02d/%02d/%04d%n",
                        dsChamCong[i].getMangaycong(),
                        dsChamCong[i].getNgay(),
                        dsChamCong[i].getThang(),
                        dsChamCong[i].getNam());
            }
        }

        if (!timThay) {
            System.out.println("❌ Không tìm thấy!");
        }
    }

    // ===== THỐNG KÊ THEO THÁNG =====
    public void thongKeTheoThang() {
        System.out.print("\nNhập tháng: ");
        int thang = sc.nextInt();
        System.out.print("Nhập năm: ");
        int nam = sc.nextInt();
        sc.nextLine();

        // Đếm số ngày công của từng nhân viên
        String[] maNV = new String[MAX];
        int[] soNgayCong = new int[MAX];
        int soNV = 0;

        for (int i = 0; i < soLuong; i++) {
            if (dsChamCong[i].getThang() == thang && dsChamCong[i].getNam() == nam) {
                String ma = dsChamCong[i].getMans();

                boolean timThay = false;
                for (int j = 0; j < soNV; j++) {
                    if (maNV[j].equalsIgnoreCase(ma)) {
                        soNgayCong[j]++;
                        timThay = true;
                        break;
                    }
                }

                if (!timThay) {
                    maNV[soNV] = ma;
                    soNgayCong[soNV] = 1;
                    soNV++;
                }
            }
        }

        if (soNV == 0) {
            System.out.println("❌ Không có dữ liệu tháng " + thang + "/" + nam);
            return;
        }

        System.out.println("\n=== THỐNG KÊ THÁNG " + thang + "/" + nam + " ===");
        System.out.printf("%-15s | %-15s%n", "Mã NV", "Số ngày công");
        System.out.println("-".repeat(40));

        for (int i = 0; i < soNV; i++) {
            System.out.printf("%-15s | %-15d%n", maNV[i], soNgayCong[i]);
        }
    }

    // ===== MENU =====
    public void menu() {
        int chon;
        do {
            System.out.println("\n╔═════════════════════════════════════╗");
            System.out.println("║      QUẢN LÝ CHẤM CÔNG NGÀY           ║");
            System.out.println("╠═══════════════════════════════════════╣");
            System.out.println("║  1. Thêm chấm công                    ║");
            System.out.println("║  2. Xóa chấm công                     ║");
            System.out.println("║  3. Hiển thị danh sách                ║");
            System.out.println("║  4. Tìm kiếm theo mã nhân viên        ║");
            System.out.println("║  5. Thống kê theo tháng               ║");
            System.out.println("║  6. Ghi file                          ║");
            System.out.println("║  0. Thoát                             ║");
            System.out.println("╚═══════════════════════════════════════╝");
            System.out.print("👉 Chọn: ");

            chon = sc.nextInt();
            sc.nextLine();

            switch (chon) {
                case 1: themChamCong(); break;
                case 2: xoaChamCong(); break;
                case 3: hienThiDanhSach(); break;
                case 4: timKiemTheoMaNV(); break;
                case 5: thongKeTheoThang(); break;
                case 6: ghiFile(); break;
                case 0: System.out.println("👋 Thoát!"); break;
                default: System.out.println("❌ Không hợp lệ!");
            }
        } while (chon != 0);
    }

    // ===== GETTERS =====
    public Chamcongngay[] getDsChamCong() {
        return dsChamCong;
    }

    public int getSoLuong() {
        return soLuong;
    }
}