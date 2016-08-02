<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<body class="easyui-layout">
	<div id="body" region="center" >
	  <!-- 查询条件区域 -->
	  <div id="search_area" >
	    <div id="conditon">
			<form id="queryInvestProForm" method="post">
				  <table border="0">
					<tr>
					  	<td><span class="investPro-search">总名称：</span></td>
					  	<td ><input  name="nameZh" id="nameZh"   /></td>
					 	<td>&nbsp;<span class="investPro-search">名称：</span></td>
					  	<td><input  name="name" id="name"  /></td>
					  <td>
						  <a  id="submitInvest_btn" class="easyui-linkbutton my-search-button" iconCls="icon-2012092109942" plain="true">查询</a>
						  <a  onclick="javascript:c$.clearForm(document.getElementById('queryInvestProForm'))" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
					  </td>
					</tr>
				  </table>
			</form>
	    </div>
	    <span id="openOrClose">111</span>
	  </div>
	  <!-- 数据表格区域 -->
	  <table id="tt" style="table-layout:fixed;" ></table>
	  <!-- 表格顶部工具按钮 -->
	</div>
	<script type="text/javascript" src="<%=path%>/ui/sys/investmember/investMemberList.js"></script>
	<script type="text/javascript">
		$("#submitInvest_btn").on("click", function(){
			$('#queryInvestProForm').submit(); 
		});
	</script>
</body>