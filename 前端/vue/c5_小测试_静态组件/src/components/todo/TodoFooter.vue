<template>
  <div class="todo-footer">
    <label>
      <input type="checkbox"  v-model="isChecked"/>
    </label>
    <span>
          <span>已完成{{finshSize}}</span> / 全部{{todos.length}}
        </span>
    <button class="btn btn-danger" v-show="finshSize" @click="deleteFinItem">清除已完成任务</button>
  </div>
</template>

<script>
    export default {
      props:{
        todos:Array,
        deleteFinItem:Function,
        selectAll:Function
      },
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
      }

    }
</script>

<style>
  .todo-footer {
    height: 40px;
    line-height: 40px;
    padding-left: 6px;
    margin-top: 5px;
  }

  .todo-footer label {
    display: inline-block;
    margin-right: 20px;
    cursor: pointer;
  }

  .todo-footer label input {
    position: relative;
    top: -1px;
    vertical-align: middle;
    margin-right: 5px;
  }

  .todo-footer button {
    float: right;
    margin-top: 5px;
  }

</style>
