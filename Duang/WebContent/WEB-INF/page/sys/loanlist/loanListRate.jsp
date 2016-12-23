<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<body class="easyui-layout">
	<div class="body" id="body_loanlist" region="center" style="border:0px;" align="center">
		<div align="center" style="margin-top:40px;width:500px;">
			<table id="loanlistrate_table" class="easyui-propertygrid" style="width:500px"   
	        data-options="url:'loanlistrate!query.do',showGroup:true,scrollbarSize:0"></table> 
        </div>	
	</div>
	<script type="text/javascript">
		//记录原基数值
		var value ="";
		$('#loanlistrate_table').propertygrid({
			onBeforeEdit:function(rowIndex, rowData){
				value=rowData.value;
			},
			onAfterEdit: function(rowIndex, rowData, changes){
				//不相等说明修改了，进行保存到数据库总
				if(value != rowData.value){
					var confirmIndex = layer.confirm('确定要将【'+rowData.name+'】的值从 '+value+'\t更改为\t'+rowData.value, {
						  btn: ['确定','取消'] //按钮
						}, function(){
							layer.close(confirmIndex);
							$.ajax({
								   type: "POST",
								   url: "loanlistrate!edit.do",
								   data: "name="+rowData.name+"&value="+rowData.value,
								   success: function(data){
									   data = JSON.parse(data);
									   console.info(data);
									   if(data.result){
									    	layer.msg("修改成功",{icon:1});  
									   }
								   }
								});
						}, function(){
						  layer.close(confirmIndex);
					});
				}
			}
		});
	</script>
</body>