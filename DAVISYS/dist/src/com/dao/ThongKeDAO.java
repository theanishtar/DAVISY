/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import com.utils.JdbcHelper;
import java.util.List;

/**
 *
 * @author doanh
 */
public class ThongKeDAO {

    private List<Object[]> getListOfArray(String sql, String[] cols, Object... args) {
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Object[]> getSPBanChay(String day, String month, String year) {
        String sql = "{CALL sp_ThongKeDoanhThu_SP_DMY(?, ?, ?)}";
        String[] cols = {"MASP", "TENSP", "LUOTBAN"};
        return this.getListOfArray(sql, cols, day, month, year);
    }

    public List<Object[]> getSPBanChay(String month, String year) {
        String sql = "{CALL sp_ThongKeDoanhThu_SP_MY(?, ?)}";
        String[] cols = {"MASP", "TENSP", "LUOTBAN"};
        return this.getListOfArray(sql, cols, month, year);
    }

    public List<Object[]> getSPBanChay(String year) {
        String sql = "{CALL sp_ThongKeDoanhThu_SP_Y(?)}";
        String[] cols = {"MASP", "TENSP", "LUOTBAN"};
        return this.getListOfArray(sql, cols, year);
    }
    
        public List<Object[]> getSPBanChayGD(String day, String month, String year) {
        String sql = "{CALL sp_ThongKeDoanhThu_SP_DMY_GD(?, ?, ?)}";
        String[] cols = {"MASP", "TENSP", "LUOTBAN"};
        return this.getListOfArray(sql, cols, day, month, year);
    }

    public List<Object[]> getSPBanChayGD(String month, String year) {
        String sql = "{CALL sp_ThongKeDoanhThu_SP_MY_GD(?, ?)}";
        String[] cols = {"MASP", "TENSP", "LUOTBAN"};
        return this.getListOfArray(sql, cols, month, year);
    }

    public List<Object[]> getSPBanChayGD(String year) {
        String sql = "{CALL sp_ThongKeDoanhThu_SP_Y_GD(?)}";
        String[] cols = {"MASP", "TENSP", "LUOTBAN"};
        return this.getListOfArray(sql, cols, year);
    }

    public List<Object[]> getSPBanChayTL(String year) {
        String sql = "{CALL sp_ThongKeDoanhThu_SPLOAI_Y(?)}";
        String[] cols = {"TENLH", "LUOTBAN"};
        return this.getListOfArray(sql, cols, year);
    }

    public List<Object[]> getDoanhthu(String day, String month, String year) {
        String sql = "{CALL sp_ThongKeDoanhThu_DMY(?, ?, ?)}";
        String[] cols = {"NGAYLAP", "TONGTIENHOMNAY"};
        return this.getListOfArray(sql, cols, day, month, year);
    }

    public List<Object[]> getDoanhthu(String month, String year) {
        String sql = "{CALL sp_ThongKeDoanhThu_MY(?, ?)}";
        String[] cols = {"THANG", "TONGTIEN"};
        return this.getListOfArray(sql, cols, month, year);
    }

    public List<Object[]> getDoanhthu(String year) {
        String sql = "{CALL sp_ThongKeDoanhThu_Y(?)}";
        String[] cols = {"NAM", "TONGTIEN"};
        return this.getListOfArray(sql, cols, year);
    }

    public List<Object[]> getDoanhthuMonthBarChart(String year) {
        String sql = "{CALL sp_ThongKeDoanhThu_M_BarChart(?)}";
        String[] cols = {"THANG", "TONGTIEN"};
        return this.getListOfArray(sql, cols, year);
    }
    
        public List<Object[]> getDoanhthuYearBarChart() {
        String sql = "{CALL sp_ThongKeDoanhThu_Y_BarChart}";
        String[] cols = {"NAM", "TONGTIEN"};
        return this.getListOfArray(sql, cols);
    }

    public List<Object[]> getNhanVienXX() {
        String sql = "{CALL sp_NHANVIENXX}";
        String[] cols = {"TENNV", "SL"};
        return this.getListOfArray(sql, cols);
    }

}
