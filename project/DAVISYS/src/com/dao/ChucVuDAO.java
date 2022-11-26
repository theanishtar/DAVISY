/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;
import com.entity.ChucVuEntity;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.utils.JdbcHelper;
public class ChucVuDAO extends DAVISY<ChucVuEntity, String > {
    
    final String INSERT_SQL = "INSERT INTO CHUCVU (MACV, TENCV) values(?, ?)";
    final String UPDATE_SQL = "UPDATE CHUCVU SET TENCV = ? WHERE MACV = CONVERT(int , ?)";
    final String DELETE_SQL = "DELETE FROM CHUCVU WHERE MACV = CONVERT(int , ?)";
    final String SELECT_ALL_SQL = "SELECT * FROM CHUCVU";
    final String SELECT_BY_ID_SQL = "SELECT * FROM CHUCVU WHERE MACV = CONVERT(int , ?)";

    @Override
    public void insert(ChucVuEntity entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaCV(), entity.getTenCV());
    }

    @Override
    public void update(ChucVuEntity entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getTenCV(), entity.getMaCV());
    }

    @Override
    public void delete(String key) {
        JdbcHelper.update(DELETE_SQL, key);
    }

    @Override
    public List<ChucVuEntity> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public ChucVuEntity selectById(String key) {
        List<ChucVuEntity> list = this.selectBySql(SELECT_BY_ID_SQL, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<ChucVuEntity> selectBySql(String sql, Object... args) {
        List<ChucVuEntity> list = new ArrayList<ChucVuEntity>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                ChucVuEntity entity = new ChucVuEntity();
                entity.setMaCV(rs.getInt("MACV"));
                entity.setTenCV(rs.getString("TENCV"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
