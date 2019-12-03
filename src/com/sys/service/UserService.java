package com.sys.service;

import com.sys.entity.Page;
import com.sys.entity.User;

import java.util.List;

/**
 * @Auther: shen
 * @Date: 2019/12/02/17:06
 * @Description:
 */
public interface UserService  {
    public List<User> listAll(String account, Page page);
    public Integer getCount(String account);
    public void delId(Integer id);
    public void add(User user);
    public void toupdate(User user);
    public User selectId(Integer id);
    public void updatePassword(User user);

}
