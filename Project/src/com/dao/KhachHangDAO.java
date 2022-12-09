package com.dao;

import com.entity.KhachHangEntity;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.utils.JdbcHelper;

public class KhachHangDAO extends DAVISY<KhachHangEntity, String> {

    final String INSERT_SQL = "INSERT INTO KHACHHANG (MAKH, HOTEN, DIENTHOAI, DIACHI, TICHDIEM) values(?, ?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE KHACHHANG SET HOTEN = ?, DIENTHOAI = ?, DIACHI = ?, TICHDIEM = ? WHERE MAKH = ?";
    final String UPDATETD_SQL = "UPDATE KHACHHANG SET TICHDIEM = ? WHERE MAKH = ?";
    final String DELETE_SQL = "DELETE FROM KHACHHANG WHERE MAKH = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM KHACHHANG";
    final String SELECT_BY_ID_SQL = "SELECT * FROM KHACHHANG WHERE MAKH = ?";
    final String SELECT_BY_sdt_SQL = "SELECT * FROM KHACHHANG WHERE DIENTHOAI = ?";

    @Override
    public void insert(KhachHangEntity entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaKH(), entity.getHoTen(), entity.getDienThoai(), entity.getDiaChi(), entity.getTichDiem());
    }

    @Override
    public void update(KhachHangEntity entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getHoTen(), entity.getDienThoai(), entity.getDiaChi(), entity.getTichDiem(), entity.getMaKH());
    }

    @Override
    public void delete(String key) {
        JdbcHelper.update(DELETE_SQL, key);
    }

    @Override
    public List<KhachHangEntity> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public KhachHangEntity selectById(String key) {
        List<KhachHangEntity> list = this.selectBySql(SELECT_BY_ID_SQL, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    public KhachHangEntity selectBySDT(String key) {
        List<KhachHangEntity> list = this.selectBySql(SELECT_BY_sdt_SQL, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public List<KhachHangEntity> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM KHACHHANG WHERE HOTEN LIKE ?";
        return this.selectBySql(sql, '%' + keyword + "%");
    }
    public List<KhachHangEntity> selectByDienThoai(String keyword) {
        String sql = "SELECT * FROM KHACHHANG WHERE DIENTHOAI LIKE ?";
        return this.selectBySql(sql, '%' + keyword + "%");
    }
    public void updateTd(KhachHangEntity entity) {
        JdbcHelper.update(UPDATETD_SQL,  entity.getTichDiem(), entity.getMaKH());
    }
    
    @Override
    protected List<KhachHangEntity> selectBySql(String sql, Object... args) {
        List<KhachHangEntity> list = new ArrayList<KhachHangEntity>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                KhachHangEntity entity = new KhachHangEntity();
                entity.setMaKH(rs.getString("MAKH"));
                entity.setHoTen(rs.getString("HOTEN"));
                entity.setDienThoai(rs.getString("DIENTHOAI"));
                entity.setDiaChi(rs.getString("DIACHI"));
                entity.setTichDiem(rs.getInt("TICHDIEM"));
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
