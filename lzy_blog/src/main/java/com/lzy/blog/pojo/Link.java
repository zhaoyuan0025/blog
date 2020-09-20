package com.lzy.blog.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: lzy_blog
 * @description: 友情链接
 * @author: lzy
 * @create: 2020-06-05 23:46
 **/
@Data
public class Link implements Serializable {

    private Integer linkId;

    private String linkName;

    private String linkUrl;

    private String createdTime;

    private String updateTime;

    private Integer version;

    private Integer deleted;
}
