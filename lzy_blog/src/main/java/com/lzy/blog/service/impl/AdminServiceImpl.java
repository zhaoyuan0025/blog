package com.lzy.blog.service.impl;

import com.lzy.blog.mapper.AdminMapper;
import com.lzy.blog.pojo.Admin;
import com.lzy.blog.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员表服务实现类
 * </p>
 *
 * @author 稽哥
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 *
 */
@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin getByUserName(String username) {
        return adminMapper.getByUserName(username);
    }

    /**
     * 插损管理员
     * @return
     */
    @Override
    public Admin getAdmin() {
        return adminMapper.getAdmin();
    }

    /**
     * 更新密码
     * @param admin
     */
    @Override
    public void updatePassword(Admin admin) {
        //先查询在更新
        Admin oldAdmin = adminMapper.getAdmin();
        oldAdmin.setPassword(admin.getPassword());
        adminMapper.update(oldAdmin);
    }

    /**
     * 更新个人信息
     * @param admin
     */
    @Override
    public void updateInfo(Admin admin) {
        adminMapper.update(admin);
    }
}
