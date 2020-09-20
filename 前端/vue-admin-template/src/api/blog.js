import request from '@/utils/request'
var group_name = 'blog'
export default {
  getByPage(page) { // 分页查询
    return request({
      url: `/${group_name}/getByPage`,
      method: 'post',
      data: page
    })
  },
  save(blog) { // 保存
    return request({
      url: `/${group_name}/save`,
      method: 'post',
      data: blog
    })
  },
  get(id) { // 根据id查询
    return request({
      url: `/${group_name}/get/${id}`,
      method: 'get'
    })
  },
  update(blog) { // 更新
    return request({
      url: `/${group_name}/update`,
      method: 'put',
      data: blog
    })
  },
  exportExcel(page) { // 导出
    return request({
      url: `/${group_name}/export`,
      method: 'post',
      data: page,
      responseType: 'blob'
    })
  },
  deleteById(id) { // 根据id删除
    return request({
      url: `/${group_name}/delete`,
      method: 'put',
      data: { blogId: id }
    })
  }
}
