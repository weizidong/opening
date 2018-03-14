<template>
  <div class="content_data">
    <Breadcrumb></Breadcrumb>
    <el-form :model="user" :rules="rules" ref="user" labelWidth="100px">
      <el-form-item label="姓名：" prop="name">
        <el-input v-model="user.name" placeholder="请输入姓名..."/>
      </el-form-item>
      <el-form-item label="电话：" prop="phone">
        <el-input v-model="user.phone" placeholder="请输入电话..."/>
      </el-form-item>
      <el-form-item label="身份证：" prop="idNumber">
        <el-input v-model="user.idNumber" placeholder="请输入身份证..."/>
      </el-form-item>
      <el-form-item label="住址：" prop="address">
        <el-input v-model="user.address" placeholder="请输入住址..."/>
      </el-form-item>
      <el-button type="edit" @click="submit">登记入库</el-button>
    </el-form>
  </div>
</template>

<script>
  import {addUserApi} from '../../api/userApi'
  import {success, error, alert} from '../../actions'
  import Breadcrumb from '../../components/Breadcrumb.vue'

  export default {
    name: 'userAdd',
    components: {Breadcrumb},
    data() {
      return {
        user: {phone: '', idNumber: '', name: '', address: ''},
        rules: {
          phone: [{required: true, pattern: /^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$/, message: '请填写11位手机号码...', trigger: 'blur'}],
          idNumber: [{required: true, pattern: /^([1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)/, message: '请填写正确的15（18）位身份证号码...', trigger: 'blur'}],
          name: [{required: true, message: '请填写真实姓名...', trigger: 'blur'}],
          address: [{required: true, message: '请填写现在的居住地址...', trigger: 'blur'}],
        },
      }
    },
    methods: {
      submit() {
        this.$refs.user.validate((valid) => {
            if (valid) {
              addUserApi(this.user).then((data) => {
                  success(`登记认筹“${this.user.name}”成功！`)
                  this.$alert(`<div>请认筹者使用卫星扫描该二维码进行绑定！</div><div class="text-center"><img src="${data}"></div>`, `${this.user.name}二维码`, {
                    confirmButtonText: '确定',
                    dangerouslyUseHTMLString: true
                  })
                }
              ).catch(({msg}) => error(msg || `登记认筹“${this.user.name}”失败！`))
            } else {
              return false
            }
          }
        )
      },
    },
    created() {
    }
    ,
  }
</script>
