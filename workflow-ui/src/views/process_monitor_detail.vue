<template>
  <div>
    <processMonitorDetail></processMonitorDetail>
    <img :src="msg" />
  </div>
</template>
<script>
  import processMonitorDetail from '../components/processMonitorDetail.vue'

  export default{
    name: 'process_monitor_detail',
    data(){
      return {
        msg: global.HOST + "/process/monitor/load_pic?processInstanceId=" + this.$route.query.id
      }
    },
    components:{
      processMonitorDetail
    },
    mounted: function () {
      this.$store.commit('changeBreadcrumbData', [{
        name: '首页',
        to: '/'
      }, {
        name: '运行中的流程监控',
        to: '/process_monitor'
      }, {
        name: '详情：ID' + this.$route.query.id,
        to: '#'
      }]);
    },
  }
</script>
<style>

</style>
