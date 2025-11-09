import java.io.Serializable;
import java.time.LocalDate;

public class ThuongPhat {
    private String maTP;
    private NhanSu nhanSu;
    private double soTien;
    private String loai; // "Thuong" hoac "Phat"
    private String lyDo;
    private LocalDate ngay;

    public ThuongPhat() {
        this.nhanSu = new NhanSu();
    }

    public ThuongPhat(String maTP, String maNS, double soTien, String loai, String lyDo, LocalDate ngay) {
        this.maTP = maTP;
        this.nhanSu = new NhanSu();
        this.nhanSu.setMa(maNS);
        this.soTien = soTien;
        this.loai = loai;
        this.lyDo = lyDo;
        this.ngay = ngay;
    }

    // Getters
    public String getMaTP() {
        return maTP;
    }

    public NhanSu getNhanSu() {
        return nhanSu;
    }

    public String getMaNhanSu() {
        return nhanSu != null ? nhanSu.getMa() : null;
    }

    public double getSoTien() {
        return soTien;
    }

    public String getLoai() {
        return loai;
    }

    public String getLyDo() {
        return lyDo;
    }

    public LocalDate getNgay() {
        return ngay;
    }

    // Setters
    public void setMaTP(String maTP) {
        this.maTP = maTP;
    }

    public void setNhanSu(NhanSu nhanSu) {
        this.nhanSu = nhanSu;
    }

    public void setMaNhanSu(String maNS) {
        if (this.nhanSu == null) {
            this.nhanSu = new NhanSu();
        }
        this.nhanSu.setMa(maNS);
    }

    public void setSoTien(double soTien) {
        this.soTien = soTien;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }

    public void setNgay(LocalDate ngay) {
        this.ngay = ngay;
    }

    // Xuat thong tin
    public void xuat() {
        System.out.println("==============================");
        System.out.println("Mã: " + maTP);
        System.out.println("Mã nhân sự: " + (nhanSu != null ? nhanSu.getMa() : "N/A"));
        System.out.println("Loại: " + loai);
        System.out.println("Số tiền: " + String.format("%,.0f", soTien) + " VND");
        System.out.println("Lý do: " + lyDo);
        System.out.println("Ngày: " + (ngay != null ? ngay : "N/A"));
        System.out.println("==============================");
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-10s | %-10s | %,15.0f | %-20s | %s",
                maTP,
                nhanSu != null ? nhanSu.getMa() : "N/A",
                loai,
                soTien,
                lyDo != null && lyDo.length() > 20 ? lyDo.substring(0, 17) + "..." : lyDo,
                ngay != null ? ngay : "N/A");
    }
}