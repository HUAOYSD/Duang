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
						  	<td class="showRight"><span class="investPro-search">借款人：</span></td>
						  	<td><input name="loanMemberName" id="loanlistSearch_name"/></td>
						 	<td class="showRight"><span class="investPro-search">手机号码：</span></td>
						  	<td><input name="loanMemberPhone" id="loanlistSearch_phone"/></td>
						  	<td class="showRight"><span class="investPro-search">身份证号：</span></td>
						  	<td><input name="loanMemberIdcard" id="loanlistSearch_idCard"/></td>
						  	<td class="showRight"><span class="investPro-search">借款模式：</span></td>
						  	<td><select style="width: 140px;" class="easyui-combobox" name="loanType" id="loanlistSearch_loanType" data-options="panelHeight:'auto'">   
								    <option value="0" selected="selected">--请选择--</option>   
								    <option value="1">普通模式</option>   
					    			<option value="2">产权模式</option>
					    			<option value="3">信用模式</option>
					    			<option value="4">急速模式</option>
								</select> 
							</td>
						</tr>
						<tr height="35px">
							<td class="showRight"><span class="investPro-search">客户经理：</span></td>
						  	<td><input name="customerManagerName" id="loanlistSearch_customer"/></td>
						  	<td class="showRight"><span class="investPro-search">申请状态：</span></td>
						  	<td><select style="width: 140px;" class="easyui-combobox" name="applyState" id="loanlistSearch_applyState" data-options="panelHeight:'auto'">   
								    <option value="0" selected="selected">--请选择--</option>   
								    <option value="1">待审核</option>   
					    			<option value="2">审核通过</option>
					    			<option value="3">审核驳回</option>
					    			<option value="4">已取消</option>
								</select> 
						  	</td>
						  	<td class="showRight"><span class="investPro-search">手续费状态：</span></td>
						  	<td><select style="width: 140px;" class="easyui-combobox" name="poundageState" id="loanlistSearch_poundageState" data-options="panelHeight:'auto'">
								    <option value="0" selected="selected">--请选择--</option>   
								    <option value="1">未扣手续费</option>   
								    <option value="2">已扣手续费</option>
								</select> 
						  	</td>
						  	<td class="showRight"><span class="investPro-search">还款方式：</span></td>
						  	<td><select style="width: 140px;" class="easyui-combobox" name="backStyle" id="loanlistSearch_backStyle" data-options="panelHeight:'auto'">
								    <option value="0" selected="selected">--请选择--</option>   
								    <option value="1">等额本息</option>   
								    <option value="2">到期一次性还款</option>
								</select> 
						  	</td>
						</tr>
						<tr height="35px">
							<td class="showRight"><span class="investPro-search">放款状态：</span></td>
						  	<td><select style="width: 140px;" class="easyui-combobox" name="loanState" id="loanlistSearch_loanState" data-options="panelHeight:'auto'">   
								    <option value="" selected="selected">--请选择--</option>   
								    <option value="1">未放款</option>   
					    			<option value="2">放款中</option>
					    			<option value="3">放款完成</option>
								</select> 
						  	</td>
						  	<td class="showRight"><span class="investPro-search">起售状态：</span></td>
						  	<td><select style="width: 140px;" class="easyui-combobox" name="isSell" id="loanlistSearch_isSell" data-options="panelHeight:'auto'">   
								    <option value="" selected="selected">--请选择--</option>   
								    <option value="1">未起标</option>   
					    			<option value="2">起标</option>
					    			<option value="3">标满</option>
								</select> 
						  	</td>
						  	<td class="showRight"><span class="investPro-search">还款状态：</span></td>
						  	<td><select style="width: 140px;" class="easyui-combobox" name="returnStatus" id="loanlistSearch_returnStatus" data-options="panelHeight:'auto'">
								    <option value="" selected="selected">--请选择--</option>   
								    <option value="1">未还款</option>   
								    <option value="2">未还尽</option>
								    <option value="3">产生逾期</option>
								    <option value="4">完成还款</option>
								</select> 
						  	</td>
						  	<td class="showRight"><span class="investPro-search">平台：</span></td>
						  	<td><select style="width: 140px;" class="easyui-combobox" name="loanStyle" id="loanlistSearch_loanStyle" data-options="panelHeight:'auto'">
								    <option value="" selected="selected">--请选择--</option>   
								    <option value="1">线下</option>   
								    <option value="2">Android</option>
								    <option value="3">IOS</option>
								    <option value="4">平台系统</option>
								</select> 
						  	</td>
						</tr>
						<tr height="35px">
							<td class="showRight"><span class="investPro-search">签约日期：</span></td>
						  	<td><input name="signDate_begin" id="signDate_begin" type="text" class="easyui-datebox"></input></td>
							<td>&nbsp;&nbsp;——&nbsp;&nbsp;</td>
						  	<td><input name="signDate_end" id="signDate_end" type="text" class="easyui-datebox"></input></td>
						  	<td>&nbsp;&nbsp;
								<a id="loanlistQueryForm_Btn" class="easyui-linkbutton my-search-button" iconCls="icon-2012092109942" plain="true">查询</a>
							</td>
							<td>&nbsp;&nbsp;
								<a onclick="javascript:$('#loanlistQueryForm').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true">重置</a>
							</td>
							<td colspan="2">&nbsp;&nbsp;</td>
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
	          <a id="allot_btn_loanlist" class="easyui-linkbutton" iconCls="icon-2012080412301" plain="true">分配客户经理</a>
	  	  </div>
	</div>
	<script type="text/javascript" src="<%=path %>/ui/sys/loanlist/loanlist.js"></script>
</body>