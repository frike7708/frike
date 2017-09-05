<template>
  <div>
    <div v-for="(items,index) in list"
         @click="show(index)"
         :data-index="index">
      <!--如果点击事件不方便传index的话 这里的data-index就是将index绑定到了元素上 那click就是@click="show"-->
      {{items.name}}
    </div>
  </div>
</template>
<script>
  import son from '../components/son.vue'
  export default{
    data(){
      return {
        list: [
          {
            name: 1111111
          }, {
            name: 2222222
          }, {
            name: 3333333
          }
        ]
      }
    },
    components:{
      son
    },
    methods: {
//    	这个方法是方便接受index的
//      show(i){
//        alert(i);
//      },
//      这个方法是不方便接受 要直接获取点击事件的
      show(e){
        console.log(e.target.getAttribute('data-index'));
      }
    }
  }
</script>
<style>

</style>
