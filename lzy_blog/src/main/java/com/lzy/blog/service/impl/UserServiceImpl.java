package com.lzy.blog.service.impl;


import com.lzy.blog.enums.ResultEnum;
import com.lzy.blog.exception.BlogException;
import com.lzy.blog.mapper.UserMapper;
import com.lzy.blog.pojo.User;
import com.lzy.blog.service.UserService;
import com.lzy.blog.utils.Md5Utils;
import com.lzy.blog.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表服务实现类
 * </p>
 *
 * @author 稽哥
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 *
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void save(User user) {
        userMapper.save(user);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public User getById(Integer id) {
        return userMapper.getById(id);
    }

    @Override
    public PageUtils<User> getByPage(PageUtils<User> page) {
        //查询数据
        List<User> userList = userMapper.getByPage(page);
        page.setList(userList);
        //查询总数
        int totalCount = userMapper.getCountPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    /**
     * 密码重置为123456
     * @param userIds
     */
    @Override
    public void resetPwd(List<Integer> userIds) {
        //先查询出来在修改，可以保证数据的完整性
        List<User> userList = userMapper.getByIds(userIds);
        userList.forEach(e ->{
            e.setPassword(Md5Utils.toMD5("123456"));
            //循环修改数据
            userMapper.update(e);
        });

    }

    @Override
    public void reister(User user) {
        //现根据用户名和账号查询用户是否存在
        User u = userMapper.getByUsername(user.getUsername());
        //如果存在
        if(u != null){
            throw new BlogException(ResultEnum.PARAMS_ERROR.getCode(),"用户已经存在");
        }
        //不存在，插入数据
        userMapper.save(user);
    }

    @Override
    public User getByUsername(String username) {
        return userMapper.getByUsername(username);
    }

    @Override
    public void delete(Integer id) {
        userMapper.deleteById(id);
    }
}
