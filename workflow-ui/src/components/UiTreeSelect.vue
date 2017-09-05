<template>
  <div class="qc-treeSelect">
    <!-- 一级搜索 -->
    <input type="text"
           class="qc-treeSelect-input"
           @click="toggleShow"
           v-model="chooseData.data">
    <!-- 搜索列表 -->
    <ul class="qc-treeSelect-root" v-if="chooseData.treeShow">
      <input type="text"
             class="qc-treeSelect-child-input">
      <template v-if="hasList">
        <treeSelectItems
          v-for="items in treeData"
          :key="items.id"
          :itemsRestData="items"
          :isFolder="items.children && items.children.length"
          :inputData="chooseData">
        </treeSelectItems>
      </template>
      <template v-else>
        <div class="qc-treeSelect-nolist">Not Found</div>
      </template>
    </ul>
  </div>
</template>
<script>
  import treeSelectItems from './UiTreeSelectItems.vue'
  export default{
    name: 'qc-treeSelect',
    data(){
      return {
        imRoot: true,
        hasList: true,
        open: true,
        chooseData: {
          data: '',
          treeShow: false
        }
      }
    },
    computed: {
      isFolder() {
        return this.treeData && this.treeData.length
      }
    },
    components: {
      treeSelectItems
    },
    props: [
      'treeData'
    ],
    methods: {
      toggleShow(){
        this.chooseData.treeShow = !this.chooseData.treeShow;
      },
      eventListener(e){
        console.log(1);
        if (!this.$el.contains(e.target)) this.chooseData.treeShow = false
      },
    },
    watch: {
      "chooseData.treeShow"(n){
        if (n) {
          document.addEventListener('click', this.eventListener, false)
        } else {
          document.removeEventListener('click', this.eventListener, false)
        }
      }
    }
  }
</script>
<style>

  ul, li {
    list-style: none
  }

  .qc-treeSelect-input {
    width: 100%;
    height: 30px;
    line-height: 30px;
    padding-left: 10px;
    border: 1px solid #d9d9d9;
    outline: none;
  }

  .qc-treeSelect-root {
    background: #fff;
    padding: 10px;
    box-shadow: 0 1px 6px rgba(0, 0, 0, .2);
    border-radius: 4px;
  }

  .qc-treeSelect-child-input {
    width: 100%;
    height: 30px;
    line-height: 30px;
    padding-left: 10px;
    border: 1px solid #d9d9d9;
    outline: none;
    border-radius: 4px;
    margin-bottom: 10px;
  }

  .qc-treeSelect-text-icon {
    display: inline-block;
    width: 16px;
    cursor: pointer;
  }

  .qc-treeSelect-nolist {
    padding-left: 10px;
  }

  .qc-treeSelect-root-show {
    cursor: pointer;
    position: relative;
    height: 30px;
    line-height: 30px;
  }

</style>
