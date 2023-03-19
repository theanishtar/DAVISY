/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui;

import com.library.extensisons.ReadMoney;
import java.awt.event.*;
import AppPackage.AnimationClass;
import com.gui.CaiDat;
import com.gui.MyAccount;
import com.frame.Header;
import java.awt.Color;
import com.ui.drawer.scroll.Drawer;
import com.ui.drawer.scroll.DrawerController;
import com.ui.drawer.scroll.DrawerItem;
import com.ui.drawer.scroll.EventDrawer;
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
import com.dao.ChucVuDAO;
import com.dao.GioHangDAO;
import com.dao.GioHangTamDAO;
import com.dao.HoaDonCTDAO;
import com.dao.HoaDonDAO;
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
import com.library.extensisons.ScanQRProduct;
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
import com.entity.ChucVuEntity;
import com.entity.GioHangEntity;
import com.entity.GioHangTamEntity;
import com.entity.GioHangTempEntity;
import com.entity.HoaDonCTEntity;
import com.entity.HoaDonEntity;
import com.file.FileExtensison;
import com.library.extensisons.Folder;
import com.library.extensisons.Qr;
import com.library.extensisons.SendInforProduct;
import com.library.extensisons.Standardization;
import com.library.extensisons.Validate;
import com.swing.table.TableCustom;
import com.swing.table.TableRowDark;
import com.swing.table.TableRowLight;
import com.utils.XImage;
import java.awt.Desktop;
import java.lang.reflect.Array;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Popup;
import javax.swing.event.PopupMenuEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import org.apache.poi.ss.usermodel.Row;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Tran Huu Dang from DAVISY
 */
public class Home extends javax.swing.JFrame implements Runnable, ThreadFactory {

    /**
     * Creates new form Home
     */
    int chose = -1;
    int row = -1;
    int soLuongNhap = 0;
    int maChucVu = 0;
    int countClick = 0, count = 0, slSP = 0;
    boolean chooserMainPage = true;
    JFileChooser f = new JFileChooser("src\\com\\images");
    File file = f.getSelectedFile();
    String anh = null, maHDT = null;
    String chechMaSp = null;
    String imageSrc = "pc.jpg"; //Lưu đường dẫn của ảnh
    BufferedImage cloneImage, image;
    SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
    private final String MAIL_REGEX = "^[\\w\\.=-]+@[\\w\\.-]+\\.[\\w]{2,3}$";
    private static final String TypeProduct_REGEX = "([A-Z]){2}+([0-9]){4}";
    public static final String SDT = "^\\+?\\d{1,3}?[- .]?\\(?(?:\\d{2,3})\\)?[- .]?\\d\\d\\d[- .]?\\d\\d\\d\\d$";
    public static final String tdn = "[a-Z0-9]{4,6}";
    HangDAO Hang = new HangDAO();
    LoaiHangDAO Loai = new LoaiHangDAO();
    SanPhamDAO SanPham = new SanPhamDAO();
    KhachHangDAO KhachHang = new KhachHangDAO();
    TaiKhoanDAO NhanVien = new TaiKhoanDAO();
    ChucVuDAO chucVu = new ChucVuDAO();
    HoaDonDAO HoaDon = new HoaDonDAO();
    GioHangTamDAO GioHangtam = new GioHangTamDAO();
    HoaDonCTDAO HDCT = new HoaDonCTDAO();
    GioHangDAO Giohang = new GioHangDAO();
    //kiểm tra luồng
    boolean startThread = false;

    //tạo mảng chứa các phần tử card
    JPanel[] arrPn;

    //tạo mảng chứa các phần tử menubar
    JLabel[] arrLbl;

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
    List<TaiKhoanEntity> listTK = new ArrayList<>();
    List<ChucVuEntity> listCV = new ArrayList<>();
    List<HoaDonEntity> listHoaDon = new ArrayList<>();
    List<GioHangTamEntity> listGHT = new ArrayList<>();
    List<HoaDonCTEntity> listCtHD = new ArrayList<>();
    List<GioHangEntity> listGiohang = new ArrayList<>();
    List<GioHangTamEntity> listGioHangHienTai = new ArrayList<>();
    //Thong ke
    ThongKeDAO TKdao = new ThongKeDAO();

    String day;
    String month;
    String year;
    String ktCV = null;
    String ktTenDN = null;
    String CV = null;
    String insertSDT = null;

    List<Object[]> listTKSP = null;
    List<Object[]> listTKDT = null;

    //Quét mã QR sản phẩm
    private WebcamPanel panel = null;
    public Webcam webcam = null;
    long millis = System.currentTimeMillis();
    java.sql.Date dayNow = new java.sql.Date(millis);
    private static final long serialVersionUID = 6441489157408381878L;
    private Executor executor = Executors.newSingleThreadExecutor(this);

    //In hóa đơn
    String billNo = "";
    Double totalAmount = 0.0;
    Double cash = 0.0;
    Double balance = 0.0;
    Double bHeight = 0.0;
    String maHDIn = null;
    float tienGiam = 0;

    ArrayList<String> tenSP = new ArrayList<>();
    ArrayList<String> giaSP = new ArrayList<>();
    ArrayList<String> SL = new ArrayList<>();
    ArrayList<String> thanhTien = new ArrayList<>();

    //Menu chương trình
    private DrawerController drawer;

    public Home() {
        initComponents();
        this.setIconImage(XImage.getAppIcon());
        setTextFileOpenSetting();
        setModeFrame();
        listCV = chucVu.selectAll();
        listTK = NhanVien.selectAll();
        getTenNhanVien("dangth");
        ktTenDN = "dangth";
        loadMain(); //gọi component Loading khi đang chờ kết nối Database
        initMenu(); //gọi lại phương thức khởi tạo MENU

        initThongKe();
        lightModeTK();
        initNhanVien();
        hideCardMenubar();
        hideMenu();
        hidePage();
        cardMenubarTrangChu.setVisible(true);
        cardTrangChuTongQuan.setVisible(true);
        pnMenu.setSize(0, 670);
        this.btnItemMenu = btnTrangChu;
        settingTable();
        initHang();
        initLoai();
        initSanPham();
        initKhachHang();
        initHoaDon();
        initChucVu();
        initgioHang();
        fillComboxCV();
        fillComboxLoai();
        fillComboxHang();
        lblrecordHang.setText(recordHang());
        lblrecordLoai.setText(recordLoai());
        lblrecordSP.setText(recordSanPham());
        SanPhamHr1.setVisible(false);
        loadMainDone = true;
        try {
            banner();
        } catch (Exception ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        tblDoanhThu.setDefaultEditor(Object.class, null);
        tblCart.setDefaultEditor(Object.class, null);

//        System.out.println(this.darkMode);
    }

    public Home(String tenDN) {
        initComponents();
        this.setIconImage(XImage.getAppIcon());
        setTextFileOpenSetting();
        setModeFrame();
        listCV = chucVu.selectAll();
        listTK = NhanVien.selectAll();
        getTenNhanVien(tenDN);
        ktTenDN = tenDN;
        loadMain(); //gọi component Loading khi đang chờ kết nối Database
        initMenu(); //gọi lại phương thức khởi tạo MENU

        initThongKe();
        initNhanVien();
        hideCardMenubar();
        hideMenu();
        hidePage();
        cardMenubarTrangChu.setVisible(true);
        cardTrangChuTongQuan.setVisible(true);
        pnMenu.setSize(0, 670);
        this.btnItemMenu = btnTrangChu;
        settingTable();
        initHang();
        initLoai();
        initSanPham();
        initKhachHang();
        initHoaDon();
        initChucVu();
        initgioHang();
        fillComboxCV();
        fillComboxLoai();
        fillComboxHang();
        lblrecordHang.setText(recordHang());
        lblrecordLoai.setText(recordLoai());
        lblrecordSP.setText(recordSanPham());
        SanPhamHr1.setVisible(false);
        loadMainDone = true;
        try {
            banner();
        } catch (Exception ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        tblDoanhThu.setDefaultEditor(Object.class, null);
        tblCart.setDefaultEditor(Object.class, null);
    }
    boolean loadMainDone = false;

    //phương thức set ảnh cho banner chuyển động
    void initBaner() throws Exception {
        FileExtensison fe = new FileExtensison();
        int w = banner1.getWidth();
        int h = banner1.getHeight();
        try {
            File f1 = new File(fe.getImgBaner1());
            File f2 = new File(fe.getImgBaner2());
            File f3 = new File(fe.getImgBaner3());
            Image img1 = ImageIO.read(f1);
            Image img2 = ImageIO.read(f2);
            Image img3 = ImageIO.read(f3);
            banner1.setIcon(new ImageIcon(img1.getScaledInstance(w, h, 0)));
            banner2.setIcon(new ImageIcon(img2.getScaledInstance(w, h, 0)));
            banner3.setIcon(new ImageIcon(img3.getScaledInstance(w, h, 0)));
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    void loadMain() {

        Thread loadThread = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
                while (true) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (loadMainDone) {
                        loadingMain.setVisible(false);
                        Clock();
                        break;
                    }
                }
            }

        };
        loadThread.start();
    }

    void showLoadBar() {
    }

    void initMenu() {
        drawer = Drawer.newDrawer(this)
                .background(new Color(90, 90, 90))
                .enableScroll(true)
                .header(new Header())
                .space(3)
                .addChild(new DrawerItem("Cửa sổ chính").build())
                .addChild(new DrawerItem("Tài khoản").build())
                .addChild(new DrawerItem("Sản phẩm").build())
                .addChild(new DrawerItem("Hóa đơn").build())
                .addChild(new DrawerItem("Giỏ hàng").build())
                .addChild(new DrawerItem("Thống kê").build())
                .addFooter(new Separator())
                .addFooter(new DrawerItem("Cài đặt").build())
                .addFooter(new DrawerItem("Giới thiệu").build())
                .addFooter(new DrawerItem("Đăng xuất").build())
                .event(new EventDrawer() {
                    @Override
                    public void selected(int index, DrawerItem item) {
                        if (drawer.isShow()) {
                            drawer.hide();
                        }
                        switch (index) {
                            case 0:
                                //gọi trang Main
                                if (chose == -1 || chose != 0) {
                                    hidePage();
                                    hideMenu();
                                    cardMenubarTrangChu.setVisible(true);
                                    cardTrangChuTongQuan.setVisible(true);
                                    chose = 0;
                                }
                                break;
                            case 1:
                                //gọi trang tài khoản
                                if (chose == -1 || chose != 1) {
//                                    System.out.println(CV);
                                    if ("Nhân viên".equalsIgnoreCase(CV)) {
                                        //nhanvien
                                        TaiKhoanHr1.setVisible(false);
                                        TaiKhoanHr3.setVisible(false);
                                        TaiKhoanHr2.setVisible(true);
                                        TaiKhoantittle1.setVisible(false);
                                        TaiKhoantittle3.setVisible(false);
                                        hidePage();
                                        hideMenu();
                                        cardMenubarTaiKhoan.setVisible(true);
                                        cardKhachHang.setVisible(true);
                                    } else {
                                        //quan li, admin
                                        TaiKhoanHr2.setVisible(false);
                                        TaiKhoanHr3.setVisible(false);
                                        TaiKhoanHr1.setVisible(true);
                                        hidePage();
                                        hideMenu();
                                        cardMenubarTaiKhoan.setVisible(true);
                                        cardTaiKhoanNhanVien.setVisible(true);
                                    }

                                    chose = 1;
                                    listNVT();
                                    listKhachHang();
                                    listChucVu();
                                    fillTableNhanVien();
                                    fillTableKhachHang();
                                    fillTableChucVu();
                                }
                                break;
                            case 2:
                                //gọi trang sản phẩm
                                if (chose == -1 || chose != 2) {
                                    SanPhamHr1.setVisible(false);
                                    SanPhamHr2.setVisible(false);
                                    SanPhamHr.setVisible(true);
                                    hidePage();
                                    hideMenu();
                                    cardMenubarSanPham.setVisible(true);
                                    cardSanPham.setVisible(true);
                                    chose = 2;
                                    listSPT();
                                    listHang();
                                    listLoai();
                                    fillTableSanPham();
                                    fillTableHang();
                                    fillTableLoai();
                                }
                                break;
                            case 3:
                                //gọi trang hóa đơn
                                if (chose == -1 || chose != 3) {
                                    HoaDonHr1.setVisible(true);
                                    hidePage();
                                    hideMenu();
                                    cardMenubarHoaDon.setVisible(true);
                                    cardHoaDon.setVisible(true);
                                    chose = 3;
                                    listHoaDon();
                                    fillTableHoaDon();

                                }
                                break;
                            case 4:
                                //gọi trang giỏ hàng
                                if (chose == -1 || chose != 4) {
                                    hidePage();
                                    hideMenu();
                                    cartShoping("");
                                    cardMenubarGioHang.setVisible(true);
                                    cardGioHang.setVisible(true);
                                    findItemCart();
                                    chose = 4;
                                }
                                break;
                            case 5:
                                //gọi trang thống kê
                                if (chose == -1 || chose != 5) {
                                    hidePage();
                                    hideMenu();
                                    cardMenubarThongKe.setVisible(true);
                                    if ("Nhân viên".equalsIgnoreCase(CV)) {
                                        ThongKeTittle1.setVisible(false);
                                        ThongKeTittle2.setVisible(true);
                                        ThongKeHr1.setVisible(false);
                                        cardThongKeDoanhThu.setVisible(false);
                                        cardThongKeSanPham.setVisible(true);
                                        cboSort.setSelectedItem("Cao nhất");
                                        ThongKeHr2.setVisible(true);
                                    } else {
                                        ThongKeTittle1.setVisible(true);
                                        ThongKeTittle2.setVisible(true);
                                        ThongKeHr1.setVisible(true);
                                        ThongKeHr2.setVisible(false);
                                        cardThongKeDoanhThu.setVisible(true);
                                        cardThongKeSanPham.setVisible(false);
                                        cboSort.setSelectedItem("Cao nhất");
                                    }
                                    chose = 5;
                                }
                                break;
                            case 6:
                                //gọi dialog cài đặt
                                //new CaiDat(new Home(), true).setVisible(true);
                                if (chose == -1 || chose != 6) {
                                    hidePage();
                                    hideMenu();
                                    cardMenubarCaiDat.setVisible(true);
                                    cardCaiDatGiaoDien.setVisible(true);
                                    CaiDatHr2.setVisible(false);
                                    CaiDatHr1.setVisible(true);
                                    chose = 6;
                                }
                                break;
                            case 7:
                                //gọi trang giới thiệu
                                if (chose == -1 || chose != 7) {
                                    hidePage();
                                    hideMenu();
                                    cardMenubarGioiThieu.setVisible(true);
                                    cardGioiThieuSanPham.setVisible(true);
                                    GioiThieuHr2.setVisible(false);
                                    chose = 7;
                                }
                                break;
                            case 8:
                                //gọi phương thức đăng xuất
                                signOut();
                                break;
                        }
                    }
                }).build();

    }

    int chooserMenuIndex = 1;

    //banner chuyển động
    void banner() throws Exception {
        initBaner();
        JLabel[] arrBaner = {
            banner1, banner2, banner3
        };
        //511, 240
        Thread threadBanner = new Thread() {
            @Override
            public void run() {
                int i = 0;
                while (true) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    switch (i) {
                        case 0:
                            banner1.setVisible(false);
                            banner2.setVisible(true);
                            banner3.setVisible(false);
                            i++;
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            continue;
                        case 1:
                            banner1.setVisible(false);
                            banner2.setVisible(false);
                            banner3.setVisible(true);
                            i++;
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            continue;
                        case 2:
                            banner1.setVisible(true);
                            banner2.setVisible(false);
                            banner3.setVisible(false);
                            i = 0;
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            continue;
                    }
                }
            }

        };
        threadBanner.start();
    }
//dang xuat

    void signOut() {
        boolean chon = MsgBox.confirm(this, "Bạn có chắc chắn đăng xuất không?");
        if (chon) {
            Login login = new Login();
            login.setVisible(true);
            this.dispose();
        }

    }
//Phương thức gọi lại menu

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
                    TaiKhoanHr2.setVisible(false);
                    TaiKhoanHr3.setVisible(false);
                    TaiKhoanHr1.setVisible(true);
                    hidePage();
                    hideMenu();
                    cardMenubarTaiKhoan.setVisible(true);
                    cardTaiKhoanNhanVien.setVisible(true);
                    chose = 1;
                    listNVT();
                    fillTableNhanVien();

                }
                break;
            case 2:
                if (chose == -1 || chose != 2) {
                    SanPhamHr1.setVisible(false);
                    SanPhamHr2.setVisible(false);
                    SanPhamHr.setVisible(true);
                    hidePage();
                    hideMenu();
                    cardMenubarSanPham.setVisible(true);
                    cardSanPham.setVisible(true);
                    chose = 2;
                    listSPT();
                    fillTableSanPham();
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
                    HoaDonHr1.setVisible(true);
                    hidePage();
                    hideMenu();
                    cardMenubarHoaDon.setVisible(true);
                    cardHoaDon.setVisible(true);
                    chose = 4;
                    listHoaDon();
                    fillTableHoaDon();
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
                    listKhachHang();
                    fillTableKhachHang();
                }
                break;
            case 8:
                if (chose == -1 || chose != 8) {
                    ThongKeHr2.setVisible(false);
                    ThongKeHr1.setVisible(true);
                    hidePage();
                    hideMenu();
                    cardMenubarThongKe.setVisible(true);
                    cardThongKeDoanhThu.setVisible(true);
                    chose = 8;
                }
                break;
        }

    }
//Quét camera

    public void initWebcam() {
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0); //0 is default webcam
        webcam.setViewSize(size);
        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        pnQR.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 300));
        executor.execute(this);
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Result result = null;
            BufferedImage image = null;
            if (!webcam.isOpen()) {
                return;
            }
            if (webcam.isOpen()) {
                if ((image = webcam.getImage()) == null) {
                    continue;
                }
            }
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            try {
                result = new MultiFormatReader().decode(bitmap);
            } catch (NotFoundException e) {
            }
            if (result != null) {
                if (txtSdtKH.getText().equalsIgnoreCase("")) {
                    MsgBox.alert(this, "Vui lòng nhập số điện thoai!");
                    txtSdtKH.requestFocus();
                    return;
                } else {
                    SanPhamEntity sp = SanPham.selectById(result.getText());
                    if (sp.getSl() > 0) {
                        insertGH(result.getText(), 1);
                    } else {
                        MsgBox.alert(cardHoaDonSanPham, "Sản phẩm đã hết!");
                        return;
                    }
                }
            }
        } while (true);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }
//Hiển thị tên nhân viên trên cửa sổ chính

    boolean checkChucVu = true; //quan li hoac admin

    public void getTenNhanVien(String tenNV) {
        for (TaiKhoanEntity tk : listTK) {
            if (tenNV.equalsIgnoreCase(tk.getTenDN())) {
                lbltenNV.setText(tk.getTenNV());
                lblChucVu.setText("Chức vụ: " + tk.getTenCV());
                CV = tk.getTenCV();
                if (!"Quản lí".equalsIgnoreCase(tk.getTenCV()) && !"admin".equalsIgnoreCase(tk.getTenCV())) {
                    checkChucVu = false;
                } else if ("Quản lí".equalsIgnoreCase(tk.getTenCV()) || "admin".equalsIgnoreCase(tk.getTenCV())) {
                    ktCV = tk.getTenCV();
                    if ("Admin".equalsIgnoreCase(ktCV)) {
                        cboVaiTro.setEnabled(false);
                    }
                }
            }
        }
    }
//Định dạng các dòng và các cột trong bảng

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
        columnModelSanPham.getColumn(1).setPreferredWidth(200);
        columnModelSanPham.getColumn(2).setPreferredWidth(100);
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

        tblChucVu.setRowHeight(30);
        TableColumnModel columnModelCV = tblChucVu.getColumnModel();
        columnModelCV.getColumn(0).setPreferredWidth(50);
        columnModelCV.getColumn(1).setPreferredWidth(100);
        columnModelCV.getColumn(2).setPreferredWidth(100);
    }
//Phương thức làm mờ nền của phần mềm
    //mở opacity 

    void showOpacity() {
        opacity.setSize(1220, 670);
    }

    //đóng opacity
    void hideOpacity() {
        opacity.setSize(0, 0);
    }

    //mở menu
    void openMenu() {
        if (pnMenu.getWidth() < 300) {
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
    }

    //đóng menu
    void closeMenu() {
        if (pnMenu.getWidth() == 300) {
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
    }

    //ẩn các menu của trang con
    public void hideCardMenubar() {
        cardMenubarGioiThieu.setVisible(false);
        cardMenubarTrangChu.setVisible(false);
        cardMenubarTaiKhoan.setVisible(false);
    }

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
            cardTrangChuTongQuan,
            cardThongKeDoanhThu, cardThongKeSanPham,
            cardGioiThieuSanPham, cardGioiThieuThanhVien,
            cardTaiKhoanNhanVien, cardTaiKhoanChucVu,
            cardSanPham, cardHangSanXuat, cardLoai,
            cardGioHang, cardHoaDon,
            cardKhachHang,
            cardCaiDatGiaoDien, cardCaiDatDuongDan
        };
        for (JPanel pn : this.arrPn) {
            pn.setVisible(false);
        }
    }

    //phương thức ẩn các menu
    public void hideMenu() {
        this.arrPn = new JPanel[]{cardMenubarTrangChu, cardMenubarTaiKhoan,
            cardMenubarGioiThieu, cardMenubarHoaDon, cardMenubarGioHang,
            cardMenubarSanPham, cardMenubarKhachHang, cardMenubarThongKe,
            cardMenubarCaiDat
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
//--------------------------------------------HÃNG---------------------------------------------------------------------------    

//Lấy vị trí của dòng trong bảng
    public String recordHang() {
        List<HangEntity> list = Hang.selectAll();
        return (row + 1) + " trên " + list.size();
    }
//Hiển trị dữ liệu trên bảng và trạng thái ban đầu của các nút

    public void initHang() {
        setLocationRelativeTo(null);
        listHang = Hang.selectAll();
        this.fillTableHang();
        lblMaHangTemp.setVisible(false);
        this.row = -1;
        this.updateStatusHang();
    }
//Hiển thị dữ liệu lên bảng

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
            System.out.println(e);
        }
    }
//Lấy dữ liệu trên database    

    public void setFormHang(HangEntity h) {
        txtMaHang.setText(h.getMaHang());
        txtTenHang.setText(h.getTenHang());
    }
//Đẩy dữ liệu lên database

    public HangEntity getFormHang() {
        HangEntity h = new HangEntity();
        h.setMaHang(txtMaHang.getText());
        h.setTenHang(txtTenHang.getText());
        return h;

    }
//Xóa trắng form

    public void clearFormHang() {
        HangEntity h = new HangEntity();
        this.setFormHang(h);
        lblMaHangTemp.setVisible(false);
        this.row = -1;
        this.updateStatusHang();
        initHang();
        lblrecordHang.setText(recordHang());
        if (darkMode == true) {
            lblMaHang.setForeground(new Color(102, 255, 255));
            lblTenHang.setForeground(new Color(102, 255, 255));
        } else {
            lblTenHang.setForeground(new Color(0, 0, 255));
            lblMaHang.setForeground(new Color(0, 0, 255));
        }
    }
//Trạng thái ban đầu của các nút

    public void updateStatusHang() {
        boolean edit = (this.row >= 0);
        boolean first = (this.row == 0);
        boolean last = (this.row == tblHang.getRowCount() - 1);
        //Trạng thái form
        btnCapNhatHang.setEnabled(edit);
        btnXoaHang.setEnabled(edit);
        //Trạng thái điều hướng
        btnFirstHang.setEnabled(edit && !first);
        btnPrevHang.setEnabled(edit && !first);
        btnNextHang.setEnabled(edit && !last);
        btnLastHang.setEnabled(edit && !last);
    }
//Hàm trung gian nhận dữ liệu từ table rồi đẩy dữ liệu lên setform

    public void editHang() {
        countClick = 0;
        String mahang = (String) tblHang.getValueAt(this.row, 0);
        HangEntity h = Hang.selectById(mahang);
        tblHang.setRowSelectionInterval(this.row, this.row);
        txtMaHang.setEnabled(true);
        this.setFormHang(h);
        this.updateStatusHang();
        lblrecordHang.setText(recordHang());
        lblrecordLoai.setText(recordLoai());
    }

    //Tìm kiếm theo tên hãng
    private void timKiemhang() {
        String keyword = txtTimKiemHang.getText();
        listHang = Hang.selectByKeyword(keyword);
        this.fillTableHang();
//        this.clearFormHang();
        this.row = - 1;
        updateStatusHang();
    }
//Kiểm tra lỗi của hãng

    public boolean checkHanglight() {
        lblTenHang.setForeground(new Color(0, 0, 255));
        lblMaHang.setForeground(new Color(0, 0, 255));
        if (txtMaHang.getText().equals("")) {
            MsgBox.alert(this, " Mã hãng không được để trống!");
            txtMaHang.requestFocus();
            lblMaHang.setForeground(Color.RED);
            return false;
        }
        if (txtTenHang.getText().equals("")) {
            MsgBox.alert(this, "Tên hãng không được để trống!");
            txtTenHang.requestFocus();
            lblTenHang.setForeground(Color.RED);
            return false;
        }
        return true;
    }

    public boolean checkHangdark() {
        lblMaHang.setForeground(new Color(102, 255, 255));
        lblTenHang.setForeground(new Color(102, 255, 255));
        if (txtMaHang.getText().equals("")) {
            MsgBox.alert(this, " Mã hãng không được để trống!");
            txtMaHang.requestFocus();
            lblMaHang.setForeground(Color.RED);
            return false;
        }
        if (txtTenHang.getText().equals("")) {
            MsgBox.alert(this, "Tên hãng không được để trống!");
            txtTenHang.requestFocus();
            lblTenHang.setForeground(Color.RED);
            return false;
        }
        return true;
    }

    //Add dữ liệu vào list tạm
    public void listHang() {
        listHang = Hang.selectAll();
        List<HangEntity> listHangTemp = new ArrayList<>();
        listHangTemp.addAll(listHang);
        listHang.clear();
        listHang.addAll(listHangTemp);
    }
//Thêm dữ liệu

    public void insertHang() {
        List<HangEntity> listh = Hang.selectAll();
        for (HangEntity sp1 : listh) {
            if (txtMaHang.getText().equals(sp1.getMaHang())) {
                int choice = (JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật hãng mã: " + txtMaHang.getText() + "?", "Xác nhận", JOptionPane.YES_NO_OPTION));
                if (choice == JOptionPane.YES_OPTION) {
                    updateHang();
                } else {
                    return;
                }
            }
        }
        HangEntity h = getFormHang();
        try {
            Hang.insert(h);
            listHang();
            this.fillTableHang();
            this.clearFormHang();
            MsgBox.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Thêm mới thất bại!");
            System.out.println(e);
        }

    }
//Cập nhật dữ liệu

    public void updateHang() {
        HangEntity h = getFormHang();
        try {
            Hang.update(h);
            listHang();
            this.fillTableHang();
            this.clearFormHang();
            MsgBox.alert(this, "Cập nhật thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Cập nhật thất bại!");
            System.out.println(e);
        }
    }
//Xóa dữ liệu

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
            System.out.println(e);
        }

    }
//Trở về dòng đầu tiên trên bảng

    public void firstHang() {
        this.row = 0;
        this.editHang();
    }
//Chạy tới dòng tiếp theo trên bảng

    public void nextHang() {
        if (this.row < tblHang.getRowCount() - 1) {
            this.row++;
            this.editHang();
        } else {
            this.firstHang();
        }
    }
//Trở về dòng trước trên bảng

    public void prevHang() {
        if (this.row > 0) {
            this.row--;
            this.editHang();
        } else {
            this.lastHang();
        }
    }
//Đi tới dòng cuối cùng trong bảng

    public void lastHang() {
        this.row = tblHang.getRowCount() - 1;
        this.editHang();
    }
//-------------------------------------------LOẠI-------------------------------------------------------------------------

//Lấy vị trí của dòng trong bảng
    public String recordLoai() {
        List<LoaiHangEntity> list = Loai.selectAll();
        return (row + 1) + " trên " + list.size();
    }
//Hiển trị dữ liệu trên bảng và trạng thái ban đầu của các nút 

    public void initLoai() {
        setLocationRelativeTo(null);
        listLoai = Loai.selectAll();
        this.fillTableLoai();
        lblMaLoaiTemp.setVisible(false);
        this.row = -1;
        this.updateStatusLoai();
    }
//Hiển thị dữ liệu lên bảng

    public void fillTableLoai() {
        DefaultTableModel model = (DefaultTableModel) tblLoaiHang.getModel();
        model.setRowCount(0);
        try {
            for (LoaiHangEntity l : listLoai) {
                Object[] row = {l.getMaLH(), l.getTenLH()};
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
            System.out.println(e);
        }
    }
//Lấy dữ liệu trên database    

    public void setFormLoai(LoaiHangEntity l) {
        txtMaLoai.setText(l.getMaLH());
        txtTenLoai.setText(l.getTenLH());
    }
//Đẩy dữ liệu lên database

    public LoaiHangEntity getFormLoai() {
        LoaiHangEntity l = new LoaiHangEntity();
        l.setMaLH(txtMaLoai.getText());
        l.setTenLH(txtTenLoai.getText());
        return l;
    }
//Xóa trống form

    public void clearFormLoai() {
        LoaiHangEntity l = new LoaiHangEntity();
        this.setFormLoai(l);
        lblMaLoaiTemp.setVisible(false);
        this.row = -1;
        this.updateStatusLoai();
        initLoai();
        lblrecordLoai.setText(recordLoai());
        if (darkMode == true) {
            lblMaLoai.setForeground(new Color(102, 255, 255));
            lblTenLoai.setForeground(new Color(102, 255, 255));
        } else {
            lblMaLoai.setForeground(new Color(0, 0, 255));
            lblTenLoai.setForeground(new Color(0, 0, 255));
        }
    }
//Trạng thái ban đầu của các nút

    public void updateStatusLoai() {
        boolean edit = (this.row >= 0);
        boolean first = (this.row == 0);
        boolean last = (this.row == tblLoaiHang.getRowCount() - 1);
        //Trạng thái form
        btnCapNhatLoai.setEnabled(edit);
        btnXoaLoai.setEnabled(edit);
        //Trạng thái điều hướng
        btnFirstLoai.setEnabled(edit && !first);
        btnPrevLoai.setEnabled(edit && !first);
        btnNextLoai.setEnabled(edit && !last);
        btnLastLoai.setEnabled(edit && !last);
    }
    //Add dữ liệu vào list tạm

    public void listLoai() {
        listLoai = Loai.selectAll();
        List<LoaiHangEntity> listLoaiTemp = new ArrayList<>();
        listLoaiTemp.addAll(listLoai);
        listLoai.clear();
        listLoai.addAll(listLoaiTemp);
    }
//Hàm trung gian nhận dữ liệu từ table rồi đẩy dữ liệu lên setform

    public void editLoai() {
        countClick = 0;
        String maloai = (String) tblLoaiHang.getValueAt(this.row, 0);
        LoaiHangEntity l = Loai.selectById(maloai);
        tblLoaiHang.setRowSelectionInterval(this.row, this.row);
        this.setFormLoai(l);
        this.updateStatusLoai();
        lblrecordLoai.setText(recordLoai());
    }

    //Tìm kiếm theo tên loại
    private void timKiemLoai() {
        String keyword = txtTimKiemLoai.getText();
        listLoai = Loai.selectByKeyword(keyword);
        this.fillTableLoai();
//        this.clearFormLoai();
        this.row = - 1;
        updateStatusLoai();
    }

//Kiểm tra lỗi của loại
    public boolean checkLoailight() {
        lblMaLoai.setForeground(new Color(0, 0, 255));
        lblTenLoai.setForeground(new Color(0, 0, 255));
        if (txtMaLoai.getText().equals("")) {
            MsgBox.alert(this, " Mã loại không được để trống!");
            txtMaLoai.requestFocus();
            lblMaLoai.setForeground(Color.RED);
            return false;
        }
//        else {
//            lblMaLoai.setForeground(new Color(0, 0, 255));
//        }
        if (txtTenLoai.getText().equals("")) {
            MsgBox.alert(this, "Tên loại không được để trống!");
            txtTenLoai.requestFocus();
            lblTenLoai.setForeground(Color.RED);
            return false;
        }
//        else {
//            lblTenLoai.setForeground(new Color(0, 0, 255));
//        }
        return true;
    }

    public boolean checkLoaidark() {
        lblMaLoai.setForeground(new Color(102, 255, 255));
        lblTenLoai.setForeground(new Color(102, 255, 255));
        if (txtMaLoai.getText().equals("")) {
            MsgBox.alert(this, " Mã loại không được để trống!");
            txtMaLoai.requestFocus();
            lblMaLoai.setForeground(Color.RED);
            return false;
        }
//        else {
//            lblMaLoai.setForeground(new Color(102, 255, 255));
//        }
        if (txtTenLoai.getText().equals("")) {
            MsgBox.alert(this, "Tên loại không được để trống!");
            txtTenLoai.requestFocus();
            lblTenLoai.setForeground(Color.RED);
            return false;
        }
//        else {
//            lblTenLoai.setForeground(new Color(102, 255, 255));
//        }
        return true;
    }
//Thêm dữ liệu

    public void insertLoai() {
        List<LoaiHangEntity> listl = Loai.selectAll();
        for (LoaiHangEntity sp1 : listl) {
            if (txtMaLoai.getText().equals(sp1.getMaLH())) {
                int choice = (JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật loại hàng mã: " + txtMaLoai.getText() + "?", "Xác nhận", JOptionPane.YES_NO_OPTION));
                if (choice == JOptionPane.YES_OPTION) {
                    updateLoai();
                } else {
                    return;
                }
            }
        }
        LoaiHangEntity l = getFormLoai();
        try {
            Loai.insert(l);
            listLoai();
            this.fillTableLoai();
            this.clearFormLoai();
            MsgBox.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Thêm mới thất bại!");
            System.out.println(e);
        }
    }

//Cập nhật dữ liệu
    public void updateLoai() {
        LoaiHangEntity l = getFormLoai();
        try {
            Loai.update(l);
            listLoai();
            this.fillTableLoai();
            this.clearFormLoai();
            MsgBox.alert(this, "Cập nhật thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Cập nhật thất bại!");
            System.out.println(e);
        }
    }

//Xóa dữ liệu
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
            System.out.println(e);
        }

    }

//Trở về dòng đầu tiên trên bảng
    public void firstLoai() {
        this.row = 0;
        this.editLoai();
    }

//Chạy tới dòng tiếp theo trên bảng
    public void nextLoai() {
        if (this.row < tblLoaiHang.getRowCount() - 1) {
            this.row++;
            this.editLoai();
        } else {
            this.firstLoai();
        }
    }

//Trở về dòng trước trên bảng
    public void prevLoai() {
        if (this.row > 0) {
            this.row--;
            this.editLoai();
        } else {
            this.lastLoai();
        }
    }

//Đi tới dòng cuối cùng trong bảng
    public void lastLoai() {
        this.row = tblLoaiHang.getRowCount() - 1;
        this.editLoai();
    }
//--------------------------------------------CHỨC VỤ-----------------------------------------------------------------------

//Lấy vị trí của dòng trong bảng
    public String recordChucVu() {
        List<ChucVuEntity> list = chucVu.selectAll();
        return (row + 1) + " trên " + list.size();
    }

//Hiển trị dữ liệu trên bảng và trạng thái ban đầu của các nút 
    public void initChucVu() {
        setLocationRelativeTo(null);
        listCV = chucVu.selectAll();
        this.fillTableChucVu();
        this.row = -1;
        this.updateStatusChucVu();
    }

//Hiển thị dữ liệu lên bảng
    public void fillTableChucVu() {
        DefaultTableModel model = (DefaultTableModel) tblChucVu.getModel();
        model.setRowCount(0);
        try {
            for (ChucVuEntity cv : listCV) {
                Object[] row = {cv.getMaCV(), cv.getTenCV(), cv.getMoTa()};
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
            System.out.println(e);
        }
    }

//Lấy dữ liệu trên database
    public void setFormChucVu(ChucVuEntity cv) {
        txtmaCV.setText(String.valueOf(cv.getMaCV()));
        txttenCV.setText(cv.getTenCV());
        txtMoTaCV.setText(cv.getMoTa());
//        System.out.println(cv.getMoTa());
    }

//Đẩy dữ liệu lên database
    public ChucVuEntity getFormChucVu() {
        String kt = txtmaCV.getText().trim();
        ChucVuEntity cv = new ChucVuEntity();
        cv.setMaCV(maChucVu);

        cv.setTenCV(txttenCV.getText());
        cv.setMoTa(txtMoTaCV.getText());
        return cv;
    }

//Xóa trống form
    public void clearFormChucVu() {
        ChucVuEntity cv = new ChucVuEntity();
        this.setFormChucVu(cv);
        this.row = -1;
        this.updateStatusChucVu();
        txtmaCV.setText("");
        initChucVu();
        lblrecordCV.setText(recordChucVu());
        if (darkMode == true) {
            lblMaChucVu.setForeground(new Color(102, 255, 255));
            lblTenCV.setForeground(new Color(102, 255, 255));
        } else {
            lblMaChucVu.setForeground(new Color(0, 0, 255));
            lblTenCV.setForeground(new Color(0, 0, 255));
        }
    }

//Trạng thái ban đầu của các nút
    public void updateStatusChucVu() {
        boolean edit = (this.row >= 0);
        boolean first = (this.row == 0);
        boolean last = (this.row == tblChucVu.getRowCount() - 1);
        //Trạng thái form
        btnCapNhatCV.setEnabled(edit);
        btnXoaCV.setEnabled(edit);
        //Trạng thái điều hướng
        btnFirstCV.setEnabled(edit && !first);
        btnPrevCV.setEnabled(edit && !first);
        btnNextCV.setEnabled(edit && !last);
        btnLastCV.setEnabled(edit && !last);
    }

//Hàm trung gian nhận dữ liệu từ table rồi đẩy dữ liệu lên setform
    public void editChucVu() {
        countClick = 0;
        String maCV = String.valueOf(tblChucVu.getValueAt(this.row, 0));
        ChucVuEntity cv = chucVu.selectById(maCV);
        tblChucVu.setRowSelectionInterval(this.row, this.row);
        this.setFormChucVu(cv);
        this.updateStatusChucVu();
        lblrecordCV.setText(recordChucVu());
    }

//Kiểm tra lỗi của loại
    public boolean checkChucVulight() {
        lblMaChucVu.setForeground(new Color(0, 0, 255));
        lblTenCV.setForeground(new Color(0, 0, 255));
        if (txtmaCV.getText().equals("")) {
            MsgBox.alert(this, " Mã chức vụ không được để trống!");
            txtmaCV.requestFocus();
            lblMaChucVu.setForeground(Color.RED);
            return false;
        } else {
            try {
                maChucVu = Integer.valueOf(txtmaCV.getText().trim());
            } catch (Exception ex) {
                MsgBox.alert(this, "Vui lòng không nhập mã chức vụ là ký tự hoặc chữ");
                txtmaCV.requestFocus();
                lblMaChucVu.setForeground(Color.RED);
                return false;
            }
            //lblMaChucVu.setForeground(new Color(0, 0, 255));
        }

        if (txttenCV.getText().equals("")) {
            MsgBox.alert(this, "Tên chức vụ không được để trống!");
            txttenCV.requestFocus();
            lblTenCV.setForeground(Color.RED);
            return false;
        }
//        else {
//            lblTenCV.setForeground(new Color(0, 0, 255));
//        }
        return true;
    }

    public boolean checkChucVudark() {
        lblMaChucVu.setForeground(new Color(102, 255, 255));
        lblTenCV.setForeground(new Color(102, 255, 255));
        if (txtmaCV.getText().equals("")) {
            MsgBox.alert(this, " Mã chức vụ không được để trống!");
            txtmaCV.requestFocus();
            lblMaChucVu.setForeground(Color.RED);
            return false;
        } else {
            try {
                maChucVu = Integer.valueOf(txtmaCV.getText().trim());
            } catch (Exception ex) {
                MsgBox.alert(this, "Vui lòng không nhập mã chức vụ là ký tự hoặc chữ");
                txtmaCV.requestFocus();
                lblMaChucVu.setForeground(Color.RED);
                return false;
            }
//            lblMaChucVu.setForeground(new Color(102, 255, 255));
        }

        if (txttenCV.getText().equals("")) {
            MsgBox.alert(this, "Tên chức vụ không được để trống!");
            txttenCV.requestFocus();
            lblTenCV.setForeground(Color.RED);
            return false;
        }
//        else {
//            lblTenCV.setForeground(new Color(102, 255, 255));
//        }
        return true;
    }
//Thêm dữ liệu

    public void insertChucVu() {
        if (txttenCV.getText().toLowerCase().contains("Admin".toLowerCase())) {
            MsgBox.alert(this, "Bạn không được phép thêm Admin!");
            return;
        } else if ("Quản lí".equalsIgnoreCase(CV) && txttenCV.getText().toLowerCase().contains("Quản lí".toLowerCase())) {
            MsgBox.alert(this, "Bạn không được phép thêm Quản lí!");
            return;
        } else {
            List<ChucVuEntity> listcv = chucVu.selectAll();
            for (ChucVuEntity cv : listcv) {
                if (Integer.valueOf(txtmaCV.getText().trim()) == cv.getMaCV()) {
                    int choice = (JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật chức vụ mã: " + txtmaCV.getText() + "?", "Xác nhận", JOptionPane.YES_NO_OPTION));
                    if (choice == JOptionPane.YES_OPTION) {
                        updateChucVu();
                        return;
                    }
                }
            }
            ChucVuEntity cv = getFormChucVu();
            try {
                chucVu.insert(cv);
                listChucVu();
                this.fillTableChucVu();
                this.clearFormChucVu();
                MsgBox.alert(this, "Thêm mới thành công!");
            } catch (Exception e) {
                MsgBox.alert(this, "Thêm mới thất bại!");
                System.out.println(e);
            }
        }
    }

//Cập nhật dữ liệu
    public void updateChucVu() {
        if (("Quản lí".equalsIgnoreCase(CV)) && ("Admin".equalsIgnoreCase(txttenCV.getText()) || "Quản lí".equalsIgnoreCase(txttenCV.getText()))) {
            MsgBox.alert(this, "Bạn không có quyền cập nhật chức vụ " + txttenCV.getText());
            return;
        } else if (Integer.valueOf(txtmaCV.getText()) == 0) {
            MsgBox.alert(this, "Bạn không có quyền cập nhật chức vụ Admin");
            return;
        } else {
            ChucVuEntity cv = getFormChucVu();
            try {
                chucVu.update(cv);
                listChucVu();
                this.fillTableChucVu();
                this.clearFormChucVu();
                MsgBox.alert(this, "Cập nhật thành công!");
            } catch (Exception e) {
                MsgBox.alert(this, "Cập nhật thất bại!");
                System.out.println(e);
            }
        }
    }

//Xóa dữ liệu
    public void deleteChucVu() {
        String maCV = txtmaCV.getText();
        if (maCV.equalsIgnoreCase("admin") || maCV.equalsIgnoreCase("Quản lí") || maCV.equalsIgnoreCase("Nhân viên")) {
            MsgBox.alert(this, "Bạn Không được phép xóa " + maCV);
        } else {
            try {
                chucVu.delete(maCV);
                listChucVu();
                this.fillTableChucVu();
                this.clearFormChucVu();
                MsgBox.alert(this, "Xóa thành công!");
            } catch (Exception e) {
                MsgBox.alert(this, "Xóa thất bại!");
                System.out.println(e);
            }
        }
    }

//Trở về dòng đầu tiên trên bảng
    public void firsChucVu() {
        this.row = 0;
        this.editChucVu();
    }

//Chạy tới dòng tiếp theo trên bảng
    public void nextChucVu() {
        if (this.row < tblChucVu.getRowCount() - 1) {
            this.row++;
            this.editChucVu();
        } else {
            this.firsChucVu();
        }
    }

//Trở về dòng trước trên bảng
    public void prevChucVu() {
        if (this.row > 0) {
            this.row--;
            this.editChucVu();
        } else {
            this.lastChucVu();
        }
    }

//Đi tới dòng cuối cùng trong bảng 
    public void lastChucVu() {
        this.row = tblChucVu.getRowCount() - 1;
        this.editChucVu();
    }

//Add dữ liệu vào list tạm
    public void listChucVu() {
        listCV = chucVu.selectAll();
        List<ChucVuEntity> listChucVuTemp = new ArrayList<>();
        listChucVuTemp.addAll(listCV);
        listCV.clear();
        listCV.addAll(listChucVuTemp);
    }

//----------------------------------------------SẢN PHẨM------------------------------------------------------------------------
//Lấy vị trí của dòng trong bảng
    public String recordSanPham() {
        List<SanPhamEntity> list = SanPham.selectAll();
        return (row + 1) + " trên " + list.size();
    }

//Hiển trị dữ liệu trên bảng và trạng thái ban đầu của các nút 
    public void initSanPham() {
        setLocationRelativeTo(null);
        txtNgayNhapSP.setText(String.valueOf(formater.format(day())));
        lblNgayNhaptemp.setVisible(false);
        lblMaSPTemp.setVisible(false);
        btnazsp.setEnabled(false);
        btnzasp.setEnabled(false);
        listSPT();
        this.fillTableSanPham();
        this.row = -1;
        this.updateStatusSanPham();

    }

    //Add dữ liệu vào list tạm
    public void listSPT() {
        list = SanPham.selectAll();
        List<SanPhamEntity> listSanPhamTemp = new ArrayList<>();
        listSanPhamTemp.addAll(list);
        list.clear();
        list.addAll(listSanPhamTemp);
    }

//Hiển thị dữ liệu lên bảng
    public void fillTableSanPham() {
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        try {
            for (SanPhamEntity sp : list) {
                LoaiHangEntity lh = Loai.selectById(sp.getMaLH());
                HangEntity hang = Hang.selectById(sp.getMaHang());
                String daytemp = formater.format(sp.getNgayNhap());
                Object[] row = {sp.getMaSP(), sp.getTenSP(), lh.getTenLH(), hang.getTenHang(), sp.getSl(), sp.getGiaNhap(), sp.getGiaBan(), daytemp, sp.getMoTa()};
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
            System.out.println(e);
        }
    }

//Tìm sản phẩm theo tên
    void fillTableSanPham(String tenSP) {
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        String kt = (String) cboTimKiemSP.getSelectedItem();
        if (!kt.equalsIgnoreCase(" ") && !kt.equals(null)) {
            try {
                for (SanPhamEntity sp : list) {
                    if (kt.equalsIgnoreCase("Tên sản phẩm")) {
                        if (sp.getTenSP().toLowerCase().contains(tenSP.toLowerCase())) {
                            LoaiHangEntity lh = Loai.selectById(sp.getMaLH());
                            HangEntity hang = Hang.selectById(sp.getMaHang());
                            String daytemp = formater.format(sp.getNgayNhap());
                            Object[] row = {sp.getMaSP(), sp.getTenSP(), lh.getTenLH(), hang.getTenHang(), sp.getSl(), sp.getGiaNhap(), sp.getGiaBan(), daytemp, sp.getMoTa()};
                            model.addRow(row);
                        }
                    } else if (kt.equalsIgnoreCase("Mã sản phẩm")) {
                        if (sp.getMaSP().toLowerCase().contains(tenSP.toLowerCase())) {
                            LoaiHangEntity lh = Loai.selectById(sp.getMaLH());
                            HangEntity hang = Hang.selectById(sp.getMaHang());
                            String daytemp = formater.format(sp.getNgayNhap());
                            Object[] row = {sp.getMaSP(), sp.getTenSP(), lh.getTenLH(), hang.getTenHang(), sp.getSl(), sp.getGiaNhap(), sp.getGiaBan(), daytemp, sp.getMoTa()};
                            model.addRow(row);
                        }
                    }
                }
            } catch (Exception e) {
                MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
                System.out.println(e);
            }
        } else {
            MsgBox.alert(this, "Vui lòng chọn tiêu chí tìm kiếm!");
            return;
        }
    }

//Lấy dữ liệu trên database
    public void setFormSanPham(SanPhamEntity sp) {
        txtMaSP.setText(sp.getMaSP());
        txtTenSP.setText(sp.getTenSP());

        LoaiHangEntity listlh = Loai.selectById(sp.getMaLH());
        HangEntity listh = Hang.selectById(sp.getMaHang());
        if (listlh == null) {
            cboMaLoai.setSelectedIndex(0);
            cboMaHang.setSelectedIndex(0);
        } else {
            cboMaLoai.setSelectedItem(listlh.getTenLH());
            cboMaHang.setSelectedItem(listh.getTenHang());
        }
        txtSoLuongSP.setText(String.valueOf(sp.getSl()));
        txtGiaNhapSP.setText(String.valueOf(sp.getGiaNhap()));
        txtGiaBanSP.setText(String.valueOf(sp.getGiaBan()));
        if (sp.getMaSP() != null) {
            String daytemp = formater.format(sp.getNgayNhap());
            txtNgayNhapSP.setText(daytemp);
        } else {
            txtNgayNhapSP.setText(formater.format(dayNow));
        }
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

//Đẩy dữ liệu lên database
    public SanPhamEntity getFormSanPham() {
        String tenloai = (String) cboMaLoai.getSelectedItem();
        String tenhang = (String) cboMaHang.getSelectedItem();
        SanPhamEntity sp = new SanPhamEntity();
        sp.setMaSP(txtMaSP.getText().toUpperCase());
        sp.setTenSP(txtTenSP.getText());
        List<LoaiHangEntity> listlh = Loai.selectAll();
        for (LoaiHangEntity lh : listlh) {
            if (tenloai.equals(lh.getTenLH())) {
                sp.setMaLH(lh.getMaLH());
            }
        }
        List<HangEntity> listh = Hang.selectAll();
        for (HangEntity h : listh) {
            if (tenhang.equals(h.getTenHang())) {
                sp.setMaHang(h.getMaHang());
            }
        }
        sp.setSl(soLuongNhap);
        sp.setGiaNhap(Float.parseFloat(txtGiaNhapSP.getText()));
        sp.setGiaBan(Float.parseFloat(txtGiaBanSP.getText()));
        sp.setNgayNhap(XDate.toDate(txtNgayNhapSP.getText(), "dd-MM-yyyy"));
        sp.setHinh(txtMaSP.getText());
        sp.setMoTa(txtMoTaSP.getText());
        return sp;
    }

//Xóa trống form
    public void clearFormSanPham() {
        SanPhamEntity sp = new SanPhamEntity();
        this.setFormSanPham(sp);
        lblNgayNhaptemp.setVisible(false);
        lblMaSPTemp.setVisible(false);
        txtGiaBanSP.setText(" ");
        txtNgayNhapSP.setText(String.valueOf(formater.format(day())));
        txtGiaNhapSP.setText(" ");
        txtSoLuongSP.setText(" ");
        //setcolorlableSP();
        this.row = -1;
        this.updateStatusSanPham();
        initSanPham();
        lblrecordSP.setText(recordSanPham());
        if (darkMode == true) {
            lblMaSP.setForeground(new Color(102, 255, 255));
            lblTenSP.setForeground(new Color(102, 255, 255));
            lblGiaNhapSP.setForeground(new Color(102, 255, 255));
            lblSoLuongSP.setForeground(new Color(102, 255, 255));
            lblGiaBanSP.setForeground(new Color(102, 255, 255));
        } else {
            lblMaSP.setForeground(new Color(0, 0, 255));
            lblTenSP.setForeground(new Color(0, 0, 255));
            lblGiaNhapSP.setForeground(new Color(0, 0, 255));
            lblSoLuongSP.setForeground(new Color(0, 0, 255));
            lblGiaBanSP.setForeground(new Color(0, 0, 255));
        }
    }
//Set màu cho label

    public void setcolorlableSP() {
        lblMaSP.setForeground(new Color(0, 0, 255));
        lblTenSP.setForeground(new Color(0, 0, 255));
        lblGiaBanSP.setForeground(new Color(0, 0, 255));
        lblGiaNhapSP.setForeground(new Color(0, 0, 255));
        lblSoLuongSP.setForeground(new Color(0, 0, 255));
    }
//Trạng thái của các nút

    public void updateStatusSanPham() {
        boolean edit = (this.row >= 0);
        boolean first = (this.row == 0);
        boolean last = (this.row == tblSanPham.getRowCount() - 1);
        //Trạng thái form
        btnCapNhatSP.setEnabled(edit);
        btnXoaSP.setEnabled(edit);
        //Trạng thái điều hướng
        btnFirstSP.setEnabled(edit && !first);
        btnPrevSP.setEnabled(edit && !first);
        btnNextSP.setEnabled(edit && !last);
        btnLastSP.setEnabled(edit && !last);
    }

//Hàm trung gian nhận dữ liệu từ table rồi đẩy dữ liệu lên setform
    public void editSanPham() {
        countClick = 0;
        String masp = (String) tblSanPham.getValueAt(this.row, 0);
        SanPhamEntity sp = SanPham.selectById(masp);
        tblSanPham.setRowSelectionInterval(this.row, this.row);
        this.setFormSanPham(sp);
        this.updateStatusSanPham();
        lblrecordSP.setText(recordSanPham());
    }

//Thêm dữ liệu
    public void insertSanPham() {
        List<SanPhamEntity> listsp = SanPham.selectAll();
        Qr qr = new Qr();
        for (SanPhamEntity sp1 : listsp) {
            if (txtMaSP.getText().equals(sp1.getMaSP())) {
                int choice = (JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật sản phẩm mã: " + txtMaSP.getText() + "?", "Xác nhận", JOptionPane.YES_NO_OPTION));
                if (choice == JOptionPane.YES_OPTION) {
                    updateSanPham();
                } else {
                    return;
                }
            }
        }
        SanPhamEntity sp = getFormSanPham();
        try {
            SanPham.insert(sp);
            listSPT();
            this.fillTableSanPham();
            this.clearFormSanPham();
            qr.createQRProduct(sp.getMaSP());
            MsgBox.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Thêm mới thất bại!");
            System.out.println(e);
        }
    }

//Cập nhật dữ liệu
    public void updateSanPham() {
        SanPhamEntity sp = getFormSanPham();
        Qr qr = new Qr();
        try {
            SanPham.update(sp);
            listSPT();
            this.fillTableSanPham();
            this.clearFormSanPham();
            qr.createQRProduct(sp.getMaSP());
            MsgBox.alert(this, "Cập nhật thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Cập nhật thất bại!");
            System.out.println(e);
        }
    }

//Xóa dữ liệu
    public void deleteSanPham() {
        String masp = txtMaSP.getText();
        Qr qr = new Qr();
        FileExtensison fe = new FileExtensison();
        try {
            SanPham.delete(masp);
            listSPT();
            this.fillTableSanPham();
            this.clearFormSanPham();
            qr.deleteQROProduct(fe.getPath() + "\\" + masp + ".png");
            qr.deleteQROProduct("src\\com\\images\\" + masp + ".png");
            MsgBox.alert(this, "Xóa thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Xóa thất bại!");
            System.out.println(e);
        }
    }

//Chạy tới dòng tiếp theo trên bảng
    public void firstSanPham() {
        this.row = 0;
        this.editSanPham();
    }

//Chạy tới dòng tiếp theo trên bảng
    public void nextSanPham() {
        if (this.row < tblSanPham.getRowCount() - 1) {
            this.row++;
            this.editSanPham();

        } else {
            this.firstSanPham();
        }
    }

//Lùi về 1 dòng trên bảng
    public void prevSanPham() {
        if (this.row > 0) {
            this.row--;
            this.editSanPham();
        } else {
            this.lastSanPham();
        }
    }

//Đi tới dòng cuối cùng trong bảng 
    public void lastSanPham() {
        this.row = tblSanPham.getRowCount() - 1;
        this.editSanPham();
    }

//Hiển thị tên Loại trên combobox
    public void fillComboxLoai() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboMaLoai.getModel();
        model.removeAllElements();
        List<LoaiHangEntity> list = Loai.selectAll();
        for (LoaiHangEntity l : list) {
            model.addElement(String.valueOf(l.getTenLH()));
        }
    }

//Hiển thị tên Hãng trên combobox
    public void fillComboxHang() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboMaHang.getModel();
        model.removeAllElements();

        for (HangEntity h : listHang) {
            model.addElement(String.valueOf(h.getTenHang()));
        }
    }

//Chọn ảnh
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
        JFileChooser chooser = new JFileChooser(); //System.out.println(nameImage);
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
        in.close();
        out.close();
    }

//Kiểm tra lỗi của sản phẩm
    public boolean checkSPlight() {
        lblMaSP.setForeground(new Color(0, 0, 255));
        lblTenSP.setForeground(new Color(0, 0, 255));
        lblGiaNhapSP.setForeground(new Color(0, 0, 255));
        lblSoLuongSP.setForeground(new Color(0, 0, 255));
        lblGiaBanSP.setForeground(new Color(0, 0, 255));
        if (txtMaSP.getText().equals("")) {
            MsgBox.alert(this, "Mã sản phẩm không được để trống!");
            txtMaSP.requestFocus();
            lblMaSP.setForeground(Color.RED);
            return false;
        } else if (!txtMaSP.getText().equals("")) {
            Matcher matcher = Pattern.compile(TypeProduct_REGEX).matcher(txtMaSP.getText().toUpperCase());
            if (matcher.matches() == false) {
                MsgBox.alert(this, "Mã sản phẩm không hợp lệ (VD: SP0001)!");
                txtMaSP.requestFocus();
                lblMaSP.setForeground(Color.RED);
                return false;
            }
        }
        if (txtTenSP.getText().equals("")) {
            MsgBox.alert(this, "Tên sản phẩm không được để trống!");
            txtTenSP.requestFocus();
            lblTenSP.setForeground(Color.RED);
            return false;
        }
        if (txtGiaNhapSP.getText().equals("")) {
            MsgBox.alert(this, "Giá nhập không được để trống!");
            txtGiaNhapSP.requestFocus();
            lblGiaNhapSP.setForeground(Color.RED);
            return false;
        } else {
            try {
                double gianhap = Double.valueOf(txtGiaNhapSP.getText());
                if (gianhap <= 0) {
                    MsgBox.alert(this, "Giá nhập phải lớn hơn 0");
                    txtGiaNhapSP.requestFocus();
                    lblGiaNhapSP.setForeground(Color.RED);
                    return false;
                }
            } catch (Exception ex) {
                MsgBox.alert(this, "Vui lòng không nhập giá nhập là ký tự hoặc chữ");
                txtGiaNhapSP.requestFocus();
                lblGiaNhapSP.setForeground(Color.RED);
                return false;
            }
        }
        if (txtSoLuongSP.getText().equals("")) {
            MsgBox.alert(this, "Vui lòng nhập số lượng!");
            txtSoLuongSP.requestFocus();
            lblSoLuongSP.setForeground(Color.RED);
            return false;
        } else {
            try {

                if (Integer.valueOf(txtSoLuongSP.getText().trim()) <= 0) {
                    MsgBox.alert(this, "Vui lòng nhập số lượng lớn hơn 0!");
                    txtSoLuongSP.setText("");
                    txtSoLuongSP.requestFocus();
                    lblSoLuongSP.setForeground(Color.RED);
                    return false;
                } else {

                    soLuongNhap = Integer.valueOf(txtSoLuongSP.getText().trim());
                }
            } catch (Exception ex) {
                MsgBox.alert(this, "Vui lòng không nhập số lượng nhập là ký tự hoặc chữ!");
                txtSoLuongSP.setText("");
                txtSoLuongSP.requestFocus();
                lblSoLuongSP.setForeground(Color.RED);
                return false;
            }

        }
        if (txtGiaBanSP.getText().equals("")) {
            MsgBox.alert(this, "Giá bán không được để trống!");
            txtGiaBanSP.requestFocus();
            lblGiaBanSP.setForeground(Color.RED);
            return false;
        } else {
            try {
                double giaban = Double.valueOf(txtGiaBanSP.getText());
                if (giaban <= 0) {
                    MsgBox.alert(this, "Giá bán phải lớn hơn 0");
                    lblGiaBanSP.setForeground(Color.RED);
                    txtGiaBanSP.requestFocus();
                    return false;
                }
            } catch (Exception ex) {
                MsgBox.alert(this, "Vui lòng không nhập giá bán là ký tự hoặc chữ");
                lblGiaBanSP.setForeground(Color.RED);
                txtGiaBanSP.requestFocus();
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

    public boolean checkSPdark() {
        lblMaSP.setForeground(new Color(102, 255, 255));
        lblTenSP.setForeground(new Color(102, 255, 255));
        lblGiaNhapSP.setForeground(new Color(102, 255, 255));
        lblSoLuongSP.setForeground(new Color(102, 255, 255));
        lblGiaBanSP.setForeground(new Color(102, 255, 255));
        if (txtMaSP.getText().equals("")) {
            MsgBox.alert(this, "Mã sản phẩm không được để trống!");
            txtMaSP.requestFocus();
            lblMaSP.setForeground(Color.RED);
            return false;
        } else if (!txtMaSP.getText().equals("")) {
            Matcher matcher = Pattern.compile(TypeProduct_REGEX).matcher(txtMaSP.getText().toUpperCase());
            if (matcher.matches() == false) {
                MsgBox.alert(this, "Mã sản phẩm không hợp lệ (VD: SP0001)!");
                txtMaSP.requestFocus();
                lblMaSP.setForeground(Color.RED);
                return false;
            }
        }
        if (txtTenSP.getText().equals("")) {
            MsgBox.alert(this, "Tên sản phẩm không được để trống!");
            txtTenSP.requestFocus();
            lblTenSP.setForeground(Color.RED);
            return false;
        }
        if (txtGiaNhapSP.getText().equals("")) {
            MsgBox.alert(this, "Giá nhập không được để trống!");
            txtGiaNhapSP.requestFocus();
            lblGiaNhapSP.setForeground(Color.RED);
            return false;
        } else {
            try {
                double gianhap = Double.valueOf(txtGiaNhapSP.getText());
                if (gianhap <= 0) {
                    MsgBox.alert(this, "Giá nhập phải lớn hơn 0");
                    txtGiaNhapSP.requestFocus();
                    lblGiaNhapSP.setForeground(Color.RED);
                    return false;
                }
            } catch (Exception ex) {
                MsgBox.alert(this, "Vui lòng không nhập giá nhập là ký tự hoặc chữ");
                txtGiaNhapSP.requestFocus();
                lblGiaNhapSP.setForeground(Color.RED);
                return false;
            }
        }
        if (txtSoLuongSP.getText().equals("")) {
            MsgBox.alert(this, "Vui lòng nhập số lượng!");
            txtSoLuongSP.requestFocus();
            lblSoLuongSP.setForeground(Color.RED);
            return false;
        } else {
            try {

                if (Integer.valueOf(txtSoLuongSP.getText().trim()) <= 0) {
                    MsgBox.alert(this, "Vui lòng nhập số lượng lớn hơn 0!");
                    txtSoLuongSP.setText("");
                    txtSoLuongSP.requestFocus();
                    lblSoLuongSP.setForeground(Color.RED);
                    return false;
                } else {

                    soLuongNhap = Integer.valueOf(txtSoLuongSP.getText().trim());
                }
            } catch (Exception ex) {
                MsgBox.alert(this, "Vui lòng không nhập số lượng nhập là ký tự hoặc chữ!");
                txtSoLuongSP.setText("");
                txtSoLuongSP.requestFocus();
                lblSoLuongSP.setForeground(Color.RED);
                return false;
            }
        }
        if (txtGiaBanSP.getText().equals("")) {
            MsgBox.alert(this, "Giá bán không được để trống!");
            txtGiaBanSP.requestFocus();
            lblGiaBanSP.setForeground(Color.RED);
            return false;
        } else {
            try {
                double giaban = Double.valueOf(txtGiaBanSP.getText());
                if (giaban <= 0) {
                    MsgBox.alert(this, "Giá bán phải lớn hơn 0");
                    lblGiaBanSP.setForeground(Color.RED);
                    txtGiaBanSP.requestFocus();
                    return false;
                }
            } catch (Exception ex) {
                MsgBox.alert(this, "Vui lòng không nhập giá bán là ký tự hoặc chữ");
                lblGiaBanSP.setForeground(Color.RED);
                txtGiaBanSP.requestFocus();
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

    //Gửi thông tin sản phẩm cho khách hàng qua mail
    public void exportInforProdct() {
        int index = tblSanPham.getSelectedRow();
        if (index > -1) {
            SendInforProduct send = new SendInforProduct();
            int messageType = JOptionPane.QUESTION_MESSAGE;
            String[] option = {"In thông tin", "Gửi thông tin qua email", "Thoát"};
            int code = JOptionPane.showOptionDialog(this, "Bạn muốn thao tác gì?", "In dữ liệu thành văn bản", 0, messageType, null, option, "Save");
            if (code == 0) {
                try {
                    if (!send.saveFile(index)) {
                        MsgBox.alert(this, "Tạm không thể xuất file\nVui lòng kiểm tra lại thao tác");
                    } else {
                        MsgBox.alert(this, "Xuất file thành công!");
                    }

                } catch (IOException ex) {
                    MsgBox.alert(this, "Thao tác không thành công\nVui lòng thử lại sau!");
                }
            } else if (code == 1) {
                String email = JOptionPane.showInputDialog("Nhập vào email: ");
                Validate validate = new Validate();
                if (!validate.checkMail(email)) {
                    JOptionPane.showMessageDialog(this, "Hệ thống không thể duyệt email này\nHãy nhập lại một email khác!");
                    return;
                }
                try {

                    if (!send.sendInforPCEmail(email, index)) {
                        MsgBox.alert(this, "Tạm không thể email\nVui lòng kiểm tra lại thao tác");
                    } else {
                        MsgBox.alert(this, "Gửi nội dung thành công!");
                    }

                } catch (IOException ex) {
                    MsgBox.alert(this, "Thao tác không thành công\nVui lòng thử lại sau!");
                }
            } else {
                return;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm!");
        }
    }

//---------------------------------------------------KHÁCH HÀNG----------------------------------------------------------------
//Kiểm tra lỗi khách hàng
    public boolean checkKHlight() {
        lblMaKH.setForeground(new Color(0, 0, 255));
        lblHovaTenKH.setForeground(new Color(0, 0, 255));
        lblSDTKH.setForeground(new Color(0, 0, 255));
        lblDiaChiKH.setForeground(new Color(0, 0, 255));
        if (txtmaKH.getText().equals("")) {
            MsgBox.alert(this, "Mã khách hàng không được để trống!");
            txtmaKH.requestFocus();
            lblMaKH.setForeground(Color.RED);
            return false;
        }
//        else {
//            lblMaKH.setForeground(new Color(0, 0, 255));
//        }
        if (txthoTen.getText().equals("")) {
            MsgBox.alert(this, "Tên khách hàng không được để trống!");
            txthoTen.requestFocus();
            lblHovaTenKH.setForeground(Color.RED);
            return false;
        } else {
            Standardization std = new Standardization();
            //String validate = tkDAO.validate(txtHoTenNV.getText());
            if (!std.checkName(txthoTen.getText())) {
                MsgBox.alert(this, "Tên khách hàng không hợp lệ vui lòng không chứa kí tự đặt biệt!");
                lblHovaTenKH.setForeground(Color.red);
                return false;
            }
//            else {
//                lblHovaTenKH.setForeground(new Color(0, 0, 255));
//            }
        }
        if (txtSDT.getText().equals("")) {
            MsgBox.alert(this, "Số điện thoại không được để trống!");
            txtSDT.requestFocus();
            lblSDTKH.setForeground(Color.RED);
            return false;
        } else {
            Matcher matcher = Pattern.compile(SDT).matcher(txtSDT.getText());
            if (matcher.matches() == false) {
                MsgBox.alert(this, "Số điện thoại không đúng dịnh dạng!");
                txtSDT.requestFocus();
                lblSDTKH.setForeground(Color.RED);
                return false;
            }
//            else {
//                lblSDTKH.setForeground(new Color(0, 0, 255));
//            }
        }
        if (txtdiaChi.getText().equals("")) {
            MsgBox.alert(this, "Địa chỉ không được để trống!");
            txtdiaChi.requestFocus();
            lblDiaChiKH.setForeground(Color.RED);
            return false;
        }
//        else {
//            lblDiaChiKH.setForeground(new Color(0, 0, 255));
//        }

        return true;
    }

    public boolean checkKHdark() {
        lblHovaTenKH.setForeground(new Color(102, 255, 255));
        lblMaKH.setForeground(new Color(102, 255, 255));
        lblSDTKH.setForeground(new Color(102, 255, 255));
        lblDiaChiKH.setForeground(new Color(102, 255, 255));
        if (txtmaKH.getText().equals("")) {
            MsgBox.alert(this, "Mã khách hàng không được để trống!");
            txtmaKH.requestFocus();
            lblMaKH.setForeground(Color.RED);
            return false;
        } //else {
//            lblMaKH.setForeground(new Color(102, 255, 255));
//        }
        if (txthoTen.getText().equals("")) {
            MsgBox.alert(this, "Tên khách hàng không được để trống!");
            txthoTen.requestFocus();
            lblHovaTenKH.setForeground(Color.RED);
            return false;
        } else {
            Standardization std = new Standardization();
            //String validate = tkDAO.validate(txtHoTenNV.getText());
            if (!std.checkName(txthoTen.getText())) {
                MsgBox.alert(this, "Tên khách hàng không hợp lệ vui lòng không chứa kí tự đặt biệt!");
                lblHovaTenKH.setForeground(Color.red);
                return false;
            }
//            else {
//                lblHovaTenKH.setForeground(new Color(102, 255, 255));
//            }
        }
        if (txtSDT.getText().equals("")) {
            MsgBox.alert(this, "Số điện thoại không được để trống!");
            txtSDT.requestFocus();
            lblSDTKH.setForeground(Color.RED);
            return false;
        } else {
            Matcher matcher = Pattern.compile(SDT).matcher(txtSDT.getText());
            if (matcher.matches() == false) {
                MsgBox.alert(this, "Số điện thoại không đúng dịnh dạng!");
                txtSDT.requestFocus();
                lblSDTKH.setForeground(Color.RED);
                return false;
            }
//            else {
//                lblSDTKH.setForeground(new Color(102, 255, 255));
//            }
        }
        if (txtdiaChi.getText().equals("")) {
            MsgBox.alert(this, "Địa chỉ không được để trống!");
            txtdiaChi.requestFocus();
            lblDiaChiKH.setForeground(Color.RED);
            return false;
        }
//        else {
//            lblDiaChiKH.setForeground(new Color(102, 255, 255));
//        }

        return true;
    }

    //Add dữ liệu vào list tạm
    public void listKhachHang() {
        listKhachHang = KhachHang.selectAll();
        List<KhachHangEntity> listKHTemp = new ArrayList<>();
        listKHTemp.addAll(listKhachHang);
        listKhachHang.clear();
        listKhachHang.addAll(listKHTemp);
    }

//Hiển trị dữ liệu trên bảng và trạng thái ban đầu của các nút 
    public void initKhachHang() {
        setLocationRelativeTo(null);
        btnaz.setEnabled(false);
        btnza.setEnabled(false);
        listKhachHang();
        this.fillTableKhachHang();
        this.row = -1;
        this.updateStatusKhachHang();
    }

    //Tìm kiếm theo tên khách hàng
    private void timKiemKH() {
        String loai = (String) cbotimKiemKH.getSelectedItem();
        String keyword = txtTimKiemKH.getText();
        if (!loai.equalsIgnoreCase(" ") && !loai.equalsIgnoreCase(null)) {
            if (loai.equalsIgnoreCase("Tên khách hàng")) {
                listKhachHang = KhachHang.selectByKeyword(keyword);

            } else {
                listKhachHang = KhachHang.selectByDienThoai(keyword);
            }
            this.fillTableKhachHang();
//            this.clearFormKhachHang();
            this.row = - 1;
            updateStatusKhachHang();
        } else {
            MsgBox.alert(this, "Vui lòng chọn tiêu chí tìm kiếm!");
            return;
        }
    }

    private void timKiemSP() {
        String loai = (String) cboTimKiemSP.getSelectedItem();
        String keyword = txtTimKiemSP.getText();
        if (!loai.equalsIgnoreCase("")) {
            if (loai.equalsIgnoreCase("Tên sản phẩm")) {
                list = SanPham.selectByMaSP(keyword);
            } else {
                list = SanPham.selectByMaSP(keyword);
            }
            this.fillTableSanPham();
            this.clearFormSanPham();
            this.row = - 1;
            updateStatusSanPham();
        } else {
            MsgBox.alert(this, "Vui lòng chọn tiêu chí tìm kiếm!");
            return;
        }
    }

//Lấy vị trí của dòng trong bảng
    public String recordKhachHang() {
        List<KhachHangEntity> list = KhachHang.selectAll();
        return (row + 1) + " trên " + list.size();
    }

//Hiển thị dữ liệu lên bảng
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
            System.out.println(e);
        }
    }

//Lấy dữ liệu trên database
    public void setFormKhachHang(KhachHangEntity kh) {

        txtmaKH.setText(kh.getMaKH());
        txthoTen.setText(kh.getHoTen());
        txtSDT.setText(kh.getDienThoai());
        txtdiaChi.setText(kh.getDiaChi());
        lblTichDiem.setText(String.valueOf(kh.getTichDiem()));

    }

//Đẩy dữ liệu trên database
    public KhachHangEntity getFormKhachHang() {
        KhachHangEntity kh = new KhachHangEntity();
        Standardization std = new Standardization();
        kh.setMaKH(txtmaKH.getText());
        kh.setHoTen(std.hightlingtName(txthoTen.getText()));
        kh.setDienThoai(txtSDT.getText());
        kh.setDiaChi(txtdiaChi.getText());
        kh.setTichDiem(0);
        return kh;

    }

//Xóa trắng form
    public void clearFormKhachHang() {
        txtmaKH.setEditable(true);
        KhachHangEntity kh = new KhachHangEntity();
        this.setFormKhachHang(kh);
        this.row = -1;
        // setcolorlableKH();
        this.updateStatusKhachHang();
        initKhachHang();
        lblrecordKH.setText(recordKhachHang());
        if (darkMode == true) {
            lblHovaTenKH.setForeground(new Color(102, 255, 255));
            lblMaKH.setForeground(new Color(102, 255, 255));
            lblSDTKH.setForeground(new Color(102, 255, 255));
            lblDiaChiKH.setForeground(new Color(102, 255, 255));
        } else {
            lblHovaTenKH.setForeground(new Color(0, 0, 255));
            lblMaKH.setForeground(new Color(0, 0, 255));
            lblSDTKH.setForeground(new Color(0, 0, 255));
            lblDiaChiKH.setForeground(new Color(0, 0, 255));
        }
    }
//Set màu cho label

    public void setcolorlableKH() {
        lblHovaTenKH.setForeground(new Color(0, 0, 255));
        lblSDTKH.setForeground(new Color(0, 0, 255));
        lblDiaChiKH.setForeground(new Color(0, 0, 255));
        lblMaKH.setForeground(new Color(0, 0, 255));

    }

//Trạng thái của các nút
    public void updateStatusKhachHang() {
        boolean edit = (this.row >= 0);
        boolean first = (this.row == 0);
        boolean last = (this.row == tblKhachHang.getRowCount() - 1);
        //Trạng thái form
        btncapNhatKhachHang.setEnabled(edit);
        btnxoaKhachHang.setEnabled(edit);
        //Trạng thái điều hướng
        btnFirstKH.setEnabled(edit && !first);
        btnPrevKH.setEnabled(edit && !first);
        btnNextKH.setEnabled(edit && !last);
        btnLastKH.setEnabled(edit && !last);
    }

//Hàm trung gian nhận dữ liệu từ table rồi đẩy dữ liệu lên setform
    public void editKhachHang() {
        countClick = 0;
        String makh = (String) tblKhachHang.getValueAt(this.row, 0);
        KhachHangEntity kh = KhachHang.selectById(makh);
        tblKhachHang.setRowSelectionInterval(this.row, this.row);
        this.setFormKhachHang(kh);
        this.updateStatusKhachHang();
        lblrecordKH.setText(recordKhachHang());
    }

//Thêm dữ liệu
    public void insertKhachHang() {
        List<KhachHangEntity> listkh = KhachHang.selectAll();
        for (KhachHangEntity kh : listkh) {
            if (txtMAKH.getText().equals(kh.getMaKH())) {
                int choice = (JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật khách hàng mã: " + txtMAKH.getText() + "?", "Xác nhận", JOptionPane.YES_NO_OPTION));
                if (choice == JOptionPane.YES_OPTION) {
                    updateKhachHang();
                } else {
                    return;
                }
            }
            if (txtSDT.getText().equalsIgnoreCase(kh.getDienThoai())) {
                MsgBox.alert(this, "Số điện thoại đã tồn tại!");
                txtSDT.requestFocus();
                return;
            }
        }
        KhachHangEntity kh = getFormKhachHang();
        try {

            KhachHang.insert(kh);
            listKhachHang();
            insertGioHang(kh.getDienThoai(), kh.getMaKH(), ktTenDN);
            listGioHang();
            this.fillTableKhachHang();
            this.clearFormKhachHang();
            MsgBox.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Thêm mới thất bại!");
            System.out.println(e);
        }
    }

//Xóa dữ liệu
    public void deleteKhachHang() {
        String makh = txtmaKH.getText();
        try {
            Giohang.delete(txtSDT.getText());
            KhachHang.delete(makh);
            listKhachHang();
            this.fillTableKhachHang();
            this.clearFormKhachHang();
            MsgBox.alert(this, "Xóa thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Bạn không thể xóa khách hàng này!");
//            System.out.println(e);
        }
    }

//Cập nhật dữ liệu
    public void updateKhachHang() {
        listGioHang();
        List<GioHangEntity> listGH = new ArrayList<>();
        List<GioHangEntity> listGHang = new ArrayList<>();
        listGHang=Giohang.selectAll();
        KhachHangEntity kh = getFormKhachHang();
        listGH = Giohang.selectByName(kh.getMaKH());
        List<KhachHangEntity> listkh = KhachHang.selectAll();
//        for (GioHangEntity gh : listGHang) {
//            if (txtSDT.getText().equalsIgnoreCase(gh.getMaGH())) {
//                MsgBox.alert(this, "Số điện thoại đã tồn tại!");
//                txtSDT.requestFocus();
//                return;
//            }
//        }
        try {
            KhachHang.update(kh);
            listKhachHang();
            for (GioHangEntity gh : listGHang) {
//                if (txtSDT.getText().equalsIgnoreCase(gh.getMaGH())) {
                    if (!kh.getDienThoai().equalsIgnoreCase(gh.getMaGH()) && kh.getMaKH().equalsIgnoreCase(gh.getMaKH())) {
                        insertGioHang(kh.getDienThoai(), kh.getMaKH(), ktTenDN);
                        listGioHang();
//                    }
                }
            }
            this.fillTableKhachHang();
            this.clearFormKhachHang();
            MsgBox.alert(this, "Cập nhật thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Cập nhật thất bại!");
            System.out.println(e);
        }
    }

//Trở về dòng đầu tiên trên bảng
    public void firstKhachHang() {
        this.row = 0;
        this.editKhachHang();
    }

//Chạy tới dòng tiếp theo trên bảng
    public void nextKhachHang() {
        if (this.row < tblKhachHang.getRowCount() - 1) {
            this.row++;
            this.editKhachHang();
        } else {
            this.firstKhachHang();
        }
    }

//Lùi về 1 dòng
    public void prevKhachHang() {
        if (this.row > 0) {
            this.row--;
            this.editKhachHang();
        } else {
            this.lastKhachHang();
        }
    }

//Đi tới dòng cuối cùng của bảng
    public void lastKhachHang() {
        this.row = tblKhachHang.getRowCount() - 1;
        this.editKhachHang();
    }
//--------------------------------------------------------------HÓA ĐƠN----------------------------------------------------------

//Hiển trị dữ liệu trên bảng và trạng thái ban đầu của các nút 
    public void initHoaDon() {
//        btnIn.setEnabled(false);
        btnazhd.setEnabled(false);
        btnzahd.setEnabled(false);
        txtTENKH.setEditable(false);
        txtMAKH.setEditable(false);
        txtTENNV.setEditable(false);
        //btnXoahd.setEnabled(false);
        setLocationRelativeTo(null);
        listHoaDon();
        this.fillTableHoaDon();
        this.row = -1;
        this.updateStatusHoaDon();
    }

//Lấy vị trí của dòng trong bảng
    public String recordHoaDon() {
        List<HoaDonEntity> list = HoaDon.selectAll();
        return (row + 1) + " trên " + list.size();
    }

    //Add dữ liệu vào list tạm
    public void listHoaDon() {
        listHoaDon = HoaDon.selectAll();
        List<HoaDonEntity> listHDTemp = new ArrayList<>();
        listHDTemp.addAll(listHoaDon);
        listHoaDon.clear();
        listHoaDon.addAll(listHDTemp);
    }

//Hiển thị dữ liệu lên bảng
    public void fillTableHoaDon() {
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);
        try {
            for (HoaDonEntity hd : listHoaDon) {
                Object[] row = {hd.getMaHD(), hd.getNgayLap(), hd.getTongTien(), hd.getTichDiem(), hd.getPhanTramGG(), hd.getTienGiam(), (int) hd.getThanhTien()};
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
            System.out.println(e);
        }
    }

//Lấy dữ liệu từ database
    public void setFormHoaDon(HoaDonEntity hd) {
        txtMAKH.setText(hd.getMaKH());
        txtTENKH.setText(hd.getTenKH());
        txtTENNV.setText(hd.getTenNV());
        txtPhanTramGG.setText(String.valueOf(hd.getPhanTramGG()));
        txtTichDiem.setText(String.valueOf(hd.getTichDiem()));

    }

//Đẩy dữ liệu lên database
    public HoaDonEntity getFormHoaDon() {
        HoaDonEntity hd = new HoaDonEntity();
        GioHangTamEntity gh = listGHT.get(0);
        listHoaDon();
        if (listHoaDon.size() < 10) {
            hd.setMaHD("HD0" + (listHoaDon.size() + 1));
        } else {
            hd.setMaHD("HD" + (listHoaDon.size() + 1));
        }
        hd.setTenDN(ktTenDN);
        hd.setMaKH(gh.getMaKH());
        hd.setMaGH(txtSdtKH.getText());
        hd.setNgayLap(dayNow);
        hd.setTongTien(Float.valueOf(txtTongtiensp.getText().substring(0, txtTongtiensp.getText().length() - 4)));
        hd.setPhanTramGG(0);
        hd.setTichDiem(0);
        hd.setThanhTien(Float.valueOf(txtTongtiensp.getText().substring(0, txtTongtiensp.getText().length() - 4)));
        return hd;
    }

//Xóa trống form
    public void clearFormHoaDon() {
        HoaDonEntity hd = new HoaDonEntity();
        lblTichDiemHD.setText("0");
        this.setFormHoaDon(hd);
        this.row = -1;
        this.updateStatusHoaDon();
        initHoaDon();
    }

//Trạng thái của các nút và form
    public void updateStatusHoaDon() {
        boolean edit = (this.row >= 0);
        boolean first = (this.row == 0);
        boolean last = (this.row == tblHoaDon.getRowCount() - 1);
        //Trạng thái form
        btnXoahd.setEnabled(edit);
        //Trạng thái điều hướng
        btnFirsthd.setEnabled(edit && !first);
        btnPrevhd.setEnabled(edit && !first);
        btnNexthd.setEnabled(edit && !last);
        btnLasthd.setEnabled(edit && !last);
    }

//Hàm trung gian nhận dữ liệu từ table rồi đẩy dữ liệu lên setform
    public void editHoaDon() {
        countClick = 0;
        String mahd = (String) tblHoaDon.getValueAt(this.row, 0);
        HoaDonEntity hd = HoaDon.selectById(mahd);
        maHDIn = mahd;
        tblHoaDon.setRowSelectionInterval(this.row, this.row);
        KhachHangEntity kh = KhachHang.selectById(hd.getMaKH());
        lblTichDiemHD.setText(String.valueOf(kh.getTichDiem()));
        this.setFormHoaDon(hd);
        this.updateStatusHoaDon();
        lblrecordhd.setText(recordHoaDon());
    }

//Thêm dữ liệu 
    public void insertHoaDon() {
        HoaDonEntity hd = getFormHoaDon();
        float tien = Float.valueOf(txtTongtiensp.getText().substring(0, txtTongtiensp.getText().length() - 3));
        try {
            HoaDon.insert(hd);
            listHoaDon();
            this.fillTableHoaDon();
            maHDT = hd.getMaHD();
            insertCTHD();
            if (tien >= 100000) {
                KhachHangEntity kh = KhachHang.selectById(hd.getMaKH());
                int diem = (int) (tien / 100000) + kh.getTichDiem();
                kh.setMaKH(hd.getMaKH());
                kh.setTichDiem(diem);
                KhachHang.updateTd(kh);
                listKhachHang();
                this.fillTableKhachHang();
            }
        } catch (Exception e) {
            MsgBox.alert(this, e + "Thêm mới thất bại!");
            System.out.println(e);
        }

    }

//Cập nhật dữ liệu
    public void updateHoaDon() {
        HoaDonEntity hd = getFormHoaDon();
        try {
            HoaDon.update(hd);
            listHoaDon();
            this.fillTableHoaDon();
            this.clearFormHoaDon();
            MsgBox.alert(this, "Cập nhật thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Cập nhật thất bại!");
            System.out.println(e);
        }
    }

//Xóa dữ liệu
    public void deleteHoaDon() {
        String mahd = (String) tblHoaDon.getValueAt(this.row, 0);
        deleteHoaDonCT(mahd);
        try {
            HoaDon.delete(mahd);
            listHoaDon();
            MsgBox.alert(this, "Xóa thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Xóa thất bại!");
            System.out.println("1" + e);
        }
        this.fillTableHoaDon();
        this.clearFormHoaDon();
    }

    //Add dữ liệu vào list tạm
    public void listHoaDonCT() {
        listCtHD = HDCT.selectAll();
        List<HoaDonCTEntity> listHDCTTemp = new ArrayList<>();
        listHDCTTemp.addAll(listCtHD);
        listCtHD.clear();
        listCtHD.addAll(listHDCTTemp);
    }

//Xóa hóa đơn chi tiết
    public void deleteHoaDonCT(String mahd) {
        try {
            HDCT.delete(mahd);
            listHoaDonCT();
        } catch (Exception e) {
            MsgBox.alert(this, "Xóa thất bại!");
            System.out.println("2" + e);
        }
    }

//Trở về dòng đầu tiên của bảng
    public void firstHoaDon() {
        this.row = 0;
        this.editHoaDon();
    }

//Đi tới dòng tiếp theo
    public void nextHoaDon() {
        if (this.row < tblHoaDon.getRowCount() - 1) {
            this.row++;
            this.editHoaDon();
        } else {
            this.firstHoaDon();
        }
    }

//Lùi về 1 dòng
    public void prevHoaDon() {
        if (this.row > 0) {
            this.row--;
            this.editHoaDon();
        } else {
            this.lastHoaDon();
        }
    }

//Đi tới dòng cuối cùng của bảng
    public void lastHoaDon() {
        this.row = tblHoaDon.getRowCount() - 1;
        this.editHoaDon();
    }

//Tìm hóa đơn 
    private void timHoaDon() {
        String keyword = txtTimKiemhd.getText();
        listHoaDon = (List<HoaDonEntity>) HoaDon.selectById(keyword);
        this.fillTableHoaDon();
        this.clearFormHoaDon();
        this.row = - 1;
        updateStatusHoaDon();
    }

    private void timKiemHD() {
        String keyword = txtTimKiemhd.getText();
        listHoaDon = HoaDon.selectByKeyword(keyword);
        this.fillTableHoaDon();
//        this.clearFormHoaDon();
        this.row = - 1;
        updateStatusHoaDon();
    }
    //Tìm kiếm chức vụ

    private void timKiemCV() {
        String keyword = txtTimCV.getText();
        listCV = chucVu.selectByKeyword(keyword);
        this.fillTableChucVu();
//        this.clearFormChucVu();
        this.row = - 1;
        updateStatusChucVu();
    }
//------------------------------------------CÁC CHỨC NĂNG KHÁC----------------------------------------------------------
    //Sắp xếp loại

    public void sortLoai(int i) {

        String Loai = (String) cboLoai.getSelectedItem();
        if (Loai == null) {
            MsgBox.alert(this, "Vui lòng chọn tiêu chí muốn sắp xếp! ");
        } else {
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

    }

    //Sắp xếp Hãng
    public void sortHang(int i) {

        String Hang = (String) cboHang.getSelectedItem();
        if (Hang == null) {
            MsgBox.alert(this, "Vui lòng chọn tiêu chí muốn sắp xếp! ");
        } else {
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

    }
    //Sắp xếp khách hàng

    public void SortKH(int i) {
        String kh = (String) cboKh.getSelectedItem();
        if (kh.equalsIgnoreCase(" ")) {
            MsgBox.alert(this, "Vui lòng chọn tiêu chí muốn sắp xếp! ");
        } else {
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

    }
    //Sắp xếp sản phẩm

    public void SortSP(int i) {
        String SP = (String) cboSXSP.getSelectedItem();
        if (SP.equals("")) {
            MsgBox.alert(this, "Vui lòng chọn tiêu chí muốn sắp xếp! ");
            return;
        } else {
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
    }

    //Sắp xếp nhân viên
    public void SortNV(int i) {
        String NV = (String) cboSortNV.getSelectedItem();
        if (NV.equalsIgnoreCase(" ")) {
            MsgBox.alert(this, "Vui lòng chọn tiêu chí muốn sắp xếp! ");
        } else {
            Comparator<TaiKhoanEntity> aznv = new Comparator<TaiKhoanEntity>() {
                @Override
                public int compare(TaiKhoanEntity nv1, TaiKhoanEntity nv2) {
                    if (NV.equalsIgnoreCase("Tên đăng nhập")) {
                        return nv1.getTenDN().compareTo(nv2.getTenDN());
                    } else {
                        return nv1.getTenNV().compareTo(nv2.getTenNV());
                    }
                }
            };
            if (i == 0) {
                Collections.sort(listNhanVien, aznv);
            } else {
                Collections.sort(listNhanVien, aznv.reversed());
            }

            this.fillTableNhanVien();
        }

    }

    public void SortCV(int i) {
        String cv = (String) cboCV.getSelectedItem();
        if (cv.equalsIgnoreCase("")) {
            MsgBox.alert(this, "Vui lòng chọn tiêu chí muốn sắp xếp! ");
        } else {
            Comparator<ChucVuEntity> aznv = new Comparator<ChucVuEntity>() {
                @Override
                public int compare(ChucVuEntity cv1, ChucVuEntity cv2) {
                    if (CV.equalsIgnoreCase("Tên chức vụ")) {
                        return cv1.getTenCV().compareTo(cv2.getTenCV());
                    } else {
                        if (cv1.getMaCV() > cv2.getMaCV()) {
                            return 1;
                        } else if (cv1.getMaCV() < cv2.getMaCV()) {
                            return -1;
                        } else {
                            return 0;
                        }

                    }
                }
            };
            if (i == 0) {
                Collections.sort(listCV, aznv);
            } else {
                Collections.sort(listCV, aznv.reversed());
            }

            this.fillTableChucVu();
        }

    }
    //Sắp xếp hóa đơn

    public void SortHoaDon(int i) {
        String HD = (String) comboboxHD.getSelectedItem();
        if (HD.equalsIgnoreCase(" ")) {
            MsgBox.alert(this, "Vui lòng chọn tiêu chí muốn sắp xếp! ");
        } else {
            Comparator<HoaDonEntity> azhd = new Comparator<HoaDonEntity>() {
                @Override
                public int compare(HoaDonEntity hd1, HoaDonEntity hd2) {
                    if (HD.equalsIgnoreCase("Mã hóa đơn")) {
                        return hd1.getMaHD().compareTo(hd2.getMaHD());
                    } else if (HD.equalsIgnoreCase("Tổng tiền")) {
                        if (hd1.getTongTien() > hd2.getTongTien()) {
                            return 1;
                        } else if (hd1.getTongTien() < hd2.getTongTien()) {
                            return -1;
                        } else {
                            return 0;
                        }
                    } else {
                        if (hd1.getThanhTien() > hd2.getThanhTien()) {
                            return 1;
                        } else if (hd1.getThanhTien() < hd2.getThanhTien()) {
                            return -1;
                        } else {
                            return 0;
                        }
                    }

                }
            };
            if (i == 0) {
                Collections.sort(listHoaDon, azhd);
            } else {
                Collections.sort(listHoaDon, azhd.reversed());
            }

            this.fillTableHoaDon();
        }

    }

    public void btnHD() {
        String HD = (String) comboboxHD.getSelectedItem();
        if (HD.equalsIgnoreCase("")) {
            btnazhd.setEnabled(false);
            btnzahd.setEnabled(false);
        } else {
            btnazhd.setEnabled(true);
            btnzahd.setEnabled(true);
            if (HD.equalsIgnoreCase("Mã hóa đơn")) {
                btnazhd.setText("A - Z");
                btnzahd.setText("Z - A");
            } else {
                btnazhd.setText("Tăng");
                btnzahd.setText("Giảm");
            }
        }

    }

    public void btnSP() {
        String SP = (String) cboSXSP.getSelectedItem();
        if (!SP.equalsIgnoreCase("")) {
            btnazsp.setEnabled(true);
            btnzasp.setEnabled(true);
            if (SP.equalsIgnoreCase("Tên sản phẩm") || SP.equalsIgnoreCase("Mã sản phẩm")) {
                btnazsp.setText("A - Z");
                btnzasp.setText("Z - A");
            } else {
                btnazsp.setText("Tăng");
                btnzasp.setText("Giảm");
            }
        } else {
            btnazsp.setEnabled(false);
            btnzasp.setEnabled(false);
        }

    }

//------------------------------------------------------------NHÂN VIÊN--------------------------------------------------------
//Hiển trị dữ liệu trên bảng và trạng thái ban đầu của các nút
    public void initNhanVien() {
        setLocationRelativeTo(null);
        sbtnTrangThaiNV.setSelected(true);
        rdoNam.setSelected(true);
        listNVT();
        this.fillTableNhanVien();
        this.row = -1;
        this.updateStatusNhanVien();
        if ("".equalsIgnoreCase(txtTenDN.getText())) {
            cboVaiTro.setEnabled(true);
        }
    }
    //Hiển thị dữ liệu lên table

    public void fillTableNhanVien() {
        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        model.setRowCount(0);
        try {
            for (TaiKhoanEntity nv : listNhanVien) {
                String daytemp = formater.format(nv.getNgaySinh());
                if ("admin".equalsIgnoreCase(ktCV)) {
                    Object[] row = {nv.getTenDN(), nv.getTenNV(), nv.getTenCV(), nv.getEmail(),
                        nv.isTrangThai() ? "Đang hoạt động" : "Ngưng hoạt động", nv.getMatKhau(),
                        nv.getDiaChi(), nv.getDienThoai(), daytemp, nv.isGioiTInh()};
                    model.addRow(row);
                } else {
                    if (!nv.getTenCV().equalsIgnoreCase("admin")) {
                        Object[] row = {nv.getTenDN(), nv.getTenNV(), nv.getTenCV(), nv.getEmail(),
                            nv.isTrangThai() ? "Đang hoạt động" : "Ngưng hoạt động", nv.getMatKhau(),
                            nv.getDiaChi(), nv.getDienThoai(), daytemp, nv.isGioiTInh()};
                        model.addRow(row);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
            System.out.println(e);
        }
    }

    public void checkTen() {

    }
//Trạng thái của các nút

    public void updateStatusNhanVien() {
        boolean edit = (this.row >= 0);
        boolean first = (this.row == 0);
        boolean last = (this.row == tblNhanVien.getRowCount() - 1);
        //Trạng thái form
        btnCapNhatNV.setEnabled(edit);
        btnXoaNV.setEnabled(edit);
        //Trạng thái điều hướng
        btnFirstNV.setEnabled(edit && !first);
        btnPrevNV.setEnabled(edit && !first);
        btnNextNV.setEnabled(edit && !last);
        btnLastNV.setEnabled(edit && !last);
    }
//Đẩy dữ liệu lên database

    public void setFormNhanVien(TaiKhoanEntity tk) {
        txtTenDN.setText(tk.getTenDN());
        txtHoTenNV.setText(tk.getTenNV());
        ChucVuEntity listcv = chucVu.selectById(String.valueOf(tk.getMaCV()));
        if ("".equalsIgnoreCase(txtTenDN.getText())) {
            cboVaiTro.setSelectedItem("Nhân viên");
        } else {
            cboVaiTro.setSelectedItem(listcv.getTenCV());
            if ("Admin".equalsIgnoreCase(listcv.getTenCV())) {
                cboVaiTro.setEnabled(false);
            } else {
                cboVaiTro.setEnabled(true);
            }
        }
        txtEmailNV.setText(tk.getEmail());
        txtMatKhauNV.setText(tk.getMatKhau());
        txtDiaChiNV.setText(tk.getDiaChi());
        txtSDTNV.setText(tk.getDienThoai());
        if (tk.getTenDN() != null) {
            String daytemp = formater.format(tk.getNgaySinh());
            txtNgaySinhNV.setText(daytemp);
        } else {
            txtNgaySinhNV.setText(formater.format(dayNow));
        }
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
//Hiển thị chức vụ

    public void fillComboxCV() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboVaiTro.getModel();
        model.removeAllElements();

        for (ChucVuEntity h : listCV) {
            model.addElement(String.valueOf(h.getTenCV()));
        }
    }
//Lấy dữ liệu từ database

    public TaiKhoanEntity getFormNhanVien() {
        Standardization std = new Standardization();
        String tencv = (String) cboVaiTro.getSelectedItem();
        TaiKhoanEntity h = new TaiKhoanEntity();
        h.setTenDN(txtTenDN.getText());
        h.setTenNV(std.hightlingtName(txtHoTenNV.getText()));
        List<ChucVuEntity> listcv = chucVu.selectAll();
        for (ChucVuEntity c : listcv) {
            if (tencv.equals(c.getTenCV())) {
                h.setMaCV(c.getMaCV());
            }
        }
        h.setEmail(txtEmailNV.getText());
        h.setMatKhau(txtMatKhauNV.getText());
        h.setDiaChi(txtDiaChiNV.getText());
        h.setDienThoai(txtSDTNV.getText());
        Date dayy = XDate.toDate(txtNgaySinhNV.getText(), "dd-MM-yyyy");
        h.setNgaySinh(dayy);
        h.setGioiTInh(rdoNam.isSelected());
        h.setTrangThai(sbtnTrangThaiNV.isSelected());
        return h;
    }
//Sự kiện của nút trạng thái

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
            } else {
                if (sbtnTrangThaiNV.isSelected()) {
                    sbtnTrangThaiNV.setSelected(false);
                } else {
                    sbtnTrangThaiNV.setSelected(true);
                }
            }
        } else {
            if (sbtnTrangThaiNV.isSelected()) {
                txtTrangThaiNV.setText("Đang hoạt động");
                txtTrangThaiNV.setForeground(new Color(0, 153, 0));
            } else {
                txtTrangThaiNV.setText("Ngưng hoạt động");
                txtTrangThaiNV.setForeground(Color.red);
            }
        }
    }
//Hàm trung gian nhận dữ liệu từ table rồi đẩy dữ liệu lên setform

    public void editNV() {
        countClick = 0;
        String tendn = (String) tblNhanVien.getValueAt(this.row, 0);
        TaiKhoanEntity h = NhanVien.selectById(tendn);
        tblNhanVien.setRowSelectionInterval(this.row, this.row);
        if ("Admin".equalsIgnoreCase(h.getTenCV())) {
            sbtnTrangThaiNV.setVisible(false);
            lblTrangThai.setVisible(false);
            txtTrangThaiNV.setVisible(false);
        } else {
            sbtnTrangThaiNV.setVisible(true);
            lblTrangThai.setVisible(true);
            txtTrangThaiNV.setVisible(true);
        }
        this.setFormNhanVien(h);
        this.updateStatusNhanVien();
        lblRecordNV.setText(recordNV());
        lblrecordHang.setText(recordHang());
        lblrecordLoai.setText(recordLoai());
    }
    //Tìm kiếm theo tên nhân viên

    private void timKiemNV() {
        String keyword = txtTimKiemNV.getText();
//        this.listNhanVien.clear();
        this.listNhanVien = NhanVien.selectByKeyword(keyword);
        this.fillTableNhanVien();
//        this.clearFormNV();
        this.row = - 1;
        updateStatusNhanVien();

    }

    //Add dữ liệu vào list tạm
    public void listNVT() {
        listNhanVien = NhanVien.selectAll();
        List<TaiKhoanEntity> listNhanVienTemp = new ArrayList<>();
        listNhanVienTemp.addAll(listNhanVien);
        listNhanVien.clear();
        listNhanVien.addAll(listNhanVienTemp);
    }
    //Lấy vị trí của dòng trong bảng

    public String recordNV() {
        List<TaiKhoanEntity> list = NhanVien.selectAll();
        return (row + 1) + " trên " + list.size();
    }
//Xóa trống form

    public void clearFormNV() {
        TaiKhoanEntity nv = new TaiKhoanEntity();
        cboVaiTro.setEnabled(true);
        rdoNam.setSelected(true);
        buttonGroup1.clearSelection();
        this.setFormNhanVien(nv);
        this.row = -1;
        // setcolorlableNV();
        this.updateStatusNhanVien();
        initNhanVien();
        lblRecordNV.setText(recordNV());
        if (darkMode == true) {
            lblTenDN.setForeground(new Color(102, 255, 255));
            lblHovaTenNV.setForeground(new Color(102, 255, 255));
            lblMatKhau.setForeground(new Color(102, 255, 255));
            lblEmailNV.setForeground(new Color(102, 255, 255));
            lblDiaChiNV.setForeground(new Color(102, 255, 255));
            lblSDTNV.setForeground(new Color(102, 255, 255));
        } else {
            lblTenDN.setForeground(new Color(0, 0, 255));
            lblHovaTenNV.setForeground(new Color(0, 0, 255));
            lblMatKhau.setForeground(new Color(0, 0, 255));
            lblEmailNV.setForeground(new Color(0, 0, 255));
            lblDiaChiNV.setForeground(new Color(0, 0, 255));
            lblSDTNV.setForeground(new Color(0, 0, 255));
        }
    }

    //Set màu cho label
    public void setcolorlableNV() {
        lblTenDN.setForeground(new Color(0, 0, 255));
        lblHovaTenNV.setForeground(new Color(0, 0, 255));
        lblSDTNV.setForeground(new Color(0, 0, 255));
        lblEmailNV.setForeground(new Color(0, 0, 255));
        lblDiaChiNV.setForeground(new Color(0, 0, 255));
        lblMatKhau.setForeground(new Color(0, 0, 255));
    }
//Thêm dữ liệu

    public void insertNV() {
        TaiKhoanEntity nv = getFormNhanVien();
        String tencv = (String) cboVaiTro.getSelectedItem();
        if ("Admin".equalsIgnoreCase(tencv)) {
            MsgBox.alert(this, "Bạn không được phép thêm Admin!");
            this.clearFormChucVu();
            return;
        } else if ("Quản lí".equalsIgnoreCase(tencv) && "Quản lí".equalsIgnoreCase(CV)) {
            MsgBox.alert(this, "Bạn không được phép thêm quản lí!");
            return;
        } else {
            List<TaiKhoanEntity> listnv = NhanVien.selectAll();
            for (TaiKhoanEntity sp1 : listnv) {
                if (txtTenDN.getText().equals(sp1.getTenDN())) {
                    int choice = (JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật tên nhân viên: " + txtTenDN.getText() + "?", "Xác nhận", JOptionPane.YES_NO_OPTION));
                    if (choice == JOptionPane.YES_OPTION) {
                        updateNV();
                    } else {
                        return;
                    }
                }
            }
            try {
                NhanVien.insert(nv);
                listNVT();
                this.fillTableNhanVien();
                this.clearFormNV();
                MsgBox.alert(this, "Thêm mới thành công!");
            } catch (Exception e) {
                MsgBox.alert(this, "Thêm mới thất bại!");
                System.out.println(e);
            }
        }
    }
//Cập nhật dữ liệu

    public void updateNV() {
        TaiKhoanEntity nv = getFormNhanVien();
        String tencv = (String) cboVaiTro.getSelectedItem();
        if ("Admin".equalsIgnoreCase(tencv)) {
            MsgBox.alert(this, "Bạn không được phép cập nhật thành Admin!");
            this.clearFormChucVu();
            return;
        } else if ("Quản lí".equalsIgnoreCase(tencv) && "Quản lí".equalsIgnoreCase(CV) && !ktTenDN.equalsIgnoreCase(txtTenDN.getText())) {
            MsgBox.alert(this, "Bạn không được phép cập nhật tài khoản này!");
            return;
        } else {
            try {
                NhanVien.update(nv);
                listNVT();
                this.fillTableNhanVien();
                this.clearFormNV();
                MsgBox.alert(this, "Cập nhật thành công!");
            } catch (Exception e) {
                MsgBox.alert(this, "Cập nhật thất bại!");
                System.out.println(e);
            }
        }
    }
//Xóa dữ liệu

    public void deleteNV() {
        String tendn = txtTenDN.getText();
//
//        List<HoaDonEntity> listHD = new ArrayList<>();
//        List<GioHangEntity> listGH = new ArrayList<>();
        String vaiTro = (String) cboVaiTro.getSelectedItem();
        if (vaiTro.equalsIgnoreCase("admin")) {
            MsgBox.alert(this, "Không thể xóa tài khoản Admin!");
            return;
        } else if (ktTenDN.equalsIgnoreCase(tendn)) {
            MsgBox.alert(this, "Bạn không thể xóa chính mình!");
            return;
        } else if ("Quản lí".equalsIgnoreCase(CV) && "Quản lí".equalsIgnoreCase(vaiTro)) {
            MsgBox.alert(this, "Bạn không thể xóa Quản lí!");
            return;
        } else {
            try {
                NhanVien.delete(tendn);
                listNVT();
                this.fillTableNhanVien();
                this.clearFormNV();
                MsgBox.alert(this, "Xóa thành công!");
            } catch (Exception e) {
//                 System.out.println(e);
                if (sbtnTrangThaiNV.isSelected()) {
                    int choice = (JOptionPane.showConfirmDialog(this, "Không được phép xóa nhân viên này \nBạn có muốn tắt trạng thái hoạt động!", "Xác nhận", JOptionPane.YES_NO_OPTION));
                    if (choice == JOptionPane.YES_OPTION) {
                        sbtnTrangThaiNV.setSelected(false);
                        updateNV();
                        return;
                    } else {
                        return;
                    }
                } else {
                    MsgBox.alert(this, "Nhân viên không hoạt động!");
                    return;
                }
            }
        }
    }

//Trở về dòng đầu tiên trong bảng
    public void firstNV() {
        this.row = 0;
        this.editNV();
    }
//Đi tới dòng tiếp theo 

    public void nextNV() {
        if (this.row < tblNhanVien.getRowCount() - 1) {
            this.row++;
            this.editNV();
        } else {
            this.firstNV();
        }
    }
//Lùi về 1 dòng

    public void prevNV() {
        if (this.row > 0) {
            this.row--;
            this.editNV();
        } else {
            this.lastNV();
        }
    }
//Đi tới dòng cuối cùng

    public void lastNV() {
        this.row = tblNhanVien.getRowCount() - 1;
        this.editNV();
    }
//Kiểm tra lỗi nhân viên

    public boolean checkNhanVienlight() {
        lblTenDN.setForeground(new Color(0, 0, 255));
        lblHovaTenNV.setForeground(new Color(0, 0, 255));
        lblMatKhau.setForeground(new Color(0, 0, 255));
        lblEmailNV.setForeground(new Color(0, 0, 255));
        lblDiaChiNV.setForeground(new Color(0, 0, 255));
        lblSDTNV.setForeground(new Color(0, 0, 255));
        if (txtTenDN.getText().equals("")) {
            MsgBox.alert(this, " Tên đăng nhập không để trống!");
            txtTenDN.requestFocus();
            lblTenDN.setForeground(Color.RED);
            return false;
        } else if (txtTenDN.getText().length() < 2) {
            MsgBox.alert(this, "Vui lòng nhập tên đăng nhập 4 đến 6 kí tự không chứa khoảng trắng và kí tự đặc biệt!");
            lblTenDN.setForeground(Color.red);
            return false;
        } else {
            Standardization std = new Standardization();
            if (!std.tendn(txtTenDN.getText())) {
                MsgBox.alert(this, "Tên đăng nhập không hợp lệ vui lòng không nhập các kí tự đặt biệt hoặc khoảng trắng!");
                lblTenDN.setForeground(Color.red);
                return false;
            }
//            else {
//                lblTenDN.setForeground(new Color(0, 0, 255));
//            }
        }
        if (txtHoTenNV.getText().equals("")) {
            MsgBox.alert(this, "Họ tên nhân viên không để trống!");
            txtHoTenNV.requestFocus();
            lblHovaTenNV.setForeground(Color.RED);
            return false;
        } else {
            Standardization std = new Standardization();
            //String validate = tkDAO.validate(txtHoTenNV.getText());
            if (!std.checkName(txtHoTenNV.getText())) {
                MsgBox.alert(this, "Tên nhân viên không hợp lệ vui lòng không nhập kí tự đặt biệt!");
                lblHovaTenNV.setForeground(Color.red);
                return false;
            }
//            else {
//                lblHovaTenNV.setForeground(new Color(0, 0, 255));
//            }
            /*
            if (!validate.equals("")) {
                if (validate.equalsIgnoreCase("Tên nhân viên không hợp lệ!")) {
                    MsgBox.alert(this, "Tên nhân viên không hợp lệ!");
                    lblHovaTenNV.setForeground(Color.red);
                    return false;
                }
            } else {
                lblHovaTenNV.setForeground(new Color(0, 0, 255));
            }
             */

        }
        if (txtMatKhauNV.getText().equals("")) {
            MsgBox.alert(this, "Mật khẩu không được để trống!");
            txtMatKhauNV.requestFocus();
            lblMatKhau.setForeground(Color.RED);
            return false;
        }
//        else {
//            lblMatKhau.setForeground(new Color(0, 0, 255));
//        }
        if (txtDiaChiNV.getText().equals("")) {
            MsgBox.alert(this, "Địa chỉ không để trống!");
            txtDiaChiNV.requestFocus();
            lblDiaChiNV.setForeground(Color.RED);
            return false;
        }
//        else {
//            lblDiaChiNV.setForeground(new Color(0, 0, 255));
//        }
        if (txtNgaySinhNV.getText().equals("")) {
            MsgBox.alert(this, "Ngày sinh không để trống!!");
            txtNgaySinhNV.requestFocus();
            lblNgaySinhNV.setForeground(Color.RED);
            return false;
        }
        try {
            formater.setLenient(false);
            if (day().compareTo(formater.parse(txtNgaySinhNV.getText())) < 0) {
                MsgBox.alert(this, "Ngày sinh phải trước ngày " + day());
                txtNgaySinhNV.requestFocus();
                lblNgaySinhNV.setForeground(Color.RED);
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        if (txtSDTNV.getText().equals("")) {
            MsgBox.alert(this, "Số điện thoại không được để trống!");
            txtSDTNV.requestFocus();
            lblSDTNV.setForeground(Color.RED);
            return false;
        } else {
            Matcher matcher = Pattern.compile(SDT).matcher(txtSDTNV.getText());
            if (matcher.matches() == false) {
                MsgBox.alert(this, "Số điện thoại không đúng dịnh dạng!");
                txtSDTNV.requestFocus();
                lblSDTNV.setForeground(Color.RED);
                return false;
            }
//            else {
//                lblSDTNV.setForeground(new Color(0, 0, 255));
//            }
        }
        if (txtEmailNV.getText().equals("")) {
            MsgBox.alert(this, "Email không được để trống!");
            txtEmailNV.requestFocus();
            lblEmailNV.setForeground(Color.RED);
            return false;
        } else {
            Matcher matcher = Pattern.compile(MAIL_REGEX).matcher(txtEmailNV.getText());
            if (matcher.matches() == false) {
                MsgBox.alert(this, "Email không đúng dịnh dạng!");
                txtEmailNV.requestFocus();
                lblEmailNV.setForeground(Color.RED);
                return false;
            }
//            else {
//                lblEmailNV.setForeground(new Color(0, 0, 255));
//            }
        }
        return true;
    }

    public boolean checkNhanViendark() {
        lblTenDN.setForeground(new Color(102, 255, 255));
        lblHovaTenNV.setForeground(new Color(102, 255, 255));
        lblMatKhau.setForeground(new Color(102, 255, 255));
        lblEmailNV.setForeground(new Color(102, 255, 255));
        lblDiaChiNV.setForeground(new Color(102, 255, 255));
        lblSDTNV.setForeground(new Color(102, 255, 255));
        if (txtTenDN.getText().equals("")) {
            MsgBox.alert(this, " Tên đăng nhập không để trống!");
            txtTenDN.requestFocus();
            lblTenDN.setForeground(Color.RED);
            return false;
        } else if (txtTenDN.getText().length() < 2) {
            MsgBox.alert(this, "Vui lòng nhập tên đăng nhập 4 đến 6 kí tự không chứa khoảng trắng và kí tự đặc biệt!");
            lblTenDN.setForeground(Color.RED);
            return false;
        } else {
            Standardization std = new Standardization();
            if (!std.tendn(txtTenDN.getText())) {
                MsgBox.alert(this, "Tên đăng nhập không hợp lệ vui lòng không nhập các kí tự đặt biệt hoặc khoảng trắng!");
                lblTenDN.setForeground(Color.RED);
                return false;
            }
//            else {
//                lblTenDN.setForeground(new Color(102, 255, 255));
//            }
        }
        if (txtHoTenNV.getText().equals("")) {
            MsgBox.alert(this, "Họ tên nhân viên không để trống!");
            txtHoTenNV.requestFocus();
            lblHovaTenNV.setForeground(Color.RED);
            return false;
        } else {
            Standardization std = new Standardization();
            //String validate = tkDAO.validate(txtHoTenNV.getText());
            if (!std.checkName(txtHoTenNV.getText())) {
                MsgBox.alert(this, "Tên nhân viên không hợp lệ vui lòng không nhập kí tự đặt biệt!");
                lblHovaTenNV.setForeground(Color.RED);
                return false;
            }
//            else {
//                lblHovaTenNV.setForeground(new Color(102, 255, 255));
//            }
            /*
            if (!validate.equals("")) {
                if (validate.equalsIgnoreCase("Tên nhân viên không hợp lệ!")) {
                    MsgBox.alert(this, "Tên nhân viên không hợp lệ!");
                    lblHovaTenNV.setForeground(Color.red);
                    return false;
                }
            } else {
                lblHovaTenNV.setForeground(new Color(0, 0, 255));
            }
             */

        }
        if (txtMatKhauNV.getText().equals("")) {
            MsgBox.alert(this, "Mật khẩu không được để trống!");
            txtMatKhauNV.requestFocus();
            lblMatKhau.setForeground(Color.RED);
            return false;
        } else {
            lblMatKhau.setForeground(new Color(102, 255, 255));
        }
        if (txtDiaChiNV.getText().equals("")) {
            MsgBox.alert(this, "Địa chỉ không để trống!");
            txtDiaChiNV.requestFocus();
            lblDiaChiNV.setForeground(Color.RED);
            return false;
        }
//        else {
//            lblDiaChiNV.setForeground(new Color(102, 255, 255));
//        }
        if (txtNgaySinhNV.getText().equals("")) {
            MsgBox.alert(this, "Ngày sinh không để trống!!");
            txtNgaySinhNV.requestFocus();
            lblNgaySinhNV.setForeground(Color.RED);
            return false;
        }
        try {
            formater.setLenient(false);
            if (day().compareTo(formater.parse(txtNgaySinhNV.getText())) < 0) {
                MsgBox.alert(this, "Ngày sinh phải trước ngày " + day());
                txtNgaySinhNV.requestFocus();
                lblNgaySinhNV.setForeground(Color.RED);
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        if (txtSDTNV.getText().equals("")) {
            MsgBox.alert(this, "Số điện thoại không được để trống!");
            txtSDTNV.requestFocus();
            lblSDTNV.setForeground(Color.RED);
            return false;
        } else {
            Matcher matcher = Pattern.compile(SDT).matcher(txtSDTNV.getText());
            if (matcher.matches() == false) {
                MsgBox.alert(this, "Số điện thoại không đúng dịnh dạng!");
                txtSDTNV.requestFocus();
                lblSDTNV.setForeground(Color.RED);
                return false;
            }
//            else {
//                lblSDTNV.setForeground(new Color(102, 255, 255));
//            }
        }
        if (txtEmailNV.getText().equals("")) {
            MsgBox.alert(this, "Email không được để trống!");
            txtEmailNV.requestFocus();
            lblEmailNV.setForeground(Color.RED);
            return false;
        } else {
            Matcher matcher = Pattern.compile(MAIL_REGEX).matcher(txtEmailNV.getText());
            if (matcher.matches() == false) {
                MsgBox.alert(this, "Email không đúng dịnh dạng!");
                txtEmailNV.requestFocus();
                lblEmailNV.setForeground(Color.RED);
                return false;
            }
//            else {
//                lblEmailNV.setForeground(new Color(102, 255, 255));
//            }
        }
        return true;
    }
//----------------------------------------------------------GIỎ HÀNG----------------------------------------------------------
    //Thêm sản phẩm vào giỏ hàng

    public void insertGioHang(String maGH, String maKH, String tenDn) {
        GioHangEntity gh = new GioHangEntity();
        try {
            gh.setMaGH(maGH);
            gh.setMaKH(maKH);
            gh.setTenDN(tenDn);
            Giohang.insert(gh);
            listGioHang();
        } catch (Exception e) {
            MsgBox.alert(cardHoaDonSanPham, "Thêm mới thất bại!");
            System.out.println(e);
        }
    }

    //Add dữ liệu vào list tạm
    public void listGioHang() {
        listGiohang = Giohang.selectAll();
        List<GioHangEntity> listGioHang = new ArrayList<>();
        listGioHang.addAll(listGiohang);
        listGiohang.clear();
        listGiohang.addAll(listGioHang);
    }

    //Hiển trị dữ liệu trên bảng và trạng thái ban đầu của các nút 
    public void initgioHang() {
        btnxoaGioHang.setEnabled(false);
        btnXacNhanDonHang.setEnabled(false);
        spnSL.setEnabled(false);
    }

//Thêm dữ liệu
    public void insertGH(String ma, int sl) {
        listGHT.clear();
        listGHT = GioHangtam.selectByIdlist(txtSdtKH.getText());
        if (listGHT == null || listGHT.isEmpty()) {
            try {
                GioHangTamEntity gh = new GioHangTamEntity();
                gh.setMaGH(txtSdtKH.getText());
                gh.setMaSP(ma);
                gh.setSoLuong(sl);
                GioHangtam.insert(gh);
                filltableGioHang();
            } catch (Exception e) {
                MsgBox.alert(cardHoaDonSanPham, "Thêm mới thất bại!");
                System.out.println(e);
            }
        } else {
            for (GioHangTamEntity gh : listGHT) {
                if (ma.equalsIgnoreCase(gh.getMaSP())) {

                    int choice = (JOptionPane.showConfirmDialog(this, "Sản phẩm đã tồn tại \n Bạn có muốn tăng số lượng không?", "Xác nhận", JOptionPane.YES_NO_OPTION));
                    if (choice == JOptionPane.YES_OPTION) {
                        try {
                            gh.setMaGH(txtSdtKH.getText());
                            gh.setMaSP(ma);
                            gh.setSoLuong(gh.getSoLuong() + 1);
                            GioHangtam.update(gh);
                            filltableGioHang();
                        } catch (Exception e) {
                            MsgBox.alert(cardHoaDonSanPham, "Thêm mới thất bại!");
                            System.out.println(e);
                        }
                        return;
                    } else {
                        return;
                    }
                }
            }
            try {
                GioHangTamEntity gh = new GioHangTamEntity();
                gh.setMaGH(txtSdtKH.getText());
                gh.setMaSP(ma);
                gh.setSoLuong(sl);
                GioHangtam.insert(gh);
                filltableGioHang();
            } catch (Exception e) {
                MsgBox.alert(cardHoaDonSanPham, "Thêm mới thất bại!");
                System.out.println(e);
            }
        }
    }

    public GioHangTamEntity getFormGH(int sl) {
        GioHangTamEntity gh = new GioHangTamEntity();
        gh.setMaGH(txtSdtKH.getText());
        gh.setMaSP((String) tblCart.getValueAt(this.row, 0));
        gh.setSoLuong(sl);
        return gh;
    }
//hiển thị sdt khách hàng 

    public void sdtKH(String sdt) {
        JPopupMenu popupMenu = new JPopupMenu("Title");
        if (!sdt.equals("")) {
            for (KhachHangEntity kh : listKhachHang) {
                if (kh.getDienThoai().contains(txtSdtKH.getText())) {
                    JMenuItem cutMenuItem = new JMenuItem(kh.getDienThoai());
                    cutMenuItem.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            txtSdtKH.setText(kh.getDienThoai());
                        }
                    });
                    popupMenu.add(cutMenuItem);
                }
            }
//            System.out.println(sdt);s
            popupMenu.setBackground(Color.white);
            popupMenu.show(home, 780, 10);
            txtSdtKH.requestFocus();
        }
    }

    public void editGH() {
        countClick = 0;
        String magh = (String) tblCart.getValueAt(this.row, 0);
        GioHangTamEntity gh = GioHangtam.selectById(magh);
        tblCart.setRowSelectionInterval(this.row, this.row);
    }
//Add dữ liệu vào list tạm

    public void listGHT() {
        listGHT = GioHangtam.selectAll();
        List<GioHangTamEntity> listGHTemp = new ArrayList<>();
        listGHTemp.addAll(listGHT);
        listGHT.clear();
        listGHT.addAll(listGHTemp);
    }
//update số lượng

    public void updategh(int sl) {
        GioHangTamEntity gh = getFormGH(sl);
        try {
            GioHangtam.update(gh);
            listGHT();
            this.filltableGioHang();
        } catch (Exception e) {
            MsgBox.alert(this, "Cập nhật thất bại!");
            System.out.println(e);
        }
    }
//delete dữ liệ giỏ hàng tạm

    public void deleteGH() {
        String magh = txtSdtKH.getText();
        String masp = (String) tblCart.getValueAt(this.row, 0);
        try {
            GioHangtam.delete2(magh, masp);
            listGHT();
            this.filltableGioHang();
            btnxoaGioHang.setEnabled(false);
        } catch (Exception e) {
            MsgBox.alert(this, "Xóa thất bại!");
            System.out.println(e);
        }
    }
//update sl trên bảng

//    public void updateSl() {
//        GioHangTamEntity gh = new GioHangTamEntity();
//        int sl = (int) tblCart.getValueAt(1, 3);
////        System.out.println(sl);
//        System.out.println(tblCart.getRowCount());
//        for (int i = 0; i < tblCart.getRowCount(); i++) {
//
////            if ((int) tblCart.getValueAt(i, 3) == 0) {
////                this.row = i;
////                deleteGH();
////                spnSL.setEnabled(false);
////                return;
////            } else {
////                gh.setMaGH(txtSdtKH.getText());
////                gh.setMaSP((String) tblCart.getValueAt(i, 0));
////                gh.setSoLuong((int) tblCart.getValueAt(i, 3));
////                try {
////                    GioHangtam.update(gh);
////                    listGHT();
////                    this.filltableGioHang();
////                } catch (Exception e) {
////                    MsgBox.alert(this, "Cập nhật thất bại!");
////                    System.out.println(e);
////                }
////                
////            }
////            MsgBox.alert(this, "Cập nhật điểm thành công!");
//        }
//
//    }
//Lấy ngày hiện tại
    public java.sql.Date day() {
        return dayNow;
    }

    public HoaDonCTEntity getFormHDCT(int i) {
        if (i >= 0) {
            listCtHD.clear();
            listCtHD = HDCT.selectAll();
            HoaDonCTEntity hd = new HoaDonCTEntity();
            GioHangTamEntity gh = listGHT.get(i);
            if (listCtHD.size() < 10) {
                hd.setMaCTHD("CTHD0" + (listCtHD.size() + 1));
            } else {
                hd.setMaCTHD("CTHD" + (listCtHD.size() + 1));
            }
            hd.setMaHD(maHDT);
            hd.setMaSP(gh.getMaSP());
            hd.setMaHang(gh.getMaHang());
            hd.setMaLH(gh.getMaLoai());
            hd.setNgayLap(dayNow);
            hd.setTenSP(gh.getTenSP());
            hd.setTenHang(gh.getTenHang());
            hd.setTenLH(gh.getTenLoai());
            hd.setNgayNhap(dayNow);
            hd.setGiaNhap(gh.getGiaNhap());
            hd.setGiaBan(gh.getGiaBan());
            hd.setSl(gh.getSoLuong());
            return hd;
        }
        return null;
    }
//Thêm dữ liệu 

    public void insertCTHD() {
        int i = 0;
        listGHT.clear();
        listGHT = GioHangtam.selectByIdlist(txtSdtKH.getText());
        for (GioHangTamEntity gh : listGHT) {
            HoaDonCTEntity hd = getFormHDCT(i);
            SanPhamEntity sp = SanPham.selectById(hd.getMaSP());
            if (hd.getSl() <= sp.getSl()) {
                try {
                    HDCT.insert(hd);
                    GioHangtam.delete2(gh.getMaGH(), gh.getMaSP()); // Xóa giỏ hàng tạm
                    listHoaDonCT();
                    listGHT();
                    int slSP = sp.getSl() - hd.getSl();
                    sp.setMaSP(gh.getMaSP());
                    sp.setSl(slSP);
                    SanPham.updateSL(sp);
                    if (slSP == 0) {
                        listSPT();
                        cardHoaDonSanPham.removeAll();
                        cardHoaDonSanPham.setVisible(false);
                        cardHoaDonSanPham.setVisible(true);
                        cartShoping(" ");
                    }
                    spnSL.setValue(0);
                    this.filltableGioHang();
                } catch (Exception e) {
                    MsgBox.alert(this, "Thêm mới thất bại!");
                    System.out.println(e);
                }
            } else {
                if (sp.getSl() <= 0) {
                    MsgBox.alert(this, "Sản phẩm đã bán hết!");
                    GioHangtam.delete2(gh.getMaGH(), gh.getMaSP());
                    listGHT();
                    this.filltableGioHang();
                    return;
                } else {
                    MsgBox.alert(this, "Số lượng sản phẩm " + sp.getMaSP() + " phải nhỏ hơn hoặc bằng " + sp.getSl());
                }
                return;
            }
        }
        i++;
    }

    public void openWebCame() {
        if (webcam.isOpen()) {
            webcam.close();
            cardHoaDonQR.setVisible(false);
            cardHoaDonSanPham.setVisible(true);
            txtFindNameProductCart.setVisible(true);
        } else {
            cardHoaDonQR.setVisible(true);
            cardHoaDonSanPham.setVisible(false);
            txtFindNameProductCart.setVisible(false);
            if (webcam.isOpen()) {
                webcam.close();
            }
            initWebcam();
        }
    }

    public void clearHD() {
        txtTienNhan.setText("");
        listCtHD.clear();
        tenSP.clear();
        giaSP.clear();
        SL.clear();
        thanhTien.clear();
        txtMAKH.setText("");
        txtTENKH.setText("");
        txtTichDiem.setText("");
        txtTENNV.setText("");
        txtPhanTramGG.setText("");
    }

    void cartShoping(String tenSpCart) {
        if (this.darkMode) {

            home = new FormHome(0);
        } else {
            home = new FormHome(1);
        }
        cardHoaDonSanPham.setLayout(new BorderLayout());
        cardHoaDonSanPham.add(home);
        SANPHAM(tenSpCart);
    }

    private void SANPHAM(String tenSpCart) {
        home.setEvent(new EventItem() {
            @Override
            public void itemClick(Component com, ModelItem item) {
                if (txtSdtKH.getText().equals("")) {
                    MsgBox.alert(cardHoaDonSanPham, "Vui lòng nhập số điện thoại!");
                    return;
                } else {
                    String ma = item.getDescription();
                    String tensp = item.getItemName();
                    float gia = (float) item.getPrice();
                    int sl = 1;
                    SanPhamEntity sp = SanPham.selectById(ma);
                    if (sp.getSl() > 0) {
                        insertGH(ma, sl);
                    } else {
                        MsgBox.alert(cardHoaDonSanPham, "Sản phẩm đã hết!");
                        return;
                    }
//                    SanPhamEntity sp = SanPham.selectById(ma);
//                    sp.setMaSP(ma);
//                    sp.setSl(sp.getSl() - 1);
//                    SanPham.updateSL(sp);
                }
            }
        });
        int ID = 1;

        for (SanPhamEntity sp : list) {
            if (sp.getTenSP().toLowerCase().contains(tenSpCart.toLowerCase())) {
                File file = new File("src\\com\\images\\" + sp.getHinh() + ".PNG");
                try {
                    Image img = ImageIO.read(file);
                    lblAnh.setText("");
                    int w = lblAnh.getWidth();
                    int h = lblAnh.getHeight();
                    String hienThi = " ";
                    if (sp.getSl() == 0) {
                        hienThi = "Đã hết";
                    }
                    home.addItem(new ModelItem(ID, sp.getTenSP(), sp.getMaSP(), hienThi, sp.getGiaBan(), sp.getTenH(), new ImageIcon(img.getScaledInstance(w, h, 0))));
                } catch (Exception e) {
                    System.out.println(e);
                }
                ID++;
            }

        }

    }

    public void findItemCart() {
        cardHoaDonSanPham.removeAll();
        cardHoaDonSanPham.setVisible(false);
        cardHoaDonSanPham.setVisible(true);
        cartShoping(txtFindNameProductCart.getText());
    }

    public void filltableGioHang() {
        DefaultTableModel model = (DefaultTableModel) tblCart.getModel();
        model.setRowCount(0);
        int thanhTien = 0;
        int check = 0;
        String sdt = txtSdtKH.getText(), ten = null;
        listGHT();
        KhachHangEntity kh = KhachHang.selectBySDT(sdt);
        lblNameCustomer.setText(kh.getHoTen());
        try {
            for (GioHangTamEntity gh : listGHT) {
                if (sdt.equals(gh.getMaGH())) {
                    check++;
                    thanhTien += gh.getTongTien();
                    Object[] row = {gh.getMaSP(), gh.getTenSP(), gh.getGiaBan(), gh.getSoLuong()};
                    model.addRow(row);

                }
                txtTongtiensp.setText(String.valueOf(thanhTien) + " VND");
            }

            if (check > 0) {
                btnXacNhanDonHang.setEnabled(true);
            } else {
                btnXacNhanDonHang.setEnabled(false);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
            System.out.println(e);
        }
    }

//-----------------------------------------------------------THỐNG KÊ------------------------------------------------------------
//Hiển trị dữ liệu trên bảng và trạng thái ban đầu của các nút
    public void initThongKe() {
        fillCboDay_ThongKe();
        fillCboMonth_ThongKe();
        fillCboYear_ThongKe();
        fillTableNhanVienXX();
        setDataPieChart(pnlChart);
        setDataBarChart(pnlChartDT);

    }
//Hiển thị ngày lên combobox

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
//Hiển thị tháng lên combobox

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
//Hiển thị năm lên combobox

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
//Thống kê sản phẩm bán chạy theo ngày hoặc tháng hoặc năm

    public void fillTableSPBanChay() {
        try {
            day = (String) cboDaySP.getSelectedItem();
            month = (String) cboMonthSP.getSelectedItem();
            year = (String) cboYearSP.getSelectedItem();
            String sortBy = (String) cboSort.getSelectedItem();
            DefaultTableModel model = (DefaultTableModel) tblSPBanChay.getModel();
            model.setRowCount(0);
            if (day.equals("Không chọn") && month.equals("Không chọn")) {
                if (sortBy.equals("Cao nhất")) {
                    listTKSP = TKdao.getSPBanChay(year);
                } else {
                    listTKSP = TKdao.getSPBanChayGD(year);
                }
            }
            if (month.equals("Không chọn")) {
                if (sortBy.equals("Cao nhất")) {
                    listTKSP = TKdao.getSPBanChay(year);
                } else {
                    listTKSP = TKdao.getSPBanChayGD(year);
                }
            }
            if (!day.equals("Không chọn") && !month.equals("Không chọn")) {
                if (sortBy.equals("Cao nhất")) {
                    listTKSP = TKdao.getSPBanChay(day, month, year);
                } else {
                    listTKSP = TKdao.getSPBanChayGD(day, month, year);
                }
            }
            if (day.equals("Không chọn") && !month.equals("Không chọn")) {
                if (sortBy.equals("Cao nhất")) {
                    listTKSP = TKdao.getSPBanChay(month, year);
                } else {
                    listTKSP = TKdao.getSPBanChayGD(month, year);
                }
            }
            int i = 0;
            for (Object[] row : listTKSP) {
                i++;
                model.addRow(row);
                if (i == 1 && sortBy.equals("Cao nhất")) {
                    SanPhamEntity sp = SanPham.selectById(String.valueOf(row[0]));
                    File file = new File("src\\com\\images\\" + sp.getHinh() + ".PNG");
                    try {
                        Image img = ImageIO.read(file);
                        lblAnhSpBanChay.setText("");
                        int w = lblAnhSpBanChay.getWidth();
                        int h = lblAnhSpBanChay.getHeight();
                        lblAnhSpBanChay.setIcon(new ImageIcon(img.getScaledInstance(w, h, 0)));
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        } catch (Exception ex) {
            //System.out.println(ex);
        }
    }

//Thống kê doanh thu theo ngày hoặc tháng hoặc năm
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
                int tempS = (int) row[0];
                double tempi = (double) row[1];
                Object[] rowO = {tempS, (int) tempi};
                model.addRow(rowO);
            }
        } catch (Exception ex) {
        }
    }
//Thống kê 3 nhân viên xuất sắc nhất

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
//Đọc file excel sản phẩm

    public void ImportFileExcelThongKeSP() {
        //hỏi
        int messageType = JOptionPane.QUESTION_MESSAGE;
        String[] option = {"Thêm dữ liệu và thư mục ảnh", "Chỉ thêm dữ liệu", "Thoát"};
        int code = JOptionPane.showOptionDialog(this, "Bạn muốn thao tác gì?", "Thêm dữ liệu qua file excel", 0, messageType, null, option, "Save");
        if (code == 0) {
            boolean checkImportXlxs = true;
            // thêm dữ liệu và thư mục ảnh
            try {
                String ktrMaSp = "", ktrDinhdang = "true";
                JFileChooser fc = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("xlsm", "xls", "xlsx");
                fc.setFileFilter(filter);
                fc.showOpenDialog(null);
                File f = fc.getSelectedFile();
                if (f == null) {
                    return;
                }
                String path = f.getAbsoluteFile().toString();
                if (!path.contains(".xlsx")) {
                    MsgBox.alert(this, "Vui lòng chọn file Excel!");
                } else {
                    FileInputStream fis = new FileInputStream(f);
                    XSSFWorkbook wb = new XSSFWorkbook(fis);
                    XSSFSheet sheet = wb.getSheetAt(0);
                    Iterator<Row> rowIter = sheet.iterator();
                    SanPhamEntity sp = new SanPhamEntity();
                    int i = 0;
                    while (rowIter.hasNext()) {
                        Row row = rowIter.next();
                        Iterator<Cell> cellIter = row.iterator();
                        if (i != 0) {
                            ktrDinhdang = "true";
                            while (cellIter.hasNext()) {
                                Cell cell = cellIter.next();
                                switch (cell.getColumnIndex()) {
                                    case 0:
                                        sp.setMaSP(String.valueOf(cell).toUpperCase());
                                        ktrMaSp = String.valueOf(cell);
                                        if (ktrMaSp.equals("")) {
                                            ktrDinhdang = "false";
                                            MsgBox.alert(this, "Mã sản phẩm không được bỏ trống!");
                                        }
                                        Matcher matcher = Pattern.compile(TypeProduct_REGEX).matcher(ktrMaSp.toUpperCase());
                                        if (matcher.matches() == false) {
                                            MsgBox.alert(this, "Mã sản phẩm " + ktrMaSp + " không hợp lệ (VD: SP0001)!");
                                            ktrDinhdang = "false";
                                        }
                                        break;
                                    case 1:
                                        sp.setTenSP(String.valueOf(cell));
                                        if (String.valueOf(cell).equals("")) {
                                            ktrDinhdang = "false";
                                            MsgBox.alert(this, "Tên sản phẩm không được bỏ trống!");
                                        }
                                        break;
                                    case 2:
                                        char kyTu;
                                        int countSpace = 0;
                                        String loaihang = String.valueOf(cell);
                                        if (loaihang.equals("")) {
                                            ktrDinhdang = "false";
                                            MsgBox.alert(this, "Tên loại hàng không được bỏ trống!");
                                        } else {
                                            List<LoaiHangEntity> listlh = Loai.selectAll();
                                            String malhMoi = loaihang.substring(0, 2).toUpperCase();
                                            for (int j = 0; j < loaihang.length(); j++) {
                                                kyTu = loaihang.charAt(j);
                                                if (Character.isWhitespace(kyTu)) {
                                                    countSpace++;
                                                    char kyTu2 = loaihang.charAt(j + 1);
                                                    if (countSpace == 1) {
                                                        malhMoi = loaihang.substring(0, 1).toUpperCase();
                                                        malhMoi = malhMoi + String.valueOf(kyTu2).toUpperCase();
                                                    }
                                                    if (countSpace == 2) {
                                                        malhMoi = malhMoi + String.valueOf(kyTu2).toUpperCase();
                                                        countSpace = 0;
                                                    }
                                                }
                                            }
                                            LoaiHangEntity lhn = new LoaiHangEntity();
                                            String checkMalh = "Exist";
                                            for (LoaiHangEntity lh : listlh) {
                                                if (lh.getTenLH().equalsIgnoreCase(loaihang)) {
                                                    checkMalh = "Exist";
                                                    sp.setMaLH(lh.getMaLH());
                                                    break;
                                                } else {
                                                    checkMalh = "Not Exist";
                                                }
                                            }
                                            if (checkMalh.equals("Not Exist")) {
                                                lhn.setMaLH(malhMoi);
                                                lhn.setTenLH(loaihang.substring(0, 1).toUpperCase() + loaihang.substring(1).toLowerCase());
                                                Loai.insert(lhn);
                                                sp.setMaLH(malhMoi);
                                                listLoai();
                                                fillTableLoai();
                                                clearFormLoai();
                                            }
                                        }
                                        break;
                                    case 3:
                                        String hang = String.valueOf(cell);
                                        if (hang.equals("")) {
                                            ktrDinhdang = "false";
                                            MsgBox.alert(this, "Tên hãng không được bỏ trống!");
                                        } else {
                                            List<HangEntity> listh = Hang.selectAll();
                                            String mahangMoi = hang.substring(0, 2).toUpperCase();
                                            char kyTuHang;
                                            countSpace = 0;
                                            for (int j = 0; j < hang.length(); j++) {
                                                kyTuHang = hang.charAt(j);
                                                if (Character.isWhitespace(kyTuHang)) {
                                                    countSpace++;
                                                    char kyTuHang2 = hang.charAt(j + 1);
                                                    if (countSpace == 1) {
                                                        mahangMoi = hang.substring(0, 1).toUpperCase();
                                                        mahangMoi = mahangMoi + String.valueOf(kyTuHang2).toUpperCase();
                                                    }
                                                    if (countSpace == 2) {
                                                        mahangMoi = mahangMoi + String.valueOf(kyTuHang2).toUpperCase();
                                                        countSpace = 0;
                                                    }
                                                }
                                            }
                                            HangEntity hn = new HangEntity();
                                            String checkMah = "Exist";
                                            for (HangEntity h : listh) {
                                                if (h.getTenHang().equalsIgnoreCase(hang)) {
                                                    checkMah = "Exist";
                                                    sp.setMaHang(h.getMaHang());
                                                    break;
                                                } else {
                                                    checkMah = "Not Exist";
                                                }
                                            }
                                            if (checkMah.equals("Not Exist")) {
                                                hn.setMaHang(mahangMoi);
                                                hn.setTenHang(hang.toUpperCase());
                                                Hang.insert(hn);
                                                sp.setMaHang(mahangMoi);
                                                listHang();
                                                fillTableHang();
                                                clearFormHang();
                                            }
                                        }
                                        break;
                                    case 4:
                                    try {
                                        String sl = String.valueOf(cell);
                                        float sol = Float.valueOf(sl);
                                        sp.setSl((int) sol);
                                    } catch (NumberFormatException ex) {
                                        MsgBox.alert(this, "Số lượng phải là số");
                                        System.out.println(ex);
                                        ktrDinhdang = "false";
                                    }
                                    break;
                                    case 5:
                                    try {
                                        String giaNhap = String.valueOf(cell);
                                        float gianhap = Float.valueOf(giaNhap);
                                        sp.setGiaNhap(gianhap);
                                    } catch (NumberFormatException ex) {
                                        MsgBox.alert(this, "Giá nhập phải là số");
                                        ktrDinhdang = "false";
                                    }
                                    break;
                                    case 6:
                                    try {
                                        String giaBan = String.valueOf(cell);
                                        float giaban = Float.valueOf(giaBan);
                                        sp.setGiaBan(giaban);
                                    } catch (NumberFormatException ex) {
                                        MsgBox.alert(this, "Giá bán phải là số");
                                        ktrDinhdang = "false";
                                    }
                                    break;
                                    case 7:
                                        sp.setNgayNhap(dayNow);
                                        sp.setHinh(ktrMaSp);
                                        sp.setMoTa(String.valueOf(cell));
                                        break;
                                }
                            }
                            SanPhamEntity checksp = SanPham.selectById(ktrMaSp);
                            if (checksp != null) {
                                MsgBox.alert(this, "Vui lòng kiểm tra mã sản phẩm: " + ktrMaSp + " trong file excel vì mã này đã tồn tại!");
                            } else {
                                if (ktrDinhdang.equals("true")) {
                                    SanPham.insert(sp);
                                    listSPT();
                                    fillTableSanPham();
                                    fillComboxHang();
                                    fillComboxLoai();
                                }
                            }
                        }
                        i++;
                    }
                    fis.close();
                    MsgBox.alert(this, "Đọc file excel thành công");
                }
            } catch (Exception ex) {
                MsgBox.alert(this, "Lỗi đọc file");
                checkImportXlxs = false;
                //System.out.println(ex);
            }
            if (checkImportXlxs) {
                MsgBox.alert(this, "Bạn đã thêm file excel thành công\nHãy tiếp tục mở thư mục chứa các hình ảnh!");
                Folder f = new Folder();
                JButton open = new JButton();
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new java.io.File(""));
                fileChooser.setDialogTitle("Chọn đường dẫn lưu mã sản phẩm!");
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                if (fileChooser.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
                    String path = fileChooser.getSelectedFile().getAbsolutePath();
                    //System.out.println(fileChooser.getSelectedFile().getAbsolutePath());
                    f.copyAllFileInFolder(path, "src//com//images");
                    MsgBox.alert(this, "Thêm ảnh thành công!");
                }
            }
        } else if (code == 1) {
            try {
                String ktrMaSp = "", ktrDinhdang = "true";
                JFileChooser fc = new JFileChooser();
                fc.showOpenDialog(null);
                File f = fc.getSelectedFile();
                if (f == null) {
                    return;
                }
                String path = f.getAbsoluteFile().toString();
                if (!path.contains(".xlsx")) {
                    MsgBox.alert(this, "Vui lòng chọn file Excel!");
                } else {
                    FileInputStream fis = new FileInputStream(f);
                    XSSFWorkbook wb = new XSSFWorkbook(fis);
                    XSSFSheet sheet = wb.getSheetAt(0);
                    Iterator<Row> rowIter = sheet.iterator();
                    SanPhamEntity sp = new SanPhamEntity();
                    int i = 0;
                    while (rowIter.hasNext()) {
                        Row row = rowIter.next();
                        Iterator<Cell> cellIter = row.iterator();
                        if (i != 0) {
                            ktrDinhdang = "true";
                            while (cellIter.hasNext()) {
                                Cell cell = cellIter.next();
                                switch (cell.getColumnIndex()) {
                                    case 0:
                                        sp.setMaSP(String.valueOf(cell).toUpperCase());
                                        ktrMaSp = String.valueOf(cell);
                                        if (ktrMaSp.equals("")) {
                                            ktrDinhdang = "false";
                                            MsgBox.alert(this, "Mã sản phẩm không được bỏ trống!");
                                        }
                                        Matcher matcher = Pattern.compile(TypeProduct_REGEX).matcher(ktrMaSp.toUpperCase());
                                        if (matcher.matches() == false) {
                                            MsgBox.alert(this, "Mã sản phẩm " + ktrMaSp + " không hợp lệ (VD: SP0001)!");
                                            ktrDinhdang = "false";
                                        }
                                        break;
                                    case 1:
                                        sp.setTenSP(String.valueOf(cell));
                                        if (String.valueOf(cell).equals("")) {
                                            ktrDinhdang = "false";
                                            MsgBox.alert(this, "Tên sản phẩm không được bỏ trống!");
                                        }
                                        break;
                                    case 2:
                                        char kyTu;
                                        int countSpace = 0;
                                        String loaihang = String.valueOf(cell);
                                        if (loaihang.equals("")) {
                                            ktrDinhdang = "false";
                                            MsgBox.alert(this, "Tên loại hàng không được bỏ trống!");
                                        } else {
                                            List<LoaiHangEntity> listlh = Loai.selectAll();
                                            String malhMoi = loaihang.substring(0, 2).toUpperCase();
                                            for (int j = 0; j < loaihang.length(); j++) {
                                                kyTu = loaihang.charAt(j);
                                                if (Character.isWhitespace(kyTu)) {
                                                    countSpace++;
                                                    char kyTu2 = loaihang.charAt(j + 1);
                                                    if (countSpace == 1) {
                                                        malhMoi = loaihang.substring(0, 1).toUpperCase();
                                                        malhMoi = malhMoi + String.valueOf(kyTu2).toUpperCase();
                                                    }
                                                    if (countSpace == 2) {
                                                        malhMoi = malhMoi + String.valueOf(kyTu2).toUpperCase();
                                                        countSpace = 0;
                                                    }
                                                }
                                            }
                                            LoaiHangEntity lhn = new LoaiHangEntity();
                                            String checkMalh = "Exist";
                                            for (LoaiHangEntity lh : listlh) {
                                                if (lh.getTenLH().equalsIgnoreCase(loaihang)) {
                                                    checkMalh = "Exist";
                                                    sp.setMaLH(lh.getMaLH());
                                                    break;
                                                } else {
                                                    checkMalh = "Not Exist";
                                                }
                                            }
                                            if (checkMalh.equals("Not Exist")) {
                                                lhn.setMaLH(malhMoi);
                                                lhn.setTenLH(loaihang.substring(0, 1).toUpperCase() + loaihang.substring(1).toLowerCase());
                                                Loai.insert(lhn);
                                                sp.setMaLH(malhMoi);
                                                listLoai();
                                                fillTableLoai();
                                                clearFormLoai();
                                            }
                                        }
                                        break;
                                    case 3:
                                        String hang = String.valueOf(cell);
                                        if (hang.equals("")) {
                                            ktrDinhdang = "false";
                                            MsgBox.alert(this, "Tên hãng không được bỏ trống!");
                                        } else {
                                            List<HangEntity> listh = Hang.selectAll();
                                            String mahangMoi = hang.substring(0, 2).toUpperCase();
                                            char kyTuHang;
                                            countSpace = 0;
                                            for (int j = 0; j < hang.length(); j++) {
                                                kyTuHang = hang.charAt(j);
                                                if (Character.isWhitespace(kyTuHang)) {
                                                    countSpace++;
                                                    char kyTuHang2 = hang.charAt(j + 1);
                                                    if (countSpace == 1) {
                                                        mahangMoi = hang.substring(0, 1).toUpperCase();
                                                        mahangMoi = mahangMoi + String.valueOf(kyTuHang2).toUpperCase();
                                                    }
                                                    if (countSpace == 2) {
                                                        mahangMoi = mahangMoi + String.valueOf(kyTuHang2).toUpperCase();
                                                        countSpace = 0;
                                                    }
                                                }
                                            }
                                            HangEntity hn = new HangEntity();
                                            String checkMah = "Exist";
                                            for (HangEntity h : listh) {
                                                if (h.getTenHang().equalsIgnoreCase(hang)) {
                                                    checkMah = "Exist";
                                                    sp.setMaHang(h.getMaHang());
                                                    break;
                                                } else {
                                                    checkMah = "Not Exist";
                                                }
                                            }
                                            if (checkMah.equals("Not Exist")) {
                                                hn.setMaHang(mahangMoi);
                                                hn.setTenHang(hang.toUpperCase());
                                                Hang.insert(hn);
                                                sp.setMaHang(mahangMoi);
                                                listHang();
                                                fillTableHang();
                                                clearFormHang();
                                            }
                                        }
                                        break;
                                    case 4:
                                    try {
                                        String sl = String.valueOf(cell);
                                        float sol = Float.valueOf(sl);
                                        sp.setSl((int) sol);
                                    } catch (NumberFormatException ex) {
                                        MsgBox.alert(this, "Số lượng phải là số");
                                        System.out.println(ex);
                                        ktrDinhdang = "false";
                                    }
                                    break;
                                    case 5:
                                    try {
                                        String giaNhap = String.valueOf(cell);
                                        float gianhap = Float.valueOf(giaNhap);
                                        sp.setGiaNhap(gianhap);
                                    } catch (NumberFormatException ex) {
                                        MsgBox.alert(this, "Giá nhập phải là số");
                                        ktrDinhdang = "false";
                                    }
                                    break;
                                    case 6:
                                    try {
                                        String giaBan = String.valueOf(cell);
                                        float giaban = Float.valueOf(giaBan);
                                        sp.setGiaBan(giaban);
                                    } catch (NumberFormatException ex) {
                                        MsgBox.alert(this, "Giá bán phải là số");
                                        ktrDinhdang = "false";
                                    }
                                    break;
                                    case 7:
                                        sp.setNgayNhap(dayNow);
                                        sp.setHinh("logokhongvien-01");
                                        sp.setMoTa(String.valueOf(cell));
                                        break;
                                }
                            }
                            SanPhamEntity checksp = SanPham.selectById(ktrMaSp);
                            if (checksp != null) {
                                MsgBox.alert(this, "Vui lòng kiểm tra mã sản phẩm: " + ktrMaSp + " trong file excel vì mã này đã tồn tại!");
                            } else {
                                if (ktrDinhdang.equals("true")) {
                                    SanPham.insert(sp);
                                    listSPT();
                                    fillTableSanPham();
                                    fillComboxHang();
                                    fillComboxLoai();
                                }
                            }
                        }
                        i++;
                    }
                    fis.close();
                    MsgBox.alert(this, "Đọc file excel thành công");
                }
            } catch (Exception ex) {
                MsgBox.alert(this, "Lỗi đọc file");
                //System.out.println(ex);
            }

        } else {
            return;
        }
        //import file

    }

//Xuất sản phẩm bán chạy ra excel
    public void ExportFileExcelThongKeSP() {
        try {
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("Danh sách sản phẩm");
            XSSFRow row = null;
            Cell cell = null;
            row = sheet.createRow(0);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("MÃ SẢN PHẨM");

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("TÊN SẢN PHẨM");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("LƯỢT BÁN");
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
                System.out.println(ex);
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //Xuất sản phẩm bán chạy ra excel
    public void FormatFileExcel() {
        try {
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("Danh sách sản phẩm");
            XSSFRow row = null;
            Cell cell = null;
            row = sheet.createRow(0);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("MÃ SẢN PHẨM");

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("TÊN SẢN PHẨM");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("LOẠI HÀNG");

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("HÃNG");

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("SỐ LƯỢNG");

            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("GIÁ NHẬP");

            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("GIÁ BÁN");

            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("MÔ TẢ");

            JFileChooser fc = new JFileChooser();
            fc.showOpenDialog(null);
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
                MsgBox.alert(this, "Tải về thành công!");
            } catch (Exception ex) {
                System.out.println("lỗi xuất file " + ex);
                System.out.println(ex);
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

//Vẽ biểu đồ tròn thống kê sản phẩm bán chạy trong năm
    public void setDataPieChart(JPanel jpPie) {
        Date now = new Date();
        SimpleDateFormat formater = new SimpleDateFormat();
        formater.applyPattern("yyyy");
        String thisYear = formater.format(now);
        List<Object[]> listTKSP_L = TKdao.getSPBanChayTL(thisYear);
        DefaultPieDataset data = new DefaultPieDataset();
        float tongsl = 0;
        for (Object[] row : listTKSP_L) {
            tongsl += (int) row[1];
        }
        for (Object[] row : listTKSP_L) {
            data.setValue((String) (row[0]), (((int) row[1] / tongsl) * 100));
        }
        JFreeChart Chart = ChartFactory.createPieChart("Tỷ lệ phần trăm loại sản phẩm bán được", data, true, true, true);
        ChartPanel chartPanel = new ChartPanel(Chart);
        chartPanel.setPreferredSize(new Dimension(jpPie.getWidth(), jpPie.getHeight()));

        jpPie.removeAll();
        jpPie.setLayout(new CardLayout());
        jpPie.add(chartPanel);

    }

//Vẽ biểu đồ cột thống kê doanh thu
    public void setDataBarChart(JPanel jpBar) {
        String choice = (String) cboChoice.getSelectedItem();
        List<Object[]> listTKDT_M = null;
        if (choice.equals("Năm")) {
            listTKDT_M = TKdao.getDoanhthuYearBarChart();
        }
        if (choice.equals("Tháng")) {
            Date now = new Date();
            SimpleDateFormat formater = new SimpleDateFormat();
            formater.applyPattern("yyyy");
            String thisYear = formater.format(now);
            listTKDT_M = TKdao.getDoanhthuMonthBarChart(thisYear);
        }
        DefaultCategoryDataset data = new DefaultCategoryDataset();
        for (Object[] row : listTKDT_M) {
            String tg = String.valueOf(row[0]);
            double tempd = (double) row[1];
            data.setValue((double) tempd, choice, tg);
        }
        JFreeChart barChart = ChartFactory.createBarChart("Biểu đồ doanh thu", choice, "Tổng tiền", data, PlotOrientation.VERTICAL, false, false, false);
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpBar.getWidth(), jpBar.getHeight()));

        jpBar.removeAll();
        jpBar.setLayout(new CardLayout());
        jpBar.add(chartPanel);
    }

    boolean threadClock = true;

//Hiển thị giờ
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
                        SimpleDateFormat formater2 = new SimpleDateFormat();
                        formater2.applyPattern("dd-MM-yyyy");
                        String dayt = formater2.format(now);
                        lblDay.setText(String.valueOf(dayt));
                        Thread.sleep(1000);

                    } catch (InterruptedException e) {
                        break;
                    }

                }
            }
        }).start();
    }
//Đi tới trang web hướng dẫn

    public void openHuongDan() {

        try {
            Desktop.getDesktop().browse(new File("src\\com\\web\\index.html").toURI());
        } catch (IOException ex) {
            MsgBox.alert(this, "Không tìm thấy file huớng dẫn");
        }
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
        dateChooser1 = new com.swing.datechooser.DateChooser();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        dateChooser2 = new com.swing.datechooser.DateChooser();
        rdoGMode = new javax.swing.ButtonGroup();
        pnMain = new javax.swing.JPanel();
        loadingMain = new com.frame.LoadingMain();
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
        pnTitle = new javax.swing.JPanel();
        jplState = new javax.swing.JPanel();
        jlbState = new javax.swing.JLabel();
        jplLose = new javax.swing.JPanel();
        jlbClose = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        pnMenubar = new javax.swing.JPanel();
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
        HoaDonHr1 = new javax.swing.JLabel();
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
        cardMenubarCaiDat = new javax.swing.JPanel();
        CaiDatTittle1 = new javax.swing.JLabel();
        CaiDatTittle2 = new javax.swing.JLabel();
        CaiDatHr1 = new javax.swing.JLabel();
        CaiDatHr2 = new javax.swing.JLabel();
        jplContainer = new javax.swing.JPanel();
        cardTrangChu = new javax.swing.JPanel();
        cardTrangChuTongQuan = new com.swing.PanelRound();
        panelRound1 = new com.swing.PanelRound();
        panelRound8 = new com.swing.PanelRound();
        lblTime = new javax.swing.JLabel();
        lblDay = new javax.swing.JLabel();
        panelRound5 = new com.swing.PanelRound();
        jLabel7 = new javax.swing.JLabel();
        lbltenNV = new javax.swing.JLabel();
        lblChucVu = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        panelRound6 = new com.swing.PanelRound();
        lblDay1 = new javax.swing.JLabel();
        lblDay2 = new javax.swing.JLabel();
        lblDay3 = new javax.swing.JLabel();
        lblDay4 = new javax.swing.JLabel();
        jSeparator23 = new javax.swing.JSeparator();
        panelRound7 = new com.swing.PanelRound();
        jLabel12 = new javax.swing.JLabel();
        panelRound4 = new com.swing.PanelRound();
        jLabel8 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lblNV1 = new javax.swing.JLabel();
        lblNV2 = new javax.swing.JLabel();
        lblNV3 = new javax.swing.JLabel();
        jSeparator18 = new javax.swing.JSeparator();
        panelRound2 = new com.swing.PanelRound();
        banner1 = new javax.swing.JLabel();
        banner2 = new javax.swing.JLabel();
        banner3 = new javax.swing.JLabel();
        panelRound3 = new com.swing.PanelRound();
        lblDay5 = new javax.swing.JLabel();
        lblAnhSpBanChay = new javax.swing.JLabel();
        cardThongKeDoanhThu = new com.swing.PanelRound();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblDoanhThu = new javax.swing.JTable();
        jLabel43 = new javax.swing.JLabel();
        cboYearDT = new com.swing.Combobox();
        jLabel44 = new javax.swing.JLabel();
        cboDayDT = new com.swing.Combobox();
        cboMonthDT = new com.swing.Combobox();
        jLabel45 = new javax.swing.JLabel();
        jPanel33 = new javax.swing.JPanel();
        jLabel98 = new javax.swing.JLabel();
        pnlChartDT = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        cboChoice = new com.swing.Combobox();
        cardThongKeSanPham = new com.swing.PanelRound();
        jLabel46 = new javax.swing.JLabel();
        cboYearSP = new com.swing.Combobox();
        jLabel47 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        cboDaySP = new com.swing.Combobox();
        cboMonthSP = new com.swing.Combobox();
        pnlChart = new javax.swing.JPanel();
        btnExport = new com.swing.Button();
        jScrollPane14 = new javax.swing.JScrollPane();
        tblSPBanChay = new javax.swing.JTable();
        jPanel32 = new javax.swing.JPanel();
        jLabel95 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        cboSort = new com.swing.Combobox();
        cardTaiKhoanChucVu = new com.swing.PanelRound();
        jLabel90 = new javax.swing.JLabel();
        txttenCV = new javax.swing.JTextField();
        lblMaChucVu = new javax.swing.JLabel();
        txtmaCV = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        btnLamMoiCV = new com.swing.Button();
        btnCapNhatCV = new com.swing.Button();
        btnXoaCV = new com.swing.Button();
        jPanel20 = new javax.swing.JPanel();
        cboCV = new com.swing.Combobox();
        jLabel93 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        btnazCV = new com.swing.EditButton();
        btnzaCV = new com.swing.EditButton();
        jLabel94 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        btnThemCV = new com.swing.Button();
        pnMarginTitleChucVu = new javax.swing.JPanel();
        btnNextCV = new com.swing.Button();
        btnFirstCV = new com.swing.Button();
        btnPrevCV = new com.swing.Button();
        btnLastCV = new com.swing.Button();
        lblTimKiemCV = new javax.swing.JLabel();
        txtTimCV = new javax.swing.JTextField();
        lblTitleTimKiemChucVu = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        scpChucVu = new javax.swing.JScrollPane();
        tblChucVu = new javax.swing.JTable();
        lblrecordCV = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        lblTenCV = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtMoTaCV = new javax.swing.JTextArea();
        cardTaiKhoanNhanVien = new com.swing.PanelRound();
        pnMarginTKNV = new javax.swing.JPanel();
        btnNextNV = new com.swing.Button();
        btnPrevNV = new com.swing.Button();
        btnLastNV = new com.swing.Button();
        lblTimKiemNV = new javax.swing.JLabel();
        txtTimKiemNV = new javax.swing.JTextField();
        lblTitleTimKiemNV = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        btnFirstNV = new com.swing.Button();
        scpNhanVien = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        jLabel85 = new javax.swing.JLabel();
        lblRecordNV = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        btnLamMoiNV = new com.swing.Button();
        btnCapNhatNV = new com.swing.Button();
        btnXoaNV = new com.swing.Button();
        jPanel16 = new javax.swing.JPanel();
        cboSortNV = new com.swing.Combobox();
        jLabel30 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        btnazNV = new com.swing.EditButton();
        btnzanv = new com.swing.EditButton();
        jLabel32 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        btnThemNV = new com.swing.Button();
        lblHovaTenNV = new javax.swing.JLabel();
        txtHoTenNV = new javax.swing.JTextField();
        lblTenDN = new javax.swing.JLabel();
        txtTenDN = new javax.swing.JTextField();
        txtEmailNV = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        lblSDTNV = new javax.swing.JLabel();
        txtSDTNV = new javax.swing.JTextField();
        lblTrangThai = new javax.swing.JLabel();
        txtDiaChiNV = new javax.swing.JTextField();
        txtNgaySinhNV = new javax.swing.JTextField();
        lblNgaySinhNV = new javax.swing.JLabel();
        lblMatKhau = new javax.swing.JLabel();
        txtMatKhauNV = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        rdoNu = new javax.swing.JRadioButton();
        rdoNam = new javax.swing.JRadioButton();
        cboVaiTro = new com.swing.Combobox();
        lblDiaChiNV = new javax.swing.JLabel();
        sbtnTrangThaiNV = new com.hicode.switchbutton.SwitchButton();
        txtTrangThaiNV = new javax.swing.JLabel();
        lblEmailNV = new javax.swing.JLabel();
        cardSanPham = new com.swing.PanelRound();
        cardHangSanXuat1 = new com.swing.PanelRound();
        cardLoai2 = new com.swing.PanelRound();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        pnBangTTSP = new javax.swing.JPanel();
        btnNextSP = new com.swing.Button();
        btnFirstSP = new com.swing.Button();
        btnPrevSP = new com.swing.Button();
        btnLastSP = new com.swing.Button();
        jLabel79 = new javax.swing.JLabel();
        lblTimKiemTempSP = new javax.swing.JLabel();
        txtTimKiemSP = new javax.swing.JTextField();
        cboTimKiemSP = new com.swing.Combobox();
        cboMaLoai = new com.swing.Combobox();
        cboMaHang = new com.swing.Combobox();
        lblAnh = new javax.swing.JLabel();
        lblBanGhiSP = new javax.swing.JLabel();
        lblrecordSP = new javax.swing.JLabel();
        pnSP = new javax.swing.JPanel();
        btnLamMoiSP = new com.swing.Button();
        btnCapNhatSP = new com.swing.Button();
        btnXoaSP = new com.swing.Button();
        btnThemSP = new com.swing.Button();
        cboSXSP = new com.swing.Combobox();
        lblTHSXSP = new javax.swing.JLabel();
        pnBtnSapxepSP = new javax.swing.JPanel();
        btnazsp = new com.swing.EditButton();
        btnzasp = new com.swing.EditButton();
        jLabel72 = new javax.swing.JLabel();
        jSeparator33 = new javax.swing.JSeparator();
        jSeparator34 = new javax.swing.JSeparator();
        jSeparator35 = new javax.swing.JSeparator();
        txtTenSP = new javax.swing.JTextField();
        lblTenSP = new javax.swing.JLabel();
        lblMaSPTemp = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        lblTenHangSP = new javax.swing.JLabel();
        lblNgayNhaptemp = new javax.swing.JLabel();
        txtNgayNhapSP = new javax.swing.JTextField();
        lblNgayNhapSP = new javax.swing.JLabel();
        lblVND1 = new javax.swing.JLabel();
        lblGiaNhapSP = new javax.swing.JLabel();
        lblVND2 = new javax.swing.JLabel();
        txtGiaBanSP = new javax.swing.JTextField();
        lblTenLoaiSP = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        txtMoTaSP = new javax.swing.JTextArea();
        lblMoTaSP = new javax.swing.JLabel();
        lblMaSP = new javax.swing.JLabel();
        lblGiaBanSP = new javax.swing.JLabel();
        lblImportFileExcel = new com.swing.Button();
        lblExportInfor = new com.swing.Button();
        lblSoLuongSP = new javax.swing.JLabel();
        lblFormat = new javax.swing.JLabel();
        txtSoLuongSP = new javax.swing.JTextField();
        btnFormatFileExcel = new com.swing.Button();
        txtGiaNhapSP = new javax.swing.JTextField();
        txtGiaNhapSP3 = new javax.swing.JTextField();
        txtGiaBanSP1 = new javax.swing.JTextField();
        cardHangSanXuat = new com.swing.PanelRound();
        cardLoai1 = new com.swing.PanelRound();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblHang = new javax.swing.JTable();
        pnBangTTHang = new javax.swing.JPanel();
        btnNextHang = new com.swing.Button();
        btnFirstHang = new com.swing.Button();
        btnPrevHang = new com.swing.Button();
        btnLastHang = new com.swing.Button();
        jLabel74 = new javax.swing.JLabel();
        lblTimKiemHang = new javax.swing.JLabel();
        lblHangTemp = new javax.swing.JLabel();
        txtTimKiemHang = new javax.swing.JTextField();
        pnHang = new javax.swing.JPanel();
        btnLamMoiHang = new com.swing.Button();
        btnCapNhatHang = new com.swing.Button();
        btnXoaHang = new com.swing.Button();
        pnSXHang = new javax.swing.JPanel();
        cboHang = new com.swing.Combobox();
        lblTHSXHang = new javax.swing.JLabel();
        pnbtnSXHang = new javax.swing.JPanel();
        btnazHang = new com.swing.EditButton();
        btnzaHang = new com.swing.EditButton();
        jLabel103 = new javax.swing.JLabel();
        jSeparator15 = new javax.swing.JSeparator();
        jSeparator16 = new javax.swing.JSeparator();
        jSeparator17 = new javax.swing.JSeparator();
        btnThemHang = new com.swing.Button();
        lblBanGhiHang = new javax.swing.JLabel();
        lblrecordHang = new javax.swing.JLabel();
        lblMaHang = new javax.swing.JLabel();
        txtTenHang = new javax.swing.JTextField();
        lblTenHang = new javax.swing.JLabel();
        lblMaHangTemp = new javax.swing.JLabel();
        txtMaHang = new javax.swing.JTextField();
        cardLoai = new com.swing.PanelRound();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblLoaiHang = new javax.swing.JTable();
        pnBangTTLoai = new javax.swing.JPanel();
        btnNextLoai = new com.swing.Button();
        btnFirstLoai = new com.swing.Button();
        btnPrevLoai = new com.swing.Button();
        btnLastLoai = new com.swing.Button();
        jLabel69 = new javax.swing.JLabel();
        lblLoaiTemp = new javax.swing.JLabel();
        txtTimKiemLoai = new javax.swing.JTextField();
        jLabel105 = new javax.swing.JLabel();
        pnLoai = new javax.swing.JPanel();
        btnLamMoiLoai = new com.swing.Button();
        btnCapNhatLoai = new com.swing.Button();
        btnXoaLoai = new com.swing.Button();
        pnSXLoai = new javax.swing.JPanel();
        cboLoai = new com.swing.Combobox();
        lblTHSXLoai = new javax.swing.JLabel();
        pnbtnSXLoai = new javax.swing.JPanel();
        btnazLoai = new com.swing.EditButton();
        btnzaLoai = new com.swing.EditButton();
        jLabel101 = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        jSeparator14 = new javax.swing.JSeparator();
        btnThemLoai = new com.swing.Button();
        lblBanGhiLoai = new javax.swing.JLabel();
        lblrecordLoai = new javax.swing.JLabel();
        lblMaLoaiTemp = new javax.swing.JLabel();
        txtMaLoai = new javax.swing.JTextField();
        lblMaLoai = new javax.swing.JLabel();
        lblTenLoai = new javax.swing.JLabel();
        txtTenLoai = new javax.swing.JTextField();
        cardGioHang = new com.swing.PanelRound();
        pnGioHangSanPham = new javax.swing.JPanel();
        cardHoaDonSanPham = new javax.swing.JPanel();
        cardHoaDonQR = new javax.swing.JPanel();
        pnQR = new javax.swing.JPanel();
        txtFindNameProductCart = new com.swing.TextField();
        jLabel77 = new javax.swing.JLabel();
        btnXNKH = new com.swing.Button();
        txtSdtKH = new javax.swing.JTextField();
        btnXacNhanDonHang = new com.swing.Button();
        jLabel9 = new javax.swing.JLabel();
        spnSL = new spinner.Spinner();
        btnxoaGioHang = new com.swing.Button();
        jSeparator19 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        switchButton1 = new com.hicode.switchbutton.SwitchButton();
        jScrollPane13 = new javax.swing.JScrollPane();
        tblCart = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        txtTongtiensp = new javax.swing.JTextField();
        pnPupopMenu = new javax.swing.JPanel();
        lblNameCustomer = new javax.swing.JLabel();
        cardHoaDon = new com.swing.PanelRound();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jPanel28 = new javax.swing.JPanel();
        btnXoahd = new com.swing.Button();
        jPanel29 = new javax.swing.JPanel();
        comboboxHD = new com.swing.Combobox();
        jLabel48 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        btnazhd = new com.swing.EditButton();
        btnzahd = new com.swing.EditButton();
        jLabel49 = new javax.swing.JLabel();
        jSeparator20 = new javax.swing.JSeparator();
        jSeparator21 = new javax.swing.JSeparator();
        jSeparator22 = new javax.swing.JSeparator();
        btnIn = new com.swing.Button();
        btnIn1 = new com.swing.Button();
        pnMarginHoaDon = new javax.swing.JPanel();
        btnNexthd = new com.swing.Button();
        btnFirsthd = new com.swing.Button();
        btnPrevhd = new com.swing.Button();
        btnLasthd = new com.swing.Button();
        jLabel81 = new javax.swing.JLabel();
        lblTimKiemHD = new javax.swing.JLabel();
        lblTittleFindHoaDon = new javax.swing.JLabel();
        txtTimKiemhd = new javax.swing.JTextField();
        jLabel104 = new javax.swing.JLabel();
        lblrecordhd = new javax.swing.JLabel();
        jLabel117 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        txtTENNV = new javax.swing.JTextField();
        txtTENKH = new javax.swing.JTextField();
        txtMAKH = new javax.swing.JTextField();
        jLabel120 = new javax.swing.JLabel();
        txtTienNhan = new javax.swing.JTextField();
        jLabel135 = new javax.swing.JLabel();
        txtPhanTramGG = new javax.swing.JTextField();
        jLabel129 = new javax.swing.JLabel();
        jLabel121 = new javax.swing.JLabel();
        txtTichDiem = new javax.swing.JTextField();
        btnSuDungDiem = new com.swing.Button();
        jLabel65 = new javax.swing.JLabel();
        lblTichDiemHD = new javax.swing.JLabel();
        cardChiTietHoaDon = new com.swing.PanelRound();
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
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        cardKhachHang = new com.swing.PanelRound();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        lblTichDiem = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        pnMarginKhachHangTitle = new javax.swing.JPanel();
        btnNextKH = new com.swing.Button();
        btnFirstKH = new com.swing.Button();
        btnPrevKH = new com.swing.Button();
        btnLastKH = new com.swing.Button();
        lblTimKiemKH = new javax.swing.JLabel();
        txtTimKiemKH = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        cbotimKiemKH = new com.swing.Combobox();
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
        lblHovaTenKH = new javax.swing.JLabel();
        txthoTen = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        lblSDTKH = new javax.swing.JLabel();
        lblMaKH = new javax.swing.JLabel();
        txtmaKH = new javax.swing.JTextField();
        txtdiaChi = new javax.swing.JTextField();
        lblDiaChiKH = new javax.swing.JLabel();
        cardCaiDatDuongDan = new com.swing.PanelRound();
        pnCaiDatDuongDan = new com.swing.PanelRound();
        lblTitleSaveQRProduct = new javax.swing.JLabel();
        txtSaveQrProduct = new javax.swing.JTextField();
        lblOpenQRProduct = new javax.swing.JLabel();
        lblOpenBannerImg = new javax.swing.JLabel();
        txtBanerPathImg1 = new javax.swing.JTextField();
        lblOpenBannerImg1 = new javax.swing.JLabel();
        lblOpenBannerImg2 = new javax.swing.JLabel();
        txtBanerPathImg2 = new javax.swing.JTextField();
        txtBanerPathImg3 = new javax.swing.JTextField();
        lblOpenBannerImg3 = new javax.swing.JLabel();
        lblCDDD1 = new javax.swing.JLabel();
        lblCDDD2 = new javax.swing.JLabel();
        lblCDDD3 = new javax.swing.JLabel();
        btnResetSaveBannerImg = new com.swing.Button();
        btnResetSaveQRProduct = new com.swing.Button();
        cardCaiDatGiaoDien = new com.swing.PanelRound();
        pnSetMode = new com.swing.PanelRound();
        lblDarkmode = new javax.swing.JLabel();
        lblLightmode = new javax.swing.JLabel();
        darkMode1 = new com.frame.DarkMode();
        lightMode1 = new com.frame.LightMode();
        rdoDarkmode = new javax.swing.JRadioButton();
        rdoLightmode = new javax.swing.JRadioButton();
        pnReviewMode = new javax.swing.JPanel();
        pbMarginReviewMode = new javax.swing.JPanel();
        pnReviewLightMode = new com.frame.pnReviewLightMode();
        pnReviewDarkMode = new com.frame.pnReviewDarkMode();

        dateChooser1.setForeground(new java.awt.Color(0, 153, 51));
        dateChooser1.setTextRefernce(txtNgayNhapSP);

        dateChooser2.setForeground(new java.awt.Color(0, 153, 51));
        dateChooser2.setTextRefernce(txtNgaySinhNV);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        pnMain.setBackground(new java.awt.Color(255, 255, 255));
        pnMain.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pnMain.add(loadingMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1220, -1));

        pnMenu.setBackground(new java.awt.Color(255, 255, 255));
        pnMenu.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(0, 0, 0)));

        btnTrangChu.setForeground(new java.awt.Color(0, 153, 51));
        btnTrangChu.setText("Cửa sổ chính");
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

        pnMain.add(pnMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 0, 670));

        opacity.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/opacity-main.png"))); // NOI18N
        opacity.setText("jLabel28");
        opacity.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                opacityMousePressed(evt);
            }
        });
        pnMain.add(opacity, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 0, 0));

        pnTitle.setBackground(new java.awt.Color(154, 213, 224));
        pnTitle.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        pnTitle.add(jplState, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 0, -1, -1));

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

        pnTitle.add(jplLose, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 0, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DAVISY");
        pnTitle.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 30));

        pnMain.add(pnTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1220, 30));

        pnMenubar.setBackground(new java.awt.Color(255, 255, 255));
        pnMenubar.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        pnMenubar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/menu_first.png"))); // NOI18N
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });
        pnMenubar.add(btnMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(-1, 0, 90, 52));

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

        HoaDonHr1.setBackground(new java.awt.Color(0, 153, 0));
        HoaDonHr1.setOpaque(true);
        cardMenubarHoaDon.add(HoaDonHr1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 43, 110, 5));

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

        cardMenubarCaiDat.setBackground(new java.awt.Color(255, 255, 255));
        cardMenubarCaiDat.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CaiDatTittle1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CaiDatTittle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CaiDatTittle1.setText("Giao diện");
        CaiDatTittle1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                CaiDatTittle1MousePressed(evt);
            }
        });
        cardMenubarCaiDat.add(CaiDatTittle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 110, 30));

        CaiDatTittle2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CaiDatTittle2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CaiDatTittle2.setText("Đường dẫn ảnh");
        CaiDatTittle2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                CaiDatTittle2MousePressed(evt);
            }
        });
        cardMenubarCaiDat.add(CaiDatTittle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 160, 30));

        CaiDatHr1.setBackground(new java.awt.Color(0, 153, 0));
        CaiDatHr1.setOpaque(true);
        cardMenubarCaiDat.add(CaiDatHr1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 43, 110, 5));

        CaiDatHr2.setBackground(new java.awt.Color(0, 153, 0));
        CaiDatHr2.setOpaque(true);
        cardMenubarCaiDat.add(CaiDatHr2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 43, 160, 5));

        cardMenubar.add(cardMenubarCaiDat, "card2");

        pnMenubar.add(cardMenubar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 1140, 50));

        pnMain.add(pnMenubar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1220, 50));

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
        panelRound5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelRound5MouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/id-card.png"))); // NOI18N
        jLabel7.setText("Danh thiếp");

        lbltenNV.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbltenNV.setText("Trống");

        lblChucVu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblChucVu.setText("Chức vụ: ");

        javax.swing.GroupLayout panelRound5Layout = new javax.swing.GroupLayout(panelRound5);
        panelRound5.setLayout(panelRound5Layout);
        panelRound5Layout.setHorizontalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelRound5Layout.createSequentialGroup()
                .addGroup(panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound5Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblChucVu)
                            .addComponent(lbltenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelRound5Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel7)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound5Layout.setVerticalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound5Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbltenNV)
                .addGap(18, 18, 18)
                .addComponent(lblChucVu)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        cardTrangChuTongQuan.add(panelRound5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, 230, 140));

        panelRound6.setBackground(new java.awt.Color(204, 204, 255));
        panelRound6.setRoundBottomRight(50);
        panelRound6.setRoundTopLeft(50);
        panelRound6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDay1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblDay1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDay1.setText("Tổng đài hỗ trợ");
        panelRound6.add(lblDay1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 166, -1));

        lblDay2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDay2.setText("Kỹ thuật: 1800.1763");
        panelRound6.add(lblDay2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 171, -1));

        lblDay3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDay3.setText("Tư vấn: 1800.1062");
        panelRound6.add(lblDay3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        lblDay4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDay4.setText("Bảo hành: 1800.1064");
        panelRound6.add(lblDay4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));
        panelRound6.add(jSeparator23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 42, 230, 10));

        cardTrangChuTongQuan.add(panelRound6, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 70, 230, 140));

        panelRound7.setBackground(new java.awt.Color(224, 223, 223));
        panelRound7.setRoundBottomLeft(30);
        panelRound7.setRoundBottomRight(30);
        panelRound7.setRoundTopLeft(30);
        panelRound7.setRoundTopRight(30);
        panelRound7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/visits (1).png"))); // NOI18N
        panelRound7.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 40, 40));

        panelRound4.setBackground(new java.awt.Color(204, 153, 255));
        panelRound4.setRoundBottomLeft(20);
        panelRound4.setRoundBottomRight(20);
        panelRound4.setRoundTopLeft(20);
        panelRound4.setRoundTopRight(20);
        panelRound4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("DAVISY");
        panelRound4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 190, 50));

        panelRound7.add(panelRound4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 190, 130));

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 51, 51));
        jLabel17.setText("Thông tin");
        panelRound7.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 140, 40));

        jLabel21.setBackground(new java.awt.Color(224, 223, 223));
        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/cashier.png"))); // NOI18N
        jLabel21.setText("Nhân viên tiêu biểu");
        jLabel21.setOpaque(true);
        panelRound7.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 190, 40));

        lblNV1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblNV1.setText("jLabel36");
        panelRound7.add(lblNV1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 210, 20));

        lblNV2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblNV2.setText("jLabel36");
        panelRound7.add(lblNV2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 210, -1));

        lblNV3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblNV3.setText("jLabel36");
        panelRound7.add(lblNV3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 210, 20));
        panelRound7.add(jSeparator18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 42, 230, 30));

        cardTrangChuTongQuan.add(panelRound7, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 70, 230, 440));

        panelRound2.setBackground(new java.awt.Color(255, 204, 255));
        panelRound2.setRoundBottomLeft(30);
        panelRound2.setRoundBottomRight(30);
        panelRound2.setRoundTopLeft(30);
        panelRound2.setRoundTopRight(30);
        panelRound2.setLayout(new java.awt.CardLayout());

        banner1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/asset/slide-login/bn1.jpeg"))); // NOI18N
        banner1.setPreferredSize(new java.awt.Dimension(511, 240));
        panelRound2.add(banner1, "card2");

        banner2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/asset/slide-login/bn2.jpg"))); // NOI18N
        banner2.setPreferredSize(new java.awt.Dimension(511, 240));
        panelRound2.add(banner2, "card4");

        banner3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/asset/slide-login/bn3.jpg"))); // NOI18N
        banner3.setPreferredSize(new java.awt.Dimension(511, 240));
        panelRound2.add(banner3, "card3");

        cardTrangChuTongQuan.add(panelRound2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 510, 240));

        panelRound3.setBackground(new java.awt.Color(204, 255, 204));
        panelRound3.setRoundBottomLeft(30);
        panelRound3.setRoundBottomRight(30);
        panelRound3.setRoundTopLeft(30);
        panelRound3.setRoundTopRight(30);

        lblDay5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblDay5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDay5.setText("Sản phẩm bán chạy nhất");

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAnhSpBanChay, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelRound3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblDay5, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound3Layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addComponent(lblAnhSpBanChay, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelRound3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblDay5)
                    .addContainerGap(209, Short.MAX_VALUE)))
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

        cardThongKeDoanhThu.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 570, 450));

        jLabel43.setText("Theo");
        cardThongKeDoanhThu.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 30, 50, 30));

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
        cardThongKeDoanhThu.add(cboYearDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, 110, 40));

        jLabel44.setText("Tháng");
        cardThongKeDoanhThu.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, 50, 30));

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
        cardThongKeDoanhThu.add(cboMonthDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, 110, 40));

        jLabel45.setText("Ngày");
        cardThongKeDoanhThu.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 50, 30));

        jPanel33.setBackground(new java.awt.Color(204, 204, 255));

        jLabel98.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel98.setForeground(new java.awt.Color(204, 0, 51));
        jLabel98.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel98.setText("BẢNG THÔNG TIN DOANH THU");

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(112, Short.MAX_VALUE))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel98, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        cardThongKeDoanhThu.add(jPanel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 570, 50));

        pnlChartDT.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout pnlChartDTLayout = new javax.swing.GroupLayout(pnlChartDT);
        pnlChartDT.setLayout(pnlChartDTLayout);
        pnlChartDTLayout.setHorizontalGroup(
            pnlChartDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 570, Short.MAX_VALUE)
        );
        pnlChartDTLayout.setVerticalGroup(
            pnlChartDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        cardThongKeDoanhThu.add(pnlChartDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 70, 570, 500));

        jLabel50.setText("Năm");
        cardThongKeDoanhThu.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 30, 50, 30));

        cboChoice.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tháng", "Năm" }));
        cboChoice.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        cboChoice.setLabeText("");
        cboChoice.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboChoiceItemStateChanged(evt);
            }
        });
        cboChoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboChoiceActionPerformed(evt);
            }
        });
        cardThongKeDoanhThu.add(cboChoice, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 20, 110, 40));

        cardTrangChu.add(cardThongKeDoanhThu, "card3");

        cardThongKeSanPham.setBackground(new java.awt.Color(255, 255, 255));
        cardThongKeSanPham.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel46.setText("Năm");
        cardThongKeSanPham.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 30, 50, 30));

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
        cardThongKeSanPham.add(cboYearSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, 110, 40));

        jLabel47.setText("Tháng");
        cardThongKeSanPham.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, 50, 30));

        jLabel59.setText("Ngày");
        cardThongKeSanPham.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 50, 30));

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
        cardThongKeSanPham.add(cboDaySP, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 110, 40));

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
        cardThongKeSanPham.add(cboMonthSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, 110, 40));

        pnlChart.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout pnlChartLayout = new javax.swing.GroupLayout(pnlChart);
        pnlChart.setLayout(pnlChartLayout);
        pnlChartLayout.setHorizontalGroup(
            pnlChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 570, Short.MAX_VALUE)
        );
        pnlChartLayout.setVerticalGroup(
            pnlChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        cardThongKeSanPham.add(pnlChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 70, 570, 500));

        btnExport.setBackground(new java.awt.Color(204, 204, 255));
        btnExport.setText("Xuất file Excel");
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });
        cardThongKeSanPham.add(btnExport, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 30, 90, -1));

        jScrollPane14.setBorder(null);

        tblSPBanChay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Lượt bán"
            }
        ));
        tblSPBanChay.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane14.setViewportView(tblSPBanChay);

        cardThongKeSanPham.add(jScrollPane14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 570, 450));

        jPanel32.setBackground(new java.awt.Color(204, 204, 255));

        jLabel95.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(204, 0, 51));
        jLabel95.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel95.setText("BẢNG THÔNG TIN SẢN PHẨM BÁN CHẠY");

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(112, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel95, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        cardThongKeSanPham.add(jPanel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 570, 50));

        jLabel51.setText("Sắp xếp theo");
        cardThongKeSanPham.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 30, 90, 30));

        cboSort.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cao nhất", "Thấp nhất" }));
        cboSort.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        cboSort.setLabeText("");
        cboSort.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboSortItemStateChanged(evt);
            }
        });
        cboSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSortActionPerformed(evt);
            }
        });
        cardThongKeSanPham.add(cboSort, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 20, 120, 40));

        cardTrangChu.add(cardThongKeSanPham, "card4");

        cardTaiKhoanChucVu.setBackground(new java.awt.Color(255, 255, 255));
        cardTaiKhoanChucVu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel90.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(0, 0, 255));
        jLabel90.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel90.setText("Mô tả:");
        jLabel90.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardTaiKhoanChucVu.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, 110, 30));

        txttenCV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txttenCV.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardTaiKhoanChucVu.add(txttenCV, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 180, 30));

        lblMaChucVu.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lblMaChucVu.setForeground(new java.awt.Color(0, 0, 255));
        lblMaChucVu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMaChucVu.setText("Mã chức vụ:");
        lblMaChucVu.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardTaiKhoanChucVu.add(lblMaChucVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 110, 30));

        txtmaCV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtmaCV.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardTaiKhoanChucVu.add(txtmaCV, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, 180, 30));

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 255)));

        btnLamMoiCV.setBackground(new java.awt.Color(204, 204, 255));
        btnLamMoiCV.setText("Làm mới");
        btnLamMoiCV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiCVActionPerformed(evt);
            }
        });

        btnCapNhatCV.setBackground(new java.awt.Color(204, 204, 255));
        btnCapNhatCV.setText("Cập nhật");
        btnCapNhatCV.setEffectColor(new java.awt.Color(204, 255, 204));
        btnCapNhatCV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatCVActionPerformed(evt);
            }
        });

        btnXoaCV.setBackground(new java.awt.Color(255, 51, 102));
        btnXoaCV.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 0, 51)));
        btnXoaCV.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaCV.setText("Xóa");
        btnXoaCV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaCVActionPerformed(evt);
            }
        });

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));

        cboCV.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Mã chức vụ", "Tên chức vụ" }));
        cboCV.setLabeText("Sắp xếp theo");
        cboCV.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboCVItemStateChanged(evt);
            }
        });

        jLabel93.setText(" Tiến hành sắp xếp");
        jLabel93.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(0, 0, 0)));
        jPanel21.setLayout(null);

        btnazCV.setBackground(new java.awt.Color(102, 204, 255));
        btnazCV.setText("A -Z");
        btnazCV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnazCVActionPerformed(evt);
            }
        });
        jPanel21.add(btnazCV);
        btnazCV.setBounds(20, 20, 159, 30);

        btnzaCV.setBackground(new java.awt.Color(204, 153, 255));
        btnzaCV.setText("Z -A");
        btnzaCV.setMargin(new java.awt.Insets(5, 14, 14, 14));
        btnzaCV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnzaCVActionPerformed(evt);
            }
        });
        jPanel21.add(btnzaCV);
        btnzaCV.setBounds(20, 90, 159, 30);

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
                            .addComponent(cboCV, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(17, 17, 17))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                            .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap()))))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addComponent(cboCV, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnThemCV.setBackground(new java.awt.Color(204, 204, 255));
        btnThemCV.setText("Thêm");
        btnThemCV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemCVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXoaCV, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoiCV, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhatCV, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemCV, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(btnLamMoiCV, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThemCV, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCapNhatCV, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoaCV, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        cardTaiKhoanChucVu.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 20, 380, 240));

        pnMarginTitleChucVu.setBackground(new java.awt.Color(204, 204, 255));
        pnMarginTitleChucVu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnNextCV.setBackground(new java.awt.Color(204, 204, 255));
        btnNextCV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/arrow-right.png"))); // NOI18N
        btnNextCV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextCVActionPerformed(evt);
            }
        });
        pnMarginTitleChucVu.add(btnNextCV, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 11, -1, -1));

        btnFirstCV.setBackground(new java.awt.Color(204, 204, 255));
        btnFirstCV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/previous.png"))); // NOI18N
        btnFirstCV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstCVActionPerformed(evt);
            }
        });
        pnMarginTitleChucVu.add(btnFirstCV, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 11, -1, -1));

        btnPrevCV.setBackground(new java.awt.Color(204, 204, 255));
        btnPrevCV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/left-arrow.png"))); // NOI18N
        btnPrevCV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevCVActionPerformed(evt);
            }
        });
        pnMarginTitleChucVu.add(btnPrevCV, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 11, -1, -1));

        btnLastCV.setBackground(new java.awt.Color(204, 204, 255));
        btnLastCV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/next.png"))); // NOI18N
        btnLastCV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastCVActionPerformed(evt);
            }
        });
        pnMarginTitleChucVu.add(btnLastCV, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 11, -1, -1));

        lblTimKiemCV.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        lblTimKiemCV.setForeground(new java.awt.Color(153, 153, 153));
        lblTimKiemCV.setText("Nhân viên");
        lblTimKiemCV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTimKiemCVMouseClicked(evt);
            }
        });
        pnMarginTitleChucVu.add(lblTimKiemCV, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 20, 210, 30));

        txtTimCV.setBackground(new java.awt.Color(204, 204, 255));
        txtTimCV.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTimCV.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimCVCaretUpdate(evt);
            }
        });
        pnMarginTitleChucVu.add(txtTimCV, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 10, 210, 40));

        lblTitleTimKiemChucVu.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lblTitleTimKiemChucVu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/search.png"))); // NOI18N
        lblTitleTimKiemChucVu.setText("Tìm kiếm:");
        pnMarginTitleChucVu.add(lblTitleTimKiemChucVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 20, 110, 30));

        jLabel97.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel97.setForeground(new java.awt.Color(204, 0, 51));
        jLabel97.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel97.setText("DANH SÁCH CHỨC VỤ");
        pnMarginTitleChucVu.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 360, 40));

        cardTaiKhoanChucVu.add(pnMarginTitleChucVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 1150, 60));

        scpChucVu.setBackground(new java.awt.Color(255, 255, 255));
        scpChucVu.setBorder(null);

        tblChucVu.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tblChucVu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã chức vụ", "Tên chức vụ", "Mô tả"
            }
        ));
        tblChucVu.setGridColor(new java.awt.Color(255, 255, 255));
        tblChucVu.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblChucVu.getTableHeader().setResizingAllowed(false);
        tblChucVu.getTableHeader().setReorderingAllowed(false);
        tblChucVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblChucVuMouseReleased(evt);
            }
        });
        scpChucVu.setViewportView(tblChucVu);

        cardTaiKhoanChucVu.add(scpChucVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 1150, 180));

        lblrecordCV.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lblrecordCV.setForeground(new java.awt.Color(204, 0, 51));
        cardTaiKhoanChucVu.add(lblrecordCV, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 540, 120, 40));

        jLabel99.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel99.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/visits (1).png"))); // NOI18N
        jLabel99.setText("Bảng ghi:");
        cardTaiKhoanChucVu.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 540, 110, 40));

        lblTenCV.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lblTenCV.setForeground(new java.awt.Color(0, 0, 255));
        lblTenCV.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTenCV.setText("Tên chức vụ:");
        lblTenCV.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardTaiKhoanChucVu.add(lblTenCV, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 110, 30));

        txtMoTaCV.setColumns(20);
        txtMoTaCV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMoTaCV.setRows(5);
        jScrollPane7.setViewportView(txtMoTaCV);

        cardTaiKhoanChucVu.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(416, 100, 320, 140));

        cardTrangChu.add(cardTaiKhoanChucVu, "card6");

        cardTaiKhoanNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        cardTaiKhoanNhanVien.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnMarginTKNV.setBackground(new java.awt.Color(204, 204, 255));
        pnMarginTKNV.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnNextNV.setBackground(new java.awt.Color(204, 204, 255));
        btnNextNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/arrow-right.png"))); // NOI18N
        btnNextNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextNVActionPerformed(evt);
            }
        });
        pnMarginTKNV.add(btnNextNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 11, -1, -1));

        btnPrevNV.setBackground(new java.awt.Color(204, 204, 255));
        btnPrevNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/left-arrow.png"))); // NOI18N
        btnPrevNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevNVActionPerformed(evt);
            }
        });
        pnMarginTKNV.add(btnPrevNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 11, -1, -1));

        btnLastNV.setBackground(new java.awt.Color(204, 204, 255));
        btnLastNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/next.png"))); // NOI18N
        btnLastNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastNVActionPerformed(evt);
            }
        });
        pnMarginTKNV.add(btnLastNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 11, -1, -1));

        lblTimKiemNV.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        lblTimKiemNV.setForeground(new java.awt.Color(153, 153, 153));
        lblTimKiemNV.setText(" Nguyễn Văn An");
        lblTimKiemNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTimKiemNVMouseClicked(evt);
            }
        });
        pnMarginTKNV.add(lblTimKiemNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 20, 210, 30));

        txtTimKiemNV.setBackground(new java.awt.Color(204, 204, 255));
        txtTimKiemNV.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTimKiemNV.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemNVCaretUpdate(evt);
            }
        });
        pnMarginTKNV.add(txtTimKiemNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 10, 210, 40));

        lblTitleTimKiemNV.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lblTitleTimKiemNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/search.png"))); // NOI18N
        lblTitleTimKiemNV.setText("Tìm kiếm:");
        pnMarginTKNV.add(lblTitleTimKiemNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 20, 110, 30));

        jLabel84.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(204, 0, 51));
        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel84.setText("DANH SÁCH NHÂN VIÊN");
        pnMarginTKNV.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 360, 40));

        btnFirstNV.setBackground(new java.awt.Color(204, 204, 255));
        btnFirstNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/previous.png"))); // NOI18N
        btnFirstNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstNVActionPerformed(evt);
            }
        });
        pnMarginTKNV.add(btnFirstNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 11, -1, -1));

        cardTaiKhoanNhanVien.add(pnMarginTKNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 1150, 60));

        scpNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        scpNhanVien.setBorder(null);
        scpNhanVien.setOpaque(false);

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
        tblNhanVien.setFocusable(false);
        tblNhanVien.setGridColor(new java.awt.Color(255, 255, 255));
        tblNhanVien.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblNhanVien.setOpaque(false);
        tblNhanVien.setRowHeight(25);
        tblNhanVien.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tblNhanVien.setShowVerticalLines(false);
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        scpNhanVien.setViewportView(tblNhanVien);

        cardTaiKhoanNhanVien.add(scpNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 1150, 140));

        jLabel85.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel85.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/visits (1).png"))); // NOI18N
        jLabel85.setText("Bảng ghi:");
        cardTaiKhoanNhanVien.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 540, 110, 40));

        lblRecordNV.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lblRecordNV.setForeground(new java.awt.Color(204, 0, 51));
        cardTaiKhoanNhanVien.add(lblRecordNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 540, 120, 40));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 255)));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnLamMoiNV.setBackground(new java.awt.Color(204, 204, 255));
        btnLamMoiNV.setText("Làm mới");
        btnLamMoiNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiNVActionPerformed(evt);
            }
        });
        jPanel15.add(btnLamMoiNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 116, 39));

        btnCapNhatNV.setBackground(new java.awt.Color(204, 204, 255));
        btnCapNhatNV.setText("Cập nhật");
        btnCapNhatNV.setEffectColor(new java.awt.Color(204, 255, 204));
        btnCapNhatNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatNVActionPerformed(evt);
            }
        });
        jPanel15.add(btnCapNhatNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 116, 39));

        btnXoaNV.setBackground(new java.awt.Color(255, 51, 102));
        btnXoaNV.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 0, 51)));
        btnXoaNV.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaNV.setText("Xóa");
        btnXoaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNVActionPerformed(evt);
            }
        });
        jPanel15.add(btnXoaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 116, 39));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cboSortNV.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Tên đăng nhập", "Tên nhân viên", " " }));
        cboSortNV.setLabeText("Sắp xếp theo");
        jPanel16.add(cboSortNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 13, 191, 40));

        jLabel30.setText(" Tiến hành sắp xếp");
        jLabel30.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel16.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 160, 19));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(0, 0, 0)));
        jPanel17.setLayout(null);

        btnazNV.setBackground(new java.awt.Color(102, 204, 255));
        btnazNV.setText("A -Z");
        btnazNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnazNVActionPerformed(evt);
            }
        });
        jPanel17.add(btnazNV);
        btnazNV.setBounds(20, 20, 159, 30);

        btnzanv.setBackground(new java.awt.Color(204, 153, 255));
        btnzanv.setText("Z - A");
        btnzanv.setMargin(new java.awt.Insets(5, 14, 14, 14));
        btnzanv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnzanvActionPerformed(evt);
            }
        });
        jPanel17.add(btnzanv);
        btnzanv.setBounds(20, 90, 159, 30);

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

        jPanel16.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 198, 131));

        jPanel15.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, -1, 220));

        btnThemNV.setBackground(new java.awt.Color(204, 204, 255));
        btnThemNV.setText("Thêm");
        btnThemNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNVActionPerformed(evt);
            }
        });
        jPanel15.add(btnThemNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 116, 39));

        cardTaiKhoanNhanVien.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 30, 400, 250));

        lblHovaTenNV.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblHovaTenNV.setForeground(new java.awt.Color(0, 0, 255));
        lblHovaTenNV.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblHovaTenNV.setText("Họ và tên:");
        lblHovaTenNV.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardTaiKhoanNhanVien.add(lblHovaTenNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 44, 110, 30));

        txtHoTenNV.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtHoTenNV.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardTaiKhoanNhanVien.add(txtHoTenNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 180, 30));

        lblTenDN.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblTenDN.setForeground(new java.awt.Color(0, 0, 255));
        lblTenDN.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblTenDN.setText("Tên đăng nhập:");
        lblTenDN.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardTaiKhoanNhanVien.add(lblTenDN, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 110, 30));

        txtTenDN.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTenDN.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardTaiKhoanNhanVien.add(txtTenDN, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 180, 30));

        txtEmailNV.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtEmailNV.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardTaiKhoanNhanVien.add(txtEmailNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 100, 180, 30));

        jLabel35.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 0, 255));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel35.setText("Vai trò:");
        jLabel35.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardTaiKhoanNhanVien.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 40, 110, 30));

        lblSDTNV.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblSDTNV.setForeground(new java.awt.Color(0, 0, 255));
        lblSDTNV.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblSDTNV.setText("Số diện thoại:");
        lblSDTNV.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardTaiKhoanNhanVien.add(lblSDTNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 110, 30));

        txtSDTNV.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtSDTNV.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardTaiKhoanNhanVien.add(txtSDTNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, 180, 30));

        lblTrangThai.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblTrangThai.setForeground(new java.awt.Color(0, 0, 255));
        lblTrangThai.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblTrangThai.setText("Trạng thái:");
        lblTrangThai.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardTaiKhoanNhanVien.add(lblTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 280, 110, 30));

        txtDiaChiNV.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtDiaChiNV.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardTaiKhoanNhanVien.add(txtDiaChiNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 220, 180, 30));

        txtNgaySinhNV.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtNgaySinhNV.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardTaiKhoanNhanVien.add(txtNgaySinhNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 160, 180, 30));

        lblNgaySinhNV.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblNgaySinhNV.setForeground(new java.awt.Color(0, 0, 255));
        lblNgaySinhNV.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblNgaySinhNV.setText("Ngày sinh:");
        lblNgaySinhNV.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardTaiKhoanNhanVien.add(lblNgaySinhNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 160, 110, 30));

        lblMatKhau.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblMatKhau.setForeground(new java.awt.Color(0, 0, 255));
        lblMatKhau.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblMatKhau.setText("Mật khẩu:");
        lblMatKhau.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardTaiKhoanNhanVien.add(lblMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 110, 30));

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

        cboVaiTro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Admin", "Quản lí ", "Nhân viên" }));
        cboVaiTro.setLabeText("");
        cardTaiKhoanNhanVien.add(cboVaiTro, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, 180, 50));

        lblDiaChiNV.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblDiaChiNV.setForeground(new java.awt.Color(0, 0, 255));
        lblDiaChiNV.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblDiaChiNV.setText("Địa chỉ:");
        lblDiaChiNV.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardTaiKhoanNhanVien.add(lblDiaChiNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 220, 110, 30));

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

        lblEmailNV.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblEmailNV.setForeground(new java.awt.Color(0, 0, 255));
        lblEmailNV.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblEmailNV.setText("Email:");
        lblEmailNV.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardTaiKhoanNhanVien.add(lblEmailNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 100, 110, 30));

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
                {"a", "â", null, null, null, null, null, null, null},
                {"d", "c", null, null, null, null, null, null, null},
                {"z", "c", null, null, null, null, null, null, null},
                {"a", "o", null, null, null, null, null, null, null}
            },
            new String [] {
                "MÃ SẢM PHẨM", "TÊN SẢN PHẨM", "TÊN LOẠI", "TÊN HÃNG", "SỐ LƯỢNG", "GIÁ NHẬP", "GIÁ BÁN", "NGÀY NHẬP", "MÔ TẢ"
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

        cardLoai2.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 1150, 170));

        pnBangTTSP.setBackground(new java.awt.Color(204, 204, 255));

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
        lblTimKiemTempSP.setText("Tai nghe");
        lblTimKiemTempSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTimKiemTempSPMouseClicked(evt);
            }
        });

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

        cboTimKiemSP.setBackground(new java.awt.Color(204, 204, 255));
        cboTimKiemSP.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Tên sản phẩm", "Mã sản phẩm" }));
        cboTimKiemSP.setLabeText("Tìm kiếm theo");

        javax.swing.GroupLayout pnBangTTSPLayout = new javax.swing.GroupLayout(pnBangTTSP);
        pnBangTTSP.setLayout(pnBangTTSPLayout);
        pnBangTTSPLayout.setHorizontalGroup(
            pnBangTTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBangTTSPLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(btnFirstSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnPrevSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnNextSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btnLastSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cboTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnBangTTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTimKiemTempSP, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );
        pnBangTTSPLayout.setVerticalGroup(
            pnBangTTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBangTTSPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnBangTTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTimKiemTempSP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnBangTTSPLayout.createSequentialGroup()
                .addGroup(pnBangTTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnFirstSP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPrevSP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNextSP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnBangTTSPLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnBangTTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnLastSP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        cardLoai2.add(pnBangTTSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 1150, 50));

        cboMaLoai.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "a", "x", "e", "f", "e", "gd", "" }));
        cboMaLoai.setLabeText("");
        cardLoai2.add(cboMaLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 20, 170, 39));

        cboMaHang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "a", "x", "e", "f", "e", "gd", " " }));
        cboMaHang.setLabeText("");
        cardLoai2.add(cboMaHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 210, 39));

        lblAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAnh.setText("Ảnh");
        lblAnh.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnhMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblAnhMouseEntered(evt);
            }
        });
        cardLoai2.add(lblAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 40, 130, 120));

        lblBanGhiSP.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lblBanGhiSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/visits (1).png"))); // NOI18N
        lblBanGhiSP.setText("Bảng ghi:");
        cardLoai2.add(lblBanGhiSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 540, 122, 40));

        lblrecordSP.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lblrecordSP.setForeground(new java.awt.Color(204, 0, 51));
        cardLoai2.add(lblrecordSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 540, 208, 40));

        pnSP.setBackground(new java.awt.Color(255, 255, 255));
        pnSP.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 255)));

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

        cboSXSP.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Mã sản phẩm", "Tên sản phẩm", "Giá nhập ", "Giá bán", " " }));
        cboSXSP.setLabeText("Sắp xếp theo");
        cboSXSP.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboSXSPItemStateChanged(evt);
            }
        });
        cboSXSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboSXSPMouseClicked(evt);
            }
        });

        lblTHSXSP.setText(" Tiến hành sắp xếp");
        lblTHSXSP.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        pnBtnSapxepSP.setBackground(new java.awt.Color(255, 255, 255));
        pnBtnSapxepSP.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(0, 0, 0)));
        pnBtnSapxepSP.setLayout(null);

        btnazsp.setBackground(new java.awt.Color(102, 204, 255));
        btnazsp.setText("A - Z");
        btnazsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnazspActionPerformed(evt);
            }
        });
        pnBtnSapxepSP.add(btnazsp);
        btnazsp.setBounds(20, 20, 120, 30);

        btnzasp.setBackground(new java.awt.Color(204, 153, 255));
        btnzasp.setText("Z - A");
        btnzasp.setMargin(new java.awt.Insets(5, 14, 14, 14));
        btnzasp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnzaspActionPerformed(evt);
            }
        });
        pnBtnSapxepSP.add(btnzasp);
        btnzasp.setBounds(20, 90, 120, 30);

        jLabel72.setForeground(new java.awt.Color(153, 153, 153));
        jLabel72.setText("hoặc");
        pnBtnSapxepSP.add(jLabel72);
        jLabel72.setBounds(70, 60, 40, 20);
        pnBtnSapxepSP.add(jSeparator33);
        jSeparator33.setBounds(100, 70, 40, 20);
        pnBtnSapxepSP.add(jSeparator34);
        jSeparator34.setBounds(20, 70, 40, 20);

        jSeparator35.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator35.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        pnBtnSapxepSP.add(jSeparator35);
        jSeparator35.setBounds(100, 0, 100, 20);

        javax.swing.GroupLayout pnSPLayout = new javax.swing.GroupLayout(pnSP);
        pnSP.setLayout(pnSPLayout);
        pnSPLayout.setHorizontalGroup(
            pnSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXoaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhatSP, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pnBtnSapxepSP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTHSXSP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboSXSP, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnSPLayout.setVerticalGroup(
            pnSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnSPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnSPLayout.createSequentialGroup()
                        .addComponent(cboSXSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTHSXSP, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(pnBtnSapxepSP, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnSPLayout.createSequentialGroup()
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

        cardLoai2.add(pnSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 40, 330, 240));

        txtTenSP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTenSP.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardLoai2.add(txtTenSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 210, 30));

        lblTenSP.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblTenSP.setForeground(new java.awt.Color(0, 0, 255));
        lblTenSP.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblTenSP.setText("Tên sản phẩm:");
        lblTenSP.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardLoai2.add(lblTenSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 100, 30));

        lblMaSPTemp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMaSPTempMouseClicked(evt);
            }
        });
        cardLoai2.add(lblMaSPTemp, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 210, 30));

        txtMaSP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtMaSP.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardLoai2.add(txtMaSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 210, 30));

        lblTenHangSP.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblTenHangSP.setForeground(new java.awt.Color(0, 0, 255));
        lblTenHangSP.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblTenHangSP.setText("Tên hãng:");
        lblTenHangSP.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardLoai2.add(lblTenHangSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 100, 30));

        lblNgayNhaptemp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNgayNhaptempMouseClicked(evt);
            }
        });
        cardLoai2.add(lblNgayNhaptemp, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, 210, 30));

        txtNgayNhapSP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtNgayNhapSP.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardLoai2.add(txtNgayNhapSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, 210, 30));

        lblNgayNhapSP.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblNgayNhapSP.setForeground(new java.awt.Color(0, 0, 255));
        lblNgayNhapSP.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblNgayNhapSP.setText("Ngày nhập:");
        lblNgayNhapSP.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardLoai2.add(lblNgayNhapSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 90, 30));

        lblVND1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        lblVND1.setForeground(new java.awt.Color(255, 0, 0));
        lblVND1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblVND1.setText("VNĐ");
        cardLoai2.add(lblVND1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 250, -1, 30));

        lblGiaNhapSP.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblGiaNhapSP.setForeground(new java.awt.Color(0, 0, 255));
        lblGiaNhapSP.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblGiaNhapSP.setText("Giá nhập:");
        lblGiaNhapSP.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardLoai2.add(lblGiaNhapSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 100, 30));

        lblVND2.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        lblVND2.setForeground(new java.awt.Color(255, 0, 0));
        lblVND2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblVND2.setText("VNĐ");
        cardLoai2.add(lblVND2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 80, -1, 30));

        txtGiaBanSP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtGiaBanSP.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardLoai2.add(txtGiaBanSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 80, 140, 30));

        lblTenLoaiSP.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblTenLoaiSP.setForeground(new java.awt.Color(0, 0, 255));
        lblTenLoaiSP.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblTenLoaiSP.setText("Tên loại:");
        lblTenLoaiSP.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardLoai2.add(lblTenLoaiSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 30, 70, 30));

        txtMoTaSP.setColumns(20);
        txtMoTaSP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMoTaSP.setRows(3);
        jScrollPane8.setViewportView(txtMoTaSP);

        cardLoai2.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 210, 420, 70));

        lblMoTaSP.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblMoTaSP.setForeground(new java.awt.Color(0, 0, 255));
        lblMoTaSP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMoTaSP.setText("Mô tả:");
        lblMoTaSP.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardLoai2.add(lblMoTaSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 180, 80, -1));

        lblMaSP.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblMaSP.setForeground(new java.awt.Color(0, 0, 255));
        lblMaSP.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblMaSP.setText("Mã sản phẩm:");
        lblMaSP.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardLoai2.add(lblMaSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 100, 30));

        lblGiaBanSP.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblGiaBanSP.setForeground(new java.awt.Color(0, 0, 255));
        lblGiaBanSP.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblGiaBanSP.setText("Giá bán:");
        lblGiaBanSP.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardLoai2.add(lblGiaBanSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 80, 70, 30));

        lblImportFileExcel.setBackground(new java.awt.Color(255, 204, 255));
        lblImportFileExcel.setText("Nhập File Excel");
        lblImportFileExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblImportFileExcelActionPerformed(evt);
            }
        });
        cardLoai2.add(lblImportFileExcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 290, 110, -1));

        lblExportInfor.setBackground(new java.awt.Color(255, 204, 255));
        lblExportInfor.setText("Xuất thông tin");
        lblExportInfor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblExportInforActionPerformed(evt);
            }
        });
        cardLoai2.add(lblExportInfor, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 290, 110, -1));

        lblSoLuongSP.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblSoLuongSP.setForeground(new java.awt.Color(0, 0, 255));
        lblSoLuongSP.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblSoLuongSP.setText("Số lượng:");
        lblSoLuongSP.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardLoai2.add(lblSoLuongSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 130, 70, 30));

        lblFormat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblFormat.setText("Format file Excel:");
        cardLoai2.add(lblFormat, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 290, 110, 20));

        txtSoLuongSP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtSoLuongSP.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardLoai2.add(txtSoLuongSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 130, 170, 30));

        btnFormatFileExcel.setBackground(new java.awt.Color(255, 204, 255));
        btnFormatFileExcel.setText("Tải về");
        btnFormatFileExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFormatFileExcelActionPerformed(evt);
            }
        });
        cardLoai2.add(btnFormatFileExcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 290, 70, -1));

        txtGiaNhapSP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtGiaNhapSP.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardLoai2.add(txtGiaNhapSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, 180, 30));

        txtGiaNhapSP3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtGiaNhapSP3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardLoai2.add(txtGiaNhapSP3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 250, 30, 30));

        txtGiaBanSP1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtGiaBanSP1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtGiaBanSP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaBanSP1ActionPerformed(evt);
            }
        });
        cardLoai2.add(txtGiaBanSP1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 80, 30, 30));

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

        pnBangTTHang.setBackground(new java.awt.Color(204, 204, 255));

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

        lblTimKiemHang.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lblTimKiemHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/search.png"))); // NOI18N
        lblTimKiemHang.setText("Tìm kiếm:");

        lblHangTemp.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        lblHangTemp.setForeground(new java.awt.Color(153, 153, 153));
        lblHangTemp.setText("SAMSUNG");
        lblHangTemp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHangTempMouseClicked(evt);
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

        javax.swing.GroupLayout pnBangTTHangLayout = new javax.swing.GroupLayout(pnBangTTHang);
        pnBangTTHang.setLayout(pnBangTTHangLayout);
        pnBangTTHangLayout.setHorizontalGroup(
            pnBangTTHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBangTTHangLayout.createSequentialGroup()
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
                .addComponent(lblTimKiemHang, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnBangTTHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHangTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiemHang, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68))
        );
        pnBangTTHangLayout.setVerticalGroup(
            pnBangTTHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBangTTHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnBangTTHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHangTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiemHang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnBangTTHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTimKiemHang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnLastHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrevHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFirstHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNextHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        cardLoai1.add(pnBangTTHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 1150, 60));

        pnHang.setBackground(new java.awt.Color(255, 255, 255));
        pnHang.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 255)));

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

        pnSXHang.setBackground(new java.awt.Color(255, 255, 255));

        cboHang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tên hãng", "Mã hãng" }));
        cboHang.setSelectedIndex(-1);
        cboHang.setLabeText("Sắp xếp theo");

        lblTHSXHang.setText(" Tiến hành sắp xếp");
        lblTHSXHang.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        pnbtnSXHang.setBackground(new java.awt.Color(255, 255, 255));
        pnbtnSXHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(0, 0, 0)));
        pnbtnSXHang.setLayout(null);

        btnazHang.setBackground(new java.awt.Color(102, 204, 255));
        btnazHang.setText("A - Z");
        btnazHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnazHangActionPerformed(evt);
            }
        });
        pnbtnSXHang.add(btnazHang);
        btnazHang.setBounds(20, 20, 159, 30);

        btnzaHang.setBackground(new java.awt.Color(204, 153, 255));
        btnzaHang.setText("Z - A");
        btnzaHang.setMargin(new java.awt.Insets(5, 14, 14, 14));
        btnzaHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnzaHangActionPerformed(evt);
            }
        });
        pnbtnSXHang.add(btnzaHang);
        btnzaHang.setBounds(20, 90, 159, 30);

        jLabel103.setForeground(new java.awt.Color(153, 153, 153));
        jLabel103.setText("hoặc");
        pnbtnSXHang.add(jLabel103);
        jLabel103.setBounds(90, 60, 40, 20);
        pnbtnSXHang.add(jSeparator15);
        jSeparator15.setBounds(120, 70, 60, 20);
        pnbtnSXHang.add(jSeparator16);
        jSeparator16.setBounds(20, 70, 60, 20);

        jSeparator17.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator17.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        pnbtnSXHang.add(jSeparator17);
        jSeparator17.setBounds(100, 0, 100, 20);

        javax.swing.GroupLayout pnSXHangLayout = new javax.swing.GroupLayout(pnSXHang);
        pnSXHang.setLayout(pnSXHangLayout);
        pnSXHangLayout.setHorizontalGroup(
            pnSXHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnSXHangLayout.createSequentialGroup()
                .addGap(0, 34, Short.MAX_VALUE)
                .addGroup(pnSXHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTHSXHang, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnSXHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnSXHangLayout.createSequentialGroup()
                            .addComponent(cboHang, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(17, 17, 17))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnSXHangLayout.createSequentialGroup()
                            .addComponent(pnbtnSXHang, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap()))))
        );
        pnSXHangLayout.setVerticalGroup(
            pnSXHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSXHangLayout.createSequentialGroup()
                .addComponent(cboHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTHSXHang, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(pnbtnSXHang, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnThemHang.setBackground(new java.awt.Color(204, 204, 255));
        btnThemHang.setText("Thêm");
        btnThemHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnHangLayout = new javax.swing.GroupLayout(pnHang);
        pnHang.setLayout(pnHangLayout);
        pnHangLayout.setHorizontalGroup(
            pnHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHangLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(pnHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXoaHang, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoiHang, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemHang, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(pnSXHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnHangLayout.setVerticalGroup(
            pnHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHangLayout.createSequentialGroup()
                .addComponent(pnSXHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(pnHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLamMoiHang, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnThemHang, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCapNhatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoaHang, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        cardLoai1.add(pnHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 40, 410, 240));

        lblBanGhiHang.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lblBanGhiHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/visits (1).png"))); // NOI18N
        lblBanGhiHang.setText("Bảng ghi:");
        cardLoai1.add(lblBanGhiHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 530, 122, 41));

        lblrecordHang.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lblrecordHang.setForeground(new java.awt.Color(204, 0, 51));
        cardLoai1.add(lblrecordHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 530, 208, 41));

        lblMaHang.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lblMaHang.setForeground(new java.awt.Color(0, 0, 255));
        lblMaHang.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMaHang.setText("Mã hãng sản xuất:");
        lblMaHang.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardLoai1.add(lblMaHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 150, 30));

        txtTenHang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTenHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardLoai1.add(txtTenHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 180, 250, 30));

        lblTenHang.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lblTenHang.setForeground(new java.awt.Color(0, 0, 255));
        lblTenHang.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTenHang.setText("Tên hãng sản xuất:");
        lblTenHang.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardLoai1.add(lblTenHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 150, 30));

        lblMaHangTemp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMaHangTempMouseClicked(evt);
            }
        });
        cardLoai1.add(lblMaHangTemp, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, 250, 30));

        txtMaHang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMaHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardLoai1.add(txtMaHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, 250, 30));

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

        pnBangTTLoai.setBackground(new java.awt.Color(204, 204, 255));

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
        lblLoaiTemp.setText("Sạc dự phòng");
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

        javax.swing.GroupLayout pnBangTTLoaiLayout = new javax.swing.GroupLayout(pnBangTTLoai);
        pnBangTTLoai.setLayout(pnBangTTLoaiLayout);
        pnBangTTLoaiLayout.setHorizontalGroup(
            pnBangTTLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBangTTLoaiLayout.createSequentialGroup()
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
                .addGroup(pnBangTTLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLoaiTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiemLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );
        pnBangTTLoaiLayout.setVerticalGroup(
            pnBangTTLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBangTTLoaiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnBangTTLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel69, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnBangTTLoaiLayout.createSequentialGroup()
                        .addGroup(pnBangTTLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnBangTTLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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

        cardLoai.add(pnBangTTLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 1150, 60));

        pnLoai.setBackground(new java.awt.Color(255, 255, 255));
        pnLoai.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 255)));

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

        pnSXLoai.setBackground(new java.awt.Color(255, 255, 255));

        cboLoai.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tên loại", "Mã Loại" }));
        cboLoai.setSelectedIndex(-1);
        cboLoai.setLabeText("Sắp xếp theo");

        lblTHSXLoai.setText(" Tiến hành sắp xếp");
        lblTHSXLoai.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        pnbtnSXLoai.setBackground(new java.awt.Color(255, 255, 255));
        pnbtnSXLoai.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(0, 0, 0)));
        pnbtnSXLoai.setLayout(null);

        btnazLoai.setBackground(new java.awt.Color(102, 204, 255));
        btnazLoai.setText("A - Z");
        btnazLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnazLoaiActionPerformed(evt);
            }
        });
        pnbtnSXLoai.add(btnazLoai);
        btnazLoai.setBounds(20, 20, 159, 30);

        btnzaLoai.setBackground(new java.awt.Color(204, 153, 255));
        btnzaLoai.setText("Z - A");
        btnzaLoai.setMargin(new java.awt.Insets(5, 14, 14, 14));
        btnzaLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnzaLoaiActionPerformed(evt);
            }
        });
        pnbtnSXLoai.add(btnzaLoai);
        btnzaLoai.setBounds(20, 90, 159, 30);

        jLabel101.setForeground(new java.awt.Color(153, 153, 153));
        jLabel101.setText("hoặc");
        pnbtnSXLoai.add(jLabel101);
        jLabel101.setBounds(90, 60, 40, 20);
        pnbtnSXLoai.add(jSeparator12);
        jSeparator12.setBounds(120, 70, 60, 20);
        pnbtnSXLoai.add(jSeparator13);
        jSeparator13.setBounds(20, 70, 60, 20);

        jSeparator14.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator14.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        pnbtnSXLoai.add(jSeparator14);
        jSeparator14.setBounds(100, 0, 100, 20);

        javax.swing.GroupLayout pnSXLoaiLayout = new javax.swing.GroupLayout(pnSXLoai);
        pnSXLoai.setLayout(pnSXLoaiLayout);
        pnSXLoaiLayout.setHorizontalGroup(
            pnSXLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnSXLoaiLayout.createSequentialGroup()
                .addGap(0, 4, Short.MAX_VALUE)
                .addGroup(pnSXLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTHSXLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnSXLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnSXLoaiLayout.createSequentialGroup()
                            .addComponent(cboLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(17, 17, 17))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnSXLoaiLayout.createSequentialGroup()
                            .addComponent(pnbtnSXLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap()))))
        );
        pnSXLoaiLayout.setVerticalGroup(
            pnSXLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSXLoaiLayout.createSequentialGroup()
                .addComponent(cboLoai, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTHSXLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(pnbtnSXLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnThemLoai.setBackground(new java.awt.Color(204, 204, 255));
        btnThemLoai.setText("Thêm");
        btnThemLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemLoaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnLoaiLayout = new javax.swing.GroupLayout(pnLoai);
        pnLoai.setLayout(pnLoaiLayout);
        pnLoaiLayout.setHorizontalGroup(
            pnLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnLoaiLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(pnLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXoaLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoiLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhatLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(pnSXLoai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnLoaiLayout.setVerticalGroup(
            pnLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnLoaiLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(btnLamMoiLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnThemLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCapNhatLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoaLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(pnLoaiLayout.createSequentialGroup()
                .addComponent(pnSXLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        cardLoai.add(pnLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 40, 380, 240));

        lblBanGhiLoai.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lblBanGhiLoai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/visits (1).png"))); // NOI18N
        lblBanGhiLoai.setText("Bảng ghi:");
        cardLoai.add(lblBanGhiLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 550, 122, 41));

        lblrecordLoai.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lblrecordLoai.setForeground(new java.awt.Color(204, 0, 51));
        cardLoai.add(lblrecordLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 550, 208, 41));

        lblMaLoaiTemp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMaLoaiTempMouseClicked(evt);
            }
        });
        cardLoai.add(lblMaLoaiTemp, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, 260, 30));

        txtMaLoai.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMaLoai.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardLoai.add(txtMaLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, 260, 30));

        lblMaLoai.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lblMaLoai.setForeground(new java.awt.Color(0, 0, 255));
        lblMaLoai.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMaLoai.setText("Mã loại hàng:");
        lblMaLoai.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardLoai.add(lblMaLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 160, 30));

        lblTenLoai.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lblTenLoai.setForeground(new java.awt.Color(0, 0, 255));
        lblTenLoai.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTenLoai.setText("Tên loại hàng:");
        lblTenLoai.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardLoai.add(lblTenLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, 150, 30));

        txtTenLoai.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTenLoai.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardLoai.add(txtTenLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 190, 260, 30));

        cardTrangChu.add(cardLoai, "card10");

        cardGioHang.setBackground(new java.awt.Color(255, 255, 255));
        cardGioHang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnGioHangSanPham.setLayout(new java.awt.CardLayout());

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

        pnGioHangSanPham.add(cardHoaDonSanPham, "card2");

        cardHoaDonQR.setBackground(new java.awt.Color(255, 255, 255));
        cardHoaDonQR.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(0, 0, 0)));

        pnQR.setBackground(new java.awt.Color(250, 250, 250));
        pnQR.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnQR.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout cardHoaDonQRLayout = new javax.swing.GroupLayout(cardHoaDonQR);
        cardHoaDonQR.setLayout(cardHoaDonQRLayout);
        cardHoaDonQRLayout.setHorizontalGroup(
            cardHoaDonQRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardHoaDonQRLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(pnQR, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(107, Short.MAX_VALUE))
        );
        cardHoaDonQRLayout.setVerticalGroup(
            cardHoaDonQRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardHoaDonQRLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(pnQR, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(155, Short.MAX_VALUE))
        );

        pnGioHangSanPham.add(cardHoaDonQR, "card3");

        cardGioHang.add(pnGioHangSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 640, 490));

        txtFindNameProductCart.setToolTipText("");
        txtFindNameProductCart.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtFindNameProductCart.setLabelText("Tìm kiếm");
        txtFindNameProductCart.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFindNameProductCartCaretUpdate(evt);
            }
        });
        cardGioHang.add(txtFindNameProductCart, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 230, 60));

        jLabel77.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel77.setText("Danh sách sản phẩm");
        cardGioHang.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 210, 40));

        btnXNKH.setBackground(new java.awt.Color(255, 153, 102));
        btnXNKH.setText("Xác nhận");
        btnXNKH.setActionCommand("Quét mã QR");
        btnXNKH.setEffectColor(new java.awt.Color(255, 204, 204));
        btnXNKH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnXNKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXNKHActionPerformed(evt);
            }
        });
        cardGioHang.add(btnXNKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 50, 120, 30));

        txtSdtKH.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSdtKH.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtSdtKH.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSdtKHCaretUpdate(evt);
            }
        });
        txtSdtKH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSdtKHKeyPressed(evt);
            }
        });
        cardGioHang.add(txtSdtKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 50, 240, 30));

        btnXacNhanDonHang.setBackground(new java.awt.Color(102, 204, 255));
        btnXacNhanDonHang.setText("Xác nhận đơn hàng");
        btnXacNhanDonHang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnXacNhanDonHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanDonHangActionPerformed(evt);
            }
        });
        cardGioHang.add(btnXacNhanDonHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 520, 190, 40));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Xóa");
        cardGioHang.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 540, 40, 30));

        spnSL.setBackground(new java.awt.Color(255, 255, 255));
        spnSL.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spnSL.setLabelText("Số lượng");
        spnSL.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnSLStateChanged(evt);
            }
        });
        cardGioHang.add(spnSL, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 500, 120, 60));

        btnxoaGioHang.setBorder(null);
        btnxoaGioHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/bin.png"))); // NOI18N
        btnxoaGioHang.setEffectColor(new java.awt.Color(255, 0, 0));
        btnxoaGioHang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnxoaGioHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaGioHangActionPerformed(evt);
            }
        });
        cardGioHang.add(btnxoaGioHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 500, 40, 40));
        cardGioHang.add(jSeparator19, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 470, 520, 10));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel10.setText("Số điện thoại:");
        cardGioHang.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 50, 110, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel11.setText("Quét bằng mã:");
        cardGioHang.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 30, 110, 30));

        switchButton1.setBackground(new java.awt.Color(0, 153, 51));
        switchButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                switchButton1MousePressed(evt);
            }
        });
        cardGioHang.add(switchButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 30, -1, -1));

        jScrollPane13.setBorder(null);

        tblCart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Giá", "Số lượng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCart.setGridColor(new java.awt.Color(255, 255, 255));
        tblCart.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tblCartFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tblCartFocusLost(evt);
            }
        });
        tblCart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCartMouseClicked(evt);
            }
        });
        tblCart.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                tblCartInputMethodTextChanged(evt);
            }
        });
        tblCart.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblCartKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblCartKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tblCartKeyTyped(evt);
            }
        });
        jScrollPane13.setViewportView(tblCart);
        if (tblCart.getColumnModel().getColumnCount() > 0) {
            tblCart.getColumnModel().getColumn(1).setResizable(false);
            tblCart.getColumnModel().getColumn(3).setResizable(false);
        }

        cardGioHang.add(jScrollPane13, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 100, 500, 350));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel13.setText("Tổng tiền:");
        cardGioHang.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 480, 70, 30));

        txtTongtiensp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTongtiensp.setForeground(new java.awt.Color(255, 0, 51));
        txtTongtiensp.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTongtiensp.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTongtienspCaretUpdate(evt);
            }
        });
        cardGioHang.add(txtTongtiensp, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 480, 190, 30));

        pnPupopMenu.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnPupopMenuLayout = new javax.swing.GroupLayout(pnPupopMenu);
        pnPupopMenu.setLayout(pnPupopMenuLayout);
        pnPupopMenuLayout.setHorizontalGroup(
            pnPupopMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );
        pnPupopMenuLayout.setVerticalGroup(
            pnPupopMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        cardGioHang.add(pnPupopMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 70, 240, -1));

        lblNameCustomer.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblNameCustomer.setForeground(new java.awt.Color(0, 0, 255));
        cardGioHang.add(lblNameCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, 480, 30));

        cardTrangChu.add(cardGioHang, "card11");

        cardHoaDon.setBackground(new java.awt.Color(255, 255, 255));
        cardHoaDon.setPreferredSize(new java.awt.Dimension(1180, 580));
        cardHoaDon.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane12.setBorder(null);

        tblHoaDon.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Ngày lập", "Tổng tiền", "Tích điểm", "Phần trăm giảm giá", "Tiền giảm", "Thành tiền"
            }
        ));
        tblHoaDon.setRowHeight(25);
        tblHoaDon.setRowMargin(0);
        tblHoaDon.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblHoaDonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseReleased(evt);
            }
        });
        jScrollPane12.setViewportView(tblHoaDon);

        cardHoaDon.add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 1150, 130));

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));
        jPanel28.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 255)));
        jPanel28.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnXoahd.setBackground(new java.awt.Color(255, 51, 102));
        btnXoahd.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 0, 51)));
        btnXoahd.setForeground(new java.awt.Color(255, 255, 255));
        btnXoahd.setText("Xóa");
        btnXoahd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoahdActionPerformed(evt);
            }
        });
        jPanel28.add(btnXoahd, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 116, 39));

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));
        jPanel29.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        comboboxHD.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Mã hóa đơn", "Tổng tiền", "Thành tiền" }));
        comboboxHD.setLabeText("Sắp xếp theo");
        comboboxHD.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboboxHDItemStateChanged(evt);
            }
        });
        jPanel29.add(comboboxHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 13, 191, 40));

        jLabel48.setText(" Tiến hành sắp xếp");
        jLabel48.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel29.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 160, 19));

        jPanel30.setBackground(new java.awt.Color(255, 255, 255));
        jPanel30.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(0, 0, 0)));
        jPanel30.setLayout(null);

        btnazhd.setBackground(new java.awt.Color(102, 204, 255));
        btnazhd.setText("A - Z");
        btnazhd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnazhdActionPerformed(evt);
            }
        });
        jPanel30.add(btnazhd);
        btnazhd.setBounds(20, 20, 159, 30);

        btnzahd.setBackground(new java.awt.Color(204, 153, 255));
        btnzahd.setText("Z -A");
        btnzahd.setMargin(new java.awt.Insets(5, 14, 14, 14));
        btnzahd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnzahdActionPerformed(evt);
            }
        });
        jPanel30.add(btnzahd);
        btnzahd.setBounds(20, 90, 159, 30);

        jLabel49.setForeground(new java.awt.Color(153, 153, 153));
        jLabel49.setText("hoặc");
        jPanel30.add(jLabel49);
        jLabel49.setBounds(90, 60, 40, 20);
        jPanel30.add(jSeparator20);
        jSeparator20.setBounds(120, 70, 60, 20);
        jPanel30.add(jSeparator21);
        jSeparator21.setBounds(20, 70, 60, 20);

        jSeparator22.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator22.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        jPanel30.add(jSeparator22);
        jSeparator22.setBounds(100, 0, 100, 20);

        jPanel29.add(jPanel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 198, 131));

        jPanel28.add(jPanel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, -1, 220));

        btnIn.setBackground(new java.awt.Color(51, 255, 255));
        btnIn.setBorder(null);
        btnIn.setText("In");
        btnIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInActionPerformed(evt);
            }
        });
        jPanel28.add(btnIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 116, 39));

        btnIn1.setBackground(new java.awt.Color(255, 51, 102));
        btnIn1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 0, 51)));
        btnIn1.setForeground(new java.awt.Color(255, 255, 255));
        btnIn1.setText("In");
        btnIn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIn1ActionPerformed(evt);
            }
        });
        jPanel28.add(btnIn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 116, 39));

        cardHoaDon.add(jPanel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 40, 410, 245));

        pnMarginHoaDon.setBackground(new java.awt.Color(204, 204, 255));

        btnNexthd.setBackground(new java.awt.Color(204, 204, 255));
        btnNexthd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/arrow-right.png"))); // NOI18N
        btnNexthd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNexthdMouseClicked(evt);
            }
        });

        btnFirsthd.setBackground(new java.awt.Color(204, 204, 255));
        btnFirsthd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/previous.png"))); // NOI18N
        btnFirsthd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFirsthdMouseClicked(evt);
            }
        });
        btnFirsthd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirsthdActionPerformed(evt);
            }
        });

        btnPrevhd.setBackground(new java.awt.Color(204, 204, 255));
        btnPrevhd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/left-arrow.png"))); // NOI18N
        btnPrevhd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPrevhdMouseClicked(evt);
            }
        });
        btnPrevhd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevhdActionPerformed(evt);
            }
        });

        btnLasthd.setBackground(new java.awt.Color(204, 204, 255));
        btnLasthd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/next.png"))); // NOI18N
        btnLasthd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLasthdMouseClicked(evt);
            }
        });
        btnLasthd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLasthdActionPerformed(evt);
            }
        });

        jLabel81.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(204, 0, 51));
        jLabel81.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel81.setText("BẢNG THÔNG TIN HÓA ĐƠN");

        lblTimKiemHD.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        lblTimKiemHD.setForeground(new java.awt.Color(153, 153, 153));
        lblTimKiemHD.setText("Tên khách hàng");
        lblTimKiemHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTimKiemHDMouseClicked(evt);
            }
        });

        lblTittleFindHoaDon.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lblTittleFindHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/search.png"))); // NOI18N
        lblTittleFindHoaDon.setText("Tìm kiếm:");

        txtTimKiemhd.setBackground(new java.awt.Color(204, 204, 255));
        txtTimKiemhd.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTimKiemhd.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemhdCaretUpdate(evt);
            }
        });
        txtTimKiemhd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTimKiemhdMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnMarginHoaDonLayout = new javax.swing.GroupLayout(pnMarginHoaDon);
        pnMarginHoaDon.setLayout(pnMarginHoaDonLayout);
        pnMarginHoaDonLayout.setHorizontalGroup(
            pnMarginHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMarginHoaDonLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(btnFirsthd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnPrevhd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnNexthd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btnLasthd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(lblTittleFindHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(pnMarginHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTimKiemHD, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiemhd, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );
        pnMarginHoaDonLayout.setVerticalGroup(
            pnMarginHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnLasthd, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
            .addComponent(btnNexthd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnPrevhd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnFirsthd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel81, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnMarginHoaDonLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnMarginHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTittleFindHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTimKiemHD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiemhd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        cardHoaDon.add(pnMarginHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, -1, 60));

        jLabel104.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel104.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/visits (1).png"))); // NOI18N
        jLabel104.setText("Bảng ghi:");
        cardHoaDon.add(jLabel104, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 550, -1, -1));

        lblrecordhd.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lblrecordhd.setForeground(new java.awt.Color(204, 0, 51));
        lblrecordhd.setText("2 trên 10");
        cardHoaDon.add(lblrecordhd, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 550, 240, -1));

        jLabel117.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel117.setForeground(new java.awt.Color(0, 0, 255));
        jLabel117.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel117.setText("Tên nhân viên: ");
        jLabel117.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardHoaDon.add(jLabel117, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 130, -1));

        jLabel118.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel118.setForeground(new java.awt.Color(0, 0, 255));
        jLabel118.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel118.setText("Tên khách hàng: ");
        jLabel118.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardHoaDon.add(jLabel118, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, -1, -1));

        jLabel119.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel119.setForeground(new java.awt.Color(0, 0, 255));
        jLabel119.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel119.setText("Mã khách hàng:");
        jLabel119.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardHoaDon.add(jLabel119, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 80, -1, -1));

        txtTENNV.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTENNV.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardHoaDon.add(txtTENNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, 170, 30));

        txtTENKH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTENKH.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardHoaDon.add(txtTENKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 170, 30));

        txtMAKH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtMAKH.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardHoaDon.add(txtMAKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 70, 140, 30));

        jLabel120.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel120.setForeground(new java.awt.Color(0, 0, 255));
        jLabel120.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel120.setText("Tiền nhận:");
        jLabel120.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardHoaDon.add(jLabel120, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 250, -1, -1));

        txtTienNhan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTienNhan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTienNhan.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTienNhanCaretUpdate(evt);
            }
        });
        cardHoaDon.add(txtTienNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 240, 140, 30));

        jLabel135.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel135.setForeground(new java.awt.Color(0, 0, 255));
        jLabel135.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel135.setText("Phần trăm giảm giá:");
        jLabel135.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardHoaDon.add(jLabel135, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, -1, 30));

        txtPhanTramGG.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPhanTramGG.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtPhanTramGG.setToolTipText("");
        txtPhanTramGG.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtPhanTramGG.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtPhanTramGG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhanTramGGActionPerformed(evt);
            }
        });
        cardHoaDon.add(txtPhanTramGG, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 240, 60, 30));

        jLabel129.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel129.setForeground(new java.awt.Color(255, 0, 0));
        jLabel129.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel129.setText("%");
        cardHoaDon.add(jLabel129, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 240, 30, 30));

        jLabel121.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel121.setForeground(new java.awt.Color(0, 0, 255));
        jLabel121.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel121.setText("Tích điểm:");
        jLabel121.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardHoaDon.add(jLabel121, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 160, -1, -1));

        txtTichDiem.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTichDiem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardHoaDon.add(txtTichDiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 150, 140, 30));

        btnSuDungDiem.setBackground(new java.awt.Color(51, 255, 255));
        btnSuDungDiem.setBorder(null);
        btnSuDungDiem.setText("Sử dụng");
        btnSuDungDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuDungDiemActionPerformed(evt);
            }
        });
        cardHoaDon.add(btnSuDungDiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 240, 70, 30));

        jLabel65.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel65.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/credit-card.png"))); // NOI18N
        jLabel65.setText("Tích điểm:");
        cardHoaDon.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 110, 30));

        lblTichDiemHD.setFont(new java.awt.Font("Times New Roman", 1, 17)); // NOI18N
        lblTichDiemHD.setForeground(new java.awt.Color(255, 0, 0));
        lblTichDiemHD.setText("0");
        cardHoaDon.add(lblTichDiemHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 220, 30));

        cardTrangChu.add(cardHoaDon, "card12");

        cardChiTietHoaDon.setBackground(new java.awt.Color(255, 255, 255));
        cardChiTietHoaDon.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel53.setText("<html> <h2>Phần mềm được phát triển bởi <a href=\"https://www.facebook.com/davisy.dev\">DAVISY TEAM</a></h2>  <!html>");
        cardChiTietHoaDon.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 500, -1));

        jLabel54.setText("<html>  <h3> Phần mềm là dự án trong học kỳ 4 của chúng tôi tại  <a href=\"\">FPT Polytechnic College</a> </h3>  <!html>");
        cardChiTietHoaDon.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, 380, -1));

        jLabel55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/businessman.png"))); // NOI18N
        cardChiTietHoaDon.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, -1, -1));

        jLabel56.setText("<html>\n<h3>Các tư liệu tham khảo</h3>  \n<ul>  \n <li> <h4> <a href=\"https://github.com/k33ptoo/\">KeepToo</a></h4></li>  \n<li> <h4><a href=\"https://github.com/DJ-Raven\">DJ-Raven</a></h4>\n</li>\n </ul> \n <!html>");
        cardChiTietHoaDon.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, 320, 110));

        jLabel57.setText("<html>  <h3>Bạn có thể tham khảo giao diện của chúng tôi tại <a href=\"https://www.github.com/theanishtar\"> đây</a> </h3>  <!html>");
        cardChiTietHoaDon.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 450, -1));

        cardTrangChu.add(cardChiTietHoaDon, "card13");

        cardGioiThieuSanPham.setBackground(new java.awt.Color(255, 255, 255));
        cardGioiThieuSanPham.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("<html> <h2>Phần mềm được phát triển bởi <a href=\"https://www.facebook.com/davisy.dev\">DAVISY TEAM</a></h2>  <!html>");
        cardGioiThieuSanPham.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 500, -1));

        jLabel3.setText("<html>  <h3> Phần mềm là dự án trong học kỳ 4 của chúng tôi tại  <a href=\"\">FPT Polytechnic College</a> </h3>  <!html>");
        cardGioiThieuSanPham.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, 380, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/businessman.png"))); // NOI18N
        cardGioiThieuSanPham.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, -1, -1));

        jLabel5.setText("<html>\n<h3>Các chức năng chính của phần mềm:</h3>  \n<ul>  \n <li> <h4> Quản lý sản phẩm (Quản lý sản phẩm, hãng sản xuất, loại hàng)</h4></li>  \n<li> <h4>Quản lý bán hàng</h4></li>\n<li> <h4>Quản lý tài khoản (Nhân viên, chức vụ, khách hàng)</h4></li>\n<li> <h4>Quên mật khẩu</h4></li>\n<li> <h4>Thống kê (Doanh thu và sản phẩm bán chạy)</h4></li>\n<li> <h4>Phần mềm được hỗ trợ bảo mật đăng nhập và phân quyền cụ thể: Admin, Quản lý, Nhân viên,...</h4></li>\n </ul> \n <!html>");
        cardGioiThieuSanPham.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 190, 610, 280));

        jLabel6.setText("<html>  <h3>Bạn có thể tham khảo giao diện của chúng tôi tại <a href=\"https://www.github.com/theanishtar\"> đây</a> </h3>  <!html>");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        cardGioiThieuSanPham.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 480, 450, -1));

        cardTrangChu.add(cardGioiThieuSanPham, "card14");

        cardGioiThieuThanhVien.setBackground(new java.awt.Color(255, 255, 255));
        cardGioiThieuThanhVien.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setText("<html> <h2>Phần mềm được phát triển bởi <a href=\"https://www.facebook.com/davisy.dev\">DAVISY TEAM</a></h2>  <!html>");
        cardGioiThieuThanhVien.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 500, -1));

        jLabel28.setText("<html>  <h3> Phần mềm là dự án trong học kỳ 4 của chúng tôi tại  <a href=\"\">FPT Polytechnic College</a> </h3>  <!html>");
        cardGioiThieuThanhVien.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 380, -1));

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/businessman.png"))); // NOI18N
        cardGioiThieuThanhVien.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, -1, -1));

        jLabel34.setText("<html>\n<h3>Các tư liệu tham khảo</h3>  \n<ul>  \n <li> <h4> <a href=\"https://github.com/k33ptoo/\">KeepToo</a></h4></li>  \n<li> <h4><a href=\"https://github.com/DJ-Raven\">DJ-Raven</a></h4>\n</li>\n </ul> \n <!html>");
        cardGioiThieuThanhVien.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, 320, 110));

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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblKhachHangMousePressed(evt);
            }
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

        pnMarginKhachHangTitle.setBackground(new java.awt.Color(204, 204, 255));
        pnMarginKhachHangTitle.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnNextKH.setBackground(new java.awt.Color(204, 204, 255));
        btnNextKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/arrow-right.png"))); // NOI18N
        btnNextKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextKHActionPerformed(evt);
            }
        });
        pnMarginKhachHangTitle.add(btnNextKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 11, -1, -1));

        btnFirstKH.setBackground(new java.awt.Color(204, 204, 255));
        btnFirstKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/previous.png"))); // NOI18N
        btnFirstKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstKHActionPerformed(evt);
            }
        });
        pnMarginKhachHangTitle.add(btnFirstKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 11, -1, -1));

        btnPrevKH.setBackground(new java.awt.Color(204, 204, 255));
        btnPrevKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/left-arrow.png"))); // NOI18N
        btnPrevKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevKHActionPerformed(evt);
            }
        });
        pnMarginKhachHangTitle.add(btnPrevKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 11, -1, -1));

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
        pnMarginKhachHangTitle.add(btnLastKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 11, -1, -1));

        lblTimKiemKH.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        lblTimKiemKH.setForeground(new java.awt.Color(153, 153, 153));
        lblTimKiemKH.setText(" Nguyễn Văn An");
        lblTimKiemKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTimKiemKHMouseClicked(evt);
            }
        });
        pnMarginKhachHangTitle.add(lblTimKiemKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 20, 210, 30));

        txtTimKiemKH.setBackground(new java.awt.Color(204, 204, 255));
        txtTimKiemKH.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTimKiemKH.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemKHCaretUpdate(evt);
            }
        });
        pnMarginKhachHangTitle.add(txtTimKiemKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 10, 210, 40));

        jLabel58.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(204, 0, 51));
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel58.setText("DANH SÁCH KHÁCH HÀNG");
        pnMarginKhachHangTitle.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 330, 60));

        cbotimKiemKH.setBackground(new java.awt.Color(204, 204, 255));
        cbotimKiemKH.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Tên khách hàng", "Số điện thoại" }));
        cbotimKiemKH.setLabeText("Tìm kiếm theo");
        pnMarginKhachHangTitle.add(cbotimKiemKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 10, 180, 40));

        cardKhachHang.add(pnMarginKhachHangTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 1150, 60));

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

        cboKh.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Mã khách hàng", "Tên khách hàng" }));
        cboKh.setLabeText("Sắp xếp theo");
        cboKh.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboKhItemStateChanged(evt);
            }
        });

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
                .addGap(0, 14, Short.MAX_VALUE)
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
                .addComponent(cboKh, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnxoaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnlamMoiKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncapNhatKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnlamMoiKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThemKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btncapNhatKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnxoaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        cardKhachHang.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 30, 390, 240));

        jLabel62.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel62.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/visits (1).png"))); // NOI18N
        jLabel62.setText("Bảng ghi:");
        cardKhachHang.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 540, 110, 40));

        lblrecordKH.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lblrecordKH.setForeground(new java.awt.Color(204, 0, 51));
        cardKhachHang.add(lblrecordKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 540, 120, 40));

        lblHovaTenKH.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lblHovaTenKH.setForeground(new java.awt.Color(0, 0, 255));
        lblHovaTenKH.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblHovaTenKH.setText("Họ và tên:");
        lblHovaTenKH.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardKhachHang.add(lblHovaTenKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 150, 30));

        txthoTen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txthoTen.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardKhachHang.add(txthoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 220, 30));

        txtSDT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSDT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardKhachHang.add(txtSDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, 230, 30));

        lblSDTKH.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lblSDTKH.setForeground(new java.awt.Color(0, 0, 255));
        lblSDTKH.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblSDTKH.setText("Số điện thoại:");
        lblSDTKH.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardKhachHang.add(lblSDTKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, 160, 30));

        lblMaKH.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lblMaKH.setForeground(new java.awt.Color(0, 0, 255));
        lblMaKH.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMaKH.setText("Mã khách hàng:");
        lblMaKH.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardKhachHang.add(lblMaKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 150, 30));

        txtmaKH.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtmaKH.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardKhachHang.add(txtmaKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 220, 30));

        txtdiaChi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtdiaChi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cardKhachHang.add(txtdiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 240, 240, 30));

        lblDiaChiKH.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lblDiaChiKH.setForeground(new java.awt.Color(0, 0, 255));
        lblDiaChiKH.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDiaChiKH.setText("Địa chỉ:");
        lblDiaChiKH.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cardKhachHang.add(lblDiaChiKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 200, 170, 30));

        cardTrangChu.add(cardKhachHang, "card16");

        cardCaiDatDuongDan.setBackground(new java.awt.Color(255, 255, 255));
        cardCaiDatDuongDan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnCaiDatDuongDan.setBackground(new java.awt.Color(255, 255, 255));
        pnCaiDatDuongDan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitleSaveQRProduct.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblTitleSaveQRProduct.setForeground(new java.awt.Color(0, 0, 204));
        lblTitleSaveQRProduct.setText("Thư mục lưu đường dẫn mã QR sản phẩm: ");
        pnCaiDatDuongDan.add(lblTitleSaveQRProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, 590, 29));

        txtSaveQrProduct.setEditable(false);
        txtSaveQrProduct.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSaveQrProduct.setText("src\\\\com\\\\product");
        pnCaiDatDuongDan.add(txtSaveQrProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, 593, 30));

        lblOpenQRProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/open.png"))); // NOI18N
        lblOpenQRProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblOpenQRProductMouseClicked(evt);
            }
        });
        pnCaiDatDuongDan.add(lblOpenQRProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 120, 41, 46));

        lblOpenBannerImg.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblOpenBannerImg.setForeground(new java.awt.Color(0, 0, 204));
        lblOpenBannerImg.setText("Đường dẫn banner ảnh trang chính:");
        pnCaiDatDuongDan.add(lblOpenBannerImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 260, 590, 29));

        txtBanerPathImg1.setEditable(false);
        txtBanerPathImg1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        pnCaiDatDuongDan.add(txtBanerPathImg1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 320, 589, 30));

        lblOpenBannerImg1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/open.png"))); // NOI18N
        lblOpenBannerImg1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblOpenBannerImg1MouseClicked(evt);
            }
        });
        pnCaiDatDuongDan.add(lblOpenBannerImg1, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 310, 41, 38));

        lblOpenBannerImg2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/open.png"))); // NOI18N
        lblOpenBannerImg2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblOpenBannerImg2MouseClicked(evt);
            }
        });
        pnCaiDatDuongDan.add(lblOpenBannerImg2, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 370, 41, 38));

        txtBanerPathImg2.setEditable(false);
        txtBanerPathImg2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        pnCaiDatDuongDan.add(txtBanerPathImg2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 380, 589, 29));

        txtBanerPathImg3.setEditable(false);
        txtBanerPathImg3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        pnCaiDatDuongDan.add(txtBanerPathImg3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 440, 589, 30));

        lblOpenBannerImg3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/open.png"))); // NOI18N
        lblOpenBannerImg3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblOpenBannerImg3MouseClicked(evt);
            }
        });
        pnCaiDatDuongDan.add(lblOpenBannerImg3, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 430, 41, 50));

        lblCDDD1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblCDDD1.setText("1:");
        pnCaiDatDuongDan.add(lblCDDD1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 320, 32, 30));

        lblCDDD2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblCDDD2.setText("2:");
        pnCaiDatDuongDan.add(lblCDDD2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 380, 32, 30));

        lblCDDD3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblCDDD3.setText("3:");
        pnCaiDatDuongDan.add(lblCDDD3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 440, 32, 30));

        btnResetSaveBannerImg.setBackground(new java.awt.Color(204, 204, 255));
        btnResetSaveBannerImg.setLabel("Khôi phục");
        btnResetSaveBannerImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetSaveBannerImgActionPerformed(evt);
            }
        });
        pnCaiDatDuongDan.add(btnResetSaveBannerImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 500, 100, -1));

        btnResetSaveQRProduct.setBackground(new java.awt.Color(204, 204, 255));
        btnResetSaveQRProduct.setLabel("Khôi phục");
        btnResetSaveQRProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetSaveQRProductActionPerformed(evt);
            }
        });
        pnCaiDatDuongDan.add(btnResetSaveQRProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 190, 100, -1));

        cardCaiDatDuongDan.add(pnCaiDatDuongDan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1220, 590));

        cardTrangChu.add(cardCaiDatDuongDan, "card15");

        cardCaiDatGiaoDien.setBackground(new java.awt.Color(255, 255, 255));
        cardCaiDatGiaoDien.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnSetMode.setBackground(new java.awt.Color(255, 255, 255));
        pnSetMode.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(0, 0, 0)));
        pnSetMode.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDarkmode.setBackground(new java.awt.Color(255, 255, 255));
        lblDarkmode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDarkmodeMouseClicked(evt);
            }
        });
        pnSetMode.add(lblDarkmode, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, 280, 190));

        lblLightmode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLightmodeMouseClicked(evt);
            }
        });
        pnSetMode.add(lblLightmode, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 280, 190));

        darkMode1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        pnSetMode.add(darkMode1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, -1, -1));

        lightMode1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 204, 204), 1, true));
        pnSetMode.add(lightMode1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));

        rdoDarkmode.setBackground(new java.awt.Color(255, 255, 255));
        rdoGMode.add(rdoDarkmode);
        rdoDarkmode.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdoDarkmode.setText("   Dark mode      ");
        rdoDarkmode.setBorder(null);
        rdoDarkmode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoDarkmodeActionPerformed(evt);
            }
        });
        pnSetMode.add(rdoDarkmode, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 520, 190, 30));

        rdoLightmode.setBackground(new java.awt.Color(255, 255, 255));
        rdoGMode.add(rdoLightmode);
        rdoLightmode.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdoLightmode.setSelected(true);
        rdoLightmode.setText("   Light mode      ");
        rdoLightmode.setBorder(null);
        rdoLightmode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoLightmodeActionPerformed(evt);
            }
        });
        pnSetMode.add(rdoLightmode, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 231, 190, 30));

        cardCaiDatGiaoDien.add(pnSetMode, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 380, 570));

        pnReviewMode.setBackground(new java.awt.Color(255, 255, 255));

        pbMarginReviewMode.setBackground(new java.awt.Color(255, 255, 255));
        pbMarginReviewMode.setPreferredSize(new java.awt.Dimension(800, 570));
        pbMarginReviewMode.setLayout(new java.awt.CardLayout());
        pbMarginReviewMode.add(pnReviewLightMode, "card3");
        pbMarginReviewMode.add(pnReviewDarkMode, "card2");

        javax.swing.GroupLayout pnReviewModeLayout = new javax.swing.GroupLayout(pnReviewMode);
        pnReviewMode.setLayout(pnReviewModeLayout);
        pnReviewModeLayout.setHorizontalGroup(
            pnReviewModeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnReviewModeLayout.createSequentialGroup()
                .addComponent(pbMarginReviewMode, javax.swing.GroupLayout.PREFERRED_SIZE, 812, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );
        pnReviewModeLayout.setVerticalGroup(
            pnReviewModeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnReviewModeLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(pbMarginReviewMode, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );

        cardCaiDatGiaoDien.add(pnReviewMode, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 820, 570));

        cardTrangChu.add(cardCaiDatGiaoDien, "card15");

        jplContainer.add(cardTrangChu, "card2");

        pnMain.add(jplContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1220, 590));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        drawer.show();
        //openMenu();
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
        boolean chon = MsgBox.confirm(this, "Bạn có chắc muốn thoát không?");
        if (chon) {
            System.exit(0);
        }

    }//GEN-LAST:event_jlbCloseFocusGained

    private void jlbCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbCloseMouseClicked
        boolean chon = MsgBox.confirm(this, "Bạn có chắc muốn thoát không?");
        if (chon) {
            System.exit(0);
        }
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
        if (pnMenu.getWidth() == 300) {
            closeMenu();
        }
        if (chooserMainPage == true) {
            this.threadClock = true;
            Clock();
        }
    }//GEN-LAST:event_opacityMousePressed

    private void btnSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSanPhamActionPerformed

        chooserMainPage = false;
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
        chooserMainPage = true;
        this.threadClock = true;
        Clock();
    }//GEN-LAST:event_btnTrangChuActionPerformed

    private void btnTaiKhoanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTaiKhoanMouseEntered
        if ("Quản lí".equalsIgnoreCase(ktCV) || "Admin".equalsIgnoreCase(ktCV)) {
            hoverMenuItem(btnTaiKhoan);
        }
    }//GEN-LAST:event_btnTaiKhoanMouseEntered

    private void btnTaiKhoanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTaiKhoanMouseExited
        if ("Quản lí".equalsIgnoreCase(ktCV) || "Admin".equalsIgnoreCase(ktCV)) {
            nonHoverMenuItem(btnTaiKhoan);
        }
    }//GEN-LAST:event_btnTaiKhoanMouseExited

    private void btnTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaiKhoanActionPerformed
        chooserMainPage = false;
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
        chooserMainPage = false;
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

        chooserMainPage = false;
        btnItemMenu = btnHoaDon;
        chooserMenu(4);
        setDefaultItemMenu();
        hoverMenuItem(btnHoaDon);
    }//GEN-LAST:event_btnHoaDonActionPerformed

    private void btnGioiThieuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGioiThieuMouseEntered
        hoverMenuItem(btnGioiThieu);
    }//GEN-LAST:event_btnGioiThieuMouseEntered

    private void btnGioiThieuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGioiThieuMouseExited
        nonHoverMenuItem(btnGioiThieu);
    }//GEN-LAST:event_btnGioiThieuMouseExited

    private void btnGioiThieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGioiThieuActionPerformed
        GioiThieuHr2.setVisible(false);
        GioiThieuHr1.setVisible(true);
        chooserMainPage = false;
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
        //HoaDonHr1.setVisible(true);
        //setLocationHr(cardHoaDon, HoaDonHr1, HoaDonTittle1);
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
        if ("Quản lí".equalsIgnoreCase(ktCV) || "Admin".equalsIgnoreCase(ktCV)) {
            chooserMainPage = false;
            btnItemMenu = btnThongKe;
            chooserMenu(8);
            setDefaultItemMenu();
            hoverMenuItem(btnThongKe);
        } else {
            chooserMainPage = false;
            btnItemMenu = btnThongKe;
            chooserMenu(8);
            setDefaultItemMenu();
            hoverMenuItem(btnThongKe);
            ThongKeTittle1.setVisible(false);
            ThongKeHr1.setVisible(false);
            cardThongKeDoanhThu.setVisible(false);
            cardThongKeSanPham.setVisible(true);
            ThongKeHr2.setVisible(true);
        }

    }//GEN-LAST:event_btnThongKeActionPerformed

    private void btnLastKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastKHActionPerformed
        lastKhachHang();
    }//GEN-LAST:event_btnLastKHActionPerformed

    private void btnLastLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastLoaiActionPerformed
        lastLoai();
    }//GEN-LAST:event_btnLastLoaiActionPerformed

    private void btnLastHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastHangActionPerformed
        lastHang();
    }//GEN-LAST:event_btnLastHangActionPerformed

    private void btnLastSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastSPActionPerformed
        lastSanPham();
    }//GEN-LAST:event_btnLastSPActionPerformed

    private void btnLastNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastNVActionPerformed
        lastNV();
    }//GEN-LAST:event_btnLastNVActionPerformed

    private void TaiKhoantittle2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TaiKhoantittle2MousePressed
        TaiKhoanHr1.setVisible(false);
        TaiKhoanHr2.setVisible(true);
        TaiKhoanHr3.setVisible(false);
        setLocationHr(cardKhachHang, TaiKhoanHr2, TaiKhoantittle2);
    }//GEN-LAST:event_TaiKhoantittle2MousePressed

    private void btnLastCVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastCVActionPerformed
        lastChucVu();
    }//GEN-LAST:event_btnLastCVActionPerformed

    private void tblHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHangMouseClicked
        countClick++;
        if (countClick == 1) {
            tblHang.setDefaultEditor(Object.class, null);
            this.row = tblHang.getSelectedRow();
            lblMaHangTemp.setVisible(true);
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
        if (darkMode == true) {
            if (checkHangdark() == true) {
                insertHang();
            }
        } else {
            if (checkHanglight() == true) {
                insertHang();
            }
        }
    }//GEN-LAST:event_btnThemHangActionPerformed

    private void btnCapNhatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatHangActionPerformed
        if (darkMode == true) {
            if (checkHangdark() == true) {
                updateHang();
            }
        } else {
            if (checkHanglight() == true) {
                updateHang();
            }
        }
    }//GEN-LAST:event_btnCapNhatHangActionPerformed

    private void btnXoaHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaHangActionPerformed
        boolean chon = MsgBox.confirm(this, "Bạn có chắc muốn xóa không?");
        if (chon) {
            deleteHang();
        }

    }//GEN-LAST:event_btnXoaHangActionPerformed

    private void btnLamMoiLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiLoaiActionPerformed
        clearFormLoai();
    }//GEN-LAST:event_btnLamMoiLoaiActionPerformed

    private void btnThemLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemLoaiActionPerformed
        if (darkMode == true) {
            if (checkLoaidark() == true) {
                insertLoai();
            }
        } else {
            if (checkLoailight() == true) {
                insertLoai();
            }
        }
    }//GEN-LAST:event_btnThemLoaiActionPerformed

    private void btnCapNhatLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatLoaiActionPerformed
        if (darkMode == true) {
            if (checkLoaidark() == true) {
                updateLoai();
            }
        } else {
            if (checkLoailight() == true) {
                updateLoai();
            }
        }
    }//GEN-LAST:event_btnCapNhatLoaiActionPerformed

    private void btnXoaLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaLoaiActionPerformed
        boolean chon = MsgBox.confirm(this, "Bạn có chắc muốn xóa không?");
        if (chon) {
            deleteLoai();
        }

    }//GEN-LAST:event_btnXoaLoaiActionPerformed

    private void btnNextLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextLoaiActionPerformed
        nextLoai();
    }//GEN-LAST:event_btnNextLoaiActionPerformed

    private void tblLoaiHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLoaiHangMouseClicked
        countClick++;
        if (countClick == 1) {
            tblLoaiHang.setDefaultEditor(Object.class, null);
            this.row = tblLoaiHang.getSelectedRow();
            editLoai();
            lblMaLoaiTemp.setVisible(true);
        }
    }//GEN-LAST:event_tblLoaiHangMouseClicked

    private void btnLastLoaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLastLoaiMouseClicked
        lastLoai();
    }//GEN-LAST:event_btnLastLoaiMouseClicked

    private void btnNextLoaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNextLoaiMouseClicked
//        nextLoai();
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
        if (darkMode == true) {
            if (checkSPdark() == true) {
                insertSanPham();
            }
        } else {
            if (checkSPlight() == true) {
                insertSanPham();
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemSPActionPerformed

    private void btnCapNhatSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatSPActionPerformed
        if (darkMode == true) {
            if (checkSPdark() == true) {
                updateSanPham();
            }
        } else {
            if (checkSPlight() == true) {
                updateSanPham();
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCapNhatSPActionPerformed

    private void btnXoaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSPActionPerformed
        boolean chon = MsgBox.confirm(this, "Bạn có chắc muốn xóa không?");
        if (chon) {
            deleteSanPham();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaSPActionPerformed

    private void tblSanPhamMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblSanPhamMousePressed

    private void tblSanPhamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseReleased
        countClick++;
        if (countClick == 1) {
            tblSanPham.setDefaultEditor(Object.class, null);
            this.row = tblSanPham.getSelectedRow();
            editSanPham();
            lblNgayNhaptemp.setVisible(true);
            lblMaSPTemp.setVisible(true);
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

    private void lblHangTempMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHangTempMouseClicked
        lblHangTemp.setVisible(false);
        txtTimKiemHang.requestFocus();
    }//GEN-LAST:event_lblHangTempMouseClicked

    private void txtTimKiemHangCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemHangCaretUpdate
        timKiemhang();
    }//GEN-LAST:event_txtTimKiemHangCaretUpdate

    private void txtTimKiemHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemHangMouseClicked
        lblHangTemp.setVisible(false);
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

    private void cboSXSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboSXSPMouseClicked
//        btnSP();
    }//GEN-LAST:event_cboSXSPMouseClicked

    private void btnazspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnazspActionPerformed
        SortSP(0);
    }//GEN-LAST:event_btnazspActionPerformed

    private void btnzaspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnzaspActionPerformed
        SortSP(1);
    }//GEN-LAST:event_btnzaspActionPerformed

    private void cboSXSPItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboSXSPItemStateChanged
        btnSP();
    }//GEN-LAST:event_cboSXSPItemStateChanged

//    public void addCartNow(){
//        GioHangTamEntity gh=GioHangtam.selectById(txtSdtKH.getText());
////        for(GioHangTamEntity ghe: gh){
//            listGioHangHienTai.addAll(gh.);
////        }
//        
//    }

    private void btnXNKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXNKHActionPerformed
        if (txtSdtKH.getText().equals("")) {
            return;
        }
        listGHT = GioHangtam.selectAll();
        filltableGioHang();
//        }

//        ScanQRProduct fr = new ScanQRProduct();
//        fr.setVisible(true);
    }//GEN-LAST:event_btnXNKHActionPerformed

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
        if (darkMode == true) {
            if (checkKHdark() == true) {
                insertKhachHang();
            }
        } else {
            if (checkKHlight() == true) {
                insertKhachHang();
            }
        }
    }//GEN-LAST:event_btnThemKhachHangActionPerformed

    private void btncapNhatKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncapNhatKhachHangActionPerformed
        if (darkMode == true) {
            if (checkKHdark() == true) {
                updateKhachHang();
            }
        } else {
            if (checkKHlight() == true) {
                updateKhachHang();
            }
        }
    }//GEN-LAST:event_btncapNhatKhachHangActionPerformed

    private void btnxoaKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaKhachHangActionPerformed
        boolean chon = MsgBox.confirm(this, "Bạn có chắc muốn xóa không?");
        if (chon) {
            deleteKhachHang();
        }

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
            tblSanPham.setDefaultEditor(Object.class, null);
            this.row = tblKhachHang.getSelectedRow();
            txtmaKH.setEditable(false);
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

    private void sbtnTrangThaiNVMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sbtnTrangThaiNVMouseReleased
        setTrangThaiHoatDong();
    }//GEN-LAST:event_sbtnTrangThaiNVMouseReleased

    private void btnLamMoiNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiNVActionPerformed
        clearFormNV();
    }//GEN-LAST:event_btnLamMoiNVActionPerformed

    private void btnThemNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNVActionPerformed
        if (darkMode == true) {
            if (checkNhanViendark() == true) {
                insertNV();
            }
        } else {
            if (checkNhanVienlight() == true) {
                insertNV();
            }
        }
    }//GEN-LAST:event_btnThemNVActionPerformed

    private void btnCapNhatNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatNVActionPerformed
        if (darkMode == true) {
            if (checkNhanViendark() == true) {
                updateNV();
            }
        } else {
            if (checkNhanVienlight() == true) {
                updateNV();
            }
        }
    }//GEN-LAST:event_btnCapNhatNVActionPerformed

    private void btnXoaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNVActionPerformed
        boolean chon = MsgBox.confirm(this, "Bạn có chắc muốn xóa không?");
        if (chon) {
            deleteNV();
        }

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
            tblNhanVien.setDefaultEditor(Object.class, null);

            editNV();
        }
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void btnxoaGioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaGioHangActionPerformed
        String masp = (String) tblCart.getValueAt(this.row, 0);
        if (!masp.equals("")) {
            deleteGH();
            return;
        } else {
            MsgBox.alert(this, "Vui lòng chọn sản phẩm!");
        }
    }//GEN-LAST:event_btnxoaGioHangActionPerformed

    private void txtTimKiemhdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemhdMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemhdMouseClicked

    private void txtTimKiemhdCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemhdCaretUpdate
        timKiemHD();
    }//GEN-LAST:event_txtTimKiemhdCaretUpdate

    private void lblTimKiemHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTimKiemHDMouseClicked
        lblTimKiemHD.setVisible(false);
        txtTimKiemhd.requestFocus();
    }//GEN-LAST:event_lblTimKiemHDMouseClicked

    private void btnLasthdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLasthdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLasthdActionPerformed

    private void btnLasthdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLasthdMouseClicked
        lastHoaDon();
    }//GEN-LAST:event_btnLasthdMouseClicked

    private void btnPrevhdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevhdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPrevhdActionPerformed

    private void btnPrevhdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrevhdMouseClicked
        prevHoaDon();
    }//GEN-LAST:event_btnPrevhdMouseClicked

    private void btnFirsthdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirsthdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFirsthdActionPerformed

    private void btnFirsthdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFirsthdMouseClicked
        firstHoaDon();
    }//GEN-LAST:event_btnFirsthdMouseClicked

    private void btnNexthdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNexthdMouseClicked
        nextHoaDon();
    }//GEN-LAST:event_btnNexthdMouseClicked

    private void btnInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInActionPerformed

        if (txtTENKH.getText().equals("")) {
            MsgBox.alert(this, "Vui lòng chọn hóa đơn!");
            return;
        } else {
            listCtHD = HDCT.selectAll();
            String hoadon = String.valueOf(tblHoaDon.getValueAt(tblHoaDon.getSelectedRow(), 0));
            HoaDonEntity hdct = HoaDon.selectById(hoadon);
            float tien = hdct.getThanhTien();
            if (txtTienNhan.getText().equals("")) {
                MsgBox.alert(this, "Vui lòng nhập tiền nhận!");
                txtTienNhan.requestFocus();
                return;
            } else if (Float.valueOf(txtTienNhan.getText()) > tien) {
//            tblHoaDon.getValueAt(tblHoaDon.getSelectedRow(), 0);
                String mahd = null;
                for (HoaDonCTEntity hd : listCtHD) {
                    if (String.valueOf(tblHoaDon.getValueAt(tblHoaDon.getSelectedRow(), 0)).equalsIgnoreCase(hd.getMaHD())) {
                        tenSP.add(hd.getTenSP());
                        giaSP.add(String.valueOf(hd.getGiaBan()));
                        SL.add(String.valueOf(hd.getSl()));
                        thanhTien.add(String.valueOf(hd.getTongTien()));
                        mahd = hd.getMaHD();
                    }
                }
                HoaDonEntity hd = HoaDon.selectById(mahd);
                tienGiam = hd.getTienGiam();

                bHeight = Double.valueOf(tenSP.size());
                //JOptionPane.showMessageDialog(rootPane, bHeight);

                PrinterJob pj = PrinterJob.getPrinterJob();
                pj.setPrintable(new BillPrintable(), getPageFormat(pj));
                try {
                    pj.print();
                    MsgBox.alert(this, "In hóa đơn thành công!");
                    clearHD();
                } catch (PrinterException ex) {
                    ex.printStackTrace();
                }
            } else {
                MsgBox.alert(this, "Tiền nhận phải lớn hơn tiền thanh toán!");
                txtTienNhan.requestFocus();
                return;
            }
        }
    }//GEN-LAST:event_btnInActionPerformed

    private void btnzahdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnzahdActionPerformed
        SortHoaDon(1);
    }//GEN-LAST:event_btnzahdActionPerformed

    private void btnazhdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnazhdActionPerformed
        SortHoaDon(0);
    }//GEN-LAST:event_btnazhdActionPerformed

    private void btnXoahdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoahdActionPerformed
        boolean chon = MsgBox.confirm(this, "Bạn có chắc muốn xóa không?");
        if (chon) {
            deleteHoaDon();
        }

    }//GEN-LAST:event_btnXoahdActionPerformed

    private void tblHoaDonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseReleased
        countClick++;
        if (countClick == 1) {
            tblHoaDon.setDefaultEditor(Object.class, null);
            this.row = tblHoaDon.getSelectedRow();
            editHoaDon();
        }

    }//GEN-LAST:event_tblHoaDonMouseReleased

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked

    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void comboboxHDItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboboxHDItemStateChanged
        btnHD();
    }//GEN-LAST:event_comboboxHDItemStateChanged

    private void btnFirstCVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstCVActionPerformed
        firsChucVu();
    }//GEN-LAST:event_btnFirstCVActionPerformed

    private void tblChucVuMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChucVuMouseReleased
        countClick++;
        if (countClick == 1) {
            this.row = tblChucVu.getSelectedRow();
            tblChucVu.setDefaultEditor(Object.class, null);
            editChucVu();
        }
    }//GEN-LAST:event_tblChucVuMouseReleased

    private void switchButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_switchButton1MousePressed
        if (!switchButton1.isSelected()) {
            initWebcam();
            cardHoaDonQR.setVisible(true);
            cardHoaDonSanPham.setVisible(false);
            txtFindNameProductCart.setVisible(false);
        } else {
            openWebCame();
        }
    }//GEN-LAST:event_switchButton1MousePressed

    private void txtSdtKHCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSdtKHCaretUpdate
        sdtKH(txtSdtKH.getText());


    }//GEN-LAST:event_txtSdtKHCaretUpdate

    private void spnSLStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnSLStateChanged
//Update số lượng sản phẩm trong giỏ hàng
        if (this.row > -1) {
            String masp = (String) tblCart.getValueAt(this.row, 0);
            if (!masp.equals("")) {
                if ((int) spnSL.getValue() == 0) {
                    deleteGH();
                    spnSL.setEnabled(false);
                    return;
                } else {
                    updategh((int) spnSL.getValue());
                    return;
                }
            } else {
                MsgBox.alert(this, "Vui lòng chọn sản phẩm!");
            }
        }
    }//GEN-LAST:event_spnSLStateChanged

    private void tblCartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCartMouseClicked
        countClick++;
        if (countClick == 1) {;
            this.row = tblCart.getSelectedRow();
            spnSL.setValue((int) tblCart.getValueAt(this.row, 3));
            editGH();
            btnxoaGioHang.setEnabled(true);
            spnSL.setEnabled(true);
        }
    }//GEN-LAST:event_tblCartMouseClicked

    private void btnXacNhanDonHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanDonHangActionPerformed
        insertHoaDon();
        setDataPieChart(pnlChart);
        setDataBarChart(pnlChartDT);
    }//GEN-LAST:event_btnXacNhanDonHangActionPerformed

    private void txtTongtienspCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTongtienspCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongtienspCaretUpdate

    private void lblTimKiemKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTimKiemKHMouseClicked
        lblTimKiemKH.setVisible(false);
        txtTimKiemKH.requestFocus();
    }//GEN-LAST:event_lblTimKiemKHMouseClicked

    private void lblTimKiemNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTimKiemNVMouseClicked
        lblTimKiemNV.setVisible(false);
        txtTimKiemNV.requestFocus();
    }//GEN-LAST:event_lblTimKiemNVMouseClicked

    private void txtTimKiemNVCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemNVCaretUpdate
        timKiemNV();
    }//GEN-LAST:event_txtTimKiemNVCaretUpdate

    private void lblTimKiemCVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTimKiemCVMouseClicked
        lblTimKiemCV.setVisible(false);
        txtTimCV.requestFocus();
    }//GEN-LAST:event_lblTimKiemCVMouseClicked

    private void txtTimCVCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimCVCaretUpdate
        timKiemCV();
    }//GEN-LAST:event_txtTimCVCaretUpdate

    private void btnazNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnazNVActionPerformed
        SortNV(0);
    }//GEN-LAST:event_btnazNVActionPerformed

    private void btnzanvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnzanvActionPerformed
        SortNV(1);
    }//GEN-LAST:event_btnzanvActionPerformed

    private void btnIn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnIn1ActionPerformed

    private void txtTienNhanCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTienNhanCaretUpdate
//        if (txtTienNhan.getText().equals(null) || txtTENKH.getText().equals("")) {
//            btnIn.setEnabled(false);
//        } else {
//            btnIn.setEnabled(true);
//
//        }
    }//GEN-LAST:event_txtTienNhanCaretUpdate

    private void txtPhanTramGGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhanTramGGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhanTramGGActionPerformed

    private void btnSuDungDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuDungDiemActionPerformed
        //Sử dụng tích điểm và phần trăm giảm giá
        KhachHangEntity kh = KhachHang.selectById(txtMAKH.getText());
        int tichDiem = 0;
        int diemSuDung = 0;
        int phanTram = 0;
        if (txtPhanTramGG.getText().equalsIgnoreCase("")) {
            txtPhanTramGG.setText("0");

        } else if (txtTichDiem.getText().equalsIgnoreCase("")) {
            txtTichDiem.setText("0");
        } else {

            try {
                if (Integer.valueOf(txtPhanTramGG.getText().trim()) < 0 || Integer.valueOf(txtPhanTramGG.getText().trim()) > 100) {
                    MsgBox.alert(this, "Vui lòng nhập giá trị từ 0 đến 100!");
                    txtPhanTramGG.requestFocus();
                    return;
                } else {
                    phanTram = Integer.valueOf(txtPhanTramGG.getText().trim());
                }
            } catch (Exception ex) {
                MsgBox.alert(this, "Vui lòng nhập phần trăm là số!");
                txtPhanTramGG.setText("");
                txtPhanTramGG.requestFocus();
                return;
            }
            try {
                if (Integer.valueOf(txtTichDiem.getText().trim()) < 0 || Integer.valueOf(txtTichDiem.getText().trim()) > kh.getTichDiem()) {
                    MsgBox.alert(this, "Vui lòng nhập giá trị từ 0 đến " + kh.getTichDiem() + "!");
                    txtTichDiem.requestFocus();
                    return;
                } else {
                    diemSuDung = Integer.valueOf(txtTichDiem.getText().trim());
                }
            } catch (Exception ex) {
                MsgBox.alert(this, "Vui lòng nhập tích điểm là số!");
                txtTichDiem.setText("");
                txtTichDiem.requestFocus();
                return;
            }
        }
//
//            if (!txtTichDiem.getText().equals("")) {

        String maHD = String.valueOf(tblHoaDon.getValueAt(tblHoaDon.getSelectedRow(), 0));
        HoaDonEntity hd = HoaDon.selectById(maHD);
        tichDiem = kh.getTichDiem() - diemSuDung;
        kh.setMaKH(txtMAKH.getText());
        kh.setTichDiem(tichDiem);
        float tinhTien = Float.valueOf(String.valueOf(tblHoaDon.getValueAt(tblHoaDon.getSelectedRow(), 6)));
        float thanhTien = tinhTien - tinhTien * Integer.valueOf(txtTichDiem.getText()) / 100 - tinhTien * phanTram / 100;
//            tichDiem =Integer.valueOf(txtTichDiem.getText()) + hd.getTichDiem();
        if (Integer.valueOf(txtPhanTramGG.getText().trim()) == 0) {
            thanhTien = hd.getTienGiam() + hd.getThanhTien();
        }
        if (Integer.valueOf(txtTichDiem.getText()) == 0) {
            tichDiem = kh.getTichDiem() + hd.getTichDiem();
            kh.setTichDiem(tichDiem);
            thanhTien = hd.getTienGiam() + hd.getThanhTien();
        }
        tichDiem = 0;
        try {
            KhachHang.updateTd(kh);
            hd.setPhanTramGG(phanTram);
            hd.setTichDiem(Integer.valueOf(txtTichDiem.getText()));
            hd.setThanhTien(thanhTien);
            hd.setMaHD(maHD);
            HoaDon.updateTT(hd);
            listHoaDon();
            listKhachHang();
            this.row = -1;
            txtPhanTramGG.setText("");
            txtTENKH.setText("");
            txtMAKH.setText("");
            txtTENNV.setText("");
            txtTichDiem.setText("");
            lblTichDiemHD.setText("");
            this.fillTableKhachHang();
            this.fillTableHoaDon();
//            MsgBox.alert(this, "Sử dụng thành công!");
        } catch (Exception e) {
            System.out.println(e);
        }
//        }
//        }

    }//GEN-LAST:event_btnSuDungDiemActionPerformed

    private void txtSdtKHKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSdtKHKeyPressed

    }//GEN-LAST:event_txtSdtKHKeyPressed

    private void lblImportFileExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblImportFileExcelActionPerformed
        ImportFileExcelThongKeSP();
    }//GEN-LAST:event_lblImportFileExcelActionPerformed

    private void btnThemCVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCVActionPerformed
        if (darkMode == true) {
            if (checkChucVudark() == true) {
                insertChucVu();
            }
        } else {
            if (checkChucVulight() == true) {
                insertChucVu();
            }
        }

    }//GEN-LAST:event_btnThemCVActionPerformed

    private void btnCapNhatCVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatCVActionPerformed
        if (darkMode == true) {
            if (checkChucVudark() == true) {
                updateChucVu();
            }
        } else {
            if (checkChucVulight() == true) {
                updateChucVu();
            }
        }
    }//GEN-LAST:event_btnCapNhatCVActionPerformed

    private void btnXoaCVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaCVActionPerformed
        boolean chon = MsgBox.confirm(this, "Bạn có chắc muốn xóa không?");
        if (chon) {
            deleteChucVu();
        }

    }//GEN-LAST:event_btnXoaCVActionPerformed

    private void btnLamMoiCVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiCVActionPerformed
        clearFormChucVu();
    }//GEN-LAST:event_btnLamMoiCVActionPerformed

    private void txtFindNameProductCartCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFindNameProductCartCaretUpdate
        findItemCart();
    }//GEN-LAST:event_txtFindNameProductCartCaretUpdate

    private void lblExportInforActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblExportInforActionPerformed
        exportInforProdct();
    }//GEN-LAST:event_lblExportInforActionPerformed

    private void tblCartKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblCartKeyPressed


    }//GEN-LAST:event_tblCartKeyPressed

    private void cboChoiceItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboChoiceItemStateChanged
        setDataBarChart(pnlChartDT);
    }//GEN-LAST:event_cboChoiceItemStateChanged

    private void cboChoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboChoiceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboChoiceActionPerformed

    private void tblCartInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tblCartInputMethodTextChanged

    }//GEN-LAST:event_tblCartInputMethodTextChanged

    private void tblCartFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblCartFocusGained
//        row = tblCart.getSelectedRow();
//        Object sl = tblCart.getValueAt(row, 3);
//        String k = String.valueOf(sl);
//          
//            String masp = (String) tblCart.getValueAt(this.row, 0);
//            if (!masp.equals("")) {
//                if (Integer.valueOf(k) == 0) {
//                    deleteGH();
//                    spnSL.setEnabled(false);
//                    return;
//                } else {
//                      System.out.println("hi");
////                    updategh(Integer.valueOf(k));
//                    return;
//                }
//            } else {
//                MsgBox.alert(this, "Vui lòng chọn sản phẩm!");
//            }
    }//GEN-LAST:event_tblCartFocusGained

    private void tblCartFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblCartFocusLost

    }//GEN-LAST:event_tblCartFocusLost

    private void tblCartKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblCartKeyTyped


    }//GEN-LAST:event_tblCartKeyTyped

    private void tblCartKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblCartKeyReleased

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            spnSL.setEnabled(false);

            row = tblCart.getSelectedRow();
            Object sl = tblCart.getValueAt(row, 3);
            String k = String.valueOf(sl);
            String masp = (String) tblCart.getValueAt(this.row, 0);
            if (!masp.equals("")) {
                if (Integer.valueOf(k) == 0) {
                    deleteGH();

                    return;
                } else {
                    updategh(Integer.valueOf(k));
                    this.row = -1;
                    spnSL.setValue(0);
                    return;
                }
            } else {
                MsgBox.alert(this, "Vui lòng chọn sản phẩm!");
            }
//            

        }
    }//GEN-LAST:event_tblCartKeyReleased

    private void panelRound5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRound5MouseClicked
        hidePage();
        //cardTaiKhoanCaNhan.setVisible(true);
        new MyAccount(this, true, ktTenDN).setVisible(true);
    }//GEN-LAST:event_panelRound5MouseClicked

    private void btnazCVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnazCVActionPerformed
        SortCV(0);
    }//GEN-LAST:event_btnazCVActionPerformed

    private void btnzaCVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnzaCVActionPerformed
        SortCV(1);
    }//GEN-LAST:event_btnzaCVActionPerformed

    private void cboKhItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboKhItemStateChanged

        String kh = (String) cboKh.getSelectedItem();
        if (kh.equalsIgnoreCase("")) {
            btnaz.setEnabled(false);
            btnza.setEnabled(false);
        } else {
            btnaz.setEnabled(true);
            btnza.setEnabled(true);
        }


    }//GEN-LAST:event_cboKhItemStateChanged

    private void cboCVItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboCVItemStateChanged
        String cv = (String) cboCV.getSelectedItem();
        if (cv.equalsIgnoreCase("")) {
            btnazCV.setEnabled(false);
            btnzaCV.setEnabled(false);
        } else {
            btnazCV.setEnabled(true);
            btnzaCV.setEnabled(true);
            if (cv.equalsIgnoreCase("Tên chức vụ")) {
                btnazCV.setText("A - Z");
                btnzaCV.setText("Z - A");
            } else {
                btnazCV.setText("Tăng");
                btnzaCV.setText("Giảm");
            }
        }
    }//GEN-LAST:event_cboCVItemStateChanged

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked

    }//GEN-LAST:event_tblKhachHangMouseClicked

    private void tblKhachHangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMousePressed
        tblKhachHang = (JTable) evt.getSource();
        if (evt.getClickCount() == 2) {
            tblSanPham.setDefaultEditor(Object.class, null);
            insertSDT = (String) tblKhachHang.getValueAt(this.row, 2);
//            hidePage();
//            hideMenu();
            cartShoping("");
            chooserMainPage = false;
            btnItemMenu = btnGioHang;
            chooserMenu(3);
            setDefaultItemMenu();
//        hoverMenuItem(btnGioHang);
//            cardMenubarGioHang.setVisible(true);
//            cardGioHang.setVisible(true);
            txtSdtKH.setText(insertSDT);
            listGHT = GioHangtam.selectAll();
            filltableGioHang();
            return;
        }        // TODO add your handling code here:
    }//GEN-LAST:event_tblKhachHangMousePressed

    private void tblHoaDonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMousePressed
        tblHoaDon = (JTable) evt.getSource();
        if (evt.getClickCount() == 2) {
            String mahd = (String) tblHoaDon.getValueAt(this.row, 0);
            new HoaDon(this, true, mahd).setVisible(true);
            return;
        }
    }//GEN-LAST:event_tblHoaDonMousePressed

    private void btnFormatFileExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFormatFileExcelActionPerformed
        FormatFileExcel();
    }//GEN-LAST:event_btnFormatFileExcelActionPerformed

    private void cboSortItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboSortItemStateChanged
        fillTableSPBanChay();
    }//GEN-LAST:event_cboSortItemStateChanged

    private void cboSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboSortActionPerformed

    private void btnPrevCVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevCVActionPerformed
        prevChucVu();
    }//GEN-LAST:event_btnPrevCVActionPerformed

    private void btnNextCVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextCVActionPerformed
        nextChucVu();
    }//GEN-LAST:event_btnNextCVActionPerformed

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        openHuongDan();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void txtGiaBanSP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaBanSP1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaBanSP1ActionPerformed

    public void chooserFileSaveQRProduct() throws FileNotFoundException, Exception {
        FileExtensison fe = new FileExtensison();

        //Lấy đường dẫn lưu mã QR
        JButton open = new JButton();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new java.io.File(""));
        fileChooser.setDialogTitle("Chọn đường dẫn lưu mã sản phẩm!");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (fileChooser.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getAbsolutePath();
            //System.out.println(fileChooser.getSelectedFile().getAbsolutePath());
            txtSaveQrProduct.setText(path);
            fe.setPath(path);
            MsgBox.alert(this, "Thay đổi thành công");
        }
    }

    public void chooserFileSaveBanner1() throws FileNotFoundException, Exception {
        FileExtensison fe = new FileExtensison();

        /*
        //Lấy đường dẫn lưu
        JButton open = new JButton();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new java.io.File(""));
        fileChooser.setDialogTitle("Chọn đường dẫn lưu ảnh banner 1!");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (fileChooser.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getAbsolutePath();
            //System.out.println(fileChooser.getSelectedFile().getAbsolutePath());
            txtBanerPathImg1.setText(path);
            fe.setImgBaner1(path);
            initBaner();
        }
         */
        JFileChooser chooser = new JFileChooser("");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Hình ảnh", "png", "jpg", "gif", "jpeg");
        chooser.setFileFilter(filter);
        chooser.showOpenDialog(null);
        File file = chooser.getSelectedFile();
        try {
            String path = file.getAbsolutePath();
            txtBanerPathImg1.setText(path);
            fe.setImgBaner1(path);
            initBaner();
            MsgBox.alert(this, "Thay đổi thành công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Thao tác đã bị hủy!");
            return;
        }
    }

    public void chooserFileSaveBanner2() throws FileNotFoundException, Exception {
        FileExtensison fe = new FileExtensison();

        //Lấy đường dẫn lưu
        JFileChooser chooser = new JFileChooser("");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Hình ảnh", "png", "jpg", "gif", "jpeg");
        chooser.setFileFilter(filter);
        chooser.showOpenDialog(null);
        File file = chooser.getSelectedFile();
        try {
            String path = file.getAbsolutePath();
            txtBanerPathImg2.setText(path);
            fe.setImgBaner2(path);
            initBaner();
            MsgBox.alert(this, "Thay đổi thành công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Thao tác đã bị hủy!");
            return;
        }
    }

    public void chooserFileSaveBanner3() throws FileNotFoundException, Exception {
        FileExtensison fe = new FileExtensison();

        //Lấy đường dẫn lưu
        JFileChooser chooser = new JFileChooser("");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Hình ảnh", "png", "jpg", "gif", "jpeg");
        chooser.setFileFilter(filter);
        chooser.showOpenDialog(null);
        File file = chooser.getSelectedFile();
        try {
            String path = file.getAbsolutePath();
            txtBanerPathImg3.setText(path);
            fe.setImgBaner3(path);
            initBaner();
            MsgBox.alert(this, "Thay đổi thành công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Thao tác đã bị hủy!");
            return;
        }
    }

    void setTextFileOpenSetting() {
        FileExtensison fe = new FileExtensison();
        try {
            txtSaveQrProduct.setText(fe.getPath());
            txtBanerPathImg1.setText(fe.getImgBaner1());
            txtBanerPathImg2.setText(fe.getImgBaner2());
            txtBanerPathImg3.setText(fe.getImgBaner3());
        } catch (Exception ex) {
            Logger.getLogger(CaiDat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void lblOpenQRProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOpenQRProductMouseClicked
        try {
            chooserFileSaveQRProduct();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CaiDat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CaiDat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblOpenQRProductMouseClicked

    private void lblOpenBannerImg1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOpenBannerImg1MouseClicked
        try {
            chooserFileSaveBanner1();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CaiDat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CaiDat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblOpenBannerImg1MouseClicked

    private void btnResetSaveBannerImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetSaveBannerImgActionPerformed
        FileExtensison fe = new FileExtensison();
        try {
            fe.setImgBaner1("src\\com\\asset\\slide-login\\bn1.jpeg");
            txtBanerPathImg1.setText("src\\com\\asset\\slide-login\\bn1.jpeg");
            fe.setImgBaner2("src\\com\\asset\\slide-login\\bn2.jpg");
            txtBanerPathImg2.setText("src\\com\\asset\\slide-login\\bn2.jpg");
            fe.setImgBaner3("src\\com\\asset\\slide-login\\bn3.jpg");
            txtBanerPathImg3.setText("src\\com\\asset\\slide-login\\bn3.jpg");
            initBaner();
        } catch (Exception ex) {
            MsgBox.alert(this, "Thao tác không thành công!");
            //Logger.getLogger(CaiDat.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnResetSaveBannerImgActionPerformed

    private void btnResetSaveQRProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetSaveQRProductActionPerformed
        FileExtensison fe = new FileExtensison();
        try {
            fe.setPath("src\\com\\images\\product");
            txtSaveQrProduct.setText("src\\com\\images\\product");
        } catch (Exception ex) {
            MsgBox.alert(this, "Thao tác không thành công!");
            //Logger.getLogger(CaiDat.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnResetSaveQRProductActionPerformed

    private void CaiDatTittle1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CaiDatTittle1MousePressed
        CaiDatHr2.setVisible(false);
        CaiDatHr1.setVisible(true);
        setLocationHr(cardCaiDatGiaoDien, CaiDatHr1, CaiDatTittle1);
    }//GEN-LAST:event_CaiDatTittle1MousePressed

    private void CaiDatTittle2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CaiDatTittle2MousePressed
        CaiDatHr1.setVisible(false);
        CaiDatHr2.setVisible(true);
        setLocationHr(cardCaiDatDuongDan, CaiDatHr2, CaiDatTittle2);
    }//GEN-LAST:event_CaiDatTittle2MousePressed

    private void lblOpenBannerImg2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOpenBannerImg2MouseClicked
        try {
            chooserFileSaveBanner2();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CaiDat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CaiDat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblOpenBannerImg2MouseClicked

    private void lblOpenBannerImg3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOpenBannerImg3MouseClicked
        try {
            chooserFileSaveBanner3();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CaiDat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CaiDat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblOpenBannerImg3MouseClicked

    private void lblAnhMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lblAnhMouseEntered

    private void lblDarkmodeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDarkmodeMouseClicked
        rdoDarkmode.setSelected(true);
        setrdoMode();
    }//GEN-LAST:event_lblDarkmodeMouseClicked

    private void lblLightmodeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLightmodeMouseClicked
        rdoLightmode.setSelected(true);
        setrdoMode();
    }//GEN-LAST:event_lblLightmodeMouseClicked

    void defaultRowColorTable(JTable tbl, int type) {
        if (type == 0) //dark
        {
            tbl.setDefaultRenderer(Object.class, new TableRowDark());
        } else //light
        {
            tbl.setDefaultRenderer(Object.class, new TableRowLight());
        }
    }

    public void setModeFrame() {
        FileExtensison f = new FileExtensison();
        try {
            String mode = f.getMode();
            if (mode.equalsIgnoreCase("dark")) {
                darkMode();
                rdoDarkmode.setSelected(true);
                this.darkMode = true;
            } else {
                lightMode();
                rdoLightmode.setSelected(true);
                this.darkMode = false;
            }
        } catch (Exception e) {
        }
    }

    void darkMode() {
        try {
            FileExtensison f = new FileExtensison();
            f.setMode("dark");
        } catch (Exception e) {
        }
        this.arrLbl = new JLabel[]{
            TrangChuTittle2,
            TaiKhoantittle1, TaiKhoantittle2, TaiKhoantittle3,
            SanPhamTittle1, SanPhamTittle2, SanPhamTittle3,
            GioHangTittle1,
            HoaDonTittle1,
            GioiThieutittle1, GioiThieutittle2,
            ThongKeTittle1, ThongKeTittle2,
            CaiDatTittle1, CaiDatTittle2
        };
        for (JLabel lbl : arrLbl) {
            lbl.setForeground(Color.white);
        }

        //
        this.arrPn = new JPanel[]{
            cardMenubarTrangChu,
            cardMenubarTaiKhoan,
            cardMenubarSanPham,
            cardMenubarGioHang,
            cardMenubarHoaDon,
            cardMenubarGioiThieu,
            cardMenubarThongKe,
            cardMenubarCaiDat,
            pnMenubar
        };
        for (JPanel pn : arrPn) {
            pn.setBackground(Color.black);
        }
        btnMenu.setBackground(Color.black);

        pnMain.setBackground(new Color(51, 51, 51));

        //-------------------------------------TRANG CHỦ------------------Đang
        cardTrangChuTongQuan.setBackground(new Color(51, 51, 51));

        //-----------------------------------TÀI KHOẢN-------------------------------Đan
        //nhân viên
        //TableCustom.apply(scpNhanVien, TableCustom.TableType.DARK);
        tblNhanVien.setBackground(new Color(60, 60, 60));
        tblNhanVien.setForeground(new Color(255, 255, 255));
        defaultRowColorTable(tblNhanVien, 0);
        //tblNhanVien.fixTable(scpNhanVien);
        //tblNhanVien.getTableHeader().setForeground(Color.blue);
        //((DefaultTableCellRenderer)theader.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        cardTaiKhoanNhanVien.setBackground(new Color(51, 51, 51));
        pnMarginTKNV.setBackground(new Color(153, 204, 255));
        jPanel16.setBackground(new Color(51, 51, 51));
        jPanel17.setBackground(new Color(51, 51, 51));
        jLabel30.setForeground(new Color(153, 153, 153));
        cboSortNV.setForeground(Color.black);
        cboSortNV.setBackground(new Color(51, 51, 51));
        cboVaiTro.setForeground(Color.white);
        cboVaiTro.setBackground(new Color(51, 51, 51));

        rdoNam.setBackground(new Color(51, 51, 51));
        rdoNam.setForeground(Color.white);
        rdoNu.setForeground(Color.white);
        rdoNu.setBackground(new Color(51, 51, 51));

        lblHovaTenNV.setForeground(new Color(102, 255, 255));
        lblTenDN.setForeground(new Color(102, 255, 255));
        lblMatKhau.setForeground(new Color(102, 255, 255));
        lblSDTNV.setForeground(new Color(102, 255, 255));
        jLabel41.setForeground(new Color(102, 255, 255));
        jLabel35.setForeground(new Color(102, 255, 255));
        lblEmailNV.setForeground(new Color(102, 255, 255));
        lblNgaySinhNV.setForeground(new Color(102, 255, 255));
        lblDiaChiNV.setForeground(new Color(102, 255, 255));
        lblTrangThai.setForeground(new Color(102, 255, 255));
        //lblTitleTimKiemNV.setForeground(Color.white);
        jPanel15.setBackground(new Color(51, 51, 51));
        jPanel15.setBorder(BorderFactory.createLineBorder(Color.black));
        jLabel85.setBackground(new Color(51, 51, 51));
        jLabel85.setForeground(Color.white);
        jPanel18.setBackground(new Color(51, 51, 51));
        jPanel17.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(75, 75, 75)));
        jSeparator6.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(75, 75, 75)));
        jSeparator7.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(75, 75, 75)));
        jSeparator8.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(75, 75, 75)));

        txtHoTenNV.setForeground(Color.white);
        txtHoTenNV.setBackground(new Color(51, 51, 51));
        txtHoTenNV.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        txtTenDN.setForeground(Color.white);
        txtTenDN.setBackground(new Color(51, 51, 51));
        txtTenDN.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        txtMatKhauNV.setForeground(Color.white);
        txtMatKhauNV.setBackground(new Color(51, 51, 51));
        txtMatKhauNV.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        txtSDTNV.setForeground(Color.white);
        txtSDTNV.setBackground(new Color(51, 51, 51));
        txtSDTNV.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        txtEmailNV.setForeground(Color.white);
        txtEmailNV.setBackground(new Color(51, 51, 51));
        txtEmailNV.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        txtNgaySinhNV.setForeground(Color.white);
        txtNgaySinhNV.setBackground(new Color(51, 51, 51));
        txtNgaySinhNV.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        txtDiaChiNV.setForeground(Color.white);
        txtDiaChiNV.setBackground(new Color(51, 51, 51));
        txtDiaChiNV.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        //txtTimKiemNV.setForeground(Color.white);
        txtTimKiemNV.setBackground(new Color(153, 204, 255));
        //txtTimKiemNV.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));

        btnLamMoiNV.setForeground(Color.white);
        btnLamMoiNV.setBackground(new Color(0, 153, 204));
        btnThemNV.setForeground(Color.white);
        btnThemNV.setBackground(new Color(0, 153, 204));
        btnCapNhatNV.setForeground(Color.white);
        btnCapNhatNV.setBackground(new Color(0, 153, 204));
        btnNextNV.setBackground(new Color(153, 204, 255));
        btnLastNV.setBackground(new Color(153, 204, 255));
        btnPrevNV.setBackground(new Color(153, 204, 255));
        btnFirstNV.setBackground(new Color(153, 204, 255));
        cboSortNV.setForeground(Color.white);
        //khách hàng
        defaultRowColorTable(tblKhachHang, 0);
        cardKhachHang.setBackground(new Color(51, 51, 51));
        txthoTen.setForeground(Color.white);
        txthoTen.setBackground(new Color(51, 51, 51));
        txthoTen.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        txtSDT.setForeground(Color.white);
        txtSDT.setBackground(new Color(51, 51, 51));
        txtSDT.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        txtmaKH.setForeground(Color.white);
        txtmaKH.setBackground(new Color(51, 51, 51));
        txtmaKH.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        txtdiaChi.setForeground(Color.white);
        txtdiaChi.setBackground(new Color(51, 51, 51));
        txtdiaChi.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        txtTimKiemKH.setForeground(Color.black);
        txtTimKiemKH.setBackground(new Color(153, 204, 255));
        //txtTimKiemKH.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));

        lblHovaTenKH.setForeground(new Color(102, 255, 255));
        lblSDTKH.setForeground(new Color(102, 255, 255));
        lblMaKH.setForeground(new Color(102, 255, 255));
        lblDiaChiKH.setForeground(new Color(102, 255, 255));
        jLabel63.setBackground(new Color(51, 51, 51));
        jLabel63.setForeground(Color.white);

        jPanel3.setBackground(new Color(51, 51, 51));
        jPanel3.setBorder(BorderFactory.createLineBorder(Color.black));
        jPanel13.setBackground(new Color(51, 51, 51));
        jPanel13.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(75, 75, 75)));
        jSeparator5.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(75, 75, 75)));
        jSeparator3.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(75, 75, 75)));
        jSeparator4.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(75, 75, 75)));
        jPanel4.setBackground(new Color(51, 51, 51));
        pnMarginKhachHangTitle.setBackground(new Color(153, 204, 255));
        jLabel29.setForeground(new Color(153, 153, 153));
        btnlamMoiKhachHang.setForeground(Color.white);
        btnlamMoiKhachHang.setBackground(new Color(0, 153, 204));
        btnThemKhachHang.setForeground(Color.white);
        btnThemKhachHang.setBackground(new Color(0, 153, 204));
        btncapNhatKhachHang.setForeground(Color.white);
        btncapNhatKhachHang.setBackground(new Color(0, 153, 204));
        btnNextKH.setBackground(new Color(153, 204, 255));
        btnLastKH.setBackground(new Color(153, 204, 255));
        btnPrevKH.setBackground(new Color(153, 204, 255));
        btnFirstKH.setBackground(new Color(153, 204, 255));
        cboKh.setForeground(Color.black);
        cboKh.setBackground(new Color(51, 51, 51));
        cbotimKiemKH.setForeground(Color.black);
        cbotimKiemKH.setBackground(new Color(153, 204, 255));

        tblKhachHang.setForeground(Color.white);
        tblKhachHang.setBackground(new Color(51, 51, 51));
        jLabel62.setBackground(new Color(51, 51, 51));
        jLabel62.setForeground(Color.white);
        tblKhachHang.setSelectionForeground(Color.black);
        cboKh.setForeground(Color.white);
        //chức vụ
        defaultRowColorTable(tblChucVu, 0);
        cardTaiKhoanChucVu.setBackground(new Color(51, 51, 51));

        lblTenCV.setForeground(new Color(102, 255, 255));
        lblMaChucVu.setForeground(new Color(102, 255, 255));
        jLabel90.setForeground(new Color(102, 255, 255));

        txttenCV.setForeground(Color.white);
        txttenCV.setBackground(new Color(51, 51, 51));
        txttenCV.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        txtmaCV.setForeground(Color.white);
        txtmaCV.setBackground(new Color(51, 51, 51));
        txtmaCV.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        txtMoTaCV.setForeground(Color.white);
        txtMoTaCV.setBackground(new Color(51, 51, 51));
        //txtMoTaCV.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));

        jPanel19.setBackground(new Color(51, 51, 51));
        jPanel19.setBorder(BorderFactory.createLineBorder(Color.black));
        jPanel21.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(75, 75, 75)));
        jSeparator9.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(75, 75, 75)));
        jSeparator10.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(75, 75, 75)));
        jSeparator11.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(75, 75, 75)));
        jPanel21.setBackground(new Color(51, 51, 51));
        pnMarginTitleChucVu.setBackground(new Color(153, 204, 255));
        jPanel20.setBackground(new Color(51, 51, 51));
        //lblTitleTimKiemChucVu.setForeground(Color.white);
        jLabel93.setForeground(new Color(153, 153, 153));
        btnLamMoiCV.setForeground(Color.white);
        btnLamMoiCV.setBackground(new Color(0, 153, 204));
        btnThemCV.setForeground(Color.white);
        btnThemCV.setBackground(new Color(0, 153, 204));
        btnCapNhatCV.setForeground(Color.white);
        btnCapNhatCV.setBackground(new Color(0, 153, 204));
        btnNextCV.setBackground(new Color(153, 204, 255));
        btnLastCV.setBackground(new Color(153, 204, 255));
        btnPrevCV.setBackground(new Color(153, 204, 255));
        btnFirstCV.setBackground(new Color(153, 204, 255));
        cboCV.setForeground(Color.black);
        cboCV.setBackground(new Color(51, 51, 51));
        txtTimCV.setForeground(Color.black);
        txtTimCV.setBackground(new Color(153, 204, 255));
        //txtTimCV.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));

        tblChucVu.setForeground(Color.white);
        tblChucVu.setBackground(new Color(51, 51, 51));
        jLabel99.setBackground(new Color(51, 51, 51));
        jLabel99.setForeground(Color.white);
        tblChucVu.setSelectionForeground(Color.black);
        cboCV.setForeground(Color.white);

        //----------------------------------SẢN PHẨM-----------------------------------Vi
        defaultRowColorTable(tblSanPham, 0);

        cardSanPham.setBackground(new Color(51, 51, 51));
        cardLoai2.setBackground(new Color(51, 51, 51));
        tblSanPham.setBackground(new Color(51, 51, 51));
        tblSanPham.setForeground(Color.white);
        txtMaSP.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        txtMaSP.setForeground(Color.white);
        txtMaSP.setBackground(new Color(51, 51, 51));
        txtTenSP.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        txtTenSP.setForeground(Color.white);
        txtTenSP.setBackground(new Color(51, 51, 51));
        txtNgayNhapSP.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        txtNgayNhapSP.setForeground(Color.white);
        txtNgayNhapSP.setBackground(new Color(51, 51, 51));
        txtGiaNhapSP.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        txtGiaNhapSP.setForeground(Color.white);
        txtGiaNhapSP.setBackground(new Color(51, 51, 51));
        txtGiaNhapSP3.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        txtGiaNhapSP3.setForeground(Color.white);
        txtGiaNhapSP3.setBackground(new Color(51, 51, 51));
        txtGiaBanSP.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        txtGiaBanSP.setForeground(Color.white);
        txtGiaBanSP.setBackground(new Color(51, 51, 51));
        txtGiaBanSP1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        txtGiaBanSP1.setForeground(Color.white);
        txtGiaBanSP1.setBackground(new Color(51, 51, 51));
        txtSoLuongSP.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        txtSoLuongSP.setForeground(Color.white);
        txtSoLuongSP.setBackground(new Color(51, 51, 51));
        txtMoTaSP.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
        txtMoTaSP.setForeground(Color.white);
        txtMoTaSP.setBackground(new Color(51, 51, 51));
        cboMaHang.setForeground(Color.white);
        cboMaHang.setBackground(new Color(51, 51, 51));
        cboMaLoai.setForeground(Color.white);
        cboMaLoai.setBackground(new Color(51, 51, 51));
        lblVND1.setBackground(new Color(51, 51, 51));
        lblVND1.setForeground(Color.red);
        lblVND2.setBackground(new Color(51, 51, 51));
        lblVND2.setForeground(Color.red);
        lblTimKiemTempSP.setBackground(new Color(51, 51, 51));
//        lblTimKiemTempSP.setForeground(Color.white);
        lblFormat.setBackground(new Color(51, 51, 51));
        lblFormat.setForeground(Color.white);
        txtTimKiemSP.setForeground(Color.black);
        txtTimKiemSP.setBackground(new Color(153, 204, 255));
        cboTimKiemSP.setForeground(Color.white);
        cboTimKiemSP.setBackground(new Color(153, 204, 255));
        lblBanGhiSP.setForeground(Color.white);
        pnSP.setBackground(new Color(51, 51, 51));
        btnThemSP.setForeground(Color.white);
        btnThemSP.setBackground(new Color(0, 153, 204));
        btnCapNhatSP.setForeground(Color.white);
        btnCapNhatSP.setBackground(new Color(0, 153, 204));
        btnLamMoiSP.setForeground(Color.white);
        btnLamMoiSP.setBackground(new Color(0, 153, 204));
        cboSXSP.setForeground(Color.white);
        cboSXSP.setBackground(new Color(51, 51, 51));
        lblTHSXSP.setForeground(Color.white);
        pnBtnSapxepSP.setBackground(new Color(51, 51, 51));
        pnBangTTSP.setBackground(new Color(153, 204, 255));
        btnFirstSP.setBackground(new Color(153, 204, 255));
        btnPrevSP.setBackground(new Color(153, 204, 255));
        btnNextSP.setBackground(new Color(153, 204, 255));
        btnLastSP.setBackground(new Color(153, 204, 255));
        lblMaSP.setForeground(new Color(102, 255, 255));
        lblTenSP.setForeground(new Color(102, 255, 255));
        lblNgayNhapSP.setForeground(new Color(102, 255, 255));
        lblGiaNhapSP.setForeground(new Color(102, 255, 255));
        lblGiaBanSP.setForeground(new Color(102, 255, 255));
        lblTenHangSP.setForeground(new Color(102, 255, 255));
        lblTenLoaiSP.setForeground(new Color(102, 255, 255));
        lblSoLuongSP.setForeground(new Color(102, 255, 255));
        lblMoTaSP.setForeground(new Color(102, 255, 255));

        lblAnh.setForeground(Color.white);

        pnSP.setBorder(BorderFactory.createLineBorder(Color.black));
        pnBtnSapxepSP.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(75, 75, 75)));
        jSeparator33.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(75, 75, 75)));
        jSeparator34.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(75, 75, 75)));
        jSeparator35.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(75, 75, 75)));
        tblSanPham.setSelectionForeground(Color.black);
        //hãng
        defaultRowColorTable(tblHang, 0);

        cardHangSanXuat.setBackground(new Color(51, 51, 51));
        cardLoai1.setBackground(new Color(51, 51, 51));
        tblHang.setBackground(new Color(51, 51, 51));
        tblHang.setForeground(Color.white);
        txtMaHang.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        txtMaHang.setForeground(Color.white);
        txtMaHang.setBackground(new Color(51, 51, 51));
        txtTenHang.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        txtTenHang.setForeground(Color.white);
        txtTenHang.setBackground(new Color(51, 51, 51));
        lblMaHang.setBackground(new Color(51, 51, 51));
        lblMaHang.setForeground(new Color(102, 255, 255));
        lblTenHang.setBackground(new Color(51, 51, 51));
        lblTenHang.setForeground(new Color(102, 255, 255));
        pnBangTTHang.setBackground(new Color(153, 204, 255));
        pnbtnSXHang.setBackground(new Color(51, 51, 51));
        lblTHSXHang.setBackground(new Color(51, 51, 51));
        lblTHSXHang.setForeground(Color.white);
        cboHang.setForeground(Color.white);
        cboHang.setBackground(new Color(51, 51, 51));
        pnHang.setBackground(new Color(51, 51, 51));
        txtTimKiemHang.setBackground(new Color(153, 204, 255));
        txtTimKiemHang.setForeground(Color.black);
//        lblHangTemp.setBackground(new Color(51, 51, 51));
        btnThemHang.setForeground(Color.white);
        btnThemHang.setBackground(new Color(0, 153, 204));
        btnCapNhatHang.setForeground(Color.white);
        btnCapNhatHang.setBackground(new Color(0, 153, 204));
        btnLamMoiHang.setForeground(Color.white);
        btnLamMoiHang.setBackground(new Color(0, 153, 204));
        lblBanGhiHang.setForeground(Color.white);
//        lblTimKiemHang.setForeground(Color.white);
        pnSXHang.setBackground(new Color(51, 51, 51));
        btnFirstHang.setBackground(new Color(153, 204, 255));
        btnPrevHang.setBackground(new Color(153, 204, 255));
        btnNextHang.setBackground(new Color(153, 204, 255));
        btnLastHang.setBackground(new Color(153, 204, 255));

        pnHang.setBorder(BorderFactory.createLineBorder(Color.black));
        pnbtnSXHang.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(75, 75, 75)));
        jSeparator15.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(75, 75, 75)));
        jSeparator16.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(75, 75, 75)));
        jSeparator17.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(75, 75, 75)));
        tblHang.setSelectionForeground(Color.black);
        //loại
        defaultRowColorTable(tblLoaiHang, 0);

        cardLoai.setBackground(new Color(51, 51, 51));
        tblLoaiHang.setBackground(new Color(51, 51, 51));
        tblLoaiHang.setForeground(Color.white);
        txtMaLoai.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        txtMaLoai.setForeground(Color.white);
        txtMaLoai.setBackground(new Color(51, 51, 51));
        txtTenLoai.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        txtTenLoai.setForeground(Color.white);
        txtTenLoai.setBackground(new Color(51, 51, 51));
        lblMaLoai.setBackground(new Color(51, 51, 51));
        lblMaLoai.setForeground(new Color(102, 255, 255));
        lblTenLoai.setBackground(new Color(51, 51, 51));
        lblTenLoai.setForeground(new Color(102, 255, 255));
        pnBangTTLoai.setBackground(new Color(153, 204, 255));
        pnbtnSXLoai.setBackground(new Color(51, 51, 51));
        lblTHSXLoai.setBackground(new Color(51, 51, 51));
        lblTHSXLoai.setForeground(Color.white);
        cboLoai.setForeground(Color.white);
        cboLoai.setBackground(new Color(51, 51, 51));
        pnLoai.setBackground(new Color(51, 51, 51));
        txtTimKiemLoai.setBackground(new Color(153, 204, 255));
        txtTimKiemLoai.setForeground(Color.black);
//        lblLoaiTemp.setBackground(new Color(51, 51, 51));
        btnThemLoai.setForeground(Color.white);
        btnThemLoai.setBackground(new Color(0, 153, 204));
        btnCapNhatLoai.setForeground(Color.white);
        btnCapNhatLoai.setBackground(new Color(0, 153, 204));
        btnLamMoiLoai.setForeground(Color.white);
        btnLamMoiLoai.setBackground(new Color(0, 153, 204));
        lblBanGhiLoai.setForeground(Color.white);
//        jLabel105.setForeground(Color.white);
        pnSXLoai.setBackground(new Color(51, 51, 51));
        btnFirstLoai.setBackground(new Color(153, 204, 255));
        btnPrevLoai.setBackground(new Color(153, 204, 255));
        btnNextLoai.setBackground(new Color(153, 204, 255));
        btnLastLoai.setBackground(new Color(153, 204, 255));

        pnLoai.setBorder(BorderFactory.createLineBorder(Color.black));
        pnbtnSXLoai.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(75, 75, 75)));
        jSeparator12.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(75, 75, 75)));
        jSeparator13.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(75, 75, 75)));
        jSeparator14.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(75, 75, 75)));
        cboLoai.setForeground(Color.white);
        tblLoaiHang.setSelectionForeground(Color.black);
        //----------------------------------HÓA ĐƠN----------------------------------Sỹ
        defaultRowColorTable(tblHoaDon, 0);
        cardHoaDon.setBackground(new Color(51, 51, 51));

        jLabel118.setForeground(new Color(102, 255, 255));
        jLabel119.setForeground(new Color(102, 255, 255));
        jLabel117.setForeground(new Color(102, 255, 255));
        jLabel135.setForeground(new Color(102, 255, 255));
        jLabel120.setForeground(new Color(102, 255, 255));
        jLabel121.setForeground(new Color(102, 255, 255));

        txtTENKH.setForeground(Color.white);
        txtTENKH.setBackground(new Color(51, 51, 51));
        txtTENKH.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        txtMAKH.setForeground(Color.white);
        txtMAKH.setBackground(new Color(51, 51, 51));
        txtMAKH.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        txtPhanTramGG.setForeground(Color.white);
        txtPhanTramGG.setBackground(new Color(51, 51, 51));
        txtPhanTramGG.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        txtTienNhan.setForeground(Color.white);
        txtTienNhan.setBackground(new Color(51, 51, 51));
        txtTienNhan.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        txtTichDiem.setForeground(Color.white);
        txtTichDiem.setBackground(new Color(51, 51, 51));
        txtTichDiem.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        txtTENNV.setForeground(Color.white);
        txtTENNV.setBackground(new Color(51, 51, 51));
        txtTENNV.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));

        jPanel28.setBackground(new Color(51, 51, 51));
        jPanel28.setBorder(BorderFactory.createLineBorder(Color.black));
        jPanel30.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(75, 75, 75)));
        jSeparator20.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(75, 75, 75)));
        jSeparator21.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(75, 75, 75)));
        jSeparator22.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(75, 75, 75)));
        jPanel30.setBackground(new Color(51, 51, 51));
        pnMarginHoaDon.setBackground(new Color(153, 204, 255));
        jLabel81.setBackground(new Color(51, 51, 51));
        jLabel48.setForeground(new Color(153, 153, 153));
        jPanel29.setBackground(new Color(51, 51, 51));

        btnSuDungDiem.setForeground(Color.white);
        btnSuDungDiem.setBackground(new Color(0, 153, 204));
        btnIn.setForeground(Color.white);
        btnIn.setBackground(new Color(0, 153, 204));
        btnNexthd.setBackground(new Color(153, 204, 255));
        btnLasthd.setBackground(new Color(153, 204, 255));
        btnPrevhd.setBackground(new Color(153, 204, 255));
        btnFirsthd.setBackground(new Color(153, 204, 255));
        comboboxHD.setForeground(Color.black);
        comboboxHD.setBackground(new Color(51, 51, 51));
        txtTimKiemhd.setForeground(Color.black);
        txtTimKiemhd.setBackground(new Color(153, 204, 255));

        jLabel65.setForeground(Color.white);
        jLabel104.setForeground(Color.white);
        tblHoaDon.setForeground(Color.white);
        tblHoaDon.setBackground(new Color(51, 51, 51));
        tblHoaDon.setSelectionForeground(Color.black);
        comboboxHD.setForeground(Color.white);

        //--------------------------------GIỎ HÀNG-------------------------------------(Sỹ)
        cardGioHang.setBackground(new Color(51, 51, 51));
        cardHoaDonSanPham.setBackground(new Color(51, 51, 51));
        cardHoaDonQR.setBackground(new Color(51, 51, 51));
        pnQR.setBackground(new Color(51, 51, 51));

        jLabel77.setForeground(new Color(102, 255, 255));
        jLabel11.setForeground(new Color(102, 255, 255));
        jLabel10.setForeground(new Color(102, 255, 255));
        jLabel13.setForeground(new Color(102, 255, 255));
        lblNameCustomer.setForeground(Color.red);

        txtSdtKH.setForeground(Color.white);
        txtSdtKH.setBackground(new Color(51, 51, 51));
        txtSdtKH.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        txtTongtiensp.setForeground(Color.white);
        txtTongtiensp.setBackground(new Color(51, 51, 51));
        txtTongtiensp.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));

        txtFindNameProductCart.setForeground(Color.white);
        txtFindNameProductCart.setBackground(new Color(51, 51, 51));
        txtFindNameProductCart.setLineColor(Color.white);

        btnXNKH.setForeground(Color.white);
        btnXNKH.setBackground(new Color(0, 153, 204));
        btnXacNhanDonHang.setForeground(Color.white);
        btnXacNhanDonHang.setBackground(new Color(0, 153, 204));

        tblCart.setForeground(Color.white);
        tblCart.setBackground(new Color(51, 51, 51));

        btnxoaGioHang.setBackground(new Color(51, 51, 51));
        pnPupopMenu.setBackground(new Color(51, 51, 51));
        pnGioHangSanPham.setBackground(new Color(51, 51, 51));

        //------------------------------THỐNG KÊ--------------------------------------(Vinh)
        cardThongKeDoanhThu.setBackground(new Color(51, 51, 51));
        jLabel43.setForeground(new java.awt.Color(102, 255, 255));
        jLabel45.setForeground(new java.awt.Color(102, 255, 255));
        jLabel44.setForeground(new java.awt.Color(102, 255, 255));
        jLabel50.setForeground(new java.awt.Color(102, 255, 255));
        cboDayDT.setBackground(new Color(51, 51, 51));
        cboMonthDT.setBackground(new Color(51, 51, 51));
        cboYearDT.setBackground(new Color(51, 51, 51));
        cboChoice.setBackground(new Color(51, 51, 51));
        cboDayDT.setForeground(Color.white);
        cboMonthDT.setForeground(Color.white);
        cboYearDT.setForeground(Color.white);
        cboChoice.setForeground(Color.white);
        cardThongKeSanPham.setBackground(new Color(51, 51, 51));
        jLabel46.setForeground(new java.awt.Color(102, 255, 255));
        jLabel47.setForeground(new java.awt.Color(102, 255, 255));
        jLabel51.setForeground(new java.awt.Color(102, 255, 255));
        jLabel59.setForeground(new java.awt.Color(102, 255, 255));
        cboDaySP.setBackground(new Color(51, 51, 51));
        cboMonthSP.setBackground(new Color(51, 51, 51));
        cboYearSP.setBackground(new Color(51, 51, 51));
        cboSort.setBackground(new Color(51, 51, 51));
        cboSort.setForeground(Color.white);
        cboDaySP.setForeground(Color.white);
        cboMonthSP.setForeground(Color.white);
        cboYearSP.setForeground(Color.white);
        pnlChart.setBackground(new Color(51, 51, 51));
        jPanel33.setBackground(new Color(153, 204, 255));
        jPanel32.setBackground(new Color(153, 204, 255));
        tblDoanhThu.setBackground(new Color(51, 51, 51));
        tblDoanhThu.setForeground(Color.white);
        tblDoanhThu.setSelectionBackground(new java.awt.Color(102, 255, 255));
        tblDoanhThu.setSelectionForeground(Color.black);
        tblSPBanChay.setBackground(new Color(51, 51, 51));
        tblSPBanChay.setForeground(Color.white);
        tblSPBanChay.setSelectionBackground(new java.awt.Color(102, 255, 255));
        tblSPBanChay.setSelectionForeground(Color.black);

        //------------------------------GIỚI THIỆU------------------------------------Vinh
        //Giới thiệu sản phẩm
        cardGioiThieuSanPham.setBackground(new Color(51, 51, 51));
        jLabel2.setBackground(new Color(51, 51, 51));
        jLabel2.setForeground(new Color(102, 255, 255));

        jLabel3.setBackground(new Color(51, 51, 51));
        jLabel3.setForeground(new Color(102, 255, 255));

        jLabel4.setBackground(new Color(51, 51, 51));
        jLabel4.setForeground(new Color(102, 255, 255));

        jLabel5.setBackground(new Color(51, 51, 51));
        jLabel5.setForeground(new Color(102, 255, 255));

        jLabel6.setBackground(new Color(51, 51, 51));
        jLabel6.setForeground(new Color(102, 255, 255));

        //Giới thiệu thành viên
        cardGioiThieuThanhVien.setBackground(new Color(51, 51, 51));

        jLabel27.setBackground(new Color(51, 51, 51));
        jLabel27.setForeground(new Color(102, 255, 255));

        jLabel28.setBackground(new Color(51, 51, 51));
        jLabel28.setForeground(new Color(102, 255, 255));

        jLabel33.setBackground(new Color(51, 51, 51));
        jLabel33.setForeground(new Color(102, 255, 255));

        jLabel34.setBackground(new Color(51, 51, 51));
        jLabel34.setForeground(new Color(102, 255, 255));

        //------------------------------CÀI ĐẶT--------------------------------------Đang
        lightMode1.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        darkMode1.setBorder(BorderFactory.createLineBorder(new Color(0, 204, 204)));
        pnSetMode.setBackground(new Color(51, 51, 51));
        cardCaiDatGiaoDien.setBackground(new Color(51, 51, 51));
        pnReviewMode.setBackground(new Color(51, 51, 51));
        rdoDarkmode.setForeground(new Color(255, 255, 255));
        rdoLightmode.setForeground(new Color(255, 255, 255));
        pnSetMode.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.white));
        //cài đặt đường dẫn ảnh
        lblCDDD1.setForeground(Color.white);
        lblCDDD2.setForeground(Color.white);
        lblCDDD3.setForeground(Color.white);
        lblTitleSaveQRProduct.setForeground(new Color(102, 255, 255));
        lblOpenBannerImg.setForeground(new Color(102, 255, 255));
        txtSaveQrProduct.setForeground(Color.white);
        txtSaveQrProduct.setBackground(Color.black);
        txtBanerPathImg1.setForeground(Color.white);
        txtBanerPathImg1.setBackground(Color.black);
        txtBanerPathImg2.setForeground(Color.white);
        txtBanerPathImg2.setBackground(Color.black);
        txtBanerPathImg3.setForeground(Color.white);
        txtBanerPathImg3.setBackground(Color.black);
        btnResetSaveQRProduct.setForeground(Color.white);
        btnResetSaveQRProduct.setBackground(new Color(0, 153, 204));
        btnResetSaveBannerImg.setForeground(Color.white);
        btnResetSaveBannerImg.setBackground(new Color(0, 153, 204));
        pnCaiDatDuongDan.setBackground(new Color(51, 51, 51));
        pnReviewLightMode.setVisible(false);
        pnReviewDarkMode.setVisible(true);
    }

    public void lightModeTK() {
        cardThongKeDoanhThu.setBackground(Color.white);
        jLabel43.setForeground(new java.awt.Color(0, 0, 255));
        jLabel43.setFont(new java.awt.Font("Times New Roman", 1, 16));
        jLabel45.setForeground(new java.awt.Color(0, 0, 255));
        jLabel45.setFont(new java.awt.Font("Times New Roman", 1, 16));
        jLabel44.setForeground(new java.awt.Color(0, 0, 255));
        jLabel44.setFont(new java.awt.Font("Times New Roman", 1, 16));
        jLabel50.setForeground(new java.awt.Color(0, 0, 255));
        jLabel50.setFont(new java.awt.Font("Times New Roman", 1, 16));
        cboDayDT.setBackground(Color.white);
        cboMonthDT.setBackground(Color.white);
        cboYearDT.setBackground(Color.white);
        cboChoice.setBackground(Color.white);
        cboDayDT.setForeground(Color.black);
        cboMonthDT.setForeground(Color.black);
        cboYearDT.setForeground(Color.black);
        cboChoice.setForeground(Color.black);
        cardThongKeSanPham.setBackground(Color.white);
        jLabel46.setForeground(new java.awt.Color(0, 0, 255));
        jLabel46.setFont(new java.awt.Font("Times New Roman", 1, 16));
        jLabel47.setForeground(new java.awt.Color(0, 0, 255));
        jLabel47.setFont(new java.awt.Font("Times New Roman", 1, 16));
        jLabel51.setForeground(new java.awt.Color(0, 0, 255));
        jLabel51.setFont(new java.awt.Font("Times New Roman", 1, 16));
        jLabel59.setForeground(new java.awt.Color(0, 0, 255));
        jLabel59.setFont(new java.awt.Font("Times New Roman", 1, 16));
        cboDaySP.setBackground(Color.white);
        cboMonthSP.setBackground(Color.white);
        cboYearSP.setBackground(Color.white);
        cboSort.setBackground(Color.white);
        cboDaySP.setForeground(Color.black);
        cboMonthSP.setForeground(Color.black);
        cboYearSP.setForeground(Color.black);
        cboSort.setForeground(Color.black);
        pnlChart.setBackground(Color.white);
        jPanel33.setBackground(new Color(204, 204, 255));
        jPanel32.setBackground(new Color(204, 204, 255));
        tblDoanhThu.setBackground(Color.white);
        tblDoanhThu.setForeground(Color.black);
        tblDoanhThu.setSelectionBackground(new java.awt.Color(0, 0, 255));
        tblDoanhThu.setSelectionForeground(Color.white);
        tblDoanhThu.setDefaultEditor(Object.class, null);
        tblSPBanChay.setBackground(Color.white);
        tblSPBanChay.setForeground(Color.black);
        tblSPBanChay.setSelectionBackground(new java.awt.Color(0, 0, 255));
        tblSPBanChay.setSelectionForeground(Color.white);
        tblSPBanChay.setDefaultEditor(Object.class, null);
    }

    void lightMode() {
        try {
            FileExtensison f = new FileExtensison();
            f.setMode("light");
        } catch (Exception e) {
        }
        this.arrLbl = new JLabel[]{
            TrangChuTittle2,
            TaiKhoantittle1, TaiKhoantittle2, TaiKhoantittle3,
            SanPhamTittle1, SanPhamTittle2, SanPhamTittle3,
            GioHangTittle1,
            HoaDonTittle1,
            GioiThieutittle1, GioiThieutittle2,
            ThongKeTittle1, ThongKeTittle2,
            CaiDatTittle1, CaiDatTittle2
        };
        for (JLabel lbl : arrLbl) {
            lbl.setForeground(Color.black);
        }

        //
        this.arrPn = new JPanel[]{
            cardMenubarTrangChu,
            cardMenubarTaiKhoan,
            cardMenubarSanPham,
            cardMenubarGioHang,
            cardMenubarHoaDon,
            cardMenubarGioiThieu,
            cardMenubarThongKe,
            cardMenubarCaiDat,
            pnMenubar
        };
        for (JPanel pn : arrPn) {
            pn.setBackground(Color.white);
        }
        btnMenu.setBackground(Color.white);
        //-------------------------------------TRANG CHỦ------------------Đang
        cardTrangChuTongQuan.setBackground(Color.white);
        //-----------------------------------TÀI KHOẢN-------------------------------Đan
        //TableCustom.apply(scpNhanVien, TableCustom.TableType.MULTI_LINE);

        tblNhanVien.setBackground(new Color(255, 255, 255));
        tblNhanVien.setForeground(new Color(0, 0, 0));
        defaultRowColorTable(tblNhanVien, 1);

        cardTaiKhoanNhanVien.setBackground(Color.white);

        pnMarginTKNV.setBackground(new Color(204, 204, 255));
        jPanel16.setBackground(new Color(255, 255, 255));
        jPanel17.setBackground(new Color(255, 255, 255));
        jLabel30.setForeground(Color.black);
        cboSortNV.setForeground(Color.white);
        cboSortNV.setBackground(new Color(255, 255, 255));
        cboVaiTro.setForeground(Color.black);
        cboVaiTro.setBackground(new Color(255, 255, 255));

        rdoNam.setBackground(new Color(255, 255, 255));
        rdoNam.setForeground(Color.black);
        rdoNu.setForeground(Color.black);
        rdoNu.setBackground(new Color(255, 255, 255));

        lblHovaTenNV.setForeground(new Color(0, 0, 255));
        lblTenDN.setForeground(new Color(0, 0, 255));
        lblMatKhau.setForeground(new Color(0, 0, 255));
        lblSDTNV.setForeground(new Color(0, 0, 255));
        jLabel41.setForeground(new Color(0, 0, 255));
        jLabel35.setForeground(new Color(0, 0, 255));
        lblEmailNV.setForeground(new Color(0, 0, 255));
        lblNgaySinhNV.setForeground(new Color(0, 0, 255));
        lblDiaChiNV.setForeground(new Color(0, 0, 255));
        lblTrangThai.setForeground(new Color(0, 0, 255));
        lblTitleTimKiemNV.setForeground(Color.black);
        jPanel15.setBackground(Color.white);
        jPanel15.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 255)));
        jLabel85.setBackground(new Color(255, 255, 255));
        jLabel85.setForeground(Color.black);
        jPanel18.setBackground(new Color(255, 255, 255));

        txtHoTenNV.setForeground(Color.black);
        txtHoTenNV.setBackground(new Color(255, 255, 255));
        txtHoTenNV.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        txtTenDN.setForeground(Color.black);
        txtTenDN.setBackground(new Color(255, 255, 255));
        txtTenDN.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        txtMatKhauNV.setForeground(Color.black);
        txtMatKhauNV.setBackground(new Color(255, 255, 255));
        txtMatKhauNV.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        txtSDTNV.setForeground(Color.black);
        txtSDTNV.setBackground(new Color(255, 255, 255));
        txtSDTNV.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        txtEmailNV.setForeground(Color.black);
        txtEmailNV.setBackground(new Color(255, 255, 255));
        txtEmailNV.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        txtNgaySinhNV.setForeground(Color.black);
        txtNgaySinhNV.setBackground(new Color(255, 255, 255));
        txtNgaySinhNV.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        txtDiaChiNV.setForeground(Color.black);
        txtDiaChiNV.setBackground(new Color(255, 255, 255));
        txtDiaChiNV.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        txtTimKiemNV.setForeground(Color.black);
        txtTimKiemNV.setBackground(new Color(204, 204, 255));
        txtTimKiemNV.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));

        btnLamMoiNV.setForeground(Color.black);
        btnLamMoiNV.setBackground(new Color(204, 204, 255));
        btnThemNV.setForeground(Color.black);
        btnThemNV.setBackground(new Color(204, 204, 255));
        btnCapNhatNV.setForeground(Color.black);
        btnCapNhatNV.setBackground(new Color(204, 204, 255));
        btnNextNV.setBackground(new Color(204, 204, 255));
        btnLastNV.setBackground(new Color(204, 204, 255));
        btnPrevNV.setBackground(new Color(204, 204, 255));
        btnFirstNV.setBackground(new Color(204, 204, 255));

        //tblNhanVien.setBackground(new Color(255, 255, 255));
        //tblNhanVien.setForeground(Color.black);
        //khách hàng
        cardKhachHang.setBackground(Color.white);
        defaultRowColorTable(tblKhachHang, 1);

        txthoTen.setForeground(Color.black);
        txthoTen.setBackground(new Color(255, 255, 255));
        txthoTen.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        txtSDT.setForeground(Color.black);
        txtSDT.setBackground(new Color(255, 255, 255));
        txtSDT.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        txtmaKH.setForeground(Color.black);
        txtmaKH.setBackground(new Color(255, 255, 255));
        txtmaKH.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        txtdiaChi.setForeground(Color.black);
        txtdiaChi.setBackground(new Color(255, 255, 255));
        txtdiaChi.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        txtTimKiemKH.setForeground(Color.black);
        txtTimKiemKH.setBackground(new Color(204, 204, 255));
        txtTimKiemKH.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));

        lblHovaTenKH.setForeground(new Color(0, 0, 255));
        lblSDTKH.setForeground(new Color(0, 0, 255));
        lblMaKH.setForeground(new Color(0, 0, 255));
        lblDiaChiKH.setForeground(new Color(0, 0, 255));
        jLabel63.setBackground(new Color(255, 255, 255));
        jLabel63.setForeground(Color.black);

        jPanel3.setBackground(Color.white);
        jPanel3.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 255)));
        jPanel13.setBackground(new Color(255, 255, 255));
        jPanel4.setBackground(new Color(255, 255, 255));
        pnMarginKhachHangTitle.setBackground(new Color(204, 204, 255));
        jLabel29.setForeground(Color.black);
        btnlamMoiKhachHang.setForeground(Color.black);
        btnlamMoiKhachHang.setBackground(new Color(204, 204, 255));
        btnThemKhachHang.setForeground(Color.black);
        btnThemKhachHang.setBackground(new Color(204, 204, 255));
        btncapNhatKhachHang.setForeground(Color.black);
        btncapNhatKhachHang.setBackground(new Color(204, 204, 255));
        btnNextKH.setBackground(new Color(204, 204, 255));
        btnLastKH.setBackground(new Color(204, 204, 255));
        btnPrevKH.setBackground(new Color(204, 204, 255));
        btnFirstKH.setBackground(new Color(204, 204, 255));
        cboKh.setForeground(Color.white);
        cboKh.setBackground(new Color(255, 255, 255));
        cbotimKiemKH.setForeground(Color.black);
        cbotimKiemKH.setBackground(new Color(204, 204, 255));

        tblKhachHang.setBackground(new Color(255, 255, 255));
        tblKhachHang.setForeground(Color.black);
        jLabel62.setBackground(new Color(255, 255, 255));
        jLabel62.setForeground(Color.black);
        tblKhachHang.setSelectionForeground(Color.black);
        //chức vụ
        cardTaiKhoanChucVu.setBackground(Color.white);
        defaultRowColorTable(tblChucVu, 1);

        lblTenCV.setForeground(new Color(0, 0, 255));
        lblMaChucVu.setForeground(new Color(0, 0, 255));
        jLabel90.setForeground(new Color(0, 0, 255));

        txttenCV.setForeground(Color.black);
        txttenCV.setBackground(new Color(255, 255, 255));
        txttenCV.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        txtmaCV.setForeground(Color.black);
        txtmaCV.setBackground(new Color(255, 255, 255));
        txtmaCV.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        txtMoTaCV.setForeground(Color.black);
        txtMoTaCV.setBackground(new Color(255, 255, 255));
        txtMoTaCV.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));

        jPanel19.setBackground(Color.white);
        jPanel19.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 255)));
        jPanel21.setBackground(new Color(255, 255, 255));
        pnMarginTitleChucVu.setBackground(new Color(204, 204, 255));
        jPanel20.setBackground(new Color(255, 255, 255));
        lblTitleTimKiemChucVu.setForeground(Color.black);
        jLabel93.setForeground(Color.black);
        btnLamMoiCV.setForeground(Color.black);
        btnLamMoiCV.setBackground(new Color(204, 204, 255));
        btnThemCV.setForeground(Color.black);
        btnThemCV.setBackground(new Color(204, 204, 255));
        btnCapNhatCV.setForeground(Color.black);
        btnCapNhatCV.setBackground(new Color(204, 204, 255));
        btnNextCV.setBackground(new Color(204, 204, 255));
        btnLastCV.setBackground(new Color(204, 204, 255));
        btnPrevCV.setBackground(new Color(204, 204, 255));
        btnFirstCV.setBackground(new Color(204, 204, 255));
        cboCV.setForeground(Color.white);
        cboCV.setBackground(new Color(255, 255, 255));
        txtTimCV.setForeground(Color.black);
        txtTimCV.setBackground(new Color(204, 204, 255));
        txtTimCV.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));

        tblChucVu.setBackground(new Color(255, 255, 255));
        tblChucVu.setForeground(Color.black);
        jLabel99.setBackground(new Color(255, 255, 255));
        jLabel99.setForeground(Color.black);
        tblChucVu.setSelectionForeground(Color.black);
        //----------------------------------SẢN PHẨM-----------------------------------Vi
        defaultRowColorTable(tblSanPham, 1);

        cardSanPham.setBackground(new Color(255, 255, 255));
        cardLoai2.setBackground(new Color(255, 255, 255));
        tblSanPham.setBackground(new Color(255, 255, 255));
        tblSanPham.setForeground(Color.black);
        txtMaSP.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        txtMaSP.setForeground(Color.black);
        txtMaSP.setBackground(Color.white);
        txtTenSP.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        txtTenSP.setForeground(Color.black);
        txtTenSP.setBackground(Color.white);
        txtNgayNhapSP.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        txtNgayNhapSP.setForeground(Color.black);
        txtNgayNhapSP.setBackground(Color.white);
        txtGiaNhapSP.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        txtGiaNhapSP.setForeground(Color.black);
        txtGiaNhapSP.setBackground(Color.white);
        txtGiaNhapSP3.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        txtGiaNhapSP3.setForeground(Color.black);
        txtGiaNhapSP3.setBackground(Color.white);
        txtGiaBanSP.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        txtGiaBanSP.setForeground(Color.black);
        txtGiaBanSP.setBackground(Color.white);
        txtGiaBanSP1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        txtGiaBanSP1.setForeground(Color.black);
        txtGiaBanSP1.setBackground(Color.white);
        txtSoLuongSP.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        txtSoLuongSP.setForeground(Color.black);
        txtSoLuongSP.setBackground(Color.white);
        txtMoTaSP.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
        txtMoTaSP.setForeground(Color.black);
        txtMoTaSP.setBackground(Color.white);
        cboMaHang.setForeground(Color.black);
        cboMaHang.setBackground(Color.white);
        cboMaLoai.setForeground(Color.black);
        cboMaLoai.setBackground(Color.white);
        btnThemSP.setForeground(Color.black);
        lblVND1.setBackground(Color.white);
        lblVND1.setForeground(Color.red);
        lblVND2.setBackground(Color.white);
        lblVND2.setForeground(Color.red);
        lblTimKiemTempSP.setBackground(Color.white);
//        lblTimKiemTempSP.setForeground(Color.black);
        lblFormat.setBackground(Color.white);
        lblFormat.setForeground(Color.black);
        txtTimKiemSP.setForeground(Color.black);
        txtTimKiemSP.setBackground(new Color(204, 204, 255));
        cboTimKiemSP.setForeground(Color.black);
        cboTimKiemSP.setBackground(new Color(204, 204, 255));
        lblBanGhiSP.setForeground(Color.black);
        pnSP.setBackground(Color.white);
        btnThemSP.setBackground(new Color(204, 204, 255));
        btnCapNhatSP.setForeground(Color.black);
        btnCapNhatSP.setBackground(new Color(204, 204, 255));
        btnLamMoiSP.setForeground(Color.black);
        btnLamMoiSP.setBackground(new Color(204, 204, 255));
        cboSXSP.setForeground(Color.black);
        cboSXSP.setBackground(Color.white);
        lblTHSXSP.setForeground(Color.black);
        pnBtnSapxepSP.setBackground(Color.white);
        pnBangTTSP.setBackground(new Color(204, 204, 255));
        btnFirstSP.setBackground(new Color(204, 204, 255));
        btnPrevSP.setBackground(new Color(204, 204, 255));
        btnNextSP.setBackground(new Color(204, 204, 255));
        btnLastSP.setBackground(new Color(204, 204, 255));
        lblMaSP.setForeground(new Color(0, 0, 255));
        lblTenSP.setForeground(new Color(0, 0, 255));
        lblNgayNhapSP.setForeground(new Color(0, 0, 255));
        lblGiaNhapSP.setForeground(new Color(0, 0, 255));
        lblGiaBanSP.setForeground(new Color(0, 0, 255));
        lblTenHangSP.setForeground(new Color(0, 0, 255));
        lblTenLoaiSP.setForeground(new Color(0, 0, 255));
        lblSoLuongSP.setForeground(new Color(0, 0, 255));
        lblMoTaSP.setForeground(new Color(0, 0, 255));
        lblAnh.setForeground(Color.black);
        tblSanPham.setSelectionForeground(Color.black);
        //pnSP.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 255)));
        //Loai
        defaultRowColorTable(tblLoaiHang, 1);

        cardLoai.setBackground(Color.white);
        tblLoaiHang.setBackground(new Color(255, 255, 255));
        tblLoaiHang.setForeground(Color.black);
        txtMaLoai.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        txtMaLoai.setForeground(Color.black);
        txtMaLoai.setBackground(Color.white);
        txtTenLoai.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        txtTenLoai.setForeground(Color.black);
        txtTenLoai.setBackground(Color.white);
        lblMaLoai.setBackground(Color.white);
        lblMaLoai.setForeground(new Color(0, 0, 255));
        lblTenLoai.setBackground(Color.white);
        lblTenLoai.setForeground(new Color(0, 0, 255));
        pnBangTTLoai.setBackground(new Color(204, 204, 255));
        pnbtnSXLoai.setBackground(Color.white);
        lblTHSXLoai.setBackground(Color.white);
        lblTHSXLoai.setForeground(Color.white);
        cboLoai.setForeground(Color.white);
        cboLoai.setBackground(Color.white);
        pnLoai.setBackground(Color.white);
        txtTimKiemLoai.setBackground(new Color(204, 204, 255));
        lblLoaiTemp.setBackground(new Color(204, 204, 255));
        btnThemLoai.setForeground(Color.black);
        btnThemLoai.setBackground(new Color(204, 204, 255));
        btnCapNhatLoai.setForeground(Color.black);
        btnCapNhatLoai.setBackground(new Color(204, 204, 255));
        btnLamMoiLoai.setForeground(Color.black);
        btnLamMoiLoai.setBackground(new Color(204, 204, 255));
        lblBanGhiLoai.setForeground(Color.white);
        jLabel105.setForeground(Color.black);
        pnSXLoai.setBackground(Color.white);
        btnFirstLoai.setBackground(new Color(204, 204, 255));
        btnPrevLoai.setBackground(new Color(204, 204, 255));
        btnNextLoai.setBackground(new Color(204, 204, 255));
        btnLastLoai.setBackground(new Color(204, 204, 255));

        tblLoaiHang.setSelectionForeground(Color.black);
        //pnLoai.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 255)));
        //Hang
        defaultRowColorTable(tblHang, 1);

        cardHangSanXuat.setBackground(Color.white);
        cardLoai1.setBackground(Color.white);
        tblHang.setBackground(new Color(255, 255, 255));
        tblHang.setForeground(Color.black);
        txtMaHang.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        txtMaHang.setForeground(Color.black);
        txtMaHang.setBackground(Color.white);
        txtTenHang.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        txtTenHang.setForeground(Color.black);
        txtTenHang.setBackground(Color.white);
        lblMaHang.setBackground(Color.white);
        lblMaHang.setForeground(new Color(0, 0, 255));
        lblTenHang.setBackground(Color.white);
        lblTenHang.setForeground(new Color(0, 0, 255));
        pnBangTTHang.setBackground(new Color(204, 204, 255));
        pnbtnSXHang.setBackground(Color.white);
        lblTHSXHang.setBackground(Color.white);
        lblTHSXHang.setForeground(Color.white);
        cboHang.setForeground(Color.white);
        cboHang.setBackground(Color.white);
        pnHang.setBackground(Color.white);
        txtTimKiemHang.setBackground(new Color(204, 204, 255));
        lblHangTemp.setBackground(new Color(204, 204, 255));
        btnThemHang.setForeground(Color.black);
        btnThemHang.setBackground(new Color(204, 204, 255));
        btnCapNhatHang.setForeground(Color.black);
        btnCapNhatHang.setBackground(new Color(204, 204, 255));
        btnLamMoiHang.setForeground(Color.black);
        btnLamMoiHang.setBackground(new Color(204, 204, 255));
        lblBanGhiHang.setForeground(Color.white);
        lblTimKiemHang.setForeground(Color.black);
        pnSXHang.setBackground(Color.white);
        btnFirstHang.setBackground(new Color(204, 204, 255));
        btnPrevHang.setBackground(new Color(204, 204, 255));
        btnNextHang.setBackground(new Color(204, 204, 255));
        btnLastHang.setBackground(new Color(204, 204, 255));

        tblHang.setSelectionForeground(Color.black);
        //pnHang.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 255)));
        //----------------------------------HÓA ĐƠN----------------------------------Sỹ
        cardHoaDon.setBackground(Color.white);

        defaultRowColorTable(tblHoaDon, 1);
        jLabel118.setForeground(new Color(0, 0, 255));
        jLabel119.setForeground(new Color(0, 0, 255));
        jLabel117.setForeground(new Color(0, 0, 255));
        jLabel135.setForeground(new Color(0, 0, 255));
        jLabel120.setForeground(new Color(0, 0, 255));
        jLabel121.setForeground(new Color(0, 0, 255));

        txtTENKH.setForeground(Color.black);
        txtTENKH.setBackground(new Color(255, 255, 255));
        txtTENKH.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        txtMAKH.setForeground(Color.black);
        txtMAKH.setBackground(new Color(255, 255, 255));
        txtMAKH.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        txtPhanTramGG.setForeground(Color.black);
        txtPhanTramGG.setBackground(new Color(255, 255, 255));
        txtPhanTramGG.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        txtTienNhan.setForeground(Color.black);
        txtTienNhan.setBackground(new Color(255, 255, 255));
        txtTienNhan.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        txtTichDiem.setForeground(Color.black);
        txtTichDiem.setBackground(new Color(255, 255, 255));
        txtTichDiem.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        txtTENNV.setForeground(Color.black);
        txtTENNV.setBackground(new Color(255, 255, 255));
        txtTENNV.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));

        jPanel28.setBackground(Color.white);
        jPanel28.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 255)));
        jPanel30.setBackground(new Color(255, 255, 255));
        pnMarginHoaDon.setBackground(new Color(204, 204, 255));
        jLabel81.setBackground(new Color(204, 204, 255));
        lblTittleFindHoaDon.setForeground(Color.black);
        jLabel48.setForeground(new Color(0, 0, 0));
        jPanel29.setBackground(new Color(255, 255, 255));

        btnSuDungDiem.setForeground(Color.black);
        btnSuDungDiem.setBackground(new Color(51, 255, 255));
        btnIn.setForeground(Color.black);
        btnIn.setBackground(new Color(51, 255, 255));
        btnNexthd.setBackground(new Color(204, 204, 255));
        btnLasthd.setBackground(new Color(204, 204, 255));
        btnPrevhd.setBackground(new Color(204, 204, 255));
        btnFirsthd.setBackground(new Color(204, 204, 255));
        comboboxHD.setForeground(Color.white);
        comboboxHD.setBackground(new Color(255, 255, 255));
        txtTimKiemhd.setForeground(Color.black);
        txtTimKiemhd.setBackground(new Color(204, 204, 255));
        txtTimKiemhd.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));

        jLabel65.setForeground(Color.black);
        jLabel104.setForeground(Color.black);
        tblHoaDon.setBackground(new Color(255, 255, 255));
        tblHoaDon.setForeground(Color.black);
        tblHoaDon.setSelectionForeground(Color.black);
        //--------------------------------GIỎ HÀNG-------------------------------------(Sỹ)
        cardGioHang.setBackground(new Color(255, 255, 255));
        cardHoaDonSanPham.setBackground(new Color(255, 255, 255));
        cardHoaDonQR.setBackground(new Color(255, 255, 255));
        pnQR.setBackground(new Color(255, 255, 255));

        jLabel77.setForeground(Color.black);
        jLabel11.setForeground(Color.black);
        jLabel10.setForeground(Color.black);
        jLabel13.setForeground(Color.black);
        lblNameCustomer.setForeground(new Color(0, 0, 255));

        txtSdtKH.setForeground(Color.black);
        txtSdtKH.setBackground(new Color(255, 255, 255));
        txtSdtKH.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        txtTongtiensp.setForeground(Color.black);
        txtTongtiensp.setBackground(new Color(255, 255, 255));
        txtTongtiensp.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));

        txtFindNameProductCart.setForeground(Color.black);
        txtFindNameProductCart.setBackground(new Color(255, 255, 255));
        txtFindNameProductCart.setLineColor(new Color(3, 155, 216));

        btnXNKH.setForeground(Color.black);
        btnXNKH.setBackground(new Color(255, 153, 102));
        btnXacNhanDonHang.setForeground(Color.black);
        btnXacNhanDonHang.setBackground(new Color(102, 204, 255));

        tblCart.setBackground(new Color(255, 255, 255));
        tblCart.setForeground(Color.black);

        btnxoaGioHang.setBackground(new Color(255, 255, 255));
        pnPupopMenu.setBackground(new Color(255, 255, 255));
        pnGioHangSanPham.setBackground(new Color(255, 255, 255));

        //------------------------------THỐNG KÊ--------------------------------------(Vinh)
        lightModeTK();

        //------------------------------GIỚI THIỆU------------------------------------Vinh
        //Giới thiệu sản phẩm
        cardGioiThieuSanPham.setBackground(new Color(255, 255, 255));
        jLabel2.setBackground(new Color(255, 255, 255));
        jLabel2.setForeground(Color.black);

        jLabel3.setBackground(new Color(255, 255, 255));
        jLabel3.setForeground(Color.black);

        jLabel4.setBackground(new Color(255, 255, 255));
        jLabel4.setForeground(Color.black);

        jLabel5.setBackground(new Color(255, 255, 255));
        jLabel5.setForeground(Color.black);

        jLabel6.setBackground(new Color(255, 255, 255));
        jLabel6.setForeground(Color.black);

        //Giới thiệu thành viên
        cardGioiThieuThanhVien.setBackground(new Color(255, 255, 255));

        jLabel27.setBackground(new Color(255, 255, 255));
        jLabel27.setForeground(Color.black);

        jLabel28.setBackground(new Color(255, 255, 255));
        jLabel28.setForeground(Color.black);

        jLabel33.setBackground(new Color(255, 255, 255));
        jLabel33.setForeground(Color.black);

        jLabel34.setBackground(new Color(255, 255, 255));
        jLabel34.setForeground(Color.black);

        //------------------------------CÀI ĐẶT--------------------------------------Đang
        //cài đặt giao diện
        lblCDDD1.setForeground(Color.black);
        lblCDDD2.setForeground(Color.black);
        lblCDDD3.setForeground(Color.black);
        lightMode1.setBorder(BorderFactory.createLineBorder(new Color(0, 204, 204)));
        darkMode1.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255)));
        pnSetMode.setBackground(new Color(255, 255, 255));
        cardCaiDatGiaoDien.setBackground(new Color(255, 255, 255));
        pnReviewMode.setBackground(new Color(255, 255, 255));
        rdoDarkmode.setForeground(new Color(0, 0, 0));
        rdoLightmode.setForeground(new Color(0, 0, 0));
        pnSetMode.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black));
        //cài đặt đường dẫn ảnh
        lblTitleSaveQRProduct.setForeground(new Color(0, 0, 204));
        lblOpenBannerImg.setForeground(new Color(0, 0, 204));
        txtSaveQrProduct.setForeground(Color.black);
        txtSaveQrProduct.setBackground(Color.white);
        txtBanerPathImg1.setForeground(Color.black);
        txtBanerPathImg1.setBackground(Color.white);
        txtBanerPathImg2.setForeground(Color.black);

        txtBanerPathImg2.setBackground(Color.white);
        txtBanerPathImg3.setForeground(Color.black);

        txtBanerPathImg3.setBackground(Color.white);
        btnResetSaveQRProduct.setForeground(Color.black);
        btnResetSaveQRProduct.setBackground(new Color(204, 204, 255));
        btnResetSaveBannerImg.setBackground(new Color(204, 204, 255));
        btnResetSaveBannerImg.setForeground(Color.black);

        pnCaiDatDuongDan.setBackground(Color.white);
        pnReviewLightMode.setVisible(true);
        pnReviewDarkMode.setVisible(false);
    }

    boolean darkMode = false;

    void setrdoMode() {
        if (rdoLightmode.isSelected()) {
            //bật chế độ lightmode
            lightMode();
            darkMode = false;
        } else {
            //bật chế độ darkmode
            darkMode();
            darkMode = true;
        }
    }
    private void rdoLightmodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoLightmodeActionPerformed
        rdoLightmode.setSelected(true);
        setrdoMode();
    }//GEN-LAST:event_rdoLightmodeActionPerformed

    private void rdoDarkmodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoDarkmodeActionPerformed
        rdoDarkmode.setSelected(true);
        setrdoMode();
    }//GEN-LAST:event_rdoDarkmodeActionPerformed

    private void lblMaHangTempMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMaHangTempMouseClicked
        lblMaHangTemp.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_lblMaHangTempMouseClicked

    private void lblMaSPTempMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMaSPTempMouseClicked
        lblMaSPTemp.setVisible(true);
    }//GEN-LAST:event_lblMaSPTempMouseClicked

    private void lblMaLoaiTempMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMaLoaiTempMouseClicked
        lblMaLoaiTemp.setVisible(true);
    }//GEN-LAST:event_lblMaLoaiTempMouseClicked

    private void lblNgayNhaptempMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNgayNhaptempMouseClicked
        lblNgayNhaptemp.setVisible(true);
    }//GEN-LAST:event_lblNgayNhaptempMouseClicked
//Định dạng format trang in

    public PageFormat getPageFormat(PrinterJob pj) {

        PageFormat pf = pj.defaultPage();
        Paper paper = pf.getPaper();

        double bodyHeight = bHeight;
        double headerHeight = 10.0;
        double footerHeight = 10.0;
        double width = cm_to_pp(15);
        double height = cm_to_pp(headerHeight + bodyHeight + footerHeight);
        paper.setSize(width, height);
        paper.setImageableArea(0, 10, width, height - cm_to_pp(1));

        pf.setOrientation(PageFormat.PORTRAIT);
        pf.setPaper(paper);

        return pf;
    }

    protected static double cm_to_pp(double cm) {
        return toPPI(cm * 0.393600787);
    }

    protected static double toPPI(double inch) {
        return inch * 72d;

    }

    public class BillPrintable implements Printable {
//In hóa đơn

        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
                throws PrinterException {
            int r = tenSP.size();
            ReadMoney rm = new ReadMoney();
            ImageIcon icon = new ImageIcon("src\\com\\asset\\header\\logokhongvien-01.png");
            int result = NO_SUCH_PAGE;
            if (pageIndex == 0) {

                Graphics2D g2d = (Graphics2D) graphics;
                double width = pageFormat.getImageableWidth();
                g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());

                //  FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));
                try {
                    int y = 20;
                    int yShift = 10;
                    int headerRectHeight = 15;
                    // int headerRectHeighta=40;

                    g2d.setFont(new Font("Monospaced", Font.PLAIN, 9));
                    g2d.drawImage(icon.getImage(), 160, 80, 90, 110, rootPane);
                    y += yShift + 135;
                    g2d.drawString("------------------------------------------------------------------------", 12, y);
                    y += yShift;
                    g2d.drawString("                                  DAVISY                              ", 12, y);
                    y += yShift;
                    g2d.drawString("                        Địa chỉ: 288 Nguyen Van Linh                     ", 12, y);
                    y += yShift;
                    g2d.drawString("                    https://www.facebook.com/davisy.dev                      ", 12, y);
                    y += yShift;
                    g2d.drawString("                             HÓA ĐƠN THANH TOÁN                            ", 12, y);
                    y += yShift;
                    g2d.drawString("------------------------------------------------------------------------", 12, y);
                    y += headerRectHeight;

                    g2d.drawString("STT       Tên sản phẩm        Số lượng        Đơn giá         Thành tiền   ", 10, y);
                    y += yShift;
                    g2d.drawString("------------------------------------------------------------------------", 10, y);
                    y += headerRectHeight;
                    int i = 1;
                    float tongThanhTien = 0;
                    for (int s = 0; s < r; s++) {
                        g2d.drawString(String.valueOf(i), 15, y);
                        g2d.drawString(tenSP.get(s), 65, y);
                        g2d.drawString(SL.get(s), 185, y);
                        g2d.drawString(giaSP.get(s), 265, y);
                        g2d.drawString(thanhTien.get(s), 355, y);
                        tongThanhTien += Float.valueOf(thanhTien.get(s));//tienGiam = hd.getTienGiam();
                        y += yShift + 15;
                        i++;

                    }

                    g2d.drawString("------------------------------------------------------------------------", 10, y);
                    y += yShift + 5;
                    g2d.drawString(" Tiền giảm :                                                    " + tienGiam + "   ", 10, y);
                    y += yShift + 5;
                    g2d.drawString("------------------------------------------------------------------------", 10, y);
                    y += yShift + 5;
                    g2d.drawString(" Thành tiền(Bằng số):                                         " + (tongThanhTien - tienGiam)+"đ"+ "   ", 10, y);
                    y += yShift + 5;
                    g2d.drawString("                     " + rm.read_money(String.valueOf((int) (tongThanhTien - tienGiam))) + "   ", 10, y);
                    y += yShift + 5;
                    g2d.drawString("------------------------------------------------------------------------", 10, y);
                    y += yShift + 5;
                    g2d.drawString(" Tiền nhận :                                                  " + txtTienNhan.getText()+"đ"+ "   ", 10, y);
                    y += yShift + 5;
                    g2d.drawString("------------------------------------------------------------------------", 10, y);
                    y += yShift + 5;
                    g2d.drawString(" Tiền thừa :                                                  " + (Float.valueOf(txtTienNhan.getText())-(tongThanhTien - tienGiam))+"đ" + "   ", 10, y);
                    y += yShift + 5;

                    g2d.drawString("************************************************************************", 10, y);
                    y += yShift + 3;
                    g2d.drawString("                             Trân trọng cảm ơn!                          ", 10, y);
                    y += yShift + 3;
                    g2d.drawString("************************************************************************", 10, y);
                    y += yShift;
                    g2d.drawString("                          Một sản phẩm của DAVISY                      ", 10, y);
                    y += yShift;
                    g2d.drawString("                        Email: davisy.dev@gmail.com                ", 10, y);
                    y += yShift;

                } catch (Exception e) {
                    e.printStackTrace();
                }

                result = PAGE_EXISTS;
            }
            return result;
        }
    }

    /*----------------------------------------------------------*/
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
//                Login LG = new Login();
//                LG.setVisible(true);
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
    private javax.swing.JLabel CaiDatHr1;
    private javax.swing.JLabel CaiDatHr2;
    private javax.swing.JLabel CaiDatTittle1;
    private javax.swing.JLabel CaiDatTittle2;
    private javax.swing.JLabel GioHangHr;
    private javax.swing.JLabel GioHangTittle1;
    private javax.swing.JLabel GioiThieuHr1;
    private javax.swing.JLabel GioiThieuHr2;
    private javax.swing.JLabel GioiThieutittle1;
    private javax.swing.JLabel GioiThieutittle2;
    private javax.swing.JLabel HoaDonHr1;
    private javax.swing.JLabel HoaDonTittle1;
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
    private javax.swing.JLabel banner1;
    private javax.swing.JLabel banner2;
    private javax.swing.JLabel banner3;
    private com.swing.Button btnCapNhatCV;
    private com.swing.Button btnCapNhatHang;
    private com.swing.Button btnCapNhatLoai;
    private com.swing.Button btnCapNhatNV;
    private com.swing.Button btnCapNhatSP;
    private com.swing.Button btnDangXuat;
    private com.swing.Button btnExport;
    private com.swing.Button btnFirstCV;
    private com.swing.Button btnFirstHang;
    private com.swing.Button btnFirstKH;
    private com.swing.Button btnFirstLoai;
    private com.swing.Button btnFirstNV;
    private com.swing.Button btnFirstSP;
    private com.swing.Button btnFirsthd;
    private com.swing.Button btnFormatFileExcel;
    private com.swing.Button btnGioHang;
    private com.swing.Button btnGioiThieu;
    private com.swing.Button btnHoaDon;
    private com.swing.Button btnIn;
    private com.swing.Button btnIn1;
    private com.swing.Button btnLamMoiCV;
    private com.swing.Button btnLamMoiHang;
    private com.swing.Button btnLamMoiLoai;
    private com.swing.Button btnLamMoiNV;
    private com.swing.Button btnLamMoiSP;
    private com.swing.Button btnLastCV;
    private com.swing.Button btnLastHang;
    private com.swing.Button btnLastKH;
    private com.swing.Button btnLastLoai;
    private com.swing.Button btnLastNV;
    private com.swing.Button btnLastSP;
    private com.swing.Button btnLasthd;
    private com.swing.Button btnMenu;
    private com.swing.Button btnNextCV;
    private com.swing.Button btnNextHang;
    private com.swing.Button btnNextKH;
    private com.swing.Button btnNextLoai;
    private com.swing.Button btnNextNV;
    private com.swing.Button btnNextSP;
    private com.swing.Button btnNexthd;
    private com.swing.Button btnPrevCV;
    private com.swing.Button btnPrevHang;
    private com.swing.Button btnPrevKH;
    private com.swing.Button btnPrevLoai;
    private com.swing.Button btnPrevNV;
    private com.swing.Button btnPrevSP;
    private com.swing.Button btnPrevhd;
    private com.swing.Button btnResetSaveBannerImg;
    private com.swing.Button btnResetSaveQRProduct;
    private com.swing.Button btnSanPham;
    private com.swing.Button btnSuDungDiem;
    private com.swing.Button btnTaiKhoan;
    private com.swing.Button btnThemCV;
    private com.swing.Button btnThemHang;
    private com.swing.Button btnThemKhachHang;
    private com.swing.Button btnThemLoai;
    private com.swing.Button btnThemNV;
    private com.swing.Button btnThemSP;
    private com.swing.Button btnThongKe;
    private com.swing.Button btnTrangChu;
    private com.swing.Button btnXNKH;
    private com.swing.Button btnXacNhanDonHang;
    private com.swing.Button btnXoaCV;
    private com.swing.Button btnXoaHang;
    private com.swing.Button btnXoaLoai;
    private com.swing.Button btnXoaNV;
    private com.swing.Button btnXoaSP;
    private com.swing.Button btnXoahd;
    private com.swing.EditButton btnaz;
    private com.swing.EditButton btnazCV;
    private com.swing.EditButton btnazHang;
    private com.swing.EditButton btnazLoai;
    private com.swing.EditButton btnazNV;
    private com.swing.EditButton btnazhd;
    private com.swing.EditButton btnazsp;
    private com.swing.Button btncapNhatKhachHang;
    private com.swing.Button btnlamMoiKhachHang;
    private com.swing.Button btnxoaGioHang;
    private com.swing.Button btnxoaKhachHang;
    private com.swing.EditButton btnza;
    private com.swing.EditButton btnzaCV;
    private com.swing.EditButton btnzaHang;
    private com.swing.EditButton btnzaLoai;
    private com.swing.EditButton btnzahd;
    private com.swing.EditButton btnzanv;
    private com.swing.EditButton btnzasp;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private com.swing.PanelRound cardCaiDatDuongDan;
    private com.swing.PanelRound cardCaiDatGiaoDien;
    private com.swing.PanelRound cardChiTietHoaDon;
    private com.swing.PanelRound cardGioHang;
    private com.swing.PanelRound cardGioiThieuSanPham;
    private com.swing.PanelRound cardGioiThieuThanhVien;
    private com.swing.PanelRound cardHangSanXuat;
    private com.swing.PanelRound cardHangSanXuat1;
    private com.swing.PanelRound cardHoaDon;
    private javax.swing.JPanel cardHoaDonQR;
    private javax.swing.JPanel cardHoaDonSanPham;
    private com.swing.PanelRound cardKhachHang;
    private com.swing.PanelRound cardLoai;
    private com.swing.PanelRound cardLoai1;
    private com.swing.PanelRound cardLoai2;
    private javax.swing.JPanel cardMenubar;
    private javax.swing.JPanel cardMenubarCaiDat;
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
    private com.swing.PanelRound cardTrangChuTongQuan;
    private com.swing.Combobox cboCV;
    private com.swing.Combobox cboChoice;
    private com.swing.Combobox cboDayDT;
    private com.swing.Combobox cboDaySP;
    private com.swing.Combobox cboHang;
    private com.swing.Combobox cboKh;
    private com.swing.Combobox cboLoai;
    private com.swing.Combobox cboMaHang;
    private com.swing.Combobox cboMaLoai;
    private com.swing.Combobox cboMonthDT;
    private com.swing.Combobox cboMonthSP;
    private com.swing.Combobox cboSXSP;
    private com.swing.Combobox cboSort;
    private com.swing.Combobox cboSortNV;
    private com.swing.Combobox cboTimKiemSP;
    private com.swing.Combobox cboVaiTro;
    private com.swing.Combobox cboYearDT;
    private com.swing.Combobox cboYearSP;
    private com.swing.Combobox cbotimKiemKH;
    private com.swing.Combobox comboboxHD;
    private com.frame.DarkMode darkMode1;
    private com.swing.datechooser.DateChooser dateChooser1;
    private com.swing.datechooser.DateChooser dateChooser2;
    private com.frame.Header header2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
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
    private javax.swing.JLabel jLabel41;
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
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
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
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator22;
    private javax.swing.JSeparator jSeparator23;
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
    private javax.swing.JLabel jlbClose;
    private javax.swing.JLabel jlbState;
    private javax.swing.JPanel jplContainer;
    private javax.swing.JPanel jplLose;
    private javax.swing.JPanel jplState;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JLabel lblAnhSpBanChay;
    private javax.swing.JLabel lblBanGhiHang;
    private javax.swing.JLabel lblBanGhiLoai;
    private javax.swing.JLabel lblBanGhiSP;
    private javax.swing.JLabel lblCDDD1;
    private javax.swing.JLabel lblCDDD2;
    private javax.swing.JLabel lblCDDD3;
    private javax.swing.JLabel lblChucVu;
    private javax.swing.JLabel lblDarkmode;
    private javax.swing.JLabel lblDay;
    private javax.swing.JLabel lblDay1;
    private javax.swing.JLabel lblDay2;
    private javax.swing.JLabel lblDay3;
    private javax.swing.JLabel lblDay4;
    private javax.swing.JLabel lblDay5;
    private javax.swing.JLabel lblDiaChiKH;
    private javax.swing.JLabel lblDiaChiNV;
    private javax.swing.JLabel lblEmailNV;
    private com.swing.Button lblExportInfor;
    private javax.swing.JLabel lblFormat;
    private javax.swing.JLabel lblGiaBanSP;
    private javax.swing.JLabel lblGiaNhapSP;
    private javax.swing.JLabel lblHangTemp;
    private javax.swing.JLabel lblHovaTenKH;
    private javax.swing.JLabel lblHovaTenNV;
    private com.swing.Button lblImportFileExcel;
    private javax.swing.JLabel lblLightmode;
    private javax.swing.JLabel lblLoaiTemp;
    private javax.swing.JLabel lblMaChucVu;
    private javax.swing.JLabel lblMaHang;
    private javax.swing.JLabel lblMaHangTemp;
    private javax.swing.JLabel lblMaKH;
    private javax.swing.JLabel lblMaLoai;
    private javax.swing.JLabel lblMaLoaiTemp;
    private javax.swing.JLabel lblMaSP;
    private javax.swing.JLabel lblMaSPTemp;
    private javax.swing.JLabel lblMatKhau;
    private javax.swing.JLabel lblMoTaSP;
    private javax.swing.JLabel lblNV1;
    private javax.swing.JLabel lblNV2;
    private javax.swing.JLabel lblNV3;
    private javax.swing.JLabel lblNameCustomer;
    private javax.swing.JLabel lblNgayNhapSP;
    private javax.swing.JLabel lblNgayNhaptemp;
    private javax.swing.JLabel lblNgaySinhNV;
    private javax.swing.JLabel lblOpenBannerImg;
    private javax.swing.JLabel lblOpenBannerImg1;
    private javax.swing.JLabel lblOpenBannerImg2;
    private javax.swing.JLabel lblOpenBannerImg3;
    private javax.swing.JLabel lblOpenQRProduct;
    private javax.swing.JLabel lblRecordNV;
    private javax.swing.JLabel lblSDTKH;
    private javax.swing.JLabel lblSDTNV;
    private javax.swing.JLabel lblSoLuongSP;
    private javax.swing.JLabel lblTHSXHang;
    private javax.swing.JLabel lblTHSXLoai;
    private javax.swing.JLabel lblTHSXSP;
    private javax.swing.JLabel lblTenCV;
    private javax.swing.JLabel lblTenDN;
    private javax.swing.JLabel lblTenHang;
    private javax.swing.JLabel lblTenHangSP;
    private javax.swing.JLabel lblTenLoai;
    private javax.swing.JLabel lblTenLoaiSP;
    private javax.swing.JLabel lblTenSP;
    private javax.swing.JLabel lblTichDiem;
    private javax.swing.JLabel lblTichDiemHD;
    private javax.swing.JLabel lblTimKiemCV;
    private javax.swing.JLabel lblTimKiemHD;
    private javax.swing.JLabel lblTimKiemHang;
    private javax.swing.JLabel lblTimKiemKH;
    private javax.swing.JLabel lblTimKiemNV;
    private javax.swing.JLabel lblTimKiemTempSP;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblTitleSaveQRProduct;
    private javax.swing.JLabel lblTitleTimKiemChucVu;
    private javax.swing.JLabel lblTitleTimKiemNV;
    private javax.swing.JLabel lblTittleFindHoaDon;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JLabel lblVND1;
    private javax.swing.JLabel lblVND2;
    private javax.swing.JLabel lblrecordCV;
    private javax.swing.JLabel lblrecordHang;
    private javax.swing.JLabel lblrecordKH;
    private javax.swing.JLabel lblrecordLoai;
    private javax.swing.JLabel lblrecordSP;
    private javax.swing.JLabel lblrecordhd;
    private javax.swing.JLabel lbltenNV;
    private com.frame.LightMode lightMode1;
    private com.frame.LoadingMain loadingMain;
    private javax.swing.JLabel opacity;
    private com.swing.PanelRound panelRound1;
    private com.swing.PanelRound panelRound2;
    private com.swing.PanelRound panelRound3;
    private com.swing.PanelRound panelRound4;
    private com.swing.PanelRound panelRound5;
    private com.swing.PanelRound panelRound6;
    private com.swing.PanelRound panelRound7;
    private com.swing.PanelRound panelRound8;
    private javax.swing.JPanel pbMarginReviewMode;
    private javax.swing.JPanel pnBangTTHang;
    private javax.swing.JPanel pnBangTTLoai;
    private javax.swing.JPanel pnBangTTSP;
    private javax.swing.JPanel pnBtnSapxepSP;
    private com.swing.PanelRound pnCaiDatDuongDan;
    private javax.swing.JPanel pnGioHangSanPham;
    private javax.swing.JPanel pnHang;
    private javax.swing.JPanel pnLoai;
    private javax.swing.JPanel pnMain;
    private javax.swing.JPanel pnMarginHoaDon;
    private javax.swing.JPanel pnMarginKhachHangTitle;
    private javax.swing.JPanel pnMarginTKNV;
    private javax.swing.JPanel pnMarginTitleChucVu;
    private javax.swing.JPanel pnMenu;
    private javax.swing.JPanel pnMenubar;
    private javax.swing.JPanel pnPupopMenu;
    private javax.swing.JPanel pnQR;
    private com.frame.pnReviewDarkMode pnReviewDarkMode;
    private com.frame.pnReviewLightMode pnReviewLightMode;
    private javax.swing.JPanel pnReviewMode;
    private javax.swing.JPanel pnSP;
    private javax.swing.JPanel pnSXHang;
    private javax.swing.JPanel pnSXLoai;
    private com.swing.PanelRound pnSetMode;
    private javax.swing.JPanel pnTitle;
    private javax.swing.JPanel pnbtnSXHang;
    private javax.swing.JPanel pnbtnSXLoai;
    private javax.swing.JPanel pnlChart;
    private javax.swing.JPanel pnlChartDT;
    private javax.swing.JRadioButton rdoDarkmode;
    private javax.swing.ButtonGroup rdoGMode;
    private javax.swing.JRadioButton rdoLightmode;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private com.hicode.switchbutton.SwitchButton sbtnTrangThaiNV;
    private javax.swing.JScrollPane scpChucVu;
    private javax.swing.JScrollPane scpNhanVien;
    private spinner.Spinner spnSL;
    private com.hicode.switchbutton.SwitchButton switchButton1;
    private javax.swing.JTable tblCart;
    private javax.swing.JTable tblChucVu;
    private javax.swing.JTable tblDoanhThu;
    private javax.swing.JTable tblHang;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTable tblLoaiHang;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTable tblSPBanChay;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtBanerPathImg1;
    private javax.swing.JTextField txtBanerPathImg2;
    private javax.swing.JTextField txtBanerPathImg3;
    private javax.swing.JTextField txtDiaChiNV;
    private javax.swing.JTextField txtEmailNV;
    private com.swing.TextField txtFindNameProductCart;
    private javax.swing.JTextField txtGiaBanSP;
    private javax.swing.JTextField txtGiaBanSP1;
    private javax.swing.JTextField txtGiaNhapSP;
    private javax.swing.JTextField txtGiaNhapSP3;
    private javax.swing.JTextField txtHoTenNV;
    private javax.swing.JTextField txtMAKH;
    private javax.swing.JTextField txtMaHang;
    private javax.swing.JTextField txtMaLoai;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtMatKhauNV;
    private javax.swing.JTextArea txtMoTaCV;
    private javax.swing.JTextArea txtMoTaSP;
    private javax.swing.JTextField txtNgayNhapSP;
    private javax.swing.JTextField txtNgaySinhNV;
    private javax.swing.JTextField txtPhanTramGG;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSDTNV;
    private javax.swing.JTextField txtSaveQrProduct;
    private javax.swing.JTextField txtSdtKH;
    private javax.swing.JTextField txtSoLuongSP;
    private javax.swing.JTextField txtTENKH;
    private javax.swing.JTextField txtTENNV;
    private javax.swing.JTextField txtTenDN;
    private javax.swing.JTextField txtTenHang;
    private javax.swing.JTextField txtTenLoai;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTichDiem;
    private javax.swing.JTextField txtTienNhan;
    private javax.swing.JTextField txtTimCV;
    private javax.swing.JTextField txtTimKiemHang;
    private javax.swing.JTextField txtTimKiemKH;
    private javax.swing.JTextField txtTimKiemLoai;
    private javax.swing.JTextField txtTimKiemNV;
    private javax.swing.JTextField txtTimKiemSP;
    private javax.swing.JTextField txtTimKiemhd;
    private javax.swing.JTextField txtTongtiensp;
    private javax.swing.JLabel txtTrangThaiNV;
    private javax.swing.JTextField txtdiaChi;
    private javax.swing.JTextField txthoTen;
    private javax.swing.JTextField txtmaCV;
    private javax.swing.JTextField txtmaKH;
    private javax.swing.JTextField txttenCV;
    // End of variables declaration//GEN-END:variables
}
