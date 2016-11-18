<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK" pageEncoding="utf-8"%>
<%@ include file="/page/inc/inc.jsp"%>
<% String path = request.getContextPath();%>
<body style="background-color:#fff;">
	<div class="operate_div_form">
	  	<form id="awardActivityLevel_edit_form" method="post" style="margin-bottom: 45px;"> 
	  		<input type="hidden" name="id" value="${entity.id }"/>
	  		<input type="hidden" name="winCode" value="${entity.winCode}"/>
	  		<input type="hidden" name="allWinCode" value="${entity.allWinCode}"/>
	  		<input type="hidden" name="winNumber" value="${entity.winNumber}"/>
	  		<input type="hidden" name="awardAtivity.id" value="${entity.awardAtivity.id}">
	  		<div>   
		        <label for="title" class="add_edit_form_label">等级名称：</label>  
		        <input class="easyui-validatebox"  name="title" value="${entity.title}" data-options="required:true,missingMessage:'请填写名称'" /> 
		    </div> 
		    <div id="div_repeatNum">   
		        <label for="awardNum" class="add_edit_form_label">奖品数量：</label>  
		        <input id="awardNum" name="awardNum" value="${entity.awardNum}" class="easyui-numberbox" type="text" style="width: 216px;" data-options="value:0"/>
		    </div> 
		    <div>   
		        <label for="award.id" class="add_edit_form_label">选择奖品：</label>  
		        <input class="easyui-combobox" name="award.id" style="width:216px;" data-options="required:true,valueField:'id',textField:'text',editable:false,
		        	url:'award!queryAwardToCheckbox.do?id=${entity.award.id}',panelHeight:'auto',required:'true',missingMessage:'请选择奖品！'" >
		    </div> 
		    <div>   
		        <label for="odds" class="add_edit_form_label">中奖几率：</label>  
		        <input class="easyui-numberbox" name="odds" value="${entity.odds}" style="width: 216px;"	data-options="required:true,missingMessage:'请输入大于1的数，比如：100表示1/100'" />
		    </div>
		    <div>   
		        <label for="product.id" class="add_edit_form_label">指定中奖人：</label>  
		        <input class="easyui-combobox" name="userId" style="width: 216px;"	data-options="valueField:'id',textField:'text',editable:false,
		        	url:'memberinfo!queryMemberToCheckbox.do?id=${entity.userId}',panelHeight:'auto',missingMessage:'请选择指定中奖人！'" />
		    </div>
		    <div>   
		        <label for="onceNum" class="add_edit_form_label">单次奖品个数：</label>  
		        <input class="easyui-numberbox" name="onceNum" value="${entity.onceNum}" style="width: 216px;" data-options="required:true,value:1" />
		    </div> 
		</form>  
	</div>
	<div align="center" class="footer-oper">
   	    <div class="content-oper">
	    	 <a id="awardActivityLevel_edit_form_submitbtn" class="easyui-linkbutton my-search-button" data-options="iconCls:'icon-2012092109942'" plain="true">保存</a>
		     &nbsp;&nbsp;&nbsp;&nbsp;
		     <a onclick="javascript:$('#awardActivityLevel_edit_form').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
    	</div>
	</div>  
	<script type="text/javascript">
		$(function(){
		});
		
		$("#awardActivityLevel_edit_form_submitbtn").on("click", function(){
			if(!$("#awardActivityLevel_edit_form").form('validate')){
				return false;
			}
			$.messager.progress();
			$("#awardActivityLevel_edit_form").form("submit",{    
			    url:"awardActivityLevel!update.do",
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