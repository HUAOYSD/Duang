<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="UTF-8"%>
<% 
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ include file="/page/inc/inc.jsp"%>
<body style="background-color:#fff;" class="bodyContent">
	<div class="content">
	  	<form id="addInvestTicketForm" method="post"> 
		    <div>   
		        <label for="name" class="from_label">名称：</label>   
		        <input class="easyui-validatebox" type="text" name="name" data-options="required:true" />   
		    </div>   
		    <div>   
		        <label for="remark" class="from_label">说明：</label>   
		        <input class="easyui-validatebox" type="text" name="remark"/>   
		    </div>
		    <div>   
		        <label for="money" class="from_label">价值：</label>   
		        <input class="easyui-validatebox" type="text" name="money"/>   
		    </div>
		    <div>   
		        <label for="minMoney" class="from_label">最小金额权限：</label>   
		        <input class="easyui-validatebox" type="text" name="minMoney"/>   
		    </div>
		     <div>   
		        <label for="beginTime" class="from_label">有效期始：</label>   
		        <input class="easyui-datetimebox" type="password" name="beginTime"/>   
		    </div>
		    <div>   
		        <label for="endTime" class="from_label">有效期至：</label>   
		        <input class="easyui-datetimebox" type="text" name="endTime"/>   
		    </div>  
		    <div>   
		        <label for="describes" class="from_label">详情介绍：</label>  
		        <textarea rows="5" cols="30" name="describes"></textarea>  
		    </div> 
		    <input type="hidden" name="id" /> 
		    <input type="hidden" name="createTime"/>  
		    <input type="hidden" name="state"/>  
		</form>
	</div>
	<div align="center" class="footer-oper">
   	    <div class="content-oper">
	    	 <a  id="submitInvestTicket_btn" class="easyui-linkbutton my-search-button" data-options="iconCls:'icon-2012092109942'" plain="true">保存</a>
		     &nbsp;&nbsp;&nbsp;&nbsp;
		     <a  onclick="javascript:$('#addInvestTicketForm').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
    	</div>
	</div>  
	<script type="text/javascript">
	  	//id不为空，说明是编辑 
		var id = "${id}";
		$(function(){
			if(id != null && id !=""){
				$.ajax({
					type:'POST',
					url:"investticket!getInvestTicketById.do",
					data:"id=${id}",
					dataType:'json',
					success:function(msg) {
						$("#addInvestTicketForm").form('load',msg);
					}
				});
			}
		});
		
		$(function(){
			$('#addInvestTicketForm').form({    
			    url:"investticket!saveInvestTicket.do",    
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
		$("#submitInvestTicket_btn").on("click", function(){
			if(!$("#addInvestTicketForm").form('validate')){
				return false;
			}
			$.messager.progress();
			$('#addInvestTicketForm').submit();
		});
	</script>
</body>