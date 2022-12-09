/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GUI;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilterOutputStream;
import java.io.IOException;
/**
 *
 * @author ADMIN
 */
public class DemoF {
    public static void main(String args[]) throws Exception {
        /*
        FileReader fr = new FileReader("src\\com\\file\\saveQRCode.txt");
        int i;
        while ((i = fr.read()) != -1) {
            System.out.print((char) i);
        }
        fr.close();
        */
        FileOutputStream file = null;
        FilterOutputStream filter = null;
        try {
            file = new FileOutputStream(new File("src\\com\\file\\saveQRCode.txt"));
            filter = new FilterOutputStream(file);
            String s = "Welcome to java.";
            byte b[] = s.getBytes();
            filter.write(b);
            filter.flush();
            System.out.println("Success...");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            filter.close();
            file.close();
        }
    }
}
