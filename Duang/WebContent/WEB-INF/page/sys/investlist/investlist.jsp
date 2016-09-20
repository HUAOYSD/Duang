<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<body class="easyui-layout">
	<div class="body" id="body_investlist" region="center" style="border:0px;" >	
		  <!-- 查询条件区域 -->
		  <div id="investlist_search_area" class="search_area query_conds_area">
		    <div class="conditon" id="investlist_conditon">
				<form id="investlistQueryForm" method="post">
					  <table border="0">
						<tr height="35px">
						  	<td class="showRight"><span class="investPro-search">借款人：</span></td>
						  	<td><input name="memberName" id="investlistSearch_memberName"/></td>
						 	<td class="showRight"><span class="investPro-search">用户名：</span></td>
						  	<td><input name="memberNickName" id="investlistSearch_memberNickName"/></td>
						  	<td class="showRight"><span class="investPro-search">标名称：</span></td>
						  	<td><input name="scaleName" id="investlistSearch_scaleName"/></td>
						  	<td class="showRight"><span class="investPro-search">状态：</span></td>
						  	<td><select style="width: 140px;" class="easyui-combobox" name="status" id="investlistSearch_loanType" data-options="panelHeight:'auto'">   
								    <option value="0" selected="selected">--请选择--</option>   
								    <option value="1">资金匹配中</option>   
					    			<option value="2">投资收益中</option>
					    			<option value="3">完成投资</option>
					    			<option value="4">到期回款中</option>
					    			<option value="5">回款成功</option>
					    			<option value="6">回款失败</option>
					    			<option value="7">过期</option>
								</select> 
							</td>
						</tr>
						<tr height="35px">
							<td class="showRight"><span class="investPro-search">手机号码：</span></td>
						  	<td><input name="memberPhone" id="investlistSearch_memberPhone"/></td>
						  	<td class="showRight"><span class="investPro-search">身份证号：</span></td>
						  	<td><input name="memberIdcard" id="investlistSearch_memberIdcard"/></td>
						  	<td class="showRight"><span class="investPro-search">合同编号：</span></td>
						  	<td><input name="pactNumber" id="investlistSearch_pactNumber"/></td>
						  	<td class="showRight"><span class="investPro-search">使用理财券：</span></td>
						  	<td><select style="width: 140px;" class="easyui-combobox" name="useTicket" id="investlistSearch_useTicket" data-options="panelHeight:'auto'">   
								    <option value="" selected="selected">--请选择--</option>   
								    <option value="1">未使用</option>   
								    <option value="2">使用</option>   
								</select> 
						  	</td>
						</tr>
						<tr height="35px">
							<td class="showRight"><span class="investPro-search">转让标：</span></td>
						  	<td><select style="width: 140px;" class="easyui-combobox" name="turnScale" id="investlistSearch_turnScale" data-options="panelHeight:'auto'">   
								    <option value="" selected="selected">--请选择--</option>   
								    <option value="0">不是</option>   
					    			<option value="1">是</option>
								</select> 
						  	</td>
						  	<td class="showRight"><span class="investPro-search">已转让：</span></td>
						  	<td><select style="width: 140px;" class="easyui-combobox" name="isTurn" id="investlistSearch_isTurn" data-options="panelHeight:'auto'">   
								    <option value="" selected="selected">--请选择--</option>   
					   				<option value="0">否</option>   
					    			<option value="1">是</option>
								</select> 
						  	</td>
						  	<td class="showRight"><span class="investPro-search">转让状态：</span></td>
						  	<td><select style="width: 140px;" class="easyui-combobox" name="turnStatus" id="investlistSearch_turnStatus" data-options="panelHeight:'auto'">
								    <option value="" selected="selected">--请选择--</option>   
								    <option value="1">转让中</option>   
								    <option value="2">成功</option>
								    <option value="3">过期</option>
								    <option value="4">失败</option>
								</select> 
						  	</td>
						  	<td class="showRight"><span class="investPro-search">平台：</span></td>
						  	<td><select style="width: 140px;" class="easyui-combobox" name="investStyle" id="investlistSearch_investStyle" data-options="panelHeight:'auto'">
								    <option value="" selected="selected">--请选择--</option>   
								    <option value="1">线下</option>   
								    <option value="2">Android</option>
								    <option value="3">IOS</option>
								    <option value="4">平台系统</option>
								</select> 
						  	</td>
						</tr>
						<tr height="35px">
							<td class="showRight"><span class="investPro-search">计息日期：</span></td>
						  	<td>
						  		<input name="calbegindate1" id="calbegindate1" type="text" class="easyui-datebox"></input>
						  	</td>
						  	<td>&nbsp;&nbsp;——&nbsp;&nbsp;</td>
						  	<td>
						  		<input name="calbegindate2" id="calbegindate2" type="text" class="easyui-datebox"></input>
						  	</td>
						  	<td class="showRight"><span class="investPro-search">结息日期：</span></td>
						  	<td>
						  		<input name="calenddate1" id="calenddate1" type="text" class="easyui-datebox"></input>
						  	</td>
						  	<td>&nbsp;&nbsp;——&nbsp;&nbsp;</td>
						  	<td>
						  		<input name="calenddate2" id="calenddate2" type="text" class="easyui-datebox"></input>
						  	</td>
						</tr>
						<tr height="35px">
						  	<td colspan="8">
						  		&nbsp;&nbsp;
								<a id="investlistQueryForm_Btn" class="easyui-linkbutton my-search-button" iconCls="icon-2012092109942" plain="true">查询</a>&nbsp;&nbsp;
								<a onclick="javascript:$('#investlistQueryForm').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true">重置</a>
						  	</td>
						</tr>
					  </table>
					  <input type="hidden" name="customerId" value="${customerId}">
				</form>
		    </div>
		    <span class="openOrClose" id="investlist_open_close">&nbsp;&nbsp;</span>
		  </div>
		  <!-- 数据表格区域 -->
		  <table id="investlist" style="table-layout:fixed;" ></table>
	 	  <!-- 表格顶部工具按钮 -->
		  <div id="tt_toolbar_investlist">
				<!-- <a id="allot_btn_investlist" class="easyui-linkbutton" iconCls="icon-2012080412301" plain="true"></a> -->
	  	  </div>
	</div>
	<script type="text/javascript" src="<%=path %>/ui/sys/investlist/investlist.js"></script>
	<script type="text/javascript">
		var customerId = "${customerId}";
	</script>
</body>