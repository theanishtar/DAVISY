/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.library.extensisons;
import com.utils.JdbcHelper;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author LE BICH VY
 */
public class Folder {
    JdbcHelper jdbc = new JdbcHelper();
    
    
    
    public boolean loopsFolder(String path){
        //D:\\New folder
        File file_source = new File("src//com//images");
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
                if (file.isFile() && nameF.substring(nameF.length()-4).equalsIgnoreCase(".png")) {
                    
                    System.out.println(file.getName());
                }
            }
        } 
        else if (file_source.isFile()) {
            //file_source.delete();
        }
        return true;
    }
    public List<String> getListHinh(){
        List<String> list = new ArrayList<>();
        list.clear();
        try {
            Class.forName(jdbc.driver);
            Connection con = DriverManager.getConnection(jdbc.dburl, jdbc.user, jdbc.pass);
            String sql = "select * from SANPHAM";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(rs.getString(8));
            }
            rs.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public static void main(String[] args) {
        Folder f = new Folder();
        System.out.println("a.png".substring("a.png".length()-4));
    }
}
