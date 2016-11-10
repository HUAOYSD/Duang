<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<body class="easyui-layout">
	<div id="billInvestList_body" region="center" >
	  <!-- 查询条件区域 -->
	  <div id="billInvestList_search_area" class="search_area" >
	    <div class="conditon" id="billInvestList_conditon">
			<form id="querybillInvestListForm" method="post">
				  <table border="0">
					<tr height="35px">
					  	<td>&nbsp;&nbsp;<span class="investPro-search">姓名：</span></td>
					  	<td ><input  name="memberInfo.realName"/></td>
					  	<td>&nbsp;&nbsp;<span class="investPro-search">电话：</span></td>
					  	<td ><input  name="memberInfo.phone"/></td>
					  	<td>&nbsp;&nbsp;<span class="investPro-search">投资标名称：</span></td>
					  	<td ><input  name="investList.scale.name"/></td>
					  	<td>&nbsp;&nbsp;<span class="investPro-search">状态：</span></td>
					  	<td>
					  		<select  class="easyui-combobox" name="status" data-options="panelHeight:'auto'">   
							    <!-- 状态，1进行中，2成功，3失败 -->
							    <option value="-1" selected="selected">--请选择--</option>   
				    			<option value="1">进行中</option>
				    			<option value="2">成功</option>
				    			<option value="3">失败</option>
							</select> 
					  	</td>
					  	<td>&nbsp;&nbsp;<span class="investPro-search">资金类型：</span></td>
					  	<td>
					  		<select  class="easyui-combobox" name="useType" data-options="panelHeight:'auto'">   
							    <!-- 资金类型，1充值，2提现，3消费（购买理财产品），4购买手续费，5赎回（仅本金），6收益，7转让手续费 -->
							    <option value="-1" selected="selected">--请选择--</option>   
							    <option value="1">充值</option>   
				    			<option value="2">提现</option>
				    			<option value="3">消费（购买理财产品）</option>
				    			<option value="4">购买手续费</option>
				    			<option value="5">赎回（仅本金）</option>
				    			<option value="6">收益</option>
				    			<option value="7">转让手续费</option>
							</select> 
					  	</td>
					  	
					 	<td>&nbsp;&nbsp;<span class="investPro-search">操作时间：</span></td>
					  	<td>
					  		<input  type="text" class="easyui-datebox"  name="optTime"></input>
					  	</td>
					  	<td>
							  &nbsp;&nbsp;
							  <a  id="submit_billInvestList_btn" class="easyui-linkbutton my-search-button" iconCls="icon-2012092109942" plain="true">查询</a>
						</td>
						<td>
							&nbsp;&nbsp;
							  <a  onclick="javascript:$('#querybillInvestListForm').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
						</td>
					</tr>
				  </table>
			</form>
	    </div>
	    <span class="openOrClose" id="billInvestList_open_close">111</span>
	  </div>
	  <!-- 数据表格区域 -->
	  <table id="billInvestList_table" style="table-layout:fixed;" ></table>
	</div>
	<script type="text/javascript" src="<%=path%>/ui/sys/billinvest/billInvestList.js"></script>
	<script type="text/javascript">
		var APP_PATH = "<%=path%>";
		$("#submit_billInvestList_btn").on("click", function(){
			$('#querybillInvestListForm').submit(); 
		});
	</script>
</body>