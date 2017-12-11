package nf.factory.Test;

import nf.factory.annotation.Component;
import nf.factory.annotation.Scope;

/**
 * Created by Administrator on 2017/12/7.
 */
@Component("dao")
@Scope("singleton")
public class StuDao implements IStuDao {

    public void findStu(){
        System.out.println("select * from stu");
    }
}
