<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<form id="RoleEditForm" action="sysrole!updateRole.do" method="post" data-options="novalidate:true">
	<input type="hidden" name="sysRoleId" id="sysRoleId">
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
				<input name="sysRoleDesc" id="sysRoleDesc"style="width: 216px; height: 24px;border: 1px solid rgb(149, 184, 231);"
					 class="easyui-validatebox " data-options="required:true,missingMessage:'请填写角色描述'"/>
			</td>
		</tr>
<%--		<tr>--%>
<%--			<td align="right" style="width: 110px;">--%>
<%--				设定权限：--%>
<%--			</td>--%>
<%--			<td>--%>
<%--				<div align="left">--%>
<%--					<ul id="powerTree"  class="easyui-tree" data-options="url:'privilege_getPowerTreeCheckbox.action',onLoadSuccess:'',checkbox:true,state:'closed'"></ul>--%>
<%--				</div>	--%>
<%--			--%>
<%--			</td>--%>
<%--		</tr>--%>
		</table>
		<br>
		<div  align="center" >
				 <a href="javascript:;" 
				 class="easyui-linkbutton" icon="icon-ok"
				  onclick="javascript:updatePower()">保存</a>  
		</div>
		</form>
<script type="text/javascript">
$(function (){
	$.ajax({
		type:'GET',
		url:"sysrole!getRoleInfo.do",
		data:"sysRoleId=" + "<%=request.getParameter("sysRoleId")%>",
		dataType:'json',
		success:function(msg) {
			$("#RoleEditForm").form('load', msg);
		}
	});
});


function updatePower() {
 	  var name = $.trim($("#sysRoleName").val());
      $("#RoleEditForm").form('submit',{
		   	url:"sysrole!updateRole.do",
		   	onSubmit: function() {
		   		if($(this).form('enableValidation').form('validate')){
		   			if(name.isNotNull){
			   			var con = true;
				 		$.ajax({
				 			 url:"sysrole_checkRoleName.do",
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
			   		}else{
			   			return false;
			   		}
		   		}else{
		 			return false;
		 		}
		 	},
			success : function(data) {
				var result = eval('(' + data + ')');
				if(result.success){
					$.messager.alert('成功','更新成功','info');
				}else{
					$.messager.alert('错误','更新失败','error');
				}
				$('#editRoleView').window('close');
				loadRoleList("sysrole!queryRolePageList.do");
			}
	   });
}
</script>
