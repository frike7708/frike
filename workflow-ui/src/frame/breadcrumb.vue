<template>
  <el-breadcrumb separator="/">
    <el-breadcrumb-item v-for="items in data.data" :key="items.id" :to="{path: items.to}">{{items.name}}</el-breadcrumb-item>

  </el-breadcrumb>
</template>
<script>
  import Vue from 'vue'
  import {Breadcrumb, BreadcrumbItem} from 'element-ui'
  Vue.use(Breadcrumb);
  Vue.use(BreadcrumbItem);
  export default{
    data(){
      return {
        data: this.$store.state.breadcrumb
      }
    }
  }
</script>
<style>

</style>
