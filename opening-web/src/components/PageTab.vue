<style lang="less">
  @import "../base.less";
  .page_tab {
    background-color: @bg-white;
    border-radius: 8px;
    margin-bottom: 28px;
    & > .el-input, & > .el-select {
     width: 220px;
     margin: 8px;
    }

  }
</style>
<template>
  <div class="page_tab">
    <slot></slot>
  </div>
</template>
<script>
  export default {
    name: 'page_tab',
  }
</script>
