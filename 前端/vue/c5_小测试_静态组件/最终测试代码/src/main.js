
import Vue from 'vue'
import App from './App.vue'


import Todo from "./Todo";


new Vue({
  el :'#app',
  components : {
    App,Todo
  },
  template : '<Todo/>'

})

