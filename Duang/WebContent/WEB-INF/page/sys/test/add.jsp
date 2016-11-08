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
function doUpload() {
	$("#btn_submit_userImg").attr("disabled", true); 
	//获取文件的属性
	var a = document.getElementById("file").files;
    if(a[0].size>4*1024*1024){
    	layer.msg("文件超出了指定4M大小",{time:3000});
    }else{
    	var fileName = a[0].name;
    	var fileType = (fileName.substring(fileName.lastIndexOf(".")+1,fileName.length)).toLowerCase();
    	//如果符合上传格式，则上传
    	if((fileType=="jpg")||(fileType=="jpeg")||(fileType=="gif")||(fileType=="png")){
    		 var formData = new FormData($( "#uploadUserImageForm" )[0]); 
    		 layer.load();
		     $.ajax({  
		          url: 'provider_upload!uploadMemberHeadImg.do?type=head&id=d2eef99f254a4bd1b00d8609dfc86059',  
		          type: 'POST',  
		          data: formData,  
		          async: false,  
		          cache: false,  
		          contentType: false,  
		          processData: false,  
		          success: function (data) {
		        	  data = JSON.parse(data);
		              console.info(data);
		          }
		          
		     });  
    	}else{
    		layer.msg("上传文件格式不合法",{time:3000});
    	}
    }
}
</script>
</head>
<%@ include file="/page/inc/inc.jsp"%>
<body>
	<div>
		<form id="uploadUserImageForm">
              <input type="file" id="file" name="file" accept=".jpg,.jpeg,.png,.gif"> 
              <input style="width:45px;" type="button" value="上传" onclick="doUpload()" id="btn_submit_userImg"/>
		</form> 
	</div>
</body>
</html>