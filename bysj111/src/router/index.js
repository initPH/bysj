import Vue from 'vue'
import Router from 'vue-router'
import '../assets/css/reset.css'
import '../assets/css/header.css'

import Home from '../views/home.vue'
import Shop from  '../views/shop.vue'
import DetailPage from '../views/DetailPage.vue'
import CarPanel from '../views/CarPanel.vue'
import PayedPage from '../views/PayedPage.vue'
import Login from '../views/login.vue'
import Regist from '../views/regist.vue'
import UserCenter from '../views/userCenter.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path:'/',
      component:Home,
      meta: {
        title: '首页'
      },
    },
    {
      path: '/Home',
      component : Home,
      meta: {
        title: '首页'
      },
    },{
      path: '/Shop',
      component : Shop,
      meta: {
        title: '商品列表'
      },
    },
    {
      path:'/detail',
      name:'detail',
      component:DetailPage,
      meta: {
        title: '商品详情'
      },
    },
    {
      path:'/carPanel',
      name:'carpanel',
      component:CarPanel,
      meta: {
        title: '购物车',
        requireAuth: true,
      },
    },
    {
      path:'/payedPage',
      name:'payedPage',
      component:PayedPage,
      meta: {
        title: '我的订单',
        requireAuth: true,
      },
    },
    {
      path:'/login',
      name:'login',
      component:Login,
      meta:{
        title:'登录'
      }
    },
    {
      path:'/regist',
      name:'regist',
      component:Regist,
      meta:{
        title:'注册'
      }
    },
    {
      path:'/userCenter',
      name:'userCenter',
      component:UserCenter,
      meta:{
        title:'用户中心',
        requireAuth: true,
      }
    },

  ]
})
