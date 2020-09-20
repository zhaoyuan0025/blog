<template>
  <div>
    <div class="table-header">
      <!-- 搜索部分开始 -->
      <el-form :inline="true" :model="page" class="demo-form-inline" size="mini">
        <el-form-item label="用户名">
          <el-input v-model="page.params.eq_username" placeholder="用户名" clearable @keyup.enter.native="getByPage" />
        </el-form-item>
        <el-form-item label="姓名">
          <el-input v-model="page.params.like_name" placeholder="姓名" clearable @keyup.enter.native="getByPage" />
        </el-form-item>
        <el-form-item label="性别。1男2女">
          <el-select v-model="page.params.eq_sex" placeholder="请选择">
            <el-option label="男" :value="1" />
            <el-option label="女" :value="2" />
          </el-select>
        </el-form-item>
        <el-form-item label="昵称">
          <el-input v-model="page.params.like_nickname" placeholder="昵称" clearable @keyup.enter.native="getByPage" />
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="page.params.like_userEmail" placeholder="邮箱" clearable @keyup.enter.native="getByPage" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="getByPage">查询</el-button>
        </el-form-item>
      </el-form>
      <!-- 搜索部分结束 -->

      <el-divider />
      <el-button
        type="primary"
        size="mini"
        @click="toAdd"
      >添加</el-button>
    </div>
    <!-- 列表开始 -->
    <el-table
      :data="dataPage.list"
      border
      fit
      stripe
      style="width: 100%"
      size="mini"
      @sort-change="sortHandler"
    >
      <template v-for="(item, index) in tableColumns">
        <el-table-column v-if="item.show" :key="index" :show-overflow-tooltip="true" :min-width="item.minWidth" :width="item.width" :prop="item.prop" :sortable="item.sortable" :label="item.label" :formatter="item.render" />
      </template>

      <el-table-column fixed="right" label="操作">
        <template slot-scope="scope">
          <el-dropdown>
            <el-button type="primary" size="mini">
              操作
              <i class="el-icon-arrow-down el-icon--right" />
            </el-button>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>
                <el-button
                  size="mini"
                  type="success"
                  @click="toUpdate(scope.row.userId)"
                >编辑</el-button>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-button
                  size="mini"
                  type="danger"
                  @click="toDelete(scope.row.userId)"
                >删除</el-button>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </template>
      </el-table-column>
    </el-table>
    <!-- 列表结束 -->

    <!-- 分页组件开始 -->
    <div class="page-div">
      <el-pagination
        :current-page="page.currentPage"
        :page-sizes="[10,15,20,30]"
        :page-size="page.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="page.totalCount"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      />
    </div>
    <!-- 分页组件结束 -->

    <!-- 添加弹窗 -->
    <el-dialog
      title="添加"
      :visible.sync="addDialog"
      width="580px"
    >
      <UserAdd @getByPage="getByPage" @closeDialog="closeDialog" />
    </el-dialog>

    <!-- 修改弹窗 -->
    <el-dialog
      title="修改"
      :visible.sync="updateDialog"
      width="580px"
    >
      <UserUpdate :user="user" @getByPage="getByPage" @closeDialog="closeDialog" />
    </el-dialog>
  </div>
</template>
<script>
import userApi from '@/api/my-user'
import UserAdd from './UserAdd'
import UserUpdate from './UserUpdate'
export default {
  components: {
    UserAdd,
    UserUpdate
  },
  data() {
    return {
      page: {
        pageSize: 20, // 每页显示条数
        currentPage: 1, // 默认页
        params: {}, // 查询参数
        sorted: {}
      },
      exportApi: userApi,
      filters: this.$options.filters,
      dataPage: { // 分页显示page
        pageSize: 20, // 每页显示条数
        currentPage: 1, // 默认页
        params: { // 查询参数
        },
        sorted: {}
      },
      tableColumns: [
        { prop: 'userId', sortable: 'custom', label: '用户id', show: true, render: (row, column, cell) => {
          return cell
        } },
        { prop: 'username', sortable: 'custom', label: '用户名', show: true, render: (row, column, cell) => {
          return cell
        } },
        { prop: 'password', sortable: 'custom', label: '密码', show: true, render: (row, column, cell) => {
          return cell
        } },
        { prop: 'name', sortable: 'custom', label: '姓名', show: true, render: (row, column, cell) => {
          return cell
        } },
        { prop: 'sex', sortable: 'custom', label: '性别', show: true, render: (row, column, cell) => {
          return cell
        } },
        { prop: 'header', sortable: 'custom', label: '头像', show: true, render: (row, column, cell) => {
          return <el-image
            style='width: 100px; height: 100px'
            src={cell}
            preview-src-list={[cell]}>
          </el-image>
        } },
        { prop: 'nickname', sortable: 'custom', label: '昵称', show: true, render: (row, column, cell) => {
          return cell
        } },
        { prop: 'userEmail', sortable: 'custom', label: '邮箱', show: true, render: (row, column, cell) => {
          return cell
        } },
        { prop: 'createdTime', sortable: 'custom', label: '创建时间', show: true, render: (row, column, cell) => {
          return cell
        } },
        { prop: 'updateTime', sortable: 'custom', label: '更新时间', show: true, render: (row, column, cell) => {
          return cell
        } }
      ],
      addDialog: false, // 添加弹窗
      updateDialog: false, // 修改弹窗
      user: {}
    }
  },
  created() {
    this.getByPage()
  },
  methods: {
    handleSizeChange(val) {
      this.page.pageSize = val
      this.getByPage()
    },
    handleCurrentChange(val) {
      this.page.currentPage = val
      this.getByPage()
    },
    sortHandler(column) {
      // 排序查询
      console.log(column)
      const key = column.prop
      const value = column.order
      this.page.sorted = {}
      this.page.sorted[key] = value
      this.getByPage()
    },
    toAdd() {
      // 跳转到添加
      this.user = {}
      this.addDialog = true
    },
    toUpdate(id) {
      // 跳转到更新
      userApi.get(id).then(res => {
        this.user = res.data
        this.updateDialog = true
      })
    },
    toDelete(id) {
      // 删除
      this.$confirm('确定删除?', '提示', { type: 'error' }).then(() => {
        userApi.deleteById(id).then(res => {
          this.$message.success(res.msg)
          this.getByPage()
        })
      })
    },
    getByPage() {
      // 分页查询
      this.addDialog = false
      userApi.getByPage(this.page).then(res => {
        this.dataPage = res.data
        this.page.totalCount = this.dataPage.totalCount
        this.page.totalPage = this.dataPage.totalPage
      })
    },
    closeDialog() {
      this.addDialog = false
      this.updateDialog = false
    }
  }
}
</script>

<style scoped>
.table-header {
  margin-bottom: 10px;
}
</style>
