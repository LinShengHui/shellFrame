package nf.factory.entity;

import nf.factory.annotation.Component;
import nf.factory.annotation.Inject;

/**
 * Created by Administrator on 2017/12/11.
 */
@Component("bean2")
public class Bean2 {
    @Inject("bean3")
    public Bean3 bean3;
}
