package com.lhrsite.blog.filter;

import com.lhrsite.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 后台登录拦截器
 * @author lhr
 * @create 2018/2/4
 */
public class AdminFilter implements Filter {
    @Autowired
    private UserService userService;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();
        String[] uris = uri.split("\\.");
        if (!uri.contains("/admin")){
            filterChain.doFilter(servletRequest, servletResponse);
        } else if ("do".equals(uris[uris.length - 1]) || "html".equals(uris[uris.length - 1])){
            Object userToken, userName;
            if ("/admin/login.html".equals(uri) || "/admin/login.do".equals(uri)){

            }else if ((userToken = request.getSession().getAttribute("userToken")) == null ||
                    (userName = request.getSession().getAttribute("userName")) == null ||
                    !userService.isLogin(userToken.toString(), userName.toString())){
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                response.setContentType("text/html;charset=utf-8");
                PrintWriter out = response.getWriter();
                out.println("<script>alert(\"请先登录\");location.href=\"/admin/login.html\";</script>");
                out.flush();
                out.close();
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
