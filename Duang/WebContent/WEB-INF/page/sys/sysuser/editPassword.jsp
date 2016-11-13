<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK" pageEncoding="utf-8"%>
<%@ include file="/page/inc/inc.jsp"%>
<% String path = request.getContextPath();%>
<body style="background-color:#fff;">
	<div class="operate_div_form">
	  	<form id="user_resetpwd_form" method="post"> 
			<input type="hidden" name="id" id="sysUserId" value="${param.sysUserId}">
		    <div>   
		        <label for="password" class="add_edit_form_label">新密码：</label>  
				<input name="password" id="passwordByReset" type="password" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写密码！'" />
		    </div> 
		    <div>   
		        <label for="resPass" class="add_edit_form_label">确认密码：</label>  
				<input name="resPass" id="resPassByReset" type="password" class="easyui-validatebox" data-options="required:true,missingMessage:'请再次填写密码！'" />
		    </div> 
		</form>  
	</div>
	<div align="center" class="footer-oper">
   	    <div class="content-oper">
	    	 <a id="user_resetpwd_form_submitbtn" class="easyui-linkbutton my-search-button" data-options="iconCls:'icon-2012092109942'" plain="true">保存</a>
		     &nbsp;&nbsp;&nbsp;&nbsp;
		     <a onclick="javascript:$('#user_resetpwd_form').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
    	</div>
	</div>  
	<script type="text/javascript">
		$("#user_resetpwd_form_submitbtn").on("click", function(){
			if(!$("#user_resetpwd_form").form('validate')){
				return false;
			}
			var txtNewPass = $('#passwordByReset').val().trim();
			var txtRePass = $('#resPassByReset').val().trim();
			if(txtNewPass == ""){
				$.messager.alert('警告','密码不能为空！','warning');
				return false;
			}
			
			if(txtNewPass.length <6) {
				$.messager.alert('警告','密码过于简单，请输入不低于6位的密码！','warning');
				return false;
			}
			
			if(txtNewPass != txtRePass){
				$.messager.alert('警告','两次密码输入不一致！','warning');
				return false;
			}
			$.messager.progress();
			$("#user_resetpwd_form").form('submit',{
				url:"sysuser!updatePassword.do",
				onSubmit: function() {
			 	},
				success : function(data) {
					$.messager.progress("close");
					var result = eval('(' + data + ')');
					if(result.success){
						layer.msg("更新成功",{time:1500});
			    		parent.layer.closeAll();
					}else{
						layer.msg("更新失败",{time:1500});
					}
				}
			});
		});
	</script>
</body>