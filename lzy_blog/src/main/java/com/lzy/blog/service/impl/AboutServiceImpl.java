package com.lzy.blog.service.impl;

import com.lzy.blog.enums.ResultEnum;
import com.lzy.blog.enums.StateEnum;
import com.lzy.blog.exception.BlogException;
import com.lzy.blog.mapper.AboutMapper;
import com.lzy.blog.pojo.About;
import com.lzy.blog.service.AboutService;
import com.lzy.blog.utils.PageUtils;
import com.lzy.blog.vo.BlogVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: lzy_blog
 * @description: 实现类
 * @author: lzy
 * @create: 2020-06-06 00:31
 **/
@Service
@Slf4j
public class AboutServiceImpl implements AboutService {

    @Autowired
    private AboutMapper aboutMapper;

    /**
     * 添加
     * @param about
     */
    @Override
    public void save(About about) {
        aboutMapper.save(about);
    }

    /**
     * 更新
     * @param about
     */
    @Override
    public void update(About about) {

        aboutMapper.updateById(about);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public About getById(Integer id) {
        return aboutMapper.getById(id);
    }

    /**
     * 阅读
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public About read() {
        About about = aboutMapper.getAbout();
        if(about == null){
            return null;
        }
        about.setAboutRead(about.getAboutRead()+1);
        aboutMapper.updateById(about);
        return about;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Integer id) {
        aboutMapper.deleteById(id);
    }

    /**
     * 根据id启用
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enableById(Integer id) {
        //先查询是否已经存在关于我的
        About about = aboutMapper.getAbout();
        if(about != null){
            throw new BlogException(ResultEnum.ERROR.getCode(),"当前已经存在关于我的");
        }
        about = aboutMapper.getById(id);
        about.setEnable(StateEnum.ENABLE.getCode());
        aboutMapper.updateEnable(about);
    }

    /**
     * 根据id弃用
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disenableById(Integer id) {
        //直接弃用
        About about = aboutMapper.getById(id);
        about.setEnable(StateEnum.NOT_ENABLE.getCode());
        aboutMapper.updateEnable(about);
    }

    /**
     * 分页查询
     * @param page
     * @return
     */
    @Override
    public PageUtils<About> getByPage(PageUtils<About> page) {
        //查询数据
        List<About> aboutList = aboutMapper.getByPage(page);
        page.setList(aboutList);
        //查询总数
        int totalCount = aboutMapper.getCountPage(page);
        page.setTotalCount(totalCount);
        return page;
    }
}
