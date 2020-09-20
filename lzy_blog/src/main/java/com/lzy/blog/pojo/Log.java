package com.lzy.blog.pojo;


import com.lzy.blog.excel.annotation.Excel;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 接口访问日志表实体类
 * </p>
 *
 * @author 稽哥
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 *
 */
@Data
public class Log implements Serializable {

    private static final long serialVersionUID = 762815810554536396L;

    /**
     * 日志id
     */
    @Excel(name = "编号")
    private Integer logId;

    /**
     * 请求路径
     */
    @Excel(name = "请求地址", autoSize = true)
    private String logUrl;

    /**
     * 参数
     */
    private String logParams;

    /**
     * 访问状态，1正常0异常
     */
    @Excel(name = "返回状态")
    private Integer logStatus;

    /**
     * 异常信息
     */
    @Excel(name = "异常信息")
    private String logMessage;

    /**
     * 请求方式，get、post等
     */
    @Excel(name = "请求方式")
    private String logMethod;

    /**
     * 响应时间
     */
    @Excel(name = "响应时间")
    private Long logTime;

    /**
     * 返回值
     */
    @Excel(name = "返回值")
    private String logResult;

    /**
     * 请求ip
     */
    @Excel(name = "请求ip" ,autoSize = true)
    private String logIp;

    /**
     * 创建时间
     */
    @Excel(name = "创建时间",autoSize = true)
    private String createdTime;

    /**
     * 创建人
     */
//    private String createdBy;

}
