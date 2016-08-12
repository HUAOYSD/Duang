/**
 * 初始化
 */
$(function() {
	$('#allot_btn_loanlist').linkbutton('disable');
	$('#loanlist_open_close').on("click",function(){
		$('#loanlist_conditon').toggle(80);
		setTimeout(domresize,100);//条件隐藏，改变表格高度
	});	
	loadloanlist("loanlist!queryByPage.do");
});


/**
 * 改变表格宽高
 */
function domresize(){
	$('#loanlist').datagrid('resize',{  
		height:$("#body_loanlist").height()-$('#loanlist_search_area').height()-8,
		width:$("#body_loanlist").width()
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
function loadloanlist(url, dataObj){
	$('#loanlist').datagrid({
		height:$("#body_loanlist").height()-$('#loanlist_search_area').height()-8,
		width:$("#body_loanlist").width(),
		loadMsg : "正在加载，请稍后...",
		singleSelect:true, 
		nowrap:true,
		rownumbers:true,
		pagination:true,
		pageSize:50,
		pageList:[50,100,150,200,250],
		sortOrder:'desc',
		toolbar:'#tt_toolbar_loanlist',
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
			             {field : "loanMemberName", title : "借款人", width : 115, align : "center" },
			             {field : "loanMemberNickName", title : "用户名", width : 115, align : "center" },
			             {field : "loanType", title : "借款模式", width : 100, align : "center" },
			             {field : "customerManagerName", title : "客户经理", width : 115, align : "center" },
		 ]],
		columns : [ [
		             {field : "loanMemberPhone", title : "手机号", width : 120, align : "center" },
		             {field : "loanMemberIdcard", title : "身份证", width : 130, align : "center" },
		             {field : "backStyle", title : "还款方式", width : 110, align : "center" },
		             {field : "applyState", title : "申请状态", width : 100, align : "center" },
		             {field : "loanState", title : "放款状态", width : 100, align : "center" },
		             {field : "money", title : "申请借款金额", width : 100, align : "center" },
		             {field : "realMoney", title : "审核金额", width : 100, align : "center" },
		             {field : "manageCost", title : "管理费", width : 100, align : "center" },
		             {field : "loanInterest", title : "借款利息", width : 100, align : "center" },
		             {field : "poundage", title : "手续费", width : 100, align : "center" },
		             {field : "poundageState", title : "手续费状态", width : 100, align : "center" },
		             {field : "getMoney", title : "应该放款金额", width : 100, align : "center" },
		             {field : "yetMoney", title : "已放款金额", width : 100, align : "center" },
		             {field : "isSell", title : "起售状态", width : 100, align : "center" },
		             {field : "returnStatus", title : "还款状态", width : 100, align : "center" },
		             {field : "returnMoney", title : "应还款金额", width : 100, align : "center" },
		             {field : "agoMoney", title : "逾期费", width : 100, align : "center" },
		             {field : "yetReturnMoney", title : "已还款金额", width : 100, align : "center" },
		             {field : "loanStyle", title : "平台", width : 100, align : "center" },
		             {field : "pactNumber", title : "合同编号", width : 150, align : "center" },
		             {field : "signDate", title : "签约日", width : 150, align : "center",
		            	 formatter: function(value,row,index){
		            		 if(value==0)
		            			 return "-&nbsp;&nbsp;-";
		            		 else	   
		            			 return new Date(value).format("yyyy-MM-dd hh:mm:ss");
		            	 }
		             }, 
		             {field : "beginReturnDate", title : "开始还款日", width : 150, align : "center",
		            	 formatter: function(value,row,index){
		            		 if(value==0)
		            			 return "-&nbsp;&nbsp;-";
		            		 else	   
		            			 return new Date(value).format("yyyy-MM-dd hh:mm:ss");
		            	 }
		             }, 
		             {field : "endReturnDate", title : "截止还款日", width : 150, align : "center",
		            	 formatter: function(value,row,index){
		            		 if(value==0)
		            			 return "-&nbsp;&nbsp;-";
		            		 else	   
		            			 return new Date(value).format("yyyy-MM-dd hh:mm:ss");
		            	 }
		             }, 
		             {field : "doneReturnDate", title : "完成还款日", width : 150, align : "center",
		            	 formatter: function(value,row,index){
		            		 if(value==0)
		            			 return "-&nbsp;&nbsp;-";
		            		 else	   
		            			 return new Date(value).format("yyyy-MM-dd hh:mm:ss");
		            	 }
		             },
		             {field : "loanUse", title : "借款用途", width : 200, align : "center" }
		             ] ],
		             onSelect:function(rowIndex, rowData){
		            	 $('#allot_btn_loanlist').linkbutton('enable');
		             },
		             onUnselect:function(rowIndex, rowData){
		            	 $('#allot_btn_loanlist').linkbutton('disable');
		             },
		             onLoadSuccess: function(data){
		            	 $('#allot_btn_loanlist').linkbutton('disable');
		             }
	});
}


/**
 * 查询
 */
$("#loanlistQueryForm_Btn").on("click", function(){
	var dataStr = $('#loanlistQueryForm').serialize();
	var urlStr = "{";
	$.each(dataStr.split("&"), function(i,n){
		var args = n.split("="); 
		if(args.length == 2){												
			if(args[1].isNotNull() && args[1]!="0"){
				urlStr += "'"+args[0]+"':'"+args[1]+"',";
			}
		}
	});
	urlStr += "'submitTime':'"+(new Date().getTime())+"'}";
	var data = eval('('+urlStr+')');
	loadloanlist("loanlist!queryByPage.do", data);
});


/**
 * 刷新数据
 */
function reloadDataGrid(){
	loadloanlist("loanlist!queryByPage.do");
}

/**
 * id
 */
var recordid = "";


/**
 * 获取记录id
 * @returns {String}
 */
function getRecordId(){
	return recordid;
}


/**
 * 打开分配客户经理页面
 * @param {Object} memberId
 */
$("#allot_btn_loanlist").on("click",function(){
	var selectedRow = $("#loanlist").datagrid('getSelected');
	if(selectedRow==null){
		layer.msg("请选择一条记录",{time:1500});
		return;
	}else{
		recordid = selectedRow.id;
	}
	layer.open({
		type: 2,
		title: '分配客户经理',
		shadeClose: true,
		maxmin:true,
		shade: 0.8,
		area: ['80%', '80%'],
		content: 'customermanager!openDialog.do?path=chose'
	}); 
});


