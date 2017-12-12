package nf.factory.Test;

import nf.factory.Factory.ContainerFactory;

/**
 * Created by Administrator on 2017/12/7.
 */
public class Main {

    public static void main(String[] args) throws Exception {

        ContainerFactory cft = new ContainerFactory();
       StuAction stu = (StuAction) cft.getBean("action");
        stu.addStu();
       /*Bean1 bean1 = (Bean1) cft.getBean("bean1");
        System.out.println(bean1+"___"+bean1.bean2+"___"+ bean1.bean2.bean3);*/
   }


}
