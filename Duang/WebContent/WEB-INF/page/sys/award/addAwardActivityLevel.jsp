<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK" pageEncoding="utf-8"%>
<%@ include file="/page/inc/inc.jsp"%>
<% String path = request.getContextPath();%>
<body style="background-color:#fff;">
	<div class="operate_div_form">
	  	<form id="addawardActivityLevel_add_form" method="post" style="margin-bottom: 45px;"> 
	  		<input type="hidden" name="awardAtivity.id" value="${awardActivityId}">
	  		<div>   
		        <label for="title" class="add_edit_form_label">等级名称：</label>  
		        <input class="easyui-validatebox" name="title" data-options="required:true,missingMessage:'请填写名称'" /> 
		    </div> 
		    <div>   
		        <label for="awardNum" class="add_edit_form_label">奖品数量：</label>  
		        <input class="easyui-numberbox" name="awardNum" style="width: 216px;" data-options="required:true,value:0"/>
		    </div> 
		    <div>   
		        <label for="award.id" class="add_edit_form_label">选择奖品：</label>  
		        <input class="easyui-combobox" name="award.id" style="width:216px;" data-options="required:true,valueField:'id',textField:'text',editable:false,
		        	url:'award!queryAwardToCheckbox.do',panelHeight:'auto',required:'true',missingMessage:'请选择奖品！'" >
		    </div> 
		    <div>   
		        <label for="odds" class="add_edit_form_label">中奖几率：</label>  
		        <input class="easyui-numberbox" name="odds" style="width: 216px;"	data-options="required:true,missingMessage:'请输入大于1的数，比如：100表示1/100'" />
		    </div>
		    <div>   
		        <label for="product.id" class="add_edit_form_label">指定中奖人：</label>  
		        <input class="easyui-combobox" name="userId" style="width: 216px;"	data-options="valueField:'id',textField:'text',editable:false,
		        	url:'memberinfo!queryMemberToCheckbox.do',panelHeight:'auto',missingMessage:'请选择指定中奖人！'" />
		    </div>
		     <div>   
		        <label for="onceNum" class="add_edit_form_label">单次奖品个数：</label>  
		        <input class="easyui-numberbox" name="onceNum" style="width: 216px;" data-options="required:true,value:1" />
		    </div>   
		</form>  
	</div>
	<div align="center" class="footer-oper">
   	    <div class="content-oper">
	    	 <a id="addawardActivityLevel_add_form_submitbtn" class="easyui-linkbutton my-search-button" data-options="iconCls:'icon-2012092109942'" plain="true">保存</a>
		     &nbsp;&nbsp;&nbsp;&nbsp;
		     <a onclick="javascript:$('#addawardActivityLevel_add_form').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
    	</div>
	</div>  
	<script type="text/javascript">
		$("#addawardActivityLevel_add_form_submitbtn").on("click", function(){
			if(!$("#addawardActivityLevel_add_form").form('validate')){
				return false;
			}
			$.messager.progress();
			$("#addawardActivityLevel_add_form").form("submit",{    
			    url:"awardActivityLevel!save.do",
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