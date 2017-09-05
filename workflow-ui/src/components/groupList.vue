<template>
  <div>
    <div>
      <!-- create dialog start -->
      <el-dialog title="编辑" v-model="createDialogEdit" size="tiny">
        <el-form ref="createFrom" :model="createForm" :rules="rules" label-width="80px">
          <el-form-item label="ID"  prop="id">
            <el-input v-model="createForm.id" class="el-col-24"  :readonly="readonly"></el-input>
          </el-form-item>
          <el-form-item label="名称">
            <el-input v-model="createForm.name" class="el-col-24"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="createDialogEdit = false">取 消</el-button>
          <el-button type="primary" @click="editGroupInfoSubmit()">确定</el-button>
        </span>
      </el-dialog>
      <!-- create dialog end -->
    </div>
    <div>
      <!-- create dialog start -->
      <el-dialog title="添加组用户" v-model="createDialog"  size="tiny">
        <section class="main" v-model="groupId">
          <div class="crumbs">
            <el-breadcrumb separator="/">
              <el-breadcrumb-item><i class="fa fa-arrows-h fa-lg"></i> 向左拖拽为添加组用户，向右拖拽为删除组用户</el-breadcrumb-item>
            </el-breadcrumb>
          </div>
          <div class="drag-box-left" >
            <div class="drag-title">可添加用户</div>
            <div class="drag-list" draggable="true"
                 v-for="list in data1"
                 :data-id="list.id"
                 @dragstart="dragstartEvent"
                 @dragend="dragendEvent"
                 @dragenter="dragenterEvent"
                 @dragleave="dragleaveEvent"
                 @dragover="dragoverEvent"
            >{{list.title}}</div>
          </div>
          <div class="drag-box-right" id="userDiv">
            <div class="drag-title">已添加组用户</div>
            <div class="drag-list" draggable="true"
                 v-for="list in data2"
                 :data-id="list.id"
                 @dragstart="dragstartEvent"
                 @dragend="dragendEvent"
                 @dragenter="dragenterEvent"
                 @dragleave="dragleaveEvent"
                 @dragover="dragoverEvent"
            >{{list.title}}</div>
              <el-button type="primary" @click="addUserToGroup()" style="">确定</el-button>
          </div>

        </section>
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
        width="120"
        align="center"
        sortable>
      </el-table-column>

      <el-table-column
        prop="name"
        label="名称"
        width="250"
        align="center">
      </el-table-column>

      <el-table-column
        prop="users"
        label="成员"
        align="center">
      </el-table-column>

      <el-table-column
        label="操作"
        width="400"
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
            @click.native.prevent="handleAddUser(scope.$index,tableData)"
            type="primary"
            icon="plus"
            size="small">
            添加成员
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

  import {getUserGroupList,otherGroupUsers,currentGroupUsers,removeGroup,addUserToGroup,editGroupName} from '../api/api';

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
        createDialog: false,
        createDialogEdit : false,
        createForm: {
          id: '',
          name: ''
        },
        rules:{
          id: [
            { required: true, message: '请输入用户组ID', trigger: 'blur' },
            { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
          ]
        },
        dragElement: null,
        lock: true,
        data1: [],
        data2: [],
        groupId:'',
        listValue: '',
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
        getUserGroupList({
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
        var id = data.id;
        var name = data.name;
        this.createForm.id = id;
        this.createForm.name = name;
        this.createDialogEdit = true;
      },

      editGroupInfoSubmit(){
        var form = this.createForm;
        editGroupName({
          id: form.id,
          name: form.name,
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
      handleAddUser($index, row) {
        var data = row[$index];
        this.groupId = data.id;
        this.createDialog = true;
          //获取不在当前组用户
        otherGroupUsers({
          id: data.id
        }).then((response) => {
          this.data1 = [];
          this.data1 = response.data.list
        })
        //获取当前组的用户
        currentGroupUsers({
          id: data.id
        }).then((response) => {
          this.data2 = [];
          this.data2 = response.data.list
        })
//     this.$router.push('/group_user_drag?id='+ data.id)
      },
      handleDelete($index, row) {
          var data = row[$index];
          removeGroup({
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

      dragstartEvent(ev) {
        const self = this;
        self.dragElement = ev.target;
        ev.target.style.backgroundColor = '#f8f8f8';
      },
      dragendEvent(ev) {
        ev.target.style.backgroundColor = '#fff';
        ev.preventDefault();
      },
      dragenterEvent(ev) {
        const self = this;
        if(self.dragElement != ev.target){
          ev.target.parentNode.insertBefore(self.dragElement, ev.target);
        }
      },
      dragleaveEvent(ev) {
        const self = this;
        if(self.dragElement != ev.target){
          if(self.lock && (ev.target == ev.target.parentNode.lastElementChild || ev.target == ev.target.parentNode.lastChild)){
            ev.target.parentNode.appendChild(self.dragElement);
            self.lock = false;
          }else{
            self.lock = true;
          }
        }
      },
      dragoverEvent(ev) {
        ev.preventDefault();
      },
      addUserToGroup(){
        var id = this.groupId;
        var users = [];
        var div=document.getElementById('userDiv');
        var lis=div.children;
        for(var i=0;i<lis.length;i++){
            if( i != 0 && i != (lis.length -1)){
              users.push(lis.item(i).innerHTML)
            }
        }
        addUserToGroup({
          id: id,
          users: users
        }).then((response) => {
          if (response.data.code == 0) {
            this.$message.success('添加成功');
            this.createDialog = false;
            this.getList();
          } else {
            this.$message.error(response.data.msg);
          }
        });
      }
    },
    created:function(){
        this.getList();
    }

  }

</script>
<style scoped>
  .drag-box-left{
    float: left;
    width: 45%;
  }
  .drag-box-right{
    float: right;
    width: 45%;
  }
  .drag-list{
    border: 1px solid #ddd;
    padding:5px;
    margin-bottom: 20px;
    transition: border .3s;
  }
  .drag-list:hover{
    border: 1px solid #20a0ff;
  }
  .drag-title{
    font-weight: 400;
    line-height: 50px;
    margin: 10px 0;
    font-size: 22px;
    color: #1f2f3d;
  }
</style>
