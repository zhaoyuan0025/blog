package com.lzy.blog.mapper;

import com.lzy.blog.pojo.Type;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 帖子类型表Mapper
 * </p>
 *
 * @author 稽哥
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 *
 */
@Component
public interface TypeMapper {

    /**
     * 根据名称查询帖子类型
     * @param typeName
     * @return
     */
    Type getByName(String typeName);

    /**
     * 插入
     * @param type
     */
    void insert(Type type);

    /**
     * 前台查询所有
     * @return
     */
    List<Type> getAll();

    /**
     * 后台查询所有
     * @return
     */
     List<Type> getTypeList();

    /**
     * 更新
     * @param type
     */
    void update(Type type);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Type getById(Integer id);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Integer id);
}
