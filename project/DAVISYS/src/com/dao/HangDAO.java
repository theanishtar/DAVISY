/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.entity.HangEntity;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.utils.JdbcHelper;

public class HangDAO extends DAVISY<HangEntity, String> {

    final String INSERT_SQL = "INSERT INTO HANG (MAHANG, TENHANG) values(?, ?)";
    final String UPDATE_SQL = "UPDATE HANG SET TENHANG = ? WHERE MAHANG = ?";
    final String DELETE_SQL = "DELETE FROM HANG WHERE MAHANG = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM HANG";
    final String SELECT_BY_ID_SQL = "SELECT * FROM HANG WHERE MAHANG = ?";

    @Override
    public void insert(HangEntity entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaHang(), entity.getTenHang());
    }

    @Override
    public void update(HangEntity entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getTenHang(), entity.getMaHang());
    }

    @Override
    public void delete(String key) {
        JdbcHelper.update(DELETE_SQL, key);
    }

    @Override
    public List<HangEntity> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public HangEntity selectById(String key) {
        List<HangEntity> list = this.selectBySql(SELECT_BY_ID_SQL, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
public List<HangEntity> selectByKeyword(String keyword){
        String sql = "SELECT * FROM HANG WHERE TENHANG LIKE ?";
        return this.selectBySql(sql, '%'+keyword+"%");
    }
    @Override
    protected List<HangEntity> selectBySql(String sql, Object... args) {
        List<HangEntity> list = new ArrayList<HangEntity>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                HangEntity entity = new HangEntity();
                entity.setMaHang(rs.getString("MAHANG"));
                entity.setTenHang(rs.getString("TENHANG"));
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
