<template>
  <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="form_edit">
    <el-form-item label="项目名称" prop="name">
      <el-input v-model="ruleForm.name"></el-input>
    </el-form-item>
    <el-form-item label="选择类型" prop="region">
      <el-select v-model="ruleForm.region" placeholder="请选择相应的机器人">
        <el-option label="尊享E生" value="1"></el-option>
        <el-option label="XXXX" value="2"></el-option>
      </el-select>
      <el-button type="" @click="">添加新的机器人</el-button>
    </el-form-item>
    <el-form-item label="Titil 名称" prop="title">
        <el-input></el-input>
    </el-form-item>
    <el-form-item label="Titil 扩展">
        <el-checkbox-group v-model="ruleForm.type">
            <el-checkbox label="打电话" name="extend"></el-checkbox>
            <el-checkbox label="发短信" name="extend"></el-checkbox>
            <el-checkbox label="在线客服" name="extend"></el-checkbox>
        </el-checkbox-group>
    </el-form-item>
    <el-form-item label="欢迎语句">
        <el-input type="textarea"></el-input>
    </el-form-item>
    <el-form-item label="非工作时间提示语">
        <el-input></el-input>
    </el-form-item>
    <el-form-item label="欢迎图标">
        <el-upload
          class="avatar-uploader"
          action="https://jsonplaceholder.typicode.com/posts/"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload">
          <img v-if="imageUrl" :src="imageUrl" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
    </el-form-item>
    <el-form-item label="引导选项">
        <el-input></el-input>
    </el-form-item>
    <el-form-item label="文本输入" prop="desc">
       <el-checkbox-group v-model="ruleForm.type">
            <el-checkbox label="图片上传支持" name="extend"></el-checkbox>
            <el-checkbox label="表情支持" name="extend"></el-checkbox>
            <el-checkbox label="语言支持" name="extend"></el-checkbox>
        </el-checkbox-group>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
      <el-button @click="resetForm('ruleForm')">重置</el-button>
    </el-form-item>
  </el-form>
</template>
<script>
  import Vue from 'vue'
  import {
    Form,
    FormItem,
    Input,
    Select,
    Option,
    DatePicker,
    TimePicker,
    Switch,
    Checkbox,
    CheckboxGroup,
    Radio,
    RadioGroup,
    Button,
    Upload
  } from 'element-ui'
  Vue.use(Form);
  Vue.use(FormItem);
  Vue.use(Input);
  Vue.use(Select);
  Vue.use(Option);
  Vue.use(DatePicker);
  Vue.use(TimePicker);
  Vue.use(Switch);
  Vue.use(Checkbox);
  Vue.use(CheckboxGroup);
  Vue.use(Radio);
  Vue.use(RadioGroup);
  Vue.use(Button);
  Vue.use(Upload);

  export default {
    data() {
      return {
        imageUrl: '',
        ruleForm: {
          name: '',
          region: '',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          resource: '',
          desc: ''
        },
        rules: {
          name: [
            {required: true, message: '请输入项目名称', trigger: 'blur'},
            {min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur'}
          ],
          region: [
            {required: true, message: '请选择活动区域', trigger: 'change'}
          ],
          title:[
            {required: true,message: '请输入Title名称', trigger: 'change'}
          ],
          date1: [
            {type: 'date', required: true, message: '请选择日期', trigger: 'change'}
          ],
          date2: [
            {type: 'date', required: true, message: '请选择时间', trigger: 'change'}
          ],
          type: [
            {type: 'array', required: true, message: '请至少选择一个活动性质', trigger: 'change'}
          ],
          resource: [
            {required: true, message: '请选择活动资源', trigger: 'change'}
          ],
          desc: [
            {required: true, message: '请填写内容', trigger: 'blur'}
          ]
        }
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            alert('submit!');
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      handleAvatarSuccess(res, file) {
        this.imageUrl = URL.createObjectURL(file.raw);
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      }
    }
  }
</script>
<style>
  .form_edit{
      height: 450px;
      overflow-y: scroll;
  }
  .form_edit .el-checkbox-group .el-checkbox + .el-checkbox {
    margin-left: 0;
  }

  .form_edit .el-checkbox-group .el-checkbox {
    float: left;
    width: 160px;
    padding-right: 20px;
    margin: 0;
    padding: 0;
  }

  .form_edit .line {
    text-align: center;
  }
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #20a0ff;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
