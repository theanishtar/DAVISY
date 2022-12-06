/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.entity.HoaDonCTEntity;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.utils.JdbcHelper;

public class HoaDonCTDAO extends DAVISY<HoaDonCTEntity, String> {

    final String INSERT_SQL = "INSERT INTO CHITIETHOADON (MACTHD,MAHD, MASP, MAHANG, MALH, NGAYLAP,TENSP,TENHANG,TENLH,NGAYNHAP,GIANHAP,GIABAN,SOLUONG) values(?, ?, ?, ?, ?,?, ?, ?, ?, ?,?, ?, ?)";
    final String UPDATE_SQL = "UPDATE CHITIETHOADON SET  MASP = ?, MAHANG = ?, MALH = ?,TENSP = ?,TENHANG = ?,TENLH = ?,SOLUONG = CONVERT(int, ?) WHERE MACTHD = ?";
    final String DELETE_SQL = "DELETE FROM CHITIETHOADON WHERE MAHD = ?";
    final String SELECT_ALL_SQL = "SELECT a.*,c.HOTEN,d.TENNV,c.TICHDIEM,b.PHANTRAMGG FROM CHITIETHOADON a,HOADON b ,KHACHHANG c ,TAIKHOAN d WHERE a.MAHD= b.MAHD AND b.MAKH=c.MAKH AND b.TENDN =d.TENDN";
    final String SELECT_BY_ID_SQL = "SELECT a.*,c.HOTEN,d.TENNV,c.TICHDIEM,b.PHANTRAMGG FROM CHITIETHOADON a,HOADON b ,KHACHHANG c ,TAIKHOAN d WHERE a.MAHD= b.MAHD AND b.MAKH=c.MAKH AND b.TENDN =d.TENDN AND a.MAHD = ?";

    @Override
    public void insert(HoaDonCTEntity entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaCTHD(),entity.getMaHD(), entity.getMaSP(), entity.getMaHang(), entity.getMaLH(), entity.getNgayLap(), entity.getTenSP(),
                entity.getTenHang(), entity.getTenLH(), entity.getNgayNhap(), entity.getGiaNhap(), entity.getGiaBan(), entity.getSl());
    }

    @Override
    public void update(HoaDonCTEntity entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getMaSP(), entity.getMaHang(), entity.getMaLH(), entity.getTenSP(),entity.getTenHang(), entity.getTenLH(), entity.getSl(),entity.getMaCTHD());
    }

    @Override
    public void delete(String key) {
        JdbcHelper.update(DELETE_SQL, key);
    }

    @Override
    public List<HoaDonCTEntity> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public HoaDonCTEntity selectById(String key) {
        List<HoaDonCTEntity> list = this.selectBySql(SELECT_BY_ID_SQL, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<HoaDonCTEntity> selectBySql(String sql, Object... args) {
        List<HoaDonCTEntity> list = new ArrayList<HoaDonCTEntity>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                HoaDonCTEntity entity = new HoaDonCTEntity();
                entity.setMaCTHD(rs.getString("MACTHD"));
                entity.setMaHD(rs.getString("MAHD"));
                entity.setMaSP(rs.getString("MASP"));
                entity.setMaHang(rs.getString("MAHANG"));
                entity.setMaLH(rs.getString("MALH"));
                entity.setNgayLap(rs.getDate("NGAYLAP"));
                entity.setTenSP(rs.getString("TENSP"));
                entity.setTenHang(rs.getString("TENHANG"));
                entity.setTenLH(rs.getString("TENLH"));
                entity.setNgayNhap(rs.getDate("NGAYNHAP"));
                entity.setGiaNhap(rs.getFloat("GIANHAP"));
                entity.setGiaBan(rs.getFloat("GIABAN"));
                entity.setSl(rs.getInt("SOLUONG"));
                entity.setTenKH(rs.getString("HOTEN"));
                entity.setTenNV(rs.getString("TENNV"));
                entity.setTichDiem(rs.getInt("TICHDIEM"));
                entity.setPhanTramGG(rs.getFloat("PHANTRAMGG"));
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
