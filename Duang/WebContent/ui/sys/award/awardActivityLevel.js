/**
 * 初始化
 */
$(function() {
	$('#edit_btn_awardActivityLevel').linkbutton('disable');
	$('#del_btn_awardActivityLevel').linkbutton('disable');
	loadawardActivityLevel("awardActivityLevel!queryByActivityId.do?activityId="+activityId);
});

/**
 * 加载列表
 * @memberOf {TypeName} 
 * @return {TypeName} 
 */
function loadawardActivityLevel(url, dataObj){
	$('#awardActivityLevel').datagrid({
			height:$("#body_awardActivityLevel").height()-$('#awardActivityLevel_search_area').height()-8,
			width:$("#body_awardActivityLevel").width(),
			loadMsg : "正在加载，请稍后...",
			singleSelect:true, 
			nowrap:true,
			rownumbers:true,
			pagination:true,
			pageSize:50,
			pageList:[50,100,150,200,250],
			sortOrder:'desc',
			toolbar:'#tt_toolbar_awardActivityLevel',
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
				             {field : "level_title", title : "等级名称", width : 150, align : "center" }
			 ]],
			columns : [ [
			             {field : "activityTitle", title : "属于活动", width : 150, align : "center" },
			             {field : "odds", title : "中奖率", width : 160, align : "center",
			            	 formatter: function(value,row,index){
			            		return "1/"+value;
			            	 }
			             },
			             {field : "winNumber", title : "已中奖次数", width : 150, align : "center" },
			             {field : "userName", title : "指定中奖人", width : 150, align : "center",
			            	 formatter: function(value,row,index){
				            		if(value ==null){
				            			return "-- --";
				            		}
				             }
			             },
			             {field : "onceNum", title : "单次奖品数", width : 150, align : "center" },
			             {field : "awardName", title : "奖品名称", width : 150, align : "center"}, 
			             {field : "awardNum", title : "奖品数", width : 150, align : "center"},
			             {field : "awardDescription", title : "奖品描述", width : 150, align : "center"},
			             {field : "winCode", title : "中奖号码", width : 600, align : "center"},
			             {field : "awardId", hidden:true},
			             {field : "awardActivityId", hidden:true}
			] ],
			onSelect:function(rowIndex, rowData){
				$('#edit_btn_awardActivityLevel').linkbutton('enable');
				$('#del_btn_awardActivityLevel').linkbutton('enable');
			},
			onUnselect:function(rowIndex, rowData){
				$('#edit_btn_awardActivityLevel').linkbutton('disable');
				$('#del_btn_awardActivityLevel').linkbutton('disable');
			},
			onLoadSuccess: function(data){
				$('#edit_btn_awardActivityLevel').linkbutton('disable');
				$('#del_btn_awardActivityLevel').linkbutton('disable');
			},
			onClickCell:function(rowIndex, field, value){
				if(field=="winCode"){
					layer.open({
						  title: '有奖的号码',content: value
					}); 
				}
			}
		});
}


/**
 * 刷新数据
 */
function reloadDataGrid(){
	loadawardActivityLevel("awardActivityLevel!queryByActivityId.do?activityId="+activityId);
}


/**
 * 打开添加页面
 */
$("#add_btn_awardActivityLevel").on("click",function(){
	layer.open({
		type: 2,
		title: '添加活动等级',
		shadeClose: true,
		shade: 0.8,
		area: ['500px', '350px'],
		content: 'awardActivityLevel!openDialog.do?path=add&activityId='+activityId
	});  
});

//删除操作
$("#del_btn_awardActivityLevel").on('click',function(){
	var selectedRow = $("#awardActivityLevel").datagrid('getSelected');
	if(selectedRow==null){
		layer.msg("请选择一个奖品等级",{time:1500});
		return;
	}
	layer.confirm('删除后无法恢复，您确定要删除吗？', {
  	  title :'警告',
		  icon: 7,
		  btn: ['确定','取消'] //按钮
		}, function(){ //确定
			$.messager.progress('close');	// 如果提交成功则隐藏进度条
			$.ajax({
				   type: "POST",
				   url: "awardActivityLevel!delete.do",
				   data: "id="+selectedRow.id,
				   success: function(data){
					 data = JSON.parse(data);
				     if(data.success==true){
				    	 reloadDataGrid();
				     }
				    layer.msg(data.msg, {time: 1000});
				   }
				});
		}, function(){//取消
		  return;
	}); 
});

/**
 * 打开更改页面
 * @param {Object} memberId
 */
$("#edit_btn_awardActivityLevel").on("click",function(){
	var selectedRow = $("#awardActivityLevel").datagrid('getSelected');
	if(selectedRow==null){
		layer.msg("请选择一个奖品等级",{time:1500});
		return;
	}
	layer.open({
		type: 2,
		title: '编辑等级',
		shadeClose: true,
		shade: 0.8,
		area: ['500px', '350px'],
		content: 'awardActivityLevel!openDialog.do?path=edit&id='+selectedRow.id
	}); 
});






