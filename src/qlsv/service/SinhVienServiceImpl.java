package qlsv.service;

import java.util.List;
import qlsv.dao.SinhVienDAO;
import qlsv.dao.SinhVienDAOImpl;
import qlsv.model.SinhVien;

/**
 *
 * @author trand
 */

// Thực hiện nhiệm vụ từ interface SinhVienService giao cho
public class SinhVienServiceImpl implements SinhVienService {
    private SinhVienDAO sinhVienDAO = null;

    // Tạo cấu tử SinhVienServideImpl, có hết các phương thức của SVDAOImpl
    public SinhVienServiceImpl() {
        sinhVienDAO = new SinhVienDAOImpl();
    }
    
    //Xuất ra danh sách sinh viên
    @Override
    public List<SinhVien> getList() {
        return sinhVienDAO.getList();
    }
    
    //Cập nhật thông tin sin viên
    @Override
    public int createOrUpdate(SinhVien sinhVien) {
        return sinhVienDAO.createOrUpdate(sinhVien);
    }

    //Xóa sinh viên
    @Override
    public int delete(SinhVien sinhVien) {
        return sinhVienDAO.delete(sinhVien);
    }
    

}
