<%@page import="java.util.Set"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String path = request.getContextPath();
%>
<%@ include file="/page/inc/inc.jsp"%>
<body style="background-color:#fff;">
	<div>
		     <div class="row0">   
		       <label for="name" class="applyLoanInfo_from_label label_member">姓名：</label>   
		       <label class="label_member">${entity.name}</label>
		    </div>
		    <div class="row1">   
		        <label for="sex" class="applyLoanInfo_from_label label_member">性别：</label>   
		       	<label class="label_member">${entity.sex}</label>
		    </div>
		     <div class="row0">   
		        <label for="phone" class="applyLoanInfo_from_label label_member">联系电话：</label>   
		       	<label class="label_member">${entity.phone}</label>
		    </div>
		    <div class="row1">   
		        <label for="email" class="applyLoanInfo_from_label label_member">邮件：</label>   
		       <label class="label_member">${entity.email}</label>
		    </div>
		    <div class="row0">   
		        <label class="applyLoanInfo_from_label label_member">贷款金额：</label>   
		     	<label class="label_member">${entity.money}</label>
		    </div>   
		    <div class="row1">   
		        <label class="applyLoanInfo_from_label label_member">贷款期限(单位月)：</label>   
		        <label class="label_member">${entity.timeLimit}</label>  
		    </div>
		     <div class="row0">   
		       <label for="use" class="applyLoanInfo_from_label label_member">用途：</label>   
		       <label class="label_member">${entity.use}</label>
		    </div>
		     <div class="row1">   
		       <label for="monthBack" class="applyLoanInfo_from_label label_member">每月可归还额度：</label>   
		       <label class="label_member">${entity.monthBack}</label>
		    </div>
		     <div class="row0">   
		       <label for="urgencyPerson" class="applyLoanInfo_from_label label_member">紧急联系人：</label>   
		       <label class="label_member">${entity.urgencyPerson}</label>
		    </div>
		     <div class="row1">   
		       <label for="urgencyPhone" class="applyLoanInfo_from_label label_member">紧急联系人电话：</label>   
		       <label class="label_member">${entity.urgencyPhone}</label>
		    </div>
		    <div class="row0">   
		       <label for="education" class="applyLoanInfo_from_label label_member">学历：</label>   
		       <label class="label_member">
		       		<c:if test="${entity.education eq 0}">专科</c:if>
		       		<c:if test="${entity.education eq 1}">本科</c:if>
		       		<c:if test="${entity.education eq 2}">硕士研究生</c:if>
		       		<c:if test="${entity.education eq 3}">博士研究生</c:if>
		       </label>
		    </div>
		    <div class="row1">   
		       <label for="marriage" class="applyLoanInfo_from_label label_member">婚姻状况：</label>   
		       <label class="label_member">
		       		<c:if test="${entity.marriage eq 0}">未婚</c:if>
		       		<c:if test="${entity.marriage eq 1}">已婚</c:if>
		       </label>
		    </div>
		    <div class="row0">   
		        <label for="idcard" class="applyLoanInfo_from_label label_member">身份证号码：</label>   
		       <label class="label_member">${entity.idcard}</label>
		    </div>
		    
		    <div class="row1">   
		        <label for="nativeAddress" class="applyLoanInfo_from_label label_member">户籍所在地：</label>   
		       <label class="label_member">${entity.nativeAddress}</label>
		    </div>
		    <div class="row0">   
		        <label for="nativeInfo" class="applyLoanInfo_from_label label_member">户籍详信息：</label>   
		       <label class="label_member">${entity.nativeInfo}</label>
		    </div>
		    <div class="row1">   
		        <label for="address" class="applyLoanInfo_from_label label_member">现在居住地：</label>   
		       <label class="label_member">${entity.address}</label>
		    </div>
		    <div class="row0">   
		       <label for="liveStyle" class="applyLoanInfo_from_label label_member">住房方式：</label>   
		       <label class="label_member">
		       		<c:if test="${entity.liveStyle eq 0}">租房</c:if>
		       		<c:if test="${entity.liveStyle eq 1}">自居房</c:if>
		       </label>
		    </div>
		    
		    <div class="row1">   
		        <label for="house" class="applyLoanInfo_from_label label_member">房子状况：</label>   
		       <label class="label_member">${entity.house}</label>
		    </div>
		    <div class="row0">   
		       <label for="hasCredit" class="applyLoanInfo_from_label label_member">有无信用卡：</label>   
		       <label class="label_member">
		       		<c:if test="${entity.hasCredit eq 0}">没有</c:if>
		       		<c:if test="${entity.hasCredit eq 1}">有</c:if>
		       </label>
		    </div>
		    <div class="row1">   
		        <label for="creditCard" class="applyLoanInfo_from_label label_member">信用卡号码：</label>   
		       <label class="label_member">${entity.creditCard}</label>
		    </div>
		    <div class="row0">   
		       <label for="industry" class="applyLoanInfo_from_label label_member">工作行业：</label>   
		       <label class="label_member">${entity.industry}</label>
		    </div>
		    <div class="row1">   
		       <label for="jobStyle" class="applyLoanInfo_from_label label_member">职业性质：</label>   
		       <label class="label_member">
		       		<c:if test="${entity.jobStyle eq 0}">兼职</c:if>
		       		<c:if test="${entity.jobStyle eq 1}">全职</c:if>
		       </label>
		    </div>
		     <div class="row0">   
		       <label for="job" class="applyLoanInfo_from_label label_member">担任职务：</label>   
		       <label class="label_member">${entity.job}</label>
		    </div>
		     <div class="row1">   
		       <label for="jobCity" class="applyLoanInfo_from_label label_member">工作城市：</label>   
		       <label class="label_member">${entity.jobCity}</label>
		    </div>
		    <div class="row0">   
		       <label for="company" class="applyLoanInfo_from_label label_member">工作公司：</label>   
		       <label class="label_member">${entity.company}</label>
		    </div>
		     <div class="row1">   
		       <label for="publicTel" class="applyLoanInfo_from_label label_member">公司电话：</label>   
		       <label class="label_member">${entity.publicTel}</label>
		    </div>
		     <div class="row0">   
		       <label for="salaryFromBank" class="applyLoanInfo_from_label label_member">工资是否属于银行代发：</label>   
		       <label class="label_member">
		       		<c:if test="${entity.jobStyle eq 0}">否</c:if>
		       		<c:if test="${entity.jobStyle eq 1}">是</c:if>
		       </label>
		    </div>
		    <div class="row1">   
		       <label for="yearIncome" class="applyLoanInfo_from_label label_member">年收入：</label>   
		       <label class="label_member">${entity.yearIncome}</label>
		    </div>
	</div>
	<script type="text/javascript">
		$(function(){
		});
	</script>
</body>