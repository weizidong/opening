mui.init()
mui('body').on('tap', '#agree', function () {
    location.href = 'house.html'
})
mui('body').on('tap', '#refuse', function () {
    wx.closeWindow()
})