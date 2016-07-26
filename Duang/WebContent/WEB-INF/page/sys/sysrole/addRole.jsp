<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<form id="RoleAddForm"  method="post">
	<table colspan=4 border="0" style="width: 400px;" cellspacing="10" cellpadding="5">
		<tr>
			<td align="right" style="width: 110px;">
				角色名称：
			</td>
				<td>
				<input  name="sysRoleName" id="sysRoleName" style="width: 216px; height: 24px;border: 1px solid rgb(149, 184, 231);"
					 class="easyui-validatebox" data-options="required:true,missingMessage:'请填写角色名称'" />
			</td>
		</tr>
		<tr>
			<td align="right" style="width: 110px;">
				角色描述：
			</td>
			<td>
				<input name="sysRoleDesc" id="sysRoleDesc" style="width: 216px; height: 24px;border: 1px solid rgb(149, 184, 231);"
					 class="easyui-validatebox " data-options="required:true,missingMessage:'请填写角色描述'"/>
			</td>
		</tr>
		<tr>
			<td align="right" style="width: 110px;">
				设定权限：
			</td>
			<td>
				<div align="left">
					<ul id="powerTree1"  class="easyui-tree" data-options="url:'privilege_getPowerTreeCheckbox.action',checkbox:true,state:'closed'"></ul>
				</div>	
			</td>
		</tr>
	</table>
	<input type="hidden" name="powerIds" id="powerIds"/>
	<br>
	<div align="center" >
		<a href="javascript:;" class="easyui-linkbutton" icon="icon-ok"  onclick="javascript:addRole()">保存</a>  
	</div>
</form>
		
<script type="text/javascript">
var closeAll = function(){
	$("#powerTree1").tree("collapseAll");
}

/**
 * 添加角色
 */
function addRole() {
	  var nodes = $('#powerTree1').tree('getChecked');
	  var powerIds = "";
	  $.each(nodes,function(i,n){
		  powerIds += n["id"]+"space";
	  });
	  $("#powerIds").val(powerIds);
<%--	  $.ajax({--%>
<%--	        	type:"post",--%>
<%--	        	url:"role_saveRole.action",--%>
<%--	        	data:"powerIds=" + powerIds ,--%>
<%--	        	success:function(msg) {--%>
<%--	        		var result = eval('('+msg+')');--%>
<%--	        		if(result) {--%>
<%--	        			$.messager.alert("消息","删除成功","info");--%>
<%--	        		} else {--%>
<%--	        			$.messager.alert("消息","删除失败","info");--%>
<%--	        		}--%>
<%--	        		loadRoleList("role_queryRolePageList.action");--%>
<%--	        	}--%>
<%--	        });   --%>
		var name = $.trim($("#sysRoleName").val());
	    $("#RoleAddForm").form('submit',{
		   	url:"role_saveRole.action",
		   	onSubmit: function() {
		 		var con = true;
		 		$.ajax({
		 			 url:"role_checkRoleName.action",
		 			 dataType:'json',
		 			 async: false,
		 			 data:"name="+name,
		 			 success:function(data) {
		 				 if(!data.success) {
		 					 $.messager.alert('错误','角色名称已经存在，请重新填写！','error');
		 					 con = false;
		 				 }
		 			 }
		 		 });
		 		return con;
		 	},
			success : function(data) {
				var result = eval('(' + data + ')');
				if(result.success){
					// 成功
					$.messager.alert('成功','添加成功','info');
				}else{
					// 失败
					$.messager.alert('错误','添加失败','error');
				}
				$('#addRoleView').window('close');
				loadRoleList("sysrole!queryRolePageList.do");
			}
		});
}
</script>
