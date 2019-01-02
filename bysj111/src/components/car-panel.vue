<template>
  <li class="nav-cart" @mouseenter="carShowhandle" @mouseleave="carHidehandle">
    <a href="javascript:;" class="ball-rect">购物车</a>
    <!--根据class改变颜色-->
							<span class="cart-empty-num" :class="{'cart-num':count>0}">
								<i>{{count}}</i>
							</span>
    <div class="nav-cart-wrapper" v-if="carShow">
      <div class="nav-cart-list">
        <div class="empty" v-if="count==0">
          <h3>购物车为空</h3>
          <p>您还没有选购任何商品，现在前往商城选购吧!</p>
        </div>
        <div class="full" v-else>
          <div class="nav-cart-items">
            <ul>
              <li class="clear" v-for="(item,index) in carPanelData">
                <div class="cart-item js-cart-item cart-item-sell">
                  <div class="cart-item-inner">
                    <div class="item-thumb">
                      <img :src="item.img_url">
                    </div>
                    <div class="item-desc">
                      <div class="cart-cell">

                        <p class="attrs">
                          <router-link :to="{name:'detail',query:{id:item.id}}">
                            <span>{{item.name}}</span>
                          </router-link>
                        </p>
                        <h6>
                          <span class="price-icon">¥</span><span class="price-num">{{item.price}}</span>
                          <span class="item-num">x {{item.count}}</span>
                        </h6>
                      </div>
                    </div>
                    <div class="del-btn" @click="delCarPanelData(item.id)">删除</div>
                  </div>
                </div>
              </li>
            </ul>
          </div>
          <div class="nav-cart-total">
            <p>共 <strong class="ng-binding">{{ count }}</strong> 件商品</p>
            <h5>合计：<span class="price-icon">¥</span><span class="price-num ng-binding" ng-bind="cartMenu.totalPrice">{{total}}</span></h5>
            <h6>
              <router-link to="carPanel">
                <el-button type="primary">去购物车</el-button>
              </router-link>

            </h6>
          </div>
        </div>
      </div>
    </div>
    <transition name="ball"
                v-on:before-enter="beforeEnter"
                v-on:enter="enter"
                v-on:after-enter="afterEnter"
                v-bind:css="true"
    >
      <div class="addcart-mask" v-show="ball.show">
        <img class="mask-item"/>
      </div>
    </transition>

  </li>
</template>


<script type="text/ecmascript-6">
  import axios from 'axios'
  import qs from 'qs'
  export default{
    data(){
      return{
      }
    },
    computed:{
      carPanelData(){
        return this.$store.state.carPanelData
      },
      count(){
        var count = 0
        var goodlist = this.$store.state.carPanelData
        for(var i=0;i<goodlist.length;i++){
          count += goodlist[i].count
        }
        return count
      },
      total(){
        var goodlist = this.$store.state.carPanelData
        var total = 0
        for(var i=0;i<goodlist.length;i++){
          total += goodlist[i].count * goodlist[i].price
        }
        return total
      },
      carShow(){
        return this.$store.state.carShow
      },
      ball(){
        return this.$store.state.ball
      }
    },
    methods:{
      delCarPanelData(goodid){
        axios.post('api/delCar',qs.stringify({
          userid:this.$store.getters.getUserid,
          goodid:goodid
        }),{
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        }).then(res=>{
            console.log(res.data)
          this.$store.commit('delCarPanelData',goodid)
          })
          .catch(function (error) {
            console.log(error);
          })
      },
      carShowhandle(){
        this.$store.commit('showCar')
      },
      carHidehandle(){
        setTimeout(() => {
          this.$store.commit('hideCar')
        },500)

      },

      beforeEnter(el){
//        //获取小球位置
        let rect = this.ball.el.getBoundingClientRect()

        //购物车位置
        let rectE = document.getElementsByClassName('ball-rect')[0].getBoundingClientRect()
        //获取小球
        let ball = document.getElementsByClassName('mask-item')[0]

        let x = rectE.left + 15 - (rect.left+rect.width/2)
        let y = rect.top + rect.height/2 - (rectE.top + 10)

        el.style.transform = 'translate3d(0,'+y+'px,0)'
        ball.style.transform='translate3d(-'+x+'px,0,0)'
        ball.src = this.ball.img
      },
      enter(el){
        let a = el.offsetHeight
        el.style.transform='translate3d(0,0,0)'
        document.getElementsByClassName('mask-item')[0].style.transform='translate3d(0,0,0)'
      },
      afterEnter(){
        this.ball.show = false
      },
    }
  }
</script>

<style>
.ball-enter-active{
  transition: 1s;
}
.ball-enter-active .mask-item{
  transition: 1s;
}
</style>
