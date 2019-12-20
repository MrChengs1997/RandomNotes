/*
使用localStorage存储数据的工具模块
暴露的内容：
1、函数
2、对象

向外暴露一个：函数
向外暴露多个:对象
 */

//暴露多个
const  TODO_key = 'token_key'
export default {
  saveTodos (todos){
    window.localStorage.setItem(TODO_key,JSON.stringify(todos))
  },
  readTodos(){
    return JSON.parse(window.localStorage.getItem(TODO_key) || '[]')
  }
}
