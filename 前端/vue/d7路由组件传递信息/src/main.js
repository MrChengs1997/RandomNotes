
import Vue from 'vue'
import App from "./App"

//路由器
//就是index.js文件
import router from './router/routes'

new Vue({
  el :'#app',
  components : {
    App
  },
  template : '<App/>',

  //注册路由
  //配置对象的属性名都是一些确定的名称，不能随便修改
  //import router1 from './router/routes'
  router

})

