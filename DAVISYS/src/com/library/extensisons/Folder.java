/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.library.extensisons;

import com.utils.JdbcHelper;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import static org.apache.poi.hssf.usermodel.HeaderFooter.file;

/**
 *
 * @author LE BICH VY
 */
public class Folder {

    JdbcHelper jdbc = new JdbcHelper();

    public List<String> list = new ArrayList<>();

    void cloneIMG(File file, String imageSrc, String folderTarget) {
        BufferedImage cloneImage, image;
        try {
            image = ImageIO.read(file);
            int wIMG = image.getWidth();
            int HIMG = image.getHeight();

            cloneImage = new BufferedImage(wIMG, HIMG, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = cloneImage.createGraphics();

            g.drawImage(image, 0, 0, null);
            g.dispose();

            try {
                //ImageIO.write(cloneImage, "PNG", new File("src\\com\\images\\" + imageSrc));
                ImageIO.write(cloneImage, "PNG", new File(folderTarget + "\\" + imageSrc));
            } catch (Exception e) {
                System.out.println("loi");
            }
        } catch (Exception e) {
            System.out.println("loi");
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

    public boolean copyAllFileInFolder(String folderSrc, String folderTarget) {
        getListHinh();
        //D:\\New folder
        //File file_source = new File("src//com//images");  E:\src
        File file_source = new File(folderSrc); // đường dẫn folder gốc folderSrc = "E:\\src" 
        Folder f = new Folder();

        //Nếu không tồn tại
        if (!file_source.exists()) {
            return false;
        } //Nếu file là thư mục
        else if (file_source.isDirectory()) {
            //Tạo list file
            File[] listFile = file_source.listFiles();
            for (File file : listFile) {
                //Nếu là hình ảnh thì 
                String nameF = file.getName();
                if (file.isFile()) {
                    if (nameF.substring(nameF.length() - 4).equalsIgnoreCase(".png")
                            || nameF.substring(nameF.length() - 4).equalsIgnoreCase(".jpg")
                            || nameF.substring(nameF.length() - 4).equalsIgnoreCase(".jpeg")) {

                        //System.out.println(nameF.substring(0,nameF.length() - 4));
                        for (String imgDTB : list) {
                            //imgDTB
                            if (imgDTB.equalsIgnoreCase(nameF.substring(0, nameF.length() - 4))) {

                                f.cloneIMG(file, nameF.substring(0, nameF.length() - 4) + ".png", folderTarget); //folderTarget =  "E:\\taget"
                                break;
                            }
                        }
                    }
                }

            }
        } else if (file_source.isFile()) {
            //file_source.delete();
            return false;
        }
        return true;
    }

    public List<String> getListHinh() {
        //List<String> list = new ArrayList<>();
        list.clear();
        try {
            Class.forName(jdbc.driver);
            Connection con = DriverManager.getConnection(jdbc.dburl, jdbc.user, jdbc.pass);
            String sql = "select * from SANPHAM";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(rs.getString(9));
                //System.out.println("DTB: "+rs.getString(9));
            }
            rs.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public static void main(String[] args) throws IOException {
        Folder f = new Folder();
        //f.loopsFolder("a");

        //f.getListHinh();
    }
}
