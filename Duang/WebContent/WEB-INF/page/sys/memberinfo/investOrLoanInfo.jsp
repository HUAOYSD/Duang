<%@page import="org.duang.entity.MemberInfo"%>
<%@page import="org.duang.entity.InvestMember"%>
<%@page import="java.util.Set"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String path = request.getContextPath();
%>
<%@ include file="/page/inc/inc.jsp"%>
<body style="background-color:#fff;">
	<div>
		    <div class="row0">   
		        <label class="from_label label_member">登录名：</label>   
		     	<label class="label_member">${entity.memberInfo.loginName}</label>
		    </div>   
		    <div class="row1">   
		        <label class="from_label label_member">真实姓名：</label>   
		        <label class="label_member">${entity.memberInfo.realName}</label>  
		    </div>
		    <c:if test="${selectInvest eq 1}">
			    <div id="investInfo">
				    <div class="row0">   
				        <label for="balance" class="from_label label_member">余额：</label>   
				       	<label class="label_member">${entity.balance}</label>
				    </div>
				     <div class="row1">   
				       <label for="investing" class="from_label label_member">投资中金额：</label>   
				       <label class="label_member">${entity.investing}</label>
				    </div>
				     <div class="row0">   
				        <label for="totalIncome" class="from_label label_member">总收益：</label>   
				       	<label class="label_member">${entity.totalIncome}</label>
				    </div>
				    <div class="row1">   
				        <label for="totalMoney" class="from_label label_member">总资产：</label>   
				       <label class="label_member">${entity.totalMoney}</label>
				    </div>
				    <div class="row0">   
				        <label for="useableScore" class="from_label label_member">可用积分：</label>   
				       <label class="label_member">${entity.useableScore}</label>
				    </div>
				    <div class="row1">   
				       <label for="registerStyle" class="from_label label_member">注册方式：</label>   
				       <label class="label_member">
				       		<c:if test="${entity.registerStyle eq 1}">线下</c:if>
				       		<c:if test="${entity.registerStyle eq 2}">Android</c:if>
				       		<c:if test="${entity.registerStyle eq 3}">IOS</c:if>
				       		<c:if test="${entity.registerStyle eq 4}">平台系统</c:if>
				       </label>
				    </div>
				    <div class="row0">   
				        <label for="isContract" class="from_label label_member">契约用户：</label>   
				       <label class="label_member">
				       		<c:if test="${entity.isContract eq 1}">是</c:if>
				       		<c:if test="${entity.isContract eq 0}">否</c:if>
				       </label>
				    </div>
			    </div>
		    </c:if>
		    <c:if test="${selectLoan eq 0}">
			     <div id="loanInfo">
				    <div class="row0">   
				        <label  class="from_label label_member">总借款：</label>   
				       	<label class="label_member">${entity.lendMoney}</label>
				    </div>
				     <div class="row1">   
				        <label class="from_label label_member">总还款：</label>   
				       <label class="label_member">${entity.backMoney}</label>
				    </div>
				     <div class="row0">   
				        <label class="from_label label_member">剩余应还：</label>   
				       	<label class="label_member">${entity.residueMoney}</label>
				    </div>
				    <div class="row1">   
				        <label class="from_label label_member">总逾期：</label>   
				       <label class="label_member">${entity.expectMoney}</label>
				    </div>
			    </div>
		   </c:if>
	</div>
	<script type="text/javascript">
		$(function(){
		});
	</script>
</body>