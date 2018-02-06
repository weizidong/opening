/**
 * 路由定义
 */
import Vue from 'vue'
import Router from 'vue-router'
import routes from './routers'
import {CREATE, TITLE} from '../constant'

Vue.use(Router)

const router = new Router({
  mode: 'history',
  routes,
})

router.afterEach(({meta, params}) => {
  if (!meta || !meta.title) {
    document.title = TITLE
  } else if (meta.title && params.id === CREATE) {
    document.title = `${TITLE} - 添加${meta.title.substr(2)}`
  } else if (meta.title) {
    document.title = `${TITLE} - ${meta.title}`
  }
})

export default router
