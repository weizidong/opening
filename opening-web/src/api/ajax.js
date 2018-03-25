import Axios from 'axios'
import router from '../router'
import {error} from '../actions'
import {logError, logInfo} from '../constant/utils'
// TODO:全局默认设置
Axios.defaults.baseURL = '/rest'
Axios.defaults.headers.post['Content-Type'] = 'application/json'
// TODO:请求拦截器,统一处理参数
Axios.interceptors.request.use((config) => {
  logInfo('请求：', config.url, '\n参数:', config.data)
  // TODO:添加debug模式
  // if (process.env.NODE_ENV === 'development' || location.search.split(/[?&]/).indexOf('debug=true') !== -1) {
  //   config.url += config.url.indexOf('?') !== -1 ? '&debug=true' : '?debug=true'
  // }
  // TODO:处理token
  return config
}, (error) => {
  logError(error)
  // TODO:错误请求处理
  return Promise.reject(error)
})

// TODO:返回拦截器
Axios.interceptors.response.use((response) => {
  if (response.status !== 200) {
    logError(response)
    error('网络异常，请稍后重试...')
    return Promise.reject(response)
  }
  logInfo('请求：', response.config.url, '\n结果：', response.data)
  const {code, msg, data} = response.data
  if (!code) {
    return response.data
  } else if (code === 200) {
    return data || response.data
  } else if (code !== 200) {
    const data = {code: code || 500, msg: msg || '服务器异常！请稍后重试...'}
    if (code === 600) {
      router.replace({name: 'login'})
    } else {
      error(data.msg)
    }
    return Promise.reject(data)
  }
}, (error) => {
  logError(error)
  // TODO:错误请求处理
  return Promise.reject(error)
})
export default Axios
