package com.acwing.servlet.user;

import com.acwing.pojo.User;
import com.acwing.service.user.UserService;
import com.acwing.service.user.UserServiceImpl;
import com.acwing.util.Constants;
import com.alibaba.fastjson2.JSON;
import com.mysql.cj.xdevapi.JsonArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        String method = req.getParameter("method");
        System.out.println("UserServlet 调用的功能为" + method);
        if(method!=null && method.equals("savepwd")){
            updatePwd(req,resp);
        }else if(method!=null && method.equals("pwdmodify")){
            //往ajax里返回结果
            //本质比较前端提交的就密码和当前用户的是否匹配
            modifyPwd(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req,resp);
    }


    public void updatePwd(HttpServletRequest req, HttpServletResponse resp){
    User user = (User) req.getSession().getAttribute(Constants.USER_SESSION);
    String rnewpassword = req.getParameter("rnewpassword");
        System.out.println("rnewpassword " + rnewpassword);
        System.out.println("userName " + user.getUserName());
        if(rnewpassword!=null && user!=null) {
            int id = user.getId();
            UserService service = new UserServiceImpl();
            boolean res = service.updatePwd(id, rnewpassword);

            //如果修改成功了
            System.out.println("updatePwd " + res);
            if (res == true) {

                req.setAttribute("message", "密码修改成功，请退出，使用新密码登录");
                //可以移除，移除以后重新登陆，也可以不移除，不重新登陆
                //req.getSession().removeAttribute(Constants.USER_SESSION);
            } else {
                req.setAttribute("message", "新密码有问题");
            }
            //重新切换页面,做转发，转发到对应的前端页面
            try {
                req.getRequestDispatcher("/jsp/pwdmodify.jsp").forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void modifyPwd(HttpServletRequest req, HttpServletResponse resp){
        String oldpassword = req.getParameter("oldpassword");
        User  user = (User) req.getSession().getAttribute(Constants.USER_SESSION);
        Map<String,String> resultMap=new HashMap<String,String>();
        System.out.println("modifyPwd() " + user.getUserPassword());
        System.out.println("oldpassword: " + oldpassword);
        if(oldpassword!=null && user!=null) {

            String userPassword = user.getUserPassword();
            //密码检查正确
            if(userPassword.equals(oldpassword)){
                resultMap.put("result","true");

            }else{
                resultMap.put("result","false");

            }
        }else if(user==null){
                resultMap.put("result","sessionerror");

        }else if(oldpassword==null){
            resultMap.put("result","error");
        }

        resp.setContentType("application/json");
        try {
            PrintWriter writer = resp.getWriter();
            writer.write(JSON.toJSONString(resultMap));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
