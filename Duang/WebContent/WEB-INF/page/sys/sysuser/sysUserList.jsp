<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<body>
	<table id="sysUserToolbar" style="width: 100%">
		<tr style="float: left">
			<td>
				<a href="javascript:void(0);"
				onclick="openAddSysUserView();" class="easyui-linkbutton"
				data-options="iconCls:'icon-add'">添加用户</a>
			</td>
		</tr>
	</table>
	<div style="height: 94.3%;">
		<table id="sysUserList"></table>
	</div>
	<div id="addSysUserView" class="easyui-dialog" closed="true"></div>
	<div id="editSysUserView" class="easyui-dialog" closed="true"></div>
	<div id="editPasswordView" class="easyui-dialog" closed="true"></div>
	<script type="text/javascript"
		src="<%=request.getContextPath() %>/ui/sys/sysuser/sysUserList.js"></script>
	<script type="text/javascript">
		$("body").height($(document).height());
	</script>
</body>