import ajax from './ajax'
// 设置根目录
const ROOT = '/article'
// 获取文章列表
export const getArticleListApi = ({page = 1, pageSize = 10, uaType, name} = {}) => ajax.post(`${ROOT}/getArticleList/${uaType}`, {page, pageSize, name, uaType})
// 删除文章
export const delArticleApi = (id) => ajax.delete(`${ROOT}/delByArt/${id}`)
// 获取文章详情
export const getArticleApi = (id) => ajax.get(`${ROOT}/get/${id}`)
// 修改/创建广告
export const addArtArticleApi = ({uaTitle, uaAuthor, uaClass, uaPic, uaType, uaBody, uaId} = {}) => ajax.post(`${ROOT}/addArt`, {uaTitle, uaAuthor, uaClass, uaPic, uaType, uaBody, uaId})
