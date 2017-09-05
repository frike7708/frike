<template>
  <div>
    <div>
      <el-button id="create_model_id"
                 type="primary"
                 size="large"
                 @click="createDialog = true">新建用户</el-button>

      <!-- create dialog start -->
      <el-dialog title="添加新用户" v-model="createDialog" size="tiny">
        <el-form ref="createFrom" :model="createForm" label-width="80px">
          <el-form-item label="ID">
            <el-input v-model="createForm.id" class="el-col-24" ></el-input>
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
          <el-form-item label="登录密码" >
            <el-input type="password" v-model="createForm.pwd" auto-complete="off"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="createDialog = false">取 消</el-button>
          <el-button type="primary" @click="createModelSubmit()">确定</el-button>
        </span>
      </el-dialog>
      <!-- create dialog end -->
    </div>
    <userList></userList>

  </div>


</template>
<script>
  import $ from 'jquery'
  import Vue from 'vue'
  import {Dialog,Form,FormItem,Input,DatePicker} from 'element-ui'
  import userList from '../components/userList.vue'
  import {addUser} from '../api/api';
  Vue.use(Dialog);
  Vue.use(Form);
  Vue.use(FormItem);
  Vue.use(Input);
  Vue.use(DatePicker);

  export default{
    data(){
      return {
        createDialog: false,
        createForm: {
          id: '',
          name: '',
          firstName: '',
          lastName: '',
          pwd:''
        }
      }
    },

    methods: {
      createModelSubmit() {
        var form = this.createForm;
        addUser({
          id: form.id,
          firstName:form.firstName,
          lastName: form.lastName,
          email: form.email,
          pwd : form.pwd
        }).then((response) => {
          if(response.data.code == 0){
            this.$message.success('添加成功');
            //刷新頁面
            window.location.reload();
          }else{
            this.$message.error(response.data.msg);
          }
        }).catch(function (error) {
          this.$message.error('添加失败 ：' + error);
        });
      }

    },
    components:{
      userList
    },
    mounted: function () {
      this.$store.commit('changeBreadcrumbData', [{
        name: '首页',
        to: '/'
      }, {
        name: '用户列表',
        to: '/user_list'
      }]);
    }
  }
</script>
<style>

</style>
