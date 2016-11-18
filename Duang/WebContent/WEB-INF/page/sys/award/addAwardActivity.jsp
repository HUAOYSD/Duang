<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK" pageEncoding="utf-8"%>
<%@ include file="/page/inc/inc.jsp"%>
<style>
	form div{
		padding-bottom:5px;
		padding-top:5px;
	}
</style>
<% String path = request.getContextPath();%>
<body style="background-color:#fff;">
	<div class="operate_div_form">
	  	<form id="awardActivity_add_form" method="post" style="margin-bottom: 45px;"> 
	  		<div>   
		        <label for="title" class="add_edit_form_label">活动名称：</label>  
		        <input class="easyui-validatebox" name="title" data-options="required:true,missingMessage:'请填写名称'" /> 
		    </div> 
		    <div>   
		        <label for="isRepeat" class="add_edit_form_label">重复抽奖：</label>  
		         <select class="easyui-combobox" id="isRepeat" name="isRepeat" style="width: 216px;" data-options="panelHeight:'auto'">   
    				<option value="0">不可重复</option>  
    				<option value="1">可重复</option>  
				 </select> 
		    </div> 
		    <div id="div_repeatNum">   
		        <label for="repeatNum" class="add_edit_form_label">重复次数：</label>  
		        <input id="repeatNum" name="repeatNum" class="easyui-numberbox" type="text" style="width: 216px;" data-options="value:0"/>
		    </div> 
		    <div>   
		        <label for="startTime" class="add_edit_form_label">开始时间：</label>  
		        <input name="startTime" type="text" style="width: 216px;" class="easyui-datetimebox" data-options="required:true,missingMessage:'请输入活动开始时间'"/>
		    </div>
		    <div>   
		        <label for="endTime" class="add_edit_form_label">截止时间：</label>  
		        <input name="endTime" type="text" style="width: 216px;" class="easyui-datetimebox" data-options="required:true,missingMessage:'请输入活动截止时间'"/>
		    </div> 
		    <div>   
		        <label for="code" class="add_edit_form_label">活动编码：</label>   
		       	<input name="code" type="text" class="easyui-numberbox" data-options="required:true,validType:['number','length[4,6]'],missingMessage:'请输入四位数字'"/> 
		    </div>
		     <div>   
		        <label for="useScore" class="add_edit_form_label">消耗积分：</label>   
		       	<input name="useScore" type="text" class="easyui-numberbox" data-options="required:true,value:1,validType:['number','length[1,10]'],missingMessage:'请输入数字'"/> 
		    </div>
		</form>  
	</div>
	<div align="center" class="footer-oper">
   	    <div class="content-oper">
	    	 <a id="awardActivity_add_form_submitbtn" class="easyui-linkbutton my-search-button" data-options="iconCls:'icon-2012092109942'" plain="true">保存</a>
		     &nbsp;&nbsp;&nbsp;&nbsp;
		     <a onclick="javascript:$('#awardActivity_add_form').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
    	</div>
	</div>  
	<script type="text/javascript">
		$(function(){
			$("#div_repeatNum").hide();
			$("#isRepeat").combobox('select','0');
			$("#isRepeat").combobox({
				onSelect: function(record){
					if(record.value==1){
						$("#div_repeatNum").show();
					}else{
						$("#div_repeatNum").hide();
					}
				}
			});
		});	
	
		$("#awardActivity_add_form_submitbtn").on("click", function(){
			if(!$("#awardActivity_add_form").form('validate')){
				return false;
			}
			$.messager.progress();
			$("#awardActivity_add_form").form("submit",{    
			    url:"awardActivity!save.do",
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