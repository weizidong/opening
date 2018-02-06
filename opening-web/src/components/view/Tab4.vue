<template>
  <el-form :model="data" :rules="rules" ref="data" label-width="100px">
    <el-form-item label="标题：" prop="Title">
      <el-input v-model="data.Title" placeholder="请输入標題..."/>
    </el-form-item>
    <el-form-item label="图片：" prop="PicUrl">
      <Avatar :url="data.PicUrl" width="375" height="207" @success="data.PicUrl = $event"/>
      <el-tag type="danger">封面大小不能超过1M，图片格式为jpg,png,jpeg</el-tag>
    </el-form-item>
    <el-form-item label="描述：" prop="Description">
      <el-input v-model="data.Description"  placeholder="请输入描述..."/>
    </el-form-item>
    <el-form-item label="链接：" prop="Url">
      <el-input v-model="data.Url" placeholder="请输入链接..."/>
    </el-form-item>
    <!--<el-form-item label="省份：" prop="saClass">-->
      <!--<el-select v-model="item.saClass" filterable placeholder="请选择">-->
        <!--<el-option v-for="item in healths" :key="item.value" :label="item.label" :value="item.value"></el-option>-->
      <!--</el-select>-->
    <!--</el-form-item>-->
    <el-form-item label="所属省份：" prop="spId">
      <el-select v-model="data.spId" filterable placeholder="请选择">
        <el-option v-for="item in provice" :key="item.spId" :label="item.spName" :value="item.spId"></el-option>
      </el-select>
    </el-form-item>
    <el-button type="edit" @click="submit">确定</el-button>
    <el-button type="del" @click="$router.back()">取消</el-button>
  </el-form>
</template>

<script>
  import { sendmsgApi } from '../../api/systemApi'
  import { getProviceApi } from '../../api/universityApi'
  import { CREATE } from '../../constant/index'
  import { success, error } from '../../actions/index'
  import Avatar from '../../components/Avatar.vue'
  import {serviceTypeFilter} from '../../constant/filter'

  export default {
    name: 'tabItem4',
    components: {
      Avatar},
    data() {
      return {
        CREATE,
        data: {Title: '', PicUrl: '', Description: '', Url: '', spId: ''},
        provice: [],
        rules: {
          Title: [{required: true, message: '必填...', trigger: 'blur'}],
          PicUrl: [{required: true, message: '必填...', trigger: 'blur'}],
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
            sendmsgApi(this.data).then(() => {
              success(`设置成功！`)
            }).catch(({msg}) => error(msg || `设置失败！`))
          } else {
            return false
          }
        })
      },
    },
    created() {
      // 获取省份
      getProviceApi().then(list => {
        this.provice = list
      })
    },
  }
</script>
