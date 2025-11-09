import java.io.Serializable;

public class PhanCong implements Serializable{
    private static final long serialVersionUID = 1L;
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