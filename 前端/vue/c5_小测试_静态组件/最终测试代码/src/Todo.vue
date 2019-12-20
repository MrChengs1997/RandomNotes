<template>
  <div class="todo-container">
    <div class="todo-wrap">

<!--      <TodoHeader :addTodo="addTodo"/>-->
      <TodoHeader @addTodo="addTodo"/>
<!--      <TodoList :todos="todos" :deleteTodo="deleteTodo"/>-->
      <TodoList :todos="todos" />
<!--      <TodoFooter :todos="todos" :deleteFinItem="deleteFinItem"-->
<!--      :selectAll="selectAll"/>-->
      <TodoFooter>
        <input type="checkbox"  v-model="isChecked" slot="checkAll"/>

        <span slot="count">已完成{{finshSize}}/ 全部{{todos.length}}</span>
        <button slot="deletes" class="btn btn-danger" v-show="finshSize" @click="deleteFinItem">清除已完成任务</button>
      </TodoFooter>
    </div>
  </div>
</template>
<script>
  import TodoHeader from "./components/todo/TodoHeader";
  import TodoList from "./components/todo/TodoList";
  import TodoFooter from "./components/todo/TodoFooter";
  //引入css样式
  import './base.css'
  //引入库
  import PubSub from 'pubsub-js'

  //引入数据存储操作的工具类
  import strogUtil from "./util/strogUtil";

    export default {
      computed:{
        finshSize(){
          return this.todos.reduce((preTotal,todo)=>preTotal+(todo.complete?1:0),0)
        },
        isChecked: {
          get() {
            return this.finshSize == this.todos.length
          },
          set(value){//当前input的新值
            this.selectAll(value)
          }
        }
      },
      mounted() {//执行异步代码
        //订阅消息
        //要删除的数据msg，index传递的参数
        PubSub.subscribe('deleteTodo', (msg,index)=> {
          this.deleteTodo(index)
        })
      },
      data(){
      return{
        // todos:[
        //   {title:'上班',complete:false},
        //   {title:'下班',complete:true},
        //   {title:'休息',complete:false}
        // ]

        //有值则进行解析,无值则进行解析空数组
        //todos:JSON.parse(window.localStorage.getItem('token_key') || '[]')
        todos:strogUtil.readTodos()
      }
    },
      watch:{//监视
        todos:{
          deep : true,//深度监视
          handler :function (newVal,oldVal) {//val都是todos的
            //将todos最新的值保存到localStroage中
            //保存的是json数据
            //window.localStorage.setItem('token_key',JSON.stringify(newVal))
            strogUtil.saveTodos(newVal)
          }
        }
      },
      components:{
        TodoHeader,TodoList,TodoFooter
      },
      methods:{
       addTodo(todo){
         this.todos.unshift(todo)
       },
        deleteTodo(index){
         this.todos.splice(index,1)
        },
        deleteFinItem(){//删除所有选中的
          //过滤留下为false的数据
         this.todos =  this.todos.filter(todo=>!todo.complete)
        },
        selectAll(check){//全选&全不选
            this.todos.forEach(todo=>todo.complete = check)
        }
      }
    }
</script>

<style>
  .todo-container {
    width: 600px;
    margin: 0 auto;
  }
  .todo-container .todo-wrap {
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 5px;
  }

</style>
