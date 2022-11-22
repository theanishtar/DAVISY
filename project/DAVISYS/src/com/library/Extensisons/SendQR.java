/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.Extensisons;

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
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author dangt
 */
public class SendQR {
    public String sendcode(String email) throws IOException {
        Qr qr = new Qr();
        String code = qr.createMailQR();
        
        final String username = "davisy.dev@gmail.com";
        final String password = "iqzhkriacknnlkna";//"ngemudntvdmhwwju"
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
            img.attachFile("src\\com\\images\\" +"QR_Code.png");
            
            content.addBodyPart(textBody);
            content.addBodyPart(img);
            message.setContent(content);
            Transport.send(message);
        } catch (MessagingException e) {

        }
        return code;
    }
    
    public static void main(String[] args) throws IOException {
        SendQR qr = new SendQR();
        System.out.println(qr.sendcode("dangtt135@gmail.com"));
    }
}
