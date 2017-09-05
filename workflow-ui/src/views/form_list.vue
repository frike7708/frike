<template>
  <div>
    <div>
      <el-button id="create_model_id"
                 type="primary"
                 size="large"
                 @click="createDialog = true">新建表单</el-button>

      <!-- create dialog start -->
      <el-dialog title="新建表单" v-model="createDialog" size="tiny">
        <el-form ref="createFrom" :model="createForm" label-width="80px">
          <el-form-item label="编码">
            <el-input v-model="createForm.code" class="el-col-24" ></el-input>
          </el-form-item>
          <el-form-item label="名称">
            <el-input v-model="createForm.name" class="el-col-24"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="createDialog = false">取 消</el-button>
          <el-button type="primary" @click="createFormSubmit()">创建</el-button>
        </span>
      </el-dialog>
      <!-- create dialog end -->
    </div>
    <FormList></FormList>

  </div>


</template>
<script>
  import $ from 'jquery'
  import Vue from 'vue'
  import {Dialog,Form,FormItem,Input,DatePicker} from 'element-ui'
  import FormList from '../components/formList.vue'
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
          name: '',
          key: '',
          description: ''
        }
      }
    },

    methods: {
      createFormSubmit() {
        var form = this.createForm;
        var code = form.code;
        var name = form.name;
        this.createDialog = false;
        this.$router.push('/form_builder?code=' + code + '&name='+ name);
      }
    },
    components:{
      FormList
    },
    mounted: function () {
      this.$store.commit('changeBreadcrumbData', [{
        name: '首页',
        to: '/'
      }, {
        name: '表单列表',
        to: '/form_list'
      }]);
    }
  }
</script>
<style>

</style>
