goUrl('.mui-bar .mui-tab-item', function (that) {
    return that.getAttribute('href')
})
var mask;
mui('body').on('tap', '#plan_map', function () {
    mask = mui.createMask()
    mask.show()
    mui('.plan_map')[0].style.display = 'block'
})
mui('body').on('tap', '.plan_map', function () {
    mui('.plan_map')[0].style.display = 'none'
    mask && mask.close()
})
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

// 获取楼栋号
ajax('/rest/house/getBuilding', {
    type: 'GET',
    success: function (data) {
        var html = ''
        var html2 = ''
        data.forEach(function (i) {
            html += '<a class="mui-control-item" href="#item' + i + '" tid="' + i + '">' + i + '号楼</a>'
            html2 += '<div id="item' + i + '" class="mui-slider-item mui-control-content"><div class="mui-scroll-wrapper"><div class="mui-scroll"><ul class="mui-table-view"></ul></div></div></div>'
        })
        mui('.mui-slider .mui-slider-indicator')[0].innerHTML = html
        mui('.mui-slider-group')[0].innerHTML = html2
        mui.init()
        mui('.mui-scroll-wrapper').scroll({
            deceleration: 0.0005 //flick 减速系数，系数越大，滚动速度越慢，滚动距离越小，默认值0.0006
        })
        find(data[0])
    }
})
var list = {}

function find(tid) {
    var mask = mui.createMask(); //callback为用户点击蒙版时自动执行的回调；
    mask.show()
    mui('.loading')[0].style.display = 'block'
    ajax('/rest/house/find/' + tid, {
        type: 'GET',
        success: function (data) {
            list = {}
            data.forEach(function (item) {
                if (list[item.floorNo]) {
                    list[item.floorNo].push(item)
                } else {
                    list[item.floorNo] = [item]
                }
            })
            var html = ''
            for (var k in list) {
                html += '<li class="mui-table-view-cell mui-row"><div class="floor mui-badge">' + k + 'F</div>'
                list[k].forEach(function (item, idx) {
                    html += '<div class="mui-col-xs-4 mui-col-sm-4"><div class="mui-card" fid="' + k + '" idx="' + idx + '">' +
                        '<span class="mui-badge ' + (['mui-badge-primary', 'mui-badge-purple', ''][item.status]) + '">' + (['未售', '已售', '未推'][item.status]) + '</span>' +
                        '<div class="house mui-card-content ' + (['mui-badge-green', 'mui-badge-danger', ''][item.status]) + '">' +
                        '<div>房号：' + item.roomNo + '</div>' +
                        '<div>面积：' + item.area + '㎡</div>' +
                        '<div>单价：' + item.unitPrice + '元</div>' +
                        '<div>总价：' + (item.totalPrice / 10000) + '万</div></div></div></div>'
                })
                html += '</div></li>'
            }
            mui('#item' + tid + ' .mui-table-view')[0].innerHTML = html
            mui('.loading')[0].style.display = 'none'
            mask.close()
            mui('.mui-table-view-cell').off('tap', '.mui-card')
            mui('.mui-table-view-cell').on('tap', '.mui-card', function () {
                var fid = this.getAttribute('fid')
                var idx = this.getAttribute('idx')
                var item = list[fid][idx]
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
    })
}

// 获取楼层房间
mui('.mui-slider .mui-slider-indicator').on('tap', '.mui-control-item', function () {
    var tid = this.getAttribute('tid');
    find(tid)
})
document.querySelector('.mui-slider').addEventListener('slide', function (event) {
    var dom = mui('.mui-slider .mui-slider-indicator .mui-control-item:nth-child(' + (event.detail.slideNumber + 1) + ')')[0]
    var tid = dom.getAttribute('tid')
    find(tid)
});