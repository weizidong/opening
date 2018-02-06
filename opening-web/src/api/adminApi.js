import md5 from 'md5'
import ajax from './ajax'
// 根路径
const ROOT = '/admin'
// 缓存我的信息
let mine = null
// 缓存菜单信息
let menus = null
// 登录
export const loginApi = ({account = '', password = '', code = ''} = {}) => ajax.post(`${ROOT}/login`, {account, password: md5(password), code})
// 获取当前登录
export const mineApi = async () => {
  if (!mine) {
    mine = await ajax.get(`${ROOT}/current`)
  }
  return Promise.resolve(mine)
}
// 登出
export const logoutApi = () => ajax.get(`${ROOT}/logout`)
// 获取菜单
export const menuApi = async () => {
  if (!menus) {
    menus = await ajax.get(`${ROOT}/menu`)
  }
  return Promise.resolve(menus)
}
// 创建管理员
export const createAdminApi = ({avatar, account, password, name, birthday, sex = 1, email, phone, roles = []} = {}) => ajax.post(`${ROOT}/create`, {
  avatar, account, password: password && md5(password), name, birthday, sex, email, phone, roles,
})
// 修改管理员
export const updateAdminApi = ({id, avatar, account, password, name, birthday, sex = 1, email, phone, roles = []} = {}) => ajax.put(`${ROOT}/update`, {
  id, avatar, account, password: password && md5(password), name, birthday, sex, email, phone, roles,
})
// 获取管理员
export const findAdminListApi = ({page = 1, pageSize = 10} = {}) => ajax.post(`${ROOT}/list`, {page, pageSize})
// 获取管理员
export const getAdminApi = (id) => ajax.get(`${ROOT}/get/${id}`)
// 删除管理员
export const delAdminApi = (id) => ajax.delete(`${ROOT}/del/${id}`)
// 启用、禁用管理员
export const toggleAdminApi = (id) => ajax.put(`${ROOT}/toggle/${id}`)
// 修改密码
export const changePasswordApi = ({old = '', pwd = ''}) => ajax.put(`${ROOT}/changePassword`, {oldPwd: md5(old), newPwd: md5(pwd)})
