import ajax from './ajax'

const ROOT = '/system'
// 上传域名
const UPLOAD_URL = 'http://up-z0.qiniu.com'
// 密匙对象 {bucket:'',token:'',path:''}
export let token = null
// 获取七牛上传密匙
const getToken = () => new Promise((resolve, reject) => {
  if (!token) {
    ajax.get(`${ROOT}/qiniu`).then((t) => {
      token = t
      resolve(token)
    }).catch((e) => reject(e))
  } else {
    resolve(token)
  }
})
// 七牛上传实现
export const upload = ({file}) => getToken().then(() => {
  const form = new FormData()
  // form.append('key', '') // 资源名，必须是UTF-8编码。 注意： 如果上传策略中 scope 指定为：<bucket>:<key>， 则该字段也必须指定。
  // form.append('crc32', '') // 上传内容的 crc32 校验码。 如填入，则七牛服务器会使用此值进行内容检验。
  // form.append('accept', '') // 当 HTTP 请求指定 accept 头部时，七牛会返回 content-type 头部的值。 该值用于兼容低版本 IE 浏览器行为。低版本 IE 浏览器在表单上传时，返回 application/json 表示下载，返回 text/plain 才会显示返回内容。
  form.append('token', token.uptoken)
  form.append('file', file)
  return ajax.post(UPLOAD_URL, form, {headers: {'Content-Type': 'multipart/form-data'}})
})
// 获取二维码列表
export const getEwCodeListApi = ({page = 1, pageSize = 10, identity = 1} = {}) => ajax.post(`${ROOT}/findCode/${identity}`, {page, pageSize})
// 生成二维码
export const getEwCodeApi = ({identity = 1, num = 1} = {}) => ajax.post(`${ROOT}/getCode/${identity}/${num}`)
// 设置
export const getConfigApi = (type) => ajax.get(`${ROOT}/config/${type}`)
// 保存
export const saveConfigApi = ({type, val} = {}) => ajax.post(`${ROOT}/saveConfig`, {id: type, val})
// 发送
export const sendmsgApi = (data = {}) => ajax.post(`${ROOT}/sendmsg`, data)
// 消息推送列表
export const sendListApi = ({page = 1, pageSize = 10} = {}) => ajax.post(`${ROOT}/msgList`, {page, pageSize})
// 获取统计
export const getStatisticApi = () => ajax.post('/statistic/getCount')
