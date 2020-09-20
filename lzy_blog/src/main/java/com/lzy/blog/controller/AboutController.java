package com.lzy.blog.controller;

import com.lzy.blog.enums.ResultEnum;
import com.lzy.blog.pojo.About;
import com.lzy.blog.service.AboutService;
import com.lzy.blog.utils.PageUtils;
import com.lzy.blog.utils.Result;
import com.lzy.blog.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @program: lzy_blog
 * @description: 关于
 * @author: lzy
 * @create: 2020-07-06 13:55
 **/
@RestController
@RequestMapping("/about")
public class AboutController {

    @Autowired
    private AboutService aboutService;

    /**
     * 添加
     * @param about
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result<Object> save(@RequestBody About about){
        aboutService.save(about);
        return new Result<>("添加成功");
    }

    /**
     * 更新
     * @param about
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public Result<Object> update(@RequestBody About about){
        aboutService.update(about);
        return new Result<>("更新成功！");
    }

    /**
     * 通过id查询 houtai
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    public Result<About> get(@PathVariable Integer id){
        About about = aboutService.getById(id);
        return new Result<>(about);
    }

    /**
     * 阅读
     * @return
     */
    @RequestMapping(value = "/read",method = RequestMethod.GET)
    public Result<About> read(){
        About about = aboutService.read();
        return new Result<>(about);
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public Result<Object> delete(@PathVariable Integer id){
        aboutService.delete(id);
        return new Result<>("删除成功！");
    }

    /**
     * 更新启用状态
     * @param id
     * @return
     */
    @RequestMapping(value = "/enable/{id}",method = RequestMethod.PUT)
    public Result<Object> enable(@PathVariable Integer id){
        aboutService.enableById(id);
        return new Result<>("启用成功！");
    }

    /**
     * 更新弃用状态
     * @param id
     * @return
     */
    @RequestMapping(value = "/disEnable/{id}",method = RequestMethod.PUT)
    public Result<Object> disEnable(@PathVariable Integer id){
        aboutService.disenableById(id);
        return new Result<>("弃用成功！");
    }

    /**
     * 分页查询
     * @param page
     * @return
     */
    @RequestMapping(value = "/getByPage",method = RequestMethod.POST)
    public Result<PageUtils<About>> getBuPage(@RequestBody PageUtils<About> page){
        String sortColumn = page.getSortColumn();
        //判断排序列是否为空
        if(StringUtils.isNotBlank(sortColumn)){
            //不为空，用数组存储可以用来排序的字段
            String[] columns = {"about_read","created_time","update_time"};
            List<String> sortList = Arrays.asList(columns);
            //如果集合中不包含排序的字段
            if(!sortList.contains(sortColumn.toLowerCase())){
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(),"排序的参数不合法！");
            }
        }
        page = aboutService.getByPage(page);
        return new Result<>(page);
    }
}
