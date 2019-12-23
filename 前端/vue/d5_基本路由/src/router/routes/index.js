/*
路由器模块
 */
//引入
//用于创建路由器的构建函数
import VueRouter from "vue-router";

//引入组件
import About from "../views/About";
import Home from "../views/Home";

//引入vue对象
import Vue from 'vue'
//使用对象
Vue.use(VueRouter)

//暴露默认的属性，可以使用任何属性名
export default new VueRouter({
  // 多个配置项
  //由于是多个使用数组进行配置
  routes:[
    {
      path:'/about',
      component :About
    },
    {
      path:'/home',
      component :Home
    },
    {
      //根路径
      path:'/',
      redirect:'/about'
    }
  ]

})
