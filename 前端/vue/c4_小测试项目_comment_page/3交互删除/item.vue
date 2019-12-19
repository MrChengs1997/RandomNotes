<template>
    <div>
      <li class="list-group-item">
        <div class="handle">
          <a href="javascript:;" @click="deleteItem">删除</a>
        </div>
        <p class="user"><span >{{comment.name}}</span><span>说:</span></p>
        <p class="centence">{{comment.content}}/{{index}}</p>
      </li>

    </div>
</template>

<script>
    export default {
      //生命接收comment
      props:{//指定属性名和属性值的类型
        comment:Object,
        deleteComments:Function,
        index:Number

      },
    //   data(){
    //     return{
    //       index:''
    //     }
    // },
      methods:{
        deleteItem() {//删除item
          //确认删除
          const  {comment} = this.comment
          if (window.confirm('确定删除 ${comment.name} 的评论？')){

            alert(this.index)
            //点击事件执行
            this.deleteComments(this.index)
          }

        }
      }
    }
</script>

<style>

</style>
