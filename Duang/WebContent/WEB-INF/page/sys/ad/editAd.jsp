<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK" pageEncoding="utf-8"%>
<%@ include file="/page/inc/inc.jsp"%>
<% String path = request.getContextPath();%>
<body style="background-color:#fff;">
	<div class="operate_div_form">
	  	<form id="ad_edit_form" method="post"> 
	  		<input type="hidden" name="id">
	  		<div>   
		        <label for="name" class="add_edit_form_label">名称：</label>  
		        <input class="easyui-validatebox" name="name" data-options="required:true,validType:'unnormal',missingMessage:'请填写名称'" /> 
		    </div> 
		    <div>   
		        <label for="linkAddress" class="add_edit_form_label">访问地址：</label>  
		        <input class="easyui-validatebox" name="linkAddress" data-options="required:true,validType:'url',missingMessage:'请填写广告访问地址'" /> 
		    </div>
		    <div>   
		        <label for="remark" class="add_edit_form_label">说明：</label>   
		       	<textarea rows="5" cols="20" name="remark"></textarea>  
		    </div>
		</form>  
	</div>
	<div align="center" class="footer-oper">
   	    <div class="content-oper">
	    	 <a id="ad_edit_form_submitbtn" class="easyui-linkbutton my-search-button" data-options="iconCls:'icon-2012092109942'" plain="true">保存</a>
		     &nbsp;&nbsp;&nbsp;&nbsp;
		     <a onclick="javascript:$('#ad_edit_form').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
    	</div>
	</div>  
	<script type="text/javascript">
	$(function(){
		$.ajax({
			type:'POST',
			url:"ad!getAdInfoById.do",
			data:"id=${entity.id}",
			dataType:'json',
			success:function(msg) {
				$("#ad_edit_form").form('load',msg);
			}
		});
	});
	$(function(){
		$('#ad_edit_form').form({    
		    url:"ad!update.do",    
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
	$("#ad_edit_form_submitbtn").on("click", function(){
		if(!$("#ad_edit_form").form('validate')){
			return false;
		}
		$.messager.progress();
		$('#ad_edit_form').submit();
	});
	</script>
</body>