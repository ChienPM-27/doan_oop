
public class QuyDinhThuongLe {
    private String mathuong;
    private String tenle;
    private String ngayle;
    private double mucthuong;

    // Constructors
    public QuyDinhThuongLe() {
    }

    public QuyDinhThuongLe(String mathuong, String tenle, String ngayle, double mucthuong) {
        this.mathuong = mathuong;
        this.tenle = tenle;
        this.ngayle = ngayle;
        this.mucthuong = mucthuong;
    }

    // ham set de lay thong tin

    public void setMathuong(String mathuong) {this.mathuong = mathuong;}
    public void setTenle(String tenle) {this.tenle = tenle;}
    public void setNgayle(String ngayle) {this.ngayle = ngayle;}
    public void setMucthuong(double mucthuong) {this.mucthuong = mucthuong;}
    // ham get
    public String getMathuong(){
        return mathuong;
    }
    public String getTenle(){
        return tenle;
    }
    public String getNgayle(){
        return ngayle;
    }
    public double getMucthuong(){
        return mucthuong;
    }
}