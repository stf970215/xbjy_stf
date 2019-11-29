package com.sys.dao;


import com.sys.DBUtil;
import com.sys.entity.Menu;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Auther: shen
 * @Date: 2019/11/29/16:32
 * @Description:
 */
public class MenuDao {
    private JdbcTemplate jdbcTemplate=new JdbcTemplate(DBUtil.getDataSource());
    public List<Menu> listAll(){
        String sql="select * from sys_menu order by order_by";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Menu.class));
    }
}
