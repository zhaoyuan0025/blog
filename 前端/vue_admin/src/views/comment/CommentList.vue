<template>
  <div>
    <div class="table-header">
      <!-- 搜索部分开始 -->
      <el-form :inline="true" :model="page" class="demo-form-inline" size="mini">
        <el-form-item label="评论内容">
          <el-input v-model="page.params.like_commentContent" placeholder="评论内容" clearable @keyup.enter.native="getByPage" />
        </el-form-item>
        <el-form-item label="评价人">
          <el-input v-model="page.params.like_commentUser" placeholder="评价人" clearable @keyup.enter.native="getByPage" />
        </el-form-item>
        <el-form-item label="评论帖子id">
          <el-input v-model="page.params.like_commentBlog" placeholder="评论帖子id" clearable @keyup.enter.native="getByPage" />
        </el-form-item>
        <el-form-item label="点赞数">
          <el-input v-model="page.params.like_commentGood" placeholder="点赞数" clearable @keyup.enter.native="getByPage" />
        </el-form-item>
        <el-form-item label="评论时间">
          <el-input v-model="page.params.like_createdTime" placeholder="评论时间" clearable @keyup.enter.native="getByPage" />
        </el-form-item>
        <el-form-item label="是否删除，0否1是">
          <el-input v-model="page.params.like_deleted" placeholder="是否删除，0否1是" clearable @keyup.enter.native="getByPage" />
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
                  @click="toUpdate(scope.row.commentId)"
                >编辑</el-button>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-button
                  size="mini"
                  type="danger"
                  @click="toDelete(scope.row.commentId)"
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
      <CommentAdd @getByPage="getByPage" @closeDialog="closeDialog" />
    </el-dialog>

    <!-- 修改弹窗 -->
    <el-dialog
      title="修改"
      :visible.sync="updateDialog"
      width="580px"
    >
      <CommentUpdate :comment="comment" @getByPage="getByPage" @closeDialog="closeDialog" />
    </el-dialog>
  </div>
</template>
<script>
import commentApi from '@/api/comment'
import CommentAdd from './CommentAdd'
import CommentUpdate from './CommentUpdate'
export default {
  components: {
    CommentAdd,
    CommentUpdate
  },
  data() {
    return {
      page: {
        pageSize: 20, // 每页显示条数
        currentPage: 1, // 默认页
        params: {}, // 查询参数
        sorted: {},
        columns: [
          'commentId',
          'commentContent',
          'commentUser',
          'commentBlog',
          'commentGood',
          'createdTime',
          'deleted'
        ]
      },
      exportApi: commentApi,
      filters: this.$options.filters,
      dataPage: { // 分页显示page
        pageSize: 20, // 每页显示条数
        currentPage: 1, // 默认页
        params: { // 查询参数
        },
        sorted: {}
      },
      tableColumns: [
        { prop: 'commentId', sortable: 'custom', label: '评论id', show: true, render: (row, column, cell) => {
          return cell
        } },
        { prop: 'commentContent', sortable: 'custom', label: '评论内容', show: true, render: (row, column, cell) => {
          return cell
        } },
        { prop: 'commentUser', sortable: 'custom', label: '评价人', show: true, render: (row, column, cell) => {
          return cell
        } },
        { prop: 'commentBlog', sortable: 'custom', label: '评论帖子id', show: true, render: (row, column, cell) => {
          return cell
        } },
        { prop: 'commentGood', sortable: 'custom', label: '点赞数', show: true, render: (row, column, cell) => {
          return cell
        } },
        { prop: 'createdTime', sortable: 'custom', label: '评论时间', show: true, render: (row, column, cell) => {
          return cell
        } },
        { prop: 'deleted', sortable: 'custom', label: '是否删除，0否1是', show: true, render: (row, column, cell) => {
          return cell
        } }
      ],
      addDialog: false, // 添加弹窗
      updateDialog: false, // 修改弹窗
      comment: {},
      valColumn: ['commentId', 'commentContent', 'commentUser', 'commentBlog', 'commentGood', 'createdTime', 'deleted'], // 导出数据表头
      headers: ['评论id', '评论内容', '评价人', '评论帖子id', '点赞数', '评论时间', '是否删除，0否1是'] // 导出excel表头对应列
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
      this.comment = {}
      this.addDialog = true
    },
    toUpdate(id) {
      // 跳转到更新
      commentApi.get(id).then(res => {
        this.comment = res.data
        this.updateDialog = true
      })
    },
    toDelete(id) {
      // 删除
      this.$confirm('确定删除?', '提示', { type: 'error' }).then(() => {
        commentApi.deleteById(id).then(res => {
          this.$message.success(res.msg)
          this.getByPage()
        })
      })
    },
    getByPage() {
      // 分页查询
      this.addDialog = false
      commentApi.getByPage(this.page).then(res => {
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
