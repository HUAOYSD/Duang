/**
 * 初始化
 */
$(function() {
	$('#investlist_open_close').on("click",function(){
		$('#investlist_conditon').toggle(80);
		setTimeout(domresize,100);//条件隐藏，改变表格高度
	});	
	loadinvestlist("investlist!queryByPage.do");
});


/**
 * 改变表格宽高
 */
function domresize(){
	$('#investlist').datagrid('resize',{  
		height:$("#body_investlist").height()-$('#investlist_search_area').height()-8,
		width:$("#body_investlist").width()
	});
}


/**
 * 监听窗口大小变化
 */
window.onresize = function(){
	setTimeout(domresize,300);
};


/**
 * 加载列表
 * @memberOf {TypeName} 
 * @return {TypeName} 
 */
function loadinvestlist(url, dataObj){
	$('#investlist').datagrid({
		height:$("#body_investlist").height()-$('#investlist_search_area').height()-8,
		width:$("#body_investlist").width(),
		loadMsg : "正在加载，请稍后...",
		singleSelect:true, 
		nowrap:true,
		rownumbers:true,
		pagination:true,
		pageSize:50,
		pageList:[50,100,150,200,250],
		sortOrder:'desc',
		toolbar:'#tt_toolbar_investlist',
		method:'post',
		autoRowHeight : false,
		border : false,
		url :url,
		queryParams : dataObj,
		fitColumns : false,
		remoteSort : false,
		rowStyler: function(index,row){},
		frozenColumns: [[
		                 {field:'id',checkbox:true},
			             {field : "memberName", title : "借款人", width : 115, align : "center" },
			             {field : "memberNickName", title : "用户名", width : 115, align : "center" },
			             {field : "scaleName", title : "标", width : 150, align : "center" },
			             {field : "status", title : "状态", width : 100, align : "center" },
			             {field : "calcBeginDate", title : "开始计息日", width : 150, align : "center",
			            	 formatter: function(value,row,index){
			            		 if(value==0)
			            			 return "-&nbsp;&nbsp;-";
			            		 else	   
			            			 return new Date(value).format("yyyy-MM-dd");
			            	 }
			             },
			             {field : "calcEndDate", title : "结束计息日", width : 150, align : "center",
			            	 formatter: function(value,row,index){
			            		 if(value==0)
			            			 return "-&nbsp;&nbsp;-";
			            		 else	   
			            			 return new Date(value).format("yyyy-MM-dd");
			            	 }
			             }
		 ]],
		columns : [ [
		             {field : "memberPhone", title : "手机号", width : 120, align : "center" },
		             {field : "memberIdcard", title : "身份证", width : 130, align : "center" },
		             {field : "money", title : "投资总金额", width : 100, align : "center" },
		             {field : "useTicket", title : "使用理财券", width : 100, align : "center" },
		             {field : "total_money", title : "总金额", width : 100, align : "center" },
		             {field : "backIncome", title : "赎回收益", width : 100, align : "center" },
		             {field : "backMoney", title : "赎回(本金)", width : 100, align : "center" },
		             {field : "income", title : "实际收益", width : 100, align : "center" },
		             {field : "ticketBonus", title : "理财券奖励", width : 100, align : "center" },
		             {field : "isTurn", title : "是否已转让", width : 100, align : "center" },
		             {field : "turnStatus", title : "转让状态", width : 100, align : "center" },
		             {field : "poundageTurn", title : "转让手续费", width : 100, align : "center" },
		             {field : "poundagePrivilege", title : "优惠手续费", width : 100, align : "center" },
		             {field : "investStyle", title : "平台", width : 100, align : "center" },
		             {field : "days", title : "周期", width : 150, align : "center",
		            	 formatter: function(value,row,index){
		            		 return value+"天";
		            	 }
		             },
		             {field : "pactNumber", title : "合同编号", width : 150, align : "center" },
		             {field : "openDate", title : "创建日期", width : 150, align : "center",
		            	 formatter: function(value,row,index){
		            		 if(value==0)
		            			 return "-&nbsp;&nbsp;-";
		            		 else	   
		            			 return new Date(value).format("yyyy-MM-dd hh:mm:ss");
		            	 }
		             }, 
		             {field : "backDate", title : "实际回款日期", width : 150, align : "center",
		            	 formatter: function(value,row,index){
		            		 if(value==0)
		            			 return "-&nbsp;&nbsp;-";
		            		 else	   
		            			 return new Date(value).format("yyyy-MM-dd");
		            	 }
		             }
		             ] ]
	});
}


/**
 * 查询
 */
$("#investlistQueryForm_Btn").on("click", function(){
	var dataStr = $('#investlistQueryForm').serialize();
	var urlStr = "{";
	$.each(dataStr.split("&"), function(i,n){
		var args = n.split("="); 
		if(args.length == 2){												
			if(args[1].isNotNull()){
				urlStr += "'"+args[0]+"':'"+args[1]+"',";
			}
		}
	});
	urlStr += "'submitTime':'"+(new Date().getTime())+"'}";
	var data = eval('('+urlStr+')');
	loadinvestlist("investlist!queryByPage.do", data);
});


/**
 * 刷新数据
 */
function reloadDataGrid(){
	loadinvestlist("investlist!queryByPage.do");
}
