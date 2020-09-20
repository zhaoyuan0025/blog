<template>
  <div>
    <el-form :model="admin" label-width="80px" size="mini">
      <el-form-item label="姓名" prop="name">
        <el-input v-model="admin.name" clearable />
      </el-form-item>
      <el-form-item label="头像" prop="header">
        <el-upload
          class="avatar-uploader"
          :action="uploadUrl"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
        >
          <img v-if="imageUrl" :src="imageUrl" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon" />
        </el-upload>
      </el-form-item>
      <el-form-item label="签名" prop="signature">
        <el-input v-model="admin.signature" clearable />
      </el-form-item>
      <el-form-item label="介绍" prop="comment">
        <el-input v-model="admin.comment" clearable />
      </el-form-item>
      <el-form-item label="账号" prop="username">
        <el-input v-model="admin.username" clearable />
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="admin.password" clearable />
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
import adminApi from '@/api/admin'
export default {
  props: {
    admin: {
      type: Object,
      default() {

      }
    }
  },
  data() {
    return {
      uploadUrl: process.env.VUE_APP_UPLOAD_URL,
      imageUrl: ''
    }
  },
  watch: {
    'admin.header': function(val) {
      this.imageUrl = val
    }
  },
  created() {
  },
  methods: {
    submit() {
      this.update(this.admin)
    },
    update() {
      // 更新
      adminApi.update(this.admin).then(res => {
        this.$message.success(res.msg)
        this.$emit('closeDialog')
        this.$emit('getByPage')
      })
    },
    handleAvatarSuccess(res, file) {
      this.$message.success(res.msg)
      this.admin.header = res.data.url
      this.imageUrl = res.data.url
    },
    close() {
      this.$emit('closeDialog')
    }
  }
}
</script>

<style>
.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
