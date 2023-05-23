package com.minqiliang.filter;


import com.alibaba.fastjson.JSONObject;
import com.minqiliang.pojo.Result;
import com.minqiliang.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*") // 要拦截的路径
public class LoginCheckFilter implements Filter {

    // 初始化
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("LoginCheckFilter初始化");
    }

    // 过滤器的核心方法
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 获取请求路径
        String url = request.getRequestURL().toString();
        log.info("请求的路径为:{}",url);
        // 如果请求的路径是登录路径，放行，并且结束方法
        if (url.contains("login")){
            filterChain.doFilter(request,response);
            return;
        }
        // 如果不是登录路径，判断token是否存在，不存在，返回未登录信息
        String token = request.getHeader("token");
        if(!StringUtils.hasLength(token)){
            log.info("请求头token为空，直接返回未登录的信息");
            Result error = Result.error("NOT_LOGIN");
            // 转为json
            String jsonString = JSONObject.toJSONString(error);
            // 输出流返回数据
            response.getWriter().write(jsonString);
            return;

        }

        // 存在token，解析token，如果解析报错，则token无效，未登录信息
        try {
            JwtUtils.parseJWT(token);
        }catch (Exception e){
            e.printStackTrace();
            log.info("令牌解析失败，直接返回未登录的信息");
            Result error = Result.error("NOT_LOGIN");
            String jsonString = JSONObject.toJSONString(error);
            response.getWriter().write(jsonString);
            return;
        }

        // 未捕获到异常，放行
        log.info("token合法，放行");
        filterChain.doFilter(request,response);
    }

    // 销毁
    public void destroy() {
        log.info("LoginCheckFilter销毁");
    }
}
