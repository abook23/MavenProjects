/**
 *
 * @param {String} url
 * @param {Function} cancel
 */
function layerOpen(url, cancel) {
	var html = common_ajax.ajaxFunc(url);
	layer.open({
		type: 1,
		shade: 0.1, //false
		title: false, //不显示标题
		content: html, //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		area: ['75%', '90%'], //宽高
		cancel: function() {
			if(cancel != null) {
				cancel();
			}
		}
	});
}

/**
 *
 * @param {String} msg
 * @param {Function} cancel
 */
function layerOpenDel(msg, cancel) {
	layer.confirm('删除提示<br>'+msg, {
		btn: ['删除'], //按钮
		title: false, //不显示标题
		time: 5000
	}, function() {
		if(cancel != null) {
			cancel();
		}
	});
}

/**
 *
 * @param {Object} msg
 * @param {Long} time
 * @param {Function} closeCallBack
 */
function layerMsg(msg, time, closeCallBack) {
	layer.msg(msg, {
		time: time //20s后自动关闭
	}, function() {
		//		parent.layer.closeAll();
		//		common_ajax.ajaxFormMainPanel('/role/main');
		if(closeCallBack != null)
			closeCallBack();
	});
}

function _selectOption(select, url) {
	$.ajax({
		type: "get",
		url: url,
		async: true,
		success: function(data) {
			var html = '';
			$.each(data, function(i, item) {
				html += '<option>' + item.name + '</option>';
			});
			$(select).html(html);
		}
	});
}