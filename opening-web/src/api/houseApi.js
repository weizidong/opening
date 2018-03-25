import ajax from './ajax'
// 设置根目录
const ROOT = '/house'
// 获取房源信息
export const allHouseApi = () => ajax.get(`${ROOT}/all`)
// 添加户型
export const addHouseTypeApi = ({name, acreage, description, spec, pics}) => ajax.post(`${ROOT}/addType`, {name, acreage, description, spec, pics})
// 获取户型列表
export const listHouseTypeApi = () => ajax.get(`${ROOT}/listType`)
// 修改户型
export const updateHouseTypeApi = ({id, name, acreage, description, spec, pics}) => ajax.put(`${ROOT}/updateType`, {id, name, acreage, description, spec, pics})
// 删除户型
export const deleteHouseTypeApi = (id) => ajax.delete(`${ROOT}/deleteType/${id}`)
