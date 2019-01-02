<template>
  <div class="car-panel-page">
    <div class="car-item-list" style="text-align: center;font-size:20px">
      <div style="float: left;width: 80px">预览</div>
      <div style="float: left;width: 300px">信息</div>
      <div style="float: left;width: 100px;margin-left: 110px">单价</div>
      <div style="float: left;width: 150px" >数量</div>
      <div style="float: left;width: 150px">金额</div>
      <div style="width: 200px;float: left">操作</div>
      <div class="car-item-list-content">
        <br>
        <!--<div v-if="carPanelData.length===0" class="empty-carpanel-remind">你还没有添加物品到购物车</div>-->
        <carPanelItem v-for="(item,index) in carPanelData" :item="item" :key="index"></carPanelItem>
      </div>
    </div>

  </div>
</template>
<script type="text/ecmascript-6">

  import carPanelItem from '../components/car-panel-item.vue'
  export default{
    components:{
      carPanelItem
    },
    computed:{
      carPanelData(){
        return this.$store.state.carPanelData
      },
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
  .empty-carpanel-remind{
    font-size: 50px;
  }

</style>
