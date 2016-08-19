<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<body class="easyui-layout">
	<div id="notificationList_body" region="center" >
	  <!-- 查询条件区域 -->
	  <div id="notificationList_search_area" class="search_area" >
	    <div class="conditon" id="notificationList_conditon">
			<form id="querynotificationListForm" method="post">
				  <table border="0">
					<tr height="35px">
					  	<td>&nbsp;&nbsp;<span class="investPro-search">标题：</span></td>
					  	<td ><input  name="title"/></td>
					  	<td>&nbsp;<span class="investPro-search">发布日期：</span></td>
					  	<td>
					  		<input  type="text" class="easyui-datebox"  name="publishTime" ></input>
					  	</td>
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
							  <a  id="submit_notificationList_btn" class="easyui-linkbutton my-search-button" iconCls="icon-2012092109942" plain="true">查询</a>
						</td>
						<td>
							&nbsp;&nbsp;
							  <a  onclick="javascript:$('#querynotificationListForm').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
						</td>
					</tr>
				  </table>
			</form>
	    </div>
	    <span class="openOrClose" id="notificationList_open_close">111</span>
	  </div>
	  <!-- 数据表格区域 -->
	  <table id="notificationList_table" style="table-layout:fixed;" ></table>
	  
	  <div id="notificationList_toolbar">
	      <a id="notificationList-add-btn" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
	      <a id="notificationList-update-btn" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a> 
  	  </div>
	  
	</div>
	<script type="text/javascript" src="<%=path%>/ui/sys/notification/notificationList.js"></script>
	<script type="text/javascript">
		var APP_PATH = "<%=path%>";
		$("#submit_notificationList_btn").on("click", function(){
			$('#querynotificationListForm').submit(); 
		});
	</script>
</body>