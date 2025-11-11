
public class QuyDinhPhuCapChucVu {
    private String maCV;
    private String tenCV;
    private Double phucap;
    private String ghichu;

    // Constructors
    public QuyDinhPhuCapChucVu() {
    }

    public QuyDinhPhuCapChucVu(String maCV, String tenCV, Double phucap, String ghichu) {
        this.maCV = maCV;
        this.tenCV = tenCV;
        this.phucap = phucap;
        this.ghichu = ghichu;
    }
    public QuyDinhPhuCapChucVu(QuyDinhPhuCapChucVu other) {
        if (other == null) return;
        this.maCV = other.maCV;
        this.tenCV = other.tenCV;
        this.phucap = other.phucap;
        this.ghichu = other.ghichu;
    }

    // ham set de lay thong tin
    public void setMaCV(String maCV) {this.maCV=maCV;}
    public void setTenCV(String tenCV) {this.tenCV=tenCV;}
    public void setPhucap(double phucap) {this.phucap=phucap;}
    public void setGhichu(String ghichu) {this.ghichu=ghichu;}
    //ham get

    public String getMaCV() {
        return maCV;
    }
    public String getTenCV(){
        return tenCV;
    }
    public Double getPhucap() {
        return phucap;
    }
    public String getGhichu() {
        return ghichu;
    }
}