<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK" pageEncoding="utf-8"%>
<%@ include file="/page/inc/inc.jsp"%>
<% String path = request.getContextPath();%>
<body style="background-color:#fff;">
	<div class="operate_div_form">
	  	<form id="customerManager_add_form" method="post"> 
	  		<div>   
		        <label for="name" class="add_edit_form_label">姓名：</label>  
		        <input class="easyui-validatebox" name="name" id="" data-options="required:true,validType:'chinese',missingMessage:'请填写姓名'" /> 
		    </div> 
		    <div>   
		        <label for="sysUser.name" class="add_edit_form_label">ID：</label>  
		        <input class="easyui-validatebox" name="sysUser.name" id="sysUserName" data-options="required:true,validType:'english',missingMessage:'请填写ID'" /> 
		    </div> 
		    <div>   
		        <label for="workNumber" class="add_edit_form_label">工号：</label>  
		        <input class="easyui-validatebox" name="workNumber" id="" data-options="required:false,missingMessage:''" /> 
		    </div> 
		    <div>   
		        <label for="sex" class="add_edit_form_label">性别：</label>  
		        <select class="easyui-combobox" name="sex" style="width: 216px;" data-options="panelHeight:'auto'">   
				    <option value="男" selected="selected">男</option>   
				    <option value="女">女</option>
				    <option value="保密">保密</option>   
				</select>  
		    </div> 
		    <div>   
		        <label for="phone" class="add_edit_form_label">手机号：</label>  
		        <input class="easyui-validatebox" name="phone" id="" data-options="required:false,validType:'mobile',missingMessage:'请输入手机号'" /> 
		    </div> 
		    <div>   
		        <label for="email" class="add_edit_form_label">邮箱：</label>  
		        <input class="easyui-validatebox" name="email" id="" data-options="required:false,validType:'email',missingMessage:'请输入邮箱'" /> 
		    </div> 
		    <div>   
		        <label for="idcard" class="add_edit_form_label">身份证：</label>  
		        <input class="easyui-validatebox" name="idcard" id="" data-options="required:false,validType:'idcard',missingMessage:'请输入身份证'" /> 
		    </div> 
		    <div>   
		        <label for="remark" class="add_edit_form_label">描述：</label>   
		       	<textarea rows="5" cols="20" name="remark" id=""></textarea>  
		    </div>
		</form>  
	</div>
	<div align="center" class="footer-oper">
   	    <div class="content-oper">
	    	 <a id="customerManager_add_form_submitbtn" class="easyui-linkbutton my-search-button" data-options="iconCls:'icon-2012092109942'" plain="true">保存</a>
		     &nbsp;&nbsp;&nbsp;&nbsp;
		     <a onclick="javascript:$('#customerManager_add_form').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
    	</div>
	</div>  
	<script type="text/javascript">
		$("#customerManager_add_form_submitbtn").on("click", function(){
			if(!$("#customerManager_add_form").form('validate')){
				return false;
			}
			$("#customerManager_add_form").form("submit",{    
			    url:"customermanager!saveCustomerManager.do",
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
			 					 layer.msg("用户ID已经存在，请重新填写", {time: 1500});
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
						layer.msg("添加成功", {time: 1500 });
						window.parent.reloadDataGrid();
			    		parent.layer.closeAll();
					}else{
						layer.msg("添加失败", {time: 1500 });
					}
				} 
			});
		});
	</script>
</body>