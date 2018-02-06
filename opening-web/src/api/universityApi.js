import ajax from './ajax'
// 设置根目录
const ROOT = '/university'
// 获取列表
export const getUniversityListApi = ({page = 1, pageSize = 10, sname, provice} = {}) => ajax.post(`${ROOT}/getAll`, {page, pageSize, sname, provice})
// 删除
export const delUniversityApi = (id) => ajax.delete(`${ROOT}/delScool/${id}`)
// 获取详情
export const getUniversityApi = (id) => ajax.get(`${ROOT}/getSchool/${id}`)
// 创建/university/saveSchool
export const createUniversityApi = ({ssId, ssName, ssProvice, ssType, ssPic, ssNum, ssLogo, ssUrl, scBlock, spId, sbId} = {}) => ajax.post(`${ROOT}/saveSchool`, {ssId, ssName, ssType, ssProvice, ssPic, ssNum, ssLogo, ssUrl, scBlock, spId, sbId})
// 修改
export const updateUniversityApi = ({ssId, ssName, ssProvice, ssType, ssPic, ssNum, ssLogo, ssUrl, scBlock, spId, sbId} = {}) => ajax.post(`${ROOT}/saveSchool`, {ssId, ssName, ssType, ssPic, ssProvice, ssNum, ssLogo, ssUrl, scBlock, spId, sbId})
// 获取分组
export const getCateApi = ({sbId, sbName} = {}) => ajax.get(`${ROOT}/getAllBlock`, {sbId, sbName})
// 高校分组
export const saveCateApi = (data) => ajax.post(`${ROOT}/saveBlock`, data)
// 删除高校分组
export const delCateApi = (id) => ajax.delete(`${ROOT}/delBlock/${id}`)
// 获取省份
export const getProviceApi = (data) => ajax.get(`${ROOT}/getAllProvice`, data)
let cateList
export const setcateListApi = async () => {
  if (!cateList) {
    cateList = await ajax.get(`${ROOT}/getAllBlock`)
  }
  return Promise.resolve(cateList)
}
