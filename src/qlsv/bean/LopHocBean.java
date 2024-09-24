package qlsv.bean;

/**
 *
 * @author trand
 */
public class LopHocBean {
    
    private String ngayDangKy;
    private int soLuongSinhVien;

    public LopHocBean() {
    }

    public LopHocBean(String ngayDangKy, int soLuongSinhVien) {
        this.ngayDangKy = ngayDangKy;
        this.soLuongSinhVien = soLuongSinhVien;
    }

    public String getNgayDangKy() {
        return ngayDangKy;
    }

    public void setNgayDangKy(String ngayDangKy) {
        this.ngayDangKy = ngayDangKy;
    }

    public int getSoLuongSinhVien() {
        return soLuongSinhVien;
    }

    public void setSoLuongSinhVien(int soLuongSinhVien) {
        this.soLuongSinhVien = soLuongSinhVien;
    }
    
    
    
}
