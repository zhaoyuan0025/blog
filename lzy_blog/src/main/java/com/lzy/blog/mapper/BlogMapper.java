package com.lzy.blog.mapper;


import com.lzy.blog.pojo.Blog;
import com.lzy.blog.utils.PageUtils;
import com.lzy.blog.vo.BlogVo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 博客表Mapper
 * </p>
 *
 * @author 稽哥
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 *
 */
@Component
public interface BlogMapper{

    /**
     * 添加
     * @param blog
     */
    void save(Blog blog);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Blog getById(String id);

    /**
     * 更新
     * @param blog
     */
    void update(Blog blog);

    /**
     * 删除
     * @param id
     */
    void deleteById(String id);

    /**
     * 分页查询
     * @param page
     * @return
     */
    List<BlogVo> getByPage(PageUtils<BlogVo> page);

    /**
     * 查询总数
     * @param page
     * @return
     */
    int getCountPage(PageUtils<BlogVo> page);

    /**
     * 推荐阅读
     * @return
     */
    List<BlogVo> recomRead();

    /**
     * 查询时间轴
     * @return
     */
    List<BlogVo> getTimeLine();

    /**
     * 更新点赞量
     * @param blogId
     */
    void updateGoods(String blogId);
}
