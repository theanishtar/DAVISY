package com.library.extensisons;

/**
 *
 * @author 
 */
public class Standardization {

    public boolean maSP(String s) {
        if (s.length() < 4 || s.length() > 6) {
            return false;
        }
        if (s.charAt(0) < 'A' || s.charAt(0) > 'Z' || s.charAt(1) < 'A' || s.charAt(1) > 'Z') {
            return false;
        }
        for (int i = 2; i < s.length(); i++) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9') {
                return false;
            }
        }
        //return true;
        return true;
    }

    public String hightlingtName(String s) {
        s = s.toLowerCase();
        String upr = "";
        if (s.length() > 1) {
            upr = s.substring(0, 1);
            upr = upr.toUpperCase();
            s = upr + s.substring(1);

            // in hoa ky tu dau moi tu
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ' ') {
                    // // t = (int) (s.charAt(i + 1)) - 32;
                    // // s.charAt(i + 1) = (char) t;
                    String t = s.substring(i + 1, i + 2);
                    t = t.toUpperCase();
                    s = s.substring(0, i + 1) + t + s.substring(i + 2);

                }
            }
        }

        return s;
    }

    public String hoTen(String s) {
        //String s;
        int i;
        // BAT DAU CHUAN HOA!

        // in thuong lai toan bo chuoi
        s = s.toLowerCase();
        // String spacebar = " ";
        // cong them khoang trang cuoi chuoi
        s = s + " ";

        // loc het cac ky tu khac chu cai
        for (i = 0; i < s.length(); i++) {
            if (i == s.length() - 1) {
                break;
            }
            if (s.charAt(i) == ' ') //   t ầ 5 ầ g
            {
                i++;                    //   0 1 2 3 4
            }
            if (s.charAt(i) < 'a' || s.charAt(i) > 'z') {
                if (s.charAt(i) < 160) {
                    s = s.substring(0, i) + s.substring(i + 1);
                    i--;
                } //else {

                //}
            }
        }
//        s = " "+s+"  ";
//        
//        for (i=1; i<s.length()-1; i++) {
//            if (s.charAt(i+1)==' ' && s.charAt(i-1)==' '){
//                if (s.charAt(i)>='a' && s.charAt(i)<='z' )
//                    //return false;
//                    s = s.substring(0,i-1)+s.substring(i+1);
//            }
//        }
        //s = s.substring(1,s.length()-1);
        // xoa khoang trang thua o vi tri dau va cuoi bang ham trim
        s = s.trim();

        // xoa 1 khoang trang khi gap 2 khoang trang
        for (i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ' && s.charAt(i + 1) == ' ') {
                s = s.substring(0, i + 1) + s.substring(i + 2);
                i--;
            }
        }
        s = s + "dang";
        // xoa phu am thua
        for (i = 0; i < s.length(); i++) {
            // neu gap van 'uu' thi giu lai
            if (s.charAt(i) == 7919 && s.charAt(i + 1) == 'u' && s.charAt(i + 2) != 'u') {
                i++;
            }
            //
            if (i < (s.length() - 1)) {
                if (s.charAt(i) == s.charAt(i + 1)) {
                    s = s.substring(0, i + 1) + s.substring(i + 2);
                    i--;
                }
            } else {
                break;
            }

        }

        // in hoa ky tu dau tien
        //s=hightlingtName(s);
        s = s.substring(0, s.length() - 4);
        return s;
    }

    public boolean tendn(String s) {
        //String s;
        int i;
        // String spacebar = " ";
        // cong them khoang trang cuoi chuoi
        if (s.length() < 4 || s.length() > 12) {
            return false;
        }
        // loc het cac ky tu khac chu cai
        for (i = 0; i < s.length(); i++) {

            if (s.charAt(i) == ' ') //   t ầ 5 ầ g
            {
                return false;                //   0 1 2 3 4
            }
            if (s.charAt(i) < 'a') {
                if (s.charAt(i) < 'A' || s.charAt(i) > 'Z') {
                    if (s.charAt(i) < '0' || s.charAt(i) > '9') {
                        return false;
                    }
                }
            } else {
                if (s.charAt(i) > 'z') {
                    return false;
                }
            }
        }
        return true;
        // xoa khoang trang thua o vi tri dau va cuoi bang ham trim

    }

    public boolean checkName(String name) {

        Standardization std = new Standardization();
        if (std.hoTen(name).equalsIgnoreCase(name)) {
            return true;
        }
        return false;
    }

    public static void Standardizationmain(String[] args) {
        Standardization std = new Standardization();
        //System.out.println(std.tendn("aAu889"));
        System.out.println(std.maSP("SP0001"));

    }
}
