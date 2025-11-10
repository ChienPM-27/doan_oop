
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

public class DanhSachThuongPhat {

    private ThuongPhat[] dsThuongPhat;
    private int soLuong;
    private static final int MAX = 500;
    private Scanner sc = new Scanner(System.in);

    public DanhSachThuongPhat() {
        dsThuongPhat = new ThuongPhat[MAX];
        soLuong = 0;
    }

    // Thêm thưởng phạt
    public void themThuongPhat() {
        if (soLuong >= MAX) {
            System.out.println("Danh sách đã đầy!");
            return;
        }

        System.out.println("\n=== THÊM THƯỞNG/PHẠT ===");
        System.out.print("Mã thuong/phat: ");
        String maTP = sc.nextLine().trim();

        System.out.print("Mã nhân viên: ");
        String maNV = sc.nextLine().trim();

        System.out.print("Loại (Thuong/Phat): ");
        String loai = sc.nextLine().trim();

        System.out.print("Số tiền: ");
        while (!sc.hasNextDouble()) {
            System.out.print("Vui lòng nhập số (Số tiền): ");
            sc.next();
        }
        double soTien = sc.nextDouble();
        sc.nextLine();

        System.out.print("Lý do: ");
        String lyDo = sc.nextLine().trim();

        System.out.print("Ngày (yyyy-MM-dd): ");
        String ngayStr = sc.nextLine().trim();
        LocalDate ngay;
        try {
            ngay = LocalDate.parse(ngayStr);
        } catch (DateTimeException dte) {
            System.out.println("Định dạng ngày không hợp lệ. Sử dụng ngày hôm nay thay thế.");
            ngay = LocalDate.now();
        }

        dsThuongPhat[soLuong] = new ThuongPhat(maTP, maNV, soTien, loai, lyDo, ngay);
        soLuong++;
        System.out.println("✓ Thêm thành công!");
    }

    // Xuất danh sách
    public void xuatDanhSach() {
        if (soLuong == 0) {
            System.out.println("Danh sách rỗng!");
            return;
        }

        System.out.println("\n========== DANH SÁCH THƯỞNG PHẠT ==========");
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-8s | %-8s | %-6s | %12s | %-30s | %-12s |\n",
                "Ma TP", "Ma NV", "Loai", "So tien", "Ly do", "Ngay");
        System.out.println("---------------------------------------------------------------------------------------------------------------");

        for (int i = 0; i < soLuong; i++) {
            if (dsThuongPhat[i] != null) {
                ThuongPhat t = dsThuongPhat[i];
                String lyDo = t.getLyDo();
                if (lyDo.length() > 30) {
                    lyDo = lyDo.substring(0, 27) + "...";
                }
                System.out.printf("| %-8s | %-8s | %-6s | %12.0f | %-30s | %-12s |\n",
                        t.getMaTP(),
                        t.getMaNV(),
                        t.getLoai(),
                        t.getSoTien(),
                        lyDo,
                        t.getNgay() != null ? t.getNgay().toString() : "");
            }
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        System.out.println("Tong so: " + soLuong + " ban ghi");
    }

    // Tìm kiếm theo mã nhân viên
    public void timKiemTheoMaNV() {
        System.out.print("Nhap ma nhan vien: ");
        String maNV = sc.nextLine().trim();

        boolean timThay = false;
        System.out.println("\n=== KET QUA TIM KIEM ===");

        for (int i = 0; i < soLuong; i++) {
            if (dsThuongPhat[i] != null) {
                String ma = dsThuongPhat[i].getMaNV();
                if (ma != null && ma.equals(maNV)) {
                    dsThuongPhat[i].xuat();
                    timThay = true;
                }
            }
        }

        if (!timThay) {
            System.out.println("Khong tim thay thuong/phat cho nhan vien: " + maNV);
        }
    }

    // Tìm kiếm theo loại
    public void timKiemTheoLoai() {
        System.out.print("Nhap loai (Thuong/Phat): ");
        String loai = sc.nextLine().trim();

        boolean timThay = false;
        System.out.println("\n=== DANH SACH " + loai.toUpperCase() + " ===");

        for (int i = 0; i < soLuong; i++) {
            if (dsThuongPhat[i] != null) {
                String loaiItem = dsThuongPhat[i].getLoai();
                if (loaiItem != null && loaiItem.equalsIgnoreCase(loai)) {
                    dsThuongPhat[i].xuat();
                    timThay = true;
                }
            }
        }

        if (!timThay) {
            System.out.println("Khong tim thay!");
        }
    }

    // Thống kê theo nhân viên
    public void thongKeTheoNhanVien() {
        System.out.print("Nhap ma nhan vien: ");
        String maNV = sc.nextLine().trim();

        double tongThuong = 0;
        double tongPhat = 0;

        for (int i = 0; i < soLuong; i++) {
            if (dsThuongPhat[i] != null) {
                String ma = dsThuongPhat[i].getMaNV();
                if (ma != null && ma.equals(maNV)) {
                    String loai = dsThuongPhat[i].getLoai();
                    if (loai != null && loai.equalsIgnoreCase("Thuong")) {
                        tongThuong += dsThuongPhat[i].getSoTien();
                    } else if (loai != null && loai.equalsIgnoreCase("Phat")) {
                        tongPhat += dsThuongPhat[i].getSoTien();
                    }
                }
            }
        }

        System.out.println("\n=== THONG KE THUONG PHAT ===");
        System.out.println("Nhan vien: " + maNV);
        System.out.println("Tong thuong: " + String.format("%,.0f", tongThuong) + " VND");
        System.out.println("Tong phat: " + String.format("%,.0f", tongPhat) + " VND");
        System.out.println("Chenh lech: " + String.format("%,.0f", (tongThuong - tongPhat)) + " VND");
    }

    // Thống kê tổng
    public void thongKeTong() {
        double tongThuong = 0;
        double tongPhat = 0;
        int soThuong = 0;
        int soPhat = 0;

        for (int i = 0; i < soLuong; i++) {
            if (dsThuongPhat[i] != null) {
                String loai = dsThuongPhat[i].getLoai();
                if (loai != null && loai.equalsIgnoreCase("Thuong")) {
                    tongThuong += dsThuongPhat[i].getSoTien();
                    soThuong++;
                } else if (loai != null && loai.equalsIgnoreCase("Phat")) {
                    tongPhat += dsThuongPhat[i].getSoTien();
                    soPhat++;
                }
            }
        }

        System.out.println("\n=== THONG KE TONG ===");
        System.out.println("So luong thuong: " + soThuong + " (Tong: " + String.format("%,.0f", tongThuong) + " VND)");
        System.out.println("So luong phat: " + soPhat + " (Tong: " + String.format("%,.0f", tongPhat) + " VND)");
        System.out.println("Tong chi: " + String.format("%,.0f", (tongThuong + tongPhat)) + " VND");
    }

    // Xóa thưởng phạt
    public void xoaThuongPhat() {
        System.out.print("Nhap ma thuong/phat can xoa: ");
        String ma = sc.nextLine().trim();

        for (int i = 0; i < soLuong; i++) {
            if (dsThuongPhat[i] != null) {
                if (dsThuongPhat[i].getMaTP() != null && dsThuongPhat[i].getMaTP().equals(ma)) {
                    for (int j = i; j < soLuong - 1; j++) {
                        dsThuongPhat[j] = dsThuongPhat[j + 1];
                    }
                    dsThuongPhat[--soLuong] = null;
                    System.out.println("✓ Xoa thanh cong!");
                    return;
                }
            }
        }
        System.out.println("✗ Khong tim thay ma: " + ma);
    }

    // Đọc file DSThuongPhat.txt
    public void docFile() {
        String fileName = "DanhSachThuongPhat.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue; // bỏ dòng trống
                }
                String[] parts = line.split(",");
                if (parts.length < 6) {
                    System.out.println("⚠️ Dòng sai định dạng: " + line);
                    continue;
                }

                try {
                    String maTP = parts[0].trim();
                    String maNV = parts[1].trim();
                    double soTien = Double.parseDouble(parts[2].trim());
                    String loai = parts[3].trim();

                    // Nếu lý do có dấu phẩy, ghép các phần dư
                    String lyDo = parts[4].trim();
                    if (parts.length > 6) {
                        StringBuilder sb = new StringBuilder(lyDo);
                        for (int k = 5; k < parts.length - 1; k++) {
                            sb.append(",").append(parts[k]);
                        }
                        lyDo = sb.toString().trim();
                        // last part is date at parts[parts.length-1]
                    }

                    String ngayStr = parts[parts.length - 1].trim(); // ngày luôn ở phần cuối
                    LocalDate ngay = LocalDate.parse(ngayStr);

                    if (soLuong >= MAX) {
                        System.out.println("⚠️ Danh sách đã đầy, dừng đọc file.");
                        break;
                    }

                    dsThuongPhat[soLuong++] = new ThuongPhat(maTP, maNV, soTien, loai, lyDo, ngay);
                } catch (NumberFormatException nfe) {
                    System.out.println("⚠️ Lỗi số ở dòng: " + line + " -> " + nfe.getMessage());
                } catch (DateTimeException dte) {
                    System.out.println("⚠️ Lỗi định dạng ngày ở dòng: " + line + " -> " + dte.getMessage());
                } catch (Exception e) {
                    System.out.println("⚠️ Lỗi khi đọc dòng: " + line + " -> " + e.getMessage());
                }
            }

            System.out.println("✅ Đọc file " + fileName + " thành công (" + soLuong + " bản ghi).");

        } catch (IOException e) {
            System.out.println("❌ Lỗi đọc file: " + e.getMessage());
        }
    }

    // ===== GHI FILE =====
    // Ghi file DSThuongPhat.txt theo định dạng: maTP,maNV,soTien,loai,lyDo,ngay
    public void ghiFile() {
        String fileName = "DanhSachThuongPhat.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < soLuong; i++) {
                ThuongPhat t = dsThuongPhat[i];
                if (t == null) {
                    continue;
                }
                String safeLyDo = t.getLyDo() == null ? "" : t.getLyDo().replace(",", ";");
                String line = t.getMaTP() + ","
                        + t.getMaNV() + ","
                        + String.valueOf((long) t.getSoTien()) + ","
                        + t.getLoai() + ","
                        + safeLyDo + ","
                        + (t.getNgay() != null ? t.getNgay().toString() : "");
                bw.write(line);
                bw.newLine();
            }
            System.out.println("✅ Ghi file " + fileName + " thành công (" + soLuong + " bản ghi).");
        } catch (IOException e) {
            System.out.println("❌ Lỗi ghi file: " + e.getMessage());
        }
    }

    // Getters
    public ThuongPhat[] getDsThuongPhat() {
        return dsThuongPhat;
    }

    public int getSoLuong() {
        return soLuong;
    }

    // Menu
    // ===== MENU =====
    public void menu() {
        int chon;
        do {
            System.out.println("\n╔═══════════════════════════════════════════════╗");
            System.out.println("║            QUẢN LÝ THƯỞNG - PHẠT                ║");
            System.out.println("╠═════════════════════════════════════════════════╣");
            System.out.println("║  1. Thêm thưởng/phạt                            ║");
            System.out.println("║  2. Xóa thưởng/phạt theo mã                     ║");
            System.out.println("║  3. Tìm kiếm theo mã nhân viên                  ║");
            System.out.println("║  4. Tìm kiếm theo loại (Thuong/Phat)            ║");
            System.out.println("║  5. Thống kê theo nhân viên                     ║");
            System.out.println("║  6. Thống kê tổng                               ║");
            System.out.println("║  7. Hiển thị tất cả                             ║");
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
            sc.nextLine(); // consume newline

            switch (chon) {
                case 1:
                    themThuongPhat();
                    break;
                case 2: {
                    System.out.print("Nhập mã thưởng/phạt cần xóa: ");

                    xoaThuongPhat(); // nếu bạn không có helper, sẽ gọi xoaThuongPhat() thay thế
                    break;
                }
                case 3:
                    timKiemTheoMaNV();
                    break;
                case 4:
                    timKiemTheoLoai();
                    break;
                case 5:
                    thongKeTheoNhanVien();
                    break;
                case 6:
                    thongKeTong();
                    break;
                case 7:
                    xuatDanhSach();
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
    }}