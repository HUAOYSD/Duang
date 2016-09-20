<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<body class="easyui-layout">
	<div id="smsList_body" region="center" >
	  <!-- 查询条件区域 -->
	  <div id="smsList_search_area" class="search_area" >
	    <div class="conditon" id="smsList_conditon">
			<form id="querysmsListForm" method="post">
				  <table border="0">
					<tr height="35px">
					  	<td>&nbsp;&nbsp;<span class="investPro-search">手机号：</span></td>
					  	<td ><input  name="phone"/></td>
					  	<td>&nbsp;&nbsp;<span class="investPro-search">内容：</span></td>
					  	<td ><input  name="content"/></td>
					  	<td class="showRight">&nbsp;&nbsp;<span class="investPro-search">状态：</span></td>
						  	<td><select style="width: 140px;" class="easyui-combobox" name="state" data-options="panelHeight:'auto'">   
								    <option value="-1" selected="selected">--请选择--</option>   
								    <option value="0">未发送</option>   
					    			<option value="1">已发送</option>
								</select> 
						</td>
					  	<td>&nbsp;&nbsp;<span class="investPro-search">时间开始于：</span></td>
					  	<td ><input name="startTime" id="startTime" type="text" class="easyui-datebox" data-options="editable:false"></input></td>
					  	<td>&nbsp;&nbsp;<span class="investPro-search">时间结束于：</span></td>
					  	<td ><input name="endTime" id="endTime" type="text" class="easyui-datebox" data-options="editable:false"></input></td>
					  	<td>&nbsp;&nbsp;
							<a id="submit_smsList_btn" class="easyui-linkbutton my-search-button" iconCls="icon-2012092109942" plain="true">查询</a>
						</td>
						<td>&nbsp;&nbsp;
							<a onclick="javascript:$('#querysmsListForm').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true">重置</a>
						</td>
					</tr>
				  </table>
			</form>
	    </div>
	    <span class="openOrClose" id="smsList_open_close">111</span>
	  </div>
	  <!-- 数据表格区域 -->
	  <table id="smsList_table" style="table-layout:fixed;" ></table>
	</div>
	<script type="text/javascript" src="<%=path%>/ui/sys/sms/smsList.js"></script>
	<script type="text/javascript">
		var APP_PATH = "<%=path%>";
		$("#submit_smsList_btn").on("click", function(){
			var startTime = $('#startTime').datebox('getValue');
			var endTime = $('#endTime').datebox('getValue');
			if((startTime == "" && endTime != "") || (startTime != "" && endTime == "")){
				layer.msg("开始时间或结束时间不能为空",{time:2000});
				return;
			}
			$('#querysmsListForm').submit(); 
		});
	</script>
</body>