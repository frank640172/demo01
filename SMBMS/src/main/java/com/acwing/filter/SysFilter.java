package com.acwing.filter;

import com.acwing.pojo.User;
import com.acwing.util.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SysFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req1 = (HttpServletRequest)req;
        HttpServletResponse resp1 = (HttpServletResponse) resp;
        User user = (User) req1.getSession().getAttribute(Constants.USER_SESSION);
        System.out.println(user);
        if(user == null){
            resp1.sendRedirect("/SMBMS_war_exploded/error.jsp");
        }else {
            filterChain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
