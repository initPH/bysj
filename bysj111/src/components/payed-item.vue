<template>
  <div>
    <hr>
    <li class="car-item">
      <img :src="item.img_url" class="car-item-img">
      <div class="car-item-name">{{item.name}}
        <br><div style="font-size: 0.8em;margin-top:10px;text-align: left">{{item.sell_point}}</div>
      </div>
      <div class="car-item-sell-point"></div>
      <div class="car-item-price">￥{{item.price}}</div>
      <div class="car-item-count">{{item.count}}</div>
      <div class="total-money">￥{{item.count*item.price}}</div>
      <el-button type="success" icon="el-icon-check" circle></el-button>
      <el-button disabled type="primary">已支付</el-button>
      <div class="receve-addr">地址:{{ item.addr }}
      </div>
      <div v-if="item.is_send" style="float: right;margin-right: 40px">
        <div v-if="!item.is_recive">
          <span style="margin-right: 80px;line-height: 40px">物品在路上......</span>
          <el-button  class="verify-button" type="danger" style="position: relative;left: 10px" @click="verifyRecive(item.id)">确认收货</el-button>
        </div>
        <div v-else>
          <el-button disabled type="danger">已确认</el-button>
        </div>
      </div>
      <div v-else="" style="float: right;margin-right: 40px">
          <span style="margin-right: 100px;line-height: 40px">商家处理中......</span>
          <el-button type="danger" @click="refund(item.id)">退款</el-button>

      </div>
  </li>

  </div>

</template>
<script type="text/ecmascript-6">
  import axios from 'axios'
  export default{
    data(){
      return{
        value:''
      }
    },
    props:{
      item:Object,
    },
    computed:{
      userInfo(){
        return this.$store.state.userInfo
      }
    },
    methods:{
      verifyRecive(goodid){
        this.$store.commit('verifyRecive',goodid)
        axios.get('api/verifyRecive',
          {
            params:{
              userid:this.$store.getters.getUserid,
              goodid:goodid
            }
          }
        ).then(res=>{
            this.$message({
              showClose: true,
              message: res.data.msg,
              type: 'success'
            });
          this.$router.push({
            path:'payedPage'
          })
          })
          .catch(function (error) {
            console.log(error);
          })
      },
      refund(goodid){
        this.$store.commit('delPayedData',goodid)
        axios.get('api/refund',
          {
            params:{
              userid:this.$store.getters.getUserid,
              goodid:goodid
            }
          }
        ).then(res=>{
                    this.$message({
                    showClose: true,
                    message: res.data.msg,
                    type: 'success'
                  });
          })
          .catch(function (error) {
            console.log(error);
          })
      },
    }

  }
</script>

<style scoped>
  .car-item-name{
    width: 300px;
    float: left;
    font-size: 18px;
    margin-left: 10px;
  }


  .car-item{
    height: 100px;
    margin-top: 20px;
    margin-bottom: 10px;
  }
  .car-item-img{
    width: 80px;
    height: 80px;
    float: left;
  }
  .car-item-price{
    color: red;
    font-size: 30px;
    font-weight: 500;
    float: left;
    margin-left: 100px;
    margin-top: 10px;
  }

  .car-item-count{
    float: left;
    font-size: 30px;
    width: 150px;
    line-height: 40px;
    margin-top: 10px;
  }
  .total-money{
    float: left;
    width: 150px;
    font-size: 30px;
    color: red;
    margin-top: 10px;
  }
  .operate-button{
    width: 120px;
    margin-top: 20px;
  }
  .receve-addr{
    position: relative;
    left: -200px;
    top: 10px;
    color: gray;
    font-size: 16px;
  }
</style>
