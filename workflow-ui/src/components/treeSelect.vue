<template>
  <div class="treeSelect">
    <input type="text"
           class="treeSelect-input"
           @focus="toggleShow"
           v-model="inputData">
    <!--@blur="toggleShow"-->
    <ul class="treeSelect-root-wrap" v-if="treeShow">
      <li v-for="items in treeData">
        <span class="treeSelect-root-show open" @click="showVal(items.value)">{{items.label}}</span>
        <ul v-if="items.children" class="treeSelect-child-wrap">
          <li v-for="citems in items.children">
            <span @click="showVal(citems.value)">{{citems.label}}</span>
          </li>
        </ul>
      </li>
    </ul>
  </div>
</template>
<script>
  export default{
    data(){
      return {
        treeShow: false,
        inputData: '',
        treeData: [{
          label: 'Node1',
          value: '0-0',
          key: '0-0',
          children: [{
            label: 'Child Node1',
            value: '0-0-1',
            key: '0-0-1',
          }, {
            label: 'Child Node2',
            value: '0-0-2',
            key: '0-0-2',
          }],
        }, {
          label: 'Node2',
          value: '0-1',
          key: '0-1',
        }]
      }
    },
    methods: {
      toggleShow(){
        this.treeShow = !this.treeShow;
      },
      showVal(e){
        this.inputData = e
      }
    }
  }
</script>
<style>
  * {
    padding: 0;
    margin: 0;
  }

  ul, li {
    list-style: none
  }

  .treeSelect-root-wrap {
    background: #efefef;
    padding-left: 10px;
  }

  .treeSelect-input {
    width: 100%;
    font-size: 14px;
    height: 30px;
    line-height: 30px;
    text-indent: 10px;
  }

  .treeSelect-root-show {
    font-size: 14px;
    cursor: pointer;
    position: relative;
    height: 30px;
    line-height: 30px;
    padding-left: 20px;
  }

  .treeSelect-root-show:after {
    content: '+';
    display: block;
    position: absolute;
    width: 20px;
    height: 20px;
    text-align: center;
    line-height: 20px;
    left: 0;
    top: 50%;
    margin-top: -10px;
  }

  .treeSelect-root-show.open:after {
    content: '-';
    display: block;
  }

  .treeSelect-child-wrap {
    padding-left: 20px;
  }

  .treeSelect-child-wrap li span {
    font-size: 12px;
    cursor: pointer;
  }
</style>
