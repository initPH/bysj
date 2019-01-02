<template>
  <div class="home-aside">
      <el-aside width="250px">
        <el-row>
          <el-col>
            <el-menu
              default-active="2"
              class="el-menu-vertical-demo"
              @open=""
              @close=""
              background-color="#545c64"
              text-color="#fff"
              active-text-color="#ffd04b">
              <el-submenu index="1">
              <template slot="title">
                <i class="el-icon-location"></i>
                <span>店铺管理</span>
              </template>
              <el-menu-item-group>
                <router-link to="goods">
                  <el-menu-item index="1-1">商品管理</el-menu-item>
                </router-link>
                <router-link to="orders">
                  <el-menu-item index="1-2">订单管理</el-menu-item>
                </router-link>
              </el-menu-item-group>
            </el-submenu>
            <el-submenu index="2">
              <template slot="title">
                <i class="el-icon-location"></i>
                <span>个人中心</span>
              </template>
              <el-menu-item-group>
                <router-link to="changeinfo">
                  <el-menu-item index="2-1">修改信息</el-menu-item>
                </router-link>
              </el-menu-item-group>
            </el-submenu>

            </el-menu>
          </el-col>
        </el-row>
      </el-aside>
  </div>
</template>
z
<script type="text/ecmascript-6">

</script>

<style>
  a{
    text-decoration: none;
  }
</style>
