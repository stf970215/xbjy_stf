package com.sys.service.impl;

import com.sys.dao.MenuDao;
import com.sys.entity.Menu;
import com.sys.service.MenuService;

import java.util.List;

/**
 * @Auther: shen
 * @Date: 2019/11/29/16:53
 * @Description:
 */
public class MenuServicelmpl implements MenuService {

    private MenuDao menuDao=new MenuDao();
    /***
     * @decription 查询所有菜单
     * @author shen
     * @date 2019/11/29 17:00
     * @params []
     * @return java.util.List<com.dfbz.sys.entity.Menu>
     */
    @Override
    public List<Menu> listAll() {
        return menuDao.listAll();
    }
}
