<template>
  <div>
    <el-button type="text" @click="dialogVisible=true">
      修改
    </el-button>
    <el-button type="text">
      <i style="color: red" class="el-icon-error" @click="deleteGood"></i>

    </el-button>
    <el-dialog
      title="添加商品"
      center
      :visible.sync="dialogVisible"
      @close="resetForm('ruleForm')"
      width="800px">
      <el-form :model="data" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="商品名称" prop="name">
          <el-input v-model="data.name"></el-input>
        </el-form-item>
        <el-form-item label="商品价格" prop="price">
          <el-col :span="4">
            <el-input v-model="data.price"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="商品数量" prop="number">
          <el-col :span="4">
            <el-input v-model="data.number"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="商品卖点" prop="sellPoint">
          <el-col :span="10">
            <el-input v-model="data.sell_point"></el-input>
          </el-col>

        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">确认修改</el-button>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>

      </el-form>
    </el-dialog>
  </div>
</template>

<script type="text/ecmascript-6">
  import axios from 'axios'
  import qs from 'qs'
  export default {
    props:['data'],
    data() {
      var validateNumber = (rule, value, callback) => {
        if(!isNaN(value)){
          callback()
        }else{
          callback(new Error("只能是数字"))
        }
      }
      var validatePrice = (rule, value, callback) => {
        if(!isNaN(value)){
          callback()
        }else{
          callback(new Error("只能是数字"))
        }
      }
      return {
        fileList2: [{name:'',url:this.data.img_url}],
        img_url:this.data.img_url,
        dialogVisible:false,

        rules: {
          name: [
            { required: true, message: '请输入商品名称', trigger: 'blur' },
            { min: 1, max: 256, message: '请输入商品名称', trigger: 'blur' }
          ],
          price:[{ required: true, message: '请输入价格', trigger: 'blur' },
            {validator: validatePrice,trigger: 'blur'}],
          number:[{ required: true, message: '请输入数量', trigger: 'blur' },
            {validator: validateNumber,trigger: 'blur'}]
        }
      };
    },
    components:{
    },
    methods: {
      deleteGood(){
        axios.post("/back/deleteGood",qs.stringify({
            id:this.data.id
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
              location.reload();
            }

          )
          .catch(error=>{console.log(error)})
      },
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            axios.post("/back/modifygood",qs.stringify({
                name:this.data.name,
                price:this.data.price,
                sell_point:this.data.sell_point,
                number:this.data.number,
                seller_id:this.$store.state.seller_id,
                id:this.data.id
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
                location.reload();
                }

              )
              .catch(error=>{console.log(error)})
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePreview(file) {
        console.log(fileList)
        console.log(file);
      },
    }
  }
</script>

<style>

</style>
