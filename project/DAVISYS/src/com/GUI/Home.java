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
import com.entity.TaiKhoanEntity;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu.Separator;
import com.swing.*;
import com.utils.MsgBox;
import com.utils.XDate;
import java.awt.BorderLayout;
import java.awt.Component;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import com.cart.FormHome;
import com.cart.EventItem;
import com.cart.ModelItem;
import com.dao.ThongKeDAO;
import com.entity.KhachHangEntity;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.hicode.switchbutton.Event;
import com.library.Extensisons.ScanQRProduct;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import com.dao.KhachHangDAO;
import com.dao.TaiKhoanDAO;
import com.entity.GioHangTempEntity;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private final String MAIL_REGEX = "^[\\w\\.=-]+@[\\w\\.-]+\\.[\\w]{2,3}$";
    HangDAO Hang = new HangDAO();
    LoaiHangDAO Loai = new LoaiHangDAO();
    SanPhamDAO SanPham = new SanPhamDAO();
    KhachHangDAO KhachHang = new KhachHangDAO();
    TaiKhoanDAO NhanVien = new TaiKhoanDAO();
    //kiểm tra luồng
    boolean startThread = false;

    //tạo mảng chứa các phần tử card
    JPanel[] arrPn;

    //tạo mảng chứa các phần tử menu
    Button[] arrBtn;
    private FormHome home;
    //màu hiển thị khi chọn menu
    Color chooserMenuItem = new Color(0, 153, 0);
    Color defaultMenuItem = Color.black;

    Button btnItemMenu = null;
    List<SanPhamEntity> list = new ArrayList<>();
    List<HangEntity> listHang = new ArrayList<>();
    List<LoaiHangEntity> listLoai = new ArrayList<>();
    List<KhachHangEntity> listKhachHang = new ArrayList<>();
    List<TaiKhoanEntity> listNhanVien = new ArrayList<>();
    List<GioHangTempEntity> listgh = new ArrayList<>();
    //Thong ke
    ThongKeDAO TKdao = new ThongKeDAO();

    String day;
    String month;
    String year;

    List<Object[]> listTKSP = null;
    List<Object[]> listTKDT = null;
    //

    public Home() {
        initComponents();
        initThongKe();
        initNhanVien();
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
        initKhachHang();
        fillComboxLoai();
        fillComboxHang();
        lblrecordHang.setText(recordHang());
        lblrecordLoai.setText(recordLoai());
        lblrecordSP.setText(recordSanPham());

        SanPhamHr1.setVisible(false);
        cartShoping();

    }

    public void settingTable() {
        tblNhanVien.setRowHeight(30);
        TableColumnModel columnModelNhanVien = tblNhanVien.getColumnModel();
        columnModelNhanVien.getColumn(0).setPreferredWidth(50);
        columnModelNhanVien.getColumn(1).setPreferredWidth(150);
        columnModelNhanVien.getColumn(2).setPreferredWidth(50);
        columnModelNhanVien.getColumn(3).setPreferredWidth(200);
        columnModelNhanVien.getColumn(4).setPreferredWidth(50);

        tblKhachHang.setRowHeight(30);
        tblKhachHang.getTableHeader().setOpaque(false);
        TableColumnModel columnModelKhachHang = tblKhachHang.getColumnModel();
        columnModelKhachHang.getColumn(0).setPreferredWidth(50);
        columnModelKhachHang.getColumn(1).setPreferredWidth(150);

        tblLoaiHang.setRowHeight(30);
        TableColumnModel columnModelLoaiHang = tblLoaiHang.getColumnModel();
        columnModelLoaiHang.getColumn(0).setPreferredWidth(50);
        columnModelLoaiHang.getColumn(1).setPreferredWidth(150);

        tblSanPham.setRowHeight(30);
        TableColumnModel columnModelSanPham = tblSanPham.getColumnModel();
        columnModelSanPham.getColumn(0).setPreferredWidth(50);
        columnModelSanPham.getColumn(1).setPreferredWidth(300);
        columnModelSanPham.getColumn(2).setPreferredWidth(50);
        columnModelSanPham.getColumn(3).setPreferredWidth(50);
        columnModelSanPham.getColumn(4).setPreferredWidth(60);
        columnModelSanPham.getColumn(5).setPreferredWidth(60);
        columnModelSanPham.getColumn(7).setPreferredWidth(100);

        tblHang.setRowHeight(30);
        TableColumnModel columnModelHang = tblHang.getColumnModel();
        columnModelHang.getColumn(0).setPreferredWidth(50);
        columnModelHang.getColumn(1).setPreferredWidth(150);

        tblDoanhThu.setRowHeight(30);
        TableColumnModel columnModelDoanhThu = tblDoanhThu.getColumnModel();
        columnModelDoanhThu.getColumn(0).setPreferredWidth(250);
        columnModelDoanhThu.getColumn(1).setPreferredWidth(627 - 250);

        tblCart.setRowHeight(30);
        TableColumnModel columnModelGioHang = tblCart.getColumnModel();
        columnModelGioHang.getColumn(0).setPreferredWidth(110);
        columnModelGioHang.getColumn(1).setPreferredWidth(315);
        columnModelGioHang.getColumn(2).setPreferredWidth(120);
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
                    cardMenubarGioHang.setVisible(true);
                    cardGioHang.setVisible(true);
                    chose = 3;
                }
                break;
            case 4:
                if (chose == -1 || chose != 4) {
                    hidePage();
                    hideMenu();
                    cardMenubarHoaDon.setVisible(true);
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
        this.threadClock = false;
        new Thread(new Runnable() {
            @Override
            public void run() {
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
            cardGioHang, cardHoaDonCho,
            cardKhachHang
        };
        for (JPanel pn : this.arrPn) {
            pn.setVisible(false);
        }
    }

    //phương thức ẩn các menu
    public void hideMenu() {
        this.arrPn = new JPanel[]{cardMenubarTrangChu, cardMenubarTaiKhoan,
            cardMenubarGioiThieu, cardMenubarHoaDon, cardMenubarGioHang,
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
                TaiKhoanHr1.setLocation(240, 43);
            }
            if (cardTaiKhoanChucVu.isVisible()) {
                TaiKhoanHr1.setLocation(440, 43);
            }
        } else if (cardMenubarGioiThieu.isVisible()) {
            if (cardGioiThieuSanPham.isVisible()) {
                GioiThieuHr1.setLocation(240, 43);
            }
            if (cardGioiThieuThanhVien.isVisible()) {
                GioiThieuHr1.setLocation(440, 43);
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
        listHang = Hang.selectAll();
        this.fillTableHang();
        this.row = -1;
        this.updateStatusHang();
    }

    public void fillTableHang() {
        DefaultTableModel model = (DefaultTableModel) tblHang.getModel();
        model.setRowCount(0);
        try {
            for (HangEntity h : listHang) {
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
            listHang();
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
            listHang();
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
            listHang();
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
        listLoai = Loai.selectAll();
        this.fillTableLoai();
        this.row = -1;
        this.updateStatusLoai();
    }

    public void fillTableLoai() {
        DefaultTableModel model = (DefaultTableModel) tblLoaiHang.getModel();
        model.setRowCount(0);
        try {
            //String keyword = txtTimKiemLoai.getText();
            //List<LoaiHangEntity> listLoai = Loai.selectByKeyword(keyword);
            for (LoaiHangEntity l : listLoai) {
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
            listLoai();
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
            listLoai();
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
            listLoai();
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
        listSPT();
        this.fillTableSanPham();
        this.row = -1;
        this.updateStatusSanPham();
    }

    public void fillTableSanPham() {
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        try {
            for (SanPhamEntity sp : list) {
                Object[] row = {sp.getMaSP(), sp.getTenSP(), sp.getTenL(), sp.getTenH(), sp.getGiaNhap(), sp.getGiaBan(), sp.getNgayNhap(), sp.getMoTa()};
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void fillTableSanPham(String tenSP) {
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        try {
            for (SanPhamEntity sp : list) {
                if (sp.getTenSP().toLowerCase().contains(tenSP.toLowerCase())) {
                    Object[] row = {sp.getMaSP(), sp.getTenSP(), sp.getTenL(), sp.getTenH(), sp.getGiaNhap(), sp.getGiaBan(), sp.getNgayNhap(), sp.getMoTa()};
                    model.addRow(row);
                }
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    public void setFormSanPham(SanPhamEntity sp) {
        txtMaSP.setText(sp.getMaSP());
        txtTenSP.setText(sp.getTenSP());
        cboMaLoai.setSelectedItem(sp.getTenL());
        cboMaHang.setSelectedItem(sp.getTenH());
        txtGiaNhapSP.setText(String.valueOf(sp.getGiaNhap()));
        txtGiaBanSP.setText(String.valueOf(sp.getGiaBan()));
        txtNgayNhapSP.setText(String.valueOf(sp.getNgayNhap()));
        if (sp.getHinh() != null) {
            File file = new File("src\\com\\images\\" + sp.getHinh() + ".PNG");
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
            lblAnh.setIcon(null);
        }
        txtMoTaSP.setText(sp.getMoTa());
    }

    public SanPhamEntity getFormSanPham() {
        String tenloai = (String) cboMaLoai.getSelectedItem();
        SanPhamEntity sp = new SanPhamEntity();
        sp.setMaSP(txtMaSP.getText());
        sp.setTenSP(txtTenSP.getText());
        sp.setTenL(tenloai);
        sp.setTenH((String) cboMaHang.getSelectedItem());
        sp.setGiaNhap(Float.parseFloat(txtGiaNhapSP.getText()));
        sp.setGiaBan(Float.parseFloat(txtGiaBanSP.getText()));
        sp.setNgayNhap(XDate.toDate(txtNgayNhapSP.getText(), "yyyy-MM-dd"));
        sp.setHinh(txtMaSP.getText());
        sp.setMoTa(txtMoTaSP.getText());
        return sp;

    }

    public void clearFormSanPham() {
        SanPhamEntity sp = new SanPhamEntity();
        this.setFormSanPham(sp);
        txtGiaBanSP.setText(" ");
        txtNgayNhapSP.setText(" ");
        txtGiaNhapSP.setText(" ");
        this.row = -1;
        this.updateStatusSanPham();
    }

    public void updateStatusSanPham() {
        boolean edit = (this.row >= 0);
        boolean first = (this.row == 0);
        boolean last = (this.row == tblSanPham.getRowCount() - 1);
        //Trạng thái form
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
            listSPT();
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
            listSPT();
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
            listSPT();
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
            model.addElement(String.valueOf(l.getTenLH()));
        }
    }

    public void fillComboxHang() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboMaHang.getModel();
        model.removeAllElements();

        for (HangEntity h : listHang) {
            model.addElement(String.valueOf(h.getTenHang()));
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
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Hình ảnh", "png", "jpg");
        chooser.setFileFilter(filter);
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

    public boolean checkSP() {
        if (txtMaSP.getText().equals("")) {
            MsgBox.alert(this, "Mã sản phẩm không được để trống!");
            txtMaSP.requestFocus();
            return false;
        }
        if (txtTenSP.getText().equals(" ")) {
            MsgBox.alert(this, "Tên sản phẩm không được để trống!");
            txtTenSP.requestFocus();
            return false;
        }
        if (txtGiaNhapSP.getText().equals("")) {
            MsgBox.alert(this, "Giá nhập không được để trống!");
            txtGiaNhapSP.requestFocus();
            return false;
        } else {
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
        if (txtGiaBanSP.getText().equals("")) {
            MsgBox.alert(this, "Giá bán không được để trống!");
            txtGiaBanSP.requestFocus();
            return false;
        } else {
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
        if (txtNgayNhapSP.getText().equals("")) {
            MsgBox.alert(this, "Ngày nhập không được để trống!");
            txtNgayNhapSP.requestFocus();
            return false;
        } else {
            try {
                formater.setLenient(false);
                formater.parse(txtNgayNhapSP.getText());
            } catch (Exception ex) {
                MsgBox.alert(this, "Vui lòng nhập ngày sinh đúng định dạng năm - tháng - ngày!");
                txtNgayNhapSP.requestFocus();
                return false;
            }
        }
        if (cboMaHang.getSelectedItem().equals("")) {
            MsgBox.alert(this, "Vui lòng chọn mã hãng sản xuất!");
            return false;
        }
        if (cboMaLoai.getSelectedItem().equals("")) {
            MsgBox.alert(this, "Vui lòng chọn mã loại hàng!");
            return false;
        }
        return true;
    }

    public boolean checkKH() {
        if (txtmaKH.getText().equals("")) {
            MsgBox.alert(this, "Mã khách hàng không được để trống!");
            txtmaKH.requestFocus();
            return false;
        }
        if (txthoTen.getText().equals(" ")) {
            MsgBox.alert(this, "Tên khách hàng không được để trống!");
            txthoTen.requestFocus();
            return false;
        }
        if (txtGiaNhapSP.getText().equals("")) {
            MsgBox.alert(this, "Giá nhập không được để trống!");
            txtGiaNhapSP.requestFocus();
            return false;
        } else if (txtSDT.getText().equals("")) {
            MsgBox.alert(this, "Số điện thoại không được để trống!");
            txtSDT.requestFocus();
            return false;
        } else if (txtdiaChi.getText().equals("")) {
            MsgBox.alert(this, "Địa chỉ không được để trống!");
            txtdiaChi.requestFocus();
            return false;
        }
        return true;
    }

    public void initKhachHang() {
        setLocationRelativeTo(null);
        listKhachHang();
        this.fillTableKhachHang();
        this.row = -1;
        this.updateStatusKhachHang();
    }

    public String recordKhachHang() {
        List<KhachHangEntity> list = KhachHang.selectAll();
        return (row + 1) + " trên " + list.size();
    }

    public void fillTableKhachHang() {
        DefaultTableModel model = (DefaultTableModel) tblKhachHang.getModel();
        model.setRowCount(0);
        try {
            for (KhachHangEntity kh : listKhachHang) {
                Object[] row = {kh.getMaKH(), kh.getHoTen(), kh.getDienThoai(), kh.getDiaChi(), kh.getTichDiem()};
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    public void setFormKhachHang(KhachHangEntity kh) {
        txtmaKH.setText(kh.getMaKH());
        txthoTen.setText(kh.getHoTen());
        txtSDT.setText(kh.getDienThoai());
        txtdiaChi.setText(kh.getDiaChi());

    }

    public KhachHangEntity getFormKhachHang() {
        KhachHangEntity kh = new KhachHangEntity();
        kh.setMaKH(txtmaKH.getText());
        kh.setHoTen(txthoTen.getText());
        kh.setDienThoai(txtSDT.getText());
        kh.setDiaChi(txtdiaChi.getText());
        kh.getTichDiem(Integer.valueOf(lblTichDiem.getText()));
        return kh;

    }

    public void clearFormKhachHang() {
        KhachHangEntity kh = new KhachHangEntity();
        this.setFormKhachHang(kh);
//        txtGiaBanSP.setText(" ");
//        txtNgayNhapSP.setText(" ");
//        txtGiaNhapSP.setText(" ");
        this.row = -1;
        this.updateStatusKhachHang();
    }

    public void updateStatusKhachHang() {
        boolean edit = (this.row >= 0);
        boolean first = (this.row == 0);
        boolean last = (this.row == tblKhachHang.getRowCount() - 1);
        //Trạng thái form
        btnThemKhachHang.setEnabled(edit);
        btncapNhatKhachHang.setEnabled(edit);
        btnxoaKhachHang.setEnabled(edit);
        //Trạng thái điều hướng
        btnFirstSP.setEnabled(edit && !first);
        btnPrevSP.setEnabled(edit && !first);
        btnNextSP.setEnabled(edit && !last);
        btnLastSP.setEnabled(edit && !last);
    }

    public void editKhachHang() {
        countClick = 0;
        String makh = (String) tblKhachHang.getValueAt(this.row, 0);
        KhachHangEntity kh = KhachHang.selectById(makh);
        tblKhachHang.setRowSelectionInterval(this.row, this.row);
        this.setFormKhachHang(kh);
        this.updateStatusKhachHang();
        lblrecordKH.setText(recordKhachHang());
    }

    public void insertKhachHang() {
        KhachHangEntity kh = getFormKhachHang();
        try {
            KhachHang.insert(kh);
            listKhachHang();
            this.fillTableKhachHang();
            this.clearFormKhachHang();
            MsgBox.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, e + "Thêm mới thất bại!");
        }
    }

    public void updateKhachHang() {
        KhachHangEntity kh = getFormKhachHang();
        try {
            KhachHang.update(kh);
            listKhachHang();
            this.fillTableKhachHang();
            this.clearFormKhachHang();
            MsgBox.alert(this, "Cập nhật thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, e + "Cập nhật thất bại!");
        }
    }

    public void deleteKhachHang() {
        String makh = txtmaKH.getText();
        try {
            KhachHang.delete(makh);
            listKhachHang();
            this.fillTableKhachHang();
            this.clearFormKhachHang();
            MsgBox.alert(this, "Xóa thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Xóa thất bại!");
        }

    }

    public void firstKhachHang() {
        this.row = 0;
        this.editKhachHang();

    }

    public void nextKhachHang() {
        if (this.row < tblKhachHang.getRowCount() - 1) {
            this.row++;
            this.editKhachHang();

        } else {
            this.firstKhachHang();
        }

    }

    public void prevKhachHang() {
        if (this.row > 0) {
            this.row--;
            this.editKhachHang();

        } else {
            this.lastKhachHang();
        }

    }

    public void lastKhachHang() {
        this.row = tblKhachHang.getRowCount() - 1;
        this.editKhachHang();
    }

    private void timKiemKH() {
        String keyword = txtTimKiemKH.getText();
        listKhachHang = (List<KhachHangEntity>) KhachHang.selectById(keyword);
        this.fillTableKhachHang();
        this.clearFormKhachHang();
        this.row = - 1;
        updateStatusKhachHang();
    }

    private void timKiemSP() {
//        String keyword = txtTimKiemSP.getText();
//        list = SanPham.selectByKeyword(keyword);
//        this.fillTableSanPham();
//        this.clearFormSanPham();
//        this.row = - 1;
//        updateStatusSanPham();

    }

    private void timKiemLoai() {
        String keyword = txtTimKiemLoai.getText();
        listLoai = Loai.selectByKeyword(keyword);
        this.fillTableLoai();
        this.clearFormLoai();
        this.row = - 1;
        updateStatusLoai();
    }

    private void timKiemhang() {
        String keyword = txtTimKiemHang.getText();
        listHang = Hang.selectByKeyword(keyword);
        this.fillTableHang();
        this.clearFormHang();
        this.row = - 1;
        updateStatusHang();
    }

    public void sortLoai(int i) {

        String Loai = (String) cboLoai.getSelectedItem();
        if (Loai.equalsIgnoreCase("Mã loại")) {
            Comparator<LoaiHangEntity> azl = new Comparator<LoaiHangEntity>() {
                @Override
                public int compare(LoaiHangEntity sp1, LoaiHangEntity sp2) {
                    return sp1.getMaLH().compareTo(sp2.getMaLH());
                }
            };
            if (i == 0) {
                Collections.sort(listLoai, azl);
            } else {
                Collections.sort(listLoai, azl.reversed());

            }
        } else {
            Comparator<LoaiHangEntity> zal = new Comparator<LoaiHangEntity>() {
                @Override
                public int compare(LoaiHangEntity sp1, LoaiHangEntity sp2) {
                    return sp1.getTenLH().compareTo(sp2.getTenLH());
                }
            };
            if (i == 0) {
                Collections.sort(listLoai, zal);
            } else {
                Collections.sort(listLoai, zal.reversed());

            }
        }

        this.fillTableLoai();

    }

    public void sortHang(int i) {

        String Hang = (String) cboHang.getSelectedItem();
        if (Hang.equalsIgnoreCase("Mã hãng")) {
            Comparator<HangEntity> azh = new Comparator<HangEntity>() {
                @Override
                public int compare(HangEntity sp1, HangEntity sp2) {
                    return sp1.getMaHang().compareTo(sp2.getMaHang());
                }
            };
            if (i == 0) {
                Collections.sort(listHang, azh);
            } else {
                Collections.sort(listHang, azh.reversed());

            }
        } else {
            Comparator<HangEntity> zah = new Comparator<HangEntity>() {
                @Override
                public int compare(HangEntity sp1, HangEntity sp2) {
                    return sp1.getTenHang().compareTo(sp2.getTenHang());
                }
            };
            if (i == 0) {
                Collections.sort(listHang, zah);
            } else {
                Collections.sort(listHang, zah.reversed());

            }
        }
        this.fillTableHang();

    }

    public void SortKH(int i) {
        String kh = (String) cboKh.getSelectedItem();
        Comparator<KhachHangEntity> azkh = new Comparator<KhachHangEntity>() {
            @Override
            public int compare(KhachHangEntity kh1, KhachHangEntity kh2) {
                if (kh.equalsIgnoreCase("Mã khách hàng")) {
                    return kh1.getMaKH().compareTo(kh2.getMaKH());
                } else {
                    return kh1.getHoTen().compareTo(kh2.getHoTen());
                }
            }
        };
        if (i == 0) {
            Collections.sort(listKhachHang, azkh);
        } else {
            Collections.sort(listKhachHang, azkh.reversed());
        }
        this.fillTableKhachHang();
    }

    public void SortSP(int i) {
        String SP = (String) cboSP.getSelectedItem();
        Comparator<SanPhamEntity> azsp = new Comparator<SanPhamEntity>() {
            @Override
            public int compare(SanPhamEntity sp1, SanPhamEntity sp2) {
                if (SP.equalsIgnoreCase("Mã sản phẩm")) {
                    return sp1.getMaSP().compareTo(sp2.getMaSP());
                } else if (SP.equalsIgnoreCase("Tên sản phẩm")) {
                    return sp1.getTenSP().compareTo(sp2.getTenSP());
                } else if (SP.equalsIgnoreCase("Giá bán")) {
                    if (sp1.getGiaBan() > sp2.getGiaBan()) {
                        return 1;
                    } else if (sp1.getGiaBan() < sp2.getGiaBan()) {
                        return -1;
                    } else {
                        return 0;
                    }
                } else {
                    if (sp1.getGiaNhap() > sp2.getGiaNhap()) {
                        return 1;
                    } else if (sp1.getGiaNhap() < sp2.getGiaNhap()) {
                        return -1;
                    } else {
                        return 0;
                    }
                }

            }
        };
        if (i == 0) {
            Collections.sort(list, azsp);
        } else {
            Collections.sort(list, azsp.reversed());
        }

        this.fillTableSanPham();
    }

    public void btnSP() {
        String SP = (String) cboSP.getSelectedItem();
        if (SP.equalsIgnoreCase("Tên sản phẩm") || SP.equalsIgnoreCase("Mã sản phẩm")) {
            btnazsp.setText("A - Z");
            btnzasp.setText("Z - A");
        } else {
            btnazsp.setText("Tăng");
            btnzasp.setText("Giảm");
        }

    }

    void cartShoping() {
        home = new FormHome();
        cardHoaDonSanPham.setLayout(new BorderLayout());
        cardHoaDonSanPham.add(home);
        SANPHAM();
    }

    private void SANPHAM() {
        home.setEvent(new EventItem() {
            @Override
            public void itemClick(Component com, ModelItem item) {

                GioHangTempEntity gh = new GioHangTempEntity();
                String ma = item.getDescription();
                String tensp = item.getItemName();
                float gia = (float) item.getPrice();
                int sl = 1;
                gh.setMaSP(ma);
                gh.setTenSP(tensp);
                gh.setGia(gia);
                gh.setSoLuong(sl);
                listgh.add(gh);
                filltableGioHang();
            }
        });
        int ID = 1;

        for (SanPhamEntity sp : list) {
            File file = new File("src\\com\\images\\" + sp.getHinh() + ".PNG");
            try {
                Image img = ImageIO.read(file);
                lblAnh.setText("");
                int w = lblAnh.getWidth();
                int h = lblAnh.getHeight();
                home.addItem(new ModelItem(ID, sp.getTenSP(), sp.getMaSP(), sp.getGiaBan(), sp.getTenH(), new ImageIcon(img.getScaledInstance(w, h, 0))));

//                lblAnh.setIcon(new ImageIcon(img.getScaledInstance(w, h, 0)));
            } catch (Exception e) {
                System.out.println(e);
            }
            ID++;
        }

    }

    public void filltableGioHang() {
        DefaultTableModel model = (DefaultTableModel) tblCart.getModel();
        model.setRowCount(0);
        try {
            for (GioHangTempEntity gh : listgh) {
                Object[] row = {gh.getMaSP(), gh.getTenSP(), gh.getGia(), gh.getSoLuong()};
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    //Thong ke ------------------------------------------------------------------------
    public void initThongKe() {
        fillCboDay_ThongKe();
        fillCboMonth_ThongKe();
        fillCboYear_ThongKe();
        fillTableNhanVienXX();
        setDataChart(pnlView);
        Clock();
    }

    public void fillCboDay_ThongKe() {
        DefaultComboBoxModel modelSP = (DefaultComboBoxModel) cboDaySP.getModel();
        DefaultComboBoxModel modelDT = (DefaultComboBoxModel) cboDayDT.getModel();
        modelSP.removeAllElements();
        modelDT.removeAllElements();
        modelSP.addElement("Không chọn");
        modelDT.addElement("Không chọn");
        for (int i = 1; i < 32; i++) {
            modelSP.addElement(String.valueOf(i));
            modelDT.addElement(String.valueOf(i));
        }

    }

    public void fillCboMonth_ThongKe() {
        DefaultComboBoxModel modelSP = (DefaultComboBoxModel) cboMonthSP.getModel();
        DefaultComboBoxModel modelDT = (DefaultComboBoxModel) cboMonthDT.getModel();
        modelSP.removeAllElements();
        modelDT.removeAllElements();
        modelSP.addElement("Không chọn");
        modelDT.addElement("Không chọn");
        for (int i = 1; i < 13; i++) {
            modelSP.addElement(String.valueOf(i));
            modelDT.addElement(String.valueOf(i));
        }

    }

    public void fillCboYear_ThongKe() {
        DefaultComboBoxModel modelSP = (DefaultComboBoxModel) cboYearSP.getModel();
        DefaultComboBoxModel modelDT = (DefaultComboBoxModel) cboYearDT.getModel();
        modelSP.removeAllElements();
        modelDT.removeAllElements();
        for (int i = 2020; i < 2023; i++) {
            modelSP.addElement(String.valueOf(i));
            modelDT.addElement(String.valueOf(i));
        }
    }

    public void fillTableSPBanChay() {
        try {
            day = (String) cboDaySP.getSelectedItem();
            month = (String) cboMonthSP.getSelectedItem();
            year = (String) cboYearSP.getSelectedItem();
            DefaultTableModel model = (DefaultTableModel) tblSPBanChay.getModel();
            model.setRowCount(0);
            if (day.equals("Không chọn") && month.equals("Không chọn")) {
                listTKSP = TKdao.getSPBanChay(year);
            }
            if (month.equals("Không chọn")) {
                listTKSP = TKdao.getSPBanChay(year);
            }
            if (!day.equals("Không chọn") && !month.equals("Không chọn")) {
                listTKSP = TKdao.getSPBanChay(day, month, year);
            }
            if (day.equals("Không chọn") && !month.equals("Không chọn")) {
                listTKSP = TKdao.getSPBanChay(month, year);
            }
            for (Object[] row : listTKSP) {
                model.addRow(row);
            }
        } catch (Exception ex) {
            //System.out.println(ex);
        }
    }

    public void fillTableDoanhThu() {
        try {
            DefaultTableModel model = (DefaultTableModel) tblDoanhThu.getModel();
            model.setRowCount(0);
            day = (String) cboDayDT.getSelectedItem();
            month = (String) cboMonthDT.getSelectedItem();
            year = (String) cboYearDT.getSelectedItem();
            if (day.equals("Không chọn") && month.equals("Không chọn")) {
                listTKDT = TKdao.getDoanhthu(year);
            }
            if (month.equals("Không chọn")) {
                listTKDT = TKdao.getDoanhthu(year);
            }
            if (!day.equals("Không chọn") && !month.equals("Không chọn")) {
                listTKDT = TKdao.getDoanhthu(day, month, year);
            }
            if (day.equals("Không chọn") && !month.equals("Không chọn")) {
                listTKDT = TKdao.getDoanhthu(month, year);
            }
            for (Object[] row : listTKDT) {
                model.addRow(row);
            }
        } catch (Exception ex) {
//                System.out.println(ex);
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

    public void ExportFileExcelThongKeSP() {
        try {
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("Danh sách sản phẩm");
            XSSFRow row = null;
            Cell cell = null;
            row = sheet.createRow(0);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("MaSP");

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("TenSP");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("LuotBan");
            int i = 0;
            for (Object[] item : listTKSP) {

                row = sheet.createRow(i + 1);

                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue(String.valueOf(item[0]));

                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue(String.valueOf(item[1]));

                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(String.valueOf(item[2]));
                i++;
            }

            JFileChooser fc = new JFileChooser();
            fc.showOpenDialog(null);
            //fc.setName("Danhsach.xlsx");
            File f = fc.getSelectedFile();
            String path = f.getAbsoluteFile().toString();
            String file = f.getAbsolutePath();
            if (!path.contains(".xlsx")) {
                file = f.getAbsolutePath() + ".xlsx";
            }
            try {
                FileOutputStream fis = new FileOutputStream(file);
                wb.write(fis);
                fis.close();
                MsgBox.alert(this, "Xuất thành công");
            } catch (Exception ex) {
                System.out.println("lỗi xuất file " + ex);
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void setDataChart(JPanel jpPie) {
        DefaultPieDataset data = new DefaultPieDataset();
        long millis = System.currentTimeMillis();
        java.sql.Date day = new java.sql.Date(millis);
        SimpleDateFormat formater = new SimpleDateFormat();
        formater.applyPattern("yyyy");
        String thisYear = formater.format(day);
        List<Object[]> listTKSP_L = TKdao.getSPBanChayTL(thisYear);
        float tongsl = 0;
        for (Object[] row : listTKSP_L) {
            tongsl += (int) row[1];
        }
        for (Object[] row : listTKSP_L) {
            data.setValue((String) (row[0]), (((int) row[1] / tongsl) * 100));
        }
        JFreeChart Chart = ChartFactory.createPieChart("Tỷ lệ phần trăm sản phẩm bán được", data, true, true, true);
        //JFreeChart Chart = ChartFactory.createPieChart3D("Tỷ lệ phần trăm sản phẩm bán được", data, true, true, true);
        Chart.setBackgroundPaint(Color.WHITE);
        ChartPanel chartPanel = new ChartPanel(Chart);
        chartPanel.setPreferredSize(new Dimension(jpPie.getWidth(), jpPie.getHeight()));
        chartPanel.setBackground(Color.WHITE);
        
        jpPie.removeAll();
        jpPie.setLayout(new CardLayout());
        jpPie.add(chartPanel);
        jpPie.validate();
        jpPie.setBackground(Color.WHITE);
        jpPie.repaint();
    }
    boolean threadClock = true;

    void Clock() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        if (!threadClock) {
                            return;
                        }
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
//NhanVien

    public void initNhanVien() {
        setLocationRelativeTo(null);
        this.fillTableNhanVien();
        this.row = -1;
        this.updateStatusNhanVien();
    }

    public void fillTableNhanVien() {
        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        model.setRowCount(0);
        try {
            List<TaiKhoanEntity> list = NhanVien.selectAll();

            for (TaiKhoanEntity nv : list) {

                Object[] row = {nv.getTenDN(), nv.getTenNV(), nv.getTenCV(), nv.getEmail(),
                    nv.isTrangThai() ? "Đang hoạt động" : "Ngưng hoạt động", nv.getMatKhau(),
                    nv.getDiaChi(), nv.getDienThoai(), nv.getNgaySinh(), nv.isGioiTInh()};
                model.addRow(row);
            }
        } catch (Exception e) {
            System.out.println(e);
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    public void updateStatusNhanVien() {
        boolean edit = (this.row >= 0);
        boolean first = (this.row == 0);
        boolean last = (this.row == tblNhanVien.getRowCount() - 1);
        //Trạng thái form
        btnThemNV.setEnabled(edit);
        btnCapNhatNV.setEnabled(edit);
        btnXoaNV.setEnabled(edit);
        //Trạng thái điều hướng
        btnFirstNV.setEnabled(edit && !first);
        btnPrevNV.setEnabled(edit && !first);
        btnNextNV.setEnabled(edit && !last);
        btnLastNV.setEnabled(edit && !last);
    }

    public void setFormNhanVien(TaiKhoanEntity tk) {
        txtTenDN.setText(tk.getTenDN());
        txtHoTenNV.setText(tk.getTenNV());
        cboVaiTro.setSelectedItem(tk.getTenCV());
        txtEmailNV.setText(tk.getEmail());
        txtMatKhauNV.setText(tk.getMatKhau());
        txtDiaChiNV.setText(tk.getDiaChi());
        txtSDTNV.setText(tk.getDienThoai());
        txtNgaySinhNV.setText(String.valueOf(tk.getNgaySinh()));
        rdoNam.setSelected(tk.isGioiTInh());
        rdoNu.setSelected(!tk.isGioiTInh());
        if (tk.isTrangThai()) {
            txtTrangThaiNV.setText("Đang hoạt động");
            txtTrangThaiNV.setForeground(new Color(0, 153, 0));
            sbtnTrangThaiNV.setSelected(true);
        } else {
            sbtnTrangThaiNV.setSelected(false);
            txtTrangThaiNV.setText("Ngưng hoạt động");
            txtTrangThaiNV.setForeground(Color.red);
        }
    }

    public TaiKhoanEntity getFormNhanVien() {
        TaiKhoanEntity h = new TaiKhoanEntity();
        h.setTenDN(txtTenDN.getText());
        h.setTenNV(txtHoTenNV.getText());
        h.setTenCV((String) cboVaiTro.getSelectedItem());
        h.setEmail(txtEmailNV.getText());
        h.setMatKhau(txtMatKhauNV.getText());
        h.setDiaChi(txtDiaChiNV.getText());
        h.setDienThoai(txtSDTNV.getText());
        h.setNgaySinh(XDate.toDate(txtNgaySinhNV.getText(), "yyyy-MM-dd"));
        h.setGioiTInh(rdoNam.isSelected());
        h.setTrangThai(sbtnTrangThaiNV.isSelected());

        return h;

    }

    public void setTrangThaiHoatDong() {
        if (tblNhanVien.getSelectedRow() > -1) {
            int ketQua = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn thay đổi trạng thái hoạt động của người dùng này?", "Cập nhật trạng thái", JOptionPane.YES_OPTION);
            if (ketQua == JOptionPane.YES_OPTION) {
                //switchButton();
                if (sbtnTrangThaiNV.isSelected()) {
                    txtTrangThaiNV.setText("Đang hoạt động");
                    txtTrangThaiNV.setForeground(new Color(0, 153, 0));
                } else {
                    txtTrangThaiNV.setText("Ngưng hoạt động");
                    txtTrangThaiNV.setForeground(Color.red);
                }
                //updateAccount();
            } else {
                if (sbtnTrangThaiNV.isSelected()) {
                    sbtnTrangThaiNV.setSelected(false);
                } else {
                    sbtnTrangThaiNV.setSelected(true);
                }
            }
        }
    }

    public void editNV() {
        countClick = 0;
        String tendn = (String) tblNhanVien.getValueAt(this.row, 0);
        TaiKhoanEntity h = NhanVien.selectById(tendn);
        tblNhanVien.setRowSelectionInterval(this.row, this.row);
        this.setFormNhanVien(h);
        this.updateStatusNhanVien();
        lblRecordNV.setText(recordNV());
        lblrecordHang.setText(recordHang());
        lblrecordLoai.setText(recordLoai());
    }

    public String recordNV() {
        List<TaiKhoanEntity> list = NhanVien.selectAll();
        return (row + 1) + " trên " + list.size();
    }

    public void clearFormNV() {
        TaiKhoanEntity nv = new TaiKhoanEntity();
        this.setFormNhanVien(nv);
        this.row = -1;
        this.updateStatusNhanVien();
    }

    public void insertNV() {
        TaiKhoanEntity nv = getFormNhanVien();
        try {
            NhanVien.insert(nv);
            listNVT();
            this.fillTableNhanVien();
            this.clearFormNV();
            MsgBox.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, e + "Thêm mới thất bại!");
        }
    }

    public void updateNV() {
        TaiKhoanEntity nv = getFormNhanVien();
        try {
            NhanVien.update(nv);
            listNVT();
            this.fillTableNhanVien();
            this.clearFormNV();
            MsgBox.alert(this, "Cập nhật thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, e + "Cập nhật thất bại!");
        }
    }

    public void deleteNV() {
        String tendn = txtTenDN.getText();
        try {
            NhanVien.delete(tendn);
            listNVT();
            this.fillTableNhanVien();
            this.clearFormNV();
            MsgBox.alert(this, "Xóa thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Xóa thất bại!");
        }

    }

    public void firstNV() {
        this.row = 0;
        this.editNV();

    }

    public void nextNV() {
        if (this.row < tblNhanVien.getRowCount() - 1) {
            this.row++;
            this.editNV();

        } else {
            this.firstNV();
        }

    }

    public void prevNV() {
        if (this.row > 0) {
            this.row--;
            this.editNV();

        } else {
            this.lastNV();
        }

    }

    public void lastNV() {
        this.row = tblNhanVien.getRowCount() - 1;
        this.editNV();
    }

    public boolean checkNhanVien() {
        if (txtTenDN.getText().equals(" ")) {
            MsgBox.alert(this, " Tên đăng nhập không để trống!");
            txtTenDN.requestFocus();
            return false;
        }
        if (txtHoTenNV.getText().equals(" ")) {
            MsgBox.alert(this, "Họ tên nhân viên không để trống!");
            txtHoTenNV.requestFocus();
            return false;
        }
        if (txtMatKhauNV.getText().equals(" ")) {
            MsgBox.alert(this, "Mật khẩu không được để trống!");
            txtMatKhauNV.requestFocus();
            return false;
        }
        if (txtDiaChiNV.getText().equals(" ")) {
            MsgBox.alert(this, "Dia chi khong de trong!");
            txtDiaChiNV.requestFocus();
            return false;
        }
        if (txtNgaySinhNV.getText().equals(" ")) {
            MsgBox.alert(this, "Ngay sinh khong de trong!");
            txtNgaySinhNV.requestFocus();
            return false;
        }
        if (txtEmailNV.getText().equals(" ")) {
            MsgBox.alert(this, "Email không được để trống!");
            txtEmailNV.requestFocus();
            return false;
        } else {
            Matcher matcher = Pattern.compile(MAIL_REGEX).matcher(txtEmailNV.getText());
            if (matcher.matches() == false) {
                MsgBox.alert(this, "Email không đúng dịnh dạng!");
                txtEmailNV.requestFocus();
                return false;
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        pnMenu = new javax.swing.JPanel();
        header2 = new com.frame.Header();
        jSeparator2 = new javax.swing.JSeparator();
        btnTrangChu = new com.swing.Button();
        btnTaiKhoan = new com.swing.Button();
        btnSanPham = new com.swing.Button();
        btnGioHang = new com.swing.Button();
        btnHoaDon = new com.swing.Button();
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
        TaiKhoantittle1 = new javax.swing.JLabel();
        TaiKhoantittle3 = new javax.swing.JLabel();
        TaiKhoanHr1 = new javax.swing.JLabel();
        TaiKhoantittle2 = new javax.swing.JLabel();
        TaiKhoanHr2 = new javax.swing.JLabel();
        TaiKhoanHr3 = new javax.swing.JLabel();
        cardMenubarSanPham = new javax.swing.JPanel();
        SanPhamTittle1 = new javax.swing.JLabel();
        SanPhamTittle3 = new javax.swing.JLabel();
        SanPhamHr = new javax.swing.JLabel();
        SanPhamTittle2 = new javax.swing.JLabel();
        SanPhamHr1 = new javax.swing.JLabel();
        SanPhamHr2 = new javax.swing.JLabel();
        cardMenubarGioHang = new javax.swing.JPanel();
        GioHangTittle1 = new javax.swing.JLabel();
        GioHangHr = new javax.swing.JLabel();
        cardMenubarHoaDon = new javax.swing.JPanel();
        HoaDonTittle1 = new javax.swing.JLabel();
        HoaDonTittle2 = new javax.swing.JLabel();
        HoaDonHr1 = new javax.swing.JLabel();
        HoaDonHr2 = new javax.swing.JLabel();
        cardMenubarGioiThieu = new javax.swing.JPanel();
        GioiThieutittle1 = new javax.swing.JLabel();
        GioiThieutittle2 = new javax.swing.JLabel();
        GioiThieuHr1 = new javax.swing.JLabel();
        GioiThieuHr2 = new javax.swing.JLabel();
        cardMenubarKhachHang = new javax.swing.JPanel();
        KhachHangTittle1 = new javax.swing.JLabel();
        KhachHangHr = new javax.swing.JLabel();
        cardMenubarThongKe = new javax.swing.JPanel();
        ThongKeTittle1 = new javax.swing.JLabel();
        ThongKeTittle2 = new javax.swing.JLabel();
        ThongKeHr1 = new javax.swing.JLabel();
        ThongKeHr2 = new javax.swing.JLabel();
        jplContainer = new javax.swing.JPanel();
        cardTrangChu = new javax.swing.JPanel();
        cardTrangChuTongQuan = new com.swing.PanelRound();
        panelRound1 = new com.swing.PanelRound();
        panelRound8 = new com.swing.PanelRound();
        lblTime = new javax.swing.JLabel();
        lblDay = new javax.swing.JLabel();
        panelRound5 = new com.swing.PanelRound();
        panelRound6 = new com.swing.PanelRound();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        panelRound7 = new com.swing.PanelRound();
        jLabel12 = new javax.swing.JLabel();
        panelRound4 = new com.swing.PanelRound();
        jLabel8 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lblNV1 = new javax.swing.JLabel();
        lblNV2 = new javax.swing.JLabel();
        lblNV3 = new javax.swing.JLabel();
        panelRound2 = new com.swing.PanelRound();
        panelRound3 = new com.swing.PanelRound();
        cardThongKeDoanhThu = new com.swing.PanelRound();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblDoanhThu = new javax.swing.JTable();
        jLabel43 = new javax.swing.JLabel();
        cboYearDT = new com.swing.Combobox();
        jLabel44 = new javax.swing.JLabel();
        cboDayDT = new com.swing.Combobox();
        cboMonthDT = new com.swing.Combobox();
        jLabel45 = new javax.swing.JLabel();
        pnlView = new javax.swing.JPanel();
        cardThongKeSanPham = new com.swing.PanelRound();
        jLabel46 = new javax.swing.JLabel();
        cboYearSP = new com.swing.Combobox();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblSPBanChay = new javax.swing.JTable();
        jLabel47 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        cboDaySP = new com.swing.Combobox();
        cboMonthSP = new com.swing.Combobox();
        btnExport = new com.swing.Button();
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
        btnNextNV = new com.swing.Button();
        btnPrevNV = new com.swing.Button();
        btnLastNV = new com.swing.Button();
        jLabel82 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        btnFirstNV = new com.swing.Button();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        jLabel85 = new javax.swing.JLabel();
        lblRecordNV = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        btnLamMoiNV = new com.swing.Button();
        btnCapNhatNV = new com.swing.Button();
        btnXoaNV = new com.swing.Button();
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
        btnThemNV = new com.swing.Button();
        jLabel33 = new javax.swing.JLabel();
        txtHoTenNV = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txtTenDN = new javax.swing.JTextField();
        txtEmailNV = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        txtSDTNV = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        txtDiaChiNV = new javax.swing.JTextField();
        txtNgaySinhNV = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        txtMatKhauNV = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        rdoNu = new javax.swing.JRadioButton();
        rdoNam = new javax.swing.JRadioButton();
        cboVaiTro = new com.swing.Combobox();
        jLabel40 = new javax.swing.JLabel();
        sbtnTrangThaiNV = new com.hicode.switchbutton.SwitchButton();
        txtTrangThaiNV = new javax.swing.JLabel();
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
        lblTimKiemTempSP = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        txtTimKiemSP = new javax.swing.JTextField();
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
        cboSP = new com.swing.Combobox();
        jLabel67 = new javax.swing.JLabel();
        jPanel35 = new javax.swing.JPanel();
        btnazsp = new com.swing.EditButton();
        btnzasp = new com.swing.EditButton();
        jLabel72 = new javax.swing.JLabel();
        jSeparator33 = new javax.swing.JSeparator();
        jSeparator34 = new javax.swing.JSeparator();
        jSeparator35 = new javax.swing.JSeparator();
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
        jLabel76 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
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
        lblHangemp = new javax.swing.JLabel();
        txtTimKiemHang = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        btnLamMoiHang = new com.swing.Button();
        btnCapNhatHang = new com.swing.Button();
        btnXoaHang = new com.swing.Button();
        jPanel7 = new javax.swing.JPanel();
        cboHang = new com.swing.Combobox();
        jLabel102 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        btnazHang = new com.swing.EditButton();
        btnzaHang = new com.swing.EditButton();
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
        lblLoaiTemp = new javax.swing.JLabel();
        txtTimKiemLoai = new javax.swing.JTextField();
        jLabel105 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        btnLamMoiLoai = new com.swing.Button();
        btnCapNhatLoai = new com.swing.Button();
        btnXoaLoai = new com.swing.Button();
        jPanel24 = new javax.swing.JPanel();
        cboLoai = new com.swing.Combobox();
        jLabel91 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        btnazLoai = new com.swing.EditButton();
        btnzaLoai = new com.swing.EditButton();
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
        cardGioHang = new com.swing.PanelRound();
        cardHoaDonSanPham = new javax.swing.JPanel();
        textField5 = new com.swing.TextField();
        jLabel77 = new javax.swing.JLabel();
        button1 = new com.swing.Button();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblCart = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        btnxoagiohang = new com.swing.Button();
        button10 = new com.swing.Button();
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
        lblTichDiem = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnNextKH = new com.swing.Button();
        btnFirstKH = new com.swing.Button();
        btnPrevKH = new com.swing.Button();
        btnLastKH = new com.swing.Button();
        jLabel60 = new javax.swing.JLabel();
        txtTimKiemKH = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnlamMoiKhachHang = new com.swing.Button();
        btncapNhatKhachHang = new com.swing.Button();
        btnxoaKhachHang = new com.swing.Button();
        jPanel4 = new javax.swing.JPanel();
        cboKh = new com.swing.Combobox();
        jLabel29 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        btnaz = new com.swing.EditButton();
        btnza = new com.swing.EditButton();
        jLabel31 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        btnThemKhachHang = new com.swing.Button();
        jLabel62 = new javax.swing.JLabel();
        lblrecordKH = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        txthoTen = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        txtmaKH = new javax.swing.JTextField();
        txtdiaChi = new javax.swing.JTextField();
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

        btnGioHang.setText("Giỏ hàng");
        btnGioHang.setEffectColor(new java.awt.Color(0, 153, 255));
        btnGioHang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnGioHang.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGioHangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGioHangMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnGioHangMouseReleased(evt);
            }
        });
        btnGioHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGioHangActionPerformed(evt);
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
                            .addComponent(btnGioHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnHoaDon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(btnHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGioHang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        TaiKhoantittle1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TaiKhoantittle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TaiKhoantittle1.setText("Nhân viên");
        TaiKhoantittle1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TaiKhoantittle1MousePressed(evt);
            }
        });
        cardMenubarTaiKhoan.add(TaiKhoantittle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 110, 30));

        TaiKhoantittle3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TaiKhoantittle3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TaiKhoantittle3.setText("Chức vụ");
        TaiKhoantittle3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TaiKhoantittle3MousePressed(evt);
            }
        });
        cardMenubarTaiKhoan.add(TaiKhoantittle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, 110, 30));

        TaiKhoanHr1.setBackground(new java.awt.Color(0, 153, 0));
        TaiKhoanHr1.setOpaque(true);
        cardMenubarTaiKhoan.add(TaiKhoanHr1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 43, 110, 5));

        TaiKhoantittle2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TaiKhoantittle2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TaiKhoantittle2.setText("Khách Hàng");
        TaiKhoantittle2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TaiKhoantittle2MousePressed(evt);
            }
        });
        cardMenubarTaiKhoan.add(TaiKhoantittle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 110, 30));

        TaiKhoanHr2.setBackground(new java.awt.Color(0, 153, 0));
        TaiKhoanHr2.setOpaque(true);
        cardMenubarTaiKhoan.add(TaiKhoanHr2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 43, 110, 5));

        TaiKhoanHr3.setBackground(new java.awt.Color(0, 153, 0));
        TaiKhoanHr3.setOpaque(true);
        cardMenubarTaiKhoan.add(TaiKhoanHr3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 43, 110, 5));

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

        SanPhamHr1.setBackground(new java.awt.Color(0, 153, 0));
        SanPhamHr1.setOpaque(true);
        cardMenubarSanPham.add(SanPhamHr1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 43, 110, 5));

        SanPhamHr2.setBackground(new java.awt.Color(0, 153, 0));
        SanPhamHr2.setOpaque(true);
        cardMenubarSanPham.add(SanPhamHr2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 43, 110, 5));

        cardMenubar.add(cardMenubarSanPham, "card2");

        cardMenubarGioHang.setBackground(new java.awt.Color(255, 255, 255));
        cardMenubarGioHang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        GioHangTittle1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        GioHangTittle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        GioHangTittle1.setText("Giỏ hàng");
        cardMenubarGioHang.add(GioHangTittle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 110, 30));

        GioHangHr.setBackground(new java.awt.Color(0, 153, 0));
        GioHangHr.setOpaque(true);
        cardMenubarGioHang.add(GioHangHr, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 43, 110, 5));

        cardMenubar.add(cardMenubarGioHang, "card2");

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
        HoaDonTittle2.setText("Hóa đơn chi tiết");
        HoaDonTittle2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                HoaDonTittle2MousePressed(evt);
            }
        });
        cardMenubarHoaDon.add(HoaDonTittle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 140, 30));

        HoaDonHr1.setBackground(new java.awt.Color(0, 153, 0));
        HoaDonHr1.setOpaque(true);
        cardMenubarHoaDon.add(HoaDonHr1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 43, 110, 5));

        HoaDonHr2.setBackground(new java.awt.Color(0, 153, 0));
        HoaDonHr2.setOpaque(true);
        cardMenubarHoaDon.add(HoaDonHr2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 43, 140, 5));

        cardMenubar.add(cardMenubarHoaDon, "card2");

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

        GioiThieuHr1.setBackground(new java.awt.Color(0, 153, 0));
        GioiThieuHr1.setOpaque(true);
        cardMenubarGioiThieu.add(GioiThieuHr1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 43, 110, 5));

        GioiThieuHr2.setBackground(new java.awt.Color(0, 153, 0));
        GioiThieuHr2.setOpaque(true);
        cardMenubarGioiThieu.add(GioiThieuHr2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 43, 110, 5));

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

        ThongKeHr1.setBackground(new java.awt.Color(0, 153, 0));
        ThongKeHr1.setOpaque(true);
        cardMenubarThongKe.add(ThongKeHr1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 43, 110, 5));

        ThongKeHr2.setBackground(new java.awt.Color(0, 153, 0));
        ThongKeHr2.setOpaque(true);
        cardMenubarThongKe.add(ThongKeHr2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 43, 160, 5));

        cardMenubar.add(cardMenubarThongKe, "card2");

        jplMenubar.add(cardMenubar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 1140, 50));

        jPanel1.add(jplMenubar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1220, -1));

        jplContainer.setLayout(new java.awt.CardLayout());

        cardTrangChu.setBackground(new java.awt.Color(204, 204, 204));
        cardTrangChu.setLayout(new java.awt.CardLayout());

        cardTrangChuTongQuan.setBackground(new java.awt.Color(255, 255, 255));
        cardTrangChuTongQuan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound1.setBackground(new java.awt.Color(204, 255, 204));
        panelRound1.setRoundBottomRight(50);
        panelRound1.setRoundTopLeft(50);

        panelRound8.setBackground(new java.awt.Color(204, 255, 204));
        panelRound8.setRoundBottomRight(50);
        panelRound8.setRoundTopLeft(50);

        lblTime.setFont(new java.awt.Font("Tahoma", 0, 32)); // NOI18N
        lblTime.setForeground(new java.awt.Color(102, 102, 255));
        lblTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTime.setText("12:20:30 AM");

        lblDay.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblDay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDay.setText("17-11-2022");

        javax.swing.GroupLayout panelRound8Layout = new javax.swing.GroupLayout(panelRound8);
        panelRound8.setLayout(panelRound8Layout);
        panelRound8Layout.setHorizontalGroup(
            panelRound8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound8Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(panelRound8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblDay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTime, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
                .addGap(21, 21, 21))
        );
        panelRound8Layout.setVerticalGroup(
            panelRound8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound8Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblTime)
                .addGap(18, 18, 18)
                .addComponent(lblDay)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
            .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelRound1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelRound8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
            .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelRound1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelRound8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
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

        jScrollPane12.setBorder(null);

        jTextArea2.setEditable(false);
        jTextArea2.setBackground(new java.awt.Color(204, 204, 255));
        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(5);
        jTextArea2.setText("Tổng đài hỗ trợ:\nKỹ thuật: 1800.1763\nKhiếu nại: 1800.1062\nBảo hành: 1800.1064");
        jTextArea2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane12.setViewportView(jTextArea2);

        javax.swing.GroupLayout panelRound6Layout = new javax.swing.GroupLayout(panelRound6);
        panelRound6.setLayout(panelRound6Layout);
        panelRound6Layout.setHorizontalGroup(
            panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound6Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        panelRound6Layout.setVerticalGroup(
            panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound6Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
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
        cardThongKeDoanhThu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane11.setBorder(null);

        tblDoanhThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Thời gian", "Doanh thu"
            }
        ));
        tblDoanhThu.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane11.setViewportView(tblDoanhThu);

        cardThongKeDoanhThu.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 500, 470));

        jLabel43.setText("Năm");
        cardThongKeDoanhThu.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, 40, 40));

        cboYearDT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2022" }));
        cboYearDT.setLabeText("");
        cboYearDT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboYearDTItemStateChanged(evt);
            }
        });
        cboYearDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboYearDTActionPerformed(evt);
            }
        });
        cardThongKeDoanhThu.add(cboYearDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, 120, 40));

        jLabel44.setText("Tháng");
        cardThongKeDoanhThu.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 40, 40));

        cboDayDT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10" }));
        cboDayDT.setLabeText("");
        cboDayDT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboDayDTItemStateChanged(evt);
            }
        });
        cboDayDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDayDTActionPerformed(evt);
            }
        });
        cardThongKeDoanhThu.add(cboDayDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 110, 40));

        cboMonthDT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "6" }));
        cboMonthDT.setLabeText("");
        cboMonthDT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboMonthDTItemStateChanged(evt);
            }
        });
        cboMonthDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMonthDTActionPerformed(evt);
            }
        });
        cardThongKeDoanhThu.add(cboMonthDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 110, 40));

        jLabel45.setText("Ngày");
        cardThongKeDoanhThu.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 40, 30));

        pnlView.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlViewLayout = new javax.swing.GroupLayout(pnlView);
        pnlView.setLayout(pnlViewLayout);
        pnlViewLayout.setHorizontalGroup(
            pnlViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 660, Short.MAX_VALUE)
        );
        pnlViewLayout.setVerticalGroup(
            pnlViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
        );

        cardThongKeDoanhThu.add(pnlView, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 50, 660, 520));

        cardTrangChu.add(cardThongKeDoanhThu, "card3");

        cardThongKeSanPham.setBackground(new java.awt.Color(255, 255, 255));
        cardThongKeSanPham.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel46.setText("Năm");
        cardThongKeSanPham.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 30, 40, 30));

        cboYearSP.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2022" }));
        cboYearSP.setLabeText("");
        cboYearSP.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboYearSPItemStateChanged(evt);
            }
        });
        cboYearSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboYearSPActionPerformed(evt);
            }
        });
        cardThongKeSanPham.add(cboYearSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 10, 130, 50));

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
        jScrollPane10.setViewportView(tblSPBanChay);

        cardThongKeSanPham.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 1180, 500));

        jLabel47.setText("Tháng");
        cardThongKeSanPham.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, 40, 30));

        jLabel59.setText("Ngày");
        cardThongKeSanPham.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 30, 30));

        cboDaySP.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10" }));
        cboDaySP.setLabeText("");
        cboDaySP.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboDaySPItemStateChanged(evt);
            }
        });
        cboDaySP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDaySPActionPerformed(evt);
            }
        });
        cardThongKeSanPham.add(cboDaySP, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 130, 40));

        cboMonthSP.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "6" }));
        cboMonthSP.setLabeText("");
        cboMonthSP.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboMonthSPItemStateChanged(evt);
            }
        });
        cboMonthSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMonthSPActionPerformed(evt);
            }
        });
        cardThongKeSanPham.add(cboMonthSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 130, 40));

        btnExport.setBackground(new java.awt.Color(204, 204, 255));
        btnExport.setText("Xuất file");
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });
        cardThongKeSanPham.add(btnExport, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 30, 80, -1));

        cardTrangChu.add(cardThongKeSanPham, "card4");

        cardTrangChuNoiBat.setBackground(new java.awt.Color(255, 255, 255));
        cardTrangChuNoiBat.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setText("jLabel27");
        cardTrangChuNoiBat.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, 340, 160));

        cardTrangChu.add(cardTrangChuNoiBat, "card5");

        cardTaiKhoanChucVu.setBackground(new java.awt.Color(255, 255, 255));
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
        cardTaiKhoanNhanVien.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel14.setBackground(new java.awt.Color(204, 204, 255));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnNextNV.setBackground(new java.awt.Color(204, 204, 255));
        btnNextNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/arrow-right.png"))); // NOI18N
        btnNextNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextNVActionPerformed(evt);
            }
        });
        jPanel14.add(btnNextNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 11, -1, -1));

        btnPrevNV.setBackground(new java.awt.Color(204, 204, 255));
        btnPrevNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/left-arrow.png"))); // NOI18N
        btnPrevNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevNVActionPerformed(evt);
            }
        });
        jPanel14.add(btnPrevNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 11, -1, -1));

        btnLastNV.setBackground(new java.awt.Color(204, 204, 255));
        btnLastNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/next.png"))); // NOI18N
        btnLastNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastNVActionPerformed(evt);
            }
        });
        jPanel14.add(btnLastNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 11, -1, -1));

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

        btnFirstNV.setBackground(new java.awt.Color(204, 204, 255));
        btnFirstNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/previous.png"))); // NOI18N
        btnFirstNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstNVActionPerformed(evt);
            }
        });
        jPanel14.add(btnFirstNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 11, -1, -1));

        cardTaiKhoanNhanVien.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 1150, 60));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tblNhanVien.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"a", "â", "â", "a", null},
                {"d", "c", "c", "a", null},
                {"z", "c", "m", "d", null},
                {"a", "o", "o", "a", null}
            },
            new String [] {
                "TÊN ĐĂNG NHẬP", "HỌ TÊN", "VAI TRÒ", "EMAIL", "TRẠNG THÁI"
            }
        ));
        tblNhanVien.setGridColor(new java.awt.Color(255, 255, 255));
        tblNhanVien.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblNhanVien.getTableHeader().setResizingAllowed(false);
        tblNhanVien.getTableHeader().setReorderingAllowed(false);
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblNhanVien);

        cardTaiKhoanNhanVien.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 1150, 140));

        jLabel85.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel85.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/visits (1).png"))); // NOI18N
        jLabel85.setText("Bảng ghi:");
        cardTaiKhoanNhanVien.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 540, 110, 40));

        lblRecordNV.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lblRecordNV.setForeground(new java.awt.Color(204, 0, 51));
        lblRecordNV.setText("2 trên 10");
        cardTaiKhoanNhanVien.add(lblRecordNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 540, 120, 40));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 255)));

        btnLamMoiNV.setBackground(new java.awt.Color(204, 204, 255));
        btnLamMoiNV.setText("Làm mới");
        btnLamMoiNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiNVActionPerformed(evt);
            }
        });

        btnCapNhatNV.setBackground(new java.awt.Color(204, 204, 255));
        btnCapNhatNV.setText("Cập nhật");
        btnCapNhatNV.setEffectColor(new java.awt.Color(204, 255, 204));
        btnCapNhatNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatNVActionPerformed(evt);
            }
        });

        btnXoaNV.setBackground(new java.awt.Color(255, 51, 102));
        btnXoaNV.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 0, 51)));
        btnXoaNV.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaNV.setText("Xóa");
        btnXoaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNVActionPerformed(evt);
            }
        });

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

        btnThemNV.setBackground(new java.awt.Color(204, 204, 255));
        btnThemNV.setText("Thêm");
        btnThemNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXoaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoiNV, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhatNV, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(btnLamMoiNV, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCapNhatNV, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        txtHoTenNV.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtHoTenNV.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardTaiKhoanNhanVien.add(txtHoTenNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 180, 30));

        jLabel34.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 0, 255));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel34.setText("Tên đăng nhập:");
        jLabel34.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardTaiKhoanNhanVien.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 110, 30));

        txtTenDN.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTenDN.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardTaiKhoanNhanVien.add(txtTenDN, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 180, 30));

        txtEmailNV.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtEmailNV.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardTaiKhoanNhanVien.add(txtEmailNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 100, 180, 30));

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

        txtSDTNV.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtSDTNV.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardTaiKhoanNhanVien.add(txtSDTNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, 180, 30));

        jLabel37.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 0, 255));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel37.setText("Trạng thái:");
        jLabel37.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardTaiKhoanNhanVien.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 280, 110, 30));

        txtDiaChiNV.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtDiaChiNV.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardTaiKhoanNhanVien.add(txtDiaChiNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 220, 180, 30));

        txtNgaySinhNV.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtNgaySinhNV.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardTaiKhoanNhanVien.add(txtNgaySinhNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 160, 180, 30));

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

        txtMatKhauNV.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtMatKhauNV.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardTaiKhoanNhanVien.add(txtMatKhauNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, 180, 30));

        jLabel41.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(0, 0, 255));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel41.setText("Giới tính:");
        jLabel41.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardTaiKhoanNhanVien.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 110, 30));

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));

        rdoNu.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        rdoNam.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoNam);
        rdoNam.setText("Nam");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 36, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoNam)
                    .addComponent(rdoNu))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        cardTaiKhoanNhanVien.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 270, 180, 60));

        cboVaiTro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Admin", "Quản lí", "Nhân Viên" }));
        cboVaiTro.setSelectedIndex(-1);
        cboVaiTro.setLabeText("Vai trò");
        cardTaiKhoanNhanVien.add(cboVaiTro, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 260, 50));

        jLabel40.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(0, 0, 255));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel40.setText("Địa chỉ:");
        jLabel40.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardTaiKhoanNhanVien.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 220, 110, 30));

        sbtnTrangThaiNV.setBackground(new java.awt.Color(0, 153, 0));
        sbtnTrangThaiNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sbtnTrangThaiNVMouseReleased(evt);
            }
        });
        cardTaiKhoanNhanVien.add(sbtnTrangThaiNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 290, 40, 20));

        txtTrangThaiNV.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtTrangThaiNV.setForeground(new java.awt.Color(0, 153, 51));
        txtTrangThaiNV.setText("Đang hoạt động");
        cardTaiKhoanNhanVien.add(txtTrangThaiNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 290, -1, -1));

        cardTrangChu.add(cardTaiKhoanNhanVien, "card7");

        cardSanPham.setBackground(new java.awt.Color(255, 255, 255));
        cardSanPham.setMinimumSize(new java.awt.Dimension(1180, 570));
        cardSanPham.setPreferredSize(new java.awt.Dimension(1180, 570));
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
        cardLoai2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tblSanPham.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"a", "â", null, null, null, null, null, null},
                {"d", "c", null, null, null, null, null, null},
                {"z", "c", null, null, null, null, null, null},
                {"a", "o", null, null, null, null, null, null}
            },
            new String [] {
                "MÃ SẢM PHẨM", "TÊN SẢN PHẨM", "TÊN LOẠI", "TÊN HÃNG", "GIÁ NHẬP", "GIÁ BÁN", "NGÀY NHẬP", "MÔ TẢ"
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
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblSanPhamMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseReleased(evt);
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
        btnFirstSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstSPActionPerformed(evt);
            }
        });

        btnPrevSP.setBackground(new java.awt.Color(204, 204, 255));
        btnPrevSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/left-arrow.png"))); // NOI18N
        btnPrevSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPrevSPMouseClicked(evt);
            }
        });
        btnPrevSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevSPActionPerformed(evt);
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

        lblTimKiemTempSP.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        lblTimKiemTempSP.setForeground(new java.awt.Color(153, 153, 153));
        lblTimKiemTempSP.setText("Tên sản phẩm");
        lblTimKiemTempSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTimKiemTempSPMouseClicked(evt);
            }
        });

        jLabel80.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel80.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/search.png"))); // NOI18N
        jLabel80.setText("Tìm kiếm:");

        txtTimKiemSP.setBackground(new java.awt.Color(204, 204, 255));
        txtTimKiemSP.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTimKiemSP.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemSPCaretUpdate(evt);
            }
        });
        txtTimKiemSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTimKiemSPMouseClicked(evt);
            }
        });

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
                    .addComponent(lblTimKiemTempSP, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(lblTimKiemTempSP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLastSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrevSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFirstSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNextSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        cardLoai2.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 1150, 60));

        cboMaLoai.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "a", "x", "e", "f", "e", "gd", "" }));
        cboMaLoai.setLabeText("");
        cardLoai2.add(cboMaLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 20, 180, 39));

        cboMaHang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "a", "x", "e", "f", "e", "gd", " " }));
        cboMaHang.setLabeText("");
        cardLoai2.add(cboMaHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 200, 39));

        lblAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAnh.setText("Ảnh");
        lblAnh.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnhMouseClicked(evt);
            }
        });
        cardLoai2.add(lblAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 40, 130, 120));

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

        cboSP.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mã sản phẩm", "Tên sản phẩm", "Giá nhập ", "Giá bán", " " }));
        cboSP.setSelectedIndex(-1);
        cboSP.setLabeText("Sắp xếp theo");
        cboSP.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboSPItemStateChanged(evt);
            }
        });
        cboSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboSPMouseClicked(evt);
            }
        });

        jLabel67.setText(" Tiến hành sắp xếp");
        jLabel67.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jPanel35.setBackground(new java.awt.Color(255, 255, 255));
        jPanel35.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(0, 0, 0)));
        jPanel35.setLayout(null);

        btnazsp.setBackground(new java.awt.Color(102, 204, 255));
        btnazsp.setText("A - Z");
        btnazsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnazspActionPerformed(evt);
            }
        });
        jPanel35.add(btnazsp);
        btnazsp.setBounds(20, 20, 120, 30);

        btnzasp.setBackground(new java.awt.Color(204, 153, 255));
        btnzasp.setText("Z - A");
        btnzasp.setMargin(new java.awt.Insets(5, 14, 14, 14));
        btnzasp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnzaspActionPerformed(evt);
            }
        });
        jPanel35.add(btnzasp);
        btnzasp.setBounds(20, 90, 120, 30);

        jLabel72.setForeground(new java.awt.Color(153, 153, 153));
        jLabel72.setText("hoặc");
        jPanel35.add(jLabel72);
        jLabel72.setBounds(70, 60, 40, 20);
        jPanel35.add(jSeparator33);
        jSeparator33.setBounds(100, 70, 40, 20);
        jPanel35.add(jSeparator34);
        jSeparator34.setBounds(20, 70, 40, 20);

        jSeparator35.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator35.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        jPanel35.add(jSeparator35);
        jSeparator35.setBounds(100, 0, 100, 20);

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXoaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhatSP, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel35, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel67, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboSP, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(cboSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel27Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnLamMoiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCapNhatSP, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        cardLoai2.add(jPanel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 40, 320, 230));

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
        jLabel71.setText("Tên hãng:");
        jLabel71.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardLoai2.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 100, 30));

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
        jLabel108.setText("Tên loại:");
        jLabel108.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardLoai2.add(jLabel108, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 30, 70, 30));

        txtMoTaSP.setColumns(20);
        txtMoTaSP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMoTaSP.setRows(5);
        jScrollPane8.setViewportView(txtMoTaSP);

        cardLoai2.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 170, 420, 110));

        jLabel109.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel109.setForeground(new java.awt.Color(0, 0, 255));
        jLabel109.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel109.setText("Mô tả:");
        jLabel109.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardLoai2.add(jLabel109, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 140, 80, -1));

        jLabel76.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(0, 0, 255));
        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel76.setText("Mã sản phẩm:");
        jLabel76.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardLoai2.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 100, 30));

        jLabel114.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel114.setForeground(new java.awt.Color(0, 0, 255));
        jLabel114.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel114.setText("Giá bán:");
        jLabel114.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardLoai2.add(jLabel114, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 80, 70, 30));

        cardHangSanXuat1.add(cardLoai2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        cardSanPham.add(cardHangSanXuat1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        cardTrangChu.add(cardSanPham, "card8");

        cardHangSanXuat.setBackground(new java.awt.Color(255, 255, 255));
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

        lblHangemp.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        lblHangemp.setForeground(new java.awt.Color(153, 153, 153));
        lblHangemp.setText("Tên hãng sản xuất");
        lblHangemp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHangempMouseClicked(evt);
            }
        });

        txtTimKiemHang.setBackground(new java.awt.Color(204, 204, 255));
        txtTimKiemHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTimKiemHang.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemHangCaretUpdate(evt);
            }
        });
        txtTimKiemHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTimKiemHangMouseClicked(evt);
            }
        });

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHangemp, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiemHang, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHangemp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiemHang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnLastHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrevHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFirstHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNextHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        cboHang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tên hãng", "Mã hãng" }));
        cboHang.setSelectedIndex(-1);
        cboHang.setLabeText("Sắp xếp theo");

        jLabel102.setText(" Tiến hành sắp xếp");
        jLabel102.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));
        jPanel26.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(0, 0, 0)));
        jPanel26.setLayout(null);

        btnazHang.setBackground(new java.awt.Color(102, 204, 255));
        btnazHang.setText("A - Z");
        btnazHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnazHangActionPerformed(evt);
            }
        });
        jPanel26.add(btnazHang);
        btnazHang.setBounds(20, 20, 159, 30);

        btnzaHang.setBackground(new java.awt.Color(204, 153, 255));
        btnzaHang.setText("Z - A");
        btnzaHang.setMargin(new java.awt.Insets(5, 14, 14, 14));
        btnzaHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnzaHangActionPerformed(evt);
            }
        });
        jPanel26.add(btnzaHang);
        btnzaHang.setBounds(20, 90, 159, 30);

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
                            .addComponent(cboHang, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(17, 17, 17))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                            .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap()))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(cboHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        cardLoai1.add(txtMaHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 260, 30));

        jLabel110.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel110.setForeground(new java.awt.Color(0, 0, 255));
        jLabel110.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel110.setText("Mã hãng sản xuất:");
        jLabel110.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardLoai1.add(jLabel110, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 210, 30));

        txtTenHang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTenHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardLoai1.add(txtTenHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 260, 30));

        jLabel111.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel111.setForeground(new java.awt.Color(0, 0, 255));
        jLabel111.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel111.setText("Tên hãng sản xuất:");
        jLabel111.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardLoai1.add(jLabel111, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, 230, 30));

        cardHangSanXuat.add(cardLoai1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        cardTrangChu.add(cardHangSanXuat, "card9");

        cardLoai.setBackground(new java.awt.Color(255, 255, 255));
        cardLoai.setMinimumSize(new java.awt.Dimension(1180, 570));
        cardLoai.setPreferredSize(new java.awt.Dimension(1180, 570));
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
                "MÃ LOẠI", "TÊN LOẠI"
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

        lblLoaiTemp.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        lblLoaiTemp.setForeground(new java.awt.Color(153, 153, 153));
        lblLoaiTemp.setText("Tên loại hàng");
        lblLoaiTemp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLoaiTempMouseClicked(evt);
            }
        });

        txtTimKiemLoai.setBackground(new java.awt.Color(204, 204, 255));
        txtTimKiemLoai.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTimKiemLoai.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemLoaiCaretUpdate(evt);
            }
        });
        txtTimKiemLoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTimKiemLoaiMouseClicked(evt);
            }
        });

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
                    .addComponent(lblLoaiTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiemLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                .addComponent(lblLoaiTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtTimKiemLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        cboLoai.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tên loại", "Mã Loại" }));
        cboLoai.setSelectedIndex(-1);
        cboLoai.setLabeText("Sắp xếp theo");

        jLabel91.setText(" Tiến hành sắp xếp");
        jLabel91.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(0, 0, 0)));
        jPanel25.setLayout(null);

        btnazLoai.setBackground(new java.awt.Color(102, 204, 255));
        btnazLoai.setText("A - Z");
        btnazLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnazLoaiActionPerformed(evt);
            }
        });
        jPanel25.add(btnazLoai);
        btnazLoai.setBounds(20, 20, 159, 30);

        btnzaLoai.setBackground(new java.awt.Color(204, 153, 255));
        btnzaLoai.setText("Z - A");
        btnzaLoai.setMargin(new java.awt.Insets(5, 14, 14, 14));
        btnzaLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnzaLoaiActionPerformed(evt);
            }
        });
        jPanel25.add(btnzaLoai);
        btnzaLoai.setBounds(20, 90, 159, 30);

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
                            .addComponent(cboLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(17, 17, 17))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                            .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap()))))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addComponent(cboLoai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        cardLoai.add(txtMaLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, 260, 30));

        jLabel112.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel112.setForeground(new java.awt.Color(0, 0, 255));
        jLabel112.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel112.setText("Mã loại hàng:");
        jLabel112.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardLoai.add(jLabel112, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 210, 30));

        jLabel113.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel113.setForeground(new java.awt.Color(0, 0, 255));
        jLabel113.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel113.setText("Tên loại hàng:");
        jLabel113.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardLoai.add(jLabel113, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, 230, 30));

        txtTenLoai.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTenLoai.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardLoai.add(txtTenLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, 260, 30));

        cardTrangChu.add(cardLoai, "card10");

        cardGioHang.setBackground(new java.awt.Color(255, 255, 255));
        cardGioHang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cardHoaDonSanPham.setBackground(new java.awt.Color(255, 255, 255));
        cardHoaDonSanPham.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout cardHoaDonSanPhamLayout = new javax.swing.GroupLayout(cardHoaDonSanPham);
        cardHoaDonSanPham.setLayout(cardHoaDonSanPhamLayout);
        cardHoaDonSanPhamLayout.setHorizontalGroup(
            cardHoaDonSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 639, Short.MAX_VALUE)
        );
        cardHoaDonSanPhamLayout.setVerticalGroup(
            cardHoaDonSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );

        cardGioHang.add(cardHoaDonSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 640, 490));

        textField5.setToolTipText("");
        textField5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textField5.setLabelText("Tìm kiếm");
        cardGioHang.add(textField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 210, 60));

        jLabel77.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel77.setText("Danh sách sản phẩm");
        cardGioHang.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 210, 40));

        button1.setBackground(new java.awt.Color(153, 153, 255));
        button1.setActionCommand("Quét mã QR");
        button1.setEffectColor(new java.awt.Color(255, 204, 204));
        button1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        button1.setLabel("Quét mã QR");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        cardGioHang.add(button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 90, 150, 30));

        jScrollPane9.setBorder(null);

        tblCart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Giá", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCart.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane9.setViewportView(tblCart);

        cardGioHang.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 150, 500, 380));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel7.setText("Số CCCD:");
        cardGioHang.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 90, 70, 30));

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardGioHang.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 90, 240, 30));

        btnxoagiohang.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 0, 0)));
        btnxoagiohang.setText("Xóa");
        btnxoagiohang.setEffectColor(new java.awt.Color(255, 0, 0));
        btnxoagiohang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnxoagiohang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoagiohangActionPerformed(evt);
            }
        });
        cardGioHang.add(btnxoagiohang, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 540, 150, 30));

        button10.setBackground(new java.awt.Color(0, 153, 0));
        button10.setForeground(new java.awt.Color(255, 255, 255));
        button10.setText("Mua hàng");
        button10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cardGioHang.add(button10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 540, 150, 30));

        cardTrangChu.add(cardGioHang, "card11");

        cardHoaDonCho.setBackground(new java.awt.Color(255, 255, 255));
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
        cardKhachHang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tblKhachHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Địa chỉ", "Tích điểm"
            }
        ));
        tblKhachHang.setGridColor(new java.awt.Color(255, 255, 255));
        tblKhachHang.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblKhachHang.getTableHeader().setResizingAllowed(false);
        tblKhachHang.getTableHeader().setReorderingAllowed(false);
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblKhachHang);

        cardKhachHang.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 1150, 180));

        lblTichDiem.setFont(new java.awt.Font("Times New Roman", 1, 17)); // NOI18N
        lblTichDiem.setForeground(new java.awt.Color(255, 0, 0));
        lblTichDiem.setText("0");
        cardKhachHang.add(lblTichDiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 220, 30));

        jLabel63.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel63.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/credit-card.png"))); // NOI18N
        jLabel63.setText("Tích điểm:");
        cardKhachHang.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 110, 30));

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnNextKH.setBackground(new java.awt.Color(204, 204, 255));
        btnNextKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/arrow-right.png"))); // NOI18N
        btnNextKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextKHActionPerformed(evt);
            }
        });
        jPanel2.add(btnNextKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 11, -1, -1));

        btnFirstKH.setBackground(new java.awt.Color(204, 204, 255));
        btnFirstKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/previous.png"))); // NOI18N
        btnFirstKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstKHActionPerformed(evt);
            }
        });
        jPanel2.add(btnFirstKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 11, -1, -1));

        btnPrevKH.setBackground(new java.awt.Color(204, 204, 255));
        btnPrevKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/left-arrow.png"))); // NOI18N
        btnPrevKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevKHActionPerformed(evt);
            }
        });
        jPanel2.add(btnPrevKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 11, -1, -1));

        btnLastKH.setBackground(new java.awt.Color(204, 204, 255));
        btnLastKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/next.png"))); // NOI18N
        btnLastKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLastKHMouseClicked(evt);
            }
        });
        btnLastKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastKHActionPerformed(evt);
            }
        });
        jPanel2.add(btnLastKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 11, -1, -1));

        jLabel60.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(153, 153, 153));
        jLabel60.setText(" Nguyễn Văn An");
        jPanel2.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 20, 210, 30));

        txtTimKiemKH.setBackground(new java.awt.Color(204, 204, 255));
        txtTimKiemKH.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTimKiemKH.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemKHCaretUpdate(evt);
            }
        });
        jPanel2.add(txtTimKiemKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 10, 210, 40));

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

        btnlamMoiKhachHang.setBackground(new java.awt.Color(204, 204, 255));
        btnlamMoiKhachHang.setText("Làm mới");
        btnlamMoiKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlamMoiKhachHangActionPerformed(evt);
            }
        });

        btncapNhatKhachHang.setBackground(new java.awt.Color(204, 204, 255));
        btncapNhatKhachHang.setText("Cập nhật");
        btncapNhatKhachHang.setEffectColor(new java.awt.Color(204, 255, 204));
        btncapNhatKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncapNhatKhachHangActionPerformed(evt);
            }
        });

        btnxoaKhachHang.setBackground(new java.awt.Color(255, 51, 102));
        btnxoaKhachHang.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 0, 51)));
        btnxoaKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        btnxoaKhachHang.setText("Xóa");
        btnxoaKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaKhachHangActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        cboKh.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mã khách hàng", "Tên khách hàng" }));
        cboKh.setLabeText("Sắp xếp theo");

        jLabel29.setText(" Tiến hành sắp xếp");
        jLabel29.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(0, 0, 0)));
        jPanel13.setLayout(null);

        btnaz.setBackground(new java.awt.Color(102, 204, 255));
        btnaz.setText("A - Z");
        btnaz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnazActionPerformed(evt);
            }
        });
        jPanel13.add(btnaz);
        btnaz.setBounds(20, 20, 159, 30);

        btnza.setBackground(new java.awt.Color(204, 153, 255));
        btnza.setText("Z - A");
        btnza.setMargin(new java.awt.Insets(5, 14, 14, 14));
        btnza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnzaActionPerformed(evt);
            }
        });
        jPanel13.add(btnza);
        btnza.setBounds(20, 90, 159, 30);

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
                            .addComponent(cboKh, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(17, 17, 17))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap()))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(cboKh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnThemKhachHang.setBackground(new java.awt.Color(204, 204, 255));
        btnThemKhachHang.setText("Thêm");
        btnThemKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKhachHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnxoaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnlamMoiKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncapNhatKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(btnlamMoiKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThemKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btncapNhatKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnxoaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        cardKhachHang.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 30, 410, 230));

        jLabel62.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel62.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/visits (1).png"))); // NOI18N
        jLabel62.setText("Bảng ghi:");
        cardKhachHang.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 540, 110, 40));

        lblrecordKH.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lblrecordKH.setForeground(new java.awt.Color(204, 0, 51));
        lblrecordKH.setText("2 trên 10");
        cardKhachHang.add(lblrecordKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 540, 120, 40));

        jLabel42.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(0, 0, 255));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel42.setText("Họ và tên:");
        jLabel42.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardKhachHang.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 150, 30));

        txthoTen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txthoTen.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardKhachHang.add(txthoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 220, 30));

        txtSDT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSDT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardKhachHang.add(txtSDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, 230, 30));

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

        txtmaKH.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtmaKH.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardKhachHang.add(txtmaKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 220, 30));

        txtdiaChi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtdiaChi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardKhachHang.add(txtdiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 240, 240, 30));

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


    private void GioiThieutittle1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GioiThieutittle1MousePressed
        GioiThieuHr1.setVisible(true);
        GioiThieuHr2.setVisible(false);
        setLocationHr(cardGioiThieuSanPham, GioiThieuHr1, GioiThieutittle1);
    }//GEN-LAST:event_GioiThieutittle1MousePressed

    private void GioiThieutittle2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GioiThieutittle2MousePressed
        GioiThieuHr1.setVisible(false);
        GioiThieuHr2.setVisible(true);
        setLocationHr(cardGioiThieuThanhVien, GioiThieuHr2, GioiThieutittle2);
    }//GEN-LAST:event_GioiThieutittle2MousePressed


    private void TaiKhoantittle1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TaiKhoantittle1MousePressed
        TaiKhoanHr1.setVisible(true);
        TaiKhoanHr2.setVisible(false);
        TaiKhoanHr3.setVisible(false);
        setLocationHr(cardTaiKhoanNhanVien, TaiKhoanHr1, TaiKhoantittle1);
    }//GEN-LAST:event_TaiKhoantittle1MousePressed

    private void TaiKhoantittle3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TaiKhoantittle3MousePressed
        TaiKhoanHr1.setVisible(false);
        TaiKhoanHr2.setVisible(false);
        TaiKhoanHr3.setVisible(true);
        setLocationHr(cardTaiKhoanChucVu, TaiKhoanHr3, TaiKhoantittle3);
    }//GEN-LAST:event_TaiKhoantittle3MousePressed

    private void opacityMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_opacityMousePressed
        closeMenu();
    }//GEN-LAST:event_opacityMousePressed

    private void btnSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSanPhamActionPerformed
        SanPhamHr1.setVisible(false);
        SanPhamHr2.setVisible(false);
        SanPhamHr.setVisible(true);
        btnItemMenu = btnSanPham;
        chooserMenu(2);
        setDefaultItemMenu();
        hoverMenuItem(btnSanPham);
    }//GEN-LAST:event_btnSanPhamActionPerformed

    private void SanPhamTittle1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SanPhamTittle1MousePressed
        SanPhamHr1.setVisible(false);
        SanPhamHr2.setVisible(false);
        SanPhamHr.setVisible(true);
        setLocationHr(cardSanPham, SanPhamHr, SanPhamTittle1);
    }//GEN-LAST:event_SanPhamTittle1MousePressed

    private void SanPhamTittle3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SanPhamTittle3MousePressed
        SanPhamHr1.setVisible(false);
        SanPhamHr.setVisible(false);
        SanPhamHr2.setVisible(true);
        setLocationHr(cardLoai, SanPhamHr2, SanPhamTittle3);
    }//GEN-LAST:event_SanPhamTittle3MousePressed

    private void SanPhamTittle2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SanPhamTittle2MousePressed
        //setLocationHr(cardHangSanXuat, SanPhamHr, 440);
    }//GEN-LAST:event_SanPhamTittle2MousePressed

    private void HoaDonTittle2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HoaDonTittle2MousePressed
        HoaDonHr1.setVisible(false);
        HoaDonHr2.setVisible(true);
        setLocationHr(cardHoaDonCho, HoaDonHr2, HoaDonTittle2);
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
        this.arrBtn = new Button[]{btnTrangChu, btnTaiKhoan, btnSanPham, btnGioiThieu, btnGioHang, btnHoaDon};
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
        this.threadClock = true;
        Clock();
    }//GEN-LAST:event_btnTrangChuActionPerformed

    private void btnTaiKhoanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTaiKhoanMouseEntered
        hoverMenuItem(btnTaiKhoan);
    }//GEN-LAST:event_btnTaiKhoanMouseEntered

    private void btnTaiKhoanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTaiKhoanMouseExited
        nonHoverMenuItem(btnTaiKhoan);
    }//GEN-LAST:event_btnTaiKhoanMouseExited

    private void btnTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaiKhoanActionPerformed
        TaiKhoanHr2.setVisible(false);
        TaiKhoanHr3.setVisible(false);
        TaiKhoanHr1.setVisible(true);
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

    private void btnGioHangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGioHangMouseEntered
        hoverMenuItem(btnGioHang);
    }//GEN-LAST:event_btnGioHangMouseEntered

    private void btnGioHangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGioHangMouseExited
        nonHoverMenuItem(btnGioHang);
    }//GEN-LAST:event_btnGioHangMouseExited

    private void btnGioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGioHangActionPerformed
        btnItemMenu = btnGioHang;
        chooserMenu(3);
        setDefaultItemMenu();
        hoverMenuItem(btnGioHang);
    }//GEN-LAST:event_btnGioHangActionPerformed

    private void btnHoaDonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoaDonMouseEntered
        hoverMenuItem(btnHoaDon);
    }//GEN-LAST:event_btnHoaDonMouseEntered

    private void btnHoaDonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoaDonMouseExited
        nonHoverMenuItem(btnHoaDon);
    }//GEN-LAST:event_btnHoaDonMouseExited

    private void btnHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoaDonActionPerformed
        HoaDonHr2.setVisible(false);
        HoaDonHr1.setVisible(true);
        btnItemMenu = btnHoaDon;
        chooserMenu(4);
        setDefaultItemMenu();
        hoverMenuItem(btnHoaDon);
    }//GEN-LAST:event_btnHoaDonActionPerformed

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
        GioiThieuHr2.setVisible(false);
        GioiThieuHr1.setVisible(true);
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

    private void HoaDonTittle1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HoaDonTittle1MousePressed
        HoaDonHr1.setVisible(true);
        HoaDonHr2.setVisible(false);
        setLocationHr(cardHoaDonCho, HoaDonHr1, HoaDonTittle1);
    }//GEN-LAST:event_HoaDonTittle1MousePressed

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

    private void btnGioHangMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGioHangMouseReleased
        closeMenu();
    }//GEN-LAST:event_btnGioHangMouseReleased

    private void btnHoaDonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoaDonMouseReleased
        closeMenu();
    }//GEN-LAST:event_btnHoaDonMouseReleased

    private void KhachHangTittle1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KhachHangTittle1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KhachHangTittle1MousePressed

    private void ThongKeTittle1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ThongKeTittle1MousePressed
        ThongKeHr2.setVisible(false);
        ThongKeHr1.setVisible(true);
        setLocationHr(cardThongKeDoanhThu, ThongKeHr1, ThongKeTittle1);
    }//GEN-LAST:event_ThongKeTittle1MousePressed

    private void ThongKeTittle2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ThongKeTittle2MousePressed
        ThongKeHr2.setVisible(true);
        ThongKeHr1.setVisible(false);
        setLocationHr(cardThongKeSanPham, ThongKeHr2, ThongKeTittle2);
    }//GEN-LAST:event_ThongKeTittle2MousePressed

    private void SanPhamTittle2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SanPhamTittle2MouseReleased
        //setLocationHr(cardHangSanXuat, SanPhamHr, 440);
        SanPhamHr1.setVisible(true);
        SanPhamHr.setVisible(false);
        SanPhamHr2.setVisible(false);
        setLocationHr(cardHangSanXuat, SanPhamHr1, SanPhamTittle2);
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
        ThongKeHr2.setVisible(false);
        ThongKeHr1.setVisible(true);
        btnItemMenu = btnThongKe;
        chooserMenu(8);
        setDefaultItemMenu();
        hoverMenuItem(btnThongKe);
    }//GEN-LAST:event_btnThongKeActionPerformed

    private void btnLastKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastKHActionPerformed
        lastKhachHang();
    }//GEN-LAST:event_btnLastKHActionPerformed

    private void btnLastLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastLoaiActionPerformed
        lastLoai();
    }//GEN-LAST:event_btnLastLoaiActionPerformed

    private void btnLastHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLastHangActionPerformed

    private void btnLastSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLastSPActionPerformed

    private void btnLastNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLastNVActionPerformed

    private void TaiKhoantittle2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TaiKhoantittle2MousePressed
        TaiKhoanHr1.setVisible(false);
        TaiKhoanHr2.setVisible(true);
        TaiKhoanHr3.setVisible(false);
        setLocationHr(cardKhachHang, TaiKhoanHr2, TaiKhoantittle2);
    }//GEN-LAST:event_TaiKhoantittle2MousePressed

    private void button46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button46ActionPerformed
        lastNV();
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
        if (checkSP() == true) {
            insertSanPham();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemSPActionPerformed

    private void btnCapNhatSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatSPActionPerformed
        if (checkSP() == true) {
            updateSanPham();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCapNhatSPActionPerformed

    private void btnXoaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSPActionPerformed
        deleteSanPham();        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaSPActionPerformed

    private void tblSanPhamMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblSanPhamMousePressed

    private void tblSanPhamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseReleased
        countClick++;
        if (countClick == 1) {
            this.row = tblSanPham.getSelectedRow();
            editSanPham();
        }
    }//GEN-LAST:event_tblSanPhamMouseReleased

    private void txtTimKiemSPCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemSPCaretUpdate
        fillTableSanPham(txtTimKiemSP.getText());
    }//GEN-LAST:event_txtTimKiemSPCaretUpdate

    private void lblTimKiemTempSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTimKiemTempSPMouseClicked
        lblTimKiemTempSP.setVisible(false);
        txtTimKiemSP.requestFocus();
    }//GEN-LAST:event_lblTimKiemTempSPMouseClicked

    private void txtTimKiemSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemSPMouseClicked
        lblTimKiemTempSP.setVisible(false);
        txtTimKiemSP.requestFocus();
    }//GEN-LAST:event_txtTimKiemSPMouseClicked

    private void lblLoaiTempMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLoaiTempMouseClicked
        lblLoaiTemp.setVisible(false);
        txtTimKiemLoai.requestFocus();
    }//GEN-LAST:event_lblLoaiTempMouseClicked

    private void txtTimKiemLoaiCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemLoaiCaretUpdate
        timKiemLoai();
    }//GEN-LAST:event_txtTimKiemLoaiCaretUpdate

    private void txtTimKiemLoaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemLoaiMouseClicked
        lblLoaiTemp.setVisible(false);
        txtTimKiemLoai.requestFocus();
    }//GEN-LAST:event_txtTimKiemLoaiMouseClicked

    private void lblHangempMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHangempMouseClicked
        lblHangemp.setVisible(false);
        txtTimKiemHang.requestFocus();
    }//GEN-LAST:event_lblHangempMouseClicked

    private void txtTimKiemHangCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemHangCaretUpdate
        timKiemhang();
    }//GEN-LAST:event_txtTimKiemHangCaretUpdate

    private void txtTimKiemHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemHangMouseClicked
        lblHangemp.setVisible(false);
        txtTimKiemHang.requestFocus();
    }//GEN-LAST:event_txtTimKiemHangMouseClicked

    private void btnazLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnazLoaiActionPerformed
        sortLoai(0);
    }//GEN-LAST:event_btnazLoaiActionPerformed

    private void btnzaLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnzaLoaiActionPerformed
        sortLoai(1);
    }//GEN-LAST:event_btnzaLoaiActionPerformed

    private void btnazHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnazHangActionPerformed
        sortHang(0);
    }//GEN-LAST:event_btnazHangActionPerformed

    private void btnzaHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnzaHangActionPerformed
        sortHang(1);
    }//GEN-LAST:event_btnzaHangActionPerformed

    private void cboSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboSPMouseClicked
//        btnSP();
    }//GEN-LAST:event_cboSPMouseClicked

    private void btnazspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnazspActionPerformed
        SortSP(0);
    }//GEN-LAST:event_btnazspActionPerformed

    private void btnzaspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnzaspActionPerformed
        SortSP(1);
    }//GEN-LAST:event_btnzaspActionPerformed

    private void cboSPItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboSPItemStateChanged
        btnSP();
    }//GEN-LAST:event_cboSPItemStateChanged

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        ScanQRProduct fr = new ScanQRProduct();
        fr.setVisible(true);
    }//GEN-LAST:event_button1ActionPerformed

    private void cboYearDTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboYearDTItemStateChanged
        fillTableDoanhThu();
    }//GEN-LAST:event_cboYearDTItemStateChanged

    private void cboYearDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboYearDTActionPerformed

    }//GEN-LAST:event_cboYearDTActionPerformed

    private void cboDayDTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboDayDTItemStateChanged
        fillTableDoanhThu();
    }//GEN-LAST:event_cboDayDTItemStateChanged

    private void cboDayDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDayDTActionPerformed

    }//GEN-LAST:event_cboDayDTActionPerformed

    private void cboMonthDTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboMonthDTItemStateChanged
        fillTableDoanhThu();
    }//GEN-LAST:event_cboMonthDTItemStateChanged

    private void cboMonthDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMonthDTActionPerformed

    }//GEN-LAST:event_cboMonthDTActionPerformed

    private void cboYearSPItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboYearSPItemStateChanged
        fillTableSPBanChay();
    }//GEN-LAST:event_cboYearSPItemStateChanged

    private void cboYearSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboYearSPActionPerformed

    }//GEN-LAST:event_cboYearSPActionPerformed

    private void cboDaySPItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboDaySPItemStateChanged
        fillTableSPBanChay();
    }//GEN-LAST:event_cboDaySPItemStateChanged

    private void cboDaySPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDaySPActionPerformed

    }//GEN-LAST:event_cboDaySPActionPerformed

    private void cboMonthSPItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboMonthSPItemStateChanged
        fillTableSPBanChay();
    }//GEN-LAST:event_cboMonthSPItemStateChanged

    private void cboMonthSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMonthSPActionPerformed

    }//GEN-LAST:event_cboMonthSPActionPerformed

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed
        ExportFileExcelThongKeSP();
    }//GEN-LAST:event_btnExportActionPerformed

    private void btnlamMoiKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlamMoiKhachHangActionPerformed
        clearFormKhachHang();
    }//GEN-LAST:event_btnlamMoiKhachHangActionPerformed

    private void btnThemKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKhachHangActionPerformed
        if (checkKH() == true) {
            insertKhachHang();
        }
    }//GEN-LAST:event_btnThemKhachHangActionPerformed

    private void btncapNhatKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncapNhatKhachHangActionPerformed
        if (checkKH() == true) {
            updateKhachHang();
        }
    }//GEN-LAST:event_btncapNhatKhachHangActionPerformed

    private void btnxoaKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaKhachHangActionPerformed
        deleteKhachHang();
    }//GEN-LAST:event_btnxoaKhachHangActionPerformed

    private void btnFirstKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstKHActionPerformed
        firstKhachHang();
    }//GEN-LAST:event_btnFirstKHActionPerformed

    private void btnFirstSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFirstSPActionPerformed

    private void btnPrevSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPrevSPActionPerformed

    private void btnPrevKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevKHActionPerformed
        prevKhachHang();
    }//GEN-LAST:event_btnPrevKHActionPerformed

    private void btnNextKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextKHActionPerformed
        nextKhachHang();
    }//GEN-LAST:event_btnNextKHActionPerformed

    private void btnLastKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLastKHMouseClicked

    }//GEN-LAST:event_btnLastKHMouseClicked

    private void tblKhachHangMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseReleased
        countClick++;
        if (countClick == 1) {
            this.row = tblKhachHang.getSelectedRow();
            editKhachHang();
        }
    }//GEN-LAST:event_tblKhachHangMouseReleased

    private void txtTimKiemKHCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemKHCaretUpdate
        timKiemKH();
    }//GEN-LAST:event_txtTimKiemKHCaretUpdate

    private void btnzaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnzaActionPerformed
        SortKH(1);
    }//GEN-LAST:event_btnzaActionPerformed

    private void btnazActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnazActionPerformed
        SortKH(0);
    }//GEN-LAST:event_btnazActionPerformed

    private void btnxoagiohangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoagiohangActionPerformed
        int i = tblCart.getSelectedRow();
        listgh.remove(i);
        filltableGioHang();
    }//GEN-LAST:event_btnxoagiohangActionPerformed

    private void sbtnTrangThaiNVMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sbtnTrangThaiNVMouseReleased
        setTrangThaiHoatDong();
    }//GEN-LAST:event_sbtnTrangThaiNVMouseReleased

    private void btnLamMoiNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiNVActionPerformed
        clearFormNV();
    }//GEN-LAST:event_btnLamMoiNVActionPerformed

    private void btnThemNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNVActionPerformed
        if (checkNhanVien() == true) {
            insertNV();
        }
    }//GEN-LAST:event_btnThemNVActionPerformed

    private void btnCapNhatNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatNVActionPerformed
        if (checkNhanVien() == true) {
            updateNV();
        }
    }//GEN-LAST:event_btnCapNhatNVActionPerformed

    private void btnXoaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNVActionPerformed
        deleteNV();
    }//GEN-LAST:event_btnXoaNVActionPerformed

    private void btnFirstNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstNVActionPerformed
        firstNV();
    }//GEN-LAST:event_btnFirstNVActionPerformed

    private void btnPrevNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevNVActionPerformed
        prevNV();
    }//GEN-LAST:event_btnPrevNVActionPerformed

    private void btnNextNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextNVActionPerformed
        nextNV();
    }//GEN-LAST:event_btnNextNVActionPerformed

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        countClick++;
        if (countClick == 1) {
            this.row = tblNhanVien.getSelectedRow();
            editNV();
        }
    }//GEN-LAST:event_tblNhanVienMouseClicked

    public void listSPT() {
        list = SanPham.selectAll();
        List<SanPhamEntity> listSanPhamTemp = new ArrayList<>();
        listSanPhamTemp.addAll(list);
        list.clear();
        list.addAll(listSanPhamTemp);
    }

    public void listHang() {
        listHang = Hang.selectAll();
        List<HangEntity> listHangTemp = new ArrayList<>();
        listHangTemp.addAll(listHang);
        listHang.clear();
        listHang.addAll(listHangTemp);
    }

    public void listLoai() {
        listLoai = Loai.selectAll();
        List<LoaiHangEntity> listLoaiTemp = new ArrayList<>();
        listLoaiTemp.addAll(listLoai);
        listLoai.clear();
        listLoai.addAll(listLoaiTemp);
    }

    public void listKhachHang() {
        listKhachHang = KhachHang.selectAll();
        List<KhachHangEntity> listKHTemp = new ArrayList<>();
        listKHTemp.addAll(listKhachHang);
        listKhachHang.clear();
        listKhachHang.addAll(listKHTemp);
    }

    public void listNVT() {
        listNhanVien = NhanVien.selectAll();
        List<TaiKhoanEntity> listNhanVienTemp = new ArrayList<>();
        listNhanVienTemp.addAll(listNhanVien);
        listNhanVien.clear();
        listNhanVien.addAll(listNhanVienTemp);
    }

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
            java.util.logging.Logger.getLogger(Home.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

    //bật tắt webcame
    /*
    if (result != null) {
                //System.out.println(result.getText());
                //result_field.setText(result.getText());
                for (SanPhamEntity sp:list){
                    if(sp.getMaSP().equalsIgnoreCase(result.getText())) {
                    webcam.close();
                        //thêm vào giỏ hàng
                    }
                    
                }
                
                //JOptionPane.showMessageDialog(this, "Đăng nhập thành công!\n"+"Xin chào: \t"+std.hightlingtName(accDAO.nameQR(result.getText())));
            }
     */
    //quét mã QR Sản phẩm để thêm vào giỏ hàng

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel GioHangHr;
    private javax.swing.JLabel GioHangTittle1;
    private javax.swing.JLabel GioiThieuHr1;
    private javax.swing.JLabel GioiThieuHr2;
    private javax.swing.JLabel GioiThieutittle1;
    private javax.swing.JLabel GioiThieutittle2;
    private javax.swing.JLabel HoaDonHr1;
    private javax.swing.JLabel HoaDonHr2;
    private javax.swing.JLabel HoaDonTittle1;
    private javax.swing.JLabel HoaDonTittle2;
    private javax.swing.JLabel KhachHangHr;
    private javax.swing.JLabel KhachHangTittle1;
    private javax.swing.JLabel SanPhamHr;
    private javax.swing.JLabel SanPhamHr1;
    private javax.swing.JLabel SanPhamHr2;
    private javax.swing.JLabel SanPhamTittle1;
    private javax.swing.JLabel SanPhamTittle2;
    private javax.swing.JLabel SanPhamTittle3;
    private javax.swing.JLabel TaiKhoanHr1;
    private javax.swing.JLabel TaiKhoanHr2;
    private javax.swing.JLabel TaiKhoanHr3;
    private javax.swing.JLabel TaiKhoantittle1;
    private javax.swing.JLabel TaiKhoantittle2;
    private javax.swing.JLabel TaiKhoantittle3;
    private javax.swing.JLabel ThongKeHr1;
    private javax.swing.JLabel ThongKeHr2;
    private javax.swing.JLabel ThongKeTittle1;
    private javax.swing.JLabel ThongKeTittle2;
    private javax.swing.JLabel TrangChuHr;
    private javax.swing.JLabel TrangChuTittle2;
    private com.swing.Button btnCapNhatHang;
    private com.swing.Button btnCapNhatLoai;
    private com.swing.Button btnCapNhatNV;
    private com.swing.Button btnCapNhatSP;
    private com.swing.Button btnDangXuat;
    private com.swing.Button btnExport;
    private com.swing.Button btnFirstHang;
    private com.swing.Button btnFirstKH;
    private com.swing.Button btnFirstLoai;
    private com.swing.Button btnFirstNV;
    private com.swing.Button btnFirstSP;
    private com.swing.Button btnGioHang;
    private com.swing.Button btnGioiThieu;
    private com.swing.Button btnHoaDon;
    private com.swing.Button btnLamMoiHang;
    private com.swing.Button btnLamMoiLoai;
    private com.swing.Button btnLamMoiNV;
    private com.swing.Button btnLamMoiSP;
    private com.swing.Button btnLastHang;
    private com.swing.Button btnLastKH;
    private com.swing.Button btnLastLoai;
    private com.swing.Button btnLastNV;
    private com.swing.Button btnLastSP;
    private com.swing.Button btnMenu;
    private com.swing.Button btnNextHang;
    private com.swing.Button btnNextKH;
    private com.swing.Button btnNextLoai;
    private com.swing.Button btnNextNV;
    private com.swing.Button btnNextSP;
    private com.swing.Button btnPrevHang;
    private com.swing.Button btnPrevKH;
    private com.swing.Button btnPrevLoai;
    private com.swing.Button btnPrevNV;
    private com.swing.Button btnPrevSP;
    private com.swing.Button btnSanPham;
    private com.swing.Button btnTaiKhoan;
    private com.swing.Button btnThemHang;
    private com.swing.Button btnThemKhachHang;
    private com.swing.Button btnThemLoai;
    private com.swing.Button btnThemNV;
    private com.swing.Button btnThemSP;
    private com.swing.Button btnThongKe;
    private com.swing.Button btnTrangChu;
    private com.swing.Button btnXoaHang;
    private com.swing.Button btnXoaLoai;
    private com.swing.Button btnXoaNV;
    private com.swing.Button btnXoaSP;
    private com.swing.EditButton btnaz;
    private com.swing.EditButton btnazHang;
    private com.swing.EditButton btnazLoai;
    private com.swing.EditButton btnazsp;
    private com.swing.Button btncapNhatKhachHang;
    private com.swing.Button btnlamMoiKhachHang;
    private com.swing.Button btnxoaKhachHang;
    private com.swing.Button btnxoagiohang;
    private com.swing.EditButton btnza;
    private com.swing.EditButton btnzaHang;
    private com.swing.EditButton btnzaLoai;
    private com.swing.EditButton btnzasp;
    private com.swing.Button button1;
    private com.swing.Button button10;
    private com.swing.Button button39;
    private com.swing.Button button40;
    private com.swing.Button button41;
    private com.swing.Button button42;
    private com.swing.Button button43;
    private com.swing.Button button44;
    private com.swing.Button button45;
    private com.swing.Button button46;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.swing.PanelRound cardGiamGia;
    private com.swing.PanelRound cardGioHang;
    private com.swing.PanelRound cardGioiThieuSanPham;
    private com.swing.PanelRound cardGioiThieuThanhVien;
    private com.swing.PanelRound cardHangSanXuat;
    private com.swing.PanelRound cardHangSanXuat1;
    private com.swing.PanelRound cardHoaDonCho;
    private javax.swing.JPanel cardHoaDonSanPham;
    private com.swing.PanelRound cardKhachHang;
    private com.swing.PanelRound cardLoai;
    private com.swing.PanelRound cardLoai1;
    private com.swing.PanelRound cardLoai2;
    private javax.swing.JPanel cardMenubar;
    private javax.swing.JPanel cardMenubarGioHang;
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
    private com.swing.Combobox cboDayDT;
    private com.swing.Combobox cboDaySP;
    private com.swing.Combobox cboHang;
    private com.swing.Combobox cboKh;
    private com.swing.Combobox cboLoai;
    private com.swing.Combobox cboMaHang;
    private com.swing.Combobox cboMaLoai;
    private com.swing.Combobox cboMonthDT;
    private com.swing.Combobox cboMonthSP;
    private com.swing.Combobox cboSP;
    private com.swing.Combobox cboVaiTro;
    private com.swing.Combobox cboYearDT;
    private com.swing.Combobox cboYearSP;
    private com.swing.Combobox combobox6;
    private com.swing.Combobox combobox9;
    private com.swing.EditButton editButton14;
    private com.swing.EditButton editButton15;
    private com.swing.EditButton editButton16;
    private com.swing.EditButton editButton17;
    private com.frame.Header header2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
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
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
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
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
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
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
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
    private javax.swing.JSeparator jSeparator33;
    private javax.swing.JSeparator jSeparator34;
    private javax.swing.JSeparator jSeparator35;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JLabel jlbClose;
    private javax.swing.JLabel jlbState;
    private javax.swing.JPanel jplContainer;
    private javax.swing.JPanel jplLose;
    private javax.swing.JPanel jplMenubar;
    private javax.swing.JPanel jplState;
    private javax.swing.JPanel jplTitle;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JLabel lblDay;
    private javax.swing.JLabel lblHangemp;
    private javax.swing.JLabel lblLoaiTemp;
    private javax.swing.JLabel lblNV1;
    private javax.swing.JLabel lblNV2;
    private javax.swing.JLabel lblNV3;
    private javax.swing.JLabel lblRecordNV;
    private javax.swing.JLabel lblTichDiem;
    private javax.swing.JLabel lblTimKiemTempSP;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblrecordHang;
    private javax.swing.JLabel lblrecordKH;
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
    private com.swing.PanelRound panelRound8;
    private javax.swing.JPanel pnMenu;
    private javax.swing.JPanel pnlView;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private com.hicode.switchbutton.SwitchButton sbtnTrangThaiNV;
    private javax.swing.JTable tblCart;
    private javax.swing.JTable tblDoanhThu;
    private javax.swing.JTable tblHang;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTable tblKhachHang5;
    private javax.swing.JTable tblLoaiHang;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTable tblSPBanChay;
    private javax.swing.JTable tblSanPham;
    private com.swing.TextField textField5;
    private javax.swing.JTextField txtDiaChiNV;
    private javax.swing.JTextField txtEmailNV;
    private javax.swing.JTextField txtGiaBanSP;
    private javax.swing.JTextField txtGiaNhapSP;
    private javax.swing.JTextField txtHoTenNV;
    private javax.swing.JTextField txtMaHang;
    private javax.swing.JTextField txtMaLoai;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtMatKhauNV;
    private javax.swing.JTextArea txtMoTaSP;
    private javax.swing.JTextField txtNgayNhapSP;
    private javax.swing.JTextField txtNgaySinhNV;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSDTNV;
    private javax.swing.JTextField txtTenDN;
    private javax.swing.JTextField txtTenHang;
    private javax.swing.JTextField txtTenLoai;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTimKiemHang;
    private javax.swing.JTextField txtTimKiemKH;
    private javax.swing.JTextField txtTimKiemLoai;
    private javax.swing.JTextField txtTimKiemSP;
    private javax.swing.JLabel txtTrangThaiNV;
    private javax.swing.JTextField txtdiaChi;
    private javax.swing.JTextField txthoTen;
    private javax.swing.JTextField txtmaKH;
    // End of variables declaration//GEN-END:variables
}
