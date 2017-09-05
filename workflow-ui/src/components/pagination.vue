<template>
  <div class="block">
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="page"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="20"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total">
    </el-pagination>
  </div>
</template>
<script>
	import Vue from 'vue'
	import {pagination} from 'element-ui'
	Vue.use(pagination);

	export default {
		methods: {
			handleSizeChange(val) {
			    alert(val);
				console.log(`每页 ${val} 条`);
			},
			handleCurrentChange(val) {
				this.currentPage = val;
				console.log(`当前页: ${val}`);
			},
      changeData(page,total){
        this.page = page;
        this.total = total;
      }

		},
		data() {
			return {
        page: 1,
        total : 100
			};
		}
	}
</script>
