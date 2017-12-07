package handler.impl;

import handler.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Created by Administrator on 2017/12/6.
 */
public class ArrayHandler implements ResultSetHandler {



    public Object handler(ResultSet resultSet) throws Exception {
        return null;
    }

    //保存到数组
    private Object[] ArrayData(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        if (rs.next()){
            Object[] str = new String[columnCount];
            for (int i=0;i<str.length;i++){
                str[i]=rs.getObject(i+1);
            }
            return str;
        }
        return null;

    }
}
