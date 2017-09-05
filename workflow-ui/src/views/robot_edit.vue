<template>
    <el-tabs v-model="activeName" type="border-card" @tab-click="handleClick">
        <el-tab-pane label="基本信息" name="first">
            <el-col :span="7">
                <display_area></display_area>
            </el-col>
            <el-col :span="17">
                <form_edit></form_edit>
            </el-col>
        </el-tab-pane>
        <el-tab-pane label="对话" name="second">对话</el-tab-pane>
        <el-tab-pane label="引导" name="third">引导</el-tab-pane>
        <el-tab-pane label="流程设计" name="fourth">
            <flowchart></flowchart>
        </el-tab-pane>
        <el-tab-pane label="表单列表" name="fifth">表单列表</el-tab-pane>
        <el-tab-pane label="流程设计" name="sixth">流程设计</el-tab-pane>
    </el-tabs>
</template>

<script>
import Vue from 'vue'
import {Tabs, TabPane} from 'element-ui'
import form_edit from '../components/form_edit'
import display_area from '../components/display_area'
import flowchart from '../components/flowchart'
Vue.use(Tabs);
Vue.use(TabPane);
    export default{
        data(){
            return {
                activeName: 'first'
            }
        },
        components:{
            form_edit,
            display_area,
            flowchart
        },
        mounted:function(){
            this.$store.commit('changeBreadcrumbData',[{
                name: '首页',
                to: '/'
            },{
                name: '机器人管理',
                to: '/robot_list'
            },{
                name: '机器人编辑',
                to: '/rebot_edit'
            }])
        },
        methods: {
            handleClick( tab, event){
                console.log( tab, event)
            }
        }
    }
</script>