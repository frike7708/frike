<template>
  <div>
    <!--<div style="width:500px;">-->
    <!--<UiTreeSelect :treeData="treeData"></UiTreeSelect>-->
    <!--</div>-->
    <tableList></tableList>
    <!--<flowList></flowList>-->
  </div>
</template>
<script>
  import tableList from '../components/tableList.vue'
  import flowList from '../components/flowList.vue'
  import UiTreeSelect from '../components/UiTreeSelect.vue'
  export default {
    name: 'homepage',
    data () {
      return {
        treeData: [
          {
            key: '0',
            value: '爷爷0'
          },
          {
            key: '1',
            value: '爷爷1',
            children: [
              {
                key: '1-0',
                value: '爸爸0'
              },
              {
                key: '1-1',
                value: '爸爸1',
                children: [
                  {
                    key: '1-1-0',
                    value: '孙子1'
                  },
                  {
                    key: '1-1-1',
                    value: '孙子2'
                  }
                ]
              }
            ]
          },
          {
            key: '2',
            value: '爷爷2',
            children: [
              {
                key: '2-0',
                value: '爸爸0'
              },
              {
                key: '2-1',
                value: '爸爸1',
              }
            ]
          }
        ]
      }
    },
    components: {
      tableList, UiTreeSelect, flowList
    },
    mounted: function () {
//      this.utils.test1();
      this.$store.commit('changeBreadcrumbData', [{
        name: '首页',
        to: '/'
      }, {
        name: '对话列表',
        to: '/homepage'
      }]);
    }
  }
</script>
