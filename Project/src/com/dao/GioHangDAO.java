/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.entity.GioHangEntity;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.utils.JdbcHelper;

public class GioHangDAO extends DAVISY<GioHangEntity, String> {

    final String INSERT_SQL = "INSERT INTO GIOHANG (MAGH, MAKH, TENDN) values(?, ?,?)";
    final String UPDATE_SQL = "UPDATE GIOHANG SET  MAKH = ?, TENDN = ? WHERE MAGH = ?";
    final String DELETE_SQL = "DELETE FROM GIOHANG WHERE MAGH = ?";
    final String DELETETENDN_SQL = "DELETE FROM GIOHANG WHERE TENDN = ?";
    final String SELECT_ALL_SQL = "SELECT *FROM GIOHANG";
    final String SELECT_BY_ID_SQL = "SELECT *FROM GIOHANG WHERE MAGH = ?";
    final String SELECT_BY_NAME_SQL = "SELECT *FROM GIOHANG WHERE MAKH = ?";

    @Override
    public void insert(GioHangEntity entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaGH(), entity.getMaKH(), entity.getTenDN());
    }

    @Override
    public void update(GioHangEntity entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getMaKH(), entity.getTenDN(), entity.getMaGH());
    }

    @Override
    public void delete(String key) {
        JdbcHelper.update(DELETE_SQL, key);
    }

    public void deleteTen(String key) {
        JdbcHelper.update(DELETETENDN_SQL, key);
    }

    @Override
    public List<GioHangEntity> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public GioHangEntity selectById(String key) {
        List<GioHangEntity> list = this.selectBySql(SELECT_BY_ID_SQL, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
//     public GioHangEntity selectByName(String key) {
//        List<GioHangEntity> list = this.selectBySql(SELECT_BY_NAME_SQL, key);
//        if (list.isEmpty()) {
//            return null;
//        }
//        return list.get(0);
//    }

    public List<GioHangEntity> selectByName(String key) {
        return this.selectBySql(SELECT_BY_NAME_SQL, key);
    }

    @Override
    protected List<GioHangEntity> selectBySql(String sql, Object... args) {
        List<GioHangEntity> list = new ArrayList<GioHangEntity>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                GioHangEntity entity = new GioHangEntity();
                entity.setMaGH(rs.getString("MAGH"));
                entity.setMaKH(rs.getString("MAKH"));
                entity.setTenDN(rs.getString("TENDN"));
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
    }
}
