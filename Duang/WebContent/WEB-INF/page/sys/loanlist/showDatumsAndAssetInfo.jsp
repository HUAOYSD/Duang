<%@page import="org.duang.util.DataUtils"%>
<%@page import="org.duang.entity.MemberInfo"%>
<%@page import="java.io.File"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path+"/";
	//获取文件路径
	String imgPathStr = (String)request.getAttribute("path");
	//判断是否有文件
	boolean isImage = false;
	//没有文件的时候提示信息
	String msg = "";
	List<String> imgList = new ArrayList<String>();
	if(DataUtils.notEmpty(imgPathStr)){
		String[] imgPaths = imgPathStr.split(";");
		for(int i = 0;i< imgPaths.length;i++){
			imgList.add(path+imgPaths[i]);
		}
		isImage = true;
	}else{
		msg = "还没有上传文件";
		isImage = false;
	}
	
%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/photoGallery/css/lyh-photobox.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/photoGallery/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/photoGallery/css/style.css">
<script src="<%=request.getContextPath()%>/js/jquery-1.10.2.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/js/photoGallery/js/lyh-photobox.min.js"></script>
<body>
	<section id="gallery">
		<div class="container-fluid">
	        <div class="row">
				<% 
					for(String img : imgList){
				%>
					<a href="<%=img%>" data-toggle="lightbox" data-gallery="sigma-gallery" data-title="" data-style="width:100px;">
	            		<img src="<%=img%>" alt="个人资料" class="img-fluid sigmapad col-lg-4">
	            	</a>
				<%} %>
			</div>
		</div>
	</section>
	<script>
		$(document).ready(function ($) {
			$(document).delegate('*[data-toggle="lightbox"]', 'click', function(event) {
				event.preventDefault();
				return $(this).lyhlightbox({
					always_show_close: true
				});
			});
	
		});
	</script>
</body>
