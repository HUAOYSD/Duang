<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<body class="easyui-layout">
	<div class="body" id="body_rolelist" region="center" >	
		  <!-- 查询条件区域 -->
		  <!-- 数据表格区域 -->
		  <table id="rolelist" style="table-layout:fixed;" ></table>
	 	  <!-- 表格顶部工具按钮 -->
		  <div id="tt_toolbar_rolelist">
		      <a id="add_btn_rolelist" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
		      <a id="edit_btn_rolelist" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a> 
	          <a id="del_btn_rolelist" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
	  	  </div>
	</div>
	<script type="text/javascript" src="<%=path %>/ui/sys/sysrole/sysRoleList.js"></script>
</body>