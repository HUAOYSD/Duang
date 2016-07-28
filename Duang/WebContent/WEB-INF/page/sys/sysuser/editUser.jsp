<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<form id="UserEditForm" action="" method="post" data-options="novalidate:true">
	<input type="hidden" name="id" id="sysUserId" value=""/>
	<input type="hidden" id="password" name="password" >
	<table colspan=4 border="0" style="" cellspacing="8" cellpadding="5">
		<tr>
			<td align="right" style="width: 105px;">
				所属角色：
			</td>
			<td>
				<input class="easyui-combobox" id="sysRole" name="sysRole.id" style="width: 216px; height: 24px" 
					data-options="valueField:'roleId',textField:'roleName',editable:false,url:'sysrole!queryRoleList.do',panelHeight:'auto',required:'true', missingMessage:'请选择所属角色！'" />
			</td>
		</tr>
		<tr>
			<td align="right" style="width: 105px;">
				用户名：
			</td>
			<td>
				<input name="name" id="sysUserNameByEdit"  style="width: 216px; height: 24px;border: 1px solid rgb(149, 184, 231);"
					 class="easyui-validatebox " data-options="required:true, missingMessage:'请填写用户名！'"/>
			</td>
		</tr>
		<tr>
			<td align="right" style="width: 105px;">
				手机号码：
			</td>
			<td>
				<input name="phone" id="phoneByEdit"style="width: 216px; height: 24px;border: 1px solid rgb(149, 184, 231);"
					 class="easyui-validatebox " data-options="required:false,missingMessage:'！'"/>
			</td>
		</tr>
		<tr>
			<td align="right" style="width: 105px;">
				邮箱：
			</td>
			<td>
				<input name="email" id="emailByEdit"style="width: 216px; height: 24px;border: 1px solid rgb(149, 184, 231);"
					 class="easyui-validatebox " data-options="required:false,missingMessage:'！'"/>
			</td>
		</tr>
		<tr>
			<td align="right" style="width: 105px;">
				身份证号：
			</td>
			<td>
				<input name="idcard" id="idcardByEdit"style="width: 216px; height: 24px;border: 1px solid rgb(149, 184, 231);"
					 class="easyui-validatebox " data-options="required:false,missingMessage:'！'"/>
			</td>
		</tr>
		<tr>
			<td align="right" style="width: 105px;">
				备注(部门)：
			</td>
			<td>
				<input name="remark" id="remark" style="width: 216px; height: 24px;border: 1px solid rgb(149, 184, 231);"/>
			</td>
		</tr>
		</table>
		<br>
		<div  align="center">
				 <a href="javascript:;" 
				 class="easyui-linkbutton" icon="icon-ok"
				  onclick="javascript:updateSysUser()">保存</a>  
		</div>
		</form>
<script type="text/javascript">
//clearFormVal("UserAddForm");
//clearFormVal("UserEditForm");
$(function (){
	$.ajax({
		type:'GET',
		url:"sysuser!getUserInfo.do",
		data:"sysUserId=" + "<%=request.getParameter("sysUserId")%>",
		dataType:'json',
		success:function(msg) {
			$("#UserEditForm").form('load',msg);
		}
	});
});


function updateSysUser(){
	if($('#phoneByEdit').val().isNotNull()){
		if(!$('#phoneByEdit').val().isPhone()){
			$.messager.alert('警告','手机号码不合法！','warning');
			return ;
		}
	}
	if($('#emailByEdit').val().isNotNull()){
		if(!$('#emailByEdit').val().isEmail()){
			$.messager.alert('警告','邮箱地址不合法！','warning');
			return ;
		}
	}
	if($('#idcardByEdit').val().isNotNull()){
		if(!$('#idcardByEdit').val().isIDCard()){
			$.messager.alert('警告','身份证号码不合法！','warning');
			return ;
		}
	}
	var name = $('#sysUserNameByEdit').val().trim();
	$("#UserEditForm").form('submit',{
		url:"sysuser!updateSysUser.do",
		onSubmit: function() {
			if($(this).form('enableValidation').form('validate')){
	   			if(name.isNotNull()){
	   				name = encodeURI(encodeURI(name));
		   			var con = true;
			 		$.ajax({
			 			 url:"sysuser!checkSysUserName.do",
			 			 dataType:'json',
			 			 async: false,
			 			 data:"name="+name+"&userid=${param.sysUserId}",
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
		success:function(data) {
			var result = eval('(' + data + ')');
			if(result.success){
				$.messager.alert('成功','更改成功','info');
			}else{
				$.messager.alert('错误','更改失败','error');
			}
			$('#editSysUserView').window('close');
			loadUserList("sysuser!queryUserList.do");
		 }
	});
}
</script>
