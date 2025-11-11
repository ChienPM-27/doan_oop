import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DanhSachBangLuong {
    private BangLuong[] dsBangLuong;
    private int soLuong;
    private static final int MAX = 500;
    private Scanner sc = new Scanner(System.in);

    public DanhSachBangLuong() {
        dsBangLuong = new BangLuong[MAX];
        soLuong = 0;
    }

    // ============================================================
    //  ĐỌC FILE
    // ============================================================
    public void docFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("DanhSachBangLuong.txt.txt"))) {
            String line;
            soLuong = 0;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length < 10) {
                    System.out.println("⚠️ Dòng sai định dạng: " + line);
                    continue;
                }

                try {
                    String maBL = parts[0].trim();
                    String maNv = parts[1].trim();
                    int thang = Integer.parseInt(parts[2].trim());
                    int nam = Integer.parseInt(parts[3].trim());
                    double luongCoBan = Double.parseDouble(parts[4].trim());
                    double phuCap = Double.parseDouble(parts[5].trim());
                    double tongThuong = Double.parseDouble(parts[6].trim());
                    double tongPhat = Double.parseDouble(parts[7].trim());
                    double truLuong = Double.parseDouble(parts[8].trim());
                    double tongLuong = Double.parseDouble(parts[9].trim());

                    BangLuong bl = new BangLuong(maBL, maNv, thang, nam, luongCoBan, phuCap, truLuong);

                    // Tính lại tổng lương (mảng thưởng phạt rỗng ở đây)
                    bl.tinhTongLuong(new ThuongPhat[0], 0);

                    dsBangLuong[soLuong++] = bl;

                } catch (NumberFormatException e) {
                    System.out.println("⚠️ Lỗi định dạng số ở dòng: " + line);
                }
            }

            System.out.println("✅ Đọc file DanhSachBangLuong.txt.txt thành công (" + soLuong + " bảng lương).");

        } catch (IOException e) {
            System.out.println("⚠️ Không tìm thấy file DanhSachBangLuong.txt.txt hoặc file rỗng.");
        }
    }

    // ============================================================
    //  GHI FILE
    // ============================================================
    public void ghiFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("DanhSachBangLuong.txt.txt"))) {
            for (int i = 0; i < soLuong; i++) {
                BangLuong bl = dsBangLuong[i];
                if (bl == null) continue;

                String line = bl.getMaBL() + "," +
                        bl.getMaNv() + "," +
                        bl.getThang() + "," +
                        bl.getNam() + "," +
                        bl.getLuongCoBan() + "," +
                        bl.getPhuCap() + "," +
                        bl.getTongThuong() + "," +
                        bl.getTongPhat() + "," +
                        bl.getTruLuong() + "," +
                        bl.getTongLuong();

                bw.write(line);
                bw.newLine();
            }

            System.out.println("✅ Ghi file DanhSachBangLuong.txt.txt thành công (" + soLuong + " bảng lương).");

        } catch (IOException e) {
            System.out.println("❌ Lỗi ghi file: " + e.getMessage());
        }
    }

    // ============================================================
    //  HÀM THÊM / SỬA / XÓA CÓ THAM SỐ
    // ============================================================

    /**
     * Thêm 1 bảng lương (có tham số). Nếu dsThuongPhat != null thì sẽ tính thưởng/phạt.
     * Trả về true nếu thêm thành công.
     */
    public boolean them(BangLuong bl, DanhSachThuongPhat dsThuongPhat) {
        if (bl == null) return false;
        if (soLuong >= MAX) {
            System.out.println("❌ Danh sách bảng lương đã đầy.");
            return false;
        }
        // Kiểm tra trùng mã bảng lương (maBL)
        for (int i = 0; i < soLuong; i++) {
            if (dsBangLuong[i] != null && dsBangLuong[i].getMaBL().equalsIgnoreCase(bl.getMaBL())) {
                System.out.println("⚠️ Mã bảng lương đã tồn tại: " + bl.getMaBL());
                return false;
            }
        }
        // Tính lại tổng lương nếu có dsThuongPhat
        if (dsThuongPhat != null) {
            bl.tinhTongLuong(dsThuongPhat.getDsThuongPhat(), dsThuongPhat.getSoLuong());
        } else {
            bl.tinhTongLuong(new ThuongPhat[0], 0);
        }
        dsBangLuong[soLuong++] = bl;
        ghiFile();
        System.out.println("✅ Đã thêm bảng lương: " + bl.getMaBL());
        return true;
    }

    /**
     * Sửa bảng lương theo mã bảng lương (maBL). newBl chứa thông tin mới (các trường sẽ ghi đè).
     * Nếu dsThuongPhat != null sẽ tính lại thưởng/phạt.
     */
    public boolean sua(String maBL, BangLuong newBl, DanhSachThuongPhat dsThuongPhat) {
        if (maBL == null || newBl == null) return false;
        for (int i = 0; i < soLuong; i++) {
            if (dsBangLuong[i] != null && dsBangLuong[i].getMaBL().equalsIgnoreCase(maBL)) {
                // Cập nhật trường (giữ maBL ban đầu nếu newBl.getMaBL() rỗng)
                dsBangLuong[i].setMaBL(newBl.getMaBL() == null || newBl.getMaBL().isEmpty() ? dsBangLuong[i].getMaBL() : newBl.getMaBL());
                dsBangLuong[i].setMaNv(newBl.getMaNv() == null || newBl.getMaNv().isEmpty() ? dsBangLuong[i].getMaNv() : newBl.getMaNv());
                dsBangLuong[i].setThang(newBl.getThang() == 0 ? dsBangLuong[i].getThang() : newBl.getThang());
                dsBangLuong[i].setNam(newBl.getNam() == 0 ? dsBangLuong[i].getNam() : newBl.getNam());
                dsBangLuong[i].setLuongCoBan(newBl.getLuongCoBan() == 0 ? dsBangLuong[i].getLuongCoBan() : newBl.getLuongCoBan());
                dsBangLuong[i].setPhuCap(newBl.getPhuCap() == 0 ? dsBangLuong[i].getPhuCap() : newBl.getPhuCap());
                dsBangLuong[i].setTruLuong(newBl.getTruLuong() == 0 ? dsBangLuong[i].getTruLuong() : newBl.getTruLuong());

                // Tính lại tổng lương
                if (dsThuongPhat != null) {
                    dsBangLuong[i].tinhTongLuong(dsThuongPhat.getDsThuongPhat(), dsThuongPhat.getSoLuong());
                } else {
                    dsBangLuong[i].tinhTongLuong(new ThuongPhat[0], 0);
                }

                ghiFile();
                System.out.println("✅ Đã sửa bảng lương: " + maBL);
                return true;
            }
        }
        System.out.println("❌ Không tìm thấy mã bảng lương: " + maBL);
        return false;
    }

    /**
     * Xóa bảng lương theo mã bảng lương (maBL).
     */
    public boolean xoaByMaBL(String maBL) {
        if (maBL == null) return false;
        for (int i = 0; i < soLuong; i++) {
            if (dsBangLuong[i] != null && dsBangLuong[i].getMaBL().equalsIgnoreCase(maBL)) {
                // Dịch mảng
                for (int j = i; j < soLuong - 1; j++) {
                    dsBangLuong[j] = dsBangLuong[j + 1];
                }
                dsBangLuong[--soLuong] = null;
                ghiFile();
                System.out.println("✅ Đã xóa bảng lương: " + maBL);
                return true;
            }
        }
        System.out.println("❌ Không tìm thấy mã bảng lương: " + maBL);
        return false;
    }

    /**
     * Xóa bảng lương theo mã nhân viên + tháng + năm (có thể xóa nhiều bản cùng mã + tháng + năm).
     */
    public boolean xoaByNhanVienThang(String maNv, int thang, int nam) {
        if (maNv == null) return false;
        int j = 0;
        boolean found = false;
        for (int i = 0; i < soLuong; i++) {
            if (dsBangLuong[i] != null &&
                    dsBangLuong[i].getMaNv().equalsIgnoreCase(maNv) &&
                    dsBangLuong[i].getThang() == thang &&
                    dsBangLuong[i].getNam() == nam) {
                found = true;
                // skip (xóa)
            } else {
                dsBangLuong[j++] = dsBangLuong[i];
            }
        }
        // Null phần còn lại
        for (int k = j; k < soLuong; k++) dsBangLuong[k] = null;
        soLuong = j;
        if (found) {
            ghiFile();
            System.out.println("✅ Đã xóa bảng lương của NV " + maNv + " tháng " + thang + "/" + nam);
        } else {
            System.out.println("❌ Không tìm thấy bảng lương của NV " + maNv + " tháng " + thang + "/" + nam);
        }
        return found;
    }

    // ============================================================
    //  1. TẠO BẢNG LƯƠNG THÁNG (giữ nguyên)
    // ============================================================
    public void taoBangLuongThang(DanhSachNhanSu dsNhanSu,
                                  DanhSachChucVu dsChucVu,
                                  DanhSachChamCongThang dsChamCong,
                                  DanhSachThuongPhat dsThuongPhat) {

        System.out.println("\n=== TẠO BẢNG LƯƠNG THÁNG ===");
        System.out.print("Nhập tháng (1-12): ");
        int thang = sc.nextInt();
        System.out.print("Nhập năm: ");
        int nam = sc.nextInt();
        sc.nextLine();

        // Kiểm tra đã tạo chưa
        if (kiemTraDaTao(thang, nam)) {
            System.out.println("⚠️ Bảng lương tháng " + thang + "/" + nam + " đã tồn tại!");
            System.out.print("Bạn có muốn tạo lại? (y/n): ");
            if (!sc.nextLine().equalsIgnoreCase("y")) {
                return;
            }
            xoaBangLuongThang(thang, nam);
        }

        System.out.println("Đang tính toán...\n");
        int dem = 0;

        // Duyệt tất cả nhân viên trong danh sách
        NhanSu[] dsNS = dsNhanSu.getDsNhanSu();
        int soLuongNS = dsNhanSu.getSoLuong();

        for (int i = 0; i < soLuongNS; i++) {
            NhanSu ns = dsNS[i];
            if (ns == null || ns.getLoaiNhanVien() != 1) continue;

            NhanVienChinhThuc nv = (NhanVienChinhThuc) ns;

            // Lấy lương cơ bản
            double luongCB = nv.getLuongcoban();

            // Lấy phụ cấp
            double phuCap = layPhuCap(dsChucVu, nv.getMachucvu());

            // Tính trừ lương (nghỉ không phép)
            double truLuong = tinhTruLuong(dsChamCong, nv.getMa(), thang, nam, luongCB);

            // Tạo bảng lương
            String maBL = "BL" + thang + nam + "_" + nv.getMa();
            BangLuong bl = new BangLuong(maBL, nv.getMa(), thang, nam, luongCB, phuCap, truLuong);

            // Tính thưởng phạt
            if (dsThuongPhat != null) {
                bl.tinhTongLuong(dsThuongPhat.getDsThuongPhat(), dsThuongPhat.getSoLuong());
            } else {
                bl.tinhTongLuong(new ThuongPhat[0], 0);
            }

            // Lưu
            dsBangLuong[soLuong++] = bl;
            dem++;
            System.out.println("✓ " + nv.getMa() + " - " + String.format("%,d", (long)bl.getTongLuong()) + " VND");
        }

        System.out.println("\n✅ Đã tạo bảng lương cho " + dem + " nhân viên");

        // Tự động lưu file
        ghiFile();
    }

    // ============================================================
    //  2. XEM BẢNG LƯƠNG
    // ============================================================
    public void xemBangLuong() {
        System.out.print("\nNhập tháng: ");
        int thang = sc.nextInt();
        System.out.print("Nhập năm: ");
        int nam = sc.nextInt();
        sc.nextLine();

        BangLuong[] ds = new BangLuong[MAX];
        int dem = 0;

        for (int i = 0; i < soLuong; i++) {
            if (dsBangLuong[i].getThang() == thang && dsBangLuong[i].getNam() == nam) {
                ds[dem++] = dsBangLuong[i];
            }
        }

        if (dem == 0) {
            System.out.println("❌ Chưa có bảng lương tháng " + thang + "/" + nam);
            return;
        }

        System.out.println("\n=== BẢNG LƯƠNG THÁNG " + thang + "/" + nam + " ===");
        BangLuong.inBangLuong(ds, dem);
    }

    // ============================================================
    //  3. SỬA BẢNG LƯƠNG (tương tác, vẫn giữ)
    // ============================================================
    public void suaBangLuong(DanhSachThuongPhat dsThuongPhat) {
        System.out.print("\nNhập mã nhân viên: ");
        String maNV = sc.nextLine().trim();
        System.out.print("Nhập tháng: ");
        int thang = sc.nextInt();
        System.out.print("Nhập năm: ");
        int nam = sc.nextInt();
        sc.nextLine();

        // Tìm bảng lương
        BangLuong bl = null;
        for (int i = 0; i < soLuong; i++) {
            if (dsBangLuong[i].getMaNv().equalsIgnoreCase(maNV)
                    && dsBangLuong[i].getThang() == thang
                    && dsBangLuong[i].getNam() == nam) {
                bl = dsBangLuong[i];
                break;
            }
        }

        if (bl == null) {
            System.out.println("❌ Không tìm thấy bảng lương!");
            return;
        }

        // Hiển thị thông tin hiện tại
        System.out.println("\n--- THÔNG TIN HIỆN TẠI ---");
        System.out.println("Lương cơ bản: " + String.format("%,d", (long)bl.getLuongCoBan()));
        System.out.println("Phụ cấp: " + String.format("%,d", (long)bl.getPhuCap()));
        System.out.println("Trừ lương: " + String.format("%,d", (long)bl.getTruLuong()));
        System.out.println("Tổng lương: " + String.format("%,d", (long)bl.getTongLuong()));

        // Sửa
        System.out.println("\n--- NHẬP THÔNG TIN MỚI ---");
        System.out.print("Lương cơ bản mới (0 = giữ nguyên): ");
        double luongCB = sc.nextDouble();
        if (luongCB > 0) bl.setLuongCoBan(luongCB);

        System.out.print("Phụ cấp mới (0 = giữ nguyên): ");
        double phuCap = sc.nextDouble();
        if (phuCap > 0) bl.setPhuCap(phuCap);

        System.out.print("Trừ lương mới (-1 = giữ nguyên): ");
        double truLuong = sc.nextDouble();
        if (truLuong >= 0) bl.setTruLuong(truLuong);

        sc.nextLine();

        // Tính lại tổng lương
        if (dsThuongPhat != null) {
            bl.tinhTongLuong(dsThuongPhat.getDsThuongPhat(), dsThuongPhat.getSoLuong());
        } else {
            bl.tinhTongLuong(new ThuongPhat[0], 0);
        }

        System.out.println("✅ Đã cập nhật và tính lại tổng lương!");

        // Tự động lưu file
        ghiFile();
    }

    // ============================================================
    //  4. XÓA BẢNG LƯƠNG (tương tác, vẫn giữ)
    // ============================================================
    public void xoaBangLuong() {
        System.out.println("\n=== XÓA BẢNG LƯƠNG ===");
        System.out.println("1. Xóa bảng lương 1 nhân viên");
        System.out.println("2. Xóa toàn bộ bảng lương 1 tháng");
        System.out.print("Chọn: ");
        int chon = sc.nextInt();
        sc.nextLine();

        if (chon == 1) {
            xoaBangLuongNhanVien();
        } else if (chon == 2) {
            System.out.print("Nhập tháng: ");
            int thang = sc.nextInt();
            System.out.print("Nhập năm: ");
            int nam = sc.nextInt();
            sc.nextLine();
            xoaBangLuongThang(thang, nam);
            System.out.println("✅ Đã xóa bảng lương tháng " + thang + "/" + nam);
        }

        // Tự động lưu file
        ghiFile();
    }

    private void xoaBangLuongNhanVien() {
        System.out.print("Nhập mã nhân viên: ");
        String maNV = sc.nextLine().trim();
        System.out.print("Nhập tháng: ");
        int thang = sc.nextInt();
        System.out.print("Nhập năm: ");
        int nam = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < soLuong; i++) {
            if (dsBangLuong[i].getMaNv().equalsIgnoreCase(maNV)
                    && dsBangLuong[i].getThang() == thang
                    && dsBangLuong[i].getNam() == nam) {

                // Dịch mảng
                for (int j = i; j < soLuong - 1; j++) {
                    dsBangLuong[j] = dsBangLuong[j + 1];
                }
                dsBangLuong[--soLuong] = null;
                System.out.println("✅ Đã xóa bảng lương!");
                return;
            }
        }
        System.out.println("❌ Không tìm thấy!");
    }

    // ============================================================
    //  5. TÌM KIẾM BẢNG LƯƠNG
    // ============================================================
    public void timKiemBangLuong() {
        System.out.println("\n=== TÌM KIẾM BẢNG LƯƠNG ===");
        System.out.println("1. Tìm theo mã nhân viên");
        System.out.println("2. Tìm theo tháng/năm");
        System.out.println("3. Tìm theo khoảng lương");
        System.out.print("Chọn: ");
        int chon = sc.nextInt();
        sc.nextLine();

        switch (chon) {
            case 1:
                timTheoMaNV();
                break;
            case 2:
                xemBangLuong();
                break;
            case 3:
                timTheoKhoangLuong();
                break;
            default:
                System.out.println("❌ Lựa chọn không hợp lệ!");
        }
    }

    private void timTheoMaNV() {
        System.out.print("Nhập mã nhân viên: ");
        String maNV = sc.nextLine().trim();

        System.out.println("\n=== LỊCH SỬ LƯƠNG: " + maNV + " ===");
        boolean timThay = false;

        for (int i = 0; i < soLuong; i++) {
            if (dsBangLuong[i].getMaNv().equalsIgnoreCase(maNV)) {
                BangLuong bl = dsBangLuong[i];
                System.out.printf("Tháng %02d/%d - Lương: %,d VND%n",
                        bl.getThang(), bl.getNam(), (long)bl.getTongLuong());
                timThay = true;
            }
        }

        if (!timThay) {
            System.out.println("❌ Không tìm thấy!");
        }
    }

    private void timTheoKhoangLuong() {
        System.out.print("Nhập tháng: ");
        int thang = sc.nextInt();
        System.out.print("Nhập năm: ");
        int nam = sc.nextInt();
        System.out.print("Lương tối thiểu: ");
        double min = sc.nextDouble();
        System.out.print("Lương tối đa: ");
        double max = sc.nextDouble();
        sc.nextLine();

        System.out.println("\n=== KẾT QUẢ TÌM KIẾM ===");
        boolean timThay = false;

        for (int i = 0; i < soLuong; i++) {
            BangLuong bl = dsBangLuong[i];
            if (bl.getThang() == thang && bl.getNam() == nam
                    && bl.getTongLuong() >= min && bl.getTongLuong() <= max) {
                System.out.printf("%-10s - Lương: %,15d VND%n",
                        bl.getMaNv(), (long)bl.getTongLuong());
                timThay = true;
            }
        }

        if (!timThay) {
            System.out.println("❌ Không tìm thấy!");
        }
    }

    // ============================================================
    //  6. THỐNG KÊ BẢNG LƯƠNG
    // ============================================================
    public void thongKeBangLuong() {
        System.out.println("\n=== THỐNG KÊ BẢNG LƯƠNG ===");
        System.out.println("1. Thống kê theo tháng");
        System.out.println("2. So sánh 2 tháng");
        System.out.println("3. Top lương cao/thấp");
        System.out.print("Chọn: ");
        int chon = sc.nextInt();
        sc.nextLine();

        switch (chon) {
            case 1:
                thongKeTheoThang();
                break;
            case 2:
                soSanh2Thang();
                break;
            case 3:
                topLuong();
                break;
            default:
                System.out.println("❌ Lựa chọn không hợp lệ!");
        }
    }

    private void thongKeTheoThang() {
        System.out.print("Nhập tháng: ");
        int thang = sc.nextInt();
        System.out.print("Nhập năm: ");
        int nam = sc.nextInt();
        sc.nextLine();

        double tong = 0;
        int dem = 0;

        for (int i = 0; i < soLuong; i++) {
            if (dsBangLuong[i].getThang() == thang && dsBangLuong[i].getNam() == nam) {
                tong += dsBangLuong[i].getTongLuong();
                dem++;
            }
        }

        if (dem == 0) {
            System.out.println("❌ Chưa có dữ liệu!");
            return;
        }

        System.out.println("\n=== THỐNG KÊ THÁNG " + thang + "/" + nam + " ===");
        System.out.println("Số nhân viên: " + dem);
        System.out.println("Tổng lương: " + String.format("%,d", (long)tong) + " VND");
        System.out.println("Lương TB: " + String.format("%,d", (long)(tong/dem)) + " VND");
    }

    private void soSanh2Thang() {
        System.out.print("Tháng 1: ");
        int t1 = sc.nextInt();
        System.out.print("Năm 1: ");
        int n1 = sc.nextInt();
        System.out.print("Tháng 2: ");
        int t2 = sc.nextInt();
        System.out.print("Năm 2: ");
        int n2 = sc.nextInt();
        sc.nextLine();

        double tong1 = 0, tong2 = 0;
        int dem1 = 0, dem2 = 0;

        for (int i = 0; i < soLuong; i++) {
            if (dsBangLuong[i].getThang() == t1 && dsBangLuong[i].getNam() == n1) {
                tong1 += dsBangLuong[i].getTongLuong();
                dem1++;
            }
            if (dsBangLuong[i].getThang() == t2 && dsBangLuong[i].getNam() == n2) {
                tong2 += dsBangLuong[i].getTongLuong();
                dem2++;
            }
        }

        System.out.println("\n=== SO SÁNH ===");
        System.out.println("Tháng " + t1 + "/" + n1 + ":");
        System.out.println("  - Số NV: " + dem1);
        System.out.println("  - Tổng: " + String.format("%,d", (long)tong1) + " VND");

        System.out.println("\nTháng " + t2 + "/" + n2 + ":");
        System.out.println("  - Số NV: " + dem2);
        System.out.println("  - Tổng: " + String.format("%,d", (long)tong2) + " VND");

        double chenhLech = tong2 - tong1;
        System.out.println("\nChênh lệch: " + String.format("%,d", (long)chenhLech) + " VND");
    }

    private void topLuong() {
        System.out.print("Nhập tháng: ");
        int thang = sc.nextInt();
        System.out.print("Nhập năm: ");
        int nam = sc.nextInt();
        sc.nextLine();

        BangLuong[] ds = new BangLuong[MAX];
        int dem = 0;

        for (int i = 0; i < soLuong; i++) {
            if (dsBangLuong[i].getThang() == thang && dsBangLuong[i].getNam() == nam) {
                ds[dem++] = dsBangLuong[i];
            }
        }

        if (dem == 0) {
            System.out.println("❌ Chưa có dữ liệu!");
            return;
        }

        // Sắp xếp giảm dần
        for (int i = 0; i < dem - 1; i++) {
            for (int j = i + 1; j < dem; j++) {
                if (ds[i].getTongLuong() < ds[j].getTongLuong()) {
                    BangLuong temp = ds[i];
                    ds[i] = ds[j];
                    ds[j] = temp;
                }
            }
        }

        System.out.println("\n=== TOP 5 LƯƠNG CAO ===");
        int top = Math.min(5, dem);
        for (int i = 0; i < top; i++) {
            System.out.printf("%d. %-10s - %,15d VND%n",
                    i+1, ds[i].getMaNv(), (long)ds[i].getTongLuong());
        }

        System.out.println("\n=== TOP 5 LƯƠNG THẤP ===");
        int start = Math.max(0, dem - 5);
        for (int i = dem - 1; i >= start; i--) {
            System.out.printf("%d. %-10s - %,15d VND%n",
                    dem-i, ds[i].getMaNv(), (long)ds[i].getTongLuong());
        }
    }

    // ============================================================
    //  HÀM HỖ TRỢ
    // ============================================================

    private double layPhuCap(DanhSachChucVu dsChucVu, String maCV) {
        if (dsChucVu == null || maCV == null) return 0;

        ChucVu[] ds = dsChucVu.getDs();
        int count = dsChucVu.getCount();

        for (int i = 0; i < count; i++) {
            if (ds[i] != null && ds[i].getMacv().equalsIgnoreCase(maCV)) {
                return ds[i].getPhucap();
            }
        }
        return 0;
    }

    private double tinhTruLuong(DanhSachChamCongThang dsChamCong,
                                String maNV, int thang, int nam, double luongCB) {
        if (dsChamCong == null) return 0; // Không trừ nếu không có dữ liệu chấm công

        Chamcongthang[] ds = dsChamCong.getChamcong();
        int count = dsChamCong.getSoluong();

        for (int i = 0; i < count; i++) {
            if (ds[i] != null
                    && ds[i].getManv().equalsIgnoreCase(maNV)
                    && ds[i].getThang() == thang
                    && ds[i].getNam() == nam) {

                int soNgayNghi = ds[i].tinhSoNgayNghi();
                return (luongCB / 26) * soNgayNghi;
            }
        }
        return 0; // Không tìm thấy = đi làm đủ ngày
    }

    private boolean kiemTraDaTao(int thang, int nam) {
        for (int i = 0; i < soLuong; i++) {
            if (dsBangLuong[i].getThang() == thang && dsBangLuong[i].getNam() == nam) {
                return true;
            }
        }
        return false;
    }

    private void xoaBangLuongThang(int thang, int nam) {
        int j = 0;
        for (int i = 0; i < soLuong; i++) {
            if (dsBangLuong[i].getThang() != thang || dsBangLuong[i].getNam() != nam) {
                dsBangLuong[j++] = dsBangLuong[i];
            }
        }
        // clear rest
        for (int k = j; k < soLuong; k++) dsBangLuong[k] = null;
        soLuong = j;
    }

    // ============================================================
    //  MENU CHÍNH (cập nhật — thêm lựa chọn gọi hàm có tham số)
    // ============================================================
    public void menu(DanhSachNhanSu dsNhanSu,
                     DanhSachChucVu dsChucVu,
                     DanhSachChamCongThang dsChamCong,
                     DanhSachThuongPhat dsThuongPhat) {
        int chon;
        do {
            System.out.println("\n╔════════════════════════════════════════════════════╗");
            System.out.println("║              QUẢN LÝ BẢNG LƯƠNG (CẬP NHẬT)         ║");
            System.out.println("╠════════════════════════════════════════════════════╣");
            System.out.println("║  1. Tạo bảng lương tháng                            ║");
            System.out.println("║  2. Xem bảng lương                                  ║");
            System.out.println("║  3. Sửa bảng lương (tương tác)                      ║");
            System.out.println("║  4. Xóa bảng lương (tương tác)                      ║");
            System.out.println("║  5. Tìm kiếm bảng lương                             ║");
            System.out.println("║  6. Thống kê bảng lương                             ║");
            System.out.println("║  7. Đọc file DSBangLuong.txt                        ║");
            System.out.println("║  8. Ghi file DSBangLuong.txt                        ║");
            System.out.println("║  9. Thêm bảng lương (tham số)                       ║");
            System.out.println("║ 10. Sửa bảng lương theo mã (tham số)                ║");
            System.out.println("║ 11. Xóa bảng lương theo mã (tham số)                ║");
            System.out.println("║ 12. Xóa bảng lương theo NV + tháng + năm (tham số)  ║");
            System.out.println("║  0. Thoát                                           ║");
            System.out.println("╚════════════════════════════════════════════════════╝");
            System.out.print("Chọn: ");

            while (!sc.hasNextInt()) {
                System.out.print("❌ Vui lòng nhập số: ");
                sc.next();
            }
            chon = sc.nextInt();
            sc.nextLine();

            switch (chon) {
                case 1:
                    taoBangLuongThang(dsNhanSu, dsChucVu, dsChamCong, dsThuongPhat);
                    break;
                case 2:
                    xemBangLuong();
                    break;
                case 3:
                    suaBangLuong(dsThuongPhat);
                    break;
                case 4:
                    xoaBangLuong();
                    break;
                case 5:
                    timKiemBangLuong();
                    break;
                case 6:
                    thongKeBangLuong();
                    break;
                case 7:
                    docFile();
                    break;
                case 8:
                    ghiFile();
                    break;
                case 9: {
                    // Thêm bảng lương bằng tham số (nhập từ bàn phím rồi gọi them(...))
                    System.out.print("Nhập mã bảng lương: ");
                    String maBL = sc.nextLine().trim();
                    System.out.print("Nhập mã nhân viên: ");
                    String maNV = sc.nextLine().trim();
                    System.out.print("Nhập tháng: ");
                    int th = sc.nextInt();
                    System.out.print("Nhập năm: ");
                    int na = sc.nextInt();
                    System.out.print("Nhập lương cơ bản: ");
                    double lcb = sc.nextDouble();
                    System.out.print("Nhập phụ cấp: ");
                    double pc = sc.nextDouble();
                    System.out.print("Nhập trừ lương: ");
                    double tru = sc.nextDouble();
                    sc.nextLine();

                    BangLuong blNew = new BangLuong(maBL, maNV, th, na, lcb, pc, tru);
                    them(blNew, dsThuongPhat);
                    break;
                }
                case 10: {
                    // Sửa theo mã bảng lương
                    System.out.print("Nhập mã bảng lương cần sửa: ");
                    String maBL = sc.nextLine().trim();
                    System.out.println("Nhập thông tin mới (để trống / nhập 0 để giữ nguyên):");
                    System.out.print("Mã bảng lương mới: ");
                    String maBLmoi = sc.nextLine().trim();
                    System.out.print("Mã NV mới: ");
                    String maNVmoi = sc.nextLine().trim();
                    System.out.print("Tháng mới (0 = giữ): ");
                    int thMoi = Integer.parseInt(sc.nextLine().trim().isEmpty() ? "0" : sc.nextLine().trim());
                    // Note: to avoid double read issues, read carefully
                    // Simpler approach: read each field with scanner methods:
                    // but to keep flow, we'll ask sequentially:
                    // We'll re-prompt in simpler way:
                    System.out.print("Tháng mới (0 = giữ): ");
                    thMoi = sc.nextInt();
                    System.out.print("Năm mới (0 = giữ): ");
                    int namMoi = sc.nextInt();
                    System.out.print("Lương cơ bản mới (0 = giữ): ");
                    double lcbMoi = sc.nextDouble();
                    System.out.print("Phụ cấp mới (0 = giữ): ");
                    double pcMoi = sc.nextDouble();
                    System.out.print("Trừ lương mới (0 = giữ): ");
                    double truMoi = sc.nextDouble();
                    sc.nextLine();

                    BangLuong newBl = new BangLuong(
                            maBLmoi.isEmpty() ? maBL : maBLmoi, // nếu ko nhập mã mới thì giữ mã cũ
                            maNVmoi.isEmpty() ? "" : maNVmoi,
                            thMoi,
                            namMoi,
                            lcbMoi,
                            pcMoi,
                            truMoi
                    );
                    // Nếu newBl chứa empty maNV (""), trong hàm sua sẽ giữ nguyên
                    sua(maBL, newBl, dsThuongPhat);
                    break;
                }
                case 11: {
                    // Xóa theo mã bảng lương
                    System.out.print("Nhập mã bảng lương cần xóa: ");
                    String maBL = sc.nextLine().trim();
                    System.out.print("Bạn có chắc muốn xóa không? (y/n): ");
                    String conf = sc.nextLine().trim();
                    if (conf.equalsIgnoreCase("y") || conf.equalsIgnoreCase("yes")) {
                        xoaByMaBL(maBL);
                    } else {
                        System.out.println("Hủy xóa.");
                    }
                    break;
                }
                case 12: {
                    // Xóa theo NV + tháng + năm
                    System.out.print("Nhập mã nhân viên: ");
                    String maNV = sc.nextLine().trim();
                    System.out.print("Nhập tháng: ");
                    int th = sc.nextInt();
                    System.out.print("Nhập năm: ");
                    int na = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Bạn có chắc muốn xóa không? (y/n): ");
                    String conf2 = sc.nextLine().trim();
                    if (conf2.equalsIgnoreCase("y") || conf2.equalsIgnoreCase("yes")) {
                        xoaByNhanVienThang(maNV, th, na);
                    } else {
                        System.out.println("Hủy xóa.");
                    }
                    break;
                }
                case 0:
                    System.out.println("👋 Thoát quản lý bảng lương!");
                    break;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        } while (chon != 0);
    }

    // Getters (cần thiết để truy cập từ bên ngoài)
    public BangLuong[] getDsBangLuong() {
        return dsBangLuong;
    }

    public int getSoLuong() {
        return soLuong;
    }
}
