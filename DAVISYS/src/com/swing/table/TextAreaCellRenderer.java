package com.swing.table;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;

public class TextAreaCellRenderer extends JTextArea implements TableCellRenderer  {

    private final List<List<Integer>> rowAndCellHeights = new ArrayList<>();
    private final HoverIndex hoverRow;

    public TextAreaCellRenderer(HoverIndex hoverRow) {
        this.hoverRow = hoverRow;
        setWrapStyleWord(true);
        setLineWrap(true);
        setOpaque(true);
        setBorder(new EmptyBorder(8, 10, 8, 10));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText(Objects.toString(value, ""));
        adjustRowHeight(table, row, column);
        if (row == hoverRow.getIndex()) {
            setBackground(new Color(230, 230, 230));
        } else {
            if (row % 2 == 0) {
                setBackground(Color.WHITE);
            } else {
                setBackground(new Color(242, 242, 242));
            }
        }
        return this;
    }

    private void adjustRowHeight(JTable table, int row, int column) {
        setBounds(table.getCellRect(row, column, false));
        int preferredHeight = getPreferredSize().height;
        while (rowAndCellHeights.size() <= row) {
            rowAndCellHeights.add(new ArrayList<>(column));
        }
        List<Integer> list = rowAndCellHeights.get(row);
        while (list.size() <= column) {
            list.add(0);
        }
        list.set(column, preferredHeight);
        int max = list.stream().max((x, y) -> Integer.compare(x, y)).get();
        if (table.getRowHeight(row) != max) {
            table.setRowHeight(row, max);
        }
    }
}
