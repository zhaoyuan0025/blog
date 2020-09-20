package com.lzy.blog.realm;

import com.lzy.blog.enums.ResultEnum;
import com.lzy.blog.enums.StateEnum;
import com.lzy.blog.exception.BlogException;
import com.lzy.blog.pojo.Admin;
import com.lzy.blog.pojo.User;
import com.lzy.blog.service.AdminService;
import com.lzy.blog.service.UserService;
import com.lzy.blog.token.UsernamePasswordToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: lzy_blog
 * @description: 管理员的授权和登录
 * @author: lzy
 * @create: 2020-06-06 23:05
 **/
public class AdminRealm extends AuthorizingRealm {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 登录认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken =(UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();

        //获取principal，根据类型去判断是前台登录还是后台管理员登录
        int state = usernamePasswordToken.getState();
        if(state == StateEnum.ADMIN.getCode()){
            //接收用户参数
            Admin admin = adminService.getByUserName(username);
            //判断
            if(null == admin){
                throw new BlogException(ResultEnum.ERROR.getCode(),"用户不存在");
            }
            return new SimpleAuthenticationInfo(admin,admin.getPassword(),this.getName());

        }else{
            //普通用户
            User user = userService.getByUsername(username);
            if(user ==null || user.getDeleted() == 1){
                throw new BlogException(ResultEnum.ERROR.getCode(),"用户不存在");
            }
            return new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
        }
    }
}
