package com.lzy.blog.controller;

import com.lzy.blog.enums.ResultEnum;
import com.lzy.blog.pojo.Comment;
import com.lzy.blog.pojo.CommentGoods;
import com.lzy.blog.pojo.User;
import com.lzy.blog.service.CommentService;
import com.lzy.blog.utils.PageUtils;
import com.lzy.blog.utils.Result;
import com.lzy.blog.utils.ShiroUtils;
import com.lzy.blog.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: lzy_blog
 * @description: 评论
 * @author: lzy
 * @create: 2020-08-26 00:55
 **/
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 添加
     * @param comment
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result<Object> save(@RequestBody Comment comment){
        //评论的人
        User user = (User) ShiroUtils.getLoginUser();
        comment.setCommentUser(user.getUserId());
        if(StringUtils.isBlank(comment.getCommentBlog())){
            return new Result<>(ResultEnum.PARAMS_NULL.getCode(),"博客的i不能为空");
        }
        commentService.save(comment);
        return new Result<>("评论成功！");
    }

    /**
     * 查询当前博客的评论
     * @param blogId
     * @return
     */
    @RequestMapping(value = "/getByBlog/{blogId}",method = RequestMethod.GET)
    public Result<List<Comment>> getByBlog(@PathVariable String blogId){
        List<Comment> comments = commentService.getByBlog(blogId);
        return new Result<>(comments);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteById/{id}",method = RequestMethod.DELETE)
    public Result<Object> deleteById(@PathVariable String id){
        commentService.deleteById(id);
        return new Result<>("删除评论成功！");
    }

    /**
     * 点赞
     *
     * @param commentGoods
     * @return
     */
    @RequestMapping(value = "/good", method = RequestMethod.POST)
    public Result<Object> good(@RequestBody CommentGoods commentGoods) {
        if (StringUtils.isBlank(commentGoods.getCommentId())) {
            return new Result<>("评论id不能为空！");
        }
        commentService.goodByCommentIdAndUser(commentGoods);
        return new Result<>("点赞成功！");
    }

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    public Result<PageUtils<Comment>> getList(@RequestBody PageUtils<Comment> page) {
        page = commentService.getByPage(page);
        return new Result<>(page);
    }

    /**
     * 后台分页查询
     * @param page
     * @return
     */
    @RequestMapping(value = "/getByPage", method = RequestMethod.POST)
    public Result<PageUtils<Comment>> getByPage(@RequestBody PageUtils<Comment> page) {
        page = commentService.getByPageBack(page);
        return new Result<>(page);
    }
}
