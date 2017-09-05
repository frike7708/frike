// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import VueRouter from 'vue-router'
import Vuex from 'vuex'
import {Col, Row, Menu, Submenu, MenuItem, MenuItemGroup,Message} from 'element-ui'
import 'element-ui/lib/theme-default/index.css'
import App from './App.vue'
import routes from './router'
import storeOption from './store'

import './config'

import utils from './utils'
Vue.prototype.utils = utils;

import 'font-awesome/css/font-awesome.min.css';
Vue.use(VueRouter);
Vue.use(Vuex);
Vue.use(Col);
Vue.use(Row);
Vue.use(Menu);
Vue.use(Submenu);
Vue.use(MenuItem);
Vue.use(MenuItemGroup);

Vue.config.productionTip = false;

// 创建一个路由对象用于管理页面的路由
const router = new VueRouter({
  mode: 'history',
  routes
});

//创建vuex 管理store
const store = new Vuex.Store(storeOption);

// const whiteList = ['/login', '/authredirect', '/reset', '/sendpwd'];// 不重定向白名单
// router.beforeEach((to, from, next) => {
//   if (store.getters.token) { // 判断是否有token
//     if (to.path === '/login') {
//       next({ path: '/' });
//     } else {
//       next();
//     }
//   } else {
//     if (whiteList.indexOf(to.path) !== -1) {
//       // 在免登录白名单，直接进入
//       next()
//     } else {
//       next('/login'); // 否则全部重定向到登录页
//     }
//   }
// });


/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
});

Vue.prototype.$message = Message;

