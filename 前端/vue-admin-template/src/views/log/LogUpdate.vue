<template>
  <div>
    <el-form :model="log" label-width="80px" size="mini">
      <el-form-item label="请求路径" prop="logUrl">
        <el-input v-model="log.logUrl" clearable />
      </el-form-item>
      <el-form-item label="参数" prop="logParams">
        <el-input v-model="log.logParams" clearable />
      </el-form-item>
      <el-form-item label="访问状态，1正常0异常" prop="logStatus">
        <el-input v-model="log.logStatus" clearable />
      </el-form-item>
      <el-form-item label="异常信息" prop="logMessage">
        <el-input v-model="log.logMessage" clearable />
      </el-form-item>
      <el-form-item label="请求方式，get、post等" prop="logMethod">
        <el-input v-model="log.logMethod" clearable />
      </el-form-item>
      <el-form-item label="响应时间" prop="logTime">
        <el-input v-model="log.logTime" clearable />
      </el-form-item>
      <el-form-item label="返回值" prop="logResult">
        <el-input v-model="log.logResult" clearable />
      </el-form-item>
      <el-form-item label="请求ip" prop="logIp">
        <el-input v-model="log.logIp" clearable />
      </el-form-item>
      <el-form-item label="创建时间" prop="createdTime">
        <el-input v-model="log.createdTime" clearable />
      </el-form-item>
      <el-form-item label="创建人" prop="createdBy">
        <el-input v-model="log.createdBy" clearable />
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
import logApi from '@/api/log'
export default {
  props: {
    log: {
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
      this.update(this.log)
    },
    update() {
      // 更新
      logApi.update(this.log).then(res => {
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
