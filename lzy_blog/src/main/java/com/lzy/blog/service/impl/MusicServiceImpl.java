package com.lzy.blog.service.impl;

import com.lzy.blog.enums.StateEnum;
import com.lzy.blog.mapper.MusicMapper;
import com.lzy.blog.pojo.About;
import com.lzy.blog.pojo.Music;
import com.lzy.blog.service.AboutService;
import com.lzy.blog.service.MusicService;
import com.lzy.blog.utils.PageUtils;
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
public class MusicServiceImpl implements MusicService {

    @Autowired
    private MusicMapper musicMapper;

    @Override
    public void save(Music music) {
        musicMapper.save(music);
    }

    @Override
    public Music getById(Integer id) {
        return musicMapper.getById(id);
    }

    @Override
    public void delete(Integer id) {
        musicMapper.deleteById(id);
    }

    @Override
    public void enableById(Integer id) {
        //先查询
        Music music = musicMapper.getById(id);
        music.setEnable(StateEnum.ENABLE.getCode());

        //在更新
        musicMapper.update(music);
    }

    @Override
    public void disenableById(Integer id) {
        //先查询
        Music music = musicMapper.getById(id);
        music.setEnable(StateEnum.NOT_ENABLE.getCode());

        //在更新
        musicMapper.update(music);
    }

    @Override
    public PageUtils<Music> getByPage(PageUtils<Music> page) {
        //查询数据
        List<Music> musicList = musicMapper.getByPage(page);
        page.setList(musicList);
        //查询总数
        int totalCount = musicMapper.getCountPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    @Override
    public List<Music> getList() {

        return musicMapper.getList();
    }

    @Override
    public void update(Music music) {
        musicMapper.update(music);
    }
}
