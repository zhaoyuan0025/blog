package com.lzy.blog.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * 点赞实体
 * @Author:
 * @Date: 2020/2/16 10:22
 * @Version 1.0
 */
@Data
public class BlogGoods {

    @Id
    private String id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 博客id
     */
    private String blogId;

}
