package edu.nf.sql;

import handler.impl.ListMapHandler;
import handler.impl.BeanHandler;
import handler.impl.ObjListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2017/12/1.
 */
public class SQLExecutor {

    private static Connection conn;

    public SQLExecutor(Connection conn){
        this.conn=conn;
    }

    //添加学生信息
    public static int zsgObj(String sql,Object...param) throws SQLException {
        check(sql,conn);//检查数据库是否已经关闭
        PreparedStatement ps= conn.prepareStatement(sql);//预编译
        setPreparedStatemen(ps,param);//未占位符赋值
        int row = ps.executeUpdate();//执行语句返回一个int
        return  row;
    }

    //批量删除
    public static boolean deleteObj(String sql,Object...param) throws SQLException {
        check(sql,conn);//检查关闭
        if (param!=null&&param.length>0){//判断传过来值
            for (int i=0;i<param.length;i++){//遍历传过来的值，进行删除
               int z = zsgObj( sql,param[i]);//调用增删改方法，传遍历的ID
            }
            return true;
        }
        return false;

    }



    //查询根据IT查询学生，使用对象保存
    public static<T> T getsingleBean(Class clazz,String sql,Object...param) throws Exception {
        check(sql,conn);//检查关闭
        PreparedStatement ps= conn.prepareStatement(sql);//预编译
        setPreparedStatemen(ps,param);//给占位符赋值
        ResultSet rs = ps.executeQuery();
        //交给对象处理，返回一个自定义类型
        T t = new BeanHandler<T>(clazz).handler(rs);
       return  t;
    }

    //查询全部学生单表，使用List
    public <T> List<T> getList(String sql,Class<?> clz,Object... param) throws Exception {
        check(sql,conn);
        PreparedStatement ps = conn.prepareStatement(sql);
        setPreparedStatemen(ps,param);
        ResultSet rs = ps.executeQuery();
        //交给List处理，返回一个自定义类型
        List<T> list = (List<T>)new ObjListHandler(clz).handler(rs);
        return list;
    }

    //多表查询返回list(map(k,v))
    public static List getSingleList(String sql,Object...param) throws Exception {
        check(sql,conn);
        PreparedStatement ps= conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        //交给List处理，返回一个自定义类型
       List list = (List) new ListMapHandler().handler(rs);
        return list;
    }



    /**
     * 检查关闭
     * @param sql
     * @param conn
     * @throws SQLException
     */
    private static void check(String sql,Connection conn) throws SQLException {
        if(conn==null){
            throw new SQLException("Connection is null");
        }
        if(sql==null){
            conn.close();
            throw new SQLException("sql is null");
        }
    }

    /**
     * 给占位符赋值
     * @param ps
     * @param param
     * @throws SQLException
     */
    private static void setPreparedStatemen(PreparedStatement ps,Object...param) throws SQLException {
        if(param != null){
            for (int i = 0 ; i<param.length;i++ ){
                ps.setObject(i+1,param[i]);
            }
        }

    }


    //insert bean list map

    // 1、参数  conncetion 不同的数据库
    // 2、参数 sql
    // 3、参数 数组

}
