
public class QuyDinhPhuCapThoiVu{
    private String maPC;
    private String loaithanhvien;
    private Double mucphucap;
    private String donvitinh;

    // Constructors
    public QuyDinhPhuCapThoiVu() {
    }

    public QuyDinhPhuCapThoiVu(String maPC, String loaithanhvien, Double mucphucap, String donvitinh) {
        this.maPC = maPC;
        this.loaithanhvien = loaithanhvien;
        this.mucphucap = mucphucap;
        this.donvitinh = donvitinh;
    }
    public QuyDinhPhuCapThoiVu(QuyDinhPhuCapThoiVu other) {
        if (other == null) return;
        this.maPC = other.maPC;
        this.loaithanhvien = other.loaithanhvien;
        this.mucphucap = other.mucphucap;
        this.donvitinh = other.donvitinh;
    }

    //ham set de lay thong tin
    public void setMaPC(String maPC) {this.maPC = maPC;}
    public void setLoaithanhvien(String loaithanhvien) {this.loaithanhvien = loaithanhvien;}
    public void setMucphucap(Double mucphucap) {this.mucphucap = mucphucap;}
    public void setDonvitinh(String donvitinh) {this.donvitinh = donvitinh;}
    //ham get
    public String getMaPC() {
        return maPC;
    }
    public String getLoaithanhvien(){
        return loaithanhvien;
    }
    public Double getMucphucap(){
        return mucphucap;
    }
    public String getDonvitinh(){
        return donvitinh;
    }
}
