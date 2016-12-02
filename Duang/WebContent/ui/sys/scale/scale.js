/**
 * 初始化
 */
$(function() {
	$('#edit_btn_scale').linkbutton('disable');
	$('#info_btn_scale').linkbutton('disable');
	$('#allot_btn_scale').linkbutton('disable');
	$('#match_btn_scale').linkbutton('disable');
	$('#loan_btn_scale').linkbutton('disable');
	$('#scale_open_close').on("click",function(){
		$('#scale_conditon').toggle(80);
		setTimeout(domresize,100);//条件隐藏，改变表格高度
	});	
	loadscale("scale!queryScaleByPage.do");
});


/**
 * 改变表格宽高
 */
function domresize(){
	$('#scale').datagrid('resize',{  
		height:$("#body_scale").height()-$('#scale_search_area').height()-8,
		width:$("#body_scale").width()
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
function loadscale(url, dataObj){
	$('#scale').datagrid({
			height:$("#body_scale").height()-$('#scale_search_area').height()-8,
			width:$("#body_scale").width(),
			loadMsg : "正在加载，请稍后...",
			singleSelect:true, 
			nowrap:true,
			rownumbers:true,
			pagination:true,
			pageSize:50,
			pageList:[50,100,150,200,250],
			sortOrder:'desc',
			toolbar:'#tt_toolbar_scale',
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
				             {field : "name", title : "标名称", width : 115, align : "center" },
				             {field : "beginTime", title : "开标时间", width : 150, align : "center",
				            	 formatter: function(value,row,index){
				            		 if(value==0)
				            			 return "-&nbsp;&nbsp;-";
				            		 else	   
				            			 return new Date(value).format("yyyy-MM-dd");
				            	 }
				             },
				             {field : "endTime", title : "结标时间", width : 150, align : "center",
				            	 formatter: function(value,row,index){
				            		 if(value==0)
				            			 return "-&nbsp;&nbsp;-";
				            		 else	   
				            			 return new Date(value).format("yyyy-MM-dd");
				            	 }
				             }
			 ]],
			columns : [ [
			             {field : "productName", title : "产品", width : 120, align : "center" },
			             {field : "productCategory", title : "产品类型", width : 120, align : "center" },
			             {field : "productNew", title : "新产品", width : 120, align : "center" },
			             {field : "productRecommend", title : "推荐产品", width : 120, align : "center" },
			             {field : "timeLimit", title : "时长", width : 120, align : "center" },
			             {field : "revenue", title : "收益率", width : 130, align : "center" },
			             {field : "revenueAdd", title : "附收益率", width : 100, align : "center" },
			             {field : "maxLimit", title : "单笔限额", width : 100, align : "center" },
			             {field : "returnStyle", title : "还款方式", width : 100, align : "center" },
			             {field : "useTicket", title : "可理财券", width : 100, align : "center" },
			             {field : "totalMoney", title : "标总额", width : 100, align : "center" },
			             {field : "residueMoney", title : "可投金额", width : 100, align : "center" },
			             {field : "yetMoney", title : "已入金额", width : 100, align : "center" },
			             {field : "scoreBonus", title : "积分反馈", width : 100, align : "center" },
			             {field : "onesScore", title : "单位积分", width : 100, align : "center" },
			             {field : "status", title : "状态", width : 100, align : "center" },
			             {field : "transfer", title : "可转让", width : 100, align : "center" },
			             {field : "turnDate", title : "最早转让日期", width : 150, align : "center",
			            	 formatter: function(value,row,index){
			            		 if(value==0)
			            			 return "-&nbsp;&nbsp;-";
			            		 else	   
			            			 return new Date(value).format("yyyy-MM-dd");
			            	 }
			             },
			             {field : "isTurn", title : "转让标", width : 100, align : "center" },
			             {field : "calcBeginTime", title : "收益开始日", width : 150, align : "center",
			            	 formatter: function(value,row,index){
			            		 if(value==0)
			            			 return "-&nbsp;&nbsp;-";
			            		 else	   
			            			 return new Date(value).format("yyyy-MM-dd hh:mm:ss");
			            	 }
			             }, 
			             {field : "calcEndTime", title : "收益截止日", width : 150, align : "center",
			            	 formatter: function(value,row,index){
			            		 if(value==0)
			            			 return "-&nbsp;&nbsp;-";
			            		 else	   
			            			 return new Date(value).format("yyyy-MM-dd");
			            	 }
			             },
			             {field : "tags", title : "标签", width : 150, align : "center" }
			] ],
			onSelect:function(rowIndex, rowData){
				$('#edit_btn_scale').linkbutton('enable');
				$('#info_btn_scale').linkbutton('enable');
				$('#allot_btn_scale').linkbutton('enable');
				$('#match_btn_scale').linkbutton('enable');
				if(rowData.status =='满标'){
					$('#loan_btn_scale').linkbutton('enable');
				}else{
					$('#loan_btn_scale').linkbutton('disable');
				}
			},
			onUnselect:function(rowIndex, rowData){
				$('#edit_btn_scale').linkbutton('disable');
				$('#info_btn_scale').linkbutton('disable');
				$('#allot_btn_scale').linkbutton('disable');
				$('#match_btn_scale').linkbutton('disable');
				$('#loan_btn_scale').linkbutton('disable');
			},
			onLoadSuccess: function(data){
				$('#edit_btn_scale').linkbutton('disable');
				$('#info_btn_scale').linkbutton('disable');
				$('#allot_btn_scale').linkbutton('disable');
				$('#match_btn_scale').linkbutton('disable');
				$('#loan_btn_scale').linkbutton('disable');
			}
		});
}


/**
 * 查询
 */
$("#scaleQueryForm_Btn").on("click", function(){
	var dataStr = $('#scaleQueryForm').serialize();
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
	loadscale("scale!queryScaleByPage.do", data);
});


/**
 * 刷新数据
 */
function reloadDataGrid(){
	loadscale("scale!queryScaleByPage.do");
}


/**
 * 打开添加页面
 */
$("#add_btn_scale").on("click",function(){
	layer.open({
		type: 2,
		title: '添加理财标',
		shadeClose: true,
		shade: 0.8,
		area: ['460px', '85%'],
		content: 'scale!openDialog.do?path=add'
	});  
});


/**
 * 打开更改页面
 * @param {Object} memberId
 */
$("#edit_btn_scale").on("click",function(){
	var selectedRow = $("#scale").datagrid('getSelected');
	if(selectedRow==null){
		layer.msg("请选择一个理财标",{time:1500});
		return;
	}
	layer.open({
		type: 2,
		title: '编辑理财标',
		shadeClose: true,
		shade: 0.8,
		area: ['460px', '85%'],
		content: 'scale!openDialog.do?path=edit&id='+selectedRow.id
	}); 
});


/**
 * 打开详情页面 
 */
$("#info_btn_scale").on("click",function(){
	var selectedRow = $("#scale").datagrid('getSelected');
	if(selectedRow==null){
		layer.msg("请选择一个理财标",{time:1500});
		return;
	}
	layer.open({
		type: 2,
		title: '理财标详情',
		shadeClose: true,
		maxmin:true,
		shade: 0.8,
		area: ['600px', '85%'],
		content: 'scale!openDialog.do?path=info&id='+selectedRow.id
	}); 
});


/**
 * 打开借贷分配 
 */
$("#allot_btn_scale").on("click",function(){
	var selectedRow = $("#scale").datagrid('getSelected');
	if(selectedRow==null){
		layer.msg("请选择一个理财标",{time:1500});
		return;
	}else if("新建标"!=selectedRow.status){
		layer.msg("该标已分配，请查寻借贷匹配详情",{time:2000});
		return;
	}
	layer.open({
		type: 2,
		title: '借贷分配',
		shadeClose: true,
		maxmin:true,
		shade: 0.8,
		area: ['80%', '85%'],
		content: 'loanlist!openDialog.do?path=allot&scaleid='+selectedRow.id+
		"&begin_date="+new Date(selectedRow.beginTime).format("yyyy-MM-dd")+"&end_date="+new Date(selectedRow.endTime).format("yyyy-MM-dd")
	}); 
});


/**
 * 打开借贷记录详情
 */
$("#match_btn_scale").on("click",function(){
	var selectedRow = $("#scale").datagrid('getSelected');
	if(selectedRow==null){
		layer.msg("请选择一个理财标",{time:1500});
		return;
	}else if("新建标"==selectedRow.status){
		layer.msg("该标未分配，请分配借贷",{time:2000});
		return;
	}
	layer.open({
		type: 2,
		title: '借贷记录详情',
		shadeClose: true,
		maxmin:true,
		shade: 0.8,
		area: ['80%', '90%'],
		content: 'scale!openDialog.do?path=loanlistinfo&scaleid='+selectedRow.id
	}); 
});


/**
 * 放款
 */
$("#loan_btn_scale").on("click",function(){
	var selectedRow = $("#scale").datagrid('getSelected');
	if(selectedRow==null){
		layer.msg("请选择一个理财标",{time:1500});
		return;
	}else if(selectedRow.status != '满标'){
		layer.msg("不是满标，不能进行放款操作",{time:1500});
		return;
	}
	//选择放款人
	layer.open({
		type: 2,
		title: '选择放款人<span style="font-size:12px;color:#f72143;">&nbsp;&nbsp;请填写选择对象的放款金额</span>',
		shadeClose: true,
		maxmin:true,
		shade: 0.8,
		area: ['50%', '80%'],
		content: 'memberMiddle!openDialog.do?path=selectUser&scaleId='+selectedRow.id
	}); 
});
