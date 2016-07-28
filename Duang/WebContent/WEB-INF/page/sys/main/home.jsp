<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理系统</title>
<%@ include file="/page/inc/inc.jsp"%>

<%-- <link rel="stylesheet" type="text/css" href="<%=path%>/css/home.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/left.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/js/easyui/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/js/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/js/easyui/themes/IconExtension.css" />
<script type="text/javascript" src="<%=path%>/js/easyui/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/layer/layer.js"></script>
<script type="text/javascript" src="<%=path%>/js/home.js"></script>
<script type="text/javascript" src="<%=path%>/js/extends.js"></script> --%>
<script type="text/javascript">
function logout() {
	location.href="<%=path%>/sys!logout.do";
}

function login() {
	location.href="<%=path%>/sys!goLoginView.do";
}
</script>
</head>
<body class="easyui-layout">
	<!-- 头部标题 -->
	<div data-options="region:'north',border:false"
		style="height: 60px; padding: 5px; background: #F3F3F3">
		<a href="www.solooo.net"><span class="northTitle">www.solooo.net</span></a>
		<c:choose>
			<c:when test="${sessionScope.sysUser == null}">
				<span class="loginInfo">
					欢迎使用本系统，您还未登录 &nbsp;&nbsp;&nbsp;&nbsp;
					<a class="easyui-menubutton m-btn l-btn l-btn-plain"
					   onclick="login()"
					   href="javascript:void(0);"> 
					   <strong style="color:rgb(80,80,80);font-weight:normal;" >请登录</strong></a> 
				</span>
			</c:when>
			<c:otherwise>
				<span class="loginInfo">
					登录用户：[${sessionScope.sysUser.name}]&nbsp;&nbsp;
					角色：[${sessionScope.sysUser.sysRole.roleName}]
					<a class="easyui-menubutton m-btn l-btn l-btn-plain"
					   onclick="$.messager.confirm('注销','您确定要退出么?',function(r){if(r){logout()};});"
					   href="javascript:void(0);"> 
					   <strong style="color:rgb(80,80,80);font-weight:normal;" >注销 </strong></a> 
				</span>
			</c:otherwise>
		</c:choose>

	</div>

	<!-- 左侧导航 -->
	<div data-options="region:'west',split:true,title:'导航菜单', fit:false, href:'<%=path %>/sys!goLeft.do'" style="width: 200px;"></div>

	<!-- 页脚信息 -->
	<div data-options="region:'south'"  style="text-align:center;align:center;height: 20px; background: #F3F3F3; padding: 2px; vertical-align: middle;">
		<span id="" style="text-align:center;align:center;">系统版本：V1.0</span> <span id="nowTime"></span>
	</div>

	<!-- 内容tabs -->
	<div id="center" data-options="region:'center'"
		style="overflow: hidden">
		<div id="tabs" class="easyui-tabs">
			<div title="首页" style="padding: 5px; display: block;" data-options="href:'<%=path %>/page/home/dataCharts.html'">
			</div>
		</div>
	</div>

	<!-- 用于弹出框 -->
	<div id="parent_win"></div>
</body>
</html>