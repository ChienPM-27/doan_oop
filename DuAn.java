

import java.util.Scanner;

public class DuAn {
    private String maDA;
    private String tenDA;
    private int ngayBD;
    private int ngayKT;
    private double kinhphi;
    private double thuongDA;
    //constructor
    public DuAn(){

    }
    //constructor có tham số
    public DuAn(String maDA, String tenDA, int ngayBD, int ngayKT, double kinhPhi, double thuongDA){
        this.maDA = maDA;
        this.tenDA = tenDA;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
        this.kinhphi = kinhphi;
        this.thuongDA = thuongDA;
    }
    // Hàm nhập thông tin dự án

    public void nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma du an: ");
        maDA = sc.nextLine();
        System.out.print("Nhap ten du an: ");
        tenDA = sc.nextLine();
        System.out.print("Nhap ngay bat dau: ");
        ngayBD = sc.nextInt();
        sc.nextLine();
        System.out.print("Nhap ngay ket thuc: ");
        ngayKT = sc.nextInt();
        sc.nextLine();
        System.out.print("Nhap kinh phi: ");
        kinhphi = sc.nextDouble();
        System.out.print("Nhap thuong du an: ");
        thuongDA = sc.nextDouble();
        sc.nextLine(); // tránh trôi dòng
    }

    // ham set de lay thong tin
    public void setMaDA(String maDA) {
        this.maDA = maDA;
    }

    public void setTenDA(String tenDA) {
        this.tenDA = tenDA;
    }

    public void setNgayBD(int ngayBD) {
        this.ngayBD = ngayBD;
    }

    public void setNgayKT(int ngayKT) {
        this.ngayKT = ngayKT;
    }

    public void setKinhphi(double kinhphi) {
        this.kinhphi = kinhphi;
    }

    public void setThuongDA(double thuongDA) {
        this.thuongDA = thuongDA;
    }

    //ham get
    public String getMaDA() {
        return maDA;
    }

    public String getTenDA() {
        return tenDA;
    }

    public int getNgayBD() {
        return ngayBD;
    }

    public int getNgayKT() {
        return ngayKT;
    }

    public double getKinhphi() {
        return kinhphi;
    }

    public double getThuongDA() {
        return thuongDA;
    }

    public void xuat() {
        System.out.println("Ma du an: " + maDA);
        System.out.println("Ten du an: " + tenDA);
        System.out.println("Ngay bat dau: " + ngayBD);
        System.out.println("Ngay ket thuc: " + ngayKT);
        System.out.println("kinh phi: " + kinhphi);
        System.out.println("Thuong du an: " + thuongDA);
    }
}
