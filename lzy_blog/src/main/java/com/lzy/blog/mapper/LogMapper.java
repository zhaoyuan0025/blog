package com.lzy.blog.mapper;

import com.lzy.blog.pojo.Log;
import com.lzy.blog.utils.PageUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 接口访问日志表Mapper
 * </p>
 *
 * @author 稽哥
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 *
 */
@Component
public interface LogMapper {

    /**
     * 保存
     * @param logger
     */
    void save(Log logger);

    /**
     * 分页查询日志信息
     * @param page
     * @return
     */
    List<Log> getByPage(PageUtils<Log> page);

    /**
     * 查询日志总数
     * @param page
     * @return
     */
    int getCountPage(PageUtils<Log> page);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据id批量删除
     * @param ids
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 查询全部数据
     * @return
     */
    List<Log> getAll();
}
