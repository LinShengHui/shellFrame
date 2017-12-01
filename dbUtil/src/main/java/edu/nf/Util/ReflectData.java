package edu.nf.Util;

import edu.nf.annotation.Column;

import java.lang.reflect.Field;
import java.sql.ResultSet;

/**
 * Created by Administrator on 2017/11/30.
 */
public class ReflectData {

    public static <T> T reflect(ResultSet rs,Class<?> clazz) throws Exception {
        //获取前台传过来的类
        Class<?> cls = clazz;
        //创建一个实例
        Object instance = cls.newInstance();
        //获取所有的属性
        Field[] fields = cls.getDeclaredFields();

        for (Field f:fields){
            //打开字段
            f.setAccessible(true);
            //获取字段类型
            String typeName = f.getType().getSimpleName();
            //获取注解的值
            String columnName = f.getAnnotation(Column.class).values();
            Object data = null;
            //根据类型，进行赋值
            if("int".equals(typeName)){//判断是否是int进行赋值
                data = rs.getInt(columnName);
            }else if("String".equals(typeName)){//判断是否是String进行赋值
                data = rs.getString(columnName);
            }
            //对class进行强行赋值
            f.set(instance,data);
        }
        return (T) instance;
    }

}
