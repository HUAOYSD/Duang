<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<body class="easyui-layout">
	<div id="memberInvestTicketList_body" region="center" >
	  <!-- 查询条件区域 -->
	  <div id="memberInvestTicketList_search_area" class="search_area" >
	    <div class="conditon" id="memberInvestTicketList_conditon">
			<form id="querymemberInvestTicketListForm" method="post">
				  <table border="0">
					<tr height="35px">
					  	<td>&nbsp;&nbsp;<span class="investPro-search">登录名：</span></td>
					  	<td ><input  name="memberInfo.loginName"/></td>
					 	<td>&nbsp;<span class="investPro-search">真实姓名：</span></td>
					  	<td>
					  		<input  type="text"  name="memberInfo.realName"></input>
					  	</td>
					  	<td>&nbsp;<span class="investPro-search">手机：</span></td>
					  	<td>
					  		<input  type="text"   name="memberInfo.phone"></input>
					  	</td>
					 </tr>
					 <tr>
					 	<td>&nbsp;&nbsp;<span class="investPro-search">理财券名称：</span></td>
					  	<td ><input  name="investTicket.name"/></td> 	
					  	<td>&nbsp;<span class="investPro-search">有效期始：</span></td>
					  	<td>
					  		<input  type="text" class="easyui-datebox"  name="startTime" ></input>
					  	</td>
					  	<td>&nbsp;<span class="investPro-search">有效期至：</span></td>
					  	<td>
					  		<input  type="text" class="easyui-datebox"  name="endTime"></input>
					  	</td>
					  	<td>
							  &nbsp;&nbsp;
							  <a  id="submit_memberInvestTicketList_btn" class="easyui-linkbutton my-search-button" iconCls="icon-2012092109942" plain="true">查询</a>
						</td>
						<td>
							&nbsp;&nbsp;
							  <a  onclick="javascript:$('#querymemberInvestTicketListForm').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
						</td>
					</tr>
				  </table>
			</form>
	    </div>
	    <span class="openOrClose" id="memberInvestTicketList_open_close">111</span>
	  </div>
	  <!-- 数据表格区域 -->
	  <table id="memberInvestTicketList_table" style="table-layout:fixed;" ></table>
	</div>
	<script type="text/javascript" src="<%=path%>/ui/sys/memberinvestticket/memberInvestTicketList.js"></script>
	<script type="text/javascript">
		var APP_PATH = "<%=path%>";
		$("#submit_memberInvestTicketList_btn").on("click", function(){
			$('#querymemberInvestTicketListForm').submit(); 
		});
	</script>
</body>