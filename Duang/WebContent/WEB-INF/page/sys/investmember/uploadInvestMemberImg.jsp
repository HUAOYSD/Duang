<%@page import="org.duang.util.DataUtils"%>
<%@page import="org.duang.entity.MemberInfo"%>
<%@page import="org.duang.entity.InvestMember"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="UTF-8"%>
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
		<!--only_show表示是否显示上传图片表单，如果为1，则显示；否则不显示 -->
		<c:if test="${only_show ne 1}">
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
		               <input type="file" id="file" name="file" accept=".jpg,.jpeg,.png,.gif"> 
		               <input style="width:45px;" type="button" value="上传" onclick="doUpload()" id="btn_submit_userImg"/>
			    </form> 
			 </div>
		</c:if>
		
		 <div style="margin:20px;" id="uploadedImage_view">
		 <%
		 	if(isImage){
		 %>
		   		<img src="<%=imgPath%>" alt="头像" height="90%" width="99%">
		  <%
		 	}else{
		  %>
		  	<span><%=msg%></span>
		  <%
		 	}
		  %>
		</div>
		<div id="uploadedImage_preview" style="margin:20px;max-width:200px;max-height:200px;display:block;"></div>  
	</div>
	<script type="text/javascript">
		//哪一种图片更改（0：头像，1：身份证前照，2：身份证后照）
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
	    		          url: 'fileUpload!uploadUserImage.do?type='+type+'&id='+userId ,  
	    		          type: 'POST',  
	    		          data: formData,  
	    		          async: false,  
	    		          cache: false,  
	    		          contentType: false,  
	    		          processData: false,  
	    		          success: function (data) {
	    		        	  layer.closeAll('loading');
	    		        	  $("#btn_submit_userImg").attr("disabled", false);
	    		        	  data = JSON.parse(data);
	    		              if(data.result==true){
	    				    		window.parent.reloadDataGrid();
	    				    		parent.layer.closeAll();
	    				    	}else{
	    				    		layer.msg(data.msg, {time: 3000});
	    				    	}
	    		          },
	    		          error: function(data){
	    		        	  layer.closeAll('loading');
	    		        	  $("#btn_submit_userImg").attr("disabled", false);
	    		          }
	    		     });  
	        	}else{
	        		layer.msg("上传文件格式不合法",{time:3000});
	        	}
	        }
		}
	</script>
</body>