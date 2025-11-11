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

    // Constructor sao chép
    public NhanVienThucTap(NhanVienThucTap nv) {
        super(nv); // gọi copy constructor của lớp cha
        this.thoigianthuctap = nv.thoigianthuctap;
        this.truonghoc = nv.truonghoc;
        this.nganhhoc = nv.nganhhoc;
    }

    // Trả về bản sao
    public NhanVienThucTap copy() {
        return new NhanVienThucTap(this);
    }

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
        // Tái sử dụng phần nhập chung của lớp cha
        super.nhap();

        Scanner sc = new Scanner(System.in);

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
        // Tái sử dụng phần xuất chung của lớp cha
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║         THÔNG TIN NHÂN VIÊN THỰC TẬP         ║");
        System.out.println("╠════════════════════════════════════════════════╣");
        // Xuất phần chung (gọn, 1 hàng) bằng super.xuat()
        super.xuat();
        // Xuất phần riêng
        System.out.printf("Thời gian TT : %s%n", thoigianthuctap);
        System.out.printf("Trường học   : %s%n", truonghoc);
        System.out.printf("Ngành học    : %s%n", nganhhoc);
        System.out.printf("Loại NV      : %s%n", getLoaiNV());
        System.out.println("╚════════════════════════════════════════════════╝");
    }

    @Override
    public String toString() {
        // Format: ma | Ho Ten | GT | dd/mm/yyyy | TrangThai | ThoiGianTT | Truong | Nganh | LoaiNV
        String hoten = (ho == null ? "" : ho) + (ten == null ? "" : " " + ten);
        String ngaysinh = String.format("%02d/%02d/%d", ngay, thang, nam);
        return String.format("%-10s | %-20s | %-6s | %-12s | %-12s | %-12s | %-15s | %-15s | %-10s",
                ma, hoten.trim(), gt, ngaysinh, trangthai, thoigianthuctap, truonghoc, nganhhoc, getLoaiNV());
    }
}
