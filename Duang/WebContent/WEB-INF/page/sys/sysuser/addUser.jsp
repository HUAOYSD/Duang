<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<form id="UserAddForm" action="" method="post" data-options="novalidate:true">
	<table colspan=4 border="0" style="" cellspacing="8" cellpadding="5">
		<tr>
			<td align="right" style="width: 105px;">
				所属角色：
			</td>
			<td>
				<input class="easyui-combobox" id="sysRole" name="sysRole.id" style="width: 216px; height: 24px;border: 1px solid rgb(149, 184, 231);" 
					data-options="valueField:'roleId',textField:'roleName',editable:false,url:'sysrole!queryRoleList.do',panelHeight:'auto',required:'true',missingMessage:'请选择角色！'" />
			</td>
		</tr>
		<tr>
			<td align="right" style="width: 105px;">
				用户名：
			</td>
			<td>
				<input name="name" id="sysUserName"style="width: 216px; height: 24px;border: 1px solid rgb(149, 184, 231);"
					 class="easyui-validatebox " data-options="required:true,missingMessage:'请填写用户名！'"/>
			</td>
		</tr>
		<tr>
			<td align="right" style="width: 105px;">
				手机号码：
			</td>
			<td>
				<input name="phone" id="phone"style="width: 216px; height: 24px;border: 1px solid rgb(149, 184, 231);"
					 class="easyui-validatebox " data-options="required:false,missingMessage:'！'"/>
			</td>
		</tr>
		<tr>
			<td align="right" style="width: 105px;">
				邮箱：
			</td>
			<td>
				<input name="email" id="email"style="width: 216px; height: 24px;border: 1px solid rgb(149, 184, 231);"
					 class="easyui-validatebox " data-options="required:false,missingMessage:'！'"/>
			</td>
		</tr>
		<tr>
			<td align="right" style="width: 105px;">
				身份证号：
			</td>
			<td>
				<input name="idcard" id="idcard"style="width: 216px; height: 24px;border: 1px solid rgb(149, 184, 231);"
					 class="easyui-validatebox " data-options="required:false,missingMessage:'！'"/>
			</td>
		</tr>
		<tr>
			<td align="right" style="width: 105px;">
				备注(部门)：
			</td>
			<td>
				<input name="remark" id="remark"style="width: 216px; height: 24px;border: 1px solid rgb(149, 184, 231);"/>
			</td>
		</tr>
		<tr>	
			<td align="right" style="width: 105px;">
				登录密码：
			</td>
			<td>
				<input  name="password" id="password" type="password" style="width: 216px; height: 24px;border: 1px solid rgb(149, 184, 231);"
					 class="easyui-validatebox" data-options="required:true,missingMessage:'请填写密码！'" />
			</td>
		</tr>
		<tr>	
			<td align="right" style="width: 105px;">
				确认密码：
			</td>
			<td>
				<input  name="sysUserPassRes" id="sysUserPassRes" type="password" style="width: 216px; height: 24px;border: 1px solid rgb(149, 184, 231);"
					 class="easyui-validatebox" data-options="required:true,missingMessage:'请再次填写密码！'" />
			</td>
		</tr>
		</table>
		<br>
		<div  align="center">
				 <a href="javascript:;" 
				 class="easyui-linkbutton" icon="icon-ok"
				  onclick="javascript:addSysUser()">保存</a>  
		</div>
		</form>
<script type="text/javascript">
clearFormVal("UserAddForm");
clearFormVal("UserEditForm");

function addSysUser() {
	var txtNewPass = $('#password').val().trim();
	var txtRePass = $('#sysUserPassRes').val().trim();
	var name = $('#sysUserName').val().trim();
	if(txtNewPass != txtRePass){
		$.messager.alert('警告','两次密码输入不一致！','warning');
		return ;
	};
	if(txtRePass.length <6) {
		$.messager.alert('警告','密码过于简单，请输入不低于6位的密码！','warning');
		return ;
	}
	if($('#phone').val().isNotNull()){
		if(!$('#phone').val().isPhone()){
			$.messager.alert('警告','手机号码不合法！','warning');
			return ;
		}
	}
	if($('#email').val().isNotNull()){
		if(!$('#email').val().isEmail()){
			$.messager.alert('警告','邮箱地址不合法！','warning');
			return ;
		}
	}
	if($('#idcard').val().isNotNull()){
		if(!$('#idcard').val().isIDCard()){
			$.messager.alert('警告','身份证号码不合法！','warning');
			return ;
		}
	}
	$("#UserAddForm").form('submit',{
		url:"sysuser!saveSysUser.do",
		onSubmit: function() {
			if($(this).form('enableValidation').form('validate')){
	   			if(name.isNotNull()){
	   				name = encodeURI(encodeURI(name));
		   			var con = true;
			 		$.ajax({
			 			 url:"sysuser!checkSysUserName.do",
			 			 dataType:'json',
			 			 async: false,
			 			 data:"name="+name,
			 			 success:function(data) {
			 				 if(!data.success) {
			 					 $.messager.alert('错误','用户名称已经存在，请重新填写！','error');
			 					 con = false;
			 				 }
			 			 }
			 		 });
			 		return con;
		   		}else{
		   			return false;
		   		}
	   		}else{
	 			return false;
	 		}
	 	},
		success : function(data) {
				var result = eval('(' + data + ')');
				if(result.success){
					$.messager.alert('成功','添加成功','info');
				}else{
					$.messager.alert('错误','添加失败','error');
				}
				$('#addSysUserView').window('close');
				loadUserList("sysuser!queryUserList.do");
		}
	});
}
</script>
