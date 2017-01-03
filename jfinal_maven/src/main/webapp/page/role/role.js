/**
 * Created by abook23 on 2016/12/24.
 */
var roleArr = new Array();

function addPage() {
	layerOpen("/role/addPage");
}

function itemPage(roleId) {
	layerOpen("/role/info?roleId=" + roleId);
}

function add(id) {
	$.ajax({
		type: "post",
		url: "/role/add",
		async: false,
		data: $("#InfoFrom").serialize(),
		success: function(data) {
			layerMsg(obj.msg, 500, function() {
				parent.layer.closeAll();
				common_ajax.ajaxFormMainPanel('/role/main');
			})
		}
	});
}

function findRoleByPid(name, pId, describe) {
	roleBreadcrumb(pId, name, describe);
	$.get("/role/findRoleByPid?pId=" + pId, function(data) {
		var html = '';
		$.each(data, function(i, item) {
			var statusStr;
			if(item.status == 1) {
				statusStr = "正常";
			} else {
				statusStr = "未激活";
			}
			html += '<tr onclick=findRoleByPid("' + item.name + '","' + item.roleId + '")>';
			html += '<td>' + item.name + '</td>';
			html += '<td>' + item._describe + '</td>';
			html += '<td>' + statusStr + '</td>';
			html += '</tr>';
		});
		$('#roleTable').find('tbody').html(html);
		findUserByeRoleId(pId);
	});
}

function roleBreadcrumb(roleId, name, describe) {
	var _roleArr = new Array();
	if(roleArr.length == 0) {
		var roleInfo = $('#roleInfo').text().split(",");
		roleArr[0] = {
			roleId: roleInfo[0],
			name: roleInfo[1],
			describe: roleInfo[2]
		};
	}
	for(i = 0; i < roleArr.length; i++) {
		if(roleArr[i].roleId == roleId) {
			break;
		} else {
			_roleArr[i] = {
				roleId: roleArr[i].roleId,
				name: roleArr[i].name,
				describe: roleArr[i].describe
			};
		}
	}
	roleArr = _roleArr;
	roleArr[roleArr.length] = {
		roleId: roleId,
		name: name,
		describe: describe
	};

	var html = '<ol class ="breadcrumb">';
	for(i = 0; i < roleArr.length; i++) {
		if(i == roleArr.length - 1)
			html += '<li><strong>' + roleArr[i].name + '</strong></li>';
		else
			html += '<li><a onclick=findRoleByPid("' + roleArr[i].name + '","' + roleArr[i].roleId + '","' + roleArr[i].describe + '")>' + roleArr[i].name + '</a></li>';
	}
	html += '</ol>';
	//	html += '<p>' + describe + '</p>';
	$('#roleBreadcrumb').empty().html(html);
}

function findUserByeRoleId(roleId) {
	$.get("/user/findByRoleId?roleId=" + roleId, function(data) {
		var html = '';
		$.each(data, function(i, item) {
			html +=
				'<tr onclick=userInfo("' + item.userId + '")>' +
				'<td>' + item.name + '</td>' +
				'<td>' + item.phone + '</td>' +
				'<td>' + item.email + '</td>' +
				'</tr>';
		});
		$('#userInfoTable').find('tbody').html(html);
	});
}

function userInfo(userId) {
	layerOpen("/user/info?id=" + userId + "&type=0");
}

function selectOption(select, url) {
	$.ajax({
		type: "get",
		url: url,
		async: true,
		success: function(data) {
			var html = '';
			$.each(data, function(i, item) {
				html += '<option value="'+item.roleId+'">' + item.name + '</option>';
			});
			$(select).html(html);
		}
	});
}
