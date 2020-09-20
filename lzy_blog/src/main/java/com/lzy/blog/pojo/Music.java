package com.lzy.blog.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: lzy_blog
 * @description: 音乐鉴赏
 * @author: lzy
 * @create: 2020-06-05 23:50
 **/
@Data
public class Music implements Serializable {

    private Integer id;

    private String name;

    private String artist;

    private String url;

    private String cover;

    private String lrc;

    private String createdTime;

    private Integer enable;

    private Integer deleted;
}
