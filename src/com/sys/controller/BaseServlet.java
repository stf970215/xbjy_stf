package com.sys.controller;

import com.sys.constants.SysConstant;
import com.sys.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Auther: shen
 * @Date: 2019/12/02/16:18
 * @Description:
 */
public class BaseServlet extends HttpServlet {
    private User loginUser=new User();

    public User getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //取出session中的登陆信息
        HttpSession session = req.getSession();
        loginUser = (User) session.getAttribute(SysConstant.SESSION_LOGIN_NAME);

        //请求头
        String uri = req.getRequestURI();
        String[] uriStr = uri.split("/");
        uri=uriStr[uriStr.length-1];

        Class clazz=this.getClass();
        try {
            Method method = clazz.getDeclaredMethod(uri, HttpServletRequest.class, HttpServletResponse.class);
            //暴力反射
            method.setAccessible(true);

            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
