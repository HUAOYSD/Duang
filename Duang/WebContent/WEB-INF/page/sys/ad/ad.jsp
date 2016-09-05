<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<body class="easyui-layout">
	<div id="ad_body" region="center" >
	  <!-- 查询条件区域 -->
	  <div id="ad_search_area" class="search_area" >
	    <div class="conditon" id="ad_conditon">
			<form id="queryadForm" method="post">
				  <table border="0">
					<tr height="35px">
						<td>&nbsp;&nbsp;<span class="investPro-search">名称：</span></td>
					  	<td ><input  name="name"/></td>
					  	<td>
							  &nbsp;&nbsp;
							  <a  id="submit_ad_btn" class="easyui-linkbutton my-search-button" iconCls="icon-2012092109942" plain="true">查询</a>
						</td>
						<td>
							&nbsp;&nbsp;
							  <a  onclick="javascript:$('#queryadForm').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
						</td>
					</tr>
				  </table>
			</form>
	    </div>
	    <span class="openOrClose" id="ad_open_close">111</span>
	  </div>
	  <div id="adToolBar" style="margin-left:10px;">
	  	  <a id="ad-add-btn" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
	      <a id="ad-update-btn" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a> 
	      <a id="ad-delete-btn" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
	      <a id="ad-icon-btn" class="easyui-linkbutton" iconCls="icon-palette" plain="true">上传图片</a>
	  </div>
	  <!-- 数据表格区域 -->
	  <table id="ad_table" style="table-layout:fixed;" ></table>
	</div>
	<script type="text/javascript" src="<%=path%>/ui/sys/ad/ad.js"></script>
	<script type="text/javascript">
		var APP_PATH = "<%=path%>";
		$("#submit_ad_btn").on("click", function(){
			$('#queryadForm').submit(); 
		});
	</script>
</body>