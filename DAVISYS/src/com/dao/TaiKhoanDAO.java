/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.entity.SanPhamEntity;
import com.entity.TaiKhoanEntity;
import com.library.extensisons.Standardization;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.utils.JdbcHelper;
import java.util.Date;

public class TaiKhoanDAO extends DAVISY<TaiKhoanEntity, String> {

    final String INSERT_SQL = "INSERT INTO TAIKHOAN (TENDN, MACV, TENNV, EMAIL, MATKHAU,DIACHI,DIENTHOAI,NGAYSINH,GIOITINH,TRANGTHAI) values(?, ?, ?, ?, ?,?, ?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE TAIKHOAN SET MACV = ?, TENNV = ?, EMAIL = ?, MATKHAU = ?,DIACHI = ? ,DIENTHOAI= ?,NGAYSINH = ?,GIOITINH = ? ,TRANGTHAI= ? WHERE TENDN = ?";
    final String DELETE_SQL = "DELETE FROM TAIKHOAN WHERE TENDN = ?";
    final String SELECT_ALL_SQL = "SELECT TENDN,TENNV,TAIKHOAN.MACV,CHUCVU.TENCV,EMAIL,MATKHAU,DIACHI,DIENTHOAI,NGAYSINH,GIOITINH,TRANGTHAI FROM TAIKHOAN ,CHUCVU WHERE TAIKHOAN.MACV = CHUCVU.MACV";
    final String SELECT_BY_ID_SQL = "SELECT TENDN,TENNV,TAIKHOAN.MACV,CHUCVU.TENCV,EMAIL,MATKHAU,DIACHI,DIENTHOAI,NGAYSINH,GIOITINH,TRANGTHAI FROM TAIKHOAN ,CHUCVU WHERE TAIKHOAN.MACV = CHUCVU.MACV AND TENDN = ?";
    final String UPDATE_NEWPW_SQL = "UPDATE TAIKHOAN SET MATKHAU = ? WHERE EMAIL = ?";

    public void updateNewPW(String mk, String email) {
        JdbcHelper.update(UPDATE_NEWPW_SQL, mk, email);
    }

    @Override
    public void insert(TaiKhoanEntity entity) {
        JdbcHelper.update(INSERT_SQL, entity.getTenDN(), entity.getMaCV(), entity.getTenNV(), entity.getEmail(), entity.getMatKhau(), entity.getDiaChi(), entity.getDienThoai(), entity.getNgaySinh(), entity.isGioiTInh(), entity.isTrangThai());
    }

    @Override
    public void update(TaiKhoanEntity entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getMaCV(), entity.getTenNV(), entity.getEmail(), entity.getMatKhau(), entity.getDiaChi(), entity.getDienThoai(), entity.getNgaySinh(), entity.isGioiTInh(), entity.isTrangThai(), entity.getTenDN());
    }

    @Override
    public void delete(String key) {
        JdbcHelper.update(DELETE_SQL, key);
    }

    @Override
    public List<TaiKhoanEntity> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public TaiKhoanEntity selectById(String key) {
        List<TaiKhoanEntity> list = this.selectBySql(SELECT_BY_ID_SQL, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public List<TaiKhoanEntity> selectByKeyword(String keyword) {
        String sql = "SELECT TENDN,TENNV,TAIKHOAN.MACV,CHUCVU.TENCV,EMAIL,MATKHAU,DIACHI,DIENTHOAI,NGAYSINH,GIOITINH,TRANGTHAI FROM TAIKHOAN ,CHUCVU WHERE TAIKHOAN.MACV = CHUCVU.MACV AND TENNV LIKE ?";
        return this.selectBySql(sql, '%' + keyword + "%");
    }

    @Override
    protected List<TaiKhoanEntity> selectBySql(String sql, Object... args) {
        List<TaiKhoanEntity> list = new ArrayList<TaiKhoanEntity>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                TaiKhoanEntity entity = new TaiKhoanEntity();
                entity.setTenDN(rs.getString("TENDN"));
                entity.setTenNV(rs.getString("TENNV"));
                entity.setMaCV(rs.getInt("MACV"));
                entity.setTenCV(rs.getString("TENCV"));
                entity.setEmail(rs.getString("EMAIL"));
                entity.setMatKhau(rs.getString("MATKHAU"));
                entity.setDiaChi(rs.getString("DIACHI"));
                entity.setDienThoai(rs.getString("DIENTHOAI"));
                entity.setNgaySinh(rs.getDate("NGAYSINH"));
                entity.setGioiTInh(rs.getBoolean("GIOITINH"));
                entity.setTrangThai(rs.getBoolean("TRANGTHAI"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String validate(String tenNV) {
        Standardization std = new Standardization();
        if (!std.hoTen(tenNV).equalsIgnoreCase(tenNV) || tenNV.equals("")) {
            return "Tên nhân viên không hợp lệ!";
        }
        return "";
    }

    @Override
    public void delete2(String key1, String key2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
