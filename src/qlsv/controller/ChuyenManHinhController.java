package qlsv.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JLabel;
//Giao diện lúc đồng ý thoát chương trình
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import qlsv.bean.DanhMucBean;
import qlsv.view.GiangVienJPanel;
import qlsv.view.SinhVienJPanel;
import qlsv.view.ThongKeJPanel;
import qlsv.view.TrangChuJPanel;

/**
 *
 * @author trand
 */
public class ChuyenManHinhController {
    
    private  JPanel jpnRoot;
    private String kindSelected = "";
     
    private List<DanhMucBean> listItem = null;

    public ChuyenManHinhController(JPanel jpnRoot) {
        this.jpnRoot = jpnRoot;
    }

    public void setView(JPanel jpnItem, JLabel jlbItem){
        kindSelected = "TrangChu";
        jpnItem.setBackground(new Color(7,151,243));
        jlbItem.setBackground(new Color(7,151,243));
        
        jpnRoot.removeAll();
        jpnRoot.setLayout(new BorderLayout());
        jpnRoot.add(new TrangChuJPanel());
        //Hiển thị ra màn hình
        jpnRoot.validate();
        jpnRoot.repaint();
        
    }

    public void setEvent(List<DanhMucBean> listItem) {
        this.listItem = listItem;
        for(DanhMucBean item : listItem) {
            item.getJlb().addMouseListener(new LabelEvent(item.getKind(), item.getJpn(), item.getJlb()));   
        }
    }

    class LabelEvent implements  MouseListener {

        private JPanel node;
        
        private String kind;
        private JPanel jpnItem;
        private JLabel jlbItem;

        public LabelEvent(String kind, JPanel jpnItem, JLabel jlbItem) {
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.jlbItem = jlbItem;
        }
        
        
        //Đổi màu khi click chuột
        @Override
        public void mouseClicked(MouseEvent e) {
            switch(kind){
                case "TrangChu":
                    node = new TrangChuJPanel();
                    break;
                case "SinhVien":  
                    node = new SinhVienJPanel();
                    break;               
                case "GiangVien":
                    node = new GiangVienJPanel();
                    break;               
                case "ThongKe":
                    node = new ThongKeJPanel();
                    break;
                case "Thoat":
                    int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn thoát không?", "Xác nhận thoát", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        System.exit(0);  // Thoát ứng dụng nếu người dùng xác nhận
                    }
                default:
                    node = new TrangChuJPanel();
                    break;
            }
            
            jpnRoot.removeAll();
            jpnRoot.setLayout(new BorderLayout());
            jpnRoot.add(node);
            jpnRoot.validate();
            jpnRoot.repaint();
            setChangeBackground(kind);
        }
        
        //Đổi màu khi nhấn chuột 1 phát
        @Override
        public void mousePressed(MouseEvent e) {
            kindSelected = kind;
            jpnItem.setBackground(new Color(124,196,242));
            jlbItem.setBackground(new Color(124,196,242));            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        //Đổi màu khi di chuột qua
        @Override
        public void mouseEntered(MouseEvent e) {
            jpnItem.setBackground(new Color(7,151,243));
            jlbItem.setBackground(new Color(7,151,243));
        }
        
        //Khi không còn click nữa
        @Override
        public void mouseExited(MouseEvent e) {
            if(!kindSelected.equalsIgnoreCase(kind)){
                jpnItem.setBackground(new Color(124,196,242));
                jlbItem.setBackground(new Color(124,196,242));
            }
        }
        
    }
    /*
    Mỗi một menu JPanel sẽ có một kind riêng đại diện cho chúng
    Khi ta click vào một kind bất kì, nó sẽ trả về kind ta click được qua: getKind()
        còn kind kia là kind của các menu JPanel
    Nếu kind ta click vào, trùng tên với kind nào, thì kind đó sẽ hiện màu tím
        ngược lại thì sẽ hiện màu xanh lá
        hiểu đơn giản là: click vào thì tím, ko clik thì xanh lá
    
    equalsIgnoreCase: so sánh 2 chuỗi, ko phân biệt viết hoa-thường
    */
    private void setChangeBackground(String kind) {
        for(DanhMucBean item : listItem) {
            if(item.getKind().equalsIgnoreCase(kind)) {
                item.getJpn().setBackground(new Color(7,151,243));
                item.getJlb().setBackground(new Color(7,151,243));
            } else {
                item.getJpn().setBackground(new Color(124,196,242));
                item.getJlb().setBackground(new Color(124,196,242));
            }
        }
    }
}
