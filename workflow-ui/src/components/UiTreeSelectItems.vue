<template>
  <li style="margin-left:20px;">
    <span v-if="isFolder" class="qc-treeSelect-text-icon"
          @click="switchIcon">{{open ? '-' : '+'}}</span>
    <span class="qc-treeSelect-root-show" @click="getVal(itemsRestData.value)">{{itemsRestData.value}}</span>
    <template v-if="itemsRestData.children && open">
      <qc-treeSelect-items
        v-for="items in itemsRestData.children"
        :key="items.id"
        :itemsRestData="items"
        :isFolder="items.children && items.children.length"
        :inputData="inputData">
      </qc-treeSelect-items>
    </template>
  </li>
</template>
<script>
  export default{
    name: 'qc-treeSelect-items',
    data(){
      return {
        open: false
      }
    },
    props: [
      'itemsRestData', 'isFolder', 'inputData'
    ],
    methods: {
      switchIcon(){
        if (this.isFolder) {
          this.open = !this.open
        }
      },
      getVal(d){
        this.inputData.data = d;
        this.inputData.treeShow = false
      }
    }
  }
</script>
<style>

</style>
