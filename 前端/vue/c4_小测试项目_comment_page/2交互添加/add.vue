<template>
  <div class="col-md-4">
    <form class="form-horizontal">
      <div class="form-group">
        <label>用户名</label>
        <input type="text" class="form-control" placeholder="用户名" v-model="name">
      </div>
      <div class="form-group">
        <label>评论内容</label>
        <textarea class="form-control" rows="6" placeholder="评论内容" v-model="content"></textarea>
      </div>
      <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
          <button type="button" class="btn btn-default pull-right"  @click="add">提交</button>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
    export default {
      //接收属性】
    props:{
      addComments:{//指定属性名，属性值的类型，必要性
        type:Function,
        required : true
      }
    }
      ,
      data(){
        return{
          name:'',
          content:''
        }
      },
      methods:{
        add(){
          //1、检查输入制度的合法性
          const name = this.name.trim()
          const content = this.content.trim()

          if(!name || !content){
            alert("数据信息为空！请输入有效信息")

            return
          }

        //2、根据输入的数据，封装一个comments对象
        const  comments ={
            name,content
        }


          //3、添加数据到comments
          this.addComments(comments)

          //4、清除输入
          this.name = ''
          this.content = ''
        }
      }


    }
</script>

<style>

</style>
