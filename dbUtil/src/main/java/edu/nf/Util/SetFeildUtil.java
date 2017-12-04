package edu.nf.Util;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Administrator on 2017/12/4.
 */
public class SetFeildUtil {
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
}
