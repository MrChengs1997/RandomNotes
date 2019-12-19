<template>
  <div class="todo-header">
    <input type="text" placeholder="请输入你的任务名称，按回车键确认"
     @keyup.enter="addItem"  v-model="title"/>
  </div>
</template>
<script>
    export default {
      props:{
        addTodo:Function
      },
      data(){
        return{
          title:''
        }
      },
      methods:{
        addItem(){
          //数据合法性
          const  title = this.title.trim()
          //根据输入的数据生成duixiang
          if(!title){
            alert('数据不能为空！！！')
          }
          //添加数据到todos
          const todo = {  title, complete:false}
          this.addTodo(todo)
          //清空
          this.title=''
        }
      }
    }
</script>

<style>
  .todo-header input {
    width: 560px;
    height: 28px;
    font-size: 14px;
    border: 1px solid #ccc;
    border-radius: 4px;
    padding: 4px 7px;
  }
  .todo-header input:focus {
    outline: none;
    border-color: rgba(82, 168, 236, 0.8);
    box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px rgba(82, 168, 236, 0.6);
  }
</style>
