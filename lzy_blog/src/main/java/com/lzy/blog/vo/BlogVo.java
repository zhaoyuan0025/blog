package com.lzy.blog.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: lzy_blog
 * @description: 显示的blog
 * @author: lzy
 * @create: 2020-07-05 23:27
 **/
@Data
public class BlogVo implements Serializable {
    /**
     * 帖子id
     */
    private String blogId;

    /**
     * 标题
     */
    private String blogTitle;

    /**
     * 封面
     */
    private String blogImage;

    /**
     * 帖子内容
     */
    private String blogContent;

    /**
     * 点赞数
     */
    private Integer blogGoods;

    /**
     * 阅读数
     */
    private Integer blogRead;

    /**
     * 收藏数
     */
    private Integer blogCollection;

    /**
     * 博客分类
     */
    private String typeName;

    /**
     * 文章来源
     */
    private String blogSource;

    /**
     * 简介
     */
    private String blogRemark;

    /**
     * 评论数
     */
    private Integer blogComment;

    /**
     * 发布月份
     */
    private String blogMonth;

    /**
     * 创建时间
     */
    private String createdTime;

    /**
     * 更新时间
     */
    private String updateTime;
}
