package nf.factory.Test;

import nf.factory.annotation.Component;
import nf.factory.annotation.Inject;

/**
 * Created by Administrator on 2017/12/11.
 */
@Component("action")
public class StuAction {

    @Inject(value = "stuService")
    private IStuService stuService;

    public void addStu() throws Exception {
        stuService.addStu();
    }


    public void setStuService(IStuService stuService) {
        this.stuService = stuService;
    }
}
