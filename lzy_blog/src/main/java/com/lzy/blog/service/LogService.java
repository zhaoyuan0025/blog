package com.lzy.blog.service;

import com.lzy.blog.pojo.Log;
import com.lzy.blog.utils.PageUtils;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

/**
 * <p>
 * 接口访问日志表服务层接口
 * </p>
 *
 * @author 稽哥
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 *
 */
public interface LogService {

    /**
     * 保存异常信息
     * @param logger
     */
    void save(Log logger);

    /**
     * 分页查询日志信息
     * @param page
     * @return
     */
    PageUtils<Log> getByPage(PageUtils<Log> page);

    /**
     * 根基id删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据id批量删除
     * @param ids
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 查询数据用于导出Excel
     * @return
     */
    Workbook export();
}
