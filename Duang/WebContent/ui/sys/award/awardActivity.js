/**
 * 初始化
 */
$(function() {
	$('#edit_btn_awardActivity').linkbutton('disable');
	$('#info_btn_awardActivity').linkbutton('disable');
	$('#awardActivity_open_close').on("click",function(){
		$('#awardActivity_conditon').toggle(80);
		setTimeout(domresize,100);//条件隐藏，改变表格高度
	});	
	loadawardActivity("awardActivity!queryAll.do");
});


/**
 * 改变表格宽高
 */
function domresize(){
	$('#awardActivity').datagrid('resize',{  
		height:$("#body_awardActivity").height()-$('#awardActivity_search_area').height()-8,
		width:$("#body_awardActivity").width()
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
function loadawardActivity(url, dataObj){
	$('#awardActivity').datagrid({
			height:$("#body_awardActivity").height()-$('#awardActivity_search_area').height()-8,
			width:$("#body_awardActivity").width(),
			loadMsg : "正在加载，请稍后...",
			singleSelect:true, 
			nowrap:true,
			rownumbers:true,
			pagination:true,
			pageSize:50,
			pageList:[50,100,150,200,250],
			sortOrder:'desc',
			toolbar:'#tt_toolbar_awardActivity',
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
				             {field : "activity_title", title : "活动名称", width : 400, align : "center" }
			 ]],
			columns : [ [
			             {field : "repeat", title : "重复抽奖", width : 150, align : "center",
			            	 formatter: function(value,row,index){
			            		 if(value==0)
			            			 return "不可重复";
			            		 else	   
			            			 return "可重复";
			            	 }
			             },
			             {field : "repeatNum", title : "重复次数", width : 150, align : "center",
			            	 formatter: function(value,row,index){
			            		 if(value==0)
			            			 return "无限次";
			            		 else	   
			            			 return value;
			            	 }
			             },
			             {field : "nowNumber", title : "目前抽奖总次数", width : 160, align : "center" },
			             {field : "winNumber", title : "中奖总次数", width : 150, align : "center" },
			             {field : "code", title : "活动编码", width : 150, align : "center" },
			             {field : "createTime", title : "创建日期", width : 150, align : "center",
			            	 formatter: function(value,row,index){
			            		 if(value==0)
			            			 return "-&nbsp;&nbsp;-";
			            		 else	   
			            			 return new Date(value).format("yyyy-MM-dd");
			            	 }
			             },
			             {field : "startTime", title : "活动开始日期", width : 150, align : "center",
			            	 formatter: function(value,row,index){
			            		 if(value==0)
			            			 return "-&nbsp;&nbsp;-";
			            		 else	   
			            			 return new Date(value).format("yyyy-MM-dd");
			            	 }
			             }, 
			             {field : "endTime", title : "活动截止日期", width : 150, align : "center",
			            	 formatter: function(value,row,index){
			            		 if(value==0)
			            			 return "-&nbsp;&nbsp;-";
			            		 else	   
			            			 return new Date(value).format("yyyy-MM-dd");
			            	 }
			             }
			] ],
			onSelect:function(rowIndex, rowData){
				$('#edit_btn_awardActivity').linkbutton('enable');
				$('#info_btn_awardActivity').linkbutton('enable');
			},
			onUnselect:function(rowIndex, rowData){
				$('#edit_btn_awardActivity').linkbutton('disable');
				$('#info_btn_awardActivity').linkbutton('disable');
			},
			onLoadSuccess: function(data){
				$('#edit_btn_awardActivity').linkbutton('disable');
				$('#info_btn_awardActivity').linkbutton('disable');
			}
		});
}


/**
 * 查询
 */
$("#awardActivityQueryForm_Btn").on("click", function(){
	var dataStr = $('#awardActivityQueryForm').serialize();
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
	loadawardActivity("awardActivity!queryawardActivityByPage.do", data);
});


/**
 * 刷新数据
 */
function reloadDataGrid(){
	loadawardActivity("awardActivity!queryAll.do");
}


/**
 * 打开添加页面
 */
$("#add_btn_awardActivity").on("click",function(){
	layer.open({
		type: 2,
		title: '添加活动',
		shadeClose: true,
		shade: 0.8,
		area: ['460px', '460px'],
		content: 'awardActivity!openDialog.do?path=add'
	});  
});


/**
 * 打开更改页面
 * @param {Object} memberId
 */
$("#edit_btn_awardActivity").on("click",function(){
	var selectedRow = $("#awardActivity").datagrid('getSelected');
	if(selectedRow==null){
		layer.msg("请选择一个活动",{time:1500});
		return;
	}
	layer.open({
		type: 2,
		title: '编辑活动',
		shadeClose: true,
		shade: 0.8,
		area: ['460px', '460px'],
		content: 'awardActivity!openDialog.do?path=edit&id='+selectedRow.id
	}); 
});


/**
 * 打开等级详情页面
 */
$("#info_btn_awardActivity").on("click",function(){
	var selectedRow = $("#awardActivity").datagrid('getSelected');
	if(selectedRow==null){
		layer.msg("请选择一个活动",{time:1500});
		return;
	}
	layer.open({
		type: 2,
		title: '等级详情',
		shadeClose: true,
		maxmin:true,
		shade: 0.8,
		area: ['90%', '85%'],
		content: 'awardActivityLevel!gotolist.do?activityId='+selectedRow.id
	}); 
});

/**
 * 打开奖品种类管理
 */
$("#award_btn_awardActivity").on("click",function(){
	layer.open({
		type: 2,
		title: '奖品种类管理',
		shadeClose: true,
		maxmin:true,
		shade: 0.8,
		area: ['650px', '600px'],
		content: 'award!gotolist.do?'
	}); 
});

