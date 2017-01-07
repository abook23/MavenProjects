/**
 * Created by abook23 on 2016/12/31.
 */

function addPage() {
	layerOpen("/page/menu/add.html");
}

function add() {
	$.ajax({
		type: "post",
		url: "/menu/add",
		data: $('#addForm').serialize(),
		async: true,
		success: function(data) {

		}

	});
}

function itemPage(id) {
	layerOpen("/page/menu/edit.html");
}

function selectOption(select, url) {
	$.ajax({
		type: "get",
		url: url,
		async: true,
		success: function(data) {
			var html = '<option value="0">一级菜单</option>';
			$.each(data, function(i, item) {
				html += '<option value="' + item.menuId + '">' + item.name + '</option>';
			});
			$(select).html(html);
		}
	});
}

/**
 *
 * @param {String} url
 * @param {Function} cancel
 */
function layerOpen(url, cancel) {
	var html = common_ajax.ajaxFunc(url);
	layer.open({
		type: 1,
		shade: false,
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
