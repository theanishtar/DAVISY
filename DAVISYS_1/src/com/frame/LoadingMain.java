/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frame;

import java.awt.Color;
import java.io.IOException;
import java.nio.CharBuffer;

/**
 *
 * @author dangt
 */
public class LoadingMain extends javax.swing.JPanel {

    /**
     * Creates new form Loading
     */
    Color color = new Color(0, 204, 0);
    //Color basic = new Color(204, 204, 204);
    Color basic = new Color(0,153,102);
    Color basicLoad = new Color(232,255,0);
    Color color1=new Color(232,255,0);
    Color color2=new Color(244,235,40);
    Color color3=new Color(255,214,33);
    Color color4=new Color(255,218,17);
    Color color5=new Color(244,206,18);
    Color color6=new Color(244,185,21);
    Color color7=new Color(244,165,32);
    Color color8=new Color(248,119,16);

    public LoadingMain() {

        initComponents();
        load1.setBackground(basic);
        load2.setBackground(basic);
        load3.setBackground(basic);
        load4.setBackground(basic);
        load5.setBackground(basic);
        load6.setBackground(basic);
        load7.setBackground(basic);
        load8.setBackground(basic);
        //
        fontL.setForeground(basic);
        fontO.setForeground(basic);
        fontA.setForeground(basic);
        fontD.setForeground(basic);
        fontI.setForeground(basic);
        fontN.setForeground(basic);
        fontG.setForeground(basic);
        font_S6.setForeground(basic);

        //
        runLoad();
        runFont();
    }

    public void runFont() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //throw new UnsupportedOperationException("Not supported yet."); 
                //boolean fat = true;
                int nb = 0;
                try {
                    while (true) {
                        switch (nb) {
                            case 0:
                                Thread.sleep(250);
                                fontL.setForeground(color1);
                                fontO.setForeground(basicLoad);
                                fontA.setForeground(basicLoad);
                                fontD.setForeground(basicLoad);
                                fontI.setForeground(basicLoad);
                                fontN.setForeground(basicLoad);
                                fontG.setForeground(basicLoad);
                                font_S6.setForeground(basicLoad);
                                nb++;

                            case 1:
                                Thread.sleep(250);
                                fontL.setForeground(basicLoad);
                                fontO.setForeground(color2);
                                fontA.setForeground(basicLoad);
                                fontD.setForeground(basicLoad);
                                fontI.setForeground(basicLoad);
                                fontN.setForeground(basicLoad);
                                fontG.setForeground(basicLoad);
                                font_S6.setForeground(basicLoad);
                                nb++;
                            case 2:
                                Thread.sleep(250);
                                fontL.setForeground(basicLoad);
                                fontO.setForeground(basicLoad);
                                fontA.setForeground(color3);
                                fontD.setForeground(basicLoad);
                                fontI.setForeground(basicLoad);
                                fontN.setForeground(basicLoad);
                                fontG.setForeground(basicLoad);
                                font_S6.setForeground(basicLoad);
                                nb++;
                            case 3:
                                Thread.sleep(250);
                                fontL.setForeground(basicLoad);
                                fontO.setForeground(basicLoad);
                                fontA.setForeground(basicLoad);
                                fontD.setForeground(color4);
                                fontI.setForeground(basicLoad);
                                fontN.setForeground(basicLoad);
                                fontG.setForeground(basicLoad);
                                font_S6.setForeground(basicLoad);
                                nb++;
                            case 4:
                                Thread.sleep(250);
                                fontL.setForeground(basicLoad);
                                fontO.setForeground(basicLoad);
                                fontA.setForeground(basicLoad);
                                fontD.setForeground(basicLoad);
                                fontI.setForeground(color5);
                                fontN.setForeground(basicLoad);
                                fontG.setForeground(basicLoad);
                                font_S6.setForeground(basicLoad);
                                nb++;
                            case 5:
                                Thread.sleep(250);
                                fontL.setForeground(basicLoad);
                                fontO.setForeground(basicLoad);
                                fontA.setForeground(basicLoad);
                                fontD.setForeground(basicLoad);
                                fontI.setForeground(basicLoad);
                                fontN.setForeground(color6);
                                fontG.setForeground(basicLoad);
                                font_S6.setForeground(basicLoad);
                                nb++;
                            case 6:
                                Thread.sleep(250);
                                fontL.setForeground(basicLoad);
                                fontO.setForeground(basicLoad);
                                fontA.setForeground(basicLoad);
                                fontD.setForeground(basicLoad);
                                fontI.setForeground(basicLoad);
                                fontN.setForeground(basicLoad);
                                fontG.setForeground(color7);
                                font_S6.setForeground(basicLoad);
                                nb++;
                            case 7:
                                Thread.sleep(250);
                                fontL.setForeground(basicLoad);
                                fontO.setForeground(basicLoad);
                                fontA.setForeground(basicLoad);
                                fontD.setForeground(basicLoad);
                                fontI.setForeground(basicLoad);
                                fontN.setForeground(basicLoad);
                                fontG.setForeground(basicLoad);
                                font_S6.setForeground(color8);
                                nb = 0;
                        }

                    }
                } catch (Exception e) {

                }

            }
        }).start();
    }

    public void runLoad() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //throw new UnsupportedOperationException("Not supported yet."); 
                int nb = 0;
                try {
                    while (true) {
                        switch (nb) {
                            case 0:
                                Thread.sleep(250);
                                load1.setBackground(color1);
                                load2.setBackground(basic);
                                load3.setBackground(basic);
                                load4.setBackground(basic);
                                load5.setBackground(basic);
                                load6.setBackground(basic);
                                load7.setBackground(basic);
                                load8.setBackground(basic);
                                nb++;

                            case 1:
                                Thread.sleep(250);
                                load1.setBackground(basic);
                                load2.setBackground(color2);
                                load3.setBackground(basic);
                                load4.setBackground(basic);
                                load5.setBackground(basic);
                                load6.setBackground(basic);
                                load7.setBackground(basic);
                                load8.setBackground(basic);
                                nb++;
                            case 2:
                                Thread.sleep(250);
                                load1.setBackground(basic);
                                load2.setBackground(basic);
                                load3.setBackground(color3);
                                load4.setBackground(basic);
                                load5.setBackground(basic);
                                load6.setBackground(basic);
                                load7.setBackground(basic);
                                load8.setBackground(basic);
                                nb++;
                            case 3:
                                Thread.sleep(250);
                                load1.setBackground(basic);
                                load2.setBackground(basic);
                                load3.setBackground(basic);
                                load4.setBackground(color4);
                                load5.setBackground(basic);
                                load6.setBackground(basic);
                                load7.setBackground(basic);
                                load8.setBackground(basic);
                                nb++;
                            case 4:
                                Thread.sleep(250);
                                load1.setBackground(basic);
                                load2.setBackground(basic);
                                load3.setBackground(basic);
                                load4.setBackground(basic);
                                load5.setBackground(color5);
                                load6.setBackground(basic);
                                load7.setBackground(basic);
                                load8.setBackground(basic);
                                nb++;
                            case 5:
                                Thread.sleep(250);
                                load1.setBackground(basic);
                                load2.setBackground(basic);
                                load3.setBackground(basic);
                                load4.setBackground(basic);
                                load5.setBackground(basic);
                                load6.setBackground(color6);
                                load7.setBackground(basic);
                                load8.setBackground(basic);
                                nb++;
                            case 6:
                                Thread.sleep(250);
                                load1.setBackground(basic);
                                load2.setBackground(basic);
                                load3.setBackground(basic);
                                load4.setBackground(basic);
                                load5.setBackground(basic);
                                load6.setBackground(basic);
                                load7.setBackground(color7);
                                load8.setBackground(basic);
                                nb++;
                            case 7:
                                Thread.sleep(250);
                                load1.setBackground(basic);
                                load2.setBackground(basic);
                                load3.setBackground(basic);
                                load4.setBackground(basic);
                                load5.setBackground(basic);
                                load6.setBackground(basic);
                                load7.setBackground(basic);
                                load8.setBackground(color8);
                                nb = 0;
                        }

                    }
                } catch (Exception e) {

                }

            }
        }).start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        kGradientPanel1 = new com.swing.KGradientPanel();
        fontL = new javax.swing.JLabel();
        fontO = new javax.swing.JLabel();
        fontA = new javax.swing.JLabel();
        load1 = new javax.swing.JLabel();
        fontD = new javax.swing.JLabel();
        load2 = new javax.swing.JLabel();
        fontI = new javax.swing.JLabel();
        load3 = new javax.swing.JLabel();
        fontN = new javax.swing.JLabel();
        load4 = new javax.swing.JLabel();
        fontG = new javax.swing.JLabel();
        load5 = new javax.swing.JLabel();
        font_S6 = new javax.swing.JLabel();
        load6 = new javax.swing.JLabel();
        load7 = new javax.swing.JLabel();
        load8 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1220, 671));

        kGradientPanel1.setkEndColor(new java.awt.Color(154, 213, 224));
        kGradientPanel1.setkStartColor(new java.awt.Color(224, 154, 214));
        kGradientPanel1.setPreferredSize(new java.awt.Dimension(1220, 671));

        fontL.setBackground(new java.awt.Color(0, 204, 0));
        fontL.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        fontL.setForeground(new java.awt.Color(0, 204, 0));
        fontL.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        fontL.setText("L");

        fontO.setBackground(new java.awt.Color(0, 204, 0));
        fontO.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        fontO.setForeground(new java.awt.Color(0, 204, 0));
        fontO.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        fontO.setText("o");

        fontA.setBackground(new java.awt.Color(0, 204, 0));
        fontA.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        fontA.setForeground(new java.awt.Color(0, 204, 0));
        fontA.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        fontA.setText("a");

        load1.setBackground(new java.awt.Color(232, 255, 0));
        load1.setOpaque(true);

        fontD.setBackground(new java.awt.Color(0, 204, 0));
        fontD.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        fontD.setForeground(new java.awt.Color(0, 204, 0));
        fontD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        fontD.setText("d");

        load2.setBackground(new java.awt.Color(244, 235, 40));
        load2.setOpaque(true);

        fontI.setBackground(new java.awt.Color(0, 204, 0));
        fontI.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        fontI.setForeground(new java.awt.Color(0, 204, 0));
        fontI.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        fontI.setText("i");

        load3.setBackground(new java.awt.Color(255, 214, 33));
        load3.setOpaque(true);

        fontN.setBackground(new java.awt.Color(0, 204, 0));
        fontN.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        fontN.setForeground(new java.awt.Color(0, 204, 0));
        fontN.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        fontN.setText("n");

        load4.setBackground(new java.awt.Color(255, 218, 17));
        load4.setOpaque(true);

        fontG.setBackground(new java.awt.Color(0, 204, 0));
        fontG.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        fontG.setForeground(new java.awt.Color(0, 204, 0));
        fontG.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        fontG.setText("g");

        load5.setBackground(new java.awt.Color(244, 206, 18));
        load5.setOpaque(true);

        font_S6.setBackground(new java.awt.Color(0, 102, 0));
        font_S6.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        font_S6.setForeground(new java.awt.Color(0, 153, 102));
        font_S6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        font_S6.setText("^");

        load6.setBackground(new java.awt.Color(244, 185, 21));
        load6.setOpaque(true);

        load7.setBackground(new java.awt.Color(244, 165, 32));
        load7.setOpaque(true);

        load8.setBackground(new java.awt.Color(248, 119, 16));
        load8.setOpaque(true);

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGap(511, 511, 511)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addComponent(fontL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fontO)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fontA)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fontD)
                        .addGap(10, 10, 10)
                        .addComponent(fontI)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fontN)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fontG)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(font_S6))
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addComponent(load1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(load2, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(load3, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(load4, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(load5, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(load6, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(load7, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(load8, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(561, Short.MAX_VALUE))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGap(274, 274, 274)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(load8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(load7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(load6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(load5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(load3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(load4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                                .addGap(95, 95, 95)
                                .addComponent(load1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                                .addGap(81, 81, 81)
                                .addComponent(load2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fontL)
                    .addComponent(fontO)
                    .addComponent(fontA)
                    .addComponent(fontD)
                    .addComponent(fontI)
                    .addComponent(fontN)
                    .addComponent(fontG)
                    .addComponent(font_S6))
                .addGap(262, 262, 262))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fontA;
    private javax.swing.JLabel fontD;
    private javax.swing.JLabel fontG;
    private javax.swing.JLabel fontI;
    private javax.swing.JLabel fontL;
    private javax.swing.JLabel fontN;
    private javax.swing.JLabel fontO;
    private javax.swing.JLabel font_S6;
    private com.swing.KGradientPanel kGradientPanel1;
    private javax.swing.JLabel load1;
    private javax.swing.JLabel load2;
    private javax.swing.JLabel load3;
    private javax.swing.JLabel load4;
    private javax.swing.JLabel load5;
    private javax.swing.JLabel load6;
    private javax.swing.JLabel load7;
    private javax.swing.JLabel load8;
    // End of variables declaration//GEN-END:variables
}
