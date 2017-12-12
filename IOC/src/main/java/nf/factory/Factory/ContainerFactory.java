package nf.factory.Factory;

import nf.factory.Definition;
import nf.factory.Util.ScanFileUtil;
import nf.factory.annotation.Component;
import nf.factory.annotation.Inject;
import nf.factory.annotation.Scope;

import java.lang.reflect.Field;
import java.util.Collection;
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
        //注入单例
        injectsingleton();
    }

    //注入
    private void injectsingleton(){
        Collection collection = singleton.values();
        for(Object bean : collection){
            System.out.println(bean);
            InjectSericerHandler.setInject(bean.getClass(),bean);
        }
    }

    //重载一个构造方法
    public ContainerFactory(String url) throws Exception {
        packageUrl = url;//传入url
        initProtoType();
        initSingleton();
    }

    //初始化原型容器
    public static void initProtoType() {
        //扫描目录下的带有注解
        List<String> list = null;
        list = ScanFileUtil.scan(packageUrl);
        for (String clazz : list) {
            Class cls = manageGainClass(clazz);
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
                def.setScope(scope);

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

    public static Object getBean(String name) {
        return  getContainerBean(name);
    }

    public static  <T> T getBean(String name, Class<T> clazz) {
        return (T) getContainerBean(name);
    }

    //注入
    private static Object getContainerBean(String name) {
        //作用域属性
        Object obj = null;
        // String scope = prototype.get(name).getScope();
        if(singleton.containsKey(name)){
            obj = singleton.get(name);
        }else if(prototype.containsKey(name)){
            obj = manageCenterBen(prototype.get(name).getClassName());
            InjectSericerHandler.setInject(obj.getClass(),obj);
        }

        // obj = ("singleton".equals(scope)) ? singleton.get(name) : centerBen(prototype.get(name).getClassName());
        //进行注入
        /*if(!"singleton".equals(scope)){

        }*/
        //Csanmethod(obj);
        return obj;

    }


    //判断component注解
    private static String componentValue(Class cls) {

        if(cls.isAnnotationPresent(Component.class)){
            Component component= (Component) cls.getAnnotation(Component.class);
            String componentName =  component.value();
            if(componentName==null||componentName.length()<=0){
                componentName=toLowerCaseFirstOne(cls.getSimpleName());
                System.out.println(componentName);
                return componentName;
            }
            return componentName;
        }
        return null;
    }

    //判断scope注解
    private static String scopeValue(Class cls) {
        if (cls.isAnnotationPresent(Scope.class)) {
            //获取注解上的值
            Scope scopeName = (Scope) cls.getAnnotation(Scope.class);
            return scopeName.value();
        }
        return "singleton";
    }



    //注入
    private static void Csanmethod(Object obj) {
        //获取对象的Class
        Class cls = obj.getClass();
        //获取所有的熟悉
        Field[] fields = cls.getDeclaredFields();
        for (Field f:fields) {//遍历熟悉
            if(f.isAnnotationPresent(Inject.class)){//判断有没有这个注解
                //打开私有方法
                f.setAccessible(true);
                //获取字段上的值
                String methodName=f.getAnnotation(Inject.class).value();
                //判断类型进行注入
                if(prototype.containsKey(methodName)){
                    Object beanData = null;
                    //创建一个实例
                    beanData =manageCenterBen(prototype.get(methodName).getClassName());
                    //注入
                    manageSetBenException(f,obj,beanData);
                }else if (singleton.containsKey(methodName)){
                    manageSetBenException(f,obj,singleton.get(methodName));

                }
            }
        }
    }




    //————————————————————————异常处理————————————————————————————————//

    //处理给字段赋值异常
    public static  void manageSetBenException(Field file,Object bean,Object beanData){
        try {
            file.set(bean,beanData);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    //处理newInstance带来在异常
    public static Object manageCenterBen(String full){
        try {
            return  Class.forName(full).newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    //给ClassFromName抛异常
    public static Class manageGainClass(String className){
        try {
            Class cls = Class.forName(className);
            return cls;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    //首字母转小写
    public static String toLowerCaseFirstOne(String s){
        if(Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }
}
