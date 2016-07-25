<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<body>
	<script type="text/javascript"
		src="<%=request.getContextPath() %>/ui/sys/sysrole/sysRoleList.js"></script>
	<table id="roleToolbar" style="width: 100%">
		<tr style="float: left">
			<td><a href="javascript:void();" onclick="openAddRoleView();"
				class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加角色</a>
			</td>
		</tr>
	</table>
	<div style="height: 94.3%;">
		<table id="RoleList"></table>
	</div>
	<div id="addRoleView" class="easyui-dialog" closed="true"></div>
	<div id="editRoleView" class="easyui-dialog" closed="true"></div>
	<div id="powerToRoleView" class="easyui-dialog" closed="true"></div>
	<script type="text/javascript">
		$("body").height($(document).height());
	</script>
</body>