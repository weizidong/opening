<style lang="less">
  @import "sidebarItem.less";
</style>
<template>
  <div class="sidebar-item">
    <el-menu-item :index="item.url || ('/view/'+(item.code||index))" v-if="!item.children">
      <i :class="item.icon||'el-icon-date'"></i><span v-if="true || !hide">{{item.name}}</span>
    </el-menu-item>
    <el-submenu :index="index" v-if="item.children">
      <template slot="title"><i class="icon-font" :class="item.icon||'el-icon-menu'"></i><span v-if="true || !hide">{{item.name}}</span></template>
      <SidebarItem v-for="(i,idx) in item.children" :hide="hide" :key="index+'-'+idx" :index="index+'-'+idx" :item="i"/>
    </el-submenu>
  </div>
</template>
<script>
  export default {
    name: 'SidebarItem',
    props: {
      index: String,
      item: Object,
      hide: Boolean,
    },
  }
</script>
