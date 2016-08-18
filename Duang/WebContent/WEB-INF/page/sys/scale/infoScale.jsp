<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/page/inc/inc.jsp"%>
<% String path = request.getContextPath();%>
<body style="background-color:#fff;">
	<div class="operate_div_form">
	  	<form id="scale_info_form" method="post" style="margin-bottom: 45px;"> 
	  		<div class="row0">   
		        <label for="name" class="infolabel from_label label_member">名称：</label>  
		        <label class="label_member">${info.name}</label>
		    </div> 
	  		<div class="row1">   
		        <label for="status" class="infolabel from_label label_member">名称：</label>  
		        <label class="label_member">${info.status}</label>
		    </div> 
		    <div class="row0">   
		        <label for="product.id" class="infolabel from_label label_member">产品：</label>  
		        <label class="label_member">${info.productName}</label>
		    </div> 
		    <div class="row1">   
		        <label for="beginTime" class="infolabel from_label label_member">开标时间：</label>  
		        <label class="label_member">${info.beginTime}</label>
		    </div> 
		    <div class="row0">   
		        <label for="endTime" class="infolabel from_label label_member">截止时间：</label>  
		        <label class="label_member">${info.endTime}</label>
		    </div> 
		    <div class="row1">   
		        <label for="timeLimit" class="infolabel from_label label_member">时长简介：</label>  
		        <label class="label_member">${info.timeLimit}</label>
		    </div> 
		    <div class="row0">   
		        <label for="calcBeginTime" class="infolabel from_label label_member">开始计息日：</label>  
		        <label class="label_member">${info.calcBeginTime}</label>
		    </div> 
		    <div class="row1">   
		        <label for="calcEndTime" class="infolabel from_label label_member">结束计息日：</label> 
		        <label class="label_member">${info.calcEndTime}</label> 
		    </div> 
		    <div class="row0">   
		        <label for="revenue" class="infolabel from_label label_member">收益率：</label>  
		        <label class="label_member">${info.revenue}</label>
		    </div> 
		    <div class="row1">   
		        <label for="revenueAdd" class="infolabel from_label label_member">附收益率：</label>  
		        <label class="label_member">${info.revenueAdd}</label>
		    </div>
		    <div class="row0">   
		        <label for="maxLimit" class="infolabel from_label label_member">单笔限额：</label>  
		        <label class="label_member">${info.maxLimit}</label>
		    </div> 
		    <div class="row1">   
		        <label for="returnStyle" class="infolabel from_label label_member">还款方式：</label>  
		        <label class="label_member">${info.returnStyle}</label>
		    </div> 
		    <div class="row0">   
		        <label for="useTicket" class="infolabel from_label label_member">可使用理财券：</label>  
		        <label class="label_member">${info.useTicket}</label>
		    </div> 
		    <div class="row1">   
		        <label for="transfer" class="infolabel from_label label_member">可转让：</label>  
		        <label class="label_member">${info.transfer}</label>
		    </div> 
		    <div class="row0">   
		        <label for="turnDate" class="infolabel from_label label_member">截止转让日期：</label>  
		        <label class="label_member">${info.turnDate}</label>
		    </div>
		    <div class="row1">   
		        <label for="totalMoney" class="infolabel from_label label_member">标总额：</label>  
		        <label class="label_member">${info.totalMoney}</label>
		    </div> 
		    <div class="row0">   
		        <label for="residueMoney" class="infolabel from_label label_member">可投金额：</label>  
		        <label class="label_member">${info.residueMoney}</label>
		    </div> 
		    <div class="row1">   
		        <label for="yetMoney" class="infolabel from_label label_member">已投金额：</label>  
		        <label class="label_member">${info.yetMoney}</label>
		    </div> 
		    <div class="row0">   
		        <label for="scoreBonus" class="infolabel from_label label_member">积分奖励：</label>  
		        <label class="label_member">${info.scoreBonus}</label>
		    </div> 
		    <div class="row1">   
		        <label for="onesScore" class="infolabel from_label label_member">单位积分：</label>  
		        <label class="label_member">${info.onesScore}</label>
		    </div> 
		    <div class="row0">   
		        <label for="isTurn" class="infolabel from_label label_member">转让标：</label>  
		        <label class="label_member">${info.isTurn}</label>
		    </div> 
		    <div class="row1">   
		        <label for="tags" class="infolabel from_label label_member">标签：</label>  
		        <label class="label_member">${info.tags}</label> 
		    </div>
		</form>  
	</div>
	<script type="text/javascript">
	</script>
</body>