<template>
  <div id="header">
    <div class="nav-global">
      <div class="container">
        <h1 class="nav-logo">
          <img src="../assets/img/ph/logo.png">
        </h1>
        <ul class="nav-aside">
          <li class="nav-user">
            <a href="javascript:;">个人中心</a>
            <!--active-->
            <div class="nav-user-wrapper" v-if="loggedin">
              <div class="nav-user-list">
                <router-link to="userCenter">
                <dl class="nav-user-avatar">
                    <dd><span class="ng-scope"></span></dd>
                    <dt class="ng-binding" v-if="userInfo.nickname == null">设置昵称</dt>
                    <dt class="ng-binding" v-else>{{ userInfo.nickname }}</dt>
                </dl>
                </router-link>
                <ul>
                  <li class="order"><router-link to="payedPage">我的订单</router-link></li>
                  <li class="car"><router-link to="carPanel">购物车</router-link></li>
                  <li class="logout" @click="logout"><router-link  to="login" >退出</router-link></li>
                </ul>
              </div>
            </div>
            <div class="nav-user-wrapper" v-else="">
              <div class="nav-user-list">
                <router-link to="login">

                <dl class="nav-user-avatar">
                    <dd><span class="ng-scope"></span></dd>
                    <dt class="ng-binding">请先登录</dt>
                </dl>
                </router-link>
              </div>
            </div>
          </li>
          <!--active-->
          <carPanel></carPanel>

        </ul>
        <ul class="nav-list">
          <li v-if="!loggedin"><router-link to="login">登录</router-link>/</li>
          <li v-if="!loggedin"><router-link to="regist">注册</router-link></li>

          <li><router-link to="Home">首页</router-link></li>
          <li><router-link to="shop">商品页面</router-link></li>
          <li><router-link to="carPanel">购物车</router-link></li>
          <li><router-link to="payedPage">我的订单</router-link></li>
          <li><router-link to="userCenter">个人中心</router-link></li>
        </ul>
      </div>
    </div>
  </div>
</template>

<style>

</style>

<script type="text/ecmascript-6">
  import carPanel from './car-panel.vue'


  export default{
    data(){
      return{
      }
    },
    components:{
      carPanel
    },
    computed:{
      loggedin(){
        return this.$store.state.loggedin
      },
      userInfo(){
        if(this.$store.state.loggedin){
          return this.$store.state.userInfo
        }
      }
    },
    methods:{
      logout(){
        this.$store.commit('reset')
      },
    }
  }
</script>
