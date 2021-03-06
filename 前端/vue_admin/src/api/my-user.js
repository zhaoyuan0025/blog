import request from '@/utils/request'
var group_name = 'user'
export default {
  getByPage(page) { // 分页查询
    return request({
      url: `/${group_name}/getByPage`,
      method: 'post',
      data: page
    })
  },
  save(user) { // 保存
    return request({
      url: `/${group_name}/save`,
      method: 'post',
      data: user
    })
  },
  get(id) { // 根据id查询
    return request({
      url: `/${group_name}/get/${id}`,
      method: 'get'
    })
  },
  update(user) { // 更新
    return request({
      url: `/${group_name}/update`,
      method: 'put',
      data: user
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
      data: { userId: id }
    })
  }
}
