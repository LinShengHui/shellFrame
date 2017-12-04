package handler.impl;

import edu.nf.Util.ReflectData;
import handler.ResultSetHandler;

import java.sql.ResultSet;

/**
 * 对象处理
 * Created by Administrator on 2017/12/1.
 */
public class BeanHandler<T> implements ResultSetHandler {
    private Class clazz = null;

    public BeanHandler(Class clazz){
        this.clazz = clazz;
    }

    public T handler(ResultSet rs) throws Exception {
        return rs.next()? (T) ReflectData.objBean(rs, clazz) :null;
    }
}
