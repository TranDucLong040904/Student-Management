package qlsv.bean;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author trand
 */
public class DanhMucBean {
    
    private String kind;
    private JPanel jpn;
    private JLabel jlb;

    /*
    DanhMucBean dùng để khai báo các dữ liệu trả về khi click chuột vào menu
    Không phải lúc nào cũng có dữ liệu trả về (ko phải lúc nào người dùng cũng click menu)
    Vậy nên khai báo 1 constructor trống để tránh sảy ra lỗi
    */
    public DanhMucBean() {
    }

    public DanhMucBean(String kind, JPanel jpn, JLabel jlb) {
        this.kind = kind;
        this.jpn = jpn;
        this.jlb = jlb;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public JPanel getJpn() {
        return jpn;
    }

    public void setJpn(JPanel jpn) {
        this.jpn = jpn;
    }

    public JLabel getJlb() {
        return jlb;
    }

    public void setJlb(JLabel jlb) {
        this.jlb = jlb;
    }
    
}
