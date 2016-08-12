<%@page import="org.duang.entity.InvestMember"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% 
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ include file="/page/inc/inc.jsp"%>
<body style="background-color: #fff;" class="bodyContent">
	<div class="content">
		<form id="loanMemberAddSaveForm" method="post">
			<h1>基本信息</h1>
			<div>
				<label for="memberInfo.loginName" class="from_label">登录名：</label> <input
					class="easyui-validatebox" type="text" name="memberInfo.loginName"
					data-options="required:true" />
			</div>
			<div>
				<label for="memberInfo.realName" class="from_label">真实姓名：</label> <input
					class="easyui-validatebox" type="text" name="memberInfo.realName"
					data-options="validType:'name'" />
			</div>
			<div>
				<label for="memberInfo.nickname" class="from_label">昵称：</label> <input
					class="easyui-validatebox" type="text" name="memberInfo.nickname"
					data-options="validType:'name'" />
			</div>
			<div>
				<label for="memberInfo.password" class="from_label">密码：</label> <input
					class="easyui-validatebox" type="password"
					name="memberInfo.password" />
			</div>
			<div>
				<label for="memberInfo.phone" class="from_label">手机：</label> <input
					class="easyui-validatebox" type="text" name="memberInfo.phone"
					data-options="validType:'mobile'" />
			</div>
			<div>
				<label for="memberInfo.idCard" class="from_label">身份证号：</label> <input
					type="text" name="memberInfo.idCard" class="easyui-validatebox"
					data-options="validType:'idcard'" />
			</div>
			<div>
				<label for="memberInfo.email" class="from_label">邮箱：</label> <input
					class="easyui-validatebox" type="text" name="memberInfo.email"
					data-options="validType:'email'" />
			</div>
			<div>
				<label for="memberInfo.age" class="from_label">年龄：</label> <input
					class="easyui-numberbox" value="18" data-options="min:18,max:150"
					type="text" name="memberInfo.age" />
			</div>

			<div>
				<label for="memberInfo.sex" class="from_label">性别：</label> <select
					class="easyui-combobox" name="memberInfo.sex"
					data-options="panelHeight:'auto'">
					<option value="1" selected="selected">男</option>
					<option value="0">女</option>
					<option value="2">保密</option>
				</select>
			</div>
			<div>
				<label for="memberInfo.isEliteAccount" class="from_label">是否为金账户：</label>
				<select class="easyui-combobox" name="memberInfo.isEliteAccount"
					data-options="panelHeight:'auto'">
					<option value="0" selected="selected">否</option>
					<option value="1">是</option>
				</select>
			</div>
			<div>
				<label for="memberInfo.type" class="from_label">用户类型：</label> <select
					class="easyui-combobox" name="memberInfo.type"
					data-options="panelHeight:'auto'">
					<option value="0" selected="selected">个体用户</option>
					<option value="1">企业用户</option>
				</select>
			</div>
			<div>
				<label for="memberInfo.level" class="from_label">级别：</label> <select
					class="easyui-combobox" name="memberInfo.level"
					data-options="panelHeight:'auto'">
					<option value="1" selected="selected">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
				</select>
			</div>
			<div>
				<label for="customerManager.id" class="from_label">客户经理姓名：</label> <input
					class="easyui-combobox" name="customerManager.id"
					data-options="    
				        valueField: 'id',
				        panelHeight:'auto',    
				        textField: 'name',
				        editable:false,
				        panelHeight:'auto',
				        missingMessage:'请选择角色！',   
				        url: 'customermanager!queryAllCusManagerIdAndName.do',
				        onSelect: function(rec){   
				        }" />
			</div>
			<div>
				<label for="memberInfo.price" class="from_label">财力值：</label> <input
					class="easyui-numberbox" value="0" data-options="min:0" type="text"
					name="memberInfo.price" />万元
			</div>
			<div>
				<label for="memberInfo.miDescribe" class="from_label">介绍：</label>
				<textarea rows="5" cols="30" name="memberInfo.miDescribe"></textarea>
			</div>
		</form>
	</div>
	<div align="center" class="footer-oper">
		<div class="content-oper">
			<a id="submitLoanMember_add_btn"
				class="easyui-linkbutton my-search-button"
				data-options="iconCls:'icon-2012092109942'" plain="true">保存</a>
			&nbsp;&nbsp;&nbsp;&nbsp; <a
				onclick="javascript:$('#loanMemberAddSaveForm').form('reset');"
				class="easyui-linkbutton my-search-button" iconCls="icon-reset"
				plain="true">重置</a>
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
			$('#loanMemberAddSaveForm').form({    
			    url:"loanmember!saveLoanMember.do",    
			    onSubmit: function(){    
			        
			    },    
			    success:function(data){ 
			    	$.messager.progress('close');	// 如果提交成功则隐藏进度条
			    	data = JSON.parse(data);
			    	console.info(data);
			    	if(data.result==true){
			    		window.parent.reloadDataGrid();
			    		parent.layer.closeAll();
			    	}else{
			    		layer.msg(data.msg, {
			    			  icon: 5,
			    			  time: 3000 //2秒关闭（如果不配置，默认是3秒）
			    		});
			    	}
			    }    
			});
		});
		$("#submitLoanMember_add_btn").on("click", function(){
			if(!$("#loanMemberAddSaveForm").form('validate')){
				return false;
			}
			$.messager.progress();
			$('#loanMemberAddSaveForm').submit();
		});
	</script>
</body>