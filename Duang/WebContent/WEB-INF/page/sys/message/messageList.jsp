<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<body class="easyui-layout">
	<div id="messageList_body" region="center" >
	  <!-- 查询条件区域 -->
	  <div id="messageList_search_area" class="search_area" >
	    <div class="conditon" id="messageList_conditon">
			<form id="querymessageListForm" method="post">
				  <table border="0">
					<tr height="35px">
					  	<td>&nbsp;&nbsp;<span class="investPro-search">标题：</span></td>
					  	<td ><input  name="title"/></td>
					 	<td>&nbsp;<span class="investPro-search">发送人：</span></td>
					  	<td>
					  		<input  type="text"  name="memberInfoBySender.realName"></input>
					  	</td>
					  	<td>&nbsp;<span class="investPro-search">接收人：</span></td>
					  	<td>
					  		<input  type="text"   name="memberInfoByReceiver.realName"></input>
					  	</td>
					  	<td>&nbsp;<span class="investPro-search">时间开始于：</span></td>
					  	<td>
					  		<input  type="text" class="easyui-datebox"  name="startTime" ></input>
					  	</td>
					  	<td>&nbsp;<span class="investPro-search">时间结束于：</span></td>
					  	<td>
					  		<input  type="text" class="easyui-datebox"  name="endTime"></input>
					  	</td>
					  	<td>
							  &nbsp;&nbsp;
							  <a  id="submit_messageList_btn" class="easyui-linkbutton my-search-button" iconCls="icon-2012092109942" plain="true">查询</a>
						</td>
						<td>
							&nbsp;&nbsp;
							  <a  onclick="javascript:$('#querymessageListForm').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
						</td>
					</tr>
				  </table>
			</form>
	    </div>
	    <span class="openOrClose" id="messageList_open_close">111</span>
	  </div>
	  <!-- 数据表格区域 -->
	  <table id="messageList_table" style="table-layout:fixed;" ></table>
	</div>
	<script type="text/javascript" src="<%=path%>/ui/sys/message/messageList.js"></script>
	<script type="text/javascript">
		var APP_PATH = "<%=path%>";
		$("#submit_messageList_btn").on("click", function(){
			$('#querymessageListForm').submit(); 
		});
	</script>
</body>