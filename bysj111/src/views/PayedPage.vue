<template>
  <div class="car-panel-page">
    <div class="car-item-list" style="text-align: center;font-size: 20px">
      <div style="float: left;width: 80px">预览</div>
      <div style="float: left;width: 300px">信息</div>
      <div style="float: left;width: 100px;margin-left: 110px">单价</div>
      <div style="float: left;width: 150px" >数量</div>
      <div style="float: left;width: 150px">金额</div>
      <div style="width: 200px;float: left">操作</div>
      <div class="car-item-list-content">
        <!--<div v-if="!goodsData" class="empty-payed-remind">-->
          <!--快去添加东西到订单吧</div>-->
        <br>
        <payedItem v-for="(item,index) in payedData" :item="item" :key="index"></payedItem>
      </div>
    </div>

  </div>
</template>
<script type="text/ecmascript-6">
  import payedItem from '../components/payed-item.vue'
  import axios from 'axios'
  export default{
    computed:{
      payedData(){
        return this.$store.state.payedData
      }
    },
    components:{
      payedItem
    },
  mounted(){
    axios.get('api/getOrder',
      {
        params:{
          userid:this.$store.getters.getUserid
        }
      }
    ).then(res=>{
        this.$store.state.payedData = res.data
      })
      .catch(function (error) {
        console.log(error);
      })
  }
  }
</script>

<style>
  .car-item-list{
    border: solid 1px gray;
  }
  .car-item-list{
    width: 1100px;
    margin: 0 auto;
  }
  .empty-payed-remind{
    font-size: 50px;
  }
</style>
