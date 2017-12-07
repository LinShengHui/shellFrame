package nf.factory.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //运行时一直保留注解
@Target(ElementType.TYPE) //定义在字段上面
public @interface Component {
    String value();
}
