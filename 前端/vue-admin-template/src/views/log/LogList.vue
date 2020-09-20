<template>
  <div>
    <div class="table-header">
      <!-- 搜索部分开始 -->
      <el-form :inline="true" :model="page" class="demo-form-inline" size="mini">
        <el-form-item label="请求路径">
          <el-input v-model="page.params.like_logUrl" placeholder="请求路径" clearable @keyup.enter.native="getByPage" />
        </el-form-item>
        <el-form-item label="参数">
          <el-input v-model="page.params.like_logParams" placeholder="参数" clearable @keyup.enter.native="getByPage" />
        </el-form-item>
        <el-form-item label="访问状态，1正常0异常">
          <el-input v-model="page.params.like_logStatus" placeholder="访问状态，1正常0异常" clearable @keyup.enter.native="getByPage" />
        </el-form-item>
        <el-form-item label="异常信息">
          <el-input v-model="page.params.like_logMessage" placeholder="异常信息" clearable @keyup.enter.native="getByPage" />
        </el-form-item>
        <el-form-item label="请求方式，get、post等">
          <el-input v-model="page.params.like_logMethod" placeholder="请求方式，get、post等" clearable @keyup.enter.native="getByPage" />
        </el-form-item>
        <el-form-item label="响应时间">
          <el-input v-model="page.params.like_logTime" placeholder="响应时间" clearable @keyup.enter.native="getByPage" />
        </el-form-item>
        <el-form-item label="返回值">
          <el-input v-model="page.params.like_logResult" placeholder="返回值" clearable @keyup.enter.native="getByPage" />
        </el-form-item>
        <el-form-item label="请求ip">
          <el-input v-model="page.params.like_logIp" placeholder="请求ip" clearable @keyup.enter.native="getByPage" />
        </el-form-item>
        <el-form-item label="创建时间">
          <el-input v-model="page.params.like_createdTime" placeholder="创建时间" clearable @keyup.enter.native="getByPage" />
        </el-form-item>
        <el-form-item label="创建人">
          <el-input v-model="page.params.like_createdBy" placeholder="创建人" clearable @keyup.enter.native="getByPage" />
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
                  @click="toUpdate(scope.row.logId)"
                >编辑</el-button>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-button
                  size="mini"
                  type="danger"
                  @click="toDelete(scope.row.logId)"
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
      <LogAdd @getByPage="getByPage" @closeDialog="closeDialog" />
    </el-dialog>

    <!-- 修改弹窗 -->
    <el-dialog
      title="修改"
      :visible.sync="updateDialog"
      width="580px"
    >
      <LogUpdate :log="log" @getByPage="getByPage" @closeDialog="closeDialog" />
    </el-dialog>
  </div>
</template>
<script>
import logApi from '@/api/log'
import LogAdd from './LogAdd'
import LogUpdate from './LogUpdate'
export default {
  components: {
    LogAdd,
    LogUpdate
  },
  data() {
    return {
      page: {
        pageSize: 20, // 每页显示条数
        currentPage: 1, // 默认页
        params: {}, // 查询参数
        sorted: {},
        columns: [
          'logId',
          'logUrl',
          'logParams',
          'logStatus',
          'logMessage',
          'logMethod',
          'logTime',
          'logResult',
          'logIp',
          'createdTime',
          'createdBy'
        ]
      },
      exportApi: logApi,
      filters: this.$options.filters,
      dataPage: { // 分页显示page
        pageSize: 20, // 每页显示条数
        currentPage: 1, // 默认页
        params: { // 查询参数
        },
        sorted: {}
      },
      tableColumns: [
        { prop: 'logId', sortable: 'custom', label: '日志id', show: true, render: (row, column, cell) => {
          return cell
        } },
        { prop: 'logUrl', sortable: 'custom', label: '请求路径', show: true, render: (row, column, cell) => {
          return cell
        } },
        { prop: 'logParams', sortable: 'custom', label: '参数', show: true, render: (row, column, cell) => {
          return cell
        } },
        { prop: 'logStatus', sortable: 'custom', label: '访问状态，1正常0异常', show: true, render: (row, column, cell) => {
          return cell
        } },
        { prop: 'logMessage', sortable: 'custom', label: '异常信息', show: true, render: (row, column, cell) => {
          return cell
        } },
        { prop: 'logMethod', sortable: 'custom', label: '请求方式，get、post等', show: true, render: (row, column, cell) => {
          return cell
        } },
        { prop: 'logTime', sortable: 'custom', label: '响应时间', show: true, render: (row, column, cell) => {
          return cell
        } },
        { prop: 'logResult', sortable: 'custom', label: '返回值', show: true, render: (row, column, cell) => {
          return cell
        } },
        { prop: 'logIp', sortable: 'custom', label: '请求ip', show: true, render: (row, column, cell) => {
          return cell
        } },
        { prop: 'createdTime', sortable: 'custom', label: '创建时间', show: true, render: (row, column, cell) => {
          return cell
        } },
        { prop: 'createdBy', sortable: 'custom', label: '创建人', show: true, render: (row, column, cell) => {
          return cell
        } }
      ],
      addDialog: false, // 添加弹窗
      updateDialog: false, // 修改弹窗
      log: {},
      valColumn: ['logId', 'logUrl', 'logParams', 'logStatus', 'logMessage', 'logMethod', 'logTime', 'logResult', 'logIp', 'createdTime', 'createdBy'], // 导出数据表头
      headers: ['日志id', '请求路径', '参数', '访问状态，1正常0异常', '异常信息', '请求方式，get、post等', '响应时间', '返回值', '请求ip', '创建时间', '创建人'] // 导出excel表头对应列
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
      this.log = {}
      this.addDialog = true
    },
    toUpdate(id) {
      // 跳转到更新
      logApi.get(id).then(res => {
        this.log = res.data
        this.updateDialog = true
      })
    },
    toDelete(id) {
      // 删除
      this.$confirm('确定删除?', '提示', { type: 'error' }).then(() => {
        logApi.deleteById(id).then(res => {
          this.$message.success(res.msg)
          this.getByPage()
        })
      })
    },
    getByPage() {
      // 分页查询
      this.addDialog = false
      logApi.getByPage(this.page).then(res => {
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
