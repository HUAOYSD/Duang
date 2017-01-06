<%@page import="org.duang.util.DataUtils"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<body class="easyui-layout">
	<div id="loan_member_repay_body" region="center" >
	  <!-- 查询条件区域 -->
	  <div id="loan_member_repay_search_area" class="search_area" >
	    <div class="conditon" id="loan_member_repay_conditon">
			<form id="queryloanMemberListRepayForm" method="post">
				  <table border="0">
					<tr height="35px">
					 	<td>&nbsp;<span class="investPro-search">真实姓名：</span></td>
					  	<td><input  name="realName" value="${realName}"/></td>
					  	<td>&nbsp;<span class="investPro-search">手机号码：</span></td>
					  	<td><input  name="phone" value="${phone}"/></td>
					</tr>
					<tr height="35px">
						<td>&nbsp;&nbsp;<span class="investPro-search">年月：</span></td>
					  	<td>
					  		<select  class="easyui-combobox" name="year" data-options="panelHeight:'auto'">
						       		<option value="0"></option>    
								    <% 
								    	Calendar calendar = Calendar.getInstance();
								    	for(int i=calendar.get(Calendar.YEAR);i>2014;i--){
								    		String year = (String)request.getAttribute("year");
								    		if(DataUtils.notEmpty(year)){
								    %>
								   	 			<option value="<%=i%>" selected="selected"><%=i%>年</option>   
								    <%
								    		}else{
								    %>
								    			<option value="<%=i%>"><%=i%>年</option>
								    <%	} }%>
							 </select> 
							 
							 <select  class="easyui-combobox" name="month" data-options="panelHeight:'auto'">
						       		<option value="0"></option>    
								     <% 
								    	for(int i=1;i<=12;i++){
								    		String month = (String)request.getAttribute("month");
								    		if(DataUtils.notEmpty(month)){
								    %>
								   	 			<option value="<%=i%>" selected="selected"><%=i%>年</option>   
								    <%
								    		}else{
								    %>
								    			<option value="<%=i%>"><%=i%>年</option>
								    <%	} }%>
							 </select> 
					  	</td>
					  	<td>&nbsp;&nbsp;<span class="investPro-search">状态：</span></td>
					  	<td>
						       	<select  class="easyui-combobox" name="state" data-options="panelHeight:'auto'">
						       		<option value="-1" selected="selected">--请选择--</option>    
								    <option value="0">未还款</option>   
								    <option value="1">已还款</option>
								</select> 
					  	</td>
					  	<td>
							  &nbsp;&nbsp;
							  <a  id="submit_loan_member_repay_list_btn" class="easyui-linkbutton my-search-button" iconCls="icon-2012092109942" plain="true">查询</a>
						</td>
						<td>
							&nbsp;&nbsp;
							  <a  onclick="javascript:$('#queryloanMemberListRepayForm').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
						</td>
					</tr>
				  </table>
			</form>
	    </div>
	    <span class="openOrClose" id="loan_member_repay_open_close">111</span>
	  </div>
	  <!-- 数据表格区域 -->
	  <table id="loan_member_repay_table" style="table-layout:fixed;" ></table>
	  <!-- 表格顶部工具按钮 -->
	  <!-- 表格顶部工具按钮 -->
	  <div id="loan_member_repay_table_toolbar">
  	  </div>
	</div>
	<script type="text/javascript" src="<%=path%>/ui/sys/loanmember/loanMemberListRepay.js"></script>
	<script type="text/javascript">
		var APP_PATH = "<%=path%>";
		$("#submit_loan_member_repay_list_btn").on("click", function(){
			$('#queryloanMemberListRepayForm').submit(); 
		});
	</script>
</body>