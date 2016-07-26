/**
 * 初始化
 */
$(function() {
	$('#stock_open_close').on("click",function(){
		$('#stock_conditon').toggle(80);
		setTimeout(domresize,100);//条件隐藏，改变表格高度
	});	
	loadstock("stock!queryByPage.do");
});


/**
 * 改变表格宽高
 */
function domresize(){
	$('#stock').datagrid('resize',{  
		height:$("#body_stock").height()-$('#stock_search_area').height()-8,
		width:$("#body_stock").width()
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
function loadstock(url, dataObj){
	$('#stock').datagrid({
			height:$("#body_stock").height()-$('#stock_search_area').height()-8,
			width:$("#body_stock").width(),
			loadMsg : "正在加载，请稍后...",
			singleSelect:true, 
			nowrap:true,
			rownumbers:true,
			pagination:true,
			pageSize:50,
			pageList:[50,100,150,200,250],
			sortOrder:'desc',
			toolbar:'#tt_toolbar_stock',
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
				             {field : "loanPersonName", title : "借款人姓名", width : 115, align : "center" },
			                 {field : "loanPersonPhone", title : "借款人手机号", width : 115, align : "center" },
			                 {field : "scaleName", title : "标名称", width : 115, align : "center" },
			                 {field : "investPersonName", title : "出借人姓名", width : 115, align : "center" },
			                 {field : "investPersonPhone", title : "出借人手机号", width : 115, align : "center" }
			 ]],
			columns : [ [
			             {field : "money", title : "存入金额", width : 120, align : "center" },
			             {field : "fetch", title : "转出金额", width : 120, align : "center" },
			             {field : "difference", title : "库存差", width : 120, align : "center" },
			             {field : "status", title : "状态", width : 120, align : "center" },
			             {field : "isTurn", title : "转让属性", width : 120, align : "center" },
			             {field : "createTime", title : "存入时间", width : 150, align : "center",
			            	 formatter: function(value,row,index){
			            		 if(value==0)
			            			 return "-&nbsp;&nbsp;-";
			            		 else	   
			            			 return new Date(value).format("yyyy-MM-dd");
			            	 }
			             },
			             {field : "fetchTime", title : "分配时间", width : 150, align : "center",
			            	 formatter: function(value,row,index){
			            		 if(value==0)
			            			 return "-&nbsp;&nbsp;-";
			            		 else	   
			            			 return new Date(value).format("yyyy-MM-dd");
			            	 }
			             },
			             {field : "pactNumberLoan", title : "借贷合同", width : 100, align : "center" },
			             {field : "pactNumberInvest", title : "出借合同", width : 100, align : "center" }
			] ]
		});
}


/**
 * 查询
 */
$("#stockQueryForm_Btn").on("click", function(){
	var dataStr = $('#stockQueryForm').serialize();
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
	loadstock("stock!queryByPage.do", data);
});


/**
 * 刷新数据
 */
function reloadDataGrid(){
	loadstock("stock!queryByPage.do");
}