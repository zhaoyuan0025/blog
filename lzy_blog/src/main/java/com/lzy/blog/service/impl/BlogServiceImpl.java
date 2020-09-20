package com.lzy.blog.service.impl;


import com.lzy.blog.dao.BlogGoodsDao;
import com.lzy.blog.dao.CollectionDao;
import com.lzy.blog.mapper.BlogMapper;
import com.lzy.blog.mapper.TypeMapper;
import com.lzy.blog.pojo.*;
import com.lzy.blog.service.BlogService;
import com.lzy.blog.utils.IdWorker;
import com.lzy.blog.utils.PageUtils;
import com.lzy.blog.utils.ShiroUtils;
import com.lzy.blog.vo.BlogVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * <p>
 * 博客表服务实现类
 * </p>
 *
 * @author 稽哥
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 */
@Service
@Slf4j
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private BlogGoodsDao blogGoodsDao;

    @Autowired
    private CollectionDao collectionDao;

    /**
     * 添加
     * @param blog
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Blog blog) {
        blog.setBlogId(idWorker.nextId() + "");
        blogMapper.save(blog);

        //取出分类，当前分类博客数+1
        Integer blogType = blog.getBlogType();
        Type type = typeMapper.getById(blogType);
        type.setTypeBlogCount(type.getTypeBlogCount()+1);
        typeMapper.update(type);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public Blog getById(String id) {
        return blogMapper.getById(id);
    }

    /**
     * 更新
     * @param blog
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Blog blog) {
        //修改之前先查询
        Blog oldBlog = blogMapper.getById(blog.getBlogId());
        blogMapper.update(blog);
        //判断分类有没有被修改，旧的分类-1，新的+1
        Integer oldTypeId = oldBlog.getBlogType();
        Integer newTypeId = blog.getBlogType();
        if(!oldTypeId.equals(newTypeId)){
            //旧的
            Type oldType = typeMapper.getById(oldTypeId);
            oldType.setTypeBlogCount(oldType.getTypeBlogCount()-1);
            typeMapper.update(oldType);

            //新的
            Type nowType = typeMapper.getById(newTypeId);
            nowType.setTypeBlogCount(nowType.getTypeBlogCount()+1);
            typeMapper.update(nowType);

        }

    }

    /**
     * 阅读 根据id查询
     * 所有异常都回滚事务
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BlogVo readById(String id) {
        Blog blog = blogMapper.getById(id);
        //阅读帖子内容，需要更新阅读数
        blog.setBlogRead(blog.getBlogRead()+1);
        //更新阅读数
        blogMapper.update(blog);
        //将blog转化为blogVo
        BlogVo blogVo = new BlogVo();
        BeanUtils.copyProperties(blog,blogVo);
        //查询分类
        Type type = typeMapper.getById(blog.getBlogType());
        blogVo.setTypeName(type.getTypeName());
        return blogVo;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void deleteById(String id) {
        blogMapper.deleteById(id);
    }

    /**
     * 分页查询
     * @param page
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public PageUtils<BlogVo> getByPage(PageUtils<BlogVo> page) {
        //查询数据
        List<BlogVo> blogVoList = blogMapper.getByPage(page);
        page.setList(blogVoList);
        //查询总数
        int totalCount = blogMapper.getCountPage(page);
       page.setTotalCount(totalCount);
        return page;
    }

    @Override
    public List<BlogVo> recomRead() {

        return blogMapper.recomRead();
    }

    @Override
    public List<BlogVo> getTimeLine() {

        return blogMapper.getTimeLine();
    }

    @Override
    public void goodByBlogAndUser(BlogGoods blogGoods) {
        User user = (User) ShiroUtils.getLoginUser();
        blogGoods.setUserId(user.getUserId());
        // 取出博客id，点赞数+1
        String blogId = blogGoods.getBlogId();
        blogMapper.updateGoods(blogId);
        try {
            blogGoods.setId(idWorker.nextId() + "");
            blogGoodsDao.save(blogGoods);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getGoodsCount(String blogId) {
        User user = (User) ShiroUtils.getLoginUser();
        int count = blogGoodsDao.countByUserIdAndBlogId(user.getUserId(), blogId);
        return count;
    }

    @Override
    public void collectionByBlogId(BlogCollection blogCollection) {
        User user = (User) ShiroUtils.getLoginUser();
        blogCollection.setUserId(user.getUserId());
        blogCollection.setUser(user);
        // 查询博客
        Blog blog = blogMapper.getById(blogCollection.getBlogId());
        blog.setBlogContent(null);
        blogCollection.setBlog(blog);

        blog.setBlogCollection(blog.getBlogCollection() + 1);
        blogMapper.update(blog);
        try {
            blogCollection.setCollectionId(idWorker.nextId() + "");
            collectionDao.save(blogCollection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getCollectionCount(String blogId) {
        User user = (User) ShiroUtils.getLoginUser();
        int count = collectionDao.countByBlogIdAndUserId(blogId, user.getUserId());
        return count;
    }

    @Override
    public PageUtils<BlogCollection> getCollectionByPage(PageUtils<BlogCollection> page) {
        User user = (User) ShiroUtils.getLoginUser();
        BlogCollection blogCollection = new BlogCollection();
        blogCollection.setUserId(user.getUserId());
        Example example = Example.of(blogCollection);
        Pageable pageable = PageRequest.of(page.getCurrentPage() - 1, page.getPageSize());
        org.springframework.data.domain.Page p = collectionDao.findAll(example, pageable);
        page.setTotalCount((int) p.getTotalElements());
        page.setTotalPage(p.getTotalPages());
        page.setList(p.getContent());
        return page;
    }
}
