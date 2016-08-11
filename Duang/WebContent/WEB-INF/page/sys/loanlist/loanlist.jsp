<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<body class="easyui-layout">
	<div class="body" id="body_loanlist" region="center" style="border:0px;" >	
		  <!-- 查询条件区域 -->
		  <div id="loanlist_search_area" class="search_area query_conds_area">
		    <div class="conditon" id="loanlist_conditon">
				<form id="loanlistQueryForm" method="post">
					  <table border="0">
						<tr height="35px">
						  	<td>&nbsp;&nbsp;<span class="investPro-search">姓名：</span></td>
						  	<td ><input name="name" id="loanlistSearch_name"/></td>
						 	<td>&nbsp;<span class="investPro-search">登录ID：</span></td>
						  	<td><input name="sysUser.name" id="loanlistSearch_sysUserId"/></td>
						  	<td>&nbsp;<span class="investPro-search">工号：</span></td>
						  	<td><input name="workNumber" id="loanlistSearch_workNumber"/></td>
						  	<td>&nbsp;<span class="investPro-search">手机号码：</span></td>
						  	<td colspan="3"><input name="phone" id="loanlistSearch_phone"/></td>
						</tr>
						<tr height="35px">
							<td>&nbsp;&nbsp;<span class="investPro-search">邮箱：</span></td>
						  	<td><input name="email" id="loanlistSearch_email"/></td>
						  	<td>&nbsp;&nbsp;<span class="investPro-search">身份证：</span></td>
						  	<td><input name="idcard" id="loanlistSearch_idcard"/></td>
						  	<td>&nbsp;<span class="investPro-search">性别：</span></td>
						  	<td><select style="width: 140px;" class="easyui-combobox" name="sex" id="loanlistSearch_sex" data-options="panelHeight:'auto'">   
								    <option value="null" selected="selected">--请选择--</option>   
								    <option value="男">男</option>   
					    			<option value="女">女</option>
					    			<option value="保密">保密</option>
								</select> 
						  	</td>
						  	<td>&nbsp;<span class="investPro-search">是否删除：</span></td>
						  	<td><select style="width: 140px;" class="easyui-combobox" name="isDelete" id="loanlistSearch_isDelete" data-options="panelHeight:'auto'">
									    <option value="0" selected="selected">否</option>   
									    <option value="1">是</option>
								</select> 
						  	</td>
						  	<td>&nbsp;&nbsp;
								<a id="loanlistQueryForm_Btn" class="easyui-linkbutton my-search-button" iconCls="icon-2012092109942" plain="true">查询</a>
							</td>
							<td>&nbsp;&nbsp;
								<a onclick="javascript:$('#loanlistQueryForm').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true">重置</a>
							</td>
						</tr>
					  </table>
				</form>
		    </div>
		    <span class="openOrClose" id="loanlist_open_close">&nbsp;&nbsp;</span>
		  </div>
		  <!-- 数据表格区域 -->
		  <table id="loanlist" style="table-layout:fixed;" ></table>
	 	  <!-- 表格顶部工具按钮 -->
		  <div id="tt_toolbar_loanlist">
	          <a id="res_btn_loanlist" class="easyui-linkbutton" iconCls="icon-2012080412301" plain="true">还原</a>
	  	  </div>
	</div>
	<script type="text/javascript" src="<%=path %>/ui/sys/loanlist/loanlist.js"></script>
</body>