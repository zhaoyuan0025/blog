package com.lzy.blog.service;

import com.lzy.blog.pojo.Admin;

/**
 * <p>
 * 管理员表服务层接口
 * </p>
 *
 * @author 稽哥
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 *
 */
public interface AdminService {

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    Admin getByUserName(String username);

    /**
     * 查询管理员
     * @return
     */
    Admin getAdmin();

    /**
     * 更新个人信息
     * @param admin
     */
    void updateInfo(Admin admin);

    /**
     * 更新密码
     * @param admin
     */
    void updatePassword(Admin admin);
}
