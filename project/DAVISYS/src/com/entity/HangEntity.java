/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

public class HangEntity {

    private String maHang, tenHang;

    public HangEntity() {
    }

    public HangEntity(String maHang, String tenHang) {
        this.maHang = maHang;
        this.tenHang = tenHang;
    }

    @Override
    public String toString() {
        return this.tenHang;
    }

    public String getMaHang() {
        return maHang;
    }

    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

}
