/**
 * Created by Administrator on 2018/4/26.
 */
import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

let store=new Vuex.Store({
  state:{
    name:'',
    seller_id:'',
    goods:[],
    order:[],
  },
  mutations:{
    reset(state){
      state.name=''
      state.seller_id=''
      state.goods=[]
      state.order=[]
    }
  },

})

export default store
