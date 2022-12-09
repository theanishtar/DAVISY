/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.util.Date;

public class HoaDonEntity {

    private String maHD, maKH, tenDN, maGH, tenKH, tenNV;
    private Date ngayLap;
    private int phanTramGG;
    private float gia, tongTien;
    private int tichDiem=0;
    private float tienGiam, thanhTien;

    public HoaDonEntity() {
    }

    public HoaDonEntity(String maHD, String maKH, String tenDN, String maGH, String tenKH, String tenNV, Date ngayLap, int phanTramGG, float gia, float tongTien, int tichDiem, float tienGiam, float thanhTien) {
        this.maHD = maHD;
        this.maKH = maKH;
        this.tenDN = tenDN;
        this.maGH = maGH;
        this.tenKH = tenKH;
        this.tenNV = tenNV;
        this.ngayLap = ngayLap;
        this.phanTramGG = phanTramGG;
        this.gia = gia;
        this.tongTien = tongTien;
        this.tichDiem = tichDiem;
        this.tienGiam = tienGiam;
        this.thanhTien = thanhTien;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
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

    public String getMaGH() {
        return maGH;
    }

    public void setMaGH(String maGH) {
        this.maGH = maGH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public int getPhanTramGG() {
        return phanTramGG;
    }

    public void setPhanTramGG(int phanTramGG) {
        this.phanTramGG = phanTramGG;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public int getTichDiem() {
        return tichDiem;
    }

    public void setTichDiem(int tichDiem) {
        this.tichDiem = tichDiem;
    }

    public float getThanhTien() {
        return thanhTien = this.tongTien - this.tienGiam;
    }

    public void setThanhTien(float thanhTien) {
        this.thanhTien = thanhTien;
    }

    public float getTienGiam() {
        if (this.phanTramGG == 0 && this.tichDiem == 0) {
            return tienGiam;
        } else {
            if (this.tichDiem > 0 && this.phanTramGG > 0) {
                tienGiam =  this.tongTien - this.tongTien * this.phanTramGG / 100 +  this.tichDiem * 1000;
            } else if (this.tichDiem > 0) {
                tienGiam = this.tichDiem * 1000;
            } else if (this.phanTramGG > 0) {
                tienGiam = this.tongTien - this.tongTien * this.phanTramGG / 100;
            }
            return tienGiam;
        }
    }

    public void setTienGiam(float tienGiam) {
        this.tienGiam = tienGiam;
    }

}
