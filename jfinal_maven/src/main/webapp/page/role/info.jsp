<link href="/css/style.css" rel="stylesheet">

<body>
	<div class="row wrapper border-bottom white-bg page-heading">
		<div class="col-sm-4">
			<h2>角色详情</h2>
			<a id="roleInfo" style="display: none;">${role.roleId},${role.name},${role._describe}</a>
			<div id="roleBreadcrumb">
				<ol class="breadcrumb">
					<li>
						<a href="index.html">${role.name}</a>
					</li>
					<li>
						<strong>详情</strong>
					</li>
				</ol>
			</div>

		</div>
		<div class="col-sm-offset-6 col-sm-2">
			<button type="button" class="btn btn-primary btn-sm">修改</button>
			<button type="button" class="btn btn-warning btn-sm">删除</button>
		</div>
	</div>
	<div style="margin-bottom: 20px;"></div>
	<div class="row">
		<div class="col-md-offset-1 col-sm-5">
			<table id="roleTable" class="table table-hover">
				<thead>
					<tr>
						<th>名称</th>
						<th>描述</th>
						<th>状态</th>
					</tr>
				</thead>
				<tbody>
					<#list roles as x>
						<tr onclick="findRoleByPid('${x.name}','${x.roleId}','${x._describe}')">
							<td>${x.name}</td>
							<td>${x._describe}</td>
							<td>
								<#if x.status==1>
									正常
									<#else>
										未激活
								</#if>
							</td>
						</tr>
					</#list>
				</tbody>
			</table>
		</div>
		<div class="col-sm-5">
			<table id="userInfoTable" class="table table-hover">
				<thead>
					<tr>
						<th>名称</th>
						<th>描述</th>
						<th>状态</th>
					</tr>
				</thead>
				<tbody>
					<#list userInfos as x>
						<tr ondblclick="userInfo('${x.userId}')">
							<td>${x.name}</td>
							<td>${x.phone}</td>
							<td>${x.email}</td>
						</tr>
					</#list>
				</tbody>
			</table>
		</div>
	</div>
	<script>
		roleArr = new Array();
	</script>
</body>