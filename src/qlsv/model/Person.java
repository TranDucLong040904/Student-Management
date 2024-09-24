package qlsv.model;

import java.util.Date;

/**
 *
 * @author trand
 */
public abstract class Person {
    String ho_ten;
    private boolean gioi_tinh;
    private Date ngay_sinh;

    public String getHo_ten() {
        return ho_ten;
    }

    public void setHo_ten(String ho_ten) {
        this.ho_ten = ho_ten;
    }

    public boolean isGioi_tinh() {
        return gioi_tinh;
    }

    public void setGioi_tinh(boolean gioi_tinh) {
        this.gioi_tinh = gioi_tinh;
    }

    public Date getNgay_sinh() {
        return ngay_sinh;
    }

    public void setNgay_sinh(Date ngay_sinh) {
        this.ngay_sinh = ngay_sinh;
    }
    
    // Lớp abstract không thân
    public abstract  int tinhTuoi();
    
    
    
}



