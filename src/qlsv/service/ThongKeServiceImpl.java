package qlsv.service;

import java.util.List;
import qlsv.bean.KhoaHocBean;
import qlsv.bean.LopHocBean;
import qlsv.dao.ThongKeDAO;
import qlsv.dao.ThongKeDAOImpl;

/**
 *
 * @author trand
 */
public class ThongKeServiceImpl implements ThongKeService {

    private ThongKeDAO thongKeDao = null;

    public ThongKeServiceImpl() {
        thongKeDao = new ThongKeDAOImpl();
    }



    @Override
    public List<LopHocBean> getListByLopHoc() {
        return thongKeDao.getListByLopHoc();
    }

    @Override
    public List<KhoaHocBean> getListByKhoaHoc() {
        return thongKeDao.getListByKhoaHoc();
    }
    
}
