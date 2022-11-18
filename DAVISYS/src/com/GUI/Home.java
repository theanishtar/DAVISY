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
import com.dao.ThongKeDAO;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu.Separator;
import com.swing.*;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
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
    private DrawerController drawer;

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

    ThongKeDAO TKdao = new ThongKeDAO();

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
        Clock();
        settingTable();
        initThongKe();
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
            cardTaiKhoanNhanVien, cardTaiKhoanQuanLi,
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
            if (cardTaiKhoanQuanLi.isVisible()) {
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

    public void initThongKe() {
        fillCboDay_ThongKe();
        fillCboMonth_ThongKe();
        fillCboYear_ThongKe();
        fillTableNhanVienXX();
    }

    public void fillCboDay_ThongKe() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboDay.getModel();
        model.removeAllElements();
        for (int i = 1; i < 13; i++) {
            model.addElement(String.valueOf(i));
        }
    }

    public void fillCboMonth_ThongKe() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboMonth.getModel();
        model.removeAllElements();
        for (int i = 1; i < 32; i++) {
            model.addElement(String.valueOf(i));
        }
    }

    public void fillCboYear_ThongKe() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboYear.getModel();
        model.removeAllElements();
        for (int i = 2020; i < 2023; i++) {
            model.addElement(String.valueOf(i));
        }
    }

    public void fillTableSPBanChay() {
        DefaultTableModel model = (DefaultTableModel) tblSPBanChay.getModel();
        model.setRowCount(0);
        String day = (String) cboDay.getSelectedItem();
        String month = (String) cboMonth.getSelectedItem();
        String year = (String) cboYear.getSelectedItem();
        List<Object[]> list = TKdao.getSPBanChay(day, month, year);
        for (Object[] row : list) {
            model.addRow(row);
        }
    }

    public void fillTableNhanVienXX() {
        int countNV = 0;
        List<Object[]> list = TKdao.getNhanVienXX();
        for (Object[] row : list) {
            countNV++;
            if (countNV == 1) {
                lblNV1.setText(String.valueOf(row[0]));
            }
            if (countNV == 2) {
                lblNV2.setText(String.valueOf(row[0]));
            }
            if (countNV == 3) {
                lblNV3.setText(String.valueOf(row[0]));
            }
        }
    }

    void Clock() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Date now = new Date();
                        SimpleDateFormat formater = new SimpleDateFormat();
                        formater.applyPattern("hh:mm:ss aa");
                        String time = formater.format(now);
                        lblTime.setText(time);
                        long millis = System.currentTimeMillis();
                        java.sql.Date day = new java.sql.Date(millis);
                        SimpleDateFormat formater2 = new SimpleDateFormat();
                        formater2.applyPattern("dd-MM-yyyy");
                        String dayt = formater2.format(day);
                        lblDay.setText(String.valueOf(dayt));
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        break;
                    }

                }
            }
        }).start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        textField3 = new com.swing.TextField();
        jplContainer = new javax.swing.JPanel();
        cardTrangChu = new javax.swing.JPanel();
        cardThongKeDoanhThu = new com.swing.PanelRound();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblSPBanChay1 = new javax.swing.JTable();
        jLabel29 = new javax.swing.JLabel();
        cboYear1 = new com.swing.Combobox();
        jLabel31 = new javax.swing.JLabel();
        cboDay1 = new com.swing.Combobox();
        jLabel30 = new javax.swing.JLabel();
        cboMonth1 = new com.swing.Combobox();
        cardThongKeSanPham = new com.swing.PanelRound();
        jLabel34 = new javax.swing.JLabel();
        cboYear = new com.swing.Combobox();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSPBanChay = new javax.swing.JTable();
        jLabel33 = new javax.swing.JLabel();
        cboDay = new com.swing.Combobox();
        jLabel32 = new javax.swing.JLabel();
        cboMonth = new com.swing.Combobox();
        cardTrangChuNoiBat = new com.swing.PanelRound();
        jLabel27 = new javax.swing.JLabel();
        cardTaiKhoanQuanLi = new com.swing.PanelRound();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cardTaiKhoanNhanVien = new com.swing.PanelRound();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        cardSanPham = new com.swing.PanelRound();
        cardHangSanXuat1 = new com.swing.PanelRound();
        cardLoai2 = new com.swing.PanelRound();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblKhachHang3 = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        button23 = new com.swing.Button();
        jLabel78 = new javax.swing.JLabel();
        button24 = new com.swing.Button();
        button25 = new com.swing.Button();
        button26 = new com.swing.Button();
        jLabel79 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        button27 = new com.swing.Button();
        button28 = new com.swing.Button();
        button29 = new com.swing.Button();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        textField16 = new com.swing.TextField();
        textField17 = new com.swing.TextField();
        combobox4 = new com.swing.Combobox();
        combobox5 = new com.swing.Combobox();
        textField18 = new com.swing.TextField();
        textField19 = new com.swing.TextField();
        textField20 = new com.swing.TextField();
        textField21 = new com.swing.TextField();
        jLabel28 = new javax.swing.JLabel();
        cardHangSanXuat = new com.swing.PanelRound();
        cardLoai1 = new com.swing.PanelRound();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblKhachHang2 = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel72 = new javax.swing.JLabel();
        button16 = new com.swing.Button();
        jLabel73 = new javax.swing.JLabel();
        button17 = new com.swing.Button();
        button18 = new com.swing.Button();
        button19 = new com.swing.Button();
        jLabel74 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        button20 = new com.swing.Button();
        button21 = new com.swing.Button();
        button22 = new com.swing.Button();
        jPanel10 = new javax.swing.JPanel();
        combobox3 = new com.swing.Combobox();
        editButton12 = new com.swing.EditButton();
        editButton13 = new com.swing.EditButton();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        textField14 = new com.swing.TextField();
        textField15 = new com.swing.TextField();
        cardLoai = new com.swing.PanelRound();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblKhachHang1 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel67 = new javax.swing.JLabel();
        button9 = new com.swing.Button();
        jLabel68 = new javax.swing.JLabel();
        button10 = new com.swing.Button();
        button11 = new com.swing.Button();
        button12 = new com.swing.Button();
        jLabel69 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        button13 = new com.swing.Button();
        button14 = new com.swing.Button();
        button15 = new com.swing.Button();
        jPanel7 = new javax.swing.JPanel();
        combobox2 = new com.swing.Combobox();
        editButton10 = new com.swing.EditButton();
        editButton11 = new com.swing.EditButton();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        textField12 = new com.swing.TextField();
        textField13 = new com.swing.TextField();
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
        textField2 = new com.swing.TextField();
        jLabel65 = new javax.swing.JLabel();
        textField7 = new com.swing.TextField();
        textField8 = new com.swing.TextField();
        textField4 = new com.swing.TextField();
        jLabel60 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        button2 = new com.swing.Button();
        jLabel62 = new javax.swing.JLabel();
        button5 = new com.swing.Button();
        button6 = new com.swing.Button();
        button7 = new com.swing.Button();
        jLabel66 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        button3 = new com.swing.Button();
        button4 = new com.swing.Button();
        button8 = new com.swing.Button();
        jPanel4 = new javax.swing.JPanel();
        combobox1 = new com.swing.Combobox();
        editButton8 = new com.swing.EditButton();
        editButton9 = new com.swing.EditButton();
        cardTrangChuTongQuan = new com.swing.PanelRound();
        panelRound1 = new com.swing.PanelRound();
        lblTime = new javax.swing.JLabel();
        lblDay = new javax.swing.JLabel();
        panelRound5 = new com.swing.PanelRound();
        panelRound6 = new com.swing.PanelRound();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        panelRound7 = new com.swing.PanelRound();
        jLabel12 = new javax.swing.JLabel();
        panelRound4 = new com.swing.PanelRound();
        jLabel8 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        lblNV1 = new javax.swing.JLabel();
        lblNV2 = new javax.swing.JLabel();
        lblNV3 = new javax.swing.JLabel();
        lblMessage1 = new javax.swing.JLabel();
        lblMessage2 = new javax.swing.JLabel();
        lblMessage3 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        panelRound2 = new com.swing.PanelRound();
        panelRound3 = new com.swing.PanelRound();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnMenu.setBackground(new java.awt.Color(255, 255, 255));
        pnMenu.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(0, 0, 0)));

        btnTrangChu.setForeground(new java.awt.Color(0, 153, 51));
        btnTrangChu.setText("Trang chủ");
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
        cardMenubarTrangChu.add(TrangChuTittle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 110, 30));

        TrangChuHr.setBackground(new java.awt.Color(0, 153, 0));
        TrangChuHr.setOpaque(true);
        cardMenubarTrangChu.add(TrangChuHr, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 110, 5));

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
        TaiKhoantittle3.setText("Khách Hàng");
        TaiKhoantittle3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TaiKhoantittle3MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TaiKhoantittle3MousePressed(evt);
            }
        });
        cardMenubarTaiKhoan.add(TaiKhoantittle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 110, 30));

        TaiKhoanHr.setBackground(new java.awt.Color(0, 153, 0));
        TaiKhoanHr.setOpaque(true);
        cardMenubarTaiKhoan.add(TaiKhoanHr, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 43, 110, 5));

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

        textField3.setLabelText("Tìm kiếm");
        cardMenubarThongKe.add(textField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 0, 240, -1));

        cardMenubar.add(cardMenubarThongKe, "card2");

        jplMenubar.add(cardMenubar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 1140, 50));

        jPanel1.add(jplMenubar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1220, -1));

        jplContainer.setLayout(new java.awt.CardLayout());

        cardTrangChu.setBackground(new java.awt.Color(204, 204, 204));
        cardTrangChu.setLayout(new java.awt.CardLayout());

        cardThongKeDoanhThu.setBackground(new java.awt.Color(255, 255, 255));
        cardThongKeDoanhThu.setRoundBottomLeft(30);
        cardThongKeDoanhThu.setRoundBottomRight(30);
        cardThongKeDoanhThu.setRoundTopLeft(30);
        cardThongKeDoanhThu.setRoundTopRight(20);
        cardThongKeDoanhThu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblSPBanChay1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "MAHD", "NGAYLAP", "TENDN", "THANHTIEN"
            }
        ));
        jScrollPane6.setViewportView(tblSPBanChay1);

        cardThongKeDoanhThu.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 1180, 500));

        jLabel29.setText("Năm");
        cardThongKeDoanhThu.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 40, 30));

        cboYear1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2022" }));
        cboYear1.setLabeText("");
        cboYear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboYear1ActionPerformed(evt);
            }
        });
        cardThongKeDoanhThu.add(cboYear1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 24, 130, 40));

        jLabel31.setText("Tháng");
        cardThongKeDoanhThu.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 40, 30));

        cboDay1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10" }));
        cboDay1.setLabeText("");
        cboDay1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDay1ActionPerformed(evt);
            }
        });
        cardThongKeDoanhThu.add(cboDay1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 24, 130, 40));

        jLabel30.setText("Ngày");
        cardThongKeDoanhThu.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 40, 30));

        cboMonth1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "6" }));
        cboMonth1.setLabeText("");
        cboMonth1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMonth1ActionPerformed(evt);
            }
        });
        cardThongKeDoanhThu.add(cboMonth1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 24, 130, 40));

        cardTrangChu.add(cardThongKeDoanhThu, "card3");

        cardThongKeSanPham.setBackground(new java.awt.Color(255, 255, 255));
        cardThongKeSanPham.setRoundBottomLeft(30);
        cardThongKeSanPham.setRoundBottomRight(30);
        cardThongKeSanPham.setRoundTopLeft(30);
        cardThongKeSanPham.setRoundTopRight(20);
        cardThongKeSanPham.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel34.setText("Năm");
        cardThongKeSanPham.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 40, 30));

        cboYear.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2022" }));
        cboYear.setLabeText("");
        cboYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboYearActionPerformed(evt);
            }
        });
        cardThongKeSanPham.add(cboYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, 130, -1));

        tblSPBanChay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "MASP", "TENSP", "LUOTBAN"
            }
        ));
        jScrollPane2.setViewportView(tblSPBanChay);

        cardThongKeSanPham.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 1180, 500));

        jLabel33.setText("Tháng");
        cardThongKeSanPham.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 40, 30));

        cboDay.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10" }));
        cboDay.setLabeText("");
        cboDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDayActionPerformed(evt);
            }
        });
        cardThongKeSanPham.add(cboDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 130, -1));

        jLabel32.setText("Ngày");
        cardThongKeSanPham.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 30, 30));

        cboMonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "6" }));
        cboMonth.setLabeText("");
        cboMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMonthActionPerformed(evt);
            }
        });
        cardThongKeSanPham.add(cboMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 130, -1));

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

        cardTaiKhoanQuanLi.setBackground(new java.awt.Color(255, 255, 255));
        cardTaiKhoanQuanLi.setRoundBottomLeft(30);
        cardTaiKhoanQuanLi.setRoundBottomRight(30);
        cardTaiKhoanQuanLi.setRoundTopLeft(30);
        cardTaiKhoanQuanLi.setRoundTopRight(20);
        cardTaiKhoanQuanLi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("<html> <h2>Phần mềm được phát triển bởi <a href=\"https://www.facebook.com/davisy.dev\">DAVISY TEAM</a></h2>  <!html>");
        cardTaiKhoanQuanLi.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 500, -1));

        jLabel9.setText("<html>  <h3> Phần mềm là dự án trong học kỳ 4 của chúng tôi tại  <a href=\"\">FPT Polytechnic College</a> </h3>  <!html>");
        cardTaiKhoanQuanLi.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, 380, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/businessman.png"))); // NOI18N
        cardTaiKhoanQuanLi.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, -1, -1));

        jLabel11.setText("<html>\n<h3>Các tư liệu tham khảo</h3>  \n<ul>  \n <li> <h4> <a href=\"https://github.com/k33ptoo/\">KeepToo</a></h4></li>  \n<li> <h4><a href=\"https://github.com/DJ-Raven\">DJ-Raven</a></h4>\n</li>\n </ul> \n <!html>");
        cardTaiKhoanQuanLi.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, 320, 110));

        jLabel13.setText("<html>  <h3>Bạn có thể tham khảo giao diện của chúng tôi tại <a href=\"https://www.github.com/theanishtar\"> đây</a> </h3>  <!html>");
        cardTaiKhoanQuanLi.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 450, -1));

        cardTrangChu.add(cardTaiKhoanQuanLi, "card6");

        cardTaiKhoanNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        cardTaiKhoanNhanVien.setRoundBottomLeft(30);
        cardTaiKhoanNhanVien.setRoundBottomRight(30);
        cardTaiKhoanNhanVien.setRoundTopLeft(30);
        cardTaiKhoanNhanVien.setRoundTopRight(20);
        cardTaiKhoanNhanVien.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("<html> <h2>Phần mềm được phát triển bởi <a href=\"https://www.facebook.com/davisy.dev\">DAVISY TEAM</a></h2>  <!html>");
        cardTaiKhoanNhanVien.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 500, -1));

        jLabel15.setText("<html>  <h3> Phần mềm là dự án trong học kỳ 4 của chúng tôi tại  <a href=\"\">FPT Polytechnic College</a> </h3>  <!html>");
        cardTaiKhoanNhanVien.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, 380, -1));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/businessman.png"))); // NOI18N
        cardTaiKhoanNhanVien.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, -1, -1));

        jLabel18.setText("<html>\n<h3>Các tư liệu tham khảo</h3>  \n<ul>  \n <li> <h4> <a href=\"https://github.com/k33ptoo/\">KeepToo</a></h4></li>  \n<li> <h4><a href=\"https://github.com/DJ-Raven\">DJ-Raven</a></h4>\n</li>\n </ul> \n <!html>");
        cardTaiKhoanNhanVien.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, 320, 110));

        jLabel20.setText("<html>  <h3>Bạn có thể tham khảo giao diện của chúng tôi tại <a href=\"https://www.github.com/theanishtar\"> đây</a> </h3>  <!html>");
        cardTaiKhoanNhanVien.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 450, -1));

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

        tblKhachHang3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tblKhachHang3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"a", "â", null, null, null, null, null, null},
                {"d", "c", null, null, null, null, null, null},
                {"z", "c", null, null, null, null, null, null},
                {"a", "o", null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8"
            }
        ));
        tblKhachHang3.setGridColor(new java.awt.Color(255, 255, 255));
        tblKhachHang3.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblKhachHang3.getTableHeader().setResizingAllowed(false);
        tblKhachHang3.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(tblKhachHang3);

        cardLoai2.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 1150, 220));

        jPanel11.setBackground(new java.awt.Color(204, 204, 255));

        jLabel77.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(204, 0, 51));
        jLabel77.setText("2 trên 10");

        button23.setBackground(new java.awt.Color(204, 204, 255));
        button23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/left-arrow.png"))); // NOI18N

        jLabel78.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel78.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/visits (1).png"))); // NOI18N
        jLabel78.setText("Bảng ghi:");

        button24.setBackground(new java.awt.Color(204, 204, 255));
        button24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/left-arrow.png"))); // NOI18N

        button25.setBackground(new java.awt.Color(204, 204, 255));
        button25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/left-arrow.png"))); // NOI18N

        button26.setBackground(new java.awt.Color(204, 204, 255));
        button26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/left-arrow.png"))); // NOI18N
        button26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button26ActionPerformed(evt);
            }
        });

        jLabel79.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(204, 0, 51));
        jLabel79.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel79.setText("BẢNG THÔNG TIN SẢN PHẨM");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(button24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(button25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(button23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(button26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel77, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel78, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel79))
                    .addComponent(button26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        cardLoai2.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 1150, 60));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 255)));

        button27.setBackground(new java.awt.Color(204, 204, 255));
        button27.setText("Thêm");

        button28.setBackground(new java.awt.Color(204, 204, 255));
        button28.setText("Cập nhật");
        button28.setEffectColor(new java.awt.Color(204, 255, 204));

        button29.setBackground(new java.awt.Color(255, 51, 102));
        button29.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 0, 51)));
        button29.setForeground(new java.awt.Color(255, 255, 255));
        button29.setText("Xóa");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button29, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button27, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button28, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(button27, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(button28, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(button29, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cardLoai2.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 60, 140, 210));

        jLabel80.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel80.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/search.png"))); // NOI18N
        jLabel80.setText("Tìm kiếm:");
        cardLoai2.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 10, 120, 30));

        jLabel81.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(153, 153, 153));
        jLabel81.setText("Lenovo");
        cardLoai2.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 10, 220, 30));

        jTextField4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardLoai2.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 10, 220, 30));

        textField16.setForeground(new java.awt.Color(204, 204, 204));
        textField16.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        textField16.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        textField16.setLabelText("Mô tả");
        textField16.setLineColor(new java.awt.Color(153, 153, 153));
        textField16.setSelectionColor(new java.awt.Color(204, 204, 204));
        cardLoai2.add(textField16, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, 260, -1));

        textField17.setForeground(new java.awt.Color(204, 204, 204));
        textField17.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        textField17.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        textField17.setLabelText("Mã sản phẩm");
        textField17.setLineColor(new java.awt.Color(153, 153, 153));
        textField17.setSelectionColor(new java.awt.Color(204, 204, 204));
        cardLoai2.add(textField17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 260, -1));

        combobox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "a", "x", "e", "f", "e", "gd", " " }));
        combobox4.setLabeText("Mã loại hàng");
        cardLoai2.add(combobox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 230, 260, 39));

        combobox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "a", "x", "e", "f", "e", "gd", " " }));
        combobox5.setLabeText("Mã hãng");
        cardLoai2.add(combobox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 160, 260, 39));

        textField18.setForeground(new java.awt.Color(204, 204, 204));
        textField18.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        textField18.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        textField18.setLabelText("Tên sản phẩm");
        textField18.setLineColor(new java.awt.Color(153, 153, 153));
        textField18.setSelectionColor(new java.awt.Color(204, 204, 204));
        cardLoai2.add(textField18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 260, -1));

        textField19.setForeground(new java.awt.Color(204, 204, 204));
        textField19.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        textField19.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        textField19.setLabelText("Giá nhập");
        textField19.setLineColor(new java.awt.Color(153, 153, 153));
        textField19.setSelectionColor(new java.awt.Color(204, 204, 204));
        cardLoai2.add(textField19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 260, -1));

        textField20.setForeground(new java.awt.Color(204, 204, 204));
        textField20.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        textField20.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        textField20.setLabelText("Giá bán");
        textField20.setLineColor(new java.awt.Color(153, 153, 153));
        textField20.setSelectionColor(new java.awt.Color(204, 204, 204));
        cardLoai2.add(textField20, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 260, -1));

        textField21.setForeground(new java.awt.Color(204, 204, 204));
        textField21.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        textField21.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        textField21.setLabelText("Ngày nhập");
        textField21.setLineColor(new java.awt.Color(153, 153, 153));
        textField21.setSelectionColor(new java.awt.Color(204, 204, 204));
        cardLoai2.add(textField21, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 260, -1));

        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Ảnh");
        jLabel28.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cardLoai2.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 70, 170, 180));

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

        tblKhachHang2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tblKhachHang2.setModel(new javax.swing.table.DefaultTableModel(
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
        tblKhachHang2.setGridColor(new java.awt.Color(255, 255, 255));
        tblKhachHang2.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblKhachHang2.getTableHeader().setResizingAllowed(false);
        tblKhachHang2.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tblKhachHang2);

        cardLoai1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 1150, 220));

        jPanel8.setBackground(new java.awt.Color(204, 204, 255));

        jLabel72.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(204, 0, 51));
        jLabel72.setText("2 trên 10");

        button16.setBackground(new java.awt.Color(204, 204, 255));
        button16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/left-arrow.png"))); // NOI18N

        jLabel73.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel73.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/visits (1).png"))); // NOI18N
        jLabel73.setText("Bảng ghi:");

        button17.setBackground(new java.awt.Color(204, 204, 255));
        button17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/left-arrow.png"))); // NOI18N

        button18.setBackground(new java.awt.Color(204, 204, 255));
        button18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/left-arrow.png"))); // NOI18N

        button19.setBackground(new java.awt.Color(204, 204, 255));
        button19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/left-arrow.png"))); // NOI18N
        button19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button19ActionPerformed(evt);
            }
        });

        jLabel74.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(204, 0, 51));
        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel74.setText("BẢNG THÔNG TIN HÃNG");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(button17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(button18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(button16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(button19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel73, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel72, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(button19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        cardLoai1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 1150, 60));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 255)));

        button20.setBackground(new java.awt.Color(204, 204, 255));
        button20.setText("Thêm");

        button21.setBackground(new java.awt.Color(204, 204, 255));
        button21.setText("Cập nhật");
        button21.setEffectColor(new java.awt.Color(204, 255, 204));

        button22.setBackground(new java.awt.Color(255, 51, 102));
        button22.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 0, 51)));
        button22.setForeground(new java.awt.Color(255, 255, 255));
        button22.setText("Xóa");

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        combobox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "a", "x", "e", "f", "e", "gd", " " }));
        combobox3.setLabeText("Sắp xếp theo");

        editButton12.setBackground(new java.awt.Color(102, 204, 255));
        editButton12.setText("Tên hãng");

        editButton13.setBackground(new java.awt.Color(102, 204, 255));
        editButton13.setText("Mã hãng");
        editButton13.setMargin(new java.awt.Insets(5, 14, 14, 14));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(combobox3, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(editButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(combobox3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(editButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(editButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button22, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button20, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button21, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(button20, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(button21, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(button22, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)))
                .addContainerGap())
        );

        cardLoai1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 60, 410, 210));

        jLabel75.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel75.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/search.png"))); // NOI18N
        jLabel75.setText("Tìm kiếm:");
        cardLoai1.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 10, 120, 30));

        jLabel76.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(153, 153, 153));
        jLabel76.setText("Lenovo");
        cardLoai1.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 10, 220, 30));

        jTextField3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardLoai1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 10, 220, 30));

        textField14.setForeground(new java.awt.Color(204, 204, 204));
        textField14.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        textField14.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        textField14.setLabelText("Tên hãng");
        textField14.setLineColor(new java.awt.Color(153, 153, 153));
        textField14.setSelectionColor(new java.awt.Color(204, 204, 204));
        cardLoai1.add(textField14, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 100, 260, -1));

        textField15.setForeground(new java.awt.Color(204, 204, 204));
        textField15.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        textField15.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        textField15.setLabelText("Mã hãng");
        textField15.setLineColor(new java.awt.Color(153, 153, 153));
        textField15.setSelectionColor(new java.awt.Color(204, 204, 204));
        cardLoai1.add(textField15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 230, -1));

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

        tblKhachHang1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tblKhachHang1.setModel(new javax.swing.table.DefaultTableModel(
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
        tblKhachHang1.setGridColor(new java.awt.Color(255, 255, 255));
        tblKhachHang1.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblKhachHang1.getTableHeader().setResizingAllowed(false);
        tblKhachHang1.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tblKhachHang1);

        cardLoai.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 1150, 220));

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));

        jLabel67.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(204, 0, 51));
        jLabel67.setText("2 trên 10");

        button9.setBackground(new java.awt.Color(204, 204, 255));
        button9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/left-arrow.png"))); // NOI18N

        jLabel68.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel68.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/visits (1).png"))); // NOI18N
        jLabel68.setText("Bảng ghi:");

        button10.setBackground(new java.awt.Color(204, 204, 255));
        button10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/left-arrow.png"))); // NOI18N

        button11.setBackground(new java.awt.Color(204, 204, 255));
        button11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/left-arrow.png"))); // NOI18N

        button12.setBackground(new java.awt.Color(204, 204, 255));
        button12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/left-arrow.png"))); // NOI18N
        button12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button12ActionPerformed(evt);
            }
        });

        jLabel69.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(204, 0, 51));
        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel69.setText("BẢNG THÔNG TIN LOẠI HÀNG");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(button10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(button11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(button9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(button12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel68, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(button12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel69, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        cardLoai.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 1150, 60));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 255)));

        button13.setBackground(new java.awt.Color(204, 204, 255));
        button13.setText("Thêm");

        button14.setBackground(new java.awt.Color(204, 204, 255));
        button14.setText("Cập nhật");
        button14.setEffectColor(new java.awt.Color(204, 255, 204));

        button15.setBackground(new java.awt.Color(255, 51, 102));
        button15.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 0, 51)));
        button15.setForeground(new java.awt.Color(255, 255, 255));
        button15.setText("Xóa");

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        combobox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "a", "x", "e", "f", "e", "gd", " " }));
        combobox2.setLabeText("Sắp xếp theo");

        editButton10.setBackground(new java.awt.Color(102, 204, 255));
        editButton10.setText("Tên loại");

        editButton11.setBackground(new java.awt.Color(102, 204, 255));
        editButton11.setText("Mã loại");
        editButton11.setMargin(new java.awt.Insets(5, 14, 14, 14));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(combobox2, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(editButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(combobox2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(editButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(editButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button15, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button13, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button14, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(button13, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(button14, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(button15, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)))
                .addContainerGap())
        );

        cardLoai.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 60, 410, 210));

        jLabel70.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel70.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/search.png"))); // NOI18N
        jLabel70.setText("Tìm kiếm:");
        cardLoai.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 10, 120, 30));

        jLabel71.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(153, 153, 153));
        jLabel71.setText("Sạc dự phòng");
        cardLoai.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 10, 220, 30));

        jTextField2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardLoai.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 10, 220, 30));

        textField12.setForeground(new java.awt.Color(204, 204, 204));
        textField12.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        textField12.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        textField12.setLabelText("Tên loại hàng");
        textField12.setLineColor(new java.awt.Color(153, 153, 153));
        textField12.setSelectionColor(new java.awt.Color(204, 204, 204));
        cardLoai.add(textField12, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 100, 260, -1));

        textField13.setForeground(new java.awt.Color(204, 204, 204));
        textField13.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        textField13.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        textField13.setLabelText("Mã loại hàng");
        textField13.setLineColor(new java.awt.Color(153, 153, 153));
        textField13.setSelectionColor(new java.awt.Color(204, 204, 204));
        cardLoai.add(textField13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 230, -1));

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

        cardKhachHang.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 1150, 210));

        textField2.setForeground(new java.awt.Color(204, 204, 204));
        textField2.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        textField2.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        textField2.setLabelText("Điện thoại");
        textField2.setLineColor(new java.awt.Color(153, 153, 153));
        textField2.setSelectionColor(new java.awt.Color(204, 204, 204));
        cardKhachHang.add(textField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 190, 230, -1));

        jLabel65.setFont(new java.awt.Font("Times New Roman", 1, 17)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(255, 0, 0));
        jLabel65.setText("0");
        cardKhachHang.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 230, 30));

        textField7.setForeground(new java.awt.Color(204, 204, 204));
        textField7.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        textField7.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        textField7.setLabelText("Mã khách hàng");
        textField7.setLineColor(new java.awt.Color(153, 0, 153));
        textField7.setSelectionColor(new java.awt.Color(255, 102, 102));
        cardKhachHang.add(textField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 80, 230, -1));

        textField8.setForeground(new java.awt.Color(204, 204, 204));
        textField8.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        textField8.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        textField8.setLabelText("Họ và tên");
        textField8.setLineColor(new java.awt.Color(153, 153, 153));
        textField8.setSelectionColor(new java.awt.Color(204, 204, 204));
        cardKhachHang.add(textField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 230, -1));

        textField4.setForeground(new java.awt.Color(204, 204, 204));
        textField4.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        textField4.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        textField4.setLabelText("Địa chỉ");
        textField4.setLineColor(new java.awt.Color(153, 153, 153));
        textField4.setSelectionColor(new java.awt.Color(204, 204, 204));
        cardKhachHang.add(textField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 230, -1));

        jLabel60.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(153, 153, 153));
        jLabel60.setText(" Nguyễn Văn An");
        cardKhachHang.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 10, 220, 30));

        jLabel63.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel63.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/credit-card.png"))); // NOI18N
        jLabel63.setText("Tích điểm:");
        cardKhachHang.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 120, 30));

        jTextField1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardKhachHang.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 10, 220, 30));

        jLabel64.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel64.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/search.png"))); // NOI18N
        jLabel64.setText("Tìm kiếm:");
        cardKhachHang.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 10, 120, 30));

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        jLabel58.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(204, 0, 51));
        jLabel58.setText("2 trên 10");

        button2.setBackground(new java.awt.Color(204, 204, 255));
        button2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/left-arrow.png"))); // NOI18N

        jLabel62.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel62.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/visits (1).png"))); // NOI18N
        jLabel62.setText("Bảng ghi:");

        button5.setBackground(new java.awt.Color(204, 204, 255));
        button5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/left-arrow.png"))); // NOI18N

        button6.setBackground(new java.awt.Color(204, 204, 255));
        button6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/left-arrow.png"))); // NOI18N

        button7.setBackground(new java.awt.Color(204, 204, 255));
        button7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/left-arrow.png"))); // NOI18N
        button7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button7ActionPerformed(evt);
            }
        });

        jLabel66.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(204, 0, 51));
        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel66.setText("BẢNG THÔNG TIN KHÁCH HÀNG");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(button6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(button7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(button7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

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
        combobox1.setLabeText("Sắp xếp theo");

        editButton8.setBackground(new java.awt.Color(102, 204, 255));
        editButton8.setText("Tăng");

        editButton9.setBackground(new java.awt.Color(102, 204, 255));
        editButton9.setText("Giảm");
        editButton9.setMargin(new java.awt.Insets(5, 14, 14, 14));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(combobox1, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(editButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(combobox1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(editButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(editButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button8, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(button8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)))
                .addContainerGap())
        );

        cardKhachHang.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 60, 400, 210));

        cardTrangChu.add(cardKhachHang, "card16");

        cardTrangChuTongQuan.setBackground(new java.awt.Color(255, 255, 255));
        cardTrangChuTongQuan.setRoundBottomLeft(30);
        cardTrangChuTongQuan.setRoundBottomRight(30);
        cardTrangChuTongQuan.setRoundTopLeft(30);
        cardTrangChuTongQuan.setRoundTopRight(20);
        cardTrangChuTongQuan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound1.setBackground(new java.awt.Color(204, 255, 204));
        panelRound1.setRoundBottomRight(50);
        panelRound1.setRoundTopLeft(50);

        lblTime.setFont(new java.awt.Font("Tahoma", 0, 32)); // NOI18N
        lblTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTime.setText("12:20:30 AM");

        lblDay.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblDay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDay.setText("17-11-2022");

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblDay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTime, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
                .addGap(21, 21, 21))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblTime)
                .addGap(18, 18, 18)
                .addComponent(lblDay)
                .addContainerGap(36, Short.MAX_VALUE))
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

        jTextArea1.setBackground(new java.awt.Color(204, 204, 255));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("Tổng đài hỗ trợ:\nKỹ thuật: 1800.1763\nKhiếu nại: 1800.1062\nBảo hành: 1800.1064");
        jTextArea1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane7.setViewportView(jTextArea1);

        javax.swing.GroupLayout panelRound6Layout = new javax.swing.GroupLayout(panelRound6);
        panelRound6.setLayout(panelRound6Layout);
        panelRound6Layout.setHorizontalGroup(
            panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound6Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        panelRound6Layout.setVerticalGroup(
            panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound6Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
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

        lblNV1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNV1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNV1.setText("jLabel36");
        panelRound7.add(lblNV1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 190, 20));

        lblNV2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNV2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNV2.setText("jLabel36");
        panelRound7.add(lblNV2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 170, 20));

        lblNV3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNV3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNV3.setText("jLabel36");
        panelRound7.add(lblNV3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 160, 20));

        lblMessage1.setBackground(new java.awt.Color(255, 255, 255));
        lblMessage1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblMessage1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMessage1.setOpaque(true);
        panelRound7.add(lblMessage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 190, 40));

        lblMessage2.setBackground(new java.awt.Color(255, 255, 255));
        lblMessage2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblMessage2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMessage2.setOpaque(true);
        panelRound7.add(lblMessage2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 190, 40));

        lblMessage3.setBackground(new java.awt.Color(255, 255, 255));
        lblMessage3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblMessage3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMessage3.setOpaque(true);
        panelRound7.add(lblMessage3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 190, 40));

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel35.setText("Top nhân viên xuất xắc");
        panelRound7.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

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
        setLocationHr(cardKhachHang, TaiKhoanHr, TaiKhoantittle3);
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

    private void button12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button12ActionPerformed

    private void button19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button19ActionPerformed

    private void button26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button26ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button26ActionPerformed

    private void cboDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDayActionPerformed
        fillTableSPBanChay();
    }//GEN-LAST:event_cboDayActionPerformed

    private void cboMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMonthActionPerformed
        fillTableSPBanChay();
    }//GEN-LAST:event_cboMonthActionPerformed

    private void cboYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboYearActionPerformed
        fillTableSPBanChay();
    }//GEN-LAST:event_cboYearActionPerformed

    private void cboYear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboYear1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboYear1ActionPerformed

    private void cboDay1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDay1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboDay1ActionPerformed

    private void cboMonth1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMonth1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboMonth1ActionPerformed

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
    private javax.swing.JLabel ThongKeHr;
    private javax.swing.JLabel ThongKeTittle1;
    private javax.swing.JLabel ThongKeTittle2;
    private javax.swing.JLabel TrangChuHr;
    private javax.swing.JLabel TrangChuTittle2;
    private com.swing.Button btnDangXuat;
    private com.swing.Button btnGiamGia;
    private com.swing.Button btnGioiThieu;
    private com.swing.Button btnHoaDon;
    private com.swing.Button btnMenu;
    private com.swing.Button btnSanPham;
    private com.swing.Button btnTaiKhoan;
    private com.swing.Button btnThongKe;
    private com.swing.Button btnTrangChu;
    private com.swing.Button button10;
    private com.swing.Button button11;
    private com.swing.Button button12;
    private com.swing.Button button13;
    private com.swing.Button button14;
    private com.swing.Button button15;
    private com.swing.Button button16;
    private com.swing.Button button17;
    private com.swing.Button button18;
    private com.swing.Button button19;
    private com.swing.Button button2;
    private com.swing.Button button20;
    private com.swing.Button button21;
    private com.swing.Button button22;
    private com.swing.Button button23;
    private com.swing.Button button24;
    private com.swing.Button button25;
    private com.swing.Button button26;
    private com.swing.Button button27;
    private com.swing.Button button28;
    private com.swing.Button button29;
    private com.swing.Button button3;
    private com.swing.Button button4;
    private com.swing.Button button5;
    private com.swing.Button button6;
    private com.swing.Button button7;
    private com.swing.Button button8;
    private com.swing.Button button9;
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
    private com.swing.PanelRound cardTaiKhoanNhanVien;
    private com.swing.PanelRound cardTaiKhoanQuanLi;
    private com.swing.PanelRound cardThongKeDoanhThu;
    private com.swing.PanelRound cardThongKeSanPham;
    private javax.swing.JPanel cardTrangChu;
    private com.swing.PanelRound cardTrangChuNoiBat;
    private com.swing.PanelRound cardTrangChuTongQuan;
    private com.swing.Combobox cboDay;
    private com.swing.Combobox cboDay1;
    private com.swing.Combobox cboMonth;
    private com.swing.Combobox cboMonth1;
    private com.swing.Combobox cboYear;
    private com.swing.Combobox cboYear1;
    private com.swing.Combobox combobox1;
    private com.swing.Combobox combobox2;
    private com.swing.Combobox combobox3;
    private com.swing.Combobox combobox4;
    private com.swing.Combobox combobox5;
    private com.swing.EditButton editButton10;
    private com.swing.EditButton editButton11;
    private com.swing.EditButton editButton12;
    private com.swing.EditButton editButton13;
    private com.swing.EditButton editButton8;
    private com.swing.EditButton editButton9;
    private com.frame.Header header2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JLabel jlbClose;
    private javax.swing.JLabel jlbState;
    private javax.swing.JPanel jplContainer;
    private javax.swing.JPanel jplLose;
    private javax.swing.JPanel jplMenubar;
    private javax.swing.JPanel jplState;
    private javax.swing.JPanel jplTitle;
    private javax.swing.JLabel lblDay;
    private javax.swing.JLabel lblMessage1;
    private javax.swing.JLabel lblMessage2;
    private javax.swing.JLabel lblMessage3;
    private javax.swing.JLabel lblNV1;
    private javax.swing.JLabel lblNV2;
    private javax.swing.JLabel lblNV3;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel opacity;
    private com.swing.PanelRound panelRound1;
    private com.swing.PanelRound panelRound2;
    private com.swing.PanelRound panelRound3;
    private com.swing.PanelRound panelRound4;
    private com.swing.PanelRound panelRound5;
    private com.swing.PanelRound panelRound6;
    private com.swing.PanelRound panelRound7;
    private javax.swing.JPanel pnMenu;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTable tblKhachHang1;
    private javax.swing.JTable tblKhachHang2;
    private javax.swing.JTable tblKhachHang3;
    private javax.swing.JTable tblSPBanChay;
    private javax.swing.JTable tblSPBanChay1;
    private com.swing.TextField textField12;
    private com.swing.TextField textField13;
    private com.swing.TextField textField14;
    private com.swing.TextField textField15;
    private com.swing.TextField textField16;
    private com.swing.TextField textField17;
    private com.swing.TextField textField18;
    private com.swing.TextField textField19;
    private com.swing.TextField textField2;
    private com.swing.TextField textField20;
    private com.swing.TextField textField21;
    private com.swing.TextField textField3;
    private com.swing.TextField textField4;
    private com.swing.TextField textField7;
    private com.swing.TextField textField8;
    // End of variables declaration//GEN-END:variables
}
