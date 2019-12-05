package com.sys.service;

import com.sys.entity.Dept;
import com.sys.entity.Page;

import java.util.List;

/**
 * @Auther: shen
 * @Date: 2019/12/02/19:40
 * @Description:
 */
public interface DeptService {
    public List<Dept> list(String dname, Page page);

    public List<Dept> listAll();

    //    public Dept list();
    public void add(Dept dept);
    public Integer getCount(String bname);

    public void delete(Integer id);
    public Integer selectyg(Integer id);
}
