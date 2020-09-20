<template>
  <div>
    <el-form :model="comment" label-width="80px" size="mini">
      <el-form-item label="评论内容" prop="commentContent">
        <el-input v-model="comment.commentContent" clearable />
      </el-form-item>
      <el-form-item label="评价人" prop="commentUser">
        <el-input v-model="comment.commentUser" clearable />
      </el-form-item>
      <el-form-item label="评论帖子id" prop="commentBlog">
        <el-input v-model="comment.commentBlog" clearable />
      </el-form-item>
      <el-form-item label="点赞数" prop="commentGood">
        <el-input v-model="comment.commentGood" clearable />
      </el-form-item>
      <el-form-item label="评论时间" prop="createdTime">
        <el-input v-model="comment.createdTime" clearable />
      </el-form-item>
      <el-form-item label="是否删除，0否1是" prop="deleted">
        <el-input v-model="comment.deleted" clearable />
      </el-form-item>
      <el-form-item class="sysUser-submit-part">
        <el-button
          type="primary"
          @click="submit"
        >提交</el-button>
        <el-button @click="close">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import commentApi from '@/api/comment'
export default {
  data() {
    return {
      comment: {}
    }
  },
  created() {
  },
  methods: {
    submit() {
      this.save(this.comment)
    },
    save() {
      // 保存
      commentApi.save(this.comment).then(res => {
        this.$message.success(res.msg)
        this.$emit('closeDialog')
        this.$emit('getByPage')
      })
    },
    close() {
      this.$emit('closeDialog')
    }
  }
}
</script>
