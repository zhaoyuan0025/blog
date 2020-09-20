package com.lzy.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动类
 * EnableTransactionManagement注解开启事务管理
 * @author Administrator
 * @date
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.lzy.blog.mapper")
public class LzyBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(LzyBlogApplication.class, args);
    }

}
