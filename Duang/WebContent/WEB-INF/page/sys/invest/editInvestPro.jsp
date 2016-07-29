<%@page import="org.duang.entity.SysInvestProduct"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<%@page import= "java.lang.reflect.*"%>
<body style="background-color:#fff;" id="body_add_investpro">
	<div id="investPro_add_contans">
	  	<form id="investProSaveForm" method="post"> 
	  		<input type="hidden" name="id" id="id"/> 
		    <div>   
		        <label for="nameZh" class="investPro_add_from_label">总名称：</label>   
		        <input class="easyui-validatebox" type="text" id="nameZh" name="nameZh"/>   
		    </div>   
		    <div>   
		        <label for="name" class="investPro_add_from_label">名称：</label>   
		        <input class="easyui-validatebox" type="text" id="name" name="name"/>   
		    </div>
		    <div>   
		        <label for="name_describe" class="investPro_add_from_label">描述：</label>   
		        <input class="easyui-validatebox" type="text" id="name_describe" name="name_describe"/>   
		    </div> 
		    <div>   
		        <label for="yield" class="investPro_add_from_label">准确的收益率：</label>   
		        <input class="easyui-validatebox" type="text" id="yield" name="yield"  />   
		    </div> 
		    <div>   
		        <label for="yield_describe" class="investPro_add_from_label">收益率描述：</label>   
		        <input class="easyui-validatebox" type="text" id="yield_describe" name="yield_describe"/>   
		    </div>
		    
		    <div>   
		        <label for="charge_ratio" class="investPro_add_from_label">手续费比例率：</label>   
		        <input class="easyui-validatebox" type="text" id="charge_ratio" name="charge_ratio"/>   
		    </div> 
		    
		    <div>   
		        <label for="title1" class="investPro_add_from_label">标题1：</label>   
		        <input class="easyui-validatebox" type="text" id="title1" name="title1"/>   
		    </div> 
		    <div>   
		        <label for="title2" class="investPro_add_from_label">标题2：</label>   
		        <input class="easyui-validatebox" type="text" id="title2" name="title2"/>   
		    </div> 
		    
		    <div>   
		        <label for="min_deadline" class="investPro_add_from_label">起投期限：</label>   
		        <input class="easyui-validatebox" type="text" id="min_deadline" name="min_deadline"/>   
		    </div> 
		     <div>   
		        <label for="min_money" class="investPro_add_from_label">起投金额：</label>   
		        <input class="easyui-validatebox" type="text" id="min_money" name="min_money"/>   
		    </div> 
		    <div>   
		        <label for="refund_type" class="investPro_add_from_label">还款方式：</label>   
		       	<select id="refund_type" class="easyui-combobox" name="refund_type">   
				    <option value="1"  >等额本息</option>   
				    <option value="2">一次性还款</option>   
				    <option value="3">先息后本</option>   
				</select>   
		    </div> 
		    
		    <div>   
		        <label for="is_sell" class="investPro_add_from_label">是否起售：</label>   
		       	<select id="is_sell" class="easyui-combobox" name="is_sell">   
				    <option value="1">起售</option>   
				    <option value="0">停售</option>   
				</select>   
		    </div> 
		    
		    <div>   
		        <label for="is_lottery" class="investPro_add_from_label">是否抽奖：</label>   
		       	<select id="is_lottery" class="easyui-combobox" name="is_lottery">   
				    <option value="0">否</option>   
				    <option value="1">是</option>   
				</select>   
		    </div>
		    
		    <div>   
		        <label for="is_red_envel" class="investPro_add_from_label">是否红包：</label>   
		       	<select id="is_red_envel" class="easyui-combobox" name="is_red_envel">   
				    <option value="0">否</option>   
				    <option value="1">是</option>   
				</select>   
		    </div>  
		    
		    <div>   
		        <label for="is_new_product" class="investPro_add_from_label">是否新品：</label>   
		       	<select id="is_new_product" class="easyui-combobox" name="is_new_product">   
				    <option value="1">是</option>   
				    <option value="0">否</option>   
				</select>   
		    </div>
		    
		    <div>   
		        <label for="is_recommend" class="investPro_add_from_label">是否推荐：</label>   
		       	<select id="is_recommend" class="easyui-combobox" name="is_recommend">   
				    <option value="1">是</option>   
				    <option value="0">否</option>   
				</select>   
		    </div> 
		    
		    <div>   
		        <label for="product_describe" class="investPro_add_from_label">介绍：</label>   
		       	<textarea rows="3" cols="25" id="product_describe" name="product_describe"></textarea>  
		    </div> 
		    <div>   
		        <label for="risk_control" class="investPro_add_from_label">风险控制：</label>   
		       	<textarea rows="3" cols="25" id="risk_control" name="risk_control"></textarea>  
		    </div>
		    <div>   
		        <label for="details" class="investPro_add_from_label">更多详细：</label>   
		       	<textarea rows="3" cols="25" id="details" name="details"></textarea>  
		    </div>
		</form>  
	</div>
	<div align="center" id="footer-oper">
   	    <div id="content-oper">
	    	 <a  id="submitInvest_btn" class="easyui-linkbutton my-search-button" data-options="iconCls:'icon-2012092109942'" plain="true">保存</a>
		     &nbsp;&nbsp;&nbsp;&nbsp;
		     <a  onclick="javascript:$('#investProSaveForm').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
    	</div>
	</div>  
	<script type="text/javascript">
		//如果isEdit==edit说明为修改
		var isEdit = "${edit}";
		$(function(){
			if(isEdit=='edit'){
				<%
					SysInvestProduct investPro = (SysInvestProduct)request.getAttribute("investPro");
				    Field[] field = investPro.getClass().getDeclaredFields(); // 获取实体类的所有属性，返回Field数组
				    for (int j = 0; j < field.length; j++) { // 遍历所有属性
		                String name = field[j].getName(); // 获取属性的名字
		                if(name.equals("serialVersionUID")){
		                	continue;
		                }
		                String bName = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set
		                Method m = investPro.getClass().getMethod("get" + bName);
				%>
		            var key ="<%=name%>";
		            var value="<%=m.invoke(investPro)%>";
				    //如果为is_*说明是select标签
				    var checkNum = /^is_[A-Za-z]+$/;
				    if(checkNum.test(key) || 'refund_type'==key){
				    	$('#'+key).combobox('select',value); 
				    }else{
				    	$("#"+key).val(value);
				    }
				    
				<%}%>
			}
			
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
			    	}else{
			    		layer.msg(data.msg, {
			    			  icon: 5,
			    			  time: 3000 //2秒关闭（如果不配置，默认是3秒）
			    		});
			    	}
			    }    
			});
		});
		$("#submitInvest_btn").on("click", function(){
			var nameZh = $("#nameZh").val();
			var name = $("#name").val();
			var yield = $("#yield").val();
			var min_deadline = $("#min_deadline").val();
			var min_money = $("#min_money").val();
			if(isNull(nameZh)){
				$('#nameZh').validatebox({    
					required: true 
				}); 
				return;
			}
			if(isNull(name)){
				$('#name').validatebox({    
				    required: true    
				}); 
				return;
			}
			if(isNull(yield)){
				$('#yield').validatebox({    
				    required: true
				}); 
				return;
			}
			if(isNull(min_deadline)){
				$('#min_deadline').validatebox({    
				    required: true
				}); 
				return;
			}
			if(isNull(min_money)){
				$('#min_money').validatebox({    
				    required: true
				}); 
				return;
			}
			$.messager.progress();
			$('#investProSaveForm').submit();
		});
		
		function isNull(val){
			if(val==null || val.trim() == ''){
				return true;
			}else{
				return false;
			}
		}
	</script>
</body>