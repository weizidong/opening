<style lang="less" scoped>
  @import "../../node_modules/viewerjs/dist/viewer.min.css";
  li{
    list-style: none;
  }
  .img-view-box {
    margin: 5px 0;
    .img-view {
      border-radius: 4px;
      border: 1px solid #d1dad5;
      position: relative;
      cursor: pointer;
      margin: 0 auto;
      &:hover {
        border-color: #97a8bd;
      }
      img {
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);
      }
    }
  }
</style>
<template>
  <ul class="img-view-box" ref="ImgView">
    <li class="img-view" :style="{width:width+'px',height:height+'px'}" @click="view">
      <img :src="src || defSrc" :alt="alt" ref="img" @error="$refs.img.src = defSrc" :style="{maxWidth:width+'px',maxHeight:height+'px'}"/>
    </li>
  </ul>
</template>
<script>
  import Viewer from 'viewerjs'

  export default {
    name: 'ImgView',
    data() {
      return {
        defOpt: {},
        viewer: null,
      }
    },
    props: {
      src: {
        type: String,
        default: '',
      },
      defSrc: {
        type: String,
        default: '/static/img/logo.png',
      },
      alt: {
        type: String,
        default: '',
      },
      width: {
        type: String,
        default: '96',
      },
      height: {
        type: String,
        default: '64',
      },
      options: {
        type: Object,
        default: () => ({}),
      },
    },
    methods: {
      view() {
        this.viewer = new Viewer(this.$refs.ImgView, {...this.defOpt, ...this.options})
      },
    },
  }
</script>
