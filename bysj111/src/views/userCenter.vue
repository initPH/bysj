<template>
  <div class="usermain">
    <div class="userinfo">
      <div class="nickname">
        <el-input v-model="userInfo.nickname">
          <template slot="prepend">昵&nbsp;&nbsp;称：</template>
        </el-input>
        <el-button type="primary" @click="changeNickName">修改</el-button>
      </div>
      <div class="password1">
        <el-input v-model="userInfo.password" type="password">
          <template slot="prepend">密码1：</template>
        </el-input>
        <el-button type="danger" @click="changePass">修改</el-button>
      </div>
      <div class="password2">
        <el-input  type="password" v-model="password2">
          <template slot="prepend">密码2：</template>
        </el-input>
        <el-button type="danger" @click="changePass">修改</el-button>
      </div>
      <div class="addr1">
        <el-input v-model="userInfo.addr1">
          <template slot="prepend">地址1：</template>
        </el-input>
        <el-button type="primary" @click="changeAddr1">修改</el-button>
      </div>
      <div class="addr2">
        <el-input v-model="userInfo.addr2">
          <template slot="prepend">地址2：</template>
        </el-input>
        <el-button type="primary" @click="changeAddr2">修改</el-button>
      </div>
      <div class="addr3">
        <el-input v-model="userInfo.addr3">
          <template slot="prepend">地址3：</template>
        </el-input>
        <el-button type="primary" @click="changeAddr3">修改</el-button>
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
        password2:''
      }
    },
    computed:{
      userInfo(){
        return this.$store.state.userInfo
      }
    },
    methods:{
      changeNickName(){
        axios.post('users/changeNickName',qs.stringify({
          id:this.$store.getters.getUserid,
          nickname:this.userInfo.nickname
        }),{
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        }).then(res=>{
            console.log(res.data)
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
      changePass(){
        if(this.password2==this.userInfo.password){
          axios.post('users/changePass',qs.stringify({
            id:this.$store.getters.getUserid,
            pass:this.userInfo.password
          }),{
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded'
            }
          }).then(res=>{
              console.log(res.data)
              this.$message({
                showClose: true,
                message: res.data.msg,
                type: 'success'
              });
            })
            .catch(function (error) {
              console.log(error);
            })
        }else{
          alert("密码1与密码2不同");
        }

      },
      changeAddr1(){
        axios.post('users/changeAddr1',qs.stringify({
          id:this.$store.getters.getUserid,
          addr1:this.userInfo.addr1
        }),{
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        }).then(res=>{
            console.log(res.data)
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
      changeAddr2(){
        axios.post('users/changeAddr2',qs.stringify({
          id:this.$store.getters.getUserid,
          addr2:this.userInfo.addr2
        }),{
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        }).then(res=>{
            console.log(res.data)
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
      changeAddr3(){
        axios.post('users/changeAddr3',qs.stringify({
          id:this.$store.getters.getUserid,
          addr3:this.userInfo.addr3
        }),{
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        }).then(res=>{
            console.log(res.data)
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

<style>
.usermain{
  width: 800px;
  height: 500px;
  background-color: white;
  margin: 0px auto;
}
  .userinfo{
    width: 550px;
    margin: 0px auto;
  }

  .el-input{
    width: 80%;
  }
  .userinfo div{
    margin-top: 20px;
  }
</style>
