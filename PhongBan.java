import java.io.Serializable;

public class PhongBan {
    private String mapb;
    private String maql;
    private String tenpb;
    private String ngaythanhlap;
    private DuAn duan = new DuAn(); // Thêm thông tin dự án

    public PhongBan() {}

    public PhongBan(String mapb, String maql, String tenpb, String ngaythanhlap, String maduan) {
        this.mapb = mapb;
        this.maql = maql;
        this.tenpb = tenpb;
        this.ngaythanhlap = ngaythanhlap;
        duan.setMaDA(maduan);
    }

    public String getMapb() { return mapb; }
    public void setMapb(String mapb) { this.mapb = mapb; }

    public String getMaql() { return maql; }
    public void setMaql(String maql) { this.maql = maql; }

    public String getTenpb() { return tenpb; }
    public void setTenpb(String tenpb) { this.tenpb = tenpb; }

    public String getNgaythanhlap() { return ngaythanhlap; }
    public void setNgaythanhlap(String ngaythanhlap) { this.ngaythanhlap = ngaythanhlap; }

    public String getDuAn() { return duan.getMaDA(); }
    public void setDuAn(String duAn) {  duan.setMaDA(duAn); }

    @Override
    public String toString() {
        return "Phong ban: " + tenpb +
                " | Ma PB: " + mapb +
                " | Ma QL: " + maql +
                " | Ngay thanh lap: " + ngaythanhlap +
                " | Du an: " + duan.getMaDA();
    }
}
