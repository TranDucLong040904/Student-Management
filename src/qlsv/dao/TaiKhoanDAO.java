//Khai báo package chứa class TaiKhoanDao
package qlsv.dao;

// import class TaiKhoan từ package qlsv.model để sử dụng trong interface TaiKhoanDAO
import qlsv.model.TaiKhoan;

/**
 *
 * @author trand
 */


// Khai báo interface TaiKhoanDAO, gọi ra các phương thức ko có phần thân, cho file Impl thực thi
public interface TaiKhoanDAO {
    
    /*
     * Phương thức nhận vào 2 dữ liệu: tên đăng nhập, mật khẩu  
     * Là một phương thức trừu tượng, ko có phần thân
     */
    public TaiKhoan login(String tdn, String mk);
}
