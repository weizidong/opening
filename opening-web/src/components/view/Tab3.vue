<template>
  <el-form :model="data" :rules="rules" ref="data" label-width="100px">
    <el-form-item label="背景图片：" prop="PicUrl">
      <Avatar :url="data.val.PicUrl" width="375" height="207" @success="health.avatar = $event"/>
      <el-tag type="danger">封面大小不能超过1M，图片格式为jpg,png,jpeg</el-tag>
    </el-form-item>
    <el-button type="edit" @click="submit">确定</el-button>
    <el-button type="del" @click="$router.back()">取消</el-button>
  </el-form>
</template>

<script>
  import { getConfigApi, saveConfigApi } from '../../api/systemApi'
  import { CREATE } from '../../constant/index'
  import { success, error } from '../../actions/index'
  import Avatar from '../../components/Avatar.vue'
  import {serviceTypeFilter} from '../../constant/filter'

  export default {
    name: 'tabItem3',
    components: {Avatar},
    data() {
      return {
        CREATE,
        data: {type: 'BG_JOIN_VIP', val: {}},
        rules: {
          val: [{min: 1, message: '不能超过15个字符...', trigger: 'blur'}],
        },
      }
    },
    computed: {
    },
    methods: {
      serviceTypeFilter,
      submit() {
        this.$refs.data.validate((valid) => {
          if (valid) {
            saveConfigApi(this.data).then(() => {
              success(`设置成功！`)
            }).catch(({msg}) => error(msg || `设置失败！`))
          } else {
            return false
          }
        })
      },
    },
    created() {
      getConfigApi(this.data.type).then(val => {
        this.data.val = JSON.parse(val || '{}')
      })
    },
  }
</script>
