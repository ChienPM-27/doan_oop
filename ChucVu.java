import java.io.Serializable;

public class ChucVu{
    private String tencv, macv;
    private double phucap;

    // Constructors
    public ChucVu() {
    }

    public ChucVu(String macv, String tencv, double phucap) {
        this.macv = macv;
        this.tencv = tencv;
        this.phucap = phucap;
    }

    // Getters and Setters
    public void setMacv(String macv){
        this.macv = macv;
    }
    public void setTencv(String tencv){
        this.tencv = tencv;
    }
    public void setPhucap(double phucap){
        this.phucap = phucap;
    }
    public String getTencv(){
        return tencv;
    }
    public String getMacv(){
        return macv;
    }
    public double getPhucap(){
        return phucap;
    }
}