package edu.nf.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2017/11/30.
 */
@Retention(RetentionPolicy.RUNTIME)//运行时一直保存注解
@Target(ElementType.FIELD)//定义在字段上面
public @interface Column {
    String values();
}
