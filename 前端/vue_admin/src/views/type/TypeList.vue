<template>
  <div>
    <div class="table-header">
      <!-- 搜索部分开始 -->
      <el-form :inline="true" :model="page" class="demo-form-inline" size="mini">
        <el-form-item label="启用">
          <el-select v-model="page.params.eq_enable" placeholder="请选择">
            <el-option label="启用" :value="1" />
            <el-option label="不启用" :value="0" />
          </el-select>
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
                  @click="toUpdate(scope.row.typeId)"
                >编辑</el-button>
              </el-dropdown-item>
              <el-dropdown-item v-if="scope.row.enable===1">
                <el-button
                  size="mini"
                  type="warning"
                  @click="toAbanding(scope.row)"
                >弃用</el-button>
              </el-dropdown-item>
              <el-dropdown-item v-else>
                <el-button
                  size="mini"
                  type="primary"
                  @click="toActive(scope.row)"
                >启用</el-button>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-button
                  size="mini"
                  type="danger"
                  @click="toDelete(scope.row.typeId)"
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
      <TypeAdd @getByPage="getByPage" @closeDialog="closeDialog" />
    </el-dialog>

    <!-- 修改弹窗 -->
    <el-dialog
      title="修改"
      :visible.sync="updateDialog"
      width="580px"
    >
      <TypeUpdate :type="type" @getByPage="getByPage" @closeDialog="closeDialog" />
    </el-dialog>
  </div>
</template>
<script>
import typeApi from '@/api/type'
import TypeAdd from './TypeAdd'
import TypeUpdate from './TypeUpdate'
export default {
  components: {
    TypeAdd,
    TypeUpdate
  },
  data() {
    return {
      page: {
        pageSize: 20, // 每页显示条数
        currentPage: 1, // 默认页
        params: {}, // 查询参数
        sorted: {},
        columns: [
          'typeId',
          'typeName',
          'typeBlogCount',
          'enable',
          'deleted'
        ]
      },
      exportApi: typeApi,
      filters: this.$options.filters,
      dataPage: { // 分页显示page
        pageSize: 20, // 每页显示条数
        currentPage: 1, // 默认页
        params: { // 查询参数
        },
        sorted: {}
      },
      tableColumns: [
        { prop: 'typeId', sortable: 'custom', label: '分类id', show: true, render: (row, column, cell) => {
          return cell
        } },
        { prop: 'typeName', sortable: 'custom', label: '分类名称', show: true, render: (row, column, cell) => {
          return cell
        } },
        { prop: 'typeBlogCount', sortable: 'custom', label: '帖子数', show: true, render: (row, column, cell) => {
          return cell
        } },
        { prop: 'enable', sortable: 'custom', label: '是否启用，0否1是', show: true, render: (row, column, cell) => {
          return cell === 1 ? <el-tag>启用</el-tag> : <el-tag type='warning'>未启用</el-tag>
        } }
      ],
      addDialog: false, // 添加弹窗
      updateDialog: false, // 修改弹窗
      type: {}
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
      this.type = {}
      this.addDialog = true
    },
    toAbanding(type) {
      // 弃用
      this.$confirm('是否弃用?', '提示', { type: 'warning' }).then(() => {
        type.enable = 0
        typeApi.update(type).then(res => {
          this.$message.success('弃用成功！')
          this.reload()
        })
      })
    },
    toActive(type) {
      // 启用
      this.$confirm('是否启用', '提示', { type: 'success' }).then(() => {
        type.enable = 1
        typeApi.update(type).then(res => {
          this.$message.success('启用成功！')
          this.reload()
        })
      })
    },
    toUpdate(id) {
      // 跳转到更新
      typeApi.get(id).then(res => {
        this.type = res.data
        this.updateDialog = true
      })
    },
    toDelete(id) {
      // 删除
      this.$confirm('确定删除?', '提示', { type: 'error' }).then(() => {
        typeApi.deleteById(id).then(res => {
          this.$message.success(res.msg)
          this.getByPage()
        })
      })
    },
    getByPage() {
      // 分页查询
      this.addDialog = false
      typeApi.getByPage(this.page).then(res => {
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
