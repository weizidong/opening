<style lang="less">
  .my_upload {
    .el-upload--text {
      display: inline-block;
      width: 36px;
      border-radius: 3px;
      border: 1px solid #d9d9d9;
      &:hover {
        border-color: #20a0ff;
      }
    }
    .el-dialog {
      width: auto;
    }
    &.hide {
      .el-upload {
        display: none;
      }
    }
  }
</style>
<template>
  <div class="my_upload" :class="{hide:list.length >= length}">
    <el-upload
      action="/"
      :http-request="upload"
      :on-success="success"
      :file-list="list"
      :list-type="type"
      :on-preview="preview"
      :before-upload="beforeUpload"
      :on-remove="remove">
      <i class="el-icon-plus"></i>
    </el-upload>
    <el-dialog v-model="showPic" :modal-append-to-body="false" title="预览">
      <img width="100%" :style="{width:width+'px',height:height+'px'}" :src="picUrl">
    </el-dialog>
  </div>
</template>
<script>
  import {warning} from '../actions'
  import {upload, token} from '../api/systemApi'

  export default {
    data() {
      return {
        list: [],
        picUrl: '',
        showPic: false,
      }
    },
    props: {
      files: {
        type: Array,
        default: () => [],
      },
      type: {
        type: String,
        default: 'picture-card',
      },
      size: {
        type: Number,
        default: 1024 * 1024,
      },
      width: {
        type: String,
        default: '320',
      },
      height: {
        type: String,
        default: '200',
      },
      length: {
        type: Number,
        default: 5,
      },
    },
    watch: {
      files(val) {
        this.list = val.map(url => ({url}))
      },
    },
    methods: {
      upload,
      success(data) {
        const url = `${token.path}/${data.key}`
        this.list.push({url})
        this.$emit('success', this.list.map(({url}) => url))
      },
      beforeUpload(file) {
        if (this.type !== 'text' && file.size >= this.size) {
          warning(`图片大小不能超过${this.size / 1024 / 1024}M！请修改后重新上传！`)
          return false
        }
        return true
      },
      remove(file) {
        if (file) {
          const idx = this.list.findIndex(({url}) => url === file.url)
          this.list.splice(idx, 1)
          this.$emit('success', this.list.map(({url}) => url))
        }
      },
      preview({url}) {
        this.picUrl = url
        this.showPic = true
      },
    },
    created() {
      let myUploadTag = document.getElementById('my_upload_tag')
      // 如果这个tag不存在，则生成相关代码tag以加载代码
      if (myUploadTag === null) {
        myUploadTag = document.createElement('style')
        myUploadTag.type = 'text/css'
        myUploadTag.innerHTML = `.my_upload .el-upload-list__item,.my_upload .el-upload--picture-card{width:${this.width}px;height:${this.health}px;}`
        myUploadTag.id = 'my_upload_tag'
        let s = document.getElementsByTagName('head')[0]
        s.appendChild(myUploadTag)
      }
    },
  }
</script>
