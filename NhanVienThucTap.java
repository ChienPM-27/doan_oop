import java.util.Scanner;

public class NhanVienThucTap extends NhanSu {
    private String thoigianthuctap;
    private String truonghoc;
    private String nganhhoc;

    // Constructor không tham số
    public NhanVienThucTap() {
        super();
        this.loaiNhanVien = 2; // Nhân viên thực tập
    }

    // Constructor có tham số
    public NhanVienThucTap(String ma, String ho, String ten, String gt, String diachi,
                           String trangthai, String macv, int ngay, int thang, int nam,
                           String thoigianthuctap, String truonghoc, String nganhhoc) {
        super(ma, ho, ten, gt, diachi, trangthai, macv, ngay, thang, nam, 2);
        this.thoigianthuctap = thoigianthuctap;
        this.truonghoc = truonghoc;
        this.nganhhoc = nganhhoc;
    }

    // Getter và Setter
    public String getThoigianthuctap() { return thoigianthuctap; }
    public void setThoigianthuctap(String thoigianthuctap) {
        this.thoigianthuctap = thoigianthuctap;
    }

    public String getTruonghoc() { return truonghoc; }
    public void setTruonghoc(String truonghoc) { this.truonghoc = truonghoc; }

    public String getNganhhoc() { return nganhhoc; }
    public void setNganhhoc(String nganhhoc) { this.nganhhoc = nganhhoc; }

    @Override
    public String getLoaiNV() {
        return "Thực tập";
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

        System.out.print("Nhập thời gian thực tập (vd: 3 tháng): ");
        thoigianthuctap = sc.nextLine().trim();

        System.out.print("Nhập trường học: ");
        truonghoc = sc.nextLine().trim();

        System.out.print("Nhập ngành học: ");
        nganhhoc = sc.nextLine().trim();

        loaiNhanVien = 2;
    }

    @Override
    public void xuat() {
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║         THÔNG TIN NHÂN VIÊN THỰC TẬP         ║");
        System.out.println("╠════════════════════════════════════════════════╣");
        System.out.printf("║ Mã NV        : %-30s ║%n", ma);
        System.out.printf("║ Họ và tên    : %-30s ║%n", ho + " " + ten);
        System.out.printf("║ Giới tính    : %-30s ║%n", gt);
        System.out.printf("║ Ngày sinh    : %02d/%02d/%-23d ║%n", ngay, thang, nam);
        System.out.printf("║ Tuổi         : %-30d ║%n", tinhTuoi());
        System.out.printf("║ Địa chỉ      : %-30s ║%n", diachi);
        System.out.printf("║ Trạng thái   : %-30s ║%n", trangthai);
        System.out.printf("║ Mã chức vụ   : %-30s ║%n", macv);
        System.out.printf("║ Thời gian TT : %-30s ║%n", thoigianthuctap);
        System.out.printf("║ Trường học   : %-30s ║%n", truonghoc);
        System.out.printf("║ Ngành học    : %-30s ║%n", nganhhoc);
        System.out.printf("║ Loại NV      : %-30s ║%n", getLoaiNV());
        System.out.println("╚════════════════════════════════════════════════╝");
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-20s | %-10s | %02d/%02d/%d | %-15s | %-20s | %-15s | %-15s",
                ma, ho + " " + ten, gt, ngay, thang, nam, thoigianthuctap, truonghoc, nganhhoc, getLoaiNV());
    }
}