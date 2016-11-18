<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<body class="easyui-layout">
	<div class="body" id="body_awardActivityLevel" region="center" style="border:0px;" >	
		  <!-- 数据表格区域 -->
		  <table id="awardActivityLevel" style="table-layout:fixed;" ></table>
	 	  <!-- 表格顶部工具按钮 -->
		  <div id="tt_toolbar_awardActivityLevel">
		      <a id="add_btn_awardActivityLevel" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
		      <a id="edit_btn_awardActivityLevel" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		      <a id="del_btn_awardActivityLevel" class="easyui-linkbutton" iconCls="icon-20130408025545236_easyicon_net_30" plain="true">删除</a>  
	  	  </div>
	</div>
	<script type="text/javascript">
		var activityId = "${activityId}";
	</script>
	<script type="text/javascript" src="<%=path %>/ui/sys/award/awardActivityLevel.js"></script>
</body>