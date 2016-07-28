<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<body style="background-color:#fff;" id="body_add_investpro">
	<div id="investPro_add_contans">
	  	<form id="investProSaveForm" method="post">   
		    <div>   
		        <label for="nameZh" class="investPro_add_from_label">总名称：</label>   
		        <input class="easyui-validatebox" type="text" name="nameZh" data-options="required:true" />   
		    </div>   
		    <div>   
		        <label for="name" class="investPro_add_from_label">名称：</label>   
		        <input class="easyui-validatebox" type="text" name="name" data-options="required:true" />   
		    </div>
		    <div>   
		        <label for="name_describe" class="investPro_add_from_label">描述：</label>   
		        <input class="easyui-validatebox" type="text" name="name_describe" data-options="required:true" />   
		    </div> 
		    <div>   
		        <label for="yield" class="investPro_add_from_label">准确的收益率：</label>   
		        <input class="easyui-validatebox easyui-numberbox" type="text" name="yield" data-options="required:true,min:0,precision:6" />   
		    </div> 
		    <div>   
		        <label for="yield_describe" class="investPro_add_from_label">收益率描述：</label>   
		        <input class="easyui-validatebox" type="text" name="yield_describe" data-options="required:true" />   
		    </div>
		    
		    <div>   
		        <label for="charge_ratio" class="investPro_add_from_label">手续费比例率：</label>   
		        <input class="easyui-validatebox" type="text" name="charge_ratio" data-options="required:true" />   
		    </div> 
		    
		    <div>   
		        <label for="title1" class="investPro_add_from_label">标题1：</label>   
		        <input class="easyui-validatebox" type="text" name="title1" data-options="required:true" />   
		    </div> 
		    <div>   
		        <label for="title2" class="investPro_add_from_label">标题2：</label>   
		        <input class="easyui-validatebox" type="text" name="title2" data-options="required:true" />   
		    </div> 
		    
		    <div>   
		        <label for="min_deadline" class="investPro_add_from_label">起投期限：</label>   
		        <input class="easyui-validatebox easyui-numberbox" type="text" name="min_deadline" data-options="required:true,min:0,max:12,suffix:'月'" />   
		    </div> 
		     <div>   
		        <label for="min_money" class="investPro_add_from_label">起投金额：</label>   
		        <input class="easyui-validatebox easyui-numberbox" type="text" name="min_money" data-options="required:true,min:0,suffix:'元'" />   
		    </div> 
		    <div>   
		        <label for="min_money" class="investPro_add_from_label">还款方式：</label>   
		       	<select id="min_money" class="easyui-combobox" name="min_money">   
				    <option value="1" selected="selected">等额本息</option>   
				    <option value="2">一次性还款</option>   
				    <option value="3">先息后本</option>   
				</select>   
		    </div> 
		    
		    <div>   
		        <label for="is_sell" class="investPro_add_from_label">是否起售：</label>   
		       	<select id="is_sell" class="easyui-combobox" name="is_sell">   
				    <option value="1" selected="selected">起售</option>   
				    <option value="0">停售</option>   
				</select>   
		    </div> 
		    
		    <div>   
		        <label for="is_lottery" class="investPro_add_from_label">是否抽奖：</label>   
		       	<select id="is_lottery" class="easyui-combobox" name="is_lottery">   
				    <option value="0" selected="selected">否</option>   
				    <option value="1">是</option>   
				</select>   
		    </div>
		    
		    <div>   
		        <label for="is_red_envel" class="investPro_add_from_label">是否红包：</label>   
		       	<select id="is_red_envel" class="easyui-combobox" name="is_red_envel">   
				    <option value="0" selected="selected">否</option>   
				    <option value="1">是</option>   
				</select>   
		    </div>  
		    
		    <div>   
		        <label for="is_new_product" class="investPro_add_from_label">是否新品：</label>   
		       	<select id="is_new_product" class="easyui-combobox" name="is_new_product">   
				    <option value="1" selected="selected">是</option>   
				    <option value="0">否</option>   
				</select>   
		    </div>
		    
		    <div>   
		        <label for="is_recommend" class="investPro_add_from_label">是否推荐：</label>   
		       	<select id="is_recommend" class="easyui-combobox" name="is_recommend">   
				    <option value="1" selected="selected">是</option>   
				    <option value="0">否</option>   
				</select>   
		    </div> 
		    
		    <div>   
		        <label for="product_describe" class="investPro_add_from_label">介绍：</label>   
		       	<textarea rows="5" cols="20" name="product_describe"></textarea>  
		    </div> 
		    <div>   
		        <label for="risk_control" class="investPro_add_from_label">风险控制：</label>   
		       	<textarea rows="5" cols="20" name="risk_control"></textarea>  
		    </div>
		    <div>   
		        <label for="details" class="investPro_add_from_label">更多详细：</label>   
		       	<textarea rows="5" cols="20" name="details"></textarea>  
		    </div>
		    <div align="center">
		    	 <a  id="submitInvest_btn" class="easyui-linkbutton my-search-button" iconCls="icon-2012092109942" plain="true">保存</a>
			     <a  onclick="javascript:$('#investProSaveForm').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
		    </div>    
		</form>  
	</div>
	<script type="text/javascript">
		$(function(){
			$('#investProSaveForm').form({    
			    url:"investpro!saveInvestPro.do",    
			    onSubmit: function(){    
			        
			    },    
			    success:function(data){ 
			    	$.messager.progress('close');	// 如果提交成功则隐藏进度条
			    	data = JSON.parse(data);
			    	console.info(data);
			    }    
			});
		});
		$("#submitInvest_btn").on("click", function(){
			$.messager.progress();
			$('#investProSaveForm').submit();
		});
	</script>
</body>