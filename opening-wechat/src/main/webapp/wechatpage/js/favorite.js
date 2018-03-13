mui.init()
// 获取开始时间
var timmer;
ajax('/rest/system/get/START_TIME', {
    type: 'GET',
    success: function (time) {
        setTime(time)
    }
})

function setTime(time) {
    var now = new Date().getTime()
    if (now >= time) {
        timmer && clearInterval(timmer)
        mui('#time')[0].innerText = '本次选房已开始...'
        return
    }
    var d = moment(time).diff(now, 'days')
    if (d > 10) {
        mui('#time')[0].innerText = moment(time).format('MM月DD号 HH:mm:ss开始')
        return
    }
    var h = moment(time).diff(now, 'hours') - d * 24
    var m = moment(time).diff(now, 'minutes') - d * 24 * 60 - h * 60
    var s = moment(time).diff(now, 'seconds') - d * 24 * 60 * 60 - h * 60 * 60 - m * 60
    mui('#time')[0].innerText = d + '天' + h + '小时' + m + '分' + s + '秒'
    if (!timmer) {
        timmer = setInterval(function () {
            setTime(time)
        }, 1000)
    }
}

goUrl('.mui-bar .mui-tab-item', function (that) {
    return that.getAttribute('href')
})

// 获取我的收藏
getFavorite()
var list;

function getFavorite() {
    ajax('/rest/house/getFavorite', {
        type: 'GET',
        success: function (data) {
            list = data
            var html = ''
            if (!list || list.length < 1) {
                html += '<li class="mui-table-view-cell mui-text-center">暂无收藏</li>'
            } else {
                list.forEach(function (item, idx) {
                    html += '<li class="mui-table-view-cell">' +
                        '                        <a class="mui-row">' +
                        '                            <div class="mui-col-xs-6 mui-col-sm-6">房号：' + item.roomNo + '</div>' +
                        '                            <div class="mui-col-xs-6 mui-col-sm-6">状态：<span class="' + (['green', 'red', ''][item.status]) + '">' + ['未售', '已售', '未推'][item.status] + '</span></div>' +
                        '                            <div class="mui-col-xs-6 mui-col-sm-6">面积：' + item.area + '㎡</div>' +
                        '                            <div class="mui-col-xs-6 mui-col-sm-6">单价：' + item.unitPrice + '元</div>' +
                        '                            <div class="mui-col-xs-6 mui-col-sm-6">总价：' + (item.totalPrice / 10000) + '万</div>' +
                        '                            <div class="mui-col-xs-6 mui-col-sm-6">户型：三室两厅</div>' +
                        '                            <div class="mui-col-xs-6 mui-col-sm-6">收藏数：' + item.count + '</div>' +
                        '                        </a>' +
                        '                        <button type="button" class="mui-btn btn1 ' + (item.status === 0 ? 'mui-btn-primary' : 'mui-btn-outlined') + '" idx="' + idx + '">认购</button>' +
                        '                        <button type="button" class="mui-btn btn2 mui-btn-success" idx="' + idx + '">取消收藏</button>' +
                        '                    </li>'
                })
            }
            mui('.mui-table-view')[0].innerHTML = html
            setBtnSuccess()
            purchase()
        }
    })
}

// 取消收藏
function setBtnSuccess() {
    mui('.mui-table-view-cell').off('tap', '.mui-btn-success')
    mui('.mui-table-view-cell').on('tap', '.mui-btn-success', function () {
        var idx = this.getAttribute('idx')
        var item = list[idx]
        ajax('/rest/house/delFavorite/' + item.id, {
            type: 'PUT',
            success: function () {
                mui.toast('取消收藏【' + item.roomNo + '】成功！')
                getFavorite()
            }
        })
    })
}

// 购买
function purchase() {
    mui('.mui-table-view-cell').off('tap', '.mui-btn-primary')
    mui('.mui-table-view-cell').on('tap', '.mui-btn-primary', function () {
        var idx = this.getAttribute('idx')
        var item = list[idx]
        if (item.status !== 0) {
            return
        }
        mui.confirm('<div class="mui-text-left">\
								<div class="mui-input-row"><label>房号：</label><div>' + item.roomNo + '</div></div>\
								<div class="mui-input-row"><label>户型图：</label><div>户型 </div></div>\
								<div class="mui-input-row"><label>户型：</label><div>三室两厅 </div></div>\
								<div class="mui-input-row"><label>状态：</label><div>' + (['未售', '已售', '未推'][item.status]) + ' </div></div>\
								<div class="mui-input-row"><label>单价：</label><div>' + item.unitPrice + '元 </div></div>\
								<div class="mui-input-row"><label>面积：</label><div>' + item.area + '㎡ </div></div>\
								<div class="mui-input-row"><label>总价：</label><div>' + (item.totalPrice / 10000) + '万 </div></div>\
								<div class="mui-input-row"><div class="mui-switch mui-switch-mini" id="mySwitch" style="float: left"><div class="mui-switch-handle"></div></div>\
								<div>本人已阅读并同意<a href="agreement.html" id="agreementHtml">微信选房协议</a></div></div>\
							</div>', '房产信息', ['取消', '收藏', '认购'],
            function (e) {
                if (e.index === 2) {
                    var agree = document.getElementById("mySwitch").classList.contains("mui-active");
                    if (!agree) {
                        mui.toast('认购房产需要同意微信选房协议！')
                    } else {
                        ajax('/rest/house/purchase/' + item.id, {
                            type: 'PUT',
                            success: function (data) {
                                mui.alert('<div class="mui-text-left">\
                                                    <div class="mui-input-row mui-text-center">认购成功！</div>\
                                                    <div class="mui-input-row"><label>客户名称：</label><div>' + data.name + '</div></div>\
                                                    <div class="mui-input-row"><label>认筹号：</label><div>' + data.phone + '</div></div>\
                                                    <div class="mui-input-row"><label>成交单位：</label><div>' + data.room + '</div></div>\
                                                    <div class="mui-input-row"><label>销控时间：</label><div>' + moment(data.time).format('YYYY-MM-DD HH:mmm:ss') + '</div></div>\
								            </div>', '提示', '确定',
                                    function (e) {
                                        location.href = 'me.html'
                                    })
                            },
                            error: function () {
                                mui.toast('认购房产【' + item.roomNo + '】失败！')
                            }
                        })
                    }
                } else if (e.index === 1) {
                    ajax('/rest/house/favorite/' + item.id, {
                        type: 'PUT',
                        success: function () {
                            mui.toast('收藏房源【' + item.roomNo + '】成功！')
                        }, error: function () {
                            mui.toast('收藏房源【' + item.roomNo + '】失败！')
                        }
                    })
                }
            })
        mui('.mui-switch')['switch']()
        goUrl('#agreementHtml', 'agreement.html')
    })
}