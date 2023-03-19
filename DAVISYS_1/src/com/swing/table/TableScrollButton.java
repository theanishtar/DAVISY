package com.swing.table;

import java.awt.AWTEvent;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLayer;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.plaf.LayerUI;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import org.jdesktop.animation.timing.interpolation.PropertySetter;

public class TableScrollButton extends JLayeredPane {

    private float animate;
    private boolean show = false;
    private Animator animator;
    private Animator animatorScroll;
    private TimingTarget target;

    public TableScrollButton() {
        init();
    }

    private void init() {
        setLayout(new BorderLayout());
        animator = new Animator(300, new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                if (show) {
                    animate = fraction;
                } else {
                    animate = 1f - fraction;
                }
                repaint();
            }
        });
        animator.setAcceleration(.5f);
        animator.setDeceleration(.5f);
        animator.setResolution(5);
        animatorScroll = new Animator(300);
        animatorScroll.setAcceleration(.5f);
        animatorScroll.setDeceleration(.5f);
        animatorScroll.setResolution(5);
    }

    private void start(boolean show) {
        if (animator.isRunning()) {
            float f = animator.getTimingFraction();
            animator.stop();
            animator.setStartFraction(1f - f);
        } else {
            animator.setStartFraction(0f);
        }
        this.show = show;
        animator.start();
    }

    @Override
    public void add(Component comp, Object constraints) {
        super.add(new JLayer(comp, new ScrollLayerUI()), constraints);
    }

    private class ScrollLayerUI extends LayerUI<JScrollPane> {

        private Shape shape;
        private Color color = new Color(60, 148, 225);
        private boolean mousePressed;
        private boolean mouseHovered;
        private final Image image = new ImageIcon(getClass().getResource("/table/up.png")).getImage();

        @Override
        public void installUI(JComponent c) {
            super.installUI(c);
            if (c instanceof JLayer) {
                ((JLayer) c).setLayerEventMask(AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK);
            }
        }

        @Override
        public void uninstallUI(JComponent c) {
            if (c instanceof JLayer) {
                ((JLayer) c).setLayerEventMask(0);
            }
            super.uninstallUI(c);
        }

        @Override
        public void paint(Graphics g, JComponent c) {
            super.paint(g, c);
            JScrollPane scroll = (JScrollPane) ((JLayer) c).getView();
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (mousePressed) {
                g2.setColor(new Color(10, 92, 137));
            } else {
                if (mouseHovered) {
                    g2.setColor(new Color(14, 122, 181));
                } else {
                    g2.setColor(new Color(18, 149, 220));
                }
            }
            int gapx = scroll.getVerticalScrollBar().isShowing() ? scroll.getVerticalScrollBar().getWidth() : 0;
            int gapy = scroll.getHorizontalScrollBar().isShowing() ? scroll.getHorizontalScrollBar().getHeight() : 0;
            int y_over = 50 + gapy;
            int x = c.getWidth() - 50 - gapx;
            int y = (int) ((c.getHeight() - 50 - gapy) + (y_over * (1f - animate)));
            shape = new Ellipse2D.Double(x, y, 40, 40);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, animate * 0.7f));
            g2.fill(shape);
            g2.drawImage(image, x + 10, y + 10, null);
            g2.dispose();
            if (scroll.getViewport().getViewRect().y > 0) {
                if (!show) {
                    start(true);
                }
            } else if (show) {
                start(false);
            }
        }

        @Override
        protected void processMouseEvent(MouseEvent e, JLayer<? extends JScrollPane> l) {
            Point point = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), l.getView());
            if (SwingUtilities.isLeftMouseButton(e)) {
                if (e.getID() == MouseEvent.MOUSE_PRESSED) {
                    if (shape.contains(point)) {
                        mousePressed = true;
                        e.consume();
                    }
                } else if (e.getID() == MouseEvent.MOUSE_RELEASED) {
                    if (mousePressed && mouseHovered) {
                        scrollBackToTop(l.getView());
                    }
                    mousePressed = false;

                }
            }
            l.repaint();
        }

        @Override
        protected void processMouseMotionEvent(MouseEvent e, JLayer<? extends JScrollPane> l) {
            Point point = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), l.getView());
            if (shape.contains(point)) {
                mouseHovered = true;
                e.consume();
            } else {
                mouseHovered = false;
            }
            l.repaint();
        }
    }

    private void scrollBackToTop(JScrollPane scroll) {
        animatorScroll.removeTarget(target);
        target = new PropertySetter(scroll.getVerticalScrollBar(), "value", scroll.getVerticalScrollBar().getValue(), 0);
        animatorScroll.addTarget(target);
        animatorScroll.start();
    }
}
