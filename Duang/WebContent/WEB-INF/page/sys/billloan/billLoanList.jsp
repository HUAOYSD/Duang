<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<body class="easyui-layout">
	<div id="billLoanList_body" region="center" >
	  <!-- 查询条件区域 -->
	  <div id="billLoanList_search_area" class="search_area" >
	    <div class="conditon" id="billLoanList_conditon">
			<form id="querybillLoanListForm" method="post">
				  <table border="0">
					<tr height="35px">
					  	<td>&nbsp;&nbsp;<span class="investPro-search">姓名：</span></td>
					  	<td ><input  name="memberInfo.realName"/></td>
					  	<td>&nbsp;&nbsp;<span class="investPro-search">电话：</span></td>
					  	<td ><input  name="memberInfo.phone"/></td>
					  	<td>&nbsp;&nbsp;<span class="investPro-search">状态：</span></td>
					  	<td>
					  		<select  class="easyui-combobox" name="status" data-options="panelHeight:'auto'">   
							    <!-- 状态，1进行中，2成功，3失败 -->
							    <option value="-1" selected="selected">--请选择--</option>   
				    			<option value="1">放款</option>
				    			<option value="2">还款</option>
				    			<option value="3">增加逾期费</option>
				    			<option value="3">还逾期费</option>
							</select> 
					  	</td>
					 	<td>&nbsp;&nbsp;<span class="investPro-search">操作时间：</span></td>
					  	<td>
					  		<input  type="text" class="easyui-datebox"  name="optTime"></input>
					  	</td>
					  	<td>
							  &nbsp;&nbsp;
							  <a  id="submit_billLoanList_btn" class="easyui-linkbutton my-search-button" iconCls="icon-2012092109942" plain="true">查询</a>
						</td>
						<td>
							&nbsp;&nbsp;
							  <a  onclick="javascript:$('#querybillLoanListForm').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
						</td>
					</tr>
				  </table>
			</form>
	    </div>
	    <span class="openOrClose" id="billLoanList_open_close">111</span>
	  </div>
	  <!-- 数据表格区域 -->
	  <table id="billLoanList_table" style="table-layout:fixed;" ></table>
	</div>
	<script type="text/javascript" src="<%=path%>/ui/sys/billloan/billLoanList.js"></script>
	<script type="text/javascript">
		var APP_PATH = "<%=path%>";
		$("#submit_billLoanList_btn").on("click", function(){
			$('#querybillLoanListForm').submit(); 
		});
	</script>
</body>