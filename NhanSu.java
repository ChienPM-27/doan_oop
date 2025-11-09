import java.time.LocalDate;
import java.time.Period;

public abstract class NhanSu {
    protected String ho;
    protected String ten;
    protected String ma;
    protected String gt;
    protected String diachi;
    protected String trangthai;
    protected String macv;
    protected int ngay;
    protected int thang;
    protected int nam;
    protected int loaiNhanVien; // 1: Chính thức, 2: Thực tập

    public NhanSu() {
    }

    public NhanSu(String ma, String ho, String ten, String gt, String diachi,
                  String trangthai, String macv, int ngay, int thang, int nam, int loaiNhanVien) {
        this.ma = ma;
        this.ho = ho;
        this.ten = ten;
        this.gt = gt;
        this.diachi = diachi;
        this.trangthai = trangthai;
        this.macv = macv;
        this.ngay = ngay;
        this.thang = thang;
        this.nam = nam;
        this.loaiNhanVien = loaiNhanVien;
    }

    // Các phương thức setter
    public void setHo(String ho) { this.ho = ho; }
    public void setTen(String ten) { this.ten = ten; }
    public void setMa(String ma) { this.ma = ma; }
    public void setGt(String gt) { this.gt = gt; }
    public void setDiachi(String diachi) { this.diachi = diachi; }
    public void setTrangthai(String trangthai) { this.trangthai = trangthai; }
    public void setMachucvu(String macv) { this.macv = macv; }
    public void setLoaiNhanVien(int loaiNhanVien) { this.loaiNhanVien = loaiNhanVien; }

    public void setNgayThangNam(int ngay, int thang, int nam) {
        this.ngay = ngay;
        this.thang = thang;
        this.nam = nam;
    }

    // Các phương thức getter
    public String getHo() { return ho; }
    public String getTen() { return ten; }
    public String getMa() { return ma; }
    public String getGt() { return gt; }
    public String getDiachi() { return diachi; }
    public String getTrangthai() { return trangthai; }
    public String getMachucvu() { return macv; }
    public int getNgay() { return ngay; }
    public int getThang() { return thang; }
    public int getNam() { return nam; }
    public int getLoaiNhanVien() { return loaiNhanVien; }

    // Tính tuổi nhân viên
    public int tinhTuoi() {
        LocalDate ngaysinh = LocalDate.of(nam, thang, ngay);
        LocalDate ngayhientai = LocalDate.now();
        Period period = Period.between(ngaysinh, ngayhientai);
        return period.getYears();
    }

    // Phương thức trừu tượng
    public abstract void nhap();
    public abstract void xuat();
    public abstract String getLoaiNV();
}