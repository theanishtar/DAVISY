/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.entity.LoaiHangEntity;
import com.entity.SanPhamEntity;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.utils.JdbcHelper;

public class LoaiHangDAO extends DAVISY<LoaiHangEntity, String> {

    final String INSERT_SQL = "INSERT INTO LOAIHANG (MALH, TENLH) values(?, ?)";
    final String UPDATE_SQL = "UPDATE LOAIHANG SET TENLH = ? WHERE MALH = ?";
    final String DELETE_SQL = "DELETE FROM LOAIHANG WHERE MALH = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM LOAIHANG";
    final String SELECT_BY_ID_SQL = "SELECT * FROM LOAIHANG WHERE MALH = ?";

    @Override
    public void insert(LoaiHangEntity entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaLH(), entity.getTenLH());
    }

    @Override
    public void update(LoaiHangEntity entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getTenLH(), entity.getMaLH());
    }

    @Override
    public void delete(String key) {
        JdbcHelper.update(DELETE_SQL, key);
    }

    @Override
    public List<LoaiHangEntity> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public LoaiHangEntity selectById(String key) {
        List<LoaiHangEntity> list = this.selectBySql(SELECT_BY_ID_SQL, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public List<LoaiHangEntity> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM LOAIHANG WHERE TENLH LIKE ?";
        return this.selectBySql(sql, '%' + keyword + "%");
    }

    @Override
    protected List<LoaiHangEntity> selectBySql(String sql, Object... args) {
        List<LoaiHangEntity> list = new ArrayList<LoaiHangEntity>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                LoaiHangEntity entity = new LoaiHangEntity();
                entity.setMaLH(rs.getString("MALH"));
                entity.setTenLH(rs.getString("TENLH"));
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
