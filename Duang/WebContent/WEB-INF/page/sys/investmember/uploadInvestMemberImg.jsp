<%@page import="org.duang.util.DataUtils"%>
<%@page import="org.duang.entity.MemberInfo"%>
<%@page import="org.duang.entity.InvestMember"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path+"/";
	MemberInfo minfo = (MemberInfo)request.getAttribute("memberInfo");
	//获取文件路径
	String imgPath = (String)request.getAttribute("path");
	//判断是否有文件
	boolean isImage = false;
	//没有文件的时候提示信息
	String msg = "";
	if(DataUtils.notEmpty(imgPath)){
		String relPath = request.getSession().getServletContext().getRealPath("/");
		//判断文件是否存在
		File file = new File(relPath+imgPath);
		if (!file.exists()) {
			isImage = false;
			msg = "还没有上传文件";
		}else{
			isImage = true;
			imgPath = path+imgPath;
		}
	}else{
		msg = "还没有上传文件";
		isImage = false;
	}
	
%>
<%@ include file="/page/inc/inc.jsp"%>
<body style="background-color:#fff;" class="bodyContent">
	<div class="content">
		<div>   
			<form id="uploadUserImageForm">
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
	               <input style="width:45px;" type="button" value="上传" onclick="doUpload()"/>  
		    </form> 
		 </div>
		 <div style="margin:20px;" id="uploadedImage_view">
		 <%
		 	if(isImage){
		 %>
		   		<img src="<%=imgPath%>" alt="头像" style="max-width:90%;max-height:90%">
		  <%
		 	}else{
		  %>
		  	<span><%=msg%></span>
		  <%
		 	}
		  %>
		</div>
		<div id="uploadedImage_preview" style="margin:20px;max-width:90%;max-height:90%"></div>  
	</div>
	<script type="text/javascript">
		var type="${type}";
		var userId ="${entity.id}";
		
		function preview1(file) {
			$("#uploadedImage_view").hide();
            var img = new Image(), 
            url = img.src = URL.createObjectURL(file);
            var $img = $(img);
            img.onload = function() {
                URL.revokeObjectURL(url);
                $('#uploadedImage_preview').empty().append($img);
            }
        }
        function preview2(file) {
            var reader = new FileReader();
            reader.onload = function(e) {
                var $img = $('<img>').attr("src", e.target.result);
                $('#uploadedImage_preview').empty().append($img);
            }
            reader.readAsDataURL(file);
        }
         
        $(function() {
            $('[type=file]').change(function(e) {
                var file = e.target.files[0];
                preview1(file);
            });
        });
		
		function doUpload() {  
		     var formData = new FormData($( "#uploadUserImageForm" )[0]); 
		     console.info(formData);
		     $.ajax({  
		          url: 'fileUpload!uploadUserImage.do?type='+type+'&id='+userId ,  
		          type: 'POST',  
		          data: formData,  
		          async: false,  
		          cache: false,  
		          contentType: false,  
		          processData: false,  
		          success: function (data) { 
		        	  data = JSON.parse(data);
		              if(data.result==true){
				    		window.parent.reloadDataGrid();
				    		parent.layer.closeAll();
				    	}else{
				    		layer.msg(data.msg, {time: 3000});
				    	}
		          }  
		     });  
		}
	</script>
</body>