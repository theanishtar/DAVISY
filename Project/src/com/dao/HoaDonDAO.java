/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.entity.HoaDonEntity;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.utils.JdbcHelper;

public class HoaDonDAO extends DAVISY<HoaDonEntity, String> {

    final String INSERT_SQL = "INSERT INTO HOADON (MAHD, TENDN, MAKH, MAGH,NGAYLAP,TONGTIEN,PHANTRAMGG,TRUTIENTICHDIEM,THANHTIEN) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE HOADON SET TENDN = ?, MAKH = ?, MAGH = ?,NGAYLAP = ?,TONGTIEN= ?,PHANTRAMGG= ?,TRUTIENTICHDIEM= ?,THANHTIEN= ? WHERE MAHD = ?";
    final String DELETE_SQL = "DELETE FROM HOADON WHERE MAHD = ?";
    final String DELETETENDN_SQL = "DELETE FROM HOADON WHERE TENDN = ?";
    final String UPDATETT_SQL = "UPDATE HOADON SET PHANTRAMGG= ?, TRUTIENTICHDIEM = ?, THANHTIEN= ? WHERE MAHD = ?";
    final String SELECT_ALL_SQL = "SELECT a.*,b.HOTEN,b.MAKH,c.TENNV  FROM HOADON a,KHACHHANG b ,TAIKHOAN c WHERE a.MAKH =b.MAKH AND a.TENDN =c.TENDN";
    final String SELECT_BY_ID_SQL = "SELECT a.*,b.HOTEN,b.MAKH,c.TENNV  FROM HOADON a,KHACHHANG b ,TAIKHOAN c WHERE a.MAKH =b.MAKH AND a.TENDN =c.TENDN AND a.MAHD = ?";
    final String SELECT_BY_NAME_SQL = "SELECT a.*,b.HOTEN,b.MAKH,c.TENNV  FROM HOADON a,KHACHHANG b ,TAIKHOAN c WHERE a.MAKH =b.MAKH AND a.TENDN =c.TENDN AND a.TENDN= ?";

    @Override
    public void insert(HoaDonEntity entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaHD(), entity.getTenDN(), entity.getMaKH(), entity.getMaGH(), entity.getNgayLap(), entity.getTongTien(), entity.getPhanTramGG(),
                entity.getTichDiem(), entity.getThanhTien());
    }

    @Override
    public void update(HoaDonEntity entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getTenDN(), entity.getMaKH(), entity.getMaGH(), entity.getNgayLap(), entity.getTongTien(), entity.getPhanTramGG(),
                entity.getTichDiem(), entity.getThanhTien(), entity.getMaHD());
    }

    public void updateTT(HoaDonEntity entity) {
        JdbcHelper.update(UPDATETT_SQL, entity.getPhanTramGG(), entity.getTichDiem(), entity.getThanhTien(), entity.getMaHD());
    }

    @Override
    public void delete(String key) {
        JdbcHelper.update(DELETE_SQL, key);
    }

    public void deleteTen(String key) {
        JdbcHelper.update(DELETETENDN_SQL, key);
    }

    @Override
    public List<HoaDonEntity> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public HoaDonEntity selectById(String key) {
        List<HoaDonEntity> list = this.selectBySql(SELECT_BY_ID_SQL, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public List<HoaDonEntity> selectByKeyword(String keyword) {
        String sql = "SELECT a.*,b.HOTEN,b.MAKH,c.TENNV  FROM HOADON a,KHACHHANG b ,TAIKHOAN c WHERE a.MAKH =b.MAKH AND a.TENDN =c.TENDN AND b.HOTEN LIKE ?";
        return this.selectBySql(sql, '%' + keyword + "%");
    }

    public HoaDonEntity selectByName(String key) {
        List<HoaDonEntity> list = this.selectBySql(SELECT_BY_NAME_SQL, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    
//    public List<HoaDonEntity> selectByName(String key) {
//         return this.selectBySql(SELECT_BY_NAME_SQL, key);
////        List<HoaDonEntity> list = this.selectBySql(SELECT_BY_NAME_SQL, key);
////        if (list.isEmpty()) {
////            return null;
////        }
////        return list.get(0);
//    }

    @Override
    protected List<HoaDonEntity> selectBySql(String sql, Object... args) {
        List<HoaDonEntity> list = new ArrayList<HoaDonEntity>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                HoaDonEntity entity = new HoaDonEntity();
                entity.setMaHD(rs.getString("MAHD"));
                entity.setMaKH(rs.getString("MAKH"));
                entity.setTenKH(rs.getString("HOTEN"));
                entity.setTenNV(rs.getString("TENNV"));
                entity.setNgayLap(rs.getDate("NGAYLAP"));
                entity.setPhanTramGG(rs.getInt("PHANTRAMGG"));
                entity.setTongTien(rs.getFloat("TONGTIEN"));
                entity.setTichDiem(rs.getInt("TRUTIENTICHDIEM"));
                entity.setThanhTien(rs.getFloat("THANHTIEN"));
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
