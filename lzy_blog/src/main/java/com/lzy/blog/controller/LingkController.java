package com.lzy.blog.controller;

import com.lzy.blog.pojo.Link;
import com.lzy.blog.service.LinkService;
import com.lzy.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: lzy_blog
 * @description: 友情链接
 * @author: lzy
 * @create: 2020-07-05 12:45
 **/
@RestController
@RequestMapping("/link")
public class LingkController {

    @Autowired
    private LinkService linkService;

    /**
     * 添加
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result<Object> save(@RequestBody Link link){
        linkService.save(link);
        return new Result<>("添加成功");
    }

    /**
     * 更新
     * @param link
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public Result<Object> update(@RequestBody Link link){
        linkService.update(link);
        return new Result<>("修改成功");
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    public Result<Object> get(@PathVariable Integer id){
        Link link = linkService.getById(id);
        return new Result<>(link);
    }

    /**
     * 查询所有
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Result<List<Link>> list(){
        List<Link> links = linkService.getAll();
        return new Result<>(links);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public Result<Object> delete(@PathVariable Integer id){
        linkService.deleteById(id);
        return new Result<>("删除成功！");
    }

}
