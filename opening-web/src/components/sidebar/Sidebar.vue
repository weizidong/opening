<style lang="less" scoped>
  @import "sidebar.less";
</style>
<template>
  <div class="sidebar" :class="{hidden:hide}">
    <div class="logo" @click="$router.push({name:'home'})"><img src="/static/img/logo.png" alt="LOGO"></div>
    <el-menu :default-active="active" unique-opened router @open="hide = false" @close="hide = true">
      <SidebarItem :index="idx+''" :hide="hide" :item="item" v-for="(item,idx) in menus" :key="idx"/>
    </el-menu>
    <!--<div class="hide" @click="hide = !hide"><i :class="{'el-icon-d-arrow-left':!hide,'el-icon-d-arrow-right':hide}"></i></div>-->
  </div>
</template>
<script>
  import SidebarItem from './SidebarItem.vue'
  import {menuApi} from '../../api/adminApi'
  import {tree2List, coocki} from '../../constant/utils'
  import {ACTIVE_PATH} from '../../constant'

  export default {
    components: {SidebarItem},
    data() {
      return {
        menus: [],
        hide: false,
        menuList: [],
      }
    },
    computed: {
      active() {
        let path = this.menuList.find((url) => this.$route.path.startsWith(url)) || coocki.get(ACTIVE_PATH) || this.$route.path
        coocki.set(ACTIVE_PATH, path, 365)
        return path
      },
    },
    methods: {},
    beforeMount() {
      menuApi().then((data) => {
        this.menuList = tree2List(data, 'children', ({url}) => url)
        this.menus = data
      })
    },
  }
</script>
