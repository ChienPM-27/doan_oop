import java.io.Serializable;
import java.time.LocalDate;

public class HopDong implements Serializable {
    private static final long serialVersionUID = 1L;
    private String maHD;
    private String manv;
    private String loaiHD;
    private LocalDate ngayBD;
    private LocalDate ngayKT;
    private String trangThai;
    private double luongCoBan;

    public HopDong() {
    }

    public HopDong(String maHD, String manv, String loaiHD, LocalDate ngayBD, LocalDate ngayKT, String trangThai, double luongCoBan) {
        this.maHD = maHD;
        this.manv = manv;
        this.loaiHD = loaiHD;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
        this.trangThai = trangThai;
        this.luongCoBan = luongCoBan;
    }

    // Getters & Setters
    public String getMaHD() { return maHD; }
    public void setMaHD(String maHD) { this.maHD = maHD; }

    public String getManv() { return manv; }
    public void setManv(String manv) { this.manv = manv; }

    public String getLoaiHD() { return loaiHD; }
    public void setLoaiHD(String loaiHD) { this.loaiHD = loaiHD; }

    public LocalDate getNgayBD() { return ngayBD; }
    public void setNgayBD(LocalDate ngayBD) { this.ngayBD = ngayBD; }

    public LocalDate getNgayKT() { return ngayKT; }
    public void setNgayKT(LocalDate ngayKT) { this.ngayKT = ngayKT; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    public double getLuongCoBan() { return luongCoBan; }
    public void setLuongCoBan(double luongCoBan) { this.luongCoBan = luongCoBan; }
}
