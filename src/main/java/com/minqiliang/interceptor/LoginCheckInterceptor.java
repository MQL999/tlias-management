package com.minqiliang.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.minqiliang.pojo.Result;
import com.minqiliang.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component // 注入到spring容器
@Slf4j // 日志
public class LoginCheckInterceptor implements HandlerInterceptor {

    // 拦截器，拦截所有请求，判断是否登录
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 获取token
        String token = request.getHeader("token");
        // 如果没有token，返回未登录信息
        if(!StringUtils.hasLength(token)){
            log.info("请求头token为空，直接返回未登录的信息");
            Result error = Result.error("NOT_LOGIN");
            // 转为json
            String jsonString = JSONObject.toJSONString(error);
            // 输出流返回数据
            response.getWriter().write(jsonString);
            return false;
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
            return false;
        }

        // 未捕获到异常，放行
        log.info("token合法，放行");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
