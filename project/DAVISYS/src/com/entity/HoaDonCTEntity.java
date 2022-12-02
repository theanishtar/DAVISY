/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;
import java.util.Date;
public class HoaDonCTEntity {

    private String maCTHD,maHD, maSP, maHang, maLH;
    private Date ngayLap;
    private String tenSP, tenHang, tenLH ;
    private Date ngayNhap;
    private float giaNhap, giaBan,tongTien,thanhTien,tienGiam,phanTramGG;
    private int sl;
    private String tenKH, tenNV;
    private int tichDiem;

    public HoaDonCTEntity() {
    }

    public HoaDonCTEntity(String maCTHD, String maHD, String maSP, String maHang, String maLH, Date ngayLap, String tenSP, String tenHang, String tenLH, Date ngayNhap, float giaNhap, float giaBan, float tongTien, float thanhTien, float tienGiam, float phanTramGG, int sl, String tenKH, String tenNV, int tichDiem) {
        this.maCTHD = maCTHD;
        this.maHD = maHD;
        this.maSP = maSP;
        this.maHang = maHang;
        this.maLH = maLH;
        this.ngayLap = ngayLap;
        this.tenSP = tenSP;
        this.tenHang = tenHang;
        this.tenLH = tenLH;
        this.ngayNhap = ngayNhap;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.tongTien = tongTien;
        this.thanhTien = thanhTien;
        this.tienGiam = tienGiam;
        this.phanTramGG = phanTramGG;
        this.sl = sl;
        this.tenKH = tenKH;
        this.tenNV = tenNV;
        this.tichDiem = tichDiem;
    }

    public float getPhanTramGG() {
        return phanTramGG;
    }

    public void setPhanTramGG(float phanTramGG) {
        this.phanTramGG = phanTramGG;
    }

   

    public float getTienGiam() {
        return tienGiam ;
    }

    public void setTienGiam(float tienGiam) {
        this.tienGiam = tienGiam;
    }

    

    public float getTongTien() {
        return this.tongTien=this.sl*this.giaBan;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public float getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(float thanhTien) {
        this.thanhTien = thanhTien;
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

    public int getTichDiem() {
        return tichDiem;
    }

    public void setTichDiem(int tichDiem) {
        this.tichDiem = tichDiem;
    }

    

    public String getMaCTHD() {
        return maCTHD;
    }

    public void setMaCTHD(String maCTHD) {
        this.maCTHD = maCTHD;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getMaHang() {
        return maHang;
    }

    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }

    public String getMaLH() {
        return maLH;
    }

    public void setMaLH(String maLH) {
        this.maLH = maLH;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public String getTenLH() {
        return tenLH;
    }

    public void setTenLH(String tenLH) {
        this.tenLH = tenLH;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
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

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public void getPhanTramGG(float aFloat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

 
}
