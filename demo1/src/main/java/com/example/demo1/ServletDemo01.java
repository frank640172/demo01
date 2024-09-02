package com.example.demo1;





import jakarta.servlet.annotation.WebServlet;


import javax.servlet.*;
import java.io.IOException;
//@WebServlet(urlPatterns = "/xnn")
public class ServletDemo01 implements Servlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("小胖墩");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("Servlet3 is comming ....");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
