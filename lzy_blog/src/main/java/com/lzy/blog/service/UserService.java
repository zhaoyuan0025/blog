package com.lzy.blog.service;

import com.lzy.blog.pojo.User;
import com.lzy.blog.utils.PageUtils;

import java.util.List;

/**
 * <p>
 * 用户表服务层接口
 * </p>
 *
 * @author 稽哥
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 *
 */
public interface UserService {

    /**
     * 添加
     * @param user
     */
    void save(User user);

    /**
     * 更新
     * @param user
     */
    void update(User user);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    User getById(Integer id);

    /**
     * 根据id删除
     * @param id
     */
    void delete(Integer id);

    /**
     * 分页查询
     * @param page
     * @return
     */
    PageUtils<User> getByPage(PageUtils<User> page);

    /**
     * 密码重置
     * @param userIds
     */
    void resetPwd(List<Integer> userIds);

    /**
     * 注册
     * @param user
     */
    void reister(User user);

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    User getByUsername(String username);
}
