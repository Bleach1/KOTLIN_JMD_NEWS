// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'
import VueResource from 'vue-resource'
//import Users from './components/Users'
import Element from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

Vue.config.productionTip = false;
Vue.use(VueResource);
//https://segmentfault.com/a/1190000010336178 vue 生命周期
/* eslint-disable no-new */
//全局注册组件   一般不用
//Vue.component("users", Users);
Vue.use(Element);
new Vue({
  el: '#app',
  router,
  components: {App},
  template: '<App/>',

});
