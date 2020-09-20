package com.lzy.blog.service.impl;

import com.lzy.blog.mapper.LinkMapper;
import com.lzy.blog.pojo.Link;
import com.lzy.blog.service.AboutService;
import com.lzy.blog.service.LinkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: lzy_blog
 * @description: 实现类
 * @author: lzy
 * @create: 2020-06-06 00:31
 **/
@Service
@Slf4j
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkMapper linkMapper;

    /**
     * 根据id删除
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        linkMapper.deleteById(id);
    }

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<Link> getAll() {
        return linkMapper.getAll();
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public Link getById(Integer id) {
        return linkMapper.getById(id);
    }

    /**
     * 更新
     * @param link
     */
    @Override
    public void update(Link link) {
        linkMapper.update(link);
    }

    /**
     * 添加
     * @param link
     */
    @Override
    public void save(Link link) {
        linkMapper.save(link);
    }
}
