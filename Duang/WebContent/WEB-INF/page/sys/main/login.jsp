<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.lang.Math"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<script type="text/javascript" src="<%=path%>/js/util.js"></script>
<script type="text/javascript">
</script>
<style>
	.bg {
		position: absolute;
		min-height: 100% !important;
		width: 100%;
		z-index: 0;
		background-color: #f1f1f1;
	}
	.background-color2 {
		background: #fff;
		position: absolute;
		top: 0;
		left: 0;
		width: 100%;
		height: 100%;
		opacity: .5;
	}
	#register-info-describe{
		position: absolute;
		top: 240px;
		width:100%;
		font-size:18px;
		font-family: 'Microsoft YaHei' , Helvetica, Arial, sans-serif;
		letter-spacing:1.5px;
		margin-left:-15px;
	}
	#register-info{
		background-color:#fff;
		width:385px;
		height:280px;
		-webkit-border-radius: 10px; /* 只支持webkit内核的浏览器 */
		border-radius: 10px; /* 只支持IE内核的浏览器（IE>=7) */
		-moz-border-radius : 10px; /* 只支持Mozilla内核的浏览器 */
		border: 1px solid #dedede;
    	border-radius: 2px;
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
	.branding{
		background: url(resources/image/logo.png) no-repeat center top;
		height: 70px;
    	margin-bottom: 15px;
    	margin-left:-15px;
    	margin-top:70px;
    	background-size: 68px 68px;
    	font-style: normal;
    	font-weight: 300;
    	text-align: center;
    	color: #aaa;
    	-webkit-font-smoothing: antialiased;
	}
	.heading h1{
	    font-size: 32px;
	    line-height: 36px;
	    margin-bottom: 6px;
	    color: #6a6a6a;
	    margin: 0 0 1.33333333rem;
	    font-family: caecilia,Times,serif;
    	font-style: normal;
    	font-weight: 300;
    	-webkit-font-smoothing: antialiased;
    	display: block;
    	-webkit-margin-before: 0.67em;
    	-webkit-margin-after: 0.67em;
    	-webkit-margin-start: 0px;
    	-webkit-margin-end: 0px;
    	margin-left:-15px;
	}
</style>
</head>
<body>
	<div class="container bg" id="container">
		<div class="background-color2"></div>
		<div class="branding"></div>
		<div class="col-lg-12 heading" align="center">
			<h1>登录</h1>
		</div>
		<div id="register-info-describe" align="center">
			<div id="register-info">
				<div class="info" align="center" style="margin-top:20px;">
					<form action="<%=path %>/sys!login.do" method="post" id="loginForm">
						<div class="col-lg-12">
							<div class="form-group">
								<input style="height:40px;width:320px;" type="text" class="form-control" name="name" id="name" placeholder="用户名">
							</div>
							<c:choose>
								<c:when test="${msg eq null }">
									<label id="nameMsg" style="display: none;" class="text-danger error-text">（用户名不能为空）</label>
								</c:when>
								<c:otherwise>
									<label id="nameMsg" style="" class="text-danger error-text">（${msg }）</label>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="col-lg-12">
							<div class="form-group">
								<input style="height:40px;width:320px;" type="password" class="form-control" name="password" id="password" placeholder="密码">
							</div>
							<label id="pwdMsg" style="display: none;" class="text-danger error-text">（密码不能为空）</label>
						</div>
						
						<div class="col-lg-12">
							<div class="col-lg-5" style="padding-right: 0px;padding-left: 0px;">
								<div class="form-group">
									<input style="height:40px;width:118px;" type="text" class="form-control" name="validateCode" id="validateCode" placeholder="验证码">
								</div>
							</div>
							<div class="col-lg-4" style="padding:0px 8px 0px 8px;">
								<img  src="<%=path %>/sys!getValidateCode.do" id="validateCodeImg" name="validateCode" onclick="getCheckCode()"></img>
							</div>
							<div class="col-lg-3" style="padding:8px 0px 0px 10px;">
								<a href="javascript:void(0)" onclick="getCheckCode()" style="text-decoration:none;font-size:13px;">&nbsp;换一张</a>
							</div>	
							<label id="vcMsg" style="display: none;" class="text-danger error-text">（验证码不能为空）</label>
						</div>
						<div class="col-lg-12" style="padding-top:15px;padding-left:0px;padding-right:0px;">
							<input style="height:40px;width:100%;" type="button" onclick="return loginSubmit();" class="btn btn-success" value="登录">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
<script type="text/javascript">
$("input[name='validateCode']").val("");
	
function getCheckCode(){
	document.getElementById("validateCodeImg").src="<%=path %>/sys!getValidateCode.do?v="+Math.random();
}
	
function loginSubmit(){
	  var name = $("#name").val();
	  var password = $("#password").val();
	  var validateCode = $("#validateCode").val();
	  if(name.isNotNull()){
		  $("#nameMsg").hide();
		  $("#name").removeClass("error-input");
	  }else{
		  $("#nameMsg").html("（用户名不能为空）");
		  $("#nameMsg").show();
		  $("#name").addClass("error-input");
		  $("#name").focus();
		  return false;
	  }
	  
	  if(password.isNotNull()){
		  $("#pwdMsg").hide();
		  $("#password").removeClass("error-input");
	  }else{
		  $("#pwdMsg").show();
		  $("#password").addClass("error-input");
		  $("#password").focus();
		  return false;
	  }
	  
	  if(validateCode.isNotNull()){
		  $("#vcMsg").hide();
		  $("#validateCode").removeClass("error-input");
		  $.ajax({
	   		  type: "POST",
	   		  url: "<%=path %>/sys!checkValidateCode.do",
	   		  data: "validateCode=" + validateCode,
	   		  dataType:'json',
	   		  success: function(msg){
	         	 if(!msg.success){
	         		$("#vcMsg").html("（验证码填写错误）");
	         		$("#vcMsg").show();
	  			    $("#validateCode").addClass("error-input");
	  			  	$("#validateCode").focus();
	  			    return false;
	         	 }else{
	         		$("#loginForm").submit();
	         	 }
	   		  },
	   		  error:function (XMLHttpRequest, textStatus, errorThrown) {
	   			 document.getElementById("validateCodeImg").src="<%=path %>/sys!getValidateCode.do?v="+Math.random();
	   		  }
   	      });
	  }else{
		  $("#vcMsg").html("（验证码不能为空）");
		  $("#vcMsg").show();
		  $("#validateCode").addClass("error-input");
		  $("#validateCode").focus();
		  return false;
	  }
	  return true;
}

document.onkeydown=function(event){
    var e = event || window.event || arguments.callee.caller.arguments[0];
    if(e && e.keyCode==13){ // enter 键
    	loginSubmit();
    }
}; 
</script>

</body>
</html>