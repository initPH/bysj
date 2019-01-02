// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import VueCookies from 'vue-cookies'
import axios from 'axios'
import qs from 'qs'

Vue.config.productionTip = false
Vue.use(ElementUI)
Vue.use(VueCookies)
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>',
  mounted(){
    this.getUserInfo()
  },
  methods:{
    getCarData(user_id){
      axios.get('api/getCar',
        {
          params:{
            userid:user_id
          }
        }
      ).then(res=>{
          this.$store.state.carPanelData = res.data
          console.log(this.$store.state.carPanelData)
        })
        .catch(function (error) {
          console.log(error);
        })
    },
    getOrderData(user_id){
      axios.get('api/getOrder',
        {
          params:{
            userid:user_id
          }
        }
      ).then(res=>{
          this.$store.state.payedData = res.data
        })
        .catch(function (error) {
          console.log(error);
        })
    },
    getUserInfo(){
      var user_id = $cookies.get("user_id")
      if(user_id != null){
        axios.post('api/cookieLogin',qs.stringify({
            id:user_id
          }),{
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded'
            }
          })
          .then(res=> {
              this.$store.commit('login')
              this.$store.commit('bindUserInfo',res.data)
              this.getCarData(user_id)
              this.getOrderData(user_id)
        })
      }
    }
  }
})

router.beforeEach((to, from, next) => {
  window.document.title = to.meta.title;
  if (to.meta.requireAuth) { // 判断该路由是否需要登录权限
    if (store.state.loggedin == true) { // 通过vuex state获取当前的login是否存在
    next();
    }
    else {
      next({
        path: '/login',
        query: {redirect: to.fullPath} // 将跳转的路由path作为参数，登录成功后跳转到该路由
      })
    }
  }
  else {
    next();
  }
})
