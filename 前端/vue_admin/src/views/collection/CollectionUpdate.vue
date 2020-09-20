<template>
  <div>
    <el-form :model="collection" label-width="80px" size="mini">
      <el-form-item label="帖子id" prop="blogId">
        <el-input v-model="collection.blogId" clearable />
      </el-form-item>
      <el-form-item label="用户id" prop="userId">
        <el-input v-model="collection.userId" clearable />
      </el-form-item>
      <el-form-item label="收藏时间" prop="collectionTime">
        <el-input v-model="collection.collectionTime" clearable />
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
import collectionApi from '@/api/collection'
export default {
  props: {
    collection: {
      type: Object,
      default() {

      }
    }
  },
  data() {
    return {
    }
  },
  created() {
  },
  methods: {
    submit() {
      this.update(this.collection)
    },
    update() {
      // 更新
      collectionApi.update(this.collection).then(res => {
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
