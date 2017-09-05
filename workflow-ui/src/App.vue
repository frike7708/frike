<template>
    <div id="app">
      <TopBar></TopBar>
      <el-row class="tac" :gutter="10">
        <el-col :span="3">
          <leftBar></leftBar>
        </el-col>
        <el-col :span="20">
          <div class="block" style="margin:15px 0">
            <breadcrumb></breadcrumb>
          </div>
          <router-view></router-view>
        </el-col>
      </el-row>
    </div>
</template>

<script>
  import leftBar from './frame/leftBar.vue'
  import TopBar from './frame/TopBar.vue'
  import breadcrumb from './frame/breadcrumb.vue'
  import './assets/reset.css'
  export default {
    name: 'backend_Wrap',
    components: {
      leftBar,
      TopBar,
      breadcrumb
    }
  }
</script>
<style>
  .el-col.el-col-3 {
    padding-left: 5px;
    padding-right: 5px;
    height: 1300px;
    background: aliceblue;
    background-color: #eef1f6;
  }
</style>
