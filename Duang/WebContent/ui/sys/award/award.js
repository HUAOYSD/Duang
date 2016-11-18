/**
 * 初始化
 */
$(function() {
	$('#edit_btn_award').linkbutton('disable');
	loadaward("award!queryAllAward.do");
});

/**
 * 加载列表
 * @memberOf {TypeName} 
 * @return {TypeName} 
 */
function loadaward(url, dataObj){
	$('#award').datagrid({
			height:$("#body_award").height()-$('#award_search_area').height()-8,
			width:$("#body_award").width(),
			loadMsg : "正在加载，请稍后...",
			singleSelect:true, 
			nowrap:true,
			rownumbers:true,
			pagination:true,
			pageSize:50,
			pageList:[50,100,150,200,250],
			sortOrder:'desc',
			toolbar:'#tt_toolbar_award',
			method:'post',
			autoRowHeight : false,
			border : false,
			url :url,
			queryParams : dataObj,
			fitColumns : false,
			remoteSort : false,
			rowStyler: function(index,row){},
			columns : [ [
			             {field:'id',checkbox:true},
			             {field : "awardName", title : "奖品名称", width : 150, align : "center" },
			             {field : "description", title : "奖品描述", width : 400, align : "center" },
			] ],
			onSelect:function(rowIndex, rowData){
				$('#edit_btn_award').linkbutton('enable');
			},
			onUnselect:function(rowIndex, rowData){
				$('#edit_btn_award').linkbutton('disable');
			},
			onLoadSuccess: function(data){
				$('#edit_btn_award').linkbutton('disable');
			}
		});
}


/**
 * 刷新数据
 */
function reloadDataGrid(){
	loadaward("award!queryAllAward.do");
}


/**
 * 打开添加页面
 */
$("#add_btn_award").on("click",function(){
	layer.open({
		type: 2,
		title: '添加奖品种类',
		shadeClose: true,
		shade: 0.8,
		area: ['460px', '300px'],
		content: 'award!openDialog.do?path=add'
	});  
});


/**
 * 打开更改页面
 * @param {Object} memberId
 */
$("#edit_btn_award").on("click",function(){
	var selectedRow = $("#award").datagrid('getSelected');
	if(selectedRow==null){
		layer.msg("请选择一个奖品种类",{time:1500});
		return;
	}
	layer.open({
		type: 2,
		title: '编辑奖品',
		shadeClose: true,
		shade: 0.8,
		area: ['460px', '300px'],
		content: 'award!openDialog.do?path=edit&id='+selectedRow.id
	}); 
});

