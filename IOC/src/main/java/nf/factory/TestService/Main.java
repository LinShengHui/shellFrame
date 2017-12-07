package nf.factory.TestService;

import nf.factory.Factory.ContainerFactory;

/**
 * Created by Administrator on 2017/12/7.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        ContainerFactory cft = new ContainerFactory();
        StuDao dao2= (StuDao) cft.getBean("dao");
        System.out.println(dao2+"111");
    }
}
