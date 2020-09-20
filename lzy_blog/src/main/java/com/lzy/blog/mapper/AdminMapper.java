package com.lzy.blog.mapper;

import com.lzy.blog.pojo.Admin;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 管理员表Mapper
 * </p>
 *
 * @author 稽哥
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 *
 */
@Component
public interface AdminMapper {

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    Admin getByUserName(String username);

    /**
     * 插损管理员的信息
     * @return
     */
    Admin getAdmin();

    /**
     * 更新个人信息
     * @param admin
     */
    void update(Admin admin);
}
