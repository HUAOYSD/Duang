<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ include file="/page/inc/inc.jsp"%>
<body style="background-color:#fff;" class="bodyContent">
	<div class="content">
	  	<form id="applyLoanInfoForm" method="post"> 
		    <div>   
		        <label for="name" class="from_label">名称：</label>   
		        <input class="easyui-validatebox" type="text" name="name" data-options="required:true" />   
		    </div>   
		    <div>   
		        <label for="money" class="from_label">状态：</label> 
		         <input class="easyui-validatebox" type="text" name="name" data-options="required:true" />   
		    </div>
		    <div>   
		        <label for="publishTime" class="from_label">发布时间：</label>   
		        <input class="easyui-datetimebox" type="password" name="publishTime"/>  
		    </div>
		     <div>   
		        <label for="beginDate" class="from_label">有效期始：</label>   
		        <input class="easyui-datetimebox" type="password" name="beginDate"/>   
		    </div>
		    <div>   
		        <label for="endDate" class="from_label">有效期至：</label>   
		        <input class="easyui-datetimebox" type="text" name="endDate"/>   
		    </div>  
		    <div>   
		        <label for="content" class="from_label">内容：</label>   
		        <textarea rows="5" cols="30" name="content"></textarea>    
		    </div>
		    <input type="hidden" name="id" /> 
		</form>
	</div>
	<div align="center" class="footer-oper">
   	    <div class="content-oper">
	    	 <a  id="submitNotifycation_btn" class="easyui-linkbutton my-search-button" data-options="iconCls:'icon-2012092109942'" plain="true">保存</a>
		     &nbsp;&nbsp;&nbsp;&nbsp;
		     <a  onclick="javascript:$('#applyLoanInfoForm').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
    	</div>
	</div>  
	<script type="text/javascript">
	  	//id不为空，说明是编辑 
		var id = "${id}";
		$(function(){
			if(id != null && id !=""){
				$.ajax({
					type:'POST',
					url:"notification!getNotificationById.do",
					data:"id=${id}",
					dataType:'json',
					success:function(msg) {
						$("#applyLoanInfoForm").form('load',msg);
					}
				});
			}
		});
		
		$(function(){
			$('#applyLoanInfoForm').form({    
			    url:"notification!saveNotification.do",    
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
		$("#submitNotifycation_btn").on("click", function(){
			if(!$("#applyLoanInfoForm").form('validate')){
				return false;
			}
			$.messager.progress();
			$('#applyLoanInfoForm').submit();
		});
	</script>
</body>