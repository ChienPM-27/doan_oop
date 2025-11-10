import java.util.Scanner;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;

public class DanhSachDuAn{

    private DuAn[] duan;
    private int count;
    Scanner sc = new Scanner(System.in);
    private static final int MAX = 100;

    public DanhSachDuAn() {
        duan = new DuAn[MAX];
        count = 0;
    }

    // Nhap danh sach du an

    // Xuat danh sach
    public void xuatDanhSach() {
        if (count == 0) {
            System.out.println("Danh sach rong");
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.println("\nDu an thu " + (i + 1) + ":");
            duan[i].xuat();
        }
    }
    // Thêm 1 dự án mới vào danh sách

    public void themDuAn() {
        if (count >= MAX) {
            System.out.println("Danh sach da day, khong the them du an moi!");
            return;
        }

        Scanner sc = new Scanner(System.in);
        DuAn da = new DuAn();

        System.out.println("\n=== Nhap thong tin du an moi ===");
        System.out.print("Ma du an: ");
        da.setMaDA(sc.nextLine());
        System.out.print("Ten du an: ");
        da.setTenDA(sc.nextLine());
        System.out.print("Ngay bat dau: ");
        da.setNgayBD(sc.nextLine());
        System.out.print("Ngay ket thuc: ");
        da.setNgayKT(sc.nextLine());
        System.out.print("Kinh phi: ");
        da.setKinhphi(sc.nextDouble());
        System.out.print("Thuong du an: ");
        da.setThuongDA(sc.nextDouble());

        duan[count++] = da;
        System.out.println("Them du an moi thanh cong!");
    }

    //tim kiem du an theo ma
    public void timKiem(String ma) {
        for (int i = 0; i < count; i++) {
            if (duan[i].getMaDA().equalsIgnoreCase(ma)) {
                System.out.println("\nTim thay du an:");
                duan[i].xuat();
                return;
            }
        }
        System.out.println("Khong tim thay");
    }

    // xoa du an theo ma
    public void xoa(String ma) {
        for (int i = 0; i < count; i++) {
            if (duan[i].getMaDA().equalsIgnoreCase(ma)) {
                for (int j = i; j < count - 1; j++) {
                    duan[j] = duan[j + 1];
                }
                count--;
                System.out.println("Da xoa du an");
                return;
            }
        }
        System.out.println("Khong ton tai du an de xoa");
    }

    //HAM SUA THONG TIN DU AN
    public void suaThongTin(String ma) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < count; i++) {
            if (duan[i].getMaDA().equalsIgnoreCase(ma)) {
                System.out.println("\n--- Sua thong tin du an ---");
System.out.print("Ten du an moi: ");
                duan[i].setTenDA(sc.nextLine());
                System.out.print("Ngay bat dau moi: ");
                duan[i].setNgayBD(sc.nextLine());
                System.out.print("Ngay ket thuc moi: ");
                duan[i].setNgayKT(sc.nextLine());
                System.out.print("Kinh phi moi: ");
                duan[i].setKinhphi(sc.nextDouble());
                System.out.print("Thuong du an moi: ");
                duan[i].setThuongDA(sc.nextDouble());
                System.out.println("Da cap nhat thong tin du an thanh cong!");
                return;
            }
        }
        System.out.println("Khong tim thay du an can sua!");
    }

    // Thong ke tong kinh phi
    public void thongKeKinhPhi() {
        double tong = 0;
        for (int i = 0; i < count; i++) {
            tong += duan[i].getKinhphi();
        }
        System.out.println("\nTong kinh phi tat ca du an: " + tong);
    }

    // Thong ke so du an co thuong
    public void thongKeDuAnThuong() {
        int dem = 0;
        for (int i = 0; i < count; i++) {
            if (duan[i].getThuongDA() > 0) {
                dem++;
            }
        }
        System.out.println("\nSo du an co thuong: " + dem);
    }
    // ===== ĐỌC FILE =====
    public void docFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("DanhSachDuAn.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length < 6) continue;

                String maDA = parts[0];
                String tenDA = parts[1];
                String ngayBD = parts[2];
                String ngayKT = parts[3];
                double kinhPhi = Double.parseDouble(parts[4]);
                double thuongDA = Double.parseDouble(parts[5]);

                if (count >= MAX) {
                    System.out.println("⚠️ Danh sách đã đầy, dừng đọc file!");
                    break;
                }

                duan[count++] = new DuAn(maDA, tenDA, ngayBD, ngayKT, kinhPhi, thuongDA);
            }
            System.out.println("✅ Đọc file DanhSachDuAn.txt thành công (" + count + " dự án).");
        } catch (IOException e) {
            System.out.println("❌ Lỗi đọc file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Lỗi dữ liệu số: " + e.getMessage());
        }
    }

    // ===== GHI FILE =====
    public void ghiFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("DanhSachDuAn.txt"))) {
            for (int i = 0; i < count; i++) {
DuAn da = duan[i];
                bw.write(da.getMaDA() + "," + da.getTenDA() + "," + da.getNgayBD() + "," +
                        da.getNgayKT() + "," + da.getKinhphi() + "," + da.getThuongDA());
                bw.newLine();
            }
            System.out.println("✅ Ghi file DanhSachDuAn.txt thành công (" + count + " dự án).");
        } catch (IOException e) {
            System.out.println("❌ Lỗi ghi file: " + e.getMessage());
        }
    }
    // ===== MENU =====
    public void menu() {
        int chon;
        do {
            System.out.println("\n╔════════════════════════════════════════════════╗");
            System.out.println("║              QUẢN LÝ DANH SÁCH DỰ ÁN             ║");
            System.out.println("╠══════════════════════════════════════════════════╣");
            System.out.println("║  1. Đọc danh sách từ file                        ║");
            System.out.println("║  2. Thêm dự án mới                               ║");
            System.out.println("║  3. Xóa dự án theo mã                            ║");
            System.out.println("║  4. Sửa thông tin dự án theo mã                  ║");
            System.out.println("║  5. Tìm kiếm dự án theo mã                       ║");
            System.out.println("║  6. Hiển thị tất cả dự án                        ║");
            System.out.println("║  7. Thống kê tổng kinh phí                       ║");
            System.out.println("║  8. Thống kê số dự án có thưởng                  ║");
            System.out.println("║  9. Ghi danh sách ra file                        ║");
            System.out.println("║  0. Thoát                                        ║");
            System.out.println("╚══════════════════════════════════════════════════╝");
            System.out.print("👉 Chọn chức năng: ");

            while (!sc.hasNextInt()) {
                System.out.print("❌ Vui lòng nhập số: ");
                sc.next();
            }
            chon = sc.nextInt();
            sc.nextLine();

            switch (chon) {
                case 1:
                    docFile();
                    break;
                case 2:
                    themDuAn();
                    break;
                case 3: {
                    System.out.print("Nhập mã dự án cần xóa: ");
                    String maXoa = sc.nextLine();
                    xoa(maXoa);
                    break;
                }
                case 4: {
                    System.out.print("Nhập mã dự án cần sửa: ");
                    String maSua = sc.nextLine();
                    suaThongTin(maSua);
break;
                }
                case 5: {
                    System.out.print("Nhập mã dự án cần tìm: ");
                    String maTim = sc.nextLine();
                    timKiem(maTim);
                    break;
                }
                case 6:
                    xuatDanhSach();
                    break;
                case 7:
                    thongKeKinhPhi();
                    break;
                case 8:
                    thongKeDuAnThuong();
                    break;
                case 9:
                    ghiFile();
                    break;
                case 0:
                    System.out.println("\n👋 Cảm ơn bạn đã sử dụng chương trình!");
                    break;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ! Vui lòng chọn lại.");
            }

        } while (chon != 0);
    }




}