import ajax from './ajax'
// 设置根目录
const ROOT = '/user'
// 获取用户列表
export const getUserListApi = ({page = 1, pageSize = 10, name} = {}) => ajax.post(`${ROOT}/list`, {page, pageSize, name})
// 获取认筹列表
export const getUserHouseApi = ({page = 1, pageSize = 10, phone} = {}) => ajax.post(`${ROOT}/user-list`, {page, pageSize, phone})
// 获取客户列表
export const getAllUserApi = ({page = 1, pageSize = 10, phone} = {}) => ajax.post(`${ROOT}/all-user-list`, {page, pageSize, phone})
// 导出认筹列表
export const exportUserHouseApi = () => ajax.post(`${ROOT}/export-user-list`)
// 登记用户
export const addUserApi = ({phone, idNumber, name, address}) => ajax.post(`${ROOT}/add`, {phone, idNumber, name, address})
