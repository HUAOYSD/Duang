<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK" pageEncoding="utf-8"%>
<%@ include file="/page/inc/inc.jsp"%>
<% String path = request.getContextPath();%>
<body style="background-color:#fff;">
	<div class="operate_div_form">
	  	<form id="role_edit_form" method="post"> 
	  		<input type="hidden" name="id" id="sysRoleId">
	  		<div>   
		        <label for="roleName" class="add_edit_form_label">角色名称：</label>  
		        <input class="easyui-validatebox" name="roleName" id="sysRoleName" data-options="required:true,missingMessage:'请填写角色名称'" /> 
		    </div> 
		    <div>   
		        <label for="roleDesc" class="add_edit_form_label">角色描述：</label>   
		       	<textarea rows="5" cols="20" id="sysRoleDesc" name="roleDesc"></textarea>  
		    </div>
		</form>  
	</div>
	<div align="center" class="footer-oper">
   	    <div class="content-oper">
	    	 <a id="role_edit_form_submitbtn" class="easyui-linkbutton my-search-button" data-options="iconCls:'icon-2012092109942'" plain="true">保存</a>
		     &nbsp;&nbsp;&nbsp;&nbsp;
		     <a onclick="javascript:$('#role_edit_form').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
    	</div>
	</div>  
	<script type="text/javascript">
		$(function(){
			$.ajax({
				type:'GET',
				url:"sysrole!getRoleInfo.do",
				data:"sysRoleId=" + "<%=request.getParameter("sysRoleId")%>",
				dataType:'json',
				success:function(msg) {
					$("#role_edit_form").form('load', msg);
				}
			});
		});
		
		
		$("#role_edit_form_submitbtn").on("click", function(){
			if(!$("#role_edit_form").form('validate')){
				return false;
			}
			$("#role_edit_form").form("submit",{    
			    url:"sysrole!updateRole.do",
			    onSubmit: function(){    
	   				var name = encodeURI(encodeURI($.trim($("#sysRoleName").val())));  
	   				var id = $.trim($("#sysRoleId").val());
		   			var con = true;
			 		$.ajax({
			 			 url:"sysrole!checkRoleName.do",
			 			 dataType:'json',
			 			 async: false,
			 			 data:"name="+name+"&roleid="+id,
			 			 success:function(data) {
			 				 if(!data.success) {
			 					 //$.messager.alert('错误','角色名称已经存在，请重新填写！','error');
			 					 layer.msg("角色名称已经存在，请重新填写", {time: 1500});
			 					 con = false;
			 				 }else{
				 				  var nodes = $('#powerTree').tree('getChecked');
		  						  var powerIds = "";
		  						  $.each(nodes,function(i,n){
			  						powerIds += n["id"]+"space";
		  						  });
		  						  $("#powerIds").val(powerIds);
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