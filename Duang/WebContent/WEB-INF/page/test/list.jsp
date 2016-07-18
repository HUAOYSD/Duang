<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<% String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>列表</title>
<script type="text/javascript">
	function chakan(id){
		window.location.href="<%=path %>/sysseraction!findSysUser.do?id="+id;
	}
	
	function shanchu(id){
		window.location.href="<%=path %>/sysseraction!deleteSysUser.do?id="+id;
	}
	
	function toadd(){
		window.location.href="add.jsp";
	}
</script>
</head>
<body>
	<div>
		<form action="<%=path %>/sysseraction!queryList.do" method="post">
			<div>
				<input name="search_name" value=""/>
				<input name="search_email" value=""/>
				<input name="查询" value="" type="button" onclick="javascript:document.forms[0].submit();"/>
				<input name="新增" value="" type="button" onclick="toadd()"/>
			</div>
			<div>
				<table cellpadding="0" cellspacing="0">
					<tr>
						<td>ID</td>
						<td>名字</td>
						<td>密码</td>
						<td>邮件</td>
						<td colspan="2">操作</td>
					</tr>
					<c:forEach items="${requestScope.sysuserlist }" var="temp" varStatus="other">
						<tr>
							<td>${temp.id }</td>
							<td>${temp.name }</td>
							<td>${temp.password }</td>
							<td>${temp.email }</td>
							<td><input name="查看" value="" type="button" onclick="chakan('${temp.id }')"/></td>
							<td><input name="删除" value="" type="button" onclick="shanchu('${temp.id }')"/></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</form>
	</div>
</body>
</html>