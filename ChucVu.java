import java.util.Scanner;

public class ChucVu {
    Scanner sc = new Scanner(System.in);
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

    public void Nhap() {
        System.out.print("Moi Nhap Ma Chuc Vu :");
        macv = sc.nextLine();
        System.out.print("Moi Nhap Ten Chuc Vu: ");
        tencv = sc.nextLine();
        System.out.print("Moi Nhap Phu Cap : ");
        phucap = sc.nextInt();
        sc.nextLine();
        ;

    }



    // Getters and Setters
    public void setMacv(String macv) {
        this.macv = macv;
    }

    public void setTencv(String tencv) {
        this.tencv = tencv;
    }

    public void setPhucap(double phucap) {
        this.phucap = phucap;
    }

    public String getTencv() {
        return tencv;
    }

    public String getMacv() {
        return macv;
    }

    public double getPhucap() {
        return phucap;
    }

    public void Xuat() {
        System.out.printf("%-10s %-20s %.2f%n", macv, tencv, phucap);
    }
}