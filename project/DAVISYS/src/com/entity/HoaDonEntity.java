/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.util.Date;

public class HoaDonEntity {

    private String maHD, tenDN, maKH, maGH;
    private Date ngayLap;
    public HoaDonEntity() {
    }

    public HoaDonEntity(String maHD, String tenDN, String maKH, String maGH, Date ngayLap) {
        this.maHD = maHD;
        this.tenDN = tenDN;
        this.maKH = maKH;
        this.maGH = maGH;
        this.ngayLap = ngayLap;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getTenDN() {
        return tenDN;
    }

    public void setTenDN(String tenDN) {
        this.tenDN = tenDN;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaGH() {
        return maGH;
    }

    public void setMaGH(String maGH) {
        this.maGH = maGH;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }
    



}
