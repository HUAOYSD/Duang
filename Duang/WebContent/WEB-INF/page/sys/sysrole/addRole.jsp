<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<form id="RoleAddForm" class="easyui-form"  method="post" data-options="novalidate:true">
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
					<ul id="powerTree"  class="easyui-tree" data-options="url:'syspower_getPowerTreeCheckbox.do',checkbox:true,state:'closed'"></ul>
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
	$("#powerTree").tree("collapseAll");
}

/**
 * 添加角色
 */
function addRole() {
	  var nodes = $('#powerTree').tree('getChecked');
	  var powerIds = "";
	  $.each(nodes,function(i,n){
		  powerIds += n["id"]+"space";
	  });
	  $("#powerIds").val(powerIds);
   	  var name = $.trim($("#sysRoleName").val());
      $("#RoleAddForm").form('submit',{
		   	url:"sysrole_saveRole.do",
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
					$.messager.alert('成功','添加成功','info');
				}else{
					$.messager.alert('错误','添加失败','error');
				}
				$('#addRoleView').window('close');
				loadRoleList("sysrole!queryRolePageList.do");
			}
	   });
}
</script>
