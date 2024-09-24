package qlsv.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import qlsv.model.GiangVien;
import qlsv.service.GiangVienService;
import qlsv.service.GiangVienServiceImpl;
import qlsv.utility.ClassTableModel;
import qlsv.view.GiangVienJFrame;


/**
 *
 * @author trand
 */
public class QuanLyGiangVienController {
    
    private JPanel jpnView;
    private JButton btnAdd;
    private JButton btnDelete;
    private JTextField jtfSearch;
    
    private GiangVienService giangVienService = null;

    private String[] listColumn = {"STT", "Mã Giảng Viên", "Họ Tên", "Ngày sinh", "Giới tính", "Số điện thoại", "Địa chỉ", "Tình trạng"};

    private DefaultTableModel tableModel;
    private TableRowSorter<TableModel> rowSorter = null;
    private JTable table;

    public QuanLyGiangVienController(JPanel jpnView, JButton btnAdd, JButton btnDelete, JTextField jtfSearch) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.btnDelete = btnDelete;
        this.jtfSearch = jtfSearch;
        
        this.giangVienService = new GiangVienServiceImpl();
        
    }

    public void setDateToTable() {
        List<GiangVien> listItem = giangVienService.getList();

        DefaultTableModel model = new ClassTableModel().setTableGiangVien(listItem, listColumn);
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
                String text = jtfSearch.getText();
                if(text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                
            }
        }); 

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();
                    selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);

                  
                    GiangVien giangVien = new GiangVien();
                    giangVien.setMa_giang_vien((int) model.getValueAt(selectedRowIndex, 1));
                    giangVien.setHo_ten(model.getValueAt(selectedRowIndex, 2).toString());
                    giangVien.setNgay_sinh((Date) model.getValueAt(selectedRowIndex, 3));
                    giangVien.setGioi_tinh(model.getValueAt(selectedRowIndex, 4).toString().equalsIgnoreCase("Nam"));
                    giangVien.setSo_dien_thoai(model.getValueAt(selectedRowIndex, 5) != null ? 
                            model.getValueAt(selectedRowIndex, 5).toString() : "");

                    giangVien.setDia_chi(model.getValueAt(selectedRowIndex, 6) != null ? 
                            model.getValueAt(selectedRowIndex, 6).toString() : "");

                    giangVien.setTinh_trang(model.getValueAt(selectedRowIndex, 7).toString().equalsIgnoreCase("Vẫn còn dạy"));

                    GiangVienJFrame frame = new GiangVienJFrame(giangVien);
                    frame.setTitle("Thông tin giảng viên");
                    frame.setResizable(false);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);


                }
           }
        });

        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(100, 50));
        table.setRowHeight(50);
        table.validate();
        table.repaint();

        JScrollPane scrollPane = new JScrollPane();
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
                GiangVienJFrame frame = new GiangVienJFrame(new GiangVien());
                frame.setTitle("Thông tin giảng viên");
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);
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

                        GiangVien giangVien = new GiangVien();
                        giangVien.setMa_giang_vien((int) model.getValueAt(selectedRowIndex, 1));

                        int result = giangVienService.delete(giangVien);
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
