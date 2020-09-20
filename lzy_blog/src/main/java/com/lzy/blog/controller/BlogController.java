package com.lzy.blog.controller;

import com.lzy.blog.enums.ResultEnum;
import com.lzy.blog.mapper.BlogMapper;
import com.lzy.blog.pojo.Blog;
import com.lzy.blog.pojo.BlogCollection;
import com.lzy.blog.pojo.BlogGoods;
import com.lzy.blog.service.BlogService;
import com.lzy.blog.utils.PageUtils;
import com.lzy.blog.utils.Result;
import com.lzy.blog.utils.StringUtils;
import com.lzy.blog.vo.BlogVo;
import com.lzy.blog.vo.TimeLineVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @program: lzy_blog
 * @description: 博客的相关功能
 * @author: lzy
 * @create: 2020-07-05 22:34
 **/
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    /**
     * 添加
     * @param blog
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result<Object> save(@RequestBody Blog blog){
        blogService.save(blog);
        return new Result<>("添加成功！");
    }

    /**
     * 根据id查询  后台用户
     * @return
     */
    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    public Result<Blog> get(@PathVariable String id){
        Blog blog = blogService.getById(id);
        return new Result<>(blog);
    }

    /**
     * 更新
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public Result<Object> update(@RequestBody Blog blog){
        blogService.update(blog);
        return new Result<>("更新成功！");
    }

    /**
     * 根据id查询，阅读
     * @param id
     * @return
     */
    @RequestMapping(value = "/read/{id}",method = RequestMethod.GET)
    public Result<BlogVo> read(@PathVariable String id){
        BlogVo blog = blogService.readById(id);
        return new Result<>(blog);
    }

    /**
     * 删除
     * @return
     */
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public Result<Object> delete(@PathVariable String id){
        blogService.deleteById(id);
        return new Result<>("删除成功");
    }

    /**
     * 分页查询
     * @param page
     * @return
     */
    @RequestMapping(value = "/getByPage",method = RequestMethod.POST)
    public Result<PageUtils<BlogVo>> getByPage(@RequestBody PageUtils<BlogVo> page){
        String sortColumn = page.getSortColumn();
        //判断排序列是否为空
        if(StringUtils.isNotBlank(sortColumn)){
            //不为空，用数组存储可以用来排序的字段
            String[] columns = {"blog_goods","blog_read","blog_collection",
                    "type_name","blog_comment","created_time","update_time"};
            List<String> sortList = Arrays.asList(columns);
            //如果集合中不包含排序的字段
            if(!sortList.contains(sortColumn.toLowerCase())){
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(),"排序的参数不合法！");
            }
        }
        page = blogService.getByPage(page);
        return new Result<>(page);
    }

    /**
     * 推荐阅读
     * @param
     * @return
     */
    @RequestMapping(value = "/recomRead",method = RequestMethod.GET)
    public Result<List<BlogVo>> recomRead(){
        List<BlogVo> blogList = blogService.recomRead();
        return new Result<>(blogList);
    }

    /**
     * 查询时间轴
     * @return
     */
    @RequestMapping(value = "/getTimeLine",method = RequestMethod.GET)
    public Result<List<TimeLineVo>> getTimeLine(){
        List<TimeLineVo> timeList = new ArrayList<>(16);
        List<BlogVo> blogVoList = blogService.getTimeLine();
        blogVoList.forEach(e -> {
            String blogMonth = e.getBlogMonth();
            TimeLineVo timeLineVo = new TimeLineVo();
            timeLineVo.setMonth(blogMonth);
            if(timeList.contains(timeLineVo)) {
                // 取出对应的数据
                TimeLineVo timeLine = getTimeLineForList(timeList, timeLineVo);
                List<BlogVo> list = timeLine.getList();
                if(list == null) {
                    list = new ArrayList<>(8);
                }
                list.add(e);
                timeLine.setList(list);
            } else {
                List<BlogVo> list = timeLineVo.getList();
                if(list == null) {
                    list = new ArrayList<>(8);
                }
                list.add(e);
                timeLineVo.setList(list);
                timeList.add(timeLineVo);
            }
        });
        return new Result<>(timeList);
    }


    /**
     * 获取对应的timeLine
     * @param timeList
     * @param timeLineVo
     * @return
     */
    private TimeLineVo getTimeLineForList(List<TimeLineVo> timeList, TimeLineVo timeLineVo) {
        for (TimeLineVo lineVo : timeList) {
            if(timeLineVo.getMonth().equals(lineVo.getMonth())) {
                return lineVo;
            }
        }
        return null;
    }

    /**
     * 点赞
     * @param blogGoods
     * @return
     */
    @RequestMapping(value = "/good", method = RequestMethod.POST)
    public Result<Object> good(@RequestBody BlogGoods blogGoods) {
        if(StringUtils.isBlank(blogGoods.getBlogId())) {
            return new Result<>("博客id不能为空！");
        }
        blogService.goodByBlogAndUser(blogGoods);
        return new Result<>("点赞成功！");
    }

    /**
     * 根据博客id和当前登录用户查询点赞记录
     * @param blogId
     * @return
     */
    @RequestMapping(value = "/getGood/{blogId}", method = RequestMethod.GET)
    public Result<Integer> getGood(@PathVariable String blogId) {
        int count = blogService.getGoodsCount(blogId);
        return new Result<>(count);
    }
    /**
     * 收藏
     * @param blogCollection
     * @return
     */
    @RequestMapping(value = "/collection", method = RequestMethod.POST)
    public Result<Object> collection(@RequestBody BlogCollection blogCollection) {
        if(StringUtils.isBlank(blogCollection.getBlogId())) {
            return new Result<>("博客id不能为空！");
        }
        blogService.collectionByBlogId(blogCollection);
        return new Result<>("收藏成功！");
    }

    /**
     * 根据博客id和当前登录用户查询收藏记录
     * @param blogId
     * @return
     */
    @RequestMapping(value = "/getCollection/{blogId}", method = RequestMethod.GET)
    public Result<Integer> getCollection(@PathVariable String blogId) {
        int count = blogService.getCollectionCount(blogId);
        return new Result<>(count);
    }

    /**
     * 分页查询我的收藏
     * @param page
     * @return
     */
    @RequestMapping(value = "/getCollectionList", method = RequestMethod.POST)
    public Result<PageUtils<BlogCollection>> getCollectionList(@RequestBody PageUtils<BlogCollection> page) {
        page = blogService.getCollectionByPage(page);
        return new Result<>(page);
    }


}
