<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="easyui-accordion" data-options="fit:true,border:false">
	<s:iterator value="topMenuList" id="menu" status='other'>
		<s:if test="#other.isFirst"> 
			<div title="${menu.name }" data-options="iconCls:'${menu.icon }',selected:true">
		</s:if>
		<s:else>  
			<div title="${menu.name }" data-options="iconCls:'${menu.icon }'">
		</s:else>
				<ul class="easyui-tree"
					data-options="url:'sys!submenu.do?powerId=${menu.id}'">
				</ul>
		</div>
	</s:iterator>    
</div>