package com.lzy.blog.controller;

import com.lzy.blog.utils.Result;
import com.lzy.blog.utils.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: lzy_blog
 * @description: 图片上传
 * @author: lzy
 * @create: 2020-08-24 14:02
 **/
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    /**
     * 上传图片
     * @param file
     * @return
     */
    @RequestMapping("/uploadImage")
    public Result<String> uploadImage(MultipartFile file){
        //得到图片的路径
        String url = uploadService.uploadImage(file);
        return new Result<>("上传图片成功！",url);
    }
}
