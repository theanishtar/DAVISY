package com.entity;
import java.util.Date;
public class TaiKhoanEntity {
    private String tenDN;
    private int maCV;
    private String tenNV,tenCV,email,matKhau,diaChi,dienThoai;
    private  Date ngaySinh;
    private boolean gioiTInh,trangThai;

    public TaiKhoanEntity() {
    }

    public TaiKhoanEntity(String tenDN, int maCV, String tenNV, String tenCV, String email, String matKhau, String diaChi, String dienThoai, Date ngaySinh, boolean gioiTInh, boolean trangThai) {
        this.tenDN = tenDN;
        this.maCV = maCV;
        this.tenNV = tenNV;
        this.tenCV = tenCV;
        this.email = email;
        this.matKhau = matKhau;
        this.diaChi = diaChi;
        this.dienThoai = dienThoai;
        this.ngaySinh = ngaySinh;
        this.gioiTInh = gioiTInh;
        this.trangThai = trangThai;
    }

    public String getTenCV() {
        return tenCV;
    }

    public void setTenCV(String tenCV) {
        this.tenCV = tenCV;
    }
    


    public String getTenDN() {
        return tenDN;
    }

    public void setTenDN(String tenDN) {
        this.tenDN = tenDN;
    }

    public int getMaCV() {
        return maCV;
    }

    public void setMaCV(int maCV) {
        this.maCV = maCV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public boolean isGioiTInh() {
        return gioiTInh;
    }

    public void setGioiTInh(boolean gioiTInh) {
        this.gioiTInh = gioiTInh;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
    
}