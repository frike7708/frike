<template>
  <processList></processList>
</template>
<script>
  import processList from '../components/processList.vue'
  export default{
    data(){
      return {}
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

</style>
