<template>
  <div>
    <el-form :model="user" label-width="80px" size="mini">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="user.username" clearable />
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="user.password" type="password" clearable />
      </el-form-item>
      <el-form-item label="姓名" prop="name">
        <el-input v-model="user.name" clearable />
      </el-form-item>
      <el-form-item label="性别" prop="sex">
        <el-select v-model="user.sex" placeholder="请选择">
          <el-option label="男" :value="1" />
          <el-option label="女" :value="2" />
        </el-select>
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
      <el-form-item label="昵称" prop="nickname">
        <el-input v-model="user.nickname" clearable />
      </el-form-item>
      <el-form-item label="邮箱" prop="userEmail">
        <el-input v-model="user.userEmail" clearable />
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
import userApi from '@/api/my-user'
export default {
  data() {
    return {
      uploadUrl: process.env.VUE_APP_UPLOAD_URL,
      imageUrl: '',
      user: {}
    }
  },
  created() {
  },
  methods: {
    submit() {
      this.save(this.user)
    },
    save() {
      // 保存
      userApi.save(this.user).then(res => {
        this.$message.success(res.msg)
        this.$emit('closeDialog')
        this.$emit('getByPage')
      })
    },
    handleAvatarSuccess(res, file) {
      this.$message.success(res.msg)
      this.user.header = res.data.url
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
