import java.io.Serializable;

public class Chamcongthang{
    private String macongthang;
    private int thang, nam, songaycong;
    private String manv;

    // HAM CONSTRUCTOR KHONG THAM SO
    public Chamcongthang(){

    }

    public Chamcongthang(String macongthang, int thang, int nam, int songaycong, String manv) {
        this.macongthang = macongthang;
        this.thang = thang;
        this.nam = nam;
        this.songaycong = songaycong;
        this.manv = manv;
    }

    // Setters
    public void setMacongthang(String macongthang) {
        this.macongthang = macongthang;
    }

    public void setThang(int thang ){
        this.thang = thang;
    }
    public void setNam(int Nam ){
        this.nam = nam;
    }

    public void setSongaycong(int songaycong) {
        this.songaycong = songaycong;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    // Getters
    public String getMacongthang() {
        return macongthang;
    }

    public int getThang() {
        return thang;
    }

    public int getNam() {
        return nam;
    }

    public int getSongaycong() {
        return songaycong;
    }

    public String getManv(){ return manv;}

    // Tinh so ngay nghi
    public int tinhSoNgayNghi() {
        // Gia su 1 thang co 26 ngay lam viec chuan
        int ngayLamViecChuan = 26;
        return ngayLamViecChuan - songaycong;
    }

    // Xuat thong tin
    public void xuat() {
        System.out.println("==============================");
        System.out.println("Mã công tháng: " + macongthang);
        System.out.println("Mã nhân sự: " + manv +  "N/A");
        System.out.println("Tháng/Năm: " + thang + "/" + nam);
        System.out.println("Số ngày công: " + songaycong);
        System.out.println("Số ngày nghỉ: " + tinhSoNgayNghi());
        System.out.println("==============================");
    }

    @Override
    public String toString() {
        return String.format("%-15s | %-10s | %02d/%04d | %3d ngày | %3d nghỉ",
                macongthang,
                manv, "N/A",
                thang, nam,
                songaycong,
                tinhSoNgayNghi());
    }
}