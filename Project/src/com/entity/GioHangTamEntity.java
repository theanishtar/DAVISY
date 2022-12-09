/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

public class GioHangTamEntity {

    private String maKH, tenKH, dienThoai, maGH, tenNV, maSP, tenSP,maHang,maLoai,tenHang,tenLoai;
    private float giaNhap,giaBan,tongTien,thanhTien;
    private int soLuong;

    public GioHangTamEntity() {
    }
    
    

    public GioHangTamEntity(String maKH, String tenKH, String dienThoai, String maGH, String tenNV, String maSP, String tenSP, String maHang, String maLoai, String tenHang, String tenLoai, float giaNhap, float giaBan, float tongTien, float thanhTien, int soLuong) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.dienThoai = dienThoai;
        this.maGH = maGH;
        this.tenNV = tenNV;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.maHang = maHang;
        this.maLoai = maLoai;
        this.tenHang = tenHang;
        this.tenLoai = tenLoai;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.tongTien = tongTien;
        this.thanhTien = thanhTien;
        this.soLuong = soLuong;
    }

    public float getTongTien() {
        return tongTien=this.soLuong*this.giaBan;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public float getThanhTien() {
        return thanhTien=this.tongTien;
    }

    public void setThanhTien(float thanhTien) {
        this.thanhTien = thanhTien;
    }



    public String getMaHang() {
        return maHang;
    }

    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

  

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getMaGH() {
        return maGH;
    }

    public void setMaGH(String maGH) {
        this.maGH = maGH;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
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

    

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    
}
