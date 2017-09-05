<template>

  <div>
    <div>
      <el-collapse>
        <el-collapse-item title="部署新流程" name="1" >
          <div>
            <el-upload
              class="upload-demo"
              drag
              :action= "action"
              :on-success="handleUploadScucess"
              :show-file-list="flag"
              multiple>
              <i class="el-icon-upload"></i>
              <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
              <div class="el-upload__tip" slot="tip">支持文件格式：zip、bar、bpmn、bpmn20.xml</div>
            </el-upload>
          </div>
        </el-collapse-item>
      </el-collapse>
    </div>

    <processList></processList>
  </div>


</template>
<script>

  import $ from 'jquery'
  import Vue from 'vue'
  import {Dialog,Form,FormItem,Input,DatePicker,Upload,Collapse,CollapseItem} from 'element-ui'
  Vue.use(Dialog);
  Vue.use(Form);
  Vue.use(FormItem);
  Vue.use(Input);
  Vue.use(DatePicker);
  Vue.use(Upload);
  Vue.use(Collapse);
  Vue.use(CollapseItem);

  import processList from '../components/processList.vue'
  export default{
    data(){
      return {
        action : global.HOST + "/process/deployed/deploy",
        flag : true
      }
    },
    methods: {
      handleUploadScucess() {
        this.$message.success('部署成功');
      },
    },
    components:{
      processList
    },
    mounted: function () {
      this.$store.commit('changeBreadcrumbData', [{
        name: '首页',
        to: '/'
      }, {
        name: '已部署的流程列表',
        to: '/process_list'
      }]);
    }
  }
</script>
<style>
  .el-collapse {
    width: 100%;
    border-radius: 4px;
    text-align: center;
    color: #20A0FF;
    box-sizing: border-box;
    margin-right: 20px;
  }

</style>
