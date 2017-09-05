import Vue from 'vue';
import Vuex from 'vuex';
import app from './modules/app';
import user from './modules/user';
import getters from './getters';

Vue.use(Vuex);


export default {
  state: {
    count: 0,
    breadcrumb: {
      data: [{
        name: '首页',
        to: '/'
      }, {
        name: '对话列表',
        to: 'homepage'
      }]
    }
  },
  mutations: {
    changeBreadcrumbData (state, data) {
      state.breadcrumb.data = data
    }
  },
  modules: {
    app,
    user
  },
  getters
}
