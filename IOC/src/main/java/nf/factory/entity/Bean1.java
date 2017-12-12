package nf.factory.entity;

import nf.factory.annotation.Component;
import nf.factory.annotation.Inject;

/**
 * Created by Administrator on 2017/12/11.
 */
@Component("bean1")
public class Bean1 {

    @Inject("bean2")
    public Bean2 bean2;
}
