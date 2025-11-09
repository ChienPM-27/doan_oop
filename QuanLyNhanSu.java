import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.Arrays;

public class QuanLyNhanSu {
    Scanner sc = new Scanner(System.in);
    private int soluongnhanvientrongcongty;
    private NhanSu[] ds;
    // constructor
    public QuanLyNhanSu(){}

    public void NhapSoLuongNhanVien() {
        System.out.print("Moi nhap so luong nhan vien");
        soluongnhanvientrongcongty = sc.nextInt();
        sc.nextLine();
        ds = new NhanSu[soluongnhanvientrongcongty];
    }
    //Nhap thong tin nhan vien (them lua chon loai)
    public void Nhapthongtinnhanvien() {
        for (int i = 0; i < soluongnhanvientrongcongty; i++) {
                System.out.println("\n==>Nhap loai nhan vien thu: " + (i + 1) + ":");
                System.out.println("1.Nhan vien chinh thuc ");
                System.out.println("2.Nhan vien thuc tap ");
                System.out.print("Chon loai nhan vien(1/2) :  ");
                int chon = sc.nextInt();
                sc.nextLine();
            if(chon == 1)  {
                ds[i] = new NhanVienChinhThuc();
            }
            else if( chon == 2){
                ds[i] = new NhanVienThucTap();
            }
            else{
                System.out.println("LUA CHON KHONG HOP LE!!!");
                ds[i] = new NhanVienChinhThuc();
            }
            ds[i].NhapTatCaThuocTinhCuaNhanSu();

            }
        }

    public void XuatThongTinNhanSu() {
        for (int i = 0; i < soluongnhanvientrongcongty; i++) {
            ds[i].XuatTatCaThongTinNhanSu();
        }
    }

    public void ThemNhanSu() {
        System.out.print("Nhap thong tin nhan su moi :");
        ds = Arrays.copyOf(ds,ds.length+1);
        ds[soluongnhanvientrongcongty-1]= new NhanSu();
        ds[soluongnhanvientrongcongty - 1].NhapTatCaThuocTinhCuaNhanSu();
        soluongnhanvientrongcongty++;
    }
    public void TimKiemNhanSuTheoMa(){
        String temp;
        System.out.print("Mời Nhập Mã Nhân Viên Cần Tìm :");
        temp = sc.nextLine();
        boolean  flag = false;
        for(int i = 0 ; i < soluongnhanvientrongcongty ; i++){
            if(ds[i].getMa().equals(temp)) {
                ds[i].XuatTatCaThongTinNhanSu();
                flag = true;
            }

        }
        if(flag == false ){
            System.out.println("Khong Tim Thay Nhan Vien Co Ma : "+temp);
        }

    }
    public void TimKiemTheoTen(){
        String temp ;
        System.out.print("Moi Nhap Ten Nhan Vien Can Tim : ");
        temp = sc.nextLine();
        int count = 0 ;
        Boolean flag = false;
        for(int i = 0 ; i < soluongnhanvientrongcongty ; i++){
            if(ds[i].getTen().equals(temp)){
                System.out.println(ds[i].getTen());
                flag  = true;
                count++;
            }
        }
        if(flag = false ){
            System.out.println("Khong Tim Thay Nhan Vien Ten : "+temp);
        }
        else {
            System.out.println("Tim thay "+count+" nhan vien");
        }
    }
    public void TimKiemNhanVienTheoHo(){
        System.out.print("Moi Nhap Ho Nhan Vien Muon Tim Kiem : ");
        String temp = sc.nextLine();
        boolean flag  = false;
        int count = 0 ;
        for(int i = 0 ; i < soluongnhanvientrongcongty ; i++){
            if(ds[i].getHo().equals(temp)){
                System.out.println(ds[i].getMa()+"-"+ds[i].getHo()+" "+ds[i].getTen()+"-"+ds[i].getNgay()+"/"+ds[i].getThang()+"/"+ds[i].getNam());
                flag = true ;
                count ++;
            }

        }
        if(flag = false ){
            System.out.println("Khong tim thay nhan vien nao co ho : "+temp);

        }
        if(flag = true ) {
            System.out.println("Da tim thay " + count + " nhan vien!!!");
        }
    }
    // ham xoa nhan vien
    public void XoaNhanVienTheoMa() {
        System.out.print("MOI NHAP MA NHAN VIEN CAN XOA : ");
        String temp = sc.nextLine();
        Boolean flag = false ;
        for( int i = 0 ; i < soluongnhanvientrongcongty ; i ++){
            if( ds[i].getMa().equals(temp)){
                for( int j  = i + 1 ; j < soluongnhanvientrongcongty-1 ; j ++){
                    ds[i].setMa(ds[j].getMa());
                    ds[i].setHo(ds[j].getHo());
                    ds[i].setTen(ds[j].getTen());
                    ds[i].setGt(ds[j].getGt());
                    ds[i].setDiachi(ds[j].getDiachi());
                    ds[i].setMachucvu(ds[j].getMachucvu());
                    ds[i].setMa(ds[j].getMa());
                    soluongnhanvientrongcongty--;
                }
                ds = Arrays.copyOf(ds,ds.length-1);
                flag = true ;
                break;
            }
        }
        if(flag == false ){
            System.out.println("KHONG XOA DUOC NHAN VIEN !!!");
        }
        else{
            System.out.println("XOA NHAN VIEN THANH CONG !!!");
        }
    }
    public void TimKiemNhanVienTheoDoTuoi(){

        System.out.println("Moi nhap do tuoi cua nhan vien can thong ke : ");
        int temp = sc.nextInt();
        sc.nextLine();
        boolean flag= false;
        int count = 0 ;
        for(int i = 0 ; i < soluongnhanvientrongcongty ; i++){
            if(ds[i].getTuoi() == temp){
                System.out.println(ds[i].getMa()+"-"+ds[i].getHo()+" "+ds[i].getTen()+"-"+ds[i].getNgay()+"/"+ds[i].getThang()+"/"+ds[i].getNam());
                flag = true ;
                count ++;
            }
        }
        if(flag == false ){
            System.out.println("KHONG TIM THAY NHAN VIEN NAO CO DO TUOI : "+temp);

        }
        if(flag == true ){
            System.out.println("Tim thay "+count+" nhan vien !!!");
        }
    }
    //HAM SUA THONG TIN SINH VIEN THEO MA KHONG CO THAM SO
    public void SuaThongTinSinhVienTheoMa(){
        System.out.print("MOI NHAP SINH VIEN MUON SUA : ");
        String temp =  sc.nextLine();
        boolean flag = false;
        for( int i = 0 ; i < soluongnhanvientrongcongty ; i++){
            if(ds[i].getMa().indexOf(temp) != 0 ){
                ds[i].NhapTatCaThuocTinhCuaNhanSu();
                flag = true;
            }
        }
        if( flag == false ){
            System.out.println("KHONG TIM THAY THONG TIN SINH VIEN!!");
        }
        else {
            System.out.println("SUA THONG TIN SINH VIEN THANH CONG!!");
        }
    }
    //HAM SUA THONG TIN SINH VEN THEO MA CO THAM SO
    public void SuaThongTinCoThamSo(String temp, String manv, String ho, String ten , String gt, String macv, String diachi, String trangthai, int ngay , int thang, int nam,int tuoi){
        boolean flag = false;
        for( int i = 0 ; i < soluongnhanvientrongcongty ; i++){
            if(ds[i].getMa().indexOf(temp)!= 0){
                ds[i].setMa(manv);
                ds[i].setHo(ho);
                ds[i].setTen(ten);
                ds[i].setGt(gt);
                ds[i].setDiachi(diachi);
                ds[i].setMachucvu(manv);
                ds[i].setNgayThangNam(ngay,thang,nam);
                ds[i].TinhTuoiNhanVien();
                flag = true;

            }
        }
        if( flag == false){
            System.out.println("CAP NHAT THONG TIN SINH VIEN THAT BAI !!!!");
        }
        if( flag == true){
            System.out.println("CAP NHAT THANH CONG!!!");
        }

    }
    // HAM THONG KE NHAN VIEN
    //--1--THONG KE THEO DO TUOI(NHOM TRE(18-25)-NHOM TRUNG NIEN(26-35,36-50)-NHOM CAO TUOI(TREN 50)
    public void ThongKeNhanVienTheoDoTuoi(){
        int count1 =  0;
        int count2 = 0 ;
        int count3 = 0 ;
        for(int i = 0 ; i < soluongnhanvientrongcongty ; i++){
            if(ds[i].getTuoi() >= 18 && ds[i].getTuoi() <= 25){
                count1 ++ ;
            }
            else if(ds[i].getTuoi() >= 26 && ds[i].getTuoi() <= 50){
                count2 ++;
            }
            else if(ds[i].getTuoi() >= 50 ){
                count3 ++;
            }
        }
        System.out.println("SO LUONG NHAN VIEN THUOC NHOM TRE (18-25) :"+count1);
        System.out.println("SO LUONG NHAN VIEN THUOC NHOM TRUNG NIEN (26-50) :"+count2);
        System.out.println("SO LUONG NHAN VIEN THUOC NHOM CAO TUOI (TREN 50) :"+count3);
    }
    //--2-- THONG KE THEO GIOI TINH
    public void ThongKeTheoGioiTinh(){
        int count1 = 0 ;
        int count2 = 0 ;
        for(int i = 0 ; i < soluongnhanvientrongcongty ; i++){
            if(ds[i].getGt().indexOf("Nam") == 1){
                count1++;
            }
            else if( ds[i].getGt().indexOf("Nu") == 1){
                count2++;
            }
        }
        System.out.println("SO LUONG NHAN VIEN NAM TRONG CONG TY LA : "+count1);
        System.out.println("SO LUONG NHAN VIEN NU TRONG CONG TY LA : "+count2);
    }
    //--3-- THONG KE THEO MA CHUC VU CUA NHAN VIEN
    public void ThongKeTheoMaChucVu(){
        for( int i = 0 ; i < soluongnhanvientrongcongty-1 ; i++){
            System.out.println("CAC NHAN VIEN CO MA CHUC VU "+ds[i].getMachucvu()+" :");
            for(int j = i+1 ; j < soluongnhanvientrongcongty ; j ++){
                if(ds[j].getMachucvu().indexOf( ds[i].getMachucvu()) != 0){
                    System.out.println(ds[i].getMa()+"-"+ds[i].getHo()+" "+ds[i].getTen()+"-"+ds[i].getNgay()+"/"+ds[i].getThang()+"/"+ds[i].getNam());
                }
            }
        }
    }
    //MENU QUAN LY NHAN SU
    public void menu() {
        int chon;
        do {
            System.out.println("\n===== MENU QUẢN LÝ NHÂN SỰ =====");
            System.out.println("1. Nhập số lượng nhân viên");
            System.out.println("2. Nhập thông tin các nhân viên");
            System.out.println("3. Xuất thông tin nhân viên");
            System.out.println("4. Thêm nhân viên mới");
            System.out.println("5. Tìm kiếm nhân viên theo mã");
            System.out.println("6. Tìm kiếm nhân viên theo tên");
            System.out.println("7. Tìm kiếm nhân viên theo họ");
            System.out.println("8. Xóa nhân viên theo mã");
            System.out.println("9. Tìm kiếm nhân viên theo độ tuổi");
            System.out.println("10. Sửa thông tin nhân viên theo mã");
            System.out.println("11. Thống kê nhân viên theo độ tuổi");
            System.out.println("12. Thống kê nhân viên theo giới tính");
            System.out.println("13. Thống kê nhân viên theo mã chức vụ");
            System.out.println("0. Thoát chương trình");
            System.out.print(">> Nhập lựa chọn của bạn: ");

            chon = sc.nextInt();
            sc.nextLine(); // xoá ký tự xuống dòng còn lại

            switch (chon) {
                case 1:
                    NhapSoLuongNhanVien();
                    // ✅ tự động chuyển sang nhập thông tin ngay sau khi nhập số lượng
                    Nhapthongtinnhanvien();
                    break;
                case 2:
                    Nhapthongtinnhanvien();
                    break;
                case 3:
                    XuatThongTinNhanSu();
                    break;
                case 4:
                    ThemNhanSu();
                    break;
                case 5:
                    TimKiemNhanSuTheoMa();
                    break;
                case 6:
                    TimKiemTheoTen();
                    break;
                case 7:
                    TimKiemNhanVienTheoHo();
                    break;
                case 8:
                    XoaNhanVienTheoMa();
                    break;
                case 9:
                    TimKiemNhanVienTheoDoTuoi();
                    break;
                case 10:
                    SuaThongTinSinhVienTheoMa();
                    break;
                case 11:
                    ThongKeNhanVienTheoDoTuoi();
                    break;
                case 12:
                    ThongKeTheoGioiTinh();
                    break;
                case 13:
                    ThongKeTheoMaChucVu();
                    break;
                case 0:
                    System.out.println("✅ Thoát chương trình. Tạm biệt!");
                    break;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ, vui lòng thử lại!");
            }
        } while (chon != 0);
    }}

