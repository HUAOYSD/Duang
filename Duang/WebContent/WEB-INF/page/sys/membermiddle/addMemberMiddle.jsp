<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK" pageEncoding="utf-8"%>
<%@ include file="/page/inc/inc.jsp"%>
<% String path = request.getContextPath();%>
<body style="background-color:#fff;">
	<div class="operate_div_form">
	  	<form id="memberMiddle_add_form" method="post" style="margin-bottom: 45px;"> 
	  		<div>   
		        <label for="userName" class="add_edit_form_label">姓名：</label>  
		        <input class="easyui-validatebox" name="userName" data-options="required:true,missingMessage:'请填写名称'" /> 
		    </div> 
		    <div>   
		       <label for="idcard" class="add_edit_form_label">证件号：</label>  
		       <input class="easyui-validatebox" name="idcard" data-options="required:true,missingMessage:'请填写证件号'" /> 
		    </div>
		    <div>   
		        <label for="payType" class="add_edit_form_label">手续费类型：</label>  
		        <select class="easyui-combobox" name="payType" style="width: 216px;" data-options="panelHeight:'auto'">   
				    <option value="1" selected="selected">从可用账户收取手续费</option>   
				    <option value="2">从预付账户收取手续费</option>   
				</select>  
		    </div>  
		</form>  
	</div>
	<div align="center" class="footer-oper">
   	    <div class="content-oper">
	    	 <a id="memberMiddle_add_form_submitbtn" class="easyui-linkbutton my-search-button" data-options="iconCls:'icon-2012092109942'" plain="true">保存</a>
		     &nbsp;&nbsp;&nbsp;&nbsp;
		     <a onclick="javascript:$('#memberMiddle_add_form').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
    	</div>
	</div>  
	<script type="text/javascript">
		$("#memberMiddle_add_form_submitbtn").on("click", function(){
			if(!$("#memberMiddle_add_form").form('validate')){
				return false;
			}
			$.messager.progress();
			$("#memberMiddle_add_form").form("submit",{    
			    url:"memberMiddle!add.do",
			    success: function(data) {
			    	$.messager.progress("close");	
					var result = eval('(' + data + ')');
					if(result.success){
						layer.msg("添加成功", {time: 1500 });
						window.parent.reloadDataGrid();
			    		parent.layer.closeAll();
					}else{
						layer.msg(result.msg, {time: 1500 });
					}
				} 
			});
		});
	</script>
</body>