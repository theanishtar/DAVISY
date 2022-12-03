/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.library.extensisons;

import java.util.Scanner;

/**
 *
 * @author DAVISY
 */
public class ReadMoney {

    String num = "400";

    public String doc_so(char num) {
        if (num == '1') {
            return "Mot ";
        }
        if (num == '2') {
            return "Hai ";
        }
        if (num == '3') {
            return "Ba ";
        }
        if (num == '4') {
            return "Bon ";
        }
        if (num == '5') {
            return "Nam ";
        }
        if (num == '6') {
            return "Sau ";
        }
        if (num == '7') {
            return "Bay ";
        }
        if (num == '8') {
            return "Tam ";
        }
        if (num == '9') {
            return "Chin ";
        }
        return "Khong ";
    }

    public String doc_hang_chuc(String s) { //06  | 24  |009 090090
        if (s.length() > 2) {
            s = s.substring(0, 2);
        }
        String number_temp = "";
        if (s.charAt(0) == '1') {
            //System.out.print("Muoi ");
            number_temp = number_temp + "Muoi ";
            if (s.charAt(1) != '0') {
                if (s.charAt(1) == '5') {
                    number_temp = number_temp + "Lam ";
                } else if (s.charAt(1) == '1') {
                    number_temp = number_temp + "Mot ";
                } else {
                    number_temp = number_temp + doc_so(s.charAt(1));
                }
                // doc_so(s.charAt(1));
            }
        } else {
            if (s.charAt(0) == '0') {
                //System.out.print("Linh ");
                number_temp = doc_so(s.charAt(1));
            } else {
                number_temp = doc_so(s.charAt(0));
                //System.out.print("Muoi ");
                number_temp = number_temp + "Muoi ";
                if (s.charAt(1) != '0') {
                    if (s.charAt(1) == '5') {
                        number_temp = number_temp + "Lam ";
                    } //System.out.print("Lam ");
                    else if (s.charAt(1) == '1') {
                        number_temp = number_temp + "Mot ";
                    } else {
                        number_temp = number_temp + doc_so(s.charAt(1));
                    }
                }
            }
        }
        return number_temp;
    }

    public String doc_hang_tram(String s) { //600  || 910  |009 090090  |910
        if (s.length() > 3) {
            s = s.substring(0, 3);
        }
        String number_temp = "";
        String number = "";
        if (s.equals("000")) {
            return "";
        }
        if (s.substring(1, 3).equals("00")) {
            number_temp = doc_so(s.charAt(0)) + "Tram ";
            return number_temp;
        }

        number_temp = "";
        number = "";
        number_temp = doc_so(s.charAt(0)) + "Tram ";
        //System.out.print("Tram ");
        if (s.charAt(1) == '0') {
            number_temp = number_temp + "Linh " + doc_so(s.charAt(s.length() - 1));
            //System.out.print("Linh ");
            //doc_so(s.charAt(s.length() - 1));
        } else {
            if (s.substring(1) == "10") {
                number_temp = number_temp + "Linh Mưoi";
            } else {
                number = number + s.substring(1);
                number_temp = number_temp + doc_hang_chuc(number);
                //doc_hang_chuc(number_temp);
            }

        }
        return number_temp;
    }

    //
    public String read_money(String s) {
        try {
            int a = Integer.valueOf(s);
            double b = Double.valueOf(s);
            if(a!=b){
                return "";
            }
        } catch (Exception e) {
            return "";
        }
        //String s = document.querySelector('#number').value;
        String number_temp = "";
        String number = "";
        if (s.length() == 1) {
            return doc_so(s.charAt(0));
            //System.out.print("nghin dong!");
        }
        if (s.length() == 2) {
            return doc_hang_chuc(s);
            //System.out.print("nghin dong!");
        }
        if (s.length() == 3) {
            //System.out.print("nghin dong!");
            return doc_hang_tram(s);

        }
        if (s.length() == 4) {  //400
            if (s.substring(1).equals("000")) {
                number_temp = doc_so(s.charAt(0)) + "Nghin ";
                return number_temp;
            }
            return doc_so(s.charAt(0)) + "Nghin " + doc_hang_tram(s);
        }
        if (s.length() == 5) {      //98 765 
            if (s.substring(1).equals("0000")) {
                number_temp = doc_hang_chuc(s) + "Nghin ";
                return number_temp;
            }
            number_temp = "";
            number = "";
            number_temp = doc_hang_chuc(s) + "Nghin ";
            //System.out.print("Trieu ");
            s = s.substring(2);
            // doc_so(s.charAt(0));
            number_temp = number_temp + doc_hang_tram(s);
            return number_temp;
            //System.out.print("VND!");
        }
        if (s.length() == 6) { //600 910  | 678 567  | 691 000
            if (s.substring(1).equals("00000")) {
                return doc_hang_tram(s) + "Nghin ";

            }
            return doc_hang_tram(s) + "Nghin " + doc_hang_tram(s.substring(3));
        }
        if (s.length() == 7) {  //5 070 090
            if (s.substring(1).equals("000000")) {
                return doc_hang_tram(s) + "Trieu ";

            }
            return doc_so(s.charAt(0)) + "Trieu " + doc_hang_tram(s.substring(1))
                    + "Nghin " + doc_hang_tram(s.substring(4)); //1.  |000.001
        }
        if (s.length() == 8) {   //80 060 008
            if (s.substring(1).equals("0000000")) {
                return doc_hang_chuc(s) + "Trieu ";
            }
            return doc_hang_chuc(s) + "Trieu " + doc_hang_tram(s.substring(2)) + "Nghin " + doc_hang_tram(s.substring(5));
        }
        if (s.length() == 9) {   //900 340 056
            if (s.substring(1).equals("00000000")) {
                return doc_hang_tram(s) + "Trieu ";
            }
            return doc_hang_tram(s) + "Trieu " + doc_hang_tram(s.substring(3)) + "Nghin " + doc_hang_tram(s.substring(6));
        }
        if (s.length() == 10) {  //9 009 090 090
            if (s.substring(1).equals("000000000")) {
                return doc_hang_tram(s) + "Ti ";
            }
            return doc_so(s.charAt(0)) + "Ti " + doc_hang_tram(s.substring(1)) + "Trieu "
                    + doc_hang_tram(s.substring(4) + "Nghin " + doc_hang_tram(s.substring(7))); //1.  |2  |89.585.760
        }
        if (s.length() == 11) {  //90 060 908 450
            if (s.substring(1).equals("0000000000")) {
                return doc_hang_chuc(s) + "Ti ";
            }
            return doc_hang_chuc(s) + "Ti " + doc_hang_tram(s.substring(2)) + "Trieu "
                    + doc_hang_tram(s.substring(6)) + "Nghin " + doc_hang_tram(s.substring(8));
        }
        if (s.length() == 12) {     //900 567 034 120
            if (s.substring(1).equals("00000000000")) {
                return doc_hang_tram(s) + "Ti ";
            }
            return doc_hang_tram(s) + "Ti " + doc_hang_tram(s.substring(3)) + "Trieu "
                    + doc_hang_tram(s.substring(6)) + "Nghin " + doc_hang_tram(s.substring(9));
        } else {
            number_temp = "";
            //return "Vượt quá giới hạn đọc số!"
        }
        //document.querySelector('#doc_so').innerHTML = number_temp;
        return number_temp + " VND";
    }

    public static void main(String[] args) {
        ReadMoney rm = new ReadMoney();
        //rm.read_money("12000");
        //System.out.println(cf.xuLy(day, cf.now()));
        System.out.println(rm.read_money("c"));

    }
}
