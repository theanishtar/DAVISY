package com.ui.drawer.scroll;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.BoundedRangeModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class ScrollBarCustomUIDark extends BasicScrollBarUI {

    private boolean isMin;
    private boolean isMax;

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        scrollbar.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                BoundedRangeModel br = scrollbar.getModel();
                boolean min = br.getValue() == br.getMinimum();
                boolean max = br.getValue() + br.getExtent() == br.getMaximum();
                if (isMin != min) {
                    isMin = min;
                    scrollbar.repaint();
                } else if (isMax != max) {
                    isMax = max;
                    scrollbar.repaint();
                }
            }
        });
    }

    @Override
    protected void paintTrack(Graphics grphcs, JComponent jc, Rectangle rctngl) {
        grphcs.setColor(new Color(250, 250, 250));
        super.paintTrack(grphcs, jc, rctngl);
    }

    @Override
    protected void paintThumb(Graphics grphcs, JComponent jc, Rectangle rctngl) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (isDragging) {
            g2.setColor(new Color(130, 130, 130));
        } else {
            if (isThumbRollover()) {
                g2.setColor(new Color(150, 150, 150));
            } else {
                g2.setColor(new Color(180, 180, 180));
            }
        }
        int round = 2;
        int spaceX = 2;
        int spaceY = 8;
        if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
            g2.fill(new RoundRectangle2D.Double(rctngl.getX() + spaceX, rctngl.getY() + spaceY, rctngl.getWidth() - spaceX * 2, rctngl.getHeight() - spaceY * 2, round, round));
        } else {
            g2.fill(new RoundRectangle2D.Double(rctngl.getX() + spaceY, rctngl.getY() + spaceX, rctngl.getWidth() - spaceY * 2, rctngl.getHeight() - spaceX * 2, round, round));
        }
        g2.dispose();
    }

    @Override
    protected JButton createIncreaseButton(int i) {
        return new ScrollButton(scrollbar.getOrientation(), true);
    }

    @Override
    protected JButton createDecreaseButton(int i) {
        return new ScrollButton(scrollbar.getOrientation(), false);
    }

    private class ScrollButton extends JButton {

        private boolean hovered;
        private boolean pressed;
        private final int orientation;
        private final boolean increase;

        public ScrollButton(int orientation, boolean increase) {
            this.orientation = orientation;
            this.increase = increase;
            setContentAreaFilled(false);
            setPreferredSize(new Dimension(18, 18));
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent me) {
                    if (SwingUtilities.isLeftMouseButton(me)) {
                        pressed = true;
                    }
                }

                @Override
                public void mouseReleased(MouseEvent me) {
                    if (SwingUtilities.isLeftMouseButton(me)) {
                        pressed = false;
                    }
                }

                @Override
                public void mouseEntered(MouseEvent me) {
                    hovered = true;
                }

                @Override
                public void mouseExited(MouseEvent me) {
                    hovered = false;
                }
            });
        }

        @Override
        public void paint(Graphics grphcs) {
            Graphics2D g2 = (Graphics2D) grphcs.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if ((increase && isMax) || (!increase && isMin)) {
                g2.setColor(new Color(250, 250, 250));
            } else {
                if (pressed) {
                    g2.setColor(new Color(150, 150, 150));
                } else {
                    if (hovered) {
                        g2.setColor(new Color(200, 200, 200));
                    } else {
                        g2.setColor(new Color(250, 250, 250));
                    }
                }
            }
            g2.fill(new Rectangle(0, 0, getWidth(), getHeight()));
            int width = getWidth();
            int height = getHeight();
            int y = (height - 5) / 2;
            int x = (width - 5) / 2;
            if ((increase && isMax) || (!increase && isMin)) {
                g2.setColor(Color.LIGHT_GRAY);
            } else {
                if (pressed) {
                    g2.setColor(Color.WHITE);
                } else {
                    g2.setColor(Color.GRAY);
                }
            }
            if (orientation == JScrollBar.VERTICAL) {
                int xx[] = {4, width - 4, width / 2};
                int yy[] = {5, 5, 0};
                Polygon poly = new Polygon(xx, yy, xx.length);
                g2.translate(0, (y));
                if (increase) {
                    g2.rotate(Math.toRadians(180), width / 2, height / 2 - y);
                    g2.fill(poly);
                } else {
                    g2.fill(poly);
                }
            } else {
                int xx[] = {4, height - 4, height / 2};
                int yy[] = {5, 5, 0};
                Polygon poly = new Polygon(xx, yy, xx.length);
                g2.translate(x, 0);
                if (increase) {
                    g2.rotate(Math.toRadians(90), width / 2 - x, height / 2 - y);
                    g2.fill(poly);
                } else {
                    g2.rotate(Math.toRadians(-90), width / 2 - 1, height / 2);
                    g2.fill(poly);
                }
            }
            g2.dispose();
        }
    }
}
