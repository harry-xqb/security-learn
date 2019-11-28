package com.security.learn.mvc.filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * create by： harry
 * date:  2019/11/24 0024 下午 3:05
 **/
public class TimeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("time filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("pre do filter");
        Date start = new Date();
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("finish filter; time:" + (new Date().getTime() - start.getTime()));
    }

    @Override
    public void destroy() {
        System.out.println("time filter destroy");
    }
}
