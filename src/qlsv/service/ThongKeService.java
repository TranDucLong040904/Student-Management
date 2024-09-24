package qlsv.service;

import java.util.List;
import qlsv.bean.KhoaHocBean;
import qlsv.bean.LopHocBean;

/**
 *
 * @author trand
 */
public interface ThongKeService {
        public List<LopHocBean> getListByLopHoc();

        public List<KhoaHocBean> getListByKhoaHoc();


}
