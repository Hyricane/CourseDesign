package com.itheima.filter;

import com.itheima.pojo.User;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "loginFilter" ,urlPatterns = "/*")
public class LoginFilter implements Filter {

    private static AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //获取请求路径
        String requestURI = request.getRequestURI();
        //定义需要放行的页面
        String[] urls = {
                "/login.do",
                "/login.html",
                "/css/**",
                "/elementuidemo/**",
                "/img/**",
                "/js/**",
                "/plugins/**",
                "/template/**",
        };

        //遍历放行页面 用AntPathMatcher的match方法匹配请求路径
        for (String url : urls) {
            boolean match = antPathMatcher.match(url, requestURI);
            if (match){
                //如果匹配 就放行页面
                filterChain.doFilter(request,response );
                return;
            }
        }

        //检查是否登录
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user==null){
            //如果未登录 拦截跳转到登录页面
            response.sendRedirect("/login.html");
            return;
        }
        //最终放行
        filterChain.doFilter(request,response );
        return;

    }
}
