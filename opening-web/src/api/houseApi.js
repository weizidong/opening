import ajax from './ajax'
// 设置根目录
const ROOT = '/house'
// 获取房源信息
export const allHouseApi = () => ajax.get(`${ROOT}/all`)
