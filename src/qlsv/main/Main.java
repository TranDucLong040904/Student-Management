package qlsv.main;


import qlsv.view.DangNhapJDialog;
//import qlsv.view.MainJFrame;

/**
 *
 * @author trand    
 */
public class Main {
    
    public static void main(String[] args) {
        //new MainJFrame().setVisible(true);
          DangNhapJDialog dialog = new DangNhapJDialog(null, true);
          dialog.setTitle("Đăng Nhập Hệ Thống");
          dialog.setResizable(false);
          dialog.setLocationRelativeTo(null);
          dialog.setVisible(true);
          
    }
}
