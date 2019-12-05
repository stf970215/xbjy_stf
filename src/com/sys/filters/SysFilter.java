package com.sys.filters;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.sys.constants.SysConstant;
import com.sys.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;

/**
 * @author shen
 * @version 1.0.1
 * @date 2019/11/29 15:16
 * @description
 */
@WebFilter("/*")
public class SysFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        //获取请求的路径
        String uri = request.getRequestURI();
        //忘记密码页面可以直接进入
        if (uri.endsWith("forget.jsp")) {
            filterChain.doFilter(request, response);
            return;
        }
        //放行index.jsp页面,登陆验证请求,图片验证码请求
        //拦截到登录请求：localhost:8080/index.jsp
        //如果cookie中有值，则直接跳转到home.jsp（7天免登录实现方案），否则跳转到登录界面index.jsp
        if (uri.endsWith("index.jsp")) {
            //获取所有cookie的信息
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (int i = 0; i < cookies.length; i++) {
                    //如果cookie中有登录信息，则直接跳转到home.jsp
                    if ("cookieLoginUser".equals(cookies[i].getName())) {
                        //获取cookie中的值并解码得到json字符串
                        String strJson = URLDecoder.decode(cookies[i].getValue(), "utf-8");
                        User loginuser = JSON.parseObject(strJson, new TypeReference<User>() {
                        });
                        //把cookie中取出的值放到session中去
                        session.setAttribute(SysConstant.SESSION_LOGIN_NAME, loginuser);
                        //登录成功 跳转home.jsp
                        response.sendRedirect("/view/common/home.jsp");
                    }
                }
            }
        } else if (uri.endsWith("/sys/login/login") || uri.endsWith("/sys/login/getPic")) {
            //直接放行登陆验证请求,图片验证码请求
        } else {
            //取session判断非法登录
            Object obj = session.getAttribute(SysConstant.SESSION_LOGIN_NAME);
            //非法登陆
            if (obj == null) {
                response.sendRedirect("/index.jsp");
            }
        }
        //放行
        filterChain.doFilter(request, response);
    }
}
