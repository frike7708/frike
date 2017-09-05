<template>
  <div>
    <iframe :src="msg" frameborder="0" style="width:100%;height:1039px;"></iframe>
  </div>
</template>
<script>
  import Vue from 'vue'
  import $ from 'jquery'
  import {Table, TableColumn, Button} from 'element-ui'
  Vue.use(Table);
  Vue.use(TableColumn);
  Vue.use(Button);

//    var reqURL = this.$parent.$data.resultURL;
//    console.log(reqURL);
  export default{
    data(){
      return {
        msg: this.$route.query.resultURL
      }
    },
    mounted: function () {
      this.$store.commit('changeBreadcrumbData', [{
        name: '首页',
        to: '/'
      },{
        name: '流程设计',
        to: '/process_model'
      }, {
        name: '设计流程图',
        to: '#'
      }]);
    },
  }
</script>
<style>

</style>
