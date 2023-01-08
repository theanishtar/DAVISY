/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swing.table;

import java.awt.Color;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author ADMIN
 */
public class TableRowDark extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component com = super.getTableCellRendererComponent(jtable, value, isSelected, hasFocus, row, column);
        if (row % 2 == 0) {
            com.setBackground(new Color(55, 55, 55));
        } else {
            com.setBackground(new Color(65, 65, 65));
        }
        if (isSelected) {
            com.setBackground(new Color(102, 204, 255));
            //com.setForeground(Color.black);
        }
        return com;
    }
}
