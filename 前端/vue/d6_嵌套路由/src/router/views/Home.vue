<template>
    <div>
      <h3>Home页面</h3>

      <div>
        <ul class="nav nav-tabs">
          <li><router-link to="/home/news">News</router-link></li>
          <li><router-link to="/home/message">Message</router-link></li>
        </ul>

        <router-view></router-view>
      </div>
    </div>
</template>

<script>
    export default {}
</script>

<style>

</style>
