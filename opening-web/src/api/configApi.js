import ajax from './ajax'
// 设置根目录
const ROOT = '/audio'
// 获取列表
export const getAudioListApi = ({page = 1, pageSize = 10, type} = {}) => ajax.post(`${ROOT}/getAudio/${type}`, {page, pageSize, type})
// 删除音频
export const delAudioApi = (id) => ajax.delete(`${ROOT}/del/${id}`)
// 获取音频详情
export const getAudioApi = (id) => ajax.get(`${ROOT}/get/${id}`)
// 创建
export const createAudioApi = (data) => ajax.post(`${ROOT}/saveOrUpdate`, data)
// 修改
export const updateAudioApi = (data) => ajax.post(`${ROOT}/saveOrUpdate`, data)
