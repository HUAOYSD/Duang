<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK" pageEncoding="utf-8"%>
<%@ include file="/page/inc/inc.jsp"%>
<% String path = request.getContextPath();%>
<body style="background-color:#fff;">
	<div class="operate_div_form">
	  	<form id="awardActivity_edit_form" method="post" style="margin-bottom: 45px;"> 
	  		<input type="hidden" name="id" value="${entity.id }"/>
	  		<input type="hidden" name="createTime" value="${entity.createTime}"/>
	  		<input type="hidden" name="nowNumber" value="${entity.nowNumber}"/>
	  		<input type="hidden" name="winNumber" value="${entity.winNumber}"/>
	  		<div>   
		        <label for="title" class="add_edit_form_label">活动名称：</label>  
		        <input class="easyui-validatebox"  name="title" value="${entity.title}" data-options="required:true,missingMessage:'请填写名称'" /> 
		    </div> 
		    <div>   
		        <label for="isRepeat" class="add_edit_form_label">重复抽奖：</label>  
		         <select class="easyui-combobox" id="isRepeat" name="isRepeat"  style="width: 216px;" data-options="panelHeight:'auto'">   
    				<option value="0">不可重复</option>  
    				<option value="1">可重复</option>  
				 </select> 
		    </div> 
		    <div id="div_repeatNum">   
		        <label for="repeatNum" class="add_edit_form_label">重复次数：</label>  
		        <input id="repeatNum" name="repeatNum" value="${entity.repeatNum}" class="easyui-numberbox" type="text" style="width: 216px;" data-options="value:0"/>
		    </div> 
		    <div>   
		        <label for="startTime" class="add_edit_form_label">开始时间：</label>  
		        <input  name="startTime" type="text" style="width: 216px;" value="${entity.startTime}" class="easyui-datetimebox" data-options="required:true,missingMessage:'请输入活动开始时间'"/>
		    </div>
		    <div>   
		        <label for="endTime" class="add_edit_form_label">截止时间：</label>  
		        <input  name="endTime" type="text" style="width: 216px;" value="${entity.endTime}" class="easyui-datetimebox" data-options="required:true,missingMessage:'请输入活动截止时间'"/>
		    </div> 
		    <div>   
		        <label for="code" class="add_edit_form_label">活动编码：</label>   
		       	<input name="code" type="text" value="${entity.code}" class="easyui-numberbox" data-options="required:true,validType:['number','length[4,6]'],missingMessage:'请输入四位数字'"/> 
		    </div>
		     <div>   
		        <label for="useScore" class="add_edit_form_label">消耗积分：</label>   
		       	<input name="useScore" type="text" value="${entity.useScore}" class="easyui-numberbox" data-options="required:true,value:1,validType:['number','length[1,10]'],missingMessage:'请输入数字'"/> 
		    </div>
		</form>  
	</div>
	<div align="center" class="footer-oper">
   	    <div class="content-oper">
	    	 <a id="awardActivity_edit_form_submitbtn" class="easyui-linkbutton my-search-button" data-options="iconCls:'icon-2012092109942'" plain="true">保存</a>
		     &nbsp;&nbsp;&nbsp;&nbsp;
		     <a onclick="javascript:$('#awardActivity_edit_form').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
    	</div>
	</div>  
	<script type="text/javascript">
		$(function(){
			
			if("${entity.isRepeat}"==1){
				$("#div_repeatNum").show();
			}else{
				$("#div_repeatNum").hide();
			}
			$("#isRepeat").combobox("select","${entity.isRepeat}");
			$("#isRepeat").combobox({
				onSelect: function(record){
					if(record.value==1){
						$("#div_repeatNum").show();
						$("#repeatNum").val("${entity.repeatNum}");
					}else{
						$("#repeatNum").val("0");
						$("#div_repeatNum").hide();
					}
				}
			});
		});
		
		$("#awardActivity_edit_form_submitbtn").on("click", function(){
			if(!$("#awardActivity_edit_form").form('validate')){
				return false;
			}
			$.messager.progress();
			$("#awardActivity_edit_form").form("submit",{    
			    url:"awardActivity!update.do",
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