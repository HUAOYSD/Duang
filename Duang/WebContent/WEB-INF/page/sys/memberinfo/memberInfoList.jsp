<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<body class="easyui-layout">
	<div id="memberInfoList_body" region="center" >
	  <!-- 查询条件区域 -->
	  <div id="memberInfoList_search_area" class="search_area" >
	    <div class="conditon" id="memberInfoList_conditon">
			<form id="queryMemberInfoListForm" method="post">
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
							  <a  id="submit_memberInfoList_btn" class="easyui-linkbutton my-search-button" iconCls="icon-2012092109942" plain="true">查询</a>
						</td>
						<td>
							&nbsp;&nbsp;
							  <a  onclick="javascript:$('#queryMemberInfoListForm').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
						</td>
					</tr>
				  </table>
			</form>
	    </div>
	    <span class="openOrClose" id="memberInfoList_open_close">111</span>
	  </div>
	  <!-- 数据表格区域 -->
	  <table id="memberInfoList_table" style="table-layout:fixed;" ></table>
	  <!-- 表格顶部工具按钮 -->
	  <!-- 表格顶部工具按钮 -->
	  <div id="memberInfoList_table_toolbar">
	  	  <!--  <a id="memberInfoList-export-btn" class="easyui-linkbutton" iconCls="icon-2012080412486" plain="true">导出</a>
	  	  <a id="memberInfoList-freeze-btn" class="easyui-linkbutton" iconCls="icon-rosette" plain="true">冻结</a>
	  	  <a id="memberInfoList-unfreeze-btn" class="easyui-linkbutton" iconCls="icon-rosette_blue" plain="true">解冻</a>
	  	  <a id="memberInfoList-sereach-btn" class="easyui-linkbutton" iconCls="icon-find " plain="true">查询账户余额</a>
	  	  <a id="memberInfoList-sereach-btn" class="easyui-linkbutton" iconCls="icon-medal_silver_2 " plain="true">余额同步</a>
	  	  <a id="memberInfoList-sereach-btn" class="easyui-linkbutton" iconCls="icon-20130406015709810_easyicon_net_16" plain="true">信息同步</a>
	      <a id="memberInfoList-add-btn" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
	      <a id="memberInfoList-update-btn" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a> 
	      <a id="memberInfoList-delete-btn" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a> -->
	      <a id="memberInfoList-sereach-investMember-btn" class="easyui-linkbutton" iconCls="icon-user_green" plain="true">理财信息</a>
	      <a id="memberInfoList-sereach-loanMember-btn" class="easyui-linkbutton" iconCls="icon-user_red" plain="true">借贷信息</a>
	      <a id="memberInfoList-upload-idcard1-btn" class="easyui-linkbutton" iconCls="icon-vcard" plain="true">上传身份证前照</a>
	      <a id="memberInfoList-upload-idcard2-btn" class="easyui-linkbutton" iconCls="icon-vcard_edit" plain="true">上传身份证后照</a>
	      <a id="memberInfoList-unselected-btn" class="easyui-linkbutton" iconCls="icon-2012080412301" plain="true">取消选择</a>
  	  </div>
	</div>
	<script type="text/javascript" src="<%=path%>/ui/sys/memberinfo/memberInfoList.js"></script>
	<script type="text/javascript">
		var APP_PATH = "<%=path%>";
		$("#submit_memberInfoList_btn").on("click", function(){
			$('#queryMemberInfoListForm').submit(); 
		});
	</script>
</body>