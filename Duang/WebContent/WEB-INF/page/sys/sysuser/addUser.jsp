<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/page/inc/inc.jsp"%>
<% String path = request.getContextPath();%>
<body style="background-color:#fff;">
	<div class="operate_div_form">
	  	<form id="user_add_form" method="post"> 
	  		<div>   
		        <label for="sysRole.id" class="add_edit_form_label">所属角色：</label>  
	        	<input class="easyui-combobox" id="sysRole" name="sysRole.id" style="width: 216px;"	data-options="valueField:'roleId',textField:'roleName',editable:false,url:'sysrole!queryRoleList.do',panelHeight:'auto',required:'true',missingMessage:'请选择角色！'" />
		    </div> 
		    <div>   
		        <label for="name" class="add_edit_form_label">用户名：</label>  
				<input name="name" id="sysUserName" class="easyui-validatebox " data-options="required:true,missingMessage:'请填写用户名！'"/>
		    </div> 
		    <div>   
		        <label for="phone" class="add_edit_form_label">手机号码：</label>  
				<input name="phone" id="phone" class="easyui-validatebox " data-options="required:false,missingMessage:'！'"/>
		    </div> 
		    <div>   
		        <label for="email" class="add_edit_form_label">邮箱：</label>  
				<input name="email" id="email" class="easyui-validatebox " data-options="required:false,missingMessage:'！'"/>
		    </div> 
		    <div>   
		        <label for="idcard" class="add_edit_form_label">身份证号：</label>  
				<input name="idcard" id="idcard" class="easyui-validatebox " data-options="required:false,missingMessage:'！'"/>
		    </div> 
		    <div>   
		        <label for="password" class="add_edit_form_label">登录密码：</label>  
				<input name="password" id="password" type="password" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写密码！'" />
		    </div> 
		    <div>   
		        <label for="sysUserPassRes" class="add_edit_form_label">确认密码：</label>  
				<input name="sysUserPassRes" id="sysUserPassRes" type="password" class="easyui-validatebox" data-options="required:true,missingMessage:'请再次填写密码！'" />
		    </div> 
		    <div>   
		        <label for="remark" class="add_edit_form_label">描述：</label>   
		       	<textarea rows="5" cols="20" id="remark" name="remark"></textarea>  
		    </div>
		</form>  
	</div>
	<div align="center" class="footer-oper">
   	    <div class="content-oper">
	    	 <a id="user_add_form_submitbtn" class="easyui-linkbutton my-search-button" data-options="iconCls:'icon-2012092109942'" plain="true">保存</a>
		     &nbsp;&nbsp;&nbsp;&nbsp;
		     <a onclick="javascript:$('#user_add_form').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
    	</div>
	</div>  
	<script type="text/javascript">
		$(function(){
			$('#user_add_form').form({    
			    url:"sysuser!saveSysUser.do",
			    onSubmit: function(){    
	   				var name = encodeURI(encodeURI($.trim($("#sysUserName").val())));  
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
			    },   
			    success: function(data) {
			    	$.messager.progress('close');	
					var result = eval('(' + data + ')');
					if(result.success){
						window.parent.reloadDataGrid();
			    		parent.layer.closeAll();
					}else{
						layer.msg("添加失败", {time: 1500});
					}
				} 
			});
		});
		
		
		$("#user_add_form_submitbtn").on("click", function(){
			if(!$("#user_add_form").form('validate')){
				return false;
			}
			var txtNewPass = $('#password').val().trim();
			var txtRePass = $('#sysUserPassRes').val().trim();
			var name = $('#sysUserName').val().trim();
			if(txtNewPass != txtRePass){
				$.messager.alert('警告','两次密码输入不一致！','warning');
				return false;
			};
			if(txtRePass.length <6) {
				$.messager.alert('警告','密码过于简单，请输入不低于6位的密码！','warning');
				return false;
			}
			if($('#phone').val().isNotNull()){
				if(!$('#phone').val().isPhone()){
					$.messager.alert('警告','手机号码不合法！','warning');
					return false;
				}
			}
			if($('#email').val().isNotNull()){
				if(!$('#email').val().isEmail()){
					$.messager.alert('警告','邮箱地址不合法！','warning');
					return false;
				}
			}
			if($('#idcard').val().isNotNull()){
				if(!$('#idcard').val().isIDCard()){
					$.messager.alert('警告','身份证号码不合法！','warning');
					return false;
				}
			}
			$.messager.progress();
			$("#user_add_form").submit();
		});
	</script>
</body>