package qlsv.model;

import java.util.Date;

/**
 *
 * @author trand
 */
public class LopHoc {
    
    private int ma_lop_hoc;
    private KhoaHoc khoaHoc;
    private SinhVien sinhVien;
    private Date ngay_dang_ky;
    private boolean tinh_trang;

    public int getMa_lop_hoc() {
        return ma_lop_hoc;
    }

    public void setMa_lop_hoc(int ma_lop_hoc) {
        this.ma_lop_hoc = ma_lop_hoc;
    }

    public KhoaHoc getKhoaHoc() {
        return khoaHoc;
    }

    public void setKhoaHoc(KhoaHoc khoaHoc) {
        this.khoaHoc = khoaHoc;
    }

    public SinhVien getSinhVien() {
        return sinhVien;
    }

    public void setSinhVien(SinhVien sinhVien) {
        this.sinhVien = sinhVien;
    }

    public Date getNgay_dang_ky() {
        return ngay_dang_ky;
    }

    public void setNgay_dang_ky(Date ngay_dang_ky) {
        this.ngay_dang_ky = ngay_dang_ky;
    }

    public boolean isTinh_trang() {
        return tinh_trang;
    }

    public void setTinh_trang(boolean tinh_trang) {
        this.tinh_trang = tinh_trang;
    }
    
}
