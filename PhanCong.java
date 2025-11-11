

public class PhanCong {

    private String maNV;
    private String maDA;
    private String vaitro;
    private Double tienthuongDA;

    // Constructors
    public PhanCong() {
    }

    public PhanCong(String maNV, String maDA, String vaitro, Double tienthuongDA) {
        this.maNV = maNV;
        this.maDA = maDA;
        this.vaitro = vaitro;
        this.tienthuongDA = tienthuongDA;
    }
     public PhanCong(PhanCong other) {
        if (other == null) return;
        this.maNV = other.maNV;
        this.maDA = other.maDA;
        this.vaitro = other.vaitro;
        this.tienthuongDA = other.tienthuongDA;
    }

    // ham set de lay thong tin
    public void setMaNV(String maNV) {this.maNV=maNV;}
    public void setMaDA(String maDA) {this.maDA=maDA;}
    public void setVaitro(String vaitro) {this.vaitro=vaitro;}
    public void setTienthuongDA(double tienthuongDA) {this.tienthuongDA=tienthuongDA;}
    //ham get
    public String getMaNV(){
        return maNV;
    }
    public String getMaDA(){
        return maDA;
    }
    public String getVaitro(){
        return vaitro;
    }
    public double getTienthuongDA(){
        return tienthuongDA;
    }
}