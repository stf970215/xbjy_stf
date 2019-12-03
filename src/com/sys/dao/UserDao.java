package com.sys.dao;

import com.sys.entity.Page;
import com.sys.entity.User;
import com.sys.utlis.DBUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Auther: shen
 * @Date: 2019/12/02/16:46
 * @Description:
 */
public class UserDao {
    private JdbcTemplate template=new JdbcTemplate(DBUtil.getDataSource());


    //查询
    public  List<User> listAll(String account, Page page){
        String sql="SELECT " +
                "b.NAME deptname," +
                "a.id id," +
                "a.account account," +
                "a.NAME NAME," +
                "a.age age," +
                "a.sex sex," +
                "a.birth_date birth_date," +
                "a.create_time create_time " +
                "FROM " +
                "sys_user a " +
                "LEFT JOIN sys_dept b ON a.dept_id = b.id "+
                "WHERE a.account LIKE ? limit ?,?";
        return template.query(sql,new BeanPropertyRowMapper<>(User.class),"%"+account+"%",(page.getPageCurrent()-1)*page.getPageSize(),page.getPageSize());
    }
    //查询总记录数
    public Integer getCount(String account){
        String sql = "select count(*) from sys_user where account like ?";
        return  template.queryForObject(sql,Integer.class,"%"+account+"%");
    }
    //删除
    public void delID(Integer id){
        String sql="delete from sys_user where id = ?";
        template.update(sql,id);
    }
    //添加
    public void add(User user) {
        String sql = "insert into sys_user (id,dept_id,account,password,name,age,sex,email,birth_date,create_time) values (null,?,?,?,?,?,?,?,?,?)";
        template.update(sql, user.getDeptId(), user.getAccount(), user.getPassword(), user.getName(), user.getAge(), user.getSex(), user.getEmail(), user.getBirthDate(), user.getCreateTime());
    }
    //修改
    public void toupdate(User user) {
        String sql = "update sys_user set dept_id=?,account=?,password=?,name=?,age=?,sex=?,email=?,birth_date=? where id=?";
        template.update(sql,  user.getDeptId(),user.getAccount(), user.getPassword(), user.getName(), user.getAge(), user.getSex(), user.getEmail(), user.getBirthDate(),user.getId());
    }
    //根据id查询
    public User selectId(Integer id){
        String sql="select*from sys_user where id=?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),id);
    }

    //修改密码
    public void updatePassword(User user) {
        String sql = "update sys_user set password =? where account = ? ";
        template.update(sql, user.getPassword(), user.getAccount());
    }


}
