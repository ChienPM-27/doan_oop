import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class DanhSachNhanSu {
    private NhanSu[] dsNhanSu;
    private int soLuong;
    private static final int MAX = 1000;
    private Scanner sc = new Scanner(System.in);
    
    public DanhSachNhanSu() {
        dsNhanSu = new NhanSu[MAX]; 
        soLuong = 0;
    }
    
    // ===== THÊM NHÂN SỰ =====
    public void themNhanSu() {
        if (soLuong >= MAX) {
            System.out.println("❌ Danh sách đã đầy!");
            return;
        }
        
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║         THÊM NHÂN SỰ MỚI             ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║  1. Nhân viên chính thức             ║");
        System.out.println("║  2. Nhân viên thực tập               ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.print("👉 Chọn loại nhân viên: ");
        
        int loai = sc.nextInt();
        sc.nextLine();
        
        NhanSu ns = null;
        
        switch (loai) {
            case 1:
                ns = new NhanVienChinhThuc();
                break;
            case 2:
                ns = new NhanVienThucTap();
                break;
            default:
                System.out.println("❌ Lựa chọn không hợp lệ!");
                return;
        }
        
        System.out.println();
        ns.nhap();
        
        if (timTheoMa(ns.getMa()) != null) {
            System.out.println("❌ Mã nhân viên đã tồn tại!");
            return;
        }
        
        dsNhanSu[soLuong++] = ns;
        System.out.println("✅ Thêm nhân sự thành công!");
        ghiFile();
    }
    
    // ===== THÊM NHÂN SỰ (CÓ THAM SỐ)  =====
    public boolean themNhanSu(NhanSu ns) {
        if (soLuong >= MAX) {
            System.out.println("❌ Danh sách đã đầy!");
            return false;
        }
        
        if (ns == null) {
            System.out.println("❌ Đối tượng nhân sự không hợp lệ!");
            return false;
        }
        
        // Kiểm tra trùng mã
        if (timTheoMa(ns.getMa()) != null) {
            System.out.println("❌ Mã nhân viên " + ns.getMa() + " đã tồn tại!");
            return false;
        }
        
        // Thêm vào cuối danh sách
        dsNhanSu[soLuong++] = ns;
        System.out.println("✅ Thêm nhân sự " + ns.getMa() + " thành công!");
        return true;
    }
    
    // ===== GHI FILE =====
    public void ghiFile() {
        try (PrintWriter pw = new PrintWriter(
                new BufferedWriter(
                    new OutputStreamWriter(
                        new FileOutputStream("DanhSachNhanSu.txt"), StandardCharsets.UTF_8)))) {

            for (int i = 0; i < soLuong; i++) {
                NhanSu ns = dsNhanSu[i];
                if (ns == null) continue;

                StringBuilder sb = new StringBuilder();
                sb.append(ns.getLoaiNhanVien()).append(",")
                  .append(ns.getMa()).append(",")
                  .append(ns.getHo()).append(",")
                  .append(ns.getTen()).append(",")
                  .append(ns.getGt()).append(",")
                  .append(ns.getDiachi()).append(",")
                  .append(ns.getTrangthai()).append(",")
                  .append(ns.getMachucvu()).append(",")
                  .append(ns.getNgay()).append(",")
                  .append(ns.getThang()).append(",")
                  .append(ns.getNam());

                if (ns.getLoaiNhanVien() == 1) {
                    NhanVienChinhThuc nv = (NhanVienChinhThuc) ns;
                    sb.append(",").append(nv.getLuongcoban())
                      .append(",").append(nv.getNgayvaolam())
                      .append(",").append(nv.getPhongban());
                } else {
                    NhanVienThucTap tt = (NhanVienThucTap) ns;
                    sb.append(",").append(tt.getThoigianthuctap())
                      .append(",").append(tt.getTruonghoc())
                      .append(",").append(tt.getNganhhoc());
                }

                pw.println(sb.toString());
            }

            pw.flush();
            System.out.println("✅ Ghi file DanhSachNhanSu.txt thành công (" + soLuong + " nhân sự).");

        } catch (IOException e) {
            System.out.println("❌ Lỗi ghi file: " + e.getMessage());
        }
    }
    
    // ===== XÓA NHÂN SỰ =====
    public void xoaNhanSu() {
        System.out.print("\nNhập mã nhân viên cần xóa: ");
        String ma = sc.nextLine().trim();
        
        for (int i = 0; i < soLuong; i++) {
            if (dsNhanSu[i].getMa().equalsIgnoreCase(ma)) {
                for (int j = i; j < soLuong - 1; j++) {
                    dsNhanSu[j] = dsNhanSu[j + 1];
                }
                dsNhanSu[--soLuong] = null;
                System.out.println("✅ Xóa nhân sự thành công!");
                ghiFile();
                return;
            }
        }
        System.out.println("❌ Không tìm thấy mã nhân viên: " + ma);
    }
    
    // ===== TÌM KIẾM THEO MÃ =====
    public NhanSu timTheoMa(String ma) {
        for (int i = 0; i < soLuong; i++) {
            if (dsNhanSu[i].getMa().equalsIgnoreCase(ma)) {
                return dsNhanSu[i];
            }
        }
        return null;
    }
    
    public void timKiemTheoMa() {
        System.out.print("\nNhập mã nhân viên cần tìm: ");
        String ma = sc.nextLine().trim();
        
        NhanSu ns = timTheoMa(ma);
        if (ns != null) {
            System.out.println("\n✅ Tìm thấy nhân sú:");
            hienThiDanhSachDang1Cot(new NhanSu[]{ns}, 1);
        } else {
            System.out.println("❌ Không tìm thấy mã nhân viên: " + ma);
        }
    }
    
    // ===== TÌM KIẾM THEO TÊN (KHÔNG THAM SỐ) =====
    public void timKiemTheoTen() {
        System.out.print("\nNhập tên nhân viên cần tìm: ");
        String ten = sc.nextLine().trim().toLowerCase();
        
        NhanSu[] ketQua = new NhanSu[MAX];
        int dem = 0;
        
        for (int i = 0; i < soLuong; i++) {
            if (dsNhanSu[i].getTen().toLowerCase().contains(ten)) {
                ketQua[dem++] = dsNhanSu[i];
            }
        }
        
        if (dem == 0) {
            System.out.println("❌ Không tìm thấy nhân viên có tên: " + ten);
        } else {
            System.out.println("\n✅ Tìm thấy " + dem + " nhân viên:");
            hienThiDanhSachDang1Cot(ketQua, dem);
        }
    }
    
    // ===== TÌM KIẾM THEO TÊN (CÓ THAM SỐ) =====
    public NhanSu[] timKiemTheoTen(String ten) {
        if (ten == null || ten.trim().isEmpty()) {
            return new NhanSu[0];
        }
        
        NhanSu[] ketQua = new NhanSu[MAX];
        int dem = 0;
        
        String tenTimKiem = ten.trim().toLowerCase();
        for (int i = 0; i < soLuong; i++) {
            if (dsNhanSu[i].getTen().toLowerCase().contains(tenTimKiem)) {
                ketQua[dem++] = dsNhanSu[i];
            }
        }
        
        // Tạo mảng đúng kích thước
        NhanSu[] result = new NhanSu[dem];
        System.arraycopy(ketQua, 0, result, 0, dem);
        
        return result;
    }
    
    // ===== TÌM KIẾM THEO LOẠI (ID 1 hoặc 2) =====
    public void timKiemTheoLoai() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║       TÌM KIẾM THEO LOẠI NHÂN SỰ      ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║  1. Nhân viên chính thức             ║");
        System.out.println("║  2. Nhân viên thực tập               ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.print("👉 Chọn loại: ");
        
        int loai = sc.nextInt();
        sc.nextLine();
        
        if (loai != 1 && loai != 2) {
            System.out.println("❌ Lựa chọn không hợp lệ!");
            return;
        }
        
        NhanSu[] ketQua = new NhanSu[MAX];
        int dem = 0;
        
        for (int i = 0; i < soLuong; i++) {
            if (dsNhanSu[i].getLoaiNhanVien() == loai) {
                ketQua[dem++] = dsNhanSu[i];
            }
        }
        
        if (dem == 0) {
            System.out.println("❌ Không có nhân sự nào thuộc loại này!");
        } else {
            if (loai == 1) {
                System.out.println("\n=== DANH SÁCH NHÂN VIÊN CHÍNH THỨC ===");
            } else {
                System.out.println("\n=== DANH SÁCH NHÂN VIÊN THỰC TẬP ===");
            }
            hienThiDanhSachDang1Cot(ketQua, dem);
        }
    }
    
    // ===== HIỂN THỊ DANH SÁCH DẠNG CỘT (MỚI) =====
    private void hienThiDanhSachDang1Cot(NhanSu[] ds, int count) {
        if (count == 0) {
            System.out.println("❌ Danh sách trống!");
            return;
        }
        
        System.out.println("\n" + "=".repeat(165));
        System.out.printf("%-8s | %-15s | %-15s | %-8s | %-12s | %-20s | %-12s | %-10s | %-25s%n",
                "MÃ NV", "HỌ", "TÊN", "GIỚI TÍNH", "NGÀY SINH", "ĐỊA CHỈ", "TRẠNG THÁI", "MÃ CHỨC VỤ", "LOẠI NHÂN VIÊN");
        System.out.println("-".repeat(165));
        
        for (int i = 0; i < count; i++) {
            if (ds[i] == null) continue;
            
            NhanSu ns = ds[i];
            System.out.printf("%-8s | %-15s | %-15s | %-8s | %02d/%02d/%04d   | %-20s | %-12s | %-10s | %-25s%n",
                    ns.getMa(),
                    chuanHoaChuoi(ns.getHo(), 15),
                    chuanHoaChuoi(ns.getTen(), 15),
                    ns.getGt(),
                    ns.getNgay(),
                    ns.getThang(),
                    ns.getNam(),
                    chuanHoaChuoi(ns.getDiachi(), 20),
                    chuanHoaChuoi(ns.getTrangthai(), 12),
                    ns.getMachucvu(),
                    ns.getLoaiNV());
        }
        
        System.out.println("=".repeat(165));
        System.out.println("Tổng số: " + count + " nhân sự");
    }
    
    // ===== CHUẨN HÓA CHUỖI (HỖ TRỢ) =====
    private String chuanHoaChuoi(String str, int maxLen) {
        if (str == null) return "";
        if (str.length() > maxLen) {
            return str.substring(0, maxLen - 3) + "...";
        }
        return str;
    }
    
    // ===== HIỂN THỊ TẤT CẢ =====
    public void hienThiTatCa() {
        if (soLuong == 0) {
            System.out.println("\n❌ Danh sách nhân sự trống!");
            return;
        }
        
        System.out.println("\n=== DANH SÁCH TẤT CẢ NHÂN SỰ ===");
        hienThiDanhSachDang1Cot(dsNhanSu, soLuong);
    }
    
    // ===== HIỂN THỊ DANH SÁCH NHÂN VIÊN CHÍNH THỨC =====
    public void hienThiNVChinhThuc() {
        NhanSu[] ketQua = new NhanSu[MAX];
        int dem = 0;
        
        for (int i = 0; i < soLuong; i++) {
            if (dsNhanSu[i].getLoaiNhanVien() == 1) {
                ketQua[dem++] = dsNhanSu[i];
            }
        }
        
        if (dem == 0) {
            System.out.println("❌ Không có nhân viên chính thức nào!");
        } else {
            System.out.println("\n=== DANH SÁCH NHÂN VIÊN CHÍNH THỨC ===");
            hienThiDanhSachDang1Cot(ketQua, dem);
        }
    }
    
    // ===== HIỂN THỊ DANH SÁCH NHÂN VIÊN THỰC TẬP =====
    public void hienThiNVThucTap() {
        NhanSu[] ketQua = new NhanSu[MAX];
        int dem = 0;
        
        for (int i = 0; i < soLuong; i++) {
            if (dsNhanSu[i].getLoaiNhanVien() == 2) {
                ketQua[dem++] = dsNhanSu[i];
            }
        }
        
        if (dem == 0) {
            System.out.println("❌ Không có nhân viên thực tập nào!");
        } else {
            System.out.println("\n=== DANH SÁCH NHÂN VIÊN THỰC TẬP ===");
            hienThiDanhSachDang1Cot(ketQua, dem);
        }
    }
    
    // ===== THỐNG KÊ =====
    public void thongKe() {
        int nvChinhThuc = 0;
        int nvThucTap = 0;
        int nam = 0, nu = 0;
        int tongTuoi = 0;
        
        for (int i = 0; i < soLuong; i++) {
            if (dsNhanSu[i].getLoaiNhanVien() == 1) {
                nvChinhThuc++;
            } else {
                nvThucTap++;
            }
            
            if (dsNhanSu[i].getGt().equalsIgnoreCase("Nam")) {
                nam++;
            } else {
                nu++;
            }
            
            tongTuoi += dsNhanSu[i].tinhTuoi();
        }
        
        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║           THỐNG KÊ NHÂN SỰ                     ║");
        System.out.println("╠════════════════════════════════════════════════╣");
        System.out.printf(" ║ Tổng số nhân sự           : %-17d  ║%n", soLuong);
        System.out.printf(" ║ Nhân viên chính thức       : %-17d ║%n", nvChinhThuc);
        System.out.printf(" ║ Nhân viên thực tập         : %-17d ║%n", nvThucTap);
        System.out.println("╠════════════════════════════════════════════════╣");
        System.out.printf(" ║ Nhân viên nam              : %-17d ║%n", nam);
        System.out.printf(" ║ Nhân viên nữ               : %-17d ║%n", nu);
        System.out.println("╠════════════════════════════════════════════════╣");
        
        if (soLuong > 0) {
            double tuoiTB = (double) tongTuoi / soLuong;
            System.out.printf("║ Tuổi trung bình       : %-17.1f ║%n", tuoiTB);
        } else {
            System.out.printf("║ Tuổi trung bình         : %-17s ║%n", "N/A");
        }
        
        System.out.println("╚════════════════════════════════════════════════╝");
    }
    
    // ===== THỐNG KÊ THEO PHÒNG BAN =====
    public void thongKeTheoPhongBan() {
        System.out.print("\nNhập tên phòng ban cần thống kê: ");
        String phongban = sc.nextLine().trim();
        
        NhanSu[] ketQua = new NhanSu[MAX];
        int dem = 0;
        
        for (int i = 0; i < soLuong; i++) {
            if (dsNhanSu[i].getLoaiNhanVien() == 1) {
                NhanVienChinhThuc nv = (NhanVienChinhThuc) dsNhanSu[i];
                if (nv.getPhongban().equalsIgnoreCase(phongban)) {
                    ketQua[dem++] = nv;
                }
            }
        }
        
        if (dem == 0) {
            System.out.println("❌ Không có nhân viên nào thuộc phòng ban này!");
        } else {
            System.out.println("\n=== NHÂN VIÊN CHÍNH THỨC THUỘC PHÒNG BAN: " + phongban + " ===");
            hienThiDanhSachDang1Cot(ketQua, dem);
        }
    }
    
    // ===== SỬA THÔNG TIN NHÂN SỰ =====
    public void suaThongTin() {
        System.out.print("\nNhập mã nhân viên cần sửa: ");
        String ma = sc.nextLine().trim();
        
        NhanSu ns = timTheoMa(ma);
        if (ns == null) {
            System.out.println("❌ Không tìm thấy mã nhân viên: " + ma);
            return;
        }
        
        System.out.println("\n✅ Tìm thấy nhân sự:");
        hienThiDanhSachDang1Cot(new NhanSu[]{ns}, 1);
        
        System.out.println("\n--- SỬA THÔNG TIN ---");
        System.out.print("Địa chỉ mới (Enter để bỏ qua): ");
        String diachi = sc.nextLine().trim();
        if (!diachi.isEmpty()) {
            ns.setDiachi(diachi);
        }
        
        System.out.print("Trạng thái mới (Enter để bỏ qua): ");
        String trangthai = sc.nextLine().trim();
        if (!trangthai.isEmpty()) {
            ns.setTrangthai(trangthai);
        }
        
        System.out.print("Mã chức vụ mới (Enter để bỏ qua): ");
        String macv = sc.nextLine().trim();
        if (!macv.isEmpty()) {
            ns.setMachucvu(macv);
        }
        
        // Sửa thông tin riêng theo loại
        if (ns.getLoaiNhanVien() == 1) {
            NhanVienChinhThuc nv = (NhanVienChinhThuc) ns;
            System.out.print("Lương cơ bản mới (0 để bỏ qua): ");
            double luong = sc.nextDouble();
            sc.nextLine();
            if (luong > 0) {
                nv.setLuongcoban(luong);
            }
            
            System.out.print("Phòng ban mới (Enter để bỏ qua): ");
            String pb = sc.nextLine().trim();
            if (!pb.isEmpty()) {
                nv.setPhongban(pb);
            }
        } else {
            NhanVienThucTap nv = (NhanVienThucTap) ns;
            System.out.print("Thời gian thực tập mới (Enter để bỏ qua): ");
            String tg = sc.nextLine().trim();
            if (!tg.isEmpty()) {
                nv.setThoigianthuctap(tg);
            }
        }
        
        System.out.println("✅ Cập nhật thông tin thành công!");
        ghiFile();
    }
    
    // ===== ĐỌC FILE =====
    public void docFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("DanhSachNhanSu.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length < 11) {
                    System.out.println("⚠️ Dòng sai định dạng: " + line);
                    continue;
                }

                int loai = Integer.parseInt(parts[0]);
                String ma = parts[1];
                String ho = parts[2];
                String ten = parts[3];
                String gt = parts[4];
                String diachi = parts[5];
                String trangthai = parts[6];
                String macv = parts[7];
                int ngay = Integer.parseInt(parts[8]);
                int thang = Integer.parseInt(parts[9]);
                int nam = Integer.parseInt(parts[10]);

                NhanSu ns;
                
                if (loai == 1) {
                    if (parts.length < 14) {
                        System.out.println("⚠️ Thiếu thông tin NVCT: " + line);
                        continue;
                    }
                    double luongcoban = Double.parseDouble(parts[11]);
                    String ngayvaolam = parts[12];
                    String phongban = parts[13];
                    
                    ns = new NhanVienChinhThuc(ma, ho, ten, gt, diachi, trangthai, macv,
                                            ngay, thang, nam, luongcoban, ngayvaolam, phongban);
                } else {
                    if (parts.length < 14) {
                        System.out.println("⚠️ Thiếu thông tin NVTT: " + line);
                        continue;
                    }
                    String thoigiantt = parts[11];
                    String truonghoc = parts[12];
                    String nganhhoc = parts[13];
                    
                    ns = new NhanVienThucTap(ma, ho, ten, gt, diachi, trangthai, macv,
                                            ngay, thang, nam, thoigiantt, truonghoc, nganhhoc);
                }

                dsNhanSu[soLuong++] = ns;
            }

            System.out.println("✅ Đọc file DanhSachNhanSu.txt thành công (" + soLuong + " nhân sự).");

        } catch (IOException e) {
            System.out.println("❌ Lỗi đọc file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("❌ Lỗi dữ liệu số: " + e.getMessage());
        }
    }
    
   // ===== MENU CHÍNH (CẬP NHẬT) =====
public void menu() {
    while (true) {
        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║          HỆ THỐNG QUẢN LÝ NHÂN SỰ            ║");
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.println("║  1. Thêm nhân sự                             ║");
        System.out.println("║  2. Xóa nhân sự theo mã                      ║");
        System.out.println("║  3. Tìm kiếm theo mã                         ║");
        System.out.println("║  4. Tìm kiếm theo tên                        ║");
        System.out.println("║  5. Tìm kiếm theo loại (ID 1 hoặc 2)         ║");
        System.out.println("║  6. Sửa thông tin nhân sự                    ║");
        System.out.println("║  7. Hiển thị tất cả nhân sự                  ║");
        System.out.println("║  8. Hiển thị NV chính thức                   ║");
        System.out.println("║  9. Hiển thị NV thực tập                     ║");
        System.out.println("║ 10. Thống kê tổng quan                       ║");
        System.out.println("║ 11. Thống kê theo phòng ban                  ║");
        System.out.println("║ 12. Đọc dữ liệu từ file                      ║");
        System.out.println("║ 13. Ghi dữ liệu ra file                      ║");
        System.out.println("║  0. Thoát                                    ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.print("👉 Chọn chức năng: ");

        String line = sc.nextLine().trim();
        int chon;
        try {
            chon = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            System.out.println("❌ Vui lòng nhập một số hợp lệ (0-13).");
            continue;
        }

        switch (chon) {
            case 1:
                themNhanSu();
                break;
            case 2:
                xoaNhanSu();
                break;
            case 3:
                timKiemTheoMa();
                break;
            case 4:
                timKiemTheoTen();
                break;
            case 5:
                timKiemTheoLoai();
                break;
            case 6:
                suaThongTin();
                break;
            case 7:
                hienThiTatCa();
                break;
            case 8:
                hienThiNVChinhThuc();
                break;
            case 9:
                hienThiNVThucTap();
                break;
            case 10:
                thongKe();
                break;
            case 11:
                thongKeTheoPhongBan();
                break;
            case 12:
                docFile();
                break;
            case 13:
                ghiFile();
                break;
            case 0:
                System.out.print("Bạn có chắc muốn thoát? (y/n): ");
                String confirm = sc.nextLine().trim();
                if (confirm.equalsIgnoreCase("y")) {
                    System.out.println("Tạm biệt! 👋");
                    return; // hoặc System.exit(0);
                } else {
                    System.out.println("Đã hủy thoát, quay lại menu.");
                }
                break;
            default:
                System.out.println("❌ Lựa chọn không hợp lệ. Vui lòng chọn 0-13.");
                break;
            }
        }
    }
    public NhanSu[] getDsNhanSu(){
        return dsNhanSu;
    }
    public int getSoLuong(){
        return soLuong;
    }
}