<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<script type="text/javascript">
	 	var begin_date = "${begin_date}";
	 	var end_date = "${end_date}";
</script>
<body class="easyui-layout">
	<div class="body" id="body_loanlist" region="center" style="border:0px;" >	
		  <!-- 查询条件区域 -->
		  <div id="loanlist_search_area" class="search_area query_conds_area">
		    <div class="conditon" id="loanlist_conditon">
				<form id="loanlistQueryForm" method="post">
					  <input type="hidden" name="scaleid" id="scaleid" value="${scaleid}"/>
					  <table border="0">
						<tr height="35px">
							<td class="showRight"><span class="investPro-search">审核通过日期：</span></td>
						  	<td><input name="begin_date" id="begin_date" type="text" class="easyui-datebox"></input></td>
							<td>——</td>
						  	<td><input name="end_date" id="end_date" type="text" class="easyui-datebox"></input></td>
						  	<td>&nbsp;
								<a id="loanlistQueryForm_Btn" class="easyui-linkbutton my-search-button" iconCls="icon-2012092109942" plain="true">查询</a>
							</td>
							<td>
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
	 	  <div class="footer-btn">
				<button type="button" class="btn btn-primary" id="chose_loanlist_scale">下一步</button>
		  </div>
	</div>
	<script type="text/javascript" src="<%=path %>/ui/sys/loanlist/allotLoanlist.js"></script>
</body>