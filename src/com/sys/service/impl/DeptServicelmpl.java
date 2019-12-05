package com.sys.service.impl;

import com.sys.dao.DeptDao;
import com.sys.entity.Dept;
import com.sys.entity.Page;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Auther: shen
 * @Date: 2019/12/02/19:40
 * @Description:
 */
public class DeptServicelmpl {
    private DeptDao deptDao=new DeptDao();
    public List<Dept> list(String dname, Page page){
        return deptDao.list(dname, page);

    }
    public List<Dept> listAll(){
        return deptDao.listAll();
    }
//    public Dept list(){
//        return deptDao.list();
//    }

    public void add(Dept dept){
        Date date=new Date();
        SimpleDateFormat ss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dept.setCreateTime(ss.format(date));
        dept.setCreateBy(3);
        deptDao.add(dept);
    }
    public Integer getCount(String bname){
        return deptDao.getCount(bname);
    }
    public void delete(Integer id){
        deptDao.delete(id);
    }
    public Integer selectyg(Integer id){
        return deptDao.selectyg(id);
    }
}
