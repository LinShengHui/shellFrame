package handler.impl;

import handler.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * List集合处理保存
 * Created by Administrator on 2017/12/3.
 */
public class ListMapHandler implements ResultSetHandler {



    public Object handler(ResultSet rs) throws Exception {
        return objList(rs);
    }

    //List多表查询
    private static<T> T objList(ResultSet rs) throws Exception {
        ResultSetMetaData mataData = rs.getMetaData();//数据库列名 id
        int columnCount = mataData.getColumnCount(); //返回此 ResultSet 对象中的列数
        List list = new ArrayList();
        Map rowData = new HashMap();//构建一个Map用来当作一行数据
        while (rs.next()){
            rowData = new HashMap(columnCount);//new以个HashMap,长度未columnCount 就是对象的列速，如 String[] str = new String[5] 一样
            for (int i=1 ; i<= columnCount;i++){//循环遍历，将一行数据保存到Map里面
                rowData.put(mataData.getColumnName(i), rs.getObject(i));//一个一个赋值

            }
            list.add(rowData);//将一行数据保存到List中去
        }
        return (T) list;
    }


}
