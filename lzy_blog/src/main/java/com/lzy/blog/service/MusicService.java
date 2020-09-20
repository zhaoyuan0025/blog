package com.lzy.blog.service;

import com.lzy.blog.pojo.Music;
import com.lzy.blog.utils.PageUtils;

import java.util.List;

/**
 * @program: lzy_blog
 * @description: 音乐鉴赏  业务层接口
 * @author: lzy
 * @create: 2020-06-06 00:29
 **/
public interface MusicService {

    /**
     * 添加
     * @param music
     */
    void save(Music music);

    /**
     * 更新
     * @param music
     */
    void update(Music music);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Music getById(Integer id);

    /**
     * 根据id删除
     * @param id
     */
    void delete(Integer id);

    /**
     * 启用状态
     * @param id
     */
    void enableById(Integer id);

    /**
     * 弃用
     * @param id
     */
    void disenableById(Integer id);

    /**
     * 分页查询
     * @param page
     * @return
     */
    PageUtils<Music> getByPage(PageUtils<Music> page);

    /**
     * 前台查询
     * @return
     */
    List<Music> getList();
}
