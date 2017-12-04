package edu.nf.Util;

import edu.nf.annotation.Column;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2017/12/4.
 */
public class checkColumnNameUtil {
    /**
     * 判断对象属性名字是是否于数据库相同
     * @param field 对象属性名字
     * @param columnName 数据库字段名
     * @return
     */
    private static Field checkColumnName(Field field, String columnName){
        String fieldName =  null ;
        //判断是否是有注解
        if (field.isAnnotationPresent(Column.class)) {
            //使用注解上的vlues
            fieldName = field.getAnnotation(Column.class).values();
        }else{
            //不是就使用属性名字
            fieldName = field.getName();
        }
        //返回，结果是数据库字段名，就返回field则使用field否则返回null
        return  fieldName.equals(columnName) ? field:null;
    }
}
