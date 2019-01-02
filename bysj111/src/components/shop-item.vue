<template>
  <div class="item">
    <div>
      <div class="item-img"><img :alt="item.name" :src= "item.img_url" style="opacity: 1;">
      </div>
      <h6>{{item.name}}</h6>
      <h3 style="color: red">{{item.sell_point}}</h3>
      <div class="item-btns clearfix">
        <router-link :to="{name:'detail',query:{id:item.id}}">
          <span class="item-gray-btn">查看详情 </span>
        </router-link>
        <span v-if="loggedin" class="item-blue-btn" @click="addCarPanelData(item,userid)">加入购物车 </span>
        <span v-else class="item-blue-btn" @click="loginNeed">加入购物车 </span>
      </div>
      <div class="item-price clearfix">
        <i>¥</i><span>{{item.price}}</span>
      </div>
      <div class="buy_time">
        <p style="text-align: center">被购买过{{ item.buy_times }}次</p>
      </div>
      <div class="discount-icon">false</div>
      <div class="item-cover">
        <a href="javascript:;" target="_blank"></a>
      </div>
    </div>
  </div>
</template>

<style>

</style>

<script type="text/ecmascript-6">
  import axios from 'axios'
  import qs from 'qs'
  export  default{
    data(){
      return{
        itemIndex : 0
      }
    },
    //接受参数
    props:{
      item : Object
    },
  computed:{
    loggedin(){
      return this.$store.state.loggedin
    },
    userid(){
      return this.$store.state.userInfo.id
    }
  },
    methods:{
      tab(index){
        this.itemIndex = index
      },
      //tianjia gouwuche
      addCarPanelData(item,userid){
        axios.post('api/addToCar',qs.stringify({
          goodid:item.id,
          userid:userid
        }),{
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        }).then(res=>{
          console.log(res.data)
        })
        this.$store.commit('addCarPanelData',item)
      },
      loginNeed(){
        if(confirm('请先登录')){
          this.$router.push({
            path:'login'
          })
        }
      }

    }
  }
</script>
