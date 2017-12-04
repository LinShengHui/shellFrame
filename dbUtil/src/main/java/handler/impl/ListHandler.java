package handler.impl;

import edu.nf.Util.ReflectData;
import handler.ResultSetHandler;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/4.
 */
public class ListHandler implements ResultSetHandler {
    private Class<?> clz;
    public ListHandler(Class<?> clz){
        this.clz = clz;
    }
    public Object handler(ResultSet resultSet) throws Exception {
        return toList(clz,resultSet);
    }

    public static <T> List<T> toList(Class<?> clz, ResultSet rs) throws Exception {
        List<T> list = new ArrayList<T>();
        while(rs.next()){
            Object obj = clz.newInstance();
            obj= ReflectData.objBean(rs, clz);
            list.add((T)obj);
        }
        return list;
    }

}
