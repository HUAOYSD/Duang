<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<body class="easyui-layout">
	<div class="body" id="body_selectMemberMiddle" region="center" style="border:0px;" >	
		  <!-- 数据表格区域 -->
		 <table id="select_memberMiddle" style="table-layout:fixed;" ></table>
		 <div style="margin-top:40px;margin-right:40px;" align="right">
			<button type="button" id="btn_select_memberMiddle" class="btn btn-primary" id="chose_loanlist_scale">确定</button>
		 </div>
	</div>
	<script type="text/javascript">
		var scaleId="${scaleId}";
	</script>
	<script type="text/javascript" src="<%=path %>/ui/sys/membermiddle/selectMemberMiddle.js"></script>
</body>