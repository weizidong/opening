<style lang="less">
  @import "../base.less";
  .table_page {
    background-color: @table-bg;
    border-radius: 5px;
    box-shadow: 0 0 10px 0 @table-shadow;
    margin-top: 5px;
    & > .el-input, & > .el-select {
      width: 220px;
      margin: 8px;
    }
  }
</style>
<template>
  <div class="table_page">
    <slot></slot>
  </div>
</template>
<script>
  export default {
    name: 'table_page',
  }
</script>
