package com.lzy.blog.service;

import com.lzy.blog.pojo.About;
import com.lzy.blog.utils.PageUtils;

/**
 * @program: lzy_blog
 * @description: 关于  业务层接口
 * @author: lzy
 * @create: 2020-06-06 00:29
 **/
public interface AboutService {

    /**
     * 添加
     * @param about
     */
    void save(About about);

    /**
     * 更新
     * @param about
     */
    void update(About about);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    About getById(Integer id);

    /**
     * 阅读
     * @return
     */
    About read();

    /**
     * 根据删除
     * @param id
     */
    void delete(Integer id);

    /**
     * 根据ID启用
     * @param id
     */
    void enableById(Integer id);

    /**
     * 根据id弃用
     * @param id
     */
    void disenableById(Integer id);

    /**
     * 分页查询
     * @param page
     * @return
     */
    PageUtils<About> getByPage(PageUtils<About> page);
}
