// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import VueCookies from 'vue-cookies'
import store from './store'
import axios from 'axios'
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
    this.getSellerInfo()
  },
  methods:{
    getSellerInfo(){
      var seller_id = $cookies.get("seller_id")
      if(seller_id != null) {
        store.state.seller_id = seller_id
        axios.get("/back/getName",
          {
            params: {
              id: seller_id
            }
          }).then(res=> {
          store.state.name = res.data.name

          this.getOrder(seller_id)
          this.getGoods(seller_id)
          console.log(this.$store.state.goods)
        }).catch(error=> {
          console.log(error)
        })
      }
    },
    getOrder(id){
      axios.get("/back/getOrder",
        {
          params:{
            id:id
          }
        }).then(res=>{
        console.log(res.data)
        this.$store.state.order=res.data
      }).catch(error=>{console.log(error)})
    },
    getGoods(id){
      axios.get("/back/getGoods",
        {
          params:{
            id:id
          }
        }).then(res=>{
        this.$store.state.goods=res.data
      }).catch(error=>{console.log(error)})
    },
  }
})


router.beforeEach((to, from, next) => {
  console.log(11111111111111)
  console.log(store.state.seller_id)
  console.log(store.state.seller_id==null)
  console.log(11111111111111)
  window.document.title = to.meta.title;
  if(to.meta.requireAuth) {
    if(store.state.seller_id != ''){
      console.log('登录了')
      next()
    }else{
      console.log('跳去登录')
      next({
        path:'/login',
        query:{redirect:to.fullPath}
      })
    }
  }else{
    next()
  }
})
