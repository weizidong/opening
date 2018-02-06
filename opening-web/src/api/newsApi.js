import ajax from './ajax'
// 设置根目录
const ROOT = '/news'
// 获取资讯列表
export const getNewsListApi = ({page = 1, pageSize = 10, uaType, name} = {}) => ajax.post(`${ROOT}/queryNewsList/${uaType}`, {page, pageSize, name})
// 删除资讯
export const delNewsApi = (id) => ajax.delete(`${ROOT}/delByNewsId/${id}`)
// 获取资讯详情
export const getNewsApi = (id) => ajax.get(`${ROOT}/get/${id}`)
// 创建资讯
export const createNewsApi = ({snTitle, snAuthor, snPic, snPic2, snPic3, snClass, uaType, snBody} = {}) => ajax.post(`${ROOT}/addOrUpdateNews`, {snTitle, snAuthor, snPic, snPic2, snPic3, snClass, uaType, snBody})
// 修改资讯
export const updateNewsApi = ({snTitle, snAuthor, snPic, snPic2, snPic3, snClass, uaType, snBody} = {}) => ajax.post(`${ROOT}/addOrUpdateNews`, {snTitle, snAuthor, snPic, snPic2, snPic3, snClass, uaType, snBody})
