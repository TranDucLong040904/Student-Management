package qlsv.service;

import java.util.List;
import qlsv.dao.GiangVienDAO;
import qlsv.dao.GiangVienDAOImpl;
import qlsv.model.GiangVien;

/**
 *
 * @author trand
 */
public class GiangVienServiceImpl implements GiangVienService {
    private GiangVienDAO giangVienDAO = null;

    public GiangVienServiceImpl() {
        giangVienDAO = new GiangVienDAOImpl();
    }

    @Override
    public List<GiangVien> getList() {
        return giangVienDAO.getList();
    }

    @Override
    public int createOrUpdate(GiangVien giangVien) {
        return giangVienDAO.createOrUpdate(giangVien);
    }
    
    @Override
    public int delete(GiangVien giangVien) {
        return giangVienDAO.delete(giangVien);
    }
      
}
