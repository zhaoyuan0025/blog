<template>
  <div>
    <div class="table-header">
      <!-- 搜索部分开始 -->
      <el-form :inline="true" :model="page" class="demo-form-inline" size="mini">
        <el-form-item label="标题">
          <el-input v-model="page.params.like_blogTitle" placeholder="标题" clearable @keyup.enter.native="getByPage" />
        </el-form-item>
        <el-form-item label="博客分类">
          <el-input v-model="page.params.like_blogType" placeholder="博客分类" clearable @keyup.enter.native="getByPage" />
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
                  @click="toUpdate(scope.row.blogId)"
                >编辑</el-button>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-button
                  size="mini"
                  type="success"
                  @click="toRead(scope.row)"
                >查看</el-button>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-button
                  size="mini"
                  type="danger"
                  @click="toDelete(scope.row.blogId)"
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
      width="1000px"
    >
      <BlogAdd @getByPage="getByPage" @closeDialog="closeDialog" />
    </el-dialog>

    <!-- 修改弹窗 -->
    <el-dialog
      title="修改"
      :visible.sync="updateDialog"
      width="1000px"
    >
      <BlogUpdate :blog="blog" @getByPage="getByPage" @closeDialog="closeDialog" />
    </el-dialog>

    <!-- 阅读 -->
    <el-dialog
      :title="readBlog.blogTitle"
      :visible.sync="readDialog"
      width="1000px"
    >
      <div v-html="readBlog.blogContent" />
    </el-dialog>
  </div>
</template>
<script>
import blogApi from '@/api/blog'
import BlogAdd from './BlogAdd'
import BlogUpdate from './BlogUpdate'
export default {
  components: {
    BlogAdd,
    BlogUpdate
  },
  data() {
    return {
      page: {
        pageSize: 20, // 每页显示条数
        currentPage: 1, // 默认页
        params: {}, // 查询参数
        sorted: {}
      },
      exportApi: blogApi,
      readBlog: {},
      filters: this.$options.filters,
      dataPage: { // 分页显示page
        pageSize: 20, // 每页显示条数
        currentPage: 1, // 默认页
        params: { // 查询参数
        },
        sorted: {}
      },
      tableColumns: [
        { prop: 'blogId', sortable: 'custom', label: '帖子id', show: true, render: (row, column, cell) => {
          return cell
        } },
        { prop: 'blogTitle', sortable: 'custom', label: '标题', show: true, render: (row, column, cell) => {
          return cell
        } },
        { prop: 'blogType', sortable: 'custom', label: '博客分类', show: true, render: (row, column, cell) => {
          return row.type.typeName
        } },
        { prop: 'blogGoods', sortable: 'custom', label: '点赞数', show: true, render: (row, column, cell) => {
          return cell
        } },
        { prop: 'blogRead', sortable: 'custom', label: '阅读数', show: true, render: (row, column, cell) => {
          return cell
        } },
        { prop: 'blogCollection', sortable: 'custom', label: '收藏数', show: true, render: (row, column, cell) => {
          return cell
        } },
        { prop: 'blogRemark', sortable: 'custom', label: '简介', show: true, render: (row, column, cell) => {
          return cell
        } },
        { prop: 'blogComment', sortable: 'custom', label: '评论数', show: true, render: (row, column, cell) => {
          return cell
        } },
        { prop: 'blogSource', sortable: 'custom', label: '文章来源', show: true, render: (row, column, cell) => {
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
      readDialog: false, // 阅读弹窗
      blog: {}
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
      this.blog = {}
      this.addDialog = true
    },
    toRead(row) {
      this.readBlog = row
      this.readDialog = true
    },
    toUpdate(id) {
      // 跳转到更新
      blogApi.get(id).then(res => {
        this.blog = res.data
        this.updateDialog = true
      })
    },
    toDelete(id) {
      // 删除
      this.$confirm('确定删除?', '提示', { type: 'error' }).then(() => {
        blogApi.deleteById(id).then(res => {
          this.$message.success(res.msg)
          this.getByPage()
        })
      })
    },
    getByPage() {
      // 分页查询
      this.addDialog = false
      blogApi.getByPage(this.page).then(res => {
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
