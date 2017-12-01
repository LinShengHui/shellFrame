package edu.nf.dao;

import java.sql.*;

/**
 * Created by Administrator on 2017/12/1.
 */
public class BaseDao {

    /**
     * 加载驱动
     */
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        String url = "jdbc:mysql://localhost:3306/study?"
                + "user=root&password=123456&useUnicode=true&characterEncoding=UTF8";
        try {
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeDb (Connection conn, Statement st, ResultSet rs){
        if(rs!=null){ try {rs.close();} catch (SQLException e) {
            e.printStackTrace();}}
        if(st!=null){ try {st.close();} catch (SQLException e) {
            e.printStackTrace();}}
        if(conn!=null){ try {conn.close();} catch (SQLException e) {
            e.printStackTrace();}}
    }

    public static void main(String[] args) {
        System.out.println(getConnection());
    }
}
