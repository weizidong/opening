<template>
  <div class="content_data">
    <Breadcrumb>
      <el-breadcrumb-item>{{CREATE == $route.params.id ? '添加广告' : '编辑广告'}}</el-breadcrumb-item>
    </Breadcrumb>
    <el-form :model="banner" :rules="rules" ref="banner" labelWidth="100px">
      <el-form-item label="广告名称：" prop="title">
        <el-input v-model="banner.title" placeholder="请输入广告名称..."/>
      </el-form-item>
      <el-form-item label="广告类型：" prop="type">
        <el-radio-group v-model="banner.type">
          <el-radio :label="0">APP首页广告</el-radio>
          <el-radio :label="1">引导页广告</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="封面图：" prop="imgUrl">
        <Avatar :url="banner.imgUrl" width="375" height="207" @success="banner.imgUrl = $event"/>
        <el-tag type="danger">封面图建议宽高比：1.8：1，建议尺寸：750px*414px</el-tag>
      </el-form-item>
      <el-form-item label="开始时间：" prop="starttime">
        <el-date-picker v-model="banner.starttime" type="datetime" placeholder="开始时间..." :picker-options="startPickerOptions"/>
      </el-form-item>
      <el-form-item label="结束时间：" prop="endtime">
        <el-date-picker v-model="banner.endtime" type="datetime" placeholder="开始时间..." :picker-options="endPickerOptions"/>
      </el-form-item>
      <el-form-item label="链接：" prop="url">
        <el-input v-model="banner.url" placeholder="请输入链接..."/>
      </el-form-item>
      <el-button type="edit" @click="submit">确定</el-button>
      <el-button type="del" @click="$router.back()">取消</el-button>
    </el-form>
  </div>
</template>

<script>
  import { getBannerApi, createBannerApi, updateBannerApi } from '../../api/userApi'
  import { CREATE } from '../../constant'
  import { success, error } from '../../actions'
  import Avatar from '../../components/Avatar.vue'
  import Breadcrumb from '../../components/Breadcrumb.vue'

  export default {
    name: 'userAdd',
    components: {Avatar, Breadcrumb},
    data() {
      return {
        CREATE,
        banner: {imgUrl: '/static/img/logo.png', title: '', type: 0, starttime: Date.now(), endtime: Date.now(), url: null},
        rules: {
          title: [{max: 15, message: '不能超过15个字符...', trigger: 'blur'}],
          url: [{
            validator: (rule, value, callback) => {
              if (value && value.indexOf('http://') !== 0 && value.indexOf('https://') !== 0) {
                callback(new Error('外链链接必须带上http://或https://'))
              } else {
                callback()
              }
            },
            trigger: 'blur',
          }],
        },
      }
    },
    computed: {
      startPickerOptions () {
        return {disabledDate: (time) => time.getTime() > this.banner.endtime}
      },
      endPickerOptions () {
        return {disabledDate: (time) => time.getTime() < Date.now() || time.getTime() < this.banner.starttime}
      },
    },
    methods: {
      submit() {
        this.$refs.banner.validate((valid) => {
          if (valid) {
            const {id} = this.$route.params
            const fn = id === CREATE ? createBannerApi(this.banner) : updateBannerApi(this.banner)
            fn.then(() => {
              success(`${id === CREATE ? '创建' : '编辑'}广告“${this.banner.title}”成功！`)
              this.$router.back()
            }).catch(({msg}) => error(msg || `${id === CREATE ? '创建' : '编辑'}广告“${this.banner.title}”失败！`))
          } else {
            return false
          }
        })
      },
    },
    created() {
      const {id} = this.$route.params
      if (id === CREATE) {
        this.banner = {imgUrl: '/static/img/logo.png', title: '', type: 0, starttime: Date.now(), endtime: Date.now(), url: null}
      } else {
        getBannerApi(id).then(banner => {
          this.banner = banner
        })
      }
    },
  }
</script>
