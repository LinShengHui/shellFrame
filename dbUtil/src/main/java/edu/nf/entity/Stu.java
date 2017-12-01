package edu.nf.entity;

import edu.nf.annotation.Column;

/**
 * Created by Administrator on 2017/11/30.
 */
public class Stu {
    @Column(values="s_no")
    private int sno;
    @Column(values="s_name")
    private String sname;
    @Column(values="s_sex")
    private String sex;

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Stu{" +
                "sno=" + sno +
                ", sname='" + sname + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
