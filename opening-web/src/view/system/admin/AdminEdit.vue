<template>
  <div class="content_data">
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ name: 'manage' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ name: 'adminList' }">管理员</el-breadcrumb-item>
      <el-breadcrumb-item>{{$route.params.id === CREATE ? '新增' : '修改'}}</el-breadcrumb-item>
    </el-breadcrumb>
    <el-button type="text" icon="d-arrow-left" @click="$router.push({name:'adminList'})"></el-button>
    <el-form :model="admin" :rules="rules" ref="admin" labelWidth="200px">
      <el-form-item label="头像：" prop="avatar">
        <Avatar :url="admin.avatar" @success="admin.avatar = $event"/>
      </el-form-item>
      <el-form-item label="姓名：" prop="name">
        <el-input v-model="admin.name" placeholder="请输入姓名..."/>
      </el-form-item>
      <el-form-item label="账号：" prop="account">
        <el-input v-model="admin.account" placeholder="请输入账号..."/>
      </el-form-item>
      <el-form-item label="密码：" prop="password">
        <el-input v-model="admin.password" placeholder="请输入密码..."/>
      </el-form-item>
      <el-form-item label="性别：" prop="sex">
        <el-radio-group v-model="admin.sex">
          <el-radio :label="1">男</el-radio>
          <el-radio :label="2">女</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="生日：" prop="birthday">
        <el-date-picker v-model="admin.birthday" type="date" format="yyyy-MM-dd" :picker-options="pickerOptions"/>
      </el-form-item>
      <el-form-item label="Email：" prop="email">
        <el-input v-model="admin.email" placeholder="请输入Emial..."/>
      </el-form-item>
      <el-form-item label="电话号码：" prop="phone">
        <el-input v-model="admin.phone" placeholder="请输入电话号码..."/>
      </el-form-item>
      <el-form-item label="状态：" prop="status">
        <el-switch v-model="admin.status" active-color="#13ce66" inactive-color="#ff4949" active-text="启用" inactive-text="禁用" :active-value="1" :inactive-value="2"/>
      </el-form-item>
      <el-form-item label="角色权限：" prop="roles">
        <el-checkbox-group v-model="admin.roles">
          <el-checkbox v-for="role in roles" :label="role.id" :key="role.id">{{role.name}}</el-checkbox>
        </el-checkbox-group>
      </el-form-item>
      <el-button type="primary" @click="submit">{{$route.params.id === CREATE ? '新增' : '保存'}}</el-button>
    </el-form>
  </div>
</template>

<script>
  import {getAdminApi, createAdminApi, updateAdminApi, mineApi} from '../../../api/adminApi'
  import {getRoleListApi} from '../../../api/roleApi'
  import {success, error, alert} from '../../../actions'
  import {CREATE} from '../../../constant'
  import Avatar from '../../../components/Avatar.vue'

  export default {
    name: 'adminEdit',
    data() {
      return {
        CREATE,
        roles: [],
        pickerOptions: {disabledDate: (time) => time.getTime() > Date.now()},
        admin: {avatar: '/static/img/logo.png', account: '', password: null, name: '', birthday: new Date().getTime(), sex: 1, email: '', phone: '', status: 1, roles: []},
        rules: {
          account: [{required: true, message: '请输入账号...', trigger: 'blur'}, {min: 5, message: '长度大于5个字符...', trigger: 'blur'}],
          password: [{min: 6, message: '长度大于6个字符...', trigger: 'blur'}],
          name: [{min: 2, message: '长度大于2个字符...', trigger: 'blur'}],
          email: [{type: 'email', message: 'Email格式不正确...', trigger: 'blur'}],
        },
      }
    },
    components: {Avatar},
    methods: {
      submit() {
        this.$refs.admin.validate((valid) => {
          if (valid) {
            const {id} = this.$route.params
            const fn = id === CREATE ? createAdminApi({...this.admin, roles: this.admin.roles.map(id => ({id}))}) : updateAdminApi({...this.admin, roles: this.admin.roles.map(id => ({id}))})
            fn.then(() => {
              success(`${id === CREATE ? '新增' : '修改'}管理员“${this.admin.account}”成功！`)
              mineApi().then(mine => mine.id !== id ? this.$router.push({name: 'adminList'}) : !this.admin.password ? this.$router.go(0) : alert('密码已经修改！请重新登录...').then(() => this.$router.push({name: 'login'})))
            }).catch(() => error(`${id === CREATE ? '新增' : '修改'}管理员“${this.admin.account}”失败！`))
          } else {
            return false
          }
        })
      },
    },
    created() {
      const {id} = this.$route.params
      getRoleListApi({pageSize: 0, menu: false}).then(({list}) => {
        this.roles = list
      })
      if (id !== CREATE) {
        getAdminApi(id).then(admin => {
          admin.roles = admin.roles.map(r => r.id)
          this.admin = admin
        })
      } else {
        this.rules.password.push({required: true, message: '请输入密码...', trigger: 'blur'})
      }
    },
  }
</script>
