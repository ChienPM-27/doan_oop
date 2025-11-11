import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DanhSachChamCongThang {
    private Chamcongthang[] chamcong;
    private int soluong;
    private static final int MAX = 500;
    private Scanner sc = new Scanner(System.in);

    public DanhSachChamCongThang() {
        chamcong = new Chamcongthang[MAX];
        soluong = 0;
    }

    // Themm cham cong thang
    public void themChamCongThang() {
        if (soluong >= MAX) {
            System.out.println("Danh sach da day!");
            return;
        }

        System.out.println("\n=== THEM CHAM CONG THANG ===");
        System.out.print("Ma cong thang: ");
        String macongthang = sc.nextLine();

        System.out.print("Ma nhan vien: ");
        String manv = sc.nextLine();

        System.out.print("Thang: ");
        int thang = sc.nextInt();

        System.out.print("Nam: ");
        int nam = sc.nextInt();

        System.out.print("So ngay cong: ");
        int songaycong = sc.nextInt();
        sc.nextLine();

        chamcong[soluong] = new Chamcongthang(macongthang, thang, nam, songaycong, manv);
        soluong++;
        System.out.println("✓ Them cham cong thang thanh cong!");
    }

    // Xuat danh sach
    public void xuatDanhSach() {
        if (soluong == 0) {
            System.out.println("Danh sach cham cong thang rong!");
            return;
        }

        System.out.println("\n========== DANH SACH CHAM CONG THANG ==========");
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.printf("| %-15s | %-10s | %-8s | %-10s | %-10s |\n",
                "Ma cong thang", "Ma NV", "Thang/Nam", "Ngay cong", "Ngay nghi");
        System.out.println("-----------------------------------------------------------------------------------");

        for (int i = 0; i < soluong; i++) {
            System.out.println("| " + chamcong[i].toString() + " |");
        }
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("Tong so: " + soluong + " ban ghi");
    }

    // Tim kiem theo ma nhan vien
    public void timKiemTheoMaNV() {
        System.out.print("Nhap ma nhan vien can tim: ");
        String manv = sc.nextLine();

        boolean timThay = false;
        System.out.println("\n=== KET QUA TIM KIEM ===");

        for (int i = 0; i < soluong; i++) {
            if (chamcong[i].getManv().equals(manv)) {
                chamcong[i].xuat();
                timThay = true;
            }
        }

        if (!timThay) {
            System.out.println("Khong tim thay cham cong cua nhan vien: " + manv);
        }
    }

    // Tim kiem theo thang nam
    public void timKiemTheoThangNam() {
        System.out.print("Nhap thang: ");
        int thang = sc.nextInt();
        System.out.print("Nhap nam: ");
        int nam = sc.nextInt();
        sc.nextLine();

        boolean timThay = false;
        System.out.println("\n=== CHAM CONG THANG " + thang + "/" + nam + " ===");

        for (int i = 0; i < soluong; i++) {
            if (chamcong[i].getThang() == thang && chamcong[i].getNam() == nam) {
                chamcong[i].xuat();
                timThay = true;
            }
        }

        if (!timThay) {
            System.out.println("Khong co du lieu cham cong thang " + thang + "/" + nam);
        }
    }

    // Cap nhat so ngay cong
    public void capNhatSoNgayCong() {
        System.out.print("Nhap ma cong thang can cap nhat: ");
        String ma = sc.nextLine();

        for (int i = 0; i < soluong; i++) {
            if (chamcong[i].getMacongthang().equals(ma)) {
                System.out.println("So ngay cong hien tai: " + chamcong[i].getSongaycong());
                System.out.print("Nhap so ngay cong moi: ");
                int songaycongmoi = sc.nextInt();
                sc.nextLine();

                chamcong[i].setSongaycong(songaycongmoi);
                System.out.println("✓ Cap nhat thanh cong!");
                return;
            }
        }
        System.out.println("✗ Khong tim thay ma cong thang: " + ma);
    }

    // Thong ke
    public void thongKe() {
        System.out.print("Nhap thang: ");
        int thang = sc.nextInt();
        System.out.print("Nhap nam: ");
        int nam = sc.nextInt();
        sc.nextLine();

        int tongNhanVien = 0;
        int tongNgayCong = 0;
        int tongNgayNghi = 0;

        for (int i = 0; i < soluong; i++) {
            if (chamcong[i].getThang() == thang && chamcong[i].getNam() == nam) {
                tongNhanVien++;
                tongNgayCong += chamcong[i].getSongaycong();
                tongNgayNghi += chamcong[i].tinhSoNgayNghi();
            }
        }

        if (tongNhanVien > 0) {
            System.out.println("\n=== THONG KE CHAM CONG THANG " + thang + "/" + nam + " ===");
            System.out.println("Tong so nhan vien: " + tongNhanVien);
            System.out.println("Tong ngay cong: " + tongNgayCong);
            System.out.println("Tong ngay nghi: " + tongNgayNghi);
            System.out.println("Trung binh ngay cong/nhan vien: " + (double) tongNgayCong / tongNhanVien);
        } else {
            System.out.println("Khong co du lieu thang " + thang + "/" + nam);
        }
    }

    // Xoa cham cong thang
    public void xoaChamCong() {
        System.out.print("Nhap ma cong thang can xoa: ");
        String ma = sc.nextLine();

        for (int i = 0; i < soluong; i++) {
            if (chamcong[i].getMacongthang().equals(ma)) {
                for (int j = i; j < soluong - 1; j++) {
                    chamcong[j] = chamcong[j + 1];
                }
                chamcong[--soluong] = null;
                System.out.println("✓ Xoa thanh cong!");
                return;
            }
        }
        System.out.println("✗ Khong tim thay ma cong thang: " + ma);
    }

    // ===== DOC FILE =====
    public void docFile() {
        String fileName = "DanhSachChamCongThang.txt.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int readCount = 0;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length < 5) {
                    System.out.println("⚠️ Dòng sai định dạng (bỏ qua): " + line);
                    continue;
                }

                try {
                    String maCongThang = parts[0].trim();
                    String maNV = parts[1].trim();
                    int thang = Integer.parseInt(parts[2].trim());
                    int nam = Integer.parseInt(parts[3].trim());
                    int soNgayCong = Integer.parseInt(parts[4].trim());

                    if (soluong >= MAX) {
                        System.out.println("⚠️ Danh sách đã đầy, dừng đọc file.");
                        break;
                    }

                    chamcong[soluong++] = new Chamcongthang(maCongThang, thang, nam, soNgayCong, maNV);
                    readCount++;
                } catch (NumberFormatException nfe) {
                    System.out.println("⚠️ Lỗi số ở dòng (bỏ qua): " + line + " -> " + nfe.getMessage());
                } catch (Exception e) {
                    System.out.println("⚠️ Lỗi khi xử lý dòng (bỏ qua): " + line + " -> " + e.getMessage());
                }
            }
            System.out.println("✅ Đã đọc file " + fileName + " (" + readCount + " bản ghi thêm). Tổng hiện có: " + soluong);
        } catch (IOException e) {
            System.out.println("❌ Lỗi đọc file: " + e.getMessage());
        }
    }
    // ===== GHI FILE =====
    public void ghiFile() {
        String fileName = "DanhSachChamCongThang.txt.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            int writeCount = 0;
            for (int i = 0; i < soluong; i++) {
                Chamcongthang c = chamcong[i];
                if (c == null) continue;
                // tránh dấu phẩy trong trường văn bản (nếu có)
                String safeMaCT = c.getMacongthang() == null ? "" : c.getMacongthang().replace(",", ";");
                String safeMaNV = c.getManv() == null ? "" : c.getManv().replace(",", ";");
                String line = safeMaCT + "," + safeMaNV + "," + c.getThang() + "," + c.getNam() + "," + c.getSongaycong();
                bw.write(line);
                bw.newLine();
                writeCount++;
            }
            System.out.println("✅ Ghi file " + fileName + " thành công (" + writeCount + " bản ghi).");
        } catch (IOException e) {
            System.out.println("❌ Lỗi ghi file: " + e.getMessage());
        }
    }

    // Getters/Setters nếu cần (giữ nguyên cấu trúc hiện tại)
    public Chamcongthang[] getChamcong() {
        return chamcong;
    }

    public int getSoluong() {
        return soluong;
    }
    // Menu
    // ===== MENU =====
    public void menu() {
        int chon;
        do {
            System.out.println("\n╔═══════════════════════════════════════════════╗");
            System.out.println("║          HỆ THỐNG QUẢN LÝ CHẤM CÔNG THÁNG       ║");
            System.out.println("╠═════════════════════════════════════════════════╣");
            System.out.println("║  1. Thêm chấm công tháng                        ║");
            System.out.println("║  2. Xóa chấm công theo mã                       ║");
            System.out.println("║  3. Tìm kiếm theo mã nhân viên                  ║");
            System.out.println("║  4. Tìm kiếm theo tháng/năm                     ║");
            System.out.println("║  5. Cập nhật số ngày công                       ║");
            System.out.println("║  6. Hiển thị tất cả chấm công tháng             ║");
            System.out.println("║  7. Thống kê theo tháng/năm                     ║");
            System.out.println("║  8. Đọc dữ liệu từ file                         ║");
            System.out.println("║  9. Ghi dữ liệu ra file                         ║");
            System.out.println("║  0. Thoát                                       ║");
            System.out.println("╚═════════════════════════════════════════════════╝");
            System.out.print("👉 Chọn chức năng: ");

            while (!sc.hasNextInt()) {
                System.out.print("❌ Vui lòng nhập số: ");
                sc.next();
            }
            chon = sc.nextInt();
            sc.nextLine();

            switch (chon) {
                case 1:
                    themChamCongThang();
                    break;
                case 2:
                    xoaChamCong();
                    break;
                case 3:
                    timKiemTheoMaNV();
                    break;
                case 4:
                    timKiemTheoThangNam();
                    break;
                case 5:
                    capNhatSoNgayCong();
                    break;
                case 6:
                    xuatDanhSach();
                    break;
                case 7:
                    thongKe();
                    break;
                case 8:
                    docFile();
                    break;
                case 9:
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


}