<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK" pageEncoding="utf-8"%>
<%@ include file="/page/inc/inc.jsp"%>
<% String path = request.getContextPath();%>
<body style="background-color:#fff;">
	<div class="operate_div_form">
	  	<form id="user_edit_form" method="post"> 
			<input type="hidden" name="id" id="sysUserId" value=""/>
			<input type="hidden" id="password" name="password" >
	  		<div>   
		        <label for="sysRole.id" class="add_edit_form_label">所属角色：</label>  
	        	<input class="easyui-combobox" id="sysRole" name="sysRole.id" style="width: 216px;"	data-options="valueField:'roleId',textField:'roleName',editable:false,url:'sysrole!queryRoleList.do',panelHeight:'auto',required:'true',missingMessage:'请选择角色！'" />
		    </div> 
		    <div>   
		        <label for="name" class="add_edit_form_label">用户名：</label>  
				<input name="name" id="sysUserName" class="easyui-validatebox " data-options="required:true,missingMessage:'请填写用户名！'"/>
		    </div> 
			<!-- 		    <div>    -->
			<!-- 		        <label for="phone" class="add_edit_form_label">手机号码：</label>   -->
			<!-- 				<input name="phone" id="phone" class="easyui-validatebox " data-options="required:false,missingMessage:'！'"/> -->
			<!-- 		    </div>  -->
			<!-- 		    <div>    -->
			<!-- 		        <label for="email" class="add_edit_form_label">邮箱：</label>   -->
			<!-- 				<input name="email" id="email" class="easyui-validatebox " data-options="required:false,missingMessage:'！'"/> -->
			<!-- 		    </div>  -->
			<!-- 		    <div>    -->
			<!-- 		        <label for="idcard" class="add_edit_form_label">身份证号：</label>   -->
			<!-- 				<input name="idcard" id="idcard" class="easyui-validatebox " data-options="required:false,missingMessage:'！'"/> -->
			<!-- 		    </div>  -->
		    <div>   
		        <label for="remark" class="add_edit_form_label">描述：</label>   
		       	<textarea rows="5" cols="20" id="remark" name="remark"></textarea>  
		    </div>
		</form>  
	</div>
	<div align="center" class="footer-oper">
   	    <div class="content-oper">
	    	 <a id="user_edit_form_submitbtn" class="easyui-linkbutton my-search-button" data-options="iconCls:'icon-2012092109942'" plain="true">保存</a>
		     &nbsp;&nbsp;&nbsp;&nbsp;
		     <a onclick="javascript:$('#user_edit_form').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
    	</div>
	</div>  
	<script type="text/javascript">
		$(function(){
			$.ajax({
				type:'GET',
				url:"sysuser!getUserInfo.do",
				data:"sysUserId=" + "<%=request.getParameter("sysUserId")%>",
				dataType:'json',
				success:function(msg) {
					$("#user_edit_form").form('load',msg);
				}
			});
		});
		
		$("#user_edit_form_submitbtn").on("click", function(){
			if(!$("#user_edit_form").form('validate')){
				return false;
			}
			// 			if($('#phone').val().isNotNull()){
			// 				if(!$('#phone').val().isPhone()){
			// 					$.messager.alert('警告','手机号码不合法！','warning');
			// 					return false;
			// 				}
			// 			}
			// 			if($('#email').val().isNotNull()){
			// 				if(!$('#email').val().isEmail()){
			// 					$.messager.alert('警告','邮箱地址不合法！','warning');
			// 					return false;
			// 				}
			// 			}
			// 			if($('#idcard').val().isNotNull()){
			// 				if(!$('#idcard').val().isIDCard()){
			// 					$.messager.alert('警告','身份证号码不合法！','warning');
			// 					return false;
			// 				}
			// 			}
			$("#user_edit_form").form("submit",{    
			    url:"sysuser!updateSysUser.do",
			    onSubmit: function(){    
	   				var name = encodeURI(encodeURI($.trim($("#sysUserName").val())));  
		   			var con = true;
			 		$.ajax({
			 			 url:"sysuser!checkSysUserName.do",
			 			 dataType:'json',
			 			 async: false,
			 			 data:"name="+name+"&userid=${param.sysUserId}",
			 			 success:function(data) {
			 				 if(!data.success) {
		  						 layer.msg("用户名称已经存在，请重新填写", {time: 1500});
			 					 con = false;
			 				 }
			 			 }
			 		 });
			 		 if(con){
			 			$.messager.progress();
			 		 }
			 		 return con;
			    },   
			    success: function(data) {
			    	$.messager.progress("close");	
					var result = eval('(' + data + ')');
					if(result.success){
						layer.msg("编辑成功", {time: 1500});
						window.parent.reloadDataGrid();
			    		parent.layer.closeAll();
					}else{
						layer.msg("编辑失败", {time: 1500});
					}
				} 
			});
		});
	</script>
</body>