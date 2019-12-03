package com.sys.dao;


import com.sys.entity.Dept;
import com.sys.utlis.DBUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Auther: shen
 * @Date: 2019/12/02/19:41
 * @Description:
 */
public class DeptDao {
    private JdbcTemplate template=new JdbcTemplate(DBUtil.getDataSource());

    //查询方法
    public List<Dept> listAll(){
        String sql="select*from sys_dept";
        return template.query(sql,new BeanPropertyRowMapper<>(Dept.class));
    }
//    public Dept list(){
//        String sql="SELECT*from sys_dept";
//        return template.queryForObject(sql,new BeanPropertyRowMapper<>(Dept.class));
//    }
    //添加方法
    public void add(){
        String sql="insert into sys_dept";
        template.update(sql);
    }
}
