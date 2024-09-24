package qlsv.service;

import qlsv.dao.TaiKhoanDAO;
import qlsv.dao.TaiKhoanDAOImpl;
import qlsv.model.TaiKhoan;

/**
 *
 * @author trand
 */

 // Class này sẽ thực thi nhiệm vụ từ interface
public class TaiKhoanServiceImpl implements TaiKhoanService {
    
    /*
     * Phạm vi private, chỉ có thể truy cập trong class này
     * Mặc định là taiKhoanDAO trống, ko chứa gì.
     */
    private TaiKhoanDAO taiKhoanDAO = null;
     
    /*
     * Tạo 1 cấu tử TaiKhoanServiceImpl, khởi tạo bằng TaiKhoanDAOImpl
     * Khi đó TaiKhoanServiceImpl sẽ thực thi được các phương thức từ TaiKhoanDAOImpl
     */
    public TaiKhoanServiceImpl() {
        taiKhoanDAO = new TaiKhoanDAOImpl();
    }
    

    // Thực hiện yêu cầu login từ TaiKhoanService
    @Override
    public TaiKhoan login(String tdn, String mk) {
        return taiKhoanDAO.login(tdn, mk);
    }
}
