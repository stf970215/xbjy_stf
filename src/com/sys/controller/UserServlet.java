package com.sys.controller;

import com.sys.constants.SysConstant;
import com.sys.entity.Page;
import com.sys.entity.User;
import com.sys.service.impl.UserServicelmpl;
import com.sys.utlis.MDUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.StringUtils;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Auther: shen
 * @Date: 2019/11/29/16:52
 * @Description:
 */

@WebServlet("/sys/user/*")
public class UserServlet extends BaseServlet {

    private UserServicelmpl service=new UserServicelmpl();

    //查询方法
    public void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        String ks = request.getParameter("ks");
//        String js = request.getParameter("js");
//
//        Date date=new Date();
//        SimpleDateFormat ss=new SimpleDateFormat("yyyy-MM-dd");
//        String format = ss.format(date);
        //查询条件
        String account = request.getParameter("account");
        account=account==null?"":account;
//        String kssj="1949-1-1";
//        ks= StringUtils.isEmpty(ks)?kssj:ks;
//         js= StringUtils.isEmpty(js)?format:js;
//        //当前页
        String pagey = request.getParameter("page");
        //查询总数据数
        Integer count = service.getCount(account);
        

        Page page=new Page();
        page.setCount(count);
        Integer pageCurrent=pagey==null?1: Integer.valueOf(pagey);
        page.setPageCurrent(pageCurrent);

        List<User> userList = service.listAll(account,page);
        //查询的数据
        request.setAttribute("list",userList);
        //查询的条件
        request.setAttribute("account",account);
        //查询的分页信息
        request.setAttribute("page",page);

        request.getRequestDispatcher("/view/sys/user/list.jsp").forward(request,response);
    }
    //删除方法
    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        if (id==null){
            return;
        }
        service.delId(Integer.valueOf(id));
        response.sendRedirect("/sys/user/list");
    }
    //添加方法
    public void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = new User();
        //获取添加的数据
        Map<String, String[]> map = request.getParameterMap();
        BeanUtils.populate(user, map);
        service.add(user);
        response.sendRedirect("/sys/user/list");
    }
    //通过ID的修改方法
    public void toUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        User users = service.selectId(Integer.valueOf(id));
        request.setAttribute("user",users);
        request.getRequestDispatcher("/view/sys/user/update.jsp").forward(request,response);
    }
    //修改方法
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user=new User();
        user.setId(Integer.valueOf(request.getParameter("id")));
        user.setDeptId(Integer.valueOf(request.getParameter("deptId")));
        user.setAccount(request.getParameter("account"));
        user.setPassword(request.getParameter("password"));
        user.setName(request.getParameter("name"));
        user.setAge(Integer.valueOf(request.getParameter("age")));
        user.setSex(Integer.valueOf(request.getParameter("sex")));
        user.setEmail(request.getParameter("email"));
        user.setBirthDate(request.getParameter("birthDate"));
        service.toupdate(user);
        response.sendRedirect("/sys/user/list");
    }
    //修改密码
    public void forgetPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        //前端的验证码
        String code = request.getParameter("code");
        //用session存值
        HttpSession session=request.getSession();
        Object object = session.getAttribute(SysConstant.SESSION_EMAIL_CODE_NAME);
        //计较前端输入的验证码和session中的验证码
        if (object==null||!code.equals(object.toString())){
            response.sendRedirect("/view/sys/user/forget.jsp");
            return;
        }
        User user=new User();
        user.setAccount(account);
        user.setPassword(MDUtil.md5(password));
        service.updatePassword(user);
        //修改完毕，跳转到登录的页面
        response.sendRedirect("/index.jsp");
    }

}
