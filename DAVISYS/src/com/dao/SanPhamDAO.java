/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.entity.SanPhamEntity;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.utils.JdbcHelper;

public class SanPhamDAO extends DAVISY<SanPhamEntity, String> {

    final String INSERT_SQL = "INSERT INTO SANPHAM (MASP, TENSP, MALH, MAHANG,GIANHAP,GIABAN,NGAYNHAP,HINH,MOTA) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

    final String UPDATE_SQL = "UPDATE SANPHAM SET  TENSP = ?, MALH = ?, MAHANG = ?,GIANHAP = ?,GIABAN = ?,NGAYNHAP = ?,HINH = ?,MOTA = ? WHERE MASP = ?";
    final String DELETE_SQL = "DELETE FROM SANPHAM WHERE MASP = ?";
    final String SELECT_ALL_SQL = "SELECT SANPHAM.MASP, SANPHAM.TENSP, SANPHAM.MALH, LOAIHANG.TENLH,SANPHAM.MAHANG,HANG.TEHANG,SANPHAM.GIANHAP,SANPHAM.GIABAN,SANPHAM.NGAYNHAP,SANPHAM.HINH,SANPHAM.MOTA FROM SANPHAM, LOAIHANG,HANG WHERE SANPHAM.MALH =LOAIHANG.MALH AND SANPHAM.MAHANG=HANG.MAHANG";
    final String SELECT_BY_ID_SQL = "SELECT SANPHAM.TENSP, SANPHAM.MALH, LOAIHANG.TENLH,SANPHAM.MAHANG,HANG.TEHANG,SANPHAM.GIANHAP,SANPHAM.GIABAN,SANPHAM.NGAYNHAP,SANPHAM.HINH,SANPHAM.MOTA FROM SANPHAM, LOAIHANG,HANG WHERE SANPHAM.MALH =LOAIHANG.MALH AND SANPHAM.MAHANG=HANG.MAHANG AND SANPHAM.MASP = ?";

    @Override
    public void insert(SanPhamEntity entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaSP(), entity.getTenSP(), entity.getMaLH(), entity.getMaHang(), entity.getGiaNhap(), entity.getGiaBan(), entity.getNgayNhap(), entity.getHinh(), entity.getMoTa());
    }

    @Override
    public void update(SanPhamEntity entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getTenSP(), entity.getMaLH(), entity.getMaHang(), entity.getGiaNhap(), entity.getGiaBan(), entity.getNgayNhap(), entity.getHinh(), entity.getMoTa(), entity.getMaSP());
    }

    @Override
    public void delete(String key) {
        JdbcHelper.update(DELETE_SQL, key);
    }

    @Override
    public List<SanPhamEntity> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public SanPhamEntity selectById(String key) {
        List<SanPhamEntity> list = this.selectBySql(SELECT_BY_ID_SQL, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<SanPhamEntity> selectBySql(String sql, Object... args) {
        List<SanPhamEntity> list = new ArrayList<SanPhamEntity>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                SanPhamEntity entity = new SanPhamEntity();
                entity.setMaSP(rs.getString("MASP"));
                entity.setTenSP(rs.getString("TENSP"));
                entity.setMaLH(rs.getString("MALH"));
                entity.setTenLH(rs.getString("TENLH"));
                entity.setMaHang(rs.getString("MAHANG"));
                entity.setTenHang(rs.getString("TEHANG"));
                entity.setGiaNhap(rs.getFloat("GIANHAP"));
                entity.setGiaBan(rs.getFloat("GIABAN"));
                entity.setNgayNhap(rs.getDate("NGAYNHAP"));
                entity.setHinh(rs.getString("HINH"));
                entity.setMoTa(rs.getString("MOTA"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
