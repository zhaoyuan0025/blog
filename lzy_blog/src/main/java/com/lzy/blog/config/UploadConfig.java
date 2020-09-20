package com.lzy.blog.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: lzy_blog
 * @description: 上传图片的配置类
 * @author: lzy
 * @create: 2020-08-24 13:57
 **/
@Data
@Component
@ConfigurationProperties(prefix = "upload")
public class UploadConfig {

    private String baseUrl;
    private List<String> allowTypes;


}
