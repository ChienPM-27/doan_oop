import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    
    // Khởi tạo các danh sách
    private static DanhSachNhanSu dsNhanSu = new DanhSachNhanSu();
    private static DanhSachChucVu dsChucVu = new DanhSachChucVu();
    private static DanhSachPhong dsPhongBan = new DanhSachPhong();
    private static DanhSachDuAn dsDuAn = new DanhSachDuAn();
    private static DanhSachPhanCong dsPhanCong = new DanhSachPhanCong();
    private static DanhSachChamCongNgay dsChamCongNgay = new DanhSachChamCongNgay();
    private static DanhSachChamCongThang dsChamCongThang = new DanhSachChamCongThang();
    private static DanhSachThuongPhat dsThuongPhat = new DanhSachThuongPhat();
    private static DanhSachBangLuong dsBangLuong = new DanhSachBangLuong();
    private static DanhSachQuyDinhPhuCapChucVu dsQDPhuCapCV = new DanhSachQuyDinhPhuCapChucVu();
    private static DanhSachQuyDinhPhuCapThoiVu dsQDPhuCapTV = new DanhSachQuyDinhPhuCapThoiVu();
    private static DanhSachQuyDinhThuongLe dsQDThuongLe = new DanhSachQuyDinhThuongLe();

    public static void main(String[] args) throws IOException {
        // Đọc dữ liệu từ file khi khởi động
        System.out.println("╔═══════════════════════════════════════════════════════╗");
        System.out.println("║        ĐANG TẢI DỮ LIỆU TỪ FILE...                   ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝");
        
        dsNhanSu.docFile();
        dsChucVu.DocFile();
        dsPhongBan.docFile();
        dsDuAn.docFile();
        dsPhanCong.docFile();
        dsChamCongNgay.docFile();
        dsChamCongThang.docFile();
        dsThuongPhat.docFile();
        dsBangLuong.docFile();
        dsQDPhuCapCV.docFile();
        dsQDPhuCapTV.docFile();
        dsQDThuongLe.docFile();
        
        System.out.println("\n✅ ĐÃ TẢI XONG DỮ LIỆU!\n");
        
        // Hiển thị menu chính
        menuChinh();
    }

    public static void menuChinh() throws IOException {
        int chon;
        do {
            System.out.println("\n╔═══════════════════════════════════════════════════════╗");
            System.out.println("║          HỆ THỐNG QUẢN LÝ NHÂN SỰ TỔNG HỢP           ║");
            System.out.println("╠═══════════════════════════════════════════════════════╣");
            System.out.println("║  1.  Quản lý Nhân sự                                  ║");
            System.out.println("║  2.  Quản lý Chức vụ                                  ║");
            System.out.println("║  3.  Quản lý Phòng ban                                ║");
            System.out.println("║  4.  Quản lý Dự án                                    ║");
            System.out.println("║  5.  Quản lý Phân công                                ║");
            System.out.println("║  6.  Quản lý Chấm công ngày                           ║");
            System.out.println("║  7.  Quản lý Chấm công tháng                          ║");
            System.out.println("║  8.  Quản lý Thưởng - Phạt                            ║");
            System.out.println("║  9.  Quản lý Bảng lương                               ║");
            System.out.println("║  10. Quản lý Quy định phụ cấp chức vụ                 ║");
            System.out.println("║  11. Quản lý Quy định phụ cấp thời vụ                 ║");
            System.out.println("║  12. Quản lý Quy định thưởng lễ                       ║");
            System.out.println("║                                                       ║");
            System.out.println("║  0.  Thoát chương trình                               ║");
            System.out.println("╚═══════════════════════════════════════════════════════╝");
            System.out.print("👉 Chọn chức năng (0-12): ");
            
            while (!sc.hasNextInt()) {
                System.out.print("❌ Vui lòng nhập số: ");
                sc.next();
            }
            chon = sc.nextInt();
            sc.nextLine();
            
            switch (chon) {
                case 1:
                    dsNhanSu.menu();
                    break;
                case 2:
                    dsChucVu.Menu();
                    break;
                case 3:
                    dsPhongBan.menu();
                    break;
                case 4:
                    dsDuAn.menu();
                    break;
                case 5:
                    dsPhanCong.menu();
                    break;
                case 6:
                    dsChamCongNgay.menu();
                    break;
                case 7:
                    dsChamCongThang.menu();
                    break;
                case 8:
                    dsThuongPhat.menu();
                    break;
                case 9:
                    dsBangLuong.menu(dsNhanSu, dsChucVu, dsChamCongThang, dsThuongPhat);
                    break;
                case 10:
                    dsQDPhuCapCV.menu();
                    break;
                case 11:
                    dsQDPhuCapTV.menu();
                    break;
                case 12:
                    dsQDThuongLe.menu();
                    break;
                case 0:
                    System.out.println("\n╔═══════════════════════════════════════════════════════╗");
                    System.out.println("║           CẢM ƠN BẠN ĐÃ SỬ DỤNG HỆ THỐNG!            ║");
                    System.out.println("║              HẸN GẶP LẠI LẦN SAU! 👋                  ║");
                    System.out.println("╚═══════════════════════════════════════════════════════╝");
                    break;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ! Vui lòng chọn từ 0-12.");
            }
            
        } while (chon != 0);
        
        sc.close();
    }
}