<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK" pageEncoding="utf-8"%>
<%@ include file="/page/inc/inc.jsp"%>
<% String path = request.getContextPath();%>
<body style="background-color:#fff;">
	<div class="operate_div_form">
	  	<form id="award_add_form" method="post" style="margin-bottom: 45px;"> 
	  		<div>   
		        <label for="name" class="add_edit_form_label">名称：</label>  
		        <input class="easyui-validatebox" name="name" data-options="required:true,missingMessage:'请填写名称'" /> 
		    </div> 
		    <div>   
		        <label for="description" class="add_edit_form_label">描述：</label>  
		        <input type="text"  name="description" style="width: 216px;"	data-options="" />
		    </div> 
		</form>  
	</div>
	<div align="center" class="footer-oper">
   	    <div class="content-oper">
	    	 <a id="award_add_form_submitbtn" class="easyui-linkbutton my-search-button" data-options="iconCls:'icon-2012092109942'" plain="true">保存</a>
		     &nbsp;&nbsp;&nbsp;&nbsp;
		     <a onclick="javascript:$('#award_add_form').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
    	</div>
	</div>  
	<script type="text/javascript">
		$("#award_add_form_submitbtn").on("click", function(){
			if(!$("#award_add_form").form('validate')){
				return false;
			}
			$.messager.progress();
			$("#award_add_form").form("submit",{    
			    url:"award!save.do",
			    success: function(data) {
			    	$.messager.progress("close");	
					var result = eval('(' + data + ')');
					if(result.success){
						layer.msg("添加成功", {time: 1500 });
						window.parent.reloadDataGrid();
			    		parent.layer.closeAll();
					}else{
						layer.msg("添加失败", {time: 1500 });
					}
				} 
			});
		});
	</script>
</body>