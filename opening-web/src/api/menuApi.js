import ajax from './ajax'
// 根路径
const ROOT = '/menu'
// 修改权限菜单
export const updateMenuApi = ({id, code, pid = 0, name, icon, url, num, tips, status, ismenu = 0} = {}) => ajax.put(`${ROOT}/update`, {
  id, code, pid, name, icon, url, num, tips, status, ismenu,
})
// 获取权限菜单树
export const getMenuTreeApi = () => ajax.get(`${ROOT}/tree`)
// 创建权限菜单
export const createMenuApi = ({code, pid = 0, name, icon, url, num, tips, status, ismenu = 0} = {}) => ajax.post(`${ROOT}/create`, {
  code, pid, name, icon, url, num, tips, status, ismenu,
})
// 启用/禁用权限菜单
export const toggleMenuApi = (id) => ajax.put(`${ROOT}/toggle/${id}`)
// 获取权限菜单列表
export const listMenuApi = () => ajax.get(`${ROOT}/list`)
// 删除权限菜单
export const delMenuApi = (id) => ajax.delete(`${ROOT}/del/${id}`)
