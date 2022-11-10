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
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu.Separator;
import com.swing.*;
import java.awt.Font;

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
        drawer = Drawer.newDrawer(this)
                .background(new Color(90, 90, 90))
                .enableScroll(true)
                .header(new Header())
                .space(3)
                .addChild(new DrawerItem("Trang chủ ").build())
                .addChild(new DrawerItem("Tài khoản").build())
                .addChild(new DrawerItem("Hóa đơn").build())
                .addFooter(new Separator())
                .addFooter(new DrawerItem("Giới thiệu").build())
                .addFooter(new DrawerItem("Trợ giúp").build())
                .addFooter(new DrawerItem("Đăng xuất").build())
                .event(new EventDrawer() {
                    @Override
                    public void selected(int index, DrawerItem item) {
                        if (drawer.isShow()) {
                            drawer.hide();
                        }
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
                            case 3:
                                if (chose == -1 || chose != 3) {
                                    hidePage();
                                    hideMenu();
                                    cardMenubarGioiThieu.setVisible(true);
                                    cardGioiThieuSanPham.setVisible(true);
                                    chose = 3;
                                }
                                break;
                        }
                    }
                }).build();

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

    //điểu chỉnh thanh gạch dưới của menu
    public synchronized void setLocationHr(JPanel pn, JLabel hr, int last) {
        if (!startThread) {
            hidePage();
            startThread = true;
            new Thread(new Runnable() {
                @Override
                public synchronized void run() {
                    //khi dời về phía sau
                    if (hr.getX() < last) {
                        while (hr.getX() < last) {
                            hr.setLocation(hr.getX() + 2, hr.getY());
                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException ex) {
                                System.out.println(ex);
                            }
                        }
                    } //khi lùi lên trước
                    else {
                        while (hr.getX() > last) {
                            hr.setLocation(hr.getX() - 2, hr.getY());
                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException ex) {
                                System.out.println(ex);
                            }
                        }
                    }
                }
            }).start();
            pn.setVisible(true);
            startThread = false;
        }
    }

    //phương thức ẩn các trang
    public void hidePage() {
        this.arrPn = new JPanel[]{
            cardTrangChuTongQuan, cardTrangChuThongKe, cardTrangChuNoiBat, cardTrangChuXuHuong,
            cardGioiThieuSanPham, cardGioiThieuThanhVien,
            cardTaiKhoanNhanVien, cardTaiKhoanQuanLi,
            cardSanPham, cardHangSanXuat, cardLoai,
            cardHoaDon, cardHoaDonCho
        };
        for (JPanel pn : this.arrPn) {
            pn.setVisible(false);
        }
    }

    //phương thức ẩn các menu
    public void hideMenu() {
        this.arrPn = new JPanel[]{cardMenubarTrangChu, cardMenubarTaiKhoan, cardMenubarGioiThieu, cardMenubarGiamGia, cardMenubarHoaDon, cardMenubarSanPham};
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
            if (cardTrangChuThongKe.isVisible()) {
                TrangChuHr.setLocation(240, 43);
            }
            if (cardTrangChuXuHuong.isVisible()) {
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
        TrangChuTittle1 = new javax.swing.JLabel();
        TrangChuTittle2 = new javax.swing.JLabel();
        TrangChuTittle3 = new javax.swing.JLabel();
        TrangChuTittle4 = new javax.swing.JLabel();
        TrangChuHr = new javax.swing.JLabel();
        textField1 = new com.swing.TextField();
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
        cardTrangChuThongKe = new com.swing.PanelRound();
        cardTrangChuXuHuong = new com.swing.PanelRound();
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
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        cardHangSanXuat = new com.swing.PanelRound();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        cardLoai = new com.swing.PanelRound();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
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
                            .addComponent(btnGiamGia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                .addGap(12, 12, 12)
                .addComponent(btnHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btnGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 131, Short.MAX_VALUE)
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

        TrangChuTittle1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TrangChuTittle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TrangChuTittle1.setText("Tổng quan");
        TrangChuTittle1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TrangChuTittle1MousePressed(evt);
            }
        });
        cardMenubarTrangChu.add(TrangChuTittle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 110, 30));

        TrangChuTittle2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TrangChuTittle2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TrangChuTittle2.setText("Thống kê");
        TrangChuTittle2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TrangChuTittle2MousePressed(evt);
            }
        });
        cardMenubarTrangChu.add(TrangChuTittle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 110, 30));

        TrangChuTittle3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TrangChuTittle3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TrangChuTittle3.setText("Xu hướng");
        TrangChuTittle3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TrangChuTittle3MousePressed(evt);
            }
        });
        cardMenubarTrangChu.add(TrangChuTittle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 110, 30));

        TrangChuTittle4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TrangChuTittle4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TrangChuTittle4.setText("Nổi bật");
        TrangChuTittle4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TrangChuTittle4MousePressed(evt);
            }
        });
        cardMenubarTrangChu.add(TrangChuTittle4, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, 110, 30));

        TrangChuHr.setBackground(new java.awt.Color(0, 153, 0));
        TrangChuHr.setOpaque(true);
        cardMenubarTrangChu.add(TrangChuHr, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 43, 110, 5));

        textField1.setLabelText("Tìm kiếm");
        cardMenubarTrangChu.add(textField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 0, 240, -1));

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
        TaiKhoantittle3.setText("Quản lí");
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

        jplMenubar.add(cardMenubar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 1140, 50));

        jPanel1.add(jplMenubar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1220, -1));

        jplContainer.setLayout(new java.awt.CardLayout());

        cardTrangChu.setBackground(new java.awt.Color(204, 204, 204));
        cardTrangChu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        cardTrangChu.add(cardTrangChuTongQuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 1160, 540));

        cardTrangChuThongKe.setBackground(new java.awt.Color(255, 255, 255));
        cardTrangChuThongKe.setRoundBottomLeft(30);
        cardTrangChuThongKe.setRoundBottomRight(30);
        cardTrangChuThongKe.setRoundTopLeft(30);
        cardTrangChuThongKe.setRoundTopRight(20);
        cardTrangChuThongKe.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        cardTrangChu.add(cardTrangChuThongKe, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 1160, 540));

        cardTrangChuXuHuong.setBackground(new java.awt.Color(255, 255, 255));
        cardTrangChuXuHuong.setRoundBottomLeft(30);
        cardTrangChuXuHuong.setRoundBottomRight(30);
        cardTrangChuXuHuong.setRoundTopLeft(30);
        cardTrangChuXuHuong.setRoundTopRight(20);
        cardTrangChuXuHuong.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        cardTrangChu.add(cardTrangChuXuHuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 1160, 540));

        cardTrangChuNoiBat.setBackground(new java.awt.Color(255, 255, 255));
        cardTrangChuNoiBat.setRoundBottomLeft(30);
        cardTrangChuNoiBat.setRoundBottomRight(30);
        cardTrangChuNoiBat.setRoundTopLeft(30);
        cardTrangChuNoiBat.setRoundTopRight(20);
        cardTrangChuNoiBat.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setText("jLabel27");
        cardTrangChuNoiBat.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, 340, 160));

        cardTrangChu.add(cardTrangChuNoiBat, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 1160, 540));

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

        cardTrangChu.add(cardTaiKhoanQuanLi, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 1160, 540));

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

        cardTrangChu.add(cardTaiKhoanNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 1160, 540));

        cardSanPham.setBackground(new java.awt.Color(255, 255, 255));
        cardSanPham.setRoundBottomLeft(30);
        cardSanPham.setRoundBottomRight(30);
        cardSanPham.setRoundTopLeft(30);
        cardSanPham.setRoundTopRight(20);
        cardSanPham.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText("<html> <h2>Phần mềm được phát triển bởi <a href=\"https://www.facebook.com/davisy.dev\">DAVISY TEAM</a></h2>  <!html>");
        cardSanPham.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 500, -1));

        jLabel29.setText("<html>  <h3> Phần mềm là dự án trong học kỳ 4 của chúng tôi tại  <a href=\"\">FPT Polytechnic College</a> </h3>  <!html>");
        cardSanPham.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, 380, -1));

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/businessman.png"))); // NOI18N
        cardSanPham.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, -1, -1));

        jLabel31.setText("<html>\n<h3>Các tư liệu tham khảo</h3>  \n<ul>  \n <li> <h4> <a href=\"https://github.com/k33ptoo/\">KeepToo</a></h4></li>  \n<li> <h4><a href=\"https://github.com/DJ-Raven\">DJ-Raven</a></h4>\n</li>\n </ul> \n <!html>");
        cardSanPham.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, 320, 110));

        jLabel32.setText("<html>  <h3>Bạn có thể tham khảo giao diện của chúng tôi tại <a href=\"https://www.github.com/theanishtar\"> đây</a> </h3>  <!html>");
        cardSanPham.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 450, -1));

        cardTrangChu.add(cardSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 1160, 540));

        cardHangSanXuat.setBackground(new java.awt.Color(255, 255, 255));
        cardHangSanXuat.setRoundBottomLeft(30);
        cardHangSanXuat.setRoundBottomRight(30);
        cardHangSanXuat.setRoundTopLeft(30);
        cardHangSanXuat.setRoundTopRight(20);
        cardHangSanXuat.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel33.setText("<html> <h2>Phần mềm được phát triển bởi <a href=\"https://www.facebook.com/davisy.dev\">DAVISY TEAM</a></h2>  <!html>");
        cardHangSanXuat.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 500, -1));

        jLabel34.setText("<html>  <h3> Phần mềm là dự án trong học kỳ 4 của chúng tôi tại  <a href=\"\">FPT Polytechnic College</a> </h3>  <!html>");
        cardHangSanXuat.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, 380, -1));

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/businessman.png"))); // NOI18N
        cardHangSanXuat.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, -1, -1));

        jLabel36.setText("<html>\n<h3>Các tư liệu tham khảo</h3>  \n<ul>  \n <li> <h4> <a href=\"https://github.com/k33ptoo/\">KeepToo</a></h4></li>  \n<li> <h4><a href=\"https://github.com/DJ-Raven\">DJ-Raven</a></h4>\n</li>\n </ul> \n <!html>");
        cardHangSanXuat.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, 320, 110));

        jLabel37.setText("<html>  <h3>Bạn có thể tham khảo giao diện của chúng tôi tại <a href=\"https://www.github.com/theanishtar\"> đây</a> </h3>  <!html>");
        cardHangSanXuat.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 450, -1));

        cardTrangChu.add(cardHangSanXuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 1160, 540));

        cardLoai.setBackground(new java.awt.Color(255, 255, 255));
        cardLoai.setRoundBottomLeft(30);
        cardLoai.setRoundBottomRight(30);
        cardLoai.setRoundTopLeft(30);
        cardLoai.setRoundTopRight(20);
        cardLoai.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel38.setText("<html> <h2>Phần mềm được phát triển bởi <a href=\"https://www.facebook.com/davisy.dev\">DAVISY TEAM</a></h2>  <!html>");
        cardLoai.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 500, -1));

        jLabel39.setText("<html>  <h3> Phần mềm là dự án trong học kỳ 4 của chúng tôi tại  <a href=\"\">FPT Polytechnic College</a> </h3>  <!html>");
        cardLoai.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, 380, -1));

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/businessman.png"))); // NOI18N
        cardLoai.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, -1, -1));

        jLabel41.setText("<html>\n<h3>Các tư liệu tham khảo</h3>  \n<ul>  \n <li> <h4> <a href=\"https://github.com/k33ptoo/\">KeepToo</a></h4></li>  \n<li> <h4><a href=\"https://github.com/DJ-Raven\">DJ-Raven</a></h4>\n</li>\n </ul> \n <!html>");
        cardLoai.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, 320, 110));

        jLabel42.setText("<html>  <h3>Bạn có thể tham khảo giao diện của chúng tôi tại <a href=\"https://www.github.com/theanishtar\"> đây</a> </h3>  <!html>");
        cardLoai.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 450, -1));

        cardTrangChu.add(cardLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 1160, 540));

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

        cardTrangChu.add(cardHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 1160, 540));

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

        cardTrangChu.add(cardHoaDonCho, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 1160, 540));

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

        cardTrangChu.add(cardGiamGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 1160, 540));

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

        cardTrangChu.add(cardGioiThieuSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 1160, 540));

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

        cardTrangChu.add(cardGioiThieuThanhVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 1160, 540));

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
        setLocationHr(cardTaiKhoanNhanVien, TaiKhoanHr, 240);
    }//GEN-LAST:event_TaiKhoantittle2MouseClicked

    private void TaiKhoantittle3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TaiKhoantittle3MouseClicked
        setLocationHr(cardTaiKhoanQuanLi, TaiKhoanHr, 440);
    }//GEN-LAST:event_TaiKhoantittle3MouseClicked


    private void TrangChuTittle2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangChuTittle2MousePressed
        setLocationHr(cardTrangChuThongKe, TrangChuHr, 240);
    }//GEN-LAST:event_TrangChuTittle2MousePressed

    private void TrangChuTittle3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangChuTittle3MousePressed
        setLocationHr(cardTrangChuXuHuong, TrangChuHr, 440);
    }//GEN-LAST:event_TrangChuTittle3MousePressed

    private void TrangChuTittle4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangChuTittle4MousePressed
        setLocationHr(cardTrangChuNoiBat, TrangChuHr, 640);
    }//GEN-LAST:event_TrangChuTittle4MousePressed

    private void TrangChuTittle1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangChuTittle1MousePressed
        setLocationHr(cardTrangChuTongQuan, TrangChuHr, 40);
    }//GEN-LAST:event_TrangChuTittle1MousePressed


    private void GioiThieutittle1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GioiThieutittle1MousePressed
        setLocationHr(cardGioiThieuSanPham, GioiThieuHr, 240);
    }//GEN-LAST:event_GioiThieutittle1MousePressed

    private void GioiThieutittle2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GioiThieutittle2MousePressed
        setLocationHr(cardGioiThieuThanhVien, GioiThieuHr, 440);
    }//GEN-LAST:event_GioiThieutittle2MousePressed


    private void TaiKhoantittle2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TaiKhoantittle2MousePressed
        setLocationHr(cardTaiKhoanNhanVien, TaiKhoanHr, 240);
    }//GEN-LAST:event_TaiKhoantittle2MousePressed

    private void TaiKhoantittle3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TaiKhoantittle3MousePressed
        setLocationHr(cardTaiKhoanQuanLi, TaiKhoanHr, 440);
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
        setLocationHr(cardSanPham, SanPhamHr, 240);
    }//GEN-LAST:event_SanPhamTittle1MousePressed

    private void SanPhamTittle3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SanPhamTittle3MousePressed
        setLocationHr(cardLoai, SanPhamHr, 640);
    }//GEN-LAST:event_SanPhamTittle3MousePressed

    private void SanPhamTittle2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SanPhamTittle2MousePressed
        setLocationHr(cardHangSanXuat, SanPhamHr, 440);
    }//GEN-LAST:event_SanPhamTittle2MousePressed

    private void HoaDonTittle1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HoaDonTittle1MousePressed
        setLocationHr(cardHoaDon, HoaDonHr, 240);
    }//GEN-LAST:event_HoaDonTittle1MousePressed

    private void HoaDonTittle2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HoaDonTittle2MousePressed
        setLocationHr(cardHoaDonCho, HoaDonHr, 440);
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
    private javax.swing.JLabel SanPhamHr;
    private javax.swing.JLabel SanPhamTittle1;
    private javax.swing.JLabel SanPhamTittle2;
    private javax.swing.JLabel SanPhamTittle3;
    private javax.swing.JLabel TaiKhoanHr;
    private javax.swing.JLabel TaiKhoantittle2;
    private javax.swing.JLabel TaiKhoantittle3;
    private javax.swing.JLabel TrangChuHr;
    private javax.swing.JLabel TrangChuTittle1;
    private javax.swing.JLabel TrangChuTittle2;
    private javax.swing.JLabel TrangChuTittle3;
    private javax.swing.JLabel TrangChuTittle4;
    private com.swing.Button btnDangXuat;
    private com.swing.Button btnGiamGia;
    private com.swing.Button btnGioiThieu;
    private com.swing.Button btnHoaDon;
    private com.swing.Button btnMenu;
    private com.swing.Button btnSanPham;
    private com.swing.Button btnTaiKhoan;
    private com.swing.Button btnTrangChu;
    private com.swing.PanelRound cardGiamGia;
    private com.swing.PanelRound cardGioiThieuSanPham;
    private com.swing.PanelRound cardGioiThieuThanhVien;
    private com.swing.PanelRound cardHangSanXuat;
    private com.swing.PanelRound cardHoaDon;
    private com.swing.PanelRound cardHoaDonCho;
    private com.swing.PanelRound cardLoai;
    private javax.swing.JPanel cardMenubar;
    private javax.swing.JPanel cardMenubarGiamGia;
    private javax.swing.JPanel cardMenubarGioiThieu;
    private javax.swing.JPanel cardMenubarHoaDon;
    private javax.swing.JPanel cardMenubarSanPham;
    private javax.swing.JPanel cardMenubarTaiKhoan;
    private javax.swing.JPanel cardMenubarTrangChu;
    private com.swing.PanelRound cardSanPham;
    private com.swing.PanelRound cardTaiKhoanNhanVien;
    private com.swing.PanelRound cardTaiKhoanQuanLi;
    private javax.swing.JPanel cardTrangChu;
    private com.swing.PanelRound cardTrangChuNoiBat;
    private com.swing.PanelRound cardTrangChuThongKe;
    private com.swing.PanelRound cardTrangChuTongQuan;
    private com.swing.PanelRound cardTrangChuXuHuong;
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
    private javax.swing.JLabel jLabel21;
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel jlbClose;
    private javax.swing.JLabel jlbState;
    private javax.swing.JPanel jplContainer;
    private javax.swing.JPanel jplLose;
    private javax.swing.JPanel jplMenubar;
    private javax.swing.JPanel jplState;
    private javax.swing.JPanel jplTitle;
    private javax.swing.JLabel lblMessage1;
    private javax.swing.JLabel lblMessage2;
    private javax.swing.JLabel lblMessage3;
    private javax.swing.JLabel lblMessage4;
    private javax.swing.JLabel opacity;
    private com.swing.PanelRound panelRound1;
    private com.swing.PanelRound panelRound2;
    private com.swing.PanelRound panelRound3;
    private com.swing.PanelRound panelRound4;
    private com.swing.PanelRound panelRound5;
    private com.swing.PanelRound panelRound6;
    private com.swing.PanelRound panelRound7;
    private javax.swing.JPanel pnMenu;
    private com.swing.TextField textField1;
    // End of variables declaration//GEN-END:variables
}
