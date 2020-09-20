package com.lzy.blog.config;

import com.lzy.blog.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @program: lzy_blog
 * @description: 1111
 * @author: lzy
 * @create: 2020-06-06 23:39
 **/
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Bean
    public LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //拦截所有请求，并放行登录的请求
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/**").
                excludePathPatterns("/*/login");
        super.addInterceptors(registry);
    }
}
