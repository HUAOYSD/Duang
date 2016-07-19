<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/page/inc/inc.jsp"%>
<% String path = request.getContextPath();%>
<body style="background-color:#fff;">
	<div class="operate_div_form">
	  	<form id="scale_edit_form" method="post" style="margin-bottom: 45px;"> 
	  		<input type="hidden" name="id" value="${entity.id }"/>
	  		<input type="hidden" name="calcBeginTime" value="${entity.calcBeginTime }"/>
	  		<input type="hidden" name="calcEndTime" value="${entity.calcEndTime }"/>
	  		<input type="hidden" name="residueMoney" value="${entity.residueMoney }"/>
	  		<input type="hidden" name="yetMoney" value="${entity.yetMoney }"/>
	  		<input type="hidden" name="status" value="${entity.status }"/>
	  		<input type="hidden" name="investList.id" value="${entity.investList.id }"/>
	  		<div>   
		        <label for="name" class="add_edit_form_label">名称：</label>  
		        <input class="easyui-validatebox" name="name" value="${entity.name }" data-options="required:true,missingMessage:'请填写名称'" /> 
		    </div> 
		    <div>   
		        <label for="product.id" class="add_edit_form_label">产品：</label>  
		        <input class="easyui-combobox" name="product.id" style="width: 216px;"	data-options="valueField:'id',textField:'text',editable:false,url:'investpro!queryProductByCombobox.do',panelHeight:'auto',required:'true',missingMessage:'请选择产品！'" />
		    </div> 
		    <div>   
		        <label for="beginTime" class="add_edit_form_label">开标时间：</label>  
		        <input name="beginTime" type="text" style="width: 216px;" value="${entity.beginTime }" class="easyui-datebox" data-options="required:true,missingMessage:'请输入开标时间'"/>
		    </div> 
		    <div>   
		        <label for="endTime" class="add_edit_form_label">截止时间：</label>  
		        <input name="endTime" type="text" style="width: 216px;" value="${entity.endTime }" class="easyui-datebox" data-options="required:true,missingMessage:'请输入截止时间'"/>
		    </div> 
		    <div>   
		        <label for="timeLimit" class="add_edit_form_label">时长简介：</label>  
		        <input class="easyui-validatebox" name="timeLimit" value="${entity.timeLimit }" data-options="required:false,validType:'',missingMessage:''" /> 
		    </div> 
		    <!-- <div>   
		        <label for="calcBeginTime" class="add_edit_form_label">开始计息日：</label>  
		        <input name="calcBeginTime" type="text" class="easyui-datebox" data-options="required:false,missingMessage:''"/>
		    </div> 
		    <div>   
		        <label for="calcEndTime" class="add_edit_form_label">结束计息日：</label>  
		        <input name="calcEndTime" type="text" class="easyui-datebox" data-options="required:false,missingMessage:''"/>
		    </div> -->
		    <div>   
		        <label for="revenue" class="add_edit_form_label">收益率：</label>  
		        <input class="easyui-validatebox" name="revenue" value="${entity.revenue }" data-options="required:true,validType:'intOrFloat',missingMessage:'请输入收益率'" /> 
		    </div> 
		    <div>   
		        <label for="revenueAdd" class="add_edit_form_label">附收益率：</label>  
		        <input class="easyui-validatebox" name="revenueAdd" value="${entity.revenueAdd }" data-options="required:false,validType:'intOrFloat',missingMessage:''" /> 
		    </div>
		    <div>   
		        <label for="maxLimit" class="add_edit_form_label">单笔限额：</label>  
		        <input class="easyui-validatebox" name="maxLimit" value="${entity.maxLimit }" data-options="required:true,validType:'intOrFloat',missingMessage:'请输入单笔限额'" /> 
		    </div> 
		    <div>   
		        <label for="returnStyle" class="add_edit_form_label">还款方式：</label>  
		        <select class="easyui-combobox" name="returnStyle" style="width: 216px;" data-options="panelHeight:'auto'">   
				    <option value="1" selected="selected">到期一次还本付息</option>   
				</select>  
		    </div> 
		    <div>   
		        <label for="useTicket" class="add_edit_form_label">可使用理财券：</label>  
		        <select class="easyui-combobox" name="useTicket" style="width: 216px;" data-options="panelHeight:'auto'">   
				    <option value="1" selected="selected">可以</option>   
				    <option value="0">不可以</option>   
				</select>  
		    </div> 
		    <div>   
		        <label for="transfer" class="add_edit_form_label">可转让：</label>  
		        <select class="easyui-combobox" name="transfer" style="width: 216px;" data-options="panelHeight:'auto'">   
				    <option value="1" selected="selected">可以</option>   
				    <option value="0">不可以</option>   
				</select>  
		    </div> 
		    <div>   
		        <label for="turnDate" class="add_edit_form_label">截止转让日期：</label>  
		        <input name="turnDate" type="text" style="width: 216px;" value="${entity.turnDate }" class="easyui-datebox" data-options="required:false,missingMessage:''"/>
		    </div>
		    <%-- <div>   
		        <label for="totalMoney" class="add_edit_form_label">标总额：</label>  
		        <input class="easyui-validatebox" name="totalMoney" value="${entity.totalMoney }" data-options="required:true,validType:'intOrFloat',missingMessage:'请输入标总额'" /> 
		    </div>  --%>
		    <!-- <div>   
		        <label for="residueMoney" class="add_edit_form_label">可投金额：</label>  
		        <input class="easyui-validatebox" name="residueMoney" data-options="required:false,validType:'intOrFloat',missingMessage:''" /> 
		    </div> 
		    <div>   
		        <label for="yetMoney" class="add_edit_form_label">已投金额：</label>  
		        <input class="easyui-validatebox" name="yetMoney" data-options="required:false,validType:'intOrFloat',missingMessage:''" /> 
		    </div> -->
		    <div>   
		        <label for="scoreBonus" class="add_edit_form_label">积分奖励：</label>  
		        <select class="easyui-combobox" name="scoreBonus" style="width: 216px;" data-options="panelHeight:'auto'">   
				    <option value="1" selected="selected">有</option>   
				    <option value="0">没有</option>   
				</select>  
		    </div> 
		    <div>   
		        <label for="onesScore" class="add_edit_form_label">单位积分：</label>  
		        <input class="easyui-validatebox" name="onesScore" value="${entity.onesScore }" data-options="required:false,validType:'integer',missingMessage:''" /> 
		    </div> 
		    <!-- <div>   
		        <label for="isTurn" class="add_edit_form_label">转让标：</label>  
		        <select class="easyui-combobox" name="isTurn" style="width: 216px;" data-options="panelHeight:'auto'">   
				    <option value="0" selected="selected">不是</option>   
				    <option value="1" selected="selected">是</option>   
				</select>  
		    </div> --> 
		    <div>   
		        <label for="tags" class="add_edit_form_label">标签：</label>   
		       	<textarea rows="5" cols="20" name="tags">${entity.tags }</textarea>  
		    </div>
		</form>  
	</div>
	<div align="center" class="footer-oper">
   	    <div class="content-oper">
	    	 <a id="scale_edit_form_submitbtn" class="easyui-linkbutton my-search-button" data-options="iconCls:'icon-2012092109942'" plain="true">保存</a>
		     &nbsp;&nbsp;&nbsp;&nbsp;
		     <a onclick="javascript:$('#scale_edit_form').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
    	</div>
	</div>  
	<script type="text/javascript">
		$(function(){
			var data = '{"product.id":"${entity.product.id}","useTicket":"${entity.useTicket}","transfer":"${entity.transfer}","scoreBonus":"${entity.scoreBonus}"}';
			$("#scale_edit_form").form('load', eval('(' + data + ')'));
		});
		
		$("#scale_edit_form_submitbtn").on("click", function(){
			if(!$("#scale_edit_form").form('validate')){
				return false;
			}
			$.messager.progress();
			$("#scale_edit_form").form("submit",{    
			    url:"scale!updateScale.do",
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