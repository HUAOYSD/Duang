<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<script type="text/javascript">
	 	var loanlistId = "${loanListId}";
	 	var tableObj;
	 	$(function(){
	 		//表格初始化
	 		tableObj = $("#loanMemberRepayInfo_table").datagrid({
	 			height:$("#body_loanMemberRepayInfo_table").height()-5,
	 			width:$("#body_loanMemberRepayInfo_table").width(),
	 			url:"repay_date!queryLoanMemberRepayByLoanListId.do?loanListId="+loanlistId,  
	 			loadMsg : "正在加载，请稍后...",
	 			singleSelect:true, 
	 			nowrap:true,
	 			rownumbers:true,
	 			pagination:true,
	 			pageSize:50,
	 			pageList:[50,100,150,200,250],
	 			sortOrder:'desc',
	 			toolbar:'',
	 			method:'post',
	 			autoRowHeight : false,
	 			border : false,
	 			pagination:true,
	 			fitColumns:true,
	 			columns:[[
	 				{field:'repaySum',title:'应该还款金额',width:100,halign:"center", align:"center" },
	 				{field:'repayRealSum',title:'实际还款金额',width:100,halign:"center", align:"center",
	 					formatter:function(value,row,index){
	 						return value+"元";
	 					}
	 				},
	 				{field:'repayDate',title:'应该还款日期',width:100,halign:"center", align:"center",
	 					formatter: function(value,row,index){
	 						   if(value==0){
	 							   return "-&nbsp;&nbsp;-";
	 						   }else{ 
	 							   return new Date(value).format("yyyy-MM-dd");
	 						   }
	 						}
	 				},
	 				{field:'reRepayDate',title:'实际还款日期',width:100,halign:"center", align:"center",
	 					formatter: function(value,row,index){
	 						   if(value==0){
	 							   return "-&nbsp;&nbsp;-";
	 						   }else{ 
	 							   return new Date(value).format("yyyy-MM-dd");
	 						   }
	 					}
	 				},
	 				{field:'reapyIndex',title:'期数',width:100,halign:"center", align:"center",
	 					formatter:function(value,row,index){
	 						return value+"期";
	 					}
	 				},
	 				{field:'state',title:'状态',width:100,halign:"center", align:"center",
	 					formatter:function(value,row,index){
	 						if(value==0){
	 							return "未还款";
	 						}else{
	 							return "已还款";
	 						}
	 					},
	 					styler: function(value,row,index){
	 						if(value==0){
	 							return 'background-color:#fe441a;color:#fff;';
	 						}else{
	 							return 'background-color:#028252;color:#fff;';
	 						}
	 					}

	 				},
	 				{field:'isOver',title:'是否逾期',width:100,halign:"center", align:"center",
	 					formatter:function(value,row,index){
	 						if(value==0){
	 							return "否";
	 						}else{
	 							return "是";
	 						}
	 					}
	 				},
	 				{field:'overDays',title:'超期天数',width:100,halign:"center", align:"center",
	 					formatter:function(value,row,index){
	 						return value+"天";
	 					}
	 				},
	 				{field:'overSum',title:'逾期费',width:100,halign:"center", align:"center",
	 					formatter: function(value,row,index){
	 						return value+"元";
	 					}
	 				}
	 			]]

	 		});
	 	});
</script>
<body class="easyui-layout">
	<div class="body" id="body_loanMemberRepayInfo_table" region="center" style="border:0px;" >	
		  <!-- 数据表格区域 -->
		  <table id="loanMemberRepayInfo_table" style="table-layout:fixed;" ></table>
	</div>
</body>