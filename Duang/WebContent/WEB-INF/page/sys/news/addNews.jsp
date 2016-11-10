<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK" pageEncoding="utf-8"%>
<%@ include file="/page/inc/inc.jsp"%>
<% String path = request.getContextPath();%>
<body style="background-color:#fff;">
	<div class="operate_div_form">
	  	<form id="news_add_form" method="post"> 
		    <div class="row">   
		        <label for="content" class="add_edit_form_label">新闻资讯内容：</label>   
		       	<textarea name="content" class="form-textarea" style="width:99%;height:300px;"></textarea>  
		    </div>
		</form>  
	</div>
	<div align="center" class="footer-oper">
   	    <div class="content-oper">
	    	 <a id="news_add_form_submitbtn" class="easyui-linkbutton my-search-button" data-options="iconCls:'icon-2012092109942'" plain="true">保存</a>
		     &nbsp;&nbsp;&nbsp;&nbsp;
		     <a onclick="javascript:$('#news_add_form').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
    	</div>
	</div>  
	<script type="text/javascript">
	$(function(){
		$('#news_add_form').form({    
		    url:"news!add.do",    
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
	$("#news_add_form_submitbtn").on("click", function(){
		if(!$("#news_add_form").form('validate')){
			return false;
		}
		$.messager.progress();
		$('#news_add_form').submit();
	});
	</script>
</body>