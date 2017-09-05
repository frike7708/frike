<template>
  <processMonitor></processMonitor>
</template>
<script>
  import processMonitor from '../components/processMonitor.vue'
  export default{
    data(){
      return {}
    },
    components:{
      processMonitor
    },
    mounted: function () {
      this.$store.commit('changeBreadcrumbData', [{
        name: '首页',
        to: '/'
      }, {
        name: '运行中的流程监控',
        to: '/process_monitor'
      }]);
    }
  }
</script>
<style>

</style>
