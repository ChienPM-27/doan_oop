import java.util.Scanner;

public class NhanVienChinhThuc extends NhanSu {
    private double luongcoban;
    private String ngayvaolam;
    private String phongban;

    public NhanVienChinhThuc() {
        super();
        this.loaiNhanVien = 1;
    }
    public NhanVienChinhThuc(String ma, String ho, String ten, String gt, String diachi,
                  String trangthai, String macv, int ngay, int thang, int nam,String ngayvaolam, double luongcoban, int loaiNhanVien){
                    super(ma, ho, ten, gt, diachi, trangthai, macv, ngay, thang, nam, loaiNhanVien);

                                
    this.ngayvaolam = ngayvaolam;
    this.luongcoban = luongcoban;
                  }

    public NhanVienChinhThuc(NhanVienChinhThuc nv) {
        super(nv);
        this.luongcoban = nv.luongcoban;
        this.ngayvaolam = nv.ngayvaolam;
        this.phongban = nv.phongban;
    }
    //GET SET
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
        super.nhap(); // ✅ tái sử dụng phần nhập chung
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập lương cơ bản: ");
        luongcoban = sc.nextDouble();
        sc.nextLine();

        System.out.print("Nhập ngày vào làm (dd/mm/yyyy): ");
        ngayvaolam = sc.nextLine().trim();

        System.out.print("Nhập phòng ban: ");
        phongban = sc.nextLine().trim();
    }

    @Override
    public void xuat() {
        System.out.println("------------------------------------------------------------");
        super.xuat(); // ✅ gọi phần xuất chung
        System.out.printf("Lương cơ bản: %,15.0f | Ngày vào làm: %s | Phòng ban: %s | Loại: %s%n",
                luongcoban, ngayvaolam, phongban, getLoaiNV());
    }
}
