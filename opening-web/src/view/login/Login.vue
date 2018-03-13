<style lang="less" scoped>
  @import "login.less";
</style>

<template>
  <div class="login_bg">
    <transition name="fade" mode="out-in">
      <div v-if="!show" class="login" key="login" @keyup.enter="submit">
        <img class="img01" src="/static/img/logo_xxb.png">
        <img class="img02" src="/static/img/login_img.png">
        <div class="box">
          <div class="title">用户管理后台
            <div>User Management Background</div>
          </div>
          <el-form :model="data" :rules="rules1" ref="ruleForm1" labelWidth="0">
            <el-form-item label="" prop="account">
              <div class="el-input">
                <input v-model="data.account" class="el-input__inner" placeholder="请输入账号">
                <i class="el-input__icon el-icon-my-profilefill"/>
              </div>
            </el-form-item>
            <el-form-item label="" prop="password">
              <div class="el-input">
                <input type="password" v-model="data.password" class="el-input__inner" placeholder="请输入密码">
                <i class="el-input__icon el-icon-my-lock_fill"/>
              </div>
            </el-form-item>
            <el-button type="primary" @click="submit">登录</el-button>
            <!--<el-button class="register" type="text" size="small" @click="show = true">没有账号怎么办？ 去注册！</el-button>-->
          </el-form>
        </div>
      </div>
      <!--<el-card v-if="show" class="login" key="register">-->
      <!--<div class="title">注册</div>-->
      <!--<el-form :model="data" :rules="rules2" ref="ruleForm2" labelWidth="100px">-->
      <!--<el-form-item label="昵称：" prop="nickName">-->
      <!--<el-input v-model="data.nickName"></el-input>-->
      <!--</el-form-item>-->
      <!--<el-form-item label="电话号码：" prop="account">-->
      <!--<el-input v-model="data.account"></el-input>-->
      <!--</el-form-item>-->
      <!--<el-form-item label="密码：" prop="password">-->
      <!--<el-input type="password" v-model="data.password"></el-input>-->
      <!--</el-form-item>-->
      <!--<el-button type="primary" @click="register">注册</el-button>-->
      <!--<el-button class="register" type="text" size="small" @click="show = false">已经有账号了！ 去登录！</el-button>-->
      <!--</el-form>-->
      <!--</el-card>-->
    </transition>
  </div>
</template>

<script>
  import {loginApi} from '../../api/adminApi'
  import {success} from '../../actions'

  export default {
    name: 'login',
    data() {
      return {
        show: false,
        data: {account: '', password: ''},
        rules1: {
          account: [{required: true, message: '请输入账号...', trigger: 'blur'}],
          password: [{required: true, message: '请输入密码...', trigger: 'blur'}, {min: 6, message: '密码长度最少为6位...', trigger: 'blur'}],
        },
//        rules2: {
//          nickName: [{required: true, message: '请输入昵称...', trigger: 'blur'}],
//          account: [{required: true, message: '请输入账号...', trigger: 'blur'}],
//          password: [{required: true, message: '请输入密码...', trigger: 'blur'}, {min: 6, message: '密码长度最少为6位...', trigger: 'blur'}],
//        },
      }
    },
    methods: {
      submit() {
        this.$refs.ruleForm1.validate((valid) => {
          if (valid) {
            loginApi(this.data).then(() => {
              success('登录成功！')
              this.$router.push({name: 'home'})
            })
          } else {
            return false
          }
        })
      },
    },
  }
</script>
