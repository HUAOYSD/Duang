<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<body>
	<script type="text/javascript"
		src="<%=request.getContextPath() %>/ui/system/sysPowerList.js"></script>
	<table id="powerToolbar" style="width: 100%">
		<tr style="float: left">
			<td><a href="javascript:void();" onclick="openAddPowerView();"
				class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加权限</a>
			</td>
		</tr>
	</table>
	<div style="height: 94.3%;">
		<table id="powerList"></table>
	</div>
	<div id="addPowerView" class="easyui-dialog" closed="true"></div>
	<div id="editPowerView" class="easyui-dialog" closed="true"></div>

	<script type="text/javascript">
		//$("body").height($(document).height());
	</script>
</body>