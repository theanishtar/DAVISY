/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;
import com.entity.GioHangTamEntity;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.utils.JdbcHelper;
public class GioHangTamDAO extends DAVISY<GioHangTamEntity, String> {
    final String INSERT_SQL = "INSERT INTO GIOHANGTAM (MAGH, MASP) values(?, ?)";
    final String UPDATE_SQL = "UPDATE GIOHANGTAM SET  MASP = ? WHERE MAGH = ?";
    final String DELETE_SQL = "DELETE FROM GIOHANGTAM WHERE MAGH = ?";
    final String SELECT_ALL_SQL = "SELECT *FROM GIOHANGTAM";
    final String SELECT_BY_ID_SQL = "SELECT *FROM GIOHANGTAM WHERE MAGH = ?";

    @Override
    public void insert(GioHangTamEntity entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaGH(),entity.getMaSP());
    }

    @Override
    public void update(GioHangTamEntity entity) {
        JdbcHelper.update(UPDATE_SQL,entity.getMaSP(), entity.getMaGH());
    }

    @Override
    public void delete(String key) {
        JdbcHelper.update(DELETE_SQL, key);
    }

    @Override
    public List<GioHangTamEntity> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public GioHangTamEntity selectById(String key) {
        List<GioHangTamEntity> list = this.selectBySql(SELECT_BY_ID_SQL, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<GioHangTamEntity> selectBySql(String sql, Object... args) {
        List<GioHangTamEntity> list = new ArrayList<GioHangTamEntity>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                GioHangTamEntity entity = new GioHangTamEntity();
                entity.setMaGH(rs.getString("MAGH"));
                entity.setMaSP(rs.getString("MASP"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
