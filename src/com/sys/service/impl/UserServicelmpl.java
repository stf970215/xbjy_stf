package com.sys.service.impl;

import com.sys.dao.UserDao;
import com.sys.entity.Page;
import com.sys.entity.User;
import com.sys.service.UserService;
import com.sys.utlis.MDUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Auther: shen
 * @Date: 2019/12/02/17:07
 * @Description:
 */
public class UserServicelmpl implements UserService {
    private UserDao userDao=new UserDao();
    //查询全部加条件
    public List<User> listAll(String account, Page page){

        return userDao.listAll(account,page);
    }

    public Integer getCount(String account){
        System.out.println(userDao.getCount(account));
        return userDao.getCount(account);
    }
    public void delId(Integer id){
        userDao.delID(id);
    }
    public void add(User user){
        user.setPassword(MDUtil.md5(user.getPassword()));
        Date date=new Date();
        SimpleDateFormat ss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        user.setCreateTime(ss.format(date));
        userDao.add(user);
    }
    public void toupdate(User user){
        userDao.toupdate(user);
    }
    public User selectId(Integer id){
        return userDao.selectId(id);
    }

    public void updatePassword(User user){
        userDao.updatePassword(user);
    }
}
