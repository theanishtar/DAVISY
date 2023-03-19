package com.swing.table;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class TableCustomCellRender extends DefaultTableCellRenderer {

    private final HoverIndex hoverRow;

    public TableCustomCellRender(HoverIndex hoverRow) {
        this.hoverRow = hoverRow;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        setBorder(new EmptyBorder(10, 10, 10, 10));
        if (row == hoverRow.getIndex()) {
            com.setBackground(new Color(230, 230, 230));
        } else {
            if (row % 2 == 0) {
                com.setBackground(Color.WHITE);
            } else {
                com.setBackground(new Color(242, 242, 242));
            }
        }
        return com;
    }
}
