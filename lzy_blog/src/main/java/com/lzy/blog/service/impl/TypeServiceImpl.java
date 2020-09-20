package com.lzy.blog.service.impl;


import com.lzy.blog.enums.StateEnum;
import com.lzy.blog.exception.BlogException;
import com.lzy.blog.mapper.TypeMapper;
import com.lzy.blog.pojo.Type;
import com.lzy.blog.service.TypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 * 帖子类型表服务实现类
 * </p>
 *
 * @author 稽哥
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 *
 */
@Service
@Slf4j
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public Type getById(Integer id) {
        return typeMapper.getById(id);
    }

    /**
     * 添加
     * @param type
     */
    @Override
    public void save(Type type) {
        //查询分类是否存在
        Type t = typeMapper.getByName(type.getTypeName());
        if (null != t){
            throw new BlogException("该分类已经存在！");
        }
        //直接添加
        typeMapper.insert(type);
    }

    /**
     * 后台查询所有
     * @return
     */
    @Override
    public List<Type> getAll() {
        return typeMapper.getAll();
    }

    /**
     * 前台查询所有
     * @return
     */
    @Override
    public List<Type> getTypeList() {
        return typeMapper.getTypeList();
    }

    /**
     * 根据id删除
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        typeMapper.deleteById(id);
    }

    /**
     * 根据id弃用
     * @param id
     */
    @Override
    public void disableById(Integer id) {
        //先查询，在启用
        Type type = typeMapper.getById(id);
        type.setEnable(StateEnum.NOT_ENABLE.getCode());

        //更新启用状态
        typeMapper.update(type);
    }

    /**
     * 根据id启用
     * @param id
     */
    @Override
    public void enableById(Integer id) {
        //先查询，在启用
        Type type = typeMapper.getById(id);
        type.setEnable(StateEnum.ENABLE.getCode());

        //更新启用状态
        typeMapper.update(type);
    }

    /**
     * geng xin
     * @param type
     */
    @Override
    public void update(Type type) {
        typeMapper.update(type);
    }
}
