import ajax from './ajax' // 默认值显示 0

// 设置根目录
const ROOT = '/service'
// 获取用户订单
export const getOrderListApi = ({page = 1, pageSize = 10, status} = {}) => ajax.post(`${ROOT}/orderList/${status}`, {page, pageSize, status})
// 改变订单状态
export const changeOrderApi = (id, status) => ajax.put(`${ROOT}/changeStatus/${id}/${status}`)
// 获取服务列表
export const getServiceListApi = ({page = 1, pageSize = 10, title} = {}) => ajax.post(`${ROOT}/list`, {page, pageSize, title})
// 删除服务
export const delServiceApi = (id) => ajax.delete(`${ROOT}/del/${id}`)
// 获取服务详情
export const getServiceApi = (id) => ajax.get(`${ROOT}/get/${id}`)
// 修改服务详情
export const updateServiceApi = ({id, title = '', cover = '', costPrice = 0, presentPrice = 0, depositPrice = 0, type = 2, reserveStartTime, reserveEndTime, startTime, endTime, pics = [], description} = {}) => ajax.put(`${ROOT}/update`, {
  id, title, cover, costPrice, presentPrice, depositPrice, type, reserveStartTime, reserveEndTime, startTime, endTime, pics, description,
})

// 创建服务详情
export const createServiceApi = ({title = '', cover = '', costPrice = 0, presentPrice = 0, depositPrice = 0, type = 2, reserveStartTime, reserveEndTime, startTime, endTime, pics = [], description} = {}) => ajax.post(`${ROOT}/create`, {
  title, cover, costPrice, presentPrice, depositPrice, type, reserveStartTime, reserveEndTime, startTime, endTime, pics, description,
})
