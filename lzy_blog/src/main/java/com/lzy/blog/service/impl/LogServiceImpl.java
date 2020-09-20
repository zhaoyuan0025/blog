package com.lzy.blog.service.impl;

import com.lzy.blog.excel.entity.ExportParams;
import com.lzy.blog.excel.handler.ExcelExportHandler;
import com.lzy.blog.mapper.LogMapper;
import com.lzy.blog.pojo.About;
import com.lzy.blog.pojo.Log;
import com.lzy.blog.service.LogService;
import com.lzy.blog.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 接口访问日志表服务实现类
 * </p>
 *
 * @author 稽哥
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 *
 */
@Service
@Slf4j
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    /**
     * 保存日志信息
     * @param logger
     */
    @Override
    public void save(Log logger) {
        logMapper.save(logger);
    }

    /**
     * 分页查询日志信息
     * @param page
     * @return
     */
    @Override
    public PageUtils<Log> getByPage(PageUtils<Log> page) {
        //查询数据
        List<Log> logList = logMapper.getByPage(page);
        page.setList(logList);
        //查询总数
        int totalCount = logMapper.getCountPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    /**
     * 根据id删除
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        logMapper.deleteById(id);
    }

    /**
     * 批量
     * @param ids
     */
    @Override
    public void deleteByIds(List<Integer> ids) {
        logMapper.deleteByIds(ids);
    }

    @Override
    public Workbook export() {
        List<Log> logList = logMapper.getAll();
        return new ExcelExportHandler()
                .createSheet(new ExportParams("最新日志", "sheet1"),
                        Log.class, logList);
    }

}
