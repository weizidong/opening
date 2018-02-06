import ajax from './ajax'
// 设置根目录
const ROOT = '/audio'
// 获取列表
export const getAudioListApi = ({page = 1, pageSize = 10, suId = 0, type, name, saAttribute} = {}) => ajax.post(`${ROOT}/getAudio/${saAttribute}`, {
  page,
  pageSize,
  type,
  name,
  suId,
})
// 删除音频
export const delAudioApi = (id) => ajax.delete(`${ROOT}/del/${id}`)
// 获取音频详情
export const getAudioApi = (id) => ajax.get(`${ROOT}/get/${id}`)
// 保存
export const saveAudioApi = ({saId, suId = 0, svId, saTitle, saAutho, saUrl, saName, saAttribute, type, saDescribe, id, name, saPic}) => ajax.post(`${ROOT}/saveOrUpdate`, {
  saId, suId, svId, saTitle, saAutho, saUrl, saName, saAttribute, type, saDescribe, id, name, saPic,
})
