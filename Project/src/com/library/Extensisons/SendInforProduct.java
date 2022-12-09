/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.extensisons;

import com.dao.SanPhamDAO;
import com.entity.SanPhamEntity;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class SendInforProduct {

    SanPhamDAO SanPham = new SanPhamDAO();
    List<SanPhamEntity> list = new ArrayList<>();

    public void listSPT() {
        list = SanPham.selectAll();
        List<SanPhamEntity> listSanPhamTemp = new ArrayList<>();
        listSanPhamTemp.addAll(list);
        list.clear();
        list.addAll(listSanPhamTemp);
    }

    public boolean sendInforPCEmail(String email, int index) throws IOException {
        listSPT();

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

            message.setSubject("Thông tin chi tiết sản phẩm: " + list.get(index).getTenSP());

            textBody.setText("\nKính chào quý khách với email: " + email + "\n\n"
                    + "Sau đây là thông tin sản phẩm của cửa hàng chúng em ạ!\n\n"
                    + "Mã sản phẩm: " + list.get(index).getMaSP() + "\n\n"
                    + "Tên sản phẩm: " + list.get(index).getTenSP() + "\n\n"
                    + "Loại sản phẩm: " + list.get(index).getTenL() + "\n\n"
                    + "Hãng sản xuất: " + list.get(index).getTenH() + "\n\n"
                    + "Mô tả: " + list.get(index).getMoTa() + "\n\n"
                    + "Giá sản phẩm: " + String.valueOf(list.get(index).getGiaBan()) + "\n"
                    + "\n-----------------------------------------------\n"
                    + "Cửa hàng phụ kiện: DAVISY\n\n\n Công ty Phần mềm DAVISYS.DEV\n "
                    + "Chi tiết liên hệ: \n"
                    + "         github.com/TheanIshtar\n"
                    + "         davisy.dev@gmail.com");

            MimeBodyPart img = new MimeBodyPart();

            content.addBodyPart(textBody);
            if (!list.get(index).getHinh().equals("")) {
                System.out.println(list.get(index).getHinh());
                img.attachFile("src\\com\\images\\" + list.get(index).getHinh() + ".PNG");
                content.addBodyPart(img);
            }
            //System.out.println(listInforPC.get(index).getHinhAnh());

            message.setContent(content);
            Transport.send(message);
            return true;
            //JOptionPane.showMessageDialog(this, "Đã gửi thông tin sản phẩm cho email: " + email);
        } catch (MessagingException e) {
            System.out.println("Loi");
            //JOptionPane.showMessageDialog(this, "Không thể gửi thông tin sản phẩm cho email: " + email);
            return false;
        }

    }

    public boolean saveFile(int index) throws FileNotFoundException, IOException {
        
        listSPT();
        String[] data = {
            "\t Thông tin chi tiết sản phẩm: " + list.get(index).getTenSP() + "\n",
            "Mã sản phẩm: " + list.get(index).getMaSP() + "\n",
            "Tên sản phẩm: " + list.get(index).getTenSP() + "\n",
            "Loại sản phẩm: " + list.get(index).getTenL() + "\n",
            "Hãng sản xuất: " + list.get(index).getTenH() + "\n",
            "Mô tả: " + list.get(index).getMoTa() + "\n",
            "Giá sản phẩm: " + String.valueOf(list.get(index).getGiaBan()) + "\n",
            "\t\t\t\tCửa hàng phụ kiện: DAVISY",
            "\t\t\t\t Công ty Phần mềm DAVISYS.DEV",
            "\t\t\t\t Chi tiết liên hệ: ",
            "\t\t\t\t         github.com/TheanIshtar"
        };

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn đường dẫn lưu văn bản!");

        int userSelection = fileChooser.showSaveDialog(fileChooser);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            try {
                File fileToSave = fileChooser.getSelectedFile();
                //WriteToFile(fileToSave.getAbsolutePath() + ".docx");

                /*  
                abcde.docx
                x : length-1
                . : length-5
                 */
                String path = fileToSave.getAbsoluteFile().toString();
                String file = fileToSave.getAbsolutePath();
                if (!path.contains(".doc")) {
                    file = fileToSave.getAbsolutePath() + ".doc";
                }

                OutputStream outputStream = new FileOutputStream(file);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);

                for (String item : data) {
                    outputStreamWriter.write(item);
                    // Dùng để xuống hàng
                    outputStreamWriter.write("\n");
                }
                // Đây là phương thức quan trọng!
                // Nó sẽ bắt chương trình chờ ghi dữ liệu xong thì mới kết thúc chương trình.
                outputStreamWriter.flush();
                return true;
            } catch (Exception e) {
                return false;
            }
        } else{
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        SendInforProduct s = new SendInforProduct();
        s.saveFile(0);
    }
}
