// WebSocket 封装
const webSocket = {
  socket: null,
  url: null,
  debug: true,// 开启debug模式
  reConnect: true,// 是否断线重连
  waitTime: 500,// 重连等待时间
  loop: true,// 是否保持心跳连接
  loopData: '@heart',// 心跳包数据
  loopTime: 10000,// 心跳间隔时间
  loopTimer: null,// 心跳定时器
  listener: {},// 监听器
  onMessage(key, fn = (msg) => console.log(`接收到 WebSocket 消息：${msg}`)) {
    this.listener[key] = fn
  },
  log(...msg) {
    if (this.debug) {
      console.log(...msg)
    }
  },
  init({debug = false, waitTime = 500, loop = true, loopData = '@heart', loopTime = 10000} = {}) {
    this.waitTime = waitTime
    this.loop = loop
    this.loopData = loopData
    this.loopTime = loopTime
    this.debug = debug
    return this
  },
  open(url, reConnect = true) {
    if (this.socket) {
      this.close()
    }
    this.reConnect = reConnect
    if (!url) {
      throw  new Error(`WebSocket 连接地址：${url} 错误！`)
    }
    this.url = url
    this.socket = new WebSocket(url)
    this.socket.onmessage = (event) => {
      const msg = JSON.parse(event.data)
      this.log('WebSocket 接收到消息：', msg)
      if (msg === this.loopData) {
        this.sendLoop()
      } else {
        let {command, data} = msg
        for (let {key, fn} in this.listener) {
          if (command === key) {
            fn(data)
          }
        }
      }
    }
    this.socket.onopen = () => {
      this.log(`WebSocket 连接成功！目标：${this.url}`)
      this.loop && this.sendLoop()
    }
    this.socket.onclose = () => {
      this.log(`WebSocket 连接已关闭！目标：${this.url}`)
      this.loopTimer && clearTimeout(this.loopTimer)
      this.reConnect && this.open(this.url)
    }
    this.socket.onerror = (event) => {
      this.log(`WebSocket 连接错误！目标：${event}`)
      this.reConnect && this.open(this.url)
    }
  },
  close() {
    this.reConnect = false
    this.loopTimer && clearTimeout(this.loopTimer)
    this.socket && this.socket.close()
  },
  sendLoop() {
    if (this.loop) {
      this.log(`发送心跳数据${this.loopData}...`)
      this.loopTimer = setTimeout(() => this.send(this.loopData), this.loopTime)
    }
  },
  send(msg) {
    if (!msg) {
      throw  new Error(`发送的消息：${msg} 不能为空！`)
    }
    this.log('发送消息：', msg)
    if (!this.socket) {
      this.open(this.url)
      setTimeout(() => {
        this.send(msg)
      }, this.waitTime)
    } else if (!this.socket.readyState) {
      setTimeout(() => this.socket.send(msg), 500)
    } else {
      this.socket.send(msg)
    }
  }
}

export default webSocket.init({
  debug: false,// 开启debug模式
  waitTime: 500,// 重连等待时间
  loop: true,// 是否保持心跳连接
  loopData: '@heart',// 心跳包数据
  loopTime: 10000,// 心跳间隔时间
})
/**
 * TODO：使用说明
 */
// import webSocket from './webSocket'
//
// webSocket.open('ws://locahost:8081/websocket/{userId}', true)
// webSocket.onMessage('通知事件名称，不能重复唯一', (msg) => {
//   console.log('这是消息体', msg)
// })
