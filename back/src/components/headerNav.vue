<template>
  <div>
    <el-header>
      <img class="nav-logo" src="../assets/img/ph/logo.png">
      <span class="header-title">管&nbsp;理&nbsp;后&nbsp;台</span>
      <div class='login-regist' v-if="loggedin" >
        <span>{{shopName}}</span>
         <span @click="logout" style="color:white;">登出</span>
      </div>
      <div class='login-regist' v-else="">
        <router-link to="login"><span style="color: white">登录</span></router-link>/<router-link to="regist"><span style="color: white">注册</span></router-link>
      </div>
    </el-header>
  </div>
</template>

<script type="text/ecmascript-6">
  export default{
    computed:{
      loggedin(){
        console.log(this.$store.state.seller_id)
        return this.$store.state.seller_id
      },
      shopName(){
        return this.$store.state.name
      }
    },
    methods:{
      logout(){
        this.$cookies.remove("seller_id")
        this.$store.commit("reset")
        console.log($cookies.get("seller_id"))
        console.log(this.$store.state.loggedin)
        this.$router.push('login')
      }
    }
  }
</script>

<style>
  .login-regist{
    color:white;
    float: right;
    line-height: 60px;
    position: relative;
    right: 50px;
  }
  .el-header{
    background-color: black;
    text-align: center;
  }
  .header-title{
    color: white;
    font-size: 40px;
    position: relative;
    right: 80px;
  }
  .nav-logo{
    float: left;
    margin-top: 5px;
    margin-left: 20px;
  }
</style>
