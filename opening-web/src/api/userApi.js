import ajax from './ajax' // 默认值显示 0

// 设置根目录
const ROOT = '/user'
// 获取用户列表
export const getUserListApi = ({page = 1, pageSize = 10, identity, name, nickname} = {}) => ajax.post(`${ROOT}/userList`, {
  page,
  pageSize,
  identity,
  name,
  nickname,
})
//
export const delUserApi = (id) => ajax.delete(`${ROOT}/delUser/${id}`)
// 获取详情
export const getUserApi = (id) => ajax.get(`${ROOT}/user/${id}`)
// 创建
export const createUserApi = (data) => ajax.post(`${ROOT}/updateUser`, data)
// 修改
export const updateUserApi = (data) => ajax.post(`${ROOT}/updateUser`, data)
// 获取下级详情
export const getChildApi = ({page = 1, pageSize = 10, pid, name}) => ajax.post(`${ROOT}/subuserList/${pid}`, {
  page,
  pageSize,
  name,
})
// 会员类别列表
let vipList
export const setVipListApi = async () => {
  if (!vipList) {
    vipList = await ajax.get(`${ROOT}/vipList`)
  }
  return Promise.resolve(vipList)
}
// 删除会员
export const delVipApi = (id) => ajax.delete(`${ROOT}/delVip/${id}`)
// 修改会员价格
export const updateSetVipApi = (data) => {
  vipList = null
  return ajax.post(`${ROOT}/saveVip`, data)
}
// 创建会员价格
export const createSetVipApi = (data) => ajax.post(`${ROOT}/saveVip`, data)
// 获取专家选项
export const getAuthorListApi = ({page = 0, pageSize = 0, identity}) => ajax.post(`${ROOT}/userList`, {page, pageSize, identity})
