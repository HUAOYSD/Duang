<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/page/inc/inc.jsp"%>
<% String path = request.getContextPath();%>
<body style="background-color:#fff;">
	<div class="operate_div_form">
	  	<form id="scale_info_form" method="post" style="margin-bottom: 45px;"> 
	  		<div class="row0">   
		        <label for="name" class="infolabel from_label label_member">名称：</label>  
		        <label class="label_member">${entity.name}</label>
		    </div> 
	  		<div class="row0">   
		        <label for="status" class="infolabel from_label label_member">名称：</label>  
		        <label class="label_member">${entity.status}</label>
		    </div> 
		    <div class="row1">   
		        <label for="product.id" class="infolabel from_label label_member">产品：</label>  
		        <label class="label_member">${entity.product.name}</label>
		    </div> 
		    <div class="row0">   
		        <label for="beginTime" class="infolabel from_label label_member">开标时间：</label>  
		        <label class="label_member">${entity.beginTime}</label>
		    </div> 
		    <div class="row1">   
		        <label for="endTime" class="infolabel from_label label_member">截止时间：</label>  
		        <label class="label_member">${entity.endTime}</label>
		    </div> 
		    <div>   
		        <label for="timeLimit" class="infolabel from_label label_member">时长简介：</label>  
		        <label class="label_member">${entity.timeLimit}</label>
		    </div> 
		    <div>   
		        <label for="calcBeginTime" class="infolabel from_label label_member">开始计息日：</label>  
		        <label class="label_member">${entity.calcBeginTime}</label>
		    </div> 
		    <div>   
		        <label for="calcEndTime" class="infolabel from_label label_member">结束计息日：</label> 
		        <label class="label_member">${entity.calcEndTime}</label> 
		    </div> 
		    <div>   
		        <label for="revenue" class="infolabel from_label label_member">收益率：</label>  
		        <label class="label_member">${entity.revenue}</label>
		    </div> 
		    <div>   
		        <label for="revenueAdd" class="infolabel from_label label_member">附收益率：</label>  
		        <label class="label_member">${entity.revenueAdd}</label>
		    </div>
		    <div>   
		        <label for="maxLimit" class="infolabel from_label label_member">单笔限额：</label>  
		        <label class="label_member">${entity.maxLimit}</label>
		    </div> 
		    <div>   
		        <label for="returnStyle" class="infolabel from_label label_member">还款方式：</label>  
		        <label class="label_member">${entity.returnStyle}</label>
		    </div> 
		    <div>   
		        <label for="useTicket" class="infolabel from_label label_member">可使用理财券：</label>  
		        <label class="label_member">${entity.useTicket}</label>
		    </div> 
		    <div>   
		        <label for="transfer" class="infolabel from_label label_member">可转让：</label>  
		        <label class="label_member">${entity.transfer}</label>
		    </div> 
		    <div>   
		        <label for="turnDate" class="infolabel from_label label_member">截止转让日期：</label>  
		        <label class="label_member">${entity.turnDate}</label>
		    </div>
		    <div>   
		        <label for="totalMoney" class="infolabel from_label label_member">标总额：</label>  
		        <label class="label_member">${entity.totalMoney}</label>
		    </div> 
		    <div>   
		        <label for="residueMoney" class="infolabel from_label label_member">可投金额：</label>  
		        <label class="label_member">${entity.residueMoney}</label>
		    </div> 
		    <div>   
		        <label for="yetMoney" class="infolabel from_label label_member">已投金额：</label>  
		        <label class="label_member">${entity.yetMoney}</label>
		    </div> 
		    <div>   
		        <label for="scoreBonus" class="infolabel from_label label_member">积分奖励：</label>  
		        <label class="label_member">${entity.scoreBonus}</label>
		    </div> 
		    <div>   
		        <label for="onesScore" class="infolabel from_label label_member">单位积分：</label>  
		        <label class="label_member">${entity.onesScore}</label>
		    </div> 
		    <div>   
		        <label for="isTurn" class="infolabel from_label label_member">转让标：</label>  
		        <label class="label_member">${entity.isTurn}</label>
		    </div> 
		    <div>   
		        <label for="tags" class="infolabel from_label label_member">标签：</label>  
		        <label class="label_member">${entity.tags}</label> 
		    </div>
		</form>  
	</div>
	<script type="text/javascript">
	</script>
</body>