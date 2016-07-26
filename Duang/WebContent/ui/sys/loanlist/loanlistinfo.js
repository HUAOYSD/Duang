/**
 * 初始化
 */
$(function() {
	loadloanlist("scaleloanlist!findScaleLoanListInfo.do");
});


/**
 * 改变表格宽高
 */
function domresize(){
	$('#loanlistinfo').datagrid('resize',{  
		height:$("#body_loanlistinfo").height()-5,
		width:$("#body_loanlistinfo").width()
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
	$('#loanlistinfo').datagrid({
		height:$("#body_loanlistinfo").height()-5,
		width:$("#body_loanlistinfo").width(),
		loadMsg : "正在加载，请稍后...",
		singleSelect:true, 
		nowrap:true,
		rownumbers:true,
		//pagination:true,
		//pageSize:50,
		//pageList:[50,100,150,200,250],
		//sortOrder:'desc',
		//toolbar:'#tt_toolbar_loanlist',
		method:'post',
		autoRowHeight : false,
		border : false,
		url :url,
		queryParams : dataObj,
		fitColumns : false,
		remoteSort : false,
		rowStyler: function(index,row){},
		frozenColumns: [[
			             {field : "loanMemberName", title : "借款人", width : 115, align : "center" },
			             {field : "loanType", title : "借款模式", width : 100, align : "center" },
			             {field : "createTime", title : "创建时间", width : 150, align : "center",
			            	 formatter: function(value,row,index){
			            		 if(value==0)
			            			 return "-&nbsp;&nbsp;-";
			            		 else	   
			            			 return new Date(value).format("yyyy-MM-dd hh:mm:ss");
			            	 }
			             }
		 ]],
		columns : [ [
		             {field : "loanMemberPhone", title : "手机号", width : 120, align : "center" },
		             {field : "loanMemberIdcard", title : "身份证", width : 130, align : "center" },
		             {field : "isSell", title : "起售状态", width : 100, align : "center" },
		             {field : "passTime", title : "审核时间", width : 150, align : "center",
		            	 formatter: function(value,row,index){
		            		 if(value==0)
		            			 return "-&nbsp;&nbsp;-";
		            		 else	   
		            			 return new Date(value).format("yyyy-MM-dd hh:mm:ss");
		            	 }
		             },
		             {field : "backStyle", title : "还款方式", width : 110, align : "center" },
		             {field : "money", title : "申请借款金额", width : 100, align : "center" },
		             {field : "realMoney", title : "审核金额", width : 100, align : "center" },
		             {field : "manageCost", title : "管理费", width : 100, align : "center" },
		             {field : "poundage", title : "手续费", width : 100, align : "center" },
		             {field : "getMoney", title : "应该放款金额", width : 100, align : "center" },
		             {field : "loanStyle", title : "平台", width : 100, align : "center" }
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


