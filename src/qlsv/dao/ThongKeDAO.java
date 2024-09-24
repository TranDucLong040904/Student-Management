package qlsv.dao;

import java.util.List;
import qlsv.bean.KhoaHocBean;
import qlsv.bean.LopHocBean;

/**
 *
 * @author trand
 */
public interface ThongKeDAO {
    
    // Trả về thống kê liên quan đến lớp học
    public List<LopHocBean> getListByLopHoc();
    
    // Trả về thống kê lien quan đến khóa học
    public List<KhoaHocBean> getListByKhoaHoc();

}
