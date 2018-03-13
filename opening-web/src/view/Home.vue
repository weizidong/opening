<style lang="less">
  @import "./home.less";
</style>

<template>
  <div class="manage">
    <MyHeader/>
    <Sidebar/>
    <div class="content">
      <transition name="manage-data" mode="out-in">
        <router-view></router-view>
      </transition>
    </div>
  </div>
</template>

<script>
  import MyHeader from '../components/header/Header.vue'
  import Sidebar from '../components/sidebar/Sidebar.vue'
  export default {
    name: 'home',
    components: {MyHeader, Sidebar},
  }
</script>
