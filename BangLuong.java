

public class BangLuong{
    private String maBL;
    private String manv;
    private int thang;
    private int nam;
    private double luongCoBan;
    private double phuCap;
    private double tongThuong;
    private double tongPhat;
    private double truLuong;
    private double tongLuong;

    public BangLuong() {
    }

    public BangLuong(String maBL, String maNv, int thang, int nam, double luongCoBan, double phuCap, double truLuong) {
        this.maBL = maBL;
        this.manv = maNv;
        this.thang = thang;
        this.nam = nam;
        this.luongCoBan = luongCoBan;
        this.phuCap = phuCap;
        this.truLuong = truLuong;
    }
        // ===== Constructor sao chép =====
    public BangLuong(BangLuong other) {
        if (other != null) {
            this.maBL = other.maBL;
            this.manv = other.manv;
            this.thang = other.thang;
            this.nam = other.nam;
            this.luongCoBan = other.luongCoBan;
            this.phuCap = other.phuCap;
            this.tongThuong = other.tongThuong;
            this.tongPhat = other.tongPhat;
            this.truLuong = other.truLuong;
            this.tongLuong = other.tongLuong;
        }
    }

    

    // Tính tổng lương
    public void tinhTongLuong(ThuongPhat[] dsThuongPhat, int soLuongTP) {
        tongThuong = 0;
        tongPhat = 0;

        if (dsThuongPhat == null || soLuongTP == 0) {
            tongLuong = luongCoBan + phuCap - truLuong;
            return;
        }

        for (int i = 0; i < soLuongTP; i++) {
            if (dsThuongPhat[i] != null &&
                dsThuongPhat[i].getMaNV() != null &&
                dsThuongPhat[i].getMaNV().equals(manv)) {

                String loai = dsThuongPhat[i].getLoai();
                if (loai != null) {
                    if (loai.equalsIgnoreCase("Thuong"))
                        tongThuong += dsThuongPhat[i].getSoTien();
                    else if (loai.equalsIgnoreCase("Phat"))
                        tongPhat += dsThuongPhat[i].getSoTien();
                }
            }
        }
        tongLuong = luongCoBan + phuCap + tongThuong - tongPhat - truLuong;
    }

    // In bảng lương
    public static void inBangLuong(BangLuong[] dsBangLuong, int soLuong) {
        if (soLuong == 0) {
            System.out.println("Chưa có bảng lương nào.");
            return;
        }

        System.out.println("==============================================================================================================================");
        System.out.printf("| %-10s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |%n",
                "Mã NV", "Lương cơ bản", "Phụ cấp", "Tổng thưởng", "Tổng phạt", "Trừ khác", "Tổng lương");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");

        double tongLuongCty = 0;
        for (int i = 0; i < soLuong; i++) {
            BangLuong bl = dsBangLuong[i];
            System.out.printf("| %-10s | %,15.0f | %,15.0f | %,15.0f | %,15.0f | %,15.0f | %,15.0f |%n",
                    bl.getMaNv(),
                    bl.getLuongCoBan(),
                    bl.getPhuCap(),
                    bl.getTongThuong(),
                    bl.getTongPhat(),
                    bl.getTruLuong(),
                    bl.getTongLuong());
            tongLuongCty += bl.getTongLuong();
        }

        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-15s | %-15s | %-15s | %-15s | %-15s | %,15.0f |%n",
                "", "", "", "", "", "TỔNG CỘNG:", tongLuongCty);
        System.out.println("==============================================================================================================================");
    }

    // Getters & Setters
    public String getMaBL() {
        return maBL;
    }

    public void setMaBL(String maBL) {
        this.maBL = maBL;
    }

    public String getMaNv() {
        return manv;
    }

    public void setMaNv(String manv) {
        this.manv = manv;
    }

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }

    public double getLuongCoBan() {
        return luongCoBan;
    }

    public void setLuongCoBan(double luongCoBan) {
        this.luongCoBan = luongCoBan;
    }

    public double getPhuCap() {
        return phuCap;
    }

    public void setPhuCap(double phuCap) {
        this.phuCap = phuCap;
    }

    public double getTongThuong() {
        return tongThuong;
    }

    public double getTongPhat() {
        return tongPhat;
    }

    public double getTruLuong() {
        return truLuong;
    }

    public void setTruLuong(double truLuong) {
        this.truLuong = truLuong;
    }

    public double getTongLuong() {
        return tongLuong;
    }
}
