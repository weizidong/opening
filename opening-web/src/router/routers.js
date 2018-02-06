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
    ],
  },
]
