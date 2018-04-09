<style lang="less" scoped>
  @import "../base.less";

  .ExportButton {
    float: right;
    margin: @table-head-margin;
    .download {
      text-align: center;
      padding: 15px;
    }
  }
</style>
<template>
  <div class="ExportButton">
    <el-button type="edit" @click="show">{{text}}</el-button>
    <el-dialog :title="text" :visible.sync="dialog.show" :modalAppendToBody="false" width="600px">
      <el-form :model="dialog" labelWidth="100px">
        <el-form-item label="导出时间选择">
          <el-date-picker :value="[dialog.start,dialog.end]" @input="dialog.start=$event[0].getTime();dialog.end=$event[1].getTime();" type="datetimerange" :picker-options="pickerOptions" range-separator="至" start-placeholder="开始时间" end-placeholder="结束时间" align="center"/>
        </el-form-item>
      </el-form>
      <div class="text-center">
        <el-button type="primary" @click="doMethod">确定导出</el-button>
        <el-button type="warning" @click="dialog.show = false">取消导出</el-button>
      </div>
    </el-dialog>
    <el-dialog :title="text" :visible.sync="download.show" :modalAppendToBody="false" width="450px">
      <span>{{download.content}}</span>
      <div class="download">
        <a :href="download.url" @click="download.show = false" v-if="!!download.url">下载</a>
      </div>
    </el-dialog>
  </div>
</template>
<script>
  export default {
    name: 'ExportButton',
    props: {
      text: {
        type: String,
        default: '导出EXCEL',
      },
      method: Function,
      showTime: {
        type: Boolean,
        default: true,
      },
    },
    data() {
      return {
        dialog: {show: false, start: null, end: null},
        download: {show: false, content: '正在生成文件导出中...请耐心等待...', url: null},
      }
    },
    computed: {
      pickerOptions() {
        return {disabledDate: (time) => time.getTime() > Date.now()}
      },
    },
    methods: {
      show() {
        if (this.showTime) {
          this.dialog.show = true
        } else {
          this.doMethod()
        }
      },
      doMethod() {
        this.dialog.show = false
        this.download.show = true
        this.method && this.method(this.dialog).then((url) => {
          this.download.content = '文件生成完成！请点击下载...'
          this.download.url = url
        })
      },
    },
  }
</script>
