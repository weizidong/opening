import ajax from './ajax'
// 根路径
const ROOT = '/cityClass'
// 修改城市
export const updateCityApi = ({qccId, qccName, qccParenid, qccStatus, qccPriority} = {}) => ajax.put(`${ROOT}/update`, {
  qccId, qccName, qccParenid, qccStatus, qccPriority,
})
// 创建城市
export const createCityApi = ({qccName, qccParenid = 0, qccStatus = 1, qccPriority = 0} = {}) => ajax.post(`${ROOT}/create`, {
  qccName, qccParenid, qccStatus, qccPriority,
})
// 启用/禁用城市
export const toggleCityApi = (id) => ajax.put(`${ROOT}/toggle/${id}`)
// 获取城市列表
export const listCityApi = ({page, pageSize, parentId, qccName}) => ajax.post(`${ROOT}/list`, {page, pageSize, parentId, qccName})
// 删除城市
export const delCityApi = (id) => ajax.delete(`${ROOT}/delete/${id}`)
// 获取城市详情
export const getCityApi = (id) => ajax.get(`${ROOT}/get/${id}`)
