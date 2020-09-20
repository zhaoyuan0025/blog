package com.lzy.blog.controller;

import com.lzy.blog.enums.ResultEnum;
import com.lzy.blog.pojo.Link;
import com.lzy.blog.pojo.Log;
import com.lzy.blog.service.LogService;
import com.lzy.blog.utils.PageUtils;
import com.lzy.blog.utils.Result;
import com.lzy.blog.utils.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * @program: lzy_blog
 * @description: 日志
 * @author: lzy
 * @create: 2020-07-06 15:20
 **/
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logService;

    @RequestMapping(value = "/getByPage",method = RequestMethod.POST)
    public Result<PageUtils<Log>> getByPage(@RequestBody PageUtils<Log> page){
        String sortColumn = page.getSortColumn();
        //判断排序列是否为空
        if(StringUtils.isNotBlank(sortColumn)){
            //不为空，用数组存储可以用来排序的字段
            String[] columns = {"log_url","log_status","log_method","log_time",
            "created_time"};
            List<String> sortList = Arrays.asList(columns);
            //如果集合中不包含排序的字段
            if(!sortList.contains(sortColumn.toLowerCase())){
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(),"排序的参数不合法！");
            }
        }
        page = logService.getByPage(page);
        return new Result<>(page);
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public Result<Object> delete(@PathVariable Integer id){
        logService.deleteById(id);
        return new Result<>("删除成功！");
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping(value = "/deleteByIds",method = RequestMethod.PUT)
    public Result<Object> deleteByIds(@RequestBody List<Integer> ids){
        logService.deleteByIds(ids);
        return new Result<>("批量删除成功！");
    }

    /**
     * 全部导出
     *
     * @throws Exception
     */
    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public void export(HttpServletResponse response) throws Exception {
        Workbook workbook = logService.export();
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + "日志");
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }
}
