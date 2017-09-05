<template>
  <div>
    <el-table
      :data="tableData"
      style="width: 100%"
      border
      @selection-change="handleSelectionChange">
      <el-table-column
        type="selection"
        width="55"
        align="center">
      </el-table-column>
      <el-table-column
        prop="id"
        label="ID"
        width="230"
        align="center"
        sortable>
      </el-table-column>
      <el-table-column
        prop="name"
        label="名称"
        width="200">
      </el-table-column>
      <el-table-column
        prop="key"
        label="流程KEY">
      </el-table-column>

      <el-table-column
        prop="deploymentId"
        label="部署ID"
        width="180"
        align="center"
        sortable>
      </el-table-column>

      <el-table-column
        label="编辑"
        width="150"
        align="center">
        <template scope="scope">
          <el-button
            @click.native.prevent="urlJump(scope.$index,tableData)"
            type="primary"
            icon="edit"
            size="small">
            设计
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-row type="flex" justify="end">
      <el-col :span="12" style="text-align:right;margin:20px 0;">
        <pagination></pagination>
      </el-col>
    </el-row>
  </div>

</template>


<script>
  import Vue from 'vue'
  import $ from 'jquery'
  import pagination from '../components/pagination.vue'
  import {Table, TableColumn, Button} from 'element-ui'
  Vue.use(Table);
  Vue.use(TableColumn);
  Vue.use(Button);


  export default {
    data() {
      return {
        tableData: [{
          id: '1',
          name: '流程1',
          code: '<span>13123</span>',
          create: '周杨1',
          creat_time: '2017-04-19 16:59:59'
        }, {
          id: '2',
          name: '流程2',
          code: '<span>13123</span>',
          create: '周杨2',
          creat_time: '2017-04-20 16:59:59'
        }, {
          id: '3',
          name: '流程3',
          code: '<span>13123</span>',
          create: '周杨3',
          creat_time: '2017-04-21 16:59:59'
        }, {
          id: '4',
          name: '流程4',
          code: '<span>13123</span>',
          create: '周杨4',
          creat_time: '2017-04-22 16:59:59'
        }],
        multipleSelection: []
      }
    },
    methods: {
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      urlJump(val, data){
        const id = data[val].id;
        this.$router.push('/flow_edit?id=' + id);
      }
    },
    created:function(){
      //var url = 'http://127.0.0.1:8055/process/selectProcessList';
      var url = global.HOST + '/process/selectProcessList';
      var _self = this;
      $.ajax({
        type: "get",
        url: url,
        success: function (data) {
          _self.tableData = data;
        }
      });
    },
    components: {
      pagination
    }
  }

</script>
