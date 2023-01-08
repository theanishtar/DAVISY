package com.swing.table;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellRenderer;
import com.ui.drawer.scroll.ScrollBarCustomUI;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import scrollbar.ScrollBarCustom;

public class TableCustom {

    public static void dark(JScrollPane scroll, TableType type) {

    }

    public static void apply(JScrollPane scroll, TableType type) {
        JTable table = (JTable) scroll.getViewport().getComponent(0);
        table.getTableHeader().setReorderingAllowed(false);

        table.getTableHeader().setDefaultRenderer(new TableHeaderCustomCellRender(table));
        table.setRowHeight(10);

        table.getTableHeader().setPreferredSize(new Dimension(0, 30));
        HoverIndex hoverRow = new HoverIndex();
        TableCellRenderer cellRender;
        if (type == TableType.DEFAULT) {
            cellRender = new TableCustomCellRender(hoverRow);
        } else {
            cellRender = new TextAreaCellRenderer(hoverRow);
            if (type == TableType.DARK) {
                /*
                  table.getTableHeader().setPreferredSize(new Dimension(0, 30));
                scroll.setVerticalScrollBar(new ScrollBarCustom());
                JPanel panel = new JPanel();
                panel.setBackground(new Color(30, 30, 30));
                scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, panel);
                scroll.getViewport().setBackground(new Color(30, 30, 30));
                //scroll.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60), 2));
                table.setRowHeight(30);
                table.getTableHeader().setForeground(Color.white);
                table.getTableHeader().setFont(new Font("Segoe UI", Font.ITALIC, 5));
                table.getTableHeader().setBackground(Color.white);
                table.getTableHeader().setBackground(new Color(30, 30, 30));
                table.getTableHeader().setForeground(new Color(200, 200, 200));
                 */
                table.setDefaultRenderer(Object.class, cellRender);
                table.setShowVerticalLines(true);
                table.setGridColor(new Color(220, 220, 220));
                table.setForeground(new Color(51, 51, 51));
                table.setSelectionForeground(new Color(51, 51, 51));
                scroll.setBorder(new LineBorder(new Color(220, 220, 220)));
                JPanel panel = new JPanel() {
                    @Override
                    public void paint(Graphics grphcs) {
                        super.paint(grphcs);
                        grphcs.setColor(new Color(220, 220, 220));
                        grphcs.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
                        grphcs.dispose();
                    }
                };
                panel.setBackground(new Color(250, 250, 250));
                scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, panel);
                scroll.getViewport().setBackground(Color.WHITE);
                scroll.getVerticalScrollBar().setUI(new ScrollBarCustomUI());
                scroll.getHorizontalScrollBar().setUI(new ScrollBarCustomUI());
                table.getTableHeader().setBackground(new Color(250, 250, 250));
                table.getTableHeader().setSize(table.getWidth(), 30);
                table.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseExited(MouseEvent e) {
                        hoverRow.setIndex(-1);
                        table.repaint();
                    }

                });
                table.addMouseMotionListener(new MouseMotionAdapter() {
                    @Override
                    public void mouseMoved(MouseEvent e) {
                        int row = table.rowAtPoint(e.getPoint());
                        if (row != hoverRow.getIndex()) {
                            hoverRow.setIndex(row);
                            table.repaint();
                        }
                    }

                    @Override
                    public void mouseDragged(MouseEvent e) {
                        int row = table.rowAtPoint(e.getPoint());
                        if (row != hoverRow.getIndex()) {
                            hoverRow.setIndex(row);
                            table.repaint();
                        }
                    }
                });
            } else {
                table.setDefaultRenderer(Object.class, cellRender);
                table.setShowVerticalLines(true);
                table.setGridColor(new Color(220, 220, 220));
                table.setForeground(new Color(51, 51, 51));
                table.setSelectionForeground(new Color(51, 51, 51));
                scroll.setBorder(new LineBorder(new Color(220, 220, 220)));
                JPanel panel = new JPanel() {
                    @Override
                    public void paint(Graphics grphcs) {
                        super.paint(grphcs);
                        grphcs.setColor(new Color(220, 220, 220));
                        grphcs.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
                        grphcs.dispose();
                    }
                };
                panel.setBackground(new Color(250, 250, 250));
                scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, panel);
                scroll.getViewport().setBackground(Color.WHITE);
                scroll.getVerticalScrollBar().setUI(new ScrollBarCustomUI());
                scroll.getHorizontalScrollBar().setUI(new ScrollBarCustomUI());
                table.getTableHeader().setBackground(new Color(250, 250, 250));
                table.getTableHeader().setSize(table.getWidth(), 30);
                table.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseExited(MouseEvent e) {
                        hoverRow.setIndex(-1);
                        table.repaint();
                    }

                });
                table.addMouseMotionListener(new MouseMotionAdapter() {
                    @Override
                    public void mouseMoved(MouseEvent e) {
                        int row = table.rowAtPoint(e.getPoint());
                        if (row != hoverRow.getIndex()) {
                            hoverRow.setIndex(row);
                            table.repaint();
                        }
                    }

                    @Override
                    public void mouseDragged(MouseEvent e) {
                        int row = table.rowAtPoint(e.getPoint());
                        if (row != hoverRow.getIndex()) {
                            hoverRow.setIndex(row);
                            table.repaint();
                        }
                    }
                });
            }
        }

    }


    public static enum TableType {
        MULTI_LINE, DEFAULT, DARK
    }
}
