<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<body class="easyui-layout">
	<div id="friends_body" region="center" >
	  <!-- 查询条件区域 -->
	  <div id="friends_search_area" class="search_area" >
	    <div class="conditon" id="friends_conditon">
			<form id="queryfriendsForm" method="post">
				  <table border="0">
					<tr height="35px">
						<td>&nbsp;&nbsp;<span class="investPro-search">自己真实姓名：</span></td>
					  	<td ><input  name="memberInfoBySelf.realName"/></td>
					  	<td>&nbsp;&nbsp;<span class="investPro-search">自己登录名：</span></td>
					  	<td ><input  name="memberInfoBySelf.loginName"/></td>
					  	<td>&nbsp;&nbsp;<span class="investPro-search">关注真实姓名：</span></td>
					  	<td ><input  name="memberInfoByTarget.realName"/></td>
					  	<td>&nbsp;&nbsp;<span class="investPro-search">关注登录名：</span></td>
					  	<td ><input  name="memberInfoByTarget.loginName"/></td>
					  	<td>
							  &nbsp;&nbsp;
							  <a  id="submit_friends_btn" class="easyui-linkbutton my-search-button" iconCls="icon-2012092109942" plain="true">查询</a>
						</td>
						<td>
							&nbsp;&nbsp;
							  <a  onclick="javascript:$('#queryfriendsForm').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
						</td>
					</tr>
				  </table>
			</form>
	    </div>
	    <span class="openOrClose" id="friends_open_close">111</span>
	  </div>
	  <!-- 数据表格区域 -->
	  <table id="friends_table" style="table-layout:fixed;" ></table>
	</div>
	<script type="text/javascript" src="<%=path%>/ui/sys/friends/friends.js"></script>
	<script type="text/javascript">
		var APP_PATH = "<%=path%>";
		$("#submit_friends_btn").on("click", function(){
			$('#queryfriendsForm').submit(); 
		});
	</script>
</body>