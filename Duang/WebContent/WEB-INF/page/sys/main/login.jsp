<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.lang.Math"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/js/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="<%=path%>/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/interactive/js/jquery.interactive_bg.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$(".bg").interactive_bg();
		$("#btns").interactive_bg({
			strength: 10,
			scale: 1.15,
			contain: false,
			wrapContent: true
		});
	});
	function getCheckCode(){
		document.getElementById("validateCodeImg").src="<%=path %>/sys!getValidateCode.do?v="+Math.random();
	}
</script>
<style>
	.bg {
		position: absolute;
		min-height: 100% !important;
		width: 100%;
		z-index: 0;
	}
	.background-color2 {
		background: #000000;
		position: absolute;
		top: 0;
		left: 0;
		width: 100%;
		height: 100%;
		opacity: .5;
	}
	#register-info-describe{
		position: absolute;
		top: 250px;
		width:100%;
		font-size:18px;
		font-family: 'Microsoft YaHei' , Helvetica, Arial, sans-serif;
		letter-spacing:1.5px;
	}
	#register-info{
		background-color:#fff;
		width:400px;
		height:420px;
		margin-top:40px;
		-webkit-border-radius: 10px; /* 只支持webkit内核的浏览器 */
		border-radius: 10px; /* 只支持IE内核的浏览器（IE>=7) */
		-moz-border-radius : 10px; /* 只支持Mozilla内核的浏览器 */
	}

	#register-info .info{
		padding:10px 20px 0px 20px;
	}
	#register-info #title{
		padding:5px 20px 0px 20px;
		
	}
	input{
		font-size:18px;
		font-family:'Microsoft YaHei' , Helvetica, Arial, sans-serif;
	}
	label{
		font-family:'Microsoft YaHei' , Helvetica, Arial, sans-serif;
		font-size:16px;
		letter-spacing:1.5px;
		font-weight: normal;
		
	}
	.error-text{
		font-family:'Microsoft YaHei' , Helvetica, Arial, sans-serif;
		font-size:12px;
		font-weight: normal;
	}
	
	.error-input{
		border-style: solid;
		border-color: red;
	}
	.col-lg-1,.col-lg-2,.col-lg-3,.col-lg-4,.col-lg-5,.col-lg-6,.col-lg-7,.col-lg-8,.col-lg-9,.col-lg-10,.col-lg-11,.col-lg-12{
		padding-left:0px;
		padding-right:0px;
	}
</style>
</head>
<body>
	<div class="container bg" id="container" data-ibg-bg="js/interactive/123.jpg">
		<div class="background-color2"></div>
		<div id="register-info-describe" align="center">
			<div id="register-info">
				<div id="title" align="left">
					<h3>登录</h3>
					<hr>
				</div>
				<div class="info" align="left">
					<form action="<%=path %>/sys!login.do" method="post">
						<div class="col-lg-12">
							<label class="control-label">用户名</label>&nbsp;&nbsp;<label class="text-danger error-text">（用户名不能为空）</label>
						</div>
						<div class="form-group">
							<input style="height:40px;" type="text" class="form-control error-input" name="name" placeholder="用户名">
						</div>
						<div class="col-lg-12">
							<label class="control-label">密码</label>
						</div>
						<div class="form-group">
							<input style="height:40px;" type="password" class="form-control" name="password" placeholder="请输入6-12位数字或字母">
						</div>
						<div class="col-lg-12">
								<label class="control-label">验证码</label>
						</div>
						<div class="col-lg-6" style="padding-right: 0px;padding-left: 0px;">
							<div class="form-group">
								<input style="height:40px;" type="text" class="form-control" name="validateCode" placeholder="验证码">
							</div>
						</div>
						<div class="col-lg-4" style="padding:0px 8px 0px 8px;">
							<img  src="<%=path %>/sys!getValidateCode.do" id="validateCodeImg" name="validateCode" onclick="getCheckCode()"></img>
						</div>
						<div class="col-lg-2" style="padding:8px 0px 0px 10px;">
							<a href="javascript:void(0)" onclick="getCheckCode()" style="text-decoration:none;font-size:13px;">换一张</a>
						</div>			
						<div class="col-lg-12 col-md-12 col-xs-12" style="padding-top:15px;padding-left:0px;padding-right:0px;">
							<input style="height:40px;width:100%;" type="submit" class="btn btn-success" value="登录">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>