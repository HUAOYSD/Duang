<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<form id="PowerAddForm" style="margin-top:10px;" action="syspower!savePower.do" method="post" data-options="novalidate:true">
	<table colspan=4 border="0" style="" cellspacing="8" cellpadding="5">
		<tr>
			<td align="right" style="width: 105px;">
				上级权限：
			</td>
			<td>
				<input class="easyui-combotree" id="parentId" name="parentId" style="width: 216px; height: 24px" 
					data-options="valueField:'parentId',textField:'parentName',url:'syspower!getPowerTreeCheckbox.do',panelHeight:'200',editable:false,required:'true', missingMessage:'请选择上级权限！'" />
			</td>
		</tr>
		<tr>
			<td align="right" style="width: 105px;">
				权限名称：
			</td>
			<td>
				<input name="name" id="powerName" style="width: 216px; height: 24px"
					 class="easyui-validatebox " data-options="required:true, missingMessage:'请填写权限名称！'"/>
			</td>
		</tr>
		<tr>	
			<td align="right" style="width: 105px;">
				访问地址：
			</td>
			<td>
				<input  name="url" id="url" style="width: 216px; height: 24px"
					 class="easyui-validatebox" data-options="required:true, missingMessage:'请填写访问地址！'" />
			</td>
		</tr>
		<tr>	
			<td align="right" style="width: 105px;">
				序号：
			</td>
			<td>
				<input  name="sortIndex" id="sortIndex" style="width: 216px; height: 24px"
					 class="easyui-validatebox" data-options="required:true, missingMessage:'请填写序号！'" />
			</td>
		</tr>
	</table>
		<br/>
		<div  align="center">
				 <a href="javascript:;" 
				 class="easyui-linkbutton" icon="icon-ok"
				  onclick="javascript:addPower()">保存</a>  
		</div>
</form>
<script type="text/javascript">
clearFormVal("PowerAddForm");
clearFormVal("UserEditForm");
</script>
