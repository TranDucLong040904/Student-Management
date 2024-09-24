package qlsv.service;

import java.util.List;
import qlsv.model.GiangVien;

/**
 *
 * @author trand
 */
public interface GiangVienService {
    
    public List<GiangVien> getList();
    public int createOrUpdate(GiangVien giangVien);
    public int delete(GiangVien giangVien);
    
}
