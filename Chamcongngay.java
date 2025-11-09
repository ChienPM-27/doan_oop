import java.io.Serializable;

public class Chamcongngay {
    private String mangaycong;
    private int ngay, thang, nam;
    private String mans;

    // CONSTRUCTOR
    public Chamcongngay() {

    }

    public Chamcongngay(String mangaycong, int ngay, int thang, int nam, String manv) {
        this.mangaycong = mangaycong;
        this.ngay = ngay;
        this.thang = thang;
        this.nam = nam;
        this.mans = manv;
    }

    // Setters
    public void setMangaycong(String mangaycong) {
        this.mangaycong = mangaycong;
    }

    public void setNgaychamcong(int ngay, int thang, int nam) {
        this.ngay = ngay;
        this.thang = thang;
        this.nam = nam;
    }

    public void setMans(String manv) {
        this.mans = manv;
    }

    // Getters
    public String getMangaycong() {
        return mangaycong;
    }

    public int getNgay() {
        return ngay;
    }

    public int getThang() {
        return thang;
    }

    public int getNam() {
        return nam;
    }

    public String getMans() {
        return mans;
    }

    // In bang cham cong ngay
}