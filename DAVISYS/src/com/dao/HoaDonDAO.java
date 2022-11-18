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

    final String INSERT_SQL = "INSERT INTO HOADON (MAHD, TENDN, MAKH, MAGH,NGAYLAP) values(?, ?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE HOADON SET TENDN = ?, MAKH = ?, MAGH = ?,NGAYLAP = ? WHERE MAHD = ?";
    final String DELETE_SQL = "DELETE FROM HOADON WHERE MAHD = ?";
    final String SELECT_ALL_SQL = "SELECT *FROM HOADON";
    final String SELECT_BY_ID_SQL = "SELECT *FROM HOADON WHERE MAHD = ?";

    @Override
    public void insert(HoaDonEntity entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaHD(), entity.getTenDN(), entity.getMaKH(), entity.getMaGH(), entity.getNgayLap());
    }

    @Override
    public void update(HoaDonEntity entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getTenDN(), entity.getMaKH(), entity.getMaGH(), entity.getNgayLap(), entity.getMaHD());
    }

    @Override
    public void delete(String key) {
        JdbcHelper.update(DELETE_SQL, key);
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

    @Override
    protected List<HoaDonEntity> selectBySql(String sql, Object... args) {
        List<HoaDonEntity> list = new ArrayList<HoaDonEntity>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                HoaDonEntity entity = new HoaDonEntity();
                entity.setMaHD(rs.getString("MAHD"));
                entity.setTenDN(rs.getString("TENDN"));
                entity.setMaKH(rs.getString("MAKH"));
                entity.setMaGH(rs.getString("MAGH"));
                entity.setNgayLap(rs.getDate("NGAYLAP"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
