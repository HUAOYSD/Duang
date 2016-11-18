<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<body class="easyui-layout">
	<div class="body" id="body_award" region="center" style="border:0px;" >	
		  <!-- 数据表格区域 -->
		  <table id="award" style="table-layout:fixed;" ></table>
	 	  <!-- 表格顶部工具按钮 -->
		  <div id="tt_toolbar_award">
		      <a id="add_btn_award" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
		      <a id="edit_btn_award" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a> 
	  	  </div>
	</div>
	<script type="text/javascript" src="<%=path %>/ui/sys/award/award.js"></script>
</body>