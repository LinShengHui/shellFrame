package nf.factory;

/**
 * Created by Administrator on 2017/12/7.
 */
public class Definition {
    //bean的唯一标识
    private String id;
    //bean的完整类名
    private String className;
    //bean的创建方式
    private String scope="singleton";

    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
