package com.lzy.blog.service;


import com.lzy.blog.pojo.Blog;
import com.lzy.blog.pojo.BlogCollection;
import com.lzy.blog.pojo.BlogGoods;
import com.lzy.blog.utils.PageUtils;
import com.lzy.blog.vo.BlogVo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * <p>
 * 博客表服务层接口
 * </p>
 *
 * @author 稽哥
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 *
 */
public interface BlogService {


    /**
     * 添加
     * @param blog
     */
    void save(Blog blog);

    /**
     * 根据id查询  后台的
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
     * 根据id查询 阅读
     * @param id
     * @return
     */
    BlogVo readById(String id);

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
    PageUtils<BlogVo> getByPage(PageUtils<BlogVo> page);

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
     * 根据博客id和用户id点赞
     * @param blogGoods
     */
    void goodByBlogAndUser(BlogGoods blogGoods);

    /**
     * 获取点赞数
     * @param blogId
     * @return
     */
    int getGoodsCount(String blogId);

    /**
     * 收藏
     * @param blogCollection
     */
    void collectionByBlogId(BlogCollection blogCollection);

    /**
     * 查询收藏数
     * @param blogId
     * @return
     */
    int getCollectionCount(String blogId);

    /**
     * 分页查询收藏
     * @param page
     * @return
     */
    PageUtils<BlogCollection> getCollectionByPage(PageUtils<BlogCollection> page);
}
