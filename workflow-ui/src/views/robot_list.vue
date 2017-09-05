<template>
  <flowList></flowList>
</template>
<script>
  import flowList from '../components/flowList.vue'
  export default{
		data(){
			return {}
		},
    components:{
      flowList
    },
    mounted: function () {
//      this.utils.test1();
      this.$store.commit('changeBreadcrumbData', [{
        name: '首页',
        to: '/'
      }, {
        name: '机器人管理',
        to: '/robot_list'
      }]);
    }
	}
</script>
<style>

</style>
