<template>
  <div class="col-md-8">
    <h3 class="reply">评论回复：</h3>
    <h2 style='display: none'>暂无评论，点击左侧添加评论！！！</h2>
    <ul class="list-group">

      <!--comment需要交给item组件-->
      <item v-for="(comment,index) in contents"
            :key="index"  :comment="comment"
      :deleteComments="deleteComments"
      :index ="index"/>

    </ul>
  </div>
</template>
<script>

  //引入item组件
  import  item from './item'

    export default {
      //声明接收属性,:这个属性就会成为组件对象的属性 组件对象
      props : ['contents','deleteComments'],//只指定属性名
      data(){
        //此时的this是组件对象
        return{

        }
      },
      components:{
        item
      }
    }
</script>

<style>
  .reply {
    margin-top: 0px;
  }

  li {
    transition: .5s;
    overflow: hidden;
  }

  .handle {
    width: 40px;
    border: 1px solid #ccc;
    background: #fff;
    position: absolute;
    right: 10px;
    top: 1px;
    text-align: center;
  }

  .handle a {
    display: block;
    text-decoration: none;
  }

  .list-group-item .centence {
    padding: 0px 50px;
  }

  .user {
    font-size: 22px;
  }
</style>
