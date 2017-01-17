<%@page import="org.duang.util.DataUtils"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="UTF-8"%>
<% 
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path+"/";
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
		<!--type表示是否显示上传图片表单，如果为1，则显示；否则不显示 -->
		<c:if test="${type eq 'upload'}">
			<div>   
				<form id="uploadBindAdLinkImgForm">
					   <label for="photoImg1" class="from_label">广告图片：</label>
		               <input type="file" id="file" name="linkAddress" accept=".jpg,.jpeg,.png,.gif"> 
		               <input style="width:45px;" type="button" value="上传" onclick="doUpload()" id="btn_submit_bindAdLinkImg"/>
			    </form> 
		 	</div>
		</c:if>
		 <div style="margin:20px;" id="uploadedImage_view">
		 <%
		 	if(isImage){
		 %>
		   		<img src="<%=imgPath%>" alt="图片">
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
			$("#btn_submit_bindAdLinkImg").attr("disabled", true); 
			//获取文件的属性
	    	var a = document.getElementById("file").files;
	        if(a[0].size>4*1024*1024){
	        	layer.msg("文件超出了指定4M大小",{time:3000});
	        }else{
	        	var fileName = a[0].name;
	        	var fileType = (fileName.substring(fileName.lastIndexOf(".")+1,fileName.length)).toLowerCase();
	        	//如果符合上传格式，则上传
	        	if((fileType=="jpg")||(fileType=="jpeg")||(fileType=="gif")||(fileType=="png")){
	        		 var formData = new FormData($( "#uploadBindAdLinkImgForm" )[0]); 
	        		 layer.load();
	    		     $.ajax({  
	    		          url: 'ad!uploadAdLinkImg.do?id=${entity.id}',  
	    		          type: 'POST',  
	    		          data: formData,  
	    		          async: false,  
	    		          cache: false,  
	    		          contentType: false,  
	    		          processData: false,  
	    		          success: function (data) {
	    		        	  layer.closeAll('loading');
	    		        	  $("#btn_submit_bindCardImg").attr("disabled", false);
	    		        	  data = JSON.parse(data);
	    		              if(data.result==true){
	    		            	  	layer.msg("上传成功！",{icon: 1});
	    				    		window.parent.reloadDataGrid();
	    				    		parent.layer.closeAll('iframe');
	    				    	}else{
	    				    		layer.msg(data.msg, {icon: 2});
	    				    	}
	    		          },
	    		          error: function(data){
	    		        	  layer.closeAll('loading');
	    		        	  $("#btn_submit_bindCardImg").attr("disabled", false);
	    		          }
	    		     });  
	        	}else{
	        		layer.msg("上传文件格式不合法",{time:3000});
	        	}
	        }
		}
	</script>
</body>