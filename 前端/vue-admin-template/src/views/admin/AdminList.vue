<template>
  <div>
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
                  @click="toUpdate(scope.row.id)"
                >编辑</el-button>
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

    <!-- 修改弹窗 -->
    <el-dialog
      title="修改"
      :visible.sync="updateDialog"
      width="580px"
    >
      <AdminUpdate :admin="admin" @getByPage="getByPage" @closeDialog="closeDialog" />
    </el-dialog>
  </div>
</template>
<script>
import adminApi from '@/api/admin'
import AdminUpdate from './AdminUpdate'
export default {
  components: {
    AdminUpdate
  },
  data() {
    return {
      page: {
        pageSize: 20, // 每页显示条数
        currentPage: 1, // 默认页
        params: {}, // 查询参数
        sorted: {},
        columns: [
          'id',
          'name',
          'header',
          'signature',
          'comment',
          'username',
          'password'
        ]
      },
      exportApi: adminApi,
      filters: this.$options.filters,
      dataPage: { // 分页显示page
        pageSize: 20, // 每页显示条数
        currentPage: 1, // 默认页
        params: { // 查询参数
        },
        sorted: {}
      },
      tableColumns: [
        { prop: 'id', sortable: 'custom', label: '管理员id', show: true, render: (row, column, cell) => {
          return cell
        } },
        { prop: 'name', sortable: 'custom', label: '姓名', show: true, render: (row, column, cell) => {
          return cell
        } },
        { prop: 'header', sortable: 'custom', label: '头像', show: true, render: (row, column, cell) => {
          return <el-image
            style='width: 100px; height: 100px'
            src={cell}
            preview-src-list={[cell]}>
          </el-image>
        } },
        { prop: 'signature', sortable: 'custom', label: '签名', show: true, render: (row, column, cell) => {
          return cell
        } },
        { prop: 'comment', sortable: 'custom', label: '介绍', show: true, render: (row, column, cell) => {
          return cell
        } },
        { prop: 'username', sortable: 'custom', label: '账号', show: true, render: (row, column, cell) => {
          return cell
        } },
        { prop: 'password', sortable: 'custom', label: '密码', show: true, render: (row, column, cell) => {
          return cell
        } }
      ],
      addDialog: false, // 添加弹窗
      updateDialog: false, // 修改弹窗
      admin: {},
      valColumn: ['id', 'name', 'header', 'signature', 'comment', 'username', 'password'], // 导出数据表头
      headers: ['管理员id', '姓名', '头像', '签名', '介绍', '账号', '密码'] // 导出excel表头对应列
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
      this.admin = {}
      this.addDialog = true
    },
    toUpdate(id) {
      // 跳转到更新
      adminApi.get(id).then(res => {
        this.admin = res.data
        this.updateDialog = true
      })
    },
    toDelete(id) {
      // 删除
      this.$confirm('确定删除?', '提示', { type: 'error' }).then(() => {
        adminApi.deleteById(id).then(res => {
          this.$message.success(res.msg)
          this.getByPage()
        })
      })
    },
    getByPage() {
      // 分页查询
      this.addDialog = false
      adminApi.getByPage(this.page).then(res => {
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
