import ajax from './ajax'
// 设置根目录
const ROOT = '/statistic'
// 获取文章列表统计
export const getOperationApi = () => ajax.post(`${ROOT}/getCount`)
