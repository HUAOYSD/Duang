<%@page import="java.util.Set"%>
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="UTF-8"%>
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
	        <label for="brand" class="applyLoanInfo_from_label label_member">品牌：</label>   
	       <label class="label_member">${entity.brand}</label>
	    </div>
	   
	    <div class="row0">   
	        <label for="age" class="applyLoanInfo_from_label label_member">使用年限：</label>   
	       <label class="label_member">${entity.age}</label>
	    </div>
	    <div class="row1">   
	        <label for="limit" class="applyLoanInfo_from_label label_member">公里数：</label>   
	       <label class="label_member">${entity.limit}</label>
	    </div>
	    <div class="row0">   
	        <label for="engine" class="applyLoanInfo_from_label label_member">发动机编号：</label>   
	       <label class="label_member">${entity.engine}</label>
	    </div>
	    <div class="row1">   
	       <label for="use" class="applyLoanInfo_from_label label_member">规划用途：</label>   
	       <label class="label_member">${entity.use}</label>
	    </div>
	    <div class="row0">   
	        <label for="carProperty" class="applyLoanInfo_from_label label_member">车辆性质：</label>   
	       <label class="label_member">${entity.carProperty}</label>
	    </div>
	</div>
	<script type="text/javascript">
		$(function(){
		});
	</script>
</body>