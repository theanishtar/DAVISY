/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.util.Date;

public class SanPhamEntity {

    private String maSP, tenSP, maLH, maHang,tenH,tenL;
    private float giaNhap, giaBan;
    private int sl=0;
    private Date ngayNhap;
    private String hinh, moTa;

    public SanPhamEntity() {
    }

    public SanPhamEntity(String maSP, String tenSP, String maLH, String maHang, String tenH, String tenL, float giaNhap, float giaBan, Date ngayNhap, String hinh, String moTa) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.maLH = maLH;
        this.maHang = maHang;
        this.tenH = tenH;
        this.tenL = tenL;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.ngayNhap = ngayNhap;
        this.hinh = hinh;
        this.moTa = moTa;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

   
    


    public String getTenH() {
        return tenH;
    }

    public void setTenH(String tenH) {
        this.tenH = tenH;
    }

    public String getTenL() {
        return tenL;
    }

    public void setTenL(String tenL) {
        this.tenL = tenL;
    }

 

    @Override
    public String toString() {
        return this.tenSP;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getMaLH() {
        return maLH;
    }

    public void setMaLH(String maLH) {
        this.maLH = maLH;
    }

    public String getMaHang() {
        return maHang;
    }

    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }

    public float getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(float giaNhap) {
        this.giaNhap = giaNhap;
    }

    public float getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(float giaBan) {
        this.giaBan = giaBan;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

}
