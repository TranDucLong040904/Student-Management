package qlsv.model;

/**
 *
 * @author trand
 */
public class GiangVien extends Person{
    private int ma_giang_vien;
    private String so_dien_thoai;
    private String dia_chi;
    private boolean tinh_trang;

    public int getMa_giang_vien() {
        return ma_giang_vien;
    }

    public void setMa_giang_vien(int ma_giang_vien) {
        this.ma_giang_vien = ma_giang_vien;
    }

    public String getSo_dien_thoai() {
        return so_dien_thoai;
    }

    public void setSo_dien_thoai(String so_dien_thoai) {
        this.so_dien_thoai = so_dien_thoai;
    }

    public String getDia_chi() {
        return dia_chi;
    }

    public void setDia_chi(String dia_chi) {
        this.dia_chi = dia_chi;
    }

    public boolean isTinh_trang() {
        return tinh_trang;
    }

    public void setTinh_trang(boolean tinh_trang) {
        this.tinh_trang = tinh_trang;
    }

    @Override
    public String toString() {
        return ma_giang_vien + " - " + ho_ten;
    }

    @Override
    public int tinhTuoi() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    
}
