/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class FileExtensison {

    
    public void setMode(String mode) throws Exception {
        FileOutputStream file = null;
        FilterOutputStream filter = null;
        try {
            file = new FileOutputStream(new File("src\\com\\file\\mode.txt"));
            filter = new FilterOutputStream(file);
            byte b[] = mode.getBytes();
            filter.write(b);
            filter.flush();
            //System.out.println("Success...");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            filter.close();
            file.close();
        }
    }
    
    public String getMode() throws Exception {
        FileReader fr = new FileReader("src\\com\\file\\mode.txt");
        int i;
        String path = "";
        while ((i = fr.read()) != -1) {
            //System.out.print((char) i);
            path += (char) i;
        }
        fr.close();
        return path;
    }
    
    public String getPath() throws Exception {
        FileReader fr = new FileReader("src\\com\\file\\saveQRCode.txt");
        int i;
        String path = "";
        while ((i = fr.read()) != -1) {
            //System.out.print((char) i);
            path += (char) i;
        }
        fr.close();
        return path;
    }

    public void setPath(String newPath) throws Exception {
        FileOutputStream file = null;
        FilterOutputStream filter = null;
        try {
            file = new FileOutputStream(new File("src\\com\\file\\saveQRCode.txt"));
            filter = new FilterOutputStream(file);
            byte b[] = newPath.getBytes();
            filter.write(b);
            filter.flush();
            //System.out.println("Success...");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            filter.close();
            file.close();
        }
    }
    
    public void setImgBaner1(String newPath)throws Exception{
        FileOutputStream file = null;
        FilterOutputStream filter = null;
        try {
            file = new FileOutputStream(new File("src\\com\\file\\bannerPath1.txt"));
            filter = new FilterOutputStream(file);
            byte b[] = newPath.getBytes();
            filter.write(b);
            filter.flush();
            //System.out.println("Success...");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            filter.close();
            file.close();
        }
    }
    
    public String getImgBaner1()throws Exception{
        FileReader fr = new FileReader("src\\com\\file\\bannerPath1.txt");
        int i;
        String path = "";
        while ((i = fr.read()) != -1) {
            //System.out.print((char) i);
            path += (char) i;
        }
        fr.close();
        return path;
    }

    public void setImgBaner2(String newPath)throws Exception{
        FileOutputStream file = null;
        FilterOutputStream filter = null;
        try {
            file = new FileOutputStream(new File("src\\com\\file\\bannerPath2.txt"));
            filter = new FilterOutputStream(file);
            byte b[] = newPath.getBytes();
            filter.write(b);
            filter.flush();
            //System.out.println("Success...");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            filter.close();
            file.close();
        }
    }
    
    public String getImgBaner2()throws Exception{
        FileReader fr = new FileReader("src\\com\\file\\bannerPath2.txt");
        int i;
        String path = "";
        while ((i = fr.read()) != -1) {
            //System.out.print((char) i);
            path += (char) i;
        }
        fr.close();
        return path;
    }
    
    public void setImgBaner3(String newPath)throws Exception{
        FileOutputStream file = null;
        FilterOutputStream filter = null;
        try {
            file = new FileOutputStream(new File("src\\com\\file\\bannerPath3.txt"));
            filter = new FilterOutputStream(file);
            byte b[] = newPath.getBytes();
            filter.write(b);
            filter.flush();
            //System.out.println("Success...");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            filter.close();
            file.close();
        }
    }
    
    public String getImgBaner3()throws Exception{
        FileReader fr = new FileReader("src\\com\\file\\bannerPath3.txt");
        int i;
        String path = "";
        while ((i = fr.read()) != -1) {
            //System.out.print((char) i);
            path += (char) i;
        }
        fr.close();
        return path;
    }
    
    public static void main(String[] args) {
        FileExtensison f = new FileExtensison();
        try {
            System.out.println(f.getMode());
        } catch (Exception ex) {
            Logger.getLogger(FileExtensison.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
