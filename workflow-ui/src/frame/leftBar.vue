<template>

    <el-row class="tac" theme="dark">
      <el-col :span="35" >
        <el-menu :default-active=defaultIndex class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose" :default-openeds=defaultOpend :router="true">
          <el-submenu index="1">
            <template slot="title"><i class="fa fa-sitemap fa-lg" style="margin-right: 9px;"></i>流程管理</template>
            <el-menu-item-group>
              <el-menu-item index="10" :route="{path:'/process_list'}"><i class="fa fa-gear fa-lg" style="margin-right: 9px;"></i>流程定义及部署</el-menu-item>

              <el-menu-item index="11" :route="{path:'/process_model'}"><i class="fa fa-css3 fa-lg" style="margin-right: 9px;"></i>流程设计</el-menu-item>

              <el-menu-item index="12" :route="{path:'/process_monitor'}"><i class="fa fa-video-camera fa-lg" style="margin-right: 9px;"></i>流程监控</el-menu-item>

            </el-menu-item-group>
          </el-submenu>

          <el-submenu index="2">
            <template slot="title"><i class="fa fa-table fa-lg" style="margin-right: 9px;"></i>表单管理</template>
            <el-menu-item-group>
              <el-menu-item index="20" :route="{path:'/form_list'}"><i class="fa fa-book fa-lg" style="margin-right: 9px;"></i>我的表单</el-menu-item>

<!--
              <el-menu-item index="22" :route="{path:'/process_monitor'}"><i class="fa fa-database fa-lg" style="margin-right: 9px;"></i>数据库</el-menu-item>
-->

            </el-menu-item-group>
          </el-submenu>

          <el-submenu index="3">
            <template slot="title"><i class="fa fa-table fa-lg" style="margin-right: 9px;"></i>系统管理</template>
            <el-menu-item-group>
              <el-menu-item index="30" :route="{path:'/group_list'}"><i class="fa fa-users fa-lg" style="margin-right: 9px;"></i>用户组管理</el-menu-item>
              <el-menu-item index="31" :route="{path:'/user_list'}"><i class="fa fa-child fa-lg" style="margin-right: 9px;"></i>用户管理</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
        </el-menu>
      </el-col>
    </el-row>
</template>
<script>
  export default {
    data(){
      return {
        defaultOpend: ['1'],
        defaultIndex: '1'
      }
    },
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      }
    }
  }
</script>

