<%@page import="org.duang.entity.InvestMember"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<body style="background-color:#fff;" class="bodyContent">
	<div class="content">
	  	<form id="investMemberEidtForm" method="post"> 
	  		<input type="hidden" name="id" value="${entity.id}"/> 
	  		<input type="hidden" name="memberInfo.id" value="${entity.memberInfo.id}"/> 
	  		<h1>基本信息</h1>
		    <div>   
		        <label for="memberInfo.name" class="from_label">登录名：</label>   
		        <input class="easyui-validatebox" type="text" name="memberInfo.name" data-options="required:true" />   
		    </div>   
		    <div>   
		        <label for="memberInfo.realName" class="from_label">真实姓名：</label>   
		        <input class="easyui-validatebox" type="text" name="memberInfo.realName"/>   
		    </div>
		    <div>   
		        <label for="memberInfo.nickname" class="from_label">昵称：</label>   
		        <input class="easyui-validatebox" type="text" name="memberInfo.nickname"/>   
		    </div>
		    <div>   
		        <label for="memberInfo.password" class="from_label">密码：</label>   
		        <input class="easyui-validatebox" type="password" name="memberInfo.password"/>   
		    </div>
		    <div>   
		        <label for="memberInfo.phone" class="from_label">手机：</label>   
		        <input class="easyui-validatebox" type="text" name="memberInfo.phone"/>   
		    </div> 
		    <div>   
		        <label for="memberInfo.email" class="from_label">邮箱：</label>   
		        <input class="easyui-validatebox" type="text" name="memberInfo.email"/>   
		    </div> 
		    <div>   
		        <label for="memberInfo.age" class="from_label">年龄：</label>   
		        <input class="easyui-validatebox" type="text" name="memberInfo.age"/>   
		    </div>
		    
		    <div>   
		        <label for="memberInfo.sex" class="from_label">性别：</label>  
		        <select  class="easyui-combobox" name="memberInfo.sex">   
				    <option value="1" selected="selected">男</option>   
				    <option value="0">女</option>
				    <option value="2">保密</option>   
				</select>   
		    </div> 
		    <div>   
		        <label for="memberInfo.isEliteAccount" class="from_label">是否为金账户：</label>   
		        <select  class="easyui-combobox" name="memberInfo.isEliteAccount">   
				    <option value="0" selected="selected">否</option>   
				    <option value="1">是</option>
				</select>   
		    </div> 
		    <div>   
		        <label for="memberInfo.type" class="from_label">用户类型：</label>   
		        <select  class="easyui-combobox" name="memberInfo.type">   
				    <option value="0" selected="selected">个体用户</option>   
				    <option value="1">企业用户</option>
				</select>    
		    </div> 
		     <div>   
		        <label for="memberInfo.level" class="from_label">级别：</label>   
		        <select  class="easyui-combobox" name="memberInfo.level">   
				    <option value="1" selected="selected">1</option>   
				    <option value="2">2</option>
				    <option value="3">3</option>
				    <option value="4">4</option>
				    <option value="5">5</option>
				</select> 
		    </div> 
		    <div>   
		        <label for="memberInfo.price" class="from_label">财力值：</label>   
		       	<input class="easyui-validatebox" type="text" name="memberInfo.price"/>万元
		    </div> 
		    <div>   
		        <label for="memberInfo.describe" class="from_label">介绍：</label>   
		       	<textarea rows="5" cols="30" name="memberInfo.describe"></textarea>  
		    </div> 
		   <h1>理财信息</h1>
		    <div>   
		        <label for="idcard" class="from_label">身份证号：</label>   
		       	<input type="text" name="idcard"/>
		    </div>
		    <div>   
		        <label for="bankCard" class="from_label">绑定银行卡号：</label>   
		       	<input type="text" name="bankCard"/>
		    </div>
		    <div>   
		        <label for="bank" class="from_label">所属银行：</label>   
		       	<input type="text" name="bank"/>
		    </div> 
		     <div>   
		        <label for="investMoney" class="from_label">投资金额：</label>   
		       	<input type="text" name="investMoney"/>
		    </div>
		     <div>   
		        <label for="investingMoney" class="from_label">投资中金额：</label>   
		       	<input  type="text" name="investingMoney"/>
		    </div>
		     <div>   
		        <label for="useableMoney" class="from_label">可用余额：</label>   
		       	<input  type="text" name="useableMoney"/>
		    </div>
		    <div>   
		        <label for="accountTotalMoney" class="from_label">账面总余额：</label>   
		       	<input  type="text" name="accountTotalMoney"/>
		    </div>
		     <div>   
		        <label for="freezeMoney" class="from_label">冻结余额：</label>   
		       	<input  type="text" name="freezeMoney"/>
		    </div>
		     <div>   
		        <label for="unfreezeMoney" class="from_label">未冻结余额：</label>   
		       	<input  type="text" name="unfreezeMoney"/>
		    </div>
		     <div>   
		        <label for="useableScore" class="from_label">可用积分：</label>   
		       	<input  type="text" name="useableScore"/>
		    </div>
		    <div>   
		        <label for="allowOnline" class="from_label">允许上线：</label>   
		       	<select  class="easyui-combobox" name="allowOnline">   
				    <option value="1" selected="selected">允许</option>   
				    <option value="0">不允许</option>
				</select> 
		    </div>
		    <div>   
		        <label for="custManagerId" class="from_label">客户经理ID：</label>   
		       	<input  type="text" name="custManagerId"/>
		    </div>
		    <div>   
		        <label for="managerName" class="from_label">客户经理姓名：</label>   
		       	<input  type="text" name="managerName"/>
		    </div>
		</form>  
	</div>
	<div align="center" class="footer-oper">
   	    <div class="content-oper">
	    	 <a  id="submitInvestMemberEdit_btn" class="easyui-linkbutton my-search-button" data-options="iconCls:'icon-2012092109942'" plain="true">保存</a>
		     &nbsp;&nbsp;&nbsp;&nbsp;
		     <a  onclick="javascript:$('#investMemberSaveForm').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
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