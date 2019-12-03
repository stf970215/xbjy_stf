package com.sys.controller;


import com.alibaba.fastjson.JSON;
import com.sys.entity.Dept;
import com.sys.service.impl.DeptServicelmpl;
import com.sys.service.impl.UserServicelmpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


/**
 * @Auther: shen
 * @Date: 2019/12/02/16:37
 * @Description:
 */
@WebServlet("/sys/dept/*")
public class DeptServlet extends BaseServlet {
    private DeptServicelmpl service=new DeptServicelmpl();
    private UserServicelmpl userServicelmpl=new UserServicelmpl();

    public void listAll(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Dept> depts = service.listAll();
        String s = JSON.toJSONString(depts);
        PrintWriter out = response.getWriter();
        out.append(s);
    }
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Dept> depts = service.listAll();
        request.setAttribute("dept",depts);
        request.getRequestDispatcher("/view/sys/dept/list.jsp").forward(request,response);
    }
}
