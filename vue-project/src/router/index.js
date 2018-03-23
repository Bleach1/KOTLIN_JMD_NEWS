import Vue from 'vue'
import Router from 'vue-router'
import RouterEmpty from '@/components/RouterEmpty'

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'RouterEmpty',
      component: RouterEmpty,
    },
    //可以配置多组
    {
      path: '/router',
      name: 'RouterEmpty',
      component: RouterEmpty,
    }
  ],
  mode: "history"
})
