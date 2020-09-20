package com.lzy.blog.config;

import com.lzy.blog.utils.IdWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: lzy_blog
 * @description: 用于将一些实体类放到spring容器中
 * @author: lzy
 * @create: 2020-06-05 18:03
 **/
@Configuration
public class BeanConfig {

    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }
}
