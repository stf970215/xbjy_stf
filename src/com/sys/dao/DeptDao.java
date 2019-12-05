package com.sys.dao;


import com.sys.entity.Dept;
import com.sys.entity.Page;
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
    private JdbcTemplate template = new JdbcTemplate(DBUtil.getDataSource());

    public List<Dept> listAll() {
        String sql = "select a.id id,a.name user,a.create_time createTime,b.name name from sys_dept a JOIN sys_user b ON a.create_by=b.id";
        return template.query(sql, new BeanPropertyRowMapper<>(Dept.class));
    }

    //查询方法
    public List<Dept> list(String bname, Page page) {
        String sql = "select a.id id,a.name user,a.create_time createTime,b.name name from sys_dept a JOIN sys_user b ON a.create_by=b.id where a.name like ? ORDER BY a.create_time desc limit ?,?";
        return template.query(sql, new BeanPropertyRowMapper<>(Dept.class), "%" + bname + "%", (page.getPageCurrent() - 1) * page.getPageSize(), page.getPageSize());
    }

    //查询总记录
    public Integer getCount(String bname) {
        String sql = "select count(*) FROM sys_dept where name like ?";
        return template.queryForObject(sql, Integer.class, "%" + bname + "%");
    }

    //添加方法
    public void add(Dept dept) {
        String sql = "insert into sys_dept(name,create_time,create_by) values(?,?,?)";
        template.update(sql, dept.getName(), dept.getCreateTime(), dept.getCreateBy());
    }

    //修改方法
    public void update(Dept dept) {

    }

    //删除方法
    public void delete(Integer id) {
        String sql = "delete from sys_dept where id=?";
        template.update(sql, id);
    }
    //查询部门下的员工数
    public Integer selectyg(Integer id){
        String sql="select count(*) count FROM sys_dept a JOIN sys_user b ON a.id=b.dept_id where a.id=?";
        return template.queryForObject(sql,Integer.class,id);
    }
}
