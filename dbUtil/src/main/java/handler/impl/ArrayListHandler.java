package handler.impl;

import edu.nf.annotation.Column;
import handler.ResultSetHandler;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * List集合处理保存
 * Created by Administrator on 2017/12/3.
 */
public class ArrayListHandler implements ResultSetHandler {



    public Object handler(ResultSet rs) throws Exception {
        return objList(rs);
    }

    //List保存
    public static<T> T objList(ResultSet rs) throws Exception {
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


    /**
     * 判断类型
     * @param obj  定义一个obj保存数据
     * @param field 对象数据类型
     * @param rs    结果集
     * @param columnName   数据库字段名
     * @throws SQLException  抛出异常
     * @throws IllegalAccessException
     */
    private static void setFeild(Object obj , Field field , ResultSet rs, String columnName) throws SQLException, IllegalAccessException {
        // field.set(obj,);
        String  className =  field.getType().getSimpleName();
        System.out.println(className);
        Object data = null;
        if("int".equals(className)){//判断是否是int进行赋值
            data = rs.getInt(columnName);
        }else if("String".equals(className)){
            data = rs.getString(columnName);
        }else{
            data = rs.getObject(columnName);
        }

        field.set(obj,data);

    }


    /**
     * 判断对象属性名字是是否于数据库相同
     * @param field 对象属性名字
     * @param columnName 数据库字段名
     * @return
     */
    private static Field checkColumnName(Field field,String columnName){
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
