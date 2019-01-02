<template>
  <div class="main">
    <div class="info">
      <div>
        <el-input v-model="name">
          <template slot="prepend">店铺名称</template>
        </el-input>
        <el-button type="primary" @click="changeName">修改</el-button>
      </div>
      <div>
        <el-input v-model="pass" type="password">
          <template slot="prepend" style="width: 100px">原始密码</template>
        </el-input>
      </div>
      <div>
        <el-input v-model="pass1" type="password">
          <template slot="prepend" style="width: 100px">修改密码</template>
        </el-input>
      </div>
      <div>
        <el-input v-model="pass2" type="password">
          <template slot="prepend">确认密码</template>
        </el-input><br><br>
        <el-button type="danger" @click="changePass">修改密码</el-button>
      </div>
    </div>


  </div>
</template>

<script type="text/ecmascript-6">
  import axios from 'axios'
  import qs from 'qs'
  export default{
    data(){
      return{
        name:this.$store.state.name,
        pass:'',
        pass1:"",
        pass2:'',
      }
    },
    methods:{
      changeName(){
        axios.post("/back/changeName",qs.stringify({
            name:this.name,
            seller_id:this.$store.state.seller_id
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
              this.dialogVisible=false
            this.$store.state.name = this.name
            }

          )
          .catch(error=>{console.log(error)})
      },
      changePass(){
        if(this.pass1!=this.pass2){
          alert("两次密码不同")
        }else{
          axios.post("/back/changePass",qs.stringify({
              seller_id:this.$store.state.seller_id,
              pass:this.pass,
              pass1:this.pass1,
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
              }

            )
            .catch(error=>{console.log(error)})
        }

      }
    }
  }
</script>

<style scoped>
  .main{
    width: 800px;
    height: 500px;
    background-color: white;
    margin: 0px auto;
  }
  .info{
    width: 550px;
    margin: 0px auto;
  }
  .el-input{
    width: 80%;
  }

</style>
