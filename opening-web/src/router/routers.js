/**
 * 路由页面定义
 */
export default [
  // 主页
  {path: `/`, redirect: `/view/home`},
  // 登录
  {path: `/login`, component: require('../view/login/Login.vue').default, name: 'login', meta: {title: '登录'}},
  // 页面
  {
    path: `/view`,
    component: require('../view/Home.vue').default,
    children: [
      // 主页
      {path: `home`, component: require('../view/home/Home.vue').default, name: 'home', meta: {title: '主页'}},
      // 系统管理
      {path: `system/admin`, component: require('../view/system/admin/AdminList.vue').default, name: 'adminList', meta: {title: '管理员'}},
      {path: `system/admin/:id`, component: require('../view/system/admin/AdminEdit.vue').default, name: 'adminEdit', meta: {title: '编辑管理员'}},
      {path: `system/role`, component: require('../view/system/role/RoleList.vue').default, name: 'roleList', meta: {title: '角色'}},
      {path: `system/role/:id`, component: require('../view/system/role/RoleEdit.vue').default, name: 'roleEdit', meta: {title: '编辑角色'}},
      {path: `system/menu`, component: require('../view/system/menu/MenuList.vue').default, name: 'menuList', meta: {title: '权限菜单'}},
      // 登记认筹者
      {path: `user/add`, component: require('../view/user/Add.vue').default, name: 'userAdd', meta: {title: '登记认筹者'}},
    ],
  },
  {
    path: `/saling`,
    component: require('../view/saling/Saling.vue').default,
  }
]
