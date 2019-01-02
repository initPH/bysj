import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)
//创建store
let store = new Vuex.Store({
  state:{
    userInfo:'',
    loggedin:false,
    payedData:[],
    //存放数据
    carPanelData:[],
    //是否为最大值
    maxOff:false,
    //控制隐藏显示
    carShow : false,
    //创建小球
    ball:{
      show:false,
      el:null,
      img:''
    },
  },
  getters:{
    //计算数量总价
    totalCount(state){
      let count = 0
      state.carPanelData.forEach(goods=>{
        count += goods.count
      })
      return count
    },
    totalPrice(state){
      let price = 0
      state.carPanelData.forEach(goods=>{
        price += goods.price * goods.count
      })
      return price
    },
    getUserid(state){
      return state.userInfo.id
    }
  },
  mutations:{
    bindUserInfo(state,data){
      state.userInfo = data
    },
  addUserInfo(state,data){
    state.userInfo = data
  },
    addCarPanelData(state,data){
      //bOff 是否添加过
      let bOff = true
      state.carPanelData.forEach(goods=>{
        if(goods.id === data.id){
          goods.count ++
          //if (goods.count > goods.limit_num){
          //  goods.count --
          //  state.maxOff = true
          //  bOff = false
          //  return
          //}
          bOff = false
          state.carShow = true
          state.ball.show = true
          state.ball.img = data.img_url
          state.ball.el = event.path[0]
        }
      })
      if(bOff){
        state.ball.show = true
        state.ball.img = data.img_url
        state.ball.el = event.path[0]
        let goodsData = data
        Vue.set(goodsData,'count',1)
        state.carPanelData.push(goodsData)
        state.carShow = true
      }
    },
    delCarPanelData(state,id){
      state.carPanelData.forEach((goods,index)=>{
        if(goods.id === id){
          state.carPanelData.splice(index,1)
          return
        }
      })
    },
    delPayedData(state,id){
      state.payedData.forEach((goods,index)=>{
        if(goods.id === id){
          state.payedData.splice(index,1)
          return
        }
      })
    },
    verifyRecive(state,id){
      state.payedData.forEach(goods=>{
        if(goods.id === id){
          goods.is_recive = 1;
          return
        }
      })
    },
    //删除、显示购物车
    showCar(state){
      state.carShow = true
    },
    hideCar(state){
      state.carShow = false
    },
    closePrompt(state){
      state.maxOff = false
    },
    addPayedData(state,data){
      state.payedData.push(data)
    },
    login(state){
      state.loggedin = true
    },
    reset(state){
      state.userInfo='',
        state.loggedin=false,
        state.payedData=[],
        //存放数据
        state.carPanelData=[],
        //是否为最大值
        state.maxOff=false,
        //控制隐藏显示
        state.carShow = false,
        //创建小球
        state.ball={
        show:false,
          el:null,
          img:''
      }
    }
  }
})
//将store暴露
export default store
