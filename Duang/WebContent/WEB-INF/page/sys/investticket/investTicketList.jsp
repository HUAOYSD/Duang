<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<body class="easyui-layout">
	<div id="investTicketList_body" region="center" >
	  <!-- 查询条件区域 -->
	  <div id="investTicketList_search_area" class="search_area" >
	    <div class="conditon" id="investTicketList_conditon">
			<form id="queryinvestTicketListForm" method="post">
				  <table border="0">
					<tr height="35px">
					  	<td>&nbsp;&nbsp;<span class="investPro-search">名称：</span></td>
					  	<td ><input  name="name"/></td>
					  	<td>
							  &nbsp;&nbsp;
							  <a  id="submit_investTicketList_btn" class="easyui-linkbutton my-search-button" iconCls="icon-2012092109942" plain="true">查询</a>
						</td>
						<td>
							&nbsp;&nbsp;
							  <a  onclick="javascript:$('#queryinvestTicketListForm').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
						</td>
					</tr>
				  </table>
			</form>
	    </div>
	    <span class="openOrClose" id="investTicketList_open_close">111</span>
	  </div>
	  <!-- 数据表格区域 -->
	  <table id="investTicketList_table" style="table-layout:fixed;" ></table>
	  
	  <div id="investTicketList_toolbar">
	      <a id="investTicketList-add-btn" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
	      <a id="investTicketList-update-btn" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a> 
	      <a id="investTicketList-delete-btn" class="easyui-linkbutton" iconCls="icon-2012080404218" plain="true">设置为过期</a>
	      <a id="investTicketList-select-InvestProduct-btn" class="easyui-linkbutton" iconCls="icon-anchor" plain="true">可使用产品</a>
  	  </div>
	  
	</div>
	<script type="text/javascript" src="<%=path%>/ui/sys/investticket/investTicketList.js"></script>
	<script type="text/javascript">
		var APP_PATH = "<%=path%>";
		$("#submit_investTicketList_btn").on("click", function(){
			$('#queryinvestTicketListForm').submit(); 
		});
	</script>
</body>