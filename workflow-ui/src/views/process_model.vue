<template>
  <div>
    <div>
      <el-button id="create_model_id"
                 type="primary"
                 size="large"
                 @click="createDialog = true">创建模型</el-button>

      <!-- create dialog start -->
      <el-dialog title="创建模型" v-model="createDialog" size="tiny">
        <el-form ref="createFrom" :model="createForm" label-width="80px">
          <el-form-item label="名称">
            <el-input v-model="createForm.name" class="el-col-24" ></el-input>
          </el-form-item>
          <el-form-item label="KEY">
            <el-input v-model="createForm.key" class="el-col-24"></el-input>
          </el-form-item>
          <el-form-item label="描述">
            <el-input type="textarea" v-model="createForm.description"></el-input>
          </el-form-item>s
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="createDialog = false">取 消</el-button>
          <el-button type="primary" @click="createModelSubmit()">创建</el-button>
        </span>
      </el-dialog>
      <!-- create dialog end -->
    </div>
    <processModel></processModel>

  </div>


</template>
<script>
  import $ from 'jquery'
  import Vue from 'vue'
  import {Dialog,Form,FormItem,Input,DatePicker} from 'element-ui'
  import processModel from '../components/processModel.vue'
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
      createModelSubmit() {
        var form = this.createForm;
        var jsonData = "{\"name\":"+ "\"" + form.name + "\"" +",\"key\":"+ "\"" +form.key +"\"" + ",\"description\":" + "\"" +form.description +"\"" + "}";
        //保存模型
        var url = global.HOST + '/process/model/create';
        var resultURL = "";
        $.ajax({
          type: "post",
          url: url,
          data: { param : jsonData},
          dataType: "json",
          async:false,
          success: function(data){
            resultURL = data.resultUrl;
           //location.href = url;
           //window.open(url);
          }
        });
        this.createDialog = false;
        this.$router.push('/process_model_create?resultURL=' + resultURL)
      }

    },
    components:{
      processModel
    },
    mounted: function () {
      this.$store.commit('changeBreadcrumbData', [{
        name: '首页',
        to: '/'
      }, {
        name: '流程设计',
        to: '/process_model'
      }]);
    }
  }
</script>
<style>

</style>
