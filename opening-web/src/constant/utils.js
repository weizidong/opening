import {isPlainObject} from 'lodash'

/**
 * debug输出，统一关闭
 * @param a
 */
export const logDebug = (...a) => {
  if (process.env.NODE_ENV === 'development') {
    console.log(...a)
  }
}
/**
 * debug输出，统一关闭
 * @param a
 */
export const logInfo = (...a) => {
  if (process.env.NODE_ENV === 'development') {
    console.info(...a)
  }
}
/**
 * debug输出，统一关闭
 * @param a
 */
export const logError = (...a) => {
  if (process.env.NODE_ENV === 'development') {
    console.error(...a)
  }
}
/**
 * 类型断言
 * @param value
 * @param type
 * @returns {boolean}
 */
export const assertType = (value, type) => {
  let valid = false
  if (type === String) {
    valid = typeof value === 'string'
  } else if (type === Number) {
    valid = typeof value === 'number'
  } else if (type === Boolean) {
    valid = typeof value === 'boolean'
  } else if (type === Function) {
    valid = typeof value === 'function'
  } else if (type === Object) {
    valid = isPlainObject(value)
  } else if (type === Array) {
    valid = Array.isArray(value)
  } else {
    valid = value instanceof type
  }
  return valid
}
/**
 * 树结构转换为list
 * @param tree 树结构
 * @param filed 子节点字段
 * @param fn 格式化节点的方式
 * @returns {Array}
 */
export const tree2List = (tree, filed, fn = (item) => item) => {
  let res = []
  if (!tree || !tree.length) {
    return res
  }
  tree.forEach((it) => {
    res.push(fn(it))
    if (it[filed] && it[filed].length) {
      res = [...res, ...tree2List(it[filed], filed, fn)]
    }
  })
  return res
}
/**
 * coocki操作封装
 * @type {{set: (function(*, *, *)), get: (function(*)), delete: (function(*=))}}
 */
export const coocki = {
  set(name, value, days) {
    const d = new Date()
    d.setTime(d.getTime() + 24 * 60 * 60 * 1000 * days)
    window.document.cookie = `${name}=${value};path=/;expires=${d.toGMTString()}`
  },
  get(name) {
    const v = window.document.cookie.match(`(^|;) ?${name}=([^;]*)(;|$)`)
    return v ? v[2] : null
  },
  delete(name) {
    this.set(name, '', -1)
  },
}
