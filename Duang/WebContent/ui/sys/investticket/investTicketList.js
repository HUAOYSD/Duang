var tableObj;
var editRow = undefined; //定义全局变量：当前编辑的行
$(function(){
	disableToolBar();
	//隐藏显示查询条件区域
	$('#investTicketList_open_close').on("click",function(){
		$('#investTicketList_conditon').toggle(80);
		setTimeout(domresize,100);//条件隐藏，改变表格高度
	});
	//表格初始化
	tableObj = $("#investTicketList_table").datagrid({
		height:$("#investTicketList_body").height()-$('#investTicketList_search_area').height()-5,
		width:$("#investTicketList_body").width(),
		loadMsg : "正在加载，请稍后...",
		url:"investticket!queryAllInvestTicket.do",  
		singleSelect:true,  
		nowrap:true,
		rownumbers:true,
		pagination:true,
		fitColumns:true,
		pageSize:50,
		pageList:[50,80,100,150,200],
		sortOrder:'desc',
		toolbar:'#investTicketList_toolbar',
		columns:[[
				{field:'id',checkbox:true},
				{field:'name',title:'名称',halign:"center", align:"center",width:200},
				{field:'state',title:'状态',halign:"center", align:"center",width:200,
					formatter: function(value,row,index){
						  if(value==0){
							   return '<font color="#ffffff">使用</font>';
						  }else{	   
							   return '<font color="#ffffff">过期</font>';
						  }
					 },
					 styler: function(value,row,index){  
			              if (value == 0){  
			                  return 'background-color:#85cebf';  
			              }else{  
			                  return 'background-color:#f88193';  
			              }  
			         }
				},
		        {field:'remark',title:'说明',halign:"center", align:"center",width:300},  
		        {field:'money',title:'价值',halign:"center", align:"center",width:100}, 
				{field:'beginTime',title:'有效期始',halign:"center", align:"center",width:200},
				{field:'endTime',title:'有效期至',halign:"center", align:"center",width:200},
				{field:'createTime',title:'派发时间',halign:"center", align:"center",width:200},
				{field:'minMoney',title:'最小投资金额权限',halign:"center", align:"center",width:150},
				{field:'describes',title:'详细描述',halign:"center", align:"center",width:600 }
		]],
		onSelect:function(rowIndex, rowData){
			enableToolBar();
		},
		onUnselect:function(rowIndex, rowData){
			disableToolBar();
		},
		onLoadSuccess: function(data){
			disableToolBar();
		}
	});
	$('#queryinvestTicketListForm').form({    
	    url:"investticket!queryBandCardByParameter.do",    
	    onSubmit: function(){    
	        
	    },    
	    success:function(data){ 
	    	data = JSON.parse(data);
	    	if(data.result==false){
	    		layer.msg(data.msg,{time:2000});
	    	}
	    	tableObj.datagrid('loadData', {
	    		"rows":data.rows,
	    		"total":data.total,
	    	}); 
	    }    
	});
});

//判断是否选择行了
var selectedRow = null; 
function isSelectedRow(){
	selectedRow = tableObj.datagrid("getSelected");
	if(selectedRow==null){
		return false;
	}
	return true;
}

function disableToolBar(){
	$('#investTicketList-update-btn').linkbutton('disable');
	$('#investTicketList-delete-btn').linkbutton('disable');
	$('#investTicketList-select-InvestProduct-btn').linkbutton('disable');
}
function enableToolBar(){
	$('#investTicketList-update-btn').linkbutton('enable');
	$('#investTicketList-delete-btn').linkbutton('enable');
	$('#investTicketList-select-InvestProduct-btn').linkbutton('enable');
}

//添加操作
$("#investTicketList-add-btn").on('click',function(){
	indexLayer = layer.open({
		type: 2,
		title: '添加理财券',
		shadeClose: true,
		shade: 0.8,
		area: ['450px', '55%'],
		content: 'investticket!gotoAddInvestTicket.do'
	});  
});

//修改操作
$("#investTicketList-update-btn").on('click',function(){
	//判断是否选择
	if(!isSelectedRow()){
		layer.msg("请选择需要修改的理财券",{time:2000});
		return;
	}
	indexLayer = layer.open({
		type: 2,
		title: '修改理财券',
		shadeClose: true,
		shade: 0.8,
		area: ['450px', '55%'],
		content: 'investticket!gotoUpdateInvestTicket.do?id='+selectedRow.id
	});  
});

//删除操作
$("#investTicketList-delete-btn").on('click',function(){
	//判断是否选择
	if(!isSelectedRow()){
		layer.msg("请选择理财券",{time:2000});
		return;
	}
	layer.confirm('修改后无法恢复，您确定要修改 '+selectedRow.name+' 的状态吗？', {
  	  title :'警告',
		  icon: 7,
		  btn: ['确定','取消'] //按钮
		}, function(){ //确定
			$.messager.progress('close');	// 如果提交成功则隐藏进度条
			$.ajax({
				   type: "POST",
				   url: "investticket!deleteInvestTicket.do",
				   data: "id="+selectedRow.id,
				   success: function(data){
					 data = JSON.parse(data);
				     if(data.result==true){
				    	 reloadDataGrid();
				    	 layer.closeAll();
				     }
				    layer.msg(data.msg, {time: 1000});
				   }
				});
		}, function(){//取消
		  return;
	}); 
});

//可使用产品
$("#investTicketList-select-InvestProduct-btn").on('click',function(){
	//判断是否选择
	if(!isSelectedRow()){
		layer.msg("请选择需要修改的理财券",{time:2000});
		return;
	}
	indexLayer = layer.open({
		id:'selectProToTicket',
		type: 2,
		skin: 'layui-layer-lan',
		title: '选择理财券适用于的产品',
		shadeClose: true,
		shade: 0.8,
		closeBtn: 1,
		area: ['98%', '97%'],
		content: 'investticket!gotoSelectProToTicket.do?id='+selectedRow.id
	}); 
});


function reloadDataGrid(){
	tableObj.datagrid('reload');  
}

//监听窗口大小变化
window.onresize = function(){
	setTimeout(domresize,300);
};
//改变表格宽高
function domresize(){
	$('#investTicketList_table').datagrid('resize',{  
		height:$("#investTicketList_body").height()-$('#investTicketList_search_area').height()-5,
		width:$("#investTicketList_body").width()
	});
}
