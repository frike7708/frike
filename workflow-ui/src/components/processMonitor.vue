<template>
  <div>
    <el-dialog v-model="dialogVisible" size="large">
     <img src="http://localhost:8055/process/monitor/load_pic?processInstanceId=115745"/>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>

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
        label="执行ID"
        width="200"
        align="center"
        sortable>
      </el-table-column>

      <el-table-column
        prop="processInstanceId"
        label="流程实例ID"
        width="200">
      </el-table-column>

      <el-table-column
        prop="processDefinitionId"
        label="流程定义ID"
        width="200">
      </el-table-column>

      <el-table-column
        prop="processDefinitionKey"
        label="流程KEY"
        width="180">
      </el-table-column>

      <el-table-column
        prop="name"
        label="当前节点名称"
        align="center">
      </el-table-column>

      <el-table-column
        prop="suspended"
        label="是否挂起"
        width="180"
        align="center"
        sortable>
      </el-table-column>

      <el-table-column
        label="操作"
        align="center">
        <template scope="scope">
          <el-button
            @click.native.prevent="loadProcessDetail(scope.$index,tableData)"
            type="primary"
            icon="picture"
            size="small">
            查看流程
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <div v-show="listLoading" class="pagination-container">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page.sync="listQuery.page" :page-sizes="[10,20,30, 50]"
                     :page-size="listQuery.limit" layout="total, sizes, prev, pager, next, jumper" :total="total">
      </el-pagination>
    </div>

  </div>

</template>


<script>
  import Vue from 'vue'
  import $ from 'jquery'
  import pagination from '../components/pagination.vue'
  import {Table, TableColumn, Button,Dialog} from 'element-ui'
  Vue.use(Table);
  Vue.use(TableColumn);
  Vue.use(Button);
  Vue.use(Dialog);

  import { getProcessMonitorList } from '../api/api';

  export default {
    data() {
      return {
        list: null,
        total: 100,
        listLoading: true,
        listQuery: {
          page: 1,
          limit: 20
        },
        tableData: [],
        multipleSelection: [],
        dialogVisible: false
      }
    },
    methods: {
      handleSizeChange(val) {
        this.listQuery.limit = val;
        this.limit = val ;
        this.getList();
      },
      handleCurrentChange(val) {
        this.listQuery.page = val;
        this.page = val ;
        this.getList();
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },

      getList() {
        getProcessMonitorList({
          page : this.page,
          limit : this.limit
        }).then((response) => {
          this.total = response.data.total;
          this.tableData = response.data.list;
        });
      },

      loadProcessDetail(val, data){
        const id = data[val].id;
        this.$router.push('/process_monitor_detail?id=' + id);
      },
    },
    created:function(){
     this.getList();
    },

  }

</script>
<style>
  .pagination-container {
    text-align: right;
    margin-top: 30px;
  }
</style>
