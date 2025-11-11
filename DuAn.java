import java.util.Scanner;

public class DuAn {
    private String maDA;
    private String tenDA;
    private String ngayBD;
    private String ngayKT;
    private double kinhphi;
    private double thuongDA;

    // constructor không tham số
    public DuAn() {
    }

    // constructor có tham số (sửa lỗi gán kinhPhi)
    public DuAn(String maDA, String tenDA, String ngayBD, String ngayKT, double kinhPhi, double thuongDA) {
        this.maDA = maDA;
        this.tenDA = tenDA;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
        this.kinhphi = kinhPhi;
        this.thuongDA = thuongDA;
    }
    public DuAn(DuAn other) {
        if (other == null) return;
        this.maDA = other.maDA;
        this.tenDA = other.tenDA;
        this.ngayBD = other.ngayBD;
        this.ngayKT = other.ngayKT;
        this.kinhphi = other.kinhphi;
        this.thuongDA = other.thuongDA;
    }


    // Hàm nhập thông tin dự án (đã sửa Scanner)
    public void nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma du an: ");
        maDA = sc.nextLine().trim();

        System.out.print("Nhap ten du an: ");
        tenDA = sc.nextLine().trim();

        System.out.print("Nhap ngay bat dau: ");
        ngayBD = sc.nextLine().trim();

        System.out.print("Nhap ngay ket thuc: ");
        ngayKT = sc.nextLine().trim();

        // đọc kinhphi an toàn
        while (true) {
            System.out.print("Nhap kinh phi: ");
            String line = sc.nextLine().trim();
            try {
                kinhphi = Double.parseDouble(line);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Gia tri khong hop le. Vui long nhap so thuc.");
            }
        }

        // đọc thuongDA an toàn
        while (true) {
            System.out.print("Nhap thuong du an: ");
            String line = sc.nextLine().trim();
            try {
                thuongDA = Double.parseDouble(line);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Gia tri khong hop le. Vui long nhap so thuc.");
            }
        }
    }

    // Setters
    public void setMaDA(String maDA) {
        this.maDA = maDA;
    }

    public void setTenDA(String tenDA) {
        this.tenDA = tenDA;
    }

    public void setNgayBD(String ngayBD) {
        this.ngayBD = ngayBD;
    }

    public void setNgayKT(String ngayKT) {
        this.ngayKT = ngayKT;
    }

    public void setKinhphi(double kinhphi) {
        this.kinhphi = kinhphi;
    }

    public void setThuongDA(double thuongDA) {
        this.thuongDA = thuongDA;
    }

    // Getters
    public String getMaDA() {
        return maDA;
    }

    public String getTenDA() {
        return tenDA;
    }

    public String getNgayBD() {
        return ngayBD;
    }

    public String getNgayKT() {
        return ngayKT;
    }

    public double getKinhphi() {
        return kinhphi;
    }

    public double getThuongDA() {
        return thuongDA;
    }

    // Hiển thị
    public void xuat() {
        System.out.println("Ma du an: " + maDA);
        System.out.println("Ten du an: " + tenDA);
System.out.println("Ngay bat dau: " + ngayBD);
        System.out.println("Ngay ket thuc: " + ngayKT);
        System.out.println("Kinh phi: " + String.format("%,.0f", kinhphi));
        System.out.println("Thuong du an: " + String.format("%,.0f", thuongDA));
    }

    @Override
    public String toString() {
        return maDA + "," + tenDA + "," + ngayBD + "," + ngayKT + "," + kinhphi + "," + thuongDA;
    }
}