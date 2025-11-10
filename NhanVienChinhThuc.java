import java.util.Scanner;

public class NhanVienChinhThuc extends NhanSu {
    private double luongcoban;
    private String ngayvaolam;
    private String phongban;

    public NhanVienChinhThuc() {
        super();
        this.loaiNhanVien = 1; // Nhân viên chính thức
    }

    public NhanVienChinhThuc(String ma, String ho, String ten, String gt, String diachi,
                             String trangthai, String macv, int ngay, int thang, int nam,
                             double luongcoban, String ngayvaolam, String phongban) {
        super(ma, ho, ten, gt, diachi, trangthai, macv, ngay, thang, nam, 1);
        this.luongcoban = luongcoban;
        this.ngayvaolam = ngayvaolam;
        this.phongban = phongban;
    }

    // Getter và Setter
    public double getLuongcoban() { return luongcoban; }
    public void setLuongcoban(double luongcoban) { this.luongcoban = luongcoban; }

    public String getNgayvaolam() { return ngayvaolam; }
    public void setNgayvaolam(String ngayvaolam) { this.ngayvaolam = ngayvaolam; }

    public String getPhongban() { return phongban; }
    public void setPhongban(String phongban) { this.phongban = phongban; }

    @Override
    public String getLoaiNV() {
        return "Chính thức";
    }

    @Override
    public void nhap() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập mã nhân viên: ");
        setMa(sc.nextLine().trim());

        System.out.print("Nhập họ: ");
        setHo(sc.nextLine().trim());

        System.out.print("Nhập tên: ");
        setTen(sc.nextLine().trim());

        System.out.print("Nhập giới tính: ");
        setGt(sc.nextLine().trim());

        System.out.print("Nhập địa chỉ: ");
        setDiachi(sc.nextLine().trim());

        System.out.print("Nhập ngày sinh (dd): ");
        ngay = sc.nextInt();
        System.out.print("Nhập tháng sinh (mm): ");
        thang = sc.nextInt();
        System.out.print("Nhập năm sinh (yyyy): ");
        nam = sc.nextInt();
        sc.nextLine();

        System.out.print("Nhập trạng thái làm việc: ");
        setTrangthai(sc.nextLine().trim());

        System.out.print("Nhập mã chức vụ: ");
        setMachucvu(sc.nextLine().trim());

        System.out.print("Nhập lương cơ bản: ");
        luongcoban = sc.nextDouble();
        sc.nextLine();

        System.out.print("Nhập ngày vào làm (dd/mm/yyyy): ");
        ngayvaolam = sc.nextLine().trim();

        System.out.print("Nhập phòng ban: ");
        phongban = sc.nextLine().trim();

        loaiNhanVien = 1;
    }

    @Override
    public void xuat() {
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║      THÔNG TIN NHÂN VIÊN CHÍNH THỨC          ║");
        System.out.println("╠════════════════════════════════════════════════╣");
        System.out.printf("║ Mã NV        : %-30s ║%n", ma);
        System.out.printf("║ Họ và tên    : %-30s ║%n", ho + " " + ten);
        System.out.printf("║ Giới tính    : %-30s ║%n", gt);
        System.out.printf("║ Ngày sinh    : %02d/%02d/%-23d ║%n", ngay, thang, nam);
        System.out.printf("║ Tuổi         : %-30d ║%n", tinhTuoi());
        System.out.printf("║ Địa chỉ      : %-30s ║%n", diachi);
        System.out.printf("║ Trạng thái   : %-30s ║%n", trangthai);
        System.out.printf("║ Mã chức vụ   : %-30s ║%n", macv);
        System.out.printf("║ Lương CB     : %,30.0f║%n", luongcoban);
        System.out.printf("║ Ngày vào làm : %-30s ║%n", ngayvaolam);
        System.out.printf("║ Phòng ban    : %-30s ║%n", phongban);
        System.out.printf("║ Loại NV      : %-30s ║%n", getLoaiNV());
        System.out.println("╚════════════════════════════════════════════════╝");
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-20s | %-10s | %02d/%02d/%d | %-15s | %-15s | %,15.0f | %-15s",
                ma, ho + " " + ten, gt, ngay, thang, nam, trangthai, phongban, luongcoban, getLoaiNV());
    }
}