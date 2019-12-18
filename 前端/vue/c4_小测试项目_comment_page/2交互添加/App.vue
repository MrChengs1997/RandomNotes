<template>
  <div>
    <header class="site-header jumbotron">
      <div class="container">
        <div class="row">
          <div class="col-xs-12">
            <h1>请发表对vue的评论</h1>
          </div>
        </div>
      </div>
    </header>
    <div class="container">
      <add :addComments="addComments"/>
      <!--组件之间的通信，此时传递的是contents变量的值-->
      <list :contents="comments"/>
    </div>
  </div>
</template>
<script>
  import add from "./components/add";
  import list from "./components/list";

  export default {
    data(){
      return{
        comments : [
          {name:'aa',content:'i love vue'},
          {name:'bb',content:'i love aa & vue'},
          {name:'cc',content:'i love bb & vue'}
        ]
      }
    },
    components :{
      add,
      list
    },
    methods:{
      //添加评论
      //需要将方法传递给组件
      addComments(comment){
        this.comments.unshift(comment)
      }
    }
  }
</script>

<style>
  .logo{
    width: 150px;
    height: 150px;
  }
</style>

