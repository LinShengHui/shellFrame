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
        Map rowData = new HashMap();
        while (rs.next()){
            rowData = new HashMap(columnCount);
            for (int i=1 ; i<= columnCount;i++){
                rowData.put(mataData.getColumnName(i), rs.getObject(i));

            }
            list.add(rowData);
        }
        return (T) list;
    }


}
