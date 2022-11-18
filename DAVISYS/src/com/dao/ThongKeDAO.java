package com.dao;

import com.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MyPC
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
        String sql = "{CALL sp_ThongKeDoanhThu_SP(?, ?, ?)}";
        String[] cols = {"MASP", "TENSP", "LUOTBAN"};
        return this.getListOfArray(sql, cols, day, month, year);

    }
    
    public List<Object[]> getNhanVienXX() {
        String sql = "{CALL sp_NHANVIENXX}";
        String[] cols = {"TENNV", "SL"};
        return this.getListOfArray(sql, cols);
    }
}
