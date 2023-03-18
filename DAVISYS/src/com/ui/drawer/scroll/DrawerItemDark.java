package com.ui.drawer.scroll;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class DrawerItemDark extends JButton {

    public void setIndex(int index) {
        this.index = index;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
        repaint();
    }

    public Color getEffectColor() {
        return effectColor;
    }

    public void setEffectColor(Color effectColor) {
        this.effectColor = effectColor;
        repaint();
    }

    private Animator animator;
    private Animator animatorMouse;
    private int targetSize;
    private float animatSize;
    private Point pressedPoint;
    private float alpha;
    private int round;
    private Color effectColor = new Color(0,204,204);
    private int index;
    private boolean mouseEnter;
    private float animateMouse;
    private Insets borderInset = new Insets(0, 15, 0, 15);
    private Icon icon;
    private int iconTextGap = 15;

    public DrawerItemDark(String text) {
        super(text);
    }

    public DrawerItemDark effectColor(Color effectColor) {
        this.effectColor = effectColor;
        return this;
    }

    public DrawerItemDark borderInset(Insets borderInset) {
        this.borderInset = borderInset;
        return this;
    }

    public DrawerItemDark icon(Icon icon) {
        this.icon = icon;
        return this;
    }

    public DrawerItemDark iconTextGap(int iconTextGap) {
        this.iconTextGap = iconTextGap;
        return this;
    }

    public DrawerItemDark font(Font font) {
        setFont(font);
        return this;
    }

    public DrawerItemDark build() {
        init();
        return this;
    }

    private void init() {
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(borderInset));
        setIconTextGap(iconTextGap);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setHorizontalAlignment(JButton.LEFT);
        if (icon != null) {
            setIcon(icon);
        }
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    targetSize = Math.max(getWidth(), getHeight()) * 2;
                    animatSize = 0;
                    pressedPoint = me.getPoint();
                    alpha = 0.5f;
                    if (animator.isRunning()) {
                        animator.stop();
                    }
                    animator.start();
                }
            }
        });
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                if (fraction > 0.5f) {
                    alpha = 1 - fraction;
                }
                animatSize = fraction * targetSize;
                repaint();
            }
        };
        animator = new Animator(500, target);
        animator.setResolution(0);
    }

    public void initMouseOver() {
        TimingTarget targetMouse = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                animateMouse = mouseEnter ? fraction : 1f - fraction;
                repaint();
            }
        };
        animatorMouse = new Animator(300, targetMouse);
        animatorMouse.setResolution(0);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                startAnimatorMouse(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                startAnimatorMouse(false);
            }
        });
    }

    private void startAnimatorMouse(boolean mouseEnter) {
        if (animatorMouse.isRunning()) {
            float f = animatorMouse.getTimingFraction();
            animatorMouse.stop();
            animatorMouse.setStartFraction(1f - f);
        } else {
            animatorMouse.setStartFraction(0f);
        }
        this.mouseEnter = mouseEnter;
        animatorMouse.start();
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        int width = getWidth();
        int height = getHeight();
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (animateMouse > 0) {
            double h = animateMouse * height;
            Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, round, round));
            double y = (height - h) / 2;
            area.intersect(new Area(new RoundRectangle2D.Double(0, y, width, h, round, round)));
            g2.setPaint(new GradientPaint(0, 0, new Color(238, 238, 103), width, height, new Color(255, 111, 111)));
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, animateMouse * 0.7f));
            g2.fill(area);
        }
        if (pressedPoint != null) {
            Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, round, round));
            g2.setColor(effectColor);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            area.intersect(new Area(new Ellipse2D.Double((pressedPoint.x - animatSize / 2), (pressedPoint.y - animatSize / 2), animatSize, animatSize)));
            g2.fill(area);
        }
        g2.dispose();
        super.paintComponent(grphcs);
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "drawer";
    }
}
