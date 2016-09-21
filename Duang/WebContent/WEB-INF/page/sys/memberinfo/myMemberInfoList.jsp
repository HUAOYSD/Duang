<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<body class="easyui-layout">
	<div id="myMemberInfoList_body" region="center" >
	  <!-- 查询条件区域 -->
	  <div id="myMemberInfoList_search_area" class="search_area" >
	    <div class="conditon" id="myMemberInfoList_conditon">
			<form id="querymyMemberInfoListForm" method="post">
				  <table border="0">
					<tr height="35px">
					  	<td>&nbsp;&nbsp;<span class="investPro-search">登录名：</span></td>
					  	<td ><input  name="loginName"/></td>
					 	<td>&nbsp;<span class="investPro-search">真实姓名：</span></td>
					  	<td><input  name="realName"/></td>
					  	<td>&nbsp;<span class="investPro-search">手机号码：</span></td>
					  	<td><input  name="phone"/></td>
					  	<td>
							  &nbsp;&nbsp;
							  <a  id="submit_myMemberInfoList_btn" class="easyui-linkbutton my-search-button" iconCls="icon-2012092109942" plain="true">查询</a>
						</td>
						<td>
							&nbsp;&nbsp;
							  <a  onclick="javascript:$('#querymyMemberInfoListForm').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
						</td>
					</tr>
				  </table>
			</form>
	    </div>
	    <span class="openOrClose" id="myMemberInfoList_open_close">111</span>
	  </div>
	  <!-- 数据表格区域 -->
	  <table id="myMemberInfoList_table" style="table-layout:fixed;" ></table>
	</div>
	<script type="text/javascript" src="<%=path%>/ui/sys/memberinfo/myMemberInfoList.js"></script>
	<script type="text/javascript">
		var APP_PATH = "<%=path%>";
		$("#submit_myMemberInfoList_btn").on("click", function(){
			$('#querymyMemberInfoListForm').submit(); 
		});
	</script>
</body>