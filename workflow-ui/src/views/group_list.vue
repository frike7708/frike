<template>
  <div>
    <div>
      <el-button id="create_model_id"
                 type="primary"
                 size="large"
                 @click="createDialog = true">新建用户组</el-button>
      <!-- create dialog start -->
      <el-dialog title="新建用户组" v-model="createDialog" size="tiny">
        <el-form ref="createFrom" :model="createForm" :rules="rules" label-width="80px">
          <el-form-item label="ID"  prop="id">
            <el-input v-model="createForm.id" class="el-col-24" ></el-input>
          </el-form-item>
          <el-form-item label="名称">
            <el-input v-model="createForm.name" class="el-col-24"></el-input>
          </el-form-item>
          <el-form-item label="成员" prop="groupusers">
            <el-select v-model="value5" multiple placeholder="请选择（至少一个用户）">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value" >
              </el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="createDialog = false">取 消</el-button>
          <el-button type="primary" @click="createModelSubmit()">确定</el-button>
        </span>
      </el-dialog>
      <!-- create dialog end -->
    </div>
    <groupList></groupList>

  </div>


</template>
<script>
  import $ from 'jquery'
  import Vue from 'vue'
  import {Dialog,Form,FormItem,Input,DatePicker,Select,Option} from 'element-ui'
  import groupList from '../components/groupList.vue'
  import {selectUser,addGroup} from '../api/api';
  Vue.use(Dialog);
  Vue.use(Form);
  Vue.use(FormItem);
  Vue.use(Input);
  Vue.use(DatePicker);
  Vue.use(Select);
  Vue.use(Option);



  export default{
    data(){
      return {
        createDialog: false,
        createForm: {
          id: '',
          name: '',
          description: ''
        },
        rules:{
          id: [
            { required: true, message: '请输入用户组ID', trigger: 'blur' },
            { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
          ],
          groupusers:[
            { required: true, message: '请选择（至少一个用户）', trigger: 'blur' },
          ]
        },
        options: [],
        value5: ''
        }
    },
    methods: {
      createModelSubmit() {
        var form = this.createForm;
        var users = this.value5;
        addGroup({
          id: form.id,
          name: form.name,
          users: users
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


      },
      getUserList(){
        selectUser({

        }).then((response) => {
          this.options = response.data.list;
        });
      }

    },
    components:{
      groupList
    },
    created:function(){
      this.getUserList();
    },
    mounted: function () {
      this.$store.commit('changeBreadcrumbData', [{
        name: '首页',
        to: '/'
      }, {
        name: '用户组管理',
        to: '/group_list'
      }]);
    }
  }
</script>
<style>

</style>
