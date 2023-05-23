package com.minqiliang.exception;

import com.minqiliang.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // 全局异常处理
public class GlobalExceptionHander{

    // 捕获异常的类型
    @ExceptionHandler(Exception.class)
    public Result ex1(Exception e){
        e.printStackTrace();
        return Result.error("对不起，操作失败！");
    }
}
