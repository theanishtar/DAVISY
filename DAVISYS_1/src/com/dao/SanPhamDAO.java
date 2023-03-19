/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.entity.SanPhamEntity;
import com.library.extensisons.Standardization;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.utils.JdbcHelper;
import java.util.Date;

public class SanPhamDAO extends DAVISY<SanPhamEntity, String> {

    final String INSERT_SQL = "INSERT INTO SANPHAM (MASP, TENSP, MALH, MAHANG,SOLUONG,GIANHAP,GIABAN,NGAYNHAP,HINH,MOTA) values(?, ?,?, ?, ?, ?, ?, ?, ?, ?)";

    final String UPDATE_SQL = "UPDATE SANPHAM SET  TENSP = ?, MALH = ?, MAHANG = ?,SOLUONG = ?,GIANHAP = ?,GIABAN = ?,NGAYNHAP = ?,HINH = ?,MOTA = ? WHERE MASP = ?";
    final String UPDATEHINH_SQL = "UPDATE SANPHAM SET HINH = ? WHERE HINH = 'logokhongvien-01' ADN MASP = ?";
    final String DELETE_SQL = "DELETE FROM SANPHAM WHERE MASP = ?";
    final String SELECT_ALL_SQL = "select a.*,b.TENLH,c.TENHANG from SANPHAM a,LOAIHANG b,HANG c WHERE a.MALH=b.MALH AND a.MAHANG=c.MAHANG  ";
    final String SELECT_BY_ID_SQL = "select a.*,b.TENLH,c.TENHANG from SANPHAM a,LOAIHANG b,HANG c WHERE a.MALH=b.MALH AND a.MAHANG=c.MAHANG AND MASP = ?";

    @Override
    public void insert(SanPhamEntity entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaSP(), entity.getTenSP(), entity.getMaLH(), entity.getMaHang(), entity.getSl(), entity.getGiaNhap(), entity.getGiaBan(), entity.getNgayNhap(), entity.getHinh(), entity.getMoTa());
    }

    @Override
    public void update(SanPhamEntity entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getTenSP(), entity.getMaLH(), entity.getMaHang(), entity.getSl(), entity.getGiaNhap(), entity.getGiaBan(), entity.getNgayNhap(), entity.getHinh(), entity.getMoTa(), entity.getMaSP());
    }

    public void updateHinh(SanPhamEntity entity) {
        JdbcHelper.update(UPDATEHINH_SQL, entity.getHinh(), entity.getMaSP());
    }

    public void updateSL(SanPhamEntity entity) {
        String sql = "UPDATE SANPHAM SET SOLUONG = ? WHERE MASP =?";
        JdbcHelper.update(sql, entity.getSl(), entity.getMaSP());
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

    public List<SanPhamEntity> selectByKeyword(String keyword) {
        String sql = "select a.*,b.TENLH,c.TENHANG from SANPHAM a,LOAIHANG b,HANG c WHERE a.MALH=b.MALH AND a.MAHANG=c.MAHANG AND TENSP LIKE ?";
        return this.selectBySql(sql, '%' + keyword + "%");
    }

    public List<SanPhamEntity> selectByMaSP(String keyword) {
        String sql = "select a.*,b.TENLH,c.TENHANG from SANPHAM a,LOAIHANG b,HANG c WHERE a.MALH=b.MALH AND a.MAHANG=c.MAHANG AND MASP LIKE ?";
        return this.selectBySql(sql, '%' + keyword + "%");
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
                entity.setMaHang(rs.getString("MAHANG"));
                entity.setSl(rs.getInt("SOLUONG"));
                entity.setGiaNhap(rs.getFloat("GIANHAP"));
                entity.setGiaBan(rs.getFloat("GIABAN"));
                entity.setNgayNhap(rs.getDate("NGAYNHAP"));
                entity.setHinh(rs.getString("HINH"));
                entity.setMoTa(rs.getString("MOTA"));
                entity.setTenL(rs.getString("TENLH"));
                entity.setTenH(rs.getString("TENHANG"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    

    @Override
    public void delete2(String key1, String key2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
