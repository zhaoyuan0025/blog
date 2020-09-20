package com.lzy.blog.interceptor;

import com.lzy.blog.enums.ResultEnum;
import com.lzy.blog.exception.BlogException;
import com.lzy.blog.utils.ShiroUtils;
import com.lzy.blog.utils.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: lzy_blog
 * @description: 拦截器
 * @author: lzy
 * @create: 2020-06-06 23:30
 **/
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 放行的白名单
     */
    private static String[] whiteList = {
            "/admin/login",
            "/user/login",
            "/user/register",
            "/link/list",
            "/music/getList",
            "/about/read",
            "/type/getList",
            "/blog/recomRead",
            "/blog/read",
            "/blog/getTimeLine",
            "/blog/getByPage",
            "/comment/getByBlog",
            "/admin/getAdmin"
    };

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (containsWhiteList(request.getRequestURI())) {
            return true;
        }
        //获取token
        String token = request.getHeader("Authorization");
        if (StringUtils.isNotBlank(token)) {
            //如果token不为空，就获取当前登录的用户
            Object loginUser = ShiroUtils.getLoginUser();
            if (null != loginUser) {
                //如果loginadmin不为空，则说明token有效
                return true;
            }
        }

        //没有登录
        throw new BlogException(ResultEnum.NOT_LOGIN);
//        return false;
    }

    /**
     * 判断url是否在白名单内
     *
     * @param s
     * @return
     */
    private boolean containsWhiteList(String s) {
        for (String url : whiteList) {
            if (s.contains(url)) {
                return true;
            }
        }
        return false;
    }
}
