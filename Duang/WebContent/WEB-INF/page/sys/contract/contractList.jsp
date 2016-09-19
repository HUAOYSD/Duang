<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<body class="easyui-layout">
	<div id="contractList_body" region="center" >
	  <!-- 查询条件区域 -->
	  <div id="contractList_search_area" class="search_area" >
	    <div class="conditon" id="contractList_conditon">
			<form id="querycontractListForm" method="post">
				  <table border="0">
					<tr height="35px">
					  	<td>&nbsp;&nbsp;<span class="investPro-search">名称：</span></td>
					  	<td ><input  name="name"/></td>
					</tr>
				  </table>
			</form>
	    </div>
	    <span class="openOrClose" id="contractList_open_close">111</span>
	  </div>
	  <!-- 数据表格区域 -->
	  <table id="contractList_table" style="table-layout:fixed;" ></table>
	</div>
	<script type="text/javascript" src="<%=path%>/ui/sys/contract/contractList.js"></script>
	<script type="text/javascript">
		var APP_PATH = "<%=path%>";
		$("#submit_contractList_btn").on("click", function(){
			$('#querycontractListForm').submit(); 
		});
	</script>
</body>