<template>
  <el-breadcrumb separator="/">
    <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
    <el-breadcrumb-item>对话模板</el-breadcrumb-item>
    <el-breadcrumb-item>对话列表</el-breadcrumb-item>
  </el-breadcrumb>
</template>
<script>
	import Vue from 'vue'
	import {Breadcrumb, BreadcrumbItem} from 'element-ui'
	Vue.use(Breadcrumb);
	Vue.use(BreadcrumbItem);
	export default{
		data(){
			return {}
		}
	}
</script>
<style>

</style>