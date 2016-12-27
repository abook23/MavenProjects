<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JSP Page</title>
	</head>

	<body>
		<div class="row wrapper border-bottom white-bg page-heading">
			<div class="col-sm-4">
				<h2>角色详情</h2>
				<ol class="breadcrumb">
					<li>
						<a href="index.html">用户管理</a>
					</li>
					<li>
						<strong>详情</strong>
					</li>
				</ol>
			</div>
			<div class="col-sm-offset-6 col-sm-2">
				<button type="button" class="btn btn-primary btn-sm">修改</button>
				<button type="button" class="btn btn-warning btn-sm">删除</button>
			</div>
		</div>
		<div style="margin-bottom: 20px;"></div>
		<div class="row">
			<div class="col-md-offset-1 col-sm-5">
				<div>
					<h1>${role.name}</h1>
					<p>${role._describe}</p>
				</div>
				<table id="roleTable" class="table table-striped table-hover">
					<thead>
						<tr>
							<th>名称</th>
							<th>描述</th>
							<th>状态</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="role" items="roles">
							<tr>
								<td style="display: none;">${role.roleId}</td>
								<td>${role.name}</td>
								<td>${role._describe}</td>
								<td>${role.status}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="col-sm-5">
				<table id="roleTable" class="table table-striped table-hover">
					<thead>
						<tr>
							<th>名称</th>
							<th>描述</th>
							<th>状态</th>
						</tr>
					</thead>
					<tbody>
						<!--<#list userInfos as x>
						<tr>
							<td>${x.name}</td>
							<td>${x.phone}</td>
							<td>${x.email}</td>
						</tr>
					</#list>-->
						<c:forEach var="u" items="userInfos">
							<tr>
								<td>${u.userId}</td>
								<td>${u.phone}</td>
								<td>${u.email}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</body>

</html>