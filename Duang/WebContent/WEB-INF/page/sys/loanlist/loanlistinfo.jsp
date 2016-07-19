<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<body class="easyui-layout">
	<div class="body" id="body_loanlistinfo" region="center" style="border:0px;" >	
		  <!-- 查询条件区域 -->
		  <input type="hidden" value="${scaleid }" id="scaleid" >
		  <!-- 数据表格区域 -->
		  <table id="loanlistinfo" style="table-layout:fixed;" ></table>
	 	  <!-- 表格顶部工具按钮 -->
	</div>
	<script type="text/javascript" src="<%=path %>/ui/sys/loanlist/loanlistinfo.js"></script>
</body>