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
	        <label for="phone" class="applyLoanInfo_from_label label_member">联系电话：</label>   
	       	<label class="label_member">${entity.phone}</label>
	    </div>
	    
	     <div class="row0">   
	        <label for="idcard" class="applyLoanInfo_from_label label_member">身份证：</label>   
	       <label class="label_member">${entity.idcard}</label>
	    </div>
	    
	    <div class="row1">   
	       <label for="province" class="applyLoanInfo_from_label label_member">省份：</label>   
	       <label class="label_member">${entity.province}</label>
	    </div>
	    <div class="row0">   
	       <label for="city" class="applyLoanInfo_from_label label_member">城市：</label>   
	       <label class="label_member">${entity.city}</label>
	    </div>
	    
	    <div class="row1">   
	        <label for="houseNumber" class="applyLoanInfo_from_label label_member">房产编号：</label>   
	       <label class="label_member">${entity.houseNumber}</label>
	    </div>
	   
	    <div class="row0">   
	        <label for="money" class="applyLoanInfo_from_label label_member">估算结果：</label>   
	       <label class="label_member">${entity.money}</label>
	    </div>
	</div>
	<script type="text/javascript">
		$(function(){
		});
	</script>
</body>