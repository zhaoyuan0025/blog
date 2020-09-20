package com.lzy.blog.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 评论表实体类
 * </p>
 *
 * @author 稽哥
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 *
 */
@Data
public class Comment implements Serializable {

    private static final long serialVersionUID = -262115810554538289L;

    /**
     * 评论id
     */
    private String id;

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 评价人
     */
    private Integer commentUser;

    /**
     * 真正的评论人
     */
    private User user;

    /**
     * 评论帖子id
     */
    private String commentBlog;

    /**
     * 评论的博客
     */
    private Blog blog;

    /**
     * 点赞数
     */
    private Integer commentGood;

    /**
     * 评论时间
     */
    private String createdTime;

    /**
     * 是否删除，0否1是
     */
//    private Integer deleted;
    /**
     * 是否评论，存库时，不存这个字段
     * 当查询用户评论情况时，对这个字段进行赋值
     */
    private boolean commentFlag = false;

}
