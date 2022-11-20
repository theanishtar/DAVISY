/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GUI;

import AppPackage.AnimationClass;
import com.frame.Header;
import java.awt.Color;
import com.UI.drawer.Drawer;
import com.UI.drawer.DrawerController;
import com.UI.drawer.scroll.DrawerItem;
import com.UI.drawer.EventDrawer;
import com.dao.HangDAO;
import com.dao.LoaiHangDAO;
import com.dao.SanPhamDAO;
import com.entity.HangEntity;
import com.entity.LoaiHangEntity;
import com.entity.SanPhamEntity;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu.Separator;
import com.swing.*;
import com.utils.MsgBox;
import com.utils.XDate;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Tran Huu Dang from DAVISY
 */
public class Home extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    int chose = -1;
    int row = -1;
    int countClick = 0;
    private DrawerController drawer;
    JFileChooser f = new JFileChooser("src\\com\\images");
    File file = f.getSelectedFile();
    String anh = null;
    String imageSrc = "pc.jpg"; //Lưu đường dẫn của ảnh
    BufferedImage cloneImage, image;
    SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
    HangDAO Hang = new HangDAO();
    LoaiHangDAO Loai = new LoaiHangDAO();
    SanPhamDAO SanPham = new SanPhamDAO();
    //kiểm tra luồng
    boolean startThread = false;

    //tạo mảng chứa các phần tử card
    JPanel[] arrPn;

    //tạo mảng chứa các phần tử menu
    Button[] arrBtn;

    //màu hiển thị khi chọn menu
    Color chooserMenuItem = new Color(0, 153, 0);
    Color defaultMenuItem = Color.black;

    Button btnItemMenu = null;

    public Home() {
        initComponents();
        hideCardMenubar();
        hideMenu();
        hidePage();
        cardMenubarTrangChu.setVisible(true);
        cardTrangChuTongQuan.setVisible(true);
        pnMenu.setSize(0, 670);
        //runFont();
        this.btnItemMenu = btnTrangChu;
        settingTable();
        initHang();
        initLoai();
        initSanPham();
        fillComboxLoai();
        fillComboxHang();
        lblrecordHang.setText(recordHang());
        lblrecordLoai.setText(recordLoai());
        lblrecordSP.setText(recordSanPham());
    }

    public void settingTable() {
        tblKhachHang.setRowHeight(30);
        tblKhachHang.getTableHeader().setOpaque(false);
        TableColumnModel columnModelChuyenDe = tblKhachHang.getColumnModel();
        columnModelChuyenDe.getColumn(0).setPreferredWidth(50);
        columnModelChuyenDe.getColumn(1).setPreferredWidth(150);
    }

    public void chooserMenu(int index) {
        switch (index) {
            case 0:
                if (chose == -1 || chose != 0) {
                    hidePage();
                    hideMenu();
                    cardMenubarTrangChu.setVisible(true);
                    cardTrangChuTongQuan.setVisible(true);
                    chose = 0;
                }
                break;
            case 1:
                if (chose == -1 || chose != 1) {
                    hidePage();
                    hideMenu();
                    cardMenubarTaiKhoan.setVisible(true);
                    cardTaiKhoanNhanVien.setVisible(true);
                    chose = 1;
                }
                break;
            case 2:
                if (chose == -1 || chose != 2) {
                    hidePage();
                    hideMenu();
                    cardMenubarSanPham.setVisible(true);
                    cardSanPham.setVisible(true);
                    chose = 2;
                }
                break;
            case 3:
                if (chose == -1 || chose != 3) {
                    hidePage();
                    hideMenu();
                    cardMenubarHoaDon.setVisible(true);
                    cardHoaDon.setVisible(true);
                    chose = 3;
                }
                break;
            case 4:
                if (chose == -1 || chose != 4) {
                    hidePage();
                    hideMenu();
                    cardMenubarGiamGia.setVisible(true);
                    cardGiamGia.setVisible(true);
                    chose = 4;
                }
                break;
            case 5:
                if (chose == -1 || chose != 5) {
                    hidePage();
                    hideMenu();
                    cardMenubarGioiThieu.setVisible(true);
                    cardGioiThieuSanPham.setVisible(true);
                    chose = 5;
                }
                break;
            case 6:
                if (chose == -1 || chose != 6) {
                    //dang xuat
                    //hỏi các kiểu
                    Login login = new Login();
                    login.setVisible(true);
                    this.dispose();
                    chose = 6;
                }
                break;
            case 7:
                if (chose == -1 || chose != 7) {
                    hidePage();
                    hideMenu();
                    cardMenubarKhachHang.setVisible(true);
                    cardKhachHang.setVisible(true);
                    chose = 7;
                }
                break;
            case 8:
                if (chose == -1 || chose != 8) {
                    hidePage();
                    hideMenu();
                    cardMenubarThongKe.setVisible(true);
                    cardThongKeDoanhThu.setVisible(true);
                    chose = 8;
                }
                break;
        }

    }

    //mở opacity 
    void showOpacity() {
        opacity.setSize(1220, 670);
    }

    //đóng opacity
    void hideOpacity() {
        opacity.setSize(0, 0);
    }

    /*
        w 300
        h 670
     */
    //mở menu
    void openMenu() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                /*
                try {
                    for (int i = 0; i <= 300; i++) {
                        pnMenu.setSize(i, 670);
                        Thread.sleep(1);
                    }
                } catch (Exception e) {
                }                
                 */

                while (pnMenu.getWidth() < 300) {
                    pnMenu.setSize(pnMenu.getWidth() + 2, 670);
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        System.out.println(ex);
                    }
                }
            }
        }).start();
        showOpacity();
    }

    //đóng menu
    void closeMenu() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                /*
                try {
                    for (int i = 300; i >= 0; i--) {
                        pnMenu.setSize(i, 670);
                        Thread.sleep(1);
                    }
                } catch (Exception e) {
                }                
                 */
                //
                while (pnMenu.getWidth() > 0) {
                    pnMenu.setSize(pnMenu.getWidth() - 2, 670);
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        System.out.println(ex);
                    }
                }
            }
        }).start();
        hideOpacity();
    }

    //ẩn các menu của trang con
    public void hideCardMenubar() {
        cardMenubarGioiThieu.setVisible(false);
        cardMenubarTrangChu.setVisible(false);
        cardMenubarTaiKhoan.setVisible(false);
    }

    /*
        w:1220, h:60
    
    void hideMenuItem(){
        lblHideMenuItem.setSize(1220, 60);
    }
    
    void showMenuItem(){
        lblHideMenuItem.setSize(0, 0);
    }
     */
    void setAnimationHr(JPanel pn, JLabel hr, JLabel item) {
        if (!startThread) {

            Thread t1 = new Thread() {
                public void run() {
                    for (int i = 1; i < item.getWidth(); i++) {
                        hr.setLocation(item.getX(), hr.getY());
                        hr.setSize(i, hr.getHeight());//110
                        try {
                            Thread.sleep(3);
                        } catch (InterruptedException ex) {
                            System.out.println(ex);
                        }
                    }
                }
            };
            t1.start();
            hidePage();
            pn.setVisible(true);

        }
    }

    //điểu chỉnh thanh gạch dưới của menu
    public void setLocationHr(JPanel pn, JLabel hr, JLabel item) {

        setAnimationHr(pn, hr, item);
    }

    //phương thức ẩn các trang
    public void hidePage() {
        this.arrPn = new JPanel[]{
            cardTrangChuTongQuan, cardThongKeDoanhThu, cardTrangChuNoiBat, cardThongKeSanPham,
            cardGioiThieuSanPham, cardGioiThieuThanhVien,
            cardTaiKhoanNhanVien, cardTaiKhoanChucVu,
            cardSanPham, cardHangSanXuat, cardLoai,
            cardHoaDon, cardHoaDonCho,
            cardKhachHang
        };
        for (JPanel pn : this.arrPn) {
            pn.setVisible(false);
        }
    }

    //phương thức ẩn các menu
    public void hideMenu() {
        this.arrPn = new JPanel[]{cardMenubarTrangChu, cardMenubarTaiKhoan,
            cardMenubarGioiThieu, cardMenubarGiamGia, cardMenubarHoaDon,
            cardMenubarSanPham, cardMenubarKhachHang
        };
        for (JPanel pn : this.arrPn) {
            pn.setVisible(false);
        }
    }

    //lấy vị trí của HR
    public boolean getAlignmentHr() {
        if (cardMenubarTrangChu.isVisible()) {
            if (cardTrangChuTongQuan.isVisible()) {
                TrangChuHr.setLocation(40, 43);
            }
            if (cardThongKeDoanhThu.isVisible()) {
                TrangChuHr.setLocation(240, 43);
            }
            if (cardThongKeSanPham.isVisible()) {
                TrangChuHr.setLocation(440, 43);
            }
            if (cardTrangChuNoiBat.isVisible()) {
                TrangChuHr.setLocation(640, 43);
            }
        } else if (cardMenubarTaiKhoan.isVisible()) {
            if (cardTaiKhoanNhanVien.isVisible()) {
                TaiKhoanHr.setLocation(240, 43);
            }
            if (cardTaiKhoanChucVu.isVisible()) {
                TaiKhoanHr.setLocation(440, 43);
            }
        } else if (cardMenubarGioiThieu.isVisible()) {
            if (cardGioiThieuSanPham.isVisible()) {
                GioiThieuHr.setLocation(240, 43);
            }
            if (cardGioiThieuThanhVien.isVisible()) {
                GioiThieuHr.setLocation(440, 43);
            }
        }
        return true;
    }

    public String recordHang() {
        List<HangEntity> list = Hang.selectAll();
        return (row + 1) + " trên " + list.size();
    }
// Hang

    public void initHang() {
        setLocationRelativeTo(null);
        this.fillTableHang();
        this.row = -1;
        this.updateStatusHang();
    }

    public void fillTableHang() {
        DefaultTableModel model = (DefaultTableModel) tblHang.getModel();
        model.setRowCount(0);
        try {
            List<HangEntity> list = Hang.selectAll();

            for (HangEntity h : list) {
                Object[] row = {h.getMaHang(), h.getTenHang()};
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    public void setFormHang(HangEntity h) {
        txtMaHang.setText(h.getMaHang());
        txtTenHang.setText(h.getTenHang());
    }

    public HangEntity getFormHang() {
        HangEntity h = new HangEntity();
        h.setMaHang(txtMaHang.getText());
        h.setTenHang(txtTenHang.getText());
        return h;

    }

    public void clearFormHang() {
        HangEntity h = new HangEntity();
        this.setFormHang(h);
        this.row = -1;
        this.updateStatusHang();
    }

    public void updateStatusHang() {
        boolean edit = (this.row >= 0);
        boolean first = (this.row == 0);
        boolean last = (this.row == tblHang.getRowCount() - 1);
        //Trạng thái form
        txtMaHang.setEditable(edit);
        btnThemHang.setEnabled(edit);
        btnCapNhatHang.setEnabled(edit);
        btnXoaHang.setEnabled(edit);
        //Trạng thái điều hướng
        btnFirstHang.setEnabled(edit && !first);
        btnPrevHang.setEnabled(edit && !first);
        btnNextHang.setEnabled(edit && !last);
        btnLastHang.setEnabled(edit && !last);
    }

    public void editHang() {
        countClick = 0;
        String mahang = (String) tblHang.getValueAt(this.row, 0);
        HangEntity h = Hang.selectById(mahang);
        tblHang.setRowSelectionInterval(this.row, this.row);
        this.setFormHang(h);
        this.updateStatusHang();
        lblrecordHang.setText(recordHang());
        lblrecordLoai.setText(recordLoai());
    }

    public boolean checkHang() {
        if (txtMaHang.getText().equals(" ")) {
            MsgBox.alert(this, " Mã hãng không được để trống!");
            txtMaHang.requestFocus();
            return false;
        }
        if (txtTenHang.getText().equals(" ")) {
            MsgBox.alert(this, "Tên hãng không được để trống!");
            txtTenHang.requestFocus();
            return false;
        }
        return true;
    }

    public void insertHang() {
        HangEntity h = getFormHang();
        try {
            Hang.insert(h);
            this.fillTableHang();
            this.clearFormHang();
            MsgBox.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, e + "Thêm mới thất bại!");
        }
    }

    public void updateHang() {
        HangEntity h = getFormHang();
        try {
            Hang.update(h);
            this.fillTableHang();
            this.clearFormHang();
            MsgBox.alert(this, "Cập nhật thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, e + "Cập nhật thất bại!");
        }
    }

    public void deleteHang() {
        String mah = txtMaHang.getText();
        try {
            Hang.delete(mah);
            this.fillTableHang();
            this.clearFormHang();
            MsgBox.alert(this, "Xóa thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Xóa thất bại!");
        }

    }

    public void firstHang() {
        this.row = 0;
        this.editHang();

    }

    public void nextHang() {
        if (this.row < tblHang.getRowCount() - 1) {
            this.row++;
            this.editHang();

        } else {
            this.firstHang();
        }

    }

    public void prevHang() {
        if (this.row > 0) {
            this.row--;
            this.editHang();

        } else {
            this.lastHang();
        }

    }

    public void lastHang() {
        this.row = tblHang.getRowCount() - 1;
        this.editHang();
    }
//Loại

    public String recordLoai() {
        List<LoaiHangEntity> list = Loai.selectAll();
        return (row + 1) + " trên " + list.size();
    }

    public void initLoai() {
        setLocationRelativeTo(null);
        this.fillTableLoai();
        this.row = -1;
        this.updateStatusLoai();
    }

    public void fillTableLoai() {
        DefaultTableModel model = (DefaultTableModel) tblLoaiHang.getModel();
        model.setRowCount(0);
        try {
            List<LoaiHangEntity> list = Loai.selectAll();
            for (LoaiHangEntity l : list) {
                Object[] row = {l.getMaLH(), l.getTenLH()};
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    public void setFormLoai(LoaiHangEntity l) {
        txtMaLoai.setText(l.getMaLH());
        txtTenLoai.setText(l.getTenLH());
    }

    public LoaiHangEntity getFormLoai() {
        LoaiHangEntity l = new LoaiHangEntity();
        l.setMaLH(txtMaLoai.getText());
        l.setTenLH(txtTenLoai.getText());
        return l;

    }

    public void clearFormLoai() {
        LoaiHangEntity l = new LoaiHangEntity();
        this.setFormLoai(l);
        this.row = -1;
        this.updateStatusLoai();
    }

    public void updateStatusLoai() {
        boolean edit = (this.row >= 0);
        boolean first = (this.row == 0);
        boolean last = (this.row == tblLoaiHang.getRowCount() - 1);
        //Trạng thái form
        txtMaLoai.setEditable(edit);
        btnThemLoai.setEnabled(edit);
        btnCapNhatLoai.setEnabled(edit);
        btnXoaLoai.setEnabled(edit);
        //Trạng thái điều hướng
        btnFirstLoai.setEnabled(edit && !first);
        btnPrevLoai.setEnabled(edit && !first);
        btnNextLoai.setEnabled(edit && !last);
        btnLastLoai.setEnabled(edit && !last);
    }

    public void editLoai() {
        countClick = 0;
        String maloai = (String) tblLoaiHang.getValueAt(this.row, 0);
        LoaiHangEntity l = Loai.selectById(maloai);
        tblLoaiHang.setRowSelectionInterval(this.row, this.row);
        this.setFormLoai(l);
        this.updateStatusLoai();
        lblrecordLoai.setText(recordLoai());
    }

    public boolean checkLoai() {
        if (txtMaLoai.getText().equals(" ")) {
            MsgBox.alert(this, " Mã loại không được để trống!");
            txtMaLoai.requestFocus();
            return false;
        }
        if (txtTenLoai.getText().equals(" ")) {
            MsgBox.alert(this, "Tên loại không được để trống!");
            txtTenLoai.requestFocus();
            return false;
        }
        return true;
    }

    public void insertLoai() {
        LoaiHangEntity l = getFormLoai();
        try {
            Loai.insert(l);
            this.fillTableLoai();
            this.clearFormLoai();
            MsgBox.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, e + "Thêm mới thất bại!");
        }
    }

    public void updateLoai() {
        LoaiHangEntity l = getFormLoai();
        try {
            Loai.update(l);
            this.fillTableLoai();
            this.clearFormLoai();
            MsgBox.alert(this, "Cập nhật thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, e + "Cập nhật thất bại!");
        }
    }

    public void deleteLoai() {
        String mal = txtMaLoai.getText();
        try {
            Loai.delete(mal);
            this.fillTableLoai();
            this.clearFormLoai();
            MsgBox.alert(this, "Xóa thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Xóa thất bại!");
        }

    }

    public void firstLoai() {
        this.row = 0;
        this.editLoai();

    }

    public void nextLoai() {
        if (this.row < tblHang.getRowCount() - 1) {
            this.row++;
            this.editLoai();

        } else {
            this.firstLoai();
        }

    }

    public void prevLoai() {
        if (this.row > 0) {
            this.row--;
            this.editLoai();

        } else {
            this.lastLoai();
        }

    }

    public void lastLoai() {
        this.row = tblHang.getRowCount() - 1;
        this.editLoai();
    }

    // Sản phẩm
    public String recordSanPham() {
        List<SanPhamEntity> list = SanPham.selectAll();
        return (row + 1) + " trên " + list.size();
    }

    public void initSanPham() {
        setLocationRelativeTo(null);
        this.fillTableSanPham();
        this.row = -1;
        this.updateStatusSanPham();
    }

    public void fillTableSanPham() {
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        try {
            List<SanPhamEntity> list = SanPham.selectAll();
            for (SanPhamEntity sp : list) {
                Object[] row = {sp.getMaSP(), sp.getTenSP(), sp.getMaLH(), sp.getMaHang(), sp.getGiaNhap(), sp.getGiaBan(), sp.getNgayNhap(), sp.getHinh(), sp.getMoTa()};
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    public void setFormSanPham(SanPhamEntity sp) {
        txtMaSP.setText(sp.getMaSP());
        txtTenSP.setText(sp.getTenSP());
        cboMaLoai.setSelectedItem(sp.getMaLH());
        cboMaHang.setSelectedItem(sp.getMaHang());
        txtGiaNhapSP.setText(String.valueOf(sp.getGiaNhap()));
        txtGiaBanSP.setText(String.valueOf(sp.getGiaBan()));
        txtNgayNhapSP.setText(String.valueOf(sp.getNgayNhap()));
        if (sp.getHinh() != null) {
            File file = new File("src\\com\\images\\" + sp.getHinh());
            try {
                Image img = ImageIO.read(file);
                lblAnh.setText("");
                int w = lblAnh.getWidth();
                int h = lblAnh.getHeight();
                lblAnh.setIcon(new ImageIcon(img.getScaledInstance(w, h, 0)));
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            lblAnh.setText("Không có");
            lblAnh.setIcon(null);
        }
        txtMoTaSP.setText(sp.getMoTa());
    }

    public SanPhamEntity getFormSanPham() {
        String loai = (String) cboMaLoai.getSelectedItem();
        SanPhamEntity sp = new SanPhamEntity();
        sp.setMaSP(txtMaSP.getText());
        sp.setTenSP(txtTenSP.getText());
        sp.setMaLH(loai);
        sp.setMaHang((String) cboMaHang.getSelectedItem());
        sp.setGiaNhap(Float.parseFloat(txtGiaNhapSP.getText()));
        sp.setGiaBan(Float.parseFloat(txtGiaBanSP.getText()));
        sp.setNgayNhap(XDate.toDate(txtNgayNhapSP.getText(), "yyyy-MM-dd"));
        sp.setHinh(txtMaSP.getText() + ".PNG");
        sp.setMoTa(txtMoTaSP.getText());
        return sp;

    }

    public void clearFormSanPham() {
        SanPhamEntity sp = new SanPhamEntity();
        this.setFormSanPham(sp);
        this.row = -1;
        this.updateStatusSanPham();
    }

    public void updateStatusSanPham() {
        boolean edit = (this.row >= 0);
        boolean first = (this.row == 0);
        boolean last = (this.row == tblSanPham.getRowCount() - 1);
        //Trạng thái form
        txtMaSP.setEditable(edit);
        btnThemSP.setEnabled(edit);
        btnCapNhatSP.setEnabled(edit);
        btnXoaSP.setEnabled(edit);
        //Trạng thái điều hướng
        btnFirstSP.setEnabled(edit && !first);
        btnPrevSP.setEnabled(edit && !first);
        btnNextSP.setEnabled(edit && !last);
        btnLastSP.setEnabled(edit && !last);
    }

    public void editSanPham() {
        countClick = 0;
        String masp = (String) tblSanPham.getValueAt(this.row, 0);
        SanPhamEntity sp = SanPham.selectById(masp);
        tblSanPham.setRowSelectionInterval(this.row, this.row);
        this.setFormSanPham(sp);
        this.updateStatusSanPham();
        lblrecordSP.setText(recordSanPham());
    }

    public void insertSanPham() {
        SanPhamEntity sp = getFormSanPham();
        try {
            SanPham.insert(sp);
            this.fillTableSanPham();
            this.clearFormSanPham();
            MsgBox.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, e + "Thêm mới thất bại!");
        }
    }

    public void updateSanPham() {
        SanPhamEntity sp = getFormSanPham();
        try {
            SanPham.update(sp);
            this.fillTableSanPham();
            this.clearFormSanPham();
            MsgBox.alert(this, "Cập nhật thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, e + "Cập nhật thất bại!");
        }
    }

    public void deleteSanPham() {
        String masp = txtMaSP.getText();
        try {
            SanPham.delete(masp);
            this.fillTableSanPham();
            this.clearFormSanPham();
            MsgBox.alert(this, "Xóa thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Xóa thất bại!");
        }

    }

    public void firstSanPham() {
        this.row = 0;
        this.editSanPham();

    }

    public void nextSanPham() {
        if (this.row < tblSanPham.getRowCount() - 1) {
            this.row++;
            this.editSanPham();

        } else {
            this.firstSanPham();
        }

    }

    public void prevSanPham() {
        if (this.row > 0) {
            this.row--;
            this.editSanPham();

        } else {
            this.lastSanPham();
        }

    }

    public void lastSanPham() {
        this.row = tblSanPham.getRowCount() - 1;
        this.editSanPham();
    }

    public void fillComboxLoai() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboMaLoai.getModel();
        model.removeAllElements();
        List<LoaiHangEntity> list = Loai.selectAll();
        for (LoaiHangEntity l : list) {
            model.addElement(String.valueOf(l.getMaLH()));
        }
    }

    public void fillComboxHang() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboMaHang.getModel();
        model.removeAllElements();
        List<HangEntity> list = Hang.selectAll();
        for (HangEntity h : list) {
            model.addElement(String.valueOf(h.getMaHang()));
        }
    }

    public void ChonAnh() {
        try {
            f.showOpenDialog(null);
            file = f.getSelectedFile();
            Image img = ImageIO.read(file);
            anh = file.getName();
            int width = lblAnh.getWidth();
            int height = lblAnh.getHeight();
            lblAnh.setIcon(new ImageIcon(img.getScaledInstance(width, height, 0)));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error");
        }

    }

    public void choseImage() {
        JFileChooser chooser = new JFileChooser("src\\com\\edusys\\anh"); //System.out.println(nameImage);
        chooser.showOpenDialog(null);
        File file = chooser.getSelectedFile();
        try {
            this.imageSrc = file.getName();
            Image img = ImageIO.read(file);
            lblAnh.setText("");
            int width = lblAnh.getWidth();
            int height = lblAnh.getHeight();
            lblAnh.setIcon(new ImageIcon(img.getScaledInstance(width, height, 0)));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Thao tác đã bị hủy!");
            return;
        }
        //clone ảnh
        try {
            this.image = ImageIO.read(file);
            int wIMG = image.getWidth();
            int HIMG = image.getHeight();
            //image = ImageIO.read(/*getClass().getClassLoader().*/getResource("C:\\Users\\dangt\\OneDrive\\Máy tính\\nganmayy.png"));
            cloneImage = new BufferedImage(wIMG, HIMG, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = cloneImage.createGraphics();

            g.drawImage(image, 0, 0, null);
            g.dispose();

            imageSrc = txtMaSP.getText() + ".PNG";
            try {
                ImageIO.write(cloneImage, "PNG", new File("src\\com\\images\\" + imageSrc));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ôi không!\nHình ảnh không tương thích!");
                return;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ôi không!\nHình ảnh không tương thích!");
            return;
        }
    }

    private static void copyFolder(File sourceFolder, File targetFolder)
            throws IOException {
        // Check neu sourceFolder la mot thu muc hoac file
        // neu sourceFolder la file thi copy den thu muc dich
        // copy file tu thuc muc nguon den thu muc dich
        InputStream in = new FileInputStream(sourceFolder);
        OutputStream out = new FileOutputStream(targetFolder);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }
        System.out.println("File da duoc copy " + targetFolder);
        in.close();
        out.close();

    }
public boolean checkSP(){
    if(txtMaSP.getText().equals("")){
        MsgBox.alert(this,"Mã sản phẩm không được để trống!");
        txtMaSP.requestFocus();
        return false;
    }
    if(txtTenSP.getText().equals(" ")){
        MsgBox.alert(this,"Tên sản phẩm không được để trống!");
        txtTenSP.requestFocus();
        return false;
    }
    if(txtGiaNhapSP.getText().equals("")){
        MsgBox.alert(this,"Giá nhập không được để trống!");
        txtGiaNhapSP.requestFocus();
        return false;
    }else {
            try {
                double gianhap = Double.valueOf(txtGiaNhapSP.getText());
                if (gianhap <= 0) {
                    MsgBox.alert(this, "Giá nhập phải lớn hơn 0");
                    return false;
                }
            } catch (Exception ex) {
                MsgBox.alert(this, "Vui lòng không nhập giá nhập là ký tự hoặc chữ");
                return false;
            }
        }
    if(txtGiaBanSP.getText().equals("")){
        MsgBox.alert(this,"Giá bán không được để trống!");
        txtGiaBanSP.requestFocus();
        return false;
    }else {
            try {
                double giaban = Double.valueOf(txtGiaBanSP.getText());
                if (giaban <= 0) {
                    MsgBox.alert(this, "Giá bán phải lớn hơn 0");
                    return false;
                }
            } catch (Exception ex) {
                MsgBox.alert(this, "Vui lòng không nhập giá bán là ký tự hoặc chữ");
                return false;
            }
        }
    if(txtNgayNhapSP.getText().equals("")){
        MsgBox.alert(this,"Ngày nhập không được để trống!");
        txtNgayNhapSP.requestFocus();
        return false;
    }else {
            try {
                formater.setLenient(false);
                formater.parse(txtNgayNhapSP.getText());
            } catch (Exception ex) {
                MsgBox.alert(this, "Vui lòng nhập ngày sinh đúng định dạng năm - tháng - ngày!");
                txtNgayNhapSP.requestFocus();
                return false;
            }
        }
    if(cboMaHang.getSelectedItem().equals("")){
        MsgBox.alert(this, "Vui lòng chọn mã hãng sản xuất!");
        return false;
    }
    if(cboMaLoai.getSelectedItem().equals("")){
        MsgBox.alert(this, "Vui lòng chọn mã loại hàng!");
        return false;
    }
    return true;
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        pnMenu = new javax.swing.JPanel();
        header2 = new com.frame.Header();
        jSeparator2 = new javax.swing.JSeparator();
        btnTrangChu = new com.swing.Button();
        btnTaiKhoan = new com.swing.Button();
        btnSanPham = new com.swing.Button();
        btnHoaDon = new com.swing.Button();
        btnGiamGia = new com.swing.Button();
        btnGioiThieu = new com.swing.Button();
        btnDangXuat = new com.swing.Button();
        btnThongKe = new com.swing.Button();
        opacity = new javax.swing.JLabel();
        jplTitle = new javax.swing.JPanel();
        jplState = new javax.swing.JPanel();
        jlbState = new javax.swing.JLabel();
        jplLose = new javax.swing.JPanel();
        jlbClose = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jplMenubar = new javax.swing.JPanel();
        btnMenu = new com.swing.Button();
        cardMenubar = new javax.swing.JPanel();
        cardMenubarTrangChu = new javax.swing.JPanel();
        TrangChuTittle2 = new javax.swing.JLabel();
        TrangChuHr = new javax.swing.JLabel();
        cardMenubarTaiKhoan = new javax.swing.JPanel();
        TaiKhoantittle2 = new javax.swing.JLabel();
        TaiKhoantittle3 = new javax.swing.JLabel();
        TaiKhoanHr = new javax.swing.JLabel();
        TaiKhoantittle4 = new javax.swing.JLabel();
        cardMenubarSanPham = new javax.swing.JPanel();
        SanPhamTittle1 = new javax.swing.JLabel();
        SanPhamTittle3 = new javax.swing.JLabel();
        SanPhamHr = new javax.swing.JLabel();
        SanPhamTittle2 = new javax.swing.JLabel();
        cardMenubarHoaDon = new javax.swing.JPanel();
        HoaDonTittle1 = new javax.swing.JLabel();
        HoaDonTittle2 = new javax.swing.JLabel();
        HoaDonHr = new javax.swing.JLabel();
        cardMenubarGiamGia = new javax.swing.JPanel();
        GiamGiaTittle1 = new javax.swing.JLabel();
        GiamGiaHr = new javax.swing.JLabel();
        cardMenubarGioiThieu = new javax.swing.JPanel();
        GioiThieutittle1 = new javax.swing.JLabel();
        GioiThieutittle2 = new javax.swing.JLabel();
        GioiThieuHr = new javax.swing.JLabel();
        cardMenubarKhachHang = new javax.swing.JPanel();
        KhachHangTittle1 = new javax.swing.JLabel();
        KhachHangHr = new javax.swing.JLabel();
        cardMenubarThongKe = new javax.swing.JPanel();
        ThongKeTittle1 = new javax.swing.JLabel();
        ThongKeTittle2 = new javax.swing.JLabel();
        ThongKeHr = new javax.swing.JLabel();
        jplContainer = new javax.swing.JPanel();
        cardTrangChu = new javax.swing.JPanel();
        cardTrangChuTongQuan = new com.swing.PanelRound();
        panelRound1 = new com.swing.PanelRound();
        panelRound5 = new com.swing.PanelRound();
        panelRound6 = new com.swing.PanelRound();
        panelRound7 = new com.swing.PanelRound();
        jLabel12 = new javax.swing.JLabel();
        panelRound4 = new com.swing.PanelRound();
        jLabel8 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lblMessage4 = new javax.swing.JLabel();
        lblMessage1 = new javax.swing.JLabel();
        lblMessage2 = new javax.swing.JLabel();
        lblMessage3 = new javax.swing.JLabel();
        panelRound2 = new com.swing.PanelRound();
        panelRound3 = new com.swing.PanelRound();
        cardThongKeDoanhThu = new com.swing.PanelRound();
        jLabel59 = new javax.swing.JLabel();
        cardThongKeSanPham = new com.swing.PanelRound();
        jLabel61 = new javax.swing.JLabel();
        cardTrangChuNoiBat = new com.swing.PanelRound();
        jLabel27 = new javax.swing.JLabel();
        cardTaiKhoanChucVu = new com.swing.PanelRound();
        jLabel90 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jLabel92 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        button39 = new com.swing.Button();
        button40 = new com.swing.Button();
        button41 = new com.swing.Button();
        jPanel20 = new javax.swing.JPanel();
        combobox9 = new com.swing.Combobox();
        jLabel93 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        editButton16 = new com.swing.EditButton();
        editButton17 = new com.swing.EditButton();
        jLabel94 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        button42 = new com.swing.Button();
        jPanel22 = new javax.swing.JPanel();
        button43 = new com.swing.Button();
        button44 = new com.swing.Button();
        button45 = new com.swing.Button();
        button46 = new com.swing.Button();
        jLabel95 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblKhachHang5 = new javax.swing.JTable();
        jLabel98 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        cardTaiKhoanNhanVien = new com.swing.PanelRound();
        jPanel14 = new javax.swing.JPanel();
        button31 = new com.swing.Button();
        button33 = new com.swing.Button();
        button34 = new com.swing.Button();
        jLabel82 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        button51 = new com.swing.Button();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKhachHang4 = new javax.swing.JTable();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        button35 = new com.swing.Button();
        button36 = new com.swing.Button();
        button37 = new com.swing.Button();
        jPanel16 = new javax.swing.JPanel();
        combobox6 = new com.swing.Combobox();
        jLabel30 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        editButton14 = new com.swing.EditButton();
        editButton15 = new com.swing.EditButton();
        jLabel32 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        button38 = new com.swing.Button();
        jLabel33 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        combobox7 = new com.swing.Combobox();
        jLabel40 = new javax.swing.JLabel();
        switchButton1 = new com.hicode.switchbutton.SwitchButton();
        cardSanPham = new com.swing.PanelRound();
        cardHangSanXuat1 = new com.swing.PanelRound();
        cardLoai2 = new com.swing.PanelRound();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        btnNextSP = new com.swing.Button();
        btnFirstSP = new com.swing.Button();
        btnPrevSP = new com.swing.Button();
        btnLastSP = new com.swing.Button();
        jLabel79 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        cboMaLoai = new com.swing.Combobox();
        cboMaHang = new com.swing.Combobox();
        lblAnh = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        lblrecordSP = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        btnLamMoiSP = new com.swing.Button();
        btnCapNhatSP = new com.swing.Button();
        btnXoaSP = new com.swing.Button();
        btnThemSP = new com.swing.Button();
        txtTenSP = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        txtNgayNhapSP = new javax.swing.JTextField();
        jLabel106 = new javax.swing.JLabel();
        txtGiaNhapSP = new javax.swing.JTextField();
        jLabel107 = new javax.swing.JLabel();
        txtGiaBanSP = new javax.swing.JTextField();
        jLabel108 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        txtMoTaSP = new javax.swing.JTextArea();
        jLabel109 = new javax.swing.JLabel();
        cardHangSanXuat = new com.swing.PanelRound();
        cardLoai1 = new com.swing.PanelRound();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblHang = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        btnNextHang = new com.swing.Button();
        btnFirstHang = new com.swing.Button();
        btnPrevHang = new com.swing.Button();
        btnLastHang = new com.swing.Button();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        btnLamMoiHang = new com.swing.Button();
        btnCapNhatHang = new com.swing.Button();
        btnXoaHang = new com.swing.Button();
        jPanel7 = new javax.swing.JPanel();
        combobox2 = new com.swing.Combobox();
        jLabel102 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        editButton10 = new com.swing.EditButton();
        editButton11 = new com.swing.EditButton();
        jLabel103 = new javax.swing.JLabel();
        jSeparator15 = new javax.swing.JSeparator();
        jSeparator16 = new javax.swing.JSeparator();
        jSeparator17 = new javax.swing.JSeparator();
        btnThemHang = new com.swing.Button();
        jLabel73 = new javax.swing.JLabel();
        lblrecordHang = new javax.swing.JLabel();
        txtMaHang = new javax.swing.JTextField();
        jLabel110 = new javax.swing.JLabel();
        txtTenHang = new javax.swing.JTextField();
        jLabel111 = new javax.swing.JLabel();
        cardLoai = new com.swing.PanelRound();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblLoaiHang = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        btnNextLoai = new com.swing.Button();
        btnFirstLoai = new com.swing.Button();
        btnPrevLoai = new com.swing.Button();
        btnLastLoai = new com.swing.Button();
        jLabel69 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jLabel105 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        btnLamMoiLoai = new com.swing.Button();
        btnCapNhatLoai = new com.swing.Button();
        btnXoaLoai = new com.swing.Button();
        jPanel24 = new javax.swing.JPanel();
        combobox8 = new com.swing.Combobox();
        jLabel91 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        editButton18 = new com.swing.EditButton();
        editButton19 = new com.swing.EditButton();
        jLabel101 = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        jSeparator14 = new javax.swing.JSeparator();
        btnThemLoai = new com.swing.Button();
        jLabel68 = new javax.swing.JLabel();
        lblrecordLoai = new javax.swing.JLabel();
        txtMaLoai = new javax.swing.JTextField();
        jLabel112 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        txtTenLoai = new javax.swing.JTextField();
        cardHoaDon = new com.swing.PanelRound();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        cardHoaDonCho = new com.swing.PanelRound();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        cardGiamGia = new com.swing.PanelRound();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        cardGioiThieuSanPham = new com.swing.PanelRound();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cardGioiThieuThanhVien = new com.swing.PanelRound();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        cardKhachHang = new com.swing.PanelRound();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        jLabel65 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        button2 = new com.swing.Button();
        button5 = new com.swing.Button();
        button6 = new com.swing.Button();
        button7 = new com.swing.Button();
        jLabel60 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        button3 = new com.swing.Button();
        button4 = new com.swing.Button();
        button8 = new com.swing.Button();
        jPanel4 = new javax.swing.JPanel();
        combobox1 = new com.swing.Combobox();
        jLabel29 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        editButton8 = new com.swing.EditButton();
        editButton9 = new com.swing.EditButton();
        jLabel31 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        button30 = new com.swing.Button();
        jLabel62 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jLabel89 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnMenu.setBackground(new java.awt.Color(255, 255, 255));
        pnMenu.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(0, 0, 0)));

        btnTrangChu.setForeground(new java.awt.Color(0, 153, 51));
        btnTrangChu.setText("Trang chủ");
        btnTrangChu.setEffectColor(new java.awt.Color(0, 153, 255));
        btnTrangChu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnTrangChu.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnTrangChu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnTrangChuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnTrangChuMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnTrangChuMouseReleased(evt);
            }
        });
        btnTrangChu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrangChuActionPerformed(evt);
            }
        });

        btnTaiKhoan.setText("Tài khoản");
        btnTaiKhoan.setEffectColor(new java.awt.Color(0, 153, 255));
        btnTaiKhoan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnTaiKhoan.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnTaiKhoanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnTaiKhoanMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnTaiKhoanMouseReleased(evt);
            }
        });
        btnTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaiKhoanActionPerformed(evt);
            }
        });

        btnSanPham.setText("Sản phẩm");
        btnSanPham.setEffectColor(new java.awt.Color(0, 153, 255));
        btnSanPham.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSanPham.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSanPhamMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSanPhamMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnSanPhamMouseReleased(evt);
            }
        });
        btnSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSanPhamActionPerformed(evt);
            }
        });

        btnHoaDon.setText("Hóa đơn");
        btnHoaDon.setEffectColor(new java.awt.Color(0, 153, 255));
        btnHoaDon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnHoaDon.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHoaDonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHoaDonMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnHoaDonMouseReleased(evt);
            }
        });
        btnHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoaDonActionPerformed(evt);
            }
        });

        btnGiamGia.setText("Giảm giá");
        btnGiamGia.setEffectColor(new java.awt.Color(0, 153, 255));
        btnGiamGia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnGiamGia.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnGiamGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGiamGiaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGiamGiaMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnGiamGiaMouseReleased(evt);
            }
        });
        btnGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGiamGiaActionPerformed(evt);
            }
        });

        btnGioiThieu.setText("Giới thiệu");
        btnGioiThieu.setEffectColor(new java.awt.Color(0, 153, 255));
        btnGioiThieu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnGioiThieu.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnGioiThieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGioiThieuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGioiThieuMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnGioiThieuMouseReleased(evt);
            }
        });
        btnGioiThieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGioiThieuActionPerformed(evt);
            }
        });

        btnDangXuat.setText("Đăng xuất");
        btnDangXuat.setEffectColor(new java.awt.Color(255, 51, 51));
        btnDangXuat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDangXuat.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnDangXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDangXuatMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDangXuatMouseExited(evt);
            }
        });
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });

        btnThongKe.setText("Thống kê");
        btnThongKe.setEffectColor(new java.awt.Color(0, 153, 255));
        btnThongKe.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThongKe.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnThongKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnThongKeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnThongKeMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnThongKeMouseReleased(evt);
            }
        });
        btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnMenuLayout = new javax.swing.GroupLayout(pnMenu);
        pnMenu.setLayout(pnMenuLayout);
        pnMenuLayout.setHorizontalGroup(
            pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMenuLayout.createSequentialGroup()
                .addComponent(header2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnMenuLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnTrangChu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGioiThieu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDangXuat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTaiKhoan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSanPham, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnHoaDon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGiamGia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThongKe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        pnMenuLayout.setVerticalGroup(
            pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMenuLayout.createSequentialGroup()
                .addComponent(header2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btnTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btnSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGioiThieu, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        jPanel1.add(pnMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 0, 670));

        opacity.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/opacity-main.png"))); // NOI18N
        opacity.setText("jLabel28");
        opacity.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                opacityMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                opacityMousePressed(evt);
            }
        });
        jPanel1.add(opacity, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 0, 0));

        jplTitle.setBackground(new java.awt.Color(154, 213, 224));
        jplTitle.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jplState.setBackground(new java.awt.Color(117, 198, 213));

        jlbState.setFont(new java.awt.Font("Baskerville Old Face", 1, 48)); // NOI18N
        jlbState.setForeground(new java.awt.Color(255, 255, 255));
        jlbState.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbState.setText("-");
        jlbState.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbStateMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlbStateMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlbStateMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jplStateLayout = new javax.swing.GroupLayout(jplState);
        jplState.setLayout(jplStateLayout);
        jplStateLayout.setHorizontalGroup(
            jplStateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbState, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );
        jplStateLayout.setVerticalGroup(
            jplStateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jplStateLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jlbState, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jplTitle.add(jplState, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 0, -1, -1));

        jplLose.setBackground(new java.awt.Color(117, 198, 213));
        jplLose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jplLoseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jplLoseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jplLoseMouseExited(evt);
            }
        });
        jplLose.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlbClose.setFont(new java.awt.Font("Source Sans Pro Black", 1, 18)); // NOI18N
        jlbClose.setForeground(new java.awt.Color(255, 255, 255));
        jlbClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbClose.setText(" X");
        jlbClose.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jlbCloseFocusGained(evt);
            }
        });
        jlbClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbCloseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlbCloseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlbCloseMouseExited(evt);
            }
        });
        jplLose.add(jlbClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 30));

        jplTitle.add(jplLose, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 0, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DAVISY");
        jplTitle.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 30));

        jPanel1.add(jplTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jplMenubar.setBackground(new java.awt.Color(255, 255, 255));
        jplMenubar.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        jplMenubar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/menu_first.png"))); // NOI18N
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });
        jplMenubar.add(btnMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1, 80, 50));

        cardMenubar.setBackground(new java.awt.Color(255, 255, 255));
        cardMenubar.setLayout(new java.awt.CardLayout());

        cardMenubarTrangChu.setBackground(new java.awt.Color(255, 255, 255));
        cardMenubarTrangChu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TrangChuTittle2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TrangChuTittle2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TrangChuTittle2.setText("Tổng quan");
        cardMenubarTrangChu.add(TrangChuTittle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 110, 30));

        TrangChuHr.setBackground(new java.awt.Color(0, 153, 0));
        TrangChuHr.setOpaque(true);
        cardMenubarTrangChu.add(TrangChuHr, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 110, 5));

        cardMenubar.add(cardMenubarTrangChu, "card2");

        cardMenubarTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));
        cardMenubarTaiKhoan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TaiKhoantittle2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TaiKhoantittle2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TaiKhoantittle2.setText("Nhân viên");
        TaiKhoantittle2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TaiKhoantittle2MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TaiKhoantittle2MousePressed(evt);
            }
        });
        cardMenubarTaiKhoan.add(TaiKhoantittle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 110, 30));

        TaiKhoantittle3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TaiKhoantittle3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TaiKhoantittle3.setText("Chức vụ");
        TaiKhoantittle3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TaiKhoantittle3MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TaiKhoantittle3MousePressed(evt);
            }
        });
        cardMenubarTaiKhoan.add(TaiKhoantittle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, 110, 30));

        TaiKhoanHr.setBackground(new java.awt.Color(0, 153, 0));
        TaiKhoanHr.setOpaque(true);
        cardMenubarTaiKhoan.add(TaiKhoanHr, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 43, 110, 5));

        TaiKhoantittle4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TaiKhoantittle4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TaiKhoantittle4.setText("Khách Hàng");
        TaiKhoantittle4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TaiKhoantittle4MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TaiKhoantittle4MousePressed(evt);
            }
        });
        cardMenubarTaiKhoan.add(TaiKhoantittle4, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 110, 30));

        cardMenubar.add(cardMenubarTaiKhoan, "card2");

        cardMenubarSanPham.setBackground(new java.awt.Color(255, 255, 255));
        cardMenubarSanPham.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SanPhamTittle1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        SanPhamTittle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SanPhamTittle1.setText("Sản phẩm");
        SanPhamTittle1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                SanPhamTittle1MousePressed(evt);
            }
        });
        cardMenubarSanPham.add(SanPhamTittle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 110, 30));

        SanPhamTittle3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        SanPhamTittle3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SanPhamTittle3.setText("Loại");
        SanPhamTittle3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                SanPhamTittle3MousePressed(evt);
            }
        });
        cardMenubarSanPham.add(SanPhamTittle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, 110, 30));

        SanPhamHr.setBackground(new java.awt.Color(0, 153, 0));
        SanPhamHr.setOpaque(true);
        cardMenubarSanPham.add(SanPhamHr, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 43, 110, 5));

        SanPhamTittle2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        SanPhamTittle2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SanPhamTittle2.setText("Hãng");
        SanPhamTittle2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                SanPhamTittle2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                SanPhamTittle2MouseReleased(evt);
            }
        });
        cardMenubarSanPham.add(SanPhamTittle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 110, 30));

        cardMenubar.add(cardMenubarSanPham, "card2");

        cardMenubarHoaDon.setBackground(new java.awt.Color(255, 255, 255));
        cardMenubarHoaDon.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        HoaDonTittle1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        HoaDonTittle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        HoaDonTittle1.setText("Hóa đơn");
        HoaDonTittle1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                HoaDonTittle1MousePressed(evt);
            }
        });
        cardMenubarHoaDon.add(HoaDonTittle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 110, 30));

        HoaDonTittle2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        HoaDonTittle2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        HoaDonTittle2.setText("Hóa đơn chờ");
        HoaDonTittle2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                HoaDonTittle2MousePressed(evt);
            }
        });
        cardMenubarHoaDon.add(HoaDonTittle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 110, 30));

        HoaDonHr.setBackground(new java.awt.Color(0, 153, 0));
        HoaDonHr.setOpaque(true);
        cardMenubarHoaDon.add(HoaDonHr, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 43, 110, 5));

        cardMenubar.add(cardMenubarHoaDon, "card2");

        cardMenubarGiamGia.setBackground(new java.awt.Color(255, 255, 255));
        cardMenubarGiamGia.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        GiamGiaTittle1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        GiamGiaTittle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        GiamGiaTittle1.setText("Voucher");
        GiamGiaTittle1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                GiamGiaTittle1MousePressed(evt);
            }
        });
        cardMenubarGiamGia.add(GiamGiaTittle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 110, 30));

        GiamGiaHr.setBackground(new java.awt.Color(0, 153, 0));
        GiamGiaHr.setOpaque(true);
        cardMenubarGiamGia.add(GiamGiaHr, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 43, 110, 5));

        cardMenubar.add(cardMenubarGiamGia, "card2");

        cardMenubarGioiThieu.setBackground(new java.awt.Color(255, 255, 255));
        cardMenubarGioiThieu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        GioiThieutittle1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        GioiThieutittle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        GioiThieutittle1.setText("Về sản phẩm");
        GioiThieutittle1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                GioiThieutittle1MousePressed(evt);
            }
        });
        cardMenubarGioiThieu.add(GioiThieutittle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 110, 30));

        GioiThieutittle2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        GioiThieutittle2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        GioiThieutittle2.setText("Về chúng tôi");
        GioiThieutittle2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                GioiThieutittle2MousePressed(evt);
            }
        });
        cardMenubarGioiThieu.add(GioiThieutittle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 110, 30));

        GioiThieuHr.setBackground(new java.awt.Color(0, 153, 0));
        GioiThieuHr.setOpaque(true);
        cardMenubarGioiThieu.add(GioiThieuHr, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 43, 110, 5));

        cardMenubar.add(cardMenubarGioiThieu, "card2");

        cardMenubarKhachHang.setBackground(new java.awt.Color(255, 255, 255));
        cardMenubarKhachHang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        KhachHangTittle1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        KhachHangTittle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        KhachHangTittle1.setText("Khách hàng");
        KhachHangTittle1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                KhachHangTittle1MousePressed(evt);
            }
        });
        cardMenubarKhachHang.add(KhachHangTittle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 110, 30));

        KhachHangHr.setBackground(new java.awt.Color(0, 153, 0));
        KhachHangHr.setOpaque(true);
        cardMenubarKhachHang.add(KhachHangHr, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 43, 110, 5));

        cardMenubar.add(cardMenubarKhachHang, "card2");

        cardMenubarThongKe.setBackground(new java.awt.Color(255, 255, 255));
        cardMenubarThongKe.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ThongKeTittle1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ThongKeTittle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ThongKeTittle1.setText("Doanh thu");
        ThongKeTittle1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ThongKeTittle1MousePressed(evt);
            }
        });
        cardMenubarThongKe.add(ThongKeTittle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 110, 30));

        ThongKeTittle2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ThongKeTittle2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ThongKeTittle2.setText("Sản phẩm bán chạy");
        ThongKeTittle2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ThongKeTittle2MousePressed(evt);
            }
        });
        cardMenubarThongKe.add(ThongKeTittle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 160, 30));

        ThongKeHr.setBackground(new java.awt.Color(0, 153, 0));
        ThongKeHr.setOpaque(true);
        cardMenubarThongKe.add(ThongKeHr, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 43, 110, 5));

        cardMenubar.add(cardMenubarThongKe, "card2");

        jplMenubar.add(cardMenubar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 1140, 50));

        jPanel1.add(jplMenubar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1220, -1));

        jplContainer.setLayout(new java.awt.CardLayout());

        cardTrangChu.setBackground(new java.awt.Color(204, 204, 204));
        cardTrangChu.setLayout(new java.awt.CardLayout());

        cardTrangChuTongQuan.setBackground(new java.awt.Color(255, 255, 255));
        cardTrangChuTongQuan.setRoundBottomLeft(30);
        cardTrangChuTongQuan.setRoundBottomRight(30);
        cardTrangChuTongQuan.setRoundTopLeft(30);
        cardTrangChuTongQuan.setRoundTopRight(20);
        cardTrangChuTongQuan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound1.setBackground(new java.awt.Color(204, 255, 204));
        panelRound1.setRoundBottomRight(50);
        panelRound1.setRoundTopLeft(50);

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );

        cardTrangChuTongQuan.add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 230, 140));

        panelRound5.setBackground(new java.awt.Color(255, 204, 204));
        panelRound5.setRoundBottomRight(50);
        panelRound5.setRoundTopLeft(50);

        javax.swing.GroupLayout panelRound5Layout = new javax.swing.GroupLayout(panelRound5);
        panelRound5.setLayout(panelRound5Layout);
        panelRound5Layout.setHorizontalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
        );
        panelRound5Layout.setVerticalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );

        cardTrangChuTongQuan.add(panelRound5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, 230, 140));

        panelRound6.setBackground(new java.awt.Color(204, 204, 255));
        panelRound6.setRoundBottomRight(50);
        panelRound6.setRoundTopLeft(50);

        javax.swing.GroupLayout panelRound6Layout = new javax.swing.GroupLayout(panelRound6);
        panelRound6.setLayout(panelRound6Layout);
        panelRound6Layout.setHorizontalGroup(
            panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
        );
        panelRound6Layout.setVerticalGroup(
            panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );

        cardTrangChuTongQuan.add(panelRound6, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 70, 230, 140));

        panelRound7.setBackground(new java.awt.Color(224, 223, 223));
        panelRound7.setRoundBottomLeft(30);
        panelRound7.setRoundBottomRight(30);
        panelRound7.setRoundTopLeft(30);
        panelRound7.setRoundTopRight(30);
        panelRound7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/bell.png"))); // NOI18N
        panelRound7.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 40, 40));

        panelRound4.setBackground(new java.awt.Color(204, 153, 255));
        panelRound4.setRoundBottomLeft(20);
        panelRound4.setRoundBottomRight(20);
        panelRound4.setRoundTopLeft(20);
        panelRound4.setRoundTopRight(20);
        panelRound4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("2 ĐạtVila, 3jack");
        panelRound4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 190, 50));

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Tổng doanh thu");
        panelRound4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, 40));

        panelRound7.add(panelRound4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 190, 130));
        panelRound7.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 42, 230, 30));

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 51, 51));
        jLabel17.setText("Thông báo");
        panelRound7.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 140, 40));

        jLabel21.setBackground(new java.awt.Color(224, 223, 223));
        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel21.setText("Các mục khác");
        jLabel21.setOpaque(true);
        panelRound7.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 190, 40));

        lblMessage4.setBackground(new java.awt.Color(255, 255, 255));
        lblMessage4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblMessage4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMessage4.setOpaque(true);
        panelRound7.add(lblMessage4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 190, 40));

        lblMessage1.setBackground(new java.awt.Color(255, 255, 255));
        lblMessage1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblMessage1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMessage1.setOpaque(true);
        panelRound7.add(lblMessage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 190, 40));

        lblMessage2.setBackground(new java.awt.Color(255, 255, 255));
        lblMessage2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblMessage2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMessage2.setOpaque(true);
        panelRound7.add(lblMessage2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 190, 40));

        lblMessage3.setBackground(new java.awt.Color(255, 255, 255));
        lblMessage3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblMessage3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMessage3.setOpaque(true);
        panelRound7.add(lblMessage3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 190, 40));

        cardTrangChuTongQuan.add(panelRound7, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 30, 230, 480));

        panelRound2.setBackground(new java.awt.Color(255, 204, 255));
        panelRound2.setRoundBottomLeft(30);
        panelRound2.setRoundBottomRight(30);
        panelRound2.setRoundTopLeft(30);
        panelRound2.setRoundTopRight(30);

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 510, Short.MAX_VALUE)
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );

        cardTrangChuTongQuan.add(panelRound2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 510, 240));

        panelRound3.setBackground(new java.awt.Color(204, 255, 204));
        panelRound3.setRoundBottomLeft(30);
        panelRound3.setRoundBottomRight(30);
        panelRound3.setRoundTopLeft(30);
        panelRound3.setRoundTopRight(30);

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );

        cardTrangChuTongQuan.add(panelRound3, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 270, 230, 240));

        cardTrangChu.add(cardTrangChuTongQuan, "card2");

        cardThongKeDoanhThu.setBackground(new java.awt.Color(255, 255, 255));
        cardThongKeDoanhThu.setRoundBottomLeft(30);
        cardThongKeDoanhThu.setRoundBottomRight(30);
        cardThongKeDoanhThu.setRoundTopLeft(30);
        cardThongKeDoanhThu.setRoundTopRight(20);
        cardThongKeDoanhThu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel59.setText("thong ke");
        cardThongKeDoanhThu.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        cardTrangChu.add(cardThongKeDoanhThu, "card3");

        cardThongKeSanPham.setBackground(new java.awt.Color(255, 255, 255));
        cardThongKeSanPham.setRoundBottomLeft(30);
        cardThongKeSanPham.setRoundBottomRight(30);
        cardThongKeSanPham.setRoundTopLeft(30);
        cardThongKeSanPham.setRoundTopRight(20);
        cardThongKeSanPham.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel61.setText("xu huong");
        cardThongKeSanPham.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, -1, -1));

        cardTrangChu.add(cardThongKeSanPham, "card4");

        cardTrangChuNoiBat.setBackground(new java.awt.Color(255, 255, 255));
        cardTrangChuNoiBat.setRoundBottomLeft(30);
        cardTrangChuNoiBat.setRoundBottomRight(30);
        cardTrangChuNoiBat.setRoundTopLeft(30);
        cardTrangChuNoiBat.setRoundTopRight(20);
        cardTrangChuNoiBat.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setText("jLabel27");
        cardTrangChuNoiBat.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, 340, 160));

        cardTrangChu.add(cardTrangChuNoiBat, "card5");

        cardTaiKhoanChucVu.setBackground(new java.awt.Color(255, 255, 255));
        cardTaiKhoanChucVu.setRoundBottomLeft(30);
        cardTaiKhoanChucVu.setRoundBottomRight(30);
        cardTaiKhoanChucVu.setRoundTopLeft(30);
        cardTaiKhoanChucVu.setRoundTopRight(20);
        cardTaiKhoanChucVu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel90.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(0, 0, 255));
        jLabel90.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel90.setText("Mô tả:");
        jLabel90.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardTaiKhoanChucVu.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, 110, 30));

        jTextField17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField17.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardTaiKhoanChucVu.add(jTextField17, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 180, 30));

        jLabel92.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(0, 0, 255));
        jLabel92.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel92.setText("Mã chức vụ:");
        jLabel92.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardTaiKhoanChucVu.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 110, 30));

        jTextField19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField19.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardTaiKhoanChucVu.add(jTextField19, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 180, 30));

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 255)));

        button39.setBackground(new java.awt.Color(204, 204, 255));
        button39.setText("Làm mới");

        button40.setBackground(new java.awt.Color(204, 204, 255));
        button40.setText("Cập nhật");
        button40.setEffectColor(new java.awt.Color(204, 255, 204));

        button41.setBackground(new java.awt.Color(255, 51, 102));
        button41.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 0, 51)));
        button41.setForeground(new java.awt.Color(255, 255, 255));
        button41.setText("Xóa");

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));

        combobox9.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "a", "x", "e", "f", "e", "gd", " " }));
        combobox9.setSelectedIndex(-1);
        combobox9.setLabeText("Sắp xếp theo");

        jLabel93.setText(" Tiến hành sắp xếp");
        jLabel93.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(0, 0, 0)));
        jPanel21.setLayout(null);

        editButton16.setBackground(new java.awt.Color(102, 204, 255));
        editButton16.setText("Tăng");
        jPanel21.add(editButton16);
        editButton16.setBounds(20, 20, 159, 30);

        editButton17.setBackground(new java.awt.Color(204, 153, 255));
        editButton17.setText("Giảm");
        editButton17.setMargin(new java.awt.Insets(5, 14, 14, 14));
        jPanel21.add(editButton17);
        editButton17.setBounds(20, 90, 159, 30);

        jLabel94.setForeground(new java.awt.Color(153, 153, 153));
        jLabel94.setText("hoặc");
        jPanel21.add(jLabel94);
        jLabel94.setBounds(90, 60, 40, 20);
        jPanel21.add(jSeparator9);
        jSeparator9.setBounds(120, 70, 60, 20);
        jPanel21.add(jSeparator10);
        jSeparator10.setBounds(20, 70, 60, 20);

        jSeparator11.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator11.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        jPanel21.add(jSeparator11);
        jSeparator11.setBounds(100, 0, 100, 20);

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addGap(0, 4, Short.MAX_VALUE)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                            .addComponent(combobox9, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(17, 17, 17))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                            .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap()))))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addComponent(combobox9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        button42.setBackground(new java.awt.Color(204, 204, 255));
        button42.setText("Thêm");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button41, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button39, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button40, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button42, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(button39, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(button42, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(button40, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(button41, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        cardTaiKhoanChucVu.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 30, 380, 230));

        jPanel22.setBackground(new java.awt.Color(204, 204, 255));
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        button43.setBackground(new java.awt.Color(204, 204, 255));
        button43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/arrow-right.png"))); // NOI18N
        jPanel22.add(button43, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 11, -1, -1));

        button44.setBackground(new java.awt.Color(204, 204, 255));
        button44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/previous.png"))); // NOI18N
        jPanel22.add(button44, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 11, -1, -1));

        button45.setBackground(new java.awt.Color(204, 204, 255));
        button45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/left-arrow.png"))); // NOI18N
        jPanel22.add(button45, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 11, -1, -1));

        button46.setBackground(new java.awt.Color(204, 204, 255));
        button46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/next.png"))); // NOI18N
        button46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button46ActionPerformed(evt);
            }
        });
        jPanel22.add(button46, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 11, -1, -1));

        jLabel95.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(153, 153, 153));
        jLabel95.setText(" Nguyễn Văn An");
        jPanel22.add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 20, 210, 30));

        jTextField20.setBackground(new java.awt.Color(204, 204, 255));
        jTextField20.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel22.add(jTextField20, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 10, 210, 40));

        jLabel96.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel96.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/search.png"))); // NOI18N
        jLabel96.setText("Tìm kiếm:");
        jPanel22.add(jLabel96, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 20, 110, 30));

        jLabel97.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel97.setForeground(new java.awt.Color(204, 0, 51));
        jLabel97.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel97.setText("DANH SÁCH NHÂN VIÊN");
        jPanel22.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 360, 40));

        cardTaiKhoanChucVu.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 1150, 60));

        jScrollPane6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tblKhachHang5.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tblKhachHang5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"a", "â", "â", "a"},
                {"d", "c", "c", "a"},
                {"z", "c", "m", "d"},
                {"a", "o", "o", "a"}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblKhachHang5.setGridColor(new java.awt.Color(255, 255, 255));
        tblKhachHang5.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblKhachHang5.getTableHeader().setResizingAllowed(false);
        tblKhachHang5.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(tblKhachHang5);

        cardTaiKhoanChucVu.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 1150, 180));

        jLabel98.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel98.setForeground(new java.awt.Color(204, 0, 51));
        jLabel98.setText("2 trên 10");
        cardTaiKhoanChucVu.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 540, 120, 40));

        jLabel99.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel99.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/visits (1).png"))); // NOI18N
        jLabel99.setText("Bảng ghi:");
        cardTaiKhoanChucVu.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 540, 110, 40));

        jLabel100.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel100.setForeground(new java.awt.Color(0, 0, 255));
        jLabel100.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel100.setText("Tên chức vụ:");
        jLabel100.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardTaiKhoanChucVu.add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 110, 30));

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane7.setViewportView(jTextArea1);

        cardTaiKhoanChucVu.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(416, 100, 320, 150));

        cardTrangChu.add(cardTaiKhoanChucVu, "card6");

        cardTaiKhoanNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        cardTaiKhoanNhanVien.setRoundBottomLeft(30);
        cardTaiKhoanNhanVien.setRoundBottomRight(30);
        cardTaiKhoanNhanVien.setRoundTopLeft(30);
        cardTaiKhoanNhanVien.setRoundTopRight(20);
        cardTaiKhoanNhanVien.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel14.setBackground(new java.awt.Color(204, 204, 255));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        button31.setBackground(new java.awt.Color(204, 204, 255));
        button31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/arrow-right.png"))); // NOI18N
        jPanel14.add(button31, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 11, -1, -1));

        button33.setBackground(new java.awt.Color(204, 204, 255));
        button33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/left-arrow.png"))); // NOI18N
        jPanel14.add(button33, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 11, -1, -1));

        button34.setBackground(new java.awt.Color(204, 204, 255));
        button34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/next.png"))); // NOI18N
        button34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button34ActionPerformed(evt);
            }
        });
        jPanel14.add(button34, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 11, -1, -1));

        jLabel82.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(153, 153, 153));
        jLabel82.setText(" Nguyễn Văn An");
        jPanel14.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 20, 210, 30));

        jTextField5.setBackground(new java.awt.Color(204, 204, 255));
        jTextField5.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel14.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 10, 210, 40));

        jLabel83.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel83.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/search.png"))); // NOI18N
        jLabel83.setText("Tìm kiếm:");
        jPanel14.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 20, 110, 30));

        jLabel84.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(204, 0, 51));
        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel84.setText("DANH SÁCH NHÂN VIÊN");
        jPanel14.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 360, 40));

        button51.setBackground(new java.awt.Color(204, 204, 255));
        button51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/previous.png"))); // NOI18N
        jPanel14.add(button51, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 11, -1, -1));

        cardTaiKhoanNhanVien.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 1150, 60));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tblKhachHang4.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tblKhachHang4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"a", "â", "â", "a"},
                {"d", "c", "c", "a"},
                {"z", "c", "m", "d"},
                {"a", "o", "o", "a"}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblKhachHang4.setGridColor(new java.awt.Color(255, 255, 255));
        tblKhachHang4.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblKhachHang4.getTableHeader().setResizingAllowed(false);
        tblKhachHang4.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblKhachHang4);

        cardTaiKhoanNhanVien.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 1150, 140));

        jLabel85.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel85.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/visits (1).png"))); // NOI18N
        jLabel85.setText("Bảng ghi:");
        cardTaiKhoanNhanVien.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 540, 110, 40));

        jLabel86.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(204, 0, 51));
        jLabel86.setText("2 trên 10");
        cardTaiKhoanNhanVien.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 540, 120, 40));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 255)));

        button35.setBackground(new java.awt.Color(204, 204, 255));
        button35.setText("Làm mới");

        button36.setBackground(new java.awt.Color(204, 204, 255));
        button36.setText("Cập nhật");
        button36.setEffectColor(new java.awt.Color(204, 255, 204));

        button37.setBackground(new java.awt.Color(255, 51, 102));
        button37.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 0, 51)));
        button37.setForeground(new java.awt.Color(255, 255, 255));
        button37.setText("Xóa");

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        combobox6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "a", "x", "e", "f", "e", "gd", " " }));
        combobox6.setSelectedIndex(-1);
        combobox6.setLabeText("Sắp xếp theo");

        jLabel30.setText(" Tiến hành sắp xếp");
        jLabel30.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(0, 0, 0)));
        jPanel17.setLayout(null);

        editButton14.setBackground(new java.awt.Color(102, 204, 255));
        editButton14.setText("Tăng");
        jPanel17.add(editButton14);
        editButton14.setBounds(20, 20, 159, 30);

        editButton15.setBackground(new java.awt.Color(204, 153, 255));
        editButton15.setText("Giảm");
        editButton15.setMargin(new java.awt.Insets(5, 14, 14, 14));
        jPanel17.add(editButton15);
        editButton15.setBounds(20, 90, 159, 30);

        jLabel32.setForeground(new java.awt.Color(153, 153, 153));
        jLabel32.setText("hoặc");
        jPanel17.add(jLabel32);
        jLabel32.setBounds(90, 60, 40, 20);
        jPanel17.add(jSeparator6);
        jSeparator6.setBounds(120, 70, 60, 20);
        jPanel17.add(jSeparator7);
        jSeparator7.setBounds(20, 70, 60, 20);

        jSeparator8.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator8.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        jPanel17.add(jSeparator8);
        jSeparator8.setBounds(100, 0, 100, 20);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGap(0, 4, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                            .addComponent(combobox6, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(17, 17, 17))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                            .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap()))))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(combobox6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        button38.setBackground(new java.awt.Color(204, 204, 255));
        button38.setText("Thêm");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button37, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button35, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button36, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button38, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(button35, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(button38, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(button36, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(button37, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        cardTaiKhoanNhanVien.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 30, 380, 230));

        jLabel33.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 0, 255));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel33.setText("Họ và tên:");
        jLabel33.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardTaiKhoanNhanVien.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 44, 110, 30));

        jTextField6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField6.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardTaiKhoanNhanVien.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 180, 30));

        jLabel34.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 0, 255));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel34.setText("Tên đăng nhập:");
        jLabel34.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardTaiKhoanNhanVien.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 110, 30));

        jTextField7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField7.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardTaiKhoanNhanVien.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 180, 30));

        jTextField8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField8.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardTaiKhoanNhanVien.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 100, 180, 30));

        jLabel35.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 0, 255));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel35.setText("Email:");
        jLabel35.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardTaiKhoanNhanVien.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 100, 110, 30));

        jLabel36.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 0, 255));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel36.setText("Số diện thoại:");
        jLabel36.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardTaiKhoanNhanVien.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 110, 30));

        jTextField9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField9.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardTaiKhoanNhanVien.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, 180, 30));

        jLabel37.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 0, 255));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel37.setText("Trạng thái:");
        jLabel37.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardTaiKhoanNhanVien.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 280, 110, 30));

        jTextField10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField10.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardTaiKhoanNhanVien.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 220, 180, 30));

        jTextField11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField11.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardTaiKhoanNhanVien.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 160, 180, 30));

        jLabel38.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 0, 255));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel38.setText("Ngày sinh:");
        jLabel38.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardTaiKhoanNhanVien.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 160, 110, 30));

        jLabel39.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(0, 0, 255));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel39.setText("Mật khẩu:");
        jLabel39.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardTaiKhoanNhanVien.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 110, 30));

        jTextField12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField12.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardTaiKhoanNhanVien.add(jTextField12, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, 180, 30));

        jLabel41.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(0, 0, 255));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel41.setText("Giới tính:");
        jLabel41.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardTaiKhoanNhanVien.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 110, 30));

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));

        jRadioButton1.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Nữ");

        jRadioButton2.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Nam");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 36, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton1))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        cardTaiKhoanNhanVien.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 270, 180, 60));

        combobox7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Admin", "Quản lí", "Nhân Viên" }));
        combobox7.setSelectedIndex(-1);
        combobox7.setLabeText("Vai trò");
        cardTaiKhoanNhanVien.add(combobox7, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 260, 50));

        jLabel40.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(0, 0, 255));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel40.setText("Địa chỉ:");
        jLabel40.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardTaiKhoanNhanVien.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 220, 110, 30));

        switchButton1.setBackground(new java.awt.Color(0, 153, 0));
        cardTaiKhoanNhanVien.add(switchButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 290, 40, 20));

        cardTrangChu.add(cardTaiKhoanNhanVien, "card7");

        cardSanPham.setBackground(new java.awt.Color(255, 255, 255));
        cardSanPham.setMinimumSize(new java.awt.Dimension(1180, 570));
        cardSanPham.setPreferredSize(new java.awt.Dimension(1180, 570));
        cardSanPham.setRoundBottomLeft(30);
        cardSanPham.setRoundBottomRight(30);
        cardSanPham.setRoundTopLeft(30);
        cardSanPham.setRoundTopRight(20);
        cardSanPham.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cardHangSanXuat1.setBackground(new java.awt.Color(255, 255, 255));
        cardHangSanXuat1.setRoundBottomLeft(30);
        cardHangSanXuat1.setRoundBottomRight(30);
        cardHangSanXuat1.setRoundTopLeft(30);
        cardHangSanXuat1.setRoundTopRight(20);
        cardHangSanXuat1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cardLoai2.setBackground(new java.awt.Color(255, 255, 255));
        cardLoai2.setMinimumSize(new java.awt.Dimension(1180, 570));
        cardLoai2.setPreferredSize(new java.awt.Dimension(1180, 570));
        cardLoai2.setRoundBottomLeft(30);
        cardLoai2.setRoundBottomRight(30);
        cardLoai2.setRoundTopLeft(30);
        cardLoai2.setRoundTopRight(20);
        cardLoai2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tblSanPham.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"a", "â", null, null, null, null, null, null, null},
                {"d", "c", null, null, null, null, null, null, null},
                {"z", "c", null, null, null, null, null, null, null},
                {"a", "o", null, null, null, null, null, null, null}
            },
            new String [] {
                "MÃ SẢM PHẨM", "TÊN SẢN PHẨM", "MÃ LOẠI", "MÃ HÃNG", "GIÁ NHẬP", "GIÁ BÁN", "NGÀY NHẬP", "HÌNH", "MÔ TẢ"
            }
        ));
        tblSanPham.setGridColor(new java.awt.Color(255, 255, 255));
        tblSanPham.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblSanPham.getTableHeader().setResizingAllowed(false);
        tblSanPham.getTableHeader().setReorderingAllowed(false);
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblSanPham);

        cardLoai2.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 1150, 180));

        jPanel11.setBackground(new java.awt.Color(204, 204, 255));

        btnNextSP.setBackground(new java.awt.Color(204, 204, 255));
        btnNextSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/arrow-right.png"))); // NOI18N
        btnNextSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNextSPMouseClicked(evt);
            }
        });

        btnFirstSP.setBackground(new java.awt.Color(204, 204, 255));
        btnFirstSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/previous.png"))); // NOI18N
        btnFirstSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFirstSPMouseClicked(evt);
            }
        });

        btnPrevSP.setBackground(new java.awt.Color(204, 204, 255));
        btnPrevSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/left-arrow.png"))); // NOI18N
        btnPrevSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPrevSPMouseClicked(evt);
            }
        });

        btnLastSP.setBackground(new java.awt.Color(204, 204, 255));
        btnLastSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/next.png"))); // NOI18N
        btnLastSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLastSPMouseClicked(evt);
            }
        });
        btnLastSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastSPActionPerformed(evt);
            }
        });

        jLabel79.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(204, 0, 51));
        jLabel79.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel79.setText("BẢNG THÔNG TIN SẢN PHẨM");

        jLabel81.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(153, 153, 153));
        jLabel81.setText("Lenovo");

        jLabel80.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel80.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/search.png"))); // NOI18N
        jLabel80.setText("Tìm kiếm:");

        jTextField4.setBackground(new java.awt.Color(204, 204, 255));
        jTextField4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(btnFirstSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnPrevSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnNextSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btnLastSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel79))
                    .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLastSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrevSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFirstSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNextSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        cardLoai2.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 1150, 60));

        cboMaLoai.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "a", "x", "e", "f", "e", "gd", "" }));
        cboMaLoai.setLabeText("Mã loại hàng");
        cardLoai2.add(cboMaLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, 270, 39));

        cboMaHang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "a", "x", "e", "f", "e", "gd", " " }));
        cboMaHang.setLabeText("Mã hãng");
        cardLoai2.add(cboMaHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 260, 39));

        lblAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAnh.setText("Ảnh");
        lblAnh.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnhMouseClicked(evt);
            }
        });
        cardLoai2.add(lblAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 70, 170, 180));

        jLabel78.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel78.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/visits (1).png"))); // NOI18N
        jLabel78.setText("Bảng ghi:");
        cardLoai2.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 530, 122, -1));

        lblrecordSP.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lblrecordSP.setForeground(new java.awt.Color(204, 0, 51));
        lblrecordSP.setText("2 trên 10");
        cardLoai2.add(lblrecordSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 530, 208, 30));

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));
        jPanel27.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 255)));

        btnLamMoiSP.setBackground(new java.awt.Color(204, 204, 255));
        btnLamMoiSP.setText("Làm mới");
        btnLamMoiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiSPActionPerformed(evt);
            }
        });

        btnCapNhatSP.setBackground(new java.awt.Color(204, 204, 255));
        btnCapNhatSP.setText("Cập nhật");
        btnCapNhatSP.setEffectColor(new java.awt.Color(204, 255, 204));
        btnCapNhatSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatSPActionPerformed(evt);
            }
        });

        btnXoaSP.setBackground(new java.awt.Color(255, 51, 102));
        btnXoaSP.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 0, 51)));
        btnXoaSP.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaSP.setText("Xóa");
        btnXoaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSPActionPerformed(evt);
            }
        });

        btnThemSP.setBackground(new java.awt.Color(204, 204, 255));
        btnThemSP.setText("Thêm");
        btnThemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXoaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhatSP, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLamMoiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnThemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCapNhatSP, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        cardLoai2.add(jPanel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 30, 140, 230));

        txtTenSP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTenSP.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardLoai2.add(txtTenSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 200, 30));

        jLabel70.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(0, 0, 255));
        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel70.setText("Tên sản phẩm:");
        jLabel70.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardLoai2.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 100, 30));

        txtMaSP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtMaSP.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardLoai2.add(txtMaSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 200, 30));

        jLabel71.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(0, 0, 255));
        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel71.setText("Mã sản phẩm:");
        jLabel71.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardLoai2.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 100, 30));

        txtNgayNhapSP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtNgayNhapSP.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardLoai2.add(txtNgayNhapSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, 200, 30));

        jLabel106.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel106.setForeground(new java.awt.Color(0, 0, 255));
        jLabel106.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel106.setText("Ngày nhập:");
        jLabel106.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardLoai2.add(jLabel106, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, 90, 30));

        txtGiaNhapSP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtGiaNhapSP.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardLoai2.add(txtGiaNhapSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 230, 200, 30));

        jLabel107.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel107.setForeground(new java.awt.Color(0, 0, 255));
        jLabel107.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel107.setText("Giá nhập:");
        jLabel107.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardLoai2.add(jLabel107, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 100, 30));

        txtGiaBanSP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtGiaBanSP.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardLoai2.add(txtGiaBanSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 80, 180, 30));

        jLabel108.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel108.setForeground(new java.awt.Color(0, 0, 255));
        jLabel108.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel108.setText("Giá bán:");
        jLabel108.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardLoai2.add(jLabel108, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 80, 70, 30));

        txtMoTaSP.setColumns(20);
        txtMoTaSP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMoTaSP.setRows(5);
        jScrollPane8.setViewportView(txtMoTaSP);

        cardLoai2.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 160, 270, 100));

        jLabel109.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel109.setForeground(new java.awt.Color(0, 0, 255));
        jLabel109.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel109.setText("Mô tả:");
        jLabel109.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardLoai2.add(jLabel109, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, 80, -1));

        cardHangSanXuat1.add(cardLoai2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        cardSanPham.add(cardHangSanXuat1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        cardTrangChu.add(cardSanPham, "card8");

        cardHangSanXuat.setBackground(new java.awt.Color(255, 255, 255));
        cardHangSanXuat.setRoundBottomLeft(30);
        cardHangSanXuat.setRoundBottomRight(30);
        cardHangSanXuat.setRoundTopLeft(30);
        cardHangSanXuat.setRoundTopRight(20);
        cardHangSanXuat.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cardLoai1.setBackground(new java.awt.Color(255, 255, 255));
        cardLoai1.setMinimumSize(new java.awt.Dimension(1180, 570));
        cardLoai1.setPreferredSize(new java.awt.Dimension(1180, 570));
        cardLoai1.setRoundBottomLeft(30);
        cardLoai1.setRoundBottomRight(30);
        cardLoai1.setRoundTopLeft(30);
        cardLoai1.setRoundTopRight(20);
        cardLoai1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tblHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tblHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"a", "â"},
                {"d", "c"},
                {"z", "c"},
                {"a", "o"}
            },
            new String [] {
                "MÃ HÃNG", "TÊN HÃNG"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHang.setGridColor(new java.awt.Color(255, 255, 255));
        tblHang.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblHang.getTableHeader().setResizingAllowed(false);
        tblHang.getTableHeader().setReorderingAllowed(false);
        tblHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHangMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblHang);

        cardLoai1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 1150, 180));

        jPanel8.setBackground(new java.awt.Color(204, 204, 255));

        btnNextHang.setBackground(new java.awt.Color(204, 204, 255));
        btnNextHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/arrow-right.png"))); // NOI18N
        btnNextHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNextHangMouseClicked(evt);
            }
        });

        btnFirstHang.setBackground(new java.awt.Color(204, 204, 255));
        btnFirstHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/previous.png"))); // NOI18N
        btnFirstHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFirstHangMouseClicked(evt);
            }
        });

        btnPrevHang.setBackground(new java.awt.Color(204, 204, 255));
        btnPrevHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/left-arrow.png"))); // NOI18N
        btnPrevHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPrevHangMouseClicked(evt);
            }
        });

        btnLastHang.setBackground(new java.awt.Color(204, 204, 255));
        btnLastHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/next.png"))); // NOI18N
        btnLastHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLastHangMouseClicked(evt);
            }
        });
        btnLastHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastHangActionPerformed(evt);
            }
        });

        jLabel74.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(204, 0, 51));
        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel74.setText("BẢNG THÔNG TIN HÃNG");

        jLabel75.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel75.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/search.png"))); // NOI18N
        jLabel75.setText("Tìm kiếm:");

        jLabel76.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(153, 153, 153));
        jLabel76.setText("Lenovo");

        jTextField3.setBackground(new java.awt.Color(204, 204, 255));
        jTextField3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(btnFirstHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnPrevHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnNextHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btnLastHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLastHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPrevHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnFirstHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNextHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        cardLoai1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 1150, 60));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 255)));

        btnLamMoiHang.setBackground(new java.awt.Color(204, 204, 255));
        btnLamMoiHang.setText("Làm mới");
        btnLamMoiHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiHangActionPerformed(evt);
            }
        });

        btnCapNhatHang.setBackground(new java.awt.Color(204, 204, 255));
        btnCapNhatHang.setText("Cập nhật");
        btnCapNhatHang.setEffectColor(new java.awt.Color(204, 255, 204));
        btnCapNhatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatHangActionPerformed(evt);
            }
        });

        btnXoaHang.setBackground(new java.awt.Color(255, 51, 102));
        btnXoaHang.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 0, 51)));
        btnXoaHang.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaHang.setText("Xóa");
        btnXoaHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaHangActionPerformed(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        combobox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "a", "x", "e", "f", "e", "gd", " " }));
        combobox2.setSelectedIndex(-1);
        combobox2.setLabeText("Sắp xếp theo");

        jLabel102.setText(" Tiến hành sắp xếp");
        jLabel102.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));
        jPanel26.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(0, 0, 0)));
        jPanel26.setLayout(null);

        editButton10.setBackground(new java.awt.Color(102, 204, 255));
        editButton10.setText("Tên hãng");
        jPanel26.add(editButton10);
        editButton10.setBounds(20, 20, 159, 30);

        editButton11.setBackground(new java.awt.Color(204, 153, 255));
        editButton11.setText("Mã hãng");
        editButton11.setMargin(new java.awt.Insets(5, 14, 14, 14));
        jPanel26.add(editButton11);
        editButton11.setBounds(20, 90, 159, 30);

        jLabel103.setForeground(new java.awt.Color(153, 153, 153));
        jLabel103.setText("hoặc");
        jPanel26.add(jLabel103);
        jLabel103.setBounds(90, 60, 40, 20);
        jPanel26.add(jSeparator15);
        jSeparator15.setBounds(120, 70, 60, 20);
        jPanel26.add(jSeparator16);
        jSeparator16.setBounds(20, 70, 60, 20);

        jSeparator17.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator17.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        jPanel26.add(jSeparator17);
        jSeparator17.setBounds(100, 0, 100, 20);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 34, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                            .addComponent(combobox2, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(17, 17, 17))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                            .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap()))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(combobox2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnThemHang.setBackground(new java.awt.Color(204, 204, 255));
        btnThemHang.setText("Thêm");
        btnThemHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXoaHang, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoiHang, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemHang, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btnLamMoiHang, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThemHang, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCapNhatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoaHang, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        cardLoai1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 50, 410, 230));

        jLabel73.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel73.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/visits (1).png"))); // NOI18N
        jLabel73.setText("Bảng ghi:");
        cardLoai1.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 530, 122, 41));

        lblrecordHang.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lblrecordHang.setForeground(new java.awt.Color(204, 0, 51));
        lblrecordHang.setText("2 trên 10");
        cardLoai1.add(lblrecordHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 530, 208, 41));

        txtMaHang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMaHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardLoai1.add(txtMaHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 180, 30));

        jLabel110.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel110.setForeground(new java.awt.Color(0, 0, 255));
        jLabel110.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel110.setText("Mã hãng sản xuất:");
        jLabel110.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardLoai1.add(jLabel110, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 130, 30));

        txtTenHang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTenHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardLoai1.add(txtTenHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 140, 180, 30));

        jLabel111.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel111.setForeground(new java.awt.Color(0, 0, 255));
        jLabel111.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel111.setText("Tên hãng sản xuất:");
        jLabel111.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardLoai1.add(jLabel111, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 100, 150, 30));

        cardHangSanXuat.add(cardLoai1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        cardTrangChu.add(cardHangSanXuat, "card9");

        cardLoai.setBackground(new java.awt.Color(255, 255, 255));
        cardLoai.setMinimumSize(new java.awt.Dimension(1180, 570));
        cardLoai.setPreferredSize(new java.awt.Dimension(1180, 570));
        cardLoai.setRoundBottomLeft(30);
        cardLoai.setRoundBottomRight(30);
        cardLoai.setRoundTopLeft(30);
        cardLoai.setRoundTopRight(20);
        cardLoai.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tblLoaiHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tblLoaiHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"a", "â"},
                {"d", "c"},
                {"z", "c"},
                {"a", "o"}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        tblLoaiHang.setGridColor(new java.awt.Color(255, 255, 255));
        tblLoaiHang.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblLoaiHang.getTableHeader().setResizingAllowed(false);
        tblLoaiHang.getTableHeader().setReorderingAllowed(false);
        tblLoaiHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLoaiHangMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblLoaiHang);

        cardLoai.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 1150, 200));

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));

        btnNextLoai.setBackground(new java.awt.Color(204, 204, 255));
        btnNextLoai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/arrow-right.png"))); // NOI18N
        btnNextLoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNextLoaiMouseClicked(evt);
            }
        });
        btnNextLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextLoaiActionPerformed(evt);
            }
        });

        btnFirstLoai.setBackground(new java.awt.Color(204, 204, 255));
        btnFirstLoai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/previous.png"))); // NOI18N
        btnFirstLoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFirstLoaiMouseClicked(evt);
            }
        });

        btnPrevLoai.setBackground(new java.awt.Color(204, 204, 255));
        btnPrevLoai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/left-arrow.png"))); // NOI18N
        btnPrevLoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPrevLoaiMouseClicked(evt);
            }
        });

        btnLastLoai.setBackground(new java.awt.Color(204, 204, 255));
        btnLastLoai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/next.png"))); // NOI18N
        btnLastLoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLastLoaiMouseClicked(evt);
            }
        });
        btnLastLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastLoaiActionPerformed(evt);
            }
        });

        jLabel69.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(204, 0, 51));
        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel69.setText("BẢNG THÔNG TIN LOẠI HÀNG");

        jLabel104.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jLabel104.setForeground(new java.awt.Color(153, 153, 153));
        jLabel104.setText("Lenovo");

        jTextField18.setBackground(new java.awt.Color(204, 204, 255));
        jTextField18.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel105.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel105.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/search.png"))); // NOI18N
        jLabel105.setText("Tìm kiếm:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(btnFirstLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnPrevLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnNextLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btnLastLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel69, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLastLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPrevLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnFirstLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNextLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        cardLoai.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 1150, 60));

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 255)));

        btnLamMoiLoai.setBackground(new java.awt.Color(204, 204, 255));
        btnLamMoiLoai.setText("Làm mới");
        btnLamMoiLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiLoaiActionPerformed(evt);
            }
        });

        btnCapNhatLoai.setBackground(new java.awt.Color(204, 204, 255));
        btnCapNhatLoai.setText("Cập nhật");
        btnCapNhatLoai.setEffectColor(new java.awt.Color(204, 255, 204));
        btnCapNhatLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatLoaiActionPerformed(evt);
            }
        });

        btnXoaLoai.setBackground(new java.awt.Color(255, 51, 102));
        btnXoaLoai.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 0, 51)));
        btnXoaLoai.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaLoai.setText("Xóa");
        btnXoaLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaLoaiActionPerformed(evt);
            }
        });

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));

        combobox8.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "a", "x", "e", "f", "e", "gd", " " }));
        combobox8.setSelectedIndex(-1);
        combobox8.setLabeText("Sắp xếp theo");

        jLabel91.setText(" Tiến hành sắp xếp");
        jLabel91.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(0, 0, 0)));
        jPanel25.setLayout(null);

        editButton18.setBackground(new java.awt.Color(102, 204, 255));
        editButton18.setText("Tăng");
        jPanel25.add(editButton18);
        editButton18.setBounds(20, 20, 159, 30);

        editButton19.setBackground(new java.awt.Color(204, 153, 255));
        editButton19.setText("Giảm");
        editButton19.setMargin(new java.awt.Insets(5, 14, 14, 14));
        jPanel25.add(editButton19);
        editButton19.setBounds(20, 90, 159, 30);

        jLabel101.setForeground(new java.awt.Color(153, 153, 153));
        jLabel101.setText("hoặc");
        jPanel25.add(jLabel101);
        jLabel101.setBounds(90, 60, 40, 20);
        jPanel25.add(jSeparator12);
        jSeparator12.setBounds(120, 70, 60, 20);
        jPanel25.add(jSeparator13);
        jSeparator13.setBounds(20, 70, 60, 20);

        jSeparator14.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator14.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        jPanel25.add(jSeparator14);
        jSeparator14.setBounds(100, 0, 100, 20);

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addGap(0, 4, Short.MAX_VALUE)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                            .addComponent(combobox8, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(17, 17, 17))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                            .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap()))))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addComponent(combobox8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnThemLoai.setBackground(new java.awt.Color(204, 204, 255));
        btnThemLoai.setText("Thêm");
        btnThemLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemLoaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXoaLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoiLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhatLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(btnLamMoiLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThemLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCapNhatLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoaLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        cardLoai.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 50, 380, 230));

        jLabel68.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel68.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/visits (1).png"))); // NOI18N
        jLabel68.setText("Bảng ghi:");
        cardLoai.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 550, 122, 41));

        lblrecordLoai.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lblrecordLoai.setForeground(new java.awt.Color(204, 0, 51));
        lblrecordLoai.setText("2 trên 10");
        cardLoai.add(lblrecordLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 550, 208, 41));

        txtMaLoai.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMaLoai.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardLoai.add(txtMaLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 180, 30));

        jLabel112.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel112.setForeground(new java.awt.Color(0, 0, 255));
        jLabel112.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel112.setText("Mã loại hàng:");
        jLabel112.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardLoai.add(jLabel112, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 130, 30));

        jLabel113.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel113.setForeground(new java.awt.Color(0, 0, 255));
        jLabel113.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel113.setText("Tên loại hàng:");
        jLabel113.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardLoai.add(jLabel113, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 100, 150, 30));

        txtTenLoai.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTenLoai.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardLoai.add(txtTenLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 140, 180, 30));

        cardTrangChu.add(cardLoai, "card10");

        cardHoaDon.setBackground(new java.awt.Color(255, 255, 255));
        cardHoaDon.setRoundBottomLeft(30);
        cardHoaDon.setRoundBottomRight(30);
        cardHoaDon.setRoundTopLeft(30);
        cardHoaDon.setRoundTopRight(20);
        cardHoaDon.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel43.setText("<html> <h2>Phần mềm được phát triển bởi <a href=\"https://www.facebook.com/davisy.dev\">DAVISY TEAM</a></h2>  <!html>");
        cardHoaDon.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 500, -1));

        jLabel44.setText("<html>  <h3> Phần mềm là dự án trong học kỳ 4 của chúng tôi tại  <a href=\"\">FPT Polytechnic College</a> </h3>  <!html>");
        cardHoaDon.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, 380, -1));

        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/businessman.png"))); // NOI18N
        cardHoaDon.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, -1, -1));

        jLabel46.setText("<html>\n<h3>Các tư liệu tham khảo</h3>  \n<ul>  \n <li> <h4> <a href=\"https://github.com/k33ptoo/\">KeepToo</a></h4></li>  \n<li> <h4><a href=\"https://github.com/DJ-Raven\">DJ-Raven</a></h4>\n</li>\n </ul> \n <!html>");
        cardHoaDon.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, 320, 110));

        jLabel47.setText("<html>  <h3>Bạn có thể tham khảo giao diện của chúng tôi tại <a href=\"https://www.github.com/theanishtar\"> đây</a> </h3>  <!html>");
        cardHoaDon.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 450, -1));

        cardTrangChu.add(cardHoaDon, "card11");

        cardHoaDonCho.setBackground(new java.awt.Color(255, 255, 255));
        cardHoaDonCho.setRoundBottomLeft(30);
        cardHoaDonCho.setRoundBottomRight(30);
        cardHoaDonCho.setRoundTopLeft(30);
        cardHoaDonCho.setRoundTopRight(20);
        cardHoaDonCho.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel48.setText("<html> <h2>Phần mềm được phát triển bởi <a href=\"https://www.facebook.com/davisy.dev\">DAVISY TEAM</a></h2>  <!html>");
        cardHoaDonCho.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 500, -1));

        jLabel49.setText("<html>  <h3> Phần mềm là dự án trong học kỳ 4 của chúng tôi tại  <a href=\"\">FPT Polytechnic College</a> </h3>  <!html>");
        cardHoaDonCho.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, 380, -1));

        jLabel50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/businessman.png"))); // NOI18N
        cardHoaDonCho.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, -1, -1));

        jLabel51.setText("<html>\n<h3>Các tư liệu tham khảo</h3>  \n<ul>  \n <li> <h4> <a href=\"https://github.com/k33ptoo/\">KeepToo</a></h4></li>  \n<li> <h4><a href=\"https://github.com/DJ-Raven\">DJ-Raven</a></h4>\n</li>\n </ul> \n <!html>");
        cardHoaDonCho.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, 320, 110));

        jLabel52.setText("<html>  <h3>Bạn có thể tham khảo giao diện của chúng tôi tại <a href=\"https://www.github.com/theanishtar\"> đây</a> </h3>  <!html>");
        cardHoaDonCho.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 450, -1));

        cardTrangChu.add(cardHoaDonCho, "card12");

        cardGiamGia.setBackground(new java.awt.Color(255, 255, 255));
        cardGiamGia.setRoundBottomLeft(30);
        cardGiamGia.setRoundBottomRight(30);
        cardGiamGia.setRoundTopLeft(30);
        cardGiamGia.setRoundTopRight(20);
        cardGiamGia.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel53.setText("<html> <h2>Phần mềm được phát triển bởi <a href=\"https://www.facebook.com/davisy.dev\">DAVISY TEAM</a></h2>  <!html>");
        cardGiamGia.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 500, -1));

        jLabel54.setText("<html>  <h3> Phần mềm là dự án trong học kỳ 4 của chúng tôi tại  <a href=\"\">FPT Polytechnic College</a> </h3>  <!html>");
        cardGiamGia.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, 380, -1));

        jLabel55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/businessman.png"))); // NOI18N
        cardGiamGia.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, -1, -1));

        jLabel56.setText("<html>\n<h3>Các tư liệu tham khảo</h3>  \n<ul>  \n <li> <h4> <a href=\"https://github.com/k33ptoo/\">KeepToo</a></h4></li>  \n<li> <h4><a href=\"https://github.com/DJ-Raven\">DJ-Raven</a></h4>\n</li>\n </ul> \n <!html>");
        cardGiamGia.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, 320, 110));

        jLabel57.setText("<html>  <h3>Bạn có thể tham khảo giao diện của chúng tôi tại <a href=\"https://www.github.com/theanishtar\"> đây</a> </h3>  <!html>");
        cardGiamGia.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 450, -1));

        cardTrangChu.add(cardGiamGia, "card13");

        cardGioiThieuSanPham.setBackground(new java.awt.Color(255, 255, 255));
        cardGioiThieuSanPham.setRoundBottomLeft(30);
        cardGioiThieuSanPham.setRoundBottomRight(30);
        cardGioiThieuSanPham.setRoundTopLeft(30);
        cardGioiThieuSanPham.setRoundTopRight(20);
        cardGioiThieuSanPham.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("<html> <h2>Phần mềm được phát triển bởi <a href=\"https://www.facebook.com/davisy.dev\">DAVISY TEAM</a></h2>  <!html>");
        cardGioiThieuSanPham.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 500, -1));

        jLabel3.setText("<html>  <h3> Phần mềm là dự án trong học kỳ 4 của chúng tôi tại  <a href=\"\">FPT Polytechnic College</a> </h3>  <!html>");
        cardGioiThieuSanPham.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, 380, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/businessman.png"))); // NOI18N
        cardGioiThieuSanPham.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, -1, -1));

        jLabel5.setText("<html>\n<h3>Các tư liệu tham khảo</h3>  \n<ul>  \n <li> <h4> <a href=\"https://github.com/k33ptoo/\">KeepToo</a></h4></li>  \n<li> <h4><a href=\"https://github.com/DJ-Raven\">DJ-Raven</a></h4>\n</li>\n </ul> \n <!html>");
        cardGioiThieuSanPham.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, 320, 110));

        jLabel6.setText("<html>  <h3>Bạn có thể tham khảo giao diện của chúng tôi tại <a href=\"https://www.github.com/theanishtar\"> đây</a> </h3>  <!html>");
        cardGioiThieuSanPham.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 450, -1));

        cardTrangChu.add(cardGioiThieuSanPham, "card14");

        cardGioiThieuThanhVien.setBackground(new java.awt.Color(255, 255, 255));
        cardGioiThieuThanhVien.setRoundBottomLeft(30);
        cardGioiThieuThanhVien.setRoundBottomRight(30);
        cardGioiThieuThanhVien.setRoundTopLeft(30);
        cardGioiThieuThanhVien.setRoundTopRight(20);
        cardGioiThieuThanhVien.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel22.setText("<html> <h2>Phần mềm được phát triển bởi <a href=\"https://www.facebook.com/davisy.dev\">DAVISY TEAM</a></h2>  <!html>");
        cardGioiThieuThanhVien.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 500, -1));

        jLabel23.setText("<html>  <h3> Phần mềm là dự án trong học kỳ 4 của chúng tôi tại  <a href=\"\">FPT Polytechnic College</a> </h3>  <!html>");
        cardGioiThieuThanhVien.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, 380, -1));

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/businessman.png"))); // NOI18N
        cardGioiThieuThanhVien.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, -1, -1));

        jLabel25.setText("<html>\n<h3>Các tư liệu tham khảo</h3>  \n<ul>  \n <li> <h4> <a href=\"https://github.com/k33ptoo/\">KeepToo</a></h4></li>  \n<li> <h4><a href=\"https://github.com/DJ-Raven\">DJ-Raven</a></h4>\n</li>\n </ul> \n <!html>");
        cardGioiThieuThanhVien.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, 320, 110));

        jLabel26.setText("<html>  <h3>Bạn có thể tham khảo giao diện của chúng tôi tại <a href=\"https://www.github.com/theanishtar\"> đây</a> </h3>  <!html>");
        cardGioiThieuThanhVien.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 450, -1));

        cardTrangChu.add(cardGioiThieuThanhVien, "card15");

        cardKhachHang.setBackground(new java.awt.Color(255, 255, 255));
        cardKhachHang.setRoundBottomLeft(30);
        cardKhachHang.setRoundBottomRight(30);
        cardKhachHang.setRoundTopLeft(30);
        cardKhachHang.setRoundTopRight(20);
        cardKhachHang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tblKhachHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"a", "â", "â", "a"},
                {"d", "c", "c", "a"},
                {"z", "c", "m", "d"},
                {"a", "o", "o", "a"}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblKhachHang.setGridColor(new java.awt.Color(255, 255, 255));
        tblKhachHang.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblKhachHang.getTableHeader().setResizingAllowed(false);
        tblKhachHang.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblKhachHang);

        cardKhachHang.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 1150, 180));

        jLabel65.setFont(new java.awt.Font("Times New Roman", 1, 17)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(255, 0, 0));
        jLabel65.setText("0");
        cardKhachHang.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 220, 30));

        jLabel63.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel63.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/credit-card.png"))); // NOI18N
        jLabel63.setText("Tích điểm:");
        cardKhachHang.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 110, 30));

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        button2.setBackground(new java.awt.Color(204, 204, 255));
        button2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/arrow-right.png"))); // NOI18N
        jPanel2.add(button2, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 11, -1, -1));

        button5.setBackground(new java.awt.Color(204, 204, 255));
        button5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/previous.png"))); // NOI18N
        jPanel2.add(button5, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 11, -1, -1));

        button6.setBackground(new java.awt.Color(204, 204, 255));
        button6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/left-arrow.png"))); // NOI18N
        jPanel2.add(button6, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 11, -1, -1));

        button7.setBackground(new java.awt.Color(204, 204, 255));
        button7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/next.png"))); // NOI18N
        button7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button7ActionPerformed(evt);
            }
        });
        jPanel2.add(button7, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 11, -1, -1));

        jLabel60.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(153, 153, 153));
        jLabel60.setText(" Nguyễn Văn An");
        jPanel2.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 20, 210, 30));

        jTextField1.setBackground(new java.awt.Color(204, 204, 255));
        jTextField1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 10, 210, 40));

        jLabel64.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel64.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/search.png"))); // NOI18N
        jLabel64.setText("Tìm kiếm:");
        jPanel2.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 20, 110, 30));

        jLabel58.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(204, 0, 51));
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel58.setText("DANH SÁCH KHÁCH HÀNG");
        jPanel2.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 360, 40));

        cardKhachHang.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 1150, 60));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 255)));

        button3.setBackground(new java.awt.Color(204, 204, 255));
        button3.setText("Thêm");

        button4.setBackground(new java.awt.Color(204, 204, 255));
        button4.setText("Cập nhật");
        button4.setEffectColor(new java.awt.Color(204, 255, 204));

        button8.setBackground(new java.awt.Color(255, 51, 102));
        button8.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 0, 51)));
        button8.setForeground(new java.awt.Color(255, 255, 255));
        button8.setText("Xóa");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        combobox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "a", "x", "e", "f", "e", "gd", " " }));
        combobox1.setSelectedIndex(-1);
        combobox1.setLabeText("Sắp xếp theo");

        jLabel29.setText(" Tiến hành sắp xếp");
        jLabel29.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(0, 0, 0)));
        jPanel13.setLayout(null);

        editButton8.setBackground(new java.awt.Color(102, 204, 255));
        editButton8.setText("Tăng");
        jPanel13.add(editButton8);
        editButton8.setBounds(20, 20, 159, 30);

        editButton9.setBackground(new java.awt.Color(204, 153, 255));
        editButton9.setText("Giảm");
        editButton9.setMargin(new java.awt.Insets(5, 14, 14, 14));
        jPanel13.add(editButton9);
        editButton9.setBounds(20, 90, 159, 30);

        jLabel31.setForeground(new java.awt.Color(153, 153, 153));
        jLabel31.setText("hoặc");
        jPanel13.add(jLabel31);
        jLabel31.setBounds(90, 60, 40, 20);
        jPanel13.add(jSeparator3);
        jSeparator3.setBounds(120, 70, 60, 20);
        jPanel13.add(jSeparator4);
        jSeparator4.setBounds(20, 70, 60, 20);

        jSeparator5.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator5.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        jPanel13.add(jSeparator5);
        jSeparator5.setBounds(100, 0, 100, 20);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 34, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                            .addComponent(combobox1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(17, 17, 17))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap()))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(combobox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        button30.setBackground(new java.awt.Color(204, 204, 255));
        button30.setText("Thêm");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button8, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button30, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(button30, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(button8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        cardKhachHang.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 30, 410, 230));

        jLabel62.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel62.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/visits (1).png"))); // NOI18N
        jLabel62.setText("Bảng ghi:");
        cardKhachHang.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 540, 110, 40));

        jLabel66.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(204, 0, 51));
        jLabel66.setText("2 trên 10");
        cardKhachHang.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 540, 120, 40));

        jLabel42.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(0, 0, 255));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel42.setText("Họ và tên:");
        jLabel42.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardKhachHang.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 150, 30));

        jTextField13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField13.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardKhachHang.add(jTextField13, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 220, 30));

        jTextField14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField14.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardKhachHang.add(jTextField14, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, 230, 30));

        jLabel87.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(0, 0, 255));
        jLabel87.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel87.setText("Số điện thoại:");
        jLabel87.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardKhachHang.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, 160, 30));

        jLabel88.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(0, 0, 255));
        jLabel88.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel88.setText("Mã khách hàng:");
        jLabel88.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardKhachHang.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 150, 30));

        jTextField15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField15.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardKhachHang.add(jTextField15, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 220, 30));

        jTextField16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField16.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardKhachHang.add(jTextField16, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 240, 240, 30));

        jLabel89.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(0, 0, 255));
        jLabel89.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel89.setText("Đại chỉ:");
        jLabel89.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardKhachHang.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 200, 170, 30));

        cardTrangChu.add(cardKhachHang, "card16");

        jplContainer.add(cardTrangChu, "card2");

        jPanel1.add(jplContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1220, 590));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        //drawer.show();
        //showOpacity();
        openMenu();
    }//GEN-LAST:event_btnMenuActionPerformed


    private void jlbStateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbStateMouseClicked
        this.setState(1);
    }//GEN-LAST:event_jlbStateMouseClicked

    private void jlbStateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbStateMouseEntered
        jplState.setBackground(new Color(229, 221, 242));
        jlbState.setForeground(Color.black);
    }//GEN-LAST:event_jlbStateMouseEntered

    private void jlbStateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbStateMouseExited
        jplState.setBackground(new Color(117, 198, 213));
        jlbState.setForeground(Color.white);
    }//GEN-LAST:event_jlbStateMouseExited

    private void jlbCloseFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jlbCloseFocusGained
        //jplLose.setBackground(Color.red);
        System.exit(0);
    }//GEN-LAST:event_jlbCloseFocusGained

    private void jlbCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbCloseMouseClicked
        System.exit(0);
    }//GEN-LAST:event_jlbCloseMouseClicked

    private void jlbCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbCloseMouseEntered
        jplLose.setBackground(Color.red);
    }//GEN-LAST:event_jlbCloseMouseEntered

    private void jlbCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbCloseMouseExited
        jplLose.setBackground(new Color(117, 198, 213));
    }//GEN-LAST:event_jlbCloseMouseExited

    private void jplLoseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplLoseMouseClicked
        System.exit(0);
    }//GEN-LAST:event_jplLoseMouseClicked

    private void jplLoseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplLoseMouseEntered
        jplLose.setBackground(Color.red);
    }//GEN-LAST:event_jplLoseMouseEntered

    private void jplLoseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplLoseMouseExited
        jplLose.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_jplLoseMouseExited


    private void TaiKhoantittle2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TaiKhoantittle2MouseClicked

    }//GEN-LAST:event_TaiKhoantittle2MouseClicked

    private void TaiKhoantittle3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TaiKhoantittle3MouseClicked

    }//GEN-LAST:event_TaiKhoantittle3MouseClicked


    private void GioiThieutittle1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GioiThieutittle1MousePressed
        setLocationHr(cardGioiThieuSanPham, GioiThieuHr, GioiThieutittle1);
    }//GEN-LAST:event_GioiThieutittle1MousePressed

    private void GioiThieutittle2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GioiThieutittle2MousePressed
        setLocationHr(cardGioiThieuThanhVien, GioiThieuHr, GioiThieutittle2);
    }//GEN-LAST:event_GioiThieutittle2MousePressed


    private void TaiKhoantittle2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TaiKhoantittle2MousePressed
        setLocationHr(cardTaiKhoanNhanVien, TaiKhoanHr, TaiKhoantittle2);
    }//GEN-LAST:event_TaiKhoantittle2MousePressed

    private void TaiKhoantittle3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TaiKhoantittle3MousePressed
        setLocationHr(cardTaiKhoanChucVu, TaiKhoanHr, TaiKhoantittle3);
    }//GEN-LAST:event_TaiKhoantittle3MousePressed

    private void opacityMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_opacityMousePressed
        closeMenu();
    }//GEN-LAST:event_opacityMousePressed

    private void btnSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSanPhamActionPerformed
        btnItemMenu = btnSanPham;
        chooserMenu(2);
        setDefaultItemMenu();
        hoverMenuItem(btnSanPham);
    }//GEN-LAST:event_btnSanPhamActionPerformed

    private void SanPhamTittle1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SanPhamTittle1MousePressed
        setLocationHr(cardSanPham, SanPhamHr, SanPhamTittle1);
    }//GEN-LAST:event_SanPhamTittle1MousePressed

    private void SanPhamTittle3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SanPhamTittle3MousePressed
        setLocationHr(cardLoai, SanPhamHr, SanPhamTittle3);
    }//GEN-LAST:event_SanPhamTittle3MousePressed

    private void SanPhamTittle2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SanPhamTittle2MousePressed
        //setLocationHr(cardHangSanXuat, SanPhamHr, 440);
    }//GEN-LAST:event_SanPhamTittle2MousePressed

    private void HoaDonTittle1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HoaDonTittle1MousePressed
        setLocationHr(cardHoaDon, HoaDonHr, HoaDonTittle1);
    }//GEN-LAST:event_HoaDonTittle1MousePressed

    private void HoaDonTittle2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HoaDonTittle2MousePressed
        setLocationHr(cardHoaDonCho, HoaDonHr, HoaDonTittle2);
    }//GEN-LAST:event_HoaDonTittle2MousePressed

    void hoverMenuItem(Button btn) {
        //Font newFont = new Font("Tahoma", Font.BOLD, 14);
        //btn.setFont(newFont);
        if (btn == btnItemMenu) {
            return;
        }
        btn.setForeground(chooserMenuItem);
    }

    void nonHoverMenuItem(Button btn) {
        if (btnItemMenu == btn) {
            return;
        }
        //Font newFont = new Font("Tahoma", Font.PLAIN, 14);
        //btn.setFont(newFont);
        btn.setForeground(defaultMenuItem);
    }

    void setDefaultItemMenu() {
        this.arrBtn = new Button[]{btnTrangChu, btnTaiKhoan, btnSanPham, btnGioiThieu, btnHoaDon, btnGiamGia};
        for (Button btn : this.arrBtn) {
            nonHoverMenuItem(btn);
        }
    }

    private void btnTrangChuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTrangChuMouseEntered
        hoverMenuItem(btnTrangChu);
    }//GEN-LAST:event_btnTrangChuMouseEntered

    private void btnTrangChuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTrangChuMouseExited
        nonHoverMenuItem(btnTrangChu);
    }//GEN-LAST:event_btnTrangChuMouseExited

    private void btnTrangChuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangChuActionPerformed
        btnItemMenu = btnTrangChu;
        chooserMenu(0);
        setDefaultItemMenu();
        hoverMenuItem(btnTrangChu);
    }//GEN-LAST:event_btnTrangChuActionPerformed

    private void btnTaiKhoanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTaiKhoanMouseEntered
        hoverMenuItem(btnTaiKhoan);
    }//GEN-LAST:event_btnTaiKhoanMouseEntered

    private void btnTaiKhoanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTaiKhoanMouseExited
        nonHoverMenuItem(btnTaiKhoan);
    }//GEN-LAST:event_btnTaiKhoanMouseExited

    private void btnTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaiKhoanActionPerformed
        btnItemMenu = btnTaiKhoan;
        chooserMenu(1);
        setDefaultItemMenu();
        hoverMenuItem(btnTaiKhoan);
    }//GEN-LAST:event_btnTaiKhoanActionPerformed

    private void btnSanPhamMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSanPhamMouseEntered
        hoverMenuItem(btnSanPham);
    }//GEN-LAST:event_btnSanPhamMouseEntered

    private void btnSanPhamMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSanPhamMouseExited
        nonHoverMenuItem(btnSanPham);
    }//GEN-LAST:event_btnSanPhamMouseExited

    private void btnHoaDonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoaDonMouseEntered
        hoverMenuItem(btnHoaDon);
    }//GEN-LAST:event_btnHoaDonMouseEntered

    private void btnHoaDonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoaDonMouseExited
        nonHoverMenuItem(btnHoaDon);
    }//GEN-LAST:event_btnHoaDonMouseExited

    private void btnHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoaDonActionPerformed
        btnItemMenu = btnHoaDon;
        chooserMenu(3);
        setDefaultItemMenu();
        hoverMenuItem(btnHoaDon);
    }//GEN-LAST:event_btnHoaDonActionPerformed

    private void btnGiamGiaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGiamGiaMouseEntered
        hoverMenuItem(btnGiamGia);
    }//GEN-LAST:event_btnGiamGiaMouseEntered

    private void btnGiamGiaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGiamGiaMouseExited
        nonHoverMenuItem(btnGiamGia);
    }//GEN-LAST:event_btnGiamGiaMouseExited

    private void btnGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGiamGiaActionPerformed
        btnItemMenu = btnGiamGia;
        chooserMenu(4);
        setDefaultItemMenu();
        hoverMenuItem(btnGiamGia);
    }//GEN-LAST:event_btnGiamGiaActionPerformed

    private void opacityMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_opacityMouseClicked
        //closeMenu();
    }//GEN-LAST:event_opacityMouseClicked

    private void btnGioiThieuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGioiThieuMouseEntered
        hoverMenuItem(btnGioiThieu);
    }//GEN-LAST:event_btnGioiThieuMouseEntered

    private void btnGioiThieuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGioiThieuMouseExited
        nonHoverMenuItem(btnGioiThieu);
    }//GEN-LAST:event_btnGioiThieuMouseExited

    private void btnGioiThieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGioiThieuActionPerformed
        btnItemMenu = btnGioiThieu;
        chooserMenu(5);
        setDefaultItemMenu();
        hoverMenuItem(btnGioiThieu);
    }//GEN-LAST:event_btnGioiThieuActionPerformed

    private void btnDangXuatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDangXuatMouseEntered
        hoverMenuItem(btnDangXuat);
    }//GEN-LAST:event_btnDangXuatMouseEntered

    private void btnDangXuatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDangXuatMouseExited
        nonHoverMenuItem(btnDangXuat);
    }//GEN-LAST:event_btnDangXuatMouseExited

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        btnItemMenu = btnDangXuat;
        chooserMenu(6);
        setDefaultItemMenu();
        hoverMenuItem(btnDangXuat);
    }//GEN-LAST:event_btnDangXuatActionPerformed

    private void GiamGiaTittle1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GiamGiaTittle1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_GiamGiaTittle1MousePressed

    private void btnTrangChuMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTrangChuMouseReleased
        closeMenu();
    }//GEN-LAST:event_btnTrangChuMouseReleased

    private void btnGioiThieuMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGioiThieuMouseReleased
        closeMenu();
    }//GEN-LAST:event_btnGioiThieuMouseReleased

    private void btnTaiKhoanMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTaiKhoanMouseReleased
        closeMenu();
    }//GEN-LAST:event_btnTaiKhoanMouseReleased

    private void btnSanPhamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSanPhamMouseReleased
        closeMenu();
    }//GEN-LAST:event_btnSanPhamMouseReleased

    private void btnHoaDonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoaDonMouseReleased
        closeMenu();
    }//GEN-LAST:event_btnHoaDonMouseReleased

    private void btnGiamGiaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGiamGiaMouseReleased
        closeMenu();
    }//GEN-LAST:event_btnGiamGiaMouseReleased

    private void KhachHangTittle1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KhachHangTittle1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KhachHangTittle1MousePressed

    private void ThongKeTittle1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ThongKeTittle1MousePressed
        setLocationHr(cardThongKeDoanhThu, ThongKeHr, ThongKeTittle1);
    }//GEN-LAST:event_ThongKeTittle1MousePressed

    private void ThongKeTittle2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ThongKeTittle2MousePressed
        setLocationHr(cardThongKeSanPham, ThongKeHr, ThongKeTittle2);
    }//GEN-LAST:event_ThongKeTittle2MousePressed

    private void SanPhamTittle2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SanPhamTittle2MouseReleased
        //setLocationHr(cardHangSanXuat, SanPhamHr, 440);
        setLocationHr(cardHangSanXuat, SanPhamHr, SanPhamTittle2);
    }//GEN-LAST:event_SanPhamTittle2MouseReleased

    private void btnThongKeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThongKeMouseEntered
        hoverMenuItem(btnThongKe);
    }//GEN-LAST:event_btnThongKeMouseEntered

    private void btnThongKeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThongKeMouseExited
        nonHoverMenuItem(btnThongKe);
    }//GEN-LAST:event_btnThongKeMouseExited

    private void btnThongKeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThongKeMouseReleased
        closeMenu();
    }//GEN-LAST:event_btnThongKeMouseReleased

    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeActionPerformed
        btnItemMenu = btnThongKe;
        chooserMenu(8);
        setDefaultItemMenu();
        hoverMenuItem(btnThongKe);
    }//GEN-LAST:event_btnThongKeActionPerformed

    private void button7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button7ActionPerformed

    private void btnLastLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastLoaiActionPerformed
        lastLoai();
    }//GEN-LAST:event_btnLastLoaiActionPerformed

    private void btnLastHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLastHangActionPerformed

    private void btnLastSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLastSPActionPerformed

    private void button34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button34ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button34ActionPerformed

    private void TaiKhoantittle4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TaiKhoantittle4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TaiKhoantittle4MouseClicked

    private void TaiKhoantittle4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TaiKhoantittle4MousePressed
        setLocationHr(cardKhachHang, TaiKhoanHr, TaiKhoantittle4);
    }//GEN-LAST:event_TaiKhoantittle4MousePressed

    private void button46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button46ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button46ActionPerformed

    private void tblHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHangMouseClicked
        countClick++;
        if (countClick == 1) {
            this.row = tblHang.getSelectedRow();
            editHang();
        }
    }//GEN-LAST:event_tblHangMouseClicked

    private void btnFirstHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFirstHangMouseClicked
        firstHang();
    }//GEN-LAST:event_btnFirstHangMouseClicked

    private void btnPrevHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrevHangMouseClicked
        prevHang();
    }//GEN-LAST:event_btnPrevHangMouseClicked

    private void btnNextHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNextHangMouseClicked
        nextHang();
    }//GEN-LAST:event_btnNextHangMouseClicked

    private void btnLastHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLastHangMouseClicked
        lastHang();
    }//GEN-LAST:event_btnLastHangMouseClicked

    private void btnLamMoiHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiHangActionPerformed
        clearFormHang();
    }//GEN-LAST:event_btnLamMoiHangActionPerformed

    private void btnThemHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemHangActionPerformed
        if (checkHang() == true) {
            insertHang();
        }
    }//GEN-LAST:event_btnThemHangActionPerformed

    private void btnCapNhatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatHangActionPerformed
        if (checkHang() == true) {
            updateHang();
        }
    }//GEN-LAST:event_btnCapNhatHangActionPerformed

    private void btnXoaHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaHangActionPerformed
        deleteHang();
    }//GEN-LAST:event_btnXoaHangActionPerformed

    private void btnLamMoiLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiLoaiActionPerformed
        clearFormLoai();
    }//GEN-LAST:event_btnLamMoiLoaiActionPerformed

    private void btnThemLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemLoaiActionPerformed
        if (checkLoai() == true) {
            insertLoai();
        }
    }//GEN-LAST:event_btnThemLoaiActionPerformed

    private void btnCapNhatLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatLoaiActionPerformed
        if (checkLoai() == true) {
            updateLoai();
        }
    }//GEN-LAST:event_btnCapNhatLoaiActionPerformed

    private void btnXoaLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaLoaiActionPerformed
        deleteLoai();
    }//GEN-LAST:event_btnXoaLoaiActionPerformed

    private void btnNextLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextLoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNextLoaiActionPerformed

    private void tblLoaiHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLoaiHangMouseClicked
        countClick++;
        if (countClick == 1) {
            this.row = tblLoaiHang.getSelectedRow();
            editLoai();
        }
    }//GEN-LAST:event_tblLoaiHangMouseClicked

    private void btnLastLoaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLastLoaiMouseClicked
        lastLoai();
    }//GEN-LAST:event_btnLastLoaiMouseClicked

    private void btnNextLoaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNextLoaiMouseClicked
        nextLoai();
    }//GEN-LAST:event_btnNextLoaiMouseClicked

    private void btnPrevLoaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrevLoaiMouseClicked
        prevLoai();
    }//GEN-LAST:event_btnPrevLoaiMouseClicked

    private void btnFirstLoaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFirstLoaiMouseClicked
        firstLoai();
    }//GEN-LAST:event_btnFirstLoaiMouseClicked

    private void btnLamMoiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiSPActionPerformed
       clearFormSanPham();
    }//GEN-LAST:event_btnLamMoiSPActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        countClick++;
        if (countClick == 1) {
            this.row = tblSanPham.getSelectedRow();
            editSanPham();
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnFirstSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFirstSPMouseClicked
        firstSanPham();        // TODO add your handling code here:
    }//GEN-LAST:event_btnFirstSPMouseClicked

    private void btnPrevSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrevSPMouseClicked
        prevSanPham();        // TODO add your handling code here:
    }//GEN-LAST:event_btnPrevSPMouseClicked

    private void btnNextSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNextSPMouseClicked
        nextSanPham();        // TODO add your handling code here:
    }//GEN-LAST:event_btnNextSPMouseClicked

    private void btnLastSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLastSPMouseClicked
        lastSanPham();        // TODO add your handling code here:
    }//GEN-LAST:event_btnLastSPMouseClicked

    private void lblAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhMouseClicked
        choseImage();
    }//GEN-LAST:event_lblAnhMouseClicked

    private void btnThemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPActionPerformed
         if(checkSP()== true){
            insertSanPham();
        }
                // TODO add your handling code here:
    }//GEN-LAST:event_btnThemSPActionPerformed

    private void btnCapNhatSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatSPActionPerformed
        if(checkSP()== true){
            updateSanPham(); 
        }
               // TODO add your handling code here:
    }//GEN-LAST:event_btnCapNhatSPActionPerformed

    private void btnXoaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSPActionPerformed
        deleteSanPham();        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaSPActionPerformed

    //San Pham
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel GiamGiaHr;
    private javax.swing.JLabel GiamGiaTittle1;
    private javax.swing.JLabel GioiThieuHr;
    private javax.swing.JLabel GioiThieutittle1;
    private javax.swing.JLabel GioiThieutittle2;
    private javax.swing.JLabel HoaDonHr;
    private javax.swing.JLabel HoaDonTittle1;
    private javax.swing.JLabel HoaDonTittle2;
    private javax.swing.JLabel KhachHangHr;
    private javax.swing.JLabel KhachHangTittle1;
    private javax.swing.JLabel SanPhamHr;
    private javax.swing.JLabel SanPhamTittle1;
    private javax.swing.JLabel SanPhamTittle2;
    private javax.swing.JLabel SanPhamTittle3;
    private javax.swing.JLabel TaiKhoanHr;
    private javax.swing.JLabel TaiKhoantittle2;
    private javax.swing.JLabel TaiKhoantittle3;
    private javax.swing.JLabel TaiKhoantittle4;
    private javax.swing.JLabel ThongKeHr;
    private javax.swing.JLabel ThongKeTittle1;
    private javax.swing.JLabel ThongKeTittle2;
    private javax.swing.JLabel TrangChuHr;
    private javax.swing.JLabel TrangChuTittle2;
    private com.swing.Button btnCapNhatHang;
    private com.swing.Button btnCapNhatLoai;
    private com.swing.Button btnCapNhatSP;
    private com.swing.Button btnDangXuat;
    private com.swing.Button btnFirstHang;
    private com.swing.Button btnFirstLoai;
    private com.swing.Button btnFirstSP;
    private com.swing.Button btnGiamGia;
    private com.swing.Button btnGioiThieu;
    private com.swing.Button btnHoaDon;
    private com.swing.Button btnLamMoiHang;
    private com.swing.Button btnLamMoiLoai;
    private com.swing.Button btnLamMoiSP;
    private com.swing.Button btnLastHang;
    private com.swing.Button btnLastLoai;
    private com.swing.Button btnLastSP;
    private com.swing.Button btnMenu;
    private com.swing.Button btnNextHang;
    private com.swing.Button btnNextLoai;
    private com.swing.Button btnNextSP;
    private com.swing.Button btnPrevHang;
    private com.swing.Button btnPrevLoai;
    private com.swing.Button btnPrevSP;
    private com.swing.Button btnSanPham;
    private com.swing.Button btnTaiKhoan;
    private com.swing.Button btnThemHang;
    private com.swing.Button btnThemLoai;
    private com.swing.Button btnThemSP;
    private com.swing.Button btnThongKe;
    private com.swing.Button btnTrangChu;
    private com.swing.Button btnXoaHang;
    private com.swing.Button btnXoaLoai;
    private com.swing.Button btnXoaSP;
    private com.swing.Button button2;
    private com.swing.Button button3;
    private com.swing.Button button30;
    private com.swing.Button button31;
    private com.swing.Button button33;
    private com.swing.Button button34;
    private com.swing.Button button35;
    private com.swing.Button button36;
    private com.swing.Button button37;
    private com.swing.Button button38;
    private com.swing.Button button39;
    private com.swing.Button button4;
    private com.swing.Button button40;
    private com.swing.Button button41;
    private com.swing.Button button42;
    private com.swing.Button button43;
    private com.swing.Button button44;
    private com.swing.Button button45;
    private com.swing.Button button46;
    private com.swing.Button button5;
    private com.swing.Button button51;
    private com.swing.Button button6;
    private com.swing.Button button7;
    private com.swing.Button button8;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.swing.PanelRound cardGiamGia;
    private com.swing.PanelRound cardGioiThieuSanPham;
    private com.swing.PanelRound cardGioiThieuThanhVien;
    private com.swing.PanelRound cardHangSanXuat;
    private com.swing.PanelRound cardHangSanXuat1;
    private com.swing.PanelRound cardHoaDon;
    private com.swing.PanelRound cardHoaDonCho;
    private com.swing.PanelRound cardKhachHang;
    private com.swing.PanelRound cardLoai;
    private com.swing.PanelRound cardLoai1;
    private com.swing.PanelRound cardLoai2;
    private javax.swing.JPanel cardMenubar;
    private javax.swing.JPanel cardMenubarGiamGia;
    private javax.swing.JPanel cardMenubarGioiThieu;
    private javax.swing.JPanel cardMenubarHoaDon;
    private javax.swing.JPanel cardMenubarKhachHang;
    private javax.swing.JPanel cardMenubarSanPham;
    private javax.swing.JPanel cardMenubarTaiKhoan;
    private javax.swing.JPanel cardMenubarThongKe;
    private javax.swing.JPanel cardMenubarTrangChu;
    private com.swing.PanelRound cardSanPham;
    private com.swing.PanelRound cardTaiKhoanChucVu;
    private com.swing.PanelRound cardTaiKhoanNhanVien;
    private com.swing.PanelRound cardThongKeDoanhThu;
    private com.swing.PanelRound cardThongKeSanPham;
    private javax.swing.JPanel cardTrangChu;
    private com.swing.PanelRound cardTrangChuNoiBat;
    private com.swing.PanelRound cardTrangChuTongQuan;
    private com.swing.Combobox cboMaHang;
    private com.swing.Combobox cboMaLoai;
    private com.swing.Combobox combobox1;
    private com.swing.Combobox combobox2;
    private com.swing.Combobox combobox6;
    private com.swing.Combobox combobox7;
    private com.swing.Combobox combobox8;
    private com.swing.Combobox combobox9;
    private com.swing.EditButton editButton10;
    private com.swing.EditButton editButton11;
    private com.swing.EditButton editButton14;
    private com.swing.EditButton editButton15;
    private com.swing.EditButton editButton16;
    private com.swing.EditButton editButton17;
    private com.swing.EditButton editButton18;
    private com.swing.EditButton editButton19;
    private com.swing.EditButton editButton8;
    private com.swing.EditButton editButton9;
    private com.frame.Header header2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel jlbClose;
    private javax.swing.JLabel jlbState;
    private javax.swing.JPanel jplContainer;
    private javax.swing.JPanel jplLose;
    private javax.swing.JPanel jplMenubar;
    private javax.swing.JPanel jplState;
    private javax.swing.JPanel jplTitle;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JLabel lblMessage1;
    private javax.swing.JLabel lblMessage2;
    private javax.swing.JLabel lblMessage3;
    private javax.swing.JLabel lblMessage4;
    private javax.swing.JLabel lblrecordHang;
    private javax.swing.JLabel lblrecordLoai;
    private javax.swing.JLabel lblrecordSP;
    private javax.swing.JLabel opacity;
    private com.swing.PanelRound panelRound1;
    private com.swing.PanelRound panelRound2;
    private com.swing.PanelRound panelRound3;
    private com.swing.PanelRound panelRound4;
    private com.swing.PanelRound panelRound5;
    private com.swing.PanelRound panelRound6;
    private com.swing.PanelRound panelRound7;
    private javax.swing.JPanel pnMenu;
    private com.hicode.switchbutton.SwitchButton switchButton1;
    private javax.swing.JTable tblHang;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTable tblKhachHang4;
    private javax.swing.JTable tblKhachHang5;
    private javax.swing.JTable tblLoaiHang;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtGiaBanSP;
    private javax.swing.JTextField txtGiaNhapSP;
    private javax.swing.JTextField txtMaHang;
    private javax.swing.JTextField txtMaLoai;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextArea txtMoTaSP;
    private javax.swing.JTextField txtNgayNhapSP;
    private javax.swing.JTextField txtTenHang;
    private javax.swing.JTextField txtTenLoai;
    private javax.swing.JTextField txtTenSP;
    // End of variables declaration//GEN-END:variables
}
