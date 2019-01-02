<template>
  <div id="app">
    <!--<headerNav></headerNav>-->
    <el-container>
      <el-header><headerNav></headerNav></el-header>
      <el-container>
        <el-aside width="250px" v-if="loggedin"><asideNav></asideNav></el-aside>
        <el-main><router-view/></el-main>
      </el-container>
    </el-container>
    <!--<asideNav></asideNav>-->

  </div>
</template>

<script>
  import headerNav from './components/headerNav.vue'
  import asideNav from './components/asideNav.vue'
export default {
  name: 'App',
  components:{
    headerNav,
    asideNav
  },
  computed:{
    loggedin(){
      if(this.$store.state.seller_id!=''){
        console.log('sssssssss')
        return true
      }else{
        console.log('aaaaaaaas')
        return false
      }
    }
  }
}
</script>

<style>

</style>
