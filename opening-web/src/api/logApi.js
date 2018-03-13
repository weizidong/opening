import ajax from './ajax'
// 根路径
const ROOT = '/log'
// 分页获取登录日志列表
export const getLoginLogApi = ({page = 1, pageSize = 10, start, end} = {}) => ajax.post(`${ROOT}/loginLog`, {
  page, pageSize, start, end,
})
// 分页获取操作日志列表
export const getOperationLogApi = ({page = 1, pageSize = 10, start, end} = {}) => ajax.post(`${ROOT}/operationLog`, {
  page, pageSize, start, end,
})
