package com.lzy.blog.controller;

import com.lzy.blog.exception.BlogException;
import com.lzy.blog.utils.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: lzy_blog
 * @description: 测试
 * @author: lzy
 * @create: 2020-06-06 00:35
 **/
@RestController
@RequestMapping(value = "/test")
public class TestController {

    /**
     * 测试
     * @return
     */
    @RequestMapping(value = "/testException",method = RequestMethod.GET)
    public Result<Object> testException(@Param("id") Integer id){
        if(id == 1){
            return new Result<>();
        }else {
            throw new BlogException("发生了异常");
        }
    }
}
