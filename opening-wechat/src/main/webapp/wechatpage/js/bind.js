mui.init()
var id = getQuery().id
ajax('/rest/user/get/' + id, {
    type: 'GET',
    success: function(data) {
        if(data.openid) {
            location.href = 'house.html'
        }
        if(data) {
            mui('#name')[0].setAttribute('placeholder', data.name || '未知')
            mui('#phone')[0].setAttribute('placeholder', data.phone ? data.phone.replace(/^(\d{3})\d{4}(\d{4})$/, '$1****$2') : '未知')
        }
    }
})
mui('body').on('tap', '#bind', function() {
    var idNumber = mui('#idNumber')[0].value
    if(!/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(idNumber)) {
        mui.alert('请输入正确的身份证号码进行绑定！', '提示', '确定', function(e) {
            mui('#idNumber')[0].value = ''
        })
        return
    }
    ajax('/rest/user/bind', {
        data: {
            id: id,
            idNumber: idNumber
        },
        success: function(data) {
            location.href = 'agreement.html'
        },
        error: function() {
            mui('#idNumber')[0].value = ''
        }
    })
})