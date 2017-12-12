package nf.factory.Factory;

import nf.factory.annotation.Inject;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2017/12/11.
 */
public class InjectSericerHandler {

    /**
     *  字段 inject 赋值
     * @param clazz
     * @param bean
     * @param
     */
    //class bean
    public  static void setInject(Class clazz, Object bean){  //1 bean
        Field[] fields = clazz.getDeclaredFields();
        for (Field field:fields) {
            if(field.isAnnotationPresent(Inject.class)){
                Inject inject = (Inject)field.getAnnotation(Inject.class);
                String injectFidldName = null;
                if(inject.value().trim() == null ||inject.value().trim().equals("")){
                    injectFidldName = field.getType().getSimpleName();
                    System.out.println(injectFidldName+"_______________");
                    // injectFidldName = BeanFactroy.toLowerCaseFirstOne(injectFidldName);
                }else{
                    injectFidldName = inject.value().trim();
                }
                //  Class injectClass = field.getType();
                setRecursionField(injectFidldName,field,bean);
            }
        }
    }
    /**
     *  递归 注入
     * @param injectFidldName
     * @param field
     * @param bean
     */
    private static  void setRecursionField(String injectFidldName,Field field,Object bean){
        Object object = ContainerFactory.getBean(injectFidldName);
        if (object==null)return;
        setInject(object.getClass(),object);

        try {
            field.setAccessible(true);
            field.set(bean,object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
