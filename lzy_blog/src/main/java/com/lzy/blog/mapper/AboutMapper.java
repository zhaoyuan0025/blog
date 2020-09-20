package com.lzy.blog.mapper;

import com.lzy.blog.pojo.About;
import com.lzy.blog.utils.PageUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: lzy_blog
 * @description: 关于 dao接口
 * @author: lzy
 * @create: 2020-06-05 23:57
 **/
@Component
public interface AboutMapper {

    /**
     * 添加
     * @param about
     */
    void save(About about);

    /**
     * 更新
     * @param about
     */
    void updateById(About about);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    About getById(Integer id);

    /**
     * 查询关于
     * @return
     */
    About getAbout();

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 更新启用状态
     * @param about
     */
    void updateEnable(About about);

    /**
     * 分页查询
     * @param page
     * @return
     */
    List<About> getByPage(PageUtils<About> page);

    /**
     * 查询总数
     * @param page
     * @return
     */
    int getCountPage(PageUtils<About> page);
}
