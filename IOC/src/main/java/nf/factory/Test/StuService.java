package nf.factory.Test;

import nf.factory.annotation.Component;
import nf.factory.annotation.Inject;

/**
 * Created by Administrator on 2017/12/7.
 */
@Component("service")
public class StuService implements IStuService {

    @Inject(value="dao")
    private IStuDao stuDao;


    public boolean addStu() throws Exception {
        stuDao.findStu();
       /* ContainerFactory cft = new ContainerFactory();
        StuDao dao= (StuDao) cft.getBean("dao");
        dao.findStu();*/
        return true;
    }

    public void setStuDao(IStuDao stuDao) {
        this.stuDao = stuDao;
    }
}
