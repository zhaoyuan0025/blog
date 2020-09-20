package com.lzy.blog.advice;

import com.lzy.blog.exception.BlogException;
import com.lzy.blog.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 定义统一异常处理
 *
 * @Author:
 * @Date:
 * @Version 1.0
 */
@ControllerAdvice
@Slf4j
public class BlogExceptionAdvice {

    /**
     * 统一处理 BlogException
     *
     * @param exception
     */
    @ExceptionHandler(BlogException.class)
    @ResponseBody
    public Result<Object> exceptionHandler(BlogException exception) {
        log.error("统一异常处理：", exception);
        return new Result<>(exception.getErrorCode(), exception.getMessage());
    }
}
