<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<form id="userPasswordForm" action="" method="post" data-options="novalidate:true">
	<input type="hidden" name="id" id="sysUserId" value="${param.sysUserId}">
	<table colspan=4 border="0" style="width: 400px;" cellspacing="8" cellpadding="5">
		<tr>
			<td align="right" style="width: 105px;">
				新密码：
			</td>
			<td>
				<input name="password" id="passwordByReset" type="password" style="width: 220px; height: 24px"
					 class="easyui-validatebox" data-options="required:true,missingMessage:'请填写密码！'" />
			</td>
		</tr>
		<tr>	
			<td align="right" style="width: 105px;">
				确认密码：
			</td>
			<td>
				<input name="resPass" id="resPassByReset" type="password" style="width: 220px; height: 24px"
					  	 class="easyui-validatebox" data-options="required:true,missingMessage:'请再次填写密码！'" />
			</td>
		</tr>
	</table>	
		<div  align="center" >
				 <a href="javascript:;" 
				 class="easyui-linkbutton" icon="icon-ok"
				  onclick="javascript:updateSysUserPassword()">保存</a>  
		</div>
		</form>
<script type="text/javascript">
function updateSysUserPassword() { 
	var txtNewPass = $('#passwordByReset').val().trim();
	var txtRePass = $('#resPassByReset').val().trim();
	if(txtNewPass == ""){
		$.messager.alert('警告','密码不能为空！','warning');
		return ;
	}
	
	if(txtNewPass.length <6) {
		$.messager.alert('警告','密码过于简单，请输入不低于6位的密码！','warning');
		return ;
	}
	
	if(txtNewPass != txtRePass){
		$.messager.alert('警告','两次密码输入不一致！','warning');
		return ;
	}
	$("#userPasswordForm").form('submit',{
		url:"sysuser!updatePassword.do",
		onSubmit: function() {
			if($(this).form('enableValidation').form('validate')){
			 	return true;
	   		}else{
	 			return false;
	 		}
	 	},
		success : function(data) {
			var result = eval('(' + data + ')');
			if(result.success){
				$.messager.alert('成功','更新成功','info');
			}else{
				$.messager.alert('错误','更新失败','error');
			}
			$('#editPasswordView').window('close');
		}
	});
}
</script>
