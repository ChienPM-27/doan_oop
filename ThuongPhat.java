
import java.time.LocalDate;

public class ThuongPhat  {
    private String maTP;
    private String maNV;  
    private double soTien;
    private String loai;  // "Thuong" hoặc "Phat"
    private String lyDo;
    private LocalDate ngay;

    public ThuongPhat() {
    }

    public ThuongPhat(String maTP, String maNV, double soTien, String loai, String lyDo, LocalDate ngay) {
        this.maTP = maTP;
        this.maNV = maNV;
        this.soTien = soTien;
        this.loai = loai;
        this.lyDo = lyDo;
        this.ngay = ngay;
    }

    // Getters
    public String getMaTP() {
        return maTP;
    }

    public String getMaNV() {
        return maNV;
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

    public void setMaNV(String maNV) {
        this.maNV = maNV;
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

    // Xuất thông tin
    public void xuat() {
        System.out.println("==============================");
        System.out.println("Mã thưởng/phạt: " + maTP);
        System.out.println("Mã nhân viên: " + (maNV != null ? maNV : "N/A"));
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
                maNV != null ? maNV : "N/A",
                loai,
                soTien,
                lyDo != null && lyDo.length() > 20 ? lyDo.substring(0, 17) + "..." : lyDo,
                ngay != null ? ngay : "N/A");
    }
}
