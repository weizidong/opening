import ajax from './ajax'
// 设置根目录
const ROOT = '/expert'
// 获取最新回答问题
export const getNewListApi = ({page = 1, pageSize = 10, name} = {}) => ajax.post(`${ROOT}/getTopicList`, {page, pageSize, name})
// 获取未回答问题
export const getWaitListApi = ({page = 1, pageSize = 10, name} = {}) => ajax.post(`${ROOT}/getTopicIBlack`, {page, pageSize, name})
// 获取已回答问题
export const getAlreadyListApi = ({page = 1, pageSize = 10, name} = {}) => ajax.post(`${ROOT}/getTopicBlack`, {page, pageSize, name})
// 删除问题
export const delquestionApi = (id) => ajax.delete(`${ROOT}/delTopic/${id}`)
// 获取问题详情
export const getQuestionApi = (id) => ajax.get(`${ROOT}/get/${id}`)
// 获取查看指定问题回复列表
export const getAnswerApi = ({page = 1, pageSize = 10, id} = {}) => ajax.post(`${ROOT}/backList/${id}`, {page, pageSize})
// 删除回复
export const delAnswerApi = (id) => ajax.delete(`${ROOT}/delBack/${id}`)
// 获取查看评论列表
export const getCommentApi = ({page = 1, pageSize = 10, id}) => ajax.post(`${ROOT}/commentList/${id}`, {page, pageSize})
// 删除评论
export const delCommentApi = (id) => ajax.delete(`${ROOT}/delComment/${id}`)
// 回复/修复问题
export const addQuestionApi = (data) => ajax.post(`${ROOT}/addBack`, data)
// 修改
export const updateQuestionApi = (data) => ajax.post(`${ROOT}/addBack`, data)
