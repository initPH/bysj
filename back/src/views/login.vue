<template>
    <div class="login-main">
      <p style="font-size: 50px;text-align: center;line-height: 100px">登录</p>
      <el-form :model="ruleForm2" status-icon :rules="rules2" ref="ruleForm2" label-width="100px" class="demo-ruleForm">
        <el-form-item label="帐号" prop="username">
          <el-input v-model.number="ruleForm2.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="pass">
          <el-input type="password" v-model="ruleForm2.pass" auto-complete="off"></el-input><br>
        </el-form-item>
        <p style="margin-left: 80px">没帐号?去<router-link to="regist" style="text-decoration: underline;color: blue">注册</router-link></p>
        <el-form-item style="margin-left: 60px;margin-top: 20px">
          <el-button type="primary" @click="submitForm('ruleForm2')">登录</el-button>
          <el-button @click="resetForm('ruleForm2')">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </template>

<script type="text/ecmascript-6">
  import axios from 'axios'
  import qs from 'qs'
  export default{
    data(){
      var checkUsername = (rule, value, callback) => {
        if (value === '') {
          return callback(new Error('帐号不能为空'));
        }else{
          callback();
        }
      };
      var validatePass = (rule,value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        }else{
          callback();
        }
      };
      return{
        ruleForm2: {
          pass: '',
          username: ''
        },
        rules2: {
          pass: [
            { validator: validatePass, trigger: 'blur' }
          ],
          username: [
            { validator: checkUsername, trigger: 'blur' }
          ]
        }
      }
    },
    methods:{
      getOrder(id){
        axios.get("/back/getOrder",
          {
            params:{
              id:id
            }
          }).then(res=>{
          this.$store.state.order=res.data
        }).catch(error=>{console.log(error)})
      },
      getGoods(id){
        axios.get("/back/getGoods",
          {
            params:{
              id:id
            }
          }).then(res=>{
          this.$store.state.goods=res.data
        }).catch(error=>{console.log(error)})
      },
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            axios.post('/back/login',qs.stringify({
                username:this.ruleForm2.username,
                password:this.ruleForm2.pass,
              }),{
                headers: {
                  'Content-Type': 'application/x-www-form-urlencoded'
                }
              })
              .then(res=> {
                if(res.data.status != 'failure'){
                  this.$message({
                    showClose: true,
                    message: "登录成功",
                    type: 'success'
                  });
                  console.log(res)
                  this.$cookies.set('seller_id',res.data.id,60 * 60 * 24)
                  this.$store.state.seller_id = res.data.id
                  this.$store.state.name = res.data.name

                  this.getGoods(res.data.id)
                  this.getOrder(res.data.id)
                  console.log(this.$store.state)
                  this.$router.push('home')// 将跳转的路由path作为参数，登录成功后跳转到该路由
                }else{
                  this.$message({
                    showClose: true,
                    message: res.data.msg ,
                    type: 'error'
                  });
                  this.$refs[formName].resetFields();
                }
              })
              .catch(function (error) {
                console.log(error);
              });

          }else{
            console.log('error submit!!');
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
    }

  }
</script>

<style>
  .login-main{
    width: 500px;
    height: 375px;
    background-color: white;
    margin: 100px auto;
  }
  .el-input{
    width: 300px;
  }
  .el-form{
    margin: 20px auto;
  }
</style>
