<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK" pageEncoding="utf-8"%>
<%@ include file="/page/inc/inc.jsp"%>
<% String path = request.getContextPath();%>
<body style="background-color:#fff;">
	<div class="operate_div_form">
	  	<form id="scale_add_form" method="post" style="margin-bottom: 45px;"> 
	  		<div>   
		        <label for="name" class="add_edit_form_label">名称：</label>  
		        <input class="easyui-validatebox" name="name" data-options="required:true,missingMessage:'请填写名称'" /> 
		    </div> 
		    <div>   
		        <label for="product.id" class="add_edit_form_label">产品：</label>  
		        <input class="easyui-combobox" name="product.id" style="width: 216px;"	data-options="valueField:'id',textField:'text',editable:false,url:'investpro!queryProductByCombobox.do',panelHeight:'auto',required:'true',missingMessage:'请选择产品！'" />
		    </div>
		    <div>   
		        <label for="singleOrSet" class="add_edit_form_label">标类型：</label>  
		        <select class="easyui-combobox" name="singleOrSet" style="width: 216px;" data-options="panelHeight:'auto'">   
				    <option value="2" selected="selected">集合项目</option>   
				    <option value="1">普通项目</option>   
				</select>  
		    </div>  
		    <div>   
		        <label for="beginTime" class="add_edit_form_label">开标时间：</label>  
		        <input name="beginTime" type="text" style="width: 216px;" class="easyui-datebox" data-options="required:true,missingMessage:'请输入开标时间'"/>
		    </div> 
		    <div>   
		        <label for="endTime" class="add_edit_form_label">截止时间：</label>  
		        <input name="endTime" type="text" style="width: 216px;" class="easyui-datebox" data-options="required:true,missingMessage:'请输入截止时间'"/>
		    </div> 
		    <div>   
		        <label for="timeLimit" class="add_edit_form_label">时长简介：</label>  
		        <input class="easyui-validatebox" name="timeLimit" data-options="required:false,validType:'',missingMessage:''" /> 
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
		        <input class="easyui-validatebox" name="revenue" data-options="required:true,validType:'intOrFloat',missingMessage:'请输入收益率'" /> 
		    </div> 
		    <div>   
		        <label for="revenueAdd" class="add_edit_form_label">附收益率：</label>  
		        <input class="easyui-validatebox" name="revenueAdd" data-options="required:false,validType:'intOrFloat',missingMessage:''" /> 
		    </div>
		    <div>   
		        <label for="maxLimit" class="add_edit_form_label">单笔最高限额：</label>  
		        <input class="easyui-validatebox" name="maxLimit" data-options="required:true,validType:'intOrFloat',missingMessage:'请输入单笔最高限额'" /> 
		    </div>
		    <div>   
		        <label for="minLimit" class="add_edit_form_label">单笔最低限额：</label>  
		        <input class="easyui-validatebox" name="minLimit" data-options="required:true,validType:'intOrFloat',missingMessage:'请输入单笔最低限额'" /> 
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
		        <input name="turnDate" type="text" style="width: 216px;" class="easyui-datebox" data-options="required:false,missingMessage:''"/>
		    </div>
		    <!-- <div>   
		        <label for="totalMoney" class="add_edit_form_label">标总额：</label>  
		        <input class="easyui-validatebox" name="totalMoney" data-options="required:true,validType:'intOrFloat',missingMessage:'请输入标总额'" /> 
		    </div>  -->
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
		        <input class="easyui-validatebox" name="onesScore" data-options="required:false,validType:'integer',missingMessage:''" /> 
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
		       	<textarea rows="5" cols="20" name="tags"></textarea>  
		    </div>
		</form>  
	</div>
	<div align="center" class="footer-oper">
   	    <div class="content-oper">
	    	 <a id="scale_add_form_submitbtn" class="easyui-linkbutton my-search-button" data-options="iconCls:'icon-2012092109942'" plain="true">保存</a>
		     &nbsp;&nbsp;&nbsp;&nbsp;
		     <a onclick="javascript:$('#scale_add_form').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
    	</div>
	</div>  
	<script type="text/javascript">
		$("#scale_add_form_submitbtn").on("click", function(){
			if(!$("#scale_add_form").form('validate')){
				return false;
			}
			$.messager.progress();
			$("#scale_add_form").form("submit",{    
			    url:"scale!saveScale.do",
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