package com.minqiliang.aop;

import com.alibaba.fastjson.JSONObject;
import com.minqiliang.mapper.OperateLogMapper;
import com.minqiliang.pojo.OperateLog;
import com.minqiliang.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class LogAspect {
    @Autowired
    private OperateLogMapper operateLogMapper;
    @Autowired
    private HttpServletRequest httpServletRequest;

    @Around("@annotation(com.minqiliang.anno.Log)")
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
        String token = httpServletRequest.getHeader("token");
        Claims claims = JwtUtils.parseJWT(token);
        Integer oprateUserId = (Integer) claims.get("id");

        LocalDateTime operateTime = LocalDateTime.now();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        long start = System.currentTimeMillis();
        // 运行原始方法
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();

        String returnValue = JSONObject.toJSONString(result);
        Long costTime = (end -start);

        // 记录日志
        OperateLog operateLog = new OperateLog(null,oprateUserId,operateTime,className,methodName,args,returnValue,costTime);
        operateLogMapper.insert(operateLog);

        log.info("aop记录操作日志：{}",operateLog);
        return result;
    }

}
