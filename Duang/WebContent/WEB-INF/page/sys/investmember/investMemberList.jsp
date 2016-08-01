<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<body class="easyui-layout">
	<div id="invest_member_body" region="center" >
	  <!-- 查询条件区域 -->
	  <div id="invest_member_search_area" class="search_area" >
	    <div class="conditon" id="invest_memeber_conditon">
			<form id="queryInvestMemberForm" method="post">
				  <table border="0">
					<tr>
					  	<td><span class="investPro-search">总名称：</span></td>
					  	<td ><input  name="nameZh"/></td>
					 	<td>&nbsp;<span class="investPro-search">名称：</span></td>
					  	<td><input  name="name"/></td>
					  <td>
						  <a  id="submit_invest_memeber_btn" class="easyui-linkbutton my-search-button" iconCls="icon-2012092109942" plain="true">查询</a>
						  <a  onclick="javascript:c$.clearForm(document.getElementById('queryInvestProForm'))" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
					  </td>
					</tr>
				  </table>
			</form>
	    </div>
	    <span class="openOrClose" id="invest_member_open_close">111</span>
	  </div>
	  <!-- 数据表格区域 -->
	  <table id="invest_memeber_table" style="table-layout:fixed;" ></table>
	  <!-- 表格顶部工具按钮 -->
	</div>
	<script type="text/javascript" src="<%=path%>/ui/sys/investmember/investMemberList.js"></script>
	<script type="text/javascript">
		$("#submit_invest_memeber_btn").on("click", function(){
			$('#queryInvestMemberForm').submit(); 
		});
	</script>
</body>