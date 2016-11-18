<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK" pageEncoding="utf-8"%>
<%@ include file="/page/inc/inc.jsp"%>
<% String path = request.getContextPath();%>
<body style="background-color:#fff;">
	<div class="operate_div_form">
	  	<form id="award_edit_form" method="post" style="margin-bottom: 45px;"> 
	  		<input type="hidden" name="id" value="${entity.id }"/>
	  		<input type="hidden" name="state" value="${entity.state }"/>
	  		<div>   
		        <label for="name" class="add_edit_form_label">名称：</label>  
		        <input class="easyui-validatebox" name="name" value="${entity.name }" data-options="required:true,missingMessage:'请填写名称'" /> 
		    </div> 
		    <div>   
		        <label for="description" class="add_edit_form_label">描述：</label>  
		        <input type="text" name="description" value="${entity.description}"  style="width: 216px;"/>
		    </div> 
		</form>  
	</div>
	<div align="center" class="footer-oper">
   	    <div class="content-oper">
	    	 <a id="award_edit_form_submitbtn" class="easyui-linkbutton my-search-button" data-options="iconCls:'icon-2012092109942'" plain="true">保存</a>
		     &nbsp;&nbsp;&nbsp;&nbsp;
		     <a onclick="javascript:$('#award_edit_form').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
    	</div>
	</div>  
	<script type="text/javascript">
		
		$("#award_edit_form_submitbtn").on("click", function(){
			if(!$("#award_edit_form").form('validate')){
				return false;
			}
			$.messager.progress();
			$("#award_edit_form").form("submit",{    
			    url:"award!update.do",
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