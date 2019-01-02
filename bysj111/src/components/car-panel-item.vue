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
      <el-button @click="paySuccess(item,userInfo.id,value)" class="operate-button" type="primary">结算</el-button>
      <div class="receve-addr" v-if="userInfo.addr1==null && userInfo.addr2==null && userInfo.addr3==null">
        地址:<el-select v-model="value"
                      placeholder="请前往个人中心设置地址">
        <el-option
          v-for="option in options"
          :key="option.value"
          :label="option.label"
          :value="option.value">
        </el-option>
      </el-select>
      </div>

      <div class="receve-addr" v-else="">地址:<el-select v-model="value">
        <el-option
          v-for="option in options"
          :key="option.value"
          :label="option.label"
          :value="option.value">
        </el-option>
      </el-select>
      </div>
    </li>
  </div>

</template>
<script>
  import axios from 'axios'
  import qs from 'qs'
  export default{
    data(){
      return{
        options: [{
          value: this.$store.state.userInfo.addr1,
          label: this.$store.state.userInfo.addr1
        }, {
          value: this.$store.state.userInfo.addr2,
          label: this.$store.state.userInfo.addr2
        }, {
          value: this.$store.state.userInfo.addr3,
          label: this.$store.state.userInfo.addr3
        },],
        value:this.$store.state.userInfo.addr1,
      }
    },
    props:{
      item:Object,
    },
    methods:{
      paySuccess(item,userid,addr){
        axios.post('api/addToOrder',qs.stringify({
          goodid:item.id,
          userid:userid,
          count:item.count,
          addr:addr
        }),{
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        }).then(res=>{
          console.log(res.data)
        })
        axios.post('api/delCar',qs.stringify({
          goodid:item.id,
          userid:userid,
        }),{
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        }).then(res=>{
          console.log(res.data)
        })
        this.$notify({
          title: '支付成功',
          message: '请到我的订单查页面看详情',
          type: 'success'
        })
        this.$store.state.carPanelData.forEach(good=>{
          if(good.id == item.id){
            good["addr"] = addr
            this.$store.commit('delCarPanelData',good.id)
            this.$store.commit('addPayedData',good)
          }
        })
      }
    },
    computed:{
      userInfo(){
       return this.$store.state.userInfo
      }
    },
  }
</script>

<style scoped>
  .car-item-name{
    width: 300px;
    height: 100px;
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
    width: 100px;
    height: 70px;
    margin-top: 10px;
  }
  .receve-addr{
    font-size: 16px;
    position: relative;
    top: -25px;
    left: -80px;
    color:black;
  }
  .el-select{
    width: 300px;
  }

</style>
