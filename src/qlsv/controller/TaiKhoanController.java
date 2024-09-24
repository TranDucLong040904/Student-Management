package qlsv.controller;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import qlsv.model.TaiKhoan;
import qlsv.service.TaiKhoanService;
import qlsv.service.TaiKhoanServiceImpl;
import qlsv.view.MainJFrame;

/**
 *
 * @author trand
 */
public class TaiKhoanController {
    
    private JDialog dialog;
    private JButton btnSubmit;
    private JTextField jtfTenDangNhap;
    private JPasswordField jtfMatKhau;
    private JLabel jlbMsg;

    private TaiKhoanService taiKhoanService = null;

    public TaiKhoanController(JDialog dialog, JButton btnSubmit, 
                JTextField jtfTenDangNhap, JPasswordField jtfMatKhau, JLabel jlbMsg) {

        this.dialog = dialog;
        this.btnSubmit = btnSubmit;
        this.jtfTenDangNhap = jtfTenDangNhap;
        this.jtfMatKhau = jtfMatKhau;
        this.jlbMsg = jlbMsg;

        taiKhoanService = new TaiKhoanServiceImpl();
    }

    
    public void setEvent() {
        btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {  

                //Nếu ko điền tdn hoặc mk thì sẽ báo lỗi
                if(jtfTenDangNhap.getText().length() == 0 || jtfMatKhau.getText().length() == 0) {
                    jlbMsg.setText("Vui lòng nhập đầy đủ thông tin");
                } else {
                    TaiKhoan taiKhoan = taiKhoanService.login(jtfTenDangNhap.getText(), jtfMatKhau.getText()); 
                    if(taiKhoan == null) {
                        jlbMsg.setText("Tên đăng nhập hoặc mật khẩu không đúng");

                    } else {

                        // Nếu trình trạng = 0 => Ko log in được vì bị khóa tài khoản
                        if(!taiKhoan.isTinh_trang()) {
                            jlbMsg.setText("Tài khoản đã bị khóa");         
                        } else {
                            
                            // Nếu nhập đúng, sẽ mở vào giao diện chính
                            
                            MainJFrame frame = new MainJFrame();
                            frame.setTitle("Quản lý đào tạo");
                            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                            frame.setVisible(true);
                            dialog.dispose();
                        }
                
                    }
                }
            }

            // Khi di chuột vào, đổi màu nút "Lưu dữ liệu"
            @Override
            public void mouseEntered(MouseEvent e) {
                btnSubmit.setBackground(new Color(0, 200, 83));
            }

            // Khi chuột đi ra, đổi màu nút "Lưu dữ liệu"
            @Override
            public void mouseExited(MouseEvent e) {
                btnSubmit.setBackground(new Color(100, 221, 23));
                
            }
        
        });
    }
}
