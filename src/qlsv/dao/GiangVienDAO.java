package qlsv.dao;

import java.util.List;
import qlsv.model.GiangVien;

/**
 *
 * @author trand
 */
public interface GiangVienDAO {
    
    public List<GiangVien> getList();
    public int createOrUpdate(GiangVien giangVien);
    public int delete(GiangVien giangVien);
}
