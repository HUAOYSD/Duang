<%@ page language="java" pageEncoding="UTF-8"%>
${msg}
<script type="text/javascript" charset="utf-8">
	//parent.$.messager.progress('close');
	setTimeout("parent.location.href='${pageContext.request.contextPath}/sys!goLoginView.do'",2000);
	function skip() {
		if (window != top) 
			top.location.href = "<%=request.getContextPath() %>/sys!goLoginView.do"; 
	}
</script>