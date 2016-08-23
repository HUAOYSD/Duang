<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<body class="easyui-layout">
	<div id="billInvestList_body" region="center" >
	  <!-- 查询条件区域 -->
	  <div id="billInvestList_search_area" class="search_area" >
	    <div class="conditon" id="billInvestList_conditon">
			<form id="querybillInvestListForm" method="post">
				  <table border="0">
					<tr height="35px">
					  	<td>&nbsp;&nbsp;<span class="investPro-search">名称：</span></td>
					  	<td ><input  name="investTicket.name"/></td>
					 	<td>&nbsp;<span class="investPro-search">使用时间：</span></td>
					  	<td>
					  		<input  type="text" class="easyui-datebox"  name="useTime"></input>
					  	</td>
					  	<td>
							  &nbsp;&nbsp;
							  <a  id="submit_billInvestList_btn" class="easyui-linkbutton my-search-button" iconCls="icon-2012092109942" plain="true">查询</a>
						</td>
						<td>
							&nbsp;&nbsp;
							  <a  onclick="javascript:$('#querybillInvestListForm').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
						</td>
					</tr>
				  </table>
			</form>
	    </div>
	    <span class="openOrClose" id="billInvestList_open_close">111</span>
	  </div>
	  <!-- 数据表格区域 -->
	  <table id="billInvestList_table" style="table-layout:fixed;" ></table>
	</div>
	<script type="text/javascript" src="<%=path%>/ui/sys/billinvest/billInvestList.js"></script>
	<script type="text/javascript">
		var APP_PATH = "<%=path%>";
		$("#submit_billInvestList_btn").on("click", function(){
			$('#querybillInvestListForm').submit(); 
		});
	</script>
</body>