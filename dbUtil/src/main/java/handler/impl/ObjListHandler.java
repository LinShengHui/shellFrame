package handler.impl;

import edu.nf.Util.ReflectData;
import handler.ResultSetHandler;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/4.
 */
public class ObjListHandler implements ResultSetHandler {
    private Class<?> clz;
    public ObjListHandler(Class<?> clz){

        this.clz = clz;
    }
    //给一个公开方法
    public Object handler(ResultSet resultSet) throws Exception {
        return toList(clz,resultSet);
    }

    //List单表
    private static <T> List<T> toList(Class<?> clz, ResultSet rs) throws Exception {
        List<T> list = new ArrayList<T>();
        while(rs.next()){//判断rs有没有下一个，有就循环
            Object obj = clz.newInstance();//实例化一个传过来的对象
            obj= ReflectData.objBean(rs, clz);//调用objBean方法 通过反射将rs查出来的值绑定到实体对象中
            list.add((T)obj);//将绑定好的实体对象保存到List中
        }
        return list;//返回一个List
    }

}
