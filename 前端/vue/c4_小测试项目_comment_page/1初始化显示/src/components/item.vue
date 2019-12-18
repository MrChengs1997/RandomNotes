<template>
    <div>
      <li class="list-group-item">
        <div class="handle">
          <a href="javascript:;">删除</a>
        </div>
        <p class="user"><span >{{comment.name}}</span><span>说:</span></p>
        <p class="centence">{{comment.content}}</p>
      </li>

    </div>
</template>

<script>
    export default {
      //生命接收comment
      props:{//指定属性名和属性值的类型
        comment:Object
      }
    }
</script>

<style>

</style>
