<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="easyui-accordion" data-options="fit:true,border:false">
	<s:iterator value="menulist" id="temp" status='other'>
		<s:if test="#other.isFirst"> 
		<div title="<s:property value='#temp.key.name'/>" data-options="iconCls:'<s:property value="#temp.key.icon"/>',selected:true">
		</s:if>
		<s:else>  
		<div title="<s:property value='#temp.key.name'/>" data-options="iconCls:'<s:property value="#temp.key.icon"/>'">
		</s:else>
		
		<s:iterator value="#temp.value" id="temps" status='others'>
			<div class="accordion-item">
	            <div class="name">
	            	<span iconcls="<s:property value="#temps.iconCls"/>" 
	            		  class="icon <s:property value="#temps.iconCls"/>">&nbsp;</span>
	                <a href="javascript:addTab('<s:property value="#temps.name"/>',
	                						   '<s:property value="#temps.url"/>',
	                						   '<s:property value="#temps.iconCls"/>')">
	                	<s:property value="#temps.name"/>
	                </a>
	            </div>
	        </div>
		</s:iterator>
		
		</div>
	</s:iterator>    
</div>