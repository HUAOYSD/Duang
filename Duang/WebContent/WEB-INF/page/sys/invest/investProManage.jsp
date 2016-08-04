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
	      <a id="add_btn" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
	      <a id="investProManage-update-btn" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a> 
	      <a id="investProManage-delete-btn" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
  	  </div>
	</div>
	<script type="text/javascript" src="<%=path%>/ui/sys/investpro/investProManage.js"></script>
	<script type="text/javascript">
		$("#submitInvest_btn").on("click", function(){
			$('#queryInvestProForm').submit(); 
		});
		function reloadDataGrid(){
	        $("#tt").datagrid('reload');  
		}
		function saveError(msg,iconNum){
			layer.msg(msg, {
  			  icon: iconNum,
  			  time: 3000 //2秒关闭（如果不配置，默认是3秒）
  			});
		}
		var indexLayer;
		//添加
		$("#add_btn").on("click",function(){
			indexLayer = layer.open({
				type: 2,
				title: '添加产品',
				shadeClose: true,
				shade: 0.8,
				area: ['450px', '850px'],
				content: 'investpro!addInvestPro.do'
			});  
		});
		
		$("#investProManage-update-btn").on("click",function(){
			var selectedRow = $("#tt").datagrid('getSelected');
			if(selectedRow==null){
				layer.msg("请选择一个产品！",{time:1000});
				return;
			}
			indexLayer = layer.open({
				type: 2,
				title: '添加产品',
				shadeClose: true,
				shade: 0.8,
				area: ['450px', '850px'],
				content: 'investpro!editInvestPro.do?id='+selectedRow.id
			}); 
		});
		
		//删除
		$("#investProManage-delete-btn").on('click',function(){
			var selectedRow = $("#tt").datagrid('getSelected'); 
			if(selectedRow==null){
				layer.msg("请选择一个产品！",{time:1000});
				return;
			}
		    layer.confirm('您确定要删除产品'+selectedRow.nameZh+' 吗？', {
				  icon: 7,
				  btn: ['确定','取消'] //按钮
				}, function(){ //确定
					$.messager.progress('close');	// 如果提交成功则隐藏进度条
					$.ajax({
						   type: "POST",
						   url: "investpro!deleteInvestPro.do",
						   data: "id="+selectedRow.id,
						   success: function(data){
							 data = JSON.parse(data);
						     if(data.result==true){
						    	 var selectedRowIndex = $("#tt").datagrid('getRowIndex',selectedRow);
						    	 $("#tt").datagrid('deleteRow',selectedRowIndex);
						    	 layer.closeAll();
						     }
					    	 layer.msg(data.msg, {time: 1000});
						   }
						});
				}, function(){//取消
				  return;
			});
		});
	</script>
</body>