<style lang="less" scoped>
  img {
    border-radius: 50%;
  }
</style>
<template>
  <img :src="src || '/static/img/logo.png'" :alt="alt" :style="{width:width+'px',height:height+'px'}"/>
</template>
<script>
  export default {
    name: 'headImg',
    props: {
      src: {
        type: String,
        default: '',
      },
      alt: {
        type: String,
        default: '',
      },
      width: {
        type: String,
        default: '64',
      },
      height: {
        type: String,
        default: '64',
      },
    },
  }
</script>
