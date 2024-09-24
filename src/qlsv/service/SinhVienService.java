package qlsv.service;

//Gói thư viện làm việc với danh sách
import java.util.List;
import qlsv.model.SinhVien;

/**
 *
 * @author trand
 */
public interface SinhVienService {
    
    // Xuất ra danh sach sinh viên
    public List<SinhVien> getList();
    //Thêm sinh viên
    public int createOrUpdate(SinhVien sinhVien);
    //Xóa sinh viên
    public int delete(SinhVien sinhVien);
    
}
