/**
 * 公用的提示框封装
 */
import {Message, MessageBox} from 'element-ui'

/**
 * 统一配置
 * @type {{customClass: string, dangerouslyUseHTMLString: boolean, duration: number, showClose: boolean, center: boolean, onClose: (function())}}
 */
const def = {
  customClass: '',// 自定义类名
  dangerouslyUseHTMLString: false,// 是否将 message 属性作为 HTML 片段处理
  duration: 2000,// 显示时间, 毫秒。设为 0 则不会自动关闭
  showClose: true,// 是否显示关闭按钮
  center: false,// 文字是否居中
  onClose: () => {
    // 关闭时的回调函数, 参数为被关闭的 message 实例
  }
}
/**
 * 成功
 * @param message
 * @param opts
 */
export const success = (message = '成功！', opts = {}) => Message.success({message, ...def, ...opts})
/**
 * 警告
 * @param message
 * @param opts
 */
export const warning = (message = '警告！', opts = {}) => Message.warning({message, ...def, ...opts})
/**
 * 提示
 * @param message
 * @param opts
 */
export const info = (message = '提示！', opts = {}) => Message.info({message, ...def, ...opts})
/**
 * 错误
 * @param message
 * @param opts
 */
export const error = (message = '错误！', opts = {}) => Message.error({message, ...def, ...opts})

/**
 * 消息
 * @param message
 * @param title
 * @param type
 * @param opts
 */
export const msg = (message = '消息', title = '提示', type = 'success', opts = {}) => MessageBox(message, title, {type, ...opts})
/**
 * alert
 * @param message
 * @param title
 * @param type
 * @param opts
 */
export const alert = (message = 'alert', title = '提示', type = 'success', opts = {}) => MessageBox.alert(message, title, {type, ...opts})
/**
 * 确认框
 * @param message
 * @param title
 * @param type
 * @param opts
 */
export const confirm = (message = '确认框', title = '提示', type = 'success', opts = {}) => MessageBox.confirm(message, title, {type, ...opts})
/**
 * 弹出框
 * @param message
 * @param title
 * @param type
 * @param opts
 */
export const prompt = (message = '弹出框', title = '提示', type = 'success', opts = {}) => MessageBox.prompt(message, title, {type, ...opts})
