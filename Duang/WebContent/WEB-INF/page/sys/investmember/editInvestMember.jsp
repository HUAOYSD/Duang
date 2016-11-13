<%@page import="org.duang.entity.InvestMember"%>
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<body style="background-color:#fff;" class="bodyContent">
	<div class="content">
	  	<form id="investMemberEidtForm" method="post"> 
	  		<input type="hidden" name="id" value="${entity.id}"/> 
	  		<input type="hidden" id="editInvestMember_memberInfoId" name="memberInfo.id" value=""/> 
	  		<h1>基本信息</h1>
		    <div>   
		        <label for="memberInfo.loginName" class="from_label">登录名：</label>   
		        <input class="easyui-validatebox" type="text" name="memberInfo.loginName" data-options="required:true" />   
		    </div>   
		    <div>   
		        <label for="memberInfo.realName" class="from_label">真实姓名：</label>   
		        <input class="easyui-validatebox" type="text" name="memberInfo.realName" data-options="validType:'name'"/>   
		    </div>
		    <div>   
		        <label for="memberInfo.nickname" class="from_label">昵称：</label>   
		        <input class="easyui-validatebox" type="text" name="memberInfo.nickname" data-options="validType:'name'"/>   
		    </div>
		    <div>   
		        <label for="memberInfo.password" class="from_label">密码：</label>   
		        <input class="easyui-validatebox" type="password" name="memberInfo.password"/>   
		    </div>
		    <div>   
		        <label for="memberInfo.phone" class="from_label">手机：</label>   
		        <input class="easyui-validatebox" type="text" name="memberInfo.phone" data-options="validType:'mobile'"/>   
		    </div> 
		    <div>   
		        <label for="memberInfo.email" class="from_label">邮箱：</label>   
		        <input class="easyui-validatebox" type="text" name="memberInfo.email" data-options="validType:'email'"/>   
		    </div> 
		    <div>   
		        <label for="memberInfo.age" class="from_label">年龄：</label>   
		        <input class="easyui-numberbox" value="18" data-options="min:18,max:150" type="text" name="memberInfo.age"/>   
		    </div>
		    
		    <div>   
		        <label for="memberInfo.sex" class="from_label">性别：</label>  
		        <select  class="easyui-combobox" name="memberInfo.sex" data-options="panelHeight:'auto'">   
				    <option value="1" selected="selected">男</option>   
				    <option value="0">女</option>
				    <option value="2">保密</option>   
				</select>   
		    </div> 
		    <div>   
		        <label for="memberInfo.isEliteAccount" class="from_label">是否为金账户：</label>   
		        <select  class="easyui-combobox" name="memberInfo.isEliteAccount" data-options="panelHeight:'auto'">   
				    <option value="0" selected="selected">否</option>   
				    <option value="1">是</option>
				</select>   
		    </div> 
		    <div>   
		        <label for="memberInfo.registerStyle" class="from_label">注册方式：</label>   
		       	<select  class="easyui-combobox" name="memberInfo.registerStyle" data-options="panelHeight:'auto'">   
				    <option value="1">线下</option>   
				    <option value="2">Android</option>
				    <option value="3">IOS</option>
				    <option value="4" selected="selected">平台系统</option>
				</select> 
		    </div>
		     <div>   
		        <label for="memberInfo.level" class="from_label">级别：</label>   
		        <select  class="easyui-combobox" name="memberInfo.level" data-options="panelHeight:'auto'">   
				    <option value="1" selected="selected">1</option>   
				    <option value="2">2</option>
				    <option value="3">3</option>
				    <option value="4">4</option>
				    <option value="5">5</option>
				</select> 
		    </div> 
		    <div>   
		        <label for="memberInfo.price" class="from_label">财力值：</label>   
		       	<input type="text" name="memberInfo.price" class="easyui-numberbox" value="0" data-options="min:0"/>万元
		    </div> 
		    <div>   
		        <label for="memberInfo.idCard" class="from_label">身份证号：</label>   
		       	<input type="text" name="memberInfo.idCard" class="easyui-validatebox" data-options="validType:'idcard'"/>
		    </div>
		    <div>   
		        <label for="memberInfo.miDescribe" class="from_label">介绍：</label>   
		       	<textarea rows="5" cols="30" name="memberInfo.miDescribe"></textarea>  
		    </div> 
		   <h1>理财信息</h1>
		    <div>   
		        <label for="balance" class="from_label">余额：</label>   
		       	<input type="text" name="balance" class="easyui-validatebox" data-options="validType:'currency'"/>
		    </div>
		     <div>   
		        <label for="investing" class="from_label">投资中金额：</label>   
		       	<input  type="text" name="investing" class="easyui-validatebox" data-options="validType:'currency'"/>
		    </div>
		     <div>   
		        <label for="totalIncome" class="from_label">总收益：</label>   
		       	<input  type="text" name="totalIncome" class="easyui-validatebox" data-options="validType:'currency'"/>
		    </div>
		    <div>   
		        <label for="totalMoney" class="from_label">总资产：</label>   
		       	<input  type="text" name="totalMoney" class="easyui-validatebox" data-options="validType:'currency'"/>
		    </div>
		    <div>   
		        <label for="useableScore" class="from_label">可用积分：</label>   
		       	<input  type="text" name="useableScore" class="easyui-validatebox" data-options="validType:'integer'"/>
		    </div>
		    <div>   
		        <label for="isContract" class="from_label">契约用户：</label>   
		       	<select  class="easyui-combobox" name="isContract" data-options="panelHeight:'auto'">   
				    <option value="0">否</option>   
				    <option value="1">是</option>
				</select> 
		    </div>
		    <div style="">   
		        <label for="customerManager.id" class="from_label">客户经理姓名：</label>   
		       	<input class="easyui-combobox" name="customerManager.id" id="editInvestMember_customerManagerId" data-options="    
				        valueField: 'id',    
				        textField: 'name',
				        panelHeight:'auto',
				        editable:false,
				        panelHeight:'auto',
				        url: 'customermanager!queryAllCusManagerIdAndName.do',
				        onSelect: function(rec){    
				             $('#addInvestMember_customerManager_name').val(rec.name);
				        }"  />   
		    </div>
		    <input type="hidden" id="addInvestMember_customerManager_name"  name="managerName" value="">
		</form>  
	</div>
	<div align="center" class="footer-oper">
   	    <div class="content-oper">
	    	 <a  id="submitInvestMemberEdit_btn" class="easyui-linkbutton my-search-button" data-options="iconCls:'icon-2012092109942',size:'large'" plain="true">保存</a>
    	</div>
	</div>  
	<script type="text/javascript">
		$(function(){
			$.ajax({
				type:'POST',
				url:"investmember!getInvestMemberInfo.do",
				data:"id=${entity.id}",
				dataType:'json',
				success:function(msg) {
					$("#investMemberEidtForm").form('load',msg);
					$('#editInvestMember_customerManagerId').combobox('select',msg.customerManagerId);  
					$('#editInvestMember_memberInfoId').val(msg.memberInfoId);
				}
			});
		});
		$(function(){
			$('#investMemberEidtForm').form({    
			    url:"investmember!updateInvestMember.do",    
			    onSubmit: function(){    
			        
			    },    
			    success:function(data){ 
			    	$.messager.progress('close');	// 如果提交成功则隐藏进度条
			    	data = JSON.parse(data);
			    	if(data.result==true){
			    		window.parent.reloadDataGrid();
			    		parent.layer.closeAll();
			    	}
			    	layer.msg(data.msg, {time: 2000});
			    }    
			});
		});
		$("#submitInvestMemberEdit_btn").on("click", function(){
			if(!$("#investMemberEidtForm").form('validate')){
				return false;
			}
			$.messager.progress();
			$('#investMemberEidtForm').submit();
		});
	</script>
</body>