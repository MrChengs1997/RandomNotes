<template>
    <div>
      <!--id来源源于-->
      <!--<router-link :to="`/home/message/detail/${m.id}`">{{m.title}}</router-link> -->
      <p>ID:{{$route.params.id}}</p>
      <ul>
        <li>id:{{messageDetailID.id}}</li>
        <li>title:{{messageDetailID.title}}</li>
        <li>content:{{messageDetailID.content}}</li>
      </ul>
    </div>
</template>
<script>
    export default {
      //消息详情的数据
      data(){
        //封装成对象进行返回
       return{
         messageDetailID:{}
       }
      },
      mounted() {
          //数据来源于ajax进行请求
          const messages = [
            {id: 1, title: 'Message001',content:'1234'},
            {id: 3, title: 'Message003',content:'2345'},
            {id: 5, title: 'Message005',content:'3456'}
          ]
        this.allmessages = messages
        //传递的id
        const id= this.$route.params.id*1 //防止书文本
        this.messageDetailID = this.allmessages.find(detail=>detail.id ==id)
        },
      //mouted（）只看点击一次进行切换数据，再点击就不会切换数据的信息
      //改变路由参数需要进行监视
      watch:{
        $route :function (value) {//路由路径（param）发生了改变，value == $route
          const id= value.params.id*1 //防止书文本
          this.messageDetailID = this.allmessages.find(detail=>detail.id ==id)
        }
      }
    }
</script>

<style>

</style>
