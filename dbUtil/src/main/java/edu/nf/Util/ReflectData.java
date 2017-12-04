package edu.nf.Util;

import edu.nf.annotation.Column;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

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

    //对象保存
    public static<T> T objBean(ResultSet rs,Class<?> clazz) throws Exception {
        Object obj  = clazz.newInstance();
        ResultSetMetaData mataData = rs.getMetaData();//数据库列名 id
        //获取所有的属性
        Field[] fields = clazz.getDeclaredFields();
        //以数据库字段未主进行循环判断
        for (int i = 1 ;i<mataData.getColumnCount()+1;i++){
            //去到数据库字段名字
           String columnName = mataData.getColumnName(i);
           //遍历对象属性
            for (Field field : fields){
                //打开开关
                field.setAccessible(true);
                //检查属性名是否与字段名匹配，不匹配返回null
                Field  f = checkColumnName(field,columnName);
                if(f != null){//不未空的情况下，根据类型设置相对于的值
                   setFeild(obj,field,rs,columnName);
                }
            }

        }
        return (T) obj;
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
    private static void setFeild(Object obj ,Field field ,ResultSet rs,String columnName) throws SQLException, IllegalAccessException {
       // field.set(obj,);
      String  className =  field.getType().getSimpleName();
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
