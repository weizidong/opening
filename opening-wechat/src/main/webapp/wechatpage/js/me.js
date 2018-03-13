mui.init()
goUrl('.mui-bar .mui-tab-item', function (that) {
    return that.getAttribute('href')
})
goUrl('.togo>a', function (that) {
    return that.getAttribute('href')
})
ajax('/rest/house/getMine', {
    type: 'GET',
    success: function (data) {
        var user = data.user
        var house = data.house
        mui('#name')[0].innerText = user.name
        mui('#headimgurl')[0].setAttribute('src', user.headimgurl)
        if (house) {
            mui('#phone')[0].innerText = user.phone
            mui('#roomNo')[0].innerText = house.roomNo
            mui('#salesTime')[0].innerText = moment(house.salesTime).format('YYYY-MM-DD HH:mm:ss')
            mui('#house')[0].style.display = 'block'
        } else {
            mui('.togo')[0].style.display = 'block'
        }
    }
})