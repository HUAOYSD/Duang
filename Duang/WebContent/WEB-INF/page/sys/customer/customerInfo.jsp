<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>后台管理系统</title>
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/home.css">
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/left.css">
		<link rel="stylesheet" type="text/css" href="<%=path%>/js/easyui/themes/gray/easyui.css">
		<link rel="stylesheet" type="text/css" href="<%=path%>/js/easyui/themes/icon.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/js/easyui/themes/IconExtension.css" />
		<script type="text/javascript" src="<%=path%>/js/easyui/jquery-1.10.2.min.js"></script>
		<script type="text/javascript" src="<%=path%>/js/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<%=path%>/js/home.js"></script>
		<script type="text/javascript" src="<%=path%>/js/extends.js"></script>
	</head>
	<body class="easyui-layout">
		<div id="body" region="center" >
		  <!-- 查询条件区域 -->
		  <div id="search_area" >
		    <div id="conditon">
				<form id="queryUserForm">
					  <table border="0">
						<tr>
						  	<td>用户名：</td>
						  	<td ><input  name="userName" id="userName"   /></td>
						 	<td>&nbsp;性别：</td>
						  	<td><input  name="sex" id="sex"  /></td>
						  	<td>&nbsp;日期：</td>
						  	<td><input class="easyui-datebox"  name="department" id="department"  /></td>
							<td>&nbsp;语言：</td>
						  	<td>
								<select class="easyui-combobox" panelHeight="auto" style="width:100px">
								  <option value="java">Java</option>
								  <option value="c">C</option>
								  <option value="basic">Basic</option>
								  <option value="perl">Perl</option>
								  <option value="python">Python</option>
						  		</select>
						 	 </td>
						  <td>
							  <a  href="javascript:void(0)" class="easyui-linkbutton my-search-button" iconCls="icon-2012092109942" plain="true">查询</a>
							  <a  href="javascript:void(0)" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
						  </td>
						</tr>
					  </table>
				</form>
		    </div>
		  </div>
		  <!-- 数据表格区域 -->
		  <table id="tt" style="table-layout:fixed;" ></table>
		  <!-- 表格顶部工具按钮 -->
		  <div id="tt_btn">
		      <a href="javascript:void(0)"  id="save" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
		      <a href="javascript:void(0)"  id="update" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a> 
		      <a href="javascript:void(0)"  id="delete" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		   </div>
		</div>
		<script type="text/javascript" src="<%=path%>/js/layer/layer.js"></script>
		<script>
			var tableObj;
			var editRow = undefined; //定义全局变量：当前编辑的行
			$(function(){
				tableObj = $("#tt").datagrid({
					height:$("#body").height()-$('#search_area').height()-5,
					width:$("#body").width(),
					idField:'userId',
					loadMsg : "正在加载，请稍后...",
					url:"datagrid.json",  
					singleSelect:true, 
					nowrap:true,
					fitColumns:true,
					rownumbers:true,
					pagination:true,
					pageSize:10,
					pageList:[10,20,30,40,50],
					sortName:'userName',
					sortOrder:'desc',
					columns:[[
						{field:'userName',title:'用户名',width:100,halign:"center", align:"left",sortable:true,editor: { type: 'validatebox', options: { required: true}}},
						{field:'name',title:'姓名',width:100,halign:"center", align:"left",editor:'text'},
						{field:'sex',title:'性别',width:100,halign:"center", align:"left",editor:'text'},
						{field:'department',title:'部门',width:100,halign:"center", align:"left",editor:'text'}
					]],
					toolbar:'#tt_btn',
					onDblClickRow:function(rowIndex, rowData){
						//双击开启编辑行
						if (editRow != undefined) {
							tableObj.datagrid("endEdit", editRow);
						}
						if (editRow == undefined) {
							tableObj.datagrid("beginEdit", rowIndex);
							editRow = rowIndex;
						}
					},
					onAfterEdit: function (rowIndex, rowData, changes) {
						//endEdit该方法触发此事件
						console.info(rowData);
						editRow = undefined;
					}
				});
				
				//新增弹出框
				$("#save").on("click", function(){
					
				});
				//修改
				$("#update").on("click", function(){
					$parent.messager.alert("提示","update", "info");
				});
				//删除
				$("#delete").on("click", function(){
					//获取选择的行
					var selectedRow = tableObj.datagrid('getSelected');
					//获取选择行的索引值index
					var selectedRowIndex = tableObj.datagrid('getRowIndex',selectedRow);
					//从表格中移除index
					tableObj.datagrid('deleteRow',selectedRowIndex);
				});
			
				$('#queryUserForm').form('submit', {
					url: '',
					onSubmit: function(){
						var isValid = $(this).form('validate');
						if (!isValid){
			
						}
						return isValid;	// 返回false终止表单提交
					},
					success: function(){
			
					}
				});
			});
			
			//监听窗口大小变化
			window.onresize = function(){
				setTimeout(domresize,300);
			};
			//改变表格宽高
			function domresize(){
				$('#tt').datagrid('resize',{  
					height:$("#body").height()-$('#search_area').height()-5,
					width:$("#body").width()
				});
			}
		
		</script>
	</body>
</html>