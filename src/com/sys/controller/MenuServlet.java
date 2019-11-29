package com.sys.controller;

import com.alibaba.fastjson.JSON;
import com.sys.entity.Menu;
import com.sys.service.impl.MenuServicelmpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


/**
 * @Auther: shen
 * @Date: 2019/11/29/16:52
 * @Description:
 */
@WebServlet("/sys/menu")
public class MenuServlet extends HttpServlet {
    private MenuServicelmpl service=new MenuServicelmpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Menu> list = service.listAll();
        String listJsonStr = JSON.toJSONString(list);
        PrintWriter out = resp.getWriter();
        out.append(listJsonStr);
    }
}
