<template>
  <div class="detail-content">
    <img :src="mydata.img_url" class="detail-main-pic">

    <div class="detail-main-name">{{mydata.name}}</div>
    <div class="goods-price">{{mydata.price}}</div>
    <div class="goods-sell-point">{{mydata.sell_point}}</div>
    <div class="change-color">
      <div  style="margin-left:60px">
        <img @click="changeMainPic" :src="mydata.img_url"  >
      </div>
      <div>
        <img @click="changeMainPic" src="../assets/img/ph/red.png" >
      </div>
      <div>
        <img @click="changeMainPic" src="../assets/img/ph/green.png">
      </div>
      <div>
        <img @click="changeMainPic" src="../assets/img/ph/blue.png">
      </div>
      <div>
        <img @click="changeMainPic" src="../assets/img/ph/black.png" >
      </div>
    </div>
    <el-button type="primary" class="add-to-carpanel" @click="addCarPanelData(mydata)">加入购物车</el-button>
  </div>


</template>
<script type="text/ecmascript-6">
  import axios from 'axios'
  export default{
    data(){
      return {
        mydata:'',
      }
    },
   mounted(){
        axios.get("api/goodDetail",{
          params:{
            goodid : this.$route.query.id
          }
        }).then(res=>{
          this.mydata = res.data
        })

    },
    methods:{
      changeMainPic(){
        let mainPic = document.getElementsByClassName('detail-main-pic')[0]
        mainPic.src = event.path[0].src
      },
      addCarPanelData(data){
        this.$store.commit('addCarPanelData',data)
      }
    }

  }
</script>

<style scoped>
  .detail-content{
    width: 1080px;
    height: 500px;
  }
  .detail-main-pic{
    width: 400px;
    height: 400px;
    float: left;
  }
  .detail-main-name{
    font-size: 30px;
    width: 650px;
    float: left;
  }
  .goods-price{
    font-size: 40px;
    color: red;
    font-weight: 600;
    padding-top: 100px;
    padding-left: 500px;
  }
  .goods-sell-point{
    color: grey;
    font-size: 20px;
    padding-top: 20px;
    padding-left: 500px;
  }
  .detail-content{
    margin:20px auto  ;
  }
  .change-color div img{
    width: 60px;
    height: 60px;
    float: left;
    margin-left: 20px;
  }
  .change-color{
    margin-top: 30px;
  }
  .add-to-carpanel{
    position: relative;
    top: 100px;
    left: -300px
  }
</style>
