<template>
<div>
  <div>
    <!-- create dialog start -->
    <el-dialog title="编辑" v-model="createDialogEdit" size="tiny">
      <el-form ref="createFrom" :model="createForm"  label-width="80px">
          <el-form-item label="ID" >
            <el-input v-model="createForm.id" class="el-col-24"  :readonly="readonly"></el-input>
          </el-form-item>
          <el-form-item label="姓氏">
            <el-input v-model="createForm.firstName" class="el-col-24" ></el-input>
          </el-form-item>
          <el-form-item label="名称">
            <el-input v-model="createForm.lastName" class="el-col-24" ></el-input>
          </el-form-item>
          <el-form-item label="邮箱">
            <el-input v-model="createForm.email" class="el-col-24"></el-input>
          </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
          <el-button @click="createDialogEdit = false">取 消</el-button>
          <el-button type="primary" @click="editUserInfoSubmit()">确定</el-button>
      </span>
    </el-dialog>
    <!-- create dialog end -->
  </div>

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
        width="180"
        align="center"
        sortable>
      </el-table-column>

      <el-table-column
        prop="firstName"
        label="姓氏"
        width="150">
      </el-table-column>

      <el-table-column
        prop="lastName"
        label="名称"
        width="150">
      </el-table-column>

      <el-table-column
        prop="email"
        label="邮箱"
        width="250">
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
</div>

</template>


<script>
  import Vue from 'vue'
  import $ from 'jquery'
  import pagination from '../components/pagination.vue'
  import {Table, TableColumn, Button,Dialog,Form,FormItem,Input,DatePicker,Select,Option} from 'element-ui'
  Vue.use(Table);
  Vue.use(TableColumn);
  Vue.use(Button);
  Vue.use(Dialog);
  Vue.use(Form);
  Vue.use(FormItem);
  Vue.use(Input);
  Vue.use(DatePicker);
  Vue.use(Select);
  Vue.use(Option);

  import { getUserList,removeUser,editUserInfo } from '../api/api';


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
        createDialogEdit : false,
        createForm: {
          id: '',
          name: '',
          firstName: '',
          lastName: '',
          pwd:''
        },
      readonly : true
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
        getUserList({
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
        this.createForm.id = data.id;
        this.createForm.firstName = data.firstName;
        this.createForm.lastName = data.lastName;
        this.createForm.email = data.email;
        this.createDialogEdit = true;
      },

      editUserInfoSubmit(){
        var form = this.createForm;
        editUserInfo({
          id: form.id,
          firstName:form.firstName,
          lastName: form.lastName,
          email: form.email
        }).then((response) => {
          if(response.data.code == 0){
            this.$message.success('修改成功');
            this.createDialogEdit = false;
            this.getList();
          }else{
            this.$message.error(response.data.msg);
          }
        }).catch(function (error) {
          this.$message.error('修改失败 ：' + error);
        });
      },

      handleDelete($index, row) {
          var data = row[$index];
          removeUser({
            id: data.id
          }).then((response) => {
              if(response.data.code == 0){
                this.$message.success('删除成功');
                this.getList();
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
