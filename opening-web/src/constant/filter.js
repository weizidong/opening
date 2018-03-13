import moment from 'moment'

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
// 课时计算
export const hourFilter = ({startTime, endTime}) => moment(endTime).diff(moment(startTime), 'hours') || 0
// 默认显示 ‘无’
export const defValFilter01 = (r, c, val) => val || '无'
// 默认值显示 ‘未知’
export const defValFilter02 = (r, c, val) => val || '未知'
// 默认值显示 0
export const defValFilter03 = (r, c, val) => val || 0
// 审核状态
export const statusFilter01 = (r, c, val) => ['未知', '待审核', '通过', '拒绝'][val] || '未知'
// 货币显示格式化
export const currencyFilter = (r, c, val) => val * 1 ? (val * 1).toFixed(2) : '0.00'
// 身份状态
export const defValFilter04 = (r, c, val) => ['未知', '学生', '老师'][val]
// 课程计价方式
export const courseTypeFilter = (r, c, val) => ['小时', '天', '一周', '月'][val]
// 广告类型
export const bannerTypeFilter = (r, c, val) => ['APP首页广告', '引导页广告'][val] || '未知'
// 公告类型
export const noticeTypeFilter = (r, c, val) => ['公告', '推送'][val]
// 是否被预约
export const subscribeFilter = (r, c, val) => ['未预约', '已预约'][val] || '未预约'
// 退款状态
export const refundFilter = (r, c, val) => ['待处理', '已同意', '已拒绝'][val] || '已同意'
// 审核状态
export const auditFilter = (r, c, val) => val === 2 ? '已审核' : '未审核'
// 提现状态
export const cashFilter = (r, c, val) => ['未知', '待审核', '审核成功', '审核失败'][val] || '未知'
// 发课状态
export const courseFilter = (r, c, val) => ['等待审核', '发布成功', '发布失败'][val] || '发布失败'
// 课程类型,0 : 线下课程 1：直播课程 2：视屏课程
export const courseTypeFilter02 = (r, c, val) => ['线下课程', '直播课程', '视频课程'][val] || '未知'
// 支付方式
export const payTypeFilter = (r, c, val) => ['其他', '微信', '支付宝', '余额支付', '银行', '其他'][val] || '其他'
// 状态计算,0:未开始，1:进行中，2：已结束
export const statusFilter = (starttime, endtime) => Date.now() < starttime ? 0 : (starttime <= Date.now() && Date.now() <= endtime) ? 1 : 2
// 预约课程状态
export const subStatusFilter = (r, c, val) => ['待支付', '已支付', '支付失败', '取消预约', '已完成', '退款中', '已退款', '拒绝退款', '上课中', '已评价'][val] || '未知'
