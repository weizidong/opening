// WebSocket 封装
export default {
  debug: true,// 开启debug模式
  socket: null,
  url: null,// 连接地址
  waitTime: 500,// 重连等待时间
  reConnect: true,// 是否断线重连
  reConnectTime: 10, // 断线重连次数
  loop: true,// 是否保持心跳连接
  loopData: '@heart',// 心跳包数据
  loopTime: 10000,// 心跳间隔时间
  connectTime: 0,// 连接次数
  loopTimer: null,// 心跳定时器
  onMessage: (msg) => { // 消息监听
    this.log(`接收到 WebSocket 消息：${msg}`)
  },
  log(...msg) {
    if (this.debug) {
      console.log(...msg)
    }
  },
  init(url, {waitTime = 500, reConnect = true, loop = true, loopData = '@heart', loopTime = 10000, reConnectTime = 10, debug = false} = {}) {
    this.waitTime = waitTime
    this.reConnect = reConnect
    this.loop = loop
    this.loopData = loopData
    this.loopTime = loopTime
    this.reConnectTime = reConnectTime
    this.debug = debug
    this.open(url)
    return this
  },
  open(url) {
    if (this.socket) {
      this.close()
    }
    if (!url) {
      throw  new Error(`WebSocket 连接地址：${url} 错误！`)
    }
    this.url = url
    this.connectTime++
    this.socket = new WebSocket(url)
    this.socket.onmessage = (event) => {
      const data = JSON.parse(event.data)
      this.log(`WebSocket 接收到消息：${data}`)
      if (data === this.loopData) {
        this.sendLoop()
      } else {
        this.onMessage(data)
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
