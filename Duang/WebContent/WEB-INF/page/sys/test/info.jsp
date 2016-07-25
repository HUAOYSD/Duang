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
<title>详情</title>
<script type="text/javascript">
	
</script>
</head>
<body>
	<div>
		<form action="<%=path %>/sysseraction.do" method="post">
			<div>
				<input name="返回" value="返回" type="button" onclick="javascript:window.history.back();"/>
			</div>
			<div>
				<table cellpadding="0" cellspacing="0">
					<tr>
						<td>ID</td>
						<td>名字</td>
						<td>密码</td>
						<td>邮件</td>
					</tr>
						<tr>
							<td>${entity.id }</td>
							<td>${entity.name }</td>
							<td>${entity.password }</td>
							<td>${entity.email }</td>
						</tr>
				</table>
			</div>
		</form>
	</div>
</body>
</html>