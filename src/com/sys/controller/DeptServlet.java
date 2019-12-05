package com.sys.controller;


import com.alibaba.fastjson.JSON;
import com.sys.entity.Dept;
import com.sys.entity.Page;
import com.sys.service.impl.DeptServicelmpl;
import com.sys.service.impl.UserServicelmpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
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
        String bname = request.getParameter("bname");
        bname=bname==null?"":bname;
        //当前页
        String pagey = request.getParameter("page");
        //查询总数据数
        Integer count = service.getCount(bname);
        Page page=new Page();
        page.setCount(count);
        Integer pageCurrent=pagey==null?1: Integer.valueOf(pagey);
        page.setPageCurrent(pageCurrent);
        List<Dept> depts = service.list(bname,page);
        //查询的数据
        request.setAttribute("dept",depts);
        //查询的条件
        request.setAttribute("bname",bname);
        //查询的分页信息
        request.setAttribute("page",page);
        request.getRequestDispatcher("/view/sys/dept/list.jsp").forward(request,response);
    }



    public void add(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, IOException {
        Dept dept=new Dept();
        //获取添加的数据
        String parameter = request.getParameter("deptname");
        if(parameter==null){
            return;
        }
        dept.setName(parameter);
//        BeanUtils.populate(dept, map);
        service.add(dept);
        response.sendRedirect("/sys/dept/list");
    }
    public void  deleteById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        if (id==null){
            return;
        }

        Integer count = service.selectyg(Integer.valueOf(id));
        if (count!=0){
            response.sendRedirect("/sys/dept/list");
            return;
        }
        service.delete(Integer.valueOf(id));
        response.sendRedirect("/sys/dept/list");
    }
}
