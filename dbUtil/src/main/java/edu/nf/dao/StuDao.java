package edu.nf.dao;


import edu.nf.Util.ReflectData;
import edu.nf.entity.Stu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/30.
 */
public class StuDao {
    private static Connection conn=null;

    //查询学生信息
    public static List findStu(int sno){
         conn = BaseDao.getConnection();
        ResultSet rs = null;//结果集
        Stu s= null;

        try {
            String Sql = "select * from tb_stu";
            rs =  getPreparedStatement(Sql).executeQuery(); //保存结果集
            List list = new ArrayList();
            while(rs.next()){//如果有值，保存到Stu里
                s =  ReflectData.reflect(rs, Stu.class);
                list.add(s);
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * PreparedStatement 预编译
     * @param sql sql语句
     * @return
     */
    public static PreparedStatement getPreparedStatement(String sql){
        try {
            return conn.prepareCall(sql);
        } catch (Exception e) {
            new RuntimeException("Error PreparedStatement");
        }
        return null;
    }







}
