var tableObj;
var editRow = undefined; //定义全局变量：当前编辑的行
$(function(){
	disableToolBar();
	//隐藏显示查询条件区域
	$('#notificationList_open_close').on("click",function(){
		$('#notificationList_conditon').toggle(80);
		setTimeout(domresize,100);//条件隐藏，改变表格高度
	});
	//表格初始化
	tableObj = $("#notificationList_table").datagrid({
		height:$("#notificationList_body").height()-$('#notificationList_search_area').height()-5,
		width:$("#notificationList_body").width(),
		loadMsg : "正在加载，请稍后...",
		url:"notification!queryAllNotification.do",  
		singleSelect:true,  
		nowrap:true,
		rownumbers:true,
		pagination:true,
		fitColumns:true,
		pageSize:50,
		pageList:[50,80,100,150,200],
		sortOrder:'desc',
		toolbar:'#notificationList_toolbar',
		columns:[[
				{field:'id',checkbox:true},
				{field:'title',title:'标题',halign:"center", align:"left",width:300},
		        {field:'publishTime',title:'发布日期',halign:"center", align:"center",width:200},
		        {field:'beginDate',title:'有效期始',halign:"center", align:"center",width:200},
				{field:'endDate',title:'有效期至',halign:"center", align:"center",width:200},
		        {field:'content',title:'内容',halign:"center", align:"left",width:600},
		        {field:'status',title:'状态',halign:"center", align:"center",width:200,
					formatter: function(value,row,index){
						  if(value==1){
							   return '启动弹窗';
						  }else if(value==2 ){	   
							   return '通告列表内';
						  }else{
							  return '启动弹窗+通告列表内';
						  }
					 }
				}
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
	$('#querynotificationListForm').form({    
	    url:"notification!queryByParameter.do",    
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
	$('#notificationList-update-btn').linkbutton('disable');
}
function enableToolBar(){
	$('#notificationList-update-btn').linkbutton('enable');
}

//添加操作
$("#notificationList-add-btn").on('click',function(){
	indexLayer = layer.open({
		type: 2,
		title: '添加',
		shadeClose: true,
		shade: 0.8,
		area: ['450px', '55%'],
		content: 'notification!gotoAdd.do'
	});  
});

//修改操作
$("#notificationList-update-btn").on('click',function(){
	//判断是否选择
	if(!isSelectedRow()){
		layer.msg("请选择需要修改的理财券",{time:2000});
		return;
	}
	indexLayer = layer.open({
		type: 2,
		title: '修改',
		shadeClose: true,
		shade: 0.8,
		area: ['450px', '55%'],
		content: 'notification!gotoUpdate.do?id='+selectedRow.id
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
	$('#notificationList_table').datagrid('resize',{  
		height:$("#notificationList_body").height()-$('#notificationList_search_area').height()-5,
		width:$("#notificationList_body").width()
	});
}
