package com.example.demo1;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取客户端发送的用户名
        resp.setContentType("text/html;charset=utf-8");
        Map<String,Object> map = new HashMap<String,Object>();
        String username = req.getParameter("username");
        //获取
        if("tom".equals(username)){
             map.put("userExit",true);
             map.put("msg","此用户名已使用");
        }else{
            map.put("userExit",true);
            map.put("msg","用户名可用");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String temp = objectMapper.writeValueAsString(map);
        System.out.println(temp);
        objectMapper.writeValue(resp.getWriter(),map);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
