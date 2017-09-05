<template>
  <el-menu :default-active=defaultIndex class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose"
           :default-openeds=defaultOpend>
    <el-submenu index="1">
      <template slot="title"><i class="el-icon-message"></i>对话模板</template>
      <el-menu-item-group>
        <template slot="title">分组一</template>
        <el-menu-item index="1-1">选项1</el-menu-item>
        <el-menu-item index="1-2">选项2</el-menu-item>
      </el-menu-item-group>
      <el-menu-item-group title="分组2">
        <el-menu-item index="1-3">选项3</el-menu-item>
      </el-menu-item-group>
      <el-submenu index="1-4">
        <template slot="title">选项4</template>
        <el-menu-item index="1-4-1">选项1</el-menu-item>
      </el-submenu>
    </el-submenu>
    <el-menu-item index="2"><i class="el-icon-menu"></i>流程设计</el-menu-item>
    <el-menu-item index="3"><i class="el-icon-setting"></i>卡片管理</el-menu-item>
  </el-menu>

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