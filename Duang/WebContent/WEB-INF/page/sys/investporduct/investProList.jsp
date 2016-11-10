<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<body class="easyui-layout">
	<div class="body" id="investProListbody" region="center" >
	  <!-- 查询条件区域 -->
	  <div class="search_area" id="investProList_search_area">
	    <div class="conditon" id="investProList_conditon">
			<form id="queryInvestProListForm" method="post">
				  <table border="0">
					<tr>
					  	<td><span class="investPro-search">总名称：</span></td>
					  	<td ><input  name="nameZh" id="nameZh"   /></td>
					 	<td>&nbsp;<span class="investPro-search">名称：</span></td>
					  	<td><input  name="name" id="name"  /></td>
					  <td>
						  <a  id="submitInvest_btn" class="easyui-linkbutton my-search-button" iconCls="icon-2012092109942" plain="true">查询</a>
						  <a  onclick="javascript:$('#queryInvestProListForm').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
					  </td>
					</tr>
				  </table>
			</form>
	    </div>
	    <span class="openOrClose" id="invest_pro_list_open_close">111</span>
	  </div>
	  <!-- 数据表格区域 -->
	  <table id="investProListTable" style="table-layout:fixed;" ></table>
	  <!-- 表格顶部工具按钮 -->
	</div>
	<script type="text/javascript" src="<%=path%>/ui/sys/investproduct/investPro.js"></script>
	<script type="text/javascript">
		$("#submitInvest_btn").on("click", function(){
			$('#queryInvestProListForm').submit(); 
		});
	</script>
</body>