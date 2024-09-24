package qlsv.dao;

// Cung cấp một kết nối với cơ sở dữ liệu
import java.sql.Connection;
//Thực thi các câu lệnh với SQL : dòng 31
import java.sql.PreparedStatement;
//Trích xuất giá trị từ database
import java.sql.ResultSet;
import qlsv.model.TaiKhoan;


/**
 *
 * @author trand
 */

 // Khai báo class TaiKhoanDAOImpl thực thi nhiệm vụ từ interface TaiKhoanDAO
public class TaiKhoanDAOImpl implements  TaiKhoanDAO {
    @Override
    /*
     * Phương thức là login
     * Đối tượng là TaiKhoan
     */
    public TaiKhoan login(String tdn, String mk) {

        // Sử dụng phương thức getConnection() từ class DBConnect để kết nối với cơ sở dữ liệu
        Connection cons = DBConnect.getConnection();
        /*
         * Câu lệnh truy vấn SQL, chọn tất cả thông tin từ bảng tai_khoan: tên đăng nhập, mật khẩu
         * 2 dấu ?, thì đọc ở dòng 35
         */
        String sql = "SELECT * FROM tai_khoan WHERE ten_dang_nhap = ? AND mat_khau = ?";

        // Mặc định là mật khẩu sai và ko đăng nhập được
        TaiKhoan taiKhoan = null;
        try {
            // Thay thế dấu ? bằng tên đăng nhập(tdn) và mật khẩu(mk)
            PreparedStatement ps = (PreparedStatement) cons.prepareStatement(sql);
            ps.setString(1, tdn);
            ps.setString(2, mk);

            //Thực hiện truy vấn dữ liệu
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                taiKhoan = new TaiKhoan();

                // Đọc dữ liệu từ cơ sở dữ liệu, và gán vào các biến
                taiKhoan.setMa_tai_khoan(rs.getInt("ma_tai_khoan"));
                taiKhoan.setTen_dang_nhap(rs.getString("ten_dang_nhap"));
                taiKhoan.setMat_khau(rs.getString("mat_khau"));
                taiKhoan.setTinh_trang(rs.getBoolean("tinh_trang"));
            }
            // Dùng xong thì đóng kết nối để tránh lãng phí tài nguyên
            ps.close();
            cons.close();

            /*
             * Trả về gia trị của đối tượng taiKhoan
             * Nếu đăng nhập thành công thì trả về thông tin tài khoản
             * Nếu sai thì vẫn giữ giá trị null
             */
            return taiKhoan;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
