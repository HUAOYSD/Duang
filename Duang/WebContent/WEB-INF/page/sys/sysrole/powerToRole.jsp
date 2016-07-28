<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<form id="PowerToRoleForm" method="post" data-options="novalidate:true">
	<input type="hidden" name="sysRoleId" id="sysRoleIdByOpt" value="${param.sysRoleId}">
	<tr>
		<td align="right" style="width: 110px;" >
			设定权限：
		</td>
		<td>
			<div align="left">
				<ul id="powerTree" ></ul>
			</div>
		</td>
	</tr>
	<input type="hidden" name="powerIds" id="powerIds" />
	<div align="center">
		<a href="javascript:;" class="easyui-linkbutton" icon="icon-ok"
			onclick="javascript:updatePower()">保存</a>
	</div>
</form>
<script type="text/javascript">
//clearFormVal("RoleAddForm");
//clearFormVal("RoleEditForm");
//clearFormVal("PowerToRoleForm");
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
	
$("#powerTree").tree("collapseAll");
	
function updatePower() {
	  var nodes = $('#powerTree').tree('getChecked');
	  var powerIds = "";
	  $.each(nodes,function(i,n){
		  powerIds += n["id"]+"space";
	  });
	  $("#powerIds").val(powerIds);
	  $("#PowerToRoleForm").form('submit',{
			url:"sysrole!updateRolePower.do",
			success : function(data) {
				var result = eval('(' + data + ')');
				if(result.success){
					$.messager.alert('成功','更新成功','info');
				}else{
					$.messager.alert('错误','更新失败','error');
				}
				$('#powerToRoleView').window('close');
				loadRoleList("sysrole!queryRolePageList.do");
			}
	   });
 }
</script>
