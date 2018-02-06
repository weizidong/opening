<style lang="less" scoped>
  .avatar_box {
    display: inline-block;
    line-height: normal;
    .avatar {
      border-radius: 6px;
    }
    .avatar-uploader {
      display: inline-block;
      border: 1px dashed #d9d9d9;
      border-radius: 6px;
      height: 100%;
      &:hover {
        border-color: #20a0ff;
      }
    }
  }
</style>
<template>
  <div class="avatar_box" :style="{width:width*1+'px',height:height*1+'px'}">
    <el-upload
      class="avatar-uploader"
      action="/"
      :show-file-list="false"
      :before-upload="beforeUpload"
      :http-request="upload"
      :list-type="type"
      :on-success="successFun">
      <img v-if="url" :src="url" class="avatar" :style="{width:width+'px',height:height+'px'}">
      <i v-else class="el-icon-plus" :style="{width:width+'px',height:height+'px',lineHeight:height+'px'}"/>
    </el-upload>
  </div>
</template>

<script>
  import {upload, token} from '../api/systemApi'
  import {warning} from '../actions'
  export default {
    props: {
      url: {
        type: String,
        default: '',
      },
      width: {
        type: String,
        default: '120',
      },
      height: {
        type: String,
        default: '120',
      },
      size: {
        type: Number,
        default: 1024 * 1024,
      },
      type: {
        type: String,
        default: 'picture',
      },
    },
    methods: {
      upload,
      successFun(data) {
        const url = `${token.path}/${data.key}`
        this.$emit('success', url)
      },
      beforeUpload(file) {
        if (this.type !== 'text' && file.size >= this.size) {
          warning(`图片大小不能超过 ${this.size / 1024 / 1024} M！请修改后重新上传！`)
          return false
        }
        return true
      },
    },
  }
</script>
