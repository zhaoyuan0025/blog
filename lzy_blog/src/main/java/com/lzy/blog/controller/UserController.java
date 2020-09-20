package com.lzy.blog.controller;

import com.lzy.blog.enums.ResultEnum;
import com.lzy.blog.enums.StateEnum;
import com.lzy.blog.pojo.User;
import com.lzy.blog.service.UserService;
import com.lzy.blog.token.UsernamePasswordToken;
import com.lzy.blog.utils.PageUtils;
import com.lzy.blog.utils.Result;
import com.lzy.blog.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: lzy_blog
 * @description: 用户模块
 * @author: lzy
 * @create: 2020-07-06 22:16
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 添加
     * @param user
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result<Object> save(@RequestBody User user){
        userService.save(user);
        return new Result<>("添加成功");
    }

    /**
     * 更新
     * @param user
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public Result<Object> update(@RequestBody User user){
        userService.update(user);
        return new Result<>("更新成功！");
    }

    /**
     * 通过id查询 houtai
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    public Result<User> get(@PathVariable Integer id){
        User user = userService.getById(id);
        return new Result<>(user);
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public Result<Object> delete(@PathVariable Integer id){
        userService.delete(id);
        return new Result<>("删除成功！");
    }

    /**
     * 分页查询
     * @param page
     * @return
     */
    @RequestMapping(value = "/getByPage",method = RequestMethod.POST)
    public Result<PageUtils<User>> getBuPage(@RequestBody PageUtils<User> page){
        String sortColumn = page.getSortColumn();
        //判断排序列是否为空
        if(StringUtils.isNotBlank(sortColumn)){
            //不为空，用数组存储可以用来排序的字段
            String[] columns = {"sex","created_time","update_time"};
            List<String> sortList = Arrays.asList(columns);
            //如果集合中不包含排序的字段
            if(!sortList.contains(sortColumn.toLowerCase())){
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(),"排序的参数不合法！");
            }
        }
        page = userService.getByPage(page);
        return new Result<>(page);
    }

    /**
     * 重置密码
     * @param userIds
     * @return
     */
    @RequestMapping(value = "/resetPwd",method = RequestMethod.PUT)
    public Result<Object> resetPwd(@RequestBody List<Integer> userIds){
        userService.resetPwd(userIds);
        return new Result<>("重置密码成功！");
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Result<Object> register(@RequestBody User user){
        userService.reister(user);
        return new Result<>("注册成功");
    }

    /**
     * 登录
     * @param user
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result<Object> login(@RequestBody User user){
        //对数据进行校验
        if(null == user || StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())){
            return new Result<>(ResultEnum.PARAMS_NULL.getCode(),"用户名或者密码或者密码错误");
        }

        //通过shiro来实现验证授权
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword(), StateEnum.USER.getCode());
        try {
            subject.login(token);
        }catch (Exception e){
            e.printStackTrace();
            return new Result<>(ResultEnum.PARAMS_NULL.getCode(),"用户名或者密码或者密码错误");
        }
        //登录成功
        Serializable id = subject.getSession().getId();
        User u = (User) subject.getPrincipal();
        u.setPassword("");
        u.setDeleted(null);
        Map<String, Object> map = new HashMap<>(2);
        map.put("token",id);
        map.put("user",u);
        //将token返回
        return new Result<>(map);
    }
}
