package com.utils;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcHelper {
    public static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//    public static String dburl ="jdbc:sqlserver://localhost:1433;encrypt=false;databaseName=DAVISYS;integratedSecurity=false;";
    public static String dburl = "jdbc:sqlserver://localhost:1433;databaseName=DAVISYS";
    public static String user = "sa";
    public static String pass = "123";
    
    static {
        try {
            Class.forName(driver);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static PreparedStatement getStmt(String sql, Object...args) throws SQLException{
        Connection connection = DriverManager.getConnection(dburl, user, pass);
        PreparedStatement pstmt = null;
        if(sql.trim().startsWith("{")){
           pstmt = connection.prepareCall(sql);
        }else{
            pstmt = connection.prepareStatement(sql);
        }
        for(int i = 0; i < args.length; i++){
            pstmt.setObject(i + 1, args[i]);
        }
        return pstmt;
    }
    
    public static int update(String sql, Object...args){
        try {
            PreparedStatement stmt = getStmt(sql, args);
            try {
                return stmt.executeUpdate();
            } finally{
                stmt.getConnection().close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    } 
    
    public static ResultSet query(String sql, Object...args){
        try {
            PreparedStatement stmt = getStmt(sql, args);
            return stmt.executeQuery();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
     public static Object value(String sql, Object...args){
         try {
             ResultSet rs = query(sql, args);
             if(rs.next()){
                 return rs.getObject(0);
             }
             rs.getStatement().getConnection().close();
         } catch (Exception e) {
             throw new RuntimeException(e);
         }
         return null;
     }
}
