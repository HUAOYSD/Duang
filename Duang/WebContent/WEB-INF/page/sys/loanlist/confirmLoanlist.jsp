<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<body class="easyui-layout" id="show_match_body" style="background-color:#fff">
	<div style="width: 100%;align:center;text-align:center;">
		<table class="table table-striped table-hover table-bordered" id="show_match_talbe">
			<thead>
				<tr>
					<th>借款</th>
					<th>身份证号码</th>
					<th>借款额度</th>
					<th>借贷期限</th>
					<th>折算额</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${loanLists!=null }">
						<c:forEach items="${loanLists }" var="temp" varStatus="other" >
							<tr>
								<td>${temp.memberInfo.realName}</td>
								<td>${temp.memberInfo.idCard}</td>
								<td>${temp.money}</td>
								<td>${temp.days}</td>
								<td>${temp.days / days * temp.money}元</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="5" style="font:normal 15px/15px 宋体;">不能匹配记录</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
	<div class="footer-btn" id="show_match_footer">
		<div style="display: none;">
			<form action="" id="go_confirm_match">
				<input type="hidden" name="scaleid" id="scaleid" value="${scaleid }" />
				<input type="hidden" name="loanListIds" id="loanListIds" value="${loanListIds }" />
			</form>
		</div>
		<button type="button" class="btn btn-default" id="prev_loanlist_scale">上一步</button>
		<button type="button" class="btn btn-primary" id="confirm_loanlist_scale">确认</button>
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
			         			parent.parent.layer.closeAll();
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