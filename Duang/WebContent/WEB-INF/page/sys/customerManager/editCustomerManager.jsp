<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK" pageEncoding="utf-8"%>
<%@ include file="/page/inc/inc.jsp"%>
<% String path = request.getContextPath();%>
<body style="background-color:#fff;">
	<div class="operate_div_form">
	  	<form id="customerManager_edit_form" method="post"> 
	  		<input type="hidden" name="id" value="${entity.id }"/>
	  		<input type="hidden" name="photo" value="${entity.photo }"/>
	  		<input type="hidden" name="qr" value="${entity.qr }"/>
	  		<input type="hidden" name="sysUser.id" value="${entity.sysUser.id }"/>
	  		<input type="hidden" name="createtime" value="${entity.createtime }"/>
	  		<input type="hidden" name="isDelete" value="${entity.isDelete }"/>
	  		<div>   
		        <label for="name" class="add_edit_form_label">姓名：</label>  
		        <input class="easyui-validatebox" name="name" id="" value="${entity.name }" data-options="required:true,validType:'chinese',missingMessage:'请填写姓名'" /> 
		    </div> 
		    <div>   
		        <label for="workNumber" class="add_edit_form_label">工号：</label>  
		        <input class="easyui-validatebox" name="workNumber" id="" value="${entity.workNumber }" data-options="required:false,missingMessage:''" /> 
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
		        <input class="easyui-validatebox" name="phone" id="" value="${entity.phone }" data-options="required:false,validType:'mobile',missingMessage:'请输入手机号'" /> 
		    </div> 
		    <div>   
		        <label for="email" class="add_edit_form_label">邮箱：</label>  
		        <input class="easyui-validatebox" name="email" id="" value="${entity.email }" data-options="required:false,validType:'email',missingMessage:'请输入邮箱'" /> 
		    </div> 
		    <div>   
		        <label for="idcard" class="add_edit_form_label">身份证：</label>  
		        <input class="easyui-validatebox" name="idcard" id="" value="${entity.idcard }" data-options="required:false,validType:'idcard',missingMessage:'请输入身份证'" /> 
		    </div> 
		    <div>   
		        <label for="remark" class="add_edit_form_label">描述：</label>   
		       	<textarea rows="5" cols="20" name="remark" id="">${entity.remark }</textarea>  
		    </div>
		</form>  
	</div>
	<div align="center" class="footer-oper">
   	    <div class="content-oper">
	    	 <a id="customerManager_edit_form_submitbtn" class="easyui-linkbutton my-search-button" data-options="iconCls:'icon-2012092109942'" plain="true">保存</a>
		     &nbsp;&nbsp;&nbsp;&nbsp;
		     <a onclick="javascript:$('#customerManager_edit_form').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
    	</div>
	</div>  
	<script type="text/javascript">
		$(function(){
			var data = '{"sex":"${entity.sex}"}';
			$("#customerManager_edit_form").form('load', eval('(' + data + ')'));
		});
		
		
		$("#customerManager_edit_form_submitbtn").on("click", function(){
			if(!$("#customerManager_edit_form").form('validate')){
				return false;
			}
			$.messager.progress();
			$("#customerManager_edit_form").form("submit",{    
			    url:"customermanager!updateCustomerManager.do",
			    success: function(data) {
			    	$.messager.progress("close");	
					var result = eval('(' + data + ')');
					if(result.success){
						layer.msg("编辑成功", {time: 1500 });
						window.parent.reloadDataGrid();
			    		parent.layer.closeAll();
					}else{
						layer.msg("编辑失败", {time: 1500 });
					}
				} 
			});
		});
	</script>
</body>