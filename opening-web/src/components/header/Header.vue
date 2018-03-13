<style lang="less" scoped>
  @import "header.less";
</style>
<template>
  <div class="header">
    <el-dropdown class="user" @command="handleCommand">
      <span class="el-dropdown-link">
        <img :src="'/static/img/admin.png'">
        <!--<span>{{mine.name || mine.nickName || '管理员'}}</span>-->
        <i class="el-icon-arrow-down el-icon--right"></i>
      </span>
      <el-dropdown-menu slot="dropdown" class="header-dropdown">
        <el-dropdown-item command="info"><i class="el-icon-my-admin"/>我的资料</el-dropdown-item>
        <el-dropdown-item command="password"><i class="el-icon-my-setting"/>修改密码</el-dropdown-item>
        <el-dropdown-item command="logout"><i class="el-icon-my-logout"/>退出登录</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
    <el-dialog v-if="dialog.data.id" :modal-append-to-body="false" :title="dialog.title" :visible.sync="dialog.show" top="10px" custom-class="my-dialog">
      <el-form :model="dialog.data" ref="form1" label-width="160px">
        <el-form-item prop="avatar" label-width="0" style="text-align: center">
          <Avatar :url="dialog.data.avatar" @success="admin.avatar = $event"/>
        </el-form-item>
        <el-form-item label="账号：" prop="account">
          <el-input v-model="dialog.data.account" disabled/>
        </el-form-item>
        <el-form-item label="姓名：" prop="name">
          <el-input v-model="dialog.data.name" placeholder="请输入姓名..."/>
        </el-form-item>
        <el-form-item label="性别：" prop="sex">
          <el-radio-group v-model="dialog.data.sex">
            <el-radio :label="1">男</el-radio>
            <el-radio :label="2">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="生日：" prop="birthday">
          <el-date-picker v-model="dialog.data.birthday" type="date" format="yyyy-MM-dd" :picker-options="pickerOptions"/>
        </el-form-item>
        <el-form-item label="Email：" prop="email">
          <el-input v-model="dialog.data.email" placeholder="请输入Emial..."/>
        </el-form-item>
        <el-form-item label="电话号码：" prop="phone">
          <el-input v-model="dialog.data.phone" placeholder="请输入电话号码..."/>
        </el-form-item>
        <el-form-item label="最近登录时间：" style="text-align: left">
          <el-date-picker v-model="dialog.data.logintime" align="left" type="date" placeholder="最近登录时间..." format="yyyy-MM-dd HH:mm" readonly/>
        </el-form-item>
      </el-form>
      <div class="text-center">
        <el-button @click="dialog.show = false">取 消</el-button>
        <el-button type="primary" @click="update(dialog.data)">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog v-if="!dialog.data.id" :modal-append-to-body="false" :title="dialog.title" :visible.sync="dialog.show">
      <el-form :model="dialog.data" :rules="rule2" ref="form2" label-width="120px">
        <el-form-item label="原密码：" prop="old">
          <el-input type="password" v-model="dialog.data.old"/>
        </el-form-item>
        <el-form-item label="新密码：" prop="pwd">
          <el-input type="password" v-model="dialog.data.pwd"/>
        </el-form-item>
        <el-form-item label="确认密码：" prop="pwd1">
          <el-input type="password" v-model="dialog.data.pwd1"/>
        </el-form-item>
      </el-form>
      <div class="text-center">
        <el-button @click="dialog.show = false">取 消</el-button>
        <el-button type="primary" @click="changePassword(dialog.data)">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {mineApi, logoutApi, changePasswordApi, updateAdminApi} from '../../api/adminApi'
  import Avatar from '../../components/Avatar.vue'
  import {alert, success, error} from '../../actions/index'

  export default {
    name: 'myHeader',
    components: {Avatar},
    data() {
      return {
        mine: {},
        dialog: {title: '', show: false, data: {}},
        pickerOptions: {disabledDate: (time) => time.getTime() > Date.now()},
        rule1: {},
        rule2: {
          old: [{type: 'string', required: true, message: '请输入原密码！', trigger: 'blur'}, {type: 'string', min: 6, message: '密码长度不少于6位！', trigger: 'blur'}],
          pwd: [{
            type: 'string',
            required: true,
            validator: (rule, value, callback) => {
              if (!value) {
                callback(new Error('请输入新密码！'))
              } else if (value.length < 6) {
                callback(new Error('密码长度不能少于6位！'))
//              } else if (!/[0-9]+/.test(value) || !/[a-z]+/.test(value) || !/[A-Z]+/.test(value)) {
//                callback(new Error('密码必须更包含数字、大写字母、小写字母'))
              } else {
                if (this.dialog.data.pwd1) {
                  this.$refs.form2.validateField('pwd1')
                }
                callback()
              }
            },
            trigger: 'blur',
          }],
          pwd1: [{
            type: 'string',
            required: true,
            validator: (rule, value, callback) => {
              if (!value) {
                callback(new Error('请再次输入密码！'))
              } else if (value !== this.dialog.data.pwd) {
                callback(new Error('两次输入密码不一致!'))
              } else {
                callback()
              }
            },
            trigger: 'blur',
          }],
        },
      }
    },
    methods: {
      handleCommand(command) {
        if (command === 'info') {
          this.dialog = {title: '我的资料', show: true, data: {avatar: '/static/img/admin.png', name: '', birthday: new Date().getTime(), sex: 1, email: '', phone: '', ...this.mine}}
        } else if (command === 'password') {
          this.dialog = {title: '修改密码', show: true, data: {pwd: '', old: ''}}
        } else if (command === 'logout') {
          logoutApi().then(() => this.$router.push({name: 'login'}))
        }
      },
      changePassword(data) {
        this.$refs.form2.validate((valid) => {
          if (valid) {
            changePasswordApi(data).then(() => success('密码修改成功！请重新登录...', {onClose: () => this.$router.push({name: 'login'})})).catch(({msg}) => error(msg))
          } else {
            return false
          }
        })
      },
      update(data) {
        this.$refs.form1.validate((valid) => {
          if (valid) {
            updateAdminApi(data).then(() => {
              success('修改成功!')
              this.dialog.show = false
              this.getMine()
            }).catch(({msg}) => error(msg))
          } else {
            return false
          }
        })
      },
      changeTel() {
        alert('更换手机正在开发中。。。')
      },
      getMine() {
        mineApi().then((user) => {
          this.mine = user
        })
      },
    },
    created() {
      this.getMine()
    },
  }
</script>
