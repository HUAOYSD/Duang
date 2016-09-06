<%@page import="org.duang.entity.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<%@page import= "java.lang.reflect.*"%>
<body style="background-color:#fff;" id="body_add_investpro">
	<div id="investPro_edit_contans" class="contans">
	  	<form id="investProEditForm" method="post" class="form"> 
	  		<input type="hidden" name="id"/> 
		    <div>   
		        <label for="nameZh" class="investPro_add_from_label">总名称：</label>   
		        <input class="easyui-validatebox" type="text" name="nameZh" id="editInvestPro_nameZh"/>   
		    </div>   
		    <div>   
		        <label for="name" class="investPro_add_from_label">名称：</label>   
		        <input class="easyui-validatebox" type="text" name="name" id="editInvestPro_name"/>   
		    </div>
		    <div>   
		        <label for="category" class="investPro_add_from_label">产品类型：</label>   
		       	<select class="easyui-combobox" name="category" data-options="panelHeight:'auto'">   
				    <option value="1">信贷产品</option>   
				    <option value="2">房标产品</option>  
				    <option value="3">车标产品</option>   
				</select>   
		    </div> 
		    <div>   
		        <label for="days" class="investPro_add_from_label">周期：</label>   
		       	<input name="days" class="easyui-combobox" data-options="editable:false,valueField:'id',textField:'text',url:'investpro!getDays.do',panelHeight:'auto'" />   
		    </div>
		    <div>   
		        <label for="nameDescribe" class="investPro_add_from_label">描述：</label>   
		        <input class="easyui-validatebox" type="text" name="nameDescribe" id="editInvestPro_nameDescribe"/>   
		    </div> 
		    <div>   
		        <label for="yield" class="investPro_add_from_label">准确的收益率：</label>   
		        <input class="easyui-validatebox" type="text"  name="yield"  id="editInvestPro_yield"/>   
		    </div> 
		    <div>   
		        <label for="yieldDescribe" class="investPro_add_from_label">收益率描述：</label>   
		        <input class="easyui-validatebox" type="text" name="yieldDescribe" id="editInvestPro_yieldDescribe"/>   
		    </div>
		    
		    <div>   
		        <label for="title1" class="investPro_add_from_label">标题1：</label>   
		        <input class="easyui-validatebox" type="text"  name="title1" id="editInvestPro_title1"/>   
		    </div> 
		    <div>   
		        <label for="title2" class="investPro_add_from_label">标题2：</label>   
		        <input class="easyui-validatebox" type="text"  name="title2" id="editInvestPro_title2"/>   
		    </div> 
		    
		    <div>   
		        <label for="isSell" class="investPro_add_from_label">是否起售：</label>   
		       	<select id="isSell" class="easyui-combobox" name="isSell" data-options="panelHeight:'auto'">   
				    <option value="1">起售</option>   
				    <option value="0">停售</option>   
				</select>   
		    </div> 
		    <div>   
		        <label for="isNewProduct" class="investPro_add_from_label">是否新品：</label>   
		       	<select class="easyui-combobox" name="isNewProduct" data-options="panelHeight:'auto'">   
				    <option value="1">是</option>   
				    <option value="0">否</option>   
				</select>   
		    </div>
		    
		    <div>   
		        <label for="isRecommend" class="investPro_add_from_label">是否推荐：</label>   
		       	<select class="easyui-combobox" name="isRecommend" data-options="panelHeight:'auto'">   
				    <option value="1">是</option>   
				    <option value="0">否</option>   
				</select>   
		    </div> 
		    
		    <div>   
		        <label for="productDescribe" class="investPro_add_from_label">介绍：</label>   
		       	<textarea rows="5" cols="25" id="productDescribe" name="productDescribe"></textarea>  
		    </div> 
		    <div>   
		        <label for="riskControl" class="investPro_add_from_label">风险控制：</label>   
		       	<textarea rows="5" cols="25" id="riskControl" name="riskControl"></textarea>  
		    </div>
		    <div>   
		        <label for="details" class="investPro_add_from_label">更多详细：</label>   
		       	<textarea rows="5" cols="25" id="details" name="details"></textarea>  
		    </div>
		</form>  
	</div>
	<div align="center" id="footer-oper">
   	    <div id="content-oper">
	    	 <a  id="submit_edit_investPro_btn" class="easyui-linkbutton my-search-button" data-options="iconCls:'icon-2012092109942'" plain="true">保存</a>
		     &nbsp;&nbsp;&nbsp;&nbsp;
		     <a  onclick="javascript:$('#investProEditForm').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
    	</div>
	</div>  
	<script type="text/javascript">
		$(function(){
			$.ajax({
				type:'POST',
				url:"investpro!getInvestProInfo.do",
				data:"id=<%=request.getParameter("id")%>",
				dataType:'json',
				success:function(msg) {
					$("#investProEditForm").form('load',msg);
				}
			});
		});
	
		$(function(){
			$('#investProEditForm').form({    
			    url:"investpro!saveInvestPro.do",    
			    onSubmit: function(){    
			        
			    },    
			    success:function(data){ 
			    	$.messager.progress('close');	// 如果提交成功则隐藏进度条
			    	data = JSON.parse(data);
			    	if(data.result==true){
			    		window.parent.reloadDataGrid();
			    		parent.layer.closeAll();
			    	}else{
			    		layer.msg(data.msg, {
			    			  icon: 5,
			    			  time: 3000 //2秒关闭（如果不配置，默认是3秒）
			    		});
			    	}
			    }    
			});
		});
		$("#submit_edit_investPro_btn").on("click", function(){
			var nameZh = $("#editInvestPro_nameZh").val();
			var name = $("#editInvestPro_name").val();
			var yield = $("#editInvestPro_yield").val();
			if(isNull(nameZh)){
				$('#editInvestPro_nameZh').validatebox({    
					required: true 
				}); 
				return;
			}
			if(isNull(name)){
				$('#editInvestPro_name').validatebox({    
				    required: true    
				}); 
				return;
			}
			if(isNull(yield)){
				$('#editInvestPro_yield').validatebox({    
				    required: true
				}); 
				return;
			}
			$.messager.progress();
			$('#investProEditForm').submit();
		});
		function isNull(val){
			if(val==null || val.trim() == '' || val=='undefined'){
				return true;
			}else{
				return false;
			}
		}
	</script>
</body>