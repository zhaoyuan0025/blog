<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
import typeApi from '@/api/type'
export default {
  name: 'App',
  created() {
    this.getTypeList()
  },
  methods: {
    getTypeList() {
      typeApi.getAll().then(res => {
        this.$store.commit('global/SET_TYPE', res.data)
      })
    }
  }
}
</script>
