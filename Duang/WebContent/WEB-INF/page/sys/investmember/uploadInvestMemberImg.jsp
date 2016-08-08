<%@page import="org.duang.entity.InvestMember"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ include file="/page/inc/inc.jsp"%>
<body style="background-color:#fff;" class="bodyContent">
	<div class="content">
		<div>   
			<form action="fileUpload!uploadUserImage.do?type=${type}&id=${entity.id}" enctype="multipart/form-data" method="post">
				   <c:if test="${type eq 0}">
				   		<label for="userImg" class="from_label">头像：</label>
				   </c:if>
				   <c:if test="${type eq 1}">
				   		<label for="idcardImg1" class="from_label">身份证前照：</label>
				   </c:if>  
				   <c:if test="${type eq 2}">
				   		<label for="idcardImg2" class="from_label">身份证后照：</label>
				   </c:if>
	               <input type="file" name="file"> 
	               <input style="width:45px;" type="submit" value="提交"/>  
		    </form> 
		 </div>  
	</div>
	<script type="text/javascript">
		$(function(){
		});
	</script>
</body>