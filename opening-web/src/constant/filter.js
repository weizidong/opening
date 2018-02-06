import moment from 'moment'
import { setVipListApi, getAuthorListApi } from '../api/userApi'
import { setcateListApi } from '../api/universityApi'
/**
 * 格式化过滤器
 * @param r 行
 * @param c 列
 * @param val 值
 */
// 日期格式化
export const dateFilter = (r, c, val) => val ? moment(val).format('YYYY-MM-DD') : '无'
// 时间格式化
export const timeFilter = (val, format = 'MM-DD') => val ? moment(val).format(format) : '无'
// 时间格式化
export const dateTimeFilter = (r, c, val) => val ? moment(val).format('YYYY-MM-DD HH:mm') : '无'
// 时间间隔格式化
export const diffFilter = (val, type = 'days') => {
  const diff = val ? moment(val).diff(Date.now(), type) : 0
  return diff > 0 ? diff : 0
}
// 默认显示 ‘无’
export const defValFilter01 = (r, c, val) => val || '无'
// 默认值显示 ‘未知’
export const defValFilter02 = (r, c, val) => val || '未知'
// 默认值显示 0
export const defValFilter03 = (r, c, val) => val || 0
// 货币显示格式化
export const currencyFilter = (r, c, val) => '￥' + (val * 1 ? (val / 100).toFixed(2) : '0.00')
// 用户身份
export const defValFilter04 = (r, c, val) => ['未定义', '校长', '专家', '老师', '学生'][val]
// 院校级别
export const defValFilter05 = (r, c, val) => ['未知', '本科', '专科', '高中'][val]
// 音乐VIP类别
// export const defValFilter06 = (r, c, val) => {
//   if (val < -1 || val === -1) {
//     return '试听音频'
//   } else {
//     return ['全部', '老高考高一', '老高考高三', '老高考高二', '新高考高一', '新高考高二', '新高考高三', '大学'][val] || '未知'
//   }
// }
// 服务类型
let vipMap
export const serviceTypeFilter = (r, c, val) => {
  if (!vipMap) {
    vipMap = {'-1': '试听', 0: '全部'}
    setVipListApi().then(list => list.forEach(({id, goodsname}) => {
      vipMap[id] = goodsname
    }))
  }
  return vipMap[val] || '未知'
}
// 分组
let cateMap
export const cateTypeFilter = (r, c, val) => {
  if (!cateMap) {
    cateMap = {}
    setcateListApi().then(list => list.forEach(({sbId, sbName}) => {
      cateMap[sbId] = sbName
    }))
  }
  return cateMap[val] || '未分组'
}
// 发布作者
let autMap
export const autTypeFilter = (r, c, val) => {
  if (!autMap) {
    autMap = {}
    getAuthorListApi({}).then(list => list.forEach(({id, name}) => {
      autMap[id] = name
    }))
  }
  return autMap[val] || '未知'
}
// 年级
export const gradeTypeFilter = (r, c, val) => ['未选择', '小一', '小二', '小三', '小四', '小五', '小六', '小七', '初一', '初二', '初三', '初四', '高一', '高二', '高三', '高四', '大学', '新高考高一', '新高考高二', '新高考高三'][val] || '未选择'
// 是否是vip
export const isVip = (r, c, val) => ['未知', 'vip会员', '普通会员'][val]
// 性别
export const sexyFilter = (r, c, val) => ['未知', '男', '女'][val] || '未知'
// 发布人
export const peopleFilter = (r, c, val) => ['小升'][val] || '未知'
// 服务类型
export const questionTypeFilter = (r, c, val) => ['升学规划', '身心健康', '学习技巧', '报考方面'][val] || '未知'
// 公告类型
export const noticeTypeFilter = (r, c, val) => ['公告', '推送'][val]
// 是否被预约
export const subscribeFilter = (r, c, val) => ['未预约', '已预约'][val] || '未预约'
// 退款状态
export const refundFilter = (r, c, val) => ['待处理', '同意', '拒绝'][val] || '待处理'
// 审核状态
export const auditFilter = (r, c, val) => val === 2 ? '已审核' : '未审核'
// 深度级别
export const depthFilter = (r, c, val) => ['', '一级', '二级', '三级', '四级', '五级', '六级'][val] || '未知'
// 分享渠道
export const shareModeFilter = (r, c, val) => ['其他', '微信好友', '微信朋友圈', 'QQ好友', 'QQ空间', 'QQ群', '心理微博'][val] || '未知'
// 奖励类型
export const rewardTypeFilter = (r, c, val) => ['未知', '首次注册奖励', '分享奖励', '推荐人才奖励', '邀请人注册奖励', '简历完善简历'][val] || '未知'
// 奖励方式
export const rewardWayFilter = (r, c, val) => ['未知', '余额充值', '现金', '礼品'][val] || '未知'
// 奖励方式
export const rewardStatusFilter = (r, c, val) => ['发放异常', '未发放', '已发放'][val] || '未知'
// 关注来源
export const attentionSourceFilter = (r, c, val) => ['未知', '系统匹配', '搜索匹配'][val] || '未知'
// 融资状态
export const rzValFilter = (val) => ['未融资', '天使轮', 'A轮', 'B轮', 'C轮', 'D轮', 'E轮', 'F轮', '其他'][val]
// 状态计算,0:未开始，1:进行中，2：已结束
export const statusFilter = (starttime, endtime) => Date.now() < starttime ? 0 : (starttime <= Date.now() && Date.now() <= endtime) ? 1 : 2
