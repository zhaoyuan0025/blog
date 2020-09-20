package com.lzy.blog.mapper;

import com.lzy.blog.pojo.Music;
import com.lzy.blog.utils.PageUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: lzy_blog
 * @description: 音乐鉴赏 dao接口
 * @author: lzy
 * @create: 2020-06-05 23:57
 **/
@Component
public interface MusicMapper {

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
    void deleteById(Integer id);

    /**
     * 查询分页
     * @param page
     * @return
     */
    List<Music> getByPage(PageUtils<Music> page);

    /**
     * 查询总数
     * @param page
     * @return
     */
    int getCountPage(PageUtils<Music> page);

    /**
     * 前台查询
     * @return
     */
    List<Music> getList();
}
