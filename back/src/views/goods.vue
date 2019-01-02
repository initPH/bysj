<template>
  <div>
  <addGood></addGood>

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
        label="数量"
        prop="number"
        width="100">
      </el-table-column>
      <el-table-column
        label="被买次数"
        prop="buy_times"
        width="80">
      </el-table-column>
      <el-table-column
        label="卖点"
        prop="sell_point"
        width="150">
      </el-table-column>
      <el-table-column
        label="上架日期"
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
          <editGood :data="scope.row"></editGood>
        </template>
      </el-table-column>
    </el-table>

  </div>
</template>

<script>
  import addGood from '../components/addGood.vue'
  import editGood from '../components/editGood.vue'
  export default {
    methods: {
      handleClick(row) {
        console.log(row);
      }
    },
    components:{
      addGood,
      editGood
    },
    data() {
      return {
      }
    },
    computed:{
      tableData(){
        console.log(this.$store.state.goods)
        return this.$store.state.goods
      }
    }
  }
</script>
