<template>
  <div>
    <el-form :model="blog" label-width="80px" size="mini">
      <el-form-item label="标题" prop="blogTitle">
        <el-input v-model="blog.blogTitle" clearable />
      </el-form-item>
      <el-form-item label="博客分类" prop="blogType">
        <el-select v-model="blog.blogType" placeholder="请选择">
          <el-option v-for="item in typeList" :key="item.typeId" :label="item.typeName" :value="item.typeId" />
        </el-select>
      </el-form-item>
      <el-form-item label="简介" prop="blogRemark">
        <el-input v-model="blog.blogRemark" type="textarea" clearable />
      </el-form-item>
      <el-form-item label="帖子内容" prop="blogContent">
        <tinymce v-model="blog.blogContent" :height="300" />
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
import blogApi from '@/api/blog'
import Tinymce from '@/components/Tinymce'
export default {
  components: {
    Tinymce
  },
  data() {
    return {
      blog: {},
      typeList: this.$store.getters.typeList
    }
  },
  created() {
  },
  methods: {
    submit() {
      this.save(this.blog)
    },
    save() {
      // 保存
      blogApi.save(this.blog).then(res => {
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
