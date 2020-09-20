package com.lzy.blog.utils;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: lzy_blog
 * @description: 分页对象
 * @author: lzy
 * @create: 2020-07-05 23:38
 **/
@Data
public class PageUtils<T> implements Serializable {

    /**
     * 定义排序的方式
     */
    private static final String SORT_ASC = "asc";
    private static final String SORT_DESC = "desc";

    /**
     * 当前页
     */
    private Integer currentPage = 1;

    /**
     * 每页显示条数
     */
    private Integer pageSize = 20;

    /**
     * 总页数
     */
    private Integer totalPage = 0;

    /**
     * 总条数
     */
    private Integer totalCount = 0;

    /**
     * 分页的起始位置
     */
    private Integer index;

    /**
     * 数据
     */
    private  List<T> list;

    /**
     * 条件参数 默认值16
     */
    private  Map<String,Object> params = new HashMap<>(16);

    /**
     * 排序列
     */
    private String sortColumn;

    /**
     * 排序方式
     */
    private String sortMethod = "asc";

    /**
     * 获取当前页
     * @return
     */
    public Integer getCurrentPage() {
        if(currentPage<1){
            return 1;
        }
        return this.currentPage;
    }

    /**
     * 设置排序列
     * @param sortColumn
     */
    public void setSortColumn(String sortColumn){
        if(StringUtils.isBlank(sortColumn)){
            this.sortColumn = null;
        }else {

            this.sortColumn = StringUtils.upperCharToUnderLine(sortColumn);
        }
    }

    /**
     * 获取页的起始位置
     * @return
     */
    public Integer getIndex(){
        return (currentPage-1)*pageSize;
    }

    /**
     * 设置总条数的时候计算总页数
     * @param totalCount
     */
    public void setTotalCount(Integer totalCount){
        this.totalCount = totalCount;
        //总页数
        this.totalPage = (int)Math.ceil(totalCount * 1.0/pageSize);
    }

    /**
     * 设置排序的方式
     */
    public void setSortMethod(String sortMethod){
        if(StringUtils.isBlank(this.sortMethod)){
            this.sortMethod = SORT_ASC;
        }
        if(sortMethod.toLowerCase().startsWith(SORT_ASC)){
            this.sortMethod = SORT_ASC;
        }else if(sortMethod.toLowerCase().startsWith(SORT_DESC)){
            this.sortMethod = SORT_DESC;
        }else{
            this.sortMethod = SORT_ASC;
        }
    }
}
