<template>
  <el-table
    :data="tableData"
    border
    style="width: 100%">
    <el-table-column
      fixed
      label="序号"
      prop="index"
      width="60">
    </el-table-column>
    <el-table-column
      label="图片"
      width="100">
      <template slot-scope="scope">
        <img width="50px" height="50px" :src="scope.row.img_url">
      </template>
    </el-table-column>
    <el-table-column
      label="商品名称"
      prop="name"
      width="330">
    </el-table-column>
    <el-table-column
      label="价格"
      prop="price"
      width="80">
    </el-table-column>
    <el-table-column
      label="购买数量"
      prop="count"
      width="100">
    </el-table-column>
    <el-table-column
      label="买家名称"
      prop="nickname"
      width="100">
    </el-table-column>
    <el-table-column
      label="收货地址"
      prop="addr"
      width="300">
    </el-table-column>
    <el-table-column
      label="发货状态"
      width="100">
      <template slot-scope="scope">
        <span v-if="scope.row.is_send">已发货</span>
        <span v-else>未发货</span>
      </template>
    </el-table-column>

    <el-table-column
      label="购买日期"
      width="120">
      <template slot-scope="scope">
        <span>{{scope.row.create_time.substr(0,10)}}</span>
      </template>
    </el-table-column>

    <el-table-column
      fixed="right"
      label="操作"
      width="100">
      <template slot-scope="scope">

        <el-button v-if="!scope.row.is_send" @click="verifySend(scope.row.id1)" type="primary">发货</el-button>
        <el-button v-else-if="!scope.row.is_recive" disabled type="danger">已发货</el-button>
        <el-button v-else disabled type="primary">已收货</el-button>

      </template>
    </el-table-column>
  </el-table>
</template>

<script type="text/ecmascript-6">
  import axios from 'axios'
  import qs from 'qs'
  export default{
    computed:{
      tableData(){
        console.log(this.$store.state.order)
        return this.$store.state.order
      }
    },
    methods:{
      verifySend(id){
        axios.post("/back/verifySend",qs.stringify({
              id:id
          }),{
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded'
            }
          })
          .then(res=>{
              this.$message({
                showClose: true,
                message: res.data.msg,
                type: res.data.status=="success"?"success":"error"
              })
             location.reload()
            }

          )
          .catch(error=>{console.log(error)})
      }
    }
  }
</script>

<style>

</style>
