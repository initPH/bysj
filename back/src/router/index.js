import Vue from 'vue'
import Router from 'vue-router'
import login from '../views/login.vue'
import regist from '../views/regist.vue'
import home from '../views/home.vue'
Vue.use(Router)
import goods from '../views/goods.vue'
import orders from '../views/orders.vue'
import changeinfo from '../views/changeInfo.vue'
export default new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      component: home,
      meta:{
        title:"首页",
      }
    },
    {
      path: '/login',
      name: 'login',
      component: login,
      meta:{
        title:"商家登录"
      }
    },{
      path: '/goods',
      name: 'goods',
      component: goods,
      meta:{
        title:"商品管理",
        requireAuth: true,
      }
    },
    {
      path: '/regist',
      name: 'regist',
      component: regist,
      meta:{
        title:"商家注册",
      }
    },
    {
      path: '/home',
      name: 'home',
      component: home,
      meta:{
        title:"管理后台",
        requireAuth: true,
      }
    },
    {
      path: '/orders',
      name: 'orders',
      component: orders,
      meta:{
        title:"订单管理",
        requireAuth: true,
      }
    },
    {
      path: '/changeinfo',
      name: 'changeinfo',
      component: changeinfo,
      meta:{
        title:"修改信息",
        requireAuth: true,
      }
    },



  ]
})
