package com.lzy.blog.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: lzy_blog
 * @description: 关于
 * @author: lzy
 * @create: 2020-06-05 23:42
 **/
@Data
public class About implements Serializable {

    private Integer aboutId;

    private String aboutTitle;

    private String aboutContent;

    private Integer aboutRead;

    private String createdTime;

    private String updateTime;

    private Integer version;

    private Integer deleted;

    private Integer enable;
}
