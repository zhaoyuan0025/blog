<template>
  <div>
    <el-form :model="type" label-width="80px" size="mini">
      <el-form-item label="分类名称" prop="typeName">
        <el-input v-model="type.typeName" clearable />
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
import typeApi from '@/api/type'
export default {
  data() {
    return {
      type: {}
    }
  },
  created() {
  },
  methods: {
    submit() {
      this.save(this.type)
    },
    save() {
      // 保存
      typeApi.save(this.type).then(res => {
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
