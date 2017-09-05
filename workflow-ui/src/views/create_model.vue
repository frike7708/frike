<template>
  <el-row :gutter="20">
    <el-col :span="20">
      <createModel></createModel>
    </el-col>
  </el-row>
</template>
<script>
  import createModel from '../components/createModel.vue'
  export default{
    data(){
      return {}
    },
    components: {
      createModel
    },
    mounted: function () {
      this.$store.commit('changeBreadcrumbData', [{
        name: '首页',
        to: '/'
      }, {
        name: '流程设计',
        to: '/process_model'
      }, {
        name: '创建模型',
        to: '/create_model'
      }]);
    }
  }
</script>
<style>

</style>
