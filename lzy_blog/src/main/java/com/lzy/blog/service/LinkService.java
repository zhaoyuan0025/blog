package com.lzy.blog.service;

import com.lzy.blog.pojo.Link;

import java.util.List;

/**
 * @program: lzy_blog
 * @description: 友情链接  业务层接口
 * @author: lzy
 * @create: 2020-06-06 00:29
 **/
public interface LinkService {

    /**
     * 添加
     * @param link
     */
    void save(Link link);

    /**
     * 更新
     * @param link
     */
    void update(Link link);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Link getById(Integer id);

    /**
     * 查询所有
     * @return
     */
    List<Link> getAll();

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Integer id);
}
