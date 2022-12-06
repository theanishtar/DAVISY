/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

public class KhachHangEntity {
    private String maKH,hoTen,dienThoai,diaChi;
    private int tichDiem;

    public KhachHangEntity() {
    }

    public KhachHangEntity(String maKH, String hoTen, String dienThoai, String diaChi, int tichDiem) {
        this.maKH = maKH;
        this.hoTen = hoTen;
        this.dienThoai = dienThoai;
        this.diaChi = diaChi;
        this.tichDiem = tichDiem;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getTichDiem() {
        return tichDiem;
    }

    public void setTichDiem(int tichDiem) {
        this.tichDiem = tichDiem;
    }


    
}
