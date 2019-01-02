<template>
  <div class="regist-main">
    <p style="font-size: 50px;text-align: center;line-height: 100px">注册</p>
    <el-form :model="ruleForm2" status-icon :rules="rules2" ref="ruleForm2" label-width="100px" class="demo-ruleForm">
      <el-form-item label="帐号" prop="username">
        <el-input v-model.number="ruleForm2.username"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="pass">
        <el-input type="password" v-model="ruleForm2.pass" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="checkPass">
        <el-input type="password" v-model="ruleForm2.checkPass" auto-complete="off"></el-input><br>
      </el-form-item>
      <p style="margin-left: 80px">有帐号？去<router-link to="login" style="text-decoration: underline;color: blue">登录</router-link></p>
      <el-form-item style="margin-top: 10px;margin-left: 60px">
        <el-button type="primary" @click="submitForm('ruleForm2')">注册</el-button>
        <el-button @click="resetForm('ruleForm2')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script type="text/ecmascript-6">
  import axios from 'axios'
  import qs from 'qs'
  export default {
    data() {
      var checkUsername = (rule, value, callback) => {
        if (value === '' ) {
          return callback(new Error('帐号不能为空'));
        }else{
          if(value.toString().length < 8){
            return callback(new Error('帐号长度最少为8'));
          }
          callback();
        }

      };
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.ruleForm2.checkPass !== '') {
            this.$refs.ruleForm2.validateField('checkPass');
          }
          callback();
        }
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.ruleForm2.pass) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
        ruleForm2: {
          pass: '',
          checkPass: '',
          username: ''
        },
        rules2: {
          pass: [
            { validator: validatePass, trigger: 'blur' }
          ],
          checkPass: [
            { validator: validatePass2, trigger: 'blur' }
          ],
          username: [
            { validator: checkUsername, trigger: 'blur' }
          ]
        }
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            axios.post('api/add',qs.stringify({
                username:this.ruleForm2.username,
                password:this.ruleForm2.pass,
              }),{
                headers: {
                  'Content-Type': 'application/x-www-form-urlencoded'
                }
            })
              .then(res=> {
                if(res.data.status == 'success'){
                  this.$message({
                    showClose: true,
                    message: res.data.msg,
                    type: 'success'
                  });
                  this.$router.push('login')// 将跳转的路由path作为参数，登录成功后跳转到该路由
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
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
  }

</script>

<style>
  .regist-main{
    width: 500px;
    height: 400px;
    background-color: white;
    margin: 100px auto;
  }
  .el-input{
    width: 300px;
  }
  .el-form{
    margin: 10px auto;
  }
</style>
