<%@page import="java.util.Set"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String path = request.getContextPath();
%>
<%@ include file="/page/inc/inc.jsp"%>
<body style="background-color: #fff;">
	<div align="center">
		<textarea class="form-textarea" id="applyContent" style="width:98%;height:80%;resize: none;">${entity.applyContent}</textarea>
	</div>


	<div id="footer"></div>
	<div id="footerbox">
		<div class="footer">
			<div class="footer-btn">
				<button onclick="review(2)" type="button" class="btn btn-primary">通过</button>
				<button onclick="review(3)" class="btn btn-warning">驳回</button>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var id="${entity.id}";
		function review(t){
			var applyContent = $("#applyContent").val();
			$.messager.progress();
	    	$.ajax({
	         	type:"post",
	         	url:"loanlist!updateLoanApplyState.do?",
	         	data:{"id":id, "applyState":t,"applyContent":applyContent},
	         	success:function(data) {
	         		data = eval('('+data+')');
	         		if(data.result) {
	         			window.parent.reloadDataGrid();
	         			parent.layer.closeAll();
	         		} else {
	         			layer.msg("修改审核结果失败，"+data.msg, {time:1500});
	         		}
	         	}
	        }); 
	    	$.messager.progress("close");
		}
	</script>
</body>