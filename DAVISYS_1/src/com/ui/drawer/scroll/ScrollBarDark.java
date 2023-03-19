package com.ui.drawer.scroll;

import java.awt.Dimension;
import javax.swing.JScrollBar;

public class ScrollBarDark extends JScrollBar {

    public ScrollBarDark(int al) {
        super(al);
        setUI(new ModernScrollBarUI());
        setPreferredSize(new Dimension(5, 5));
        setOpaque(false);
        setUnitIncrement(20);
    }
}
