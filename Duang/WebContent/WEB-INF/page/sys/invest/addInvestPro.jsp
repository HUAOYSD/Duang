<%@page import="org.duang.entity.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<body style="background-color:#fff;" id="body_add_investpro">
	<div id="investPro_add_contans">
	  	<form id="investProSaveForm" method="post"> 
	  		<input type="hidden" name="id"/> 
		    <div>   
		        <label for="nameZh" class="investPro_add_from_label">总名称：</label>   
		        <input class="easyui-validatebox" type="text" name="nameZh" data-options="required:true" />   
		    </div>   
		    <div>   
		        <label for="name" class="investPro_add_from_label">名称：</label>   
		        <input class="easyui-validatebox" type="text" name="name" data-options="required:true" />   
		    </div>
		    <div>   
		        <label for="category" class="investPro_add_from_label">产品类型：</label>   
		       	<select class="easyui-combobox" name="category">   
				    <option value="0" selected="selected">信贷产品</option>   
				    <option value="1">标类产品</option>   
				</select>   
		    </div> 
		    <div>   
		        <label for="nameDescribe" class="investPro_add_from_label">描述：</label>   
		        <input class="easyui-validatebox" type="text" name="nameDescribe"/>   
		    </div> 
		    <div>   
		        <label for="yield" class="investPro_add_from_label">准确的收益率：</label>   
		        <input class="easyui-validatebox" type="text" name="yield" data-options="required:true" />   
		    </div> 
		    <div>   
		        <label for="yieldDescribe" class="investPro_add_from_label">收益率描述：</label>   
		        <input class="easyui-validatebox" type="text" name="yieldDescribe"/>   
		    </div>
		    <div>   
		        <label for="title1" class="investPro_add_from_label">标题1：</label>   
		        <input class="easyui-validatebox" type="text"  name="title1"/>   
		    </div> 
		    <div>   
		        <label for="title2" class="investPro_add_from_label">标题2：</label>   
		        <input class="easyui-validatebox" type="text" name="title2"/>   
		    </div> 
		    
		    <div>   
		        <label for="minDeadline" class="investPro_add_from_label">起投期限：</label>   
		        <input class="easyui-validatebox" type="text"  name="minDeadline" data-options="required:true" />   
		    </div> 
		     <div>   
		        <label for="minMoney" class="investPro_add_from_label">起投金额：</label>   
		        <input class="easyui-validatebox" type="text" name="minMoney" data-options="required:true" />   
		    </div> 
		    <div>   
		        <label for="refundType" class="investPro_add_from_label">还款方式：</label>   
		       	<select  class="easyui-combobox" name="refundType">   
				    <option value="1" selected="selected">等额本息</option>   
				    <option value="2">一次性还款</option>   
				    <option value="3">先息后本</option>   
				</select>   
		    </div> 
		    
		    <div>   
		        <label for="isSell" class="investPro_add_from_label">是否起售：</label>   
		       	<select class="easyui-combobox" name="isSell">   
				    <option value="1" selected="selected">起售</option>   
				    <option value="0">停售</option>   
				</select>   
		    </div> 
		    <div>   
		        <label for="isNewProduct" class="investPro_add_from_label">是否新品：</label>   
		       	<select class="easyui-combobox" name="isNewProduct">   
				    <option value="1" selected="selected">是</option>   
				    <option value="0">否</option>   
				</select>   
		    </div>
		    
		    <div>   
		        <label for="isRecommend" class="investPro_add_from_label">是否推荐：</label>   
		       	<select class="easyui-combobox" name="isRecommend">   
				    <option value="1" selected="selected">是</option>   
				    <option value="0">否</option>   
				</select>   
		    </div> 
		    
		    <div>   
		        <label for="product_describe" class="investPro_add_from_label">介绍：</label>   
		       	<textarea rows="5" cols="20"  name="productDescribe"></textarea>  
		    </div> 
		    <div>   
		        <label for="risk_control" class="investPro_add_from_label">风险控制：</label>   
		       	<textarea rows="5" cols="20"  name="riskControl"></textarea>  
		    </div>
		    <div>   
		        <label for="details" class="investPro_add_from_label">更多详细：</label>   
		       	<textarea rows="5" cols="20"  name="details"></textarea>  
		    </div>
		</form>  
	</div>
	<div align="center" id="footer-oper">
   	    <div id="content-oper">
	    	 <a  id="submit_addInvestPro" class="easyui-linkbutton my-search-button" data-options="iconCls:'icon-2012092109942'" plain="true">保存</a>
		     &nbsp;&nbsp;&nbsp;&nbsp;
		     <a  onclick="javascript:$('#investProSaveForm').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
    	</div>
	</div>  
	<script type="text/javascript">
		//如果isEdit==edit说明为修改
		$(function(){
			$('#investProSaveForm').form({    
			    url:"investpro!saveInvestPro.do",    
			    onSubmit: function(){    
			        
			    },    
			    success:function(data){ 
			    	$.messager.progress('close');	// 如果提交成功则隐藏进度条
			    	data = JSON.parse(data);
			    	if(data.result==true){
			    		window.parent.reloadDataGrid();
			    		parent.layer.closeAll();
			    	}
			    	layer.msg(data.msg, {time: 3000});
			    }    
			});
		});
		$("#submit_addInvestPro").on("click", function(){
			if(!$("#investProSaveForm").form('validate')){
				return false;
			}
			$.messager.progress();
			$('#investProSaveForm').submit();
		});
	</script>
</body>