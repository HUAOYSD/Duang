<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<body class="easyui-layout">
	<div class="body" id="investProToTicketListbody" region="center" >
	  <!-- 查询条件区域 -->
	  <div class="search_area" id="investProToTicketList_search_area">
	    <div class="conditon" id="investProToTicketList_conditon">
			<form id="queryinvestProToTicketListForm" method="post">
				  <table border="0">
					<tr>
					  	<td><span class="investProToTicket-search">总名称：</span></td>
					  	<td ><input  name="nameZh" id="nameZh"   /></td>
					 	<td>&nbsp;<span class="investProToTicket-search">名称：</span></td>
					  	<td><input  name="name" id="name"  /></td>
					  <td>
						  <a  id="submitInvestProToTicket_btn" class="easyui-linkbutton my-search-button" iconCls="icon-2012092109942" plain="true">查询</a>
						  <a  onclick="javascript:$('#queryinvestProToTicketListForm').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
					  </td>
					</tr>
				  </table>
			</form>
	    </div>
	    <span class="openOrClose" id="invest_pro_to_ticket_list_open_close">111</span>
	  </div>
	  <!-- 数据表格区域 -->
	  <table id="investProToTicketListTable" style="table-layout:fixed;" ></table>
	  <div  class="footer-btn">
		<a class="btn-ok" onclick="saveInvestProTicket()">确定</a>
		<a class="btn-cancel" onclick="cancle()">取消</a>
	  </div>
	</div>
	<script type="text/javascript" src="<%=path%>/ui/sys/investproduct/investProToTicket.js"></script>
	<script type="text/javascript">
		var investTicketId="${investTicketId}";
		var investProductIds = "${investProductIds}";
		$("#submitInvestProToTicket_btn").on("click", function(){
			$('#queryinvestProToTicketListForm').submit(); 
		});
	</script>
</body>