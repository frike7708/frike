<template>
  <div>
    <el-row>
      <el-col :span="23" class="robot-block listHeight" v-for="(items,index) in listData" :key="items.id">
        <a class="name" :href="items.path">{{items.name}}</a>
        <div class="time">创建时间：<span>{{items.time}}</span></div>
        <div class="info">备注：{{items.info}}</div>
        <el-button-group class="button-group">
          <el-button type="primary" icon="edit" @click="editlistData(index)"></el-button>
          <el-button type="danger" icon="delete" @click="dellistData(index)"></el-button>
        </el-button-group>
      </el-col>
    </el-row>
    <div class="block" style="margin-top:20px;">
      <span class="wrapper">
        <el-button type="success" icon="plus">添加</el-button>
        <el-button type="danger" icon="delete">删除</el-button>
      </span>
    </div>
    <el-row type="flex" justify="end">
      <el-col :span="12" style="text-align:right;margin:20px 0;">
        <pagination></pagination>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import Vue from 'vue'
  import pagination from '../components/pagination.vue'
  import {Table, TableColumn, Button, ButtonGroup} from 'element-ui'
  Vue.use(Table);
  Vue.use(TableColumn);
  Vue.use(Button);
  Vue.use(ButtonGroup);

  export default {
    data() {
      return {
        listData: [
          {
            name: '尊享e生',
            path: '#1',
            time: '2017年11月11日',
            info: '啦啦啦啦啦1'
          }, {
            name: '投保流程2',
            path: '#2',
            time: '2017年11月11日',
            info: '啦啦啦啦啦2'
          }, {
            name: '投保流程3',
            path: '#3',
            time: '2017年11月11日',
            info: '啦啦啦啦啦3'
          }, {
            name: '投保流程4',
            path: '#4',
            time: '2017年11月11日',
            info: '啦啦啦啦啦4'
          }, {
            name: '投保流程5',
            path: '#5',
            time: '2017年11月11日',
            info: '啦啦啦啦啦5'
          }
        ],
      }
    },
    methods: {
      editlistData(index){
        
      },
      dellistData(index){
        this.listData.splice( index, 1)
      }
    },
    components: {
      pagination,
    }
  }
</script>
<style>
  .listHeight {
    height: 95px;
    border: 1px solid #e0e0e0;
  }

  .robot-block {
    padding: 5px 15px;
    margin-bottom: 10px;
    position: relative;
  }

  .robot-block .name {
    height: 40px;
    line-height: 40px;
    font-size: 20px;
    cursor: pointer;
    color: #1D8CE0;
    font-weight: bolder;
    letter-spacing: 2px;
  }

  .robot-block .name:hover {
    text-decoration: underline;
  }

  .robot-block .time {
    height: 25px;
    line-height: 25px;
    font-size: 13px;
    font-style: italic;
    letter-spacing: 1px;
  }

  .robot-block .time span {
    color: #1D8CE0;
  }

  .robot-block .info {
    height: 20px;
    line-height: 20px;
    font-size: 13px;
    letter-spacing: 1px;
  }

  .button-group {
    position: absolute;
    right: 20px;
    top: 50%;
    margin-top: -18px;
    height: 36px;
  }
</style>
