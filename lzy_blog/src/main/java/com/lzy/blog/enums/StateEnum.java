package com.lzy.blog.enums;

import lombok.Getter;

/**
 * @program: lzy_blog
 * @description: 状态码枚举
 * @author: lzy
 * @create: 2020-06-05 17:31
 **/
@Getter
public enum  StateEnum {

    /**
     * 逻辑删除状态码
     */
    DELETED(1,"已删除"),
    NOT_DELETED(0,"未删除"),

    /**
     * 启用状态
     */
    ENABLE(1,"启用"),
    NOT_ENABLE(0,"未启用"),

    /**
     * 性别状态
     */
    SEX_MAN(1,"男"),
    SEX_WOMAN(2,"女"),

    /**
     * 请求访问状态枚举
     */
    REQUEST_SUCCESS(1,"请求成功"),
    REQUEST_ERROR(0,"请求异常"),

    /**
     * 用户标识。
     * 0表示管理员，1表示普通用户
     */
    ADMIN(0, "管理员"),
    USER(1, "普通用户");

    private Integer code;
    private String msg;

    StateEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
