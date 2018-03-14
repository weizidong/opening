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
