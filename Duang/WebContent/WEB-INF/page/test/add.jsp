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
<title>增加</title>
<script type="text/javascript">
	
</script>
</head>
<body>
	<div>
		<form action="<%=path %>/sysseraction!saveSysUser.do" method="post">
			<div>
				<input name="返回" value="返回" type="button" onclick="javascript:window.history.back();"/>
			</div>
			<div>
				ID:<input name="id"/><br/>
				名字:<input name="name"/><br/>
				密码:<input name="password"/><br/>
				邮箱:<input name="email"/><br/>
				
				<input name="增加" value="增加" type="button" onclick="javascript:document.forms[0].submit();"/>
			</div>
		</form>
	</div>
</body>
</html>