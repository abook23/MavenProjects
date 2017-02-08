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
			parent.layer.closeAll();
			common_ajax.ajaxFormMainPanel('/menu/main');
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

function menu_update(menuId, status) {
	if(status == 1) {
		status = 0;
	} else if(status == 0) {
		status = 1;
	}
	$.post("/menu/update", {
		menuId: menuId,
		status: status
	}, function(obj) {
		debugger;
		table.ajax.reload(); //table 在main.html 里
	});
}

function menu_del(menuId) {
	layerOpenDel("你确定要删除吗？", function() {
		$.post("/menu/delete", {
			menuId: menuId,
			status: status
		}, function(obj) {
			debugger;
			table.ajax.reload(); //table 在main.html 里
		});
	});
}