package com.library.extensisons;

import com.file.FileExtensison;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFileChooser;

/**
 *
 * @author DAVISY
 */
public class Qr {

    /**
     * @param args the command line arguments
     */
    Random rand = new Random();
    String qrCode;

    public String path() {
        String voucher = "thean";
        String s = "";
        int min = 33;
        int max = 126;
        // 9D, 12H, 20T, 22-25: so phan tram giam gia
        while (s.length() < 25) {
            //int randPath = rand.nextInt(225);
            int randPath = (int) (Math.random() * (max - min + 1) + min);
            s = s + (char) randPath;
            if (s.length() == 9) {
                s = s + "D";
            }
            if (s.length() == 12) {
                s = s + "H";
            }
            if (s.length() == 17) {
                s = s + "T";
            }
            if (s.length() == 22) {
                s = s + voucher;
            }

            //System.out.println(s);
        }
        return s;
    }

    public void createQRProduct(String maSP) throws Exception {
        String qrCodeData = maSP;
        //String filePath = /*qr.getPath()*/ " " + hoTen + ".png";
        FileExtensison fe = new FileExtensison();
        String filePath = fe.getPath()+"\\" + maSP + ".png";
        //String filePath = fe.GetPath()+"src\\com\\images\\product\\" + maSP + ".png";
        try {
            //Trình tạo mã QR
            String charset = "UTF-8"; // or "ISO-8859-1"
            Map< EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap< EncodeHintType, ErrorCorrectionLevel>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(
                    new String(qrCodeData.getBytes(charset), charset),
                    BarcodeFormat.QR_CODE, 200, 200, hintMap);
            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
                    .lastIndexOf('.') + 1), new File(filePath));
            //System.out.println("QR Code image created successfully! and stored at location " + filePath);
        } catch (Exception e) {
        }
    }

    public boolean deleteQROProduct(String path) {
        //D:\\New folder
        File file_source = new File(path);
        //Nếu không tồn tại
        if (!file_source.exists()) {
            return false;
        } //Nếu file là thư mục
        else if (file_source.isDirectory()) {
            //Tạo list file
            File[] listFile = file_source.listFiles();
            for (File file : listFile) {
                //Nếu là tập tin thì xóa
                if (file.isFile()) {
                    file.delete();
                }
            }
        } else if (file_source.isFile()) {
            file_source.delete();
        }
        return true;
    }

    public void deleteFile(String path) {
        //D:\\New folder
        File file_source = new File(path);
        //Nếu không tồn tại
        if (!file_source.exists()) {
            return;
        } //Nếu file là thư mục
        if (file_source.isFile()) {
            file_source.delete();
        }
    }

    public String createQR(String hoTen) {
        Qr qr = new Qr();
        String code = "";
        try {
            //this.qrCode = code;
            //Lấy đường dẫn lưu mã QR
            JButton open = new JButton();
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new java.io.File(""));
            fileChooser.setDialogTitle("Chọn đường dẫn lưu mã đăng nhập!");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if (fileChooser.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
                //System.out.println("a");
            }
            //System.out.println(fileChooser.getSelectedFile().getAbsoluteFile());

            code = qr.path();
            String qrCodeData = code;
            //String filePath = /*qr.getPath()*/ " " + hoTen + ".png";
            String filePath = fileChooser.getSelectedFile().getAbsoluteFile() + "\\" + hoTen + ".png";
            //Trình tạo mã QR
            String charset = "UTF-8"; // or "ISO-8859-1"
            Map< EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap< EncodeHintType, ErrorCorrectionLevel>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(
                    new String(qrCodeData.getBytes(charset), charset),
                    BarcodeFormat.QR_CODE, 200, 200, hintMap);
            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
                    .lastIndexOf('.') + 1), new File(filePath));
            //System.out.println("QR Code image created successfully! and stored at location " + filePath);
            return code;
        } catch (Exception e) {
            //System.err.println(e);
            return "Chọn đường dẫn";
        }
        //return code;
    }

    //tao gui mail
    public String createMailQR() {
        Qr qr = new Qr();
        String code = "";
        try {
            //laays gio phut giay
            Date now = new Date();
            SimpleDateFormat formaterHour = new SimpleDateFormat();
            formaterHour.applyPattern("hh");
            String time = formaterHour.format(now) + "H_";
            SimpleDateFormat formaterMinute = new SimpleDateFormat();
            formaterMinute.applyPattern("mm");
            time = time + formaterMinute.format(now) + "M_";
            SimpleDateFormat formaterSeconds = new SimpleDateFormat();
            formaterSeconds.applyPattern("ss");
            time = time + formaterSeconds.format(now) + "S";
            SimpleDateFormat formaterday = new SimpleDateFormat();
            formaterday.applyPattern("aadd_MM_yyyy_");
            String day = formaterday.format(now);
            String timeday = day + time;
            System.out.println(timeday);
            String pathName = timeday;
            code = qr.path();
            String qrCodeData = code;
            String filePath = "src\\com\\images\\codechangepassword\\" + pathName + ".PNG";
            //String filePath = fileChooser.getSelectedFile().getAbsoluteFile()+ "\\" +"QR_Code.png";
            //Trình tạo mã QR
            String charset = "UTF-8"; // or "ISO-8859-1"
            Map< EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap< EncodeHintType, ErrorCorrectionLevel>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(
                    new String(qrCodeData.getBytes(charset), charset),
                    BarcodeFormat.QR_CODE, 200, 200, hintMap);
            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
                    .lastIndexOf('.') + 1), new File(filePath));
            //System.out.println("QR Code image created successfully! and stored at location " + filePath);
            return code;
        } catch (Exception e) {
            //System.err.println(e);
            return "Chọn đường dẫn";
        }
        //return code;
    }

    public static void main(String[] args) throws Exception {
        Qr qr = new Qr();
        qr.createQRProduct("SP01");
        //System.out.println(qr.createQR("QRCode"));
        //System.out.println(qr.createMailQR());
        // TODO code application logic here
    }

}
