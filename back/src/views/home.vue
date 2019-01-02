<template>
  <h1>欢迎来到商城管理后台</h1>
</template>

<script type="text/ecmascript-6">
  export default{

    mounted(){
      var seller_id = $cookies.get("seller_id")
      if(seller_id == null){
        this.$router.push('login')
      }
    }
  }
</script>

<style>

</style>
