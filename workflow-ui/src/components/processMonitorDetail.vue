<template>
  <div>
    <el-table
      :data="tableData"
      style="width: 100%"
      border
      @selection-change="handleSelectionChange">

      <el-table-column
        prop="id"
        label="任务ID"
        width="200"
        align="center"
        sortable>
      </el-table-column>

      <el-table-column
        prop="name"
        label="任务名称"
        width="200">
      </el-table-column>

      <el-table-column
        prop="assignee"
        label="处理人"
        width="200">
      </el-table-column>

      <el-table-column
        prop="startTime"
        label="开始时间"
        width="200">
      </el-table-column>

      <el-table-column
        prop="endTime"
        label="结束时间"
        align="center">
      </el-table-column>

    </el-table>
  </div>
</template>

<script>
  import Vue from 'vue'
  import $ from 'jquery'
  import pagination from '../components/pagination.vue'
  import {Table, TableColumn, Button,Dialog} from 'element-ui'
  Vue.use(Table);
  Vue.use(TableColumn);
  Vue.use(Button);
  Vue.use(Dialog);

  export default {
    data() {
      return {
        tableData: [],
        multipleSelection: []
      }
    },
    methods: {
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },

    },
    created:function(){
      var url = global.HOST + '/process/monitor/taskDetail?processInstanceId='+ this.$route.query.id;
      var _self = this;
      $.ajax({
        type: "get",
        url: url,
        success: function (data) {
          _self.tableData = data;
        }
      });
    },
    components: {
    }
  }

</script>
