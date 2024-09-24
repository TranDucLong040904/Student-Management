package qlsv.dao;

import java.util.List;
import qlsv.model.SinhVien;

/**
 *
 * @author trand
 */

// Interface giao nhiệm vụ
public interface SinhVienDAO {
    
    // In ra danh sách sinh viên vào database
    public List<SinhVien> getList();
    //Thêm sinh viên vào database
    public int createOrUpdate(SinhVien sinhVien);
    //Xóa sinh viên khỏi database
    public int delete(SinhVien sinhVien);
}
