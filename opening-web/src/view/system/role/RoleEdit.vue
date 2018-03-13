<template>
  <div class="content_data">
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ name: 'manage' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ name: 'roleList' }">角色</el-breadcrumb-item>
      <el-breadcrumb-item>{{$route.params.id === CREATE ? '新增' : '修改'}}</el-breadcrumb-item>
    </el-breadcrumb>
    <el-button type="text" icon="d-arrow-left" @click="$router.push({name:'roleList'})"></el-button>
    <el-form :model="role" :rules="rules" ref="role" labelWidth="100px">
      <el-form-item label="角色名称：" prop="name">
        <el-input v-model="role.name"/>
      </el-form-item>
      <el-form-item label="父角色：" prop="pid">
        <el-select v-model="role.pid" filterable placeholder="请选择父角色...">
          <el-option v-for="role in roles" :key="role.id" :label="role.name" :value="role.id"/>
        </el-select>
      </el-form-item>
      <el-form-item label="角色权限：" prop="menus">
        <el-tree :data="menus" style="height: 200px;overflow-y: scroll" checkStrictly :props="treeProps" ref="tree" show-checkbox node-key="id" :indent="30" default-expand-all @check-change="checkChange"/>
      </el-form-item>
      <el-form-item label="角色提示：" prop="tips">
        <el-input type="textarea" v-model="role.tips" :rows="5"/>
      </el-form-item>
      <el-button type="primary" @click="submit">{{$route.params.id === CREATE ? '新增' : '保存'}}</el-button>
    </el-form>
  </div>
</template>

<script>
  import {getRoleApi, createRoleApi, updateRoleListApi, getRoleListApi} from '../../../api/roleApi'
  import {getMenuTreeApi} from '../../../api/menuApi'
  import {success, error} from '../../../actions'
  import {CREATE} from '../../../constant'

  export default {
    name: 'roleEdit',
    data() {
      return {
        CREATE,
        roles: [{id: 0, name: '无'}],
        menus: [],
        role: {name: '', num: 0, pid: 0, tips: '', menus: []},
        rules: {},
        treeProps: {
          label: 'name',
          children: 'children',
          disabled: ({status}) => !status,
        },
      }
    },
    methods: {
      submit() {
        this.$refs.role.validate((valid) => {
          if (valid) {
            const {id} = this.$route.params
            const fn = id === CREATE ? createRoleApi(this.role) : updateRoleListApi(this.role)
            fn.then(() => {
              success(`${id === CREATE ? '新增' : '修改'}角色“${this.role.name}”成功！`)
              this.$router.push({name: 'roleList'})
              this.$router.go(0)
            }).catch(() => error(`${id === CREATE ? '新增' : '修改'}角色“${this.role.name}”失败！`))
          } else {
            return false
          }
        })
      },
      checkChange() {
        this.role.menus = this.$refs.tree.getCheckedKeys().map(id => ({id}))
      },
    },
    created() {
      const {id} = this.$route.params
      getRoleListApi({pageSize: 0, menu: false}).then(({list}) => {
        this.roles = [...this.roles, ...list]
      })
      getMenuTreeApi().then(d => {
        this.menus = d
      })
      if (id !== CREATE) {
        getRoleApi(id).then((role) => {
          this.$refs.tree.setCheckedKeys(role.menus.map(d => d.id))
          this.role = role
        })
      }
    },
  }
</script>
