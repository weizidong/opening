/**
 * 启动入口
 */
import Vue from 'vue'
import router from './router'
import 'element-ui/lib/theme-chalk/index.css'
import ElementUI from 'element-ui'
import App from './App.vue'

Vue.use(ElementUI)

new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: {App},
})
