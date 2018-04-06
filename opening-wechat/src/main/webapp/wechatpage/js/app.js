function ajax(url, config) {
	config = config || {};
	// console.log('请求:', url)
	// console.log('方式:', config.type || 'POST')
	// console.log('参数:', config.data)
	mui.ajax(url, {
		data: config.data,
		dataType: 'json', //服务器返回json格式数据
		type: config.type || 'post', //HTTP请求类型
		timeout: 10000, //超时时间设置为10秒；
		headers: {
			'Content-Type': 'application/json'
		},
		success: function(data) {
			if(typeof data === 'string') {
				data = JSON.parse(data);
			}
			if(typeof data === 'string') {
				data = JSON.parse(data);
			}
			if(data.code !== 200) {
				// console.log('错误:', data)
				mui.alert(data.msg, '提示', '确定', function() {
					config.error && config.error(data.msg);
				})
			} else {
				// console.log('结果:', data.data)
				config.success && config.success(data.data);
			}
		},
		error: function(xhr, type, errorThrown) {
			mui.alert('网络发生异常，请重新加载...', '提示', '确定', function() {
				location.reload(true);
			});
			config.error && config.error();
		}
	});
}

function getQuery() {
	var search = location.search.substr(1).split(/[=&]/);
	var query = {};
	for(var i = 0; i < search.length; i += 2) {
		query[decodeURI(search[i])] = decodeURI(search[i + 1])
	}
	return query;
}

function pushState(param) {
	var query = getQuery();
	$.extend(query, param);
	var state = {
		title: document.title,
		url: location.origin + location.pathname + "?" + $.param(query, true)
	};
	window.history.pushState(state, state.title, state.url)
}

function replaceState(param) {
	var query = getQuery();
	$.extend(query, param);
	var state = {
		title: document.title,
		url: location.origin + location.pathname + "?" + $.param(query, true)
	};
	window.history.replaceState(state, state.title, state.url)
}

function goUrl(Selector, url) {
	mui('body').off('tap', Selector);
	mui('body').on('tap', Selector, function() {
		if(typeof url === 'string') {
			location.href = url
		} else {
			location.href = url(this)
		}
	})
}