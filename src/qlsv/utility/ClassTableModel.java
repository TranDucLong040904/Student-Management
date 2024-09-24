package qlsv.utility;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import qlsv.model.GiangVien;
import qlsv.model.SinhVien;

/**
 *
 * @author trand
 */
public class ClassTableModel {
    
    public DefaultTableModel setTableSinhVien(List<SinhVien> listItem, String[] listColum) {
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
        };
        
        dtm.setColumnIdentifiers(listColum);
        int columns = listColum.length;
        Object[] obj = null;
        int rows = listItem.size();
        if(rows > 0) {
            for(int i = 0; i < rows; i++) {
                SinhVien sinhVien = listItem.get(i);
                obj = new Object[columns];
                obj[0] = (i + 1);
                obj[1] = sinhVien.getMa_sinh_vien();
                obj[2] = sinhVien.getHo_ten();
                obj[3] = sinhVien.getNgay_sinh();
                obj[4] = sinhVien.isGioi_tinh() == true ? "Nam" : "Nữ";
                obj[5] = sinhVien.getSo_dien_thoai();
                obj[6] = sinhVien.getDia_chi();
                obj[7] = sinhVien.isTinh_trang() == true ? "Đang học" : "Đã nghỉ học";
                dtm.addRow(obj);
            }
        }


        return dtm;
    }
    
    public DefaultTableModel setTableGiangVien(List<GiangVien> listItem, String[] listColum) {
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
        };
        
        dtm.setColumnIdentifiers(listColum);
        int columns = listColum.length;
        Object[] obj = null;
        int rows = listItem.size();
        if(rows > 0) {
            for(int i = 0; i < rows; i++) {
                GiangVien giangVien = listItem.get(i);
                obj = new Object[columns];
                obj[0] = (i + 1);
                obj[1] = giangVien.getMa_giang_vien();
                obj[2] = giangVien.getHo_ten();
                obj[3] = giangVien.getNgay_sinh();
                obj[4] = giangVien.isGioi_tinh() == true ? "Nam" : "Nữ";
                obj[5] = giangVien.getSo_dien_thoai();
                obj[6] = giangVien.getDia_chi();
                obj[7] = giangVien.isTinh_trang() == true ? "Đang dạy" : "Đã nghỉ dạy";
                dtm.addRow(obj);
            }
        }


        return dtm;
    }
}
