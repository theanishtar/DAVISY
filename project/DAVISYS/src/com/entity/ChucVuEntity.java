/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

public class ChucVuEntity {

    private int maCV;
    private String tenCV, moTa;

    public ChucVuEntity() {
    }

    public ChucVuEntity(int maCV, String tenCV, String moTa) {
        this.maCV = maCV;
        this.tenCV = tenCV;
        this.moTa = moTa;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getMaCV() {
        return maCV;
    }

    public void setMaCV(int maCV) {
        this.maCV = maCV;
    }

    public String getTenCV() {
        return tenCV;
    }

    public void setTenCV(String tenCV) {
        this.tenCV = tenCV;
    }

}
