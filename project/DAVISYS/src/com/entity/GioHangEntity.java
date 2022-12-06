/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

public class GioHangEntity {
    private String maGH,maKH,tenDN;

    public GioHangEntity() {
    }

    public GioHangEntity(String maGH, String maKH, String tenDN) {
        this.maGH = maGH;
        this.maKH = maKH;
        this.tenDN = tenDN;
    }

    public String getMaGH() {
        return maGH;
    }

    public void setMaGH(String maGH) {
        this.maGH = maGH;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenDN() {
        return tenDN;
    }

    public void setTenDN(String tenDN) {
        this.tenDN = tenDN;
    }

   
    
}
