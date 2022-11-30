package com.utils;

import java.awt.Component;
import javax.swing.JOptionPane;

public class MsgBox {

    public static void alert(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message,
                "Cửa hàng DAVISY", JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirm(Component parent, String message) {
        int result = JOptionPane.showConfirmDialog(parent, message, 
                "Cửa hàng DAVISY", JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE);
        return result == JOptionPane.YES_OPTION;
    }
    
    public static String promt(Component parent, String message){
        return JOptionPane.showInputDialog(parent, message, 
                "Cửa hàng DAVISY", JOptionPane.INFORMATION_MESSAGE);
    }

}
