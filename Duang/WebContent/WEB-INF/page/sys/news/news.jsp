<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<body class="easyui-layout">
	<div id="news_body" region="center" >
	  <div id="newsToolBar" style="margin-left:10px;">
	  	  <a id="news-add-btn" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
	      <a id="news-delete-btn" class="easyui-linkbutton" iconCls="icon-01" plain="true">禁用</a>
	      <a id="news-icon-btn" class="easyui-linkbutton" iconCls="icon-palette" plain="true">上传图片</a>
	  </div>
	  <!-- 数据表格区域 -->
	  <table id="news_table" style="table-layout:fixed;" ></table>
	</div>
	<script type="text/javascript" src="<%=path%>/ui/sys/news/news.js"></script>
	<script type="text/javascript">
		var APP_PATH = "<%=path%>";
		$("#submit_news_btn").on("click", function(){
			$('#querynewsForm').submit(); 
		});
	</script>
</body>