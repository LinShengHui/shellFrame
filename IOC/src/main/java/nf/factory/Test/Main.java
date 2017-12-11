package nf.factory.Test;

import nf.factory.Factory.ContainerFactory;

/**
 * Created by Administrator on 2017/12/7.
 */
public class Main {

    public static void main(String[] args) throws Exception {

        ContainerFactory cft = new ContainerFactory();
        StuService sertice= (StuService) cft.getBean("service");

        sertice.addStu();
    }
}
