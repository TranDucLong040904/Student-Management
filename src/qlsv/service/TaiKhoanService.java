package qlsv.service;

import qlsv.model.TaiKhoan;

/**
 *
 * @author trand
 */

 // interface dùng để giao nhiệm vụ
public interface  TaiKhoanService {
    
    // Phương thức yêu cầu nhập tdn, mk
    public TaiKhoan login(String tdn, String mk);
}
