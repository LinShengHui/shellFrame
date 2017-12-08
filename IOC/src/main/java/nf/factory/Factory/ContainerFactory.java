package nf.factory.Factory;

import nf.factory.Definition;
import nf.factory.Util.ScanFileUtil;
import nf.factory.annotation.Component;
import nf.factory.annotation.Scope;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 容器工厂
 * Created by Administrator on 2017/12/7.
 */
public class ContainerFactory {
    //构建单利容器
    private static Map<String, Object> singleton = new HashMap<String, Object>();
    //构建原型容器
    private static Map<String, Definition> prototype = new HashMap<String, Definition>();
    //选择包名
    private static String packageUrl = "";

    //初始化
    public ContainerFactory() throws Exception {
        initProtoType();
        initSingleton();
    }

    //重载一个构造方法
    public ContainerFactory(String url) throws Exception {
        packageUrl = url;//传入url
        initProtoType();
        initSingleton();
    }

    public static void initProtoType() throws Exception {
        //扫描目录下的带有注解
        List<String> list = null;
            list = ScanFileUtil.scan(packageUrl);

            for (String clazz : list) {
                Class cls = Class.forName(clazz);
                //获取column的值
                String columnClassValues = componentValue(cls);
                if(columnClassValues!=null&&columnClassValues.length()>0){
                 //获取scope的值
                 String scope = scopeValue(cls);
                //获取完整类名
                String ClassName = cls.getCanonicalName();
                //构建bean的定义
                Definition def = new Definition();
                def.setId(columnClassValues);
                def.setClassName(ClassName);
                //如果scop有值
                if (scope != null && scope.length() > 0) {
                    def.setScope(scope);
                }
                //保存到容器中
                prototype.put(columnClassValues, def);
               }
            }

    }


    //初始化单例
    private void initSingleton() {
        //遍历prototype里面的值
        for (String key : prototype.keySet()) {
            Definition def = prototype.get(key);
            //判断是否是singleton
            if ("singleton".equals(def.getScope())) {
                //将实例化保存到singleton
                try {
                    singleton.put(key, Class.forName(def.getClassName()).newInstance());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public Object getBean(String name) {
        return  getContainerBean(name);
    }

    public <T> T getBean(String name, Class<T> clazz) {
        return (T) getContainerBean(name);
    }


    private Object getContainerBean(String name) {
        //作用域熟悉
        String scope = prototype.get(name).getScope();
        try {
            return ("singleton".equals(scope)) ? singleton.get(name) : Class.forName(prototype.get(name).getClassName()).newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    //判断component注解
    private static String componentValue(Class cls) {
        if (cls.isAnnotationPresent(Component.class)) {
            //获取注解上的值
            Component columnName = (Component) cls.getAnnotation(Component.class);
            return columnName.value();
        }
        return null;
    }

    //判断scope注解
    private static String scopeValue(Class cls) {
        if (cls.isAnnotationPresent(Scope.class)) {
            //获取注解上的值
            Scope columnName = (Scope) cls.getAnnotation(Scope.class);
            return columnName.value();
        }
        return null;
    }


    public static void main(String[] args) throws Exception {
        initProtoType();
    }

}
