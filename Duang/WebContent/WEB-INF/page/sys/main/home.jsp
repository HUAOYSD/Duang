<%@ page language="java" contentType="text/html; charset=GBK"
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
<script type="text/javascript">
function logout() {
	location.href="<%=path%>/sys!logout.do";
}

function login() {
	location.href="<%=path%>/sys!goLoginView.do";
}
</script>
<style type="text/css">
	a{
		text-decorationnone;
	}
</style>
</head>
<body class="easyui-layout">
	<!-- 头部标题 -->
	<div data-options="region:'north',border:false" style="height: 80px; padding: 5px;background:#fff;">
		<a href="http://localhost:8080/Duang" target="_blank"><img src="resources/image/logo.png" alt="logo" style="width:40px;height:40px;margin:10px 0px 10px 10px;"></a>
		<div style="margin-left:60px;margin-top:-35px;"></div>
		<c:choose>
			<c:when test="${sessionScope.sysUser == null}">
				<span class="loginInfo">
					<font font-size="15" style="color:rgb(72,139,209);" >欢迎使用本系统，您还未登录 &nbsp;&nbsp;&nbsp;&nbsp;</font>
					<a class="easyui-menubutton m-btn l-btn l-btn-plain"
					   onclick="login()"
					   href="javascript:void(0);"> 
					   <strong style="color:rgb(80,80,80);font-weight:normal;" >请登录</strong></a> 
				</span>
			</c:when>
			<c:otherwise>
					<span class="loginInfo">
						<font font-size="15" style="color:#000;" >当前用户：${sessionScope.sysUser.name} ，欢迎登录</font>
						<font font-size="15" style="color:#000;" >系统角色：${sessionScope.sysUser.sysRole.roleName}</font>&nbsp;&nbsp;
					    <a onclick="$.messager.confirm('注销','您确定要退出么?',function(r){if(r){logout()};});"  href="javascript:void(0);"> 
					   		<strong style="color:#000;font-weight:normal;">注销 </strong>
					   	</a>&nbsp;&nbsp; 
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
			<div title="首页" style="padding: 5px; display: block;" data-options="href:'<%=path %>/page/home/dataCharts.html',closable:true">
			</div>
		</div>
	</div>

	<!-- 用于弹出框 -->
	<div id="parent_win"></div>
</body>
</html>