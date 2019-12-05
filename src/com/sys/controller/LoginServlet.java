package com.sys.controller;

import com.alibaba.fastjson.JSON;
import com.sys.constants.SysConstant;
import com.sys.entity.User;
import com.sys.service.impl.UserServicelmpl;
import com.sys.utlis.ImgCodeUtil;
import com.sys.utlis.MDUtil;
import jdk.nashorn.api.scripting.URLReader;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @Auther: shen
 * @Date: 2019/12/04/17:51
 * @Description:
 */


@WebServlet("/sys/login/*")
public class LoginServlet extends BaseServlet {
    private UserServicelmpl service=new UserServicelmpl();
    //登录验证
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //取前端的值
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String code = request.getParameter("picCode");
        String remember = request.getParameter("remember");
        //获取session里面存的图片验证码
        HttpSession session=request.getSession();
        Object obj = session.getAttribute(SysConstant.SESSION_PIC_CODE_NAME);
        //判断图片验证码
        if (obj==null||!obj.toString().equals(code)){
            //图片验证码不对
            response.sendRedirect("/index.jsp");
            return;
        }
        //把值存进对象中通过数据对比
        User user =new User();
        user.setAccount(account);
        user.setPassword(MDUtil.md5(password));
        //密文
        List<User> users = service.checkLogin(user);
        //判断账号与密码不同并判断是否存相同的
        if (users==null||users.size()==0||users.size()>1){
            //登录失败，跳转回去
            response.sendRedirect("/index.jsp");
            return;
        }
        else {
            User userLogin=users.get(0);
            //验证通过,存储登录的用户和密码
            session.setAttribute(SysConstant.SESSION_LOGIN_NAME,users.get(0));

            //勾选了7天免登录的判断
            if ("1".equals(remember)){
                //存入明文
                userLogin.setPassword(password);
                String strJson = JSON.toJSONString(userLogin);
                strJson= URLEncoder.encode(strJson,"utf-8");
                Cookie cookieLoginuser=new Cookie(SysConstant.COOKIE_LOGIN_USER,strJson);
                //单位为秒
                cookieLoginuser.setMaxAge(7*24*60*6);
                //任何请求都可以获取cookie
                cookieLoginuser.setPath("/");
                response.addCookie(cookieLoginuser);

            }


            //登录成功，跳转到home.jsp
            request.getRequestDispatcher("/view/comm_test/home.jsp").forward(request,response);
            return;
        }

    }
    //获取图片
    public void getPic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ImgCodeUtil imgCodeUtil = new ImgCodeUtil();
        //获取验证码图片
        BufferedImage image = imgCodeUtil.getImage();
        ////获取验证码文本内容
        String code = imgCodeUtil.getText();
        //把图片验证码存入session
        HttpSession session = request.getSession();
        session.setAttribute(SysConstant.SESSION_PIC_CODE_NAME, code);
        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos = response.getOutputStream();
        ImageIO.write(image, "jpeg", sos);
        sos.flush();
        sos.close();
    }
}
