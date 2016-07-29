<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/page/inc/inc.jsp"%>
<% String path = request.getContextPath();%>
<body style="background-color:#fff;">
	<div class="operate_div_form">
	  	<form id="power_add_form" method="post"> 
	  		<div>   
		        <label for="parentId" class="add_edit_form_label">上级权限：</label>   
		       	<input class="easyui-combotree" id="parentId" name="parentId" style="width: 216px;" data-options="valueField:'parentId',textField:'parentName',url:'syspower!getPowerTreeCheckbox.do',panelHeight:'200',editable:false,required:'true', missingMessage:'请选择上级权限！'" />
		    </div> 
		    <div>   
		        <label for="name" class="add_edit_form_label">权限名称：</label>   
				<input class="easyui-validatebox" name="name" id="powerName" data-options="required:true, missingMessage:'请填写权限名称！'"/>
		    </div>  
		    <div>   
		        <label for="url" class="add_edit_form_label">访问地址：</label>   
				<input class="easyui-validatebox" name="url" id="url" data-options="required:true, missingMessage:'请填写访问地址！'" />
		    </div>  
		    <div>   
		        <label for="icon" class="add_edit_form_label">图标样式：</label>   
		        <input class="easyui-validatebox" type="text" id="icon" name="icon" data-options="required:false" />   
		    </div>  
		    <div>   
		        <label for="sortIndex" class="add_edit_form_label">序号：</label>   
		        <input class="easyui-validatebox" type="text" id="sortIndex" name="sortIndex" data-options="required:false" />   
		    </div>  
		    <div>   
		        <label for="remark" class="add_edit_form_label">描述：</label>   
		       	<textarea rows="5" cols="20" id="remark" name="remark"></textarea>  
		    </div>
		</form>  
	</div>
	<div align="center" class="footer-oper">
   	    <div class="content-oper">
	    	 <a id="power_add_form_submitbtn" class="easyui-linkbutton my-search-button" data-options="iconCls:'icon-2012092109942'" plain="true">保存</a>
		     &nbsp;&nbsp;&nbsp;&nbsp;
		     <a onclick="javascript:$('#power_add_form').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
    	</div>
	</div>  
	<script type="text/javascript">
		$(function(){
			$('#power_add_form').form({    
			    url:"syspower!savePower.do",    
			    onSubmit: function(){    
	   				var name = encodeURI(encodeURI($.trim($("#powerName").val())));  
		   			var con = true;
			 		$.ajax({
			 			 url:"syspower!checkPowerName.do",
			 			 dataType:'json',
			 			 async: false,
			 			 data:"name="+name,
			 			 success:function(data) {
			 				 if(!data.success) {
			 				 	 $.messager.progress('close');	
			 					 $.messager.alert('错误','权限名称已经存在！','error');
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
						layer.msg("添加失败", {
			    			  icon: 5,
			    			  time: 1500
			    		});
					}
				} 
			});
		});
		
		
		/**
		 * 保存权限
		 */
		$("#power_add_form_submitbtn").on("click", function(){
			if(!$("#power_add_form").form('validate')){
				return false;
			}
			$.messager.progress();
			$("#power_add_form").submit();
		});
	</script>
</body>