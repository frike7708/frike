<template>
  <div>
    {{msg}}
  </div>
</template>
<script>
  export default{
    name: 'flow_edit',
    data(){
      return {
        msg: "您进入的是ID" + this.$route.query.id + "页面"
      }
    },
    mounted: function () {
//      this.utils.test2();
      this.$store.commit('changeBreadcrumbData', [{
        name: '首页',
        to: '/'
      }, {
        name: '对话列表',
        to: '/flow_list'
      }, {
        name: '对话详情：ID' + this.$route.query.id,
        to: '#'
      }]);
    },
  }
</script>
<style>

</style>
