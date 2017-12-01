package edu.nf;

import edu.nf.annotation.Column;

/**
 * Created by Administrator on 2017/12/1.
 */

public class User {
    @Column(values="u_no")
    private int uno;
    @Column(values="u_name")
    private String uname;
    @Column(values="u_sex")
    private String sex;
}
