<template>
  <div>
    <header class="site-header jumbotron">
      <div class="container">
        <div class="row">
          <div class="col-xs-12">
            <h1>请发表对React的评论</h1>
          </div>
        </div>
      </div>
    </header>
    <div class="container">
      <add/>
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
          {name:'aa',content:'i love '},
          {name:'bb',content:'i love aa'},
          {name:'cc',content:'i love bb'}
        ]
      }
    },
    components :{
      add,
      list
    }
  }
</script>

<style>
  .logo{
    width: 150px;
    height: 150px;
  }
</style>

