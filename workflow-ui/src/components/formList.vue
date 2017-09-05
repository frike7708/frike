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
        width="200"
        align="center">
      </el-table-column>

      <el-table-column
        prop="code"
        label="编码"
        width="150"
        sortable>
      </el-table-column>

      <el-table-column
        prop="name"
        label="名称"
        width="150">
      </el-table-column>

      <el-table-column
        prop="gmtCreated"
        label="创建时间"
        width="190"
        align="center"
        sortable>
    </el-table-column>

      <el-table-column
        prop="creator"
        label="创建人"
        width="190"
        align="center"
        sortable>
      </el-table-column>


      <el-table-column
        label="操作"
        align="center">
        <template scope="scope">
          <el-button
            @click.native.prevent="handleEdit(scope.$index,tableData)"
            type="primary"
            icon="edit"
            size="small">
            编辑
          </el-button>
          <el-button
            @click.native.prevent="handleDelete(scope.$index,tableData)"
            type="danger"
            icon="delete"
            size="small">
            删除
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
  import {Table, TableColumn, Button} from 'element-ui'
  Vue.use(Table);
  Vue.use(TableColumn);
  Vue.use(Button);

  import {getFormList,editForm,removeForm} from '../api/api';

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
        multipleSelection: []
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
      getList() {
        getFormList({
          page : this.page,
          limit : this.limit
        }).then((response) => {
          this.total = response.data.total;
          this.tableData = response.data.list;
        });
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },

      handleEdit($index, row) {
        var data = row[$index];
        var code = data.code;
        var name = data.name;
        var metaInfo = data.metaInfo;
        this.$router.push('/form_reader?code=' + code + '&name=' + name + '&json=' + metaInfo)
      },

      handleDelete($index, row) {
          var data = row[$index];
          removeForm({
            id: data.id
          }).then((response) => {
              if(response.data.code == 0){
                this.$message.success('删除成功');
                //刷新頁面
                window.location.reload();
              }else{
                this.$message.error(response.data.msg);
              }
          }).catch(function (error) {
            this.$message.error('删除失败 ：' + error);
          });
      },

    },
    created:function(){
        this.getList();
    }

  }

</script>
<style>
  .pagination-container {
    text-align: right;
    margin-top: 30px;
  }
</style>
