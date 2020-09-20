package com.lzy.blog.controller;

import com.lzy.blog.enums.ResultEnum;
import com.lzy.blog.enums.StateEnum;
import com.lzy.blog.pojo.Admin;
import com.lzy.blog.service.AdminService;
import com.lzy.blog.token.UsernamePasswordToken;
import com.lzy.blog.utils.Result;
import com.lzy.blog.utils.ShiroUtils;
import com.lzy.blog.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;

import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: lzy_blog
 * @description: 管理员登录
 * @author: lzy
 * @create: 2020-06-06 22:50
 **/
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result<Object> login(@RequestBody Admin admin){
        //对数据进行校验
        if(null == admin || StringUtils.isBlank(admin.getUsername()) || StringUtils.isBlank(admin.getPassword())){
            return new Result<>(ResultEnum.PARAMS_NULL.getCode(),"用户名或者密码或者密码错误");
        }

        //通过shiro来实现验证授权
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(admin.getUsername(), admin.getPassword(), StateEnum.ADMIN.getCode());
        try {
            subject.login(token);
        }catch (Exception e){
            e.printStackTrace();
            return new Result<>(ResultEnum.PARAMS_NULL.getCode(),"用户名或者密码或者密码错误");
        }
        //登录成功
        Serializable id = subject.getSession().getId();
        Map<String, Object> map = new HashMap<>(2);
        map.put("token",id);
        //将token返回
        return new Result<>(map);
    }

    /**
     * 获取当前登录用户的信息
     * @return
     */
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public Result<Admin> getLoginInfo(){
        Admin loginAdmin = (Admin) ShiroUtils.getLoginUser();
        //将用户的密码信息隐藏
        loginAdmin.setPassword("");
        return new Result<>(loginAdmin);
    }

    /**
     * 查询管理员
     * @return
     */
    @RequestMapping(value = "/getAdmin",method = RequestMethod.GET)
    public Result<Admin> getAdmin(){
        Admin admin = adminService.getAdmin();
        return new Result<>(admin);
    }

    /**
     * 更新个人信息
     * @param admin
     * @return
     */
    @RequestMapping(value = "/updateInfo",method = RequestMethod.PUT)
    public Result<Object> updateInfo(@RequestBody Admin admin){
        adminService.updateInfo(admin);
        return new Result<>("更新成功！");
    }

    /**
     * 更新密码
     * @param admin
     * @return
     */
    @RequestMapping(value = "/updatePassword",method = RequestMethod.PUT)
    public Result<Object> updatePassword(@RequestBody Admin admin){
        adminService.updatePassword(admin);
        return new Result<>("更新密码成功！");
    }
}
