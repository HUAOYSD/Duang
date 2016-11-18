<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<body class="easyui-layout">
	<div class="body" id="body_awardActivity" region="center" style="border:0px;" >	
		  <!-- 查询条件区域 -->
		  <div id="awardActivity_search_area" class="search_area query_conds_area">
		    <div class="conditon" id="awardActivity_conditon">
				<form id="awardActivityQueryForm" method="post">
					  <table border="0">
						<tr height="35px">
						  	<td class="showRight"><span class="investPro-search">活动名称：</span></td>
						  	<td><input name="title"/></td>
						  	<td colspan="2">&nbsp;&nbsp;
								<a id="awardActivityQueryForm_Btn" class="easyui-linkbutton my-search-button" iconCls="icon-2012092109942" plain="true">查询</a>&nbsp;&nbsp;
						  		<a onclick="javascript:$('#awardActivityQueryForm').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true">重置</a>
						  	</td>
						</tr>
					  </table>
				</form>
		    </div>
		    <span class="openOrClose" id="awardActivity_open_close">&nbsp;&nbsp;</span>
		  </div>
		  <!-- 数据表格区域 -->
		  <table id="awardActivity" style="table-layout:fixed;" ></table>
	 	  <!-- 表格顶部工具按钮 -->
		  <div id="tt_toolbar_awardActivity">
		      <a id="add_btn_awardActivity" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
		      <a id="edit_btn_awardActivity" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a> 
		      <a id="info_btn_awardActivity" class="easyui-linkbutton" iconCls="icon-information" plain="true">等级详情</a> 
		      <a id="award_btn_awardActivity" class="easyui-linkbutton" iconCls="icon-wrench_orange" plain="true">奖品管理</a> 
	  	  </div>
	</div>
	<script type="text/javascript" src="<%=path %>/ui/sys/award/awardActivity.js"></script>
</body>