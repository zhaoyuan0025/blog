package com.lzy.blog.controller;

import com.lzy.blog.enums.ResultEnum;
import com.lzy.blog.pojo.Music;
import com.lzy.blog.service.MusicService;
import com.lzy.blog.utils.PageUtils;
import com.lzy.blog.utils.Result;
import com.lzy.blog.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @program: lzy_blog
 * @description: 音乐鉴赏
 * @author: lzy
 * @create: 2020-07-06 15:56
 **/
@RestController
@RequestMapping("/music")
public class MusicController {

    @Autowired
    private MusicService musicService;

    /**
     * 添加
     * @param music
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result<Object> save(@RequestBody Music music){
        musicService.save(music);
        return new Result<>("添加成功");
    }

    /**
     * 更新
     * @param music
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public Result<Object> update(@RequestBody Music music){
        musicService.update(music);
        return new Result<>("更新成功！");
    }

    /**
     * 通过id查询 houtai
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    public Result<Music> get(@PathVariable Integer id){
        Music music = musicService.getById(id);
        return new Result<>(music);
    }
    
    /**
     * 根据id删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public Result<Object> delete(@PathVariable Integer id){
        musicService.delete(id);
        return new Result<>("删除成功！");
    }

    /**
     * 更新启用状态
     * @param id
     * @return
     */
    @RequestMapping(value = "/enable/{id}",method = RequestMethod.PUT)
    public Result<Object> enable(@PathVariable Integer id){
        musicService.enableById(id);
        return new Result<>("启用成功！");
    }

    /**
     * 更新弃用状态
     * @param id
     * @return
     */
    @RequestMapping(value = "/disEnable/{id}",method = RequestMethod.PUT)
    public Result<Object> disEnable(@PathVariable Integer id){
        musicService.disenableById(id);
        return new Result<>("弃用成功！");
    }

    /**
     * 分页查询
     * @param page
     * @return
     */
    @RequestMapping(value = "/getByPage",method = RequestMethod.POST)
    public Result<PageUtils<Music>> getBuPage(@RequestBody PageUtils<Music> page){
        String sortColumn = page.getSortColumn();
        //判断排序列是否为空
        if(StringUtils.isNotBlank(sortColumn)){
            //不为空，用数组存储可以用来排序的字段
            String[] columns = {"artist","created_time","enable"};
            List<String> sortList = Arrays.asList(columns);
            //如果集合中不包含排序的字段
            if(!sortList.contains(sortColumn.toLowerCase())){
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(),"排序的参数不合法！");
            }
        }
        page = musicService.getByPage(page);
        return new Result<>(page);
    }

    /**
     * 前台查询
     * @param
     * @return
     */
    @RequestMapping(value = "/getList",method = RequestMethod.GET)
    public Result<List<Music>> getList(){
        List<Music> musicList = musicService.getList();
        return new Result<>(musicList);
    }

}
