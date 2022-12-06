/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

public class LoaiHangEntity {

    private String maLH, tenLH;

    public LoaiHangEntity() {
    }

    public LoaiHangEntity(String maLH, String tenLH) {
        this.maLH = maLH;
        this.tenLH = tenLH;
    }

    @Override
    public String toString() {
        return this.tenLH;
    }

    public String getMaLH() {
        return maLH;
    }

    public void setMaLH(String maLH) {
        this.maLH = maLH;
    }

    public String getTenLH() {
        return tenLH;
    }

    public void setTenLH(String tenLH) {
        this.tenLH = tenLH;
    }

}
