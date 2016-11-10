<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<body class="easyui-layout">
	<div class="body" id="body_stock" region="center" style="border:0px;" >	
		  <!-- 查询条件区域 -->
		  <div id="stock_search_area" class="search_area query_conds_area">
		    <div class="conditon" id="stock_conditon">
				<form id="stockQueryForm" method="post">
					  <table border="0">
						<tr height="35px">
						  	<td class="showRight"><span class="investPro-search">借款人姓名：</span></td>
						  	<td><input name="loanname"/></td>
						 	<td class="showRight"><span class="investPro-search">借款人手机号：</span></td>
						  	<td><input name="loanphone"/></td>
						  	<td class="showRight"><span class="investPro-search">出借人姓名：</span></td>
						  	<td><input name="investname"/></td>
						 	<td class="showRight"><span class="investPro-search">出借人手机号：</span></td>
						  	<td><input name="investphone"/></td>
						</tr>
						<tr height="35px">
						  	<td class="showRight"><span class="investPro-search">状态：</span></td>
						  	<td><select style="width: 140px;" class="easyui-combobox" name="status" data-options="panelHeight:'auto'">   
								    <option value="null" selected="selected">--请选择--</option>   
								    <option value="0">仅入库</option>   
					    			<option value="1">分配到理财标</option>
					    			<option value="2">成功</option>
								</select> 
						  	</td>
						  	<td class="showRight"><span class="investPro-search">转让属性：</span></td>
						  	<td><select style="width: 140px;" class="easyui-combobox" name="istrans" data-options="panelHeight:'auto'">   
								    <option value="null" selected="selected">--请选择--</option>   
								    <option value="0">否</option>   
					    			<option value="1">是</option>
								</select> 
						  	</td>
						  	<td class="showRight"><span class="investPro-search">标名称：</span></td>
						  	<td><input name="scalename"/></td>
						  	<td colspan="2">&nbsp;&nbsp;
								<a id="stockQueryForm_Btn" class="easyui-linkbutton my-search-button" iconCls="icon-2012092109942" plain="true">查询</a>&nbsp;&nbsp;
						  		<a onclick="javascript:$('#stockQueryForm').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true">重置</a>
						  	</td>
						</tr>
					  </table>
				</form>
		    </div>
		    <span class="openOrClose" id="stock_open_close">&nbsp;&nbsp;</span>
		  </div>
		  <!-- 数据表格区域 -->
		  <table id="stock" style="table-layout:fixed;" ></table>
	 	  <!-- 表格顶部工具按钮 -->
		  <div id="tt_toolbar_stock"></div>
	</div>
	<script type="text/javascript" src="<%=path %>/ui/sys/stock/stock.js"></script>
</body>