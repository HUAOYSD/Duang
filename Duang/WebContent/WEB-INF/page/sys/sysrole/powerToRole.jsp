<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/page/inc/inc.jsp"%>
<% String path = request.getContextPath();%>
<body style="background-color:#fff;">
	<div class="operate_div_form">
	  	<form id="PowerToRoleForm" method="post"> 
	  		<input type="hidden" name="powerIds" id="powerIds" />
	  		<input type="hidden" name="sysRoleId" id="sysRoleIdByOpt" value="${param.sysRoleId}">
		    <div style="text-align: center;">   
		        <label class="add_edit_form_label" style="margin-left: -8px;">权限分配</label>  
		        <hr/> 
		    </div>
		    <div style="height: 280px; padding-top:8px; overflow: scroll;border: 1px solid #000;">   
				<ul id="powerTree"></ul>
		    </div>
		</form>  
	</div>
	<div align="center" class="footer-oper">
   	    <div class="content-oper">
	    	 <a class="easyui-linkbutton my-search-button" onclick="updatePower()" data-options="iconCls:'icon-2012092109942'" plain="true">保存</a>
    	</div>
	</div>  
	<script type="text/javascript">
		$(function(){
			$("#powerTree").tree({
				url:'syspower!getPowerTreeCheckbox.do',
				checkbox:true,
				state:'closed',
				onLoadSuccess:function(node,data) {
					$.ajax({
						type:'POST',
						async:false,
						url:"sysrole!getRolePowerInfo.do",
						data:"sysRoleId=" + "<%=request.getParameter("sysRoleId")%>",
						dataType:'json',
						success:function(msg) {
							$(msg).each(function(i, obj){
				                var n = $("#powerTree").tree('find',obj.id);
				                if(n){
				                    $("#powerTree").tree('check',n.target);
				                }
				            });
						}
					});
				}
			});
		});
		
		
		function updatePower() {
			  var nodes = $('#powerTree').tree('getChecked');
			  var powerIds = "";
			  $.each(nodes,function(i,n){
				  powerIds += n["id"]+"space";
			  });
			  if(powerIds.isNotNull()){
				  $.messager.progress();
				  $("#powerIds").val(powerIds);
				  $("#PowerToRoleForm").form('submit',{
						url:"sysrole!updateRolePower.do",
						success : function(data) {
							var result = eval('(' + data + ')');
							if(result.success){
								layer.msg("操作成功", {time: 1500});
								parent.layer.closeAll();
							}else{
								$.messager.progress("close");
								layer.msg("更新失败", {time: 1500});
							}
						}
				   });
				   $.messager.progress("close");
			  }else{
			  	   layer.msg("请选择权限", {time: 1500});
			  }
		}
	</script>
</body>