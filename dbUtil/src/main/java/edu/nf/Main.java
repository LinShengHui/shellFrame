package edu.nf;

import edu.nf.dao.BaseDao;
import edu.nf.entity.Stu;
import edu.nf.sql.SQLExecutor;

import java.util.List;

/**
 * Created by Administrator on 2017/11/30.
 */
public class Main {
    public static void main(String[] args) throws Exception {
/*
        Stu stu = new SQLExecutor(BaseDao.getConnection()).getsingleBean(Stu.class,"select * from tb_stu where s_no= ?",1);
      */
        //查询多表 List(map(k,v))
     /*   List list = new SQLExecutor(BaseDao.getConnection()).getSingleList("select * from tb_stu");
        Iterator it = list.iterator();
        while(it.hasNext()) {
            Map hm = (Map)it.next();
            System.out.println(hm.get("s_no")+","+hm.get("s_name"));
        }
        System.out.println(list.size());*/

        //单表的查询多条语句
        SQLExecutor sqlExecutor = new SQLExecutor(BaseDao.getConnection());
        String sql = "select * from tb_stu";
       List<Stu> list = sqlExecutor.getList(sql, Stu.class);
        System.out.println("查询到："+list.size());


    }
}
