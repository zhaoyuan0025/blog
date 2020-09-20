import request from '@/utils/request'
var group_name = 'type'
export default {
  getByPage(page) { // 分页查询
    return request({
      url: `/${group_name}/getByPage`,
      method: 'post',
      data: page
    })
  },
  save(type) { // 保存
    return request({
      url: `/${group_name}/save`,
      method: 'post',
      data: type
    })
  },
  get(id) { // 根据id查询
    return request({
      url: `/${group_name}/get/${id}`,
      method: 'get'
    })
  },
  getAll() { // 查询所有
    return request({
      url: `/${group_name}/all`,
      method: 'get'
    })
  },
  update(type) { // 更新
    return request({
      url: `/${group_name}/update`,
      method: 'put',
      data: type
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
      data: { typeId: id }
    })
  }
}
