[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://tranhuudang.cf)
<br/>

<br/>

# DAVISY-PRO1014

## Tên dự án: Xây dựng ứng dụng quản lý bán hàng cho cửa hàng bán phụ kiện thiết bị di động DAVISY

<br/>

<br/>

## Thành viên
| Tên thành viên                                             | Ảnh minh họa                                                                                                                                     | Chức vụ                  | Sản phẩm nổi bật                                                                        |
| -----------------------------------------------------------| -------------------------------------------------------------------------------------------------------------------------------------------------| ------------------------ | --------------------------------------------------------------------------------------- |
| [Trần Hữu Đang](https://github.com/Theanishtar)            | <img src="https://raw.githubusercontent.com/echhoclaptrinh/Image/main/gif-flog/pepefrg-55.gif" title="" alt="06-00-18-00-html5.gif" width="100"> | Project Manager, developer          | [Mô phỏng thuật toán sắp xếp](https://github.com/TheanIshtar/simulation-sort-algorithm) |
| [Nguyễn Khánh Đan](https://github.com/nguyenkhanhdan)      | <img src="https://raw.githubusercontent.com/echhoclaptrinh/Image/main/gif-flog/zQRCHEK.gif" width="100">                                 | BA Writer, Tester, developer        | [Hệ thống bán hàng FreshFood](https://github.com/NguyenKhanhDan/FreshFood)              |
| [Đoàn Hiệp Sỹ](https://github.com/DoanSy166)               | <img src="https://raw.githubusercontent.com/echhoclaptrinh/Image/main/gif-flog/f5f.gif" width="100">                                             | DevOps, developer                   | [Phần mềm quản lí xe máy](https://github.com/DoanSy16/biker-shop-manager)               |
| [Phùng Quốc Vinh](https://github.com/Dinhisme)             | <img src="https://raw.githubusercontent.com/echhoclaptrinh/Image/main/gif-flog/pepefrg-72.gif" width="100">                                      | Data Scentist, developer            | [Dodge Game](https://github.com/Dinhisme/DodgeGame)                                                                     |
| [Lê Bích Vi](https://github.com/TheBank0911)               | <img src="https://raw.githubusercontent.com/echhoclaptrinh/Image/main/gif-flog/pepefrg-34.gif" width="100">                                      | Business Analyst, developer         | [Quản lí kí túc xá](https://github.com/TheBank0911/Quanlikitucxa)                                                                      |

Ngoài ra các thành viên còn đảm nhận một số nhiệm vụ khác được trình bày cụ thể trong phần báo cáo!<br/>

<br/>

# Documents
- [Cài đặt và sử dụng](#install)
- [Tài khoản và phân quyền](#account)
- [Các tính năng nâng cao](#ui-components)
  - [Tùy chỉnh giao diện (Darkmode - Lightmode)](#)
  - [Quên mật khẩu](#header-bar)
  - [Thêm sản phẩm số lượng lớn thông qua file excel](#header-bar)
  - [Tạo mã QR cho từng sản phẩm](#)
  - [Quét mã sản phẩm](#radio-box)
  - [Gửi thông tin sản phẩm qua email khách hàng](#button)
  - [In thông tin sản phẩm thành file word](#textbox)
  - [In hóa đơn thành file pdf](#alert)
  - [Tìm kiếm nâng cao (được tùy chỉnh các tiêu chí tìm kiếm)](#alert)
  - [Đọc số tiền trong hóa đơn](#alert)
  - [Thống kê theo dạng biểu đồ](#alert)  
  - [Xuất thống kê thành file excel](#alert)
- [Các công nghệ sử dụng](#)
  - [Lớp drawer dùng để khởi tạo menu](#drawer)
  - [Thư viện bên ngoài để mở webcam](#webcam)
  - [Lớp tiện ích để chuyển đổi giữa mã nhị phân và ASCII](#bitmap)
  - [Thư viện để xuất file excel, word, pdf,...](#file)
  - [Thư viện Chart để vẽ biểu đồ 2D](#chart)
  - [Thư viện làm việc với email](#email)
- [Các công cụ sử dụng](#)
  - [Netbeans IDE](#)
  - [MSS Management Studio 18](#)
  - [Visual Studio Code](#)
  - [Github.com](#)
  - [Trello.com](#)
  - [Visio 2016](#)
  - [Balsamiq Wireframes](#)
  - [Microsoft Office 2016](#)
  - [Launch 4 J](#)
  
- [Các tư liệu tham khảo](#draw-func)
  - [Github - DJ Raven](#draw)
  - [Github - KeepToo](#gotoxy)
  - [YouTube - Ếch học lập trình](#square)
  - [YouTube - Abner Rodrigues](#set-color)
  - [YouTube - Bro Code](#clear)
  - [YouTube - Thân Triệu](#codepage)
<br>

<br>

# Mô tả chi tiết dự án

## Mô tả

Phần mềm là dự án cuối môn của nhóm DAVISY trong môn dự án 1 - PRO1041 tại [FPT Polytechnic](https://caodang.fpt.edu.vn/) được hướng dẫn bởi thầy [Trần Văn Nhuộm](https://thaynhuom.edu.vn/).

<!-- <br/>
<center><img src=""></center>
<br/> -->

## Account
| Tên tài khoản         | Tên đăng nhập        | Chức vụ            | Email                           | Trạng Thái                                       |
| ----------------------| ---------------------| ------------------ | ------------------------------- | -------------------------------------------------|
| Trần Văn Nhuộm        | admin                | Quản trị           | nhuomtv@fpt.edu.vn              | <span style="color:green" >Đang hoạt động</span> |
| Trần Hữu Đang         | dangth               | Quản lí            | dangthpc04349@fpt.edu.vn        | <span style="color:green" >Đang hoạt động</span> |
| Nguyễn Khánh Đan      | dannk                | Nhân viên          | dannkpc04351@fpt.edu            | <span style="color:green" >Đang hoạt động</span> |
| Đoàn Hiệp Sỹ          | sydh                 | Nhân viên          | sydhpc04388@fpt.edu.vn          | <span style="color:green" >Đang hoạt động</span> |
| Phùng Quốc Vinh       | vinhpq               | Nhân viên          | vinhpqpc04338@fpt.edu.vn        | <span style="color:red" >Ngưng hoạt động</span>  |
| Lê Bích Vi            | vilb                 | Nhân viên          | vilbpc04354@fpt.edu.vn          | <span style="color:green" >Đang hoạt động</span> |
<hr/>

<br/>

>Quản trị sẽ có toàn quyền với phần mềm

>Quản lí chỉ được phép thêm các nhân viên và các thống kê về doanh thu (cùng toàn bộ các quyền của nhân viên)

>Nhân viên sẽ bị hạn chế về quyền thêm tài khoản (chỉ được thêm sửa xóa khách hàng), chỉ được xem thống kê sản phẩm bán chạy
<br/>

<br/>

## Install

1. Tải và giải nén file đã được đính kèm
2. Cài đặt vào ổ đĩa D trong máy tính
3. Chạy chương trình đã cài đặt và đăng nhập với tài khoản bên trên

## Run Project by Netbeans IDE
1. Mở project "DAVISYS"
2. Chạy trang login hoặc main trong package com.gui
3. Đăng nhập các tài khoản bên trên

<br/>

<br/>

# Các công nghệ sử dụng trong dự án



## drawer
```java
void initMenu() {
        drawer = Drawer.newDrawer(this)
                .addChild(new DrawerItem("Cửa sổ chính").build())
                .addChild(new DrawerItem("Tài khoản").build())
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
                              break;
                            case 1:
                              //gọi trang tài khoản
                              break;
                        }
                    }
                }).build();
}
```
<br/>

<br/>

## webcam


```java
public void initWebcam() {
    Dimension size = WebcamResolution.QVGA.getSize();
    webcam = Webcam.getWebcams().get(0); //0 is default webcam
    webcam.setViewSize(size);
    panel = new WebcamPanel(webcam);
    panel.setPreferredSize(size);
    pnQR.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 300));
    executor.execute(this);
}
```
Ghi đè phương thức chạy của luồng mở webcam
```java
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
                //xử lý mã tại đây
            }
        } while (true);
    }
```
<br>

<br>

## bitmap

```java
//Trình tạo mã QR
try {
    String charset = "UTF-8"; // or "ISO-8859-1"
    Map< EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap< EncodeHintType, ErrorCorrectionLevel>();
    hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
    BitMatrix matrix = new MultiFormatWriter().encode(
            new String(qrCodeData.getBytes(charset), charset),
            BarcodeFormat.QR_CODE, 200, 200, hintMap);
    MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
            .lastIndexOf('.') + 1), new File(filePath));
     } catch (Exception e) {
}
```
<br>

<br>

## file
```java
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
    } catch (Exception ex) {
        System.out.println(ex);
    }
} catch (Exception ex) {
    System.out.println(ex);
}

```
<br>
<br>

## chart
```java
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
```
<br>
<br>

## Email

```java
Properties prop = new Properties();
    prop.put("mail.smtp.host", "smtp.gmail.com");
    prop.put("mail.smtp.port", "587");
    prop.put("mail.smtp.auth", "true");
    prop.put("mail.smtp.starttls.enable", "true"); //TLS

    Session session = Session.getInstance(prop,
            new Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    });
    try {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(email));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(email)
        );

        Multipart content = new MimeMultipart();
        MimeBodyPart textBody = new MimeBodyPart();

        message.setSubject("DAVISY");

        textBody.setText("Hello");

        content.addBodyPart(textBody);
        message.setContent(content);
        Transport.send(message);

    } catch (MessagingException e) {
        System.out.println(e);
    }

```

[Designed by Theanishtar in CanTho city.](tranhuudang.cf)
