package com.sys.service.impl;

import com.sys.dao.DeptDao;
import com.sys.entity.Dept;

import java.util.List;

/**
 * @Auther: shen
 * @Date: 2019/12/02/19:40
 * @Description:
 */
public class DeptServicelmpl {
    private DeptDao deptDao=new DeptDao();
    public List<Dept> listAll(){
        return deptDao.listAll();
    }
//    public Dept list(){
//        return deptDao.list();
//    }
}
