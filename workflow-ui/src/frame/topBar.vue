<template>
  <el-menu theme="dark" :default-active="activeIndex" class="topBar" mode="horizontal" @select="handleSelect">
    <el-menu-item index="1">众安DWE管理后台</el-menu-item>

  </el-menu>
</template>
<script>
  import Hamburger from '../components/Hamburger';

	export default {
    components: {
      Hamburger
    },
		data() {
			return {
				activeIndex: '1',
			};
		},
		methods: {
			handleSelect(key, keyPath) {
				console.log(key, keyPath);
			}
		}
	}
</script>
