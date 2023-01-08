/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.extensisons;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author dangt
 */
public class SendQR {

    Date now = new Date();
    int intTime = 0;
    String nowTime;

    public void getTime() {
        Date now = new Date();
        this.now = now;
        SimpleDateFormat formaterHour = new SimpleDateFormat();
        formaterHour.applyPattern("hh");
        String time = formaterHour.format(now) + "H_";
        SimpleDateFormat formaterMinute = new SimpleDateFormat();
        formaterMinute.applyPattern("mm");
        time = time + formaterMinute.format(now) + "M_";
        SimpleDateFormat formaterSeconds = new SimpleDateFormat();
        formaterSeconds.applyPattern("ss");
        time = time + formaterSeconds.format(now) + "S";
        intTime = Integer.valueOf(formaterSeconds.format(now));
        nowTime = time;
    }

    public String sendcode(String email) throws IOException {
        Qr qr = new Qr();
        
        
        //laays gio phut giay
        getTime();
        if (intTime >= 58 && intTime <= 59) {
            try {
                Thread.sleep(3000);
                getTime();
            } catch (InterruptedException ex) {
                Logger.getLogger(SendQR.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //System.out.println(intTime);
        SimpleDateFormat formaterday = new SimpleDateFormat();
        formaterday.applyPattern("aadd_MM_yyyy_");
        String day = formaterday.format(now);
        String timeday = day + nowTime;
        System.out.println(timeday);
        String pathName = timeday;
        SimpleDateFormat formaterMinute = new SimpleDateFormat();
        formaterMinute.applyPattern("mm");
        String minute = formaterMinute.format(now);
        String qrCodeData = qr.path() + minute; //gias trij text cua QR
        System.out.println(qrCodeData.substring(qrCodeData.length()-2));
        System.out.println(qrCodeData);
        //Taoj max QR
        try {
            
            //tao ma qr
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
        } catch (Exception e) {
        }

        final String username = "davisy.dev@gmail.com";
        final String password = "ofukrmzrjlfloplu";//"ngemudntvdmhwwju"
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

            message.setSubject("Xác nhận thay đổi mật khẩu trên hệ thống DAVISY");

            textBody.setText("Xin chào! \nVui lòng quét mã dưới đây để thay đổi mật khẩu..."
                    + "\n\n\n-------------\nLiên hệ: FB.com/davisy.dev\nDavisy.dev");

            MimeBodyPart img = new MimeBodyPart();
            img.attachFile("src\\com\\images\\codechangepassword\\" + pathName + ".PNG");

            content.addBodyPart(textBody);
            content.addBodyPart(img);
            message.setContent(content);
            Transport.send(message);

        } catch (MessagingException e) {
            System.out.println(e);
        }
        
        //qr.deleteFile("src\\com\\images\\codechangepassword\\" + pathName + ".PNG");
        return qrCodeData;
    }

    public static void main(String[] args) throws IOException {
        SendQR qr = new SendQR();
        System.out.println(qr.sendcode("khanhdan0604@gmail.com"));
    }
}
