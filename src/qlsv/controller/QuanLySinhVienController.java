package qlsv.controller;

import java.awt.BorderLayout;
import java.awt.Color;
// Đại diện cho kích thước khung hình
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
//Show thông báo ra màn hình
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;

/*
2 cái này đi đôi với nhau, đọc , lắng nghe sự kiện thay đổi document
Đi liền với mấy cái: JTextField, JTextArea 
*/
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
//Quản lí bảng
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import qlsv.model.SinhVien;
import qlsv.service.SinhVienService;
import qlsv.service.SinhVienServiceImpl;
import qlsv.utility.ClassTableModel;
import qlsv.view.SinhVienJFrame;


/**
 *
 * @author trand
 */
public class QuanLySinhVienController {
    
    private JPanel jpnView;
    private JButton btnAdd;
    private JButton btnDelete;
    private JTextField jtfSearch;

    private SinhVienService sinhVienService = null;

    private  String[] listColumn = {"STT", "Mã Sinh Viên", "Họ Tên", "Ngày sinh", "Giới tính", "Số điện thoại", "Địa chỉ", "Tình trạng"};
    
    //DefaultTableModel: lớp để lưu trữ, quản lí dữ liệu cho bảng 
    private DefaultTableModel tableModel;
    // TableRowSorter: dùng để sắp các hàng trong cột
    private TableRowSorter<TableModel> rowSorter = null;
    private JTable table;

    public QuanLySinhVienController(JPanel jpnView, JButton btnAdd, JButton btnDelete, JTextField jtfSearch) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.btnDelete = btnDelete;
        this.jtfSearch = jtfSearch;
        
        this.sinhVienService = new SinhVienServiceImpl();
    }

    public void setDateToTable() {
        //Lấy danh sách sv từ SinhVienServiceImpl
        List<SinhVien> listItem = sinhVienService.getList();
        
        DefaultTableModel model = new ClassTableModel().setTableSinhVien(listItem, listColumn);
        table = new JTable(model);
        

        rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);

        jtfSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jtfSearch.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                //Đọc văn bản nhập vào từ ô tìm kiếm
                String text = jtfSearch.getText();
                //Nếu trống thì thôi
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    /*
                    Tạo ra một bộ lọc dữ liệu
                    ?i: ko phân biệt chữ hoa, chữ thường
                    */
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                
            }
            
        });

        table.addMouseListener(new MouseAdapter() {
            //Xử lí khi người dùng nhấp đúp chuột
           @Override
           public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();
                    selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);

                    //Tạo đối tượng sinhVien và gán thông tin từ JTable vào
                    SinhVien sinhVien = new SinhVien();
                    sinhVien.setMa_sinh_vien((int) model.getValueAt(selectedRowIndex, 1));
                    sinhVien.setHo_ten(model.getValueAt(selectedRowIndex, 2).toString());
                    sinhVien.setNgay_sinh((Date) model.getValueAt(selectedRowIndex, 3));
                    sinhVien.setGioi_tinh(model.getValueAt(selectedRowIndex, 4).toString().equalsIgnoreCase("Nam"));
                    sinhVien.setSo_dien_thoai(model.getValueAt(selectedRowIndex, 5) != null ? 
                            model.getValueAt(selectedRowIndex, 5).toString() : "");

                    sinhVien.setDia_chi(model.getValueAt(selectedRowIndex, 6) != null ? 
                            model.getValueAt(selectedRowIndex, 6).toString() : "");

                    sinhVien.setTinh_trang(model.getValueAt(selectedRowIndex, 7).toString().equalsIgnoreCase("Đang học"));

                    SinhVienJFrame frame = new SinhVienJFrame(sinhVien);
                    frame.setTitle("Thông tin sinh viên");
                    frame.setResizable(false);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);


                }
           }
        
            
        });

        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        //Đặt kích thước cho từng cột trong bảng: rộng 100px + cao 50px
        table.getTableHeader().setPreferredSize(new Dimension(100, 50));
        // Các dòng thông tin còn lại cao 50px
        table.setRowHeight(50);
        //Sau khi có sự thay đổi thì reload lại giao diện
        table.validate();
        table.repaint();

        JScrollPane scrollPane = new JScrollPane();
        //Thêm các 'table' vào vùng nhìn được của nó
        scrollPane.getViewport().add(table);
        scrollPane.setPreferredSize(new Dimension(1300, 400));
        
        jpnView.removeAll();
        jpnView.setLayout(new BorderLayout());
        jpnView.add(scrollPane);
        jpnView.validate();
        jpnView.repaint();
    }

    
    public void setEvent() {
        btnAdd.addMouseListener(new MouseAdapter() {
            // Khi click vào nút thêm mới, sẽ hiện ra chỗ để điền thông tin sinh viên
            @Override
            public void mouseClicked(MouseEvent e) {  
                SinhVienJFrame frame = new SinhVienJFrame(new SinhVien());
                frame.setTitle("Thông tin sinh viên");
                //Hiển thị ra giữa màn hình
                frame.setLocationRelativeTo(null);
                //Giao diện ko tự đổi kích thước, mà sẽ fix luôn
                frame.setResizable(false);
                //Hiển thị ra được màn hình
                frame.setVisible(true);
            }

            // Khi di chuột vào, đổi màu nút "Thêm mới"
            @Override
            public void mouseEntered(MouseEvent e) {
                btnAdd.setBackground(new Color(0, 200, 83));
            }

            // Khi chuột đi ra, đổi màu nút "Thêm mới"
            @Override
            public void mouseExited(MouseEvent e) {
                btnAdd.setBackground(new Color(100, 221, 23));
                
            }
        });

        btnDelete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();
                    if (selectedRowIndex != -1) {
                        selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);

                        SinhVien sinhVien = new SinhVien();
                        sinhVien.setMa_sinh_vien((int) model.getValueAt(selectedRowIndex, 1));

                        int result = sinhVienService.delete(sinhVien);
                        if (result > 0) {
                            JOptionPane.showMessageDialog(null, "Xóa thành công!");
                            setDateToTable(); // Cập nhật lại bảng sau khi xóa thành công
                        } else {
                            JOptionPane.showMessageDialog(null, "Xóa không thành công!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn giảng viên cần xóa!");
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnDelete.setBackground(new Color(255, 102, 102));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnDelete.setBackground(new Color(255, 51, 51));
            }
        });

       
                
    }

}
