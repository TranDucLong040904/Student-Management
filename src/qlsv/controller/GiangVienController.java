package qlsv.controller;

import com.toedter.calendar.JDateChooser;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.net.PortUnreachableException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import qlsv.model.GiangVien;
import qlsv.service.GiangVienService;
import qlsv.service.GiangVienServiceImpl;

/**
 *
 * @author trand
 */
public class GiangVienController {
    private JButton btnSubmit;
    private JTextField jtfMaGiangVien;
    private JTextField jtfHoTen;
    private JDateChooser jdcNgaySinh;
    private JRadioButton jrdNam;
    private JRadioButton jrdNu;
    private JTextField jtfSoDienThoai;
    private JTextArea  jtaDiaChi;
    private JCheckBox jcbTinhTrang;
    private JLabel jlbMsg;

    private GiangVien giangVien = null;
    private GiangVienService giangVienService = null;

    public GiangVienController(JButton btnSubmit, JTextField jtfMaGiangVien, JTextField jtfHoTen,
            JDateChooser jdcNgaySinh, JRadioButton jrdNam, JRadioButton jrdNu, 
            JTextField jtfSoDienThoai, JTextArea jtaDiaChi, JCheckBox jcbTinhTrang, JLabel jlbMsg) {
                
        this.btnSubmit = btnSubmit;
        this.jtfMaGiangVien = jtfMaGiangVien;
        this.jtfHoTen = jtfHoTen;
        this.jdcNgaySinh = jdcNgaySinh;
        this.jrdNam = jrdNam;
        this.jrdNu = jrdNu;
        this.jtfSoDienThoai = jtfSoDienThoai;
        this.jtaDiaChi = jtaDiaChi;
        this.jcbTinhTrang = jcbTinhTrang;
        this.jlbMsg = jlbMsg;

        this.giangVienService = new GiangVienServiceImpl();
        
        
        
    }

    public void setView(GiangVien giangVien) {
        this.giangVien = giangVien;

        jtfMaGiangVien.setText("PKA" + giangVien.getMa_giang_vien());
        jtfHoTen.setText(giangVien.getHo_ten());
        jdcNgaySinh.setDate(giangVien.getNgay_sinh());

        if(giangVien.isGioi_tinh()) {
            jrdNam.setSelected(true);
        } else {
            jrdNu.setSelected(true);
        }

        jtfSoDienThoai.setText(giangVien.getSo_dien_thoai());
        jtaDiaChi.setText(giangVien.getDia_chi());
        jcbTinhTrang.setSelected(giangVien.isTinh_trang());

        setEvent();
    }   

    public void setEvent() {
        btnSubmit.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if(jtfHoTen.getText().length() == 0 || jdcNgaySinh.getDate() == null) {
                    jlbMsg.setText("Vui lòng nhập đầy đủ thông tin");                    
                } else {

                    giangVien.setHo_ten(jtfHoTen.getText());
                    giangVien.setNgay_sinh(jdcNgaySinh.getDate());
                    giangVien.setGioi_tinh(jrdNam.isSelected());
                    giangVien.setSo_dien_thoai(jtfSoDienThoai.getText());
                    giangVien.setDia_chi(jtaDiaChi.getText());
                    giangVien.setTinh_trang(jcbTinhTrang.isSelected());

                    int lastId = giangVienService.createOrUpdate(giangVien);
                    if(lastId > 0) {
                        giangVien.setMa_giang_vien(lastId);
                        String lastIdString = String.valueOf(lastId);
                        jtfMaGiangVien.setText("PKA" + lastId);
                        jlbMsg.setText("Lưu dữ liệu thành công!");
                    }
                               
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnSubmit.setBackground(new Color(0, 200, 83));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                btnSubmit.setBackground(new Color(100, 221, 23));
            }
        });

    }
      
    
}
