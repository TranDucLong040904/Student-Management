package qlsv.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import qlsv.model.GiangVien;

/**
 *
 * @author trand
 */
public class GiangVienDAOImpl implements GiangVienDAO {
    
    @Override
    public List<GiangVien> getList() {
        Connection cons = DBConnect.getConnection();
        String sql = "SELECT * FROM giang_vien";
        List<GiangVien> list = new ArrayList<>();
        try {
            PreparedStatement ps = cons.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                GiangVien giangVien = new GiangVien();
                giangVien.setMa_giang_vien(rs.getInt("ma_giang_vien"));
                giangVien.setHo_ten(rs.getString("ho_ten"));
                giangVien.setSo_dien_thoai(rs.getString("so_dien_thoai"));
                giangVien.setDia_chi(rs.getString("dia_chi"));
                giangVien.setNgay_sinh(rs.getDate("ngay_sinh"));
                giangVien.setGioi_tinh(rs.getBoolean("gioi_tinh"));
                giangVien.setTinh_trang(rs.getBoolean("tinh_trang"));
                list.add(giangVien);
            }
            ps.close();
            cons.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int createOrUpdate(GiangVien giangVien) {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "INSERT INTO giang_vien(ma_giang_vien, ho_ten, ngay_sinh, gioi_tinh, so_dien_thoai, dia_chi, tinh_trang) VALUES(?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE ho_ten = VALUES(ho_ten), ngay_sinh = VALUES(ngay_sinh), gioi_tinh = VALUES(gioi_tinh), so_dien_thoai = VALUES(so_dien_thoai), dia_chi = VALUES(dia_chi), tinh_trang = VALUES(tinh_trang);";
            PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setInt(1, giangVien.getMa_giang_vien());
            ps.setString(2, giangVien.getHo_ten());
            ps.setDate(3, new Date(giangVien.getNgay_sinh().getTime()));
            ps.setBoolean(4, giangVien.isGioi_tinh());
            ps.setString(5, giangVien.getSo_dien_thoai());
            ps.setString(6, giangVien.getDia_chi());
            ps.setBoolean(7, giangVien.isTinh_trang());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }
            ps.close();
            cons.close();
            return generatedKey;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    
    @Override
    public int delete(GiangVien giangVien) {
        try (Connection cons = DBConnect.getConnection();
             PreparedStatement ps = cons.prepareStatement("DELETE FROM giang_vien WHERE ma_giang_vien = ?")) {

            ps.setInt(1, giangVien.getMa_giang_vien());

            return ps.executeUpdate(); 
        } catch (Exception e) {
            e.printStackTrace();
            return -1; 
        }
    }


    public static void main(String[] args) {
        GiangVienDAO giangVienDAO = new GiangVienDAOImpl();
        System.out.println(giangVienDAO.getList());
    }
}
