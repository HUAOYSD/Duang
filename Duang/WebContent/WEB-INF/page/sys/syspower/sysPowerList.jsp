<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<body class="easyui-layout">
 	<div class="body" id="body_powerlist" region="center">	
		  <!-- 查询条件区域 -->
		  <!-- 数据表格区域 -->
		  <table id="powerlist" style="table-layout:fixed;" ></table>
	 	  <!-- 表格顶部工具按钮 --> 
		  <div id="tt_toolbar_powerlist">
		      <a id="add_btn_powerlist" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增权限</a>
	  	  </div>
	</div>
	<script type="text/javascript" src="<%=path %>/ui/sys/syspower/sysPowerList.js"></script>
</body>