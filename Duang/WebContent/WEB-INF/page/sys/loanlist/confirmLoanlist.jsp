<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<body class="easyui-layout" id="show_match_body">
	<div style="width: 100%;align:center;text-align:center;">
		<table style="align:center;text-align:center;width:100%;" id="show_match_talbe">
			<tr class="row1" style="padding-top:2px;">
				<td style="font:bold 15px/15px 宋体;">借款</td>
				<td style="font:bold 15px/15px 宋体;">身份证号码</td>
				<td style="font:bold 15px/15px 宋体;">借款额度</td>
				<td style="font:bold 15px/15px 宋体;">借贷期限</td>
				<td style="font:bold 15px/15px 宋体;">折算额</td>
			</tr>
			<c:choose>
				<c:when test="${loanLists!=null }">
					<c:forEach items="${loanLists }" var="temp" varStatus="other">
						<tr class="row0">
							<td style="font:normal 15px/15px 宋体;">${temp.memberInfo.realName}</td>
							<td style="font:normal 15px/15px 宋体;">${temp.memberInfo.idCard}</td>
							<td style="font:normal 15px/15px 宋体;">${temp.money}</td>
							<td style="font:normal 15px/15px 宋体;">${temp.days}</td>
							<td style="font:normal 15px/15px 宋体;">${temp.days / days * temp.money}元</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr class="row0">
						<td colspan="5" style="font:normal 15px/15px 宋体;">不能匹配记录</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
	</div>
	<div class="footer-btn" id="show_match_footer">
		<div style="display: none;">
			<form action="" id="go_confirm_match">
				<input type="hidden" name="scaleid" id="scaleid" value="${scaleid }" />
				<input type="hidden" name="loanListIds" id="loanListIds" value="${loanListIds }" />
			</form>
		</div>
		<a id="prev_loanlist_scale" class="btn-ok" style="width:80px;"><b>上一步</b></a>
		<a id="confirm_loanlist_scale" class="btn-ok"><b>确认</b></a>
    </div>
	<script type="text/javascript">
		$("#prev_loanlist_scale").on("click", function(){
			parent.layer.closeAll();
		});
		
		$("#confirm_loanlist_scale").on("click", function(){
			$.messager.confirm('确认','您确认操作吗？',function(r){    
			    if (r){    
			    	$.messager.progress();
					$("#go_confirm_match").form("submit",{    
					    url:"scaleloanlist!confirmAllotLoanList.do",
					    onSubmit: function(){    
					    },   
					    success: function(data) {
					    	$.messager.progress("close");
					    	var result = eval('('+data+')');
			         		if(result.success) {
			         			//parent.layer.closeAll();
			         			layer.closeAll();
			         		} else {
			         			layer.msg("借贷分配失败",{time:1500});
			         		}
						} 
					});
			    }
			});
		});
	</script>
</body>