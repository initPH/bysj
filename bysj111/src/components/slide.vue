<template>
  <el-carousel :interval="4000" type="card" height="280px">
    <el-carousel-item v-for="(img,index) in imgList" :key="index">
      <img :src="imgList[index].url">

    </el-carousel-item>
  </el-carousel>
</template>
<script>
  export default{
    data(){
      return{
        imgList:[
          {url:require('../assets/img/ph/swipe2.jpg')},
          {url:require('../assets/img/ph/swipe3.jpg')},
          {url:require('../assets/img/ph/swipe4.jpg')},]

      }
    }
  }
</script>
<style>
  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
</style>
