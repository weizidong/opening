import ajax from './ajax'
// 根路径
const ROOT = '/role'
// 添加角色
export const createRoleApi = ({num = 1, pid = 0, name, tips = '', menus = []} = {}) => ajax.post(`${ROOT}/create`, {
  num, pid, name, menus, tips,
})
// 修改角色
export const updateRoleListApi = ({id, num = 1, pid = 0, tips = '', name, menus = []} = {}) => ajax.put(`${ROOT}/update`, {
  id, num, pid, name, menus, tips,
})
// 获取角色列表
export const getRoleListApi = ({page = 1, pageSize = 10, menu = true, start, end} = {}) => ajax.post(`${ROOT}/list`, {
  page, pageSize, start, end, menu,
})
// 获取角色
export const getRoleApi = (id) => ajax.get(`${ROOT}/get/${id}`)
// 删除角色
export const delRoleApi = (id) => ajax.delete(`${ROOT}/del/${id}`)
