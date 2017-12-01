package edu.nf;

import edu.nf.dao.StuDao;
import edu.nf.entity.Stu;

import java.util.List;

/**
 * Created by Administrator on 2017/11/30.
 */
public class Main {
    public static void main(String[] args) {

       List<Stu> list = StuDao.findStu(1);
       for (Stu s:list){
           System.out.println(s.getSname());
       }
    }
}
