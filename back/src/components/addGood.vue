<template>
  <div>
    <el-button @click="dialogVisible=true">
      <i style="color: green" class="el-icon-circle-plus"></i>
      添加商品
    </el-button>
    <el-dialog
      title="添加商品"
      center
      :visible.sync="dialogVisible"
      @close="resetForm('ruleForm')"
      width="800px">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="商品名称" prop="name">
          <el-input v-model="ruleForm.name"></el-input>
        </el-form-item>
        <el-form-item label="商品价格" prop="price">
          <el-col :span="4">
            <el-input v-model="ruleForm.price"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="商品数量" prop="number">
          <el-col :span="4">
            <el-input v-model="ruleForm.number"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="商品卖点" prop="sellPoint">
          <el-col :span="10">
            <el-input v-model="ruleForm.sellPoint"></el-input>
          </el-col>

        </el-form-item>
        <el-form-item label="图片上传">
          <el-upload
            ref="upload"
            class="upload-demo"
            action="/back/uppic"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :on-success="uploadSuccess"
            :file-list="fileList2"
            :limit="1"
            :auto-upload="false"
            list-type="picture">
            <el-button size="small" type="primary">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
          </el-upload>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">确认添加</el-button>
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
        fileList2: [],
        img_url:'',
        dialogVisible:false,
        ruleForm: {
          name: '',
          price:'',
          sellPoint:'',
          number:''
        },
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
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$refs.upload.submit();
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
      uploadSuccess(file){
        this.img_url = file.filePath
        axios.post("/back/addgood",qs.stringify({
            name:this.ruleForm.name,
            price:this.ruleForm.price,
            sell_point:this.ruleForm.sellPoint,
            number:this.ruleForm.number,
            seller_id:this.$store.state.seller_id,
            img_url:this.img_url
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
      }
    }
  }
</script>

<style>

</style>
