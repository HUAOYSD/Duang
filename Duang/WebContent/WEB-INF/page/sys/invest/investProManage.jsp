<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<body class="easyui-layout">
	<div id="body" region="center" >
	  <!-- 查询条件区域 -->
	  <div id="search_area" >
	    <div id="conditon">
			<form id="queryInvestProForm" method="post">
				  <table border="0">
					<tr>
					  	<td><span class="investPro-search">总名称：</span></td>
					  	<td ><input  name="nameZh" id="nameZh"   /></td>
					 	<td>&nbsp;<span class="investPro-search">名称：</span></td>
					  	<td><input  name="name" id="name"  /></td>
					  <td>
						  <a  id="submitInvest_btn" class="easyui-linkbutton my-search-button" iconCls="icon-2012092109942" plain="true">查询</a>
						  <a  onclick="javascript:c$.clearForm(document.getElementById('queryInvestProForm'))" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
					  </td>
					</tr>
				  </table>
			</form>
	    </div>
	    <span id="openOrClose">111</span>
	  </div>
	  <!-- 数据表格区域 -->
	  <table id="tt" style="table-layout:fixed;" ></table>
	  <!-- 表格顶部工具按钮 -->
	  <div id="tt_toolbar">
	      <a id="add" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
	      <a href="javascript:void(0)"  id="update" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a> 
	      <a href="javascript:void(0)"  id="delete" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
  	  </div>
	</div>
	<script type="text/javascript" src="<%=path%>/ui/sys/investpro/investProManage.js"></script>
	<script type="text/javascript">
		$("#submitInvest_btn").on("click", function(){
			$('#queryInvestProForm').submit(); 
		});
		
		$("#add").on("click",function(){
			layer.open({
				type: 2,
				title: '添加产品',
				shadeClose: true,
				shade: 0.8,
				area: ['380px', '90%'],
				content: 'investpro!addInvestPro.do'
			});  
		});
	</script>
</body>