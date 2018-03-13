<template>
  <div class="content_data">
    <Breadcrumb/>
    <div v-loading="loading" element-loading-text="拼命加载中...">
      <el-tree :data="data" :props="options" node-key="id" :render-content="renderContent"/>
    </div>
    <el-dialog :title="menu.title" :modal="false" customClass="my-dialog" :visible.sync="menu.show" top="10px" width="1000px">
      <el-form :model="menu" ref="menu" labelWidth="120px">
        <el-form-item label="菜单名称：" :rules="[{required: true, message: '请输入菜单名称...', trigger: 'blur'}]">
          <el-input v-model="menu.name"/>
        </el-form-item>
        <el-form-item label="菜单图标：">
          <el-radio-group v-model="menu.icon">
            <el-radio v-for="icon in icons" style="margin: 5px 5px 5px 0;width: 50px;line-height: 20px" :label="icon" :key="icon"><i :class="icon" style="font-size: 20px;vertical-align: middle"/></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="菜单顺序：">
          <el-input-number v-model="menu.num"/>
        </el-form-item>
        <el-form-item label="菜单说明：">
          <el-input type="textarea" v-model="menu.tips" :rows="3"/>
        </el-form-item>
        <el-form-item label="父级菜单：">
          <el-select v-model="menu.pid" filterable placeholder="请选择父级菜单...">
            <el-option v-for="menu in menus" :key="menu.id" :label="menu.name" :value="menu.id"/>
          </el-select>
        </el-form-item>
        <el-form-item label="URL地址：">
          <el-input v-model="menu.url"/>
        </el-form-item>
        <el-form-item label="权限Code：">
          <el-input v-model="menu.code"/>
        </el-form-item>
      </el-form>
      <div class="text-center">
        <el-button @click="menu.show = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script type="text/jsx">
  import {getMenuTreeApi, toggleMenuApi, updateMenuApi, listMenuApi, createMenuApi, delMenuApi} from '../../../api/menuApi'
  import {success, error, confirm} from '../../../actions'
  import {icons} from '../../../constant'
  import Breadcrumb from '../../../components/Breadcrumb.vue'
  export default {
    name: 'menuList',
    data() {
      return {
        icons,
        options: {label: 'name', children: 'children'},
        menus: [{id: 0, name: '顶级菜单'}],
        data: [],
        loading: true,
        menu: {show: false, name: '', icon: '', tips: '', num: 0, code: '', url: '', pid: 0},
      }
    },
    components: {Breadcrumb},
    methods: {
      renderContent(h, {node, data, store}) {
        return (
          <div class="tree_data">
            <div class="tree_name" style={{width: 160 - ((node.level - 1) * 16) + 'px'}}><i class={data.icon || (data.ismenu ? 'el-icon-menu' : 'el-icon-date')}/>{data.name}</div>
            <div class="tree_content">
              <span class="text-overflow" style="width:35%">{data.url}</span>
              <span style="width:5%">{data.num}</span>
              <span style="width:20%">{data.tips}</span>
              <span style="width:40%;text-align:right;">
                <el-button plain={true} type="danger" size="mini" on-click={(e) => this.toggle(data, e)}>{['启用', '禁用'][data.status]}</el-button>
                <el-button plain={true} type="primary" size="mini" on-click={(e) => this.edit(data, e)}>修改</el-button>
                <el-button plain={true} type="primary" size="mini" on-click={(e) => this.create(data, e)}>创建</el-button>
                <el-button plain={true} type="danger" size="mini" on-click={(e) => this.del(data, store, e)}>删除</el-button>
              </span>
            </div>
          </div>)
      },
      edit(data, e) {
        e.stopPropagation()
        this.menu = {...this.menu, ...data, title: `修改菜单“${data.name}”`, show: true}
      },
      save() {
        this.$refs.menu.validate((valid) => {
          if (valid) {
            const fn = this.menu.id ? updateMenuApi(this.menu) : createMenuApi(this.menu)
            fn.then(() => {
              this.menu.show = false
              this.getTree()
            })
          } else {
            return false
          }
        })
      },
      toggle(data, e) {
        e.stopPropagation()
        confirm(`确认${data.status ? '禁用' : '启用'}菜单"${data.name}"?`, 'warning', '操作确认').then(() => toggleMenuApi(data.id).then(() => {
          success(`${data.status ? '禁用' : '启用'}菜单"${data.name}"成功！`)
          data.status = data.status ? 0 : 1
        }).catch(({msg}) => error(msg || '操作失败！请稍后重试...')))
      },
      getTree() {
        this.loading = true
        getMenuTreeApi().then((data) => {
          this.data = data
          this.loading = false
        })
      },
      create({id}, e) {
        e.stopPropagation()
        this.menu = {name: '', icon: '', tips: '', num: 0, pid: id, title: `创建菜单`, show: true}
      },
      del(data, store, e) {
        e.stopPropagation()
        confirm(`确认删除菜单"${data.name}"?`, 'error', '删除确认').then(() => delMenuApi(data.id).then(() => {
          success(`删除菜单"${data.name}"成功！`)
          store.remove(data)
        }).catch(({msg}) => error(msg || '操作失败！请稍后重试...')))
      },
    },
    created() {
      listMenuApi().then((menus) => {
        this.menus = [...this.menus, ...menus]
      })
      this.getTree()
    },
  }
</script>
