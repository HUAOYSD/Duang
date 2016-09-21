<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<body class="easyui-layout">
	<div class="body" id="body_memberinfo" region="center" style="border:0px;" >	
		  <!-- 查询条件区域 -->
		  <div id="memberinfo_search_area" class="search_area query_conds_area">
		    <div class="conditon" id="memberinfo_conditon">
				<form id="memberinfoQueryForm" method="post">
					  <table border="0">
						<tr height="35px">
						  	<td class="showRight"><span class="investPro-search">登录名：</span></td>
						  	<td><input name="loginName"/></td>
						 	<td class="showRight"><span class="investPro-search">真实姓名：</span></td>
						  	<td><input name="realName"/></td>
						 	<td class="showRight"><span class="investPro-search">手机号码：</span></td>
						  	<td><input name="phone"/></td>
						 	<td class="showRight"><span class="investPro-search">身份证号：</span></td>
						  	<td><input name="idCard"/></td>
						  	<td colspan="2">&nbsp;&nbsp;
								<a id="memberinfoQueryForm_Btn" class="easyui-linkbutton my-search-button" iconCls="icon-2012092109942" plain="true">查询</a>&nbsp;&nbsp;
						  		<a onclick="javascript:$('#memberinfoQueryForm').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true">重置</a>
						  	</td>
						</tr>
					  </table>
				</form>   
		    </div>
		    <span class="openOrClose" id="memberinfo_open_close">&nbsp;&nbsp;</span>
		  </div>
		  <!-- 数据表格区域 -->
		  <table id="memberinfo" style="table-layout:fixed;" ></table>
	</div>
	<script type="text/javascript" src="<%=path %>/ui/sys/memberinfo/levelmemberlist.js"></script>
</body>