package com.acwing.servlet.user;

import com.acwing.pojo.User;
import com.acwing.service.user.UserService;
import com.acwing.service.user.UserServiceImpl;
import com.acwing.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    //调用业务层

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从前端得到用户名和密码
        String username = req.getParameter("userCode");
        String password = req.getParameter("userPassword");
        UserService service = new UserServiceImpl();
        User user = service.login(username, password);
        //比较密码
        if(user!=null){
            //登陆成功将用户的信息放到session中
            req.getSession().setAttribute(Constants.USER_SESSION,user);
            resp.sendRedirect("jsp/frame.jsp");
        }else{
            //没有用户返回登陆页面，并提示错误信息
            req.setAttribute("error","登陆信息错误");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
