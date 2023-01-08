/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui;

import AppPackage.AnimationClass;
import com.dao.TaiKhoanDAO;
import com.entity.TaiKhoanEntity;
import com.library.extensisons.ScanQR;
import com.utils.MsgBox;
import com.utils.XImage;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Tran Huu Dang from DAVISY
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login2
     */
    AnimationClass ac = new AnimationClass();
    TaiKhoanDAO tk = new TaiKhoanDAO();
    List<TaiKhoanEntity> listTK = new ArrayList<>();
    public String tenDN;

    public Login() {

        initComponents();
        this.setIconImage(XImage.getAppIcon());
        listTK = tk.selectAll();
        txtPassword.setEchoChar('●');
        lblHide.setVisible(false);
        slideShow();
    }

    public boolean ktNull() {
        if (txtUsername.getText().equals("")) {
            MsgBox.alert(this, "Vui lòng nhập tên đăng nhập!");
            txtUsername.requestFocus();
            return false;
        } else if (txtPassword.getText().equals("")) {
            MsgBox.alert(this, "Vui lòng nhập mật khẩu!");
            txtPassword.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    public void dangNhap() {
        if (ktNull()) {
            for (TaiKhoanEntity tk : listTK) {
                if (txtUsername.getText().equalsIgnoreCase(tk.getTenDN()) && txtPassword.getText().equalsIgnoreCase(tk.getMatKhau())) {
                    if (tk.isTrangThai()) {
                        tenDN = tk.getTenDN();
                        login(tenDN);
                        return;
                    } else {
                        MsgBox.alert(this, "Tài khoản không hoạt động!");
//                        txtUsername.setText("");
//                        txtPassword.setText("");
                        txtUsername.requestFocus();
                        return;
                    }

                }

            }
            MsgBox.alert(this, "Tên đăng nhập không tồn tại hoặc mật khẩu sai!");
//            txtUsername.setText("");
//            txtPassword.setText("");
            txtUsername.requestFocus();
            return;
        }
    }

    public void slideShow() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int nb = 0;
                try {
                    while (true) {
                        switch (nb) {
                            case 0:
                                Thread.sleep(2000);
                                ac.jLabelXLeft(0, -480, 12, 10, img1);
                                ac.jLabelXLeft(480, 0, 12, 10, img2);
                                ac.jLabelXLeft(960, 480, 12, 10, img3);
                                ac.jLabelXLeft(1440, 960, 12, 10, img4);
                                ac.jLabelXLeft(1920, 1440, 12, 10, img5);
                                nb++;

                            case 1:
                                Thread.sleep(3000);
                                ac.jLabelXLeft(-480, -960, 12, 10, img1);
                                ac.jLabelXLeft(0, -480, 12, 10, img2);
                                ac.jLabelXLeft(480, 0, 12, 10, img3);
                                ac.jLabelXLeft(960, 480, 12, 10, img4);
                                ac.jLabelXLeft(1440, 960, 12, 10, img5);
                                nb++;
                            case 2:
                                Thread.sleep(3000);

                                //
                                ac.jLabelXLeft(-960, -1440, 12, 10, img1);
                                ac.jLabelXLeft(-480, -960, 12, 10, img2);
                                ac.jLabelXLeft(0, -480, 12, 10, img3);
                                ac.jLabelXLeft(480, 0, 12, 10, img4);
                                ac.jLabelXLeft(960, 480, 12, 10, img5);
                                nb++;
                            case 3:
                                Thread.sleep(3000);

                                //
                                ac.jLabelXLeft(-1440, -1920, 12, 10, img1);
                                ac.jLabelXLeft(-960, -1440, 12, 10, img2);
                                ac.jLabelXLeft(-480, -960, 12, 10, img3);
                                ac.jLabelXLeft(0, -480, 12, 10, img4);
                                ac.jLabelXLeft(480, 0, 12, 10, img5);
                                nb++;
                            case 4:
                                Thread.sleep(7000);
                                ac.jLabelXRight(-1920, -1440, 12, 10, img1);
                                ac.jLabelXRight(-1440, -960, 12, 10, img2);
                                ac.jLabelXRight(-960, -480, 12, 10, img3);
                                ac.jLabelXRight(-480, 0, 12, 10, img4);
                                ac.jLabelXRight(0, 480, 12, 10, img5);
                                nb++;
                            case 5:
                                Thread.sleep(3000);
                                ac.jLabelXRight(-1440, -960, 12, 10, img1);
                                ac.jLabelXRight(-960, -480, 12, 10, img2);
                                ac.jLabelXRight(-480, 0, 12, 10, img3);
                                ac.jLabelXRight(0, 480, 12, 10, img4);
                                ac.jLabelXRight(480, 960, 12, 10, img5);
                                nb++;
                            case 6:
                                Thread.sleep(3000);
                                ac.jLabelXRight(-960, -480, 12, 10, img1);
                                ac.jLabelXRight(-480, 0, 12, 10, img2);
                                ac.jLabelXRight(0, 480, 12, 10, img3);
                                ac.jLabelXRight(480, 960, 12, 10, img4);
                                ac.jLabelXRight(960, 1440, 12, 10, img5);
                                nb++;
                            case 7:
                                Thread.sleep(7000);
                                ac.jLabelXRight(-480, 0, 12, 10, img1);
                                ac.jLabelXRight(0, 480, 12, 10, img2);
                                ac.jLabelXRight(480, 960, 12, 10, img3);
                                ac.jLabelXRight(960, 1440, 12, 10, img4);
                                ac.jLabelXRight(1440, 1920, 12, 10, img5);
                                nb = 0;
                                Thread.sleep(4000);

                        }
                    }
                } catch (Exception e) {

                }
            }
        }).start();
    }

    String emailGetTxt = null;

    public boolean checkEntityEmail() {
        TaiKhoanEntity tklist = tk.selectById(txtUsername.getText());
        if (tklist != null) {
            emailGetTxt = tklist.getEmail();
            return true;
        } else {
            MsgBox.alert(this, "Tài khoản không tồn tại!");
        }
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Main = new com.swing.KGradientPanel();
        jplState = new javax.swing.JPanel();
        jlbState = new javax.swing.JLabel();
        jplLose = new javax.swing.JPanel();
        jlbClose = new javax.swing.JLabel();
        panelRound1 = new com.swing.PanelRound();
        lblTitleLogin = new javax.swing.JLabel();
        btnLogin = new com.swing.Button();
        txtUsername = new com.swing.TextField();
        lblShow = new javax.swing.JLabel();
        lblHide = new javax.swing.JLabel();
        txtPassword = new com.swing.PasswordField();
        lblForget = new javax.swing.JLabel();
        jplAnimation = new javax.swing.JPanel();
        img1 = new javax.swing.JLabel();
        img2 = new javax.swing.JLabel();
        img3 = new javax.swing.JLabel();
        img4 = new javax.swing.JLabel();
        img5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Main.setkEndColor(new java.awt.Color(154, 213, 224));
        Main.setkStartColor(new java.awt.Color(224, 154, 214));
        Main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jplState.setBackground(new java.awt.Color(117, 198, 213));

        jlbState.setFont(new java.awt.Font("Baskerville Old Face", 1, 48)); // NOI18N
        jlbState.setForeground(new java.awt.Color(255, 255, 255));
        jlbState.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbState.setText("-");
        jlbState.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jlbStateFocusGained(evt);
            }
        });
        jlbState.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbStateMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlbStateMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlbStateMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jplStateLayout = new javax.swing.GroupLayout(jplState);
        jplState.setLayout(jplStateLayout);
        jplStateLayout.setHorizontalGroup(
            jplStateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbState, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );
        jplStateLayout.setVerticalGroup(
            jplStateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jplStateLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jlbState, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Main.add(jplState, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 0, -1, -1));

        jplLose.setBackground(new java.awt.Color(117, 198, 213));
        jplLose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jplLoseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jplLoseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jplLoseMouseExited(evt);
            }
        });
        jplLose.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlbClose.setFont(new java.awt.Font("Source Sans Pro Black", 1, 18)); // NOI18N
        jlbClose.setForeground(new java.awt.Color(255, 255, 255));
        jlbClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbClose.setText(" X");
        jlbClose.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jlbCloseFocusGained(evt);
            }
        });
        jlbClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbCloseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlbCloseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlbCloseMouseExited(evt);
            }
        });
        jplLose.add(jlbClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 30));

        Main.add(jplLose, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 0, -1, -1));

        panelRound1.setBackground(new java.awt.Color(255, 255, 255));
        panelRound1.setRoundBottomRight(20);
        panelRound1.setRoundTopRight(20);
        panelRound1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitleLogin.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblTitleLogin.setForeground(new java.awt.Color(71, 163, 255));
        lblTitleLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitleLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/businessman.png"))); // NOI18N
        lblTitleLogin.setText("ĐĂNG NHẬP");
        panelRound1.add(lblTitleLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 430, -1));

        btnLogin.setBackground(new java.awt.Color(71, 163, 255));
        btnLogin.setForeground(new java.awt.Color(161, 30, 145));
        btnLogin.setText("Đăng nhập");
        btnLogin.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        panelRound1.add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 400, 340, 38));

        txtUsername.setDisabledTextColor(new java.awt.Color(204, 0, 0));
        txtUsername.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtUsername.setLabelText("Tên đăng nhập");
        txtUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsernameKeyPressed(evt);
            }
        });
        panelRound1.add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 330, -1));

        lblShow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/show.png"))); // NOI18N
        lblShow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblShowMouseClicked(evt);
            }
        });
        panelRound1.add(lblShow, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 270, -1, -1));

        lblHide.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/hide.png"))); // NOI18N
        lblHide.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHideMouseClicked(evt);
            }
        });
        panelRound1.add(lblHide, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 270, -1, -1));

        txtPassword.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPassword.setLabelText("Mật khẩu");
        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPasswordKeyPressed(evt);
            }
        });
        panelRound1.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 330, -1));

        lblForget.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblForget.setForeground(new java.awt.Color(153, 0, 153));
        lblForget.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblForget.setText("Quên mật khẩu?");
        lblForget.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblForgetMouseClicked(evt);
            }
        });
        panelRound1.add(lblForget, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, 330, -1));

        Main.add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 120, 430, 500));

        jplAnimation.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        img1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/asset/slide-login/img3.jpg"))); // NOI18N
        img1.setText("jLabel1");
        jplAnimation.add(img1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 480, 500));

        img2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/asset/slide-login/img2.jpg"))); // NOI18N
        img2.setText("jLabel1");
        jplAnimation.add(img2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, 480, 500));

        img3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/asset/slide-login/img1.jpg"))); // NOI18N
        img3.setText("jLabel1");
        jplAnimation.add(img3, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 0, 480, 500));

        img4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/asset/slide-login/img4.jpg"))); // NOI18N
        img4.setText("jLabel1");
        jplAnimation.add(img4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1440, 0, 480, 500));

        img5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/asset/slide-login/img5.jpg"))); // NOI18N
        img5.setText("jLabel1");
        jplAnimation.add(img5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1920, 0, 480, 500));

        Main.add(jplAnimation, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 480, 500));

        jLabel1.setFont(new java.awt.Font("Cormorant Infant Medium", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 255));
        jLabel1.setText("DAVISY");
        Main.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 180, 50));

        getContentPane().add(Main, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 700));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jlbStateFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jlbStateFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jlbStateFocusGained

    private void jlbStateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbStateMouseClicked
        this.setState(1);
    }//GEN-LAST:event_jlbStateMouseClicked

    private void jlbStateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbStateMouseEntered
        jplState.setBackground(new Color(229, 221, 242));
        jlbState.setForeground(Color.black);
    }//GEN-LAST:event_jlbStateMouseEntered

    private void jlbStateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbStateMouseExited
        jplState.setBackground(new Color(117, 198, 213));
        jlbState.setForeground(Color.white);
    }//GEN-LAST:event_jlbStateMouseExited

    private void jlbCloseFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jlbCloseFocusGained
        //jplLose.setBackground(Color.red);
        System.exit(0);
    }//GEN-LAST:event_jlbCloseFocusGained

    private void jlbCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbCloseMouseClicked
        if (!txtUsername.getText().equals("") || !txtPassword.getText().equals("")) {
            int ketQua = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn thoát khỏi chương trình?", "Thoát chương trình", JOptionPane.YES_NO_OPTION);
            if (ketQua == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        } else {
            System.exit(0);
        }
    }//GEN-LAST:event_jlbCloseMouseClicked

    private void jlbCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbCloseMouseEntered
        // TODO add your handling code here:
        jplLose.setBackground(Color.red);
    }//GEN-LAST:event_jlbCloseMouseEntered

    private void jlbCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbCloseMouseExited
        // TODO add your handling code here:
        jplLose.setBackground(new Color(117, 198, 213));
    }//GEN-LAST:event_jlbCloseMouseExited

    private void jplLoseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplLoseMouseClicked
        System.exit(0);
    }//GEN-LAST:event_jplLoseMouseClicked

    private void jplLoseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplLoseMouseEntered
        jplLose.setBackground(Color.red);
        //jlbClose.setForeground(Color.white);
    }//GEN-LAST:event_jplLoseMouseEntered

    private void jplLoseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplLoseMouseExited
        // TODO add your handling code here:
        jplLose.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_jplLoseMouseExited

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void lblHideMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHideMouseClicked
        lblHide.setVisible(false);
        lblShow.setVisible(true);
        txtPassword.setEchoChar('●');
    }//GEN-LAST:event_lblHideMouseClicked

    private void lblShowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblShowMouseClicked
        lblShow.setVisible(false);
        lblHide.setVisible(true);
        txtPassword.setEchoChar((char) 0);
    }//GEN-LAST:event_lblShowMouseClicked

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        dangNhap();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void txtUsernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsernameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtPassword.requestFocus();
        }
    }//GEN-LAST:event_txtUsernameKeyPressed

    private void txtPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            dangNhap();
        }
    }//GEN-LAST:event_txtPasswordKeyPressed

    private void lblForgetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblForgetMouseClicked
        // getEmail
        txtPassword.setText("");
        if (checkEntityEmail()) {
            boolean kq = MsgBox.confirm(this, "Email " + emailGetTxt + " có phải của bạn không?");
            if (kq) {
                // gửi mã
                try {
                    ScanQR readQR = new ScanQR(emailGetTxt);
                    readQR.setVisible(true);
                    this.dispose();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }//GEN-LAST:event_lblForgetMouseClicked

    public void login(String tenDN) {
        Home frhome = new Home(tenDN);
        frhome.setVisible(true);
        this.dispose();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorialuiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.swing.KGradientPanel Main;
    private com.swing.Button btnLogin;
    private javax.swing.JLabel img1;
    private javax.swing.JLabel img2;
    private javax.swing.JLabel img3;
    private javax.swing.JLabel img4;
    private javax.swing.JLabel img5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jlbClose;
    private javax.swing.JLabel jlbState;
    private javax.swing.JPanel jplAnimation;
    private javax.swing.JPanel jplLose;
    private javax.swing.JPanel jplState;
    private javax.swing.JLabel lblForget;
    private javax.swing.JLabel lblHide;
    private javax.swing.JLabel lblShow;
    private javax.swing.JLabel lblTitleLogin;
    private com.swing.PanelRound panelRound1;
    private com.swing.PasswordField txtPassword;
    private com.swing.TextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
