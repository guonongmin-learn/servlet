package cn.cos18.bookmgr.dao;

import java.sql.*;

class BaseDao {

    private Connection conn = null;
    PreparedStatement ps = null;

    BaseDao(){
        getConnection();
    }

    public void getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url= "jdbc:mysql://localhost:3306/bookmgr";
        try {
            conn = DriverManager.getConnection(url, "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    ResultSet query(String sql, Object... objects){
        if (conn == null){
            System.out.println("Connection is null");
        }
        try {
            ps = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0; i< objects.length; i++){
            try {
                ps.setObject(i + 1,objects[i]);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try {
            return ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean excute(String sql , Object... objects){
        try {
            ps = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0; i< objects.length; i++){
            try {
                ps.setObject(i + 1,objects[i]);
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }

        try {
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;

    }
}
