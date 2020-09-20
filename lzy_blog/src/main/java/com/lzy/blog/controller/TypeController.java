package com.lzy.blog.controller;

import com.lzy.blog.pojo.Type;
import com.lzy.blog.service.TypeService;
import com.lzy.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: lzy_blog
 * @description: 帖子的分类模块
 * @author: lzy
 * @create: 2020-06-22 00:05
 **/
@RestController
@RequestMapping("/type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    /**
     * 添加类型
     * @param type
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result<Object> save(@RequestBody Type type){
        typeService.save(type);
        return new Result<>("添加成功");
    }

    /**
     * 后台查询所有
     * @return
     */
    @RequestMapping(value = "/listBack",method = RequestMethod.GET)
    public Result<List<Type>> list(){
        List<Type> typeList = typeService.getAll();
        return new Result<>(typeList);
    }

    /**
     * 前台查询所有
     * @return
     */
    @RequestMapping(value = "/getList",method = RequestMethod.GET)
    public Result<List<Type>> getList(){
        List<Type> typeList = typeService.getTypeList();
        return new Result<>(typeList);
    }

    /**
     * 根据id更新
     * @param type
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public Result<Object> update(@RequestBody Type type){
        typeService.update(type);
        return new Result<>("更新成功");
    }

    /**
     *根据id启用
     * @param id
     * @return
     */
    @RequestMapping(value = "/enable/{id}",method = RequestMethod.PUT)
    public Result<Object> enable(@PathVariable Integer id){
        typeService.enableById(id);
        return new Result<>("已经启用");
    }

    /**
     * 根据id弃用
     * @param id
     * @return
     */
    @RequestMapping(value = "/disable/{id}",method = RequestMethod.PUT)
    public Result<Object> disable(@PathVariable Integer id){

        typeService.disableById(id);
        return new Result<>("已经弃用");
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/getById/{id}",method = RequestMethod.GET)
    public Result<Object> getById(@PathVariable Integer id){

        Type type = typeService.getById(id);
        return new Result<>(type);
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public Result<Object> delete(@PathVariable Integer id){
        typeService.deleteById(id);
        return new Result<>("删除成功！");
    }
}
