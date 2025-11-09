import java.util.Scanner;

public class QuanLyNhanSu {
    private NhanSu[] dsNhanSu;
    private int soLuong;
    private static final int MAX = 1000;
    private Scanner sc = new Scanner(System.in);
    
    public QuanLyNhanSu() {
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
        
        // Kiểm tra trùng mã
        System.out.println();
        ns.nhap();
        
        if (timTheoMa(ns.getMa()) != null) {
            System.out.println("❌ Mã nhân viên đã tồn tại!");
            return;
        }
        
        dsNhanSu[soLuong++] = ns;
        System.out.println("✅ Thêm nhân sự thành công!");
    }
    
    // ===== XÓA NHÂN SỰ =====
    public void xoaNhanSu() {
        System.out.print("\nNhập mã nhân viên cần xóa: ");
        String ma = sc.nextLine().trim();
        
        for (int i = 0; i < soLuong; i++) {
            if (dsNhanSu[i].getMa().equalsIgnoreCase(ma)) {
                // Dịch chuyển mảng
                for (int j = i; j < soLuong - 1; j++) {
                    dsNhanSu[j] = dsNhanSu[j + 1];
                }
                dsNhanSu[--soLuong] = null;
                System.out.println("✅ Xóa nhân sự thành công!");
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
            System.out.println("\n✅ Tìm thấy nhân sự:");
            ns.xuat();
        } else {
            System.out.println("❌ Không tìm thấy mã nhân viên: " + ma);
        }
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
        
        boolean timThay = false;
        System.out.println("\n" + "=".repeat(120));
        
        if (loai == 1) {
            System.out.println("DANH SÁCH NHÂN VIÊN CHÍNH THỨC");
        } else {
            System.out.println("DANH SÁCH NHÂN VIÊN THỰC TẬP");
        }
        
        System.out.println("=".repeat(120));
        
        for (int i = 0; i < soLuong; i++) {
            if (dsNhanSu[i].getLoaiNhanVien() == loai) {
                dsNhanSu[i].xuat();
                System.out.println();
                timThay = true;
            }
        }
        
        if (!timThay) {
            System.out.println("❌ Không có nhân sự nào thuộc loại này!");
        }
        System.out.println("=".repeat(120));
    }
    
    // ===== HIỂN THỊ TẤT CẢ =====
    public void hienThiTatCa() {
        if (soLuong == 0) {
            System.out.println("\n❌ Danh sách nhân sự trống!");
            return;
        }
        
        System.out.println("\n" + "=".repeat(120));
        System.out.println("DANH SÁCH TẤT CẢ NHÂN SỰ");
        System.out.println("=".repeat(120));
        
        for (int i = 0; i < soLuong; i++) {
            dsNhanSu[i].xuat();
            System.out.println();
        }
        
        System.out.println("=".repeat(120));
        System.out.println("Tổng số nhân sự: " + soLuong);
    }
    
    // ===== HIỂN THI DANH SÁCH NHÂN VIÊN CHÍNH THỨC =====
    public void hienThiNVChinhThuc() {
        int dem = 0;
        System.out.println("\n" + "=".repeat(120));
        System.out.println("DANH SÁCH NHÂN VIÊN CHÍNH THỨC");
        System.out.println("=".repeat(120));
        
        for (int i = 0; i < soLuong; i++) {
            if (dsNhanSu[i].getLoaiNhanVien() == 1) {
                dsNhanSu[i].xuat();
                System.out.println();
                dem++;
            }
        }
        
        if (dem == 0) {
            System.out.println("❌ Không có nhân viên chính thức nào!");
        }
        System.out.println("=".repeat(120));
        System.out.println("Tổng số: " + dem + " nhân viên chính thức");
    }
    
    // ===== HIỂN THI DANH SÁCH NHÂN VIÊN THỰC TẬP =====
    public void hienThiNVThucTap() {
        int dem = 0;
        System.out.println("\n" + "=".repeat(120));
        System.out.println("DANH SÁCH NHÂN VIÊN THỰC TẬP");
        System.out.println("=".repeat(120));
        
        for (int i = 0; i < soLuong; i++) {
            if (dsNhanSu[i].getLoaiNhanVien() == 2) {
                dsNhanSu[i].xuat();
                System.out.println();
                dem++;
            }
        }
        
        if (dem == 0) {
            System.out.println("❌ Không có nhân viên thực tập nào!");
        }
        System.out.println("=".repeat(120));
        System.out.println("Tổng số: " + dem + " nhân viên thực tập");
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
        
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║           THỐNG KÊ NHÂN SỰ                    ║");
        System.out.println("╠════════════════════════════════════════════════╣");
        System.out.printf("║ Tổng số nhân sự           : %-17d ║%n", soLuong);
        System.out.printf("║ Nhân viên chính thức       : %-17d ║%n", nvChinhThuc);
        System.out.printf("║ Nhân viên thực tập         : %-17d ║%n", nvThucTap);
        System.out.println("╠════════════════════════════════════════════════╣");
        System.out.printf("║ Nhân viên nam              : %-17d ║%n", nam);
        System.out.printf("║ Nhân viên nữ               : %-17d ║%n", nu);
        System.out.println("╠════════════════════════════════════════════════╣");
        
        if (soLuong > 0) {
            double tuoiTB = (double) tongTuoi / soLuong;
            System.out.printf("║ Tuổi trung bình            : %-17.1f ║%n", tuoiTB);
        } else {
            System.out.printf("║ Tuổi trung bình            : %-17s ║%n", "N/A");
        }
        
        System.out.println("╚════════════════════════════════════════════════╝");
    }
    
    // ===== THỐNG KÊ THEO PHÒNG BAN (CHỈ NHÂN VIÊN CHÍNH THỨC) =====
    public void thongKeTheoPhongBan() {
        System.out.print("\nNhập tên phòng ban cần thống kê: ");
        String phongban = sc.nextLine().trim();
        
        int dem = 0;
        System.out.println("\n" + "=".repeat(120));
        System.out.println("NHÂN VIÊN CHÍNH THỨC THUỘC PHÒNG BAN: " + phongban);
        System.out.println("=".repeat(120));
        
        for (int i = 0; i < soLuong; i++) {
            if (dsNhanSu[i].getLoaiNhanVien() == 1) {
                NhanVienChinhThuc nv = (NhanVienChinhThuc) dsNhanSu[i];
                if (nv.getPhongban().equalsIgnoreCase(phongban)) {
                    nv.xuat();
                    System.out.println();
                    dem++;
                }
            }
        }
        
        if (dem == 0) {
            System.out.println("❌ Không có nhân viên nào thuộc phòng ban này!");
        }
        System.out.println("=".repeat(120));
        System.out.println("Tổng số: " + dem + " nhân viên");
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
        ns.xuat();
        
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
    }
    
    // ===== MENU CHÍNH =====
    public void menu() {
        int chon;
        do {
            System.out.println("\n╔════════════════════════════════════════════════╗");
            System.out.println("║          HỆ THỐNG QUẢN LÝ NHÂN SỰ            ║");
            System.out.println("╠════════════════════════════════════════════════╣");
            System.out.println("║  1. Thêm nhân sự                              ║");
            System.out.println("║  2. Xóa nhân sự theo mã                       ║");
            System.out.println("║  3. Tìm kiếm theo mã                          ║");
            System.out.println("║  4. Tìm kiếm theo loại (ID 1 hoặc 2)         ║");
            System.out.println("║  5. Sửa thông tin nhân sự                     ║");
            System.out.println("║  6. Hiển thị tất cả nhân sự                   ║");
            System.out.println("║  7. Hiển thị NV chính thức                    ║");
            System.out.println("║  8. Hiển thị NV thực tập                      ║");
            System.out.println("║  9. Thống kê tổng quan                        ║");
            System.out.println("║ 10. Thống kê theo phòng ban                   ║");
            System.out.println("║  0. Thoát                                     ║");
            System.out.println("╚════════════════════════════════════════════════╝");
            System.out.print("👉 Chọn chức năng: ");
            
            while (!sc.hasNextInt()) {
                System.out.print("❌ Vui lòng nhập số: ");
                sc.next();
            }
            chon = sc.nextInt();
            sc.nextLine();
            
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
                    timKiemTheoLoai();
                    break;
                case 5:
                    suaThongTin();
                    break;
                case 6:
                    hienThiTatCa();
                    break;
                case 7:
                    hienThiNVChinhThuc();
                    break;
                case 8:
                    hienThiNVThucTap();
                    break;
                case 9:
                    thongKe();
                    break;
                case 10:
                    thongKeTheoPhongBan();
                    break;
                case 0:
                    System.out.println("\n👋 Cảm ơn bạn đã sử dụng hệ thống!");
                    break;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ!");
            }
            
        } while (chon != 0);
    }
}