package edu.nf;

import edu.nf.dao.BaseDao;
import edu.nf.sql.SQLExecutor;

/**
 * Created by Administrator on 2017/11/30.
 */
public class Main {
    public static void main(String[] args) throws Exception {
/*
        Stu stu = new SQLExecutor(BaseDao.getConnection()).getsingleBean(Stu.class,"select * from tb_stu where s_no= ?",1);
      */
        //查询多表 List(map(k,v))
     /*  List list = new SQLExecutor(BaseDao.getConnection()).getSingleList("select * from tb_stu");
        Iterator it = list.iterator();
        while(it.hasNext()) {
            Map hm = (Map)it.next();
            System.out.println(hm.get("s_no")+","+hm.get("s_name"));
        }
        System.out.println(list.size());*/

        /*//单表的查询多条语句List(Obj)
        SQLExecutor sqlExecutor = new SQLExecutor(BaseDao.getConnection());
        String sql = "select * from tb_stu";
       List<Stu> list = sqlExecutor.getList(sql, Stu.class);
        System.out.println("查询到："+list.size());*/


        //添加学生
        String sql  = "insert into tb_stu values(?,?,?)";
        int i  = new SQLExecutor(BaseDao.getConnection()).zsgObj(sql,1,"Tome","男");
        System.out.println(i);

        //根据ID 123批量删除学生
      /*  String sql = "delete FROM tb_stu where s_no = ? ";
        System.out.println(new SQLExecutor(BaseDao.getConnection()).deleteObj(sql,2,3));*/
    }
}
