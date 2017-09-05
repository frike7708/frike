<template>
  <el-row :gutter="20">
    <el-col :span="4">
      <div class="grid-content bg-purple">123123</div>
    </el-col>
    <el-col :span="20">
      <form_create></form_create>
    </el-col>
  </el-row>
</template>
<script>
  import form_create from '../components/form_create.vue'
  export default{
    data(){
      return {}
    },
    components: {
      form_create
    },
    mounted: function () {
      this.$store.commit('changeBreadcrumbData', [{
        name: '首页',
        to: '/'
      }, {
        name: '机器人管理',
        to: '/robot_list'
      }, {
        name: '机器人创建',
        to: '/robot_create'
      }]);
    }
  }
</script>
<style>

</style>
