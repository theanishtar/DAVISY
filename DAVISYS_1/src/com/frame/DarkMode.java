/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frame;

/**
 *
 * @author ADMIN
 */
public class DarkMode extends javax.swing.JPanel {

    /**
     * Creates new form DarkMode
     */
    public DarkMode() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel10 = new javax.swing.JPanel();
        lblHovaTenNV = new javax.swing.JLabel();
        cboVaiTro = new com.swing.Combobox();
        jLabel35 = new javax.swing.JLabel();
        sbtnTrangThaiNV = new com.hicode.switchbutton.SwitchButton();
        lblTrangThai = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jPanel10.setBackground(new java.awt.Color(51, 51, 51));

        lblHovaTenNV.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblHovaTenNV.setForeground(new java.awt.Color(102, 255, 255));
        lblHovaTenNV.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblHovaTenNV.setText("Họ và tên:");
        lblHovaTenNV.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        cboVaiTro.setBackground(new java.awt.Color(51, 51, 51));
        cboVaiTro.setForeground(new java.awt.Color(255, 255, 255));
        cboVaiTro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Admin", "Quản lí ", "Nhân viên" }));
        cboVaiTro.setLabeText("");

        jLabel35.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(102, 255, 255));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel35.setText("Vai trò:");
        jLabel35.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        sbtnTrangThaiNV.setBackground(new java.awt.Color(0, 153, 0));
        sbtnTrangThaiNV.setFocusable(false);
        sbtnTrangThaiNV.setEnabled(false);
        sbtnTrangThaiNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sbtnTrangThaiNVMouseReleased(evt);
            }
        });

        lblTrangThai.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblTrangThai.setForeground(new java.awt.Color(102, 255, 255));
        lblTrangThai.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblTrangThai.setText("Trạng thái:");
        lblTrangThai.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jLabel2.setBackground(new java.awt.Color(51, 51, 51));
        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tran Huu Dang");
        jLabel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        jLabel2.setOpaque(true);
        jLabel2.setPreferredSize(new java.awt.Dimension(84, 16));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTrangThai, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblHovaTenNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboVaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbtnTrangThaiNV, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblHovaTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboVaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(0, 19, Short.MAX_VALUE)
                        .addComponent(sbtnTrangThaiNV, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblTrangThai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void sbtnTrangThaiNVMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sbtnTrangThaiNVMouseReleased
        
    }//GEN-LAST:event_sbtnTrangThaiNVMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.swing.Combobox cboVaiTro;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JLabel lblHovaTenNV;
    private javax.swing.JLabel lblTrangThai;
    private com.hicode.switchbutton.SwitchButton sbtnTrangThaiNV;
    // End of variables declaration//GEN-END:variables
}